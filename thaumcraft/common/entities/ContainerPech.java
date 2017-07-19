/*     */ package thaumcraft.common.entities;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ICrafting;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.WeightedRandomChestContent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.ChestGenHooks;
/*     */ import thaumcraft.common.container.SlotOutput;
/*     */ import thaumcraft.common.entities.monster.EntityPech;
/*     */ 
/*     */ 
/*     */ public class ContainerPech
/*     */   extends Container
/*     */ {
/*     */   private EntityPech pech;
/*     */   private InventoryPech inventory;
/*     */   private EntityPlayer player;
/*     */   private final World theWorld;
/*     */   
/*     */   public ContainerPech(InventoryPlayer par1InventoryPlayer, World par3World, EntityPech par2IMerchant)
/*     */   {
/*  33 */     this.pech = par2IMerchant;
/*  34 */     this.theWorld = par3World;
/*  35 */     this.player = par1InventoryPlayer.field_70458_d;
/*  36 */     this.inventory = new InventoryPech(par1InventoryPlayer.field_70458_d, par2IMerchant, this);
/*  37 */     this.pech.trading = true;
/*  38 */     func_75146_a(new Slot(this.inventory, 0, 36, 29));
/*     */     
/*     */ 
/*     */ 
/*  42 */     for (int i = 0; i < 2; i++) {
/*  43 */       for (int j = 0; j < 2; j++) {
/*  44 */         func_75146_a(new SlotOutput(this.inventory, 1 + j + i * 2, 106 + 18 * j, 20 + 18 * i));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  49 */     for (i = 0; i < 3; i++)
/*     */     {
/*  51 */       for (int j = 0; j < 9; j++)
/*     */       {
/*  53 */         func_75146_a(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*     */       }
/*     */     }
/*     */     
/*  57 */     for (i = 0; i < 9; i++)
/*     */     {
/*  59 */       func_75146_a(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
/*     */     }
/*     */   }
/*     */   
/*     */   public InventoryPech getMerchantInventory()
/*     */   {
/*  65 */     return this.inventory;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_75132_a(ICrafting par1ICrafting)
/*     */   {
/*  71 */     super.func_75132_a(par1ICrafting);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75142_b()
/*     */   {
/*  82 */     super.func_75142_b();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_75140_a(EntityPlayer par1EntityPlayer, int par2)
/*     */   {
/*  89 */     if (par2 == 0) {
/*  90 */       generateContents();
/*  91 */       return true;
/*     */     }
/*  93 */     return super.func_75140_a(par1EntityPlayer, par2);
/*     */   }
/*     */   
/*  96 */   ChestGenHooks chest = ChestGenHooks.getInfo("dungeonChest");
/*     */   
/*     */   private boolean hasStuffInPack() {
/*  99 */     for (ItemStack stack : this.pech.loot) {
/* 100 */       if ((stack != null) && (stack.field_77994_a > 0))
/* 101 */         return true;
/*     */     }
/* 103 */     return false;
/*     */   }
/*     */   
/*     */   private void generateContents()
/*     */   {
/* 108 */     if ((!this.theWorld.field_72995_K) && (this.inventory.func_70301_a(0) != null) && (this.inventory.func_70301_a(1) == null) && (this.inventory.func_70301_a(2) == null) && (this.inventory.func_70301_a(3) == null) && (this.inventory.func_70301_a(4) == null))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 113 */       if (this.pech.isValued(this.inventory.func_70301_a(0))) {
/* 114 */         int value = this.pech.getValue(this.inventory.func_70301_a(0));
/* 115 */         if (this.theWorld.field_73012_v.nextInt(100) <= value / 2) {
/* 116 */           this.pech.setTamed(false);
/* 117 */           this.pech.updateAINextTick = true;
/* 118 */           this.pech.func_85030_a("thaumcraft:pech_trade", 0.4F, 1.0F);
/*     */         }
/*     */         
/* 121 */         if (this.theWorld.field_73012_v.nextInt(5) == 0) {
/* 122 */           value += this.theWorld.field_73012_v.nextInt(3);
/*     */         }
/* 124 */         else if (this.theWorld.field_73012_v.nextBoolean()) {
/* 125 */           value -= this.theWorld.field_73012_v.nextInt(3);
/*     */         }
/*     */         
/* 128 */         ArrayList<List> pos = (ArrayList)EntityPech.tradeInventory.get(Integer.valueOf(this.pech.getPechType()));
/* 129 */         while (value > 0) {
/* 130 */           int am = Math.min(5, Math.max((value + 1) / 2, this.theWorld.field_73012_v.nextInt(value) + 1));
/*     */           
/*     */ 
/*     */ 
/* 134 */           value -= am;
/*     */           
/* 136 */           if ((am == 1) && (this.theWorld.field_73012_v.nextBoolean()) && (hasStuffInPack())) {
/* 137 */             ArrayList<Integer> loot = new ArrayList();
/* 138 */             for (int a = 0; a < this.pech.loot.length; a++) {
/* 139 */               if ((this.pech.loot[a] != null) && (this.pech.loot[a].field_77994_a > 0))
/* 140 */                 loot.add(Integer.valueOf(a));
/*     */             }
/* 142 */             int r = ((Integer)loot.get(this.theWorld.field_73012_v.nextInt(loot.size()))).intValue();
/* 143 */             ItemStack is = this.pech.loot[r].func_77946_l();
/* 144 */             is.field_77994_a = 1;
/* 145 */             func_75135_a(is, 1, 5, false);
/* 146 */             this.pech.loot[r].field_77994_a -= 1;
/* 147 */             if (this.pech.loot[r].field_77994_a <= 0) this.pech.loot[r] = null;
/*     */           }
/* 149 */           else if ((am >= 4) && (this.theWorld.field_73012_v.nextBoolean())) {
/* 150 */             WeightedRandomChestContent[] contents = this.chest.getItems(this.theWorld.field_73012_v);
/* 151 */             WeightedRandomChestContent wc = null;
/* 152 */             int cc = 0;
/*     */             do {
/* 154 */               wc = contents[this.theWorld.field_73012_v.nextInt(contents.length)];
/* 155 */               cc++;
/* 156 */             } while ((cc < 50) && ((wc.field_76297_b == null) || (wc.field_76292_a > 5) || (wc.field_76296_e > 1)));
/*     */             
/* 158 */             if ((wc == null) || (wc.field_76297_b == null)) {
/* 159 */               value += am;
/*     */             }
/*     */             else {
/* 162 */               ItemStack is = wc.field_76297_b.func_77946_l();
/* 163 */               is.func_77980_a(this.theWorld, this.player, 0);
/* 164 */               func_75135_a(is, 1, 5, false);
/*     */             }
/* 166 */           } else { List it = null;
/*     */             do {
/* 168 */               it = (List)pos.get(this.theWorld.field_73012_v.nextInt(pos.size()));
/* 169 */             } while (((Integer)it.get(0)).intValue() != am);
/* 170 */             ItemStack is = ((ItemStack)it.get(1)).func_77946_l();
/* 171 */             is.func_77980_a(this.theWorld, this.player, 0);
/* 172 */             func_75135_a(is, 1, 5, false);
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 177 */         this.inventory.func_70298_a(0, 1);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_75137_b(int par1, int par2) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_75145_c(EntityPlayer par1EntityPlayer)
/*     */   {
/* 191 */     return this.pech.isTamed();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2)
/*     */   {
/* 200 */     ItemStack itemstack = null;
/* 201 */     Slot slot = (Slot)this.field_75151_b.get(par2);
/*     */     
/* 203 */     if ((slot != null) && (slot.func_75216_d()))
/*     */     {
/* 205 */       ItemStack itemstack1 = slot.func_75211_c();
/* 206 */       itemstack = itemstack1.func_77946_l();
/*     */       
/* 208 */       if (par2 == 0)
/*     */       {
/* 210 */         if (!func_75135_a(itemstack1, 5, 41, true))
/*     */         {
/* 212 */           return null;
/*     */         }
/*     */         
/*     */       }
/* 216 */       else if ((par2 >= 1) && (par2 < 5))
/*     */       {
/* 218 */         if (!func_75135_a(itemstack1, 5, 41, true))
/*     */         {
/* 220 */           return null;
/*     */         }
/*     */       }
/* 223 */       else if (par2 != 0)
/*     */       {
/* 225 */         if ((par2 >= 5) && (par2 < 41))
/*     */         {
/*     */ 
/* 228 */           if (!func_75135_a(itemstack1, 0, 1, true))
/*     */           {
/* 230 */             return null;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 235 */       if (itemstack1.field_77994_a == 0)
/*     */       {
/* 237 */         slot.func_75215_d((ItemStack)null);
/*     */       }
/*     */       else
/*     */       {
/* 241 */         slot.func_75218_e();
/*     */       }
/*     */       
/* 244 */       if (itemstack1.field_77994_a == itemstack.field_77994_a)
/*     */       {
/* 246 */         return null;
/*     */       }
/*     */       
/* 249 */       slot.func_82870_a(par1EntityPlayer, itemstack1);
/*     */     }
/*     */     
/* 252 */     return itemstack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75134_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 261 */     super.func_75134_a(par1EntityPlayer);
/* 262 */     this.pech.trading = false;
/*     */     
/* 264 */     if (!this.theWorld.field_72995_K)
/*     */     {
/*     */ 
/* 267 */       for (int a = 0; a < 5; a++)
/*     */       {
/* 269 */         ItemStack itemstack = this.inventory.func_70304_b(a);
/*     */         
/* 271 */         if (itemstack != null)
/*     */         {
/* 273 */           EntityItem ei = par1EntityPlayer.func_71019_a(itemstack, false);
/* 274 */           if (ei != null) {
/* 275 */             ei.func_145799_b("PechDrop");
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean func_75135_a(ItemStack p_75135_1_, int p_75135_2_, int p_75135_3_, boolean p_75135_4_)
/*     */   {
/* 287 */     boolean flag1 = false;
/* 288 */     int k = p_75135_2_;
/*     */     
/* 290 */     if (p_75135_4_)
/*     */     {
/* 292 */       k = p_75135_3_ - 1;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 298 */     if (p_75135_1_.func_77985_e())
/*     */     {
/* 300 */       while ((p_75135_1_.field_77994_a > 0) && (((!p_75135_4_) && (k < p_75135_3_)) || ((p_75135_4_) && (k >= p_75135_2_))))
/*     */       {
/* 302 */         Slot slot = (Slot)this.field_75151_b.get(k);
/* 303 */         ItemStack itemstack1 = slot.func_75211_c();
/*     */         
/* 305 */         if ((itemstack1 != null) && (itemstack1.func_77973_b() == p_75135_1_.func_77973_b()) && ((!p_75135_1_.func_77981_g()) || (p_75135_1_.func_77960_j() == itemstack1.func_77960_j())) && (ItemStack.func_77970_a(p_75135_1_, itemstack1)))
/*     */         {
/* 307 */           int l = itemstack1.field_77994_a + p_75135_1_.field_77994_a;
/*     */           
/* 309 */           if (l <= p_75135_1_.func_77976_d())
/*     */           {
/* 311 */             p_75135_1_.field_77994_a = 0;
/* 312 */             itemstack1.field_77994_a = l;
/* 313 */             slot.func_75218_e();
/* 314 */             flag1 = true;
/*     */           }
/* 316 */           else if (itemstack1.field_77994_a < p_75135_1_.func_77976_d())
/*     */           {
/* 318 */             p_75135_1_.field_77994_a -= p_75135_1_.func_77976_d() - itemstack1.field_77994_a;
/* 319 */             itemstack1.field_77994_a = p_75135_1_.func_77976_d();
/* 320 */             slot.func_75218_e();
/* 321 */             flag1 = true;
/*     */           }
/*     */         }
/*     */         
/* 325 */         if (p_75135_4_)
/*     */         {
/* 327 */           k--;
/*     */         }
/*     */         else
/*     */         {
/* 331 */           k++;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 336 */     if (p_75135_1_.field_77994_a > 0)
/*     */     {
/* 338 */       if (p_75135_4_)
/*     */       {
/* 340 */         k = p_75135_3_ - 1;
/*     */       }
/*     */       else
/*     */       {
/* 344 */         k = p_75135_2_;
/*     */       }
/*     */       
/* 347 */       while (((!p_75135_4_) && (k < p_75135_3_)) || ((p_75135_4_) && (k >= p_75135_2_)))
/*     */       {
/* 349 */         Slot slot = (Slot)this.field_75151_b.get(k);
/* 350 */         ItemStack itemstack1 = slot.func_75211_c();
/*     */         
/* 352 */         if (itemstack1 == null)
/*     */         {
/* 354 */           slot.func_75215_d(p_75135_1_.func_77946_l());
/* 355 */           slot.func_75218_e();
/* 356 */           p_75135_1_.field_77994_a = 0;
/* 357 */           flag1 = true;
/* 358 */           break;
/*     */         }
/*     */         
/* 361 */         if (p_75135_4_)
/*     */         {
/* 363 */           k--;
/*     */         }
/*     */         else
/*     */         {
/* 367 */           k++;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 372 */     return flag1;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ContainerPech.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */