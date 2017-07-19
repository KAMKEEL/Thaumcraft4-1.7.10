/*    */ package thaumcraft.common.lib.world.biomes;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.monster.EntityEnderman;
/*    */ import net.minecraft.entity.monster.EntityWitch;
/*    */ import net.minecraft.entity.passive.EntityBat;
/*    */ import net.minecraft.world.biome.BiomeGenBase;
/*    */ import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
/*    */ import thaumcraft.common.config.Config;
/*    */ import thaumcraft.common.entities.monster.EntityBrainyZombie;
/*    */ import thaumcraft.common.entities.monster.EntityGiantBrainyZombie;
/*    */ 
/*    */ public class BiomeGenEerie extends BiomeGenBase
/*    */ {
/*    */   public BiomeGenEerie(int par1)
/*    */   {
/* 19 */     super(par1);
/* 20 */     this.field_76762_K.clear();
/* 21 */     this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityBat.class, 3, 1, 1));
/*    */     
/* 23 */     this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityWitch.class, 8, 1, 1));
/* 24 */     this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityEnderman.class, 4, 1, 1));
/* 25 */     if (Config.spawnAngryZombie) {
/* 26 */       this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityBrainyZombie.class, 32, 1, 1));
/* 27 */       this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityGiantBrainyZombie.class, 8, 1, 1));
/*    */     }
/* 29 */     if (Config.spawnWisp)
/* 30 */       this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(thaumcraft.common.entities.monster.EntityWisp.class, 3, 1, 1));
/* 31 */     if (Config.spawnElder) {
/* 32 */       this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(thaumcraft.common.entities.monster.EntityEldritchGuardian.class, 1, 1, 1));
/*    */     }
/* 34 */     this.field_76760_I.field_76832_z = 2;
/* 35 */     this.field_76760_I.field_76802_A = 1;
/* 36 */     this.field_76760_I.field_76803_B = 2;
/* 37 */     func_76732_a(0.5F, 0.5F);
/* 38 */     func_76735_a("Eerie");
/* 39 */     func_76739_b(4212800);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_150558_b(int x, int y, int z)
/*    */   {
/* 48 */     return 4212800;
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_150571_c(int x, int y, int z)
/*    */   {
/* 55 */     return 4215616;
/*    */   }
/*    */   
/*    */   public int func_76731_a(float par1)
/*    */   {
/* 60 */     return 2237081;
/*    */   }
/*    */   
/*    */   public int getWaterColorMultiplier()
/*    */   {
/* 65 */     return 3035999;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public BiomeGenBase func_150566_k()
/*    */   {
/* 72 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/biomes/BiomeGenEerie.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */