/*     */ package thaumcraft.codechicken.lib.lighting;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import thaumcraft.codechicken.lib.colour.ColourRGBA;
/*     */ import thaumcraft.codechicken.lib.render.CCRenderPipeline;
/*     */ import thaumcraft.codechicken.lib.render.CCRenderState;
/*     */ import thaumcraft.codechicken.lib.render.CCRenderState.IVertexOperation;
/*     */ import thaumcraft.codechicken.lib.vec.BlockCoord;
/*     */ 
/*     */ public class LightMatrix
/*     */   implements CCRenderState.IVertexOperation
/*     */ {
/*  15 */   public static final int operationIndex = ;
/*     */   
/*  17 */   public int computed = 0;
/*  18 */   public float[][] ao = new float[13][4];
/*  19 */   public int[][] brightness = new int[13][4];
/*     */   
/*     */   public IBlockAccess access;
/*  22 */   public BlockCoord pos = new BlockCoord();
/*     */   
/*  24 */   private int sampled = 0;
/*  25 */   private float[] aSamples = new float[27];
/*  26 */   private int[] bSamples = new int[27];
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  31 */   public static final int[][] ssamplem = { { 0, 1, 2, 3, 4, 5, 6, 7, 8 }, { 18, 19, 20, 21, 22, 23, 24, 25, 26 }, { 0, 9, 18, 1, 10, 19, 2, 11, 20 }, { 6, 15, 24, 7, 16, 25, 8, 17, 26 }, { 0, 3, 6, 9, 12, 15, 18, 21, 24 }, { 2, 5, 8, 11, 14, 17, 20, 23, 26 }, { 9, 10, 11, 12, 13, 14, 15, 16, 17 }, { 9, 10, 11, 12, 13, 14, 15, 16, 17 }, { 3, 12, 21, 4, 13, 22, 5, 14, 23 }, { 3, 12, 21, 4, 13, 22, 5, 14, 23 }, { 1, 4, 7, 10, 13, 16, 19, 22, 25 }, { 1, 4, 7, 10, 13, 16, 19, 22, 25 }, { 13, 13, 13, 13, 13, 13, 13, 13, 13 } };
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
/*     */ 
/*  45 */   public static final int[][] qsamplem = { { 0, 1, 3, 4 }, { 5, 1, 2, 4 }, { 6, 7, 3, 4 }, { 5, 7, 8, 4 } };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  50 */   public static final float[] sideao = { 0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F, 0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F, 1.0F };
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
/*     */ 
/*     */   public void locate(IBlockAccess a, int x, int y, int z)
/*     */   {
/*  78 */     this.access = a;
/*  79 */     this.pos.set(x, y, z);
/*  80 */     this.computed = 0;
/*  81 */     this.sampled = 0;
/*     */   }
/*     */   
/*     */   public void sample(int i) {
/*  85 */     if ((this.sampled & 1 << i) == 0) {
/*  86 */       int x = this.pos.x + i % 3 - 1;
/*  87 */       int y = this.pos.y + i / 9 - 1;
/*  88 */       int z = this.pos.z + i / 3 % 3 - 1;
/*  89 */       Block b = this.access.func_147439_a(x, y, z);
/*  90 */       this.bSamples[i] = this.access.func_72802_i(x, y, z, b.getLightValue(this.access, x, y, z));
/*  91 */       this.aSamples[i] = b.func_149685_I();
/*     */     }
/*     */   }
/*     */   
/*     */   public int[] brightness(int side) {
/*  96 */     sideSample(side);
/*  97 */     return this.brightness[side];
/*     */   }
/*     */   
/*     */   public float[] ao(int side) {
/* 101 */     sideSample(side);
/* 102 */     return this.ao[side];
/*     */   }
/*     */   
/*     */   public void sideSample(int side) {
/* 106 */     if ((this.computed & 1 << side) == 0) {
/* 107 */       int[] ssample = ssamplem[side];
/* 108 */       for (int q = 0; q < 4; q++) {
/* 109 */         int[] qsample = qsamplem[q];
/* 110 */         if (Minecraft.func_71379_u()) {
/* 111 */           interp(side, q, ssample[qsample[0]], ssample[qsample[1]], ssample[qsample[2]], ssample[qsample[3]]);
/*     */         } else
/* 113 */           interp(side, q, ssample[4], ssample[4], ssample[4], ssample[4]);
/*     */       }
/* 115 */       this.computed |= 1 << side;
/*     */     }
/*     */   }
/*     */   
/*     */   private void interp(int s, int q, int a, int b, int c, int d) {
/* 120 */     sample(a);sample(b);sample(c);sample(d);
/* 121 */     this.ao[s][q] = (interpAO(this.aSamples[a], this.aSamples[b], this.aSamples[c], this.aSamples[d]) * sideao[s]);
/* 122 */     this.brightness[s][q] = interpBrightness(this.bSamples[a], this.bSamples[b], this.bSamples[c], this.bSamples[d]);
/*     */   }
/*     */   
/*     */   public static float interpAO(float a, float b, float c, float d) {
/* 126 */     return (a + b + c + d) / 4.0F;
/*     */   }
/*     */   
/*     */   public static int interpBrightness(int a, int b, int c, int d) {
/* 130 */     if (a == 0)
/* 131 */       a = d;
/* 132 */     if (b == 0)
/* 133 */       b = d;
/* 134 */     if (c == 0)
/* 135 */       c = d;
/* 136 */     return a + b + c + d >> 2 & 0xFF00FF;
/*     */   }
/*     */   
/*     */   public boolean load()
/*     */   {
/* 141 */     CCRenderState.pipeline.addDependency(CCRenderState.colourAttrib);
/* 142 */     CCRenderState.pipeline.addDependency(CCRenderState.lightCoordAttrib);
/* 143 */     return true;
/*     */   }
/*     */   
/*     */   public void operate()
/*     */   {
/* 148 */     LC lc = CCRenderState.lc;
/* 149 */     float[] a = ao(lc.side);
/* 150 */     float f = a[0] * lc.fa + a[1] * lc.fb + a[2] * lc.fc + a[3] * lc.fd;
/* 151 */     int[] b = brightness(lc.side);
/* 152 */     CCRenderState.setColour(ColourRGBA.multiplyC(CCRenderState.colour, f));
/* 153 */     CCRenderState.setBrightness((int)(b[0] * lc.fa + b[1] * lc.fb + b[2] * lc.fc + b[3] * lc.fd) & 0xFF00FF);
/*     */   }
/*     */   
/*     */   public int operationID()
/*     */   {
/* 158 */     return operationIndex;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/lighting/LightMatrix.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */