/*     */ package thaumcraft.codechicken.core.launch;
/*     */ 
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import cpw.mods.fml.common.versioning.ComparableVersion;
/*     */ import cpw.mods.fml.relauncher.FMLLaunchHandler;
/*     */ import cpw.mods.fml.relauncher.IFMLCallHook;
/*     */ import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import java.awt.Desktop;
/*     */ import java.awt.Dialog.ModalityType;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.InterruptedIOException;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.Field;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
/*     */ import java.net.URLClassLoader;
/*     */ import java.net.URLConnection;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import java.util.regex.PatternSyntaxException;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipFile;
/*     */ import javax.swing.BoundedRangeModel;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JEditorPane;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JProgressBar;
/*     */ import javax.swing.event.HyperlinkEvent;
/*     */ import javax.swing.event.HyperlinkEvent.EventType;
/*     */ import javax.swing.event.HyperlinkListener;
/*     */ import net.minecraft.launchwrapper.LaunchClassLoader;
/*     */ import sun.misc.URLClassPath;
/*     */ import sun.net.util.URLUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DepLoader
/*     */   implements IFMLLoadingPlugin, IFMLCallHook
/*     */ {
/*  66 */   private static ByteBuffer downloadBuffer = ByteBuffer.allocateDirect(8388608);
/*     */   private static final String owner = "CB's DepLoader";
/*     */   private static DepLoadInst inst;
/*     */   
/*     */   public static abstract interface IDownloadDisplay
/*     */   {
/*     */     public abstract void resetProgress(int paramInt);
/*     */     
/*     */     public abstract void setPokeThread(Thread paramThread);
/*     */     
/*     */     public abstract void updateProgress(int paramInt);
/*     */     
/*     */     public abstract boolean shouldStopIt();
/*     */     
/*     */     public abstract void updateProgressString(String paramString, Object... paramVarArgs);
/*     */     
/*     */     public abstract Object makeDialog();
/*     */     
/*     */     public abstract void showErrorDialog(String paramString1, String paramString2);
/*     */   }
/*     */   
/*     */   public static class Downloader extends JOptionPane implements DepLoader.IDownloadDisplay
/*     */   {
/*     */     private JDialog container;
/*     */     private JLabel currentActivity;
/*     */     private JProgressBar progress;
/*     */     boolean stopIt;
/*     */     Thread pokeThread;
/*     */     
/*     */     private Box makeProgressPanel() {
/*  96 */       Box box = Box.createVerticalBox();
/*  97 */       box.add(Box.createRigidArea(new Dimension(0, 10)));
/*  98 */       JLabel welcomeLabel = new JLabel("<html><b><font size='+1'>CB's DepLoader is setting up your minecraft environment</font></b></html>");
/*  99 */       box.add(welcomeLabel);
/* 100 */       welcomeLabel.setAlignmentY(0.0F);
/* 101 */       welcomeLabel = new JLabel("<html>Please wait, CB's DepLoader has some tasks to do before you can play</html>");
/* 102 */       welcomeLabel.setAlignmentY(0.0F);
/* 103 */       box.add(welcomeLabel);
/* 104 */       box.add(Box.createRigidArea(new Dimension(0, 10)));
/* 105 */       this.currentActivity = new JLabel("Currently doing ...");
/* 106 */       box.add(this.currentActivity);
/* 107 */       box.add(Box.createRigidArea(new Dimension(0, 10)));
/* 108 */       this.progress = new JProgressBar(0, 100);
/* 109 */       this.progress.setStringPainted(true);
/* 110 */       box.add(this.progress);
/* 111 */       box.add(Box.createRigidArea(new Dimension(0, 30)));
/* 112 */       return box;
/*     */     }
/*     */     
/*     */     public JDialog makeDialog()
/*     */     {
/* 117 */       if (this.container != null) {
/* 118 */         return this.container;
/*     */       }
/* 120 */       setMessageType(1);
/* 121 */       setMessage(makeProgressPanel());
/* 122 */       setOptions(new Object[] { "Stop" });
/* 123 */       addPropertyChangeListener(new PropertyChangeListener()
/*     */       {
/*     */         public void propertyChange(PropertyChangeEvent evt) {
/* 126 */           if ((evt.getSource() == DepLoader.Downloader.this) && (evt.getPropertyName() == "value")) {
/* 127 */             DepLoader.Downloader.this.requestClose("This will stop minecraft from launching\nAre you sure you want to do this?");
/*     */           }
/*     */         }
/* 130 */       });
/* 131 */       this.container = new JDialog(null, "Hello", Dialog.ModalityType.MODELESS);
/* 132 */       this.container.setResizable(false);
/* 133 */       this.container.setLocationRelativeTo(null);
/* 134 */       this.container.add(this);
/* 135 */       updateUI();
/* 136 */       this.container.pack();
/* 137 */       this.container.setMinimumSize(this.container.getPreferredSize());
/* 138 */       this.container.setVisible(true);
/* 139 */       this.container.setDefaultCloseOperation(0);
/* 140 */       this.container.addWindowListener(new WindowAdapter()
/*     */       {
/*     */         public void windowClosing(WindowEvent e) {
/* 143 */           DepLoader.Downloader.this.requestClose("Closing this window will stop minecraft from launching\nAre you sure you wish to do this?");
/*     */         }
/* 145 */       });
/* 146 */       return this.container;
/*     */     }
/*     */     
/*     */     protected void requestClose(String message) {
/* 150 */       int shouldClose = JOptionPane.showConfirmDialog(this.container, message, "Are you sure you want to stop?", 0, 2);
/* 151 */       if (shouldClose == 0) {
/* 152 */         this.container.dispose();
/*     */       }
/* 154 */       this.stopIt = true;
/* 155 */       if (this.pokeThread != null) {
/* 156 */         this.pokeThread.interrupt();
/*     */       }
/*     */     }
/*     */     
/*     */     public void updateProgressString(String progressUpdate, Object... data)
/*     */     {
/* 162 */       if (this.currentActivity != null) {
/* 163 */         this.currentActivity.setText(String.format(progressUpdate, data));
/*     */       }
/*     */     }
/*     */     
/*     */     public void resetProgress(int sizeGuess) {
/* 168 */       if (this.progress != null) {
/* 169 */         this.progress.getModel().setRangeProperties(0, 0, 0, sizeGuess, false);
/*     */       }
/*     */     }
/*     */     
/*     */     public void updateProgress(int fullLength) {
/* 174 */       if (this.progress != null) {
/* 175 */         this.progress.getModel().setValue(fullLength);
/*     */       }
/*     */     }
/*     */     
/*     */     public void setPokeThread(Thread currentThread) {
/* 180 */       this.pokeThread = currentThread;
/*     */     }
/*     */     
/*     */     public boolean shouldStopIt()
/*     */     {
/* 185 */       return this.stopIt;
/*     */     }
/*     */     
/*     */     public void showErrorDialog(String name, String url)
/*     */     {
/* 190 */       JEditorPane ep = new JEditorPane("text/html", "<html>CB's DepLoader was unable to download required library " + name + "<br>Check your internet connection and try restarting or download it manually from" + "<br><a href=\"" + url + "\">" + url + "</a> and put it in your mods folder" + "</html>");
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 197 */       ep.setEditable(false);
/* 198 */       ep.setOpaque(false);
/* 199 */       ep.addHyperlinkListener(new HyperlinkListener()
/*     */       {
/*     */         public void hyperlinkUpdate(HyperlinkEvent event) {
/*     */           try {
/* 203 */             if (event.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED)) {
/* 204 */               Desktop.getDesktop().browse(event.getURL().toURI());
/*     */             }
/*     */           }
/*     */           catch (Exception e) {}
/*     */         }
/* 209 */       });
/* 210 */       JOptionPane.showMessageDialog(null, ep, "A download error has occured", 0);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static class DummyDownloader
/*     */     implements DepLoader.IDownloadDisplay
/*     */   {
/*     */     public void resetProgress(int sizeGuess) {}
/*     */     
/*     */ 
/*     */     public void setPokeThread(Thread currentThread) {}
/*     */     
/*     */ 
/*     */     public void updateProgress(int fullLength) {}
/*     */     
/*     */ 
/*     */     public boolean shouldStopIt()
/*     */     {
/* 229 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */     public void updateProgressString(String string, Object... data) {}
/*     */     
/*     */ 
/*     */     public Object makeDialog()
/*     */     {
/* 238 */       return null;
/*     */     }
/*     */     
/*     */ 
/*     */     public void showErrorDialog(String name, String url) {}
/*     */   }
/*     */   
/*     */   public static class VersionedFile
/*     */   {
/*     */     public final Pattern pattern;
/*     */     public final String filename;
/*     */     public final ComparableVersion version;
/*     */     public final String name;
/*     */     
/*     */     public VersionedFile(String filename, Pattern pattern)
/*     */     {
/* 254 */       this.pattern = pattern;
/* 255 */       this.filename = filename;
/* 256 */       Matcher m = pattern.matcher(filename);
/* 257 */       if (m.matches()) {
/* 258 */         this.name = m.group(1);
/* 259 */         this.version = new ComparableVersion(m.group(2));
/*     */       }
/*     */       else {
/* 262 */         this.name = null;
/* 263 */         this.version = null;
/*     */       }
/*     */     }
/*     */     
/*     */     public boolean matches() {
/* 268 */       return this.name != null;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static class Dependency
/*     */   {
/*     */     public String url;
/*     */     
/*     */     public DepLoader.VersionedFile file;
/*     */     
/*     */     public String existing;
/*     */     public boolean coreLib;
/*     */     
/*     */     public Dependency(String url, DepLoader.VersionedFile file, boolean coreLib)
/*     */     {
/* 284 */       this.url = url;
/* 285 */       this.file = file;
/* 286 */       this.coreLib = coreLib;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class DepLoadInst
/*     */   {
/*     */     private File modsDir;
/*     */     private File v_modsDir;
/*     */     private DepLoader.IDownloadDisplay downloadMonitor;
/*     */     private JDialog popupWindow;
/* 296 */     private Map<String, DepLoader.Dependency> depMap = new HashMap();
/* 297 */     private HashSet<String> depSet = new HashSet();
/*     */     
/*     */     public DepLoadInst() {
/* 300 */       String mcVer = (String)cpw.mods.fml.relauncher.FMLInjectionData.data()[4];
/* 301 */       File mcDir = (File)cpw.mods.fml.relauncher.FMLInjectionData.data()[6];
/*     */       
/* 303 */       this.modsDir = new File(mcDir, "mods");
/* 304 */       this.v_modsDir = new File(mcDir, "mods/" + mcVer);
/* 305 */       if (!this.v_modsDir.exists())
/* 306 */         this.v_modsDir.mkdirs();
/*     */     }
/*     */     
/*     */     private void addClasspath(String name) {
/*     */       try {
/* 311 */         ((LaunchClassLoader)DepLoader.class.getClassLoader()).addURL(new File(this.v_modsDir, name).toURI().toURL());
/*     */       } catch (MalformedURLException e) {
/* 313 */         throw new RuntimeException(e);
/*     */       }
/*     */     }
/*     */     
/*     */     private void deleteMod(File mod) {
/* 318 */       if (mod.delete()) {
/* 319 */         return;
/*     */       }
/*     */       try {
/* 322 */         ClassLoader cl = DepLoader.class.getClassLoader();
/* 323 */         URL url = mod.toURI().toURL();
/* 324 */         Field f_ucp = URLClassLoader.class.getDeclaredField("ucp");
/* 325 */         Field f_loaders = URLClassPath.class.getDeclaredField("loaders");
/* 326 */         Field f_lmap = URLClassPath.class.getDeclaredField("lmap");
/* 327 */         f_ucp.setAccessible(true);
/* 328 */         f_loaders.setAccessible(true);
/* 329 */         f_lmap.setAccessible(true);
/*     */         
/* 331 */         URLClassPath ucp = (URLClassPath)f_ucp.get(cl);
/* 332 */         Closeable loader = (Closeable)((Map)f_lmap.get(ucp)).remove(URLUtil.urlNoFragString(url));
/* 333 */         if (loader != null) {
/* 334 */           loader.close();
/* 335 */           ((List)f_loaders.get(ucp)).remove(loader);
/*     */         }
/*     */       } catch (Exception e) {
/* 338 */         e.printStackTrace();
/*     */       }
/*     */       
/* 341 */       if (!mod.delete()) {
/* 342 */         mod.deleteOnExit();
/* 343 */         String msg = "CB's DepLoader was unable to delete file " + mod.getPath() + " the game will now try to delete it on exit. If this dialog appears again, delete it manually.";
/* 344 */         System.err.println(msg);
/* 345 */         if (!GraphicsEnvironment.isHeadless()) {
/* 346 */           JOptionPane.showMessageDialog(null, msg, "An update error has occured", 0);
/*     */         }
/* 348 */         System.exit(1);
/*     */       }
/*     */     }
/*     */     
/*     */     private void download(DepLoader.Dependency dep) {
/* 353 */       this.popupWindow = ((JDialog)this.downloadMonitor.makeDialog());
/* 354 */       File libFile = new File(this.v_modsDir, dep.file.filename);
/*     */       try {
/* 356 */         URL libDownload = new URL(dep.url + '/' + dep.file.filename);
/* 357 */         this.downloadMonitor.updateProgressString("Downloading file %s", new Object[] { libDownload.toString() });
/* 358 */         System.out.format("Downloading file %s\n", new Object[] { libDownload.toString() });
/* 359 */         URLConnection connection = libDownload.openConnection();
/* 360 */         connection.setConnectTimeout(5000);
/* 361 */         connection.setReadTimeout(5000);
/* 362 */         connection.setRequestProperty("User-Agent", "CB's DepLoader Downloader");
/* 363 */         int sizeGuess = connection.getContentLength();
/* 364 */         download(connection.getInputStream(), sizeGuess, libFile);
/* 365 */         this.downloadMonitor.updateProgressString("Download complete", new Object[0]);
/* 366 */         System.out.println("Download complete");
/*     */         
/* 368 */         scanDepInfo(libFile);
/*     */       } catch (Exception e) {
/* 370 */         libFile.delete();
/* 371 */         if (this.downloadMonitor.shouldStopIt()) {
/* 372 */           System.err.println("You have stopped the downloading operation before it could complete");
/* 373 */           System.exit(1);
/* 374 */           return;
/*     */         }
/* 376 */         this.downloadMonitor.showErrorDialog(dep.file.filename, dep.url + '/' + dep.file.filename);
/* 377 */         throw new RuntimeException("A download error occured", e);
/*     */       }
/*     */     }
/*     */     
/*     */     private void download(InputStream is, int sizeGuess, File target) throws Exception {
/* 382 */       if (sizeGuess > DepLoader.downloadBuffer.capacity()) {
/* 383 */         throw new Exception(String.format("The file %s is too large to be downloaded by CB's DepLoader - the download is invalid", new Object[] { target.getName() }));
/*     */       }
/* 385 */       DepLoader.downloadBuffer.clear();
/*     */       
/* 387 */       int fullLength = 0;
/*     */       
/* 389 */       this.downloadMonitor.resetProgress(sizeGuess);
/*     */       try {
/* 391 */         this.downloadMonitor.setPokeThread(Thread.currentThread());
/* 392 */         byte[] smallBuffer = new byte['Ѐ'];
/* 393 */         int bytesRead; while ((bytesRead = is.read(smallBuffer)) >= 0) {
/* 394 */           DepLoader.downloadBuffer.put(smallBuffer, 0, bytesRead);
/* 395 */           fullLength += bytesRead;
/* 396 */           if (this.downloadMonitor.shouldStopIt()) {
/*     */             break;
/*     */           }
/* 399 */           this.downloadMonitor.updateProgress(fullLength);
/*     */         }
/* 401 */         is.close();
/* 402 */         this.downloadMonitor.setPokeThread(null);
/* 403 */         DepLoader.downloadBuffer.limit(fullLength);
/* 404 */         DepLoader.downloadBuffer.position(0);
/*     */       }
/*     */       catch (InterruptedIOException e) {
/* 407 */         Thread.interrupted();
/* 408 */         throw new Exception("Stop");
/*     */       } catch (IOException e) {
/* 410 */         throw e;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */       try
/*     */       {
/* 417 */         if (!target.exists()) {
/* 418 */           target.createNewFile();
/*     */         }
/*     */         
/* 421 */         DepLoader.downloadBuffer.position(0);
/* 422 */         FileOutputStream fos = new FileOutputStream(target);
/* 423 */         fos.getChannel().write(DepLoader.downloadBuffer);
/* 424 */         fos.close();
/*     */ 
/*     */ 
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/*     */ 
/* 431 */         throw e;
/*     */       }
/*     */     }
/*     */     
/*     */     private String checkExisting(DepLoader.Dependency dep) {
/* 436 */       for (File f : this.modsDir.listFiles()) {
/* 437 */         DepLoader.VersionedFile vfile = new DepLoader.VersionedFile(f.getName(), dep.file.pattern);
/* 438 */         if ((vfile.matches()) && (vfile.name.equals(dep.file.name)))
/*     */         {
/*     */ 
/* 441 */           if (!f.renameTo(new File(this.v_modsDir, f.getName())))
/*     */           {
/*     */ 
/* 444 */             deleteMod(f); }
/*     */         }
/*     */       }
/* 447 */       for (File f : this.v_modsDir.listFiles()) {
/* 448 */         DepLoader.VersionedFile vfile = new DepLoader.VersionedFile(f.getName(), dep.file.pattern);
/* 449 */         if ((vfile.matches()) && (vfile.name.equals(dep.file.name)))
/*     */         {
/*     */ 
/* 452 */           int cmp = vfile.version.compareTo(dep.file.version);
/* 453 */           if (cmp < 0) {
/* 454 */             System.out.println("Deleted old version " + f.getName());
/* 455 */             deleteMod(f);
/* 456 */             return null;
/*     */           }
/* 458 */           if (cmp > 0) {
/* 459 */             System.err.println("Warning: version of " + dep.file.name + ", " + vfile.version + " is newer than request " + dep.file.version);
/* 460 */             return f.getName();
/*     */           }
/* 462 */           return f.getName();
/*     */         } }
/* 464 */       return null;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public void load()
/*     */     {
/* 471 */       scanDepInfos();
/* 472 */       if (this.depMap.isEmpty()) {
/* 473 */         return;
/*     */       }
/* 475 */       loadDeps();
/* 476 */       activateDeps();
/*     */     }
/*     */     
/*     */     private void activateDeps() {
/* 480 */       for (DepLoader.Dependency dep : this.depMap.values())
/* 481 */         if (dep.coreLib)
/* 482 */           addClasspath(dep.existing);
/*     */     }
/*     */     
/*     */     private void loadDeps() {
/* 486 */       this.downloadMonitor = (FMLLaunchHandler.side().isClient() ? new DepLoader.Downloader() : new DepLoader.DummyDownloader());
/*     */       try {
/* 488 */         while (!this.depSet.isEmpty()) {
/* 489 */           Iterator<String> it = this.depSet.iterator();
/* 490 */           DepLoader.Dependency dep = (DepLoader.Dependency)this.depMap.get(it.next());
/* 491 */           it.remove();
/* 492 */           load(dep);
/*     */         }
/*     */       } finally {
/* 495 */         if (this.popupWindow != null) {
/* 496 */           this.popupWindow.setVisible(false);
/* 497 */           this.popupWindow.dispose();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     private void load(DepLoader.Dependency dep) {
/* 503 */       dep.existing = checkExisting(dep);
/* 504 */       if ((dep.existing == null) && (dep.file.name.equalsIgnoreCase("baubles")))
/*     */       {
/* 506 */         download(dep);
/* 507 */         dep.existing = dep.file.filename;
/*     */       }
/*     */     }
/*     */     
/*     */     private List<File> modFiles() {
/* 512 */       List<File> list = new LinkedList();
/* 513 */       list.addAll(Arrays.asList(this.modsDir.listFiles()));
/* 514 */       list.addAll(Arrays.asList(this.v_modsDir.listFiles()));
/* 515 */       return list;
/*     */     }
/*     */     
/*     */     private void scanDepInfosWeb()
/*     */     {
/*     */       try {
/* 521 */         InputStream input = new URL("https://dl.dropboxusercontent.com/u/47135879/dependancies.info").openStream();
/* 522 */         if (input != null)
/* 523 */           loadJSon(input);
/*     */       } catch (Exception e) {
/* 525 */         System.err.println("Failed to load dependencies.info from https://dl.dropboxusercontent.com/u/47135879/dependancies.info as JSON");
/* 526 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     
/*     */     private void scanDepInfos() {
/* 531 */       for (File file : modFiles()) {
/* 532 */         if ((file.getName().endsWith(".jar")) || (file.getName().endsWith(".zip")))
/*     */         {
/*     */ 
/* 535 */           scanDepInfo(file); }
/*     */       }
/*     */     }
/*     */     
/*     */     private void scanDepInfo(File file) {
/*     */       try {
/* 541 */         ZipFile zip = new ZipFile(file);
/* 542 */         ZipEntry e = zip.getEntry("dependancies.info");
/* 543 */         if (e == null) e = zip.getEntry("dependencies.info");
/* 544 */         if (e != null)
/* 545 */           loadJSon(zip.getInputStream(e));
/* 546 */         zip.close();
/*     */       }
/*     */       catch (Exception e) {
/* 549 */         System.err.println("Failed to load dependencies.info from " + file.getName() + " as JSON");
/* 550 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     
/*     */     private void loadJSon(InputStream input) throws IOException {
/* 555 */       InputStreamReader reader = new InputStreamReader(input);
/* 556 */       JsonElement root = new JsonParser().parse(reader);
/* 557 */       if (root.isJsonArray()) {
/* 558 */         loadJSonArr(root);
/*     */       } else
/* 560 */         loadJson(root.getAsJsonObject());
/* 561 */       reader.close();
/*     */     }
/*     */     
/*     */     private void loadJSonArr(JsonElement root) throws IOException {
/* 565 */       for (JsonElement node : root.getAsJsonArray()) {
/* 566 */         loadJson(node.getAsJsonObject());
/*     */       }
/*     */     }
/*     */     
/*     */     private void loadJson(JsonObject node) throws IOException {
/* 571 */       boolean obfuscated = ((LaunchClassLoader)DepLoader.class.getClassLoader()).getClassBytes("net.minecraft.world.World") == null;
/*     */       
/*     */ 
/*     */ 
/* 575 */       String testClass = node.get("class").getAsString();
/* 576 */       if (DepLoader.class.getResource("/" + testClass.replace('.', '/') + ".class") != null) {
/* 577 */         return;
/*     */       }
/*     */       
/* 580 */       String repo = node.get("repo").getAsString();
/* 581 */       String filename = node.get("file").getAsString();
/* 582 */       if ((!obfuscated) && (node.has("dev"))) {
/* 583 */         filename = node.get("dev").getAsString();
/*     */       }
/*     */       
/* 586 */       boolean coreLib = (node.has("coreLib")) && (node.get("coreLib").getAsBoolean());
/*     */       
/*     */ 
/* 589 */       Pattern pattern = null;
/*     */       try {
/* 591 */         if (node.has("pattern"))
/* 592 */           pattern = Pattern.compile(node.get("pattern").getAsString());
/*     */       } catch (PatternSyntaxException e) {
/* 594 */         System.err.println("Invalid filename pattern: " + node.get("pattern"));
/* 595 */         e.printStackTrace();
/*     */       }
/* 597 */       if (pattern == null) {
/* 598 */         pattern = Pattern.compile("(\\w+).*?([\\d\\.]+)[-\\w]*\\.[^\\d]+");
/*     */       }
/*     */       
/* 601 */       DepLoader.VersionedFile file = new DepLoader.VersionedFile(filename, pattern);
/* 602 */       if (!file.matches()) {
/* 603 */         throw new RuntimeException("Invalid filename format for dependency: " + filename);
/*     */       }
/*     */       
/* 606 */       addDep(new DepLoader.Dependency(repo, file, coreLib));
/*     */     }
/*     */     
/*     */     private void addDep(DepLoader.Dependency newDep)
/*     */     {
/* 611 */       if (mergeNew((DepLoader.Dependency)this.depMap.get(newDep.file.name), newDep)) {
/* 612 */         this.depMap.put(newDep.file.name, newDep);
/* 613 */         this.depSet.add(newDep.file.name);
/*     */       }
/*     */     }
/*     */     
/*     */     private boolean mergeNew(DepLoader.Dependency oldDep, DepLoader.Dependency newDep) {
/* 618 */       if (oldDep == null) {
/* 619 */         return true;
/*     */       }
/* 621 */       DepLoader.Dependency newest = newDep.file.version.compareTo(oldDep.file.version) > 0 ? newDep : oldDep;
/* 622 */       newest.coreLib = ((newDep.coreLib) || (oldDep.coreLib));
/*     */       
/* 624 */       return newest == newDep;
/*     */     }
/*     */   }
/*     */   
/*     */   public static void load() {
/* 629 */     if (inst == null) {
/* 630 */       inst = new DepLoadInst();
/* 631 */       inst.load();
/*     */     }
/*     */   }
/*     */   
/*     */   public String[] getASMTransformerClass()
/*     */   {
/* 637 */     return null;
/*     */   }
/*     */   
/*     */   public String getModContainerClass()
/*     */   {
/* 642 */     return null;
/*     */   }
/*     */   
/*     */   public String getSetupClass()
/*     */   {
/* 647 */     return getClass().getName();
/*     */   }
/*     */   
/*     */ 
/*     */   public void injectData(Map<String, Object> data) {}
/*     */   
/*     */ 
/*     */   public Void call()
/*     */   {
/* 656 */     load();
/*     */     
/* 658 */     return null;
/*     */   }
/*     */   
/*     */   public String getAccessTransformerClass()
/*     */   {
/* 663 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/core/launch/DepLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */