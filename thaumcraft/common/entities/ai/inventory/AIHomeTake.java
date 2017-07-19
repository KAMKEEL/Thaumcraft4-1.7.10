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
/*     */ public class AIHomeTake extends EntityAIBase
/*     */ {
/*     */   private EntityGolemBase theGolem;
/*  20 */   private int countChest = 0;
/*     */   private IInventory inv;
/*     */   
/*     */   public AIHomeTake(EntityGolemBase par1EntityCreature)
/*     */   {
/*  25 */     this.theGolem = par1EntityCreature;
/*  26 */     func_75248_a(3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  35 */     ChunkCoordinates home = this.theGolem.func_110172_bL();
/*     */     
/*  37 */     if ((this.theGolem.getCarried() != null) || (this.theGolem.field_70173_aa % Config.golemDelay > 0) || (!this.theGolem.func_70661_as().func_75500_f()) || (this.theGolem.func_70092_e(home.field_71574_a + 0.5F, home.field_71572_b + 0.5F, home.field_71573_c + 0.5F) > 5.0D))
/*     */     {
/*  39 */       return false;
/*     */     }
/*     */     
/*  42 */     ForgeDirection facing = ForgeDirection.getOrientation(this.theGolem.homeFacing);
/*  43 */     int cX = home.field_71574_a - facing.offsetX;
/*  44 */     int cY = home.field_71572_b - facing.offsetY;
/*  45 */     int cZ = home.field_71573_c - facing.offsetZ;
/*     */     
/*  47 */     TileEntity tile = this.theGolem.field_70170_p.func_147438_o(cX, cY, cZ);
/*     */     
/*  49 */     boolean repeat = true;
/*  50 */     boolean didRepeat = false;
/*  51 */     while (repeat) {
/*  52 */       if (didRepeat) { repeat = false;
/*     */       }
/*  54 */       if ((tile != null) && ((tile instanceof IInventory))) {
/*  55 */         ArrayList<ItemStack> neededList = GolemHelper.getItemsNeeded(this.theGolem, this.theGolem.getUpgradeAmount(5) > 0);
/*     */         
/*  57 */         if (neededList != null) {
/*  58 */           if (neededList.size() > 0) {
/*  59 */             for (ItemStack stack : neededList)
/*  60 */               if (GolemHelper.validTargetForItem(this.theGolem, stack)) {
/*  61 */                 ItemStack needed = stack.func_77946_l();
/*  62 */                 needed.field_77994_a = this.theGolem.getCarrySpace();
/*  63 */                 ItemStack result = InventoryUtils.extractStack((IInventory)tile, needed, facing.ordinal(), this.theGolem.checkOreDict(), this.theGolem.ignoreDamage(), this.theGolem.ignoreNBT(), true);
/*  64 */                 if (result != null) {
/*  65 */                   this.theGolem.setCarried(result);
/*     */                   try {
/*  67 */                     if (Config.golemChestInteract) ((IInventory)tile).func_70295_k_();
/*     */                   }
/*     */                   catch (Exception e) {}
/*  70 */                   this.countChest = 5;
/*  71 */                   this.inv = ((IInventory)tile);
/*  72 */                   return true;
/*     */                 }
/*     */               }
/*     */           }
/*     */         } else {
/*     */           for (;;) {
/*  78 */             ItemStack is = GolemHelper.getFirstItemUsingTimeout(this.theGolem, (IInventory)tile, facing.ordinal(), false);
/*  79 */             if ((is != null) && (GolemHelper.validTargetForItem(this.theGolem, is))) {
/*  80 */               ItemStack result = GolemHelper.getFirstItemUsingTimeout(this.theGolem, (IInventory)tile, facing.ordinal(), true);
/*  81 */               this.theGolem.setCarried(result);
/*     */               try {
/*  83 */                 if (Config.golemChestInteract) ((IInventory)tile).func_70295_k_();
/*     */               }
/*     */               catch (Exception e) {}
/*  86 */               this.countChest = 5;
/*  87 */               this.inv = ((IInventory)tile);
/*  88 */               return true;
/*     */             }
/*  90 */             if (is == null) return false;
/*     */           }
/*     */         }
/*     */       }
/*  94 */       if ((!didRepeat) && (InventoryUtils.getDoubleChest(tile) != null)) {
/*  95 */         tile = InventoryUtils.getDoubleChest(tile);
/*  96 */         didRepeat = true;
/*  97 */       } else { repeat = false;
/*     */       }
/*     */     }
/* 100 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/* 110 */     return this.countChest > 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/*     */     try
/*     */     {
/* 120 */       if ((this.inv != null) && (Config.golemChestInteract)) { this.inv.func_70305_f();
/*     */       }
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */   public void func_75246_d()
/*     */   {
/* 128 */     this.countChest -= 1;
/* 129 */     super.func_75246_d();
/*     */   }
/*     */   
/*     */   public void func_75249_e() {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/inventory/AIHomeTake.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */