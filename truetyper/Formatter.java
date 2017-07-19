/*    */ package truetyper;
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
/*    */ public class Formatter
/*    */ {
/*    */   public static float[] getFormatted(char c)
/*    */   {
/* 24 */     int[] outrgba = null;
/*    */     
/* 26 */     switch (c) {
/*    */     case '0': 
/* 28 */       outrgba = new int[] { 0, 0, 0, 0, 255 };
/* 29 */       break;
/*    */     case '1': 
/* 31 */       outrgba = new int[] { 0, 0, 170, 255 };
/* 32 */       break;
/*    */     case '2': 
/* 34 */       outrgba = new int[] { 0, 170, 0, 255 };
/* 35 */       break;
/*    */     case '3': 
/* 37 */       outrgba = new int[] { 0, 170, 170, 255 };
/* 38 */       break;
/*    */     case '4': 
/* 40 */       outrgba = new int[] { 170, 0, 0, 255 };
/* 41 */       break;
/*    */     case '5': 
/* 43 */       outrgba = new int[] { 170, 0, 170, 255 };
/* 44 */       break;
/*    */     case '6': 
/* 46 */       outrgba = new int[] { 255, 170, 0, 255 };
/* 47 */       break;
/*    */     case '7': 
/* 49 */       outrgba = new int[] { 170, 170, 170, 255 };
/* 50 */       break;
/*    */     case '8': 
/* 52 */       outrgba = new int[] { 85, 85, 85, 255 };
/* 53 */       break;
/*    */     case '9': 
/* 55 */       outrgba = new int[] { 85, 85, 255, 255 };
/* 56 */       break;
/*    */     case 'a': 
/* 58 */       outrgba = new int[] { 85, 255, 85, 255 };
/* 59 */       break;
/*    */     case 'b': 
/* 61 */       outrgba = new int[] { 85, 255, 255, 255 };
/* 62 */       break;
/*    */     case 'c': 
/* 64 */       outrgba = new int[] { 255, 85, 85, 255 };
/* 65 */       break;
/*    */     case 'd': 
/* 67 */       outrgba = new int[] { 85, 255, 255, 255 };
/* 68 */       break;
/*    */     case 'e': 
/* 70 */       outrgba = new int[] { 255, 255, 85, 255 };
/* 71 */       break;
/*    */     case 'f': 
/* 73 */       outrgba = new int[] { 255, 255, 255, 255 };
/* 74 */       break;
/*    */     case ':': case ';': case '<': case '=': case '>': case '?': case '@': case 'A': case 'B': case 'C': case 'D': case 'E': case 'F': case 'G': case 'H': case 'I': case 'J': case 'K': case 'L': case 'M': case 'N': case 'O': case 'P': case 'Q': case 'R': case 'S': case 'T': case 'U': case 'V': case 'W': case 'X': case 'Y': case 'Z': case '[': case '\\': case ']': case '^': case '_': case '`': default: 
/* 76 */       outrgba = new int[] { 255, 255, 255, 255 };
/*    */     }
/*    */     
/* 79 */     float[] outfloat = new float[outrgba.length];
/* 80 */     for (int i = 0; i < outrgba.length; i++) {
/* 81 */       outfloat[i] = (outrgba[i] > 0 ? outrgba[i] / 255 : 0.0F);
/*    */     }
/* 83 */     return outfloat;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/truetyper/Formatter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */