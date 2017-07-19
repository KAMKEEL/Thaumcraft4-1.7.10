/*     */ package thaumcraft.codechicken.lib.vec;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.MathContext;
/*     */ import java.math.RoundingMode;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.codechicken.lib.math.MathHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Rotation
/*     */   extends Transformation
/*     */ {
/*  21 */   public static Transformation[] quarterRotations = { new RedundantTransformation(), new VariableTransformation(new Matrix4(0.0D, 0.0D, -1.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D))new VariableTransformationnew Matrix4
/*     */   {
/*     */     public void apply(Vector3 vec)
/*     */     {
/*  25 */       double d1 = vec.x;double d2 = vec.z;
/*  26 */       vec.x = (-d2);vec.z = d1; }
/*     */     
/*  28 */     public Transformation inverse() { return Rotation.quarterRotations[3];
/*     */     }
/*  21 */   }, new VariableTransformation(new Matrix4(-1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, -1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D))new VariableTransformationnew Matrix4
/*     */   {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void apply(Vector3 vec)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*  32 */       vec.x = (-vec.x);vec.z = (-vec.z); }
/*     */     
/*  34 */     public Transformation inverse() { return this;
/*     */     }
/*  21 */   }, new VariableTransformation(new Matrix4(0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, -1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D))
/*     */   {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void apply(Vector3 vec)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  38 */       double d1 = vec.x;double d2 = vec.z;
/*  39 */       vec.x = d2;vec.z = (-d1); }
/*     */     
/*  41 */     public Transformation inverse() { return Rotation.quarterRotations[1];
/*     */     }
/*  21 */   } };
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
/*  45 */   public static Transformation[] sideRotations = { new RedundantTransformation(), new VariableTransformation(new Matrix4(1.0D, 0.0D, 0.0D, 0.0D, 0.0D, -1.0D, 0.0D, 0.0D, 0.0D, 0.0D, -1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D))new VariableTransformationnew Matrix4
/*     */   {
/*     */     public void apply(Vector3 vec)
/*     */     {
/*  49 */       vec.y = (-vec.y);vec.z = (-vec.z); }
/*     */     
/*  51 */     public Transformation inverse() { return this;
/*     */     }
/*  45 */   }, new VariableTransformation(new Matrix4(1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, -1.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D))new VariableTransformationnew Matrix4
/*     */   {
/*     */ 
/*     */ 
/*     */ 
/*     */     public void apply(Vector3 vec)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*  55 */       double d1 = vec.y;double d2 = vec.z;
/*  56 */       vec.y = (-d2);vec.z = d1; }
/*     */     
/*  58 */     public Transformation inverse() { return Rotation.sideRotations[3];
/*     */     }
/*  45 */   }, new VariableTransformation(new Matrix4(1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, -1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D))new VariableTransformationnew Matrix4
/*     */   {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void apply(Vector3 vec)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  62 */       double d1 = vec.y;double d2 = vec.z;
/*  63 */       vec.y = d2;vec.z = (-d1); }
/*     */     
/*  65 */     public Transformation inverse() { return Rotation.sideRotations[2];
/*     */     }
/*  45 */   }, new VariableTransformation(new Matrix4(0.0D, 1.0D, 0.0D, 0.0D, -1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D))new VariableTransformationnew Matrix4
/*     */   {
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
/*     */     public void apply(Vector3 vec)
/*     */     {
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
/*  69 */       double d0 = vec.x;double d1 = vec.y;
/*  70 */       vec.x = d1;vec.y = (-d0); }
/*     */     
/*  72 */     public Transformation inverse() { return Rotation.sideRotations[5];
/*     */     }
/*  45 */   }, new VariableTransformation(new Matrix4(0.0D, -1.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D))
/*     */   {
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
/*     */     public void apply(Vector3 vec)
/*     */     {
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
/*  76 */       double d0 = vec.x;double d1 = vec.y;
/*  77 */       vec.x = (-d1);vec.y = d0; }
/*     */     
/*  79 */     public Transformation inverse() { return Rotation.sideRotations[4];
/*     */     }
/*  45 */   } };
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
/*  83 */   public static Vector3[] axes = { new Vector3(0.0D, -1.0D, 0.0D), new Vector3(0.0D, 1.0D, 0.0D), new Vector3(0.0D, 0.0D, -1.0D), new Vector3(0.0D, 0.0D, 1.0D), new Vector3(-1.0D, 0.0D, 0.0D), new Vector3(1.0D, 0.0D, 0.0D) };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  91 */   public static int[] sideRotMap = { 3, 4, 2, 5, 3, 5, 2, 4, 1, 5, 0, 4, 1, 4, 0, 5, 1, 2, 0, 3, 1, 3, 0, 2 };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  99 */   public static int[] rotSideMap = { -1, -1, 2, 0, 1, 3, -1, -1, 2, 0, 3, 1, 2, 0, -1, -1, 3, 1, 2, 0, -1, -1, 1, 3, 2, 0, 1, 3, -1, -1, 2, 0, 3, 1, -1, -1 };
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
/* 110 */   public static int[] sideRotOffsets = { 0, 2, 2, 0, 1, 3 };
/*     */   public double angle;
/*     */   
/*     */   public static int rotateSide(int s, int r) {
/* 114 */     return sideRotMap[(s << 2 | r)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int rotationTo(int s1, int s2)
/*     */   {
/* 122 */     if ((s1 & 0x6) == (s2 & 0x6))
/* 123 */       throw new IllegalArgumentException("Faces " + s1 + " and " + s2 + " are opposites");
/* 124 */     return rotSideMap[(s1 * 6 + s2)];
/*     */   }
/*     */   
/*     */ 
/*     */   public Vector3 axis;
/*     */   
/*     */   private Quat quat;
/*     */   
/*     */   public static int getSidedRotation(EntityPlayer player, int side)
/*     */   {
/* 134 */     Vector3 look = new Vector3(player.func_70676_i(1.0F));
/* 135 */     double max = 0.0D;
/* 136 */     int maxr = 0;
/* 137 */     for (int r = 0; r < 4; r++)
/*     */     {
/* 139 */       Vector3 axis = axes[rotateSide(side ^ 0x1, r)];
/* 140 */       double d = look.scalarProject(axis);
/* 141 */       if (d > max)
/*     */       {
/* 143 */         max = d;
/* 144 */         maxr = r;
/*     */       }
/*     */     }
/* 147 */     return maxr;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Transformation sideOrientation(int s, int r)
/*     */   {
/* 155 */     return quarterRotations[((r + sideRotOffsets[s]) % 4)].with(sideRotations[s]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int getSideFromLookAngle(EntityLivingBase entity)
/*     */   {
/* 164 */     Vector3 look = new Vector3(entity.func_70676_i(1.0F));
/* 165 */     double max = 0.0D;
/* 166 */     int maxs = 0;
/* 167 */     for (int s = 0; s < 6; s++) {
/* 168 */       double d = look.scalarProject(axes[s]);
/* 169 */       if (d > max) {
/* 170 */         max = d;
/* 171 */         maxs = s;
/*     */       }
/*     */     }
/* 174 */     return maxs;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Rotation(double angle, Vector3 axis)
/*     */   {
/* 184 */     this.angle = angle;
/* 185 */     this.axis = axis;
/*     */   }
/*     */   
/*     */   public Rotation(double angle, double x, double y, double z)
/*     */   {
/* 190 */     this(angle, new Vector3(x, y, z));
/*     */   }
/*     */   
/*     */   public Rotation(Quat quat)
/*     */   {
/* 195 */     this.quat = quat;
/*     */     
/* 197 */     this.angle = (Math.acos(quat.s) * 2.0D);
/* 198 */     if (this.angle == 0.0D)
/*     */     {
/* 200 */       this.axis = new Vector3(0.0D, 1.0D, 0.0D);
/*     */     }
/*     */     else
/*     */     {
/* 204 */       double sa = Math.sin(this.angle * 0.5D);
/* 205 */       this.axis = new Vector3(quat.x / sa, quat.y / sa, quat.z / sa);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void apply(Vector3 vec)
/*     */   {
/* 212 */     if (this.quat == null) {
/* 213 */       this.quat = Quat.aroundAxis(this.axis, this.angle);
/*     */     }
/* 215 */     vec.rotate(this.quat);
/*     */   }
/*     */   
/*     */ 
/*     */   public void applyN(Vector3 normal)
/*     */   {
/* 221 */     apply(normal);
/*     */   }
/*     */   
/*     */ 
/*     */   public void apply(Matrix4 mat)
/*     */   {
/* 227 */     mat.rotate(this.angle, this.axis);
/*     */   }
/*     */   
/*     */   public Quat toQuat()
/*     */   {
/* 232 */     if (this.quat == null)
/* 233 */       this.quat = Quat.aroundAxis(this.axis, this.angle);
/* 234 */     return this.quat;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void glApply()
/*     */   {
/* 241 */     GL11.glRotatef((float)(this.angle * 57.29577951308232D), (float)this.axis.x, (float)this.axis.y, (float)this.axis.z);
/*     */   }
/*     */   
/*     */ 
/*     */   public Transformation inverse()
/*     */   {
/* 247 */     return new Rotation(-this.angle, this.axis);
/*     */   }
/*     */   
/*     */   public Transformation merge(Transformation next)
/*     */   {
/* 252 */     if ((next instanceof Rotation)) {
/* 253 */       Rotation r = (Rotation)next;
/* 254 */       if (r.axis.equalsT(this.axis)) {
/* 255 */         return new Rotation(this.angle + r.angle, this.axis);
/*     */       }
/* 257 */       return new Rotation(toQuat().copy().multiply(r.toQuat()));
/*     */     }
/*     */     
/* 260 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isRedundant()
/*     */   {
/* 265 */     return MathHelper.between(-1.0E-5D, this.angle, 1.0E-5D);
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 271 */     MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
/* 272 */     return "Rotation(" + new BigDecimal(this.angle, cont) + ", " + new BigDecimal(this.axis.x, cont) + ", " + new BigDecimal(this.axis.y, cont) + ", " + new BigDecimal(this.axis.z, cont) + ")";
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/vec/Rotation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */