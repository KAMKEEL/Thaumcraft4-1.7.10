/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ContainerHoverHarness extends net.minecraft.inventory.Container
/*     */ {
/*     */   private World worldObj;
/*     */   private int posX;
/*     */   private int posY;
/*     */   private int posZ;
/*  18 */   public IInventory input = new InventoryHoverHarness(this);
/*  19 */   ItemStack armor = null;
/*  20 */   EntityPlayer player = null;
/*     */   private int blockSlot;
/*     */   
/*     */   public ContainerHoverHarness(InventoryPlayer iinventory, World par2World, int par3, int par4, int par5)
/*     */   {
/*  25 */     this.worldObj = par2World;
/*  26 */     this.posX = par3;
/*  27 */     this.posY = par4;
/*  28 */     this.posZ = par5;
/*  29 */     this.player = iinventory.field_70458_d;
/*  30 */     this.armor = iinventory.func_70448_g();
/*  31 */     this.blockSlot = (iinventory.field_70461_c + 28);
/*     */     
/*  33 */     func_75146_a(new Slot(this.input, 0, 80, 32));
/*  34 */     bindPlayerInventory(iinventory);
/*  35 */     if (!par2World.field_72995_K) {
/*     */       try {
/*  37 */         ItemStack jar = ItemStack.func_77949_a(this.armor.field_77990_d.func_74775_l("jar"));
/*  38 */         this.input.func_70299_a(0, jar);
/*     */       } catch (Exception e) {}
/*     */     }
/*  41 */     func_75130_a(this.input);
/*     */   }
/*     */   
/*     */   protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
/*  45 */     for (int i = 0; i < 3; i++) {
/*  46 */       for (int j = 0; j < 9; j++) {
/*  47 */         func_75146_a(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  52 */     for (int i = 0; i < 9; i++) {
/*  53 */       func_75146_a(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot)
/*     */   {
/*  64 */     if (slot == this.blockSlot) return null;
/*  65 */     ItemStack stack = null;
/*  66 */     Slot slotObject = (Slot)this.field_75151_b.get(slot);
/*     */     
/*     */ 
/*  69 */     if ((slotObject != null) && (slotObject.func_75216_d())) {
/*  70 */       ItemStack stackInSlot = slotObject.func_75211_c();
/*  71 */       stack = stackInSlot.func_77946_l();
/*     */       
/*  73 */       if (slot == 0) {
/*  74 */         if ((!this.input.func_94041_b(slot, stackInSlot)) || (!mergeItemStack(stackInSlot, 1, this.field_75151_b.size(), true, 64)))
/*     */         {
/*     */ 
/*  77 */           return null;
/*     */         }
/*     */         
/*     */       }
/*  81 */       else if ((!this.input.func_94041_b(slot, stackInSlot)) || (!mergeItemStack(stackInSlot, 0, 1, false, 1)))
/*     */       {
/*  83 */         return null;
/*     */       }
/*     */       
/*  86 */       if (stackInSlot.field_77994_a == 0) {
/*  87 */         slotObject.func_75215_d(null);
/*     */       } else {
/*  89 */         slotObject.func_75218_e();
/*     */       }
/*     */     }
/*     */     
/*  93 */     return stack;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_75144_a(int par1, int par2, int par3, EntityPlayer par4EntityPlayer)
/*     */   {
/*  99 */     if (par1 == this.blockSlot) return null;
/* 100 */     InventoryPlayer inventoryplayer = par4EntityPlayer.field_71071_by;
/* 101 */     if ((par1 != 0) || (this.input.func_94041_b(par1, inventoryplayer.func_70445_o())) || ((par1 == 0) && (inventoryplayer.func_70445_o() == null)))
/*     */     {
/* 103 */       return super.func_75144_a(par1, par2, par3, par4EntityPlayer);
/*     */     }
/* 105 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_75145_c(EntityPlayer var1)
/*     */   {
/* 112 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_75141_a(int par1, ItemStack par2ItemStack)
/*     */   {
/* 119 */     if (this.input.func_94041_b(par1, par2ItemStack)) { super.func_75141_a(par1, par2ItemStack);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_75134_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 125 */     if (!this.worldObj.field_72995_K)
/*     */     {
/* 127 */       ItemStack var3 = this.input.func_70304_b(0);
/* 128 */       if (var3 != null) {
/* 129 */         NBTTagCompound var4 = new NBTTagCompound();
/* 130 */         var3.func_77955_b(var4);
/* 131 */         this.armor.func_77983_a("jar", var4);
/*     */       } else {
/* 133 */         this.armor.func_77983_a("jar", new NBTTagCompound());
/*     */       }
/* 135 */       if (this.player == null) return;
/* 136 */       if ((this.player.func_70694_bm() != null) && (this.player.func_70694_bm().func_77969_a(this.armor)))
/* 137 */         this.player.func_70062_b(0, this.armor);
/* 138 */       this.player.field_71071_by.func_70296_d();
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean mergeItemStack(ItemStack par1ItemStack, int par2, int par3, boolean par4, int limit)
/*     */   {
/* 144 */     boolean var5 = false;
/* 145 */     int var6 = par2;
/*     */     
/* 147 */     if (par4)
/*     */     {
/* 149 */       var6 = par3 - 1;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 155 */     if (par1ItemStack.func_77985_e())
/*     */     {
/* 157 */       while ((par1ItemStack.field_77994_a > 0) && (((!par4) && (var6 < par3)) || ((par4) && (var6 >= par2))))
/*     */       {
/* 159 */         Slot var7 = (Slot)this.field_75151_b.get(var6);
/* 160 */         ItemStack var8 = var7.func_75211_c();
/*     */         
/* 162 */         if ((var8 != null) && (var8.func_77973_b() == par1ItemStack.func_77973_b()) && ((!par1ItemStack.func_77981_g()) || (par1ItemStack.func_77960_j() == var8.func_77960_j())) && (ItemStack.func_77970_a(par1ItemStack, var8)))
/*     */         {
/*     */ 
/*     */ 
/* 166 */           int var9 = var8.field_77994_a + par1ItemStack.field_77994_a;
/*     */           
/* 168 */           if (var9 <= Math.min(par1ItemStack.func_77976_d(), limit))
/*     */           {
/* 170 */             par1ItemStack.field_77994_a = 0;
/* 171 */             var8.field_77994_a = var9;
/* 172 */             var7.func_75218_e();
/* 173 */             var5 = true;
/*     */           }
/* 175 */           else if (var8.field_77994_a < Math.min(par1ItemStack.func_77976_d(), limit))
/*     */           {
/* 177 */             par1ItemStack.field_77994_a -= Math.min(par1ItemStack.func_77976_d(), limit) - var8.field_77994_a;
/* 178 */             var8.field_77994_a = Math.min(par1ItemStack.func_77976_d(), limit);
/* 179 */             var7.func_75218_e();
/* 180 */             var5 = true;
/*     */           }
/*     */         }
/*     */         
/* 184 */         if (par4)
/*     */         {
/* 186 */           var6--;
/*     */         }
/*     */         else
/*     */         {
/* 190 */           var6++;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 195 */     if (par1ItemStack.field_77994_a > 0)
/*     */     {
/* 197 */       if (par4)
/*     */       {
/* 199 */         var6 = par3 - 1;
/*     */       }
/*     */       else
/*     */       {
/* 203 */         var6 = par2;
/*     */       }
/*     */       
/* 206 */       while (((!par4) && (var6 < par3)) || ((par4) && (var6 >= par2)))
/*     */       {
/* 208 */         Slot var7 = (Slot)this.field_75151_b.get(var6);
/* 209 */         ItemStack var8 = var7.func_75211_c();
/*     */         
/* 211 */         if (var8 == null)
/*     */         {
/* 213 */           ItemStack res = par1ItemStack.func_77946_l();
/* 214 */           res.field_77994_a = Math.min(res.field_77994_a, limit);
/* 215 */           var7.func_75215_d(res);
/* 216 */           var7.func_75218_e();
/* 217 */           par1ItemStack.field_77994_a -= res.field_77994_a;
/* 218 */           var5 = true;
/* 219 */           break;
/*     */         }
/*     */         
/* 222 */         if (par4)
/*     */         {
/* 224 */           var6--;
/*     */         }
/*     */         else
/*     */         {
/* 228 */           var6++;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 233 */     return var5;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/container/ContainerHoverHarness.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */