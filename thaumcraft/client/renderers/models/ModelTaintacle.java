/*     */ package thaumcraft.client.renderers.models;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.common.entities.monster.EntityTaintacle;
/*     */ 
/*     */ 
/*     */ public class ModelTaintacle
/*     */   extends ModelBase
/*     */ {
/*  14 */   public ModelRenderer tentacle = new ModelRendererTaintacle(this);
/*     */   public ModelRenderer[] tents;
/*  16 */   public ModelRenderer orb = new ModelRendererTaintacle(this);
/*  17 */   private int length = 10;
/*     */   
/*     */   public ModelTaintacle(int length)
/*     */   {
/*  21 */     int var3 = 0;
/*  22 */     this.length = length;
/*  23 */     this.field_78089_u = 64;
/*  24 */     this.field_78090_t = 64;
/*  25 */     this.tentacle = new ModelRendererTaintacle(this, 0, 0);
/*     */     
/*  27 */     this.tentacle.func_78789_a(-4.0F, -4.0F, -4.0F, 8, 8, 8);
/*  28 */     this.tentacle.field_78800_c = 0.0F;
/*  29 */     this.tentacle.field_78798_e = 0.0F;
/*  30 */     this.tentacle.field_78797_d = 12.0F;
/*  31 */     this.tents = new ModelRendererTaintacle[length];
/*  32 */     for (int k = 0; k < length - 1; k++)
/*     */     {
/*  34 */       this.tents[k] = new ModelRendererTaintacle(this, 0, 16);
/*  35 */       this.tents[k].func_78789_a(-4.0F, -4.0F, -4.0F, 8, 8, 8);
/*  36 */       this.tents[k].field_78797_d = -8.0F;
/*     */       
/*  38 */       if (k == 0)
/*     */       {
/*  40 */         this.tentacle.func_78792_a(this.tents[k]);
/*     */       }
/*     */       else
/*     */       {
/*  44 */         this.tents[(k - 1)].func_78792_a(this.tents[k]);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  49 */     this.orb = new ModelRendererTaintacle(this, 0, 56);
/*  50 */     this.orb.func_78789_a(-2.0F, -2.0F, -2.0F, 4, 4, 4);
/*  51 */     this.orb.field_78797_d = -8.0F;
/*  52 */     this.tents[(length - 2)].func_78792_a(this.orb);
/*     */     
/*  54 */     this.tents[(length - 1)] = new ModelRendererTaintacle(this, 0, 32);
/*  55 */     this.tents[(length - 1)].func_78789_a(-6.0F, -6.0F, -6.0F, 12, 12, 12);
/*  56 */     this.tents[(length - 1)].field_78797_d = -8.0F;
/*  57 */     this.tents[(length - 2)].func_78792_a(this.tents[(length - 1)]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
/*     */   {
/*  67 */     boolean agi = false;
/*  68 */     float flail = 0.0F;
/*  69 */     int ht = 0;
/*  70 */     int at = 0;
/*  71 */     if ((entity instanceof EntityTaintacle)) {
/*  72 */       EntityTaintacle tentacle = (EntityTaintacle)entity;
/*  73 */       agi = tentacle.getAgitationState();
/*  74 */       flail = tentacle.flailIntensity;
/*  75 */       ht = tentacle.field_70737_aN;
/*  76 */       at = tentacle.field_70724_aR;
/*     */     }
/*  78 */     float mod = par6 * 0.2F;
/*  79 */     float fs = agi ? 3.0F : 1.0F + (agi ? mod : -mod);
/*  80 */     float fi = flail + ((ht > 0) || (at > 0) ? mod : -mod);
/*     */     
/*  82 */     this.tentacle.field_78795_f = 0.0F;
/*  83 */     for (int k = 0; k < this.length - 1; k++)
/*     */     {
/*  85 */       this.tents[k].field_78795_f = (0.15F * fi * MathHelper.func_76126_a(par3 * 0.1F * fs - k / 2.0F));
/*  86 */       this.tents[k].field_78808_h = (0.1F / fi * MathHelper.func_76126_a(par3 * 0.15F - k / 2.0F));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
/*     */   {
/*  95 */     func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
/*  96 */     GL11.glPushMatrix();
/*  97 */     GL11.glEnable(3042);
/*  98 */     GL11.glBlendFunc(770, 771);
/*  99 */     float height = 0.0F;
/* 100 */     float hc = par1Entity.field_70131_O * 10.0F;
/* 101 */     if (par1Entity.field_70173_aa < hc) {
/* 102 */       height = (hc - par1Entity.field_70173_aa) / hc * par1Entity.field_70131_O;
/*     */     }
/* 104 */     GL11.glTranslatef(0.0F, (par1Entity.field_70131_O == 3.0F ? 0.6F : 1.2F) + height, 0.0F);
/* 105 */     GL11.glScalef(par1Entity.field_70131_O / 3.0F, par1Entity.field_70131_O / 3.0F, par1Entity.field_70131_O / 3.0F);
/* 106 */     ((ModelRendererTaintacle)this.tentacle).render(par7, 0.88F);
/* 107 */     GL11.glDisable(3042);
/* 108 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/ModelTaintacle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */