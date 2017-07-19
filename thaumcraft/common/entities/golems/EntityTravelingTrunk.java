/*     */ package thaumcraft.common.entities.golems;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.Block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.BaseAttributeMap;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemFood;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.lib.events.EventHandlerEntity;
/*     */ 
/*     */ public class EntityTravelingTrunk extends EntityLiving implements net.minecraft.entity.IEntityOwnable
/*     */ {
/*     */   public EntityTravelingTrunk(World world)
/*     */   {
/*  43 */     super(world);
/*  44 */     this.jumpDelay = 0;
/*  45 */     this.field_70156_m = true;
/*  46 */     this.jumpDelay = (this.field_70146_Z.nextInt(20) + 10);
/*  47 */     this.field_70178_ae = true;
/*  48 */     this.field_70174_ab = 10;
/*  49 */     this.lidrot = 0.0F;
/*  50 */     func_110163_bv();
/*  51 */     func_70105_a(0.8F, 0.8F);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  57 */     super.func_110147_ax();
/*  58 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(75.0D);
/*  59 */     func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
/*  60 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  66 */     super.func_70088_a();
/*  67 */     this.field_70180_af.func_75682_a(15, new Byte((byte)0));
/*  68 */     this.field_70180_af.func_75682_a(16, new Byte((byte)0));
/*  69 */     this.field_70180_af.func_75682_a(17, "");
/*  70 */     this.field_70180_af.func_75682_a(18, Byte.valueOf((byte)-1));
/*  71 */     this.field_70180_af.func_75682_a(19, new Byte((byte)0));
/*  72 */     this.field_70180_af.func_75682_a(20, new Short((short)0));
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource ds, float par2)
/*     */   {
/*  77 */     if (ds == DamageSource.field_76367_g) return false;
/*  78 */     if (getUpgrade() == 3) return false;
/*  79 */     return super.func_70097_a(ds, par2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound nbttagcompound)
/*     */   {
/*  86 */     super.func_70014_b(nbttagcompound);
/*  87 */     nbttagcompound.func_74757_a("Stay", getStay());
/*  88 */     nbttagcompound.func_74774_a("upgrade", (byte)getUpgrade());
/*     */     
/*  90 */     if (func_152113_b() == null)
/*     */     {
/*  92 */       nbttagcompound.func_74778_a("Owner", "");
/*     */     }
/*     */     else
/*     */     {
/*  96 */       nbttagcompound.func_74778_a("Owner", func_152113_b());
/*     */     }
/*     */     
/*  99 */     nbttagcompound.func_74782_a("Inventory", this.inventory.writeToNBT(new NBTTagList()));
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound nbttagcompound)
/*     */   {
/* 105 */     super.func_70037_a(nbttagcompound);
/* 106 */     setStay(nbttagcompound.func_74767_n("Stay"));
/*     */     
/* 108 */     setUpgrade(nbttagcompound.func_74771_c("upgrade"));
/*     */     
/* 110 */     String s = nbttagcompound.func_74779_i("Owner");
/*     */     
/* 112 */     if (s.length() > 0)
/*     */     {
/* 114 */       setOwner(s);
/*     */     }
/*     */     
/* 117 */     NBTTagList nbttaglist = nbttagcompound.func_150295_c("Inventory", 10);
/* 118 */     this.inventory.readFromNBT(nbttaglist);
/*     */     
/* 120 */     setInvSize();
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/* 125 */     super.func_70636_d();
/* 126 */     if (getUpgrade() == 5) {
/* 127 */       pullItems();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 135 */     super.func_70071_h_();
/*     */     
/* 137 */     if (this.field_70171_ac) {
/* 138 */       this.field_70181_x += 0.032999999821186066D;
/*     */     }
/* 140 */     if (this.field_70170_p.field_72995_K) {
/* 141 */       if (!this.field_70122_E)
/*     */       {
/* 143 */         if ((this.field_70181_x < 0.0D) && (!this.field_70171_ac)) { this.lidrot += 0.015F;
/*     */         }
/*     */       }
/* 146 */       if ((this.field_70122_E) || (this.field_70171_ac))
/*     */       {
/* 148 */         if (!isOpen()) {
/* 149 */           this.lidrot -= 0.1F;
/* 150 */           if (this.lidrot < 0.0F) { this.lidrot = 0.0F;
/*     */           }
/*     */         }
/*     */       }
/* 154 */       if (isOpen()) this.lidrot += 0.035F;
/* 155 */       if (this.lidrot > (isOpen() ? 0.5F : 0.2F)) this.lidrot = (isOpen() ? 0.5F : 0.2F);
/*     */     }
/* 157 */     else if ((func_110143_aJ() < func_110138_aP()) && ((getUpgrade() == 3) || (this.field_70173_aa % 50 == 0))) {
/* 158 */       func_70691_i(1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_70626_be()
/*     */   {
/* 166 */     if (getAnger() > 0) setAnger(getAnger() - 1);
/* 167 */     if (this.eatDelay > 0) this.eatDelay -= 1;
/* 168 */     this.field_70143_R = 0.0F;
/* 169 */     if (func_70902_q() != null)
/*     */     {
/* 171 */       if (!this.field_70170_p.field_72995_K) {
/* 172 */         ArrayList<WeakReference<Entity>> ll = (ArrayList)EventHandlerEntity.linkedEntities.get(func_70902_q().func_70005_c_());
/* 173 */         if (ll == null) {
/* 174 */           ll = new ArrayList();
/*     */         }
/* 176 */         boolean add = true;
/* 177 */         for (WeakReference<Entity> trunk : ll) {
/* 178 */           if ((trunk.get() != null) && (((Entity)trunk.get()).func_145782_y() == func_145782_y())) {
/* 179 */             add = false;
/* 180 */             break;
/*     */           }
/*     */         }
/* 183 */         if (add) {
/* 184 */           ll.add(new WeakReference(this));
/* 185 */           EventHandlerEntity.linkedEntities.put(func_70902_q().func_70005_c_(), ll);
/*     */         }
/*     */       }
/*     */       
/* 189 */       if ((!getStay()) && 
/* 190 */         (func_70902_q() != null) && (func_70032_d(func_70902_q()) > 20.0F))
/*     */       {
/* 192 */         int i = MathHelper.func_76128_c(func_70902_q().field_70165_t) - 2;
/* 193 */         int j = MathHelper.func_76128_c(func_70902_q().field_70161_v) - 2;
/* 194 */         int k = MathHelper.func_76128_c(func_70902_q().field_70121_D.field_72338_b);
/* 195 */         for (int l = 0; l <= 4; l++) {
/* 196 */           for (int i1 = 0; i1 <= 4; i1++)
/*     */           {
/* 198 */             if (((l < 1) || (i1 < 1) || (l > 3) || (i1 > 3)) && ((this.field_70170_p.func_147445_c(i + l, k - 1, j + i1, false)) || (this.field_70170_p.func_147439_a(i + l, k - 1, j + i1).func_149688_o() == Material.field_151586_h)) && (!this.field_70170_p.func_147445_c(i + l, k, j + i1, false)) && (!this.field_70170_p.func_147445_c(i + l, k + 1, j + i1, false)))
/*     */             {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 204 */               this.field_70170_p.func_72908_a(i + l + 0.5F, k, j + i1 + 0.5F, "mob.endermen.portal", 0.5F, 1.0F);
/* 205 */               func_70012_b(i + l + 0.5F, k, j + i1 + 0.5F, this.field_70177_z, this.field_70125_A);
/*     */               
/* 207 */               func_70624_b(null);
/* 208 */               return;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 213 */       if ((func_70638_az() != null) && (func_70638_az().field_70128_L)) {
/* 214 */         func_70624_b(null);
/* 215 */         setAnger(5);
/*     */       }
/*     */       
/* 218 */       if ((!getStay()) && (getUpgrade() == 2) && (getAnger() == 0) && (func_70638_az() == null) && (func_70902_q() != null) && (getOwnerEntity().func_70643_av() != null) && (!getOwnerEntity().func_70643_av().field_70128_L))
/*     */       {
/*     */ 
/* 221 */         if (((getOwnerEntity().func_70643_av() instanceof EntityLivingBase)) && (func_70685_l(getOwnerEntity().func_70643_av()))) {
/* 222 */           setAnger(600);
/* 223 */           func_70624_b(getOwnerEntity().func_70643_av());
/*     */         }
/*     */       }
/*     */       
/* 227 */       boolean move = false;
/* 228 */       if ((getAnger() > 0) && (func_70638_az() != null) && (!func_70638_az().field_70128_L) && (func_70638_az() != getOwnerEntity())) {
/* 229 */         func_70625_a(func_70638_az(), 10.0F, 20.0F);
/* 230 */         move = true;
/* 231 */         if ((this.field_70724_aR <= 0) && (func_70032_d(func_70638_az()) < 1.5D) && (func_70638_az().field_70121_D.field_72337_e > this.field_70121_D.field_72338_b) && (func_70638_az().field_70121_D.field_72338_b < this.field_70121_D.field_72337_e))
/*     */         {
/*     */ 
/* 234 */           this.field_70724_aR = (10 + this.field_70170_p.field_73012_v.nextInt(5));
/* 235 */           func_70638_az().func_70097_a(DamageSource.func_76358_a(this), 4.0F);
/* 236 */           this.field_70170_p.func_72960_a(this, (byte)17);
/* 237 */           this.field_70170_p.func_72956_a(this, "mob.blaze.hit", 0.5F, this.field_70170_p.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 242 */       if ((func_70902_q() != null) && (func_70032_d(func_70902_q()) > 5.0F) && (getAnger() == 0) && (!getStay()))
/*     */       {
/* 244 */         func_70625_a(func_70902_q(), 10.0F, 20.0F);
/* 245 */         move = true;
/*     */       }
/*     */       
/* 248 */       if (((this.field_70122_E) || (this.field_70171_ac)) && (this.jumpDelay-- <= 0) && (move))
/*     */       {
/* 250 */         boolean fast = getUpgrade() == 0;
/* 251 */         this.jumpDelay = (this.field_70146_Z.nextInt(10) + 5);
/* 252 */         this.jumpDelay /= 3;
/*     */         
/* 254 */         this.field_70703_bu = true;
/*     */         
/* 256 */         this.field_768_a = 1.0F;
/* 257 */         this.field_70702_br = (1.0F - this.field_70146_Z.nextFloat() * 2.0F);
/* 258 */         this.field_70701_bs = (fast ? 8.0F : 6.0F);
/* 259 */         if (this.field_70171_ac) this.field_70701_bs *= 0.75F;
/* 260 */         this.field_70747_aH = (fast ? 0.04F : 0.03F);
/* 261 */         this.field_70170_p.func_72956_a(this, "random.chestclosed", 0.1F, this.field_70170_p.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*     */       }
/*     */       else {
/* 264 */         this.field_70703_bu = false;
/* 265 */         if (this.field_70122_E)
/*     */         {
/* 267 */           this.field_70702_br = (this.field_70701_bs = 0.0F);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected boolean func_70692_ba()
/*     */   {
/* 277 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70100_b_(EntityPlayer entityplayer) {}
/*     */   
/*     */ 
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 289 */     return net.minecraft.init.Blocks.field_150364_r.field_149762_H.func_150498_e();
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 295 */     return "random.break";
/*     */   }
/*     */   
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 301 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected float func_70599_aP()
/*     */   {
/* 308 */     return 0.5F;
/*     */   }
/*     */   
/*     */   public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData)
/*     */   {
/* 313 */     setInvSize();
/* 314 */     return super.func_110161_a(par1EntityLivingData);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 319 */   public int slotCount = 27;
/* 320 */   public InventoryTrunk inventory = new InventoryTrunk(this, this.slotCount);
/*     */   public float lidrot;
/*     */   public float field_768_a;
/*     */   
/* 324 */   public void setInvSize() { setRows(getUpgrade() == 1 ? 4 : 3);
/* 325 */     this.slotCount = (getRows() * 9);
/*     */   }
/*     */   
/*     */   public boolean func_70085_c(EntityPlayer player)
/*     */   {
/* 330 */     if (player.func_70093_af()) return false;
/* 331 */     ItemStack itemstack = player.field_71071_by.func_70448_g();
/*     */     
/* 333 */     if ((itemstack != null) && (itemstack.func_77973_b() == ConfigItems.itemGolemBell)) {
/* 334 */       if ((getUpgrade() == 3) && (!func_152113_b().equals(player.func_70005_c_()))) return true;
/* 335 */       return false;
/*     */     }
/* 337 */     if ((getUpgrade() == -1) && (itemstack != null) && (itemstack.func_77973_b() == ConfigItems.itemGolemUpgrade)) {
/* 338 */       setUpgrade(itemstack.func_77960_j());
/* 339 */       setInvSize();
/* 340 */       itemstack.field_77994_a -= 1;
/* 341 */       if (itemstack.field_77994_a <= 0)
/*     */       {
/* 343 */         player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, (ItemStack)null);
/*     */       }
/* 345 */       this.field_70170_p.func_72956_a(this, "thaumcraft:upgrade", 0.5F, 1.0F);
/* 346 */       player.func_71038_i();
/* 347 */       return true;
/*     */     }
/* 349 */     if ((itemstack != null) && ((itemstack.func_77973_b() instanceof ItemFood)) && (func_110143_aJ() < func_110138_aP()))
/*     */     {
/*     */ 
/* 352 */       ItemFood itemfood = (ItemFood)itemstack.func_77973_b();
/* 353 */       itemstack.field_77994_a -= 1;
/* 354 */       func_70691_i(itemfood.func_150905_g(itemstack));
/* 355 */       if (func_110143_aJ() == func_110138_aP()) {
/* 356 */         this.field_70170_p.func_72956_a(this, "random.burp", 0.5F, this.field_70170_p.field_73012_v.nextFloat() * 0.5F + 0.5F);
/*     */       } else
/* 358 */         this.field_70170_p.func_72956_a(this, "random.eat", 0.5F, this.field_70170_p.field_73012_v.nextFloat() * 0.5F + 0.5F);
/* 359 */       this.field_70170_p.func_72960_a(this, (byte)18);
/* 360 */       this.lidrot = 0.15F;
/* 361 */       if (itemstack.field_77994_a <= 0)
/*     */       {
/* 363 */         player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/*     */       }
/* 365 */       return true;
/*     */     }
/* 367 */     if (!this.field_70170_p.field_72995_K) {
/* 368 */       if ((getUpgrade() == 3) && (!func_152113_b().equals(player.func_70005_c_()))) return true;
/* 369 */       player.openGui(Thaumcraft.instance, 2, this.field_70170_p, func_145782_y(), 0, 0);
/* 370 */       return false;
/*     */     }
/*     */     
/* 373 */     return true;
/*     */   }
/*     */   
/*     */   void showHeartsOrSmokeFX(boolean flag)
/*     */   {
/* 378 */     String s = "heart";
/* 379 */     int amount = 1;
/* 380 */     if (!flag)
/*     */     {
/* 382 */       s = "explode";
/* 383 */       amount = 7;
/*     */     }
/* 385 */     for (int i = 0; i < amount; i++)
/*     */     {
/* 387 */       double d = this.field_70146_Z.nextGaussian() * 0.02D;
/* 388 */       double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 389 */       double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 390 */       this.field_70170_p.func_72869_a(s, this.field_70165_t + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N, this.field_70163_u + 0.5D + this.field_70146_Z.nextFloat() * this.field_70131_O, this.field_70161_v + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N, d, d1, d2);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void pullItems()
/*     */   {
/* 397 */     if ((this.field_70128_L) || (func_110143_aJ() <= 0.0F)) return;
/* 398 */     List list = null;
/* 399 */     if (!this.field_70170_p.field_72995_K) {
/* 400 */       list = this.field_70170_p.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a(this.field_70165_t - 0.5D, this.field_70163_u - 0.5D, this.field_70161_v - 0.5D, this.field_70165_t + 0.5D, this.field_70163_u + 0.5D, this.field_70161_v + 0.5D));
/*     */       
/* 402 */       for (int a = 0; a < list.size(); a++)
/*     */       {
/* 404 */         Entity entity = (Entity)list.get(a);
/* 405 */         if ((entity instanceof EntityItem)) {
/* 406 */           ItemStack stack = ((EntityItem)entity).func_92059_d().func_77946_l();
/*     */           
/* 408 */           ItemStack outstack = thaumcraft.common.lib.utils.InventoryUtils.placeItemStackIntoInventory(stack, this.inventory, 0, true);
/* 409 */           if ((outstack == null) || (outstack.field_77994_a != stack.field_77994_a)) {
/* 410 */             this.field_70170_p.func_72956_a(this, "random.eat", 0.5F, this.field_70170_p.field_73012_v.nextFloat() * 0.5F + 0.5F);
/* 411 */             this.field_70170_p.func_72960_a(this, (byte)17);
/* 412 */             if ((outstack != null) && (outstack.field_77994_a >= 0)) {
/* 413 */               ((EntityItem)entity).func_92058_a(outstack);
/*     */             } else {
/* 415 */               entity.func_70106_y();
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 421 */     list = this.field_70170_p.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a(this.field_70165_t - 3.0D, this.field_70163_u - 3.0D, this.field_70161_v - 3.0D, this.field_70165_t + 3.0D, this.field_70163_u + 3.0D, this.field_70161_v + 3.0D));
/*     */     
/* 423 */     for (int a = 0; a < list.size(); a++)
/*     */     {
/* 425 */       Entity entity = (Entity)list.get(a);
/* 426 */       if ((entity instanceof EntityItem))
/*     */       {
/*     */ 
/*     */ 
/* 430 */         double d6 = entity.field_70165_t - this.field_70165_t;
/* 431 */         double d8 = entity.field_70163_u - this.field_70163_u + this.field_70131_O * 0.8F;
/* 432 */         double d10 = entity.field_70161_v - this.field_70161_v;
/* 433 */         double d11 = MathHelper.func_76133_a(d6 * d6 + d8 * d8 + d10 * d10);
/* 434 */         d6 /= d11;
/* 435 */         d8 /= d11;
/* 436 */         d10 /= d11;
/* 437 */         double d13 = 0.075D;
/* 438 */         entity.field_70159_w -= d6 * d13;
/* 439 */         entity.field_70181_x -= d8 * d13;
/* 440 */         entity.field_70179_y -= d10 * d13;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70645_a(DamageSource par1DamageSource)
/*     */   {
/* 448 */     if (!this.field_70170_p.field_72995_K) this.inventory.dropAllItems();
/* 449 */     super.func_70645_a(par1DamageSource);
/*     */   }
/*     */   
/*     */   public boolean func_70648_aU()
/*     */   {
/* 454 */     return true;
/*     */   }
/*     */   
/*     */   public int getUpgrade() {
/* 458 */     return this.field_70180_af.func_75683_a(18);
/*     */   }
/*     */   
/*     */   public void setUpgrade(int upgrade) {
/* 462 */     this.field_70180_af.func_75692_b(18, Byte.valueOf((byte)upgrade));
/*     */   }
/*     */   
/*     */   public int getRows() {
/* 466 */     return this.field_70180_af.func_75683_a(19);
/*     */   }
/*     */   
/*     */   public void setRows(int rows) {
/* 470 */     this.field_70180_af.func_75692_b(19, Byte.valueOf((byte)rows));
/*     */   }
/*     */   
/*     */   public int getAnger() {
/* 474 */     return this.field_70180_af.func_75693_b(20);
/*     */   }
/*     */   
/*     */   public void setAnger(int anger) {
/* 478 */     this.field_70180_af.func_75692_b(20, Short.valueOf((short)anger));
/*     */   }
/*     */   
/*     */   public boolean isOpen()
/*     */   {
/* 483 */     return this.field_70180_af.func_75683_a(15) == 1;
/*     */   }
/*     */   
/*     */   public void setOpen(boolean par1)
/*     */   {
/* 488 */     this.field_70180_af.func_75692_b(15, Byte.valueOf((byte)(par1 ? 1 : 0)));
/*     */   }
/*     */   
/*     */   public boolean getStay()
/*     */   {
/* 493 */     return this.field_70180_af.func_75683_a(16) == 1;
/*     */   }
/*     */   
/*     */   public void setStay(boolean par1)
/*     */   {
/* 498 */     this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(par1 ? 1 : 0)));
/*     */   }
/*     */   
/*     */ 
/*     */   public float field_767_b;
/*     */   
/*     */   private int jumpDelay;
/* 505 */   private int eatDelay = 0;
/*     */   
/*     */ 
/*     */ 
/*     */   public String func_152113_b()
/*     */   {
/* 511 */     return this.field_70180_af.func_75681_e(17);
/*     */   }
/*     */   
/*     */   public void setOwner(String par1Str)
/*     */   {
/* 516 */     this.field_70180_af.func_75692_b(17, par1Str);
/*     */   }
/*     */   
/*     */   public Entity func_70902_q()
/*     */   {
/* 521 */     return getOwnerEntity();
/*     */   }
/*     */   
/*     */   public EntityLivingBase getOwnerEntity()
/*     */   {
/* 526 */     return this.field_70170_p.func_72924_a(func_152113_b());
/*     */   }
/*     */   
/*     */ 
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public void func_70103_a(byte par1)
/*     */   {
/* 533 */     if (par1 == 17)
/*     */     {
/* 535 */       this.lidrot = 0.15F;
/*     */ 
/*     */     }
/* 538 */     else if (par1 == 18)
/*     */     {
/* 540 */       this.lidrot = 0.15F;
/* 541 */       showHeartsOrSmokeFX(true);
/*     */     }
/*     */     else
/*     */     {
/* 545 */       super.func_70103_a(par1);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_71027_c(int par1)
/*     */   {
/* 551 */     if ((getStay()) || (this.field_70128_L) || (this.field_71093_bK == par1)) { return;
/*     */     }
/* 553 */     if (func_70902_q() == null) {
/*     */       try {
/* 555 */         MinecraftServer minecraftserver = MinecraftServer.func_71276_C();
/* 556 */         WorldServer worldserver1 = minecraftserver.func_71218_a(par1);
/* 557 */         if (worldserver1 == null) return;
/* 558 */         Entity target = worldserver1.func_72924_a(func_152113_b());
/* 559 */         if (target == null) return;
/* 560 */         this.field_70170_p.func_72900_e(this);
/* 561 */         this.field_70128_L = false;
/* 562 */         if (func_70089_S())
/*     */         {
/* 564 */           this.field_70170_p.func_72866_a(this, false);
/*     */         }
/* 566 */         Entity entity = EntityList.func_75620_a(EntityList.func_75621_b(this), worldserver1);
/*     */         
/* 568 */         if (entity != null)
/*     */         {
/* 570 */           entity.func_82141_a(this, true);
/* 571 */           entity.func_70012_b(target.field_70165_t, target.field_70163_u + 0.25D, target.field_70161_v, entity.field_70177_z, entity.field_70125_A);
/* 572 */           entity.field_71093_bK = par1;
/* 573 */           worldserver1.func_72838_d(entity);
/*     */         }
/* 575 */         this.field_71093_bK = par1;
/* 576 */         this.field_70128_L = true;
/*     */       } catch (Exception e) {
/* 578 */         Thaumcraft.log.error("Error while teleporting traveling trunk to dimension " + par1);
/* 579 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 582 */     super.func_71027_c(par1);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/golems/EntityTravelingTrunk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */