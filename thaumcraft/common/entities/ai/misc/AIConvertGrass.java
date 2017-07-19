/*     */ package thaumcraft.common.entities.ai.misc;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
/*     */ 
/*     */ public class AIConvertGrass extends net.minecraft.entity.ai.EntityAIBase
/*     */ {
/*     */   private EntityLiving entity;
/*     */   private World world;
/*  17 */   int field_48399_a = 0;
/*     */   
/*     */   public AIConvertGrass(EntityLiving par1EntityLiving)
/*     */   {
/*  21 */     this.entity = par1EntityLiving;
/*  22 */     this.world = par1EntityLiving.field_70170_p;
/*  23 */     func_75248_a(7);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  31 */     if (this.entity.func_70681_au().nextInt(250) != 0)
/*     */     {
/*  33 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  37 */     int var1 = MathHelper.func_76128_c(this.entity.field_70165_t);
/*  38 */     int var2 = MathHelper.func_76128_c(this.entity.field_70163_u);
/*  39 */     int var3 = MathHelper.func_76128_c(this.entity.field_70161_v);
/*  40 */     return (this.world.func_147439_a(var1, var2, var3) == Blocks.field_150329_H) && (this.world.func_72805_g(var1, var2, var3) == 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/*  51 */     this.field_48399_a = 40;
/*  52 */     this.world.func_72960_a(this.entity, (byte)10);
/*  53 */     this.entity.func_70661_as().func_75499_g();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/*  61 */     this.field_48399_a = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  69 */     return this.field_48399_a > 0;
/*     */   }
/*     */   
/*     */   public int func_48396_h()
/*     */   {
/*  74 */     return this.field_48399_a;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75246_d()
/*     */   {
/*  82 */     this.field_48399_a = Math.max(0, this.field_48399_a - 1);
/*     */     
/*  84 */     if (this.field_48399_a == 4)
/*     */     {
/*  86 */       int var1 = MathHelper.func_76128_c(this.entity.field_70165_t);
/*  87 */       int var2 = MathHelper.func_76128_c(this.entity.field_70163_u);
/*  88 */       int var3 = MathHelper.func_76128_c(this.entity.field_70161_v);
/*     */       
/*  90 */       if (this.world.func_147439_a(var1, var2, var3) == Blocks.field_150329_H)
/*     */       {
/*  92 */         this.world.func_72926_e(2001, var1, var2, var3, Block.func_149682_b(Blocks.field_150349_c) + 4096);
/*  93 */         this.world.func_147468_f(var1, var2, var3);
/*  94 */         this.world.func_147465_d(var1, var2, var3, ConfigBlocks.blockTaintFibres, 0, 3);
/*  95 */         Utils.setBiomeAt(this.world, var1, var3, ThaumcraftWorldGenerator.biomeTaint);
/*  96 */         this.entity.func_70615_aA();
/*     */       }
/*  98 */       else if (this.world.func_147439_a(var1, var2 - 1, var3) == Blocks.field_150349_c)
/*     */       {
/* 100 */         this.world.func_72926_e(2001, var1, var2 - 1, var3, Block.func_149682_b(Blocks.field_150349_c));
/* 101 */         this.world.func_147465_d(var1, var2, var3, ConfigBlocks.blockTaintFibres, 0, 3);
/* 102 */         Utils.setBiomeAt(this.world, var1, var3, ThaumcraftWorldGenerator.biomeTaint);
/* 103 */         this.entity.func_70615_aA();
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/misc/AIConvertGrass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */