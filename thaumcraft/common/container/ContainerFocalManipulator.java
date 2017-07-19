/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.common.tiles.TileFocalManipulator;
/*     */ 
/*     */ public class ContainerFocalManipulator extends Container
/*     */ {
/*     */   private TileFocalManipulator table;
/*     */   private int lastBreakTime;
/*     */   
/*     */   public ContainerFocalManipulator(InventoryPlayer par1InventoryPlayer, TileFocalManipulator tileEntity)
/*     */   {
/*  20 */     this.table = tileEntity;
/*  21 */     func_75146_a(new SlotLimitedByClass(ItemFocusBasic.class, tileEntity, 0, 88, 60));
/*     */     
/*     */ 
/*  24 */     for (int i = 0; i < 3; i++)
/*     */     {
/*  26 */       for (int j = 0; j < 9; j++)
/*     */       {
/*  28 */         func_75146_a(new Slot(par1InventoryPlayer, j + i * 9 + 9, 16 + j * 18, 151 + i * 18));
/*     */       }
/*     */     }
/*     */     
/*  32 */     for (i = 0; i < 9; i++)
/*     */     {
/*  34 */       func_75146_a(new Slot(par1InventoryPlayer, i, 16 + i * 18, 209));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_75140_a(EntityPlayer p, int button)
/*     */   {
/*  42 */     if ((button >= 0) && 
/*  43 */       (!this.table.startCraft(button, p))) {
/*  44 */       this.table.func_145831_w().func_72908_a(this.table.field_145851_c, this.table.field_145848_d, this.table.field_145849_e, "thaumcraft:craftfail", 0.33F, 1.0F);
/*     */     }
/*     */     
/*     */ 
/*  48 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_75145_c(EntityPlayer par1EntityPlayer)
/*     */   {
/*  55 */     return this.table.func_70300_a(par1EntityPlayer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2)
/*     */   {
/*  64 */     ItemStack itemstack = null;
/*  65 */     Slot slot = (Slot)this.field_75151_b.get(par2);
/*     */     
/*  67 */     if ((slot != null) && (slot.func_75216_d()))
/*     */     {
/*  69 */       ItemStack itemstack1 = slot.func_75211_c();
/*  70 */       itemstack = itemstack1.func_77946_l();
/*     */       
/*  72 */       if (par2 != 0)
/*     */       {
/*  74 */         if ((itemstack1.func_77973_b() instanceof ItemFocusBasic))
/*     */         {
/*  76 */           if (!func_75135_a(itemstack1, 0, 1, false))
/*     */           {
/*  78 */             return null;
/*     */           }
/*     */         }
/*  81 */         else if ((par2 >= 1) && (par2 < 28))
/*     */         {
/*  83 */           if (!func_75135_a(itemstack1, 28, 37, false))
/*     */           {
/*  85 */             return null;
/*     */           }
/*     */         }
/*  88 */         else if ((par2 >= 28) && (par2 < 37) && (!func_75135_a(itemstack1, 1, 28, false)))
/*     */         {
/*  90 */           return null;
/*     */         }
/*     */       }
/*  93 */       else if (!func_75135_a(itemstack1, 1, 37, false))
/*     */       {
/*  95 */         return null;
/*     */       }
/*     */       
/*  98 */       if (itemstack1.field_77994_a == 0)
/*     */       {
/* 100 */         slot.func_75215_d((ItemStack)null);
/*     */       }
/*     */       else
/*     */       {
/* 104 */         slot.func_75218_e();
/*     */       }
/*     */       
/* 107 */       if (itemstack1.field_77994_a == itemstack.field_77994_a)
/*     */       {
/* 109 */         return null;
/*     */       }
/*     */       
/* 112 */       slot.func_82870_a(par1EntityPlayer, itemstack1);
/*     */     }
/*     */     
/* 115 */     return itemstack;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/container/ContainerFocalManipulator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */