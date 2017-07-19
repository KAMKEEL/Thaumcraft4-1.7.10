/*     */ package thaumcraft.client.gui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.multiplayer.PlayerControllerMP;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.wands.FocusUpgradeType;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.container.ContainerFocalManipulator;
/*     */ import thaumcraft.common.tiles.TileFocalManipulator;
/*     */ 
/*     */ @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */ public class GuiFocalManipulator extends GuiContainer
/*     */ {
/*     */   private TileFocalManipulator table;
/*     */   private float xSize_lo;
/*     */   private float ySize_lo;
/*     */   
/*     */   public GuiFocalManipulator(InventoryPlayer par1InventoryPlayer, TileFocalManipulator table)
/*     */   {
/*  42 */     super(new ContainerFocalManipulator(par1InventoryPlayer, table));
/*     */     
/*  44 */     this.table = table;
/*  45 */     this.field_146999_f = 192;
/*  46 */     this.field_147000_g = 233;
/*     */     
/*  48 */     if (table.size > 0) {
/*  49 */       gatherInfo();
/*  50 */       this.selected = table.upgrade;
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
/*     */ 
/*     */   public void func_73863_a(int par1, int par2, float par3)
/*     */   {
/*  64 */     super.func_73863_a(par1, par2, par3);
/*  65 */     this.xSize_lo = par1;
/*  66 */     this.ySize_lo = par2;
/*     */     
/*  68 */     int baseX = this.field_147003_i;
/*  69 */     int baseY = this.field_147009_r;
/*     */     
/*  71 */     int mposx = 0;
/*  72 */     int mposy = 0;
/*     */     
/*  74 */     if (this.rank > 0) {
/*  75 */       for (int a = 0; a < this.possibleUpgrades.size(); a++) {
/*  76 */         mposx = par1 - (baseX + 48 + a * 16);
/*  77 */         mposy = par2 - (baseY + 104);
/*  78 */         if ((mposx >= 0) && (mposy >= 0) && (mposx < 16) && (mposy < 16)) {
/*  79 */           FocusUpgradeType u = (FocusUpgradeType)this.possibleUpgrades.get(a);
/*  80 */           List list = new ArrayList();
/*  81 */           list.add(EnumChatFormatting.DARK_PURPLE + "" + EnumChatFormatting.UNDERLINE + u.getLocalizedName());
/*  82 */           list.add(u.getLocalizedText());
/*  83 */           drawHoveringTextFixed(list, baseX + this.field_146999_f - 36, baseY + 24, this.field_146289_q, this.field_146294_l - (baseX + this.field_146999_f - 16));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  88 */     if (this.selected >= 0) {
/*  89 */       mposx = par1 - (baseX + 48);
/*  90 */       mposy = par2 - (baseY + 48);
/*  91 */       if ((mposx >= 0) && (mposy >= 0) && (mposx < 36) && (mposy < 36)) {
/*  92 */         List list = new ArrayList();
/*  93 */         list.add(StatCollector.func_74838_a("wandtable.text1"));
/*  94 */         drawHoveringText(list, par1, par2, this.field_146289_q);
/*     */       }
/*  96 */       mposx = par1 - (baseX + 108);
/*  97 */       mposy = par2 - (baseY + 58);
/*  98 */       if ((mposx >= 0) && (mposy >= 0) && (mposx < 36) && (mposy < 16)) {
/*  99 */         List list = new ArrayList();
/* 100 */         list.add(StatCollector.func_74838_a("wandtable.text2"));
/* 101 */         drawHoveringText(list, par1, par2, this.field_146289_q);
/*     */       }
/* 103 */       if ((this.table.size == 0) && (this.rank * 8 <= this.field_146297_k.field_71439_g.field_71068_ca)) {
/* 104 */         mposx = par1 - (baseX + 48);
/* 105 */         mposy = par2 - (baseY + 88);
/* 106 */         if ((mposx >= 0) && (mposy >= 0) && (mposx < 96) && (mposy < 8)) {
/* 107 */           List list = new ArrayList();
/* 108 */           list.add(StatCollector.func_74838_a("wandtable.text3"));
/* 109 */           drawHoveringText(list, par1, par2, this.field_146289_q);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 114 */     for (int a = 0; a < this.upgrades.size(); a++) {
/* 115 */       mposx = par1 - (baseX + 56 + a * 16);
/* 116 */       mposy = par2 - (baseY + 32);
/* 117 */       if ((mposx >= 0) && (mposy >= 0) && (mposx < 16) && (mposy < 16)) {
/* 118 */         FocusUpgradeType u = (FocusUpgradeType)this.upgrades.get(a);
/* 119 */         List list = new ArrayList();
/* 120 */         list.add(EnumChatFormatting.DARK_PURPLE + "" + EnumChatFormatting.UNDERLINE + u.getLocalizedName());
/* 121 */         list.add(u.getLocalizedText());
/* 122 */         drawHoveringTextFixed(list, baseX + this.field_146999_f - 36, baseY + 24, this.field_146289_q, this.field_146294_l - (baseX + this.field_146999_f - 16));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/* 128 */   int selected = -1;
/* 129 */   int rank = 0;
/*     */   long time;
/* 131 */   long nextSparkle = 0L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_146976_a(float par1, int par2, int par3)
/*     */   {
/* 138 */     this.time = System.currentTimeMillis();
/* 139 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 140 */     UtilsFX.bindTexture("textures/gui/gui_wandtable.png");
/* 141 */     int k = (this.field_146294_l - this.field_146999_f) / 2;
/* 142 */     int l = (this.field_146295_m - this.field_147000_g) / 2;
/*     */     
/* 144 */     GL11.glEnable(3042);
/* 145 */     GL11.glBlendFunc(770, 771);
/*     */     
/* 147 */     func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
/*     */     
/* 149 */     if ((this.table.func_70301_a(0) == null) || (this.table.rank < 0) || (this.table.reset)) {
/* 150 */       this.rank = 0;
/* 151 */       this.selected = -1;
/* 152 */       this.possibleUpgrades.clear();
/* 153 */       this.upgrades.clear();
/* 154 */       this.aspects = new AspectList();
/* 155 */       this.table.reset = false;
/* 156 */       this.table.rank = 0;
/*     */     }
/*     */     
/* 159 */     if (this.rank > 0) {
/* 160 */       for (int a = 0; a < this.possibleUpgrades.size(); a++) {
/* 161 */         FocusUpgradeType u = (FocusUpgradeType)this.possibleUpgrades.get(a);
/* 162 */         if (this.selected == u.id) {
/* 163 */           func_73729_b(k + 48 + a * 16, l + 104, 200, 0, 16, 16);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 168 */     if ((this.rank > 0) && (this.selected >= 0) && (this.table.func_70301_a(0) != null)) {
/* 169 */       int xp = this.rank * 8;
/*     */       
/* 171 */       if ((this.table.size == 0) && (xp <= this.field_146297_k.field_71439_g.field_71068_ca)) {
/* 172 */         func_73729_b(k + 48, l + 88, 8, 240, 96, 8);
/*     */       }
/* 174 */       func_73729_b(k + 108, l + 59, 200, 16, 16, 16);
/* 175 */       int start = 0;
/* 176 */       if (this.table.aspects.size() > 0) {
/* 177 */         for (Aspect aspect : this.table.aspects.getAspectsSorted()) {
/* 178 */           if ((aspect != null) && (this.table.aspects.getAmount(aspect) != 0)) {
/* 179 */             int size = (int)(this.table.aspects.getAmount(aspect) / this.table.size * 96.0F);
/* 180 */             Color c = new Color(aspect.getColor());
/* 181 */             GL11.glColor4f(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, 0.9F);
/* 182 */             func_73729_b(k + 48 + start, l + 88, 112 + start, 240, size, 8);
/*     */             
/* 184 */             start += size;
/*     */             
/* 186 */             if (this.table.func_145831_w().field_73012_v.nextInt(66) == 0) {
/* 187 */               float x = 48 + start;
/* 188 */               float y = 92.0F;
/* 189 */               float xx = (46 + this.rank * 16 - x) / 9.0F;
/* 190 */               float yy = (38.0F - y) / 9.0F;
/* 191 */               this.sparkles.put(Long.valueOf(this.time), new Sparkle(x, y, xx, yy, c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F));
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 198 */       this.field_146289_q.func_78261_a("" + xp, k + 125, l + 64, xp > this.field_146297_k.field_71439_g.field_71068_ca ? 16151160 : 10092429);
/*     */       
/*     */ 
/* 201 */       AspectList al = this.aspects;
/* 202 */       if (this.table.size > 0) al = this.table.aspects;
/* 203 */       int q = 0;
/* 204 */       for (Aspect a : al.getAspectsSorted()) {
/* 205 */         if (a != null) {
/* 206 */           GL11.glPushMatrix();
/* 207 */           GL11.glTranslated(k + 49, l + 68 - al.size() * 2.5D, 0.0D);
/* 208 */           GL11.glScaled(0.5D, 0.5D, 0.5D);
/* 209 */           this.field_146289_q.func_78261_a(a.getName(), 0, q * 10, a.getColor());
/* 210 */           String s = this.myFormatter.format(al.getAmount(a) / 100.0F);
/* 211 */           this.field_146289_q.func_78261_a(s, 48, q * 10, a.getColor());
/* 212 */           GL11.glPopMatrix();
/* 213 */           q++;
/*     */         }
/*     */       }
/*     */     }
/* 217 */     if (this.rank > 0) {
/* 218 */       if (this.nextSparkle < this.time) {
/* 219 */         this.nextSparkle = (this.time + (this.table.size > 0 ? '\n' : 'Ǵ') + this.table.func_145831_w().field_73012_v.nextInt(200));
/* 220 */         this.sparkles.put(Long.valueOf(this.time), new Sparkle(42 + this.rank * 16 + this.table.func_145831_w().field_73012_v.nextInt(12), 34 + this.table.func_145831_w().field_73012_v.nextInt(12), 0.0F, 0.0F, 0.5F + this.table.func_145831_w().field_73012_v.nextFloat() * 0.4F, 1.0F - this.table.func_145831_w().field_73012_v.nextFloat() * 0.4F, 1.0F - this.table.func_145831_w().field_73012_v.nextFloat() * 0.4F));
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 228 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 229 */       for (int a = 0; a < this.possibleUpgrades.size(); a++) {
/* 230 */         FocusUpgradeType u = (FocusUpgradeType)this.possibleUpgrades.get(a);
/* 231 */         GL11.glPushMatrix();
/* 232 */         GL11.glEnable(3042);
/* 233 */         GL11.glBlendFunc(770, 771);
/* 234 */         this.field_146297_k.field_71446_o.func_110577_a(u.icon);
/* 235 */         UtilsFX.drawTexturedQuadFull(k + 48 + a * 16, l + 104, this.field_73735_i);
/* 236 */         GL11.glPopMatrix();
/*     */       }
/*     */     }
/* 239 */     else if ((this.rank == 0) && (this.table.func_70301_a(0) != null)) {
/* 240 */       try { gatherInfo();
/*     */       } catch (Exception e) {} }
/* 242 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 243 */     for (int a = 0; a < this.upgrades.size(); a++) {
/* 244 */       FocusUpgradeType u = (FocusUpgradeType)this.upgrades.get(a);
/* 245 */       GL11.glPushMatrix();
/* 246 */       GL11.glEnable(3042);
/* 247 */       GL11.glBlendFunc(770, 771);
/* 248 */       this.field_146297_k.field_71446_o.func_110577_a(u.icon);
/* 249 */       UtilsFX.drawTexturedQuadFull(k + 56 + a * 16, l + 32, this.field_73735_i);
/* 250 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 253 */     GL11.glDisable(3042);
/*     */   }
/*     */   
/* 256 */   DecimalFormat myFormatter = new DecimalFormat("#######.#");
/* 257 */   ArrayList<FocusUpgradeType> possibleUpgrades = new ArrayList();
/* 258 */   ArrayList<FocusUpgradeType> upgrades = new ArrayList();
/* 259 */   AspectList aspects = new AspectList();
/*     */   
/*     */   private void gatherInfo()
/*     */   {
/* 263 */     this.possibleUpgrades.clear();
/* 264 */     this.upgrades.clear();
/* 265 */     this.aspects = new AspectList();
/*     */     
/* 267 */     ItemFocusBasic focus = (ItemFocusBasic)this.table.func_70301_a(0).func_77973_b();
/* 268 */     short[] s = focus.getAppliedUpgrades(this.table.func_70301_a(0));
/* 269 */     this.rank = 1;
/* 270 */     int fu = 0;
/* 271 */     for (; (this.rank <= 5) && 
/* 272 */           (s[(this.rank - 1)] != -1); this.rank += 1)
/*     */     {
/*     */ 
/*     */ 
/* 275 */       this.upgrades.add(FocusUpgradeType.types[s[(this.rank - 1)]]);
/* 276 */       fu++;
/*     */     }
/*     */     
/* 279 */     if (fu == 5) {
/* 280 */       this.rank = -1;
/*     */     } else {
/* 282 */       FocusUpgradeType[] ut = focus.getPossibleUpgradesByRank(this.table.func_70301_a(0), this.rank);
/* 283 */       if (ut == null) return;
/* 284 */       for (int a = 0; a < ut.length; a++) {
/* 285 */         if (focus.canApplyUpgrade(this.table.func_70301_a(0), Minecraft.func_71410_x().field_71439_g, ut[a], this.rank)) {
/* 286 */           this.possibleUpgrades.add(ut[a]);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 292 */     if (this.table.size > 0) {
/* 293 */       this.selected = this.table.upgrade;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_146979_b(int p_146979_1_, int p_146979_2_)
/*     */   {
/* 300 */     UtilsFX.bindTexture(thaumcraft.client.fx.ParticleEngine.particleTexture);
/* 301 */     Long[] keys = (Long[])this.sparkles.keySet().toArray(new Long[0]);
/* 302 */     for (Long key : keys) {
/* 303 */       Sparkle s = (Sparkle)this.sparkles.get(key);
/* 304 */       drawSparkle(s.x, s.y, s.frame, s.r, s.g, s.b);
/* 305 */       if (s.nextframe < this.time) {
/* 306 */         s.frame += 1;
/* 307 */         s.nextframe = (this.time + 50L);
/* 308 */         s.x += s.mx;
/* 309 */         s.y += s.my;
/*     */       }
/* 311 */       if (s.frame == 9) {
/* 312 */         this.sparkles.remove(key);
/*     */       } else {
/* 314 */         this.sparkles.put(key, s);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_73864_a(int mx, int my, int par3)
/*     */   {
/* 322 */     super.func_73864_a(mx, my, par3);
/*     */     
/* 324 */     int gx = (this.field_146294_l - this.field_146999_f) / 2;
/* 325 */     int gy = (this.field_146295_m - this.field_147000_g) / 2;
/*     */     
/*     */ 
/* 328 */     int var7 = mx - (gx + 48);
/* 329 */     int var8 = my - (gy + 88);
/*     */     
/* 331 */     if ((this.table.size == 0) && (this.selected >= 0) && (this.rank * 8 <= this.field_146297_k.field_71439_g.field_71068_ca) && (var7 >= 0) && (var8 >= 0) && (var7 < 96) && (var8 < 8))
/*     */     {
/*     */ 
/* 334 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, this.selected);
/* 335 */       playButtonClick();
/* 336 */       return;
/*     */     }
/*     */     
/* 339 */     if (this.table.size == 0) {
/* 340 */       for (int a = 0; a < this.possibleUpgrades.size(); a++) {
/* 341 */         FocusUpgradeType u = (FocusUpgradeType)this.possibleUpgrades.get(a);
/* 342 */         var7 = mx - (gx + 48 + a * 16);
/* 343 */         var8 = my - (gy + 104);
/* 344 */         if ((var7 >= 0) && (var8 >= 0) && (var7 < 16) && (var8 < 16)) {
/* 345 */           this.aspects = new AspectList();
/* 346 */           if (this.selected == u.id) {
/* 347 */             this.selected = -1;
/*     */           }
/*     */           else {
/* 350 */             this.selected = u.id;
/* 351 */             int amt = 200;
/* 352 */             for (int q = 1; q < this.rank; q++) amt *= 2;
/* 353 */             AspectList tal = new AspectList();
/* 354 */             for (Aspect as : FocusUpgradeType.types[this.selected].aspects.getAspects()) {
/* 355 */               tal.add(as, amt);
/*     */             }
/* 357 */             this.aspects = thaumcraft.common.lib.research.ResearchManager.reduceToPrimals(tal);
/*     */           }
/* 359 */           playButtonClick();
/* 360 */           return;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void playButtonClick() {
/* 367 */     this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:cameraclack", 0.4F, 1.0F, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 372 */   HashMap<Long, Sparkle> sparkles = new HashMap();
/*     */   
/*     */   private class Sparkle { float x;
/*     */     float y;
/*     */     float mx;
/*     */     float my;
/*     */     float r;
/*     */     float g;
/*     */     float b;
/* 381 */     public Sparkle(float x, float y, float mx, float my, float r, float g, float b) { this.x = x;
/* 382 */       this.y = y;
/* 383 */       this.mx = mx;
/* 384 */       this.my = my;
/* 385 */       this.frame = 0;
/* 386 */       this.r = r;
/* 387 */       this.g = g;
/* 388 */       this.b = b;
/* 389 */       this.nextframe = (System.currentTimeMillis() + 50L); }
/*     */     
/*     */     long nextframe;
/*     */     int frame; }
/*     */   
/* 394 */   private void drawSparkle(double x, double y, int frame, float r, float g, float b) { GL11.glPushMatrix();
/* 395 */     GL11.glEnable(3042);
/* 396 */     GL11.glBlendFunc(770, 1);
/* 397 */     GL11.glColor4f(r, g, b, 0.9F);
/* 398 */     GL11.glTranslated(x, y, 200.0D);
/* 399 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 400 */     float var8 = frame / 16.0F;
/* 401 */     float var9 = var8 + 0.0624375F;
/* 402 */     float var10 = 0.4375F;
/* 403 */     float var11 = var10 + 0.0624375F;
/* 404 */     tessellator.func_78382_b();
/* 405 */     tessellator.func_78380_c(220);
/* 406 */     tessellator.func_78369_a(r, g, b, 0.9F);
/* 407 */     tessellator.func_78374_a(-4.0D, 4.0D, this.field_73735_i, var9, var11);
/* 408 */     tessellator.func_78374_a(4.0D, 4.0D, this.field_73735_i, var9, var10);
/* 409 */     tessellator.func_78374_a(4.0D, -4.0D, this.field_73735_i, var8, var10);
/* 410 */     tessellator.func_78374_a(-4.0D, -4.0D, this.field_73735_i, var8, var11);
/* 411 */     tessellator.func_78381_a();
/* 412 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 413 */     GL11.glDisable(3042);
/* 414 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   protected void drawHoveringTextFixed(List listin, int x, int y, FontRenderer font, int width)
/*     */   {
/* 419 */     if (!listin.isEmpty())
/*     */     {
/* 421 */       List list = new ArrayList();
/* 422 */       for (Object o : listin) {
/* 423 */         String s = (String)o;
/* 424 */         s = trimStringNewline(s);
/*     */         
/* 426 */         List list2 = font.func_78271_c(s, width);
/*     */         
/* 428 */         for (iterator = list2.iterator(); iterator.hasNext();)
/*     */         {
/* 430 */           String s1 = (String)iterator.next();
/* 431 */           list.add(s1);
/*     */         }
/*     */       }
/*     */       Iterator iterator;
/* 435 */       GL11.glDisable(32826);
/* 436 */       RenderHelper.func_74518_a();
/* 437 */       GL11.glDisable(2896);
/* 438 */       GL11.glDisable(2929);
/* 439 */       int k = 0;
/* 440 */       Iterator iterator = list.iterator();
/*     */       
/* 442 */       while (iterator.hasNext())
/*     */       {
/* 444 */         String s = (String)iterator.next();
/* 445 */         int l = font.func_78256_a(s);
/*     */         
/* 447 */         if (l > k)
/*     */         {
/* 449 */           k = l;
/*     */         }
/*     */       }
/*     */       
/* 453 */       int j2 = x + 12;
/* 454 */       int k2 = y - 12;
/* 455 */       int i1 = 8;
/*     */       
/* 457 */       if (list.size() > 1)
/*     */       {
/* 459 */         i1 += 2 + (list.size() - 1) * 10;
/*     */       }
/*     */       
/*     */ 
/* 463 */       this.field_73735_i = 300.0F;
/* 464 */       field_146296_j.field_77023_b = 300.0F;
/* 465 */       int j1 = -267386864;
/* 466 */       func_73733_a(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, j1, j1);
/* 467 */       func_73733_a(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, j1, j1);
/* 468 */       func_73733_a(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, j1, j1);
/* 469 */       func_73733_a(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1);
/* 470 */       func_73733_a(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, j1, j1);
/* 471 */       int k1 = 1347420415;
/* 472 */       int l1 = (k1 & 0xFEFEFE) >> 1 | k1 & 0xFF000000;
/* 473 */       func_73733_a(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1);
/* 474 */       func_73733_a(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, k1, l1);
/* 475 */       func_73733_a(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, k1, k1);
/* 476 */       func_73733_a(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, l1, l1);
/*     */       
/* 478 */       for (int i2 = 0; i2 < list.size(); i2++)
/*     */       {
/* 480 */         String s1 = (String)list.get(i2);
/* 481 */         font.func_78261_a(s1, j2, k2, -1);
/*     */         
/* 483 */         if (i2 == 0)
/*     */         {
/* 485 */           k2 += 2;
/*     */         }
/*     */         
/* 488 */         k2 += 10;
/*     */       }
/*     */       
/* 491 */       this.field_73735_i = 0.0F;
/* 492 */       field_146296_j.field_77023_b = 0.0F;
/* 493 */       GL11.glEnable(2896);
/* 494 */       GL11.glEnable(2929);
/* 495 */       RenderHelper.func_74519_b();
/* 496 */       GL11.glEnable(32826);
/*     */     }
/*     */   }
/*     */   
/*     */   private String trimStringNewline(String p_78273_1_)
/*     */   {
/* 502 */     while ((p_78273_1_ != null) && (p_78273_1_.endsWith("\n")))
/*     */     {
/* 504 */       p_78273_1_ = p_78273_1_.substring(0, p_78273_1_.length() - 1);
/*     */     }
/*     */     
/* 507 */     return p_78273_1_;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/gui/GuiFocalManipulator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */