/*     */ package thaumcraft.common.entities.ai.fluid;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.RandomPositionGenerator;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ import thaumcraft.common.entities.golems.GolemHelper;
/*     */ 
/*     */ 
/*     */ public class AILiquidGoto
/*     */   extends EntityAIBase
/*     */ {
/*     */   private EntityGolemBase theGolem;
/*     */   private double waterX;
/*     */   private double waterY;
/*     */   private double waterZ;
/*     */   private World theWorld;
/*     */   
/*     */   public AILiquidGoto(EntityGolemBase par1EntityCreature)
/*     */   {
/*  29 */     this.theGolem = par1EntityCreature;
/*  30 */     this.theWorld = par1EntityCreature.field_70170_p;
/*  31 */     func_75248_a(3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  39 */     if ((this.theGolem.field_70173_aa % Config.golemDelay > 0) || ((this.theGolem.fluidCarried != null) && (this.theGolem.fluidCarried.amount > this.theGolem.getFluidCarryLimit() - 1000)))
/*     */     {
/*     */ 
/*  42 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  47 */     ArrayList<FluidStack> fluids = GolemHelper.getMissingLiquids(this.theGolem);
/*     */     
/*  49 */     for (FluidStack fluid : fluids) {
/*  50 */       Vec3 var1 = GolemHelper.findPossibleLiquid(fluid, this.theGolem);
/*     */       
/*  52 */       if (var1 != null)
/*     */       {
/*  54 */         this.theGolem.itemWatched = new ItemStack(Item.func_150899_d(fluid.fluidID), 1, fluid.amount);
/*  55 */         this.waterX = var1.field_72450_a;
/*  56 */         this.waterY = var1.field_72448_b;
/*  57 */         this.waterZ = var1.field_72449_c;
/*  58 */         double dd = this.theGolem.func_70011_f(this.waterX, this.waterY, this.waterZ);
/*  59 */         for (int xx = -1; xx <= 1; xx++)
/*  60 */           for (int zz = -1; zz <= 1; zz++) {
/*  61 */             double dd2 = this.theGolem.func_70011_f(var1.field_72450_a + xx, this.waterY, var1.field_72449_c + zz);
/*  62 */             if ((dd2 < dd) && (this.theGolem.field_70170_p.func_147445_c((int)var1.field_72450_a + xx, (int)this.waterY, (int)var1.field_72449_c + zz, true)))
/*     */             {
/*  64 */               this.waterX = (var1.field_72450_a + xx);
/*  65 */               this.waterZ = (var1.field_72449_c + zz);
/*  66 */               dd = dd2;
/*     */             }
/*     */           }
/*  69 */         return true;
/*     */       }
/*     */     }
/*     */     
/*  73 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  82 */     return (this.count > 0) && (!this.theGolem.func_70661_as().func_75500_f());
/*     */   }
/*     */   
/*     */   public void func_75251_c()
/*     */   {
/*  87 */     this.count = 0;
/*     */   }
/*     */   
/*     */ 
/*  91 */   int count = 0;
/*  92 */   int prevX = 0;
/*  93 */   int prevY = 0;
/*  94 */   int prevZ = 0;
/*     */   
/*     */   public void func_75246_d()
/*     */   {
/*  98 */     this.count -= 1;
/*  99 */     if ((this.count == 0) && (this.prevX == MathHelper.func_76128_c(this.theGolem.field_70165_t)) && (this.prevY == MathHelper.func_76128_c(this.theGolem.field_70163_u)) && (this.prevZ == MathHelper.func_76128_c(this.theGolem.field_70161_v))) {
/* 100 */       Vec3 var2 = RandomPositionGenerator.func_75463_a(this.theGolem, 2, 1);
/*     */       
/* 102 */       if (var2 != null)
/*     */       {
/* 104 */         this.count = 20;
/* 105 */         this.theGolem.func_70661_as().func_75492_a(var2.field_72450_a, var2.field_72448_b, var2.field_72449_c, this.theGolem.func_70689_ay());
/*     */       }
/*     */     }
/* 108 */     super.func_75246_d();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/* 116 */     this.count = 200;
/* 117 */     this.prevX = MathHelper.func_76128_c(this.theGolem.field_70165_t);
/* 118 */     this.prevY = MathHelper.func_76128_c(this.theGolem.field_70163_u);
/* 119 */     this.prevZ = MathHelper.func_76128_c(this.theGolem.field_70161_v);
/* 120 */     this.theGolem.func_70661_as().func_75492_a(this.waterX, this.waterY, this.waterZ, this.theGolem.func_70689_ay());
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/fluid/AILiquidGoto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */