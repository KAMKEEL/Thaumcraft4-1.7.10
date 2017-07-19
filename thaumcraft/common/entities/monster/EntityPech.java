/*     */ package thaumcraft.common.entities.monster;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentData;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIArrowAttack;
/*     */ import net.minecraft.entity.ai.EntityAIAvoidEntity;
/*     */ import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*     */ import net.minecraft.entity.ai.EntityAIOpenDoor;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemEnchantedBook;
/*     */ import net.minecraft.item.ItemNameTag;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.entities.ai.combat.AIAttackOnCollide;
/*     */ import thaumcraft.common.entities.ai.pech.AIPechItemEntityGoto;
/*     */ import thaumcraft.common.entities.ai.pech.AIPechTradePlayer;
/*     */ import thaumcraft.common.entities.projectile.EntityPechBlast;
/*     */ import thaumcraft.common.items.ItemManaBean;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ 
/*     */ public class EntityPech extends EntityMob implements IRangedAttackMob
/*     */ {
/*     */   public String func_70005_c_()
/*     */   {
/*  67 */     if (func_94056_bM())
/*     */     {
/*  69 */       return func_94057_bL();
/*     */     }
/*  71 */     switch (getPechType()) {
/*  72 */     case 0:  return StatCollector.func_74838_a("entity.Thaumcraft.Pech.name");
/*  73 */     case 1:  return StatCollector.func_74838_a("entity.Thaumcraft.Pech.1.name");
/*  74 */     case 2:  return StatCollector.func_74838_a("entity.Thaumcraft.Pech.2.name");
/*     */     }
/*  76 */     return StatCollector.func_74838_a("entity.Thaumcraft.Pech.name");
/*     */   }
/*     */   
/*     */ 
/*  80 */   public ItemStack[] loot = new ItemStack[9];
/*     */   
/*  82 */   public boolean trading = false;
/*  83 */   public boolean updateAINextTick = false;
/*     */   
/*  85 */   private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 0.6D, 20, 50, 15.0F);
/*  86 */   private EntityAIArrowAttack aiBlastAttack = new EntityAIArrowAttack(this, 0.6D, 20, 30, 15.0F);
/*  87 */   private AIAttackOnCollide aiMeleeAttack = new AIAttackOnCollide(this, EntityLivingBase.class, 0.6D, false);
/*  88 */   private EntityAIAvoidEntity aiAvoidPlayer = new EntityAIAvoidEntity(this, EntityPlayer.class, 8.0F, 0.5D, 0.6D);
/*     */   
/*     */   public EntityPech(World world)
/*     */   {
/*  92 */     super(world);
/*  93 */     func_70105_a(0.6F, 1.8F);
/*  94 */     func_70661_as().func_75498_b(false);
/*  95 */     func_70661_as().func_75491_a(true);
/*     */     
/*  97 */     this.field_70714_bg.func_75776_a(0, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  98 */     this.field_70714_bg.func_75776_a(1, new AIPechTradePlayer(this));
/*     */     
/* 100 */     this.field_70714_bg.func_75776_a(3, new AIPechItemEntityGoto(this));
/*     */     
/* 102 */     this.field_70714_bg.func_75776_a(5, new EntityAIOpenDoor(this, true));
/*     */     
/* 104 */     this.field_70714_bg.func_75776_a(6, new EntityAIMoveTowardsRestriction(this, 0.5D));
/* 105 */     this.field_70714_bg.func_75776_a(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
/*     */     
/* 107 */     this.field_70714_bg.func_75776_a(9, new EntityAIWander(this, 0.6D));
/* 108 */     this.field_70714_bg.func_75776_a(9, new net.minecraft.entity.ai.EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
/* 109 */     this.field_70714_bg.func_75776_a(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
/* 110 */     this.field_70714_bg.func_75776_a(11, new net.minecraft.entity.ai.EntityAILookIdle(this));
/*     */     
/* 112 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
/*     */     
/* 114 */     if ((world != null) && (!world.field_72995_K))
/*     */     {
/* 116 */       setCombatTask();
/*     */     }
/*     */     
/* 119 */     this.field_82174_bp[0] = 0.2F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70062_b(int par1, ItemStack par2ItemStack)
/*     */   {
/* 128 */     super.func_70062_b(par1, par2ItemStack);
/*     */     
/* 130 */     if ((!this.field_70170_p.field_72995_K) && (par1 == 0))
/*     */     {
/* 132 */       this.updateAINextTick = true;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_82164_bB()
/*     */   {
/* 139 */     super.func_82164_bB();
/* 140 */     switch (this.field_70146_Z.nextInt(20)) {
/*     */     case 0: case 12: 
/* 142 */       ItemStack wand = new ItemStack(ConfigItems.itemWandCasting);
/* 143 */       ItemStack focus = new ItemStack(ConfigItems.itemFocusPech);
/* 144 */       ((ItemWandCasting)wand.func_77973_b()).setFocus(wand, focus);
/* 145 */       ((ItemWandCasting)wand.func_77973_b()).addVis(wand, Aspect.EARTH, 2 + this.field_70146_Z.nextInt(6), true);
/* 146 */       ((ItemWandCasting)wand.func_77973_b()).addVis(wand, Aspect.ENTROPY, 2 + this.field_70146_Z.nextInt(6), true);
/* 147 */       ((ItemWandCasting)wand.func_77973_b()).addVis(wand, Aspect.WATER, 2 + this.field_70146_Z.nextInt(6), true);
/* 148 */       ((ItemWandCasting)wand.func_77973_b()).addVis(wand, Aspect.AIR, this.field_70146_Z.nextInt(4), true);
/* 149 */       ((ItemWandCasting)wand.func_77973_b()).addVis(wand, Aspect.FIRE, this.field_70146_Z.nextInt(4), true);
/* 150 */       ((ItemWandCasting)wand.func_77973_b()).addVis(wand, Aspect.ORDER, this.field_70146_Z.nextInt(4), true);
/* 151 */       func_70062_b(0, wand);
/* 152 */       break;
/*     */     case 1: 
/* 154 */       func_70062_b(0, new ItemStack(Items.field_151052_q)); break;
/*     */     case 3: 
/* 156 */       func_70062_b(0, new ItemStack(Items.field_151049_t)); break;
/*     */     case 5: 
/* 158 */       func_70062_b(0, new ItemStack(Items.field_151040_l)); break;
/*     */     case 6: 
/* 160 */       func_70062_b(0, new ItemStack(Items.field_151036_c)); break;
/*     */     case 7: 
/* 162 */       func_70062_b(0, new ItemStack(Items.field_151112_aM)); break;
/*     */     case 8: 
/* 164 */       func_70062_b(0, new ItemStack(Items.field_151050_s)); break;
/*     */     case 9: 
/* 166 */       func_70062_b(0, new ItemStack(Items.field_151035_b)); break;
/*     */     case 2: case 4: case 10: case 11: case 13: 
/* 168 */       func_70062_b(0, new ItemStack(Items.field_151031_f));
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */   public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData)
/*     */   {
/* 175 */     par1EntityLivingData = super.func_110161_a(par1EntityLivingData);
/*     */     
/* 177 */     func_82164_bB();
/*     */     
/* 179 */     ItemStack itemstack = func_70694_bm();
/* 180 */     if ((itemstack != null) && (itemstack.func_77973_b() == ConfigItems.itemWandCasting))
/*     */     {
/* 182 */       setPechType(1);
/* 183 */       this.field_82174_bp[0] = 0.1F;
/*     */     }
/* 185 */     else if (itemstack != null)
/*     */     {
/* 187 */       if (itemstack.func_77973_b() == Items.field_151031_f)
/* 188 */         setPechType(2);
/* 189 */       func_82162_bC();
/*     */     }
/*     */     
/* 192 */     func_98053_h(this.field_70146_Z.nextFloat() < 0.75F * this.field_70170_p.func_147462_b(this.field_70165_t, this.field_70163_u, this.field_70161_v));
/*     */     
/* 194 */     return super.func_110161_a(par1EntityLivingData);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_70601_bi()
/*     */   {
/* 201 */     BiomeGenBase biome = this.field_70170_p.func_72807_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70161_v));
/* 202 */     boolean magicBiome = false;
/* 203 */     if (biome != null) {
/* 204 */       magicBiome = (BiomeDictionary.isBiomeOfType(biome, net.minecraftforge.common.BiomeDictionary.Type.MAGICAL)) && (biome.field_76756_M != Config.biomeTaintID);
/*     */     }
/* 206 */     int count = 0;
/*     */     try {
/* 208 */       List l = this.field_70170_p.func_72872_a(EntityPech.class, this.field_70121_D.func_72314_b(16.0D, 16.0D, 16.0D));
/* 209 */       if (l != null) count = l.size();
/*     */     }
/*     */     catch (Exception e) {}
/* 212 */     if ((this.field_70170_p.field_73011_w.field_76574_g != 0) && (biome.field_76756_M != Config.biomeMagicalForestID) && (biome.field_76756_M != Config.biomeEerieID))
/*     */     {
/* 214 */       magicBiome = false;
/*     */     }
/* 216 */     return (count < 4) && (magicBiome) && (super.func_70601_bi());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public float func_70047_e()
/*     */   {
/* 223 */     return this.field_70131_O * 0.66F;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70636_d()
/*     */   {
/* 229 */     super.func_70636_d();
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/* 234 */     super.func_70088_a();
/* 235 */     this.field_70180_af.func_75682_a(13, new Byte((byte)0));
/* 236 */     this.field_70180_af.func_75682_a(14, new Short((short)0));
/* 237 */     this.field_70180_af.func_75682_a(16, new Byte((byte)0));
/*     */   }
/*     */   
/*     */   public int getPechType()
/*     */   {
/* 242 */     return this.field_70180_af.func_75683_a(13);
/*     */   }
/*     */   
/*     */   public int getAnger()
/*     */   {
/* 247 */     return this.field_70180_af.func_75693_b(14);
/*     */   }
/*     */   
/*     */   public boolean isTamed()
/*     */   {
/* 252 */     return this.field_70180_af.func_75683_a(16) == 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPechType(int par1)
/*     */   {
/* 260 */     this.field_70180_af.func_75692_b(13, Byte.valueOf((byte)par1));
/*     */   }
/*     */   
/*     */   public void setAnger(int par1)
/*     */   {
/* 265 */     this.field_70180_af.func_75692_b(14, Short.valueOf((short)par1));
/*     */   }
/*     */   
/*     */   public void setTamed(boolean par1)
/*     */   {
/* 270 */     this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(par1 ? 1 : 0)));
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70650_aV()
/*     */   {
/* 276 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/* 282 */     super.func_110147_ax();
/* 283 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D);
/* 284 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(6.0D);
/* 285 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.5D);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 291 */     super.func_70014_b(par1NBTTagCompound);
/*     */     
/* 293 */     par1NBTTagCompound.func_74774_a("PechType", (byte)getPechType());
/*     */     
/* 295 */     par1NBTTagCompound.func_74777_a("Anger", (short)getAnger());
/*     */     
/* 297 */     par1NBTTagCompound.func_74757_a("Tamed", isTamed());
/*     */     
/*     */ 
/* 300 */     NBTTagList nbttaglist = new NBTTagList();
/*     */     
/* 302 */     for (int i = 0; i < this.loot.length; i++)
/*     */     {
/* 304 */       NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*     */       
/* 306 */       if (this.loot[i] != null)
/*     */       {
/* 308 */         this.loot[i].func_77955_b(nbttagcompound1);
/*     */       }
/*     */       
/* 311 */       nbttaglist.func_74742_a(nbttagcompound1);
/*     */     }
/* 313 */     par1NBTTagCompound.func_74782_a("Loot", nbttaglist);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 319 */     super.func_70037_a(par1NBTTagCompound);
/*     */     
/* 321 */     if (par1NBTTagCompound.func_74764_b("PechType"))
/*     */     {
/* 323 */       byte b0 = par1NBTTagCompound.func_74771_c("PechType");
/* 324 */       setPechType(b0);
/*     */     }
/*     */     
/* 327 */     setAnger(par1NBTTagCompound.func_74765_d("Anger"));
/*     */     
/* 329 */     setTamed(par1NBTTagCompound.func_74767_n("Tamed"));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 334 */     if (par1NBTTagCompound.func_74764_b("Loot"))
/*     */     {
/* 336 */       NBTTagList nbttaglist = par1NBTTagCompound.func_150295_c("Loot", 10);
/* 337 */       for (int i = 0; i < this.loot.length; i++)
/*     */       {
/* 339 */         this.loot[i] = ItemStack.func_77949_a(nbttaglist.func_150305_b(i));
/*     */       }
/*     */     }
/*     */     
/* 343 */     this.updateAINextTick = true;
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean func_70692_ba()
/*     */   {
/*     */     try
/*     */     {
/* 351 */       if (this.loot == null) return true;
/* 352 */       int q = 0;
/* 353 */       for (ItemStack is : this.loot) {
/* 354 */         if ((is != null) && (is.field_77994_a > 0)) q++;
/*     */       }
/* 356 */       return q < 5; } catch (Exception e) {}
/* 357 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_110164_bC()
/*     */   {
/* 363 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70628_a(boolean flag, int i)
/*     */   {
/* 372 */     for (int a = 0; a < this.loot.length; a++) {
/* 373 */       if ((this.loot[a] != null) && (this.field_70170_p.field_73012_v.nextFloat() < 0.88F)) {
/* 374 */         func_70099_a(this.loot[a].func_77946_l(), 1.5F);
/*     */       }
/*     */     }
/*     */     
/* 378 */     Aspect[] aspects = (Aspect[])Aspect.getPrimalAspects().toArray(new Aspect[0]);
/* 379 */     for (int a = 0; a < 1 + i; a++) {
/* 380 */       if (this.field_70146_Z.nextBoolean()) {
/* 381 */         ItemStack is = new ItemStack(ConfigItems.itemManaBean);
/* 382 */         ((ItemManaBean)is.func_77973_b()).setAspects(is, new AspectList().add(aspects[this.field_70146_Z.nextInt(aspects.length)], 1));
/* 383 */         func_70099_a(is, 1.5F);
/*     */       }
/*     */     }
/* 386 */     if (this.field_70170_p.field_73012_v.nextInt(10) < 1 + i) { func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 18), 1.5F);
/*     */     }
/* 388 */     super.func_70628_a(flag, i);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70600_l(int par1)
/*     */   {
/* 394 */     func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 9), 1.5F);
/*     */   }
/*     */   
/*     */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public void func_70103_a(byte par1)
/*     */   {
/* 400 */     if (par1 == 16)
/*     */     {
/* 402 */       this.mumble = 3.1415927F;
/*     */ 
/*     */     }
/* 405 */     else if (par1 == 17)
/*     */     {
/* 407 */       this.mumble = 6.2831855F;
/*     */ 
/*     */     }
/* 410 */     else if (par1 == 18)
/*     */     {
/* 412 */       for (int i = 0; i < 5; i++)
/*     */       {
/* 414 */         double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 415 */         double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 416 */         double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 417 */         this.field_70170_p.func_72869_a("happyVillager", this.field_70165_t + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N, this.field_70163_u + 0.5D + this.field_70146_Z.nextFloat() * this.field_70131_O, this.field_70161_v + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N, d0, d1, d2);
/*     */       }
/*     */     }
/* 420 */     if (par1 == 19)
/*     */     {
/* 422 */       for (int i = 0; i < 5; i++)
/*     */       {
/* 424 */         double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 425 */         double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 426 */         double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 427 */         this.field_70170_p.func_72869_a("angryVillager", this.field_70165_t + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N, this.field_70163_u + 0.5D + this.field_70146_Z.nextFloat() * this.field_70131_O, this.field_70161_v + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N, d0, d1, d2);
/*     */       }
/* 429 */       this.mumble = 6.2831855F;
/*     */     }
/*     */     else
/*     */     {
/* 433 */       super.func_70103_a(par1);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 439 */   public float mumble = 0.0F;
/*     */   
/*     */   public void func_70642_aH()
/*     */   {
/* 443 */     if (!this.field_70170_p.field_72995_K) {
/* 444 */       if (this.field_70146_Z.nextInt(3) == 0) {
/* 445 */         List list = this.field_70170_p.func_72839_b(this, this.field_70121_D.func_72314_b(4.0D, 2.0D, 4.0D));
/*     */         
/* 447 */         for (int i = 0; i < list.size(); i++)
/*     */         {
/* 449 */           Entity entity1 = (Entity)list.get(i);
/*     */           
/* 451 */           if ((entity1 instanceof EntityPech))
/*     */           {
/* 453 */             this.field_70170_p.func_72960_a(this, (byte)17);
/* 454 */             func_85030_a("thaumcraft:pech_trade", func_70599_aP(), func_70647_i());
/* 455 */             return;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 460 */       this.field_70170_p.func_72960_a(this, (byte)16);
/*     */     }
/* 462 */     super.func_70642_aH();
/*     */   }
/*     */   
/*     */   public int func_70627_aG()
/*     */   {
/* 467 */     return 120;
/*     */   }
/*     */   
/*     */ 
/*     */   protected float func_70599_aP()
/*     */   {
/* 473 */     return 0.4F;
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70639_aQ()
/*     */   {
/* 479 */     return "thaumcraft:pech_idle";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 485 */     return "thaumcraft:pech_hit";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70673_aS()
/*     */   {
/* 491 */     return "thaumcraft:pech_death";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Entity func_70782_k()
/*     */   {
/* 499 */     return getAnger() == 0 ? null : super.func_70782_k();
/*     */   }
/*     */   
/*     */   public void setCombatTask()
/*     */   {
/* 504 */     this.field_70714_bg.func_85156_a(this.aiMeleeAttack);
/* 505 */     this.field_70714_bg.func_85156_a(this.aiArrowAttack);
/* 506 */     this.field_70714_bg.func_85156_a(this.aiBlastAttack);
/* 507 */     ItemStack itemstack = func_70694_bm();
/*     */     
/* 509 */     if ((itemstack != null) && (itemstack.func_77973_b() == Items.field_151031_f))
/*     */     {
/* 511 */       this.field_70714_bg.func_75776_a(2, this.aiArrowAttack);
/*     */ 
/*     */     }
/* 514 */     else if ((itemstack != null) && (itemstack.func_77973_b() == ConfigItems.itemWandCasting))
/*     */     {
/* 516 */       this.field_70714_bg.func_75776_a(2, this.aiBlastAttack);
/*     */     }
/*     */     else
/*     */     {
/* 520 */       this.field_70714_bg.func_75776_a(2, this.aiMeleeAttack);
/*     */     }
/*     */     
/* 523 */     if (isTamed()) {
/* 524 */       this.field_70714_bg.func_85156_a(this.aiAvoidPlayer);
/*     */     } else {
/* 526 */       this.field_70714_bg.func_75776_a(4, this.aiAvoidPlayer);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_82196_d(EntityLivingBase entitylivingbase, float f)
/*     */   {
/* 533 */     if (getPechType() == 2) {
/* 534 */       EntityArrow entityarrow = new EntityArrow(this.field_70170_p, this, entitylivingbase, 1.6F, 14 - this.field_70170_p.field_73013_u.func_151525_a() * 4);
/* 535 */       int i = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, func_70694_bm());
/* 536 */       int j = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, func_70694_bm());
/* 537 */       entityarrow.func_70239_b(f * 2.0F + this.field_70146_Z.nextGaussian() * 0.25D + this.field_70170_p.field_73013_u.func_151525_a() * 0.11F);
/*     */       
/* 539 */       if (i > 0)
/*     */       {
/* 541 */         entityarrow.func_70239_b(entityarrow.func_70242_d() + i * 0.5D + 0.5D);
/*     */       }
/*     */       
/* 544 */       if (j > 0)
/*     */       {
/* 546 */         entityarrow.func_70240_a(j);
/*     */       }
/*     */       
/* 549 */       func_85030_a("random.bow", 1.0F, 1.0F / (func_70681_au().nextFloat() * 0.4F + 0.8F));
/* 550 */       this.field_70170_p.func_72838_d(entityarrow);
/*     */     }
/* 552 */     else if (getPechType() == 1) {
/* 553 */       EntityPechBlast blast = new EntityPechBlast(this.field_70170_p, this, 1, 0, this.field_70146_Z.nextFloat() < 0.1F);
/* 554 */       double d0 = entitylivingbase.field_70165_t + entitylivingbase.field_70159_w - this.field_70165_t;
/* 555 */       double d1 = entitylivingbase.field_70163_u + entitylivingbase.func_70047_e() - 1.500000023841858D - this.field_70163_u;
/* 556 */       double d2 = entitylivingbase.field_70161_v + entitylivingbase.field_70179_y - this.field_70161_v;
/* 557 */       float f1 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
/* 558 */       blast.func_70186_c(d0, d1 + f1 * 0.1F, d2, 1.5F, 4.0F);
/* 559 */       func_85030_a("thaumcraft:ice", 0.4F, 1.0F + this.field_70146_Z.nextFloat() * 0.1F);
/* 560 */       this.field_70170_p.func_72838_d(blast);
/*     */     }
/*     */     
/* 563 */     func_71038_i();
/*     */   }
/*     */   
/*     */   private void becomeAngryAt(Entity par1Entity)
/*     */   {
/* 568 */     this.field_70789_a = par1Entity;
/* 569 */     if (getAnger() <= 0) {
/* 570 */       this.field_70170_p.func_72960_a(this, (byte)19);
/* 571 */       func_85030_a("thaumcraft:pech_charge", func_70599_aP(), func_70647_i());
/*     */     }
/* 573 */     func_70624_b((EntityLivingBase)par1Entity);
/* 574 */     setAnger(400 + this.field_70146_Z.nextInt(400));
/* 575 */     setTamed(false);
/*     */     
/* 577 */     this.updateAINextTick = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_70658_aO()
/*     */   {
/* 584 */     int i = super.func_70658_aO() + 2;
/*     */     
/* 586 */     if (i > 20)
/*     */     {
/* 588 */       i = 20;
/*     */     }
/*     */     
/* 591 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70097_a(DamageSource damSource, float par2)
/*     */   {
/* 597 */     if (func_85032_ar())
/*     */     {
/* 599 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 603 */     Entity entity = damSource.func_76346_g();
/*     */     
/* 605 */     if ((entity instanceof EntityPlayer))
/*     */     {
/* 607 */       List list = this.field_70170_p.func_72839_b(this, this.field_70121_D.func_72314_b(32.0D, 16.0D, 32.0D));
/*     */       
/* 609 */       for (int i = 0; i < list.size(); i++)
/*     */       {
/* 611 */         Entity entity1 = (Entity)list.get(i);
/*     */         
/* 613 */         if ((entity1 instanceof EntityPech))
/*     */         {
/* 615 */           EntityPech entitypech = (EntityPech)entity1;
/* 616 */           entitypech.becomeAngryAt(entity);
/*     */         }
/*     */       }
/*     */       
/* 620 */       becomeAngryAt(entity);
/*     */     }
/*     */     
/* 623 */     return super.func_70097_a(damSource, par2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 629 */   int chargecount = 0;
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 633 */     if (this.mumble > 0.0F) this.mumble *= 0.75F;
/* 634 */     if (getAnger() > 0) { setAnger(getAnger() - 1);
/*     */     }
/* 636 */     if ((getAnger() > 0) && ((this.field_70789_a == null) || (func_70638_az() == null))) {
/* 637 */       func_70782_k();
/* 638 */       func_70624_b((EntityLivingBase)this.field_70789_a);
/* 639 */       if (this.field_70789_a != null) {
/* 640 */         if (this.chargecount > 0) this.chargecount -= 1;
/* 641 */         if (this.chargecount == 0) {
/* 642 */           this.chargecount = 100;
/* 643 */           func_85030_a("thaumcraft:pech_charge", func_70599_aP(), func_70647_i());
/*     */         }
/* 645 */         this.field_70170_p.func_72960_a(this, (byte)17);
/*     */       }
/*     */     }
/*     */     
/* 649 */     if ((this.field_70170_p.field_72995_K) && (this.field_70146_Z.nextInt(15) == 0) && (getAnger() > 0)) {
/* 650 */       double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 651 */       double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 652 */       double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 653 */       this.field_70170_p.func_72869_a("angryVillager", this.field_70165_t + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N, this.field_70163_u + 0.5D + this.field_70146_Z.nextFloat() * this.field_70131_O, this.field_70161_v + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N, d0, d1, d2);
/*     */     }
/*     */     
/* 656 */     if ((this.field_70170_p.field_72995_K) && (this.field_70146_Z.nextInt(25) == 0) && (isTamed())) {
/* 657 */       double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 658 */       double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 659 */       double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 660 */       this.field_70170_p.func_72869_a("happyVillager", this.field_70165_t + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N, this.field_70163_u + 0.5D + this.field_70146_Z.nextFloat() * this.field_70131_O, this.field_70161_v + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N, d0, d1, d2);
/*     */     }
/*     */     
/* 663 */     super.func_70071_h_();
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70619_bc()
/*     */   {
/* 669 */     if (this.updateAINextTick) {
/* 670 */       this.updateAINextTick = false;
/* 671 */       setCombatTask();
/*     */     }
/*     */     
/* 674 */     super.func_70619_bc();
/* 675 */     if (this.field_70173_aa % 40 == 0)
/*     */     {
/* 677 */       func_70691_i(1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canPickup(ItemStack entityItem)
/*     */   {
/* 684 */     if (entityItem == null) return false;
/* 685 */     if ((!isTamed()) && (valuedItems.containsKey(Integer.valueOf(Item.func_150891_b(entityItem.func_77973_b()))))) {
/* 686 */       return true;
/*     */     }
/* 688 */     for (int a = 0; a < this.loot.length; a++) {
/* 689 */       if ((this.loot[a] != null) && (this.loot[a].field_77994_a <= 0)) this.loot[a] = null;
/* 690 */       if (this.loot[a] == null) return true;
/* 691 */       if ((InventoryUtils.areItemStacksEqualStrict(entityItem, this.loot[a])) && (entityItem.field_77994_a + this.loot[a].field_77994_a <= this.loot[a].func_77976_d()))
/* 692 */         return true;
/*     */     }
/* 694 */     return false;
/*     */   }
/*     */   
/*     */   public ItemStack pickupItem(ItemStack entityItem) {
/* 698 */     if (entityItem == null) { return entityItem;
/*     */     }
/* 700 */     if ((!isTamed()) && (isValued(entityItem)))
/*     */     {
/* 702 */       if (this.field_70146_Z.nextInt(10) < getValue(entityItem)) {
/* 703 */         setTamed(true);
/* 704 */         this.updateAINextTick = true;
/* 705 */         this.field_70170_p.func_72960_a(this, (byte)18);
/*     */       }
/*     */       
/* 708 */       entityItem.field_77994_a -= 1;
/*     */       
/* 710 */       if (entityItem.field_77994_a <= 0) {
/* 711 */         return null;
/*     */       }
/* 713 */       return entityItem;
/*     */     }
/*     */     
/*     */ 
/* 717 */     for (int a = 0; a < this.loot.length; a++) {
/* 718 */       if ((this.loot[a] != null) && (this.loot[a].field_77994_a <= 0)) this.loot[a] = null;
/* 719 */       if ((entityItem != null) && (entityItem.field_77994_a > 0) && (this.loot[a] != null) && (this.loot[a].field_77994_a < this.loot[a].func_77976_d()) && (InventoryUtils.areItemStacksEqualStrict(entityItem, this.loot[a])))
/*     */       {
/*     */ 
/*     */ 
/* 723 */         if (entityItem.field_77994_a + this.loot[a].field_77994_a <= this.loot[a].func_77976_d()) {
/* 724 */           this.loot[a].field_77994_a += entityItem.field_77994_a;
/* 725 */           return null;
/*     */         }
/* 727 */         int sz = Math.min(entityItem.field_77994_a, this.loot[a].func_77976_d() - this.loot[a].field_77994_a);
/* 728 */         this.loot[a].field_77994_a += sz;
/* 729 */         entityItem.field_77994_a -= sz;
/*     */       }
/*     */       
/* 732 */       if ((entityItem != null) && (entityItem.field_77994_a <= 0)) { entityItem = null;
/*     */       }
/*     */     }
/*     */     
/* 736 */     for (int a = 0; a < this.loot.length; a++) {
/* 737 */       if ((this.loot[a] != null) && (this.loot[a].field_77994_a <= 0)) this.loot[a] = null;
/* 738 */       if ((entityItem != null) && (entityItem.field_77994_a > 0) && (this.loot[a] == null)) {
/* 739 */         this.loot[a] = entityItem.func_77946_l();
/* 740 */         return null;
/*     */       }
/*     */     }
/* 743 */     if ((entityItem != null) && (entityItem.field_77994_a <= 0)) entityItem = null;
/* 744 */     return entityItem;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70085_c(EntityPlayer player)
/*     */   {
/* 752 */     if ((player.func_70093_af()) || ((player.func_70694_bm() != null) && ((player.func_70694_bm().func_77973_b() instanceof ItemNameTag))))
/* 753 */       return false;
/* 754 */     if ((!this.field_70170_p.field_72995_K) && (isTamed())) {
/* 755 */       player.openGui(Thaumcraft.instance, 1, this.field_70170_p, func_145782_y(), 0, 0);
/* 756 */       return true;
/*     */     }
/*     */     
/* 759 */     return super.func_70085_c(player);
/*     */   }
/*     */   
/*     */   public boolean isValued(ItemStack item) {
/* 763 */     if (item == null) return false;
/* 764 */     boolean value = valuedItems.containsKey(Integer.valueOf(Item.func_150891_b(item.func_77973_b())));
/* 765 */     if (!value) {
/* 766 */       AspectList al = ThaumcraftCraftingManager.getObjectTags(item);
/* 767 */       al = ThaumcraftCraftingManager.getBonusTags(item, al);
/* 768 */       if (al.getAmount(Aspect.GREED) > 0)
/* 769 */         value = true;
/*     */     }
/* 771 */     return value;
/*     */   }
/*     */   
/*     */   public int getValue(ItemStack item) {
/* 775 */     if (item == null) return 0;
/* 776 */     int value = valuedItems.containsKey(Integer.valueOf(Item.func_150891_b(item.func_77973_b()))) ? ((Integer)valuedItems.get(Integer.valueOf(Item.func_150891_b(item.func_77973_b())))).intValue() : 0;
/* 777 */     if (value == 0) {
/* 778 */       AspectList al = ThaumcraftCraftingManager.getObjectTags(item);
/* 779 */       al = ThaumcraftCraftingManager.getBonusTags(item, al);
/* 780 */       value = Math.min(32, al.getAmount(Aspect.GREED));
/*     */     }
/* 782 */     return value;
/*     */   }
/*     */   
/* 785 */   static HashMap<Integer, Integer> valuedItems = new HashMap();
/* 786 */   public static HashMap<Integer, ArrayList<List>> tradeInventory = new HashMap();
/*     */   
/* 788 */   static { valuedItems.put(Integer.valueOf(Item.func_150891_b(ConfigItems.itemManaBean)), Integer.valueOf(1));
/* 789 */     valuedItems.put(Integer.valueOf(Item.func_150891_b(Items.field_151043_k)), Integer.valueOf(2));
/* 790 */     valuedItems.put(Integer.valueOf(Item.func_150891_b(Items.field_151153_ao)), Integer.valueOf(2));
/* 791 */     valuedItems.put(Integer.valueOf(Item.func_150891_b(Items.field_151079_bi)), Integer.valueOf(3));
/* 792 */     valuedItems.put(Integer.valueOf(Item.func_150891_b(Items.field_151045_i)), Integer.valueOf(4));
/* 793 */     valuedItems.put(Integer.valueOf(Item.func_150891_b(Items.field_151166_bC)), Integer.valueOf(5));
/*     */     
/*     */ 
/*     */ 
/* 797 */     ArrayList<List> forInv = new ArrayList();
/* 798 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ConfigItems.itemManaBean) }));
/* 799 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ConfigItems.itemNugget, 1, 16) }));
/* 800 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ConfigItems.itemNugget, 1, 31) }));
/* 801 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ConfigItems.itemNugget, 1, 21) }));
/* 802 */     if (Config.foundCopperIngot)
/* 803 */       forInv.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ConfigItems.itemNugget, 1, 17) }));
/* 804 */     if (Config.foundTinIngot)
/* 805 */       forInv.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ConfigItems.itemNugget, 1, 18) }));
/* 806 */     if (Config.foundSilverIngot)
/* 807 */       forInv.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ConfigItems.itemNugget, 1, 19) }));
/* 808 */     if (Config.foundLeadIngot)
/* 809 */       forInv.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ConfigItems.itemNugget, 1, 20) }));
/* 810 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(2), new ItemStack(Items.field_151072_bj) }));
/* 811 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(2), new ItemStack(ConfigBlocks.blockCustomPlant, 1, 0) }));
/* 812 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(2), new ItemStack(Items.field_151068_bn, 1, 8201) }));
/* 813 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(2), new ItemStack(Items.field_151068_bn, 1, 8194) }));
/* 814 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151062_by) }));
/* 815 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(ConfigItems.itemResource, 1, 9) }));
/* 816 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151153_ao, 1, 0) }));
/* 817 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151068_bn, 1, 8265) }));
/* 818 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151068_bn, 1, 8262) }));
/* 819 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(5), new ItemStack(Items.field_151153_ao, 1, 1) }));
/* 820 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(4), new ItemStack(ConfigItems.itemPickThaumium) }));
/* 821 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(5), new ItemStack(ConfigBlocks.blockCustomPlant, 1, 1) }));
/* 822 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(5), new ItemStack(ConfigBlocks.blockCustomPlant, 1, 1) }));
/* 823 */     tradeInventory.put(Integer.valueOf(0), forInv);
/*     */     
/*     */ 
/* 826 */     ArrayList<List> forMag = new ArrayList();
/* 827 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ConfigItems.itemManaBean) }));
/* 828 */     for (int a = 0; a < 6; a++)
/* 829 */       forMag.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ConfigItems.itemShard, 1, a) }));
/* 830 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ConfigItems.itemResource, 1, 9) }));
/* 831 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(2), new ItemStack(ConfigItems.itemResource, 1, 9) }));
/* 832 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(2), new ItemStack(Items.field_151068_bn, 1, 8193) }));
/* 833 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(2), new ItemStack(Items.field_151068_bn, 1, 8261) }));
/* 834 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(3), Items.field_151134_bR.func_92111_a(new EnchantmentData(Config.enchHaste, 1)) }));
/* 835 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151153_ao, 1, 0) }));
/* 836 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151068_bn, 1, 8225) }));
/* 837 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151068_bn, 1, 8229) }));
/* 838 */     for (int a = 0; a < 7; a++)
/* 839 */       forMag.add(Arrays.asList(new Object[] { Integer.valueOf(4), new ItemStack(ConfigBlocks.blockCrystal, 1, a) }));
/* 840 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(5), new ItemStack(Items.field_151153_ao, 1, 1) }));
/* 841 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(5), Items.field_151134_bR.func_92111_a(new EnchantmentData(Config.enchRepair, 1)) }));
/* 842 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(5), new ItemStack(ConfigItems.itemFocusPouch) }));
/* 843 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(5), new ItemStack(ConfigItems.itemFocusPech) }));
/* 844 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(5), new ItemStack(ConfigItems.itemAmuletVis, 1, 0) }));
/* 845 */     tradeInventory.put(Integer.valueOf(1), forMag);
/*     */     
/*     */ 
/* 848 */     ArrayList<List> forArc = new ArrayList();
/* 849 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ConfigItems.itemManaBean) }));
/* 850 */     for (int a = 0; a < 15; a++)
/* 851 */       forArc.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ConfigBlocks.blockCandle, 1, a) }));
/* 852 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(2), new ItemStack(Items.field_151073_bk) }));
/* 853 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(2), new ItemStack(Items.field_151068_bn, 1, 8194) }));
/* 854 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(2), new ItemStack(Items.field_151068_bn, 1, 8201) }));
/* 855 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(2), Items.field_151134_bR.func_92111_a(new EnchantmentData(Enchantment.field_77345_t, 1)) }));
/* 856 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151062_by) }));
/* 857 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(ConfigItems.itemResource, 1, 9) }));
/* 858 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151068_bn, 1, 8270) }));
/* 859 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151068_bn, 1, 8225) }));
/* 860 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151153_ao, 1, 0) }));
/* 861 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(5), new ItemStack(Items.field_151153_ao, 1, 1) }));
/* 862 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(4), new ItemStack(ConfigItems.itemBootsThaumium) }));
/* 863 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(5), new ItemStack(ConfigItems.itemRingRunic, 1, 0) }));
/* 864 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(5), Items.field_151134_bR.func_92111_a(new EnchantmentData(Enchantment.field_77343_v, 1)) }));
/* 865 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(5), Items.field_151134_bR.func_92111_a(new EnchantmentData(Enchantment.field_77342_w, 1)) }));
/* 866 */     tradeInventory.put(Integer.valueOf(2), forArc);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/EntityPech.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */