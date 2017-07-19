/*     */ package thaumcraft.client.gui;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.research.ResearchItem;
/*     */ import thaumcraft.client.lib.RenderEventHandler;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.playerdata.PacketAspectCombinationToServer;
/*     */ import thaumcraft.common.lib.network.playerdata.PacketAspectPlaceToServer;
/*     */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ import thaumcraft.common.lib.research.ResearchManager.HexEntry;
/*     */ import thaumcraft.common.lib.research.ResearchNoteData;
/*     */ import thaumcraft.common.lib.utils.HexUtils.Hex;
/*     */ import thaumcraft.common.lib.utils.HexUtils.Pixel;
/*     */ import thaumcraft.common.tiles.TileResearchTable;
/*     */ 
/*     */ @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */ public class GuiResearchTable extends GuiContainer
/*     */ {
/*     */   private static boolean RESEARCHER_1;
/*     */   private static boolean RESEARCHER_2;
/*     */   private static boolean RESEARCHDUPE;
/*  49 */   private final int HEX_SIZE = 9;
/*     */   
/*     */ 
/*     */ 
/*     */   private float xSize_lo;
/*     */   
/*     */ 
/*     */ 
/*     */   private float ySize_lo;
/*     */   
/*     */ 
/*     */ 
/*  61 */   private long butcount1 = 0L;
/*  62 */   private long butcount2 = 0L;
/*  63 */   private int page = 0;
/*  64 */   private int lastPage = 0;
/*     */   
/*  66 */   private int isMouseButtonDown = 0;
/*     */   
/*     */   private TileResearchTable tileEntity;
/*     */   
/*     */   private FontRenderer galFontRenderer;
/*     */   
/*     */   private String username;
/*     */   
/*     */   EntityPlayer player;
/*  75 */   public Aspect select1 = null;
/*  76 */   public Aspect select2 = null;
/*  77 */   private AspectList aspectlist = new AspectList();
/*     */   
/*  79 */   private HashMap<String, Rune> runes = new HashMap();
/*     */   
/*     */   private class Rune {
/*     */     int q;
/*     */     int r;
/*     */     long start;
/*     */     long decay;
/*     */     int rune;
/*     */     
/*  88 */     public Rune(int q, int r, long start, long decay, int rune) { this.q = q;
/*  89 */       this.r = r;
/*  90 */       this.start = start;
/*  91 */       this.decay = decay;
/*  92 */       this.rune = rune;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public GuiResearchTable(EntityPlayer player, TileResearchTable e)
/*     */   {
/*  99 */     super(new thaumcraft.common.container.ContainerResearchTable(player.field_71071_by, e));
/* 100 */     this.tileEntity = e;
/* 101 */     this.field_146999_f = 255;
/* 102 */     this.field_147000_g = 255;
/* 103 */     this.galFontRenderer = FMLClientHandler.instance().getClient().field_71464_q;
/*     */     
/* 105 */     this.username = player.func_70005_c_();
/* 106 */     this.player = player;
/*     */     
/* 108 */     RESEARCHER_1 = ResearchManager.isResearchComplete(player.func_70005_c_(), "RESEARCHER1");
/* 109 */     RESEARCHER_2 = ResearchManager.isResearchComplete(player.func_70005_c_(), "RESEARCHER2");
/* 110 */     RESEARCHDUPE = ResearchManager.isResearchComplete(player.func_70005_c_(), "RESEARCHDUPE");
/*     */     
/* 112 */     int count = 0;
/* 113 */     for (Aspect aspect : Aspect.aspects.values()) {
/* 114 */       this.aspectlist.add(aspect, count);
/* 115 */       count++;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_146979_b(int mx, int my)
/*     */   {
/* 127 */     Minecraft mc = Minecraft.func_71410_x();
/* 128 */     long time = System.nanoTime() / 1000000L;
/*     */     
/*     */ 
/* 131 */     if (thaumcraft.client.lib.PlayerNotifications.getListAndUpdate(time).size() > 0) {
/* 132 */       GL11.glPushMatrix();
/* 133 */       Thaumcraft.instance.renderEventHandler.notifyHandler.renderNotifyHUD(this.field_146294_l, this.field_146295_m, time);
/* 134 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 141 */   private float popupScale = 0.05F;
/*     */   
/*     */ 
/*     */ 
/*     */   private Aspect draggedAspect;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_73863_a(int mx, int my, float par3)
/*     */   {
/* 152 */     super.func_73863_a(mx, my, par3);
/* 153 */     this.xSize_lo = mx;
/* 154 */     this.ySize_lo = my;
/* 155 */     int var5 = this.field_147003_i;
/* 156 */     int var6 = this.field_147009_r;
/* 157 */     int gx = (this.field_146294_l - this.field_146999_f) / 2;
/* 158 */     int gy = (this.field_146295_m - this.field_147000_g) / 2;
/*     */     
/* 160 */     if ((this.note != null) && (RESEARCHDUPE) && (this.note.isComplete())) {
/* 161 */       int var7 = mx - (gx + 37);
/* 162 */       int var8 = my - (gy + 5);
/* 163 */       if ((var7 >= 0) && (var8 >= 0) && (var7 < 24) && (var8 < 24))
/*     */       {
/* 165 */         RenderHelper.func_74520_c();
/* 166 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 167 */         ResearchItem rr = thaumcraft.api.research.ResearchCategories.getResearch(this.note.key);
/* 168 */         String ss = StatCollector.func_74838_a("tc.research.copy");
/* 169 */         GL11.glEnable(3042);
/*     */         
/* 171 */         UtilsFX.bindTexture("textures/gui/guiresearchtable2.png");
/* 172 */         func_73729_b(gx + 100, gy + 21, 184, 224, 48, 16);
/*     */         
/* 174 */         AspectList al = rr.tags.copy();
/* 175 */         for (Aspect aspect : al.getAspects()) {
/* 176 */           al.add(aspect, this.note.copies);
/*     */         }
/* 178 */         int count = 0;
/* 179 */         for (Aspect aspect : al.getAspectsSorted()) {
/* 180 */           UtilsFX.drawTag(gx + 100 + 48 + count * 16, gy + 21, aspect, al.getAmount(aspect), 0, this.field_73735_i);
/* 181 */           count++;
/*     */         }
/*     */         
/* 184 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 185 */         this.field_146289_q.func_78261_a(ss, gx + 100, gy + 12, -1);
/*     */       }
/*     */     }
/*     */     
/* 189 */     RenderHelper.func_74518_a();
/* 190 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 192 */     if (org.lwjgl.input.Mouse.isButtonDown(0))
/*     */     {
/* 194 */       int sx = gx + 10;
/* 195 */       int sy = gy + 40;
/*     */       
/* 197 */       if ((this.isMouseButtonDown == 0) && (mx >= sx) && (mx < sx + 80) && (my >= sy) && (my < sy + 80))
/*     */       {
/* 199 */         Aspect aspect = getClickedAspect(mx, my, gx, gy, false);
/* 200 */         if (aspect != null) {
/* 201 */           playButtonAspect();
/* 202 */           this.isMouseButtonDown = 1;
/* 203 */           this.draggedAspect = aspect;
/*     */         }
/*     */       }
/* 206 */       else if ((this.isMouseButtonDown == 1) && (this.draggedAspect != null)) {
/* 207 */         GL11.glEnable(3042);
/* 208 */         drawOrb(mx - 8, my - 8, this.draggedAspect.getColor());
/* 209 */         GL11.glDisable(3042);
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 214 */       if ((this.isMouseButtonDown == 1) && (this.draggedAspect != null))
/*     */       {
/* 216 */         if (this.note != null) {
/* 217 */           int mouseX = mx - (gx + 169);
/* 218 */           int mouseY = my - (gy + 83);
/* 219 */           HexUtils.Hex hp = new HexUtils.Pixel(mouseX, mouseY).toHex(9);
/* 220 */           if ((this.note.hexEntries.containsKey(hp.toString())) && (((ResearchManager.HexEntry)this.note.hexEntries.get(hp.toString())).type == 0))
/*     */           {
/* 222 */             playButtonCombine();
/* 223 */             playButtonWrite();
/* 224 */             PacketHandler.INSTANCE.sendToServer(new PacketAspectPlaceToServer(this.player, (byte)hp.q, (byte)hp.r, this.tileEntity.field_145851_c, this.tileEntity.field_145848_d, this.tileEntity.field_145849_e, this.draggedAspect));
/*     */             
/* 226 */             this.draggedAspect = null;
/*     */           }
/*     */         }
/*     */         
/* 230 */         if (this.draggedAspect != null) {
/* 231 */           boolean skip = false;
/* 232 */           int mouseX = mx - (gx + 20);
/* 233 */           int mouseY = my - (gy + 146);
/* 234 */           if ((mouseX >= -16) && (mouseY >= -16) && (mouseX < 16) && (mouseY < 16)) {
/* 235 */             playButtonAspect();
/* 236 */             this.select1 = this.draggedAspect;
/* 237 */             skip = true;
/*     */           }
/*     */           
/* 240 */           mouseX = mx - (gx + 79);
/* 241 */           mouseY = my - (gy + 146);
/* 242 */           if ((!skip) && (mouseX >= -16) && (mouseY >= -16) && (mouseX < 16) && (mouseY < 16)) {
/* 243 */             playButtonAspect();
/* 244 */             this.select2 = this.draggedAspect;
/* 245 */             skip = true;
/*     */           }
/* 247 */           if (!skip) {
/* 248 */             Aspect aspect = getClickedAspect(mx, my, gx, gy, false);
/*     */             
/* 250 */             if (aspect == this.draggedAspect)
/*     */             {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 267 */               if (this.select1 == null) {
/* 268 */                 this.select1 = this.draggedAspect;
/*     */               }
/* 270 */               else if (this.select2 == null) {
/* 271 */                 this.select2 = this.draggedAspect;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 280 */       this.isMouseButtonDown = 0;
/* 281 */       this.draggedAspect = null;
/*     */     }
/*     */     
/*     */ 
/* 285 */     drawAspectText(var5 + 10, var6 + 40, mx, my);
/*     */     
/* 287 */     if ((this.note != null) && ((this.tileEntity.func_70301_a(0) == null) || (this.tileEntity.func_70301_a(0).func_77960_j() == this.tileEntity.func_70301_a(0).func_77958_k())))
/*     */     {
/* 289 */       int sx = Math.max(this.field_146289_q.func_78256_a(StatCollector.func_74838_a("tile.researchtable.noink.0")), this.field_146289_q.func_78256_a(StatCollector.func_74838_a("tile.researchtable.noink.1"))) / 2;
/*     */       
/* 291 */       UtilsFX.drawCustomTooltip(this, field_146296_j, this.field_146289_q, Arrays.asList(new String[] { StatCollector.func_74838_a("tile.researchtable.noink.0"), StatCollector.func_74838_a("tile.researchtable.noink.1") }), gx + 157 - sx, gy + 84, 11);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_146976_a(float par1, int par2, int par3)
/*     */   {
/* 304 */     int var5 = this.field_147003_i;
/* 305 */     int var6 = this.field_147009_r;
/*     */     
/* 307 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 308 */     GL11.glEnable(3042);
/* 309 */     UtilsFX.bindTexture("textures/gui/guiresearchtable2.png");
/* 310 */     func_73729_b(var5, var6, 0, 0, 255, 167);
/* 311 */     func_73729_b(var5 + 40, var6 + 167, 0, 166, 184, 88);
/*     */     
/* 313 */     if (this.page < this.lastPage) {
/* 314 */       func_73729_b(var5 + 51, var6 + 121, 208, 208, 24, 8);
/*     */     }
/*     */     
/* 317 */     if (this.page > 0) {
/* 318 */       func_73729_b(var5 + 27, var6 + 121, 184, 208, 24, 8);
/*     */     }
/*     */     
/* 321 */     if ((this.butcount2 < System.nanoTime()) && (this.select1 != null) && (this.select2 != null)) {
/* 322 */       func_73729_b(var5 + 35, var6 + 139, 184, 184, 32, 16);
/* 323 */       drawOrb(var5 + 43, var6 + 139);
/*     */     }
/* 325 */     else if ((this.butcount2 >= System.nanoTime()) && (this.select1 != null) && (this.select2 != null)) {
/* 326 */       func_73729_b(var5 + 35, var6 + 139, 184, 184, 32, 16);
/* 327 */       func_73729_b(var5 + 35, var6 + 139, 184, 168, 32, 16);
/*     */     }
/*     */     
/* 330 */     if ((RESEARCHDUPE) && (this.note != null) && (this.note.isComplete())) {
/* 331 */       func_73729_b(var5 + 37, var6 + 5, 232, 200, 24, 24);
/*     */     }
/*     */     
/* 334 */     drawAspects(var5 + 10, var6 + 40);
/* 335 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 336 */     RenderHelper.func_74518_a();
/*     */     
/* 338 */     drawResearchData(var5, var6, par2, par3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void drawAspects(int x, int y)
/*     */   {
/* 345 */     AspectList aspects = Thaumcraft.proxy.getPlayerKnowledge().getAspectsDiscovered(this.username);
/* 346 */     if (aspects != null) {
/* 347 */       int count = aspects.size();
/*     */       
/*     */ 
/*     */ 
/* 351 */       this.lastPage = ((count - 20) / 5);
/* 352 */       count = 0;
/* 353 */       int drawn = 0;
/* 354 */       for (Aspect aspect : aspects.getAspectsSorted())
/*     */       {
/* 356 */         count++;
/* 357 */         if ((count - 1 >= this.page * 5) && 
/* 358 */           (drawn < 25)) {
/* 359 */           boolean faded = (aspects.getAmount(aspect) <= 0) && (this.tileEntity.bonusAspects.getAmount(aspect) <= 0);
/* 360 */           int xx = drawn / 5 * 16;
/* 361 */           int yy = drawn % 5 * 16;
/* 362 */           UtilsFX.drawTag(x + xx, y + yy, aspect, aspects.getAmount(aspect), this.tileEntity.bonusAspects.getAmount(aspect), this.field_73735_i, 771, faded ? 0.33F : 1.0F);
/*     */           
/*     */ 
/* 365 */           drawn++;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 374 */     if ((this.select1 != null) && (Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(this.player.func_70005_c_(), this.select1) <= 0) && (this.tileEntity.bonusAspects.getAmount(this.select1) <= 0))
/*     */     {
/* 376 */       this.select1 = null;
/*     */     }
/*     */     
/* 379 */     if ((this.select2 != null) && (Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(this.player.func_70005_c_(), this.select2) <= 0) && (this.tileEntity.bonusAspects.getAmount(this.select2) <= 0))
/*     */     {
/* 381 */       this.select2 = null;
/*     */     }
/*     */     
/* 384 */     if (this.select1 != null) {
/* 385 */       UtilsFX.drawTag(x + 3, y + 99, this.select1, 0.0F, 0, this.field_73735_i);
/*     */     }
/* 387 */     if (this.select2 != null) {
/* 388 */       UtilsFX.drawTag(x + 61, y + 99, this.select2, 0.0F, 0, this.field_73735_i);
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawAspectText(int x, int y, int mx, int my) {
/* 393 */     int var7 = 0;
/* 394 */     int var8 = 0;
/*     */     
/* 396 */     AspectList aspects = Thaumcraft.proxy.getPlayerKnowledge().getAspectsDiscovered(this.username);
/* 397 */     if (aspects != null) {
/* 398 */       int count = 0;
/* 399 */       int drawn = 0;
/* 400 */       for (Aspect aspect : aspects.getAspectsSorted())
/*     */       {
/* 402 */         count++;
/* 403 */         if ((count - 1 >= this.page * 5) && 
/* 404 */           (drawn < 25)) {
/* 405 */           int xx = drawn / 5 * 16;
/* 406 */           int yy = drawn % 5 * 16;
/* 407 */           var7 = mx - (x + xx);
/* 408 */           var8 = my - (y + yy);
/* 409 */           if ((var7 >= 0) && (var8 >= 0) && (var7 < 16) && (var8 < 16))
/*     */           {
/* 411 */             UtilsFX.drawCustomTooltip(this, field_146296_j, this.field_146289_q, Arrays.asList(new String[] { aspect.getName(), aspect.getLocalizedDescription() }), mx, my - 8, 11);
/*     */             
/*     */ 
/* 414 */             if ((RESEARCHER_1) && (!aspect.isPrimal())) {
/* 415 */               GL11.glPushMatrix();
/* 416 */               GL11.glEnable(3042);
/* 417 */               GL11.glBlendFunc(770, 771);
/* 418 */               UtilsFX.bindTexture("textures/aspects/_back.png");
/* 419 */               GL11.glPushMatrix();
/* 420 */               GL11.glTranslated(mx + 6, my + 6, 0.0D);
/* 421 */               GL11.glScaled(1.25D, 1.25D, 0.0D);
/* 422 */               UtilsFX.drawTexturedQuadFull(0, 0, 0.0D);
/* 423 */               GL11.glPopMatrix();
/* 424 */               GL11.glPushMatrix();
/* 425 */               GL11.glTranslated(mx + 24, my + 6, 0.0D);
/* 426 */               GL11.glScaled(1.25D, 1.25D, 0.0D);
/* 427 */               UtilsFX.drawTexturedQuadFull(0, 0, 0.0D);
/* 428 */               GL11.glPopMatrix();
/* 429 */               UtilsFX.drawTag(mx + 26, my + 8, aspect.getComponents()[1], 0.0F, 0, 0.0D);
/* 430 */               UtilsFX.drawTag(mx + 8, my + 8, aspect.getComponents()[0], 0.0F, 0, 0.0D);
/* 431 */               GL11.glDisable(3042);
/* 432 */               GL11.glPopMatrix();
/*     */             }
/* 434 */             return;
/*     */           }
/* 436 */           drawn++;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 441 */     if (this.select1 != null) {
/* 442 */       var7 = mx - (x + 3);
/* 443 */       var8 = my - (y + 99);
/* 444 */       if ((var7 >= 0) && (var8 >= 0) && (var7 < 16) && (var8 < 16))
/*     */       {
/* 446 */         UtilsFX.drawCustomTooltip(this, field_146296_j, this.field_146289_q, Arrays.asList(new String[] { this.select1.getName(), this.select1.getLocalizedDescription() }), mx, my - 8, 11);
/*     */         
/*     */ 
/* 449 */         return;
/*     */       }
/*     */     }
/* 452 */     if (this.select2 != null) {
/* 453 */       var7 = mx - (x + 61);
/* 454 */       var8 = my - (y + 99);
/* 455 */       if ((var7 >= 0) && (var8 >= 0) && (var7 < 16) && (var8 < 16))
/*     */       {
/* 457 */         UtilsFX.drawCustomTooltip(this, field_146296_j, this.field_146289_q, Arrays.asList(new String[] { this.select2.getName(), this.select2.getLocalizedDescription() }), mx, my - 8, 11);
/*     */         
/*     */ 
/* 460 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   class Coord2D
/*     */   {
/*     */     int x;
/*     */     int y;
/*     */     
/*     */     Coord2D(int x, int y) {
/* 471 */       this.x = x;
/* 472 */       this.y = y;
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawResearchData(int x, int y, int mx, int my) {
/* 477 */     GL11.glPushMatrix();
/* 478 */     GL11.glEnable(3042);
/*     */     
/* 480 */     drawSheet(x, y, mx, my);
/*     */     
/* 482 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   private void drawHex(HexUtils.Hex hex, int x, int y) {
/* 486 */     GL11.glPushMatrix();
/* 487 */     GL11.glAlphaFunc(516, 0.003921569F);
/* 488 */     GL11.glEnable(3042);
/* 489 */     UtilsFX.bindTexture("textures/gui/hex1.png");
/* 490 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.25F);
/* 491 */     HexUtils.Pixel pix = hex.toPixel(9);
/* 492 */     GL11.glTranslated(x + pix.x, y + pix.y, 0.0D);
/*     */     
/* 494 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 495 */     tessellator.func_78382_b();
/* 496 */     tessellator.func_78380_c(240);
/* 497 */     tessellator.func_78369_a(1.0F, 1.0F, 1.0F, 0.25F);
/* 498 */     tessellator.func_78374_a(-8.0D, 8.0D, this.field_73735_i, 0.0D, 1.0D);
/* 499 */     tessellator.func_78374_a(8.0D, 8.0D, this.field_73735_i, 1.0D, 1.0D);
/* 500 */     tessellator.func_78374_a(8.0D, -8.0D, this.field_73735_i, 1.0D, 0.0D);
/* 501 */     tessellator.func_78374_a(-8.0D, -8.0D, this.field_73735_i, 0.0D, 0.0D);
/* 502 */     tessellator.func_78381_a();
/* 503 */     GL11.glAlphaFunc(516, 0.1F);
/* 504 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */   private void drawHexHighlight(HexUtils.Hex hex, int x, int y)
/*     */   {
/* 510 */     GL11.glPushMatrix();
/* 511 */     GL11.glAlphaFunc(516, 0.003921569F);
/* 512 */     GL11.glEnable(3042);
/* 513 */     GL11.glBlendFunc(770, 1);
/* 514 */     UtilsFX.bindTexture("textures/gui/hex2.png");
/* 515 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 516 */     HexUtils.Pixel pix = hex.toPixel(9);
/* 517 */     GL11.glTranslated(x + pix.x, y + pix.y, 0.0D);
/*     */     
/* 519 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 520 */     tessellator.func_78382_b();
/*     */     
/* 522 */     tessellator.func_78369_a(1.0F, 1.0F, 1.0F, 1.0F);
/* 523 */     tessellator.func_78374_a(-8.0D, 8.0D, this.field_73735_i, 0.0D, 1.0D);
/* 524 */     tessellator.func_78374_a(8.0D, 8.0D, this.field_73735_i, 1.0D, 1.0D);
/* 525 */     tessellator.func_78374_a(8.0D, -8.0D, this.field_73735_i, 1.0D, 0.0D);
/* 526 */     tessellator.func_78374_a(-8.0D, -8.0D, this.field_73735_i, 0.0D, 0.0D);
/* 527 */     tessellator.func_78381_a();
/* 528 */     GL11.glBlendFunc(770, 771);
/* 529 */     GL11.glAlphaFunc(516, 0.1F);
/* 530 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   private void drawLine(double x, double y, double x2, double y2)
/*     */   {
/* 535 */     int count = FMLClientHandler.instance().getClient().field_71439_g.field_70173_aa;
/* 536 */     float alpha = 0.3F + MathHelper.func_76126_a((float)(count + x)) * 0.3F + 0.3F;
/*     */     
/* 538 */     Tessellator var12 = Tessellator.field_78398_a;
/* 539 */     GL11.glPushMatrix();
/* 540 */     GL11.glLineWidth(3.0F);
/* 541 */     GL11.glDisable(3553);
/*     */     
/* 543 */     GL11.glBlendFunc(770, 1);
/* 544 */     var12.func_78371_b(3);
/*     */     
/* 546 */     var12.func_78369_a(0.0F, 0.6F, 0.8F, alpha);
/* 547 */     var12.func_78377_a(x, y, 0.0D);
/* 548 */     var12.func_78377_a(x2, y2, 0.0D);
/*     */     
/* 550 */     var12.func_78381_a();
/* 551 */     GL11.glBlendFunc(770, 771);
/* 552 */     GL11.glDisable(32826);
/* 553 */     GL11.glEnable(3553);
/* 554 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/* 558 */   public ResearchNoteData note = null;
/*     */   
/* 560 */   long lastRuneCheck = 0L;
/*     */   
/*     */   private void drawSheet(int x, int y, int mx, int my)
/*     */   {
/* 564 */     this.note = ResearchManager.getData(this.tileEntity.func_70301_a(1));
/*     */     
/* 566 */     if ((this.note == null) || (this.note.key == null) || (this.note.key.length() == 0)) {
/* 567 */       this.runes.clear();
/* 568 */       return;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 573 */     UtilsFX.bindTexture("textures/misc/parchment3.png");
/* 574 */     func_73729_b(x + 94, y + 8, 0, 0, 150, 150);
/* 575 */     long time = System.currentTimeMillis();
/* 576 */     if (this.lastRuneCheck < time) {
/* 577 */       this.lastRuneCheck = (time + 250L);
/* 578 */       int k = this.field_146297_k.field_71441_e.field_73012_v.nextInt(120) - 60;
/* 579 */       int l = this.field_146297_k.field_71441_e.field_73012_v.nextInt(120) - 60;
/* 580 */       HexUtils.Hex hp = new HexUtils.Pixel(k, l).toHex(9);
/* 581 */       if ((!this.runes.containsKey(hp.toString())) && (!this.note.hexes.containsKey(hp.toString())))
/*     */       {
/* 583 */         this.runes.put(hp.toString(), new Rune(hp.q, hp.r, time, this.lastRuneCheck + 15000L + this.field_146297_k.field_71441_e.field_73012_v.nextInt(10000), this.field_146297_k.field_71441_e.field_73012_v.nextInt(16)));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 589 */     if (this.runes.size() > 0) {
/* 590 */       Rune[] rns = (Rune[])this.runes.values().toArray(new Rune[0]);
/* 591 */       for (int a = 0; a < rns.length; a++) {
/* 592 */         Rune rune = rns[a];
/* 593 */         if (rune.decay < time) {
/* 594 */           this.runes.remove(rune.q + ":" + rune.r);
/*     */         }
/*     */         else {
/* 597 */           HexUtils.Pixel pix = new HexUtils.Hex(rune.q, rune.r).toPixel(9);
/* 598 */           float progress = (float)(time - rune.start) / (float)(rune.decay - rune.start);
/* 599 */           float alpha = 0.5F;
/* 600 */           if (progress < 0.25F) {
/* 601 */             alpha = progress * 2.0F;
/*     */           }
/* 603 */           else if (progress > 0.5F) {
/* 604 */             alpha = 1.0F - progress;
/*     */           }
/* 606 */           drawRune(x + 169 + pix.x, y + 83 + pix.y, rune.rune, alpha * 0.66F);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 612 */     int mouseX = mx - (x + 169);
/* 613 */     int mouseY = my - (y + 83);
/* 614 */     HexUtils.Hex hp = new HexUtils.Pixel(mouseX, mouseY).toHex(9);
/*     */     
/*     */ 
/* 617 */     this.lines.clear();
/* 618 */     this.checked.clear();
/* 619 */     this.highlight.clear();
/* 620 */     for (HexUtils.Hex hex : this.note.hexes.values()) {
/* 621 */       if ((((ResearchManager.HexEntry)this.note.hexEntries.get(hex.toString())).type == 1) && (Thaumcraft.proxy.getPlayerKnowledge().hasDiscoveredAspect(this.username, ((ResearchManager.HexEntry)this.note.hexEntries.get(hex.toString())).aspect)))
/*     */       {
/* 623 */         checkConnections(hex);
/*     */       }
/*     */     }
/*     */     
/* 627 */     for (HexUtils.Hex[] con : this.lines.values()) {
/* 628 */       HexUtils.Pixel p1 = con[0].toPixel(9);
/* 629 */       HexUtils.Pixel p2 = con[1].toPixel(9);
/* 630 */       drawLine(x + 169 + p1.x, y + 83 + p1.y, x + 169 + p2.x, y + 83 + p2.y);
/*     */     }
/*     */     
/* 633 */     UtilsFX.bindTexture("textures/gui/hex1.png");
/* 634 */     GL11.glPushMatrix();
/*     */     
/* 636 */     if (!this.note.isComplete()) {
/* 637 */       for (HexUtils.Hex hex : this.note.hexes.values()) {
/* 638 */         if (((ResearchManager.HexEntry)this.note.hexEntries.get(hex.toString())).type != 1) {
/* 639 */           if (!this.note.isComplete()) {
/* 640 */             if (hex.equals(hp))
/* 641 */               drawHexHighlight(hex, x + 169, y + 83);
/* 642 */             drawHex(hex, x + 169, y + 83);
/*     */           }
/*     */         } else {
/* 645 */           drawOrb(x + 161 + hex.toPixel(9).x, y + 75 + hex.toPixel(9).y);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 650 */     for (HexUtils.Hex hex : this.note.hexes.values()) {
/* 651 */       if ((((ResearchManager.HexEntry)this.note.hexEntries.get(hex.toString())).aspect != null) && (!Thaumcraft.proxy.getPlayerKnowledge().hasDiscoveredAspect(this.username, ((ResearchManager.HexEntry)this.note.hexEntries.get(hex.toString())).aspect)))
/*     */       {
/* 653 */         HexUtils.Pixel pix = hex.toPixel(9);
/* 654 */         UtilsFX.bindTexture("textures/aspects/_unknown.png");
/* 655 */         GL11.glPushMatrix();
/* 656 */         GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.5F);
/* 657 */         GL11.glEnable(3042);
/* 658 */         GL11.glBlendFunc(770, 771);
/* 659 */         GL11.glTranslated(x + 161 + pix.x, y + 75 + pix.y, 0.0D);
/* 660 */         UtilsFX.drawTexturedQuadFull(0, 0, this.field_73735_i);
/* 661 */         GL11.glDisable(3042);
/* 662 */         GL11.glPopMatrix();
/*     */       }
/* 664 */       else if ((((ResearchManager.HexEntry)this.note.hexEntries.get(hex.toString())).type == 1) || (this.highlight.contains(hex.toString()))) {
/* 665 */         HexUtils.Pixel pix = hex.toPixel(9);
/* 666 */         UtilsFX.drawTag(x + 161 + pix.x, y + 75 + pix.y, ((ResearchManager.HexEntry)this.note.hexEntries.get(hex.toString())).aspect, 0.0F, 0, this.field_73735_i, 771, 1.0F, false);
/* 667 */       } else if (((ResearchManager.HexEntry)this.note.hexEntries.get(hex.toString())).type == 2) {
/* 668 */         HexUtils.Pixel pix = hex.toPixel(9);
/* 669 */         UtilsFX.drawTag(x + 161 + pix.x, y + 75 + pix.y, ((ResearchManager.HexEntry)this.note.hexEntries.get(hex.toString())).aspect, 0.0F, 0, this.field_73735_i, 771, 0.66F, true);
/*     */       }
/*     */     }
/*     */     
/* 673 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 680 */   private HashMap<String, HexUtils.Hex[]> lines = new HashMap();
/* 681 */   private ArrayList<String> checked = new ArrayList();
/* 682 */   private ArrayList<String> highlight = new ArrayList();
/*     */   
/*     */   private void checkConnections(HexUtils.Hex hex) {
/* 685 */     this.checked.add(hex.toString());
/* 686 */     for (int a = 0; a < 6; a++) {
/* 687 */       HexUtils.Hex target = hex.getNeighbour(a);
/* 688 */       if ((!this.checked.contains(target.toString())) && 
/* 689 */         (this.note.hexEntries.containsKey(target.toString())) && (((ResearchManager.HexEntry)this.note.hexEntries.get(target.toString())).type >= 1))
/*     */       {
/* 691 */         Aspect aspect1 = ((ResearchManager.HexEntry)this.note.hexEntries.get(hex.toString())).aspect;
/* 692 */         Aspect aspect2 = ((ResearchManager.HexEntry)this.note.hexEntries.get(target.toString())).aspect;
/*     */         
/* 694 */         if ((Thaumcraft.proxy.getPlayerKnowledge().hasDiscoveredAspect(this.username, aspect1)) && (Thaumcraft.proxy.getPlayerKnowledge().hasDiscoveredAspect(this.username, aspect2)) && (((!aspect1.isPrimal()) && ((aspect1.getComponents()[0] == aspect2) || (aspect1.getComponents()[1] == aspect2))) || ((!aspect2.isPrimal()) && ((aspect2.getComponents()[0] == aspect1) || (aspect2.getComponents()[1] == aspect1)))))
/*     */         {
/*     */ 
/*     */ 
/* 698 */           String k1 = hex.toString() + ":" + target.toString();
/* 699 */           String k2 = target.toString() + ":" + hex.toString();
/* 700 */           if ((!this.lines.containsKey(k1)) && (!this.lines.containsKey(k2))) {
/* 701 */             this.lines.put(k1, new HexUtils.Hex[] { hex, target });
/* 702 */             this.highlight.add(target.toString());
/*     */           }
/* 704 */           checkConnections(target);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawRune(double x, double y, int rune, float alpha)
/*     */   {
/* 712 */     GL11.glPushMatrix();
/* 713 */     UtilsFX.bindTexture("textures/misc/script.png");
/* 714 */     GL11.glColor4f(0.0F, 0.0F, 0.0F, alpha);
/*     */     
/* 716 */     GL11.glTranslated(x, y, 0.0D);
/* 717 */     if (rune < 16) {
/* 718 */       GL11.glRotatef(90.0F, 0.0F, 0.0F, -1.0F);
/*     */     }
/*     */     
/* 721 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 722 */     float var8 = 0.0625F * rune;
/* 723 */     float var9 = var8 + 0.0625F;
/* 724 */     float var10 = 0.0F;
/* 725 */     float var11 = 1.0F;
/* 726 */     tessellator.func_78382_b();
/* 727 */     tessellator.func_78369_a(0.0F, 0.0F, 0.0F, alpha);
/* 728 */     tessellator.func_78374_a(-5.0D, 5.0D, this.field_73735_i, var9, var11);
/* 729 */     tessellator.func_78374_a(5.0D, 5.0D, this.field_73735_i, var9, var10);
/* 730 */     tessellator.func_78374_a(5.0D, -5.0D, this.field_73735_i, var8, var10);
/* 731 */     tessellator.func_78374_a(-5.0D, -5.0D, this.field_73735_i, var8, var11);
/* 732 */     tessellator.func_78381_a();
/* 733 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_73864_a(int mx, int my, int par3)
/*     */   {
/* 741 */     super.func_73864_a(mx, my, par3);
/* 742 */     if ((this.butcount1 > System.nanoTime()) || (this.butcount2 > System.nanoTime())) { return;
/*     */     }
/* 744 */     int gx = (this.field_146294_l - this.field_146999_f) / 2;
/* 745 */     int gy = (this.field_146295_m - this.field_147000_g) / 2;
/*     */     
/*     */ 
/* 748 */     int var7 = mx - (gx + 35);
/* 749 */     int var8 = my - (gy + 139);
/*     */     
/* 751 */     if ((var7 >= 0) && (var8 >= 0) && (var7 < 32) && (var8 < 16) && (this.butcount2 < System.nanoTime()) && (this.select1 != null) && (this.select2 != null))
/*     */     {
/*     */ 
/*     */ 
/* 755 */       this.butcount2 = (System.nanoTime() + 200000000L);
/* 756 */       playButtonClick();
/* 757 */       playButtonCombine();
/* 758 */       PacketHandler.INSTANCE.sendToServer(new PacketAspectCombinationToServer(this.player, this.tileEntity.field_145851_c, this.tileEntity.field_145848_d, this.tileEntity.field_145849_e, this.select1, this.select2, this.tileEntity.bonusAspects.getAmount(this.select1) > 0, this.tileEntity.bonusAspects.getAmount(this.select2) > 0, true));
/*     */       
/*     */ 
/*     */ 
/* 762 */       return;
/*     */     }
/*     */     
/*     */ 
/* 766 */     var7 = mx - (gx + 27);
/* 767 */     var8 = my - (gy + 121);
/* 768 */     if ((this.page > 0) && (var7 >= 0) && (var8 >= 0) && (var7 < 24) && (var8 < 8))
/*     */     {
/* 770 */       this.page -= 1;
/* 771 */       playButtonScroll();
/* 772 */       return;
/*     */     }
/*     */     
/* 775 */     var7 = mx - (gx + 51);
/* 776 */     var8 = my - (gy + 121);
/* 777 */     if ((this.page < this.lastPage) && (var7 >= 0) && (var8 >= 0) && (var7 < 24) && (var8 < 8))
/*     */     {
/* 779 */       this.page += 1;
/* 780 */       playButtonScroll();
/* 781 */       return;
/*     */     }
/*     */     
/* 784 */     if (this.select1 != null) {
/* 785 */       var7 = mx - (gx + 11);
/* 786 */       var8 = my - (gy + 137);
/* 787 */       if ((var7 >= 0) && (var8 >= 0) && (var7 < 16) && (var8 < 16))
/*     */       {
/* 789 */         this.select1 = null;
/* 790 */         playButtonAspect();
/* 791 */         return;
/*     */       }
/*     */     }
/* 794 */     if (this.select2 != null) {
/* 795 */       var7 = mx - (gx + 71);
/* 796 */       var8 = my - (gy + 137);
/* 797 */       if ((var7 >= 0) && (var8 >= 0) && (var7 < 16) && (var8 < 16))
/*     */       {
/* 799 */         this.select2 = null;
/* 800 */         playButtonAspect();
/* 801 */         return;
/*     */       }
/*     */     }
/*     */     
/* 805 */     if (this.note != null) {
/* 806 */       checkClickedHex(mx, my, gx, gy);
/*     */       
/* 808 */       if ((RESEARCHDUPE) && (this.note.isComplete())) {
/* 809 */         var7 = mx - (gx + 37);
/* 810 */         var8 = my - (gy + 5);
/* 811 */         if ((var7 >= 0) && (var8 >= 0) && (var7 < 24) && (var8 < 24))
/*     */         {
/* 813 */           this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 5);
/* 814 */           playButtonClick();
/* 815 */           return;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 820 */     if ((func_146272_n()) && (RESEARCHER_2))
/*     */     {
/*     */ 
/* 823 */       Aspect aspect = getClickedAspect(mx, my, gx, gy, true);
/* 824 */       if ((aspect != null) && (!aspect.isPrimal())) {
/* 825 */         AspectList aspects = Thaumcraft.proxy.getPlayerKnowledge().getAspectsDiscovered(this.username);
/* 826 */         if ((aspects != null) && ((aspects.getAmount(aspect.getComponents()[0]) > 0) || (this.tileEntity.bonusAspects.getAmount(aspect.getComponents()[0]) > 0)) && ((aspects.getAmount(aspect.getComponents()[1]) > 0) || (this.tileEntity.bonusAspects.getAmount(aspect.getComponents()[1]) > 0)))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/* 831 */           this.draggedAspect = null;
/* 832 */           playButtonCombine();
/* 833 */           PacketHandler.INSTANCE.sendToServer(new PacketAspectCombinationToServer(this.player, this.tileEntity.field_145851_c, this.tileEntity.field_145848_d, this.tileEntity.field_145849_e, aspect.getComponents()[0], aspect.getComponents()[1], this.tileEntity.bonusAspects.getAmount(aspect.getComponents()[0]) > 0, this.tileEntity.bonusAspects.getAmount(aspect.getComponents()[1]) > 0, true));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void checkClickedHex(int mx, int my, int gx, int gy)
/*     */   {
/* 845 */     int mouseX = mx - (gx + 169);
/* 846 */     int mouseY = my - (gy + 83);
/* 847 */     HexUtils.Hex hp = new HexUtils.Pixel(mouseX, mouseY).toHex(9);
/*     */     
/* 849 */     if ((this.note.hexes.containsKey(hp.toString())) && (((ResearchManager.HexEntry)this.note.hexEntries.get(hp.toString())).type == 2)) {
/* 850 */       playButtonCombine();
/* 851 */       playButtonErase();
/* 852 */       PacketHandler.INSTANCE.sendToServer(new PacketAspectPlaceToServer(this.player, (byte)hp.q, (byte)hp.r, this.tileEntity.field_145851_c, this.tileEntity.field_145848_d, this.tileEntity.field_145849_e, null));
/*     */       
/* 854 */       return;
/*     */     }
/*     */   }
/*     */   
/*     */   private Aspect getClickedAspect(int mx, int my, int gx, int gy, boolean ignoreZero) {
/* 859 */     AspectList aspects = Thaumcraft.proxy.getPlayerKnowledge().getAspectsDiscovered(this.username);
/* 860 */     if (aspects != null) {
/* 861 */       int count = 0;
/* 862 */       int drawn = 0;
/* 863 */       for (Aspect aspect : aspects.getAspectsSorted()) {
/* 864 */         count++;
/* 865 */         if ((count - 1 >= this.page * 5) && 
/* 866 */           (drawn < 25)) {
/* 867 */           int xx = drawn / 5 * 16;
/* 868 */           int yy = drawn % 5 * 16;
/* 869 */           int var7 = mx - (gx + xx + 10);
/* 870 */           int var8 = my - (gy + yy + 40);
/* 871 */           if (((ignoreZero) || (aspects.getAmount(aspect) > 0) || (this.tileEntity.bonusAspects.getAmount(aspect) > 0)) && 
/* 872 */             (var7 >= 0) && (var8 >= 0) && (var7 < 16) && (var8 < 16))
/*     */           {
/* 874 */             return aspect;
/*     */           }
/*     */           
/* 877 */           drawn++;
/*     */         }
/*     */       }
/*     */     }
/* 881 */     return null;
/*     */   }
/*     */   
/*     */   private void playButtonClick() {
/* 885 */     this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:cameraclack", 0.4F, 1.0F, false);
/*     */   }
/*     */   
/*     */ 
/*     */   private void playButtonAspect()
/*     */   {
/* 891 */     this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:hhoff", 0.2F, 1.0F + this.field_146297_k.field_71451_h.field_70170_p.field_73012_v.nextFloat() * 0.1F, false);
/*     */   }
/*     */   
/*     */ 
/*     */   private void playButtonCombine()
/*     */   {
/* 897 */     this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:hhon", 0.3F, 1.0F, false);
/*     */   }
/*     */   
/*     */ 
/*     */   private void playButtonWrite()
/*     */   {
/* 903 */     this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:write", 0.2F, 1.0F, false);
/*     */   }
/*     */   
/*     */ 
/*     */   private void playButtonErase()
/*     */   {
/* 909 */     this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:erase", 0.2F, 1.0F + this.field_146297_k.field_71451_h.field_70170_p.field_73012_v.nextFloat() * 0.1F, false);
/*     */   }
/*     */   
/*     */ 
/*     */   private void playButtonScroll()
/*     */   {
/* 915 */     this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:key", 0.3F, 1.0F, false);
/*     */   }
/*     */   
/*     */ 
/*     */   private void drawOrb(double x, double y)
/*     */   {
/* 921 */     int count = FMLClientHandler.instance().getClient().field_71439_g.field_70173_aa;
/* 922 */     float red = 0.7F + MathHelper.func_76126_a((float)((count + x) / 10.0D)) * 0.15F + 0.15F;
/* 923 */     float green = 0.7F + MathHelper.func_76126_a((float)((count + x + y) / 11.0D)) * 0.15F + 0.15F;
/* 924 */     float blue = 0.7F + MathHelper.func_76126_a((float)((count + y) / 12.0D)) * 0.15F + 0.15F;
/* 925 */     Color c = new Color(red, green, blue);
/* 926 */     drawOrb(x, y, c.getRGB());
/*     */   }
/*     */   
/*     */   private void drawOrb(double x, double y, int color)
/*     */   {
/* 931 */     int count = FMLClientHandler.instance().getClient().field_71439_g.field_70173_aa;
/* 932 */     Color c = new Color(color);
/* 933 */     float red = c.getRed() / 255.0F;
/* 934 */     float green = c.getGreen() / 255.0F;
/* 935 */     float blue = c.getBlue() / 255.0F;
/* 936 */     if (thaumcraft.common.config.Config.colorBlind) {
/* 937 */       red /= 1.8F;
/* 938 */       green /= 1.8F;
/* 939 */       blue /= 1.8F;
/*     */     }
/* 941 */     GL11.glPushMatrix();
/* 942 */     UtilsFX.bindTexture(thaumcraft.client.fx.ParticleEngine.particleTexture);
/* 943 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 944 */     GL11.glTranslated(x, y, 0.0D);
/* 945 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 946 */     int part = count % 8;
/* 947 */     float var8 = 0.5F + part / 8.0F;
/* 948 */     float var9 = var8 + 0.0624375F;
/* 949 */     float var10 = 0.5F;
/* 950 */     float var11 = var10 + 0.0624375F;
/* 951 */     tessellator.func_78382_b();
/* 952 */     tessellator.func_78380_c(240);
/* 953 */     tessellator.func_78369_a(red, green, blue, 1.0F);
/* 954 */     tessellator.func_78374_a(0.0D, 16.0D, this.field_73735_i, var9, var11);
/* 955 */     tessellator.func_78374_a(16.0D, 16.0D, this.field_73735_i, var9, var10);
/* 956 */     tessellator.func_78374_a(16.0D, 0.0D, this.field_73735_i, var8, var10);
/* 957 */     tessellator.func_78374_a(0.0D, 0.0D, this.field_73735_i, var8, var11);
/* 958 */     tessellator.func_78381_a();
/* 959 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/gui/GuiResearchTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */