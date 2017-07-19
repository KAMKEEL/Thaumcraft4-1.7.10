/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import cpw.mods.fml.common.registry.GameRegistry;
/*     */ import cpw.mods.fml.common.registry.GameRegistry.UniqueIdentifier;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.api.WorldCoordinates;
/*     */ 
/*     */ public class TileMagicBox extends TileThaumcraft implements IInventory
/*     */ {
/*  20 */   ArrayList<ItemStack> boxContents = new ArrayList();
/*     */   
/*  22 */   WorldCoordinates master = null;
/*     */   
/*  24 */   byte sorting = -1;
/*  25 */   short linkedBoxes = -1;
/*     */   
/*     */   public static thaumcraft.common.container.ContainerMagicBox tc;
/*     */   
/*     */   public int func_70302_i_()
/*     */   {
/*  31 */     return 27 * (getInventory().linkedBoxes + 1);
/*     */   }
/*     */   
/*     */   private ArrayList<ItemStack> getContents() {
/*  35 */     return this.master != null ? getInventory().boxContents : this.boxContents;
/*     */   }
/*     */   
/*     */   private TileMagicBox getInventory() {
/*  39 */     TileEntity tile = null;
/*  40 */     if (this.master != null) {
/*  41 */       tile = this.field_145850_b.func_147438_o(this.master.x, this.master.y, this.master.z);
/*     */     }
/*  43 */     return (tile != null) && ((tile instanceof TileMagicBox)) ? (TileMagicBox)tile : this;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_70301_a(int par1)
/*     */   {
/*  49 */     return par1 >= getContents().size() ? null : (ItemStack)getContents().get(par1);
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_70298_a(int par1, int par2)
/*     */   {
/*  55 */     if ((par1 < getContents().size()) && (getContents().get(par1) != null))
/*     */     {
/*     */ 
/*     */ 
/*  59 */       if (((ItemStack)getContents().get(par1)).field_77994_a <= par2)
/*     */       {
/*  61 */         ItemStack var3 = (ItemStack)getContents().get(par1);
/*  62 */         getContents().remove(par1);
/*  63 */         getInventory().func_70296_d();
/*  64 */         return var3;
/*     */       }
/*     */       
/*     */ 
/*  68 */       ItemStack var3 = ((ItemStack)getContents().get(par1)).func_77979_a(par2);
/*     */       
/*  70 */       if (((ItemStack)getContents().get(par1)).field_77994_a == 0)
/*     */       {
/*  72 */         getContents().remove(par1);
/*     */       }
/*     */       
/*  75 */       getInventory().func_70296_d();
/*  76 */       return var3;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  81 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack func_70304_b(int par1)
/*     */   {
/*  88 */     if ((par1 < getContents().size()) && (getContents().get(par1) != null))
/*     */     {
/*  90 */       ItemStack var2 = (ItemStack)getContents().get(par1);
/*  91 */       getContents().remove(par1);
/*  92 */       return var2;
/*     */     }
/*     */     
/*     */ 
/*  96 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70299_a(int par1, ItemStack par2ItemStack)
/*     */   {
/* 103 */     if ((par1 >= getContents().size()) && (par2ItemStack != null) && (par2ItemStack.field_77994_a > 0)) {
/* 104 */       getContents().add(par2ItemStack);
/*     */     }
/* 106 */     else if ((par2ItemStack != null) && (par2ItemStack.field_77994_a > 0)) {
/* 107 */       getContents().set(par1, par2ItemStack);
/* 108 */     } else if (par1 < getContents().size()) {
/* 109 */       getContents().remove(par1);
/*     */     }
/*     */     
/* 112 */     if ((par2ItemStack != null) && (par2ItemStack.field_77994_a > func_70297_j_()))
/*     */     {
/* 114 */       par2ItemStack.field_77994_a = func_70297_j_();
/*     */     }
/* 116 */     getInventory().func_70296_d();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70296_d()
/*     */   {
/* 123 */     super.func_70296_d();
/* 124 */     sort();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String func_145825_b()
/*     */   {
/* 133 */     return "Magic Box";
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_145839_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 139 */     super.func_145839_a(par1NBTTagCompound);
/*     */     
/* 141 */     NBTTagList var2 = par1NBTTagCompound.func_150295_c("Items", 10);
/* 142 */     this.boxContents = new ArrayList();
/*     */     
/* 144 */     for (int var3 = 0; var3 < var2.func_74745_c(); var3++)
/*     */     {
/* 146 */       NBTTagCompound var4 = var2.func_150305_b(var3);
/* 147 */       this.boxContents.add(ItemStack.func_77949_a(var4));
/*     */     }
/*     */     
/* 150 */     sort();
/*     */   }
/*     */   
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 156 */     this.sorting = par1NBTTagCompound.func_74771_c("sort");
/* 157 */     this.master = null;
/* 158 */     if (par1NBTTagCompound.func_74764_b("w_x")) {
/* 159 */       this.master = new WorldCoordinates();
/* 160 */       this.master.readNBT(par1NBTTagCompound);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_145841_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 167 */     super.func_145841_b(par1NBTTagCompound);
/* 168 */     NBTTagList var2 = new NBTTagList();
/* 169 */     for (int var3 = 0; var3 < this.boxContents.size(); var3++)
/*     */     {
/* 171 */       if (this.boxContents.get(var3) != null)
/*     */       {
/* 173 */         NBTTagCompound var4 = new NBTTagCompound();
/* 174 */         ((ItemStack)this.boxContents.get(var3)).func_77955_b(var4);
/* 175 */         var2.func_74742_a(var4);
/*     */       }
/*     */     }
/* 178 */     par1NBTTagCompound.func_74782_a("Items", var2);
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 184 */     par1NBTTagCompound.func_74774_a("sort", this.sorting);
/* 185 */     if (this.master != null) {
/* 186 */       this.master.writeNBT(par1NBTTagCompound);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70297_j_()
/*     */   {
/* 193 */     return 64;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 199 */     return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_145845_h()
/*     */   {
/* 211 */     super.func_145845_h();
/*     */     
/* 213 */     if ((getInventory() == this) && (this.linkedBoxes < 0)) {
/* 214 */       refreshLinks();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_145842_c(int par1, int par2)
/*     */   {
/* 221 */     if (par1 == 1)
/*     */     {
/* 223 */       return true;
/*     */     }
/*     */     
/* 226 */     if (par1 == 2)
/*     */     {
/* 228 */       return true;
/*     */     }
/* 230 */     return this.field_145846_f;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70295_k_() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70305_f() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_145843_s()
/*     */   {
/* 251 */     func_145836_u();
/* 252 */     super.func_145843_s();
/*     */   }
/*     */   
/*     */   public boolean func_145818_k_()
/*     */   {
/* 257 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack itemstack)
/*     */   {
/* 262 */     return true;
/*     */   }
/*     */   
/*     */   public void sort()
/*     */   {
/* 267 */     if ((func_145831_w() == null) || (this.sorting < 0)) { return;
/*     */     }
/* 269 */     boolean done = false;
/* 270 */     label269: while (!done) {
/* 271 */       done = true;
/*     */       
/* 273 */       for (int i = 0;; i++) { if (i >= getContents().size() - 1) {
/*     */           break label269;
/*     */         }
/* 276 */         done = swopSlots(i, i + 1);
/*     */         
/*     */ 
/*     */ 
/* 280 */         if ((((ItemStack)getContents().get(i)).field_77994_a < ((ItemStack)getContents().get(i)).func_77976_d()) && (((ItemStack)getContents().get(i)).func_77969_a((ItemStack)getContents().get(i + 1))) && (ItemStack.func_77970_a((ItemStack)getContents().get(i), (ItemStack)getContents().get(i + 1))))
/*     */         {
/*     */ 
/* 283 */           ItemStack is1 = ((ItemStack)getContents().get(i)).func_77946_l();
/* 284 */           ItemStack is2 = ((ItemStack)getContents().get(i + 1)).func_77946_l();
/*     */           
/* 286 */           int c = Math.min(is1.func_77976_d() - is1.field_77994_a, is2.field_77994_a);
/*     */           
/* 288 */           is1.field_77994_a += c;
/* 289 */           is2.field_77994_a -= c;
/*     */           
/* 291 */           getContents().set(i, is1);
/* 292 */           done = false;
/* 293 */           if (is2.field_77994_a > 0) {
/* 294 */             getContents().set(i + 1, is2);
/*     */           } else {
/* 296 */             getContents().remove(i + 1);
/* 297 */             break;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean swopSlots(int i, int j)
/*     */   {
/* 306 */     if ((this.sorting == 0) || (this.sorting == 1)) {
/* 307 */       if ((((ItemStack)getContents().get(i)).func_82833_r() != null) && (((ItemStack)getContents().get(j)).func_82833_r() != null))
/*     */       {
/* 309 */         String s1 = "";
/* 310 */         String s2 = "";
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 323 */         s1 = s1 + ((ItemStack)getContents().get(i)).func_82833_r();
/* 324 */         s2 = s2 + ((ItemStack)getContents().get(j)).func_82833_r();
/* 325 */         if (((ItemStack)getContents().get(i)).func_77942_o()) {
/* 326 */           s1 = s1 + "" + ((ItemStack)getContents().get(i)).field_77990_d.hashCode();
/*     */         }
/*     */         
/* 329 */         if (((ItemStack)getContents().get(j)).func_77942_o()) {
/* 330 */           s2 = s2 + "" + ((ItemStack)getContents().get(j)).field_77990_d.hashCode();
/*     */         }
/* 332 */         int r = s1.compareToIgnoreCase(s2);
/* 333 */         if (((r > 0) && (this.sorting == 0)) || ((r < 0) && (this.sorting == 1))) {
/* 334 */           ItemStack is1 = ((ItemStack)getContents().get(i)).func_77946_l();
/* 335 */           ItemStack is2 = ((ItemStack)getContents().get(j)).func_77946_l();
/* 336 */           getContents().set(i, is2);
/* 337 */           getContents().set(j, is1);
/* 338 */           return false;
/*     */         }
/*     */         
/*     */       }
/*     */     }
/* 343 */     else if ((this.sorting == 2) && 
/* 344 */       (((ItemStack)getContents().get(i)).func_82833_r() != null) && (((ItemStack)getContents().get(j)).func_82833_r() != null))
/*     */     {
/* 346 */       String s1 = "";
/* 347 */       String s2 = "";
/* 348 */       if (GameRegistry.findUniqueIdentifierFor(((ItemStack)getContents().get(i)).func_77973_b()) != null) {
/* 349 */         s1 = s1 + GameRegistry.findUniqueIdentifierFor(((ItemStack)getContents().get(i)).func_77973_b()).modId;
/*     */       }
/* 351 */       else if (GameRegistry.findUniqueIdentifierFor(Block.func_149634_a(((ItemStack)getContents().get(i)).func_77973_b())) != null) {
/* 352 */         s1 = s1 + GameRegistry.findUniqueIdentifierFor(Block.func_149634_a(((ItemStack)getContents().get(i)).func_77973_b())).modId;
/*     */       }
/* 354 */       if (GameRegistry.findUniqueIdentifierFor(((ItemStack)getContents().get(j)).func_77973_b()) != null) {
/* 355 */         s1 = s1 + GameRegistry.findUniqueIdentifierFor(((ItemStack)getContents().get(j)).func_77973_b()).modId;
/*     */       }
/* 357 */       else if (GameRegistry.findUniqueIdentifierFor(Block.func_149634_a(((ItemStack)getContents().get(j)).func_77973_b())) != null) {
/* 358 */         s1 = s1 + GameRegistry.findUniqueIdentifierFor(Block.func_149634_a(((ItemStack)getContents().get(j)).func_77973_b())).modId;
/*     */       }
/*     */       
/* 361 */       s1 = s1 + ((ItemStack)getContents().get(i)).func_82833_r();
/* 362 */       s2 = s2 + ((ItemStack)getContents().get(j)).func_82833_r();
/* 363 */       if (((ItemStack)getContents().get(i)).func_77942_o()) {
/* 364 */         s1 = s1 + "" + ((ItemStack)getContents().get(i)).field_77990_d.hashCode();
/*     */       }
/* 366 */       if (((ItemStack)getContents().get(j)).func_77942_o()) {
/* 367 */         s2 = s2 + "" + ((ItemStack)getContents().get(j)).field_77990_d.hashCode();
/*     */       }
/* 369 */       int r = s1.compareToIgnoreCase(s2);
/* 370 */       if ((r > 0) && (this.sorting == 2)) {
/* 371 */         ItemStack is1 = ((ItemStack)getContents().get(i)).func_77946_l();
/* 372 */         ItemStack is2 = ((ItemStack)getContents().get(j)).func_77946_l();
/* 373 */         getContents().set(i, is2);
/* 374 */         getContents().set(j, is1);
/* 375 */         return false;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 381 */     return true;
/*     */   }
/*     */   
/*     */   public void refreshLinks() {
/* 385 */     if (getInventory() != this) return;
/* 386 */     this.linkedBoxes = 0;
/* 387 */     ArrayList<WorldCoordinates> list = new ArrayList();
/* 388 */     findBoxes(this.field_145851_c, this.field_145848_d, this.field_145849_e, list);
/* 389 */     this.linkedBoxes = ((short)list.size());
/*     */   }
/*     */   
/*     */   private void findBoxes(int x, int y, int z, ArrayList<WorldCoordinates> list) {
/* 393 */     if (list.size() >= 1024) { return;
/*     */     }
/* 395 */     for (int a = 0; a < 6; a++) {
/* 396 */       ForgeDirection dir = ForgeDirection.getOrientation(a);
/* 397 */       TileEntity tile = this.field_145850_b.func_147438_o(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
/* 398 */       if ((tile != null) && ((tile instanceof TileMagicBox))) {
/* 399 */         WorldCoordinates wc = new WorldCoordinates(tile);
/* 400 */         if (!list.contains(wc)) {
/* 401 */           list.add(wc);
/* 402 */           findBoxes(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ, list);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileMagicBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */