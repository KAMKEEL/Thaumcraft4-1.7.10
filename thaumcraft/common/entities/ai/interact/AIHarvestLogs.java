/*     */ package thaumcraft.common.entities.ai.interact;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.Block.SoundType;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityLookHelper;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.common.util.FakePlayer;
/*     */ import net.minecraftforge.common.util.FakePlayerFactory;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ public class AIHarvestLogs extends EntityAIBase
/*     */ {
/*     */   private EntityGolemBase theGolem;
/*     */   private int xx;
/*     */   private int yy;
/*     */   private int zz;
/*     */   private float movementSpeed;
/*     */   private float distance;
/*     */   private World theWorld;
/*  31 */   private Block block = net.minecraft.init.Blocks.field_150350_a;
/*  32 */   private int blockMd = 0;
/*  33 */   private int delay = -1;
/*  34 */   private int maxDelay = 1;
/*  35 */   private int mod = 1;
/*     */   FakePlayer player;
/*  37 */   private int count = 0;
/*     */   
/*     */   public AIHarvestLogs(EntityGolemBase par1EntityCreature)
/*     */   {
/*  41 */     this.theGolem = par1EntityCreature;
/*  42 */     this.theWorld = par1EntityCreature.field_70170_p;
/*  43 */     func_75248_a(3);
/*  44 */     this.distance = MathHelper.func_76123_f(this.theGolem.getRange() / 3.0F);
/*  45 */     if ((this.theWorld instanceof WorldServer)) {
/*  46 */       this.player = FakePlayerFactory.get((WorldServer)this.theWorld, new GameProfile((java.util.UUID)null, "FakeThaumcraftGolem"));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  54 */     if ((this.delay >= 0) || (this.theGolem.field_70173_aa % Config.golemDelay > 0) || (!this.theGolem.func_70661_as().func_75500_f())) {
/*  55 */       return false;
/*     */     }
/*     */     
/*  58 */     Vec3 var1 = findLog();
/*     */     
/*  60 */     if (var1 == null)
/*     */     {
/*  62 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  66 */     this.xx = ((int)var1.field_72450_a);
/*  67 */     this.yy = ((int)var1.field_72448_b);
/*  68 */     this.zz = ((int)var1.field_72449_c);
/*     */     
/*  70 */     this.block = this.theWorld.func_147439_a(this.xx, this.yy, this.zz);
/*  71 */     this.blockMd = this.theWorld.func_72805_g(this.xx, this.yy, this.zz);
/*     */     
/*  73 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  82 */     return (this.theWorld.func_147439_a(this.xx, this.yy, this.zz) == this.block) && (this.theWorld.func_72805_g(this.xx, this.yy, this.zz) == this.blockMd) && (this.count-- > 0) && ((this.delay > 0) || (Utils.isWoodLog(this.theWorld, this.xx, this.yy, this.zz)) || (!this.theGolem.func_70661_as().func_75500_f()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75246_d()
/*     */   {
/*  91 */     double dist = this.theGolem.func_70092_e(this.xx + 0.5D, this.yy + 0.5D, this.zz + 0.5D);
/*  92 */     this.theGolem.func_70671_ap().func_75650_a(this.xx + 0.5D, this.yy + 0.5D, this.zz + 0.5D, 30.0F, 30.0F);
/*     */     
/*  94 */     if (dist <= 4.0D)
/*     */     {
/*  96 */       if (this.delay < 0) {
/*  97 */         this.delay = ((int)Math.max(5.0F, (20.0F - this.theGolem.getGolemStrength() * 3.0F) * this.block.func_149712_f(this.theWorld, this.xx, this.yy, this.zz)));
/*  98 */         this.maxDelay = this.delay;
/*  99 */         this.mod = (this.delay / Math.round(this.delay / 6.0F));
/*     */       }
/*     */       
/* 102 */       if (this.delay > 0) {
/* 103 */         if ((--this.delay > 0) && (this.delay % this.mod == 0) && (this.theGolem.func_70661_as().func_75500_f())) {
/* 104 */           this.theGolem.startActionTimer();
/* 105 */           this.theWorld.func_72908_a(this.xx + 0.5F, this.yy + 0.5F, this.zz + 0.5F, this.block.field_149762_H.func_150495_a(), (this.block.field_149762_H.func_150497_c() + 0.7F) / 8.0F, this.block.field_149762_H.func_150494_d() * 0.5F);
/*     */           
/* 107 */           BlockUtils.destroyBlockPartially(this.theWorld, this.theGolem.func_145782_y(), this.xx, this.yy, this.zz, (int)(9.0F * (1.0F - this.delay / this.maxDelay)));
/*     */         }
/*     */         
/*     */ 
/* 111 */         if (this.delay == 0) {
/* 112 */           harvest();
/*     */           
/* 114 */           if (Utils.isWoodLog(this.theWorld, this.xx, this.yy, this.zz)) {
/* 115 */             this.delay = -1;
/* 116 */             this.block = this.theWorld.func_147439_a(this.xx, this.yy, this.zz);
/* 117 */             this.blockMd = this.theWorld.func_72805_g(this.xx, this.yy, this.zz);
/* 118 */             func_75249_e();
/*     */           } else {
/* 120 */             checkAdjacent();
/*     */           }
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
/* 132 */     for (int x2 = -1; x2 <= 1; x2++) {
/* 133 */       for (int z2 = -1; z2 <= 1; z2++) {
/* 134 */         for (int y2 = -1; y2 <= 1; y2++) {
/* 135 */           int x = this.xx + x2;
/* 136 */           int y = this.yy + y2;
/* 137 */           int z = this.zz + z2;
/*     */           
/* 139 */           if ((Math.abs(this.theGolem.func_110172_bL().field_71574_a - x) <= this.distance) && (Math.abs(this.theGolem.func_110172_bL().field_71572_b - y) <= this.distance) && (Math.abs(this.theGolem.func_110172_bL().field_71573_c - z) <= this.distance))
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/* 144 */             if (Utils.isWoodLog(this.theWorld, x, y, z))
/*     */             {
/* 146 */               Vec3 var1 = Vec3.func_72443_a(x, y, z);
/*     */               
/* 148 */               if (var1 != null)
/*     */               {
/* 150 */                 this.xx = ((int)var1.field_72450_a);
/* 151 */                 this.yy = ((int)var1.field_72448_b);
/* 152 */                 this.zz = ((int)var1.field_72449_c);
/*     */                 
/* 154 */                 this.block = this.theWorld.func_147439_a(this.xx, this.yy, this.zz);
/* 155 */                 this.blockMd = this.theWorld.func_72805_g(this.xx, this.yy, this.zz);
/*     */                 
/* 157 */                 this.delay = -1;
/*     */                 
/* 159 */                 func_75249_e();
/* 160 */                 return;
/*     */               }
/*     */             } }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_75251_c() {
/* 169 */     BlockUtils.destroyBlockPartially(this.theWorld, this.theGolem.func_145782_y(), this.xx, this.yy, this.zz, -1);
/* 170 */     this.delay = -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/* 178 */     this.count = 200;
/* 179 */     this.theGolem.func_70661_as().func_75492_a(this.xx + 0.5D, this.yy + 0.5D, this.zz + 0.5D, this.theGolem.func_70689_ay());
/*     */   }
/*     */   
/*     */   void harvest() {
/* 183 */     this.count = 200;
/* 184 */     this.theWorld.func_72926_e(2001, this.xx, this.yy, this.zz, Block.func_149682_b(this.block) + (this.blockMd << 12));
/* 185 */     BlockUtils.breakFurthestBlock(this.theWorld, this.xx, this.yy, this.zz, this.block, this.player);
/* 186 */     this.theGolem.startActionTimer();
/*     */   }
/*     */   
/*     */ 
/*     */   private Vec3 findLog()
/*     */   {
/* 192 */     Random rand = this.theGolem.func_70681_au();
/*     */     
/* 194 */     for (int var2 = 0; var2 < this.distance * 4.0F; var2++)
/*     */     {
/* 196 */       int x = (int)(this.theGolem.func_110172_bL().field_71574_a + rand.nextInt((int)(1.0F + this.distance * 2.0F)) - this.distance);
/* 197 */       int y = (int)(this.theGolem.func_110172_bL().field_71572_b + rand.nextInt((int)(1.0F + this.distance)) - this.distance / 2.0F);
/* 198 */       int z = (int)(this.theGolem.func_110172_bL().field_71573_c + rand.nextInt((int)(1.0F + this.distance * 2.0F)) - this.distance);
/* 199 */       if (Utils.isWoodLog(this.theWorld, x, y, z)) {
/* 200 */         Vec3 v = Vec3.func_72443_a(x, y, z);
/* 201 */         double dist = this.theGolem.func_70092_e(x + 0.5D, y + 0.5D, z + 0.5D);
/* 202 */         int yy = 1;
/* 203 */         while ((Utils.isWoodLog(this.theWorld, x, y - yy, z)) && (this.theGolem.func_70092_e(x + 0.5D, y - yy + 0.5D, z + 0.5D) < dist)) {
/* 204 */           v = Vec3.func_72443_a(x, y - yy, z);
/* 205 */           dist = this.theGolem.func_70092_e(x + 0.5D, y - yy + 0.5D, z + 0.5D);
/* 206 */           yy++;
/*     */         }
/*     */         
/* 209 */         return v;
/*     */       }
/*     */     }
/* 212 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/interact/AIHarvestLogs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */