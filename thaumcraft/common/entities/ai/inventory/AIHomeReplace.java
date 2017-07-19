/*     */ package thaumcraft.common.entities.ai.inventory;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ import thaumcraft.common.entities.golems.GolemHelper;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ 
/*     */ public class AIHomeReplace extends EntityAIBase
/*     */ {
/*     */   private EntityGolemBase theGolem;
/*  20 */   private int countChest = 0;
/*     */   private IInventory inv;
/*     */   
/*     */   public AIHomeReplace(EntityGolemBase par1EntityCreature)
/*     */   {
/*  25 */     this.theGolem = par1EntityCreature;
/*  26 */     func_75248_a(3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  34 */     ChunkCoordinates home = this.theGolem.func_110172_bL();
/*     */     
/*  36 */     if ((this.theGolem.getCarried() == null) || (this.theGolem.field_70173_aa % Config.golemDelay > 0) || (!this.theGolem.func_70661_as().func_75500_f()) || (this.theGolem.func_70092_e(home.field_71574_a + 0.5F, home.field_71572_b + 0.5F, home.field_71573_c + 0.5F) > 5.0D))
/*     */     {
/*     */ 
/*  39 */       return false;
/*     */     }
/*     */     
/*  42 */     ForgeDirection facing = ForgeDirection.getOrientation(this.theGolem.homeFacing);
/*  43 */     int cX = home.field_71574_a - facing.offsetX;
/*  44 */     int cY = home.field_71572_b - facing.offsetY;
/*  45 */     int cZ = home.field_71573_c - facing.offsetZ;
/*  46 */     if (GolemHelper.isOnTimeOut(this.theGolem, this.theGolem.getCarried())) return true;
/*  47 */     switch (this.theGolem.getCore()) {
/*     */     case 1: 
/*  49 */       return !GolemHelper.findSomethingEmptyCore(this.theGolem, this.theGolem.getCarried());
/*     */     case 8: 
/*  51 */       return !GolemHelper.findSomethingUseCore(this.theGolem, this.theGolem.getCarried());
/*     */     case 10: 
/*  53 */       return !GolemHelper.findSomethingSortCore(this.theGolem, this.theGolem.getCarried());
/*     */     }
/*  55 */     TileEntity tile = this.theGolem.field_70170_p.func_147438_o(cX, cY, cZ);
/*  56 */     ArrayList<ItemStack> neededList = GolemHelper.getItemsNeeded(this.theGolem, this.theGolem.getUpgradeAmount(5) > 0);
/*  57 */     if ((neededList != null) && (neededList.size() > 0)) {
/*  58 */       for (ItemStack stack : neededList) {
/*  59 */         if (InventoryUtils.areItemStacksEqual(stack, this.theGolem.itemCarried, this.theGolem.checkOreDict(), this.theGolem.ignoreDamage(), this.theGolem.ignoreNBT()))
/*     */         {
/*  61 */           return false;
/*     */         }
/*     */       }
/*  64 */       return true;
/*     */     }
/*     */     
/*     */ 
/*  68 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  76 */     return (func_75250_a()) || (this.countChest > 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/*     */     try
/*     */     {
/*  85 */       if ((this.inv != null) && (Config.golemChestInteract)) { this.inv.func_70305_f();
/*     */       }
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_75246_d()
/*     */   {
/*  94 */     this.countChest -= 1;
/*     */     
/*  96 */     super.func_75246_d();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/* 105 */     ChunkCoordinates home = this.theGolem.func_110172_bL();
/* 106 */     ForgeDirection facing = ForgeDirection.getOrientation(this.theGolem.homeFacing);
/* 107 */     int cX = home.field_71574_a - facing.offsetX;
/* 108 */     int cY = home.field_71572_b - facing.offsetY;
/* 109 */     int cZ = home.field_71573_c - facing.offsetZ;
/*     */     
/* 111 */     TileEntity tile = this.theGolem.field_70170_p.func_147438_o(cX, cY, cZ);
/* 112 */     boolean repeat = true;
/* 113 */     boolean didRepeat = false;
/* 114 */     while (repeat) {
/* 115 */       if (didRepeat) { repeat = false;
/*     */       }
/* 117 */       if ((tile != null) && ((tile instanceof IInventory))) {
/* 118 */         ItemStack result = InventoryUtils.placeItemStackIntoInventory(this.theGolem.getCarried(), (IInventory)tile, facing.ordinal(), true);
/*     */         
/*     */ 
/* 121 */         if (!ItemStack.func_77989_b(result, this.theGolem.itemCarried)) {
/* 122 */           this.theGolem.setCarried(result);
/*     */           try {
/* 124 */             if (Config.golemChestInteract) ((IInventory)tile).func_70295_k_();
/*     */           }
/*     */           catch (Exception e) {}
/* 127 */           this.countChest = 5;
/* 128 */           this.inv = ((IInventory)tile);
/* 129 */           break;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 134 */       if ((!didRepeat) && (InventoryUtils.getDoubleChest(tile) != null)) {
/* 135 */         tile = InventoryUtils.getDoubleChest(tile);
/* 136 */         didRepeat = true;
/* 137 */       } else { repeat = false;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/inventory/AIHomeReplace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */