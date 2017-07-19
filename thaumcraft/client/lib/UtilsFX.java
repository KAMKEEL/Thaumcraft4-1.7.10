/*      */ package thaumcraft.client.lib;
/*      */ 
/*      */ import cpw.mods.fml.client.FMLClientHandler;
/*      */ import cpw.mods.fml.relauncher.ReflectionHelper;
/*      */ import java.awt.Color;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.io.InputStream;
/*      */ import java.io.Serializable;
/*      */ import java.text.DecimalFormat;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Random;
/*      */ import javax.imageio.ImageIO;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*      */ import net.minecraft.client.gui.FontRenderer;
/*      */ import net.minecraft.client.gui.Gui;
/*      */ import net.minecraft.client.gui.GuiScreen;
/*      */ import net.minecraft.client.gui.inventory.GuiContainer;
/*      */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*      */ import net.minecraft.client.renderer.ItemRenderer;
/*      */ import net.minecraft.client.renderer.Tessellator;
/*      */ import net.minecraft.client.renderer.texture.TextureManager;
/*      */ import net.minecraft.client.renderer.texture.TextureMap;
/*      */ import net.minecraft.client.resources.IResourceManager;
/*      */ import net.minecraft.client.settings.GameSettings;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.IIcon;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.Timer;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.World;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ import thaumcraft.api.aspects.Aspect;
/*      */ import thaumcraft.client.fx.ParticleEngine;
/*      */ import thaumcraft.client.fx.particles.FXScorch;
/*      */ import thaumcraft.client.fx.particles.FXSparkle;
/*      */ import thaumcraft.common.config.Config;
/*      */ 
/*      */ public class UtilsFX
/*      */ {
/*   49 */   public static final String[] colorNames = { "White", "Orange", "Magenta", "Light Blue", "Yellow", "Lime", "Pink", "Gray", "Light Gray", "Cyan", "Purple", "Blue", "Brown", "Green", "Red", "Black" };
/*      */   
/*      */ 
/*      */ 
/*   53 */   public static final String[] colorCodes = { "§f", "§6", "§d", "§9", "§e", "§a", "§d", "§8", "§7", "§b", "§5", "§9", "§4", "§2", "§c", "§8" };
/*      */   
/*      */ 
/*      */ 
/*   57 */   public static final int[] colors = { 15790320, 15435844, 12801229, 6719955, 14602026, 4312372, 14188952, 4408131, 10526880, 2651799, 8073150, 2437522, 5320730, 3887386, 11743532, 1973019 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*   62 */   public static int[] connectedTextureRefByID = { 0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 4, 4, 5, 5, 4, 4, 5, 5, 17, 17, 22, 26, 17, 17, 22, 26, 16, 16, 20, 20, 16, 16, 28, 28, 21, 21, 46, 42, 21, 21, 43, 38, 4, 4, 5, 5, 4, 4, 5, 5, 9, 9, 30, 12, 9, 9, 30, 12, 16, 16, 20, 20, 16, 16, 28, 28, 25, 25, 45, 37, 25, 25, 40, 32, 0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 4, 4, 5, 5, 4, 4, 5, 5, 17, 17, 22, 26, 17, 17, 22, 26, 7, 7, 24, 24, 7, 7, 10, 10, 29, 29, 44, 41, 29, 29, 39, 33, 4, 4, 5, 5, 4, 4, 5, 5, 9, 9, 30, 12, 9, 9, 30, 12, 7, 7, 24, 24, 7, 7, 10, 10, 8, 8, 36, 35, 8, 8, 34, 11 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   76 */   public static float[] lightBrightnessTable = null;
/*      */   
/*      */   public static float getBrightnessFromLight(int light) {
/*   79 */     if (lightBrightnessTable == null) {
/*   80 */       lightBrightnessTable = new float[16];
/*   81 */       float f = 0.0F;
/*   82 */       for (int i = 0; i <= 15; i++)
/*      */       {
/*   84 */         float f1 = 1.0F - i / 15.0F;
/*   85 */         lightBrightnessTable[i] = ((1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f);
/*      */       }
/*      */     }
/*   88 */     return lightBrightnessTable[light];
/*      */   }
/*      */   
/*      */   public static void infusedStoneSparkle(World world, int x, int y, int z, int md) {
/*   92 */     if (!world.field_72995_K) return;
/*   93 */     int color = 0;
/*   94 */     switch (md) {
/*   95 */     case 1:  color = 1; break;
/*   96 */     case 2:  color = 4; break;
/*   97 */     case 3:  color = 2; break;
/*   98 */     case 4:  color = 3; break;
/*   99 */     case 5:  color = 6; break;
/*  100 */     case 6:  color = 5;
/*      */     }
/*  102 */     for (int a = 0; a < thaumcraft.common.Thaumcraft.proxy.particleCount(3); a++) {
/*  103 */       FXSparkle fx = new FXSparkle(world, x + world.field_73012_v.nextFloat(), y + world.field_73012_v.nextFloat(), z + world.field_73012_v.nextFloat(), 1.75F, color == -1 ? world.field_73012_v.nextInt(5) : color, 3 + world.field_73012_v.nextInt(3));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  109 */       fx.setGravity(0.1F);
/*  110 */       ParticleEngine.instance.addEffect(world, fx);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void shootFire(World world, EntityPlayer p, boolean offset, int range, boolean lance) {
/*  115 */     Vec3 vec3d = p.func_70676_i(range);
/*  116 */     double px = p.field_70165_t - MathHelper.func_76134_b(p.field_70177_z / 180.0F * 3.141593F) * 0.1F;
/*  117 */     double py = p.field_70163_u - 0.07999999821186066D;
/*  118 */     double pz = p.field_70161_v - MathHelper.func_76126_a(p.field_70177_z / 180.0F * 3.141593F) * 0.1F;
/*  119 */     if (p.func_145782_y() != FMLClientHandler.instance().getClient().field_71439_g.func_145782_y()) {
/*  120 */       py = p.field_70121_D.field_72338_b + p.field_70131_O / 2.0F + 0.25D;
/*      */     }
/*  122 */     for (int q = 0; q < 3; q++) {
/*  123 */       FXScorch ef = new FXScorch(p.field_70170_p, px, py, pz, vec3d, range, lance);
/*      */       
/*  125 */       ef.field_70165_t += vec3d.field_72450_a * 0.30000001192092896D;
/*  126 */       ef.field_70163_u += vec3d.field_72448_b * 0.30000001192092896D;
/*  127 */       ef.field_70161_v += vec3d.field_72449_c * 0.30000001192092896D;
/*  128 */       ef.field_70169_q = ef.field_70165_t;
/*  129 */       ef.field_70167_r = ef.field_70163_u;
/*  130 */       ef.field_70166_s = ef.field_70161_v;
/*  131 */       ef.field_70165_t += vec3d.field_72450_a * 0.30000001192092896D;
/*  132 */       ef.field_70163_u += vec3d.field_72448_b * 0.30000001192092896D;
/*  133 */       ef.field_70161_v += vec3d.field_72449_c * 0.30000001192092896D;
/*  134 */       ParticleEngine.instance.addEffect(world, ef);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void renderFacingQuad(double px, double py, double pz, float angle, float scale, float alpha, int frames, int cframe, float partialTicks, int color)
/*      */   {
/*  140 */     if ((Minecraft.func_71410_x().field_71451_h instanceof EntityPlayer)) {
/*  141 */       Tessellator tessellator = Tessellator.field_78398_a;
/*  142 */       float arX = ActiveRenderInfo.field_74588_d;
/*  143 */       float arZ = ActiveRenderInfo.field_74586_f;
/*  144 */       float arYZ = ActiveRenderInfo.field_74587_g;
/*  145 */       float arXY = ActiveRenderInfo.field_74596_h;
/*  146 */       float arXZ = ActiveRenderInfo.field_74589_e;
/*      */       
/*  148 */       EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
/*  149 */       double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * partialTicks;
/*  150 */       double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * partialTicks;
/*  151 */       double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * partialTicks;
/*      */       
/*  153 */       GL11.glTranslated(-iPX, -iPY, -iPZ);
/*      */       
/*  155 */       tessellator.func_78382_b();
/*  156 */       tessellator.func_78380_c(220);
/*  157 */       tessellator.func_78384_a(color, (int)(alpha * 255.0F));
/*      */       
/*  159 */       Vec3 v1 = Vec3.func_72443_a(-arX * scale - arYZ * scale, -arXZ * scale, -arZ * scale - arXY * scale);
/*  160 */       Vec3 v2 = Vec3.func_72443_a(-arX * scale + arYZ * scale, arXZ * scale, -arZ * scale + arXY * scale);
/*  161 */       Vec3 v3 = Vec3.func_72443_a(arX * scale + arYZ * scale, arXZ * scale, arZ * scale + arXY * scale);
/*  162 */       Vec3 v4 = Vec3.func_72443_a(arX * scale - arYZ * scale, -arXZ * scale, arZ * scale - arXY * scale);
/*      */       
/*  164 */       if (angle != 0.0F)
/*      */       {
/*  166 */         Vec3 pvec = Vec3.func_72443_a(iPX, iPY, iPZ);
/*  167 */         Vec3 tvec = Vec3.func_72443_a(px, py, pz);
/*  168 */         Vec3 qvec = pvec.func_72444_a(tvec).func_72432_b();
/*  169 */         QuadHelper.setAxis(qvec, angle).rotate(v1);
/*  170 */         QuadHelper.setAxis(qvec, angle).rotate(v2);
/*  171 */         QuadHelper.setAxis(qvec, angle).rotate(v3);
/*  172 */         QuadHelper.setAxis(qvec, angle).rotate(v4);
/*      */       }
/*      */       
/*  175 */       float f2 = cframe / frames;
/*  176 */       float f3 = (cframe + 1) / frames;
/*  177 */       float f4 = 0.0F;
/*  178 */       float f5 = 1.0F;
/*  179 */       tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
/*  180 */       tessellator.func_78374_a(px + v1.field_72450_a, py + v1.field_72448_b, pz + v1.field_72449_c, f2, f5);
/*  181 */       tessellator.func_78374_a(px + v2.field_72450_a, py + v2.field_72448_b, pz + v2.field_72449_c, f3, f5);
/*  182 */       tessellator.func_78374_a(px + v3.field_72450_a, py + v3.field_72448_b, pz + v3.field_72449_c, f3, f4);
/*  183 */       tessellator.func_78374_a(px + v4.field_72450_a, py + v4.field_72448_b, pz + v4.field_72449_c, f2, f4);
/*      */       
/*  185 */       tessellator.func_78381_a();
/*      */     }
/*      */   }
/*      */   
/*      */   public static void renderFacingStrip(double px, double py, double pz, float angle, float scale, float alpha, int frames, int strip, int frame, float partialTicks, int color) {
/*  190 */     if ((Minecraft.func_71410_x().field_71451_h instanceof EntityPlayer)) {
/*  191 */       Tessellator tessellator = Tessellator.field_78398_a;
/*  192 */       float arX = ActiveRenderInfo.field_74588_d;
/*  193 */       float arZ = ActiveRenderInfo.field_74586_f;
/*  194 */       float arYZ = ActiveRenderInfo.field_74587_g;
/*  195 */       float arXY = ActiveRenderInfo.field_74596_h;
/*  196 */       float arXZ = ActiveRenderInfo.field_74589_e;
/*      */       
/*  198 */       EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
/*  199 */       double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * partialTicks;
/*  200 */       double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * partialTicks;
/*  201 */       double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * partialTicks;
/*      */       
/*  203 */       GL11.glTranslated(-iPX, -iPY, -iPZ);
/*      */       
/*  205 */       tessellator.func_78382_b();
/*  206 */       tessellator.func_78380_c(220);
/*  207 */       tessellator.func_78384_a(color, (int)(alpha * 255.0F));
/*      */       
/*  209 */       Vec3 v1 = Vec3.func_72443_a(-arX * scale - arYZ * scale, -arXZ * scale, -arZ * scale - arXY * scale);
/*  210 */       Vec3 v2 = Vec3.func_72443_a(-arX * scale + arYZ * scale, arXZ * scale, -arZ * scale + arXY * scale);
/*  211 */       Vec3 v3 = Vec3.func_72443_a(arX * scale + arYZ * scale, arXZ * scale, arZ * scale + arXY * scale);
/*  212 */       Vec3 v4 = Vec3.func_72443_a(arX * scale - arYZ * scale, -arXZ * scale, arZ * scale - arXY * scale);
/*      */       
/*  214 */       if (angle != 0.0F)
/*      */       {
/*  216 */         Vec3 pvec = Vec3.func_72443_a(iPX, iPY, iPZ);
/*  217 */         Vec3 tvec = Vec3.func_72443_a(px, py, pz);
/*  218 */         Vec3 qvec = pvec.func_72444_a(tvec).func_72432_b();
/*  219 */         QuadHelper.setAxis(qvec, angle).rotate(v1);
/*  220 */         QuadHelper.setAxis(qvec, angle).rotate(v2);
/*  221 */         QuadHelper.setAxis(qvec, angle).rotate(v3);
/*  222 */         QuadHelper.setAxis(qvec, angle).rotate(v4);
/*      */       }
/*      */       
/*  225 */       float f2 = frame / frames;
/*  226 */       float f3 = (frame + 1) / frames;
/*  227 */       float f4 = strip / frames;
/*  228 */       float f5 = (strip + 1.0F) / frames;
/*  229 */       tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
/*  230 */       tessellator.func_78374_a(px + v1.field_72450_a, py + v1.field_72448_b, pz + v1.field_72449_c, f3, f5);
/*  231 */       tessellator.func_78374_a(px + v2.field_72450_a, py + v2.field_72448_b, pz + v2.field_72449_c, f3, f4);
/*  232 */       tessellator.func_78374_a(px + v3.field_72450_a, py + v3.field_72448_b, pz + v3.field_72449_c, f2, f4);
/*  233 */       tessellator.func_78374_a(px + v4.field_72450_a, py + v4.field_72448_b, pz + v4.field_72449_c, f2, f5);
/*      */       
/*  235 */       tessellator.func_78381_a();
/*      */     }
/*      */   }
/*      */   
/*      */   public static void renderAnimatedQuad(float scale, float alpha, int frames, int cframe, float partialTicks, int color) {
/*  240 */     if ((Minecraft.func_71410_x().field_71451_h instanceof EntityPlayer)) {
/*  241 */       Tessellator tessellator = Tessellator.field_78398_a;
/*      */       
/*  243 */       tessellator.func_78382_b();
/*  244 */       tessellator.func_78380_c(220);
/*  245 */       tessellator.func_78384_a(color, (int)(alpha * 255.0F));
/*      */       
/*  247 */       float f2 = cframe / frames;
/*  248 */       float f3 = (cframe + 1) / frames;
/*  249 */       float f4 = 0.0F;
/*  250 */       float f5 = 1.0F;
/*  251 */       tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
/*      */       
/*  253 */       tessellator.func_78374_a(-0.5D * scale, 0.5D * scale, 0.0D, f2, f5);
/*  254 */       tessellator.func_78374_a(0.5D * scale, 0.5D * scale, 0.0D, f3, f5);
/*  255 */       tessellator.func_78374_a(0.5D * scale, -0.5D * scale, 0.0D, f3, f4);
/*  256 */       tessellator.func_78374_a(-0.5D * scale, -0.5D * scale, 0.0D, f2, f4);
/*  257 */       tessellator.func_78381_a();
/*      */     }
/*      */   }
/*      */   
/*      */   public static void renderAnimatedQuadStrip(float scale, float alpha, int frames, int strip, int cframe, float partialTicks, int color) {
/*  262 */     if ((Minecraft.func_71410_x().field_71451_h instanceof EntityPlayer)) {
/*  263 */       Tessellator tessellator = Tessellator.field_78398_a;
/*      */       
/*  265 */       tessellator.func_78382_b();
/*  266 */       tessellator.func_78380_c(220);
/*  267 */       tessellator.func_78384_a(color, (int)(alpha * 255.0F));
/*      */       
/*  269 */       float f2 = cframe / frames;
/*  270 */       float f3 = (cframe + 1) / frames;
/*  271 */       float f4 = strip / frames;
/*  272 */       float f5 = (strip + 1) / frames;
/*  273 */       tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
/*      */       
/*  275 */       tessellator.func_78374_a(-0.5D * scale, 0.5D * scale, 0.0D, f2, f5);
/*  276 */       tessellator.func_78374_a(0.5D * scale, 0.5D * scale, 0.0D, f3, f5);
/*  277 */       tessellator.func_78374_a(0.5D * scale, -0.5D * scale, 0.0D, f3, f4);
/*  278 */       tessellator.func_78374_a(-0.5D * scale, -0.5D * scale, 0.0D, f2, f4);
/*  279 */       tessellator.func_78381_a();
/*      */     }
/*      */   }
/*      */   
/*      */   public static Vec3 perpendicular(Vec3 v)
/*      */   {
/*  285 */     if (v.field_72449_c == 0.0D) {
/*  286 */       return zCrossProduct(v);
/*      */     }
/*  288 */     return xCrossProduct(v);
/*      */   }
/*      */   
/*      */   public static Vec3 xCrossProduct(Vec3 v)
/*      */   {
/*  293 */     double d = v.field_72449_c;
/*  294 */     double d1 = -v.field_72448_b;
/*  295 */     v.field_72450_a = 0.0D;
/*  296 */     v.field_72448_b = d;
/*  297 */     v.field_72449_c = d1;
/*  298 */     return v;
/*      */   }
/*      */   
/*      */   public static Vec3 zCrossProduct(Vec3 v)
/*      */   {
/*  303 */     double d = v.field_72448_b;
/*  304 */     double d1 = -v.field_72450_a;
/*  305 */     v.field_72450_a = d;
/*  306 */     v.field_72448_b = d1;
/*  307 */     v.field_72449_c = 0.0D;
/*  308 */     return v;
/*      */   }
/*      */   
/*      */   public static void drawTexturedQuad(int par1, int par2, int par3, int par4, int par5, int par6, double zLevel)
/*      */   {
/*  313 */     float var7 = 0.00390625F;
/*  314 */     float var8 = 0.00390625F;
/*  315 */     Tessellator var9 = Tessellator.field_78398_a;
/*  316 */     var9.func_78382_b();
/*  317 */     var9.func_78374_a(par1 + 0, par2 + par6, zLevel, (par3 + 0) * var7, (par4 + par6) * var8);
/*  318 */     var9.func_78374_a(par1 + par5, par2 + par6, zLevel, (par3 + par5) * var7, (par4 + par6) * var8);
/*  319 */     var9.func_78374_a(par1 + par5, par2 + 0, zLevel, (par3 + par5) * var7, (par4 + 0) * var8);
/*  320 */     var9.func_78374_a(par1 + 0, par2 + 0, zLevel, (par3 + 0) * var7, (par4 + 0) * var8);
/*  321 */     var9.func_78381_a();
/*      */   }
/*      */   
/*      */   public static void drawTexturedQuadFull(int par1, int par2, double zLevel)
/*      */   {
/*  326 */     Tessellator var9 = Tessellator.field_78398_a;
/*  327 */     var9.func_78382_b();
/*  328 */     var9.func_78374_a(par1 + 0, par2 + 16, zLevel, 0.0D, 1.0D);
/*  329 */     var9.func_78374_a(par1 + 16, par2 + 16, zLevel, 1.0D, 1.0D);
/*  330 */     var9.func_78374_a(par1 + 16, par2 + 0, zLevel, 1.0D, 0.0D);
/*  331 */     var9.func_78374_a(par1 + 0, par2 + 0, zLevel, 0.0D, 0.0D);
/*  332 */     var9.func_78381_a();
/*      */   }
/*      */   
/*      */   public static void renderQuad(String texture)
/*      */   {
/*  337 */     renderQuad(texture, 1, 0.66F);
/*      */   }
/*      */   
/*      */   public static void renderQuad(String texture, int blend, float trans) {
/*  341 */     renderQuad(texture, blend, trans, 1.0F, 1.0F, 1.0F);
/*      */   }
/*      */   
/*      */   public static void renderQuad(String texture, int blend, float trans, float r, float g, float b)
/*      */   {
/*  346 */     bindTexture(texture);
/*  347 */     Tessellator tessellator = Tessellator.field_78398_a;
/*      */     
/*  349 */     GL11.glEnable(32826);
/*  350 */     GL11.glEnable(3042);
/*  351 */     GL11.glBlendFunc(770, blend);
/*  352 */     GL11.glColor4f(r, g, b, trans);
/*      */     
/*  354 */     tessellator.func_78382_b();
/*  355 */     tessellator.func_78369_a(r, g, b, trans);
/*  356 */     tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
/*  357 */     tessellator.func_78374_a(0.0D, 1.0D, 0.0D, 0.0D, 1.0D);
/*  358 */     tessellator.func_78374_a(1.0D, 1.0D, 0.0D, 1.0D, 1.0D);
/*  359 */     tessellator.func_78374_a(1.0D, 0.0D, 0.0D, 1.0D, 0.0D);
/*  360 */     tessellator.func_78374_a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*  361 */     tessellator.func_78381_a();
/*  362 */     GL11.glDisable(3042);
/*  363 */     GL11.glDisable(32826);
/*      */   }
/*      */   
/*      */   public static void renderQuadCenteredFromTexture(String texture, float scale, float red, float green, float blue, int brightness, int blend, float opacity)
/*      */   {
/*  368 */     bindTexture(texture);
/*  369 */     renderQuadCenteredFromTexture(scale, red, green, blue, brightness, blend, opacity);
/*      */   }
/*      */   
/*      */   public static void renderQuadCenteredFromTexture(ResourceLocation texture, float scale, float red, float green, float blue, int brightness, int blend, float opacity) {
/*  373 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(texture);
/*  374 */     renderQuadCenteredFromTexture(scale, red, green, blue, brightness, blend, opacity);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void renderQuadCenteredFromTexture(float scale, float red, float green, float blue, int brightness, int blend, float opacity)
/*      */   {
/*  380 */     Tessellator tessellator = Tessellator.field_78398_a;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  386 */     GL11.glScalef(scale, scale, scale);
/*  387 */     GL11.glEnable(3042);
/*  388 */     GL11.glBlendFunc(770, blend);
/*      */     
/*  390 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, opacity);
/*      */     
/*  392 */     tessellator.func_78382_b();
/*  393 */     if (brightness > 0) tessellator.func_78380_c(brightness);
/*  394 */     tessellator.func_78369_a(red, green, blue, opacity);
/*      */     
/*  396 */     tessellator.func_78374_a(-0.5D, 0.5D, 0.0D, 0.0D, 1.0D);
/*  397 */     tessellator.func_78374_a(0.5D, 0.5D, 0.0D, 1.0D, 1.0D);
/*  398 */     tessellator.func_78374_a(0.5D, -0.5D, 0.0D, 1.0D, 0.0D);
/*  399 */     tessellator.func_78374_a(-0.5D, -0.5D, 0.0D, 0.0D, 0.0D);
/*  400 */     tessellator.func_78381_a();
/*      */     
/*  402 */     GL11.glDisable(3042);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static void renderQuadFromTexture(String texture, int tileSize, int icon, float scale, float red, float green, float blue, int brightness, int blend, float opacity)
/*      */   {
/*  409 */     bindTexture(texture);
/*  410 */     int size = getTextureSize(texture, tileSize);
/*  411 */     float size16 = size * tileSize;
/*  412 */     float float_sizeMinus0_01 = size - 0.01F;
/*  413 */     float float_texNudge = 1.0F / (size * size * 2.0F);
/*  414 */     float float_reciprocal = 1.0F / size;
/*      */     
/*  416 */     Tessellator tessellator = Tessellator.field_78398_a;
/*      */     
/*  418 */     int i = icon;
/*  419 */     float f = (i % tileSize * size + 0.0F) / size16;
/*  420 */     float f1 = (i % tileSize * size + float_sizeMinus0_01) / size16;
/*  421 */     float f2 = (i / tileSize * size + 0.0F) / size16;
/*  422 */     float f3 = (i / tileSize * size + float_sizeMinus0_01) / size16;
/*  423 */     float f5 = 0.0F;
/*  424 */     float f6 = 0.3F;
/*  425 */     GL11.glEnable(32826);
/*  426 */     GL11.glScalef(scale, scale, scale);
/*  427 */     GL11.glEnable(3042);
/*  428 */     GL11.glBlendFunc(770, blend);
/*      */     
/*  430 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, opacity);
/*      */     
/*  432 */     tessellator.func_78382_b();
/*  433 */     tessellator.func_78380_c(brightness);
/*  434 */     tessellator.func_78369_a(red, green, blue, opacity);
/*  435 */     tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
/*  436 */     tessellator.func_78374_a(0.0D, 1.0D, 0.0D, f1, f2);
/*  437 */     tessellator.func_78374_a(1.0D, 1.0D, 0.0D, f, f2);
/*  438 */     tessellator.func_78374_a(1.0D, 0.0D, 0.0D, f, f3);
/*  439 */     tessellator.func_78374_a(0.0D, 0.0D, 0.0D, f1, f3);
/*  440 */     tessellator.func_78381_a();
/*      */     
/*  442 */     GL11.glDisable(3042);
/*  443 */     GL11.glDisable(32826);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void renderQuadFromIcon(boolean isBlock, IIcon icon, float scale, float red, float green, float blue, int brightness, int blend, float opacity)
/*      */   {
/*  452 */     if (isBlock)
/*      */     {
/*  454 */       Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
/*      */     }
/*      */     else
/*      */     {
/*  458 */       Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110576_c);
/*      */     }
/*      */     
/*  461 */     Tessellator tessellator = Tessellator.field_78398_a;
/*      */     
/*  463 */     float f1 = icon.func_94212_f();
/*  464 */     float f2 = icon.func_94206_g();
/*  465 */     float f3 = icon.func_94209_e();
/*  466 */     float f4 = icon.func_94210_h();
/*      */     
/*      */ 
/*  469 */     GL11.glScalef(scale, scale, scale);
/*  470 */     GL11.glEnable(3042);
/*  471 */     GL11.glBlendFunc(770, blend);
/*      */     
/*  473 */     GL11.glColor4f(red, green, blue, opacity);
/*      */     
/*  475 */     tessellator.func_78382_b();
/*  476 */     if (brightness > -1) tessellator.func_78380_c(brightness);
/*  477 */     tessellator.func_78369_a(red, green, blue, opacity);
/*  478 */     tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
/*  479 */     tessellator.func_78374_a(0.0D, 0.0D, 0.0D, f1, f4);
/*  480 */     tessellator.func_78374_a(1.0D, 0.0D, 0.0D, f3, f4);
/*  481 */     tessellator.func_78374_a(1.0D, 1.0D, 0.0D, f3, f2);
/*  482 */     tessellator.func_78374_a(0.0D, 1.0D, 0.0D, f1, f2);
/*  483 */     tessellator.func_78381_a();
/*      */     
/*      */ 
/*  486 */     GL11.glDisable(3042);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void renderQuadCenteredFromIcon(boolean isBlock, IIcon icon, float scale, float red, float green, float blue, int brightness, int blend, float opacity)
/*      */   {
/*  496 */     if (isBlock)
/*      */     {
/*  498 */       Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
/*      */     }
/*      */     else
/*      */     {
/*  502 */       Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110576_c);
/*      */     }
/*      */     
/*  505 */     Tessellator tessellator = Tessellator.field_78398_a;
/*      */     
/*  507 */     float f1 = icon.func_94212_f();
/*  508 */     float f2 = icon.func_94206_g();
/*  509 */     float f3 = icon.func_94209_e();
/*  510 */     float f4 = icon.func_94210_h();
/*      */     
/*  512 */     GL11.glEnable(32826);
/*  513 */     GL11.glScalef(scale, scale, scale);
/*  514 */     GL11.glEnable(3042);
/*  515 */     GL11.glBlendFunc(770, blend);
/*      */     
/*  517 */     GL11.glColor4f(red, green, blue, opacity);
/*      */     
/*  519 */     tessellator.func_78382_b();
/*  520 */     tessellator.func_78380_c(brightness);
/*  521 */     tessellator.func_78369_a(red, green, blue, opacity);
/*  522 */     tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
/*      */     
/*  524 */     tessellator.func_78374_a(-0.5D, 0.5D, 0.0D, f1, f4);
/*  525 */     tessellator.func_78374_a(0.5D, 0.5D, 0.0D, f3, f4);
/*  526 */     tessellator.func_78374_a(0.5D, -0.5D, 0.0D, f3, f2);
/*  527 */     tessellator.func_78374_a(-0.5D, -0.5D, 0.0D, f1, f2);
/*  528 */     tessellator.func_78381_a();
/*      */     
/*      */ 
/*  531 */     GL11.glDisable(3042);
/*  532 */     GL11.glDisable(32826);
/*      */   }
/*      */   
/*      */   public static int getTextureAnimationSize(String s)
/*      */   {
/*  537 */     if (textureSizeCache.get(s) != null) return ((Integer)textureSizeCache.get(s)).intValue();
/*      */     try {
/*  539 */       InputStream inputstream = Minecraft.func_71410_x().func_110442_L().func_110536_a(new ResourceLocation("thaumcraft", s)).func_110527_b();
/*  540 */       if (inputstream == null)
/*      */       {
/*  542 */         throw new Exception("Image not found: " + s);
/*      */       }
/*  544 */       BufferedImage bi = ImageIO.read(inputstream);
/*      */       
/*  546 */       int size = bi.getWidth() / bi.getHeight();
/*  547 */       textureSizeCache.put(s, Integer.valueOf(size));
/*  548 */       return size;
/*      */     }
/*      */     catch (Exception e) {
/*  551 */       e.printStackTrace();
/*      */     }
/*  553 */     return 16;
/*      */   }
/*      */   
/*      */   public static int getTextureSize(String s, int dv) {
/*  557 */     if (textureSizeCache.get(Arrays.asList(new Serializable[] { s, Integer.valueOf(dv) })) != null) return ((Integer)textureSizeCache.get(Arrays.asList(new Serializable[] { s, Integer.valueOf(dv) }))).intValue();
/*      */     try {
/*  559 */       InputStream inputstream = Minecraft.func_71410_x().func_110442_L().func_110536_a(new ResourceLocation("thaumcraft", s)).func_110527_b();
/*  560 */       if (inputstream == null)
/*      */       {
/*  562 */         throw new Exception("Image not found: " + s);
/*      */       }
/*  564 */       BufferedImage bi = ImageIO.read(inputstream);
/*      */       
/*  566 */       int size = bi.getWidth() / dv;
/*  567 */       textureSizeCache.put(Arrays.asList(new Serializable[] { s, Integer.valueOf(dv) }), Integer.valueOf(size));
/*  568 */       return size;
/*      */     }
/*      */     catch (Exception e) {
/*  571 */       e.printStackTrace();
/*      */     }
/*  573 */     return 16;
/*      */   }
/*      */   
/*  576 */   private static Map textureSizeCache = new HashMap();
/*      */   
/*      */   public static int getBrightnessForRender(Entity entity, double x, double z)
/*      */   {
/*  580 */     int var2 = MathHelper.func_76128_c(x);
/*  581 */     int var3 = MathHelper.func_76128_c(z);
/*      */     
/*  583 */     if (entity.field_70170_p.func_72899_e(var2, 0, var3))
/*      */     {
/*  585 */       double var4 = (entity.field_70121_D.field_72337_e - entity.field_70121_D.field_72338_b) * 0.66D;
/*  586 */       int var6 = MathHelper.func_76128_c(entity.field_70163_u - entity.field_70129_M + var4);
/*  587 */       return entity.field_70170_p.func_72802_i(var2, var6, var3, 2);
/*      */     }
/*      */     
/*      */ 
/*  591 */     return 0;
/*      */   }
/*      */   
/*      */ 
/*  595 */   static Map<String, ResourceLocation> boundTextures = new HashMap();
/*      */   
/*      */   public static void bindTexture(String texture) {
/*  598 */     ResourceLocation rl = null;
/*  599 */     if (boundTextures.containsKey(texture)) {
/*  600 */       rl = (ResourceLocation)boundTextures.get(texture);
/*      */     } else {
/*  602 */       rl = new ResourceLocation("thaumcraft", texture);
/*      */     }
/*  604 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(rl);
/*      */   }
/*      */   
/*      */   public static void bindTexture(String mod, String texture) {
/*  608 */     ResourceLocation rl = null;
/*  609 */     if (boundTextures.containsKey(mod + ":" + texture)) {
/*  610 */       rl = (ResourceLocation)boundTextures.get(mod + ":" + texture);
/*      */     } else {
/*  612 */       rl = new ResourceLocation(mod, texture);
/*      */     }
/*  614 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(rl);
/*      */   }
/*      */   
/*      */   public static void bindTexture(ResourceLocation resource)
/*      */   {
/*  619 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(resource);
/*      */   }
/*      */   
/*      */   public static void drawTag(int x, int y, Aspect aspect, float amount, int bonus, double z, int blend, float alpha)
/*      */   {
/*  624 */     drawTag(x, y, aspect, amount, bonus, z, blend, alpha, false);
/*      */   }
/*      */   
/*      */   public static void drawTag(int x, int y, Aspect aspect, float amt, int bonus, double z) {
/*  628 */     drawTag(x, y, aspect, amt, bonus, z, 771, 1.0F, false);
/*      */   }
/*      */   
/*      */   public static void drawTag(int x, int y, Aspect aspect) {
/*  632 */     drawTag(x, y, aspect, 0.0F, 0, 0.0D, 771, 1.0F, true);
/*      */   }
/*      */   
/*  635 */   static DecimalFormat myFormatter = new DecimalFormat("#######.##");
/*      */   
/*      */   public static void drawTag(int x, int y, Aspect aspect, float amount, int bonus, double z, int blend, float alpha, boolean bw)
/*      */   {
/*  639 */     drawTag(x, y, aspect, amount, bonus, z, blend, alpha, bw);
/*      */   }
/*      */   
/*      */   public static void drawTag(double x, double y, Aspect aspect, float amount, int bonus, double z, int blend, float alpha, boolean bw)
/*      */   {
/*  644 */     if (aspect == null) return;
/*  645 */     Minecraft mc = Minecraft.func_71410_x();
/*  646 */     Color color = new Color(aspect.getColor());
/*  647 */     GL11.glPushMatrix();
/*  648 */     GL11.glDisable(2896);
/*  649 */     GL11.glAlphaFunc(516, 0.003921569F);
/*  650 */     GL11.glEnable(3042);
/*  651 */     GL11.glBlendFunc(770, blend);
/*      */     
/*      */ 
/*      */ 
/*  655 */     GL11.glPushMatrix();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  661 */     mc.field_71446_o.func_110577_a(aspect.getImage());
/*  662 */     if (!bw) {
/*  663 */       GL11.glColor4f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, alpha);
/*      */     } else {
/*  665 */       GL11.glColor4f(0.1F, 0.1F, 0.1F, alpha * 0.8F);
/*      */     }
/*      */     
/*  668 */     Tessellator var9 = Tessellator.field_78398_a;
/*      */     
/*  670 */     var9.func_78382_b();
/*  671 */     if (!bw) {
/*  672 */       var9.func_78369_a(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, alpha);
/*      */     } else {
/*  674 */       var9.func_78369_a(0.1F, 0.1F, 0.1F, alpha * 0.8F);
/*      */     }
/*  676 */     var9.func_78374_a(x + 0.0D, y + 16.0D, z, 0.0D, 1.0D);
/*  677 */     var9.func_78374_a(x + 16.0D, y + 16.0D, z, 1.0D, 1.0D);
/*  678 */     var9.func_78374_a(x + 16.0D, y + 0.0D, z, 1.0D, 0.0D);
/*  679 */     var9.func_78374_a(x + 0.0D, y + 0.0D, z, 0.0D, 0.0D);
/*  680 */     var9.func_78381_a();
/*      */     
/*  682 */     GL11.glPopMatrix();
/*      */     
/*      */ 
/*  685 */     if (amount > 0.0F) {
/*  686 */       GL11.glPushMatrix();
/*  687 */       GL11.glScalef(0.5F, 0.5F, 0.5F);
/*  688 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  689 */       String am = myFormatter.format(amount);
/*  690 */       int sw = mc.field_71466_p.func_78256_a(am);
/*  691 */       if (blend > 1)
/*  692 */         for (int a = -1; a <= 1; a++) for (int b = -1; b <= 1; b++)
/*  693 */             if (((a == 0) || (b == 0)) && ((a != 0) || (b != 0)))
/*  694 */               mc.field_71466_p.func_78276_b(am, a + 32 - sw + (int)x * 2, b + 32 - mc.field_71466_p.field_78288_b + (int)y * 2, 0);
/*  695 */       mc.field_71466_p.func_78276_b(am, 32 - sw + (int)x * 2, 32 - mc.field_71466_p.field_78288_b + (int)y * 2, 16777215);
/*  696 */       GL11.glPopMatrix();
/*      */     }
/*      */     
/*  699 */     if (bonus > 0) {
/*  700 */       GL11.glPushMatrix();
/*  701 */       bindTexture(ParticleEngine.particleTexture);
/*  702 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  703 */       int px = 16 * (mc.field_71439_g.field_70173_aa % 16);
/*  704 */       drawTexturedQuad((int)x - 4, (int)y - 4, px, 80, 16, 16, z);
/*  705 */       if (bonus > 1) {
/*  706 */         GL11.glScalef(0.5F, 0.5F, 0.5F);
/*  707 */         String am = "" + bonus;
/*  708 */         int sw = mc.field_71466_p.func_78256_a(am) / 2;
/*  709 */         if (blend > 1)
/*  710 */           for (int a = -1; a <= 1; a++) for (int b = -1; b <= 1; b++)
/*  711 */               if (((a == 0) || (b == 0)) && ((a != 0) || (b != 0)))
/*  712 */                 mc.field_71466_p.func_78276_b(am, 8 - sw + a + (int)x * 2, 15 + b - mc.field_71466_p.field_78288_b + (int)y * 2, 0);
/*  713 */         mc.field_71466_p.func_78276_b(am, 8 - sw + (int)x * 2, 15 - mc.field_71466_p.field_78288_b + (int)y * 2, 16777215);
/*      */       }
/*  715 */       GL11.glPopMatrix();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  721 */     GL11.glDisable(3042);
/*  722 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  723 */     GL11.glAlphaFunc(516, 0.1F);
/*  724 */     GL11.glEnable(2896);
/*  725 */     GL11.glPopMatrix();
/*      */   }
/*      */   
/*      */ 
/*      */   public static boolean isVisibleTo(float fov, Entity ent, double x, double y, double z)
/*      */   {
/*  731 */     double dist = ent.func_70011_f(x, y, z);
/*  732 */     if (dist < 2.0D) return true;
/*  733 */     Minecraft mc = FMLClientHandler.instance().getClient();
/*  734 */     double vT = fov + mc.field_71474_y.field_74334_X / 2.0F;
/*  735 */     int j = 512;
/*  736 */     if (j > 400) j = 400;
/*  737 */     double rD = j;
/*      */     
/*  739 */     float f1 = MathHelper.func_76134_b(-ent.field_70177_z * 0.01745329F - 3.141593F);
/*  740 */     float f3 = MathHelper.func_76126_a(-ent.field_70177_z * 0.01745329F - 3.141593F);
/*  741 */     float f5 = -MathHelper.func_76134_b(-ent.field_70125_A * 0.01745329F);
/*  742 */     float f7 = MathHelper.func_76126_a(-ent.field_70125_A * 0.01745329F);
/*  743 */     double lx = f3 * f5;
/*  744 */     double ly = f7;
/*  745 */     double lz = f1 * f5;
/*  746 */     double dx = x + 0.5D - ent.field_70165_t;
/*  747 */     double dy = y + 0.5D - ent.field_70163_u - ent.func_70047_e();
/*  748 */     double dz = z + 0.5D - ent.field_70161_v;
/*  749 */     double len = Math.sqrt(dx * dx + dy * dy + dz * dz);
/*  750 */     double dot = dx / len * lx + dy / len * ly + dz / len * lz;
/*  751 */     double angle = Math.acos(dot);
/*      */     
/*  753 */     return ((angle < vT) && (mc.field_71474_y.field_74320_O == 0) && (dist < rD)) || ((mc.field_71474_y.field_74320_O > 0) && (dist < rD));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static void drawCustomTooltip(GuiScreen gui, net.minecraft.client.renderer.entity.RenderItem itemRenderer, FontRenderer fr, List var4, int par2, int par3, int subTipColor)
/*      */   {
/*  760 */     GL11.glDisable(32826);
/*      */     
/*      */ 
/*  763 */     GL11.glDisable(2929);
/*      */     
/*  765 */     if (!var4.isEmpty())
/*      */     {
/*  767 */       int var5 = 0;
/*  768 */       Iterator var6 = var4.iterator();
/*      */       
/*  770 */       while (var6.hasNext())
/*      */       {
/*  772 */         String var7 = (String)var6.next();
/*  773 */         int var8 = fr.func_78256_a(var7);
/*      */         
/*  775 */         if (var8 > var5)
/*      */         {
/*  777 */           var5 = var8;
/*      */         }
/*      */       }
/*      */       
/*  781 */       int var15 = par2 + 12;
/*  782 */       int var16 = par3 - 12;
/*  783 */       int var9 = 8;
/*      */       
/*  785 */       if (var4.size() > 1)
/*      */       {
/*  787 */         var9 += 2 + (var4.size() - 1) * 10;
/*      */       }
/*  789 */       itemRenderer.field_77023_b = 300.0F;
/*  790 */       int var10 = -267386864;
/*  791 */       drawGradientRect(var15 - 3, var16 - 4, var15 + var5 + 3, var16 - 3, var10, var10);
/*  792 */       drawGradientRect(var15 - 3, var16 + var9 + 3, var15 + var5 + 3, var16 + var9 + 4, var10, var10);
/*  793 */       drawGradientRect(var15 - 3, var16 - 3, var15 + var5 + 3, var16 + var9 + 3, var10, var10);
/*  794 */       drawGradientRect(var15 - 4, var16 - 3, var15 - 3, var16 + var9 + 3, var10, var10);
/*  795 */       drawGradientRect(var15 + var5 + 3, var16 - 3, var15 + var5 + 4, var16 + var9 + 3, var10, var10);
/*  796 */       int var11 = 1347420415;
/*  797 */       int var12 = (var11 & 0xFEFEFE) >> 1 | var11 & 0xFF000000;
/*  798 */       drawGradientRect(var15 - 3, var16 - 3 + 1, var15 - 3 + 1, var16 + var9 + 3 - 1, var11, var12);
/*  799 */       drawGradientRect(var15 + var5 + 2, var16 - 3 + 1, var15 + var5 + 3, var16 + var9 + 3 - 1, var11, var12);
/*  800 */       drawGradientRect(var15 - 3, var16 - 3, var15 + var5 + 3, var16 - 3 + 1, var11, var11);
/*  801 */       drawGradientRect(var15 - 3, var16 + var9 + 2, var15 + var5 + 3, var16 + var9 + 3, var12, var12);
/*      */       
/*  803 */       for (int var13 = 0; var13 < var4.size(); var13++)
/*      */       {
/*  805 */         String var14 = (String)var4.get(var13);
/*      */         
/*  807 */         if (var13 == 0)
/*      */         {
/*  809 */           var14 = "§" + Integer.toHexString(subTipColor) + var14;
/*      */         }
/*      */         else
/*      */         {
/*  813 */           var14 = "§7" + var14;
/*      */         }
/*      */         
/*  816 */         fr.func_78261_a(var14, var15, var16, -1);
/*      */         
/*  818 */         if (var13 == 0)
/*      */         {
/*  820 */           var16 += 2;
/*      */         }
/*      */         
/*  823 */         var16 += 10;
/*      */       }
/*      */     }
/*      */     
/*  827 */     itemRenderer.field_77023_b = 0.0F;
/*  828 */     GL11.glEnable(2929);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static void drawGradientRect(int par1, int par2, int par3, int par4, int par5, int par6)
/*      */   {
/*  835 */     float var7 = (par5 >> 24 & 0xFF) / 255.0F;
/*  836 */     float var8 = (par5 >> 16 & 0xFF) / 255.0F;
/*  837 */     float var9 = (par5 >> 8 & 0xFF) / 255.0F;
/*  838 */     float var10 = (par5 & 0xFF) / 255.0F;
/*  839 */     float var11 = (par6 >> 24 & 0xFF) / 255.0F;
/*  840 */     float var12 = (par6 >> 16 & 0xFF) / 255.0F;
/*  841 */     float var13 = (par6 >> 8 & 0xFF) / 255.0F;
/*  842 */     float var14 = (par6 & 0xFF) / 255.0F;
/*  843 */     GL11.glDisable(3553);
/*  844 */     GL11.glEnable(3042);
/*  845 */     GL11.glDisable(3008);
/*  846 */     GL11.glBlendFunc(770, 771);
/*  847 */     GL11.glShadeModel(7425);
/*  848 */     Tessellator var15 = Tessellator.field_78398_a;
/*  849 */     var15.func_78382_b();
/*  850 */     var15.func_78369_a(var8, var9, var10, var7);
/*  851 */     var15.func_78377_a(par3, par2, 300.0D);
/*  852 */     var15.func_78377_a(par1, par2, 300.0D);
/*  853 */     var15.func_78369_a(var12, var13, var14, var11);
/*  854 */     var15.func_78377_a(par1, par4, 300.0D);
/*  855 */     var15.func_78377_a(par3, par4, 300.0D);
/*  856 */     var15.func_78381_a();
/*  857 */     GL11.glShadeModel(7424);
/*  858 */     GL11.glDisable(3042);
/*  859 */     GL11.glEnable(3008);
/*  860 */     GL11.glEnable(3553);
/*      */   }
/*      */   
/*      */   public static void drawFloatyLine(double x, double y, double z, double x2, double y2, double z2, float partialTicks, int color, String texture, float speed, float distance) {
/*  864 */     drawFloatyLine(x, y, z, x2, y2, z2, partialTicks, color, texture, speed, distance, 0.15F);
/*      */   }
/*      */   
/*      */   public static void drawFloatyLine(double x, double y, double z, double x2, double y2, double z2, float partialTicks, int color, String texture, float speed, float distance, float width) {
/*  868 */     EntityLivingBase player = Minecraft.func_71410_x().field_71451_h;
/*  869 */     double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * partialTicks;
/*  870 */     double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * partialTicks;
/*  871 */     double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * partialTicks;
/*      */     
/*  873 */     double ePX = x2;
/*  874 */     double ePY = y2;
/*  875 */     double ePZ = z2;
/*      */     
/*  877 */     GL11.glTranslated(-iPX + ePX, -iPY + ePY, -iPZ + ePZ);
/*      */     
/*  879 */     float time = (float)(System.nanoTime() / 30000000L);
/*      */     
/*  881 */     Color co = new Color(color);
/*  882 */     float r = co.getRed() / 255.0F;
/*  883 */     float g = co.getGreen() / 255.0F;
/*  884 */     float b = co.getBlue() / 255.0F;
/*      */     
/*      */ 
/*  887 */     GL11.glDepthMask(false);
/*  888 */     GL11.glEnable(3042);
/*  889 */     GL11.glBlendFunc(770, 1);
/*      */     
/*  891 */     Tessellator tessellator = Tessellator.field_78398_a;
/*      */     
/*  893 */     double ds1x = ePX;
/*  894 */     double ds1y = ePY;
/*  895 */     double ds1z = ePZ;
/*  896 */     double dd1x = x;
/*  897 */     double dd1y = y;
/*  898 */     double dd1z = z;
/*  899 */     double dc1x = (float)(dd1x - ds1x);
/*  900 */     double dc1y = (float)(dd1y - ds1y);
/*  901 */     double dc1z = (float)(dd1z - ds1z);
/*      */     
/*  903 */     bindTexture(texture);
/*  904 */     GL11.glDisable(2884);
/*  905 */     tessellator.func_78371_b(5);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  914 */     double dx2 = 0.0D;
/*  915 */     double dy2 = 0.0D;
/*  916 */     double dz2 = 0.0D;
/*  917 */     double d3 = x - ePX;
/*  918 */     double d4 = y - ePY;
/*  919 */     double d5 = z - ePZ;
/*      */     
/*  921 */     float dist = MathHelper.func_76133_a(d3 * d3 + d4 * d4 + d5 * d5);
/*  922 */     float blocks = Math.round(dist);
/*  923 */     float length = blocks * (Config.golemLinkQuality / 2.0F);
/*      */     
/*  925 */     float f9 = 0.0F;
/*  926 */     float f10 = 1.0F;
/*      */     
/*  928 */     for (int i = 0; i <= length * distance; i++)
/*      */     {
/*  930 */       float f2 = i / length;
/*  931 */       float f2a = i * 1.5F / length;
/*  932 */       f2a = Math.min(0.75F, f2a);
/*  933 */       float f3 = 1.0F - Math.abs(i - length / 2.0F) / (length / 2.0F);
/*      */       
/*  935 */       double dx = dc1x + MathHelper.func_76126_a((float)((z % 16.0D + dist * (1.0F - f2) * Config.golemLinkQuality / 2.0F - time % 32767.0F / 5.0F) / 4.0D)) * 0.5F * f3;
/*  936 */       double dy = dc1y + MathHelper.func_76126_a((float)((x % 16.0D + dist * (1.0F - f2) * Config.golemLinkQuality / 2.0F - time % 32767.0F / 5.0F) / 3.0D)) * 0.5F * f3;
/*  937 */       double dz = dc1z + MathHelper.func_76126_a((float)((y % 16.0D + dist * (1.0F - f2) * Config.golemLinkQuality / 2.0F - time % 32767.0F / 5.0F) / 2.0D)) * 0.5F * f3;
/*      */       
/*  939 */       tessellator.func_78369_a(r, g, b, f3);
/*      */       
/*  941 */       float f13 = (1.0F - f2) * dist - time * speed;
/*  942 */       tessellator.func_78374_a(dx * f2, dy * f2 - width, dz * f2, f13, f10);
/*  943 */       tessellator.func_78374_a(dx * f2, dy * f2 + width, dz * f2, f13, f9);
/*      */     }
/*      */     
/*      */ 
/*  947 */     tessellator.func_78381_a();
/*      */     
/*  949 */     tessellator.func_78371_b(5);
/*  950 */     for (i = 0; i <= length * distance; i++)
/*      */     {
/*  952 */       float f2 = i / length;
/*  953 */       float f2a = i * 1.5F / length;
/*  954 */       f2a = Math.min(0.75F, f2a);
/*  955 */       float f3 = 1.0F - Math.abs(i - length / 2.0F) / (length / 2.0F);
/*      */       
/*  957 */       double dx = dc1x + MathHelper.func_76126_a((float)((z % 16.0D + dist * (1.0F - f2) * Config.golemLinkQuality / 2.0F - time % 32767.0F / 5.0F) / 4.0D)) * 0.5F * f3;
/*  958 */       double dy = dc1y + MathHelper.func_76126_a((float)((x % 16.0D + dist * (1.0F - f2) * Config.golemLinkQuality / 2.0F - time % 32767.0F / 5.0F) / 3.0D)) * 0.5F * f3;
/*  959 */       double dz = dc1z + MathHelper.func_76126_a((float)((y % 16.0D + dist * (1.0F - f2) * Config.golemLinkQuality / 2.0F - time % 32767.0F / 5.0F) / 2.0D)) * 0.5F * f3;
/*      */       
/*  961 */       tessellator.func_78369_a(r, g, b, f3);
/*      */       
/*  963 */       float f13 = (1.0F - f2) * dist - time * speed;
/*      */       
/*  965 */       tessellator.func_78374_a(dx * f2 - width, dy * f2, dz * f2, f13, f10);
/*  966 */       tessellator.func_78374_a(dx * f2 + width, dy * f2, dz * f2, f13, f9);
/*      */     }
/*      */     
/*  969 */     tessellator.func_78381_a();
/*      */     
/*  971 */     GL11.glEnable(2884);
/*  972 */     GL11.glDisable(3042);
/*  973 */     GL11.glDepthMask(true);
/*      */   }
/*      */   
/*      */   public static void drawFloatyGUILine(double x, double y, double x2, double y2, float partialTicks, int color, String texture, float speed, float distance)
/*      */   {
/*  978 */     GL11.glPushMatrix();
/*  979 */     GL11.glTranslated(x2, y2, 0.0D);
/*      */     
/*  981 */     float time = (float)(System.nanoTime() / 30000000L);
/*      */     
/*  983 */     Color co = new Color(color);
/*  984 */     float r = co.getRed() / 255.0F;
/*  985 */     float g = co.getGreen() / 255.0F;
/*  986 */     float b = co.getBlue() / 255.0F;
/*      */     
/*      */ 
/*      */ 
/*  990 */     GL11.glEnable(3042);
/*  991 */     GL11.glBlendFunc(770, 771);
/*      */     
/*  993 */     Tessellator tessellator = Tessellator.field_78398_a;
/*      */     
/*  995 */     double dc1x = (float)(x - x2);
/*  996 */     double dc1y = (float)(y - y2);
/*      */     
/*  998 */     bindTexture(texture);
/*      */     
/* 1000 */     tessellator.func_78371_b(5);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1008 */     double d3 = x - x2;
/* 1009 */     double d4 = y - y2;
/*      */     
/* 1011 */     float dist = MathHelper.func_76133_a(d3 * d3 + d4 * d4);
/* 1012 */     double dx = d3 / dist;
/* 1013 */     double dy = d4 / dist;
/*      */     
/* 1015 */     GL11.glRotated((float)-(Math.atan2(d3, d4) * 180.0D / 3.141592653589793D) + 90.0F, 0.0D, 0.0D, 1.0D);
/*      */     
/* 1017 */     float blocks = Math.round(dist);
/* 1018 */     float length = blocks * distance;
/* 1019 */     float f9 = 0.0F;
/* 1020 */     float f10 = 1.0F;
/* 1021 */     float sec = 1.0F / length;
/* 1022 */     for (int i = 0; i <= length; i++)
/*      */     {
/* 1024 */       float f2 = i / length;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1032 */       tessellator.func_78369_a(r, g, b, 1.0F);
/*      */       
/* 1034 */       float f13 = (1.0F - f2) * length;
/* 1035 */       float f14 = (1.0F - f2) * length + sec;
/* 1036 */       float width = 1.0F;
/* 1037 */       tessellator.func_78374_a(dx * i, 0.0F - width, 0.0D, f13 / width, f10);
/* 1038 */       tessellator.func_78374_a(dx * i, 0.0F + width, 0.0D, f14 / width, f9);
/*      */     }
/*      */     
/*      */ 
/* 1042 */     tessellator.func_78381_a();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1068 */     GL11.glDisable(3042);
/*      */     
/* 1070 */     GL11.glPopMatrix();
/*      */   }
/*      */   
/*      */   public static float getEquippedProgress(ItemRenderer ir) {
/*      */     try {
/* 1075 */       return ((Float)ReflectionHelper.getPrivateValue(ItemRenderer.class, ir, new String[] { "equippedProgress", "f", "field_78454_c" })).floatValue();
/*      */     } catch (Exception e) {}
/* 1077 */     return 0.0F;
/*      */   }
/*      */   
/*      */   public static float getPrevEquippedProgress(ItemRenderer ir)
/*      */   {
/*      */     try {
/* 1083 */       return ((Float)ReflectionHelper.getPrivateValue(ItemRenderer.class, ir, new String[] { "prevEquippedProgress", "g", "field_78451_d" })).floatValue();
/*      */     } catch (Exception e) {}
/* 1085 */     return 0.0F;
/*      */   }
/*      */   
/*      */   public static Timer getTimer(Minecraft mc)
/*      */   {
/*      */     try {
/* 1091 */       return (Timer)ReflectionHelper.getPrivateValue(Minecraft.class, mc, new String[] { "timer", "Q", "field_71428_T" });
/*      */     } catch (Exception e) {}
/* 1093 */     return new Timer(20.0F);
/*      */   }
/*      */   
/*      */   public static int getGuiXSize(GuiContainer gui)
/*      */   {
/*      */     try {
/* 1099 */       return ((Integer)ReflectionHelper.getPrivateValue(GuiContainer.class, gui, new String[] { "xSize", "f", "field_146999_f" })).intValue();
/*      */     } catch (Exception e) {}
/* 1101 */     return 0;
/*      */   }
/*      */   
/*      */   public static int getGuiYSize(GuiContainer gui)
/*      */   {
/*      */     try {
/* 1107 */       return ((Integer)ReflectionHelper.getPrivateValue(GuiContainer.class, gui, new String[] { "ySize", "g", "field_147000_g" })).intValue();
/*      */     } catch (Exception e) {}
/* 1109 */     return 0;
/*      */   }
/*      */   
/*      */   public static float getGuiZLevel(Gui gui)
/*      */   {
/*      */     try {
/* 1115 */       return ((Float)ReflectionHelper.getPrivateValue(Gui.class, gui, new String[] { "zLevel", "e", "field_73735_i" })).floatValue();
/*      */     } catch (Exception e) {}
/* 1117 */     return 0.0F;
/*      */   }
/*      */   
/*      */   public static ResourceLocation getParticleTexture()
/*      */   {
/*      */     try
/*      */     {
/* 1124 */       return (ResourceLocation)ReflectionHelper.getPrivateValue(net.minecraft.client.particle.EffectRenderer.class, null, new String[] { "particleTextures", "b", "field_110737_b" });
/*      */     } catch (Exception e) {}
/* 1126 */     return null;
/*      */   }
/*      */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/lib/UtilsFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */