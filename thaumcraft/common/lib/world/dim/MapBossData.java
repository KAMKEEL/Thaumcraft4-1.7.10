/*    */ package thaumcraft.common.lib.world.dim;
/*    */ 
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.world.WorldSavedData;
/*    */ 
/*    */ public class MapBossData extends WorldSavedData
/*    */ {
/*    */   public int bossCount;
/*    */   
/*    */   public MapBossData(String p_i2140_1_)
/*    */   {
/* 12 */     super(p_i2140_1_);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_76184_a(NBTTagCompound p_76184_1_)
/*    */   {
/* 20 */     this.bossCount = p_76184_1_.func_74762_e("bossCount");
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_76187_b(NBTTagCompound p_76187_1_)
/*    */   {
/* 28 */     p_76187_1_.func_74768_a("bossCount", this.bossCount);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/dim/MapBossData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */