/*     */ package thaumcraft.client.renderers.entity;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.RenderLiving;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.renderers.models.entities.ModelWatcher;
/*     */ import thaumcraft.common.entities.monster.EntityWatcher;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderWatcher
/*     */   extends RenderLiving
/*     */ {
/*  22 */   private static final ResourceLocation field_177114_e = new ResourceLocation("thaumcraft", "textures/models/watcher.png");
/*  23 */   private static final ResourceLocation field_177117_k = new ResourceLocation("thaumcraft", "textures/models/watcher_beam.png");
/*     */   int field_177115_a;
/*     */   
/*     */   public RenderWatcher()
/*     */   {
/*  28 */     super(new ModelWatcher(), 0.5F);
/*  29 */     this.field_177115_a = ((ModelWatcher)this.field_77045_g).func_178706_a();
/*     */   }
/*     */   
/*     */   private Vec3 func_177110_a(EntityLivingBase p_177110_1_, double p_177110_2_, float p_177110_4_)
/*     */   {
/*  34 */     double d1 = p_177110_1_.field_70142_S + (p_177110_1_.field_70165_t - p_177110_1_.field_70142_S) * p_177110_4_;
/*  35 */     double d2 = p_177110_2_ + p_177110_1_.field_70137_T + (p_177110_1_.field_70163_u - p_177110_1_.field_70137_T) * p_177110_4_;
/*  36 */     double d3 = p_177110_1_.field_70136_U + (p_177110_1_.field_70161_v - p_177110_1_.field_70136_U) * p_177110_4_;
/*  37 */     return Vec3.func_72443_a(d1, d2, d3);
/*     */   }
/*     */   
/*     */   public void func_177109_a(EntityWatcher p_177109_1_, double p_177109_2_, double p_177109_4_, double p_177109_6_, float p_177109_8_, float p_177109_9_)
/*     */   {
/*  42 */     if (this.field_177115_a != ((ModelWatcher)this.field_77045_g).func_178706_a())
/*     */     {
/*  44 */       this.field_77045_g = new ModelWatcher();
/*  45 */       this.field_177115_a = ((ModelWatcher)this.field_77045_g).func_178706_a();
/*     */     }
/*     */     
/*  48 */     super.func_76986_a(p_177109_1_, p_177109_2_, p_177109_4_, p_177109_6_, p_177109_8_, p_177109_9_);
/*  49 */     EntityLivingBase entitylivingbase = p_177109_1_.getTargetedEntity();
/*     */     
/*  51 */     if (entitylivingbase != null)
/*     */     {
/*  53 */       float f2 = p_177109_1_.func_175477_p(p_177109_9_);
/*  54 */       Tessellator tessellator = Tessellator.field_78398_a;
/*  55 */       func_110776_a(field_177117_k);
/*  56 */       GL11.glTexParameterf(3553, 10242, 10497.0F);
/*  57 */       GL11.glTexParameterf(3553, 10243, 10497.0F);
/*  58 */       GL11.glDisable(2896);
/*  59 */       GL11.glDisable(2884);
/*  60 */       GL11.glDisable(3042);
/*  61 */       GL11.glDepthMask(true);
/*  62 */       float f3 = 240.0F;
/*  63 */       OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, f3, f3);
/*  64 */       GL11.glEnable(3042);
/*  65 */       GL11.glBlendFunc(770, 1);
/*  66 */       float f4 = (float)p_177109_1_.field_70170_p.func_82737_E() + p_177109_9_;
/*  67 */       float f5 = f4 * 0.5F % 1.0F;
/*  68 */       float f6 = p_177109_1_.func_70047_e();
/*  69 */       GL11.glPushMatrix();
/*  70 */       GL11.glTranslatef((float)p_177109_2_, (float)p_177109_4_ + f6, (float)p_177109_6_);
/*  71 */       Vec3 vec3 = func_177110_a(entitylivingbase, entitylivingbase.field_70131_O * 0.5D, p_177109_9_);
/*  72 */       Vec3 vec31 = func_177110_a(p_177109_1_, f6, p_177109_9_);
/*  73 */       Vec3 vec32 = vec3.func_72444_a(vec31);
/*  74 */       double d3 = vec32.func_72433_c() + 1.0D;
/*  75 */       vec32 = vec32.func_72432_b();
/*  76 */       float f7 = (float)Math.acos(vec32.field_72448_b);
/*  77 */       float f8 = (float)Math.atan2(vec32.field_72449_c, vec32.field_72450_a);
/*  78 */       GL11.glRotatef((1.5707964F + -f8) * 57.295776F, 0.0F, 1.0F, 0.0F);
/*  79 */       GL11.glRotatef(f7 * 57.295776F, 1.0F, 0.0F, 0.0F);
/*  80 */       byte b0 = 1;
/*  81 */       double d4 = f4 * 0.05D * (1.0D - (b0 & 0x1) * 2.5D);
/*  82 */       tessellator.func_78382_b();
/*  83 */       float f9 = f2 * f2;
/*  84 */       tessellator.func_78370_a(64 + (int)(f9 * 240.0F), 32 + (int)(f9 * 192.0F), 128 - (int)(f9 * 64.0F), 255);
/*  85 */       double d5 = b0 * 0.2D;
/*  86 */       double d6 = d5 * 1.41D;
/*  87 */       double d7 = 0.0D + Math.cos(d4 + 2.356194490192345D) * d6;
/*  88 */       double d8 = 0.0D + Math.sin(d4 + 2.356194490192345D) * d6;
/*  89 */       double d9 = 0.0D + Math.cos(d4 + 0.7853981633974483D) * d6;
/*  90 */       double d10 = 0.0D + Math.sin(d4 + 0.7853981633974483D) * d6;
/*  91 */       double d11 = 0.0D + Math.cos(d4 + 3.9269908169872414D) * d6;
/*  92 */       double d12 = 0.0D + Math.sin(d4 + 3.9269908169872414D) * d6;
/*  93 */       double d13 = 0.0D + Math.cos(d4 + 5.497787143782138D) * d6;
/*  94 */       double d14 = 0.0D + Math.sin(d4 + 5.497787143782138D) * d6;
/*  95 */       double d15 = 0.0D + Math.cos(d4 + 3.141592653589793D) * d5;
/*  96 */       double d16 = 0.0D + Math.sin(d4 + 3.141592653589793D) * d5;
/*  97 */       double d17 = 0.0D + Math.cos(d4 + 0.0D) * d5;
/*  98 */       double d18 = 0.0D + Math.sin(d4 + 0.0D) * d5;
/*  99 */       double d19 = 0.0D + Math.cos(d4 + 1.5707963267948966D) * d5;
/* 100 */       double d20 = 0.0D + Math.sin(d4 + 1.5707963267948966D) * d5;
/* 101 */       double d21 = 0.0D + Math.cos(d4 + 4.71238898038469D) * d5;
/* 102 */       double d22 = 0.0D + Math.sin(d4 + 4.71238898038469D) * d5;
/* 103 */       double d23 = 0.0D;
/* 104 */       double d24 = 0.4999D;
/* 105 */       double d25 = -1.0F + f5;
/* 106 */       double d26 = d3 * (0.5D / d5) + d25;
/* 107 */       tessellator.func_78374_a(d15, d3, d16, d24, d26);
/* 108 */       tessellator.func_78374_a(d15, 0.0D, d16, d24, d25);
/* 109 */       tessellator.func_78374_a(d17, 0.0D, d18, d23, d25);
/* 110 */       tessellator.func_78374_a(d17, d3, d18, d23, d26);
/* 111 */       tessellator.func_78374_a(d19, d3, d20, d24, d26);
/* 112 */       tessellator.func_78374_a(d19, 0.0D, d20, d24, d25);
/* 113 */       tessellator.func_78374_a(d21, 0.0D, d22, d23, d25);
/* 114 */       tessellator.func_78374_a(d21, d3, d22, d23, d26);
/* 115 */       double d27 = 0.0D;
/*     */       
/* 117 */       if (p_177109_1_.field_70173_aa % 2 == 0)
/*     */       {
/* 119 */         d27 = 0.5D;
/*     */       }
/*     */       
/* 122 */       tessellator.func_78374_a(d7, d3, d8, 0.5D, d27 + 0.5D);
/* 123 */       tessellator.func_78374_a(d9, d3, d10, 1.0D, d27 + 0.5D);
/* 124 */       tessellator.func_78374_a(d13, d3, d14, 1.0D, d27);
/* 125 */       tessellator.func_78374_a(d11, d3, d12, 0.5D, d27);
/* 126 */       tessellator.func_78381_a();
/* 127 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_76986_a(EntityLiving entity, double x, double y, double z, float p_76986_8_, float partialTicks)
/*     */   {
/* 134 */     func_177109_a((EntityWatcher)entity, x, y, z, p_76986_8_, partialTicks);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_76986_a(EntityLivingBase entity, double x, double y, double z, float p_76986_8_, float partialTicks)
/*     */   {
/* 141 */     func_177109_a((EntityWatcher)entity, x, y, z, p_76986_8_, partialTicks);
/*     */   }
/*     */   
/*     */ 
/*     */   protected ResourceLocation func_110775_a(Entity entity)
/*     */   {
/* 147 */     return field_177114_e;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_76986_a(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks)
/*     */   {
/* 153 */     func_177109_a((EntityWatcher)entity, x, y, z, p_76986_8_, partialTicks);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderWatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */