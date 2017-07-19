/*     */ package thaumcraft.codechicken.lib.vec;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import thaumcraft.codechicken.lib.util.Copyable;
/*     */ 
/*     */ public class CuboidCoord
/*     */   implements Iterable<BlockCoord>, Copyable<CuboidCoord>
/*     */ {
/*     */   public BlockCoord min;
/*     */   public BlockCoord max;
/*     */   
/*     */   public CuboidCoord()
/*     */   {
/*  15 */     this.min = new BlockCoord();
/*  16 */     this.max = new BlockCoord();
/*     */   }
/*     */   
/*     */   public CuboidCoord(BlockCoord min, BlockCoord max)
/*     */   {
/*  21 */     this.min = min;
/*  22 */     this.max = max;
/*     */   }
/*     */   
/*     */   public CuboidCoord(BlockCoord coord)
/*     */   {
/*  27 */     this(coord, coord.copy());
/*     */   }
/*     */   
/*     */   public CuboidCoord(int[] ia)
/*     */   {
/*  32 */     this(ia[0], ia[1], ia[2], ia[3], ia[4], ia[5]);
/*     */   }
/*     */   
/*     */   public CuboidCoord(int x1, int y1, int z1, int x2, int y2, int z2)
/*     */   {
/*  37 */     this(new BlockCoord(x1, y1, z1), new BlockCoord(x2, y2, z2));
/*     */   }
/*     */   
/*     */   public CuboidCoord expand(int amount) {
/*  41 */     return expand(amount, amount, amount);
/*     */   }
/*     */   
/*     */   public CuboidCoord expand(int x, int y, int z) {
/*  45 */     this.max.add(x, y, z);
/*  46 */     this.min.sub(x, y, z);
/*  47 */     return this;
/*     */   }
/*     */   
/*     */   public CuboidCoord expand(int side, int amount)
/*     */   {
/*  52 */     if (side % 2 == 0) {
/*  53 */       this.min = this.min.offset(side, amount);
/*     */     } else
/*  55 */       this.max = this.max.offset(side, amount);
/*  56 */     return this;
/*     */   }
/*     */   
/*     */   public int size(int s)
/*     */   {
/*  61 */     switch (s)
/*     */     {
/*     */     case 0: 
/*     */     case 1: 
/*  65 */       return this.max.y - this.min.y + 1;
/*     */     case 2: 
/*     */     case 3: 
/*  68 */       return this.max.z - this.min.z + 1;
/*     */     case 4: 
/*     */     case 5: 
/*  71 */       return this.max.x - this.min.x + 1;
/*     */     }
/*  73 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getSide(int s)
/*     */   {
/*  79 */     switch (s) {
/*     */     case 0: 
/*  81 */       return this.min.y;
/*  82 */     case 1:  return this.max.y;
/*  83 */     case 2:  return this.min.z;
/*  84 */     case 3:  return this.max.z;
/*  85 */     case 4:  return this.min.x;
/*  86 */     case 5:  return this.max.x;
/*     */     }
/*  88 */     throw new IndexOutOfBoundsException("Switch Falloff");
/*     */   }
/*     */   
/*     */   public CuboidCoord setSide(int s, int v)
/*     */   {
/*  93 */     switch (s) {
/*     */     case 0: 
/*  95 */       this.min.y = v; break;
/*  96 */     case 1:  this.max.y = v; break;
/*  97 */     case 2:  this.min.z = v; break;
/*  98 */     case 3:  this.max.z = v; break;
/*  99 */     case 4:  this.min.x = v; break;
/* 100 */     case 5:  this.max.x = v; break;
/* 101 */     default:  throw new IndexOutOfBoundsException("Switch Falloff");
/*     */     }
/* 103 */     return this;
/*     */   }
/*     */   
/*     */   public int getVolume()
/*     */   {
/* 108 */     return (this.max.x - this.min.x + 1) * (this.max.y - this.min.y + 1) * (this.max.z - this.min.z + 1);
/*     */   }
/*     */   
/*     */   public Vector3 getCenterVec()
/*     */   {
/* 113 */     return new Vector3(this.min.x + (this.max.x - this.min.x + 1) / 2.0D, this.min.y + (this.max.y - this.min.y + 1) / 2.0D, this.min.z + (this.max.z - this.min.z + 1) / 2.0D);
/*     */   }
/*     */   
/*     */   public BlockCoord getCenter(BlockCoord store)
/*     */   {
/* 118 */     store.set(this.min.x + (this.max.x - this.min.x) / 2, this.min.y + (this.max.y - this.min.y) / 2, this.min.z + (this.max.z - this.min.z) / 2);
/* 119 */     return store;
/*     */   }
/*     */   
/*     */   public boolean contains(BlockCoord coord)
/*     */   {
/* 124 */     return contains(coord.x, coord.y, coord.z);
/*     */   }
/*     */   
/*     */   public boolean contains(int x, int y, int z)
/*     */   {
/* 129 */     return (x >= this.min.x) && (x <= this.max.x) && (y >= this.min.y) && (y <= this.max.y) && (z >= this.min.z) && (z <= this.max.z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int[] intArray()
/*     */   {
/* 136 */     return new int[] { this.min.x, this.min.y, this.min.z, this.max.x, this.max.y, this.max.z };
/*     */   }
/*     */   
/*     */   public CuboidCoord copy()
/*     */   {
/* 141 */     return new CuboidCoord(this.min.copy(), this.max.copy());
/*     */   }
/*     */   
/*     */   public Cuboid6 bounds()
/*     */   {
/* 146 */     return new Cuboid6(this.min.x, this.min.y, this.min.z, this.max.x + 1, this.max.y + 1, this.max.z + 1);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB toAABB()
/*     */   {
/* 151 */     return bounds().toAABB();
/*     */   }
/*     */   
/*     */   public void set(BlockCoord min, BlockCoord max)
/*     */   {
/* 156 */     this.min.set(min);
/* 157 */     this.max.set(max);
/*     */   }
/*     */   
/*     */   public CuboidCoord set(int x1, int y1, int z1, int x2, int y2, int z2)
/*     */   {
/* 162 */     this.min.set(x1, y1, z1);
/* 163 */     this.max.set(x2, y2, z2);
/* 164 */     return this;
/*     */   }
/*     */   
/*     */   public CuboidCoord set(BlockCoord coord) {
/* 168 */     this.min.set(coord);
/* 169 */     this.max.set(coord);
/* 170 */     return this;
/*     */   }
/*     */   
/*     */   public CuboidCoord set(int[] ia)
/*     */   {
/* 175 */     return set(ia[0], ia[1], ia[2], ia[3], ia[4], ia[5]);
/*     */   }
/*     */   
/*     */   public CuboidCoord include(BlockCoord coord) {
/* 179 */     return include(coord.x, coord.y, coord.z);
/*     */   }
/*     */   
/*     */   public CuboidCoord include(int x, int y, int z) {
/* 183 */     if (x < this.min.x) { this.min.x = x;
/* 184 */     } else if (x > this.max.x) this.max.x = x;
/* 185 */     if (y < this.min.y) { this.min.y = y;
/* 186 */     } else if (y > this.max.y) this.max.y = y;
/* 187 */     if (z < this.min.z) { this.min.z = z;
/* 188 */     } else if (z > this.max.z) this.max.z = z;
/* 189 */     return this;
/*     */   }
/*     */   
/*     */   public Iterator<BlockCoord> iterator() {
/* 193 */     new Iterator() {
/* 194 */       BlockCoord b = null;
/*     */       
/*     */       public boolean hasNext() {
/* 197 */         return (this.b == null) || (!this.b.equals(CuboidCoord.this.max));
/*     */       }
/*     */       
/*     */       public BlockCoord next() {
/* 201 */         if (this.b == null) {
/* 202 */           this.b = CuboidCoord.this.min.copy();
/*     */         }
/* 204 */         else if (this.b.z != CuboidCoord.this.max.z) {
/* 205 */           this.b.z += 1;
/*     */         } else {
/* 207 */           this.b.z = CuboidCoord.this.min.z;
/* 208 */           if (this.b.y != CuboidCoord.this.max.y) {
/* 209 */             this.b.y += 1;
/*     */           } else {
/* 211 */             this.b.y = CuboidCoord.this.min.y;
/* 212 */             this.b.x += 1;
/*     */           }
/*     */         }
/*     */         
/* 216 */         return this.b.copy();
/*     */       }
/*     */       
/*     */       public void remove() {
/* 220 */         throw new UnsupportedOperationException();
/*     */       }
/*     */     };
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/vec/CuboidCoord.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */