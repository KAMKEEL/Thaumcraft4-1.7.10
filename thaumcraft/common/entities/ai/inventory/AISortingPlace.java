/*     */ package thaumcraft.common.entities.ai.inventory;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ import thaumcraft.common.entities.golems.GolemHelper;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ 
/*     */ public class AISortingPlace extends EntityAIBase
/*     */ {
/*     */   private EntityGolemBase theGolem;
/*  19 */   private int countChest = 0;
/*     */   private IInventory inv;
/*     */   private int xx;
/*     */   private int yy;
/*     */   private int zz;
/*     */   
/*     */   public AISortingPlace(EntityGolemBase par1EntityCreature)
/*     */   {
/*  27 */     this.theGolem = par1EntityCreature;
/*  28 */     func_75248_a(3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  36 */     if ((this.theGolem.itemCarried == null) || (!this.theGolem.func_70661_as().func_75500_f())) {
/*  37 */       return false;
/*     */     }
/*     */     
/*  40 */     ChunkCoordinates home = this.theGolem.func_110172_bL();
/*  41 */     ForgeDirection facing = ForgeDirection.getOrientation(this.theGolem.homeFacing);
/*  42 */     int cX = home.field_71574_a - facing.offsetX;
/*  43 */     int cY = home.field_71572_b - facing.offsetY;
/*  44 */     int cZ = home.field_71573_c - facing.offsetZ;
/*     */     
/*  46 */     ArrayList<IInventory> mc = GolemHelper.getMarkedContainersAdjacentToGolem(this.theGolem.field_70170_p, this.theGolem);
/*     */     
/*  48 */     for (Iterator i$ = mc.iterator(); i$.hasNext();) { te = (IInventory)i$.next();
/*  49 */       tile = (TileEntity)te;
/*  50 */       if ((tile != null) && ((tile.field_145851_c != cX) || (tile.field_145848_d != cY) || (tile.field_145849_e != cZ)))
/*     */       {
/*  52 */         for (Integer side : GolemHelper.getMarkedSides(this.theGolem, tile, (byte)-1)) {
/*  53 */           ItemStack is = InventoryUtils.placeItemStackIntoInventory(this.theGolem.itemCarried, te, side.intValue(), false);
/*  54 */           if ((!ItemStack.func_77989_b(is, this.theGolem.itemCarried)) && (InventoryUtils.inventoryContains(te, this.theGolem.itemCarried, side.intValue(), this.theGolem.checkOreDict(), this.theGolem.ignoreDamage(), this.theGolem.ignoreNBT())))
/*     */           {
/*  56 */             this.xx = tile.field_145851_c;
/*  57 */             this.yy = tile.field_145848_d;
/*  58 */             this.zz = tile.field_145849_e;
/*  59 */             return true;
/*     */           }
/*     */         }
/*  62 */         if (InventoryUtils.getDoubleChest(tile) != null) {
/*  63 */           for (Integer side : GolemHelper.getMarkedSides(this.theGolem, tile, (byte)-1)) {
/*  64 */             ItemStack is = InventoryUtils.placeItemStackIntoInventory(this.theGolem.itemCarried, InventoryUtils.getDoubleChest(tile), side.intValue(), false);
/*  65 */             if ((!ItemStack.func_77989_b(is, this.theGolem.itemCarried)) && (InventoryUtils.inventoryContains(te, this.theGolem.itemCarried, side.intValue(), this.theGolem.checkOreDict(), this.theGolem.ignoreDamage(), this.theGolem.ignoreNBT())))
/*     */             {
/*  67 */               this.xx = tile.field_145851_c;
/*  68 */               this.yy = tile.field_145848_d;
/*  69 */               this.zz = tile.field_145849_e;
/*  70 */               return true;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     IInventory te;
/*     */     TileEntity tile;
/*  78 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  87 */     return (this.count > 0) && ((func_75250_a()) || (this.countChest > 0));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/*     */     try
/*     */     {
/*  96 */       if ((this.inv != null) && (Config.golemChestInteract)) this.inv.func_70305_f();
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/* 101 */   int count = 0;
/*     */   
/*     */   public void func_75246_d()
/*     */   {
/* 105 */     this.countChest -= 1;
/* 106 */     this.count -= 1;
/* 107 */     super.func_75246_d();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/* 115 */     this.count = 200;
/* 116 */     ChunkCoordinates home = this.theGolem.func_110172_bL();
/* 117 */     ForgeDirection facing = ForgeDirection.getOrientation(this.theGolem.homeFacing);
/* 118 */     int cX = home.field_71574_a - facing.offsetX;
/* 119 */     int cY = home.field_71572_b - facing.offsetY;
/* 120 */     int cZ = home.field_71573_c - facing.offsetZ;
/*     */     
/* 122 */     TileEntity tile = this.theGolem.field_70170_p.func_147438_o(this.xx, this.yy, this.zz);
/* 123 */     IInventory te; Iterator i$; if ((tile != null) && ((tile.field_145851_c != cX) || (tile.field_145848_d != cY) || (tile.field_145849_e != cZ)))
/*     */     {
/* 125 */       te = (IInventory)tile;
/* 126 */       ArrayList<Byte> matchingColors = this.theGolem.getColorsMatching(this.theGolem.itemCarried);
/* 127 */       for (i$ = matchingColors.iterator(); i$.hasNext();) { byte color = ((Byte)i$.next()).byteValue();
/* 128 */         for (Integer side : GolemHelper.getMarkedSides(this.theGolem, tile, color)) {
/* 129 */           this.theGolem.itemCarried = InventoryUtils.placeItemStackIntoInventory(this.theGolem.itemCarried, te, side.intValue(), true);
/* 130 */           this.countChest = 5;
/* 131 */           this.inv = ((IInventory)tile);
/* 132 */           if (this.theGolem.itemCarried == null) break;
/*     */         }
/* 134 */         if ((InventoryUtils.getDoubleChest(tile) != null) && (this.theGolem.itemCarried != null))
/* 135 */           for (Integer side : GolemHelper.getMarkedSides(this.theGolem, tile, color)) {
/* 136 */             ItemStack is = InventoryUtils.placeItemStackIntoInventory(this.theGolem.itemCarried, InventoryUtils.getDoubleChest(tile), side.intValue(), false);
/* 137 */             if (!ItemStack.func_77989_b(is, this.theGolem.itemCarried)) {
/* 138 */               this.theGolem.itemCarried = InventoryUtils.placeItemStackIntoInventory(this.theGolem.itemCarried, InventoryUtils.getDoubleChest(tile), side.intValue(), true);
/* 139 */               this.countChest = 5;
/* 140 */               this.inv = InventoryUtils.getDoubleChest(tile);
/* 141 */               if (this.theGolem.itemCarried == null)
/*     */                 break;
/*     */             }
/*     */           }
/* 145 */         if (this.countChest == 5) {
/*     */           try {
/* 147 */             if (Config.golemChestInteract) { ((IInventory)tile).func_70295_k_();
/*     */             }
/*     */           }
/*     */           catch (Exception e) {}
/*     */         }
/*     */       }
/*     */     }
/* 154 */     this.theGolem.updateCarried();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/inventory/AISortingPlace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */