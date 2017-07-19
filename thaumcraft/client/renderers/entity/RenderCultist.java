/*     */ package thaumcraft.client.renderers.entity;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.awt.Color;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.RenderBiped;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.boss.BossStatus;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.monster.EntityCultistCleric;
/*     */ import thaumcraft.common.entities.monster.boss.EntityCultistLeader;
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderCultist
/*     */   extends RenderBiped
/*     */ {
/*  30 */   private static final ResourceLocation skin = new ResourceLocation("thaumcraft", "textures/models/cultist.png");
/*     */   
/*     */   public RenderCultist()
/*     */   {
/*  34 */     super(new ModelBiped(), 0.5F);
/*     */   }
/*     */   
/*     */ 
/*     */   protected ResourceLocation func_110775_a(EntityLiving p_110775_1_)
/*     */   {
/*  40 */     return skin;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2)
/*     */   {
/*  46 */     if ((par1EntityLiving instanceof EntityCultistLeader)) {
/*  47 */       BossStatus.func_82824_a((EntityCultistLeader)par1EntityLiving, false);
/*  48 */       GL11.glScalef(1.25F, 1.25F, 1.25F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_76986_a(Entity entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
/*     */   {
/*  55 */     GL11.glPushMatrix();
/*     */     
/*  57 */     float bob = 0.0F;
/*  58 */     boolean rit = ((entity instanceof EntityCultistCleric)) && (((EntityCultistCleric)entity).getIsRitualist());
/*  59 */     if (rit) {
/*  60 */       int val = new Random(entity.func_145782_y()).nextInt(1000);
/*  61 */       float c = ((EntityCultistCleric)entity).field_70173_aa + p_76986_9_ + val;
/*  62 */       bob = MathHelper.func_76126_a(c / 9.0F) * 0.1F + 0.21F;
/*  63 */       GL11.glTranslated(0.0D, bob, 0.0D);
/*     */     }
/*     */     
/*  66 */     super.func_76986_a(entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */     
/*  68 */     if (rit) {
/*  69 */       GL11.glPushMatrix();
/*  70 */       drawFloatyLine(entity.field_70165_t, entity.field_70163_u + entity.func_70047_e() * 1.2F, entity.field_70161_v, ((EntityCultistCleric)entity).func_110172_bL().field_71574_a + 0.5D, ((EntityCultistCleric)entity).func_110172_bL().field_71572_b + 1.5D - bob, ((EntityCultistCleric)entity).func_110172_bL().field_71573_c + 0.5D, p_76986_9_, 1114129, "textures/misc/wispy.png", -0.03F, Math.min(((EntityCultistCleric)entity).field_70173_aa, 10) / 10.0F, 0.25F);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  76 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/*  79 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   private void drawFloatyLine(double x, double y, double z, double x2, double y2, double z2, float partialTicks, int color, String texture, float speed, float distance, float width) {
/*  83 */     EntityLivingBase player = Minecraft.func_71410_x().field_71451_h;
/*  84 */     double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * partialTicks;
/*  85 */     double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * partialTicks;
/*  86 */     double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * partialTicks;
/*     */     
/*  88 */     double ePX = x2;
/*  89 */     double ePY = y2;
/*  90 */     double ePZ = z2;
/*     */     
/*  92 */     GL11.glTranslated(-iPX + ePX, -iPY + ePY, -iPZ + ePZ);
/*     */     
/*  94 */     float time = (float)(System.nanoTime() / 30000000L);
/*     */     
/*  96 */     Color co = new Color(color);
/*  97 */     float r = co.getRed() / 255.0F;
/*  98 */     float g = co.getGreen() / 255.0F;
/*  99 */     float b = co.getBlue() / 255.0F;
/*     */     
/*     */ 
/*     */ 
/* 103 */     GL11.glEnable(3042);
/* 104 */     GL11.glBlendFunc(770, 771);
/*     */     
/* 106 */     Tessellator tessellator = Tessellator.field_78398_a;
/*     */     
/* 108 */     double ds1x = ePX;
/* 109 */     double ds1y = ePY;
/* 110 */     double ds1z = ePZ;
/* 111 */     double dd1x = x;
/* 112 */     double dd1y = y;
/* 113 */     double dd1z = z;
/* 114 */     double dc1x = (float)(dd1x - ds1x);
/* 115 */     double dc1y = (float)(dd1y - ds1y);
/* 116 */     double dc1z = (float)(dd1z - ds1z);
/*     */     
/* 118 */     UtilsFX.bindTexture(texture);
/*     */     
/* 120 */     tessellator.func_78371_b(5);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 129 */     double dx2 = 0.0D;
/* 130 */     double dy2 = 0.0D;
/* 131 */     double dz2 = 0.0D;
/* 132 */     double d3 = x - ePX;
/* 133 */     double d4 = y - ePY;
/* 134 */     double d5 = z - ePZ;
/*     */     
/* 136 */     float dist = MathHelper.func_76133_a(d3 * d3 + d4 * d4 + d5 * d5);
/* 137 */     float blocks = Math.round(dist);
/* 138 */     float length = blocks * (Config.golemLinkQuality / 2.0F);
/*     */     
/* 140 */     float f9 = 0.0F;
/* 141 */     float f10 = 1.0F;
/*     */     
/* 143 */     for (int i = 0; i <= length * distance; i++)
/*     */     {
/* 145 */       float f2 = i / length;
/* 146 */       float f2a = i * 1.5F / length;
/* 147 */       f2a = Math.min(0.75F, f2a);
/* 148 */       float f3 = 1.0F - Math.abs(i - length / 2.0F) / (length / 2.0F);
/*     */       
/* 150 */       double dx = dc1x + MathHelper.func_76126_a((float)((z % 16.0D + dist * (1.0F - f2) * Config.golemLinkQuality / 2.0F - time % 32767.0F / 5.0F) / 4.0D)) * 0.5F * f3;
/* 151 */       double dy = dc1y + MathHelper.func_76126_a((float)((x % 16.0D + dist * (1.0F - f2) * Config.golemLinkQuality / 2.0F - time % 32767.0F / 5.0F) / 3.0D)) * 0.5F * f3;
/* 152 */       double dz = dc1z + MathHelper.func_76126_a((float)((y % 16.0D + dist * (1.0F - f2) * Config.golemLinkQuality / 2.0F - time % 32767.0F / 5.0F) / 2.0D)) * 0.5F * f3;
/*     */       
/* 154 */       tessellator.func_78369_a(r, g, b, 0.8F);
/*     */       
/* 156 */       float f13 = (1.0F - f2) * dist - time * speed;
/* 157 */       tessellator.func_78374_a(dx * f2, dy * f2 - width, dz * f2, f13, f10);
/* 158 */       tessellator.func_78374_a(dx * f2, dy * f2 + width, dz * f2, f13, f9);
/*     */     }
/*     */     
/*     */ 
/* 162 */     tessellator.func_78381_a();
/*     */     
/* 164 */     tessellator.func_78371_b(5);
/* 165 */     for (i = 0; i <= length * distance; i++)
/*     */     {
/* 167 */       float f2 = i / length;
/* 168 */       float f2a = i * 1.5F / length;
/* 169 */       f2a = Math.min(0.75F, f2a);
/* 170 */       float f3 = 1.0F - Math.abs(i - length / 2.0F) / (length / 2.0F);
/*     */       
/* 172 */       double dx = dc1x + MathHelper.func_76126_a((float)((z % 16.0D + dist * (1.0F - f2) * Config.golemLinkQuality / 2.0F - time % 32767.0F / 5.0F) / 4.0D)) * 0.5F * f3;
/* 173 */       double dy = dc1y + MathHelper.func_76126_a((float)((x % 16.0D + dist * (1.0F - f2) * Config.golemLinkQuality / 2.0F - time % 32767.0F / 5.0F) / 3.0D)) * 0.5F * f3;
/* 174 */       double dz = dc1z + MathHelper.func_76126_a((float)((y % 16.0D + dist * (1.0F - f2) * Config.golemLinkQuality / 2.0F - time % 32767.0F / 5.0F) / 2.0D)) * 0.5F * f3;
/*     */       
/* 176 */       tessellator.func_78369_a(r, g, b, 0.8F);
/*     */       
/* 178 */       float f13 = (1.0F - f2) * dist - time * speed;
/*     */       
/* 180 */       tessellator.func_78374_a(dx * f2 - width, dy * f2, dz * f2, f13, f10);
/* 181 */       tessellator.func_78374_a(dx * f2 + width, dy * f2, dz * f2, f13, f9);
/*     */     }
/*     */     
/* 184 */     tessellator.func_78381_a();
/*     */     
/*     */ 
/* 187 */     GL11.glDisable(3042);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderCultist.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */