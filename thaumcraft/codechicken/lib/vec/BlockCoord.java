/*     */ package thaumcraft.codechicken.lib.vec;
/*     */ 
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import thaumcraft.codechicken.lib.math.MathHelper;
/*     */ import thaumcraft.codechicken.lib.util.Copyable;
/*     */ 
/*     */ public class BlockCoord implements Comparable<BlockCoord>, Copyable<BlockCoord>
/*     */ {
/*     */   public int x;
/*     */   public int y;
/*     */   public int z;
/*     */   
/*     */   public BlockCoord(int x, int y, int z)
/*     */   {
/*  15 */     this.x = x;
/*  16 */     this.y = y;
/*  17 */     this.z = z;
/*     */   }
/*     */   
/*     */   public BlockCoord(Vector3 v)
/*     */   {
/*  22 */     this(MathHelper.floor_double(v.x), MathHelper.floor_double(v.y), MathHelper.floor_double(v.z));
/*     */   }
/*     */   
/*     */   public BlockCoord(TileEntity tile)
/*     */   {
/*  27 */     this(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
/*     */   }
/*     */   
/*     */   public BlockCoord(int[] ia)
/*     */   {
/*  32 */     this(ia[0], ia[1], ia[2]);
/*     */   }
/*     */   
/*     */ 
/*     */   public BlockCoord() {}
/*     */   
/*     */ 
/*     */   public static BlockCoord fromAxes(int[] ia)
/*     */   {
/*  41 */     return new BlockCoord(ia[2], ia[0], ia[1]);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/*  47 */     if (!(obj instanceof BlockCoord))
/*  48 */       return false;
/*  49 */     BlockCoord o2 = (BlockCoord)obj;
/*  50 */     return (this.x == o2.x) && (this.y == o2.y) && (this.z == o2.z);
/*     */   }
/*     */   
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  56 */     return (this.x ^ this.z) * 31 + this.y;
/*     */   }
/*     */   
/*     */   public int compareTo(BlockCoord o)
/*     */   {
/*  61 */     if (this.x != o.x) return this.x < o.x ? 1 : -1;
/*  62 */     if (this.y != o.y) return this.y < o.y ? 1 : -1;
/*  63 */     if (this.z != o.z) return this.z < o.z ? 1 : -1;
/*  64 */     return 0;
/*     */   }
/*     */   
/*     */   public Vector3 toVector3Centered()
/*     */   {
/*  69 */     return new Vector3(this.x + 0.5D, this.y + 0.5D, this.z + 0.5D);
/*     */   }
/*     */   
/*     */   public BlockCoord multiply(int i)
/*     */   {
/*  74 */     this.x *= i;
/*  75 */     this.y *= i;
/*  76 */     this.z *= i;
/*  77 */     return this;
/*     */   }
/*     */   
/*     */   public double mag()
/*     */   {
/*  82 */     return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
/*     */   }
/*     */   
/*     */   public int mag2()
/*     */   {
/*  87 */     return this.x * this.x + this.y * this.y + this.z * this.z;
/*     */   }
/*     */   
/*     */   public boolean isZero()
/*     */   {
/*  92 */     return (this.x == 0) && (this.y == 0) && (this.z == 0);
/*     */   }
/*     */   
/*     */   public boolean isAxial()
/*     */   {
/*  97 */     return (this.y == 0) || (this.z == 0);
/*     */   }
/*     */   
/*     */   public BlockCoord add(BlockCoord coord2)
/*     */   {
/* 102 */     this.x += coord2.x;
/* 103 */     this.y += coord2.y;
/* 104 */     this.z += coord2.z;
/* 105 */     return this;
/*     */   }
/*     */   
/*     */   public BlockCoord add(int i, int j, int k)
/*     */   {
/* 110 */     this.x += i;
/* 111 */     this.y += j;
/* 112 */     this.z += k;
/* 113 */     return this;
/*     */   }
/*     */   
/*     */   public BlockCoord sub(BlockCoord coord2)
/*     */   {
/* 118 */     this.x -= coord2.x;
/* 119 */     this.y -= coord2.y;
/* 120 */     this.z -= coord2.z;
/* 121 */     return this;
/*     */   }
/*     */   
/*     */   public BlockCoord sub(int i, int j, int k)
/*     */   {
/* 126 */     this.x -= i;
/* 127 */     this.y -= j;
/* 128 */     this.z -= k;
/* 129 */     return this;
/*     */   }
/*     */   
/*     */   public BlockCoord offset(int side)
/*     */   {
/* 134 */     return offset(side, 1);
/*     */   }
/*     */   
/*     */   public BlockCoord offset(int side, int amount)
/*     */   {
/* 139 */     BlockCoord offset = sideOffsets[side];
/* 140 */     this.x += offset.x * amount;
/* 141 */     this.y += offset.y * amount;
/* 142 */     this.z += offset.z * amount;
/* 143 */     return this;
/*     */   }
/*     */   
/*     */   public BlockCoord inset(int side)
/*     */   {
/* 148 */     return inset(side, 1);
/*     */   }
/*     */   
/*     */   public BlockCoord inset(int side, int amount)
/*     */   {
/* 153 */     return offset(side, -amount);
/*     */   }
/*     */   
/*     */   public int getSide(int side) {
/* 157 */     switch (side) {
/*     */     case 0: 
/*     */     case 1: 
/* 160 */       return this.y;
/*     */     case 2: 
/*     */     case 3: 
/* 163 */       return this.z;
/*     */     case 4: 
/*     */     case 5: 
/* 166 */       return this.x;
/*     */     }
/* 168 */     throw new IndexOutOfBoundsException("Switch Falloff");
/*     */   }
/*     */   
/*     */   public BlockCoord setSide(int s, int v)
/*     */   {
/* 173 */     switch (s) {
/*     */     case 0: 
/*     */     case 1: 
/* 176 */       this.y = v; break;
/*     */     case 2: case 3: 
/* 178 */       this.z = v; break;
/*     */     case 4: case 5: 
/* 180 */       this.x = v; break;
/* 181 */     default:  throw new IndexOutOfBoundsException("Switch Falloff");
/*     */     }
/* 183 */     return this;
/*     */   }
/*     */   
/* 186 */   public static final BlockCoord[] sideOffsets = { new BlockCoord(0, -1, 0), new BlockCoord(0, 1, 0), new BlockCoord(0, 0, -1), new BlockCoord(0, 0, 1), new BlockCoord(-1, 0, 0), new BlockCoord(1, 0, 0) };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int[] intArray()
/*     */   {
/* 196 */     return new int[] { this.x, this.y, this.z };
/*     */   }
/*     */   
/*     */   public BlockCoord copy()
/*     */   {
/* 201 */     return new BlockCoord(this.x, this.y, this.z);
/*     */   }
/*     */   
/*     */   public BlockCoord set(int i, int j, int k)
/*     */   {
/* 206 */     this.x = i;
/* 207 */     this.y = j;
/* 208 */     this.z = k;
/* 209 */     return this;
/*     */   }
/*     */   
/*     */   public BlockCoord set(BlockCoord coord)
/*     */   {
/* 214 */     return set(coord.x, coord.y, coord.z);
/*     */   }
/*     */   
/*     */   public BlockCoord set(int[] ia)
/*     */   {
/* 219 */     return set(ia[0], ia[1], ia[2]);
/*     */   }
/*     */   
/*     */   public int toSide()
/*     */   {
/* 224 */     if (!isAxial()) return -1;
/* 225 */     if (this.y < 0) return 0;
/* 226 */     if (this.y > 0) return 1;
/* 227 */     if (this.z < 0) return 2;
/* 228 */     if (this.z > 0) return 3;
/* 229 */     if (this.x < 0) return 4;
/* 230 */     if (this.x > 0) { return 5;
/*     */     }
/* 232 */     return -1;
/*     */   }
/*     */   
/*     */   public int absSum()
/*     */   {
/* 237 */     return (this.x < 0 ? -this.x : this.x) + (this.y < 0 ? -this.y : this.y) + (this.z < 0 ? -this.z : this.z);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/vec/BlockCoord.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */