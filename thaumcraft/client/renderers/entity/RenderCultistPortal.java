/*     */ package thaumcraft.client.renderers.entity;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.AbstractClientPlayer;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.boss.BossStatus;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.entities.monster.boss.EntityCultistPortal;
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderCultistPortal
/*     */   extends Render
/*     */ {
/*     */   public RenderCultistPortal()
/*     */   {
/*  28 */     this.field_76989_e = 0.1F;
/*  29 */     this.field_76987_f = 0.5F;
/*     */   }
/*     */   
/*  32 */   public static final ResourceLocation portaltex = new ResourceLocation("thaumcraft", "textures/misc/cultist_portal.png");
/*     */   
/*     */ 
/*     */   public void renderPortal(EntityCultistPortal portal, double px, double py, double pz, float par8, float f)
/*     */   {
/*  37 */     if (BossStatus.field_82826_b < 100) {
/*  38 */       BossStatus.func_82824_a(portal, false);
/*     */     }
/*  40 */     long nt = System.nanoTime();
/*  41 */     long time = nt / 50000000L;
/*  42 */     float scaley = 1.5F;
/*  43 */     int e = (int)Math.min(50.0F, portal.field_70173_aa + f);
/*     */     
/*  45 */     if (portal.field_70737_aN > 0) {
/*  46 */       double d = Math.sin(portal.field_70737_aN * 72 * 3.141592653589793D / 180.0D);
/*  47 */       scaley = (float)(scaley - d / 4.0D);
/*  48 */       e = (int)(e + 6.0D * d);
/*     */     }
/*     */     
/*  51 */     if (portal.pulse > 0) {
/*  52 */       double d = Math.sin(portal.pulse * 36 * 3.141592653589793D / 180.0D);
/*  53 */       scaley = (float)(scaley + d / 4.0D);
/*  54 */       e = (int)(e + 12.0D * d);
/*     */     }
/*     */     
/*  57 */     float scale = e / 50.0F * 1.3F;
/*     */     
/*  59 */     py += portal.field_70131_O / 2.0F;
/*     */     
/*  61 */     float m = (1.0F - portal.func_110143_aJ() / portal.func_110138_aP()) / 3.0F;
/*  62 */     float bob = MathHelper.func_76126_a(portal.field_70173_aa / (5.0F - 12.0F * m)) * m + m;
/*  63 */     float bob2 = MathHelper.func_76126_a(portal.field_70173_aa / (6.0F - 15.0F * m)) * m + m;
/*  64 */     float alpha = 1.0F - bob;
/*     */     
/*  66 */     scaley -= bob / 4.0F;
/*  67 */     scale -= bob2 / 3.0F;
/*     */     
/*  69 */     UtilsFX.bindTexture(portaltex);
/*  70 */     GL11.glPushMatrix();
/*     */     
/*  72 */     GL11.glEnable(3042);
/*  73 */     GL11.glBlendFunc(770, 771);
/*  74 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);
/*     */     
/*  76 */     if ((Minecraft.func_71410_x().field_71451_h instanceof EntityPlayer))
/*     */     {
/*  78 */       Tessellator tessellator = Tessellator.field_78398_a;
/*  79 */       float arX = ActiveRenderInfo.field_74588_d;
/*  80 */       float arZ = ActiveRenderInfo.field_74586_f;
/*  81 */       float arYZ = ActiveRenderInfo.field_74587_g;
/*  82 */       float arXY = ActiveRenderInfo.field_74596_h;
/*  83 */       float arXZ = ActiveRenderInfo.field_74589_e;
/*     */       
/*  85 */       EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
/*  86 */       double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * f;
/*  87 */       double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * f;
/*  88 */       double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * f;
/*  89 */       tessellator.func_78382_b();
/*  90 */       tessellator.func_78380_c(220);
/*  91 */       tessellator.func_78369_a(1.0F, 1.0F, 1.0F, alpha);
/*  92 */       Vec3 v1 = Vec3.func_72443_a(-arX - arYZ, -arXZ, -arZ - arXY);
/*  93 */       Vec3 v2 = Vec3.func_72443_a(-arX + arYZ, arXZ, -arZ + arXY);
/*  94 */       Vec3 v3 = Vec3.func_72443_a(arX + arYZ, arXZ, arZ + arXY);
/*  95 */       Vec3 v4 = Vec3.func_72443_a(arX - arYZ, -arXZ, arZ - arXY);
/*  96 */       int frame = 15 - (int)time % 16;
/*  97 */       float f2 = frame / 16.0F;
/*  98 */       float f3 = f2 + 0.0625F;
/*  99 */       float f4 = 0.0F;
/* 100 */       float f5 = 1.0F;
/* 101 */       tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
/* 102 */       tessellator.func_78374_a(px + v1.field_72450_a * scale, py + v1.field_72448_b * scaley, pz + v1.field_72449_c * scale, f3, f4);
/* 103 */       tessellator.func_78374_a(px + v2.field_72450_a * scale, py + v2.field_72448_b * scaley, pz + v2.field_72449_c * scale, f3, f5);
/* 104 */       tessellator.func_78374_a(px + v3.field_72450_a * scale, py + v3.field_72448_b * scaley, pz + v3.field_72449_c * scale, f2, f5);
/* 105 */       tessellator.func_78374_a(px + v4.field_72450_a * scale, py + v4.field_72448_b * scaley, pz + v4.field_72449_c * scale, f2, f4);
/*     */       
/* 107 */       tessellator.func_78381_a();
/*     */     }
/*     */     
/* 110 */     GL11.glDisable(32826);
/* 111 */     GL11.glDisable(3042);
/* 112 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 123 */     renderPortal((EntityCultistPortal)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected ResourceLocation func_110775_a(Entity entity)
/*     */   {
/* 131 */     return AbstractClientPlayer.field_110314_b;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderCultistPortal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */