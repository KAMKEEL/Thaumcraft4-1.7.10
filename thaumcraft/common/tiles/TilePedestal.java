/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ public class TilePedestal extends TileThaumcraft implements net.minecraft.inventory.ISidedInventory
/*     */ {
/*  18 */   private static final int[] slots = { 0 };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  23 */   private ItemStack[] inventory = new ItemStack[1];
/*     */   
/*     */   private String customName;
/*     */   
/*     */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox()
/*     */   {
/*  30 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c + 1, this.field_145848_d + 2, this.field_145849_e + 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70302_i_()
/*     */   {
/*  41 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70301_a(int par1)
/*     */   {
/*  50 */     return this.inventory[par1];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70298_a(int par1, int par2)
/*     */   {
/*  63 */     if (this.inventory[par1] != null)
/*     */     {
/*  65 */       if (!this.field_145850_b.field_72995_K) {
/*  66 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       }
/*     */       
/*     */ 
/*  70 */       if (this.inventory[par1].field_77994_a <= par2)
/*     */       {
/*  72 */         ItemStack itemstack = this.inventory[par1];
/*  73 */         this.inventory[par1] = null;
/*  74 */         func_70296_d();
/*  75 */         return itemstack;
/*     */       }
/*     */       
/*     */ 
/*  79 */       ItemStack itemstack = this.inventory[par1].func_77979_a(par2);
/*     */       
/*  81 */       if (this.inventory[par1].field_77994_a == 0)
/*     */       {
/*  83 */         this.inventory[par1] = null;
/*     */       }
/*  85 */       func_70296_d();
/*  86 */       return itemstack;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  91 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70304_b(int par1)
/*     */   {
/* 102 */     if (this.inventory[par1] != null)
/*     */     {
/* 104 */       ItemStack itemstack = this.inventory[par1];
/* 105 */       this.inventory[par1] = null;
/* 106 */       return itemstack;
/*     */     }
/*     */     
/*     */ 
/* 110 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70299_a(int par1, ItemStack par2ItemStack)
/*     */   {
/* 120 */     this.inventory[par1] = par2ItemStack;
/*     */     
/* 122 */     if ((par2ItemStack != null) && (par2ItemStack.field_77994_a > func_70297_j_()))
/*     */     {
/* 124 */       par2ItemStack.field_77994_a = func_70297_j_();
/*     */     }
/* 126 */     func_70296_d();
/* 127 */     if (!this.field_145850_b.field_72995_K) {
/* 128 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setInventorySlotContentsFromInfusion(int par1, ItemStack par2ItemStack)
/*     */   {
/* 134 */     this.inventory[par1] = par2ItemStack;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 140 */     func_70296_d();
/* 141 */     if (!this.field_145850_b.field_72995_K) {
/* 142 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String func_145825_b()
/*     */   {
/* 152 */     return func_145818_k_() ? this.customName : "container.pedestal";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_145818_k_()
/*     */   {
/* 162 */     return (this.customName != null) && (this.customName.length() > 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setGuiDisplayName(String par1Str)
/*     */   {
/* 170 */     this.customName = par1Str;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 177 */     NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
/* 178 */     this.inventory = new ItemStack[func_70302_i_()];
/*     */     
/* 180 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++)
/*     */     {
/* 182 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 183 */       byte b0 = nbttagcompound1.func_74771_c("Slot");
/*     */       
/* 185 */       if ((b0 >= 0) && (b0 < this.inventory.length))
/*     */       {
/* 187 */         this.inventory[b0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 194 */     NBTTagList nbttaglist = new NBTTagList();
/*     */     
/* 196 */     for (int i = 0; i < this.inventory.length; i++)
/*     */     {
/* 198 */       if (this.inventory[i] != null)
/*     */       {
/* 200 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 201 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 202 */         this.inventory[i].func_77955_b(nbttagcompound1);
/* 203 */         nbttaglist.func_74742_a(nbttagcompound1);
/*     */       }
/*     */     }
/*     */     
/* 207 */     nbttagcompound.func_74782_a("Items", nbttaglist);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_145839_a(NBTTagCompound nbtCompound)
/*     */   {
/* 216 */     super.func_145839_a(nbtCompound);
/*     */     
/* 218 */     if (nbtCompound.func_74764_b("CustomName"))
/*     */     {
/* 220 */       this.customName = nbtCompound.func_74779_i("CustomName");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_145841_b(NBTTagCompound nbtCompound)
/*     */   {
/* 231 */     super.func_145841_b(nbtCompound);
/*     */     
/* 233 */     if (func_145818_k_())
/*     */     {
/* 235 */       nbtCompound.func_74778_a("CustomName", this.customName);
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
/* 247 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
/*     */   {
/* 253 */     super.onDataPacket(net, pkt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean canUpdate()
/*     */   {
/* 261 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 271 */     return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this;
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
/* 286 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int[] func_94128_d(int par1)
/*     */   {
/* 296 */     return slots;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_102007_a(int par1, ItemStack par2ItemStack, int par3)
/*     */   {
/* 306 */     return func_70301_a(par1) == null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_102008_b(int par1, ItemStack par2ItemStack, int par3)
/*     */   {
/* 316 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_145842_c(int i, int j)
/*     */   {
/* 322 */     if (i == 11)
/*     */     {
/* 324 */       if (this.field_145850_b.field_72995_K) {
/* 325 */         for (int a = 0; a < Thaumcraft.proxy.particleCount(5); a++) {
/* 326 */           Thaumcraft.proxy.blockSparkle(this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, 12583104, 2);
/*     */         }
/*     */       }
/* 329 */       return true;
/*     */     }
/*     */     
/* 332 */     if (i == 12)
/*     */     {
/* 334 */       if (this.field_145850_b.field_72995_K) {
/* 335 */         for (int a = 0; a < Thaumcraft.proxy.particleCount(10); a++) {
/* 336 */           Thaumcraft.proxy.blockSparkle(this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, 55537, 2);
/*     */         }
/*     */       }
/* 339 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 343 */     return super.func_145842_c(i, j);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TilePedestal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */