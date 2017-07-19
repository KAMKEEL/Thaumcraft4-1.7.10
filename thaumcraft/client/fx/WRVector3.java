/*     */ package thaumcraft.client.fx;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.Vec3;
/*     */ 
/*     */ public class WRVector3
/*     */ {
/*     */   public float x;
/*     */   public float y;
/*     */   public float z;
/*     */   
/*     */   public WRVector3(double x, double y, double z)
/*     */   {
/*  15 */     this.x = ((float)x);
/*  16 */     this.y = ((float)y);
/*  17 */     this.z = ((float)z);
/*     */   }
/*     */   
/*     */ 
/*     */   public WRVector3(TileEntity tile)
/*     */   {
/*  23 */     this.x = (tile.field_145851_c + 0.5F);
/*  24 */     this.y = (tile.field_145848_d + 0.5F);
/*  25 */     this.z = (tile.field_145849_e + 0.5F);
/*     */   }
/*     */   
/*     */   public WRVector3(Entity entity)
/*     */   {
/*  30 */     this(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
/*     */   }
/*     */   
/*     */   public WRVector3 add(WRVector3 vec)
/*     */   {
/*  35 */     this.x += vec.x;
/*  36 */     this.y += vec.y;
/*  37 */     this.z += vec.z;
/*  38 */     return this;
/*     */   }
/*     */   
/*     */   public WRVector3 sub(WRVector3 vec)
/*     */   {
/*  43 */     this.x -= vec.x;
/*  44 */     this.y -= vec.y;
/*  45 */     this.z -= vec.z;
/*  46 */     return this;
/*     */   }
/*     */   
/*     */   public WRVector3 scale(float scale)
/*     */   {
/*  51 */     this.x *= scale;
/*  52 */     this.y *= scale;
/*  53 */     this.z *= scale;
/*  54 */     return this;
/*     */   }
/*     */   
/*     */   public WRVector3 scale(float scalex, float scaley, float scalez)
/*     */   {
/*  59 */     this.x *= scalex;
/*  60 */     this.y *= scaley;
/*  61 */     this.z *= scalez;
/*  62 */     return this;
/*     */   }
/*     */   
/*     */   public WRVector3 normalize()
/*     */   {
/*  67 */     float length = length();
/*  68 */     this.x /= length;
/*  69 */     this.y /= length;
/*  70 */     this.z /= length;
/*  71 */     return this;
/*     */   }
/*     */   
/*     */   public float length()
/*     */   {
/*  76 */     return (float)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
/*     */   }
/*     */   
/*     */   public float lengthPow2()
/*     */   {
/*  81 */     return this.x * this.x + this.y * this.y + this.z * this.z;
/*     */   }
/*     */   
/*     */   public WRVector3 copy()
/*     */   {
/*  86 */     return new WRVector3(this.x, this.y, this.z);
/*     */   }
/*     */   
/*     */   public static WRVector3 crossProduct(WRVector3 vec1, WRVector3 vec2)
/*     */   {
/*  91 */     return new WRVector3(vec1.y * vec2.z - vec1.z * vec2.y, vec1.z * vec2.x - vec1.x * vec2.z, vec1.x * vec2.y - vec1.y * vec2.x);
/*     */   }
/*     */   
/*     */   public static WRVector3 xCrossProduct(WRVector3 vec)
/*     */   {
/*  96 */     return new WRVector3(0.0D, vec.z, -vec.y);
/*     */   }
/*     */   
/*     */   public static WRVector3 zCrossProduct(WRVector3 vec)
/*     */   {
/* 101 */     return new WRVector3(-vec.y, vec.x, 0.0D);
/*     */   }
/*     */   
/*     */   public static float dotProduct(WRVector3 vec1, WRVector3 vec2)
/*     */   {
/* 106 */     return vec1.x * vec2.x + vec1.y * vec2.y + vec1.z * vec2.z;
/*     */   }
/*     */   
/*     */   public static float angle(WRVector3 vec1, WRVector3 vec2)
/*     */   {
/* 111 */     return anglePreNorm(vec1.copy().normalize(), vec2.copy().normalize());
/*     */   }
/*     */   
/*     */   public static float anglePreNorm(WRVector3 vec1, WRVector3 vec2)
/*     */   {
/* 116 */     return (float)Math.acos(dotProduct(vec1, vec2));
/*     */   }
/*     */   
/*     */   public WRVector3 rotate(float angle, WRVector3 axis)
/*     */   {
/* 121 */     return WRMat4.rotationMat(angle, axis).translate(this);
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 126 */     return "[" + this.x + "," + this.y + "," + this.z + "]";
/*     */   }
/*     */   
/*     */   public Vec3 toVec3D()
/*     */   {
/* 131 */     return Vec3.func_72443_a(this.x, this.y, this.z);
/*     */   }
/*     */   
/*     */   public static WRVector3 getPerpendicular(WRVector3 vec)
/*     */   {
/* 136 */     if (vec.z == 0.0F) {
/* 137 */       return zCrossProduct(vec);
/*     */     }
/* 139 */     return xCrossProduct(vec);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isZero()
/*     */   {
/* 145 */     return (this.x == 0.0F) && (this.y == 0.0F) && (this.z == 0.0F);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/WRVector3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */