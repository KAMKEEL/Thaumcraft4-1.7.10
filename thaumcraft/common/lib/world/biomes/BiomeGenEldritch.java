/*    */ package thaumcraft.common.lib.world.biomes;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.biome.BiomeGenBase;
/*    */ import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
/*    */ import thaumcraft.common.entities.monster.EntityEldritchGuardian;
/*    */ import thaumcraft.common.entities.monster.EntityInhabitedZombie;
/*    */ 
/*    */ public class BiomeGenEldritch extends BiomeGenBase
/*    */ {
/*    */   public BiomeGenEldritch(int p_i1990_1_)
/*    */   {
/* 18 */     super(p_i1990_1_);
/*    */     
/* 20 */     this.field_76761_J.clear();
/* 21 */     this.field_76762_K.clear();
/* 22 */     this.field_76755_L.clear();
/* 23 */     this.field_82914_M.clear();
/* 24 */     this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityInhabitedZombie.class, 1, 1, 1));
/* 25 */     this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityEldritchGuardian.class, 1, 1, 1));
/* 26 */     this.field_76752_A = Blocks.field_150346_d;
/* 27 */     this.field_76753_B = Blocks.field_150346_d;
/* 28 */     func_76735_a("Eldritch");
/* 29 */     func_76745_m();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_76731_a(float p_76731_1_)
/*    */   {
/* 39 */     return 0;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_76728_a(World world, Random random, int x, int z) {}
/*    */   
/*    */ 
/*    */ 
/*    */   public BiomeGenBase func_150566_k()
/*    */   {
/* 50 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/biomes/BiomeGenEldritch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */