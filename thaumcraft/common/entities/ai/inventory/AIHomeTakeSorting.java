/*     */ package thaumcraft.common.entities.ai.inventory;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ import thaumcraft.common.entities.golems.GolemHelper;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ 
/*     */ public class AIHomeTakeSorting extends EntityAIBase
/*     */ {
/*     */   private EntityGolemBase theGolem;
/*  19 */   private int countChest = 0;
/*     */   private IInventory inv;
/*     */   
/*     */   public AIHomeTakeSorting(EntityGolemBase par1EntityCreature)
/*     */   {
/*  24 */     this.theGolem = par1EntityCreature;
/*  25 */     func_75248_a(3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  34 */     ChunkCoordinates home = this.theGolem.func_110172_bL();
/*     */     
/*  36 */     if ((this.theGolem.getCarried() != null) || (this.theGolem.field_70173_aa % Config.golemDelay > 0) || (!this.theGolem.func_70661_as().func_75500_f()) || (this.theGolem.func_70092_e(home.field_71574_a + 0.5F, home.field_71572_b + 0.5F, home.field_71573_c + 0.5F) > 5.0D))
/*     */     {
/*  38 */       return false;
/*     */     }
/*     */     
/*  41 */     ForgeDirection facing = ForgeDirection.getOrientation(this.theGolem.homeFacing);
/*  42 */     int cX = home.field_71574_a - facing.offsetX;
/*  43 */     int cY = home.field_71572_b - facing.offsetY;
/*  44 */     int cZ = home.field_71573_c - facing.offsetZ;
/*     */     
/*  46 */     TileEntity tile = this.theGolem.field_70170_p.func_147438_o(cX, cY, cZ);
/*     */     
/*  48 */     boolean repeat = true;
/*  49 */     boolean didRepeat = false;
/*  50 */     while (repeat) {
/*  51 */       if (didRepeat) { repeat = false;
/*     */       }
/*  53 */       if ((tile != null) && ((tile instanceof IInventory)))
/*     */       {
/*  55 */         ArrayList<ItemStack> neededList = GolemHelper.getItemsNeeded(this.theGolem, this.theGolem.getUpgradeAmount(5) > 0);
/*     */         
/*  57 */         if ((neededList != null) && (neededList.size() > 0)) {
/*  58 */           for (ItemStack stack : neededList) {
/*  59 */             ItemStack needed = stack.func_77946_l();
/*  60 */             needed.field_77994_a = this.theGolem.getCarrySpace();
/*  61 */             if (InventoryUtils.extractStack((IInventory)tile, needed, facing.ordinal(), this.theGolem.checkOreDict(), this.theGolem.ignoreDamage(), this.theGolem.ignoreNBT(), false) != null) {
/*  62 */               return true;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*  67 */       if ((!didRepeat) && (InventoryUtils.getDoubleChest(tile) != null)) {
/*  68 */         tile = InventoryUtils.getDoubleChest(tile);
/*  69 */         didRepeat = true;
/*  70 */       } else { repeat = false;
/*     */       }
/*     */     }
/*  73 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  83 */     return (this.count > 0) && ((func_75250_a()) || (this.countChest > 0));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/*     */     try
/*     */     {
/*  93 */       if ((this.inv != null) && (Config.golemChestInteract)) this.inv.func_70305_f();
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*  98 */   int count = 0;
/*     */   
/*     */   public void func_75246_d()
/*     */   {
/* 102 */     this.countChest -= 1;
/* 103 */     this.count -= 1;
/* 104 */     super.func_75246_d();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/* 113 */     this.count = 200;
/* 114 */     ChunkCoordinates home = this.theGolem.func_110172_bL();
/* 115 */     ForgeDirection facing = ForgeDirection.getOrientation(this.theGolem.homeFacing);
/* 116 */     int cX = home.field_71574_a - facing.offsetX;
/* 117 */     int cY = home.field_71572_b - facing.offsetY;
/* 118 */     int cZ = home.field_71573_c - facing.offsetZ;
/*     */     
/* 120 */     TileEntity tile = this.theGolem.field_70170_p.func_147438_o(cX, cY, cZ);
/* 121 */     boolean repeat = true;
/* 122 */     boolean didRepeat = false;
/* 123 */     while (repeat) {
/* 124 */       if (didRepeat) { repeat = false;
/*     */       }
/* 126 */       if ((tile != null) && ((tile instanceof IInventory))) {
/* 127 */         ArrayList<ItemStack> neededList = GolemHelper.getItemsNeeded(this.theGolem, this.theGolem.getUpgradeAmount(5) > 0);
/* 128 */         if ((neededList != null) && (neededList.size() > 0)) {
/* 129 */           for (ItemStack stack : neededList) {
/* 130 */             ItemStack needed = stack.func_77946_l();
/* 131 */             needed.field_77994_a = this.theGolem.getCarrySpace();
/* 132 */             ItemStack result = InventoryUtils.extractStack((IInventory)tile, needed, facing.ordinal(), this.theGolem.checkOreDict(), this.theGolem.ignoreDamage(), this.theGolem.ignoreNBT(), true);
/* 133 */             if (result != null)
/*     */             {
/* 135 */               this.theGolem.setCarried(result);
/*     */               try {
/* 137 */                 if (Config.golemChestInteract) ((IInventory)tile).func_70295_k_();
/*     */               }
/*     */               catch (Exception e) {}
/* 140 */               this.countChest = 5;
/* 141 */               this.inv = ((IInventory)tile);
/*     */             }
/*     */           }
/*     */         }
/* 145 */         if (this.theGolem.getCarried() != null)
/*     */           break;
/*     */       }
/* 148 */       if ((!didRepeat) && (InventoryUtils.getDoubleChest(tile) != null)) {
/* 149 */         tile = InventoryUtils.getDoubleChest(tile);
/* 150 */         didRepeat = true;
/* 151 */       } else { repeat = false;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/inventory/AIHomeTakeSorting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */