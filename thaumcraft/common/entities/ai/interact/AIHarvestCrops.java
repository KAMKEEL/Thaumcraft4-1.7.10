/*     */ package thaumcraft.common.entities.ai.interact;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.Block.SoundType;
/*     */ import net.minecraft.block.BlockDirectional;
/*     */ import net.minecraft.block.BlockLog;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityLookHelper;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemSeedFood;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.common.IPlantable;
/*     */ import net.minecraftforge.common.util.FakePlayer;
/*     */ import net.minecraftforge.common.util.FakePlayerFactory;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.BlockCoordinates;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.lib.utils.CropUtils;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ public class AIHarvestCrops extends EntityAIBase
/*     */ {
/*     */   private EntityGolemBase theGolem;
/*     */   private int xx;
/*     */   private int yy;
/*     */   private int zz;
/*     */   private float movementSpeed;
/*     */   private float distance;
/*     */   private World theWorld;
/*  47 */   private Block block = Blocks.field_150350_a;
/*  48 */   private int blockMd = 0;
/*  49 */   private int delay = -1;
/*  50 */   private int maxDelay = 1;
/*  51 */   private int mod = 1;
/*  52 */   private int count = 0;
/*     */   
/*     */   public AIHarvestCrops(EntityGolemBase par1EntityCreature)
/*     */   {
/*  56 */     this.theGolem = par1EntityCreature;
/*  57 */     this.theWorld = par1EntityCreature.field_70170_p;
/*  58 */     func_75248_a(3);
/*  59 */     this.distance = MathHelper.func_76123_f(this.theGolem.getRange() / 4.0F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  67 */     if ((this.delay >= 0) || (this.theGolem.field_70173_aa % Config.golemDelay > 0) || (!this.theGolem.func_70661_as().func_75500_f())) {
/*  68 */       return false;
/*     */     }
/*     */     
/*  71 */     Vec3 var1 = findGrownCrop();
/*     */     
/*  73 */     if (var1 == null)
/*     */     {
/*  75 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  79 */     this.xx = ((int)var1.field_72450_a);
/*  80 */     this.yy = ((int)var1.field_72448_b);
/*  81 */     this.zz = ((int)var1.field_72449_c);
/*     */     
/*  83 */     this.block = this.theWorld.func_147439_a(this.xx, this.yy, this.zz);
/*  84 */     this.blockMd = this.theWorld.func_72805_g(this.xx, this.yy, this.zz);
/*     */     
/*  86 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  95 */     return (this.theWorld.func_147439_a(this.xx, this.yy, this.zz) == this.block) && (this.theWorld.func_72805_g(this.xx, this.yy, this.zz) == this.blockMd) && (this.count-- > 0) && ((this.delay > 0) || (!this.theGolem.func_70661_as().func_75500_f()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_75246_d()
/*     */   {
/* 102 */     double dist = this.theGolem.func_70092_e(this.xx + 0.5D, this.yy + 0.5D, this.zz + 0.5D);
/* 103 */     this.theGolem.func_70671_ap().func_75650_a(this.xx + 0.5D, this.yy + 0.5D, this.zz + 0.5D, 30.0F, 30.0F);
/* 104 */     if (dist <= 4.0D)
/*     */     {
/* 106 */       if (this.delay < 0) {
/* 107 */         this.delay = ((int)Math.max(10.0F, (20.0F - this.theGolem.getGolemStrength() * 2.0F) * this.block.func_149712_f(this.theWorld, this.xx, this.yy, this.zz)));
/* 108 */         this.maxDelay = this.delay;
/* 109 */         this.mod = (this.delay / Math.round(this.delay / 6.0F));
/*     */       }
/*     */       
/* 112 */       if (this.delay > 0) {
/* 113 */         if ((--this.delay > 0) && (this.delay % this.mod == 0) && (this.theGolem.func_70661_as().func_75500_f())) {
/* 114 */           this.theGolem.startActionTimer();
/* 115 */           this.theWorld.func_72908_a(this.xx + 0.5F, this.yy + 0.5F, this.zz + 0.5F, this.block.field_149762_H.func_150495_a(), (this.block.field_149762_H.func_150497_c() + 0.7F) / 8.0F, this.block.field_149762_H.func_150494_d() * 0.5F);
/*     */           
/*     */ 
/* 118 */           BlockUtils.destroyBlockPartially(this.theWorld, this.theGolem.func_145782_y(), this.xx, this.yy, this.zz, (int)(9.0F * (1.0F - this.delay / this.maxDelay)));
/*     */         }
/*     */         
/*     */ 
/* 122 */         if (this.delay == 0) {
/* 123 */           harvest();
/* 124 */           checkAdjacent();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void checkAdjacent()
/*     */   {
/* 135 */     for (int x2 = -2; x2 <= 2; x2++) {
/* 136 */       for (int z2 = -2; z2 <= 2; z2++) {
/* 137 */         for (int y2 = -1; y2 <= 1; y2++) {
/* 138 */           int x = this.xx + x2;
/* 139 */           int y = this.yy + y2;
/* 140 */           int z = this.zz + z2;
/*     */           
/* 142 */           if ((Math.abs(this.theGolem.func_110172_bL().field_71574_a - x) <= this.distance) && (Math.abs(this.theGolem.func_110172_bL().field_71572_b - y) <= this.distance) && (Math.abs(this.theGolem.func_110172_bL().field_71573_c - z) <= this.distance))
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/* 147 */             if (CropUtils.isGrownCrop(this.theWorld, x, y, z))
/*     */             {
/* 149 */               Vec3 var1 = Vec3.func_72443_a(x, y, z);
/*     */               
/* 151 */               if (var1 != null)
/*     */               {
/* 153 */                 this.xx = ((int)var1.field_72450_a);
/* 154 */                 this.yy = ((int)var1.field_72448_b);
/* 155 */                 this.zz = ((int)var1.field_72449_c);
/*     */                 
/* 157 */                 this.block = this.theWorld.func_147439_a(this.xx, this.yy, this.zz);
/* 158 */                 this.blockMd = this.theWorld.func_72805_g(this.xx, this.yy, this.zz);
/*     */                 
/* 160 */                 this.delay = -1;
/*     */                 
/* 162 */                 func_75249_e();
/* 163 */                 return;
/*     */               }
/*     */             } }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_75251_c() {
/* 172 */     BlockUtils.destroyBlockPartially(this.theWorld, this.theGolem.func_145782_y(), this.xx, this.yy, this.zz, -1);
/* 173 */     this.delay = -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/* 181 */     this.count = 200;
/* 182 */     this.theGolem.func_70661_as().func_75492_a(this.xx + 0.5D, this.yy + 0.5D, this.zz + 0.5D, this.theGolem.func_70689_ay());
/*     */   }
/*     */   
/* 185 */   ArrayList<BlockCoordinates> checklist = new ArrayList();
/*     */   
/*     */ 
/*     */   private Vec3 findGrownCrop()
/*     */   {
/* 190 */     Random rand = this.theGolem.func_70681_au();
/*     */     
/* 192 */     if (this.checklist.size() == 0) {
/* 193 */       for (int a = (int)-this.distance; a <= this.distance; a++) {
/* 194 */         for (int b = (int)-this.distance; b <= this.distance; b++)
/* 195 */           this.checklist.add(new BlockCoordinates(this.theGolem.func_110172_bL().field_71574_a + a, 0, this.theGolem.func_110172_bL().field_71573_c + b));
/*     */       }
/* 197 */       Collections.shuffle(this.checklist, rand);
/*     */     }
/*     */     
/* 200 */     int x = ((BlockCoordinates)this.checklist.get(0)).x;
/* 201 */     int z = ((BlockCoordinates)this.checklist.get(0)).z;
/* 202 */     this.checklist.remove(0);
/*     */     
/* 204 */     for (int y = this.theGolem.func_110172_bL().field_71572_b - 3; 
/* 205 */         y <= this.theGolem.func_110172_bL().field_71572_b + 3; y++)
/*     */     {
/* 207 */       if (CropUtils.isGrownCrop(this.theWorld, x, y, z))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 213 */         return Vec3.func_72443_a(x, y, z);
/*     */       }
/*     */     }
/*     */     
/* 217 */     return null;
/*     */   }
/*     */   
/*     */   void harvest()
/*     */   {
/* 222 */     this.count = 200;
/* 223 */     int md = this.blockMd;
/* 224 */     FakePlayer fp = FakePlayerFactory.get((WorldServer)this.theWorld, new GameProfile((java.util.UUID)null, "FakeThaumcraftGolem"));
/* 225 */     fp.func_70107_b(this.theGolem.field_70165_t, this.theGolem.field_70163_u, this.theGolem.field_70161_v);
/* 226 */     if (CropUtils.clickableCrops.contains(this.block.func_149739_a() + md)) {
/* 227 */       this.block.func_149727_a(this.theWorld, this.xx, this.yy, this.zz, fp, 0, 0.0F, 0.0F, 0.0F);
/*     */     }
/*     */     else {
/* 230 */       this.theWorld.func_147480_a(this.xx, this.yy, this.zz, true);
/*     */       
/*     */ 
/* 233 */       if (this.theGolem.getUpgradeAmount(4) > 0) {
/* 234 */         ArrayList<ItemStack> items = new ArrayList();
/* 235 */         ArrayList<Entity> drops = EntityUtils.getEntitiesInRange(this.theWorld, this.theGolem.field_70165_t, this.theGolem.field_70163_u, this.theGolem.field_70161_v, this.theGolem, EntityItem.class, 6.0D);
/* 236 */         if (drops.size() > 0) {
/* 237 */           for (Entity e : drops) {
/* 238 */             if ((e instanceof EntityItem)) {
/* 239 */               if (e.field_70173_aa < 2) {
/* 240 */                 Vec3 v = Vec3.func_72443_a(e.field_70165_t - this.theGolem.field_70165_t, e.field_70163_u - this.theGolem.field_70163_u, e.field_70161_v - this.theGolem.field_70161_v);
/* 241 */                 v = v.func_72432_b();
/* 242 */                 e.field_70159_w = (-v.field_72450_a / 4.0D);
/* 243 */                 e.field_70181_x = 0.075D;
/* 244 */                 e.field_70179_y = (-v.field_72449_c / 4.0D);
/*     */               }
/*     */               
/* 247 */               boolean done = false;
/* 248 */               EntityItem item = (EntityItem)e;
/* 249 */               ItemStack st = item.func_92059_d();
/*     */               
/* 251 */               if ((st.func_77973_b() != null) && (st.func_77973_b() == Items.field_151100_aR) && (st.func_77960_j() == 3)) {
/* 252 */                 int var5 = BlockDirectional.func_149895_l(this.blockMd);
/* 253 */                 int par2 = this.xx + net.minecraft.util.Direction.field_71583_a[var5];
/* 254 */                 int par4 = this.zz + net.minecraft.util.Direction.field_71581_b[var5];
/* 255 */                 Block var6 = this.theWorld.func_147439_a(par2, this.yy, par4);
/* 256 */                 if ((var6 == Blocks.field_150364_r) && (BlockLog.func_150165_c(this.theWorld.func_72805_g(par2, this.yy, par4)) == 3)) {
/* 257 */                   st.field_77994_a -= 1;
/* 258 */                   this.theWorld.func_147465_d(this.xx, this.yy, this.zz, Blocks.field_150375_by, BlockDirectional.func_149895_l(this.blockMd), 3);
/*     */                 }
/* 260 */                 done = true;
/*     */               }
/* 262 */               else if ((st.func_77973_b() != null) && (st.func_77973_b() == thaumcraft.common.config.ConfigItems.itemManaBean)) {
/* 263 */                 if (this.block.func_149707_d(this.theWorld, this.xx, this.yy, this.zz, 0)) {
/* 264 */                   st.field_77994_a -= 1;
/* 265 */                   if (!st.func_77973_b().func_77648_a(st.func_77946_l(), fp, this.theWorld, this.xx, this.yy + 1, this.zz, 0, 0.5F, 0.5F, 0.5F)) {
/* 266 */                     this.theWorld.func_147465_d(this.xx, this.yy, this.zz, ConfigBlocks.blockManaPod, 0, 3);
/*     */                   }
/*     */                 }
/* 269 */                 done = true;
/*     */ 
/*     */ 
/*     */ 
/*     */               }
/*     */               else
/*     */               {
/*     */ 
/*     */ 
/* 278 */                 int[] xm = { 0, 0, 1, 1, -1, 0, -1, -1, 1 };
/* 279 */                 int[] zm = { 0, 1, 0, 1, 0, -1, -1, 1, -1 };
/* 280 */                 int count = 0;
/* 281 */                 while ((st != null) && (st.field_77994_a > 0) && (count < 9)) {
/* 282 */                   if ((st.func_77973_b() != null) && (((st.func_77973_b() instanceof IPlantable)) || ((st.func_77973_b() instanceof ItemSeedFood))))
/*     */                   {
/*     */ 
/* 285 */                     if (st.func_77973_b().func_77648_a(st.func_77946_l(), fp, this.theWorld, this.xx + xm[count], this.yy - 1, this.zz + zm[count], ForgeDirection.UP.ordinal(), 0.5F, 0.5F, 0.5F)) {
/* 286 */                       st.field_77994_a -= 1;
/*     */                     }
/*     */                   }
/* 289 */                   count++;
/*     */                 }
/*     */               }
/*     */               
/* 293 */               if (st.field_77994_a <= 0) {
/* 294 */                 item.func_70106_y();
/*     */               } else {
/* 296 */                 item.func_92058_a(st);
/*     */               }
/*     */               
/* 299 */               if (done)
/*     */                 break;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 306 */     fp.func_70106_y();
/* 307 */     this.theGolem.startActionTimer();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/interact/AIHarvestCrops.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */