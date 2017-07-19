/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import thaumcraft.common.items.ItemResearchNotes;
/*     */ import thaumcraft.common.tiles.TileResearchTable;
/*     */ 
/*     */ public class ContainerResearchTable extends Container
/*     */ {
/*     */   public TileResearchTable tileEntity;
/*     */   String[] aspects;
/*     */   EntityPlayer player;
/*     */   
/*     */   public ContainerResearchTable(InventoryPlayer iinventory, TileResearchTable iinventory1)
/*     */   {
/*  20 */     this.player = iinventory.field_70458_d;
/*  21 */     this.tileEntity = iinventory1;
/*  22 */     this.aspects = ((String[])thaumcraft.api.aspects.Aspect.aspects.keySet().toArray(new String[0]));
/*     */     
/*     */ 
/*  25 */     func_75146_a(new SlotLimitedByClass(thaumcraft.api.IScribeTools.class, iinventory1, 0, 14, 10));
/*  26 */     func_75146_a(new SlotLimitedByClass(ItemResearchNotes.class, iinventory1, 1, 70, 10));
/*     */     
/*     */ 
/*  29 */     bindPlayerInventory(iinventory);
/*     */   }
/*     */   
/*     */   protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
/*  33 */     for (int i = 0; i < 3; i++) {
/*  34 */       for (int j = 0; j < 9; j++) {
/*  35 */         func_75146_a(new Slot(inventoryPlayer, j + i * 9 + 9, 48 + j * 18, 175 + i * 18));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  40 */     for (int i = 0; i < 9; i++) {
/*  41 */       func_75146_a(new Slot(inventoryPlayer, i, 48 + i * 18, 233));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_75140_a(EntityPlayer par1EntityPlayer, int button)
/*     */   {
/*  48 */     if (button == 1) {
/*  49 */       return true;
/*     */     }
/*  51 */     if (button == 5) {
/*  52 */       this.tileEntity.duplicate(par1EntityPlayer);
/*  53 */       return true;
/*     */     }
/*  55 */     return false;
/*     */   }
/*     */   
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot)
/*     */   {
/*  60 */     ItemStack stack = null;
/*  61 */     Slot slotObject = (Slot)this.field_75151_b.get(slot);
/*     */     
/*     */ 
/*  64 */     if ((slotObject != null) && (slotObject.func_75216_d())) {
/*  65 */       ItemStack stackInSlot = slotObject.func_75211_c();
/*  66 */       stack = stackInSlot.func_77946_l();
/*     */       
/*     */ 
/*  69 */       if (slot < 2) {
/*  70 */         if (!func_75135_a(stackInSlot, 2, this.field_75151_b.size(), true))
/*     */         {
/*  72 */           return null;
/*     */         }
/*     */       }
/*  75 */       else if (!func_75135_a(stackInSlot, 0, 2, false)) {
/*  76 */         return null;
/*     */       }
/*     */       
/*  79 */       if (stackInSlot.field_77994_a == 0) {
/*  80 */         slotObject.func_75215_d(null);
/*     */       } else {
/*  82 */         slotObject.func_75218_e();
/*     */       }
/*     */     }
/*     */     
/*  86 */     return stack;
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean func_75135_a(ItemStack par1ItemStack, int par2, int par3, boolean par4)
/*     */   {
/*  92 */     boolean var5 = false;
/*  93 */     int var6 = par2;
/*     */     
/*  95 */     if (par4)
/*     */     {
/*  97 */       var6 = par3 - 1;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 103 */     if (par1ItemStack.func_77985_e())
/*     */     {
/* 105 */       while ((par1ItemStack.field_77994_a > 0) && (((!par4) && (var6 < par3)) || ((par4) && (var6 >= par2))))
/*     */       {
/* 107 */         Slot var7 = (Slot)this.field_75151_b.get(var6);
/* 108 */         ItemStack var8 = var7.func_75211_c();
/*     */         
/* 110 */         if ((var8 != null) && (var7.func_75214_a(par1ItemStack)) && (var8.func_77973_b() == par1ItemStack.func_77973_b()) && ((!par1ItemStack.func_77981_g()) || (par1ItemStack.func_77960_j() == var8.func_77960_j())) && (ItemStack.func_77970_a(par1ItemStack, var8)))
/*     */         {
/*     */ 
/*     */ 
/* 114 */           int var9 = var8.field_77994_a + par1ItemStack.field_77994_a;
/*     */           
/* 116 */           if (var9 <= par1ItemStack.func_77976_d())
/*     */           {
/* 118 */             par1ItemStack.field_77994_a = 0;
/* 119 */             var8.field_77994_a = var9;
/* 120 */             var7.func_75218_e();
/* 121 */             var5 = true;
/*     */           }
/* 123 */           else if (var8.field_77994_a < par1ItemStack.func_77976_d())
/*     */           {
/* 125 */             par1ItemStack.field_77994_a -= par1ItemStack.func_77976_d() - var8.field_77994_a;
/* 126 */             var8.field_77994_a = par1ItemStack.func_77976_d();
/* 127 */             var7.func_75218_e();
/* 128 */             var5 = true;
/*     */           }
/*     */         }
/*     */         
/* 132 */         if (par4)
/*     */         {
/* 134 */           var6--;
/*     */         }
/*     */         else
/*     */         {
/* 138 */           var6++;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 143 */     if (par1ItemStack.field_77994_a > 0)
/*     */     {
/* 145 */       if (par4)
/*     */       {
/* 147 */         var6 = par3 - 1;
/*     */       }
/*     */       else
/*     */       {
/* 151 */         var6 = par2;
/*     */       }
/*     */       
/* 154 */       while (((!par4) && (var6 < par3)) || ((par4) && (var6 >= par2)))
/*     */       {
/* 156 */         Slot var7 = (Slot)this.field_75151_b.get(var6);
/* 157 */         ItemStack var8 = var7.func_75211_c();
/*     */         
/* 159 */         if ((var8 == null) && (var7.func_75214_a(par1ItemStack)))
/*     */         {
/* 161 */           var7.func_75215_d(par1ItemStack.func_77946_l());
/* 162 */           var7.func_75218_e();
/* 163 */           par1ItemStack.field_77994_a = 0;
/* 164 */           var5 = true;
/* 165 */           break;
/*     */         }
/*     */         
/* 168 */         if (par4)
/*     */         {
/* 170 */           var6--;
/*     */         }
/*     */         else
/*     */         {
/* 174 */           var6++;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 179 */     return var5;
/*     */   }
/*     */   
/*     */   public boolean func_75145_c(EntityPlayer player)
/*     */   {
/* 184 */     return this.tileEntity.func_70300_a(player);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/container/ContainerResearchTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */