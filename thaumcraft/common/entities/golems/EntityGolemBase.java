/*      */ package thaumcraft.common.entities.golems;
/*      */ 
/*      */ import cpw.mods.fml.common.FMLCommonHandler;
/*      */ import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
/*      */ import cpw.mods.fml.relauncher.Side;
/*      */ import cpw.mods.fml.relauncher.SideOnly;
/*      */ import io.netty.buffer.ByteBuf;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.enchantment.EnchantmentHelper;
/*      */ import net.minecraft.entity.DataWatcher;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.SharedMonsterAttributes;
/*      */ import net.minecraft.entity.ai.EntityAILookIdle;
/*      */ import net.minecraft.entity.ai.EntityAITasks;
/*      */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*      */ import net.minecraft.entity.ai.attributes.BaseAttributeMap;
/*      */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*      */ import net.minecraft.entity.monster.EntityCreeper;
/*      */ import net.minecraft.entity.monster.EntityGolem;
/*      */ import net.minecraft.entity.monster.EntityMob;
/*      */ import net.minecraft.entity.monster.IMob;
/*      */ import net.minecraft.entity.passive.EntityAnimal;
/*      */ import net.minecraft.entity.passive.EntityBat;
/*      */ import net.minecraft.entity.passive.EntityTameable;
/*      */ import net.minecraft.entity.passive.EntityVillager;
/*      */ import net.minecraft.entity.passive.IAnimals;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.player.InventoryPlayer;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.pathfinding.PathNavigate;
/*      */ import net.minecraft.potion.Potion;
/*      */ import net.minecraft.potion.PotionEffect;
/*      */ import net.minecraft.util.ChunkCoordinates;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.StatCollector;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldProvider;
/*      */ import net.minecraftforge.fluids.FluidStack;
/*      */ import org.apache.logging.log4j.Level;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ import thaumcraft.api.aspects.Aspect;
/*      */ import thaumcraft.api.aspects.AspectList;
/*      */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*      */ import thaumcraft.common.Thaumcraft;
/*      */ import thaumcraft.common.config.ConfigBlocks;
/*      */ import thaumcraft.common.config.ConfigItems;
/*      */ import thaumcraft.common.entities.InventoryMob;
/*      */ import thaumcraft.common.entities.ai.combat.AIAvoidCreeperSwell;
/*      */ import thaumcraft.common.entities.ai.combat.AIDartAttack;
/*      */ import thaumcraft.common.entities.ai.combat.AIGolemAttackOnCollide;
/*      */ import thaumcraft.common.entities.ai.combat.AIHurtByTarget;
/*      */ import thaumcraft.common.entities.ai.combat.AINearestAttackableTarget;
/*      */ import thaumcraft.common.entities.ai.combat.AINearestButcherTarget;
/*      */ import thaumcraft.common.entities.ai.fluid.AIEssentiaGather;
/*      */ import thaumcraft.common.entities.ai.fluid.AILiquidEmpty;
/*      */ import thaumcraft.common.entities.ai.fluid.AILiquidGather;
/*      */ import thaumcraft.common.entities.ai.fluid.AILiquidGoto;
/*      */ import thaumcraft.common.entities.ai.interact.AIFish;
/*      */ import thaumcraft.common.entities.ai.interact.AIHarvestCrops;
/*      */ import thaumcraft.common.entities.ai.interact.AIHarvestLogs;
/*      */ import thaumcraft.common.entities.ai.inventory.AIEmptyDrop;
/*      */ import thaumcraft.common.entities.ai.inventory.AIEmptyGoto;
/*      */ import thaumcraft.common.entities.ai.inventory.AIEmptyPlace;
/*      */ import thaumcraft.common.entities.ai.inventory.AIFillGoto;
/*      */ import thaumcraft.common.entities.ai.inventory.AIFillTake;
/*      */ import thaumcraft.common.entities.ai.inventory.AIHomeDrop;
/*      */ import thaumcraft.common.entities.ai.inventory.AIHomePlace;
/*      */ import thaumcraft.common.entities.ai.inventory.AIHomeReplace;
/*      */ import thaumcraft.common.entities.ai.inventory.AIHomeTake;
/*      */ import thaumcraft.common.entities.ai.inventory.AIItemPickup;
/*      */ import thaumcraft.common.entities.ai.inventory.AISortingPlace;
/*      */ import thaumcraft.common.entities.ai.misc.AIOpenDoor;
/*      */ import thaumcraft.common.entities.ai.misc.AIReturnHome;
/*      */ import thaumcraft.common.entities.projectile.EntityDart;
/*      */ import thaumcraft.common.items.wands.ItemWandCasting;
/*      */ import thaumcraft.common.lib.utils.EntityUtils;
/*      */ import thaumcraft.common.lib.utils.InventoryUtils;
/*      */ import thaumcraft.common.lib.utils.Utils;
/*      */ 
/*      */ public class EntityGolemBase extends EntityGolem implements IEntityAdditionalSpawnData
/*      */ {
/*   92 */   public InventoryMob inventory = new InventoryMob(this, 1);
/*      */   public ItemStack itemCarried;
/*      */   public FluidStack fluidCarried;
/*   95 */   public ItemStack itemWatched = null;
/*      */   
/*      */   public Aspect essentia;
/*      */   public int essentiaAmount;
/*   99 */   public boolean advanced = false;
/*  100 */   public int homeFacing = 0;
/*  101 */   public boolean paused = false;
/*  102 */   public boolean inactive = false;
/*  103 */   public boolean flag = false;
/*      */   
/*  105 */   public byte[] colors = null;
/*  106 */   public byte[] upgrades = null;
/*  107 */   public String decoration = "";
/*  108 */   public float bootup = -1.0F;
/*      */   
/*  110 */   public EnumGolemType golemType = EnumGolemType.WOOD;
/*      */   
/*      */   public EntityGolemBase(World par1World)
/*      */   {
/*  114 */     super(par1World);
/*  115 */     this.field_70180_af.func_75682_a(30, Byte.valueOf((byte)(int)func_110138_aP()));
/*  116 */     this.field_70138_W = 1.0F;
/*  117 */     this.colors = new byte[] { -1 };
/*  118 */     this.upgrades = new byte[] { -1 };
/*  119 */     func_70105_a(0.4F, 0.95F);
/*      */     
/*      */ 
/*  122 */     func_70661_as().func_75498_b(true);
/*  123 */     func_70661_as().func_75490_c(true);
/*  124 */     func_70661_as().func_75495_e(true);
/*  125 */     func_110163_bv();
/*      */   }
/*      */   
/*      */ 
/*      */   protected void func_110147_ax()
/*      */   {
/*  131 */     super.func_110147_ax();
/*  132 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
/*  133 */     func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
/*  134 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0D);
/*  135 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.6D);
/*  136 */     func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(32.0D);
/*      */   }
/*      */   
/*      */ 
/*      */   public EntityGolemBase(World par0World, EnumGolemType type, boolean adv)
/*      */   {
/*  142 */     this(par0World);
/*  143 */     this.golemType = type;
/*  144 */     this.advanced = adv;
/*  145 */     this.upgrades = new byte[this.golemType.upgrades + (this.advanced ? 1 : 0)];
/*  146 */     for (int a = 0; a < this.upgrades.length; a++) this.upgrades[a] = -1;
/*      */   }
/*      */   
/*      */   public boolean setupGolemInventory() {
/*  150 */     ItemStack core = null;
/*  151 */     if (!ItemGolemCore.hasInventory(getCore())) { return false;
/*      */     }
/*  153 */     if (getCore() > -1) {
/*  154 */       int invSize = 0;
/*  155 */       switch (getCore()) {
/*      */       default: 
/*  157 */         invSize = 6;
/*  158 */         invSize += getUpgradeAmount(2) * 6;
/*  159 */         break;
/*      */       case 5: 
/*  161 */         invSize = 1;
/*  162 */         invSize += getUpgradeAmount(2);
/*  163 */         break;
/*      */       }
/*      */       
/*      */       
/*  167 */       InventoryMob inventory2 = new InventoryMob(this, invSize);
/*  168 */       for (int a = 0; a < this.inventory.inventory.length; a++) {
/*  169 */         inventory2.inventory[a] = this.inventory.inventory[a];
/*      */       }
/*  171 */       this.inventory = inventory2;
/*      */     }
/*      */     
/*  174 */     byte[] oldcolors = this.colors;
/*  175 */     this.colors = new byte[this.inventory.slotCount];
/*  176 */     for (int a = 0; a < this.inventory.slotCount; a++) {
/*  177 */       this.colors[a] = -1;
/*  178 */       if (a < oldcolors.length) { this.colors[a] = oldcolors[a];
/*      */       }
/*      */     }
/*  181 */     return true;
/*      */   }
/*      */   
/*      */   public boolean setupGolem()
/*      */   {
/*  186 */     if (!this.field_70170_p.field_72995_K) { this.field_70180_af.func_75692_b(19, Byte.valueOf((byte)this.golemType.ordinal()));
/*      */     }
/*  188 */     if ((getGolemType() == EnumGolemType.STONE) || (getGolemType() == EnumGolemType.IRON) || (getGolemType() == EnumGolemType.THAUMIUM))
/*      */     {
/*      */ 
/*  191 */       func_70661_as().func_75491_a(false);
/*      */     } else {
/*  193 */       func_70661_as().func_75491_a(true);
/*      */     }
/*      */     
/*  196 */     if (getGolemType().fireResist) {
/*  197 */       this.field_70178_ae = true;
/*      */     }
/*      */     
/*  200 */     int bonus = 0;
/*  201 */     try { bonus = getGolemDecoration().contains("H") ? 5 : 0; } catch (Exception e) {}
/*  202 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(getGolemType().health + bonus);
/*      */     
/*  204 */     int damage = 2 + getGolemStrength() + getUpgradeAmount(1);
/*  205 */     try { if (getGolemDecoration().contains("M")) damage += 2; } catch (Exception e) {}
/*  206 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(damage);
/*      */     
/*      */ 
/*  209 */     this.field_70714_bg.field_75782_a.clear();
/*      */     
/*      */ 
/*      */ 
/*  213 */     if (getCore() > -1) {
/*  214 */       this.field_70714_bg.func_75776_a(0, new AIAvoidCreeperSwell(this));
/*      */     }
/*      */     
/*      */ 
/*  218 */     switch (getCore()) {
/*      */     case 0: 
/*  220 */       this.field_70714_bg.func_75776_a(0, new AIHomeReplace(this));
/*  221 */       this.field_70714_bg.func_75776_a(1, new AIHomePlace(this));
/*  222 */       this.field_70714_bg.func_75776_a(2, new AIHomeDrop(this));
/*  223 */       this.field_70714_bg.func_75776_a(3, new AIFillTake(this));
/*  224 */       this.field_70714_bg.func_75776_a(4, new AIFillGoto(this));
/*  225 */       break;
/*      */     case 1: 
/*  227 */       this.field_70714_bg.func_75776_a(0, new AIHomeReplace(this));
/*  228 */       this.field_70714_bg.func_75776_a(1, new AIEmptyPlace(this));
/*  229 */       this.field_70714_bg.func_75776_a(2, new AIEmptyDrop(this));
/*  230 */       this.field_70714_bg.func_75776_a(3, new AIEmptyGoto(this));
/*  231 */       this.field_70714_bg.func_75776_a(4, new AIHomeTake(this));
/*  232 */       break;
/*      */     case 2: 
/*  234 */       this.field_70714_bg.func_75776_a(0, new AIHomeReplace(this));
/*  235 */       this.field_70714_bg.func_75776_a(1, new AIHomePlace(this));
/*  236 */       this.field_70714_bg.func_75776_a(2, new AIItemPickup(this));
/*  237 */       break;
/*      */     case 3: 
/*  239 */       this.field_70714_bg.func_75776_a(2, new AIHarvestCrops(this));
/*  240 */       break;
/*      */     case 4: 
/*  242 */       if (this.decoration.contains("R")) this.field_70714_bg.func_75776_a(2, new AIDartAttack(this));
/*  243 */       this.field_70714_bg.func_75776_a(3, new AIGolemAttackOnCollide(this));
/*  244 */       this.field_70715_bh.func_75776_a(1, new AIHurtByTarget(this, false));
/*  245 */       this.field_70715_bh.func_75776_a(2, new AINearestAttackableTarget(this, 0, true));
/*  246 */       break;
/*      */     case 5: 
/*  248 */       this.field_70714_bg.func_75776_a(1, new AILiquidEmpty(this));
/*  249 */       this.field_70714_bg.func_75776_a(2, new AILiquidGather(this));
/*  250 */       this.field_70714_bg.func_75776_a(3, new AILiquidGoto(this));
/*  251 */       break;
/*      */     case 6: 
/*  253 */       this.field_70714_bg.func_75776_a(1, new thaumcraft.common.entities.ai.fluid.AIEssentiaEmpty(this));
/*  254 */       this.field_70714_bg.func_75776_a(2, new AIEssentiaGather(this));
/*  255 */       this.field_70714_bg.func_75776_a(3, new thaumcraft.common.entities.ai.fluid.AIEssentiaGoto(this));
/*  256 */       break;
/*      */     case 7: 
/*  258 */       this.field_70714_bg.func_75776_a(2, new AIHarvestLogs(this));
/*  259 */       break;
/*      */     case 8: 
/*  261 */       this.field_70714_bg.func_75776_a(0, new AIHomeReplace(this));
/*  262 */       this.field_70714_bg.func_75776_a(0, new thaumcraft.common.entities.ai.interact.AIUseItem(this));
/*  263 */       this.field_70714_bg.func_75776_a(4, new AIHomeTake(this));
/*  264 */       break;
/*      */     case 9: 
/*  266 */       if (this.decoration.contains("R")) this.field_70714_bg.func_75776_a(2, new AIDartAttack(this));
/*  267 */       this.field_70714_bg.func_75776_a(3, new AIGolemAttackOnCollide(this));
/*  268 */       this.field_70715_bh.func_75776_a(1, new AINearestButcherTarget(this, 0, true));
/*  269 */       break;
/*      */     case 10: 
/*  271 */       this.field_70714_bg.func_75776_a(0, new AIHomeReplace(this));
/*  272 */       this.field_70714_bg.func_75776_a(1, new AISortingPlace(this));
/*  273 */       this.field_70714_bg.func_75776_a(3, new thaumcraft.common.entities.ai.inventory.AISortingGoto(this));
/*  274 */       this.field_70714_bg.func_75776_a(4, new thaumcraft.common.entities.ai.inventory.AIHomeTakeSorting(this));
/*  275 */       break;
/*      */     case 11: 
/*  277 */       this.field_70714_bg.func_75776_a(2, new AIFish(this));
/*      */     }
/*      */     
/*      */     
/*  281 */     if (getCore() > -1) {
/*  282 */       this.field_70714_bg.func_75776_a(5, new AIOpenDoor(this, true));
/*  283 */       this.field_70714_bg.func_75776_a(6, new AIReturnHome(this));
/*  284 */       this.field_70714_bg.func_75776_a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
/*  285 */       this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
/*      */     }
/*      */     
/*  288 */     return true;
/*      */   }
/*      */   
/*      */   public int getCarryLimit() {
/*  292 */     int base = this.golemType.carry;
/*  293 */     if (this.field_70170_p.field_72995_K) base = getGolemType().carry;
/*  294 */     base += Math.min(16, Math.max(4, base)) * getUpgradeAmount(1);
/*  295 */     return base;
/*      */   }
/*      */   
/*      */   public int getFluidCarryLimit() {
/*  299 */     return MathHelper.func_76128_c(Math.sqrt(getCarryLimit())) * 1000;
/*      */   }
/*      */   
/*      */   public float func_70689_ay()
/*      */   {
/*  304 */     if ((this.paused) || (this.inactive)) return 0.0F;
/*  305 */     float speed = this.golemType.speed * (this.decoration.contains("B") ? 1.1F : 1.0F);
/*  306 */     if (this.decoration.contains("P")) speed *= 0.88F;
/*  307 */     speed *= (1.0F + getUpgradeAmount(0) * 0.15F);
/*  308 */     if (this.advanced) { speed *= 1.1F;
/*      */     }
/*  310 */     if ((func_70090_H()) && ((getGolemType() == EnumGolemType.STONE) || (getGolemType() == EnumGolemType.IRON) || (getGolemType() == EnumGolemType.THAUMIUM)))
/*      */     {
/*      */ 
/*  313 */       speed *= 2.0F;
/*      */     }
/*      */     
/*  316 */     return speed;
/*      */   }
/*      */   
/*      */   public void setup(int side)
/*      */   {
/*  321 */     this.homeFacing = side;
/*      */     
/*  323 */     setupGolem();
/*  324 */     setupGolemInventory();
/*      */   }
/*      */   
/*      */ 
/*      */   protected void func_70088_a()
/*      */   {
/*  330 */     super.func_70088_a();
/*  331 */     func_70096_w().func_82709_a(16, 5);
/*  332 */     this.field_70180_af.func_75682_a(17, "");
/*  333 */     this.field_70180_af.func_75682_a(18, Byte.valueOf((byte)0));
/*  334 */     this.field_70180_af.func_75682_a(19, Byte.valueOf((byte)0));
/*  335 */     this.field_70180_af.func_75682_a(20, String.valueOf(""));
/*  336 */     this.field_70180_af.func_75682_a(21, Byte.valueOf((byte)-1));
/*  337 */     this.field_70180_af.func_75682_a(22, String.valueOf(""));
/*  338 */     this.field_70180_af.func_75682_a(23, String.valueOf(""));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean func_70650_aV()
/*      */   {
/*  348 */     return (!this.paused) && (!this.inactive);
/*      */   }
/*      */   
/*      */ 
/*      */   public void func_70636_d()
/*      */   {
/*  354 */     super.func_70636_d();
/*  355 */     if (this.action > 0) this.action -= 1;
/*  356 */     if (this.leftArm > 0) this.leftArm -= 1;
/*  357 */     if (this.rightArm > 0) this.rightArm -= 1;
/*  358 */     if (this.healing > 0) { this.healing -= 1;
/*      */     }
/*  360 */     int xx = MathHelper.func_76128_c(this.field_70165_t);
/*  361 */     int yy = MathHelper.func_76128_c(this.field_70163_u);
/*  362 */     int zz = MathHelper.func_76128_c(this.field_70161_v);
/*  363 */     if ((yy > 0) && (this.field_70170_p.func_147439_a(xx, yy - 1, zz) == ConfigBlocks.blockCosmeticSolid) && (this.field_70170_p.func_72805_g(xx, yy - 1, zz) == 10))
/*      */     {
/*  365 */       this.inactive = true;
/*      */     } else {
/*  367 */       this.inactive = false;
/*      */     }
/*      */     
/*  370 */     if (!this.field_70170_p.field_72995_K)
/*      */     {
/*  372 */       if (this.regenTimer > 0) {
/*  373 */         this.regenTimer -= 1;
/*      */       } else {
/*  375 */         this.regenTimer = this.golemType.regenDelay;
/*  376 */         if (this.decoration.contains("F")) this.regenTimer = ((int)(this.regenTimer * 0.66F));
/*  377 */         if ((!this.field_70170_p.field_72995_K) && (func_110143_aJ() < func_110138_aP())) {
/*  378 */           this.field_70170_p.func_72960_a(this, (byte)5);
/*  379 */           func_70691_i(1.0F);
/*      */         }
/*      */       }
/*      */       
/*  383 */       if ((func_70092_e(func_110172_bL().field_71574_a, func_110172_bL().field_71572_b, func_110172_bL().field_71573_c) >= 2304.0D) || (func_70094_T()))
/*      */       {
/*      */ 
/*  386 */         int var1 = MathHelper.func_76128_c(func_110172_bL().field_71574_a);
/*  387 */         int var2 = MathHelper.func_76128_c(func_110172_bL().field_71573_c);
/*  388 */         int var3 = MathHelper.func_76128_c(func_110172_bL().field_71572_b);
/*  389 */         for (int var0 = 1; var0 >= -1; var0--)
/*      */         {
/*  391 */           for (int var4 = -1; var4 <= 1; var4++)
/*      */           {
/*  393 */             for (int var5 = -1; var5 <= 1; var5++)
/*      */             {
/*  395 */               if ((World.func_147466_a(this.field_70170_p, var1 + var4, var3 - 1 + var0, var2 + var5)) && (!this.field_70170_p.func_147445_c(var1 + var4, var3 + var0, var2 + var5, false)))
/*      */               {
/*      */ 
/*  398 */                 func_70012_b(var1 + var4 + 0.5F, var3 + var0, var2 + var5 + 0.5F, this.field_70177_z, this.field_70125_A);
/*      */                 
/*  400 */                 func_70661_as().func_75499_g();
/*  401 */                 return;
/*      */               }
/*      */               
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  409 */     else if ((this.bootup > 0.0F) && (getCore() > -1)) {
/*  410 */       this.bootup *= this.bootup / 33.1F;
/*  411 */       this.field_70170_p.func_72980_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, "thaumcraft:cameraticks", this.bootup * 0.2F, 1.0F * this.bootup, false);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*  416 */   public int regenTimer = 0;
/*      */   
/*      */   public float getRange() {
/*  419 */     float dmod = 16 + getUpgradeAmount(3) * 4;
/*  420 */     if (this.decoration.contains("G")) dmod += Math.max(dmod * 0.1F, 1.0F);
/*  421 */     if (this.advanced) dmod += Math.max(dmod * 0.2F, 2.0F);
/*  422 */     return dmod;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean func_110176_b(int par1, int par2, int par3)
/*      */   {
/*  430 */     float dmod = getRange();
/*  431 */     return func_110172_bL().func_71569_e(par1, par2, par3) < dmod * dmod;
/*      */   }
/*      */   
/*      */ 
/*      */   protected void func_70626_be()
/*      */   {
/*  437 */     this.field_70708_bq += 1;
/*  438 */     func_70623_bb();
/*      */     
/*  440 */     boolean vara = func_70090_H();
/*  441 */     boolean varb = func_70058_J();
/*      */     
/*  443 */     if ((vara) || (varb))
/*      */     {
/*  445 */       this.field_70703_bu = (this.field_70146_Z.nextFloat() < 0.8F);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void func_70645_a(DamageSource ds)
/*      */   {
/*  454 */     if (!this.field_70170_p.field_72995_K) {
/*  455 */       FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[Thaumcraft] " + this + " was killed by " + ds.func_76364_f() + " (" + ds.func_76355_l() + ")");
/*      */     }
/*      */     
/*  458 */     super.func_70645_a(ds);
/*      */   }
/*      */   
/*      */   public void func_70015_d(int par1)
/*      */   {
/*  463 */     if (!this.golemType.fireResist) {
/*  464 */       super.func_70015_d(par1);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   protected boolean func_70692_ba()
/*      */   {
/*  471 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void func_70623_bb() {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int func_70682_h(int par1)
/*      */   {
/*  484 */     return par1;
/*      */   }
/*      */   
/*      */ 
/*      */   public short getColors(int slot)
/*      */   {
/*  490 */     char[] chars = this.field_70180_af.func_75681_e(22).toCharArray();
/*  491 */     if (slot < chars.length) {
/*  492 */       if (("" + chars[slot]).equals("h")) {
/*  493 */         return -1;
/*      */       }
/*  495 */       return Short.parseShort("" + chars[slot], 16);
/*      */     }
/*  497 */     return -1;
/*      */   }
/*      */   
/*      */   public void setColors(int slot, int color)
/*      */   {
/*  502 */     this.colors[slot] = ((byte)color);
/*  503 */     String s = "";
/*  504 */     for (byte c : this.colors) {
/*  505 */       if (c == -1) {
/*  506 */         s = s + "h";
/*      */       } else
/*  508 */         s = s + Integer.toHexString(c);
/*      */     }
/*  510 */     this.field_70180_af.func_75692_b(22, String.valueOf(s));
/*      */   }
/*      */   
/*      */   public byte getUpgrade(int slot)
/*      */   {
/*  515 */     char[] chars = this.field_70180_af.func_75681_e(23).toCharArray();
/*  516 */     if (slot < chars.length) {
/*  517 */       byte t = Byte.parseByte("" + chars[slot], 16);
/*  518 */       if (t == 15) return -1;
/*  519 */       return t;
/*      */     }
/*  521 */     return -1;
/*      */   }
/*      */   
/*      */   public int getUpgradeAmount(int type)
/*      */   {
/*  526 */     int a = 0;
/*  527 */     for (byte b : this.upgrades) {
/*  528 */       if (type == b) a++;
/*      */     }
/*  530 */     return a;
/*      */   }
/*      */   
/*      */   public void setUpgrade(int slot, byte upgrade)
/*      */   {
/*  535 */     this.upgrades[slot] = upgrade;
/*  536 */     String s = "";
/*  537 */     for (byte c : this.upgrades) s = s + Integer.toHexString(c);
/*  538 */     this.field_70180_af.func_75692_b(23, String.valueOf(s));
/*      */   }
/*      */   
/*      */   public ArrayList<Byte> getColorsMatching(ItemStack match) {
/*  542 */     ArrayList<Byte> l = new ArrayList();
/*      */     
/*  544 */     if ((this.inventory.inventory != null) && (this.inventory.inventory.length > 0)) {
/*  545 */       boolean allNull = true;
/*  546 */       for (int a = 0; a < this.inventory.inventory.length; a++) {
/*  547 */         if (this.inventory.func_70301_a(a) != null) allNull = false;
/*  548 */         if (InventoryUtils.areItemStacksEqual(this.inventory.func_70301_a(a), match, checkOreDict(), ignoreDamage(), ignoreNBT())) {
/*  549 */           l.add(Byte.valueOf(this.colors[a]));
/*      */         }
/*      */       }
/*  552 */       if (allNull) {
/*  553 */         for (int a = 0; a < this.inventory.inventory.length; a++)
/*  554 */           l.add(Byte.valueOf(this.colors[a]));
/*      */       }
/*      */     }
/*  557 */     return l;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void func_70014_b(NBTTagCompound nbt)
/*      */   {
/*  565 */     super.func_70014_b(nbt);
/*      */     
/*  567 */     nbt.func_74768_a("HomeX", func_110172_bL().field_71574_a);
/*  568 */     nbt.func_74768_a("HomeY", func_110172_bL().field_71572_b);
/*  569 */     nbt.func_74768_a("HomeZ", func_110172_bL().field_71573_c);
/*  570 */     nbt.func_74774_a("HomeFacing", (byte)this.homeFacing);
/*  571 */     nbt.func_74774_a("GolemType", (byte)this.golemType.ordinal());
/*  572 */     nbt.func_74774_a("Core", getCore());
/*  573 */     nbt.func_74778_a("Decoration", this.decoration);
/*  574 */     nbt.func_74774_a("toggles", getTogglesValue());
/*  575 */     nbt.func_74757_a("advanced", this.advanced);
/*  576 */     nbt.func_74773_a("colors", this.colors);
/*  577 */     nbt.func_74773_a("upgrades", this.upgrades);
/*      */     
/*  579 */     if ((getCore() == 5) && (this.fluidCarried != null)) {
/*  580 */       this.fluidCarried.writeToNBT(nbt);
/*      */     }
/*      */     
/*  583 */     if ((getCore() == 6) && (this.essentia != null) && (this.essentiaAmount > 0)) {
/*  584 */       nbt.func_74778_a("essentia", this.essentia.getTag());
/*  585 */       nbt.func_74774_a("essentiaAmount", (byte)this.essentiaAmount);
/*      */     }
/*      */     
/*  588 */     NBTTagCompound var4 = new NBTTagCompound();
/*  589 */     if (this.itemCarried != null)
/*      */     {
/*  591 */       this.itemCarried.func_77955_b(var4);
/*      */     }
/*  593 */     nbt.func_74782_a("ItemCarried", var4);
/*      */     
/*  595 */     if (getOwnerName() == null)
/*      */     {
/*  597 */       nbt.func_74778_a("Owner", "");
/*      */     }
/*      */     else
/*      */     {
/*  601 */       nbt.func_74778_a("Owner", getOwnerName());
/*      */     }
/*      */     
/*      */ 
/*  605 */     NBTTagList tl = new NBTTagList();
/*  606 */     for (Marker l : this.markers) {
/*  607 */       NBTTagCompound nbtc = new NBTTagCompound();
/*  608 */       nbtc.func_74768_a("x", l.x);
/*  609 */       nbtc.func_74768_a("y", l.y);
/*  610 */       nbtc.func_74768_a("z", l.z);
/*  611 */       nbtc.func_74768_a("dim", l.dim);
/*  612 */       nbtc.func_74774_a("side", l.side);
/*  613 */       nbtc.func_74774_a("color", l.color);
/*  614 */       tl.func_74742_a(nbtc);
/*      */     }
/*  616 */     nbt.func_74782_a("Markers", tl);
/*      */     
/*  618 */     nbt.func_74782_a("Inventory", this.inventory.writeToNBT(new NBTTagList()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void func_70037_a(NBTTagCompound nbt)
/*      */   {
/*  627 */     super.func_70037_a(nbt);
/*      */     
/*  629 */     int hx = nbt.func_74762_e("HomeX");
/*  630 */     int hy = nbt.func_74762_e("HomeY");
/*  631 */     int hz = nbt.func_74762_e("HomeZ");
/*  632 */     this.homeFacing = nbt.func_74771_c("HomeFacing");
/*  633 */     func_110171_b(hx, hy, hz, 32);
/*      */     
/*  635 */     this.advanced = nbt.func_74767_n("advanced");
/*  636 */     this.golemType = EnumGolemType.getType(nbt.func_74771_c("GolemType"));
/*      */     
/*  638 */     setCore(nbt.func_74771_c("Core"));
/*      */     
/*  640 */     if (getCore() == 5) {
/*  641 */       this.fluidCarried = FluidStack.loadFluidStackFromNBT(nbt);
/*      */     }
/*      */     
/*  644 */     if (getCore() == 6) {
/*  645 */       String s = nbt.func_74779_i("essentia");
/*  646 */       if (s != null) {
/*  647 */         this.essentia = Aspect.getAspect(s);
/*  648 */         if (this.essentia != null) {
/*  649 */           this.essentiaAmount = nbt.func_74771_c("essentiaAmount");
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  654 */     setTogglesValue(nbt.func_74771_c("toggles"));
/*      */     
/*  656 */     NBTTagCompound var4 = nbt.func_74775_l("ItemCarried");
/*  657 */     this.itemCarried = ItemStack.func_77949_a(var4);
/*  658 */     updateCarried();
/*      */     
/*  660 */     this.decoration = nbt.func_74779_i("Decoration");
/*  661 */     setGolemDecoration(this.decoration);
/*      */     
/*  663 */     String var2 = nbt.func_74779_i("Owner");
/*      */     
/*  665 */     if (var2.length() > 0)
/*      */     {
/*  667 */       setOwner(var2);
/*      */     }
/*      */     
/*  670 */     this.field_70180_af.func_75692_b(30, Byte.valueOf((byte)(int)func_110143_aJ()));
/*      */     
/*  672 */     NBTTagList nbttaglist = nbt.func_150295_c("Markers", 10);
/*  673 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++)
/*      */     {
/*  675 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/*  676 */       int x = nbttagcompound1.func_74762_e("x");
/*  677 */       int y = nbttagcompound1.func_74762_e("y");
/*  678 */       int z = nbttagcompound1.func_74762_e("z");
/*  679 */       int dim = nbttagcompound1.func_74762_e("dim");
/*  680 */       byte s = nbttagcompound1.func_74771_c("side");
/*  681 */       byte c = nbttagcompound1.func_74771_c("color");
/*  682 */       this.markers.add(new Marker(x, y, z, (byte)dim, s, c));
/*      */     }
/*      */     
/*  685 */     this.upgrades = new byte[this.golemType.upgrades + (this.advanced ? 1 : 0)];
/*  686 */     int ul = this.upgrades.length;
/*  687 */     this.upgrades = nbt.func_74770_j("upgrades");
/*  688 */     if (ul != this.upgrades.length) {
/*  689 */       byte[] tt = new byte[ul];
/*  690 */       for (int a = 0; a < ul; a++) tt[a] = -1;
/*  691 */       for (int a = 0; a < this.upgrades.length; a++) {
/*  692 */         if (a < ul) tt[a] = this.upgrades[a];
/*      */       }
/*  694 */       this.upgrades = tt;
/*      */     }
/*      */     
/*      */ 
/*  698 */     String st = "";
/*  699 */     for (byte c : this.upgrades) st = st + Integer.toHexString(c);
/*  700 */     this.field_70180_af.func_75692_b(23, String.valueOf(st));
/*      */     
/*  702 */     setupGolem();
/*  703 */     setupGolemInventory();
/*      */     
/*  705 */     NBTTagList nbttaglist2 = nbt.func_150295_c("Inventory", 10);
/*  706 */     this.inventory.readFromNBT(nbttaglist2);
/*      */     
/*  708 */     this.colors = nbt.func_74770_j("colors");
/*      */     
/*  710 */     byte[] oldcolors = this.colors;
/*  711 */     this.colors = new byte[this.inventory.slotCount];
/*  712 */     for (int a = 0; a < this.inventory.slotCount; a++) {
/*  713 */       this.colors[a] = -1;
/*  714 */       if (a < oldcolors.length) { this.colors[a] = oldcolors[a];
/*      */       }
/*      */     }
/*  717 */     st = "";
/*  718 */     for (byte c : this.colors) {
/*  719 */       if (c == -1) {
/*  720 */         st = st + "h";
/*      */       } else {
/*  722 */         st = st + Integer.toHexString(c);
/*      */       }
/*      */     }
/*  725 */     this.field_70180_af.func_75692_b(22, String.valueOf(st));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public String getOwnerName()
/*      */   {
/*  732 */     return this.field_70180_af.func_75681_e(17);
/*      */   }
/*      */   
/*      */   public void setOwner(String par1Str)
/*      */   {
/*  737 */     this.field_70180_af.func_75692_b(17, par1Str);
/*      */   }
/*      */   
/*      */   public void setMarkers(ArrayList<Marker> markers)
/*      */   {
/*  742 */     this.markers = markers;
/*      */   }
/*      */   
/*      */   public ArrayList<Marker> getMarkers()
/*      */   {
/*  747 */     validateMarkers();
/*  748 */     return this.markers;
/*      */   }
/*      */   
/*      */   protected void validateMarkers() {
/*  752 */     ArrayList<Marker> newMarkers = new ArrayList();
/*  753 */     for (Marker marker : this.markers) {
/*  754 */       if (marker.dim == this.field_70170_p.field_73011_w.field_76574_g)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*  759 */         newMarkers.add(marker);
/*      */       }
/*      */     }
/*  762 */     this.markers = newMarkers;
/*      */   }
/*      */   
/*  765 */   protected ArrayList<Marker> markers = new ArrayList();
/*      */   
/*      */   public EntityLivingBase getOwner()
/*      */   {
/*  769 */     return this.field_70170_p.func_72924_a(getOwnerName());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void func_70665_d(DamageSource ds, float par2)
/*      */   {
/*  777 */     if ((ds.func_76347_k()) && (this.golemType.fireResist)) {
/*  778 */       return;
/*      */     }
/*  780 */     if ((ds == DamageSource.field_76368_d) || (ds == DamageSource.field_76380_i)) {
/*  781 */       func_70012_b(func_110172_bL().field_71574_a + 0.5D, func_110172_bL().field_71572_b + 0.5D, func_110172_bL().field_71573_c + 0.5D, 0.0F, 0.0F);
/*      */     }
/*  783 */     super.func_70665_d(ds, par2);
/*  784 */     if (!this.field_70170_p.field_72995_K) this.field_70180_af.func_75692_b(30, Byte.valueOf((byte)(int)func_110143_aJ()));
/*      */   }
/*      */   
/*      */   public void func_70691_i(float par1)
/*      */   {
/*  789 */     super.func_70691_i(par1);
/*      */     try {
/*  791 */       if (!this.field_70170_p.field_72995_K) this.field_70180_af.func_75692_b(30, Byte.valueOf((byte)(int)func_110143_aJ()));
/*      */     }
/*      */     catch (Exception e) {}
/*      */   }
/*      */   
/*      */   public void func_70606_j(float par1)
/*      */   {
/*  798 */     super.func_70606_j(par1);
/*      */     try {
/*  800 */       if (!this.field_70170_p.field_72995_K) this.field_70180_af.func_75692_b(30, Byte.valueOf((byte)(int)func_110143_aJ()));
/*      */     } catch (Exception e) {}
/*      */   }
/*      */   
/*      */   public float getHealthPercentage() {
/*  805 */     return this.field_70180_af.func_75683_a(30) / func_110138_aP();
/*      */   }
/*      */   
/*      */   public void setCarried(ItemStack stack)
/*      */   {
/*  810 */     this.itemCarried = stack;
/*  811 */     updateCarried();
/*      */   }
/*      */   
/*      */   public boolean hasSomething() {
/*  815 */     return this.inventory.hasSomething();
/*      */   }
/*      */   
/*      */   public ItemStack getCarried()
/*      */   {
/*  820 */     if ((this.itemCarried != null) && (this.itemCarried.field_77994_a <= 0)) {
/*  821 */       setCarried(null);
/*      */     }
/*  823 */     return this.itemCarried;
/*      */   }
/*      */   
/*      */   public int getCarrySpace() {
/*  827 */     if (this.itemCarried == null) return getCarryLimit();
/*  828 */     return Math.min(getCarryLimit() - this.itemCarried.field_77994_a, this.itemCarried.func_77976_d() - this.itemCarried.field_77994_a);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean[] getToggles()
/*      */   {
/*  839 */     return Utils.unpack(this.field_70180_af.func_75683_a(18));
/*      */   }
/*      */   
/*      */   public byte getTogglesValue()
/*      */   {
/*  844 */     return this.field_70180_af.func_75683_a(18);
/*      */   }
/*      */   
/*      */   public void setToggle(int index, boolean tog)
/*      */   {
/*  849 */     boolean[] fz = getToggles();
/*  850 */     fz[index] = tog;
/*  851 */     this.field_70180_af.func_75692_b(18, Byte.valueOf(Utils.pack(fz)));
/*      */   }
/*      */   
/*      */   public void setTogglesValue(byte tog)
/*      */   {
/*  856 */     this.field_70180_af.func_75692_b(18, Byte.valueOf(tog));
/*      */   }
/*      */   
/*      */   public boolean canAttackHostiles() {
/*  860 */     return getToggles()[1] == 0;
/*      */   }
/*      */   
/*      */   public boolean canAttackAnimals() {
/*  864 */     return getToggles()[2] == 0;
/*      */   }
/*      */   
/*      */   public boolean canAttackPlayers() {
/*  868 */     return getToggles()[3] == 0;
/*      */   }
/*      */   
/*      */   public boolean canAttackCreepers() {
/*  872 */     return getToggles()[4] == 0;
/*      */   }
/*      */   
/*      */   public boolean checkOreDict() {
/*  876 */     return getToggles()[5];
/*      */   }
/*      */   
/*      */   public boolean ignoreDamage() {
/*  880 */     return getToggles()[6];
/*      */   }
/*      */   
/*      */   public boolean ignoreNBT() {
/*  884 */     return getToggles()[7];
/*      */   }
/*      */   
/*      */   public EnumGolemType getGolemType() {
/*  888 */     return EnumGolemType.getType(this.field_70180_af.func_75683_a(19));
/*      */   }
/*      */   
/*      */   public int getGolemStrength() {
/*  892 */     return getGolemType().strength + getUpgradeAmount(1);
/*      */   }
/*      */   
/*      */   public void setCore(byte core)
/*      */   {
/*  897 */     this.field_70180_af.func_75692_b(21, Byte.valueOf(core));
/*      */   }
/*      */   
/*      */   public byte getCore() {
/*  901 */     return this.field_70180_af.func_75683_a(21);
/*      */   }
/*      */   
/*      */   public String getGolemDecoration() {
/*  905 */     return this.field_70180_af.func_75681_e(20);
/*      */   }
/*      */   
/*      */   public void setGolemDecoration(String string) {
/*  909 */     this.field_70180_af.func_75692_b(20, String.valueOf(this.decoration));
/*      */   }
/*      */   
/*      */   public ItemStack getCarriedForDisplay()
/*      */   {
/*  914 */     if (this.field_70180_af.func_82710_f(16) != null)
/*  915 */       return this.field_70180_af.func_82710_f(16);
/*  916 */     return null;
/*      */   }
/*      */   
/*      */   public void updateCarried()
/*      */   {
/*  921 */     if (this.itemCarried != null) {
/*  922 */       func_70096_w().func_75692_b(16, this.itemCarried.func_77946_l());
/*  923 */       func_70096_w().func_82708_h(16);
/*      */     }
/*  925 */     else if ((getCore() == 5) && (this.fluidCarried != null)) {
/*  926 */       func_70096_w().func_75692_b(16, new ItemStack(Item.func_150899_d(this.fluidCarried.fluidID), 1, this.fluidCarried.amount));
/*  927 */       func_70096_w().func_82708_h(16);
/*      */     }
/*  929 */     else if (getCore() == 6) {
/*  930 */       ItemStack disp = new ItemStack(ConfigItems.itemJarFilled);
/*  931 */       int amt = (int)(64.0F * (this.essentiaAmount / getCarryLimit()));
/*  932 */       if ((this.essentia != null) && (this.essentiaAmount > 0)) {
/*  933 */         ((IEssentiaContainerItem)disp.func_77973_b()).setAspects(disp, new AspectList().add(this.essentia, amt));
/*      */       }
/*      */       
/*  936 */       func_70096_w().func_75692_b(16, disp);
/*  937 */       func_70096_w().func_82708_h(16);
/*      */     } else {
/*  939 */       func_70096_w().func_82709_a(16, 5);
/*  940 */       func_70096_w().func_82708_h(16);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   protected float func_70599_aP()
/*      */   {
/*  947 */     return 0.1F;
/*      */   }
/*      */   
/*  950 */   boolean pdw = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void func_70628_a(boolean par1, int par2)
/*      */   {
/*  959 */     dropStuff();
/*      */   }
/*      */   
/*      */ 
/*      */   public void dropStuff()
/*      */   {
/*  965 */     if ((!this.field_70170_p.field_72995_K) && (this.itemCarried != null)) {
/*  966 */       func_70099_a(this.itemCarried, 0.5F);
/*      */     }
/*      */   }
/*      */   
/*      */   protected boolean addDecoration(String type, ItemStack itemStack)
/*      */   {
/*  972 */     if (this.decoration.contains(type)) return false;
/*  973 */     if (((type.equals("F")) || (type.equals("H"))) && ((this.decoration.contains("F")) || (this.decoration.contains("H")))) return false;
/*  974 */     if (((type.equals("G")) || (type.equals("V"))) && ((this.decoration.contains("G")) || (this.decoration.contains("V")))) return false;
/*  975 */     if (((type.equals("B")) || (type.equals("P"))) && ((this.decoration.contains("P")) || (this.decoration.contains("B")))) { return false;
/*      */     }
/*  977 */     this.decoration += type;
/*      */     
/*  979 */     if (!this.field_70170_p.field_72995_K) {
/*  980 */       setGolemDecoration(this.decoration);
/*  981 */       itemStack.field_77994_a -= 1;
/*  982 */       this.field_70170_p.func_72956_a(this, "thaumcraft:cameraclack", 1.0F, 1.0F);
/*      */     }
/*      */     
/*  985 */     setupGolem();
/*      */     
/*  987 */     return true;
/*      */   }
/*      */   
/*      */   public boolean customInteraction(EntityPlayer player) {
/*  991 */     if ((player.field_71071_by.func_70448_g() != null) && (player.field_71071_by.func_70448_g().func_77973_b() == ConfigItems.itemGolemBell))
/*      */     {
/*  993 */       return false;
/*      */     }
/*  995 */     if ((player.field_71071_by.func_70448_g() != null) && (player.field_71071_by.func_70448_g().func_77973_b() == ConfigItems.itemGolemDecoration))
/*      */     {
/*  997 */       addDecoration(ItemGolemDecoration.getDecoChar(player.field_71071_by.func_70448_g().func_77960_j()), player.field_71071_by.func_70448_g());
/*      */       
/*      */ 
/* 1000 */       player.func_71038_i();
/* 1001 */       return false;
/*      */     }
/* 1003 */     if ((player.field_71071_by.func_70448_g() != null) && (player.field_71071_by.func_70448_g().func_77973_b() == Items.field_151106_aX)) {
/* 1004 */       player.field_71071_by.func_146026_a(Items.field_151106_aX);
/* 1005 */       player.func_71038_i();
/*      */       
/* 1007 */       for (int var3 = 0; var3 < 3; var3++)
/*      */       {
/* 1009 */         double var4 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 1010 */         double var6 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 1011 */         double var8 = this.field_70146_Z.nextGaussian() * 0.02D;
/* 1012 */         this.field_70170_p.func_72869_a("heart", this.field_70165_t + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N, this.field_70163_u + 0.5D + this.field_70146_Z.nextFloat() * this.field_70131_O, this.field_70161_v + this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F - this.field_70130_N, var4, var6, var8);
/* 1013 */         this.field_70170_p.func_72956_a(this, "random.eat", 0.3F, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F);
/* 1014 */         int duration = 600;
/* 1015 */         if (this.field_70170_p.field_72995_K) {
/* 1016 */           if ((func_70660_b(Potion.field_76424_c) != null) && (func_70660_b(Potion.field_76424_c).func_76459_b() < 2400)) duration += func_70660_b(Potion.field_76424_c).func_76459_b();
/* 1017 */           func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, duration, 0));
/*      */         }
/*      */       }
/* 1020 */       func_70691_i(5.0F);
/* 1021 */       return false;
/*      */     }
/* 1023 */     if ((getCore() > -1) && (ItemGolemCore.hasGUI(getCore())) && ((player.field_71071_by.func_70448_g() == null) || (!(player.field_71071_by.func_70448_g().func_77973_b() instanceof ItemWandCasting))) && (!this.field_70170_p.field_72995_K))
/*      */     {
/* 1025 */       player.openGui(Thaumcraft.instance, 0, this.field_70170_p, func_145782_y(), 0, 0);
/* 1026 */       return false;
/*      */     }
/*      */     
/* 1029 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean func_70085_c(EntityPlayer player)
/*      */   {
/* 1037 */     if (player.func_70093_af()) { return false;
/*      */     }
/* 1039 */     ItemStack itemstack = player.field_71071_by.func_70448_g();
/*      */     
/* 1041 */     if ((getCore() > -1) && (itemstack != null) && (itemstack.func_77973_b() == ConfigItems.itemGolemBell)) {
/* 1042 */       return false;
/*      */     }
/* 1044 */     if ((getCore() == -1) && (itemstack != null) && (itemstack.func_77973_b() == ConfigItems.itemGolemCore) && (itemstack.func_77960_j() != 100))
/*      */     {
/*      */ 
/* 1047 */       setCore((byte)itemstack.func_77960_j());
/* 1048 */       setupGolem();
/* 1049 */       setupGolemInventory();
/* 1050 */       itemstack.field_77994_a -= 1;
/* 1051 */       if (itemstack.field_77994_a <= 0)
/*      */       {
/* 1053 */         player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, (ItemStack)null);
/*      */       }
/* 1055 */       this.field_70170_p.func_72956_a(this, "thaumcraft:upgrade", 0.5F, 1.0F);
/* 1056 */       player.func_71038_i();
/* 1057 */       this.field_70170_p.func_72960_a(this, (byte)7);
/* 1058 */       return true;
/*      */     }
/* 1060 */     if ((itemstack != null) && (itemstack.func_77973_b() == ConfigItems.itemGolemUpgrade))
/*      */     {
/* 1062 */       for (int a = 0; a < this.upgrades.length; a++) {
/* 1063 */         if ((getUpgrade(a) == -1) && (getUpgradeAmount(itemstack.func_77960_j()) < 2)) {
/* 1064 */           setUpgrade(a, (byte)itemstack.func_77960_j());
/* 1065 */           setupGolem();
/* 1066 */           setupGolemInventory();
/* 1067 */           itemstack.field_77994_a -= 1;
/* 1068 */           if (itemstack.field_77994_a <= 0)
/*      */           {
/* 1070 */             player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, (ItemStack)null);
/*      */           }
/* 1072 */           this.field_70170_p.func_72956_a(this, "thaumcraft:upgrade", 0.5F, 1.0F);
/* 1073 */           player.func_71038_i();
/* 1074 */           return true;
/*      */         }
/*      */       }
/* 1077 */       return false;
/*      */     }
/*      */     
/* 1080 */     return customInteraction(player);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 1085 */   public int action = 0;
/* 1086 */   public int leftArm = 0;
/* 1087 */   public int rightArm = 0;
/* 1088 */   public int healing = 0;
/*      */   
/*      */   public int getActionTimer() {
/* 1091 */     return 3 - Math.abs(this.action - 3);
/*      */   }
/*      */   
/*      */   public void startActionTimer() {
/* 1095 */     if (this.action == 0) {
/* 1096 */       this.action = 6;
/* 1097 */       this.field_70170_p.func_72960_a(this, (byte)4);
/*      */     }
/*      */   }
/*      */   
/*      */   public void startLeftArmTimer() {
/* 1102 */     if (this.leftArm == 0) {
/* 1103 */       this.leftArm = 5;
/* 1104 */       this.field_70170_p.func_72960_a(this, (byte)6);
/*      */     }
/*      */   }
/*      */   
/*      */   public void startRightArmTimer() {
/* 1109 */     if (this.rightArm == 0) {
/* 1110 */       this.rightArm = 5;
/* 1111 */       this.field_70170_p.func_72960_a(this, (byte)8);
/*      */     }
/*      */   }
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public void func_70103_a(byte par1)
/*      */   {
/* 1118 */     if (par1 == 4)
/*      */     {
/* 1120 */       this.action = 6;
/*      */     }
/* 1122 */     else if (par1 == 5)
/*      */     {
/* 1124 */       this.healing = 5;
/*      */       
/* 1126 */       int bonus = 0;
/* 1127 */       try { bonus = getGolemDecoration().contains("H") ? 5 : 0; } catch (Exception e) {}
/* 1128 */       func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(getGolemType().health + bonus);
/*      */ 
/*      */     }
/* 1131 */     else if (par1 == 6)
/*      */     {
/* 1133 */       this.leftArm = 5;
/*      */     }
/* 1135 */     else if (par1 == 8)
/*      */     {
/* 1137 */       this.rightArm = 5;
/*      */     }
/* 1139 */     else if (par1 == 7)
/*      */     {
/* 1141 */       this.bootup = 33.0F;
/*      */     }
/*      */     else
/*      */     {
/* 1145 */       super.func_70103_a(par1);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   protected void func_70064_a(double par1, boolean par3)
/*      */   {
/* 1152 */     if ((par3) && (this.field_70143_R > 0.0F))
/*      */     {
/* 1154 */       int var4 = MathHelper.func_76128_c(this.field_70165_t);
/* 1155 */       int var5 = MathHelper.func_76128_c(this.field_70163_u - 0.20000000298023224D - this.field_70129_M);
/* 1156 */       int var6 = MathHelper.func_76128_c(this.field_70161_v);
/* 1157 */       Block var7 = this.field_70170_p.func_147439_a(var4, var5, var6);
/*      */       
/* 1159 */       if ((this.field_70170_p.func_147437_c(var4, var5, var6)) && (this.field_70170_p.func_147439_a(var4, var5 - 1, var6) == Blocks.field_150422_aJ))
/*      */       {
/* 1161 */         var7 = this.field_70170_p.func_147439_a(var4, var5 - 1, var6);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1166 */     if (par3)
/*      */     {
/* 1168 */       if (this.field_70143_R > 0.0F)
/*      */       {
/* 1170 */         func_70069_a(this.field_70143_R);
/* 1171 */         this.field_70143_R = 0.0F;
/*      */       }
/*      */     }
/* 1174 */     else if (par1 < 0.0D)
/*      */     {
/* 1176 */       this.field_70143_R = ((float)(this.field_70143_R - par1));
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public EntityLivingBase func_70638_az()
/*      */   {
/* 1183 */     EntityLivingBase e = super.func_70638_az();
/* 1184 */     if ((e != null) && (!e.func_70089_S())) e = null;
/* 1185 */     return e;
/*      */   }
/*      */   
/*      */ 
/*      */   public int func_70658_aO()
/*      */   {
/* 1191 */     int var1 = super.func_70658_aO() + this.golemType.armor;
/*      */     
/* 1193 */     if (this.decoration.contains("V")) var1++;
/* 1194 */     if (this.decoration.contains("P")) { var1 += 4;
/*      */     }
/* 1196 */     if (var1 > 20)
/*      */     {
/* 1198 */       var1 = 20;
/*      */     }
/*      */     
/* 1201 */     return var1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean func_70652_k(Entity par1Entity)
/*      */   {
/* 1208 */     float f = (float)func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
/* 1209 */     int i = 0;
/*      */     
/* 1211 */     if ((par1Entity instanceof EntityLivingBase))
/*      */     {
/* 1213 */       f += EnchantmentHelper.func_77512_a(this, (EntityLivingBase)par1Entity);
/* 1214 */       i += EnchantmentHelper.func_77507_b(this, (EntityLivingBase)par1Entity);
/*      */     }
/*      */     
/* 1217 */     boolean flag = par1Entity.func_70097_a(DamageSource.func_76358_a(this), f);
/*      */     
/* 1219 */     if (flag)
/*      */     {
/* 1221 */       if (this.decoration.contains("V")) {
/* 1222 */         EntityUtils.setRecentlyHit((EntityLivingBase)par1Entity, 100);
/*      */       }
/*      */       
/* 1225 */       if (i > 0)
/*      */       {
/* 1227 */         par1Entity.func_70024_g(-MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F) * i * 0.5F, 0.1D, MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F) * i * 0.5F);
/* 1228 */         this.field_70159_w *= 0.6D;
/* 1229 */         this.field_70179_y *= 0.6D;
/*      */       }
/*      */       
/* 1232 */       int j = EnchantmentHelper.func_90036_a(this) + getUpgradeAmount(2);
/*      */       
/* 1234 */       if (j > 0)
/*      */       {
/* 1236 */         par1Entity.func_70015_d(j * 4);
/*      */       }
/*      */       
/* 1239 */       if ((par1Entity instanceof EntityLivingBase))
/*      */       {
/* 1241 */         EnchantmentHelper.func_151384_a((EntityLivingBase)par1Entity, this);
/*      */       }
/*      */       
/* 1244 */       EnchantmentHelper.func_151385_b(this, par1Entity);
/*      */     }
/*      */     
/* 1247 */     return flag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean func_70097_a(DamageSource ds, float par2)
/*      */   {
/* 1255 */     this.paused = false;
/* 1256 */     if (ds == DamageSource.field_76367_g) { return false;
/*      */     }
/* 1258 */     if ((getGolemType() == EnumGolemType.THAUMIUM) && (ds == DamageSource.field_76376_m)) { par2 *= 0.5F;
/*      */     }
/* 1260 */     if ((ds.func_76364_f() != null) && (getUpgradeAmount(5) > 0) && (ds.func_76364_f().func_145782_y() != func_145782_y()))
/*      */     {
/* 1262 */       ds.func_76364_f().func_70097_a(DamageSource.func_92087_a(this), getUpgradeAmount(5) * 2 + this.field_70146_Z.nextInt(2 * getUpgradeAmount(5)));
/*      */       
/*      */ 
/* 1265 */       ds.func_76364_f().func_85030_a("damage.thorns", 0.5F, 1.0F);
/*      */     }
/*      */     
/* 1268 */     return super.func_70097_a(ds, par2);
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean func_70686_a(Class par1Class)
/*      */   {
/* 1274 */     return (EntityVillager.class != par1Class) && (EntityGolemBase.class != par1Class) && (EntityBat.class != par1Class);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isValidTarget(Entity target)
/*      */   {
/* 1281 */     if (!target.func_70089_S()) { return false;
/*      */     }
/* 1283 */     if (((target instanceof EntityPlayer)) && (((EntityPlayer)target).func_70005_c_().equals(getOwnerName()))) {
/* 1284 */       return false;
/*      */     }
/* 1286 */     if (!func_110176_b(MathHelper.func_76128_c(target.field_70165_t), MathHelper.func_76128_c(target.field_70163_u), MathHelper.func_76128_c(target.field_70161_v)))
/*      */     {
/*      */ 
/* 1289 */       return false;
/*      */     }
/* 1291 */     if (getCore() == 9) {
/* 1292 */       if ((((target instanceof EntityAnimal)) || ((target instanceof IAnimals))) && (!(target instanceof IMob)) && ((!(target instanceof EntityTameable)) || (!((EntityTameable)target).func_70909_n())) && (!(target instanceof EntityGolem)))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/* 1297 */         if (((target instanceof EntityAnimal)) && (((EntityAnimal)target).func_70631_g_())) { return false;
/*      */         }
/* 1299 */         return true;
/*      */       }
/*      */     }
/*      */     else {
/* 1303 */       if ((canAttackCreepers()) && (getUpgradeAmount(4) > 0) && ((target instanceof EntityCreeper)))
/*      */       {
/* 1305 */         return true;
/*      */       }
/* 1307 */       if ((canAttackHostiles()) && (((target instanceof EntityMob)) || ((target instanceof IMob))) && (!(target instanceof EntityCreeper)))
/*      */       {
/*      */ 
/* 1310 */         return true;
/*      */       }
/* 1312 */       if ((canAttackAnimals()) && (getUpgradeAmount(4) > 0) && (((target instanceof EntityAnimal)) || ((target instanceof IAnimals))) && (!(target instanceof IMob)) && ((!(target instanceof EntityTameable)) || (!((EntityTameable)target).func_70909_n())) && (!(target instanceof EntityGolem)))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/* 1317 */         return true;
/*      */       }
/* 1319 */       if ((canAttackPlayers()) && (getUpgradeAmount(4) > 0) && ((target instanceof EntityPlayer)))
/*      */       {
/* 1321 */         return true;
/*      */       }
/*      */     }
/* 1324 */     return false;
/*      */   }
/*      */   
/*      */   public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLiving)
/*      */   {
/* 1329 */     EntityDart var2 = new EntityDart(this.field_70170_p, this, par1EntityLiving, 1.6F, 7.0F - getUpgradeAmount(3) * 1.75F);
/* 1330 */     float f = (float)func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
/* 1331 */     var2.func_70239_b(f * 0.4F);
/* 1332 */     func_85030_a("thaumcraft:golemironshoot", 0.5F, 1.0F / (func_70681_au().nextFloat() * 0.4F + 0.6F));
/* 1333 */     this.field_70170_p.func_72838_d(var2);
/* 1334 */     startLeftArmTimer();
/*      */   }
/*      */   
/*      */   public int getAttackSpeed() {
/* 1338 */     return 20 - (this.advanced ? 2 : 0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected String func_70639_aQ()
/*      */   {
/* 1345 */     return "thaumcraft:cameraclack";
/*      */   }
/*      */   
/*      */ 
/*      */   protected String func_70621_aR()
/*      */   {
/* 1351 */     return "thaumcraft:cameraclack";
/*      */   }
/*      */   
/*      */ 
/*      */   protected String func_70673_aS()
/*      */   {
/* 1357 */     return "thaumcraft:cameraclack";
/*      */   }
/*      */   
/*      */ 
/*      */   public void writeSpawnData(ByteBuf data)
/*      */   {
/* 1363 */     data.writeByte(getCore());
/* 1364 */     data.writeBoolean(this.advanced);
/* 1365 */     data.writeByte(this.inventory.slotCount);
/* 1366 */     data.writeByte(this.upgrades.length);
/* 1367 */     for (byte b : this.upgrades) data.writeByte(b);
/*      */   }
/*      */   
/*      */   public void readSpawnData(ByteBuf data)
/*      */   {
/*      */     try {
/* 1373 */       setCore(data.readByte());
/* 1374 */       this.advanced = data.readBoolean();
/* 1375 */       if (getCore() >= 0) this.bootup = 0.0F;
/* 1376 */       this.inventory = new InventoryMob(this, data.readByte());
/*      */       
/* 1378 */       this.colors = new byte[this.inventory.slotCount];
/* 1379 */       for (int a = 0; a < this.inventory.slotCount; a++) { this.colors[a] = -1;
/*      */       }
/* 1381 */       this.upgrades = new byte[data.readByte()];
/* 1382 */       for (int a = 0; a < this.upgrades.length; a++) { this.upgrades[a] = data.readByte();
/*      */       }
/*      */       
/* 1385 */       int bonus = 0;
/*      */       try {
/* 1387 */         bonus = getGolemDecoration().contains("H") ? 5 : 0;
/*      */       }
/*      */       catch (Exception e) {}
/* 1390 */       func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(getGolemType().health + bonus);
/*      */     }
/*      */     catch (Exception e) {}
/*      */   }
/*      */   
/*      */ 
/*      */   public String func_70005_c_()
/*      */   {
/* 1398 */     if (func_94056_bM()) {
/* 1399 */       return func_94057_bL();
/*      */     }
/* 1401 */     return StatCollector.func_74838_a("item.ItemGolemPlacer." + getGolemType().ordinal() + ".name");
/*      */   }
/*      */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/golems/EntityGolemBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */