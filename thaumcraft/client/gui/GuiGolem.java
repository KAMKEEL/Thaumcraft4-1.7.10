/*     */ package thaumcraft.client.gui;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.multiplayer.PlayerControllerMP;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.entity.RenderLiving;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidContainerRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.util.glu.GLU;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.client.renderers.entity.RenderGolemBase;
/*     */ import thaumcraft.client.renderers.models.entities.ModelGolem;
/*     */ import thaumcraft.common.container.SlotGhostFluid;
/*     */ import thaumcraft.common.entities.InventoryMob;
/*     */ import thaumcraft.common.entities.golems.ContainerGolem;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ import thaumcraft.common.entities.golems.EnumGolemType;
/*     */ import thaumcraft.common.entities.golems.ItemGolemCore;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class GuiGolem
/*     */   extends GuiContainer
/*     */ {
/*     */   private float xSize_lo;
/*     */   private float ySize_lo;
/*     */   private EntityGolemBase golem;
/*  53 */   private int threat = -1;
/*     */   
/*  55 */   RenderLiving rgb = new RenderGolemBase(new ModelGolem(false));
/*     */   
/*     */ 
/*     */   public GuiGolem(EntityPlayer player, EntityGolemBase e)
/*     */   {
/*  60 */     super(new ContainerGolem(player.field_71071_by, e.inventory));
/*  61 */     this.golem = e;
/*  62 */     if ((this.golem.advanced) && (this.golem.field_70170_p.field_73012_v.nextInt(4) == 0)) {
/*  63 */       this.threat = this.golem.field_70170_p.field_73012_v.nextInt(9);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_146979_b(int par1, int par2)
/*     */   {
/*  75 */     GL11.glPushMatrix();
/*  76 */     GL11.glScaled(0.5D, 0.5D, 0.5D);
/*  77 */     if (this.threat >= 0) {
/*  78 */       this.field_146289_q.func_78279_b(StatCollector.func_74838_a("golemthreat." + this.threat + ".text"), 80, 22, 110, 14540253);
/*     */     } else {
/*  80 */       this.field_146289_q.func_78279_b(StatCollector.func_74838_a("golemblurb." + this.golem.getCore() + ".text"), 80, 22, 110, 14540253);
/*     */     }
/*  82 */     if (((ContainerGolem)this.field_147002_h).maxScroll > 0) {
/*  83 */       this.field_146289_q.func_78276_b(((ContainerGolem)this.field_147002_h).currentScroll + 1 + "/" + (((ContainerGolem)this.field_147002_h).maxScroll + 1), 323, 140, 14540253);
/*     */     }
/*     */     
/*     */ 
/*  87 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_73863_a(int par1, int par2, float par3)
/*     */   {
/*  96 */     super.func_73863_a(par1, par2, par3);
/*  97 */     this.xSize_lo = par1;
/*  98 */     this.ySize_lo = par2;
/*     */     
/* 100 */     int baseX = this.field_147003_i;
/* 101 */     int baseY = this.field_147009_r;
/*     */     
/* 103 */     int slots = this.golem.inventory.slotCount;
/* 104 */     int typeLoc = this.golem.getGolemType().ordinal() * 24;
/* 105 */     if ((this.golem.getCore() > -1) && (ItemGolemCore.hasInventory(this.golem.getCore())) && (this.golem.getUpgradeAmount(5) > 0)) {
/* 106 */       for (int a = 0; a < Math.min(6, slots); a++) {
/* 107 */         int mposx = par1 - (baseX + 96 + a / 2 * 28);
/* 108 */         int mposy = par2 - (baseY + 4 + a % 2 * 31);
/*     */         
/* 110 */         if ((mposx >= 0) && (mposy >= 0) && (mposx < 24) && (mposy < 12))
/*     */         {
/* 112 */           String text = "Any color";
/* 113 */           if (this.golem.getColors(a + ((ContainerGolem)this.field_147002_h).currentScroll * 6) >= 0) { text = thaumcraft.common.lib.utils.Utils.colorNames[this.golem.getColors(a + ((ContainerGolem)this.field_147002_h).currentScroll * 6)];
/*     */           }
/* 115 */           int size = this.field_146289_q.func_78256_a(text);
/* 116 */           this.field_146289_q.func_78276_b(text, baseX + 133 - size / 2, baseY - 6, 16645629);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_146976_a(float par1, int par2, int par3)
/*     */   {
/* 129 */     int baseX = this.field_147003_i;
/* 130 */     int baseY = this.field_147009_r;
/*     */     
/* 132 */     int mposx = par2 - (baseX + 139);
/* 133 */     int mposy = par3 - (baseY + 10);
/*     */     
/* 135 */     GL11.glPushMatrix();
/* 136 */     GL11.glEnable(3042);
/*     */     
/* 138 */     UtilsFX.bindTexture("textures/gui/guigolem.png");
/* 139 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 140 */     func_73729_b(baseX, baseY, 0, 0, this.field_146999_f, this.field_147000_g);
/*     */     
/* 142 */     int slots = this.golem.inventory.slotCount;
/* 143 */     int typeLoc = this.golem.getGolemType().ordinal() * 24;
/* 144 */     IIcon icon = null;
/* 145 */     if ((this.golem.getCore() > -1) && (ItemGolemCore.hasInventory(this.golem.getCore()))) {
/* 146 */       for (int a = 0; a < Math.min(6, slots); a++) {
/* 147 */         func_73729_b(baseX + 96 + a / 2 * 28, baseY + 12 + a % 2 * 31, 184, typeLoc, 24, 24);
/* 148 */         if (this.golem.getUpgradeAmount(4) > 0) {
/* 149 */           func_73729_b(baseX + 96 + a / 2 * 28, baseY + 4 + a % 2 * 31, 72, 168, 24, 12);
/* 150 */           int color = this.golem.getColors(a + ((ContainerGolem)this.field_147002_h).currentScroll * 6);
/* 151 */           if (color > -1) {
/* 152 */             Color c = new Color(thaumcraft.common.lib.utils.Utils.colors[color]);
/* 153 */             float r = c.getRed() / 255.0F;
/* 154 */             float g = c.getGreen() / 255.0F;
/* 155 */             float b = c.getBlue() / 255.0F;
/* 156 */             GL11.glColor4f(r, g, b, 1.0F);
/* 157 */             func_73729_b(baseX + 105 + a / 2 * 28, baseY + 7 + a % 2 * 31, 0, 176, 6, 6);
/* 158 */             GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */           }
/*     */         }
/* 161 */         if (this.golem.getCore() == 5) {
/* 162 */           FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(this.golem.inventory.func_70301_a(a + ((ContainerGolem)this.field_147002_h).currentScroll * 6));
/* 163 */           if (fluid != null) {
/* 164 */             icon = fluid.getFluid().getIcon();
/* 165 */             if (icon != null) {
/* 166 */               GL11.glPushMatrix();
/* 167 */               GL11.glTranslatef(baseX + 108 + a / 2 * 28, baseY + 24 + a % 2 * 31, 0.0F);
/* 168 */               UtilsFX.renderQuadCenteredFromIcon(true, icon, 16.0F, 1.0F, 1.0F, 1.0F, 200, 771, 1.0F);
/* 169 */               GL11.glPopMatrix();
/* 170 */               UtilsFX.bindTexture("textures/gui/guigolem.png");
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 175 */       if (slots > 6) {
/* 176 */         if (((ContainerGolem)this.field_147002_h).currentScroll > 0) {
/* 177 */           func_73729_b(baseX + 111, baseY + 68, 0, 200, 24, 8);
/*     */         } else
/* 179 */           func_73729_b(baseX + 111, baseY + 68, 0, 208, 24, 8);
/* 180 */         if (((ContainerGolem)this.field_147002_h).currentScroll < ((ContainerGolem)this.field_147002_h).maxScroll) {
/* 181 */           func_73729_b(baseX + 135, baseY + 68, 24, 200, 24, 8);
/*     */         } else {
/* 183 */           func_73729_b(baseX + 135, baseY + 68, 24, 208, 24, 8);
/*     */         }
/*     */       }
/*     */     }
/* 187 */     if ((this.golem.getCore() == 4) && 
/* 188 */       (this.golem.getUpgradeAmount(4) > 0)) {
/* 189 */       func_73729_b(baseX + 104, baseY + 5, 8, 168, 8, 8);
/* 190 */       func_73729_b(baseX + 104, baseY + 21, 8, 168, 8, 8);
/* 191 */       func_73729_b(baseX + 104, baseY + 37, 8, 168, 8, 8);
/* 192 */       func_73729_b(baseX + 104, baseY + 53, 8, 168, 8, 8);
/*     */       
/* 194 */       if (this.golem.canAttackHostiles()) func_73729_b(baseX + 104, baseY + 5, 8, 176, 8, 8);
/* 195 */       if (this.golem.canAttackAnimals()) func_73729_b(baseX + 104, baseY + 21, 8, 176, 8, 8);
/* 196 */       if (this.golem.canAttackPlayers()) func_73729_b(baseX + 104, baseY + 37, 8, 176, 8, 8);
/* 197 */       if (this.golem.canAttackCreepers()) { func_73729_b(baseX + 104, baseY + 53, 8, 176, 8, 8);
/*     */       }
/* 199 */       this.field_146289_q.func_78276_b("Monsters", baseX + 122, baseY + 6, 16764108);
/* 200 */       this.field_146289_q.func_78276_b("Animals", baseX + 122, baseY + 22, 16777164);
/* 201 */       this.field_146289_q.func_78276_b("Players", baseX + 122, baseY + 38, 13421823);
/* 202 */       this.field_146289_q.func_78276_b("Creepers", baseX + 122, baseY + 54, 13434828);
/* 203 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*     */     
/*     */ 
/* 207 */     if (this.golem.getCore() == 0) {
/* 208 */       func_73729_b(baseX + 62, baseY + 54, 8, 168, 8, 8);
/* 209 */       String text = "Precise amount";
/* 210 */       if (this.golem.getToggles()[0] == 0) {
/* 211 */         func_73729_b(baseX + 62, baseY + 54, 8, 176, 8, 8);
/*     */       } else {
/* 213 */         text = "Any amount";
/*     */       }
/*     */       
/* 216 */       GL11.glPushMatrix();
/* 217 */       GL11.glTranslatef(baseX + 66, baseY + 48, 0.0F);
/* 218 */       GL11.glScalef(0.5F, 0.5F, 0.0F);
/* 219 */       int size = this.field_146289_q.func_78256_a(text);
/* 220 */       this.field_146289_q.func_78276_b(text, -size / 2, 0, 16645629);
/* 221 */       GL11.glScalef(1.0F, 1.0F, 1.0F);
/* 222 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 225 */     if (this.golem.getCore() == 8) {
/* 226 */       func_73729_b(baseX + 42, baseY + 40, 8, 168, 8, 8);
/* 227 */       String text1 = "Block";
/* 228 */       if (this.golem.getToggles()[0] == 0) {
/* 229 */         func_73729_b(baseX + 42, baseY + 40, 8, 176, 8, 8);
/*     */       } else {
/* 231 */         text1 = "Empty space";
/*     */       }
/*     */       
/* 234 */       func_73729_b(baseX + 42, baseY + 50, 8, 168, 8, 8);
/* 235 */       String text2 = "Right click";
/* 236 */       if (this.golem.getToggles()[1] == 0) {
/* 237 */         func_73729_b(baseX + 42, baseY + 50, 8, 176, 8, 8);
/*     */       } else {
/* 239 */         text2 = "Left click";
/*     */       }
/*     */       
/* 242 */       func_73729_b(baseX + 42, baseY + 60, 8, 168, 8, 8);
/* 243 */       String text3 = "Not sneaking";
/* 244 */       if (this.golem.getToggles()[2] == 0) {
/* 245 */         func_73729_b(baseX + 42, baseY + 60, 8, 176, 8, 8);
/*     */       } else {
/* 247 */         text3 = "Sneaking";
/*     */       }
/*     */       
/* 250 */       GL11.glPushMatrix();
/* 251 */       GL11.glTranslatef(baseX + 53, baseY + 42, 0.0F);
/* 252 */       GL11.glScalef(0.5F, 0.5F, 0.0F);
/* 253 */       this.field_146289_q.func_78276_b(text1, 0, 0, 16645629);
/* 254 */       this.field_146289_q.func_78276_b(text2, 0, 20, 16645629);
/* 255 */       this.field_146289_q.func_78276_b(text3, 0, 40, 16645629);
/* 256 */       GL11.glScalef(1.0F, 1.0F, 1.0F);
/* 257 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 260 */     if ((this.golem.getUpgradeAmount(5) > 0) && (ItemGolemCore.canSort(this.golem.getCore()))) {
/* 261 */       int shiftx = this.golem.getCore() == 10 ? 66 : 180;
/* 262 */       int shifty = this.golem.getCore() == 10 ? 12 : 0;
/* 263 */       func_73729_b(baseX + shiftx, baseY + 24 + shifty, 8, 168, 8, 8);
/* 264 */       String text1 = "Use Ore dictionary";
/* 265 */       if (this.golem.checkOreDict()) {
/* 266 */         func_73729_b(baseX + shiftx, baseY + 24 + shifty, 8, 176, 8, 8);
/*     */       }
/*     */       
/* 269 */       func_73729_b(baseX + shiftx, baseY + 34 + shifty, 8, 168, 8, 8);
/* 270 */       String text2 = "Ignore item damage";
/* 271 */       if (this.golem.ignoreDamage()) {
/* 272 */         func_73729_b(baseX + shiftx, baseY + 34 + shifty, 8, 176, 8, 8);
/*     */       }
/*     */       
/* 275 */       func_73729_b(baseX + shiftx, baseY + 44 + shifty, 8, 168, 8, 8);
/* 276 */       String text3 = "Ignore NBT values";
/* 277 */       if (this.golem.ignoreNBT()) {
/* 278 */         func_73729_b(baseX + shiftx, baseY + 44 + shifty, 8, 176, 8, 8);
/*     */       }
/*     */       
/* 281 */       GL11.glPushMatrix();
/* 282 */       GL11.glTranslatef(baseX + shiftx + 10, baseY + 26 + shifty, 0.0F);
/* 283 */       GL11.glScalef(0.5F, 0.5F, 0.0F);
/* 284 */       this.field_146289_q.func_78276_b(text1, 0, 0, this.golem.checkOreDict() ? 16645629 : 6710886);
/* 285 */       this.field_146289_q.func_78276_b(text2, 0, 20, this.golem.ignoreDamage() ? 16645629 : 6710886);
/* 286 */       this.field_146289_q.func_78276_b(text3, 0, 40, this.golem.ignoreNBT() ? 16645629 : 6710886);
/* 287 */       GL11.glScalef(1.0F, 1.0F, 1.0F);
/* 288 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 289 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/*     */ 
/* 293 */     GL11.glDisable(3042);
/* 294 */     GL11.glPopMatrix();
/*     */     
/* 296 */     drawGolem(this.field_146297_k, baseX + 51, baseY + 75, 30, baseX + 51 - this.xSize_lo, baseY + 75 - 50 - this.ySize_lo);
/* 297 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/* 300 */   private static ModelGolem model = new ModelGolem(true);
/*     */   private Slot theSlot;
/*     */   
/*     */   public void drawGolem(Minecraft mc, int par1, int par2, int par3, float par4, float par5) {
/* 304 */     GL11.glEnable(2903);
/* 305 */     GL11.glPushMatrix();
/* 306 */     GL11.glMatrixMode(5889);
/* 307 */     GL11.glPushMatrix();
/*     */     
/* 309 */     GL11.glLoadIdentity();
/* 310 */     ScaledResolution var7 = new ScaledResolution(Minecraft.func_71410_x(), this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
/* 311 */     GL11.glViewport((var7.func_78326_a() - 320) / 2 * var7.func_78325_e(), (var7.func_78328_b() - 240) / 2 * var7.func_78325_e(), 320 * var7.func_78325_e(), 240 * var7.func_78325_e());
/* 312 */     GL11.glTranslatef(-0.34F, 0.23F, 0.0F);
/* 313 */     GLU.gluPerspective(90.0F, 1.3333334F, 9.0F, 80.0F);
/* 314 */     float var8 = 1.0F;
/* 315 */     GL11.glMatrixMode(5888);
/* 316 */     GL11.glLoadIdentity();
/*     */     
/* 318 */     RenderHelper.func_74519_b();
/*     */     
/* 320 */     GL11.glTranslatef(-1.5F, -1.0F, -12.0F);
/* 321 */     GL11.glScalef(var8, var8, var8);
/* 322 */     float var9 = 5.0F;
/* 323 */     GL11.glScalef(var9, var9, var9);
/*     */     
/* 325 */     float f2 = this.golem.field_70761_aq;
/* 326 */     float f3 = this.golem.field_70177_z;
/* 327 */     float f4 = this.golem.field_70125_A;
/* 328 */     float f5 = this.golem.field_70758_at;
/* 329 */     float f6 = this.golem.field_70759_as;
/*     */     
/* 331 */     this.golem.field_70761_aq = -20.0F;
/* 332 */     this.golem.field_70177_z = 0.0F;
/* 333 */     this.golem.field_70125_A = 0.0F;
/* 334 */     this.golem.field_70758_at = -5.0F;
/* 335 */     this.golem.field_70759_as = -5.0F;
/*     */     
/* 337 */     RenderManager.field_78727_a.func_147940_a(this.golem, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
/*     */     
/* 339 */     this.golem.field_70761_aq = f2;
/* 340 */     this.golem.field_70177_z = f3;
/* 341 */     this.golem.field_70125_A = f4;
/* 342 */     this.golem.field_70758_at = f5;
/* 343 */     this.golem.field_70759_as = f6;
/*     */     
/*     */ 
/* 346 */     GL11.glDisable(32826);
/* 347 */     RenderHelper.func_74518_a();
/*     */     
/* 349 */     GL11.glMatrixMode(5889);
/* 350 */     GL11.glViewport(0, 0, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
/* 351 */     GL11.glPopMatrix();
/* 352 */     GL11.glMatrixMode(5888);
/* 353 */     RenderHelper.func_74518_a();
/* 354 */     GL11.glPopMatrix();
/* 355 */     GL11.glDisable(32826);
/* 356 */     OpenGlHelper.func_77473_a(OpenGlHelper.field_77476_b);
/* 357 */     GL11.glDisable(3553);
/* 358 */     OpenGlHelper.func_77473_a(OpenGlHelper.field_77478_a);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_73864_a(int par1, int par2, int par3)
/*     */   {
/* 364 */     super.func_73864_a(par1, par2, par3);
/*     */     
/* 366 */     int baseX = (this.field_146294_l - this.field_146999_f) / 2;
/* 367 */     int baseY = (this.field_146295_m - this.field_147000_g) / 2;
/*     */     
/*     */ 
/*     */ 
/* 371 */     int slots = this.golem.inventory.slotCount;
/* 372 */     int typeLoc = this.golem.getGolemType().ordinal() * 24;
/* 373 */     if ((this.golem.getCore() > -1) && (ItemGolemCore.hasInventory(this.golem.getCore()))) {
/* 374 */       for (int a = 0; a < Math.min(6, slots); a++) {
/* 375 */         if (this.golem.getUpgradeAmount(4) > 0)
/*     */         {
/*     */ 
/* 378 */           int var7 = par1 - (baseX + 96 + a / 2 * 28);
/* 379 */           int var8 = par2 - (baseY + 4 + a % 2 * 31);
/*     */           
/* 381 */           if ((var7 >= 0) && (var8 >= 0) && (var7 < 8) && (var8 < 12))
/*     */           {
/* 383 */             this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, a + ((ContainerGolem)this.field_147002_h).currentScroll * 6);
/*     */             
/* 385 */             return;
/*     */           }
/*     */           
/*     */ 
/* 389 */           var7 = par1 - (baseX + 96 + 16 + a / 2 * 28);
/* 390 */           var8 = par2 - (baseY + 4 + a % 2 * 31);
/*     */           
/* 392 */           if ((var7 >= 0) && (var8 >= 0) && (var7 < 8) && (var8 < 12))
/*     */           {
/* 394 */             this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, a + slots + ((ContainerGolem)this.field_147002_h).currentScroll * 6);
/* 395 */             return;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 400 */       if (slots > 6)
/*     */       {
/* 402 */         int var7 = par1 - (baseX + 111);
/* 403 */         int var8 = par2 - (baseY + 68);
/*     */         
/* 405 */         if ((var7 >= 0) && (var8 >= 0) && (var7 < 24) && (var8 < 8) && (((ContainerGolem)this.field_147002_h).currentScroll > 0))
/*     */         {
/*     */ 
/* 408 */           this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 66);
/* 409 */           ((ContainerGolem)this.field_147002_h).currentScroll -= 1;
/* 410 */           ((ContainerGolem)this.field_147002_h).refreshInventory();
/*     */           
/* 412 */           return;
/*     */         }
/*     */         
/*     */ 
/* 416 */         var7 = par1 - (baseX + 135);
/* 417 */         var8 = par2 - (baseY + 68);
/*     */         
/* 419 */         if ((var7 >= 0) && (var8 >= 0) && (var7 < 24) && (var8 < 8) && (((ContainerGolem)this.field_147002_h).currentScroll < ((ContainerGolem)this.field_147002_h).maxScroll))
/*     */         {
/*     */ 
/* 422 */           this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 67);
/* 423 */           ((ContainerGolem)this.field_147002_h).currentScroll += 1;
/* 424 */           ((ContainerGolem)this.field_147002_h).refreshInventory();
/* 425 */           return;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 432 */     if (this.golem.getCore() == 4) {
/* 433 */       for (int a = 0; a < 4; a++) {
/* 434 */         int var7 = par1 - (baseX + 104);
/* 435 */         int var8 = par2 - (baseY + 5 + 16 * a);
/*     */         
/* 437 */         if ((var7 >= 0) && (var8 >= 0) && (var7 < 8) && (var8 < 8))
/*     */         {
/* 439 */           this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 51 + a);
/* 440 */           return;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 445 */     if (this.golem.getCore() == 0) {
/* 446 */       int var7 = par1 - (baseX + 62);
/* 447 */       int var8 = par2 - (baseY + 54);
/*     */       
/* 449 */       if ((var7 >= 0) && (var8 >= 0) && (var7 < 8) && (var8 < 8))
/*     */       {
/* 451 */         this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 50);
/* 452 */         return;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 457 */     if (this.golem.getCore() == 8) {
/* 458 */       int var7 = par1 - (baseX + 42);
/* 459 */       int var8 = par2 - (baseY + 40);
/*     */       
/* 461 */       if ((var7 >= 0) && (var8 >= 0) && (var7 < 8) && (var8 < 8))
/*     */       {
/* 463 */         this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 50);
/* 464 */         return;
/*     */       }
/*     */       
/* 467 */       var7 = par1 - (baseX + 42);
/* 468 */       var8 = par2 - (baseY + 50);
/*     */       
/* 470 */       if ((var7 >= 0) && (var8 >= 0) && (var7 < 8) && (var8 < 8))
/*     */       {
/* 472 */         this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 51);
/* 473 */         return;
/*     */       }
/*     */       
/* 476 */       var7 = par1 - (baseX + 42);
/* 477 */       var8 = par2 - (baseY + 60);
/*     */       
/* 479 */       if ((var7 >= 0) && (var8 >= 0) && (var7 < 8) && (var8 < 8))
/*     */       {
/* 481 */         this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 52);
/* 482 */         return;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 487 */     if ((this.golem.getUpgradeAmount(5) > 0) && (ItemGolemCore.canSort(this.golem.getCore()))) {
/* 488 */       int shiftx = this.golem.getCore() == 10 ? 66 : 180;
/* 489 */       int shifty = this.golem.getCore() == 10 ? 12 : 0;
/* 490 */       for (int a = 0; a < 3; a++) {
/* 491 */         int var7 = par1 - (baseX + shiftx);
/* 492 */         int var8 = par2 - (baseY + 24 + a * 10 + shifty);
/*     */         
/* 494 */         if ((var7 >= 0) && (var8 >= 0) && (var7 < 64) && (var8 < 8))
/*     */         {
/* 496 */           this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 55 + a);
/* 497 */           return;
/*     */         }
/*     */       }
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
/*     */ 
/*     */ 
/*     */   protected void func_146285_a(ItemStack par1ItemStack, int par2, int par3)
/*     */   {
/* 515 */     if ((this.golem.getCore() != 5) || (!(this.theSlot instanceof SlotGhostFluid))) {
/* 516 */       super.func_146285_a(par1ItemStack, par2, par3);
/*     */     } else {
/* 518 */       List list = new ArrayList();
/*     */       
/* 520 */       FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(par1ItemStack);
/* 521 */       if (fluid != null) {
/* 522 */         list.add(fluid.getFluid().getLocalizedName(fluid));
/* 523 */         FontRenderer font = par1ItemStack.func_77973_b().getFontRenderer(par1ItemStack);
/* 524 */         drawHoveringText(list, par2, par3, font == null ? this.field_146289_q : font);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/gui/GuiGolem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */