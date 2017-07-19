/*    */ package thaumcraft.common.entities.monster;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.enchantment.EnchantmentHelper;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.EntityAILookIdle;
/*    */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*    */ import net.minecraft.entity.ai.EntityAIOpenDoor;
/*    */ import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
/*    */ import net.minecraft.entity.ai.EntityAISwimming;
/*    */ import net.minecraft.entity.ai.EntityAITasks;
/*    */ import net.minecraft.entity.ai.EntityAIWander;
/*    */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.EnumDifficulty;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ import thaumcraft.common.entities.ai.combat.AIAttackOnCollide;
/*    */ import thaumcraft.common.entities.ai.combat.AICultistHurtByTarget;
/*    */ 
/*    */ public class EntityCultistKnight extends EntityCultist
/*    */ {
/*    */   public EntityCultistKnight(World p_i1745_1_)
/*    */   {
/* 29 */     super(p_i1745_1_);
/* 30 */     this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
/* 31 */     this.field_70714_bg.func_75776_a(3, new AIAttackOnCollide(this, EntityLivingBase.class, 1.0D, false));
/* 32 */     this.field_70714_bg.func_75776_a(4, new EntityAIRestrictOpenDoor(this));
/* 33 */     this.field_70714_bg.func_75776_a(5, new EntityAIOpenDoor(this, true));
/* 34 */     this.field_70714_bg.func_75776_a(6, new net.minecraft.entity.ai.EntityAIMoveTowardsRestriction(this, 0.8D));
/* 35 */     this.field_70714_bg.func_75776_a(7, new EntityAIWander(this, 0.8D));
/* 36 */     this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/* 37 */     this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
/* 38 */     this.field_70715_bh.func_75776_a(1, new AICultistHurtByTarget(this, true));
/* 39 */     this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected void func_110147_ax()
/*    */   {
/* 46 */     super.func_110147_ax();
/* 47 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(36.0D);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void func_82164_bB()
/*    */   {
/* 56 */     func_70062_b(4, new ItemStack(ConfigItems.itemHelmetCultistPlate));
/* 57 */     func_70062_b(3, new ItemStack(ConfigItems.itemChestCultistPlate));
/* 58 */     func_70062_b(2, new ItemStack(ConfigItems.itemLegsCultistPlate));
/* 59 */     func_70062_b(1, new ItemStack(ConfigItems.itemBootsCultist));
/*    */     
/* 61 */     if (this.field_70146_Z.nextFloat() < (this.field_70170_p.field_73013_u == EnumDifficulty.HARD ? 0.05F : 0.01F))
/*    */     {
/* 63 */       int i = this.field_70146_Z.nextInt(5);
/* 64 */       if (i == 0)
/*    */       {
/* 66 */         func_70062_b(0, new ItemStack(ConfigItems.itemSwordVoid));
/* 67 */         func_70062_b(4, new ItemStack(ConfigItems.itemHelmetCultistRobe));
/*    */       }
/*    */       else
/*    */       {
/* 71 */         func_70062_b(0, new ItemStack(ConfigItems.itemSwordThaumium));
/* 72 */         if (this.field_70146_Z.nextBoolean()) {
/* 73 */           func_70062_b(4, null);
/*    */         }
/*    */       }
/*    */     } else {
/* 77 */       func_70062_b(0, new ItemStack(Items.field_151040_l));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   protected void func_82162_bC()
/*    */   {
/* 84 */     float f = this.field_70170_p.func_147462_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 85 */     if ((func_70694_bm() != null) && (this.field_70146_Z.nextFloat() < 0.25F * f))
/*    */     {
/* 87 */       EnchantmentHelper.func_77504_a(this.field_70146_Z, func_70694_bm(), (int)(5.0F + f * this.field_70146_Z.nextInt(18)));
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/EntityCultistKnight.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */