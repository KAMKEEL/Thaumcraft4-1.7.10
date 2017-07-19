/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.items.relics.ItemHandMirror;
/*     */ 
/*     */ public class ContainerHandMirror extends Container
/*     */ {
/*     */   private World worldObj;
/*     */   private int posX;
/*     */   private int posY;
/*     */   private int posZ;
/*  19 */   public IInventory input = new InventoryHandMirror(this);
/*  20 */   ItemStack mirror = null;
/*  21 */   EntityPlayer player = null;
/*     */   
/*     */   public ContainerHandMirror(InventoryPlayer iinventory, World par2World, int par3, int par4, int par5)
/*     */   {
/*  25 */     this.worldObj = par2World;
/*  26 */     this.posX = par3;
/*  27 */     this.posY = par4;
/*  28 */     this.posZ = par5;
/*  29 */     this.player = iinventory.field_70458_d;
/*  30 */     this.mirror = iinventory.func_70448_g();
/*     */     
/*  32 */     func_75146_a(new Slot(this.input, 0, 80, 24));
/*  33 */     bindPlayerInventory(iinventory);
/*  34 */     func_75130_a(this.input);
/*     */   }
/*     */   
/*     */   protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
/*  38 */     for (int i = 0; i < 3; i++) {
/*  39 */       for (int j = 0; j < 9; j++) {
/*  40 */         func_75146_a(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  45 */     for (int i = 0; i < 9; i++) {
/*  46 */       func_75146_a(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_75130_a(IInventory par1IInventory)
/*     */   {
/*  53 */     if ((this.input.func_70301_a(0) != null) && (ItemStack.func_77989_b(this.input.func_70301_a(0), this.mirror)))
/*     */     {
/*  55 */       this.player.field_71070_bA = this.player.field_71069_bz;
/*     */     }
/*  57 */     else if ((!this.worldObj.field_72995_K) && (this.input.func_70301_a(0) != null) && (this.player != null) && 
/*  58 */       (ItemHandMirror.transport(this.mirror, this.input.func_70301_a(0), this.player, this.worldObj))) {
/*  59 */       this.input.func_70299_a(0, null);
/*  60 */       for (int var4 = 0; var4 < this.field_75149_d.size(); var4++)
/*     */       {
/*  62 */         ((net.minecraft.inventory.ICrafting)this.field_75149_d.get(var4)).func_71111_a(this, 0, null);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot)
/*     */   {
/*  73 */     ItemStack stack = null;
/*  74 */     Slot slotObject = (Slot)this.field_75151_b.get(slot);
/*     */     
/*     */ 
/*  77 */     if ((slotObject != null) && (slotObject.func_75216_d()) && (!(slotObject.func_75211_c().func_77973_b() instanceof ItemHandMirror))) {
/*  78 */       ItemStack stackInSlot = slotObject.func_75211_c();
/*  79 */       stack = stackInSlot.func_77946_l();
/*     */       
/*  81 */       if (slot == 0) {
/*  82 */         if (!mergeItemStack(stackInSlot, 1, this.field_75151_b.size(), true, 64))
/*     */         {
/*  84 */           return null;
/*     */         }
/*     */       }
/*  87 */       else if (!mergeItemStack(stackInSlot, 0, 1, false, 64)) {
/*  88 */         return null;
/*     */       }
/*     */       
/*  91 */       if (stackInSlot.field_77994_a == 0) {
/*  92 */         slotObject.func_75215_d(null);
/*     */       } else {
/*  94 */         slotObject.func_75218_e();
/*     */       }
/*     */     }
/*     */     
/*  98 */     return stack;
/*     */   }
/*     */   
/*     */   public boolean func_75145_c(EntityPlayer var1)
/*     */   {
/* 103 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_75134_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 109 */     super.func_75134_a(par1EntityPlayer);
/*     */     
/* 111 */     if (!this.worldObj.field_72995_K)
/*     */     {
/* 113 */       for (int var2 = 0; var2 < 1; var2++)
/*     */       {
/* 115 */         ItemStack var3 = this.input.func_70304_b(var2);
/*     */         
/* 117 */         if (var3 != null)
/*     */         {
/* 119 */           par1EntityPlayer.func_71019_a(var3, false);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean mergeItemStack(ItemStack par1ItemStack, int par2, int par3, boolean par4, int limit)
/*     */   {
/* 127 */     boolean var5 = false;
/* 128 */     int var6 = par2;
/*     */     
/* 130 */     if (par4)
/*     */     {
/* 132 */       var6 = par3 - 1;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 138 */     if (par1ItemStack.func_77985_e())
/*     */     {
/* 140 */       while ((par1ItemStack.field_77994_a > 0) && (((!par4) && (var6 < par3)) || ((par4) && (var6 >= par2))))
/*     */       {
/* 142 */         Slot var7 = (Slot)this.field_75151_b.get(var6);
/* 143 */         ItemStack var8 = var7.func_75211_c();
/*     */         
/* 145 */         if ((var8 != null) && (var8.func_77973_b() == par1ItemStack.func_77973_b()) && ((!par1ItemStack.func_77981_g()) || (par1ItemStack.func_77960_j() == var8.func_77960_j())) && (ItemStack.func_77970_a(par1ItemStack, var8)))
/*     */         {
/*     */ 
/*     */ 
/* 149 */           int var9 = var8.field_77994_a + par1ItemStack.field_77994_a;
/*     */           
/* 151 */           if (var9 <= Math.min(par1ItemStack.func_77976_d(), limit))
/*     */           {
/* 153 */             par1ItemStack.field_77994_a = 0;
/* 154 */             var8.field_77994_a = var9;
/* 155 */             var7.func_75218_e();
/* 156 */             var5 = true;
/*     */           }
/* 158 */           else if (var8.field_77994_a < Math.min(par1ItemStack.func_77976_d(), limit))
/*     */           {
/* 160 */             par1ItemStack.field_77994_a -= Math.min(par1ItemStack.func_77976_d(), limit) - var8.field_77994_a;
/* 161 */             var8.field_77994_a = Math.min(par1ItemStack.func_77976_d(), limit);
/* 162 */             var7.func_75218_e();
/* 163 */             var5 = true;
/*     */           }
/*     */         }
/*     */         
/* 167 */         if (par4)
/*     */         {
/* 169 */           var6--;
/*     */         }
/*     */         else
/*     */         {
/* 173 */           var6++;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 178 */     if (par1ItemStack.field_77994_a > 0)
/*     */     {
/* 180 */       if (par4)
/*     */       {
/* 182 */         var6 = par3 - 1;
/*     */       }
/*     */       else
/*     */       {
/* 186 */         var6 = par2;
/*     */       }
/*     */       
/* 189 */       while (((!par4) && (var6 < par3)) || ((par4) && (var6 >= par2)))
/*     */       {
/* 191 */         Slot var7 = (Slot)this.field_75151_b.get(var6);
/* 192 */         ItemStack var8 = var7.func_75211_c();
/*     */         
/* 194 */         if (var8 == null)
/*     */         {
/* 196 */           ItemStack res = par1ItemStack.func_77946_l();
/* 197 */           res.field_77994_a = Math.min(res.field_77994_a, limit);
/* 198 */           var7.func_75215_d(res);
/* 199 */           var7.func_75218_e();
/* 200 */           par1ItemStack.field_77994_a -= res.field_77994_a;
/* 201 */           var5 = true;
/* 202 */           break;
/*     */         }
/*     */         
/* 205 */         if (par4)
/*     */         {
/* 207 */           var6--;
/*     */         }
/*     */         else
/*     */         {
/* 211 */           var6++;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 216 */     return var5;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/container/ContainerHandMirror.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */