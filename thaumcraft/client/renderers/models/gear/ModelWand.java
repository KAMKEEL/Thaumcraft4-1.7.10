/*     */ package thaumcraft.client.renderers.models.gear;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.ItemRenderer;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.api.wands.WandCap;
/*     */ import thaumcraft.api.wands.WandRod;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.client.renderers.block.BlockRenderer;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ 
/*     */ public class ModelWand
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer Rod;
/*     */   ModelRenderer Focus;
/*     */   ModelRenderer Cap;
/*     */   ModelRenderer CapBottom;
/*     */   
/*     */   public ModelWand()
/*     */   {
/*  38 */     this.field_78090_t = 32;
/*  39 */     this.field_78089_u = 32;
/*     */     
/*  41 */     this.Cap = new ModelRenderer(this, 0, 0);
/*  42 */     this.Cap.func_78789_a(-1.0F, -1.0F, -1.0F, 2, 2, 2);
/*  43 */     this.Cap.func_78793_a(0.0F, 0.0F, 0.0F);
/*  44 */     this.Cap.func_78787_b(64, 32);
/*  45 */     this.Cap.field_78809_i = true;
/*  46 */     setRotation(this.Cap, 0.0F, 0.0F, 0.0F);
/*  47 */     this.CapBottom = new ModelRenderer(this, 0, 0);
/*  48 */     this.CapBottom.func_78789_a(-1.0F, -1.0F, -1.0F, 2, 2, 2);
/*  49 */     this.CapBottom.func_78793_a(0.0F, 20.0F, 0.0F);
/*  50 */     this.CapBottom.func_78787_b(64, 32);
/*  51 */     this.CapBottom.field_78809_i = true;
/*  52 */     setRotation(this.CapBottom, 0.0F, 0.0F, 0.0F);
/*  53 */     this.Rod = new ModelRenderer(this, 0, 8);
/*  54 */     this.Rod.func_78789_a(-1.0F, -1.0F, -1.0F, 2, 18, 2);
/*  55 */     this.Rod.func_78793_a(0.0F, 2.0F, 0.0F);
/*  56 */     this.Rod.func_78787_b(64, 32);
/*  57 */     this.Rod.field_78809_i = true;
/*  58 */     setRotation(this.Rod, 0.0F, 0.0F, 0.0F);
/*  59 */     this.Focus = new ModelRenderer(this, 0, 0);
/*  60 */     this.Focus.func_78789_a(-3.0F, -6.0F, -3.0F, 6, 6, 6);
/*  61 */     this.Focus.func_78793_a(0.0F, 0.0F, 0.0F);
/*  62 */     this.Focus.func_78787_b(64, 32);
/*  63 */     this.Focus.field_78809_i = true;
/*  64 */     setRotation(this.Focus, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public void render(ItemStack wandStack)
/*     */   {
/*  69 */     if (RenderManager.field_78727_a.field_78724_e == null) return;
/*  70 */     ItemWandCasting wand = (ItemWandCasting)wandStack.func_77973_b();
/*  71 */     ItemStack focusStack = wand.getFocusItem(wandStack);
/*  72 */     EntityPlayer player = Minecraft.func_71410_x().field_71439_g;
/*  73 */     boolean staff = wand.isStaff(wandStack);
/*  74 */     boolean runes = wand.hasRunes(wandStack);
/*  75 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(wand.getRod(wandStack).getTexture());
/*     */     
/*  77 */     GL11.glPushMatrix();
/*     */     
/*  79 */     if (staff) { GL11.glTranslated(0.0D, 0.2D, 0.0D);
/*     */     }
/*  81 */     GL11.glPushMatrix();
/*  82 */     if (wand.getRod(wandStack).isGlowing()) {
/*  83 */       int j = (int)(200.0F + MathHelper.func_76126_a(player.field_70173_aa) * 5.0F + 5.0F);
/*  84 */       int k = j % 65536;
/*  85 */       int l = j / 65536;
/*  86 */       OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/*     */     }
/*  88 */     if (staff) {
/*  89 */       GL11.glTranslated(0.0D, -0.1D, 0.0D);
/*  90 */       GL11.glScaled(1.2D, 2.0D, 1.2D);
/*     */     }
/*  92 */     this.Rod.func_78785_a(0.0625F);
/*  93 */     if (wand.getRod(wandStack).isGlowing()) {
/*  94 */       int i = player.func_70070_b(0.0F);
/*  95 */       int j = i % 65536;
/*  96 */       int k = i / 65536;
/*  97 */       OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, j / 1.0F, k / 1.0F);
/*     */     }
/*  99 */     GL11.glPopMatrix();
/*     */     
/* 101 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(wand.getCap(wandStack).getTexture());
/*     */     
/* 103 */     GL11.glPushMatrix();
/* 104 */     if (staff) {
/* 105 */       GL11.glScaled(1.3D, 1.1D, 1.3D);
/*     */     } else {
/* 107 */       GL11.glScaled(1.2D, 1.0D, 1.2D);
/*     */     }
/* 109 */     if (wand.isSceptre(wandStack)) {
/* 110 */       GL11.glPushMatrix();
/* 111 */       GL11.glScaled(1.3D, 1.3D, 1.3D);
/* 112 */       this.Cap.func_78785_a(0.0625F);
/* 113 */       GL11.glPopMatrix();
/* 114 */       GL11.glPushMatrix();
/* 115 */       GL11.glTranslated(0.0D, 0.3D, 0.0D);
/* 116 */       GL11.glScaled(1.0D, 0.66D, 1.0D);
/* 117 */       this.Cap.func_78785_a(0.0625F);
/* 118 */       GL11.glPopMatrix();
/*     */     } else {
/* 120 */       this.Cap.func_78785_a(0.0625F);
/*     */     }
/*     */     
/* 123 */     if (staff) {
/* 124 */       GL11.glTranslated(0.0D, 0.225D, 0.0D);
/* 125 */       GL11.glPushMatrix();
/* 126 */       GL11.glScaled(1.0D, 0.66D, 1.0D);
/* 127 */       this.Cap.func_78785_a(0.0625F);
/* 128 */       GL11.glPopMatrix();
/* 129 */       GL11.glTranslated(0.0D, 0.65D, 0.0D);
/*     */     }
/* 131 */     this.CapBottom.func_78785_a(0.0625F);
/* 132 */     GL11.glPopMatrix();
/*     */     
/*     */ 
/*     */ 
/* 136 */     if (wand.getFocus(wandStack) != null) {
/* 137 */       if (wand.getFocus(wandStack).getOrnament(focusStack) != null) {
/* 138 */         GL11.glPushMatrix();
/*     */         
/* 140 */         GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/*     */         
/* 142 */         Tessellator tessellator = Tessellator.field_78398_a;
/* 143 */         IIcon icon = wand.getFocus(wandStack).getOrnament(focusStack);
/* 144 */         float f1 = icon.func_94212_f();
/* 145 */         float f2 = icon.func_94206_g();
/* 146 */         float f3 = icon.func_94209_e();
/* 147 */         float f4 = icon.func_94210_h();
/* 148 */         RenderManager.field_78727_a.field_78724_e.func_110577_a(TextureMap.field_110576_c);
/* 149 */         GL11.glPushMatrix();
/* 150 */         GL11.glTranslatef(-0.25F, -0.1F, 0.0275F);
/* 151 */         GL11.glScaled(0.5D, 0.5D, 0.5D);
/* 152 */         ItemRenderer.func_78439_a(tessellator, f1, f2, f3, f4, icon.func_94211_a(), icon.func_94216_b(), 0.1F);
/* 153 */         GL11.glPopMatrix();
/* 154 */         GL11.glPushMatrix();
/* 155 */         GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 156 */         GL11.glTranslatef(-0.25F, -0.1F, 0.0275F);
/* 157 */         GL11.glScaled(0.5D, 0.5D, 0.5D);
/* 158 */         ItemRenderer.func_78439_a(tessellator, f1, f2, f3, f4, icon.func_94211_a(), icon.func_94216_b(), 0.1F);
/* 159 */         GL11.glPopMatrix();
/* 160 */         GL11.glPopMatrix();
/*     */       }
/*     */       
/* 163 */       float alpha = 0.95F;
/*     */       
/* 165 */       if (wand.getFocus(wandStack).getFocusDepthLayerIcon(focusStack) != null) {
/* 166 */         GL11.glPushMatrix();
/* 167 */         if (staff) {
/* 168 */           GL11.glTranslatef(0.0F, -0.15F, 0.0F);
/* 169 */           GL11.glScaled(0.165D, 0.1765D, 0.165D);
/*     */         }
/*     */         else {
/* 172 */           GL11.glTranslatef(0.0F, -0.09F, 0.0F);
/* 173 */           GL11.glScaled(0.16D, 0.16D, 0.16D);
/*     */         }
/* 175 */         Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110576_c);
/* 176 */         this.renderBlocks.func_147775_a(Blocks.field_150348_b);
/* 177 */         BlockRenderer.drawFaces(this.renderBlocks, null, wand.getFocus(wandStack).getFocusDepthLayerIcon(focusStack), false);
/* 178 */         GL11.glPopMatrix();
/* 179 */         alpha = 0.6F;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 184 */       if (Thaumcraft.isHalloween) {
/* 185 */         UtilsFX.bindTexture("textures/models/spec_h.png");
/*     */       } else {
/* 187 */         UtilsFX.bindTexture("textures/models/wand.png");
/*     */       }
/* 189 */       GL11.glPushMatrix();
/* 190 */       if (staff) {
/* 191 */         GL11.glTranslatef(0.0F, -0.0475F, 0.0F);
/* 192 */         GL11.glScaled(0.525D, 0.5525D, 0.525D);
/*     */       }
/*     */       else {
/* 195 */         GL11.glScaled(0.5D, 0.5D, 0.5D); }
/* 196 */       Color c = new Color(wand.getFocus(wandStack).getFocusColor(focusStack));
/* 197 */       GL11.glColor4f(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, alpha);
/* 198 */       int j = (int)(195.0F + MathHelper.func_76126_a(player.field_70173_aa / 3.0F) * 10.0F + 10.0F);
/* 199 */       int k = j % 65536;
/* 200 */       int l = j / 65536;
/* 201 */       OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/*     */       
/* 203 */       this.Focus.func_78785_a(0.0625F);
/* 204 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/*     */ 
/* 208 */     if (wand.isSceptre(wandStack)) {
/* 209 */       GL11.glPushMatrix();
/* 210 */       int j = 200;
/* 211 */       int k = j % 65536;
/* 212 */       int l = j / 65536;
/* 213 */       OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/*     */       
/* 215 */       GL11.glBlendFunc(770, 1);
/* 216 */       for (int rot = 0; rot < 10; rot++) {
/* 217 */         GL11.glPushMatrix();
/* 218 */         GL11.glRotated(36 * rot + player.field_70173_aa, 0.0D, 1.0D, 0.0D);
/* 219 */         drawRune(0.16D, -0.009999999776482582D, -0.125D, rot, player);
/* 220 */         GL11.glPopMatrix();
/*     */       }
/* 222 */       GL11.glBlendFunc(770, 771);
/* 223 */       GL11.glPopMatrix();
/*     */     }
/* 225 */     if (runes) {
/* 226 */       GL11.glPushMatrix();
/* 227 */       int j = 200;
/* 228 */       int k = j % 65536;
/* 229 */       int l = j / 65536;
/* 230 */       OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/*     */       
/* 232 */       GL11.glBlendFunc(770, 1);
/* 233 */       for (int rot = 0; rot < 4; rot++) {
/* 234 */         GL11.glRotated(90.0D, 0.0D, 1.0D, 0.0D);
/* 235 */         for (int a = 0; a < 14; a++) {
/* 236 */           int rune = (a + rot * 3) % 16;
/* 237 */           drawRune(0.36D + a * 0.14D, -0.009999999776482582D, -0.08D, rune, player);
/*     */         }
/*     */       }
/* 240 */       GL11.glBlendFunc(770, 771);
/* 241 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 244 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   private void drawRune(double x, double y, double z, int rune, EntityPlayer player)
/*     */   {
/* 249 */     GL11.glPushMatrix();
/* 250 */     UtilsFX.bindTexture("textures/misc/script.png");
/* 251 */     float r = MathHelper.func_76126_a((player.field_70173_aa + rune * 5) / 5.0F) * 0.1F + 0.88F;
/* 252 */     float g = MathHelper.func_76126_a((player.field_70173_aa + rune * 5) / 7.0F) * 0.1F + 0.63F;
/* 253 */     float alpha = MathHelper.func_76126_a((player.field_70173_aa + rune * 5) / 10.0F) * 0.2F;
/* 254 */     GL11.glColor4f(r, g, 0.2F, alpha + 0.6F);
/* 255 */     GL11.glRotated(90.0D, 0.0D, 0.0D, 1.0D);
/* 256 */     GL11.glTranslated(x, y, z);
/*     */     
/* 258 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 259 */     float var8 = 0.0625F * rune;
/* 260 */     float var9 = var8 + 0.0625F;
/* 261 */     float var10 = 0.0F;
/* 262 */     float var11 = 1.0F;
/* 263 */     tessellator.func_78382_b();
/* 264 */     tessellator.func_78369_a(r, g, 0.2F, alpha + 0.6F);
/* 265 */     tessellator.func_78374_a(-0.06D - alpha / 40.0F, 0.06D + alpha / 40.0F, 0.0D, var9, var11);
/* 266 */     tessellator.func_78374_a(0.06D + alpha / 40.0F, 0.06D + alpha / 40.0F, 0.0D, var9, var10);
/* 267 */     tessellator.func_78374_a(0.06D + alpha / 40.0F, -0.06D - alpha / 40.0F, 0.0D, var8, var10);
/* 268 */     tessellator.func_78374_a(-0.06D - alpha / 40.0F, -0.06D - alpha / 40.0F, 0.0D, var8, var11);
/* 269 */     tessellator.func_78381_a();
/* 270 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/* 274 */   private final RenderBlocks renderBlocks = new RenderBlocks();
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*     */   {
/* 278 */     model.field_78795_f = x;
/* 279 */     model.field_78796_g = y;
/* 280 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 285 */     super.func_78087_a(f, f1, f2, f3, f4, f5, null);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/gear/ModelWand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */