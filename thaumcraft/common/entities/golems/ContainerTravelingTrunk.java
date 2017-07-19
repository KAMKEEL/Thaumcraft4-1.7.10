/*     */ package thaumcraft.common.entities.golems;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ContainerTravelingTrunk extends Container
/*     */ {
/*     */   private InventoryTrunk mobInv;
/*     */   private EntityTravelingTrunk trunk;
/*     */   private int numRows;
/*     */   
/*     */   public ContainerTravelingTrunk(net.minecraft.inventory.IInventory iinventory, World par3World, EntityTravelingTrunk trunk)
/*     */   {
/*  17 */     this.trunk = trunk;
/*  18 */     this.mobInv = trunk.inventory;
/*  19 */     this.numRows = trunk.getRows();
/*  20 */     for (int j = 0; j < this.numRows; j++) {
/*  21 */       for (int i1 = 0; i1 < 9; i1++) {
/*  22 */         func_75146_a(new Slot(this.mobInv, i1 + j * 9, 8 + i1 * 18, 15 + j * 23));
/*     */       }
/*     */     }
/*     */     
/*  26 */     for (int k = 0; k < 3; k++) {
/*  27 */       for (int j1 = 0; j1 < 9; j1++) {
/*  28 */         func_75146_a(new Slot(iinventory, j1 + k * 9 + 9, 8 + j1 * 18, 118 + k * 18));
/*     */       }
/*     */     }
/*     */     
/*  32 */     for (int l = 0; l < 9; l++) {
/*  33 */       func_75146_a(new Slot(iinventory, l, 8 + l * 18, 176));
/*     */     }
/*     */     
/*  36 */     trunk.setOpen(true);
/*  37 */     trunk.field_70170_p.func_72956_a(trunk, "random.chestopen", 0.5F, trunk.field_70170_p.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_75140_a(EntityPlayer par1EntityPlayer, int button)
/*     */   {
/*  43 */     if (button == 1) {
/*  44 */       this.trunk.setStay(!this.trunk.getStay());
/*  45 */       return true;
/*     */     }
/*     */     
/*  48 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2)
/*     */   {
/*  58 */     ItemStack itemstack = null;
/*  59 */     Slot slot = (Slot)this.field_75151_b.get(par2);
/*     */     
/*  61 */     if ((slot != null) && (slot.func_75216_d()))
/*     */     {
/*  63 */       ItemStack itemstack1 = slot.func_75211_c();
/*  64 */       itemstack = itemstack1.func_77946_l();
/*     */       
/*  66 */       if (par2 < this.numRows * 9)
/*     */       {
/*  68 */         if (!func_75135_a(itemstack1, this.numRows * 9, this.field_75151_b.size(), true))
/*     */         {
/*  70 */           return null;
/*     */         }
/*     */       }
/*  73 */       else if (!func_75135_a(itemstack1, 0, this.numRows * 9, false))
/*     */       {
/*  75 */         return null;
/*     */       }
/*     */       
/*  78 */       if (itemstack1.field_77994_a == 0)
/*     */       {
/*  80 */         slot.func_75215_d((ItemStack)null);
/*     */       }
/*     */       else
/*     */       {
/*  84 */         slot.func_75218_e();
/*     */       }
/*     */     }
/*     */     
/*  88 */     return itemstack;
/*     */   }
/*     */   
/*     */   public boolean func_75145_c(EntityPlayer entityplayer)
/*     */   {
/*  93 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_75134_a(EntityPlayer par1EntityPlayer)
/*     */   {
/*  99 */     super.func_75134_a(par1EntityPlayer);
/* 100 */     this.trunk.setOpen(false);
/* 101 */     this.trunk.field_70170_p.func_72956_a(this.trunk, "random.chestclosed", 0.5F, this.trunk.field_70170_p.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/golems/ContainerTravelingTrunk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */