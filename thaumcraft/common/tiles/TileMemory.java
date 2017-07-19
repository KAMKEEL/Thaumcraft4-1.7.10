/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileMemory
/*    */   extends TileEntity
/*    */ {
/*    */   public Block oldblock;
/*    */   public int oldmeta;
/*    */   public NBTTagCompound tileEntityCompound;
/*    */   
/*    */   public TileMemory() {}
/*    */   
/*    */   public TileMemory(Block bi, int md, TileEntity te)
/*    */   {
/* 21 */     this.oldblock = bi;
/* 22 */     this.oldmeta = md;
/* 23 */     if (te != null) {
/* 24 */       this.tileEntityCompound = new NBTTagCompound();
/* 25 */       te.func_145841_b(this.tileEntityCompound);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean canUpdate()
/*    */   {
/* 32 */     return false;
/*    */   }
/*    */   
/*    */   public void recreateTileEntity() {
/* 36 */     if ((this.tileEntityCompound != null) && (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != null)) {
/* 37 */       this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.oldblock, this.oldmeta, 0);
/* 38 */       this.tileEntityCompound.func_74768_a("x", this.field_145851_c);
/* 39 */       this.tileEntityCompound.func_74768_a("y", this.field_145848_d);
/* 40 */       this.tileEntityCompound.func_74768_a("z", this.field_145849_e);
/* 41 */       this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e).func_145839_a(this.tileEntityCompound);
/*    */     }
/* 43 */     func_70296_d();
/* 44 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_145839_a(NBTTagCompound nbttagcompound)
/*    */   {
/* 51 */     super.func_145839_a(nbttagcompound);
/* 52 */     this.oldblock = Block.func_149729_e(nbttagcompound.func_74762_e("oldblock"));
/* 53 */     this.oldmeta = nbttagcompound.func_74762_e("oldmeta");
/* 54 */     if (nbttagcompound.func_74764_b("TileEntity")) {
/* 55 */       this.tileEntityCompound = nbttagcompound.func_74775_l("TileEntity");
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_145841_b(NBTTagCompound nbttagcompound)
/*    */   {
/* 62 */     super.func_145841_b(nbttagcompound);
/* 63 */     nbttagcompound.func_74768_a("oldblock", Block.func_149682_b(this.oldblock));
/* 64 */     nbttagcompound.func_74768_a("oldmeta", this.oldmeta);
/* 65 */     if (this.tileEntityCompound != null) {
/* 66 */       nbttagcompound.func_74782_a("TileEntity", this.tileEntityCompound);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileMemory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */