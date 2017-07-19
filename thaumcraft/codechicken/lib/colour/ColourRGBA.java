/*    */ package thaumcraft.codechicken.lib.colour;
/*    */ 
/*    */ public class ColourRGBA extends Colour {
/*    */   public ColourRGBA(int colour) {
/*  5 */     super(colour >> 24 & 0xFF, colour >> 16 & 0xFF, colour >> 8 & 0xFF, colour & 0xFF);
/*    */   }
/*    */   
/*    */   public ColourRGBA(double r, double g, double b, double a) {
/*  9 */     super((int)(255.0D * r), (int)(255.0D * g), (int)(255.0D * b), (int)(255.0D * a));
/*    */   }
/*    */   
/*    */   public ColourRGBA(int r, int g, int b, int a) {
/* 13 */     super(r, g, b, a);
/*    */   }
/*    */   
/*    */   public ColourRGBA(ColourRGBA colour) {
/* 17 */     super(colour);
/*    */   }
/*    */   
/*    */   public int pack() {
/* 21 */     return pack(this);
/*    */   }
/*    */   
/*    */   public Colour copy()
/*    */   {
/* 26 */     return new ColourRGBA(this);
/*    */   }
/*    */   
/*    */   public static int pack(Colour colour) {
/* 30 */     return (colour.r & 0xFF) << 24 | (colour.g & 0xFF) << 16 | (colour.b & 0xFF) << 8 | colour.a & 0xFF;
/*    */   }
/*    */   
/*    */   public static int multiply(int c1, int c2) {
/* 34 */     if (c1 == -1) return c2;
/* 35 */     if (c2 == -1) return c1;
/* 36 */     int r = ((c1 >>> 24) * (c2 >>> 24) & 0xFF00) << 16;
/* 37 */     int g = ((c1 >> 16 & 0xFF) * (c2 >> 16 & 0xFF) & 0xFF00) << 8;
/* 38 */     int b = (c1 >> 8 & 0xFF) * (c2 >> 8 & 0xFF) & 0xFF00;
/* 39 */     int a = (c1 & 0xFF) * (c2 & 0xFF) >> 8;
/* 40 */     return r | g | b | a;
/*    */   }
/*    */   
/*    */   public static int multiplyC(int c, float f) {
/* 44 */     int r = (int)((c >>> 24) * f);
/* 45 */     int g = (int)((c >> 16 & 0xFF) * f);
/* 46 */     int b = (int)((c >> 8 & 0xFF) * f);
/* 47 */     return r << 24 | g << 16 | b << 8 | c & 0xFF;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/colour/ColourRGBA.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */