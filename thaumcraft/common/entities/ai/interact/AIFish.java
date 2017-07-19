/*     */ package thaumcraft.common.entities.ai.interact;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemFishFood.FishType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.FurnaceRecipes;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.util.WeightedRandom;
/*     */ import net.minecraft.util.WeightedRandomFishable;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBobber;
/*     */ 
/*     */ public class AIFish extends net.minecraft.entity.ai.EntityAIBase
/*     */ {
/*     */   private EntityGolemBase theGolem;
/*     */   private float quality;
/*     */   private float distance;
/*     */   private World theWorld;
/*  32 */   private int maxDelay = 1;
/*  33 */   private int mod = 1;
/*  34 */   private int count = 0;
/*  35 */   private Vec3 target = null;
/*  36 */   private EntityGolemBobber bobber = null;
/*     */   
/*     */   public AIFish(EntityGolemBase par1EntityCreature)
/*     */   {
/*  40 */     this.theGolem = par1EntityCreature;
/*  41 */     this.theWorld = par1EntityCreature.field_70170_p;
/*  42 */     func_75248_a(3);
/*  43 */     this.distance = MathHelper.func_76123_f(this.theGolem.getRange() / 2.0F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  51 */     if ((this.target != null) || (this.count > 0) || (this.theGolem.field_70173_aa % Config.golemDelay > 0) || (!this.theGolem.func_70661_as().func_75500_f()))
/*     */     {
/*  53 */       return false;
/*     */     }
/*     */     
/*  56 */     if (this.bobber != null) {
/*  57 */       this.bobber.func_70106_y();
/*     */     }
/*     */     
/*  60 */     Vec3 vv = findWater();
/*     */     
/*  62 */     if (vv == null)
/*     */     {
/*  64 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  68 */     this.target = Vec3.func_72443_a(vv.field_72450_a, vv.field_72448_b, vv.field_72449_c);
/*  69 */     this.quality = 0.0F;
/*  70 */     int x = (int)this.target.field_72450_a;
/*  71 */     int y = (int)this.target.field_72448_b;
/*  72 */     int z = (int)this.target.field_72449_c;
/*  73 */     for (int a = 2; a <= 5; a++) {
/*  74 */       ForgeDirection dir = ForgeDirection.getOrientation(a);
/*  75 */       if ((this.theWorld.func_147439_a(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ).func_149688_o() == Material.field_151586_h) && (this.theWorld.func_147437_c(x + dir.offsetX, y + 1 + dir.offsetY, z + dir.offsetZ)))
/*     */       {
/*  77 */         this.quality += 3.0E-5F;
/*  78 */         if (this.theWorld.func_72937_j(x + dir.offsetX, y + 1 + dir.offsetY, z + dir.offsetZ)) {
/*  79 */           this.quality += 3.0E-5F;
/*     */         }
/*  81 */         for (int depth = 1; depth <= 3; depth++) {
/*  82 */           if (this.theWorld.func_147439_a(x + dir.offsetX, y - depth + dir.offsetY, z + dir.offsetZ).func_149688_o() == Material.field_151586_h) {
/*  83 */             this.quality += 1.5E-5F;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*  88 */     this.theWorld.func_72956_a(this.theGolem, "random.bow", 0.5F, 0.4F / (this.theWorld.field_73012_v.nextFloat() * 0.4F + 0.8F));
/*  89 */     this.bobber = new EntityGolemBobber(this.theWorld, this.theGolem, x, y, z);
/*  90 */     return this.theWorld.func_72838_d(this.bobber);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  99 */     return (this.bobber != null) && (!this.bobber.field_70128_L) && (this.target != null) && (this.count-- > 0);
/*     */   }
/*     */   
/*     */   public void func_75246_d()
/*     */   {
/* 104 */     if (this.target != null) {
/* 105 */       this.theGolem.func_70671_ap().func_75650_a(this.target.field_72450_a + 0.5D, this.target.field_72448_b + 1.0D, this.target.field_72449_c + 0.5D, 30.0F, 30.0F);
/*     */       
/* 107 */       float chance = this.quality + this.theGolem.getGolemStrength() * 1.5E-4F;
/*     */       
/* 109 */       if (this.theWorld.field_73012_v.nextFloat() < chance) {
/* 110 */         this.theGolem.startRightArmTimer();
/*     */         
/* 112 */         int qq = 1;
/*     */         
/* 114 */         if ((this.theGolem.getUpgradeAmount(0) > 0) && 
/* 115 */           (this.theWorld.field_73012_v.nextInt(10) < this.theGolem.getUpgradeAmount(0))) {
/* 116 */           qq++;
/*     */         }
/*     */         
/*     */ 
/* 120 */         for (int a = 0; a < qq; a++) {
/* 121 */           ItemStack fs = getFishingResult();
/*     */           
/* 123 */           if (this.theGolem.getUpgradeAmount(2) > 0) {
/* 124 */             ItemStack sr = FurnaceRecipes.func_77602_a().func_151395_a(fs);
/* 125 */             if (sr != null)
/*     */             {
/* 127 */               fs = sr.func_77946_l();
/*     */             }
/*     */           }
/*     */           
/* 131 */           EntityItem entityitem = new EntityItem(this.theWorld, this.target.field_72450_a + 0.5D, this.target.field_72448_b + 1.0D, this.target.field_72449_c + 0.5D, fs);
/*     */           
/* 133 */           if (this.theGolem.getUpgradeAmount(2) > 0) {
/* 134 */             entityitem.func_70015_d(2);
/*     */           }
/* 136 */           entityitem.field_145804_b = 20;
/* 137 */           double d1 = this.theGolem.field_70165_t + this.theWorld.field_73012_v.nextFloat() - this.theWorld.field_73012_v.nextFloat() - this.target.field_72450_a + 0.5D;
/* 138 */           double d3 = this.theGolem.field_70163_u - this.target.field_72448_b + 1.0D;
/* 139 */           double d5 = this.theGolem.field_70161_v + this.theWorld.field_73012_v.nextFloat() - this.theWorld.field_73012_v.nextFloat() - this.target.field_72449_c + 0.5D;
/* 140 */           double d7 = MathHelper.func_76133_a(d1 * d1 + d3 * d3 + d5 * d5);
/* 141 */           double d9 = 0.1D;
/* 142 */           entityitem.field_70159_w = (d1 * d9);
/* 143 */           entityitem.field_70181_x = (d3 * d9 + MathHelper.func_76133_a(d7) * 0.08D);
/* 144 */           entityitem.field_70179_y = (d5 * d9);
/* 145 */           this.theWorld.func_72838_d(entityitem);
/*     */         }
/*     */         
/* 148 */         if (this.bobber != null) {
/* 149 */           this.bobber.func_85030_a("random.splash", 0.15F, 1.0F + (this.theWorld.field_73012_v.nextFloat() - this.theWorld.field_73012_v.nextFloat()) * 0.4F);
/* 150 */           ((WorldServer)this.theWorld).func_147487_a("splash", this.bobber.field_70165_t, this.bobber.field_70163_u + 0.5D, this.bobber.field_70161_v, 20 + this.theWorld.field_73012_v.nextInt(20), 0.10000000149011612D, 0.0D, 0.10000000149011612D, 0.0D);
/*     */           
/*     */ 
/* 153 */           this.bobber.func_70106_y();
/*     */         }
/* 155 */         this.target = null;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/* 163 */     if (this.bobber != null) this.bobber.func_70106_y();
/* 164 */     this.target = null;
/* 165 */     this.count = -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/* 173 */     this.count = (300 + this.theWorld.field_73012_v.nextInt(200));
/* 174 */     this.theGolem.startRightArmTimer();
/*     */   }
/*     */   
/*     */ 
/*     */   private Vec3 findWater()
/*     */   {
/* 180 */     Random rand = this.theGolem.func_70681_au();
/*     */     
/* 182 */     for (int var2 = 0; var2 < this.distance * 2.0F; var2++)
/*     */     {
/* 184 */       int x = (int)(this.theGolem.func_110172_bL().field_71574_a + rand.nextInt((int)(1.0F + this.distance * 2.0F)) - this.distance);
/* 185 */       int y = (int)(this.theGolem.func_110172_bL().field_71572_b + rand.nextInt((int)(1.0F + this.distance)) - this.distance / 2.0F);
/* 186 */       int z = (int)(this.theGolem.func_110172_bL().field_71573_c + rand.nextInt((int)(1.0F + this.distance * 2.0F)) - this.distance);
/* 187 */       if ((this.theWorld.func_147439_a(x, y, z).func_149688_o() == Material.field_151586_h) && (this.theWorld.func_147437_c(x, y + 1, z)))
/*     */       {
/* 189 */         Vec3 v = Vec3.func_72443_a(x, y, z);
/* 190 */         return v;
/*     */       }
/*     */     }
/* 193 */     return null;
/*     */   }
/*     */   
/* 196 */   private static final List LOOTCRAP = Arrays.asList(new WeightedRandomFishable[] { new WeightedRandomFishable(new ItemStack(Items.field_151021_T), 10).func_150709_a(0.9F), new WeightedRandomFishable(new ItemStack(Items.field_151116_aA), 10), new WeightedRandomFishable(new ItemStack(Items.field_151103_aS), 10), new WeightedRandomFishable(new ItemStack(Items.field_151068_bn), 10), new WeightedRandomFishable(new ItemStack(Items.field_151007_F), 5), new WeightedRandomFishable(new ItemStack(Items.field_151112_aM), 2).func_150709_a(0.9F), new WeightedRandomFishable(new ItemStack(Items.field_151054_z), 10), new WeightedRandomFishable(new ItemStack(Items.field_151055_y), 5), new WeightedRandomFishable(new ItemStack(Items.field_151100_aR, 10, 0), 5), new WeightedRandomFishable(new ItemStack(Blocks.field_150479_bC), 10), new WeightedRandomFishable(new ItemStack(Items.field_151078_bh), 10) });
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 208 */   private static final List LOOTRARE = Arrays.asList(new WeightedRandomFishable[] { new WeightedRandomFishable(new ItemStack(Blocks.field_150392_bi), 1), new WeightedRandomFishable(new ItemStack(Items.field_151057_cb), 1), new WeightedRandomFishable(new ItemStack(Items.field_151141_av), 1), new WeightedRandomFishable(new ItemStack(Items.field_151031_f), 1).func_150709_a(0.25F).func_150707_a(), new WeightedRandomFishable(new ItemStack(Items.field_151112_aM), 1).func_150709_a(0.25F).func_150707_a(), new WeightedRandomFishable(new ItemStack(Items.field_151122_aG), 1).func_150707_a() });
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 215 */   private static final List LOOTFISH = Arrays.asList(new WeightedRandomFishable[] { new WeightedRandomFishable(new ItemStack(Items.field_151115_aP, 1, ItemFishFood.FishType.COD.func_150976_a()), 60), new WeightedRandomFishable(new ItemStack(Items.field_151115_aP, 1, ItemFishFood.FishType.SALMON.func_150976_a()), 25), new WeightedRandomFishable(new ItemStack(Items.field_151115_aP, 1, ItemFishFood.FishType.CLOWNFISH.func_150976_a()), 2), new WeightedRandomFishable(new ItemStack(Items.field_151115_aP, 1, ItemFishFood.FishType.PUFFERFISH.func_150976_a()), 13) });
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private ItemStack getFishingResult()
/*     */   {
/* 224 */     float f = this.theWorld.field_73012_v.nextFloat();
/* 225 */     float f1 = 0.1F - this.theGolem.getUpgradeAmount(5) * 0.025F;
/* 226 */     float f2 = 0.05F + this.theGolem.getUpgradeAmount(4) * 0.0125F;
/*     */     
/* 228 */     int x = (int)this.target.field_72450_a;
/* 229 */     int y = (int)this.target.field_72448_b;
/* 230 */     int z = (int)this.target.field_72449_c;
/* 231 */     for (int a = 2; a <= 5; a++) {
/* 232 */       ForgeDirection dir = ForgeDirection.getOrientation(a);
/* 233 */       if ((this.theWorld.func_147439_a(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ).func_149688_o() == Material.field_151586_h) && (this.theWorld.func_147437_c(x + dir.offsetX, y + 1 + dir.offsetY, z + dir.offsetZ)))
/*     */       {
/* 235 */         f1 -= 0.005F;
/* 236 */         f2 += 0.00125F;
/* 237 */         if (this.theWorld.func_72937_j(x + dir.offsetX, y + 1 + dir.offsetY, z + dir.offsetZ)) {
/* 238 */           f1 -= 0.005F;
/* 239 */           f2 += 0.00125F;
/*     */         }
/* 241 */         for (int depth = 1; depth <= 3; depth++) {
/* 242 */           if (this.theWorld.func_147439_a(x + dir.offsetX, y - depth + dir.offsetY, z + dir.offsetZ).func_149688_o() == Material.field_151586_h) {
/* 243 */             f2 += 0.001F;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 248 */     f1 = MathHelper.func_76131_a(f1, 0.0F, 1.0F);
/* 249 */     f2 = MathHelper.func_76131_a(f2, 0.0F, 1.0F);
/*     */     
/* 251 */     if (f < f1)
/*     */     {
/* 253 */       return ((WeightedRandomFishable)WeightedRandom.func_76271_a(this.theWorld.field_73012_v, LOOTCRAP)).func_150708_a(this.theWorld.field_73012_v);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 258 */     f -= f1;
/*     */     
/* 260 */     if (f < f2)
/*     */     {
/* 262 */       return ((WeightedRandomFishable)WeightedRandom.func_76271_a(this.theWorld.field_73012_v, LOOTRARE)).func_150708_a(this.theWorld.field_73012_v);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 267 */     float f3 = f - f2;
/* 268 */     return ((WeightedRandomFishable)WeightedRandom.func_76271_a(this.theWorld.field_73012_v, LOOTFISH)).func_150708_a(this.theWorld.field_73012_v);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/interact/AIFish.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */