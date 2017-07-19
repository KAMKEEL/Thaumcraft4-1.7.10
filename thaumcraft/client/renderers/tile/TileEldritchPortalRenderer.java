/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.tiles.TileEldritchPortal;
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
/*     */ 
/*     */ public class TileEldritchPortalRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*  29 */   public static final ResourceLocation portaltex = new ResourceLocation("thaumcraft", "textures/misc/eldritch_portal.png");
/*     */   
/*     */ 
/*     */   public void func_147500_a(TileEntity te, double x, double y, double z, float f)
/*     */   {
/*     */     
/*  35 */     if (te.func_145831_w() != null) {
/*  36 */       renderPortal((TileEldritchPortal)te, x, y, z, f);
/*     */     }
/*  38 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */   private void renderPortal(TileEldritchPortal te, double x, double y, double z, float f)
/*     */   {
/*  44 */     long nt = System.nanoTime();
/*  45 */     long time = nt / 50000000L;
/*     */     
/*  47 */     int c = (int)Math.min(30.0F, te.opencount + f);
/*  48 */     int e = (int)Math.min(5.0F, te.opencount + f);
/*  49 */     float scale = e / 5.0F;
/*  50 */     float scaley = c / 30.0F;
/*  51 */     UtilsFX.bindTexture(portaltex);
/*  52 */     GL11.glPushMatrix();
/*     */     
/*  54 */     GL11.glDepthMask(false);
/*     */     
/*     */ 
/*  57 */     GL11.glEnable(3042);
/*  58 */     GL11.glBlendFunc(770, 771);
/*  59 */     GL11.glColor4f(1.0F, 0.0F, 1.0F, 1.0F);
/*     */     
/*  61 */     if ((Minecraft.func_71410_x().field_71451_h instanceof EntityPlayer))
/*     */     {
/*  63 */       Tessellator tessellator = Tessellator.field_78398_a;
/*  64 */       float arX = ActiveRenderInfo.field_74588_d;
/*  65 */       float arZ = ActiveRenderInfo.field_74586_f;
/*  66 */       float arYZ = ActiveRenderInfo.field_74587_g;
/*  67 */       float arXY = ActiveRenderInfo.field_74596_h;
/*  68 */       float arXZ = ActiveRenderInfo.field_74589_e;
/*     */       
/*  70 */       EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
/*  71 */       double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * f;
/*  72 */       double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * f;
/*  73 */       double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * f;
/*     */       
/*  75 */       tessellator.func_78382_b();
/*  76 */       tessellator.func_78380_c(220);
/*  77 */       tessellator.func_78369_a(1.0F, 1.0F, 1.0F, 1.0F);
/*  78 */       double px = x + 0.5D;
/*  79 */       double py = y + 0.5D;
/*  80 */       double pz = z + 0.5D;
/*  81 */       Vec3 v1 = Vec3.func_72443_a(-arX - arYZ, -arXZ, -arZ - arXY);
/*  82 */       Vec3 v2 = Vec3.func_72443_a(-arX + arYZ, arXZ, -arZ + arXY);
/*  83 */       Vec3 v3 = Vec3.func_72443_a(arX + arYZ, arXZ, arZ + arXY);
/*  84 */       Vec3 v4 = Vec3.func_72443_a(arX - arYZ, -arXZ, arZ - arXY);
/*  85 */       int frame = (int)time % 16;
/*  86 */       float f2 = frame / 16.0F;
/*  87 */       float f3 = f2 + 0.0625F;
/*  88 */       float f4 = 0.0F;
/*  89 */       float f5 = 1.0F;
/*  90 */       tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
/*  91 */       tessellator.func_78374_a(px + v1.field_72450_a * scale, py + v1.field_72448_b * scaley, pz + v1.field_72449_c * scale, f2, f5);
/*  92 */       tessellator.func_78374_a(px + v2.field_72450_a * scale, py + v2.field_72448_b * scaley, pz + v2.field_72449_c * scale, f3, f5);
/*  93 */       tessellator.func_78374_a(px + v3.field_72450_a * scale, py + v3.field_72448_b * scaley, pz + v3.field_72449_c * scale, f3, f4);
/*  94 */       tessellator.func_78374_a(px + v4.field_72450_a * scale, py + v4.field_72448_b * scaley, pz + v4.field_72449_c * scale, f2, f4);
/*     */       
/*  96 */       tessellator.func_78381_a();
/*     */     }
/*     */     
/*  99 */     GL11.glDisable(3042);
/*     */     
/*     */ 
/* 102 */     GL11.glDepthMask(true);
/*     */     
/* 104 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileEldritchPortalRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */