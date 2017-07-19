/*     */ package thaumcraft.common.lib.world;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.structure.StructureBoundingBox;
/*     */ import net.minecraft.world.gen.structure.StructureComponent;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
/*     */ import thaumcraft.common.config.ConfigEntities;
/*     */ 
/*     */ public class ComponentBankerHome extends StructureVillagePieces.Village
/*     */ {
/*     */   private boolean isTallHouse;
/*     */   private int tablePosition;
/*     */   
/*     */   public ComponentBankerHome() {}
/*     */   
/*     */   public ComponentBankerHome(StructureVillagePieces.Start p_i2101_1_, int p_i2101_2_, Random p_i2101_3_, StructureBoundingBox p_i2101_4_, int p_i2101_5_)
/*     */   {
/*  25 */     super(p_i2101_1_, p_i2101_2_);
/*  26 */     this.field_74885_f = p_i2101_5_;
/*  27 */     this.field_74887_e = p_i2101_4_;
/*  28 */     this.isTallHouse = p_i2101_3_.nextBoolean();
/*  29 */     this.tablePosition = p_i2101_3_.nextInt(3);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_143012_a(NBTTagCompound p_143012_1_)
/*     */   {
/*  35 */     super.func_143012_a(p_143012_1_);
/*  36 */     p_143012_1_.func_74768_a("T", this.tablePosition);
/*  37 */     p_143012_1_.func_74757_a("C", this.isTallHouse);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_143011_b(NBTTagCompound p_143011_1_)
/*     */   {
/*  43 */     super.func_143011_b(p_143011_1_);
/*  44 */     this.tablePosition = p_143011_1_.func_74762_e("T");
/*  45 */     this.isTallHouse = p_143011_1_.func_74767_n("C");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static Object buildComponent(StructureVillagePieces.Start p_74908_0_, List p_74908_1_, Random p_74908_2_, int p_74908_3_, int p_74908_4_, int p_74908_5_, int p_74908_6_, int p_74908_7_)
/*     */   {
/*  52 */     StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(p_74908_3_, p_74908_4_, p_74908_5_, 0, 0, 0, 4, 6, 5, p_74908_6_);
/*  53 */     return (func_74895_a(structureboundingbox)) && (StructureComponent.func_74883_a(p_74908_1_, structureboundingbox) == null) ? new ComponentBankerHome(p_74908_0_, p_74908_7_, p_74908_2_, structureboundingbox, p_74908_6_) : null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
/*     */   {
/*  63 */     if (this.field_143015_k < 0)
/*     */     {
/*  65 */       this.field_143015_k = func_74889_b(p_74875_1_, p_74875_3_);
/*     */       
/*  67 */       if (this.field_143015_k < 0)
/*     */       {
/*  69 */         return true;
/*     */       }
/*     */       
/*  72 */       this.field_74887_e.func_78886_a(0, this.field_143015_k - this.field_74887_e.field_78894_e + 6 - 1, 0);
/*     */     }
/*     */     
/*  75 */     func_151549_a(p_74875_1_, p_74875_3_, 1, 1, 1, 3, 5, 4, Blocks.field_150350_a, Blocks.field_150350_a, false);
/*  76 */     func_151549_a(p_74875_1_, p_74875_3_, 0, 0, 0, 3, 0, 4, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*  77 */     func_151549_a(p_74875_1_, p_74875_3_, 1, 0, 1, 2, 0, 3, Blocks.field_150346_d, Blocks.field_150346_d, false);
/*     */     
/*  79 */     if (this.isTallHouse)
/*     */     {
/*  81 */       func_151549_a(p_74875_1_, p_74875_3_, 1, 4, 1, 2, 4, 3, Blocks.field_150364_r, Blocks.field_150364_r, false);
/*     */     }
/*     */     else
/*     */     {
/*  85 */       func_151549_a(p_74875_1_, p_74875_3_, 1, 5, 1, 2, 5, 3, Blocks.field_150364_r, Blocks.field_150364_r, false);
/*     */     }
/*     */     
/*  88 */     func_151550_a(p_74875_1_, Blocks.field_150364_r, 0, 1, 4, 0, p_74875_3_);
/*  89 */     func_151550_a(p_74875_1_, Blocks.field_150364_r, 0, 2, 4, 0, p_74875_3_);
/*  90 */     func_151550_a(p_74875_1_, Blocks.field_150364_r, 0, 1, 4, 4, p_74875_3_);
/*  91 */     func_151550_a(p_74875_1_, Blocks.field_150364_r, 0, 2, 4, 4, p_74875_3_);
/*  92 */     func_151550_a(p_74875_1_, Blocks.field_150364_r, 0, 0, 4, 1, p_74875_3_);
/*  93 */     func_151550_a(p_74875_1_, Blocks.field_150364_r, 0, 0, 4, 2, p_74875_3_);
/*  94 */     func_151550_a(p_74875_1_, Blocks.field_150364_r, 0, 0, 4, 3, p_74875_3_);
/*  95 */     func_151550_a(p_74875_1_, Blocks.field_150364_r, 0, 3, 4, 1, p_74875_3_);
/*  96 */     func_151550_a(p_74875_1_, Blocks.field_150364_r, 0, 3, 4, 2, p_74875_3_);
/*  97 */     func_151550_a(p_74875_1_, Blocks.field_150364_r, 0, 3, 4, 3, p_74875_3_);
/*  98 */     func_151549_a(p_74875_1_, p_74875_3_, 0, 1, 0, 0, 3, 0, Blocks.field_150364_r, Blocks.field_150364_r, false);
/*  99 */     func_151549_a(p_74875_1_, p_74875_3_, 3, 1, 0, 3, 3, 0, Blocks.field_150364_r, Blocks.field_150364_r, false);
/* 100 */     func_151549_a(p_74875_1_, p_74875_3_, 0, 1, 4, 0, 3, 4, Blocks.field_150364_r, Blocks.field_150364_r, false);
/* 101 */     func_151549_a(p_74875_1_, p_74875_3_, 3, 1, 4, 3, 3, 4, Blocks.field_150364_r, Blocks.field_150364_r, false);
/* 102 */     func_151549_a(p_74875_1_, p_74875_3_, 0, 1, 1, 0, 3, 3, Blocks.field_150344_f, Blocks.field_150344_f, false);
/* 103 */     func_151549_a(p_74875_1_, p_74875_3_, 3, 1, 1, 3, 3, 3, Blocks.field_150344_f, Blocks.field_150344_f, false);
/* 104 */     func_151549_a(p_74875_1_, p_74875_3_, 1, 1, 0, 2, 3, 0, Blocks.field_150344_f, Blocks.field_150344_f, false);
/* 105 */     func_151549_a(p_74875_1_, p_74875_3_, 1, 1, 4, 2, 3, 4, Blocks.field_150344_f, Blocks.field_150344_f, false);
/* 106 */     func_151550_a(p_74875_1_, Blocks.field_150411_aY, 0, 0, 2, 2, p_74875_3_);
/* 107 */     func_151550_a(p_74875_1_, Blocks.field_150411_aY, 0, 3, 2, 2, p_74875_3_);
/*     */     
/* 109 */     if (this.tablePosition > 0)
/*     */     {
/* 111 */       func_151550_a(p_74875_1_, Blocks.field_150463_bK, 0, this.tablePosition, 1, 3, p_74875_3_);
/* 112 */       func_151550_a(p_74875_1_, Blocks.field_150456_au, 0, this.tablePosition, 2, 3, p_74875_3_);
/*     */     }
/*     */     
/* 115 */     func_151550_a(p_74875_1_, Blocks.field_150350_a, 0, 1, 1, 0, p_74875_3_);
/* 116 */     func_151550_a(p_74875_1_, Blocks.field_150350_a, 0, 1, 2, 0, p_74875_3_);
/* 117 */     func_74881_a(p_74875_1_, p_74875_3_, p_74875_2_, 1, 1, 0, func_151555_a(Blocks.field_150466_ao, 1));
/*     */     
/* 119 */     if ((func_151548_a(p_74875_1_, 1, 0, -1, p_74875_3_).func_149688_o() == Material.field_151579_a) && (func_151548_a(p_74875_1_, 1, -1, -1, p_74875_3_).func_149688_o() != Material.field_151579_a))
/*     */     {
/* 121 */       func_151550_a(p_74875_1_, Blocks.field_150446_ar, func_151555_a(Blocks.field_150446_ar, 3), 1, 0, -1, p_74875_3_);
/*     */     }
/*     */     
/* 124 */     for (int i = 0; i < 5; i++)
/*     */     {
/* 126 */       for (int j = 0; j < 4; j++)
/*     */       {
/* 128 */         func_74871_b(p_74875_1_, j, 6, i, p_74875_3_);
/* 129 */         func_151554_b(p_74875_1_, Blocks.field_150347_e, 0, j, -1, i, p_74875_3_);
/*     */       }
/*     */     }
/*     */     
/* 133 */     func_74893_a(p_74875_1_, p_74875_3_, 1, 1, 2, 1);
/* 134 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   protected int func_74888_b(int par1)
/*     */   {
/* 140 */     return ConfigEntities.entBankerId;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/ComponentBankerHome.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */