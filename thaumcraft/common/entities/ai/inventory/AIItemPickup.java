/*     */ package thaumcraft.common.entities.ai.inventory;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.entities.InventoryMob;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ 
/*     */ public class AIItemPickup extends net.minecraft.entity.ai.EntityAIBase
/*     */ {
/*     */   private EntityGolemBase theGolem;
/*     */   private Entity targetEntity;
/*     */   
/*     */   public AIItemPickup(EntityGolemBase par1EntityCreature)
/*     */   {
/*  21 */     this.theGolem = par1EntityCreature;
/*  22 */     func_75248_a(3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  30 */     if (this.theGolem.field_70173_aa % thaumcraft.common.config.Config.golemDelay > 0) return false;
/*  31 */     return findItem();
/*     */   }
/*     */   
/*     */   private boolean findItem() {
/*  35 */     double range = Double.MAX_VALUE;
/*  36 */     float dmod = this.theGolem.getRange();
/*  37 */     java.util.List<Entity> targets = this.theGolem.field_70170_p.func_72839_b(this.theGolem, AxisAlignedBB.func_72330_a(this.theGolem.func_110172_bL().field_71574_a, this.theGolem.func_110172_bL().field_71572_b, this.theGolem.func_110172_bL().field_71573_c, this.theGolem.func_110172_bL().field_71574_a + 1, this.theGolem.func_110172_bL().field_71572_b + 1, this.theGolem.func_110172_bL().field_71573_c + 1).func_72314_b(dmod, dmod, dmod));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  42 */     if (targets.size() == 0) return false;
/*  43 */     for (Entity e : targets) {
/*  44 */       if (((e instanceof EntityItem)) && (((EntityItem)e).field_145804_b < 5)) { if (!this.theGolem.inventory.allEmpty()) { if (this.theGolem.inventory.getAmountNeededSmart(((EntityItem)e).func_92059_d(), this.theGolem.getUpgradeAmount(5) > 0) <= 0) {} } else if ((this.theGolem.getCarried() == null) || ((thaumcraft.common.lib.utils.InventoryUtils.areItemStacksEqualStrict(this.theGolem.getCarried(), ((EntityItem)e).func_92059_d())) && (((EntityItem)e).func_92059_d().field_77994_a <= this.theGolem.getCarrySpace())))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*  49 */           double distance = e.func_70092_e(this.theGolem.func_110172_bL().field_71574_a + 0.5F, this.theGolem.func_110172_bL().field_71572_b + 0.5F, this.theGolem.func_110172_bL().field_71573_c + 0.5F);
/*     */           
/*     */ 
/*     */ 
/*  53 */           double distance2 = e.func_70092_e(this.theGolem.field_70165_t, this.theGolem.field_70163_u, this.theGolem.field_70161_v);
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*  58 */           if ((distance2 < range) && (distance <= dmod * dmod)) {
/*  59 */             range = distance2;
/*  60 */             this.targetEntity = e;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*  65 */     if (this.targetEntity == null)
/*     */     {
/*  67 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  71 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  80 */     return (this.count-- > 0) && (!this.theGolem.func_70661_as().func_75500_f()) && (this.targetEntity.func_70089_S());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/*  88 */     this.count = 0;
/*  89 */     this.targetEntity = null;
/*  90 */     this.theGolem.func_70661_as().func_75499_g();
/*     */   }
/*     */   
/*  93 */   int count = 0;
/*     */   
/*     */ 
/*     */   public void func_75246_d()
/*     */   {
/*  98 */     this.theGolem.func_70671_ap().func_75651_a(this.targetEntity, 30.0F, 30.0F);
/*  99 */     double dist = this.theGolem.func_70068_e(this.targetEntity);
/* 100 */     if (dist <= 2.0D)
/*     */     {
/* 102 */       pickUp();
/*     */     }
/*     */   }
/*     */   
/*     */   private void pickUp()
/*     */   {
/* 108 */     int amount = 0;
/* 109 */     if ((this.targetEntity instanceof EntityItem)) {
/* 110 */       ItemStack stack = ((EntityItem)this.targetEntity).func_92059_d().func_77946_l();
/*     */       
/* 112 */       if (((EntityItem)this.targetEntity).func_92059_d().field_77994_a < this.theGolem.getCarrySpace()) {
/* 113 */         amount = ((EntityItem)this.targetEntity).func_92059_d().field_77994_a;
/*     */       } else {
/* 115 */         amount = this.theGolem.getCarrySpace();
/*     */       }
/* 117 */       stack.field_77994_a = amount;
/* 118 */       ((EntityItem)this.targetEntity).func_92059_d().field_77994_a -= amount;
/* 119 */       if (this.theGolem.getCarried() == null) {
/* 120 */         this.theGolem.setCarried(stack);
/*     */       } else {
/* 122 */         this.theGolem.getCarried().field_77994_a += amount;
/*     */       }
/*     */     }
/* 125 */     if (amount == 0) {
/* 126 */       return;
/*     */     }
/* 128 */     this.targetEntity.field_70170_p.func_72956_a(this.targetEntity, "random.pop", 0.2F, ((this.targetEntity.field_70170_p.field_73012_v.nextFloat() - this.targetEntity.field_70170_p.field_73012_v.nextFloat()) * 0.7F + 1.0F) * 2.0F);
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
/* 139 */     this.count = 200;
/* 140 */     this.theGolem.func_70661_as().func_75497_a(this.targetEntity, this.theGolem.func_70689_ay());
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/inventory/AIItemPickup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */