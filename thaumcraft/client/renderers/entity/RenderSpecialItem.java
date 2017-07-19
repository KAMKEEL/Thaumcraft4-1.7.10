/*     */ package thaumcraft.client.renderers.entity;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.AbstractClientPlayer;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderItem;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.common.entities.EntitySpecialItem;
/*     */ 
/*     */ 
/*     */ public class RenderSpecialItem
/*     */   extends Render
/*     */ {
/*  26 */   private RenderBlocks renderBlocks = new RenderBlocks();
/*     */   
/*     */ 
/*  29 */   private Random random = new Random();
/*  30 */   public boolean field_77024_a = true;
/*     */   
/*     */ 
/*  33 */   public float zLevel = 0.0F;
/*     */   
/*     */   public RenderSpecialItem()
/*     */   {
/*  37 */     this.field_76989_e = 0.15F;
/*  38 */     this.field_76987_f = 0.75F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void doRenderItem(EntitySpecialItem par1EntityItem, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/*  46 */     this.random.setSeed(187L);
/*  47 */     float var11 = MathHelper.func_76126_a((par1EntityItem.field_70292_b + par9) / 10.0F + par1EntityItem.field_70290_d) * 0.1F + 0.1F;
/*  48 */     GL11.glPushMatrix();
/*  49 */     GL11.glTranslatef((float)par2, (float)par4 + var11 + 0.15F, (float)par6);
/*     */     
/*  51 */     int q = !FMLClientHandler.instance().getClient().field_71474_y.field_74347_j ? 5 : 10;
/*     */     
/*  53 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  54 */     RenderHelper.func_74518_a();
/*  55 */     float f1 = par1EntityItem.field_70292_b / 500.0F;
/*  56 */     float f3 = 0.9F;
/*  57 */     float f2 = 0.0F;
/*     */     
/*  59 */     Random random = new Random(245L);
/*  60 */     GL11.glDisable(3553);
/*  61 */     GL11.glShadeModel(7425);
/*  62 */     GL11.glEnable(3042);
/*  63 */     GL11.glBlendFunc(770, 1);
/*  64 */     GL11.glDisable(3008);
/*  65 */     GL11.glEnable(2884);
/*  66 */     GL11.glDepthMask(false);
/*  67 */     GL11.glPushMatrix();
/*  68 */     for (int i = 0; i < q; i++)
/*     */     {
/*  70 */       GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
/*  71 */       GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
/*  72 */       GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
/*  73 */       GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
/*  74 */       GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
/*  75 */       GL11.glRotatef(random.nextFloat() * 360.0F + f1 * 360.0F, 0.0F, 0.0F, 1.0F);
/*  76 */       tessellator.func_78371_b(6);
/*  77 */       float fa = random.nextFloat() * 20.0F + 5.0F + f2 * 10.0F;
/*  78 */       float f4 = random.nextFloat() * 2.0F + 1.0F + f2 * 2.0F;
/*  79 */       fa /= 30.0F / (Math.min(par1EntityItem.field_70292_b, 10) / 10.0F);
/*  80 */       f4 /= 30.0F / (Math.min(par1EntityItem.field_70292_b, 10) / 10.0F);
/*  81 */       tessellator.func_78384_a(16777215, (int)(255.0F * (1.0F - f2)));
/*  82 */       tessellator.func_78377_a(0.0D, 0.0D, 0.0D);
/*  83 */       tessellator.func_78384_a(16711935, 0);
/*  84 */       tessellator.func_78377_a(-0.866D * f4, fa, -0.5F * f4);
/*  85 */       tessellator.func_78377_a(0.866D * f4, fa, -0.5F * f4);
/*  86 */       tessellator.func_78377_a(0.0D, fa, 1.0F * f4);
/*  87 */       tessellator.func_78377_a(-0.866D * f4, fa, -0.5F * f4);
/*  88 */       tessellator.func_78381_a();
/*     */     }
/*     */     
/*  91 */     GL11.glPopMatrix();
/*  92 */     GL11.glDepthMask(true);
/*  93 */     GL11.glDisable(2884);
/*  94 */     GL11.glDisable(3042);
/*  95 */     GL11.glShadeModel(7424);
/*  96 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  97 */     GL11.glEnable(3553);
/*  98 */     GL11.glEnable(3008);
/*  99 */     RenderHelper.func_74519_b();
/*     */     
/* 101 */     GL11.glPopMatrix();
/*     */     
/* 103 */     RenderItem ri = new RenderItem();
/* 104 */     ri.func_76976_a(RenderManager.field_78727_a);
/* 105 */     ItemStack var10 = par1EntityItem.func_92059_d();
/* 106 */     if (var10 != null) {
/* 107 */       EntityItem ei = new EntityItem(par1EntityItem.field_70170_p, par1EntityItem.field_70165_t, par1EntityItem.field_70163_u, par1EntityItem.field_70161_v, var10);
/*     */       
/* 109 */       ei.field_70292_b = par1EntityItem.field_70292_b;
/* 110 */       ei.field_70290_d = par1EntityItem.field_70290_d;
/* 111 */       ri.func_76986_a(ei, par2, par4, par6, par8, par9);
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
/*     */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 124 */     doRenderItem((EntitySpecialItem)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity entity)
/*     */   {
/* 129 */     return AbstractClientPlayer.field_110314_b;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/entity/RenderSpecialItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */