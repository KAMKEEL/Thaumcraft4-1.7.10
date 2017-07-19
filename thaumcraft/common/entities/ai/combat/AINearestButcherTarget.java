/*     */ package thaumcraft.common.entities.ai.combat;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.command.IEntitySelector;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.EntityAITarget;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AINearestButcherTarget
/*     */   extends EntityAITarget
/*     */ {
/*     */   EntityGolemBase theGolem;
/*     */   EntityLivingBase target;
/*     */   int targetChance;
/*     */   private final IEntitySelector entitySelector;
/*  30 */   private float targetDistance = 0.0F;
/*     */   
/*     */ 
/*     */   private AIOldestAttackableTargetSorter theOldestAttackableTargetSorter;
/*     */   
/*     */ 
/*     */ 
/*     */   public AINearestButcherTarget(EntityGolemBase par1EntityLiving, int par4, boolean par5)
/*     */   {
/*  39 */     this(par1EntityLiving, 0.0F, par4, par5, false, (IEntitySelector)null);
/*     */   }
/*     */   
/*     */   public AINearestButcherTarget(EntityGolemBase par1, float par3, int par4, boolean par5, boolean par6, IEntitySelector par7IEntitySelector)
/*     */   {
/*  44 */     super(par1, par5, par6);
/*  45 */     this.theGolem = par1;
/*  46 */     this.targetDistance = 0.0F;
/*  47 */     this.targetChance = par4;
/*  48 */     this.theOldestAttackableTargetSorter = new AIOldestAttackableTargetSorter(this, par1);
/*  49 */     this.entitySelector = par7IEntitySelector;
/*  50 */     func_75248_a(3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  58 */     this.targetDistance = this.theGolem.getRange();
/*  59 */     if ((this.targetChance > 0) && (this.field_75299_d.func_70681_au().nextInt(this.targetChance) != 0))
/*     */     {
/*  61 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  66 */     List var5 = this.field_75299_d.field_70170_p.func_82733_a(EntityLivingBase.class, this.field_75299_d.field_70121_D.func_72314_b(this.targetDistance, 4.0D, this.targetDistance), this.entitySelector);
/*     */     
/*  68 */     Collections.sort(var5, this.theOldestAttackableTargetSorter);
/*  69 */     Iterator var2 = var5.iterator();
/*     */     
/*  71 */     while (var2.hasNext())
/*     */     {
/*  73 */       Entity var3 = (Entity)var2.next();
/*  74 */       EntityLivingBase var4 = (EntityLivingBase)var3;
/*     */       
/*  76 */       if (this.theGolem.isValidTarget(var3))
/*     */       {
/*  78 */         this.target = var4;
/*     */         
/*  80 */         List var55 = this.field_75299_d.field_70170_p.func_82733_a(this.target.getClass(), this.field_75299_d.field_70121_D.func_72314_b(this.targetDistance, 4.0D, this.targetDistance), this.entitySelector);
/*     */         
/*     */ 
/*  83 */         Iterator var22 = var55.iterator();
/*  84 */         int count = 0;
/*  85 */         while (var22.hasNext())
/*     */         {
/*  87 */           Entity var33 = (Entity)var22.next();
/*  88 */           if (this.theGolem.isValidTarget(var33)) { count++;
/*     */           }
/*     */         }
/*  91 */         if (count > 2) {
/*  92 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  98 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/* 108 */     this.field_75299_d.func_70624_b(this.target);
/* 109 */     super.func_75249_e();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/combat/AINearestButcherTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */