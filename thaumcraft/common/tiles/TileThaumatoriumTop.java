/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.ISidedInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ 
/*     */ public class TileThaumatoriumTop
/*     */   extends TileThaumcraft implements IAspectContainer, IEssentiaTransport, ISidedInventory
/*     */ {
/*  18 */   public TileThaumatorium thaumatorium = null;
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/*  22 */     return true;
/*     */   }
/*     */   
/*     */   public void func_145845_h()
/*     */   {
/*  27 */     if (this.thaumatorium == null) {
/*  28 */       TileEntity tile = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
/*  29 */       if ((tile != null) && ((tile instanceof TileThaumatorium))) {
/*  30 */         this.thaumatorium = ((TileThaumatorium)tile);
/*  31 */         this.field_145850_b.func_147444_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, func_145838_q());
/*  32 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  33 */         func_70296_d();
/*     */       } else {
/*  35 */         this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 9, 3);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public int addToContainer(Aspect tt, int am)
/*     */   {
/*  42 */     if (this.thaumatorium == null) return am;
/*  43 */     return this.thaumatorium.addToContainer(tt, am);
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(Aspect tt, int am)
/*     */   {
/*  48 */     if (this.thaumatorium == null) return false;
/*  49 */     return this.thaumatorium.takeFromContainer(tt, am);
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(AspectList ot)
/*     */   {
/*  54 */     return false;
/*     */   }
/*     */   
/*     */   public boolean doesContainerContain(AspectList ot)
/*     */   {
/*  59 */     return false;
/*     */   }
/*     */   
/*     */   public boolean doesContainerContainAmount(Aspect tt, int am)
/*     */   {
/*  64 */     if (this.thaumatorium == null) return false;
/*  65 */     return this.thaumatorium.doesContainerContainAmount(tt, am);
/*     */   }
/*     */   
/*     */   public int containerContains(Aspect tt)
/*     */   {
/*  70 */     if (this.thaumatorium == null) return 0;
/*  71 */     return this.thaumatorium.containerContains(tt);
/*     */   }
/*     */   
/*     */   public boolean doesContainerAccept(Aspect tag)
/*     */   {
/*  76 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isConnectable(ForgeDirection face)
/*     */   {
/*  83 */     if (this.thaumatorium == null) return false;
/*  84 */     return this.thaumatorium.isConnectable(face);
/*     */   }
/*     */   
/*     */   public boolean canInputFrom(ForgeDirection face)
/*     */   {
/*  89 */     if (this.thaumatorium == null) return false;
/*  90 */     return this.thaumatorium.canInputFrom(face);
/*     */   }
/*     */   
/*     */   public boolean canOutputTo(ForgeDirection face)
/*     */   {
/*  95 */     return false;
/*     */   }
/*     */   
/*     */   public void setSuction(Aspect aspect, int amount)
/*     */   {
/* 100 */     if (this.thaumatorium == null) return;
/* 101 */     this.thaumatorium.setSuction(aspect, amount);
/*     */   }
/*     */   
/*     */   public Aspect getSuctionType(ForgeDirection loc)
/*     */   {
/* 106 */     if (this.thaumatorium == null) return null;
/* 107 */     return this.thaumatorium.getSuctionType(loc);
/*     */   }
/*     */   
/*     */   public int getSuctionAmount(ForgeDirection loc)
/*     */   {
/* 112 */     if (this.thaumatorium == null) return 0;
/* 113 */     return this.thaumatorium.getSuctionAmount(loc);
/*     */   }
/*     */   
/*     */   public Aspect getEssentiaType(ForgeDirection loc)
/*     */   {
/* 118 */     return null;
/*     */   }
/*     */   
/*     */   public int getEssentiaAmount(ForgeDirection loc)
/*     */   {
/* 123 */     return 0;
/*     */   }
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, ForgeDirection face)
/*     */   {
/* 128 */     if (this.thaumatorium == null) return 0;
/* 129 */     return this.thaumatorium.takeEssentia(aspect, amount, face);
/*     */   }
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, ForgeDirection face)
/*     */   {
/* 134 */     if (this.thaumatorium == null) return 0;
/* 135 */     return this.thaumatorium.addEssentia(aspect, amount, face);
/*     */   }
/*     */   
/*     */   public int getMinimumSuction()
/*     */   {
/* 140 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean renderExtendedTube()
/*     */   {
/* 145 */     return false;
/*     */   }
/*     */   
/*     */   public AspectList getAspects()
/*     */   {
/* 150 */     if (this.thaumatorium == null) return null;
/* 151 */     return this.thaumatorium.essentia;
/*     */   }
/*     */   
/*     */   public void setAspects(AspectList aspects)
/*     */   {
/* 156 */     if (this.thaumatorium == null) return;
/* 157 */     this.thaumatorium.setAspects(aspects);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70302_i_()
/*     */   {
/* 165 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_70301_a(int par1)
/*     */   {
/* 171 */     if (this.thaumatorium == null) return null;
/* 172 */     return this.thaumatorium.func_70301_a(par1);
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_70298_a(int par1, int par2)
/*     */   {
/* 178 */     if (this.thaumatorium == null) return null;
/* 179 */     return this.thaumatorium.func_70298_a(par1, par2);
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_70304_b(int par1)
/*     */   {
/* 185 */     if (this.thaumatorium == null) return null;
/* 186 */     return this.thaumatorium.func_70304_b(par1);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70299_a(int par1, ItemStack par2ItemStack)
/*     */   {
/* 192 */     if (this.thaumatorium == null) return;
/* 193 */     this.thaumatorium.func_70299_a(par1, par2ItemStack);
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_145825_b()
/*     */   {
/* 199 */     return "container.alchemyfurnace";
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_145818_k_()
/*     */   {
/* 205 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70297_j_()
/*     */   {
/* 211 */     return 64;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 217 */     return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this;
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
/* 232 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int[] func_94128_d(int par1)
/*     */   {
/* 242 */     return new int[] { 0 };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_102007_a(int par1, ItemStack par2ItemStack, int par3)
/*     */   {
/* 252 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_102008_b(int par1, ItemStack par2ItemStack, int par3)
/*     */   {
/* 262 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileThaumatoriumTop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */