/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class TileChestHungry extends TileEntity implements net.minecraft.inventory.IInventory
/*     */ {
/*  13 */   private ItemStack[] chestContents = new ItemStack[36];
/*     */   
/*     */   public float lidAngle;
/*     */   
/*     */   public float prevLidAngle;
/*     */   
/*     */   public int numUsingPlayers;
/*     */   
/*     */   private int ticksSinceSync;
/*     */   
/*     */ 
/*     */   public int func_70302_i_()
/*     */   {
/*  26 */     return 27;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_70301_a(int par1)
/*     */   {
/*  32 */     return this.chestContents[par1];
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_70298_a(int par1, int par2)
/*     */   {
/*  38 */     if (this.chestContents[par1] != null)
/*     */     {
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
/*  51 */       ItemStack var3 = this.chestContents[par1].func_77979_a(par2);
/*     */       
/*  53 */       if (this.chestContents[par1].field_77994_a == 0)
/*     */       {
/*  55 */         this.chestContents[par1] = null;
/*     */       }
/*     */       
/*  58 */       func_70296_d();
/*  59 */       return var3;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  64 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack func_70304_b(int par1)
/*     */   {
/*  71 */     if (this.chestContents[par1] != null)
/*     */     {
/*  73 */       ItemStack var2 = this.chestContents[par1];
/*  74 */       this.chestContents[par1] = null;
/*  75 */       return var2;
/*     */     }
/*     */     
/*     */ 
/*  79 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70299_a(int par1, ItemStack par2ItemStack)
/*     */   {
/*  86 */     this.chestContents[par1] = par2ItemStack;
/*     */     
/*  88 */     if ((par2ItemStack != null) && (par2ItemStack.field_77994_a > func_70297_j_()))
/*     */     {
/*  90 */       par2ItemStack.field_77994_a = func_70297_j_();
/*     */     }
/*     */     
/*  93 */     func_70296_d();
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_145825_b()
/*     */   {
/*  99 */     return "Hungry Chest";
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_145839_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 105 */     super.func_145839_a(par1NBTTagCompound);
/* 106 */     NBTTagList var2 = par1NBTTagCompound.func_150295_c("Items", 10);
/* 107 */     this.chestContents = new ItemStack[func_70302_i_()];
/*     */     
/* 109 */     for (int var3 = 0; var3 < var2.func_74745_c(); var3++)
/*     */     {
/* 111 */       NBTTagCompound var4 = var2.func_150305_b(var3);
/* 112 */       int var5 = var4.func_74771_c("Slot") & 0xFF;
/*     */       
/* 114 */       if ((var5 >= 0) && (var5 < this.chestContents.length))
/*     */       {
/* 116 */         this.chestContents[var5] = ItemStack.func_77949_a(var4);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_145841_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 124 */     super.func_145841_b(par1NBTTagCompound);
/* 125 */     NBTTagList var2 = new NBTTagList();
/*     */     
/* 127 */     for (int var3 = 0; var3 < this.chestContents.length; var3++)
/*     */     {
/* 129 */       if (this.chestContents[var3] != null)
/*     */       {
/* 131 */         NBTTagCompound var4 = new NBTTagCompound();
/* 132 */         var4.func_74774_a("Slot", (byte)var3);
/* 133 */         this.chestContents[var3].func_77955_b(var4);
/* 134 */         var2.func_74742_a(var4);
/*     */       }
/*     */     }
/*     */     
/* 138 */     par1NBTTagCompound.func_74782_a("Items", var2);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70297_j_()
/*     */   {
/* 144 */     return 64;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70300_a(EntityPlayer par1EntityPlayer)
/*     */   {
/* 150 */     return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_145836_u()
/*     */   {
/* 157 */     super.func_145836_u();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_145845_h()
/*     */   {
/* 167 */     super.func_145845_h();
/*     */     
/* 169 */     if (++this.ticksSinceSync % 20 * 4 == 0) {}
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 174 */     this.prevLidAngle = this.lidAngle;
/* 175 */     float var1 = 0.1F;
/*     */     
/*     */ 
/* 178 */     if ((this.numUsingPlayers > 0) && (this.lidAngle == 0.0F))
/*     */     {
/* 180 */       double var2 = this.field_145851_c + 0.5D;
/* 181 */       double var4 = this.field_145849_e + 0.5D;
/* 182 */       this.field_145850_b.func_72908_a(var2, this.field_145848_d + 0.5D, var4, "random.chestopen", 0.5F, this.field_145850_b.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*     */     }
/*     */     
/* 185 */     if (((this.numUsingPlayers == 0) && (this.lidAngle > 0.0F)) || ((this.numUsingPlayers > 0) && (this.lidAngle < 1.0F)))
/*     */     {
/* 187 */       float var8 = this.lidAngle;
/*     */       
/* 189 */       if (this.numUsingPlayers > 0)
/*     */       {
/* 191 */         this.lidAngle += var1;
/*     */       }
/*     */       else
/*     */       {
/* 195 */         this.lidAngle -= var1;
/*     */       }
/*     */       
/* 198 */       if (this.lidAngle > 1.0F)
/*     */       {
/* 200 */         this.lidAngle = 1.0F;
/*     */       }
/*     */       
/* 203 */       float var3 = 0.5F;
/*     */       
/* 205 */       if ((this.lidAngle < var3) && (var8 >= var3))
/*     */       {
/* 207 */         double var4 = this.field_145851_c + 0.5D;
/* 208 */         double var6 = this.field_145849_e + 0.5D;
/* 209 */         this.field_145850_b.func_72908_a(var4, this.field_145848_d + 0.5D, var6, "random.chestclosed", 0.5F, this.field_145850_b.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*     */       }
/*     */       
/* 212 */       if (this.lidAngle < 0.0F)
/*     */       {
/* 214 */         this.lidAngle = 0.0F;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_145842_c(int par1, int par2)
/*     */   {
/* 222 */     if (par1 == 1)
/*     */     {
/* 224 */       this.numUsingPlayers = par2;
/* 225 */       return true;
/*     */     }
/*     */     
/* 228 */     if (par1 == 2)
/*     */     {
/* 230 */       if (this.lidAngle < par2 / 10.0F) this.lidAngle = (par2 / 10.0F);
/* 231 */       return true;
/*     */     }
/* 233 */     return this.field_145846_f;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70295_k_()
/*     */   {
/* 239 */     this.numUsingPlayers += 1;
/* 240 */     this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, thaumcraft.common.config.ConfigBlocks.blockChestHungry, 1, this.numUsingPlayers);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70305_f()
/*     */   {
/* 246 */     this.numUsingPlayers -= 1;
/* 247 */     this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, thaumcraft.common.config.ConfigBlocks.blockChestHungry, 1, this.numUsingPlayers);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_145843_s()
/*     */   {
/* 256 */     func_145836_u();
/* 257 */     super.func_145843_s();
/*     */   }
/*     */   
/*     */   public boolean func_145818_k_()
/*     */   {
/* 262 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack itemstack)
/*     */   {
/* 267 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileChestHungry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */