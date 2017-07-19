/*     */ package thaumcraft.api;
/*     */ 
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockCoordinates
/*     */   implements Comparable
/*     */ {
/*     */   public int x;
/*     */   public int y;
/*     */   public int z;
/*     */   
/*     */   public BlockCoordinates() {}
/*     */   
/*     */   public BlockCoordinates(int par1, int par2, int par3)
/*     */   {
/*  20 */     this.x = par1;
/*  21 */     this.y = par2;
/*  22 */     this.z = par3;
/*     */   }
/*     */   
/*     */   public BlockCoordinates(TileEntity tile)
/*     */   {
/*  27 */     this.x = tile.field_145851_c;
/*  28 */     this.y = tile.field_145848_d;
/*  29 */     this.z = tile.field_145849_e;
/*     */   }
/*     */   
/*     */   public BlockCoordinates(BlockCoordinates par1ChunkCoordinates)
/*     */   {
/*  34 */     this.x = par1ChunkCoordinates.x;
/*  35 */     this.y = par1ChunkCoordinates.y;
/*  36 */     this.z = par1ChunkCoordinates.z;
/*     */   }
/*     */   
/*     */   public boolean equals(Object par1Obj)
/*     */   {
/*  41 */     if (!(par1Obj instanceof BlockCoordinates))
/*     */     {
/*  43 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  47 */     BlockCoordinates coordinates = (BlockCoordinates)par1Obj;
/*  48 */     return (this.x == coordinates.x) && (this.y == coordinates.y) && (this.z == coordinates.z);
/*     */   }
/*     */   
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  54 */     return this.x + this.y << 8 + this.z << 16;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int compareWorldCoordinate(BlockCoordinates par1)
/*     */   {
/*  62 */     return this.y == par1.y ? this.z - par1.z : this.z == par1.z ? this.x - par1.x : this.y - par1.y;
/*     */   }
/*     */   
/*     */   public void set(int par1, int par2, int par3, int d)
/*     */   {
/*  67 */     this.x = par1;
/*  68 */     this.y = par2;
/*  69 */     this.z = par3;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getDistanceSquared(int par1, int par2, int par3)
/*     */   {
/*  77 */     float f = this.x - par1;
/*  78 */     float f1 = this.y - par2;
/*  79 */     float f2 = this.z - par3;
/*  80 */     return f * f + f1 * f1 + f2 * f2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getDistanceSquaredToWorldCoordinates(BlockCoordinates par1ChunkCoordinates)
/*     */   {
/*  88 */     return getDistanceSquared(par1ChunkCoordinates.x, par1ChunkCoordinates.y, par1ChunkCoordinates.z);
/*     */   }
/*     */   
/*     */   public int compareTo(Object par1Obj)
/*     */   {
/*  93 */     return compareWorldCoordinate((BlockCoordinates)par1Obj);
/*     */   }
/*     */   
/*     */   public void readNBT(NBTTagCompound nbt) {
/*  97 */     this.x = nbt.func_74762_e("b_x");
/*  98 */     this.y = nbt.func_74762_e("b_y");
/*  99 */     this.z = nbt.func_74762_e("b_z");
/*     */   }
/*     */   
/*     */   public void writeNBT(NBTTagCompound nbt) {
/* 103 */     nbt.func_74768_a("b_x", this.x);
/* 104 */     nbt.func_74768_a("b_y", this.y);
/* 105 */     nbt.func_74768_a("b_z", this.z);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/BlockCoordinates.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */