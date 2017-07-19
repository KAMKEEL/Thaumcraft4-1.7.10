/*     */ package thaumcraft.codechicken.lib.render;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import thaumcraft.codechicken.lib.colour.ColourRGBA;
/*     */ import thaumcraft.codechicken.lib.lighting.LC;
/*     */ import thaumcraft.codechicken.lib.lighting.LightMatrix;
/*     */ import thaumcraft.codechicken.lib.render.uv.UV;
/*     */ import thaumcraft.codechicken.lib.util.Copyable;
/*     */ import thaumcraft.codechicken.lib.vec.BlockCoord;
/*     */ import thaumcraft.codechicken.lib.vec.Transformation;
/*     */ import thaumcraft.codechicken.lib.vec.Vector3;
/*     */ 
/*     */ 
/*     */ public class CCRenderState
/*     */ {
/*     */   private static int nextOperationIndex;
/*     */   
/*     */   public static int registerOperation()
/*     */   {
/*  27 */     return nextOperationIndex++;
/*     */   }
/*     */   
/*     */   public static int operationCount() {
/*  31 */     return nextOperationIndex;
/*     */   }
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
/*  57 */   private static ArrayList<VertexAttribute<?>> vertexAttributes = new ArrayList();
/*     */   
/*  59 */   private static int registerVertexAttribute(VertexAttribute<?> attr) { vertexAttributes.add(attr);
/*  60 */     return vertexAttributes.size() - 1;
/*     */   }
/*     */   
/*     */ 
/*  64 */   public static VertexAttribute<?> getAttribute(int index) { return (VertexAttribute)vertexAttributes.get(index); }
/*     */   
/*     */   public static abstract interface IVertexSource { public abstract Vertex5[] getVertices();
/*     */     
/*     */     public abstract <T> T getAttributes(CCRenderState.VertexAttribute<T> paramVertexAttribute);
/*     */     
/*     */     public abstract boolean hasAttribute(CCRenderState.VertexAttribute<?> paramVertexAttribute);
/*     */     
/*     */     public abstract void prepareVertex(); }
/*     */   
/*  74 */   public static abstract class VertexAttribute<T> implements CCRenderState.IVertexOperation { public final int attributeIndex = CCRenderState.registerVertexAttribute(this);
/*  75 */     private final int operationIndex = CCRenderState.registerOperation();
/*     */     
/*     */ 
/*     */ 
/*  79 */     public boolean active = false;
/*     */     
/*     */ 
/*     */ 
/*     */     public abstract T newArray(int paramInt);
/*     */     
/*     */ 
/*     */     public int operationID()
/*     */     {
/*  88 */       return this.operationIndex;
/*     */     }
/*     */   }
/*     */   
/*     */   public static void arrayCopy(Object src, int srcPos, Object dst, int destPos, int length) {
/*  93 */     System.arraycopy(src, srcPos, dst, destPos, length);
/*  94 */     if ((dst instanceof Copyable[])) {
/*  95 */       Object[] oa = (Object[])dst;
/*  96 */       Copyable<Object>[] c = (Copyable[])dst;
/*  97 */       for (int i = destPos; i < destPos + length; i++)
/*  98 */         if (c[i] != null)
/*  99 */           oa[i] = c[i].copy();
/*     */     }
/*     */   }
/*     */   
/*     */   public static <T> T copyOf(VertexAttribute<T> attr, T src, int length) {
/* 104 */     T dst = attr.newArray(length);
/* 105 */     arrayCopy(src, 0, dst, 0, length);
/* 106 */     return dst;
/*     */   }
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
/* 132 */   public static VertexAttribute<Vector3[]> normalAttrib = new VertexAttribute()
/*     */   {
/*     */     private Vector3[] normalRef;
/*     */     
/*     */     public Vector3[] newArray(int length) {
/* 137 */       return new Vector3[length];
/*     */     }
/*     */     
/*     */     public boolean load()
/*     */     {
/* 142 */       this.normalRef = ((Vector3[])CCRenderState.model.getAttributes(this));
/* 143 */       if (CCRenderState.model.hasAttribute(this)) {
/* 144 */         return this.normalRef != null;
/*     */       }
/* 146 */       if (CCRenderState.model.hasAttribute(CCRenderState.sideAttrib)) {
/* 147 */         CCRenderState.pipeline.addDependency(CCRenderState.sideAttrib);
/* 148 */         return true;
/*     */       }
/* 150 */       throw new IllegalStateException("Normals requested but neither normal or side attrutes are provided by the model");
/*     */     }
/*     */     
/*     */     public void operate()
/*     */     {
/* 155 */       if (this.normalRef != null) {
/* 156 */         CCRenderState.setNormal(this.normalRef[CCRenderState.vertexIndex]);
/*     */       } else
/* 158 */         CCRenderState.setNormal(thaumcraft.codechicken.lib.vec.Rotation.axes[CCRenderState.side]);
/*     */     }
/*     */   };
/* 161 */   public static VertexAttribute<int[]> colourAttrib = new VertexAttribute()
/*     */   {
/*     */     private int[] colourRef;
/*     */     
/*     */     public int[] newArray(int length) {
/* 166 */       return new int[length];
/*     */     }
/*     */     
/*     */     public boolean load()
/*     */     {
/* 171 */       this.colourRef = ((int[])CCRenderState.model.getAttributes(this));
/* 172 */       return (this.colourRef != null) || (!CCRenderState.model.hasAttribute(this));
/*     */     }
/*     */     
/*     */     public void operate()
/*     */     {
/* 177 */       if (this.colourRef != null) {
/* 178 */         CCRenderState.setColour(ColourRGBA.multiply(CCRenderState.baseColour, this.colourRef[CCRenderState.vertexIndex]));
/*     */       } else
/* 180 */         CCRenderState.setColour(CCRenderState.baseColour);
/*     */     }
/*     */   };
/* 183 */   public static VertexAttribute<int[]> sideAttrib = new VertexAttribute()
/*     */   {
/*     */     private int[] sideRef;
/*     */     
/*     */     public int[] newArray(int length) {
/* 188 */       return new int[length];
/*     */     }
/*     */     
/*     */     public boolean load()
/*     */     {
/* 193 */       this.sideRef = ((int[])CCRenderState.model.getAttributes(this));
/* 194 */       if (CCRenderState.model.hasAttribute(this)) {
/* 195 */         return this.sideRef != null;
/*     */       }
/* 197 */       CCRenderState.pipeline.addDependency(CCRenderState.normalAttrib);
/* 198 */       return true;
/*     */     }
/*     */     
/*     */     public void operate()
/*     */     {
/* 203 */       if (this.sideRef != null) {
/* 204 */         CCRenderState.side = this.sideRef[CCRenderState.vertexIndex];
/*     */       } else {
/* 206 */         CCRenderState.side = CCModel.findSide(CCRenderState.normal);
/*     */       }
/*     */     }
/*     */   };
/*     */   
/*     */ 
/* 212 */   public static VertexAttribute<LC[]> lightCoordAttrib = new VertexAttribute() {
/*     */     private LC[] lcRef;
/* 214 */     private Vector3 vec = new Vector3();
/* 215 */     private Vector3 pos = new Vector3();
/*     */     
/*     */     public LC[] newArray(int length)
/*     */     {
/* 219 */       return new LC[length];
/*     */     }
/*     */     
/*     */     public boolean load()
/*     */     {
/* 224 */       this.lcRef = ((LC[])CCRenderState.model.getAttributes(this));
/* 225 */       if (CCRenderState.model.hasAttribute(this)) {
/* 226 */         return this.lcRef != null;
/*     */       }
/* 228 */       this.pos.set(CCRenderState.lightMatrix.pos.x, CCRenderState.lightMatrix.pos.y, CCRenderState.lightMatrix.pos.z);
/* 229 */       CCRenderState.pipeline.addDependency(CCRenderState.sideAttrib);
/* 230 */       CCRenderState.pipeline.addRequirement(Transformation.operationIndex);
/* 231 */       return true;
/*     */     }
/*     */     
/*     */     public void operate()
/*     */     {
/* 236 */       if (this.lcRef != null) {
/* 237 */         CCRenderState.lc.set(this.lcRef[CCRenderState.vertexIndex]);
/*     */       } else {
/* 239 */         CCRenderState.lc.compute(this.vec.set(CCRenderState.vert.vec).sub(this.pos), CCRenderState.side);
/*     */       }
/*     */     }
/*     */   };
/*     */   
/*     */   public static IVertexSource model;
/*     */   public static int firstVertexIndex;
/*     */   public static int lastVertexIndex;
/*     */   public static int vertexIndex;
/* 248 */   public static CCRenderPipeline pipeline = new CCRenderPipeline();
/*     */   
/*     */   public static int baseColour;
/*     */   
/*     */   public static int alphaOverride;
/*     */   public static boolean useNormals;
/* 254 */   public static LightMatrix lightMatrix = new LightMatrix();
/*     */   
/*     */ 
/* 257 */   public static Vertex5 vert = new Vertex5();
/*     */   public static boolean hasNormal;
/* 259 */   public static Vector3 normal = new Vector3();
/*     */   
/*     */   public static boolean hasColour;
/*     */   
/*     */   public static int colour;
/*     */   public static boolean hasBrightness;
/*     */   public static int brightness;
/*     */   public static int side;
/* 267 */   public static LC lc = new LC();
/*     */   
/*     */   public static void reset() {
/* 270 */     model = null;
/* 271 */     pipeline.reset();
/* 272 */     hasNormal = hasColour = hasBrightness = 0;
/* 273 */     baseColour = alphaOverride = -1;
/*     */   }
/*     */   
/*     */   public static void setPipeline(IVertexOperation... ops) {
/* 277 */     pipeline.setPipeline(ops);
/*     */   }
/*     */   
/*     */   public static void setPipeline(IVertexSource model, int start, int end, IVertexOperation... ops) {
/* 281 */     pipeline.reset();
/* 282 */     setModel(model, start, end);
/* 283 */     pipeline.setPipeline(ops);
/*     */   }
/*     */   
/*     */   public static void bindModel(IVertexSource model) {
/* 287 */     if (model != model) {
/* 288 */       model = model;
/* 289 */       pipeline.rebuild();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void setModel(IVertexSource source) {
/* 294 */     setModel(source, 0, source.getVertices().length);
/*     */   }
/*     */   
/*     */   public static void setModel(IVertexSource source, int start, int end) {
/* 298 */     bindModel(source);
/* 299 */     firstVertexIndex = start;
/* 300 */     lastVertexIndex = end;
/*     */   }
/*     */   
/*     */   public static void render(IVertexOperation... ops) {
/* 304 */     setPipeline(ops);
/* 305 */     render();
/*     */   }
/*     */   
/*     */   public static void render() {
/* 309 */     Vertex5[] verts = model.getVertices();
/* 310 */     for (vertexIndex = firstVertexIndex; vertexIndex < lastVertexIndex; vertexIndex += 1) {
/* 311 */       model.prepareVertex();
/* 312 */       vert.set(verts[vertexIndex]);
/* 313 */       runPipeline();
/* 314 */       writeVert();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void runPipeline() {
/* 319 */     pipeline.operate();
/*     */   }
/*     */   
/*     */   public static void writeVert() {
/* 323 */     if (hasNormal)
/* 324 */       Tessellator.field_78398_a.func_78375_b((float)normal.x, (float)normal.y, (float)normal.z);
/* 325 */     if (hasColour)
/* 326 */       Tessellator.field_78398_a.func_78370_a(colour >>> 24, colour >> 16 & 0xFF, colour >> 8 & 0xFF, alphaOverride >= 0 ? alphaOverride : colour & 0xFF);
/* 327 */     if (hasBrightness)
/* 328 */       Tessellator.field_78398_a.func_78380_c(brightness);
/* 329 */     Tessellator.field_78398_a.func_78374_a(vert.vec.x, vert.vec.y, vert.vec.z, vert.uv.u, vert.uv.v);
/*     */   }
/*     */   
/*     */   public static void setNormal(double x, double y, double z) {
/* 333 */     hasNormal = true;
/* 334 */     normal.set(x, y, z);
/*     */   }
/*     */   
/*     */   public static void setNormal(Vector3 n) {
/* 338 */     hasNormal = true;
/* 339 */     normal.set(n);
/*     */   }
/*     */   
/*     */   public static void setColour(int c) {
/* 343 */     hasColour = true;
/* 344 */     colour = c;
/*     */   }
/*     */   
/*     */   public static void setBrightness(int b) {
/* 348 */     hasBrightness = true;
/* 349 */     brightness = b;
/*     */   }
/*     */   
/*     */   public static void setBrightness(IBlockAccess world, int x, int y, int z) {
/* 353 */     setBrightness(world.func_147439_a(x, y, z).func_149677_c(world, x, y, z));
/*     */   }
/*     */   
/*     */   public static void pullLightmap() {
/* 357 */     setBrightness((int)OpenGlHelper.lastBrightnessY << 16 | (int)OpenGlHelper.lastBrightnessX);
/*     */   }
/*     */   
/*     */   public static void changeTexture(String texture) {
/* 361 */     changeTexture(new ResourceLocation(texture));
/*     */   }
/*     */   
/*     */   public static void changeTexture(ResourceLocation texture) {
/* 365 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(texture);
/*     */   }
/*     */   
/*     */   public static void startDrawing() {
/* 369 */     startDrawing(7);
/*     */   }
/*     */   
/*     */   private static void startDrawing(int mode) {
/* 373 */     Tessellator.field_78398_a.func_78371_b(mode);
/* 374 */     if (hasColour)
/* 375 */       Tessellator.field_78398_a.func_78370_a(colour >>> 24, colour >> 16 & 0xFF, colour >> 8 & 0xFF, alphaOverride >= 0 ? alphaOverride : colour & 0xFF);
/* 376 */     if (hasBrightness)
/* 377 */       Tessellator.field_78398_a.func_78380_c(brightness);
/*     */   }
/*     */   
/*     */   public static void draw() {
/* 381 */     Tessellator.field_78398_a.func_78381_a();
/*     */   }
/*     */   
/*     */   public static abstract interface IVertexOperation
/*     */   {
/*     */     public abstract boolean load();
/*     */     
/*     */     public abstract void operate();
/*     */     
/*     */     public abstract int operationID();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/render/CCRenderState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */