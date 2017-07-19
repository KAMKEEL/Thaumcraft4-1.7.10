/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ICrafting;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.playerdata.PacketAspectPool;
/*     */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*     */ import thaumcraft.common.tiles.TileDeconstructionTable;
/*     */ 
/*     */ public class ContainerDeconstructionTable extends Container
/*     */ {
/*     */   private TileDeconstructionTable table;
/*     */   private int lastBreakTime;
/*     */   
/*     */   public ContainerDeconstructionTable(InventoryPlayer par1InventoryPlayer, TileDeconstructionTable tileEntity)
/*     */   {
/*  28 */     this.table = tileEntity;
/*  29 */     func_75146_a(new SlotLimitedHasAspects(tileEntity, 0, 64, 16));
/*     */     
/*     */ 
/*  32 */     for (int i = 0; i < 3; i++)
/*     */     {
/*  34 */       for (int j = 0; j < 9; j++)
/*     */       {
/*  36 */         func_75146_a(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*     */       }
/*     */     }
/*     */     
/*  40 */     for (i = 0; i < 9; i++)
/*     */     {
/*  42 */       func_75146_a(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_75132_a(ICrafting par1ICrafting)
/*     */   {
/*  49 */     super.func_75132_a(par1ICrafting);
/*  50 */     par1ICrafting.func_71112_a(this, 0, this.table.breaktime);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_75140_a(EntityPlayer p, int button)
/*     */   {
/*  57 */     if ((button == 1) && 
/*  58 */       (this.table.aspect != null)) {
/*  59 */       Thaumcraft.proxy.playerKnowledge.addAspectPool(p.func_70005_c_(), this.table.aspect, (short)1);
/*  60 */       thaumcraft.common.lib.research.ResearchManager.scheduleSave(p);
/*  61 */       PacketHandler.INSTANCE.sendTo(new PacketAspectPool(this.table.aspect.getTag(), Short.valueOf((short)1), Short.valueOf(Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(p.func_70005_c_(), this.table.aspect))), (net.minecraft.entity.player.EntityPlayerMP)p);
/*     */       
/*     */ 
/*     */ 
/*  65 */       this.table.aspect = null;
/*  66 */       this.table.func_145831_w().func_147471_g(this.table.field_145851_c, this.table.field_145848_d, this.table.field_145849_e);
/*     */     }
/*     */     
/*  69 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75142_b()
/*     */   {
/*  78 */     super.func_75142_b();
/*     */     
/*  80 */     for (int i = 0; i < this.field_75149_d.size(); i++)
/*     */     {
/*  82 */       ICrafting icrafting = (ICrafting)this.field_75149_d.get(i);
/*     */       
/*  84 */       if (this.lastBreakTime != this.table.breaktime)
/*     */       {
/*  86 */         icrafting.func_71112_a(this, 0, this.table.breaktime);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  91 */     this.lastBreakTime = this.table.breaktime;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public void func_75137_b(int par1, int par2)
/*     */   {
/*  98 */     if (par1 == 0)
/*     */     {
/* 100 */       this.table.breaktime = par2;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75145_c(EntityPlayer par1EntityPlayer)
/*     */   {
/* 109 */     return this.table.func_70300_a(par1EntityPlayer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2)
/*     */   {
/* 118 */     ItemStack itemstack = null;
/* 119 */     Slot slot = (Slot)this.field_75151_b.get(par2);
/*     */     
/* 121 */     if ((slot != null) && (slot.func_75216_d()))
/*     */     {
/* 123 */       ItemStack itemstack1 = slot.func_75211_c();
/* 124 */       itemstack = itemstack1.func_77946_l();
/*     */       
/* 126 */       if (par2 != 0)
/*     */       {
/* 128 */         AspectList al = ThaumcraftCraftingManager.getObjectTags(itemstack1);
/* 129 */         al = ThaumcraftCraftingManager.getBonusTags(itemstack1, al);
/*     */         
/* 131 */         if ((al != null) && (al.size() > 0))
/*     */         {
/* 133 */           if (!func_75135_a(itemstack1, 0, 1, false))
/*     */           {
/* 135 */             return null;
/*     */           }
/*     */         }
/* 138 */         else if ((par2 >= 1) && (par2 < 28))
/*     */         {
/* 140 */           if (!func_75135_a(itemstack1, 28, 37, false))
/*     */           {
/* 142 */             return null;
/*     */           }
/*     */         }
/* 145 */         else if ((par2 >= 28) && (par2 < 37) && (!func_75135_a(itemstack1, 1, 28, false)))
/*     */         {
/* 147 */           return null;
/*     */         }
/*     */       }
/* 150 */       else if (!func_75135_a(itemstack1, 1, 37, false))
/*     */       {
/* 152 */         return null;
/*     */       }
/*     */       
/* 155 */       if (itemstack1.field_77994_a == 0)
/*     */       {
/* 157 */         slot.func_75215_d((ItemStack)null);
/*     */       }
/*     */       else
/*     */       {
/* 161 */         slot.func_75218_e();
/*     */       }
/*     */       
/* 164 */       if (itemstack1.field_77994_a == itemstack.field_77994_a)
/*     */       {
/* 166 */         return null;
/*     */       }
/*     */       
/* 169 */       slot.func_82870_a(par1EntityPlayer, itemstack1);
/*     */     }
/*     */     
/* 172 */     return itemstack;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/container/ContainerDeconstructionTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */