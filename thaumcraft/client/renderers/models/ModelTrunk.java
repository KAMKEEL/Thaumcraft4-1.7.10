/*     */ package thaumcraft.client.renderers.models;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.IIcon;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.entities.golems.EntityTravelingTrunk;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelTrunk
/*     */   extends ModelBase
/*     */ {
/*     */   public ModelRenderer chestLid;
/*     */   public ModelRenderer chestBelow;
/*     */   public ModelRenderer chestKnob;
/*     */   
/*     */   public ModelTrunk()
/*     */   {
/*  33 */     this.chestLid = new ModelRenderer(this, 0, 0).func_78787_b(64, 64);
/*  34 */     this.chestLid.func_78790_a(0.0F, -5.0F, -14.0F, 14, 5, 14, 0.0F);
/*  35 */     this.chestLid.field_78800_c = 1.0F;
/*  36 */     this.chestLid.field_78797_d = 7.0F;
/*  37 */     this.chestLid.field_78798_e = 15.0F;
/*  38 */     this.chestKnob = new ModelRenderer(this, 0, 0).func_78787_b(64, 64);
/*  39 */     this.chestKnob.func_78790_a(-1.0F, -2.0F, -15.0F, 2, 4, 1, 0.0F);
/*  40 */     this.chestKnob.field_78800_c = 8.0F;
/*  41 */     this.chestKnob.field_78797_d = 7.0F;
/*  42 */     this.chestKnob.field_78798_e = 15.0F;
/*  43 */     this.chestBelow = new ModelRenderer(this, 0, 19).func_78787_b(64, 64);
/*  44 */     this.chestBelow.func_78790_a(0.0F, 0.0F, 0.0F, 14, 10, 14, 0.0F);
/*  45 */     this.chestBelow.field_78800_c = 1.0F;
/*  46 */     this.chestBelow.field_78797_d = 6.0F;
/*  47 */     this.chestBelow.field_78798_e = 1.0F;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/*  53 */     this.chestKnob.field_78795_f = this.chestLid.field_78795_f;
/*  54 */     this.chestLid.func_78785_a(0.0625F);
/*  55 */     this.chestBelow.func_78785_a(0.0625F);
/*  56 */     this.chestKnob.func_78785_a(0.0625F);
/*     */     
/*  58 */     GL11.glPushMatrix();
/*     */     
/*  60 */     GL11.glTranslatef(this.chestKnob.field_82906_o, this.chestKnob.field_82908_p, this.chestKnob.field_82907_q);
/*     */     
/*     */ 
/*  63 */     GL11.glEnable(3042);
/*  64 */     GL11.glBlendFunc(770, 771);
/*     */     
/*  66 */     GL11.glPushMatrix();
/*  67 */     GL11.glTranslatef(this.chestKnob.field_78800_c * 0.0625F, this.chestKnob.field_78797_d * 0.0625F, this.chestKnob.field_78798_e * 0.0625F);
/*     */     
/*  69 */     if (this.chestKnob.field_78808_h != 0.0F)
/*     */     {
/*  71 */       GL11.glRotatef(this.chestKnob.field_78808_h * 57.295776F, 0.0F, 0.0F, 1.0F);
/*     */     }
/*     */     
/*  74 */     if (this.chestKnob.field_78796_g != 0.0F)
/*     */     {
/*  76 */       GL11.glRotatef(this.chestKnob.field_78796_g * 57.295776F, 0.0F, 1.0F, 0.0F);
/*     */     }
/*     */     
/*  79 */     if (this.chestKnob.field_78795_f != 0.0F)
/*     */     {
/*  81 */       GL11.glRotatef(this.chestKnob.field_78795_f * 57.295776F, 1.0F, 0.0F, 0.0F);
/*     */     }
/*     */     
/*  84 */     GL11.glTranslatef(-0.075F, -0.115F, -0.94301F);
/*     */     
/*  86 */     GL11.glScaled(0.15D, 0.15D, 0.15D);
/*  87 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  88 */     IIcon icon = ConfigItems.itemGolemUpgrade.func_77617_a(((EntityTravelingTrunk)entity).getUpgrade());
/*  89 */     float ff1 = icon.func_94212_f();
/*  90 */     float ff2 = icon.func_94206_g();
/*  91 */     float ff3 = icon.func_94209_e();
/*  92 */     float ff4 = icon.func_94210_h();
/*  93 */     RenderManager.field_78727_a.field_78724_e.func_110577_a(TextureMap.field_110576_c);
/*  94 */     tessellator.func_78382_b();
/*  95 */     tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
/*  96 */     tessellator.func_78374_a(0.0D, 0.0D, 0.0D, ff1, ff4);
/*  97 */     tessellator.func_78374_a(1.0D, 0.0D, 0.0D, ff3, ff4);
/*  98 */     tessellator.func_78374_a(1.0D, 1.0D, 0.0D, ff3, ff2);
/*  99 */     tessellator.func_78374_a(0.0D, 1.0D, 0.0D, ff1, ff2);
/* 100 */     tessellator.func_78381_a();
/*     */     
/* 102 */     GL11.glPopMatrix();
/*     */     
/* 104 */     GL11.glDisable(3042);
/*     */     
/* 106 */     GL11.glTranslatef(-this.chestKnob.field_82906_o, -this.chestKnob.field_82908_p, -this.chestKnob.field_82907_q);
/*     */     
/* 108 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/ModelTrunk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */