/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.ISidedInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.entities.EntityItemGrate;
/*     */ 
/*     */ public class TileGrate
/*     */   extends TileEntity implements ISidedInventory
/*     */ {
/*     */   public int func_70302_i_()
/*     */   {
/*  16 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70301_a(int par1)
/*     */   {
/*  25 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70298_a(int par1, int par2)
/*     */   {
/*  35 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70304_b(int par1)
/*     */   {
/*  45 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70299_a(int par1, ItemStack stack)
/*     */   {
/*  54 */     if (!this.field_145850_b.field_72995_K) {
/*  55 */       EntityItemGrate ei = new EntityItemGrate(this.field_145850_b, this.field_145851_c + 0.5D, this.field_145848_d + 0.6D, this.field_145849_e + 0.5D, stack.func_77946_l());
/*  56 */       ei.field_70181_x = -0.1D;
/*  57 */       ei.field_70159_w = 0.0D;
/*  58 */       ei.field_70179_y = 0.0D;
/*  59 */       this.field_145850_b.func_72838_d(ei);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70297_j_()
/*     */   {
/*  71 */     return 64;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */   {
/*  80 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70295_k_() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70305_f() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_94041_b(int par1, ItemStack par2ItemStack)
/*     */   {
/*  95 */     return this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) == 5;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int[] func_94128_d(int par1)
/*     */   {
/* 105 */     return (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) == 5) && (par1 == ForgeDirection.UP.ordinal()) ? new int[] { 0 } : new int[0];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_102007_a(int par1, ItemStack par2ItemStack, int par3)
/*     */   {
/* 115 */     return (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) == 5) && (par3 == ForgeDirection.UP.ordinal());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_102008_b(int par1, ItemStack par2ItemStack, int par3)
/*     */   {
/* 125 */     return false;
/*     */   }
/*     */   
/*     */   public String func_145825_b()
/*     */   {
/* 130 */     return "thaumcraft.grate";
/*     */   }
/*     */   
/*     */   public boolean func_145818_k_()
/*     */   {
/* 135 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/* 140 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileGrate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */