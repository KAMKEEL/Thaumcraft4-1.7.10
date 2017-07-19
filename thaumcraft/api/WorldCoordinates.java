/*     */ package thaumcraft.api;
/*     */ 
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldCoordinates
/*     */   implements Comparable
/*     */ {
/*     */   public int x;
/*     */   public int y;
/*     */   public int z;
/*     */   public int dim;
/*     */   
/*     */   public WorldCoordinates() {}
/*     */   
/*     */   public WorldCoordinates(int par1, int par2, int par3, int d)
/*     */   {
/*  22 */     this.x = par1;
/*  23 */     this.y = par2;
/*  24 */     this.z = par3;
/*  25 */     this.dim = d;
/*     */   }
/*     */   
/*     */   public WorldCoordinates(TileEntity tile)
/*     */   {
/*  30 */     this.x = tile.field_145851_c;
/*  31 */     this.y = tile.field_145848_d;
/*  32 */     this.z = tile.field_145849_e;
/*  33 */     this.dim = tile.func_145831_w().field_73011_w.field_76574_g;
/*     */   }
/*     */   
/*     */   public WorldCoordinates(WorldCoordinates par1ChunkCoordinates)
/*     */   {
/*  38 */     this.x = par1ChunkCoordinates.x;
/*  39 */     this.y = par1ChunkCoordinates.y;
/*  40 */     this.z = par1ChunkCoordinates.z;
/*  41 */     this.dim = par1ChunkCoordinates.dim;
/*     */   }
/*     */   
/*     */   public boolean equals(Object par1Obj)
/*     */   {
/*  46 */     if (!(par1Obj instanceof WorldCoordinates))
/*     */     {
/*  48 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  52 */     WorldCoordinates coordinates = (WorldCoordinates)par1Obj;
/*  53 */     return (this.x == coordinates.x) && (this.y == coordinates.y) && (this.z == coordinates.z) && (this.dim == coordinates.dim);
/*     */   }
/*     */   
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  59 */     return this.x + this.y << 8 + this.z << 16 + this.dim << 24;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int compareWorldCoordinate(WorldCoordinates par1)
/*     */   {
/*  67 */     return this.dim == par1.dim ? this.y - par1.y : this.y == par1.y ? this.z - par1.z : this.z == par1.z ? this.x - par1.x : -1;
/*     */   }
/*     */   
/*     */ 
/*     */   public void set(int par1, int par2, int par3, int d)
/*     */   {
/*  73 */     this.x = par1;
/*  74 */     this.y = par2;
/*  75 */     this.z = par3;
/*  76 */     this.dim = d;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getDistanceSquared(int par1, int par2, int par3)
/*     */   {
/*  84 */     float f = this.x - par1;
/*  85 */     float f1 = this.y - par2;
/*  86 */     float f2 = this.z - par3;
/*  87 */     return f * f + f1 * f1 + f2 * f2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getDistanceSquaredToWorldCoordinates(WorldCoordinates par1ChunkCoordinates)
/*     */   {
/*  95 */     return getDistanceSquared(par1ChunkCoordinates.x, par1ChunkCoordinates.y, par1ChunkCoordinates.z);
/*     */   }
/*     */   
/*     */   public int compareTo(Object par1Obj)
/*     */   {
/* 100 */     return compareWorldCoordinate((WorldCoordinates)par1Obj);
/*     */   }
/*     */   
/*     */   public void readNBT(NBTTagCompound nbt) {
/* 104 */     this.x = nbt.func_74762_e("w_x");
/* 105 */     this.y = nbt.func_74762_e("w_y");
/* 106 */     this.z = nbt.func_74762_e("w_z");
/* 107 */     this.dim = nbt.func_74762_e("w_d");
/*     */   }
/*     */   
/*     */   public void writeNBT(NBTTagCompound nbt) {
/* 111 */     nbt.func_74768_a("w_x", this.x);
/* 112 */     nbt.func_74768_a("w_y", this.y);
/* 113 */     nbt.func_74768_a("w_z", this.z);
/* 114 */     nbt.func_74768_a("w_d", this.dim);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/WorldCoordinates.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */