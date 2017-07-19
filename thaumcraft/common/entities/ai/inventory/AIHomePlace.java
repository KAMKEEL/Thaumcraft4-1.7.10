/*     */ package thaumcraft.common.entities.ai.inventory;
/*     */ 
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ 
/*     */ public class AIHomePlace extends EntityAIBase
/*     */ {
/*     */   private EntityGolemBase theGolem;
/*  16 */   private int countChest = 0;
/*     */   private IInventory inv;
/*     */   
/*     */   public AIHomePlace(EntityGolemBase par1EntityCreature)
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
/*  30 */     ChunkCoordinates home = this.theGolem.func_110172_bL();
/*     */     
/*  32 */     if ((this.theGolem.getCarried() == null) || (this.theGolem.field_70173_aa % Config.golemDelay > 0) || (!this.theGolem.func_70661_as().func_75500_f()) || (this.theGolem.func_70092_e(home.field_71574_a + 0.5F, home.field_71572_b + 0.5F, home.field_71573_c + 0.5F) > 5.0D))
/*     */     {
/*     */ 
/*  35 */       return false;
/*     */     }
/*     */     
/*  38 */     ForgeDirection facing = ForgeDirection.getOrientation(this.theGolem.homeFacing);
/*  39 */     int cX = home.field_71574_a - facing.offsetX;
/*  40 */     int cY = home.field_71572_b - facing.offsetY;
/*  41 */     int cZ = home.field_71573_c - facing.offsetZ;
/*     */     
/*  43 */     net.minecraft.tileentity.TileEntity tile = this.theGolem.field_70170_p.func_147438_o(cX, cY, cZ);
/*     */     
/*  45 */     boolean repeat = true;
/*  46 */     boolean didRepeat = false;
/*  47 */     while (repeat) {
/*  48 */       if (didRepeat) { repeat = false;
/*     */       }
/*  50 */       if ((tile != null) && ((tile instanceof IInventory))) {
/*  51 */         ItemStack result = InventoryUtils.placeItemStackIntoInventory(this.theGolem.getCarried(), (IInventory)tile, facing.ordinal(), false);
/*     */         
/*     */ 
/*     */ 
/*  55 */         if (!ItemStack.func_77989_b(result, this.theGolem.itemCarried)) {
/*  56 */           return true;
/*     */         }
/*     */       }
/*  59 */       if ((!didRepeat) && (InventoryUtils.getDoubleChest(tile) != null)) {
/*  60 */         tile = InventoryUtils.getDoubleChest(tile);
/*  61 */         didRepeat = true;
/*  62 */       } else { repeat = false;
/*     */       }
/*     */     }
/*  65 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  74 */     return (func_75250_a()) || (this.countChest > 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/*     */     try
/*     */     {
/*  83 */       if ((this.inv != null) && (Config.golemChestInteract)) { this.inv.func_70305_f();
/*     */       }
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_75246_d()
/*     */   {
/*  92 */     this.countChest -= 1;
/*     */     
/*  94 */     super.func_75246_d();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/* 103 */     ChunkCoordinates home = this.theGolem.func_110172_bL();
/* 104 */     ForgeDirection facing = ForgeDirection.getOrientation(this.theGolem.homeFacing);
/* 105 */     int cX = home.field_71574_a - facing.offsetX;
/* 106 */     int cY = home.field_71572_b - facing.offsetY;
/* 107 */     int cZ = home.field_71573_c - facing.offsetZ;
/*     */     
/* 109 */     net.minecraft.tileentity.TileEntity tile = this.theGolem.field_70170_p.func_147438_o(cX, cY, cZ);
/* 110 */     boolean repeat = true;
/* 111 */     boolean didRepeat = false;
/* 112 */     while (repeat) {
/* 113 */       if (didRepeat) { repeat = false;
/*     */       }
/* 115 */       if ((tile != null) && ((tile instanceof IInventory))) {
/* 116 */         ItemStack result = InventoryUtils.placeItemStackIntoInventory(this.theGolem.getCarried(), (IInventory)tile, facing.ordinal(), true);
/*     */         
/*     */ 
/* 119 */         if (!ItemStack.func_77989_b(result, this.theGolem.itemCarried)) {
/* 120 */           this.theGolem.setCarried(result);
/*     */           try {
/* 122 */             if (Config.golemChestInteract) ((IInventory)tile).func_70295_k_();
/*     */           }
/*     */           catch (Exception e) {}
/* 125 */           this.countChest = 5;
/* 126 */           this.inv = ((IInventory)tile);
/* 127 */           break;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 132 */       if ((!didRepeat) && (InventoryUtils.getDoubleChest(tile) != null)) {
/* 133 */         tile = InventoryUtils.getDoubleChest(tile);
/* 134 */         didRepeat = true;
/* 135 */       } else { repeat = false;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/inventory/AIHomePlace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */