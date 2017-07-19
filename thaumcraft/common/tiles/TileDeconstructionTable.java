/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.ISidedInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ 
/*     */ public class TileDeconstructionTable
/*     */   extends TileThaumcraft
/*     */   implements ISidedInventory
/*     */ {
/*     */   public Aspect aspect;
/*     */   public int breaktime;
/*  24 */   private ItemStack[] itemStacks = new ItemStack[1];
/*     */   
/*     */ 
/*     */ 
/*     */   private String customName;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_70302_i_()
/*     */   {
/*  35 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70301_a(int par1)
/*     */   {
/*  44 */     return this.itemStacks[par1];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_70298_a(int par1, int par2)
/*     */   {
/*  54 */     if (this.itemStacks[par1] != null)
/*     */     {
/*     */ 
/*     */ 
/*  58 */       if (this.itemStacks[par1].field_77994_a <= par2)
/*     */       {
/*  60 */         ItemStack itemstack = this.itemStacks[par1];
/*  61 */         this.itemStacks[par1] = null;
/*  62 */         func_70296_d();
/*  63 */         return itemstack;
/*     */       }
/*     */       
/*     */ 
/*  67 */       ItemStack itemstack = this.itemStacks[par1].func_77979_a(par2);
/*     */       
/*  69 */       if (this.itemStacks[par1].field_77994_a == 0)
/*     */       {
/*  71 */         this.itemStacks[par1] = null;
/*     */       }
/*  73 */       func_70296_d();
/*  74 */       return itemstack;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  79 */     return null;
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
/*  90 */     if (this.itemStacks[par1] != null)
/*     */     {
/*  92 */       ItemStack itemstack = this.itemStacks[par1];
/*  93 */       this.itemStacks[par1] = null;
/*  94 */       func_70296_d();
/*  95 */       return itemstack;
/*     */     }
/*     */     
/*     */ 
/*  99 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70299_a(int par1, ItemStack par2ItemStack)
/*     */   {
/* 109 */     this.itemStacks[par1] = par2ItemStack;
/*     */     
/* 111 */     if ((par2ItemStack != null) && (par2ItemStack.field_77994_a > func_70297_j_()))
/*     */     {
/* 113 */       par2ItemStack.field_77994_a = func_70297_j_();
/*     */     }
/* 115 */     func_70296_d();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String func_145825_b()
/*     */   {
/* 124 */     return func_145818_k_() ? this.customName : "container.decontable";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_145818_k_()
/*     */   {
/* 134 */     return (this.customName != null) && (this.customName.length() > 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setGuiDisplayName(String par1Str)
/*     */   {
/* 142 */     this.customName = par1Str;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 149 */     this.aspect = Aspect.getAspect(nbttagcompound.func_74779_i("Aspect"));
/* 150 */     NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
/* 151 */     this.itemStacks = new ItemStack[func_70302_i_()];
/*     */     
/* 153 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++)
/*     */     {
/* 155 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 156 */       byte b0 = nbttagcompound1.func_74771_c("Slot");
/*     */       
/* 158 */       if ((b0 >= 0) && (b0 < this.itemStacks.length))
/*     */       {
/* 160 */         this.itemStacks[b0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 167 */     if (this.aspect != null) nbttagcompound.func_74778_a("Aspect", this.aspect.getTag());
/* 168 */     NBTTagList nbttaglist = new NBTTagList();
/*     */     
/* 170 */     for (int i = 0; i < this.itemStacks.length; i++)
/*     */     {
/* 172 */       if (this.itemStacks[i] != null)
/*     */       {
/* 174 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 175 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 176 */         this.itemStacks[i].func_77955_b(nbttagcompound1);
/* 177 */         nbttaglist.func_74742_a(nbttagcompound1);
/*     */       }
/*     */     }
/*     */     
/* 181 */     nbttagcompound.func_74782_a("Items", nbttaglist);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_145839_a(NBTTagCompound nbtCompound)
/*     */   {
/* 190 */     super.func_145839_a(nbtCompound);
/*     */     
/*     */ 
/*     */ 
/* 194 */     if (nbtCompound.func_74764_b("CustomName"))
/*     */     {
/* 196 */       this.customName = nbtCompound.func_74779_i("CustomName");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_145841_b(NBTTagCompound nbtCompound)
/*     */   {
/* 208 */     super.func_145841_b(nbtCompound);
/*     */     
/* 210 */     if (func_145818_k_())
/*     */     {
/* 212 */       nbtCompound.func_74778_a("CustomName", this.customName);
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
/* 224 */     return 64;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getBreakTimeScaled(int par1)
/*     */   {
/* 235 */     return this.breaktime * par1 / 40;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canUpdate()
/*     */   {
/* 241 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_145845_h()
/*     */   {
/* 251 */     boolean flag1 = false;
/* 252 */     if (!this.field_145850_b.field_72995_K)
/*     */     {
/*     */ 
/* 255 */       if ((this.breaktime == 0) && (canBreak()))
/*     */       {
/* 257 */         this.breaktime = 40;
/* 258 */         flag1 = true;
/*     */       }
/*     */       
/* 261 */       if ((this.breaktime > 0) && (canBreak()))
/*     */       {
/* 263 */         this.breaktime -= 1;
/*     */         
/* 265 */         if (this.breaktime == 0)
/*     */         {
/* 267 */           this.breaktime = 0;
/* 268 */           breakItem();
/* 269 */           flag1 = true;
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 274 */         this.breaktime = 0;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 279 */     if (flag1)
/*     */     {
/* 281 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 282 */       func_70296_d();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean canBreak()
/*     */   {
/* 291 */     if ((this.itemStacks[0] == null) || (this.aspect != null))
/*     */     {
/* 293 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 297 */     AspectList al = ThaumcraftCraftingManager.getObjectTags(this.itemStacks[0]);
/* 298 */     al = ThaumcraftCraftingManager.getBonusTags(this.itemStacks[0], al);
/*     */     
/* 300 */     if ((al == null) || (al.size() == 0)) { return false;
/*     */     }
/* 302 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void breakItem()
/*     */   {
/* 311 */     if (canBreak())
/*     */     {
/*     */ 
/* 314 */       AspectList al = ThaumcraftCraftingManager.getObjectTags(this.itemStacks[0]);
/* 315 */       al = ThaumcraftCraftingManager.getBonusTags(this.itemStacks[0], al);
/*     */       
/* 317 */       AspectList primals = ResearchManager.reduceToPrimals(al);
/* 318 */       if (this.field_145850_b.field_73012_v.nextInt(80) < primals.visSize()) {
/* 319 */         this.aspect = primals.getAspects()[this.field_145850_b.field_73012_v.nextInt(primals.getAspects().length)];
/*     */       }
/*     */       
/* 322 */       this.itemStacks[0].field_77994_a -= 1;
/*     */       
/* 324 */       if (this.itemStacks[0].field_77994_a <= 0)
/*     */       {
/* 326 */         this.itemStacks[0] = null;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 337 */     return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this;
/*     */   }
/*     */   
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
/* 353 */     AspectList al = ThaumcraftCraftingManager.getObjectTags(par2ItemStack);
/* 354 */     al = ThaumcraftCraftingManager.getBonusTags(par2ItemStack, al);
/* 355 */     if ((al != null) && (al.size() > 0))
/* 356 */       return true;
/* 357 */     return false;
/*     */   }
/*     */   
/* 360 */   private static final int[] sides = { 0 };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int[] func_94128_d(int par1)
/*     */   {
/* 369 */     return par1 != 1 ? sides : new int[0];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_102007_a(int par1, ItemStack par2ItemStack, int par3)
/*     */   {
/* 379 */     return par3 == 1 ? false : func_94041_b(par1, par2ItemStack);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_102008_b(int par1, ItemStack par2ItemStack, int par3)
/*     */   {
/* 389 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileDeconstructionTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */