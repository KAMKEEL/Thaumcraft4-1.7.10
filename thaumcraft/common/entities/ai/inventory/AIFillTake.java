/*     */ package thaumcraft.common.entities.ai.inventory;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ import thaumcraft.common.entities.golems.GolemHelper;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ 
/*     */ public class AIFillTake extends net.minecraft.entity.ai.EntityAIBase
/*     */ {
/*     */   private EntityGolemBase theGolem;
/*  19 */   private int countChest = 0;
/*     */   private IInventory inv;
/*     */   
/*     */   public AIFillTake(EntityGolemBase par1EntityCreature)
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
/*  34 */     if ((this.theGolem.getCarried() != null) || (this.theGolem.itemWatched == null) || (!this.theGolem.func_70661_as().func_75500_f()) || (!this.theGolem.hasSomething()))
/*     */     {
/*     */ 
/*  37 */       return false;
/*     */     }
/*     */     
/*  40 */     ForgeDirection facing = ForgeDirection.getOrientation(this.theGolem.homeFacing);
/*  41 */     ChunkCoordinates home = this.theGolem.func_110172_bL();
/*  42 */     int cX = home.field_71574_a - facing.offsetX;
/*  43 */     int cY = home.field_71572_b - facing.offsetY;
/*  44 */     int cZ = home.field_71573_c - facing.offsetZ;
/*     */     
/*     */ 
/*  47 */     ArrayList<IInventory> mc = GolemHelper.getMarkedContainersAdjacentToGolem(this.theGolem.field_70170_p, this.theGolem);
/*     */     
/*     */ 
/*  50 */     for (Iterator i$ = mc.iterator(); i$.hasNext();) { te = (IInventory)i$.next();
/*  51 */       tile = (TileEntity)te;
/*  52 */       if ((tile != null) && ((tile.field_145851_c != cX) || (tile.field_145848_d != cY) || (tile.field_145849_e != cZ)))
/*     */       {
/*     */ 
/*  55 */         ArrayList<Byte> matchingColors = this.theGolem.getColorsMatching(this.theGolem.itemWatched);
/*  56 */         for (i$ = matchingColors.iterator(); i$.hasNext();) { byte color = ((Byte)i$.next()).byteValue();
/*     */           
/*  58 */           for (Integer side : GolemHelper.getMarkedSides(this.theGolem, tile, color)) {
/*  59 */             ItemStack target = this.theGolem.itemWatched.func_77946_l();
/*     */             
/*  61 */             target.field_77994_a = (this.theGolem.getToggles()[0] != 0 ? this.theGolem.getCarrySpace() : Math.min(target.field_77994_a, this.theGolem.getCarrySpace()));
/*  62 */             ItemStack result = InventoryUtils.extractStack(te, target, side.intValue(), this.theGolem.checkOreDict(), this.theGolem.ignoreDamage(), this.theGolem.ignoreNBT(), true);
/*     */             
/*  64 */             if ((result == null) && (InventoryUtils.getDoubleChest(tile) != null)) {
/*  65 */               result = InventoryUtils.extractStack(InventoryUtils.getDoubleChest(tile), target, side.intValue(), this.theGolem.checkOreDict(), this.theGolem.ignoreDamage(), this.theGolem.ignoreNBT(), true);
/*     */             }
/*     */             
/*  68 */             if (result != null) {
/*  69 */               this.theGolem.setCarried(result);
/*     */               try {
/*  71 */                 if (Config.golemChestInteract) this.inv.func_70295_k_();
/*     */               }
/*     */               catch (Exception e) {}
/*  74 */               this.countChest = 5;
/*  75 */               this.count = 200;
/*  76 */               this.theGolem.itemWatched = null;
/*  77 */               this.theGolem.updateCarried();
/*  78 */               return true;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     IInventory te;
/*     */     TileEntity tile;
/*     */     Iterator i$;
/*  87 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  95 */     return (this.count > 0) && ((!this.theGolem.func_70661_as().func_75500_f()) || (this.countChest > 0));
/*     */   }
/*     */   
/*  98 */   int count = 0;
/*     */   
/*     */   public void func_75246_d() {
/* 101 */     this.count -= 1;
/* 102 */     this.countChest -= 1;
/* 103 */     super.func_75246_d();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/*     */     try
/*     */     {
/* 112 */       if ((this.inv != null) && (Config.golemChestInteract)) this.inv.func_70305_f();
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */   public void func_75249_e() {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/inventory/AIFillTake.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */