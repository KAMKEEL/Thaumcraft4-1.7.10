/*     */ package thaumcraft.client.gui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.renderer.entity.RenderItem;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.crafting.CrucibleRecipe;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.container.ContainerThaumatorium;
/*     */ import thaumcraft.common.tiles.TileThaumatorium;
/*     */ 
/*     */ @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */ public class GuiThaumatorium extends GuiContainer
/*     */ {
/*     */   private TileThaumatorium inventory;
/*  24 */   private ContainerThaumatorium container = null;
/*  25 */   private int index = 0;
/*  26 */   private int lastSize = 0;
/*  27 */   private net.minecraft.entity.player.EntityPlayer player = null;
/*     */   
/*     */   public GuiThaumatorium(InventoryPlayer par1InventoryPlayer, TileThaumatorium par2TileEntityFurnace)
/*     */   {
/*  31 */     super(new ContainerThaumatorium(par1InventoryPlayer, par2TileEntityFurnace));
/*  32 */     this.inventory = par2TileEntityFurnace;
/*  33 */     this.container = ((ContainerThaumatorium)this.field_147002_h);
/*  34 */     this.container.updateRecipes();
/*  35 */     this.lastSize = this.container.recipes.size();
/*  36 */     this.player = par1InventoryPlayer.field_70458_d;
/*  37 */     refreshIndex();
/*     */   }
/*     */   
/*     */   void refreshIndex() {
/*  41 */     if ((this.inventory.recipeHash != null) && (this.container.recipes.size() > 0)) {
/*  42 */       for (int a = 0; a < this.container.recipes.size(); a++) {
/*  43 */         if (this.inventory.recipeHash.contains(Integer.valueOf(((CrucibleRecipe)this.container.recipes.get(a)).hash))) {
/*  44 */           this.index = a;
/*  45 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  50 */     this.startAspect = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_146979_b(int par1, int par2) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_146976_a(float par1, int mx, int my)
/*     */   {
/*  67 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  68 */     UtilsFX.bindTexture("textures/gui/gui_thaumatorium.png");
/*  69 */     int k = (this.field_146294_l - this.field_146999_f) / 2;
/*  70 */     int l = (this.field_146295_m - this.field_147000_g) / 2;
/*     */     
/*  72 */     GL11.glEnable(3042);
/*  73 */     func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
/*     */     
/*     */ 
/*  76 */     if (this.index >= this.container.recipes.size()) {
/*  77 */       this.index = (this.container.recipes.size() - 1);
/*     */     }
/*     */     
/*  80 */     if (this.container.recipes.size() > 0) {
/*  81 */       if (this.lastSize != this.container.recipes.size()) {
/*  82 */         this.lastSize = this.container.recipes.size();
/*  83 */         refreshIndex();
/*     */       }
/*     */       
/*  86 */       if (this.index < 0) { this.index = 0;
/*     */       }
/*  88 */       if (this.container.recipes.size() > 1)
/*     */       {
/*  90 */         if (this.index > 0) {
/*  91 */           func_73729_b(k + 128, l + 16, 192, 16, 16, 8);
/*     */         } else {
/*  93 */           func_73729_b(k + 128, l + 16, 176, 16, 16, 8);
/*     */         }
/*     */         
/*  96 */         if (this.index < this.container.recipes.size() - 1) {
/*  97 */           func_73729_b(k + 128, l + 24, 192, 24, 16, 8);
/*     */         } else {
/*  99 */           func_73729_b(k + 128, l + 24, 176, 24, 16, 8);
/*     */         }
/*     */       }
/*     */       
/* 103 */       if (((CrucibleRecipe)this.container.recipes.get(this.index)).aspects.size() > 6)
/*     */       {
/* 105 */         if (this.startAspect > 0) {
/* 106 */           func_73729_b(k + 32, l + 40, 192, 32, 8, 16);
/*     */         } else {
/* 108 */           func_73729_b(k + 32, l + 40, 176, 32, 8, 16);
/*     */         }
/*     */         
/* 111 */         if (this.startAspect < ((CrucibleRecipe)this.container.recipes.get(this.index)).aspects.size() - 1) {
/* 112 */           func_73729_b(k + 136, l + 40, 200, 32, 8, 16);
/*     */         } else {
/* 114 */           func_73729_b(k + 136, l + 40, 184, 32, 8, 16);
/*     */         }
/*     */       } else {
/* 117 */         this.startAspect = 0;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 122 */       if ((this.inventory.recipeHash != null) && (this.inventory.recipeHash.size() > 0))
/*     */       {
/* 124 */         int x = mx - (k + 112);
/* 125 */         int y = my - (l + 16);
/* 126 */         if (((x >= 0) && (y >= 0) && (x < 16) && (y < 16)) || (this.inventory.recipeHash.contains(Integer.valueOf(((CrucibleRecipe)this.container.recipes.get(this.index)).hash))))
/*     */         {
/* 128 */           GL11.glPushMatrix();
/* 129 */           GL11.glEnable(3042);
/* 130 */           func_73729_b(k + 104, l + 8, 176, 96, 48, 48);
/* 131 */           GL11.glDisable(3042);
/* 132 */           GL11.glPopMatrix();
/*     */         }
/*     */         
/* 135 */         GL11.glPushMatrix();
/* 136 */         GL11.glEnable(3042);
/* 137 */         float alpha = 0.6F + MathHelper.func_76126_a(this.field_146297_k.field_71439_g.field_70173_aa / 5.0F) * 0.4F + 0.4F;
/* 138 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);
/* 139 */         func_73729_b(k + 88, l + 16, 176, 56, 24, 24);
/* 140 */         GL11.glDisable(3042);
/* 141 */         GL11.glPopMatrix();
/*     */       }
/*     */       
/* 144 */       drawAspects(k, l);
/*     */       
/* 146 */       drawOutput(k, l, mx, my);
/*     */       
/* 148 */       if (this.inventory.maxRecipes > 1) {
/* 149 */         GL11.glPushMatrix();
/* 150 */         GL11.glTranslatef(k + 136, l + 33, 0.0F);
/* 151 */         GL11.glScalef(0.5F, 0.5F, 0.0F);
/* 152 */         String text = this.inventory.recipeHash.size() + "/" + this.inventory.maxRecipes;
/*     */         
/* 154 */         int ll = this.field_146289_q.func_78256_a(text) / 2;
/* 155 */         this.field_146289_q.func_78276_b(text, -ll, 0, 16777215);
/* 156 */         GL11.glScalef(1.0F, 1.0F, 1.0F);
/* 157 */         GL11.glPopMatrix();
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 162 */     GL11.glDisable(3042);
/*     */   }
/*     */   
/* 165 */   int startAspect = 0;
/*     */   
/*     */   private void drawAspects(int k, int l) {
/* 168 */     int count = 0;
/* 169 */     int pos = 0;
/* 170 */     if (this.inventory.recipeHash.contains(Integer.valueOf(((CrucibleRecipe)this.container.recipes.get(this.index)).hash)))
/* 171 */       for (Aspect aspect : ((CrucibleRecipe)this.container.recipes.get(this.index)).aspects.getAspectsSorted()) {
/* 172 */         if (count >= this.startAspect)
/*     */         {
/* 174 */           GL11.glPushMatrix();
/* 175 */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 176 */           func_73729_b(k + 41 + 16 * pos, l + 57, 176, 8, 14, 6);
/*     */           
/* 178 */           int i1 = (int)(this.inventory.essentia.getAmount(aspect) / ((CrucibleRecipe)this.container.recipes.get(this.index)).aspects.getAmount(aspect) * 12.0F);
/*     */           
/*     */ 
/* 181 */           Color c = new Color(aspect.getColor());
/* 182 */           GL11.glColor4f(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, 1.0F);
/* 183 */           func_73729_b(k + 42 + 16 * pos, l + 58, 176, 0, i1, 4);
/* 184 */           GL11.glPopMatrix();
/*     */           
/* 186 */           pos++;
/*     */         }
/* 188 */         count++;
/* 189 */         if (count >= 6 + this.startAspect)
/*     */           break;
/*     */       }
/* 192 */     count = 0;
/* 193 */     pos = 0;
/* 194 */     for (Aspect aspect : ((CrucibleRecipe)this.container.recipes.get(this.index)).aspects.getAspectsSorted()) {
/* 195 */       if (count >= this.startAspect) {
/* 196 */         UtilsFX.drawTag(k + 40 + 16 * pos, l + 40, aspect, ((CrucibleRecipe)this.container.recipes.get(this.index)).aspects.getAmount(aspect), 0, this.field_73735_i);
/* 197 */         pos++;
/*     */       }
/* 199 */       count++;
/* 200 */       if (count >= 6 + this.startAspect)
/*     */         break;
/*     */     }
/*     */   }
/*     */   
/* 205 */   private void drawOutput(int x, int y, int mx, int my) { GL11.glPushMatrix();
/* 206 */     boolean dull = false;
/* 207 */     if ((this.inventory.recipeHash.size() < this.inventory.maxRecipes) || (this.inventory.recipeHash.contains(Integer.valueOf(((CrucibleRecipe)this.container.recipes.get(this.index)).hash))))
/*     */     {
/* 209 */       dull = true;
/* 210 */       float alpha = 0.3F + MathHelper.func_76126_a(this.field_146297_k.field_71439_g.field_70173_aa / 4.0F) * 0.3F + 0.3F;
/* 211 */       GL11.glColor4f(0.5F, 0.5F, 0.5F, alpha);
/* 212 */       field_146296_j.field_77024_a = false;
/*     */     }
/*     */     
/* 215 */     GL11.glEnable(2896);
/* 216 */     GL11.glEnable(2884);
/* 217 */     GL11.glEnable(3042);
/*     */     
/* 219 */     field_146296_j.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, ((CrucibleRecipe)this.container.recipes.get(this.index)).getRecipeOutput(), x + 112, y + 16);
/*     */     
/*     */ 
/*     */ 
/* 223 */     field_146296_j.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, ((CrucibleRecipe)this.container.recipes.get(this.index)).getRecipeOutput(), x + 112, y + 16);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 228 */     int xx = mx - (x + 112);
/* 229 */     int yy = my - (y + 16);
/* 230 */     if ((xx >= 0) && (yy >= 0) && (xx < 16) && (yy < 16)) {
/* 231 */       func_146285_a(((CrucibleRecipe)this.container.recipes.get(this.index)).getRecipeOutput(), mx, my);
/*     */     }
/*     */     
/*     */ 
/* 235 */     if (dull) { field_146296_j.field_77024_a = true;
/*     */     }
/* 237 */     GL11.glDisable(3042);
/* 238 */     GL11.glDisable(2896);
/* 239 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_73864_a(int mx, int my, int par3)
/*     */   {
/* 245 */     super.func_73864_a(mx, my, par3);
/*     */     
/* 247 */     int gx = (this.field_146294_l - this.field_146999_f) / 2;
/* 248 */     int gy = (this.field_146295_m - this.field_147000_g) / 2;
/*     */     
/*     */ 
/* 251 */     int x = mx - (gx + 112);
/* 252 */     int y = my - (gy + 16);
/*     */     
/* 254 */     if ((this.container.recipes.size() > 0) && (this.index >= 0) && (this.index < this.container.recipes.size())) {
/* 255 */       if ((x >= 0) && (y >= 0) && (x < 16) && (y < 16) && (
/* 256 */         (this.inventory.recipeHash.size() < this.inventory.maxRecipes) || (this.inventory.recipeHash.contains(Integer.valueOf(((CrucibleRecipe)this.container.recipes.get(this.index)).hash)))))
/*     */       {
/* 258 */         this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, this.index);
/* 259 */         playButtonSelect();
/*     */       }
/*     */       
/*     */ 
/* 263 */       if (this.container.recipes.size() > 1)
/*     */       {
/* 265 */         if (this.index > 0) {
/* 266 */           x = mx - (gx + 128);
/* 267 */           y = my - (gy + 16);
/* 268 */           if ((x >= 0) && (y >= 0) && (x < 16) && (y < 8)) {
/* 269 */             this.index -= 1;
/* 270 */             playButtonClick();
/*     */           }
/*     */         }
/*     */         
/* 274 */         if (this.index < this.container.recipes.size() - 1) {
/* 275 */           x = mx - (gx + 128);
/* 276 */           y = my - (gy + 24);
/* 277 */           if ((x >= 0) && (y >= 0) && (x < 16) && (y < 8)) {
/* 278 */             this.index += 1;
/* 279 */             playButtonClick();
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 284 */       if (((CrucibleRecipe)this.container.recipes.get(this.index)).aspects.size() > 6)
/*     */       {
/* 286 */         if (this.startAspect > 0) {
/* 287 */           x = mx - (gx + 32);
/* 288 */           y = my - (gy + 40);
/* 289 */           if ((x >= 0) && (y >= 0) && (x < 8) && (y < 16)) {
/* 290 */             this.startAspect -= 1;
/* 291 */             playButtonClick();
/*     */           }
/*     */         }
/*     */         
/* 295 */         if (this.startAspect < ((CrucibleRecipe)this.container.recipes.get(this.index)).aspects.size() - 1) {
/* 296 */           x = mx - (gx + 136);
/* 297 */           y = my - (gy + 40);
/* 298 */           if ((x >= 0) && (y >= 0) && (x < 8) && (y < 16)) {
/* 299 */             this.startAspect += 1;
/* 300 */             playButtonClick();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void playButtonSelect()
/*     */   {
/* 310 */     this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:hhon", 0.3F, 1.0F, false);
/*     */   }
/*     */   
/*     */ 
/*     */   private void playButtonClick()
/*     */   {
/* 316 */     this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:cameraclack", 0.4F, 1.0F, false);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/gui/GuiThaumatorium.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */