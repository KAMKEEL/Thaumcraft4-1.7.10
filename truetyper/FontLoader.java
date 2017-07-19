/*    */ package truetyper;
/*    */ 
/*    */ import java.awt.Font;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.resources.IResource;
/*    */ import net.minecraft.client.resources.IResourceManager;
/*    */ import net.minecraft.util.ResourceLocation;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FontLoader
/*    */ {
/*    */   public static TrueTypeFont loadSystemFont(String name, float defSize, boolean antialias)
/*    */   {
/* 35 */     return loadSystemFont(name, defSize, antialias, 0);
/*    */   }
/*    */   
/*    */ 
/*    */   public static TrueTypeFont loadSystemFont(String name, float defSize, boolean antialias, int type)
/*    */   {
/* 41 */     TrueTypeFont out = null;
/*    */     try {
/* 43 */       Font font = new Font(name, type, (int)defSize);
/* 44 */       font = font.deriveFont(defSize);
/* 45 */       out = new TrueTypeFont(font, antialias);
/*    */     } catch (Exception e) {
/* 47 */       e.printStackTrace();
/*    */     }
/* 49 */     return out;
/*    */   }
/*    */   
/*    */   public static TrueTypeFont createFont(ResourceLocation res, float defSize, boolean antialias) {
/* 53 */     return createFont(res, defSize, antialias, 0);
/*    */   }
/*    */   
/*    */   public static TrueTypeFont createFont(ResourceLocation res, float defSize, boolean antialias, int type)
/*    */   {
/* 58 */     TrueTypeFont out = null;
/*    */     try {
/* 60 */       Font font = Font.createFont(type, Minecraft.func_71410_x().func_110442_L().func_110536_a(res).func_110527_b());
/* 61 */       font = font.deriveFont(defSize);
/* 62 */       out = new TrueTypeFont(font, antialias);
/*    */     } catch (Exception e) {
/* 64 */       e.printStackTrace();
/*    */     }
/* 66 */     return out;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/truetyper/FontLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */