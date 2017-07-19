/*    */ package truetyper;
/*    */ 
/*    */ import java.nio.FloatBuffer;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.ScaledResolution;
/*    */ import net.minecraft.client.settings.GameSettings;
/*    */ import org.lwjgl.BufferUtils;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import org.lwjgl.util.vector.Matrix4f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FontHelper
/*    */ {
/* 32 */   private static String formatEscape = "§";
/*    */   
/*    */   public static void drawString(String s, float x, float y, TrueTypeFont font, float scaleX, float scaleY, int format, float... rgba) {
/* 35 */     Minecraft mc = Minecraft.func_71410_x();
/* 36 */     ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x(), mc.field_71443_c, mc.field_71440_d);
/* 37 */     if (mc.field_71474_y.field_74319_N) {
/* 38 */       return;
/*    */     }
/* 40 */     int amt = 1;
/* 41 */     if (sr.func_78325_e() == 1) {
/* 42 */       amt = 2;
/*    */     }
/*    */     
/* 45 */     FloatBuffer matrixData = BufferUtils.createFloatBuffer(16);
/* 46 */     GL11.glGetFloat(2982, matrixData);
/* 47 */     Matrix4f matrix = new Matrix4f();
/* 48 */     matrix.load(matrixData);
/*    */     
/* 50 */     set2DMode();
/* 51 */     y = mc.field_71440_d - y * sr.func_78325_e() - font.getLineHeight() / amt;
/* 52 */     GL11.glEnable(3042);
/* 53 */     if (s.contains(formatEscape)) {
/* 54 */       String[] pars = s.split(formatEscape);
/* 55 */       float totalOffset = 0.0F;
/* 56 */       for (int i = 0; i < pars.length; i++) {
/* 57 */         String par = pars[i];
/* 58 */         float[] c = rgba;
/* 59 */         if (i > 0) {
/* 60 */           c = Formatter.getFormatted(par.charAt(0));
/* 61 */           par = par.substring(1, par.length());
/*    */         }
/* 63 */         font.drawString(x * sr.func_78325_e() + totalOffset, y - matrix.m31 * sr.func_78325_e(), par, scaleX / amt, scaleY / amt, format, c);
/* 64 */         totalOffset += font.getWidth(par);
/*    */       }
/*    */     } else {
/* 67 */       font.drawString(x * sr.func_78325_e(), y - matrix.m31 * sr.func_78325_e(), s, scaleX / amt, scaleY / amt, format, rgba);
/*    */     }
/* 69 */     GL11.glDisable(3042);
/* 70 */     set3DMode();
/*    */   }
/*    */   
/*    */   private static void set2DMode() {
/* 74 */     Minecraft mc = Minecraft.func_71410_x();
/*    */     
/* 76 */     GL11.glMatrixMode(5889);
/* 77 */     GL11.glPushMatrix();
/* 78 */     GL11.glLoadIdentity();
/* 79 */     GL11.glOrtho(0.0D, mc.field_71443_c, 0.0D, mc.field_71440_d, -1.0D, 1.0D);
/* 80 */     GL11.glMatrixMode(5888);
/* 81 */     GL11.glPushMatrix();
/* 82 */     GL11.glLoadIdentity();
/*    */   }
/*    */   
/*    */   private static void set3DMode() {
/* 86 */     GL11.glMatrixMode(5889);
/* 87 */     GL11.glPopMatrix();
/* 88 */     GL11.glMatrixMode(5888);
/* 89 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/truetyper/FontHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */