/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import thaumcraft.api.TileThaumcraft;
/*    */ 
/*    */ 
/*    */ public class TileWarded
/*    */   extends TileThaumcraft
/*    */ {
/* 12 */   public int owner = 0;
/* 13 */   public Block block = Blocks.field_150350_a;
/* 14 */   public byte blockMd = 0;
/* 15 */   public boolean safeToRemove = false;
/*    */   public byte light;
/*    */   
/*    */   public boolean canUpdate()
/*    */   {
/* 20 */     return false;
/*    */   }
/*    */   
/*    */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*    */   {
/* 25 */     this.block = Block.func_149729_e(nbttagcompound.func_74762_e("bi"));
/* 26 */     this.blockMd = nbttagcompound.func_74771_c("md");
/* 27 */     this.light = nbttagcompound.func_74771_c("ll");
/* 28 */     this.owner = nbttagcompound.func_74762_e("oi");
/* 29 */     if (this.owner == 0) {
/* 30 */       String s = nbttagcompound.func_74779_i("owner");
/* 31 */       if (s != null) this.owner = s.hashCode();
/*    */     }
/* 33 */     if (this.block == null) this.block = Blocks.field_150348_b;
/*    */   }
/*    */   
/*    */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*    */   {
/* 38 */     nbttagcompound.func_74768_a("bi", Block.func_149682_b(this.block));
/* 39 */     nbttagcompound.func_74774_a("md", this.blockMd);
/* 40 */     nbttagcompound.func_74774_a("ll", this.light);
/* 41 */     nbttagcompound.func_74768_a("oi", this.owner);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileWarded.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */