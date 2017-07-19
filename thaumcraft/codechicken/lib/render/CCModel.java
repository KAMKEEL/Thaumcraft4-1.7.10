/*      */ package thaumcraft.codechicken.lib.render;
/*      */ 
/*      */ import java.io.BufferedReader;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.regex.Matcher;
/*      */ import java.util.regex.Pattern;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.resources.IResource;
/*      */ import net.minecraft.client.resources.IResourceManager;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import thaumcraft.codechicken.lib.lighting.LC;
/*      */ import thaumcraft.codechicken.lib.lighting.LightModel;
/*      */ import thaumcraft.codechicken.lib.render.uv.UV;
/*      */ import thaumcraft.codechicken.lib.render.uv.UVTransformation;
/*      */ import thaumcraft.codechicken.lib.vec.Cuboid6;
/*      */ import thaumcraft.codechicken.lib.vec.Transformation;
/*      */ import thaumcraft.codechicken.lib.vec.TransformationList;
/*      */ import thaumcraft.codechicken.lib.vec.Vector3;
/*      */ 
/*      */ public class CCModel implements CCRenderState.IVertexSource, thaumcraft.codechicken.lib.util.Copyable<CCModel>
/*      */ {
/*      */   public final int vertexMode;
/*      */   public final int vp;
/*      */   public Vertex5[] verts;
/*      */   
/*      */   private static class PositionNormalEntry
/*      */   {
/*      */     public Vector3 pos;
/*   39 */     public LinkedList<Vector3> normals = new LinkedList();
/*      */     
/*      */     public PositionNormalEntry(Vector3 position)
/*      */     {
/*   43 */       this.pos = position;
/*      */     }
/*      */     
/*      */     public boolean positionEqual(Vector3 v)
/*      */     {
/*   48 */       return (this.pos.x == v.x) && (this.pos.y == v.y) && (this.pos.z == v.z);
/*      */     }
/*      */     
/*      */     public PositionNormalEntry addNormal(Vector3 normal)
/*      */     {
/*   53 */       this.normals.add(normal);
/*   54 */       return this;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*   61 */   public ArrayList<Object> attributes = new ArrayList();
/*      */   
/*      */   protected CCModel(int vertexMode)
/*      */   {
/*   65 */     if ((vertexMode != 7) && (vertexMode != 4)) {
/*   66 */       throw new IllegalArgumentException("Models must be GL_QUADS or GL_TRIANGLES");
/*      */     }
/*   68 */     this.vertexMode = vertexMode;
/*   69 */     this.vp = (vertexMode == 7 ? 4 : 3);
/*      */   }
/*      */   
/*      */   public Vector3[] normals() {
/*   73 */     return (Vector3[])getAttributes(CCRenderState.normalAttrib);
/*      */   }
/*      */   
/*      */   public Vertex5[] getVertices()
/*      */   {
/*   78 */     return this.verts;
/*      */   }
/*      */   
/*      */   public <T> T getAttributes(CCRenderState.VertexAttribute<T> attr)
/*      */   {
/*   83 */     if (attr.attributeIndex < this.attributes.size()) {
/*   84 */       return (T)this.attributes.get(attr.attributeIndex);
/*      */     }
/*   86 */     return null;
/*      */   }
/*      */   
/*      */   public boolean hasAttribute(CCRenderState.VertexAttribute<?> attrib)
/*      */   {
/*   91 */     return (attrib.attributeIndex < this.attributes.size()) && (this.attributes.get(attrib.attributeIndex) != null);
/*      */   }
/*      */   
/*      */ 
/*      */   public void prepareVertex() {}
/*      */   
/*      */   public <T> T getOrAllocate(CCRenderState.VertexAttribute<T> attrib)
/*      */   {
/*   99 */     T array = getAttributes(attrib);
/*  100 */     if (array == null) {
/*  101 */       while (this.attributes.size() <= attrib.attributeIndex)
/*  102 */         this.attributes.add(null);
/*  103 */       this.attributes.set(attrib.attributeIndex, array = attrib.newArray(this.verts.length));
/*      */     }
/*  105 */     return array;
/*      */   }
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
/*      */   public CCModel generateBox(int i, double x1, double y1, double z1, double w, double h, double d, double tx, double ty, double tw, double th, double f)
/*      */   {
/*  127 */     double x2 = x1 + w;
/*  128 */     double y2 = y1 + h;
/*  129 */     double z2 = z1 + d;
/*  130 */     x1 /= f;x2 /= f;y1 /= f;y2 /= f;z1 /= f;z2 /= f;
/*      */     
/*      */ 
/*  133 */     double u1 = (tx + d + w) / tw;double v1 = (ty + d) / th;
/*  134 */     double u2 = (tx + d * 2.0D + w) / tw;double v2 = ty / th;
/*  135 */     this.verts[(i++)] = new Vertex5(x1, y1, z2, u1, v2);
/*  136 */     this.verts[(i++)] = new Vertex5(x1, y1, z1, u1, v1);
/*  137 */     this.verts[(i++)] = new Vertex5(x2, y1, z1, u2, v1);
/*  138 */     this.verts[(i++)] = new Vertex5(x2, y1, z2, u2, v2);
/*      */     
/*      */ 
/*  141 */     u1 = (tx + d) / tw;v1 = (ty + d) / th;
/*  142 */     u2 = (tx + d + w) / tw;v2 = ty / th;
/*  143 */     this.verts[(i++)] = new Vertex5(x2, y2, z2, u2, v2);
/*  144 */     this.verts[(i++)] = new Vertex5(x2, y2, z1, u2, v1);
/*  145 */     this.verts[(i++)] = new Vertex5(x1, y2, z1, u1, v1);
/*  146 */     this.verts[(i++)] = new Vertex5(x1, y2, z2, u1, v2);
/*      */     
/*      */ 
/*  149 */     u1 = (tx + d + w) / tw;v1 = (ty + d) / th;
/*  150 */     u2 = (tx + d) / tw;v2 = (ty + d + h) / th;
/*  151 */     this.verts[(i++)] = new Vertex5(x1, y2, z1, u2, v1);
/*  152 */     this.verts[(i++)] = new Vertex5(x2, y2, z1, u1, v1);
/*  153 */     this.verts[(i++)] = new Vertex5(x2, y1, z1, u1, v2);
/*  154 */     this.verts[(i++)] = new Vertex5(x1, y1, z1, u2, v2);
/*      */     
/*      */ 
/*  157 */     u1 = (tx + d * 2.0D + w * 2.0D) / tw;v1 = (ty + d) / th;
/*  158 */     u2 = (tx + d * 2.0D + w) / tw;v2 = (ty + d + h) / th;
/*  159 */     this.verts[(i++)] = new Vertex5(x1, y2, z2, u1, v1);
/*  160 */     this.verts[(i++)] = new Vertex5(x1, y1, z2, u1, v2);
/*  161 */     this.verts[(i++)] = new Vertex5(x2, y1, z2, u2, v2);
/*  162 */     this.verts[(i++)] = new Vertex5(x2, y2, z2, u2, v1);
/*      */     
/*      */ 
/*  165 */     u1 = (tx + d) / tw;v1 = (ty + d) / th;
/*  166 */     u2 = tx / tw;v2 = (ty + d + h) / th;
/*  167 */     this.verts[(i++)] = new Vertex5(x1, y2, z2, u2, v1);
/*  168 */     this.verts[(i++)] = new Vertex5(x1, y2, z1, u1, v1);
/*  169 */     this.verts[(i++)] = new Vertex5(x1, y1, z1, u1, v2);
/*  170 */     this.verts[(i++)] = new Vertex5(x1, y1, z2, u2, v2);
/*      */     
/*      */ 
/*  173 */     u1 = (tx + d * 2.0D + w) / tw;v1 = (ty + d) / th;
/*  174 */     u2 = (tx + d + w) / tw;v2 = (ty + d + h) / th;
/*  175 */     this.verts[(i++)] = new Vertex5(x2, y1, z2, u1, v2);
/*  176 */     this.verts[(i++)] = new Vertex5(x2, y1, z1, u2, v2);
/*  177 */     this.verts[(i++)] = new Vertex5(x2, y2, z1, u2, v1);
/*  178 */     this.verts[(i++)] = new Vertex5(x2, y2, z2, u1, v1);
/*      */     
/*  180 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CCModel generateBlock(int i, Cuboid6 bounds)
/*      */   {
/*  191 */     return generateBlock(i, bounds, 0);
/*      */   }
/*      */   
/*      */   public CCModel generateBlock(int i, Cuboid6 bounds, int mask)
/*      */   {
/*  196 */     return generateBlock(i, bounds.min.x, bounds.min.y, bounds.min.z, bounds.max.x, bounds.max.y, bounds.max.z, mask);
/*      */   }
/*      */   
/*      */   public CCModel generateBlock(int i, double x1, double y1, double z1, double x2, double y2, double z2)
/*      */   {
/*  201 */     return generateBlock(i, x1, y1, z1, x2, y2, z2, 0);
/*      */   }
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
/*      */   public CCModel generateBlock(int i, double x1, double y1, double z1, double x2, double y2, double z2, int mask)
/*      */   {
/*  220 */     if ((mask & 0x1) == 0) {
/*  221 */       double u1 = x1;double v1 = z1;
/*  222 */       double u2 = x2;double v2 = z2;
/*  223 */       this.verts[(i++)] = new Vertex5(x1, y1, z2, u1, v2);
/*  224 */       this.verts[(i++)] = new Vertex5(x1, y1, z1, u1, v1);
/*  225 */       this.verts[(i++)] = new Vertex5(x2, y1, z1, u2, v1);
/*  226 */       this.verts[(i++)] = new Vertex5(x2, y1, z2, u2, v2);
/*      */     }
/*      */     
/*  229 */     if ((mask & 0x2) == 0) {
/*  230 */       double u1 = x1 + 2.0D;double v1 = z1;
/*  231 */       double u2 = x2 + 2.0D;double v2 = z2;
/*  232 */       this.verts[(i++)] = new Vertex5(x2, y2, z2, u2, v2);
/*  233 */       this.verts[(i++)] = new Vertex5(x2, y2, z1, u2, v1);
/*  234 */       this.verts[(i++)] = new Vertex5(x1, y2, z1, u1, v1);
/*  235 */       this.verts[(i++)] = new Vertex5(x1, y2, z2, u1, v2);
/*      */     }
/*      */     
/*  238 */     if ((mask & 0x4) == 0) {
/*  239 */       double u1 = 1.0D - x1 + 4.0D;double v1 = 1.0D - y2;
/*  240 */       double u2 = 1.0D - x2 + 4.0D;double v2 = 1.0D - y1;
/*  241 */       this.verts[(i++)] = new Vertex5(x1, y1, z1, u1, v2);
/*  242 */       this.verts[(i++)] = new Vertex5(x1, y2, z1, u1, v1);
/*  243 */       this.verts[(i++)] = new Vertex5(x2, y2, z1, u2, v1);
/*  244 */       this.verts[(i++)] = new Vertex5(x2, y1, z1, u2, v2);
/*      */     }
/*      */     
/*  247 */     if ((mask & 0x8) == 0) {
/*  248 */       double u1 = x1 + 6.0D;double v1 = 1.0D - y2;
/*  249 */       double u2 = x2 + 6.0D;double v2 = 1.0D - y1;
/*  250 */       this.verts[(i++)] = new Vertex5(x2, y1, z2, u2, v2);
/*  251 */       this.verts[(i++)] = new Vertex5(x2, y2, z2, u2, v1);
/*  252 */       this.verts[(i++)] = new Vertex5(x1, y2, z2, u1, v1);
/*  253 */       this.verts[(i++)] = new Vertex5(x1, y1, z2, u1, v2);
/*      */     }
/*      */     
/*  256 */     if ((mask & 0x10) == 0) {
/*  257 */       double u1 = z1 + 8.0D;double v1 = 1.0D - y2;
/*  258 */       double u2 = z2 + 8.0D;double v2 = 1.0D - y1;
/*  259 */       this.verts[(i++)] = new Vertex5(x1, y1, z2, u2, v2);
/*  260 */       this.verts[(i++)] = new Vertex5(x1, y2, z2, u2, v1);
/*  261 */       this.verts[(i++)] = new Vertex5(x1, y2, z1, u1, v1);
/*  262 */       this.verts[(i++)] = new Vertex5(x1, y1, z1, u1, v2);
/*      */     }
/*      */     
/*  265 */     if ((mask & 0x20) == 0) {
/*  266 */       double u1 = 1.0D - z1 + 10.0D;double v1 = 1.0D - y2;
/*  267 */       double u2 = 1.0D - z2 + 10.0D;double v2 = 1.0D - y1;
/*  268 */       this.verts[(i++)] = new Vertex5(x2, y1, z1, u1, v2);
/*  269 */       this.verts[(i++)] = new Vertex5(x2, y2, z1, u1, v1);
/*  270 */       this.verts[(i++)] = new Vertex5(x2, y2, z2, u2, v1);
/*  271 */       this.verts[(i++)] = new Vertex5(x2, y1, z2, u2, v2);
/*      */     }
/*      */     
/*  274 */     return this;
/*      */   }
/*      */   
/*      */   public CCModel computeNormals()
/*      */   {
/*  279 */     return computeNormals(0, this.verts.length);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CCModel computeNormals(int start, int length)
/*      */   {
/*  291 */     if ((length % this.vp != 0) || (start % this.vp != 0)) {
/*  292 */       throw new IllegalArgumentException("Cannot generate normals across polygons");
/*      */     }
/*  294 */     Vector3[] normals = (Vector3[])getOrAllocate(CCRenderState.normalAttrib);
/*  295 */     for (int k = 0; k < length; k += this.vp)
/*      */     {
/*  297 */       int i = k + start;
/*  298 */       Vector3 diff1 = this.verts[(i + 1)].vec.copy().subtract(this.verts[i].vec);
/*  299 */       Vector3 diff2 = this.verts[(i + this.vp - 1)].vec.copy().subtract(this.verts[i].vec);
/*  300 */       normals[i] = diff1.crossProduct(diff2).normalize();
/*  301 */       for (int d = 1; d < this.vp; d++) {
/*  302 */         normals[(i + d)] = normals[i].copy();
/*      */       }
/*      */     }
/*  305 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CCModel computeLighting(LightModel light)
/*      */   {
/*  315 */     Vector3[] normals = normals();
/*  316 */     int[] colours = (int[])getAttributes(CCRenderState.colourAttrib);
/*  317 */     if (colours == null) {
/*  318 */       setColour(-1);
/*  319 */       colours = (int[])getAttributes(CCRenderState.colourAttrib);
/*      */     }
/*  321 */     for (int k = 0; k < this.verts.length; k++)
/*  322 */       colours[k] = light.apply(colours[k], normals[k]);
/*  323 */     return this;
/*      */   }
/*      */   
/*      */   public CCModel setColour(int c)
/*      */   {
/*  328 */     int[] colours = (int[])getOrAllocate(CCRenderState.colourAttrib);
/*  329 */     Arrays.fill(colours, c);
/*  330 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public CCModel computeLightCoords()
/*      */   {
/*  338 */     LC[] lcs = (LC[])getOrAllocate(CCRenderState.lightCoordAttrib);
/*  339 */     Vector3[] normals = normals();
/*  340 */     for (int i = 0; i < this.verts.length; i++)
/*  341 */       lcs[i] = new LC().compute(this.verts[i].vec, normals[i]);
/*  342 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CCModel smoothNormals()
/*      */   {
/*  351 */     ArrayList<PositionNormalEntry> map = new ArrayList();
/*  352 */     Vector3[] normals = normals();
/*  353 */     label108: for (int k = 0; k < this.verts.length; k++)
/*      */     {
/*  355 */       Vector3 vec = this.verts[k].vec;
/*  356 */       for (PositionNormalEntry e : map) {
/*  357 */         if (e.positionEqual(vec))
/*      */         {
/*  359 */           e.addNormal(normals[k]);
/*      */           break label108;
/*      */         }
/*      */       }
/*  363 */       map.add(new PositionNormalEntry(vec).addNormal(normals[k]));
/*      */     }
/*      */     
/*  366 */     for (PositionNormalEntry e : map)
/*      */     {
/*  368 */       if (e.normals.size() > 1)
/*      */       {
/*      */ 
/*  371 */         new_n = new Vector3();
/*  372 */         for (Vector3 n : e.normals) {
/*  373 */           new_n.add(n);
/*      */         }
/*  375 */         new_n.normalize();
/*  376 */         for (Vector3 n : e.normals)
/*  377 */           n.set(new_n);
/*      */       } }
/*      */     Vector3 new_n;
/*  380 */     return this;
/*      */   }
/*      */   
/*      */   public CCModel apply(Transformation t)
/*      */   {
/*  385 */     for (int k = 0; k < this.verts.length; k++) {
/*  386 */       this.verts[k].apply(t);
/*      */     }
/*  388 */     Vector3[] normals = normals();
/*  389 */     if (normals != null) {
/*  390 */       for (int k = 0; k < normals.length; k++)
/*  391 */         t.applyN(normals[k]);
/*      */     }
/*  393 */     return this;
/*      */   }
/*      */   
/*      */   public CCModel apply(UVTransformation uvt)
/*      */   {
/*  398 */     for (int k = 0; k < this.verts.length; k++) {
/*  399 */       this.verts[k].apply(uvt);
/*      */     }
/*  401 */     return this;
/*      */   }
/*      */   
/*      */   public CCModel expand(int extraVerts)
/*      */   {
/*  406 */     int newLen = this.verts.length + extraVerts;
/*  407 */     this.verts = ((Vertex5[])Arrays.copyOf(this.verts, newLen));
/*  408 */     for (int i = 0; i < this.attributes.size(); i++) {
/*  409 */       if (this.attributes.get(i) != null)
/*  410 */         this.attributes.set(i, CCRenderState.copyOf(CCRenderState.getAttribute(i), this.attributes.get(i), newLen));
/*      */     }
/*  412 */     return this;
/*      */   }
/*      */   
/*      */   public void render()
/*      */   {
/*  417 */     render(0, this.verts.length, new CCRenderState.IVertexOperation[] { null, null, null });
/*      */   }
/*      */   
/*      */   public void render(double x, double y, double z, double u, double v)
/*      */   {
/*  422 */     render(new CCRenderState.IVertexOperation[] { new Vector3(x, y, z).translation(), new thaumcraft.codechicken.lib.render.uv.UVTranslation(u, v) });
/*      */   }
/*      */   
/*      */   public void render(double x, double y, double z, UVTransformation u)
/*      */   {
/*  427 */     render(new CCRenderState.IVertexOperation[] { new Vector3(x, y, z).translation(), u });
/*      */   }
/*      */   
/*      */   public void render(Transformation t, double u, double v)
/*      */   {
/*  432 */     render(new CCRenderState.IVertexOperation[] { t, new thaumcraft.codechicken.lib.render.uv.UVTranslation(u, v) });
/*      */   }
/*      */   
/*      */   public void render(CCRenderState.IVertexOperation... ops)
/*      */   {
/*  437 */     render(0, this.verts.length, ops);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void render(int start, int end, CCRenderState.IVertexOperation... ops)
/*      */   {
/*  448 */     CCRenderState.setPipeline(this, start, end, ops);
/*  449 */     CCRenderState.render();
/*      */   }
/*      */   
/*      */   public static CCModel quadModel(int numVerts)
/*      */   {
/*  454 */     return newModel(7, numVerts);
/*      */   }
/*      */   
/*      */   public static CCModel triModel(int numVerts)
/*      */   {
/*  459 */     return newModel(4, numVerts);
/*      */   }
/*      */   
/*      */   public static CCModel newModel(int vertexMode, int numVerts)
/*      */   {
/*  464 */     CCModel model = newModel(vertexMode);
/*  465 */     model.verts = new Vertex5[numVerts];
/*  466 */     return model;
/*      */   }
/*      */   
/*      */   public static CCModel newModel(int vertexMode)
/*      */   {
/*  471 */     return new CCModel(vertexMode);
/*      */   }
/*      */   
/*      */   public static double[] parseDoubles(String s, String token)
/*      */   {
/*  476 */     String[] as = s.split(token);
/*  477 */     double[] values = new double[as.length];
/*  478 */     for (int i = 0; i < as.length; i++)
/*  479 */       values[i] = Double.parseDouble(as[i]);
/*  480 */     return values;
/*      */   }
/*      */   
/*      */   public static void illegalAssert(boolean b, String err)
/*      */   {
/*  485 */     if (!b) throw new IllegalArgumentException(err);
/*      */   }
/*      */   
/*      */   public static void assertMatch(Matcher m, String s)
/*      */   {
/*  490 */     m.reset(s);
/*  491 */     illegalAssert(m.matches(), "Malformed line: " + s);
/*      */   }
/*      */   
/*  494 */   private static final Pattern vertPattern = Pattern.compile("v(?: ([\\d\\.+-]+))+");
/*  495 */   private static final Pattern uvwPattern = Pattern.compile("vt(?: ([\\d\\.+-]+))+");
/*  496 */   private static final Pattern normalPattern = Pattern.compile("vn(?: ([\\d\\.+-]+))+");
/*  497 */   private static final Pattern polyPattern = Pattern.compile("f(?: ((?:\\d*)(?:/\\d*)?(?:/\\d*)?))+");
/*  498 */   public static final Matcher vertMatcher = vertPattern.matcher("");
/*  499 */   public static final Matcher uvwMatcher = uvwPattern.matcher("");
/*  500 */   public static final Matcher normalMatcher = normalPattern.matcher("");
/*  501 */   public static final Matcher polyMatcher = polyPattern.matcher("");
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Map<String, CCModel> parseObjModels(InputStream input, int vertexMode, Transformation coordSystem)
/*      */     throws IOException
/*      */   {
/*  513 */     if (coordSystem == null)
/*  514 */       coordSystem = new thaumcraft.codechicken.lib.vec.RedundantTransformation();
/*  515 */     int vp = vertexMode == 7 ? 4 : 3;
/*      */     
/*  517 */     HashMap<String, CCModel> modelMap = new HashMap();
/*  518 */     ArrayList<Vector3> verts = new ArrayList();
/*  519 */     ArrayList<Vector3> uvs = new ArrayList();
/*  520 */     ArrayList<Vector3> normals = new ArrayList();
/*  521 */     ArrayList<int[]> polys = new ArrayList();
/*  522 */     String modelName = "unnamed";
/*      */     
/*  524 */     BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(input));
/*      */     
/*      */     String line;
/*  527 */     while ((line = reader.readLine()) != null)
/*      */     {
/*  529 */       line = line.replaceAll("\\s+", " ").trim();
/*  530 */       if ((!line.startsWith("#")) && (line.length() != 0))
/*      */       {
/*      */ 
/*  533 */         if (line.startsWith("v "))
/*      */         {
/*  535 */           assertMatch(vertMatcher, line);
/*  536 */           double[] values = parseDoubles(line.substring(2), " ");
/*  537 */           illegalAssert(values.length >= 3, "Vertices must have x, y and z components");
/*  538 */           Vector3 vert = new Vector3(values[0], values[1], values[2]);
/*  539 */           coordSystem.apply(vert);
/*  540 */           verts.add(vert);
/*      */ 
/*      */         }
/*  543 */         else if (line.startsWith("vt "))
/*      */         {
/*  545 */           assertMatch(uvwMatcher, line);
/*  546 */           double[] values = parseDoubles(line.substring(3), " ");
/*  547 */           illegalAssert(values.length >= 2, "Tex Coords must have u, and v components");
/*  548 */           uvs.add(new Vector3(values[0], 1.0D - values[1], 0.0D));
/*      */ 
/*      */         }
/*  551 */         else if (line.startsWith("vn "))
/*      */         {
/*  553 */           assertMatch(normalMatcher, line);
/*  554 */           double[] values = parseDoubles(line.substring(3), " ");
/*  555 */           illegalAssert(values.length >= 3, "Normals must have x, y and z components");
/*  556 */           Vector3 norm = new Vector3(values[0], values[1], values[2]).normalize();
/*  557 */           coordSystem.applyN(norm);
/*  558 */           normals.add(norm);
/*      */         }
/*      */         else {
/*  561 */           if (line.startsWith("f "))
/*      */           {
/*  563 */             assertMatch(polyMatcher, line);
/*  564 */             String[] av = line.substring(2).split(" ");
/*  565 */             illegalAssert(av.length >= 3, "Polygons must have at least 3 vertices");
/*  566 */             int[][] polyVerts = new int[av.length][3];
/*  567 */             for (int i = 0; i < av.length; i++)
/*      */             {
/*  569 */               String[] as = av[i].split("/");
/*  570 */               for (int p = 0; p < as.length; p++)
/*  571 */                 if (as[p].length() > 0)
/*  572 */                   polyVerts[i][p] = Integer.parseInt(as[p]);
/*      */             }
/*  574 */             if (vp == 3) {
/*  575 */               triangulate(polys, polyVerts);
/*      */             } else
/*  577 */               quadulate(polys, polyVerts);
/*      */           }
/*  579 */           if (line.startsWith("g "))
/*      */           {
/*  581 */             if (!polys.isEmpty())
/*      */             {
/*  583 */               modelMap.put(modelName, createModel(verts, uvs, normals, vertexMode, polys));
/*  584 */               polys.clear();
/*      */             }
/*  586 */             modelName = line.substring(2);
/*      */           }
/*      */         } }
/*      */     }
/*  590 */     if (!polys.isEmpty()) {
/*  591 */       modelMap.put(modelName, createModel(verts, uvs, normals, vertexMode, polys));
/*      */     }
/*  593 */     return modelMap;
/*      */   }
/*      */   
/*      */   public static void triangulate(List<int[]> polys, int[][] polyVerts)
/*      */   {
/*  598 */     for (int i = 2; i < polyVerts.length; i++)
/*      */     {
/*  600 */       polys.add(polyVerts[0]);
/*  601 */       polys.add(polyVerts[i]);
/*  602 */       polys.add(polyVerts[(i - 1)]);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void quadulate(List<int[]> polys, int[][] polyVerts)
/*      */   {
/*  608 */     if (polyVerts.length == 4)
/*      */     {
/*  610 */       polys.add(polyVerts[0]);
/*  611 */       polys.add(polyVerts[3]);
/*  612 */       polys.add(polyVerts[2]);
/*  613 */       polys.add(polyVerts[1]);
/*      */     }
/*      */     else
/*      */     {
/*  617 */       for (int i = 2; i < polyVerts.length; i++)
/*      */       {
/*  619 */         polys.add(polyVerts[0]);
/*  620 */         polys.add(polyVerts[i]);
/*  621 */         polys.add(polyVerts[(i - 1)]);
/*  622 */         polys.add(polyVerts[(i - 1)]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Map<String, CCModel> parseObjModels(ResourceLocation res)
/*      */   {
/*  634 */     return parseObjModels(res, 4, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Map<String, CCModel> parseObjModels(ResourceLocation res, Transformation coordSystem)
/*      */   {
/*      */     try
/*      */     {
/*  646 */       return parseObjModels(Minecraft.func_71410_x().func_110442_L().func_110536_a(res).func_110527_b(), 4, coordSystem);
/*      */ 
/*      */     }
/*      */     catch (IOException e)
/*      */     {
/*      */ 
/*  652 */       throw new RuntimeException("failed to load model: " + res, e);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Map<String, CCModel> parseObjModels(ResourceLocation res, int vertexMode, Transformation coordSystem)
/*      */   {
/*      */     try
/*      */     {
/*  667 */       return parseObjModels(Minecraft.func_71410_x().func_110442_L().func_110536_a(res).func_110527_b(), vertexMode, coordSystem);
/*      */ 
/*      */     }
/*      */     catch (Exception e)
/*      */     {
/*      */ 
/*  673 */       throw new RuntimeException("failed to load model: " + res, e);
/*      */     }
/*      */   }
/*      */   
/*      */   public static CCModel createModel(List<Vector3> verts, List<Vector3> uvs, List<Vector3> normals, int vertexMode, List<int[]> polys)
/*      */   {
/*  679 */     int vp = vertexMode == 7 ? 4 : 3;
/*  680 */     if ((polys.size() < vp) || (polys.size() % vp != 0)) {
/*  681 */       throw new IllegalArgumentException("Invalid number of vertices for model: " + polys.size());
/*      */     }
/*  683 */     boolean hasNormals = ((int[])polys.get(0))[2] > 0;
/*  684 */     CCModel model = newModel(vertexMode, polys.size());
/*  685 */     if (hasNormals) {
/*  686 */       model.getOrAllocate(CCRenderState.normalAttrib);
/*      */     }
/*  688 */     for (int i = 0; i < polys.size(); i++)
/*      */     {
/*  690 */       int[] ai = (int[])polys.get(i);
/*  691 */       Vector3 vert = ((Vector3)verts.get(ai[0] - 1)).copy();
/*  692 */       Vector3 uv = ai[1] <= 0 ? new Vector3() : ((Vector3)uvs.get(ai[1] - 1)).copy();
/*  693 */       if (ai[2] > 0 != hasNormals) {
/*  694 */         throw new IllegalArgumentException("Normals are an all or nothing deal here.");
/*      */       }
/*  696 */       model.verts[i] = new Vertex5(vert, uv.x, uv.y);
/*  697 */       if (hasNormals) {
/*  698 */         model.normals()[i] = ((Vector3)normals.get(ai[2] - 1)).copy();
/*      */       }
/*      */     }
/*  701 */     return model;
/*      */   }
/*      */   
/*      */   private static <T> int addIndex(List<T> list, T elem)
/*      */   {
/*  706 */     int i = list.indexOf(elem) + 1;
/*  707 */     if (i == 0) {
/*  708 */       list.add(elem);
/*  709 */       i = list.size();
/*      */     }
/*  711 */     return i;
/*      */   }
/*      */   
/*      */   private static String clean(double d) {
/*  715 */     return d == (int)d ? Integer.toString((int)d) : Double.toString(d);
/*      */   }
/*      */   
/*      */   public static void exportObj(Map<String, CCModel> models, PrintWriter p)
/*      */   {
/*  720 */     List<Vector3> verts = new ArrayList();
/*  721 */     List<UV> uvs = new ArrayList();
/*  722 */     List<Vector3> normals = new ArrayList();
/*  723 */     List<int[]> polys = new ArrayList();
/*  724 */     for (Map.Entry<String, CCModel> e : models.entrySet()) {
/*  725 */       p.println("g " + (String)e.getKey());
/*  726 */       CCModel m = (CCModel)e.getValue();
/*      */       
/*  728 */       int vStart = verts.size();
/*  729 */       int uStart = uvs.size();
/*  730 */       int nStart = normals.size();
/*  731 */       boolean hasNormals = m.normals() != null;
/*  732 */       polys.clear();
/*      */       
/*  734 */       for (int i = 0; i < m.verts.length; i++) {
/*  735 */         int[] ia = new int[hasNormals ? 3 : 2];
/*  736 */         ia[0] = addIndex(verts, m.verts[i].vec);
/*  737 */         ia[1] = addIndex(uvs, m.verts[i].uv);
/*  738 */         if (hasNormals)
/*  739 */           ia[2] = addIndex(normals, m.normals()[i]);
/*  740 */         polys.add(ia);
/*      */       }
/*      */       
/*  743 */       if (vStart < verts.size()) {
/*  744 */         p.println();
/*  745 */         for (int i = vStart; i < verts.size(); i++) {
/*  746 */           Vector3 v = (Vector3)verts.get(i);
/*  747 */           p.format("v %s %s %s\n", new Object[] { clean(v.x), clean(v.y), clean(v.z) });
/*      */         }
/*      */       }
/*  750 */       if (uStart < uvs.size()) {
/*  751 */         p.println();
/*  752 */         for (int i = uStart; i < uvs.size(); i++) {
/*  753 */           UV uv = (UV)uvs.get(i);
/*  754 */           p.format("vt %s %s\n", new Object[] { clean(uv.u), clean(uv.v) });
/*      */         }
/*      */       }
/*  757 */       if (nStart < normals.size()) {
/*  758 */         p.println();
/*  759 */         for (int i = nStart; i < normals.size(); i++) {
/*  760 */           Vector3 n = (Vector3)normals.get(i);
/*  761 */           p.format("vn %s %s %s\n", new Object[] { clean(n.x), clean(n.y), clean(n.z) });
/*      */         }
/*      */       }
/*      */       
/*  765 */       p.println();
/*  766 */       for (int i = 0; i < polys.size(); i++) {
/*  767 */         if (i % m.vp == 0)
/*  768 */           p.format("f", new Object[0]);
/*  769 */         int[] ia = (int[])polys.get(i);
/*  770 */         if (hasNormals) {
/*  771 */           p.format(" %d/%d/%d", new Object[] { Integer.valueOf(ia[0]), Integer.valueOf(ia[1]), Integer.valueOf(ia[2]) });
/*      */         } else
/*  773 */           p.format(" %d/%d", new Object[] { Integer.valueOf(ia[0]), Integer.valueOf(ia[1]) });
/*  774 */         if (i % m.vp == m.vp - 1) {
/*  775 */           p.println();
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public CCModel shrinkUVs(double d)
/*      */   {
/*  786 */     for (int k = 0; k < this.verts.length; k += this.vp)
/*      */     {
/*  788 */       UV uv = new UV();
/*  789 */       for (int i = 0; i < this.vp; i++)
/*      */       {
/*  791 */         uv.add(this.verts[(k + i)].uv);
/*      */       }
/*  793 */       uv.multiply(1.0D / this.vp);
/*  794 */       for (int i = 0; i < this.vp; i++)
/*      */       {
/*  796 */         Vertex5 vert = this.verts[(k + i)];
/*  797 */         vert.uv.u += (vert.uv.u < uv.u ? d : -d);
/*  798 */         vert.uv.v += (vert.uv.v < uv.v ? d : -d);
/*      */       }
/*      */     }
/*  801 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CCModel sidedCopy(int side1, int side2, Vector3 point)
/*      */   {
/*  812 */     CCModel model = newModel(this.vertexMode, this.verts.length);
/*  813 */     copy(this, 0, model, 0, model.verts.length);
/*  814 */     return model.apply(new TransformationList(new Transformation[] { (Transformation)thaumcraft.codechicken.lib.vec.Rotation.sideRotations[side1].inverse(), thaumcraft.codechicken.lib.vec.Rotation.sideRotations[side2] }).at(point));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void copy(CCModel src, int srcpos, CCModel dst, int destpos, int length)
/*      */   {
/*  822 */     for (int k = 0; k < length; k++) {
/*  823 */       dst.verts[(destpos + k)] = src.verts[(srcpos + k)].copy();
/*      */     }
/*  825 */     for (int i = 0; i < src.attributes.size(); i++) {
/*  826 */       if (src.attributes.get(i) != null) {
/*  827 */         CCRenderState.arrayCopy(src.attributes.get(i), srcpos, dst.getOrAllocate(CCRenderState.getAttribute(i)), destpos, length);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void generateSidedModels(CCModel[] models, int side, Vector3 point)
/*      */   {
/*  838 */     for (int s = 0; s < 6; s++)
/*      */     {
/*  840 */       if (s != side)
/*      */       {
/*      */ 
/*  843 */         models[s] = models[side].sidedCopy(side, s, point);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void generateSidedModelsH(CCModel[] models, int side, Vector3 point)
/*      */   {
/*  855 */     for (int s = 2; s < 6; s++)
/*      */     {
/*  857 */       if (s != side)
/*      */       {
/*      */ 
/*  860 */         models[s] = models[side].sidedCopy(side, s, point);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public CCModel backfacedCopy() {
/*  866 */     return generateBackface(this, 0, copy(), 0, this.verts.length);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static CCModel generateBackface(CCModel src, int srcpos, CCModel dst, int destpos, int length)
/*      */   {
/*  875 */     int vp = src.vp;
/*  876 */     if ((srcpos % vp != 0) || (destpos % vp != 0) || (length % vp != 0)) {
/*  877 */       throw new IllegalArgumentException("Vertices do not align with polygons");
/*      */     }
/*  879 */     int[][] o = { { 0, 0 }, { 1, vp - 1 }, { 2, vp - 2 }, { 3, vp - 3 } };
/*  880 */     for (int i = 0; i < length; i++)
/*      */     {
/*  882 */       int b = i / vp * vp;
/*  883 */       int d = i % vp;
/*  884 */       int di = destpos + b + o[d][1];
/*  885 */       int si = srcpos + b + o[d][0];
/*  886 */       dst.verts[di] = src.verts[si].copy();
/*  887 */       for (int a = 0; a < src.attributes.size(); a++) {
/*  888 */         if (src.attributes.get(a) != null)
/*  889 */           CCRenderState.arrayCopy(src.attributes.get(a), si, dst.getOrAllocate(CCRenderState.getAttribute(a)), di, 1);
/*      */       }
/*  891 */       if ((dst.normals() != null) && (dst.normals()[di] != null))
/*  892 */         dst.normals()[di].negate();
/*      */     }
/*  894 */     return dst;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CCModel generateSidedParts(int side, Vector3 point)
/*      */   {
/*  903 */     if (this.verts.length % (6 * this.vp) != 0)
/*  904 */       throw new IllegalArgumentException("Invalid number of vertices for sided part generation");
/*  905 */     int length = this.verts.length / 6;
/*      */     
/*  907 */     for (int s = 0; s < 6; s++)
/*      */     {
/*  909 */       if (s != side)
/*      */       {
/*      */ 
/*  912 */         generateSidedPart(side, s, point, length * side, length * s, length);
/*      */       }
/*      */     }
/*  915 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CCModel generateSidedPartsH(int side, Vector3 point)
/*      */   {
/*  924 */     if (this.verts.length % (4 * this.vp) != 0)
/*  925 */       throw new IllegalArgumentException("Invalid number of vertices for sided part generation");
/*  926 */     int length = this.verts.length / 4;
/*      */     
/*  928 */     for (int s = 2; s < 6; s++)
/*      */     {
/*  930 */       if (s != side)
/*      */       {
/*      */ 
/*  933 */         generateSidedPart(side, s, point, length * (side - 2), length * (s - 2), length);
/*      */       }
/*      */     }
/*  936 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public CCModel generateSidedPart(int side1, int side2, Vector3 point, int srcpos, int destpos, int length)
/*      */   {
/*  944 */     return apply(new TransformationList(new Transformation[] { (Transformation)thaumcraft.codechicken.lib.vec.Rotation.sideRotations[side1].inverse(), thaumcraft.codechicken.lib.vec.Rotation.sideRotations[side2] }).at(point), srcpos, destpos, length);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public CCModel apply(Transformation t, int srcpos, int destpos, int length)
/*      */   {
/*  952 */     for (int k = 0; k < length; k++)
/*      */     {
/*  954 */       this.verts[(destpos + k)] = this.verts[(srcpos + k)].copy();
/*  955 */       this.verts[(destpos + k)].vec.apply(t);
/*      */     }
/*      */     
/*  958 */     Vector3[] normals = normals();
/*  959 */     if (normals != null) {
/*  960 */       for (int k = 0; k < length; k++) {
/*  961 */         normals[(destpos + k)] = normals[(srcpos + k)].copy();
/*  962 */         t.applyN(normals[(destpos + k)]);
/*      */       }
/*      */     }
/*  965 */     return this;
/*      */   }
/*      */   
/*      */   public static CCModel combine(Collection<CCModel> models)
/*      */   {
/*  970 */     if (models.isEmpty()) {
/*  971 */       return null;
/*      */     }
/*  973 */     int numVerts = 0;
/*  974 */     int vertexMode = -1;
/*  975 */     for (CCModel model : models)
/*      */     {
/*  977 */       if (vertexMode == -1)
/*  978 */         vertexMode = model.vertexMode;
/*  979 */       if (vertexMode != model.vertexMode) {
/*  980 */         throw new IllegalArgumentException("Cannot combine models with different vertex modes");
/*      */       }
/*  982 */       numVerts += model.verts.length;
/*      */     }
/*      */     
/*  985 */     CCModel c_model = newModel(vertexMode, numVerts);
/*  986 */     int i = 0;
/*  987 */     for (CCModel model : models)
/*      */     {
/*  989 */       copy(model, 0, c_model, i, model.verts.length);
/*  990 */       i += model.verts.length;
/*      */     }
/*      */     
/*  993 */     return c_model;
/*      */   }
/*      */   
/*      */   public CCModel twoFacedCopy()
/*      */   {
/*  998 */     CCModel model = newModel(this.vertexMode, this.verts.length * 2);
/*  999 */     copy(this, 0, model, 0, this.verts.length);
/* 1000 */     return generateBackface(model, 0, model, this.verts.length, this.verts.length);
/*      */   }
/*      */   
/*      */   public CCModel copy()
/*      */   {
/* 1005 */     CCModel model = newModel(this.vertexMode, this.verts.length);
/* 1006 */     copy(this, 0, model, 0, this.verts.length);
/* 1007 */     return model;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Vector3 collapse()
/*      */   {
/* 1015 */     Vector3 v = new Vector3();
/* 1016 */     for (Vertex5 vert : this.verts)
/* 1017 */       v.add(vert.vec);
/* 1018 */     v.multiply(1.0D / this.verts.length);
/* 1019 */     return v;
/*      */   }
/*      */   
/*      */   public CCModel zOffset(Cuboid6 offsets)
/*      */   {
/* 1024 */     for (int k = 0; k < this.verts.length; k++)
/*      */     {
/* 1026 */       Vertex5 vert = this.verts[k];
/* 1027 */       Vector3 normal = normals()[k];
/* 1028 */       switch (findSide(normal))
/*      */       {
/*      */       case 0: 
/* 1031 */         vert.vec.y += offsets.min.y;
/* 1032 */         break;
/*      */       case 1: 
/* 1034 */         vert.vec.y += offsets.max.y;
/* 1035 */         break;
/*      */       case 2: 
/* 1037 */         vert.vec.z += offsets.min.z;
/* 1038 */         break;
/*      */       case 3: 
/* 1040 */         vert.vec.z += offsets.max.z;
/* 1041 */         break;
/*      */       case 4: 
/* 1043 */         vert.vec.x += offsets.min.x;
/* 1044 */         break;
/*      */       case 5: 
/* 1046 */         vert.vec.x += offsets.max.x;
/*      */       }
/*      */       
/*      */     }
/* 1050 */     return this;
/*      */   }
/*      */   
/*      */   public static int findSide(Vector3 normal)
/*      */   {
/* 1055 */     if (normal.y <= -0.99D) return 0;
/* 1056 */     if (normal.y >= 0.99D) return 1;
/* 1057 */     if (normal.z <= -0.99D) return 2;
/* 1058 */     if (normal.z >= 0.99D) return 3;
/* 1059 */     if (normal.x <= -0.99D) return 4;
/* 1060 */     if (normal.x >= 0.99D) return 5;
/* 1061 */     return -1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Cuboid6 bounds()
/*      */   {
/* 1069 */     Vector3 vec1 = this.verts[0].vec;
/* 1070 */     Cuboid6 c = new Cuboid6(vec1.copy(), vec1.copy());
/* 1071 */     for (int i = 1; i < this.verts.length; i++)
/* 1072 */       c.enclose(this.verts[i].vec);
/* 1073 */     return c;
/*      */   }
/*      */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/render/CCModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */