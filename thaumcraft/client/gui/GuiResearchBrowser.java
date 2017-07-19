/*      */ package thaumcraft.client.gui;
/*      */ 
/*      */ import cpw.mods.fml.client.FMLClientHandler;
/*      */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*      */ import cpw.mods.fml.relauncher.SideOnly;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*      */ import net.minecraft.client.gui.FontRenderer;
/*      */ import net.minecraft.client.gui.GuiButton;
/*      */ import net.minecraft.client.gui.GuiScreen;
/*      */ import net.minecraft.client.renderer.RenderHelper;
/*      */ import net.minecraft.client.renderer.Tessellator;
/*      */ import net.minecraft.client.renderer.entity.RenderItem;
/*      */ import net.minecraft.client.renderer.texture.TextureManager;
/*      */ import net.minecraft.client.settings.GameSettings;
/*      */ import net.minecraft.client.settings.KeyBinding;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.util.ChatComponentTranslation;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.StatCollector;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldProvider;
/*      */ import org.lwjgl.input.Mouse;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ import thaumcraft.api.ThaumcraftApi;
/*      */ import thaumcraft.api.aspects.Aspect;
/*      */ import thaumcraft.api.aspects.AspectList;
/*      */ import thaumcraft.api.research.ResearchCategories;
/*      */ import thaumcraft.api.research.ResearchCategoryList;
/*      */ import thaumcraft.api.research.ResearchItem;
/*      */ import thaumcraft.client.fx.ParticleEngine;
/*      */ import thaumcraft.client.lib.UtilsFX;
/*      */ import thaumcraft.client.renderers.tile.TileNodeRenderer;
/*      */ import thaumcraft.common.CommonProxy;
/*      */ import thaumcraft.common.Thaumcraft;
/*      */ import thaumcraft.common.config.Config;
/*      */ import thaumcraft.common.lib.network.PacketHandler;
/*      */ import thaumcraft.common.lib.network.playerdata.PacketPlayerCompleteToServer;
/*      */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*      */ import thaumcraft.common.lib.research.ResearchManager;
/*      */ import thaumcraft.common.lib.utils.InventoryUtils;
/*      */ 
/*      */ @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*      */ public class GuiResearchBrowser extends GuiScreen
/*      */ {
/*      */   private static int guiMapTop;
/*      */   private static int guiMapLeft;
/*      */   private static int guiMapBottom;
/*      */   private static int guiMapRight;
/*   61 */   protected int paneWidth = 256;
/*   62 */   protected int paneHeight = 230;
/*      */   
/*      */ 
/*   65 */   protected int mouseX = 0;
/*      */   
/*      */ 
/*   68 */   protected int mouseY = 0;
/*      */   
/*      */   protected double field_74117_m;
/*      */   
/*      */   protected double field_74115_n;
/*      */   
/*      */   protected double guiMapX;
/*      */   
/*      */   protected double guiMapY;
/*      */   
/*      */   protected double field_74124_q;
/*      */   
/*      */   protected double field_74123_r;
/*   81 */   private int isMouseButtonDown = 0;
/*      */   
/*   83 */   public static int lastX = -5;
/*   84 */   public static int lastY = -6;
/*      */   
/*      */   private GuiButton button;
/*   87 */   private LinkedList<ResearchItem> research = new LinkedList();
/*   88 */   public static HashMap<String, ArrayList<String>> completedResearch = new HashMap();
/*   89 */   public static ArrayList<String> highlightedItem = new ArrayList();
/*   90 */   private static String selectedCategory = null;
/*      */   
/*      */   private FontRenderer galFontRenderer;
/*   93 */   private ResearchItem currentHighlight = null;
/*   94 */   private String player = "";
/*   95 */   long popuptime = 0L;
/*   96 */   String popupmessage = "";
/*      */   
/*      */   public GuiResearchBrowser()
/*      */   {
/*  100 */     short var2 = 141;
/*  101 */     short var3 = 141;
/*  102 */     this.field_74117_m = (this.guiMapX = this.field_74124_q = lastX * 24 - var2 / 2 - 12);
/*  103 */     this.field_74115_n = (this.guiMapY = this.field_74123_r = lastY * 24 - var3 / 2);
/*  104 */     updateResearch();
/*  105 */     this.galFontRenderer = FMLClientHandler.instance().getClient().field_71464_q;
/*  106 */     this.player = Minecraft.func_71410_x().field_71439_g.func_70005_c_();
/*      */   }
/*      */   
/*      */ 
/*      */   public GuiResearchBrowser(double x, double y)
/*      */   {
/*  112 */     this.field_74117_m = (this.guiMapX = this.field_74124_q = x);
/*  113 */     this.field_74115_n = (this.guiMapY = this.field_74123_r = y);
/*  114 */     updateResearch();
/*  115 */     this.galFontRenderer = FMLClientHandler.instance().getClient().field_71464_q;
/*  116 */     this.player = Minecraft.func_71410_x().field_71439_g.func_70005_c_();
/*      */   }
/*      */   
/*  119 */   public boolean hasScribestuff = false;
/*      */   
/*      */   public void updateResearch() {
/*  122 */     if (this.field_146297_k == null) this.field_146297_k = Minecraft.func_71410_x();
/*  123 */     this.research.clear();
/*  124 */     this.hasScribestuff = false;
/*  125 */     if (selectedCategory == null) {
/*  126 */       Collection cats = ResearchCategories.researchCategories.keySet();
/*  127 */       selectedCategory = (String)cats.iterator().next();
/*      */     }
/*      */     
/*  130 */     Collection col = ResearchCategories.getResearchList(selectedCategory).research.values();
/*  131 */     for (Object res : col)
/*      */     {
/*  133 */       this.research.add((ResearchItem)res);
/*      */     }
/*      */     
/*      */ 
/*  137 */     if ((ResearchManager.consumeInkFromPlayer(this.field_146297_k.field_71439_g, false)) && (InventoryUtils.isPlayerCarrying(this.field_146297_k.field_71439_g, new ItemStack(Items.field_151121_aF)) >= 0))
/*      */     {
/*  139 */       this.hasScribestuff = true;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  145 */     guiMapTop = ResearchCategories.getResearchList(selectedCategory).minDisplayColumn * 24 - 85;
/*  146 */     guiMapLeft = ResearchCategories.getResearchList(selectedCategory).minDisplayRow * 24 - 112;
/*  147 */     guiMapBottom = ResearchCategories.getResearchList(selectedCategory).maxDisplayColumn * 24 - 112;
/*  148 */     guiMapRight = ResearchCategories.getResearchList(selectedCategory).maxDisplayRow * 24 - 61;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void func_146281_b()
/*      */   {
/*  155 */     short var2 = 141;
/*  156 */     short var3 = 141;
/*  157 */     lastX = (int)((this.guiMapX + var2 / 2 + 12.0D) / 24.0D);
/*  158 */     lastY = (int)((this.guiMapY + var3 / 2) / 24.0D);
/*      */     
/*      */ 
/*      */ 
/*  162 */     super.func_146281_b();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void func_73866_w_() {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void func_146284_a(GuiButton par1GuiButton)
/*      */   {
/*  197 */     super.func_146284_a(par1GuiButton);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void func_73869_a(char par1, int par2)
/*      */   {
/*  205 */     if (par2 == this.field_146297_k.field_71474_y.field_151445_Q.func_151463_i())
/*      */     {
/*  207 */       highlightedItem.clear();
/*  208 */       this.field_146297_k.func_147108_a((GuiScreen)null);
/*  209 */       this.field_146297_k.func_71381_h();
/*      */     }
/*      */     else
/*      */     {
/*  213 */       if (par2 == 1)
/*      */       {
/*  215 */         highlightedItem.clear();
/*      */       }
/*  217 */       super.func_73869_a(par1, par2);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void func_73863_a(int mx, int my, float par3)
/*      */   {
/*  226 */     int var4 = (this.field_146294_l - this.paneWidth) / 2;
/*  227 */     int var5 = (this.field_146295_m - this.paneHeight) / 2;
/*      */     
/*      */ 
/*  230 */     if (Mouse.isButtonDown(0))
/*      */     {
/*  232 */       int var6 = var4 + 8;
/*  233 */       int var7 = var5 + 17;
/*      */       
/*  235 */       if (((this.isMouseButtonDown == 0) || (this.isMouseButtonDown == 1)) && (mx >= var6) && (mx < var6 + 224) && (my >= var7) && (my < var7 + 196))
/*      */       {
/*  237 */         if (this.isMouseButtonDown == 0)
/*      */         {
/*  239 */           this.isMouseButtonDown = 1;
/*      */         }
/*      */         else
/*      */         {
/*  243 */           this.guiMapX -= mx - this.mouseX;
/*  244 */           this.guiMapY -= my - this.mouseY;
/*  245 */           this.field_74124_q = (this.field_74117_m = this.guiMapX);
/*  246 */           this.field_74123_r = (this.field_74115_n = this.guiMapY);
/*      */         }
/*      */         
/*  249 */         this.mouseX = mx;
/*  250 */         this.mouseY = my;
/*      */       }
/*      */       
/*  253 */       if (this.field_74124_q < guiMapTop)
/*      */       {
/*  255 */         this.field_74124_q = guiMapTop;
/*      */       }
/*      */       
/*  258 */       if (this.field_74123_r < guiMapLeft)
/*      */       {
/*  260 */         this.field_74123_r = guiMapLeft;
/*      */       }
/*      */       
/*  263 */       if (this.field_74124_q >= guiMapBottom)
/*      */       {
/*  265 */         this.field_74124_q = (guiMapBottom - 1);
/*      */       }
/*      */       
/*  268 */       if (this.field_74123_r >= guiMapRight)
/*      */       {
/*  270 */         this.field_74123_r = (guiMapRight - 1);
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*  275 */       this.isMouseButtonDown = 0;
/*      */     }
/*      */     
/*      */ 
/*  279 */     func_146276_q_();
/*  280 */     genResearchBackground(mx, my, par3);
/*      */     
/*  282 */     if (this.popuptime > System.currentTimeMillis()) {
/*  283 */       int xq = var4 + 128;
/*  284 */       int yq = var5 + 128;
/*  285 */       int var41 = this.field_146289_q.func_78267_b(this.popupmessage, 150) / 2;
/*  286 */       func_73733_a(xq - 78, yq - var41 - 3, xq + 78, yq + var41 + 3, -1073741824, -1073741824);
/*  287 */       this.field_146289_q.func_78279_b(this.popupmessage, xq - 75, yq - var41, 150, -7302913);
/*      */     }
/*      */     
/*  290 */     Collection cats = ResearchCategories.researchCategories.keySet();
/*  291 */     int count = 0;
/*  292 */     boolean swop = false;
/*  293 */     for (Object obj : cats) {
/*  294 */       if (count == 9) {
/*  295 */         count = 0;
/*  296 */         swop = true;
/*      */       }
/*  298 */       ResearchCategoryList rcl = ResearchCategories.getResearchList((String)obj);
/*  299 */       if ((!((String)obj).equals("ELDRITCH")) || (ResearchManager.isResearchComplete(this.player, "ELDRITCHMINOR")))
/*      */       {
/*  301 */         int mposx = mx - (var4 - 24 + (swop ? 280 : 0));
/*  302 */         int mposy = my - (var5 + count * 24);
/*  303 */         if ((mposx >= 0) && (mposx < 24) && (mposy >= 0) && (mposy < 24))
/*      */         {
/*  305 */           this.field_146289_q.func_78261_a(ResearchCategories.getCategoryName((String)obj), mx, my - 8, 16777215);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*  310 */         count++;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void func_73876_c()
/*      */   {
/*  321 */     this.field_74117_m = this.guiMapX;
/*  322 */     this.field_74115_n = this.guiMapY;
/*  323 */     double var1 = this.field_74124_q - this.guiMapX;
/*  324 */     double var3 = this.field_74123_r - this.guiMapY;
/*      */     
/*  326 */     if (var1 * var1 + var3 * var3 < 4.0D)
/*      */     {
/*  328 */       this.guiMapX += var1;
/*  329 */       this.guiMapY += var3;
/*      */     }
/*      */     else
/*      */     {
/*  333 */       this.guiMapX += var1 * 0.85D;
/*  334 */       this.guiMapY += var3 * 0.85D;
/*      */     }
/*      */   }
/*      */   
/*      */   protected void genResearchBackground(int par1, int par2, float par3)
/*      */   {
/*  340 */     long t = System.nanoTime() / 50000000L;
/*      */     
/*  342 */     int var4 = MathHelper.func_76128_c(this.field_74117_m + (this.guiMapX - this.field_74117_m) * par3);
/*  343 */     int var5 = MathHelper.func_76128_c(this.field_74115_n + (this.guiMapY - this.field_74115_n) * par3);
/*      */     
/*  345 */     if (var4 < guiMapTop)
/*      */     {
/*  347 */       var4 = guiMapTop;
/*      */     }
/*      */     
/*  350 */     if (var5 < guiMapLeft)
/*      */     {
/*  352 */       var5 = guiMapLeft;
/*      */     }
/*      */     
/*  355 */     if (var4 >= guiMapBottom)
/*      */     {
/*  357 */       var4 = guiMapBottom - 1;
/*      */     }
/*      */     
/*  360 */     if (var5 >= guiMapRight)
/*      */     {
/*  362 */       var5 = guiMapRight - 1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  367 */     int var8 = (this.field_146294_l - this.paneWidth) / 2;
/*  368 */     int var9 = (this.field_146295_m - this.paneHeight) / 2;
/*  369 */     int var10 = var8 + 16;
/*  370 */     int var11 = var9 + 17;
/*  371 */     this.field_73735_i = 0.0F;
/*  372 */     GL11.glDepthFunc(518);
/*  373 */     GL11.glPushMatrix();
/*  374 */     GL11.glTranslatef(0.0F, 0.0F, -200.0F);
/*  375 */     GL11.glEnable(3553);
/*  376 */     RenderHelper.func_74520_c();
/*  377 */     GL11.glDisable(2896);
/*  378 */     GL11.glEnable(32826);
/*  379 */     GL11.glEnable(2903);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  385 */     GL11.glPushMatrix();
/*  386 */     GL11.glScalef(2.0F, 2.0F, 1.0F);
/*  387 */     int vx = (int)((var4 - guiMapTop) / Math.abs(guiMapTop - guiMapBottom) * 288.0F);
/*  388 */     int vy = (int)((var5 - guiMapLeft) / Math.abs(guiMapLeft - guiMapRight) * 316.0F);
/*  389 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  390 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(ResearchCategories.getResearchList(selectedCategory).background);
/*      */     
/*  392 */     func_73729_b(var10 / 2, var11 / 2, vx / 2, vy / 2, 112, 98);
/*  393 */     GL11.glScalef(0.5F, 0.5F, 1.0F);
/*  394 */     GL11.glPopMatrix();
/*      */     
/*  396 */     GL11.glEnable(2929);
/*  397 */     GL11.glDepthFunc(515);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  402 */     if (completedResearch.get(this.player) != null) {
/*  403 */       for (int var22 = 0; var22 < this.research.size(); var22++)
/*      */       {
/*  405 */         ResearchItem var33 = (ResearchItem)this.research.get(var22);
/*      */         
/*  407 */         if ((var33.parents != null) && (var33.parents.length > 0))
/*      */         {
/*  409 */           for (int a = 0; a < var33.parents.length; a++) {
/*  410 */             if ((var33.parents[a] != null) && (ResearchCategories.getResearch(var33.parents[a]).category.equals(selectedCategory)))
/*      */             {
/*  412 */               ResearchItem parent = ResearchCategories.getResearch(var33.parents[a]);
/*  413 */               if (!parent.isVirtual()) {
/*  414 */                 int var24 = var33.displayColumn * 24 - var4 + 11 + var10;
/*  415 */                 int var25 = var33.displayRow * 24 - var5 + 11 + var11;
/*  416 */                 int var26 = parent.displayColumn * 24 - var4 + 11 + var10;
/*  417 */                 int var27 = parent.displayRow * 24 - var5 + 11 + var11;
/*      */                 
/*  419 */                 boolean var28 = ((ArrayList)completedResearch.get(this.player)).contains(var33.key);
/*  420 */                 boolean var29 = ((ArrayList)completedResearch.get(this.player)).contains(parent.key);
/*      */                 
/*  422 */                 int var30 = Math.sin(Minecraft.func_71386_F() % 600L / 600.0D * 3.141592653589793D * 2.0D) > 0.6D ? 255 : 130;
/*      */                 
/*  424 */                 if (var28)
/*      */                 {
/*  426 */                   drawLine(var24, var25, var26, var27, 0.1F, 0.1F, 0.1F, par3, false);
/*      */                 }
/*  428 */                 else if ((!var33.isLost()) && (
/*  429 */                   ((!var33.isHidden()) && (!var33.isLost())) || ((((ArrayList)completedResearch.get(this.player)).contains("@" + var33.key)) && (
/*  430 */                   (!var33.isConcealed()) || (canUnlockResearch(var33)))))) {
/*  431 */                   if (var29)
/*      */                   {
/*  433 */                     drawLine(var24, var25, var26, var27, 0.0F, 1.0F, 0.0F, par3, true);
/*      */                   }
/*  435 */                   else if (((!parent.isHidden()) && (!var33.isLost())) || ((((ArrayList)completedResearch.get(this.player)).contains("@" + parent.key)) && ((!parent.isConcealed()) || (canUnlockResearch(parent)))))
/*      */                   {
/*  437 */                     drawLine(var24, var25, var26, var27, 0.0F, 0.0F, 1.0F, par3, true); }
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*  443 */         if ((var33.siblings != null) && (var33.siblings.length > 0))
/*      */         {
/*  445 */           for (int a = 0; a < var33.siblings.length; a++) {
/*  446 */             if ((var33.siblings[a] != null) && (ResearchCategories.getResearch(var33.siblings[a]).category.equals(selectedCategory))) {
/*  447 */               ResearchItem sibling = ResearchCategories.getResearch(var33.siblings[a]);
/*  448 */               if ((!sibling.isVirtual()) && (
/*  449 */                 (sibling.parents == null) || ((sibling.parents != null) && (!Arrays.asList(sibling.parents).contains(var33.key)))))
/*      */               {
/*      */ 
/*  452 */                 int var24 = var33.displayColumn * 24 - var4 + 11 + var10;
/*  453 */                 int var25 = var33.displayRow * 24 - var5 + 11 + var11;
/*  454 */                 int var26 = sibling.displayColumn * 24 - var4 + 11 + var10;
/*  455 */                 int var27 = sibling.displayRow * 24 - var5 + 11 + var11;
/*      */                 
/*  457 */                 boolean var28 = ((ArrayList)completedResearch.get(this.player)).contains(var33.key);
/*  458 */                 boolean var29 = ((ArrayList)completedResearch.get(this.player)).contains(sibling.key);
/*      */                 
/*      */ 
/*      */ 
/*  462 */                 if (var28)
/*      */                 {
/*  464 */                   drawLine(var24, var25, var26, var27, 0.1F, 0.1F, 0.2F, par3, false);
/*      */                 }
/*  466 */                 else if ((!var33.isLost()) && 
/*  467 */                   ((!var33.isHidden()) || (((ArrayList)completedResearch.get(this.player)).contains("@" + var33.key))) && (
/*  468 */                   (!var33.isConcealed()) || (canUnlockResearch(var33)))) {
/*  469 */                   if (var29)
/*      */                   {
/*  471 */                     drawLine(var24, var25, var26, var27, 0.0F, 1.0F, 0.0F, par3, true);
/*      */                   }
/*  473 */                   else if (((!sibling.isHidden()) || (((ArrayList)completedResearch.get(this.player)).contains("@" + sibling.key))) && ((!sibling.isConcealed()) || (canUnlockResearch(sibling))))
/*      */                   {
/*  475 */                     drawLine(var24, var25, var26, var27, 0.0F, 0.0F, 1.0F, par3, true);
/*      */                   }
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  484 */     this.currentHighlight = null;
/*  485 */     RenderItem itemRenderer = new RenderItem();
/*      */     
/*  487 */     GL11.glEnable(32826);
/*  488 */     GL11.glEnable(2903);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  493 */     if (completedResearch.get(this.player) != null) {
/*  494 */       for (int var24 = 0; var24 < this.research.size(); var24++)
/*      */       {
/*  496 */         ResearchItem var35 = (ResearchItem)this.research.get(var24);
/*  497 */         int var26 = var35.displayColumn * 24 - var4;
/*  498 */         int var27 = var35.displayRow * 24 - var5;
/*      */         
/*  500 */         if ((!var35.isVirtual()) && (var26 >= -24) && (var27 >= -24) && (var26 <= 224) && (var27 <= 196))
/*      */         {
/*      */ 
/*  503 */           int var42 = var10 + var26;
/*  504 */           int var41 = var11 + var27;
/*      */           
/*  506 */           if (((ArrayList)completedResearch.get(this.player)).contains(var35.key))
/*      */           {
/*  508 */             if (ThaumcraftApi.getWarp(var35.key) > 0) {
/*  509 */               drawForbidden(var42 + 11, var41 + 11);
/*      */             }
/*  511 */             float var38 = 1.0F;
/*  512 */             GL11.glColor4f(var38, var38, var38, 1.0F);
/*      */           }
/*      */           else {
/*  515 */             if ((!((ArrayList)completedResearch.get(this.player)).contains("@" + var35.key)) && (
/*      */             
/*      */ 
/*      */ 
/*  519 */               (var35.isLost()) || 
/*  520 */               ((var35.isHidden()) && (!((ArrayList)completedResearch.get(this.player)).contains("@" + var35.key))) || (
/*  521 */               (var35.isConcealed()) && (!canUnlockResearch(var35))))) {
/*      */               continue;
/*      */             }
/*  524 */             if (ThaumcraftApi.getWarp(var35.key) > 0) {
/*  525 */               drawForbidden(var42 + 11, var41 + 11);
/*      */             }
/*      */             
/*  528 */             if (canUnlockResearch(var35))
/*      */             {
/*  530 */               float var38 = (float)Math.sin(Minecraft.func_71386_F() % 600L / 600.0D * 3.141592653589793D * 2.0D) * 0.25F + 0.75F;
/*  531 */               GL11.glColor4f(var38, var38, var38, 1.0F);
/*      */             }
/*      */             else
/*      */             {
/*  535 */               float var38 = 0.3F;
/*  536 */               GL11.glColor4f(var38, var38, var38, 1.0F);
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  543 */           UtilsFX.bindTexture("textures/gui/gui_research.png");
/*      */           
/*  545 */           GL11.glEnable(2884);
/*  546 */           GL11.glEnable(3042);
/*  547 */           GL11.glBlendFunc(770, 771);
/*      */           
/*      */ 
/*      */ 
/*  551 */           if (var35.isRound())
/*      */           {
/*  553 */             func_73729_b(var42 - 2, var41 - 2, 54, 230, 26, 26);
/*      */ 
/*      */           }
/*  556 */           else if (var35.isHidden())
/*      */           {
/*  558 */             if ((Config.researchDifficulty == -1) || ((Config.researchDifficulty == 0) && (var35.isSecondary()))) {
/*  559 */               func_73729_b(var42 - 2, var41 - 2, 230, 230, 26, 26);
/*      */             } else {
/*  561 */               func_73729_b(var42 - 2, var41 - 2, 86, 230, 26, 26);
/*      */             }
/*      */             
/*      */ 
/*      */           }
/*  566 */           else if ((Config.researchDifficulty == -1) || ((Config.researchDifficulty == 0) && (var35.isSecondary())))
/*      */           {
/*  568 */             func_73729_b(var42 - 2, var41 - 2, 110, 230, 26, 26);
/*      */           }
/*      */           else
/*      */           {
/*  572 */             func_73729_b(var42 - 2, var41 - 2, 0, 230, 26, 26);
/*      */           }
/*      */           
/*  575 */           if (var35.isSpecial())
/*      */           {
/*  577 */             func_73729_b(var42 - 2, var41 - 2, 26, 230, 26, 26);
/*      */           }
/*      */           
/*      */ 
/*  581 */           if (!canUnlockResearch(var35))
/*      */           {
/*  583 */             float var40 = 0.1F;
/*  584 */             GL11.glColor4f(var40, var40, var40, 1.0F);
/*  585 */             itemRenderer.field_77024_a = false;
/*      */           }
/*  587 */           GL11.glDisable(3042);
/*      */           
/*  589 */           if (highlightedItem.contains(var35.key)) {
/*  590 */             GL11.glPushMatrix();
/*  591 */             GL11.glEnable(3042);
/*  592 */             GL11.glBlendFunc(770, 771);
/*  593 */             GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  594 */             this.field_146297_k.field_71446_o.func_110577_a(ParticleEngine.particleTexture);
/*  595 */             int px = (int)(t % 16L) * 16;
/*  596 */             GL11.glTranslatef(var42 - 5, var41 - 5, 0.0F);
/*      */             
/*  598 */             UtilsFX.drawTexturedQuad(0, 0, px, 80, 16, 16, 0.0D);
/*  599 */             GL11.glDisable(3042);
/*  600 */             GL11.glPopMatrix();
/*      */           }
/*      */           
/*  603 */           if (var35.icon_item != null) {
/*  604 */             GL11.glPushMatrix();
/*  605 */             GL11.glEnable(3042);
/*  606 */             GL11.glBlendFunc(770, 771);
/*  607 */             RenderHelper.func_74520_c();
/*  608 */             GL11.glDisable(2896);
/*  609 */             GL11.glEnable(32826);
/*  610 */             GL11.glEnable(2903);
/*  611 */             GL11.glEnable(2896);
/*  612 */             itemRenderer.func_82406_b(this.field_146289_q, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(var35.icon_item), var42 + 3, var41 + 3);
/*      */             
/*  614 */             GL11.glDisable(2896);
/*  615 */             GL11.glDepthMask(true);
/*  616 */             GL11.glEnable(2929);
/*  617 */             GL11.glDisable(3042);
/*  618 */             GL11.glPopMatrix();
/*      */           }
/*  620 */           else if (var35.icon_resource != null) {
/*  621 */             GL11.glPushMatrix();
/*  622 */             GL11.glEnable(3042);
/*  623 */             GL11.glBlendFunc(770, 771);
/*  624 */             this.field_146297_k.field_71446_o.func_110577_a(var35.icon_resource);
/*  625 */             if (!itemRenderer.field_77024_a) {
/*  626 */               GL11.glColor4f(0.2F, 0.2F, 0.2F, 1.0F);
/*      */             }
/*  628 */             UtilsFX.drawTexturedQuadFull(var42 + 3, var41 + 3, this.field_73735_i);
/*  629 */             GL11.glPopMatrix();
/*      */           }
/*      */           
/*      */ 
/*  633 */           if (!canUnlockResearch(var35))
/*      */           {
/*  635 */             itemRenderer.field_77024_a = true;
/*      */           }
/*      */           
/*  638 */           if ((par1 >= var10) && (par2 >= var11) && (par1 < var10 + 224) && (par2 < var11 + 196) && (par1 >= var42) && (par1 <= var42 + 22) && (par2 >= var41) && (par2 <= var41 + 22))
/*      */           {
/*  640 */             this.currentHighlight = var35;
/*      */           }
/*  642 */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */         }
/*      */       }
/*      */     }
/*  646 */     GL11.glDisable(2929);
/*  647 */     GL11.glEnable(3042);
/*  648 */     GL11.glBlendFunc(770, 771);
/*  649 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */     
/*  651 */     Collection cats = ResearchCategories.researchCategories.keySet();
/*  652 */     int count = 0;
/*  653 */     boolean swop = false;
/*  654 */     for (Object obj : cats) {
/*  655 */       ResearchCategoryList rcl = ResearchCategories.getResearchList((String)obj);
/*      */       
/*  657 */       if ((!((String)obj).equals("ELDRITCH")) || (ResearchManager.isResearchComplete(this.player, "ELDRITCHMINOR")))
/*      */       {
/*      */ 
/*  660 */         GL11.glPushMatrix();
/*      */         
/*  662 */         if (count == 9) {
/*  663 */           count = 0;
/*  664 */           swop = true;
/*      */         }
/*  666 */         int s0 = !swop ? 0 : 264;
/*  667 */         int s1 = 0;
/*  668 */         int s2 = swop ? 14 : 0;
/*  669 */         if (!selectedCategory.equals((String)obj)) {
/*  670 */           s1 = 24;
/*  671 */           s2 = swop ? 6 : 8;
/*      */         }
/*      */         
/*  674 */         UtilsFX.bindTexture("textures/gui/gui_research.png");
/*  675 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  676 */         if (swop) {
/*  677 */           drawTexturedModalRectReversed(var8 + s0 - 8, var9 + count * 24, 176 + s1, 232, 24, 24);
/*      */         } else {
/*  679 */           func_73729_b(var8 - 24 + s0, var9 + count * 24, 152 + s1, 232, 24, 24);
/*      */         }
/*      */         
/*  682 */         if (highlightedItem.contains((String)obj)) {
/*  683 */           GL11.glPushMatrix();
/*      */           
/*  685 */           this.field_146297_k.field_71446_o.func_110577_a(ParticleEngine.particleTexture);
/*  686 */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  687 */           int px = (int)(16L * (t % 16L));
/*  688 */           UtilsFX.drawTexturedQuad(var8 - 27 + s2 + s0, var9 - 4 + count * 24, px, 80, 16, 16, -90.0D);
/*  689 */           GL11.glPopMatrix();
/*      */         }
/*      */         
/*  692 */         GL11.glPushMatrix();
/*  693 */         this.field_146297_k.field_71446_o.func_110577_a(rcl.icon);
/*  694 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */         
/*  696 */         UtilsFX.drawTexturedQuadFull(var8 - 19 + s2 + s0, var9 + 4 + count * 24, -80.0D);
/*  697 */         GL11.glPopMatrix();
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  705 */         if (!selectedCategory.equals((String)obj)) {
/*  706 */           UtilsFX.bindTexture("textures/gui/gui_research.png");
/*  707 */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  708 */           if (swop) {
/*  709 */             drawTexturedModalRectReversed(var8 + s0 - 8, var9 + count * 24, 224, 232, 24, 24);
/*      */           } else {
/*  711 */             func_73729_b(var8 - 24 + s0, var9 + count * 24, 200, 232, 24, 24);
/*      */           }
/*      */         }
/*      */         
/*  715 */         GL11.glPopMatrix();
/*  716 */         count++;
/*      */       }
/*      */     }
/*  719 */     UtilsFX.bindTexture("textures/gui/gui_research.png");
/*  720 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  721 */     func_73729_b(var8, var9, 0, 0, this.paneWidth, this.paneHeight);
/*      */     
/*  723 */     GL11.glPopMatrix();
/*      */     
/*  725 */     this.field_73735_i = 0.0F;
/*  726 */     GL11.glDepthFunc(515);
/*  727 */     GL11.glDisable(2929);
/*  728 */     GL11.glEnable(3553);
/*      */     
/*  730 */     super.func_73863_a(par1, par2, par3);
/*      */     
/*  732 */     if ((completedResearch.get(this.player) != null) && 
/*  733 */       (this.currentHighlight != null))
/*      */     {
/*  735 */       String var34 = this.currentHighlight.getName();
/*  736 */       int var26 = par1 + 6;
/*  737 */       int var27 = par2 - 4;
/*  738 */       int var99 = 0;
/*  739 */       FontRenderer fr = this.field_146289_q;
/*  740 */       if ((!((ArrayList)completedResearch.get(this.player)).contains(this.currentHighlight.key)) && (!canUnlockResearch(this.currentHighlight)))
/*      */       {
/*  742 */         fr = this.galFontRenderer; }
/*  743 */       if (canUnlockResearch(this.currentHighlight))
/*      */       {
/*  745 */         boolean secondary = (!((ArrayList)completedResearch.get(this.player)).contains(this.currentHighlight.key)) && (this.currentHighlight.tags != null) && (this.currentHighlight.tags.size() > 0) && ((Config.researchDifficulty == -1) || ((Config.researchDifficulty == 0) && (this.currentHighlight.isSecondary())));
/*      */         
/*      */ 
/*      */ 
/*  749 */         boolean primary = (!secondary) && (!((ArrayList)completedResearch.get(this.player)).contains(this.currentHighlight.key));
/*  750 */         int var42 = (int)Math.max(fr.func_78256_a(var34), fr.func_78256_a(this.currentHighlight.getText()) / 1.9F);
/*  751 */         int var41 = fr.func_78267_b(var34, var42) + 5;
/*  752 */         if (primary) {
/*  753 */           var99 += 9;
/*  754 */           var42 = (int)Math.max(var42, fr.func_78256_a(StatCollector.func_74838_a("tc.research.shortprim")) / 1.9F);
/*      */         }
/*  756 */         if (secondary) {
/*  757 */           var99 += 29;
/*  758 */           var42 = (int)Math.max(var42, fr.func_78256_a(StatCollector.func_74838_a("tc.research.short")) / 1.9F);
/*      */         }
/*  760 */         int warp = ThaumcraftApi.getWarp(this.currentHighlight.key);
/*  761 */         if (warp > 5) warp = 5;
/*  762 */         String ws = StatCollector.func_74838_a("tc.forbidden");
/*  763 */         String wr = StatCollector.func_74838_a("tc.forbidden.level." + warp);
/*  764 */         String wte = ws.replaceAll("%n", wr);
/*  765 */         if (ThaumcraftApi.getWarp(this.currentHighlight.key) > 0) {
/*  766 */           var99 += 9;
/*  767 */           var42 = (int)Math.max(var42, fr.func_78256_a(wte) / 1.9F);
/*      */         }
/*  769 */         func_73733_a(var26 - 3, var27 - 3, var26 + var42 + 3, var27 + var41 + 6 + var99, -1073741824, -1073741824);
/*      */         
/*  771 */         GL11.glPushMatrix();
/*  772 */         GL11.glTranslatef(var26, var27 + var41 - 1, 0.0F);
/*  773 */         GL11.glScalef(0.5F, 0.5F, 0.5F);
/*  774 */         this.field_146289_q.func_78261_a(this.currentHighlight.getText(), 0, 0, -7302913);
/*  775 */         GL11.glPopMatrix();
/*      */         
/*      */ 
/*  778 */         if (warp > 0) {
/*  779 */           GL11.glPushMatrix();
/*  780 */           GL11.glTranslatef(var26, var27 + var41 + 8, 0.0F);
/*  781 */           GL11.glScalef(0.5F, 0.5F, 0.5F);
/*      */           
/*  783 */           this.field_146289_q.func_78261_a(wte, 0, 0, 16777215);
/*  784 */           GL11.glPopMatrix();
/*  785 */           var41 += 9;
/*      */         }
/*      */         
/*  788 */         GL11.glPushMatrix();
/*  789 */         if (primary) {
/*  790 */           GL11.glPushMatrix();
/*  791 */           GL11.glTranslatef(var26, var27 + var41 + 8, 0.0F);
/*  792 */           GL11.glScalef(0.5F, 0.5F, 0.5F);
/*  793 */           if (ResearchManager.getResearchSlot(this.field_146297_k.field_71439_g, this.currentHighlight.key) >= 0) {
/*  794 */             this.field_146289_q.func_78261_a(StatCollector.func_74838_a("tc.research.hasnote"), 0, 0, 16753920);
/*      */           }
/*  796 */           else if (this.hasScribestuff) {
/*  797 */             this.field_146289_q.func_78261_a(StatCollector.func_74838_a("tc.research.getprim"), 0, 0, 8900331);
/*      */           } else {
/*  799 */             this.field_146289_q.func_78261_a(StatCollector.func_74838_a("tc.research.shortprim"), 0, 0, 14423100);
/*      */           }
/*  801 */           GL11.glPopMatrix();
/*      */         }
/*  803 */         else if (secondary) {
/*  804 */           boolean enough = true;
/*      */           
/*  806 */           int cc = 0;
/*  807 */           for (Aspect a : this.currentHighlight.tags.getAspectsSortedAmount())
/*      */           {
/*  809 */             if (Thaumcraft.proxy.playerKnowledge.hasDiscoveredAspect(this.player, a)) {
/*  810 */               float alpha = 1.0F;
/*  811 */               if (Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(this.player, a) < this.currentHighlight.tags.getAmount(a)) {
/*  812 */                 alpha = (float)Math.sin(Minecraft.func_71386_F() % 600L / 600.0D * 3.141592653589793D * 2.0D) * 0.25F + 0.75F;
/*  813 */                 enough = false;
/*      */               }
/*  815 */               GL11.glPushMatrix();
/*  816 */               GL11.glPushAttrib(1048575);
/*  817 */               UtilsFX.drawTag(var26 + cc * 16, var27 + var41 + 8, a, this.currentHighlight.tags.getAmount(a), 0, 0.0D, 771, alpha, false);
/*  818 */               GL11.glPopAttrib();
/*  819 */               GL11.glPopMatrix();
/*      */             } else {
/*  821 */               enough = false;
/*  822 */               GL11.glPushMatrix();
/*  823 */               UtilsFX.bindTexture("textures/aspects/_unknown.png");
/*  824 */               GL11.glColor4f(0.5F, 0.5F, 0.5F, 0.5F);
/*  825 */               GL11.glTranslated(var26 + cc * 16, var27 + var41 + 8, 0.0D);
/*  826 */               UtilsFX.drawTexturedQuadFull(0, 0, 0.0D);
/*  827 */               GL11.glPopMatrix();
/*      */             }
/*  829 */             cc++;
/*      */           }
/*  831 */           GL11.glPushMatrix();
/*  832 */           GL11.glTranslatef(var26, var27 + var41 + 27, 0.0F);
/*  833 */           GL11.glScalef(0.5F, 0.5F, 0.5F);
/*  834 */           if (enough) {
/*  835 */             this.field_146289_q.func_78261_a(StatCollector.func_74838_a("tc.research.purchase"), 0, 0, 8900331);
/*      */           } else {
/*  837 */             this.field_146289_q.func_78261_a(StatCollector.func_74838_a("tc.research.short"), 0, 0, 14423100);
/*      */           }
/*  839 */           GL11.glPopMatrix();
/*      */         }
/*  841 */         GL11.glPopMatrix();
/*      */ 
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*  848 */         GL11.glPushMatrix();
/*      */         
/*  850 */         int var42 = (int)Math.max(fr.func_78256_a(var34), fr.func_78256_a(StatCollector.func_74838_a("tc.researchmissing")) / 1.5F);
/*  851 */         String var39 = StatCollector.func_74838_a("tc.researchmissing");
/*  852 */         int var30 = fr.func_78267_b(var39, var42 * 2);
/*  853 */         func_73733_a(var26 - 3, var27 - 3, var26 + var42 + 3, var27 + var30 + 10, -1073741824, -1073741824);
/*  854 */         GL11.glTranslatef(var26, var27 + 12, 0.0F);
/*  855 */         GL11.glScalef(0.5F, 0.5F, 0.5F);
/*  856 */         this.field_146289_q.func_78279_b(var39, 0, 0, var42 * 2, -9416624);
/*  857 */         GL11.glPopMatrix();
/*      */       }
/*      */       
/*  860 */       fr.func_78261_a(var34, var26, var27, this.currentHighlight.isSpecial() ? -8355776 : canUnlockResearch(this.currentHighlight) ? -1 : this.currentHighlight.isSpecial() ? -128 : -8355712);
/*      */     }
/*      */     
/*  863 */     GL11.glEnable(2929);
/*  864 */     GL11.glEnable(2896);
/*  865 */     RenderHelper.func_74518_a();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected void func_73864_a(int par1, int par2, int par3)
/*      */   {
/*  872 */     this.popuptime = (System.currentTimeMillis() - 1L);
/*  873 */     int var4; int var5; int count; boolean swop; if ((this.currentHighlight != null) && (!((ArrayList)completedResearch.get(this.player)).contains(this.currentHighlight.key)) && (canUnlockResearch(this.currentHighlight)))
/*      */     {
/*  875 */       updateResearch();
/*  876 */       boolean secondary = (this.currentHighlight.tags != null) && (this.currentHighlight.tags.size() > 0) && ((Config.researchDifficulty == -1) || ((Config.researchDifficulty == 0) && (this.currentHighlight.isSecondary())));
/*      */       
/*      */ 
/*  879 */       if (secondary) {
/*  880 */         boolean enough = true;
/*  881 */         for (Aspect a : this.currentHighlight.tags.getAspects()) {
/*  882 */           if (Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(this.player, a) < this.currentHighlight.tags.getAmount(a)) {
/*  883 */             enough = false;
/*  884 */             break;
/*      */           }
/*      */         }
/*  887 */         if (enough) {
/*  888 */           PacketHandler.INSTANCE.sendToServer(new PacketPlayerCompleteToServer(this.currentHighlight.key, this.field_146297_k.field_71439_g.func_70005_c_(), this.field_146297_k.field_71439_g.field_70170_p.field_73011_w.field_76574_g, (byte)0));
/*      */         }
/*      */       }
/*  891 */       else if ((this.hasScribestuff) && (ResearchManager.getResearchSlot(this.field_146297_k.field_71439_g, this.currentHighlight.key) == -1)) {
/*  892 */         PacketHandler.INSTANCE.sendToServer(new PacketPlayerCompleteToServer(this.currentHighlight.key, this.field_146297_k.field_71439_g.func_70005_c_(), this.field_146297_k.field_71439_g.field_70170_p.field_73011_w.field_76574_g, (byte)1));
/*      */         
/*  894 */         this.popuptime = (System.currentTimeMillis() + 3000L);
/*  895 */         this.popupmessage = new ChatComponentTranslation(StatCollector.func_74838_a("tc.research.popup"), new Object[] { "" + this.currentHighlight.getName() }).func_150260_c();
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*  900 */     else if ((this.currentHighlight != null) && (((ArrayList)completedResearch.get(this.player)).contains(this.currentHighlight.key))) {
/*  901 */       this.field_146297_k.func_147108_a(new GuiResearchRecipe(this.currentHighlight, 0, this.guiMapX, this.guiMapY));
/*      */     } else {
/*  903 */       var4 = (this.field_146294_l - this.paneWidth) / 2;
/*  904 */       var5 = (this.field_146295_m - this.paneHeight) / 2;
/*      */       
/*  906 */       Collection cats = ResearchCategories.researchCategories.keySet();
/*  907 */       count = 0;
/*  908 */       swop = false;
/*  909 */       for (Object obj : cats) {
/*  910 */         ResearchCategoryList rcl = ResearchCategories.getResearchList((String)obj);
/*  911 */         if ((!((String)obj).equals("ELDRITCH")) || (ResearchManager.isResearchComplete(this.player, "ELDRITCHMINOR")))
/*      */         {
/*      */ 
/*  914 */           if (count == 9) {
/*  915 */             count = 0;
/*  916 */             swop = true;
/*      */           }
/*      */           
/*  919 */           int mposx = par1 - (var4 - 24 + (swop ? 280 : 0));
/*  920 */           int mposy = par2 - (var5 + count * 24);
/*  921 */           if ((mposx >= 0) && (mposx < 24) && (mposy >= 0) && (mposy < 24))
/*      */           {
/*  923 */             selectedCategory = (String)obj;
/*  924 */             updateResearch();
/*  925 */             playButtonClick();
/*  926 */             break;
/*      */           }
/*      */           
/*  929 */           count++;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  934 */     super.func_73864_a(par1, par2, par3);
/*      */   }
/*      */   
/*      */   public void drawTexturedModalRectReversed(int par1, int par2, int par3, int par4, int par5, int par6)
/*      */   {
/*  939 */     float f = 0.00390625F;
/*  940 */     float f1 = 0.00390625F;
/*  941 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  942 */     tessellator.func_78382_b();
/*  943 */     tessellator.func_78374_a(par1 + 0, par2 + par6, this.field_73735_i, (par3 + 0) * f, (par4 + par6) * f1);
/*  944 */     tessellator.func_78374_a(par1 + par5, par2 + par6, this.field_73735_i, (par3 - par5) * f, (par4 + par6) * f1);
/*  945 */     tessellator.func_78374_a(par1 + par5, par2 + 0, this.field_73735_i, (par3 - par5) * f, (par4 + 0) * f1);
/*  946 */     tessellator.func_78374_a(par1 + 0, par2 + 0, this.field_73735_i, (par3 + 0) * f, (par4 + 0) * f1);
/*  947 */     tessellator.func_78381_a();
/*      */   }
/*      */   
/*      */   private void playButtonClick() {
/*  951 */     this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:cameraclack", 0.4F, 1.0F, false);
/*      */   }
/*      */   
/*      */ 
/*      */   private boolean canUnlockResearch(ResearchItem res)
/*      */   {
/*  957 */     if ((res.parents != null) && (res.parents.length > 0)) {
/*  958 */       for (String pt : res.parents) {
/*  959 */         ResearchItem parent = ResearchCategories.getResearch(pt);
/*  960 */         if ((parent != null) && (!((ArrayList)completedResearch.get(this.player)).contains(parent.key)))
/*  961 */           return false;
/*      */       }
/*      */     }
/*  964 */     if ((res.parentsHidden != null) && (res.parentsHidden.length > 0)) {
/*  965 */       for (String pt : res.parentsHidden) {
/*  966 */         ResearchItem parent = ResearchCategories.getResearch(pt);
/*  967 */         if ((parent != null) && (!((ArrayList)completedResearch.get(this.player)).contains(parent.key)))
/*  968 */           return false;
/*      */       }
/*      */     }
/*  971 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean func_73868_f()
/*      */   {
/*  979 */     return false;
/*      */   }
/*      */   
/*      */   private void drawLine(int x, int y, int x2, int y2, float r, float g, float b, float te, boolean wiggle) {
/*  983 */     float count = FMLClientHandler.instance().getClient().field_71439_g.field_70173_aa + te;
/*      */     
/*  985 */     Tessellator var12 = Tessellator.field_78398_a;
/*      */     
/*  987 */     GL11.glPushMatrix();
/*  988 */     GL11.glAlphaFunc(516, 0.003921569F);
/*  989 */     GL11.glDisable(3553);
/*  990 */     GL11.glEnable(3042);
/*  991 */     GL11.glBlendFunc(770, 771);
/*      */     
/*      */ 
/*      */ 
/*  995 */     double d3 = x - x2;
/*  996 */     double d4 = y - y2;
/*  997 */     float dist = MathHelper.func_76133_a(d3 * d3 + d4 * d4);
/*  998 */     int inc = (int)(dist / 2.0F);
/*  999 */     float dx = (float)(d3 / inc);
/* 1000 */     float dy = (float)(d4 / inc);
/*      */     
/* 1002 */     if (Math.abs(d3) > Math.abs(d4)) {
/* 1003 */       dx *= 2.0F;
/*      */     } else {
/* 1005 */       dy *= 2.0F;
/*      */     }
/*      */     
/* 1008 */     GL11.glLineWidth(3.0F);
/* 1009 */     GL11.glEnable(2848);
/* 1010 */     GL11.glHint(3154, 4354);
/*      */     
/*      */ 
/*      */ 
/* 1014 */     var12.func_78371_b(3);
/* 1015 */     for (int a = 0; a <= inc; a++) {
/* 1016 */       float r2 = r;
/* 1017 */       float g2 = g;
/* 1018 */       float b2 = b;
/* 1019 */       float mx = 0.0F;
/* 1020 */       float my = 0.0F;
/* 1021 */       float op = 0.6F;
/*      */       
/* 1023 */       if (wiggle) {
/* 1024 */         float phase = a / inc;
/* 1025 */         mx = MathHelper.func_76126_a((count + a) / 7.0F) * 5.0F * (1.0F - phase);
/* 1026 */         my = MathHelper.func_76126_a((count + a) / 5.0F) * 5.0F * (1.0F - phase);
/* 1027 */         r2 *= (1.0F - phase);
/* 1028 */         g2 *= (1.0F - phase);
/* 1029 */         b2 *= (1.0F - phase);
/* 1030 */         op *= phase;
/*      */       }
/*      */       
/* 1033 */       var12.func_78369_a(r2, g2, b2, op);
/*      */       
/* 1035 */       var12.func_78377_a(x - dx * a + mx, y - dy * a + my, 0.0D);
/*      */       
/* 1037 */       if (Math.abs(d3) > Math.abs(d4)) {
/* 1038 */         dx *= (1.0F - 1.0F / (inc * 3.0F / 2.0F));
/*      */       } else {
/* 1040 */         dy *= (1.0F - 1.0F / (inc * 3.0F / 2.0F));
/*      */       }
/*      */     }
/* 1043 */     var12.func_78381_a();
/*      */     
/* 1045 */     GL11.glBlendFunc(770, 771);
/* 1046 */     GL11.glDisable(2848);
/* 1047 */     GL11.glDisable(3042);
/* 1048 */     GL11.glDisable(32826);
/* 1049 */     GL11.glEnable(3553);
/* 1050 */     GL11.glAlphaFunc(516, 0.1F);
/* 1051 */     GL11.glPopMatrix();
/*      */   }
/*      */   
/*      */ 
/*      */   private void drawForbidden(double x, double y)
/*      */   {
/* 1057 */     int count = FMLClientHandler.instance().getClient().field_71439_g.field_70173_aa;
/* 1058 */     GL11.glPushMatrix();
/* 1059 */     GL11.glEnable(3042);
/* 1060 */     GL11.glBlendFunc(770, 771);
/* 1061 */     UtilsFX.bindTexture(TileNodeRenderer.nodetex);
/* 1062 */     int frames = 32;
/* 1063 */     int part = count % frames;
/* 1064 */     GL11.glTranslated(x, y, 0.0D);
/* 1065 */     UtilsFX.renderAnimatedQuadStrip(80.0F, 0.66F, frames, 5, frames - 1 - part, 0.0F, 4456533);
/* 1066 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1067 */     GL11.glDisable(3042);
/* 1068 */     GL11.glPopMatrix();
/*      */   }
/*      */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/gui/GuiResearchBrowser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */