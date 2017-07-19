/*     */ package thaumcraft.common.entities.monster.boss;
/*     */ 
/*     */ import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.boss.IBossDisplayData;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.entities.monster.EntityCultist;
/*     */ import thaumcraft.common.entities.monster.EntityCultistKnight;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXBlockArc;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import thaumcraft.common.tiles.TileBanner;
/*     */ 
/*     */ public class EntityCultistPortal extends EntityMob implements IBossDisplayData
/*     */ {
/*     */   public EntityCultistPortal(World par1World)
/*     */   {
/*  37 */     super(par1World);
/*  38 */     this.field_70178_ae = true;
/*  39 */     this.field_70728_aV = 30;
/*  40 */     func_70105_a(1.5F, 3.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_70658_aO()
/*     */   {
/*  46 */     return 5;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  52 */     super.func_70088_a();
/*     */   }
/*     */   
/*  55 */   int stage = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound nbt)
/*     */   {
/*  63 */     super.func_70014_b(nbt);
/*  64 */     nbt.func_74768_a("stage", this.stage);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound nbt)
/*     */   {
/*  74 */     super.func_70037_a(nbt);
/*  75 */     this.stage = nbt.func_74762_e("stage");
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  81 */     super.func_110147_ax();
/*  82 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(500.0D);
/*  83 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(0.0D);
/*  84 */     func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   public float func_70053_R()
/*     */   {
/*  90 */     return 0.0F;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70067_L()
/*     */   {
/*  96 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_70104_M()
/*     */   {
/* 101 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70091_d(double par1, double par3, double par5) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70626_be() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_70112_a(double par1)
/*     */   {
/* 118 */     return par1 < 4096.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_70070_b(float par1)
/*     */   {
/* 126 */     return 15728880;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float func_70013_c(float par1)
/*     */   {
/* 135 */     return 1.0F;
/*     */   }
/*     */   
/* 138 */   int stagecounter = 200;
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/* 142 */     super.func_70071_h_();
/* 143 */     if (!this.field_70170_p.field_72995_K) {
/* 144 */       if (this.stagecounter > 0) {
/* 145 */         this.stagecounter -= 1;
/* 146 */         if ((this.stagecounter == 160) && (this.stage == 0)) {
/* 147 */           this.field_70170_p.func_72960_a(this, (byte)16);
/* 148 */           for (int a = 2; a < 6; a++) {
/* 149 */             ForgeDirection dir = ForgeDirection.getOrientation(a);
/* 150 */             this.field_70170_p.func_147465_d((int)this.field_70165_t - dir.offsetX * 6, (int)this.field_70163_u, (int)this.field_70161_v + dir.offsetZ * 6, ConfigBlocks.blockWoodenDevice, 8, 3);
/* 151 */             TileEntity te = this.field_70170_p.func_147438_o((int)this.field_70165_t - dir.offsetX * 6, (int)this.field_70163_u, (int)this.field_70161_v + dir.offsetZ * 6);
/* 152 */             if ((te != null) && ((te instanceof TileBanner))) {
/* 153 */               int face = 0;
/*     */               
/* 155 */               switch (a) {
/* 156 */               case 2:  face = 8; break;
/* 157 */               case 3:  face = 0; break;
/* 158 */               case 4:  face = 12; break;
/* 159 */               case 5:  face = 4; break;
/*     */               }
/* 161 */               ((TileBanner)te).setFacing((byte)face);
/* 162 */               PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockArc((int)this.field_70165_t - dir.offsetX * 6, (int)this.field_70163_u, (int)this.field_70161_v + dir.offsetZ * 6, func_145782_y()), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.field_76574_g, this.field_70165_t, this.field_70163_u, this.field_70161_v, 32.0D));
/*     */               
/*     */ 
/* 165 */               func_85030_a("thaumcraft:wandfail", 1.0F, 1.0F);
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 170 */         if ((this.stagecounter > 20) && (this.stagecounter < 150) && (this.stage == 0) && (this.stagecounter % 13 == 0)) {
/* 171 */           int a = (int)this.field_70165_t + this.field_70146_Z.nextInt(5) - this.field_70146_Z.nextInt(5);
/* 172 */           int b = (int)this.field_70161_v + this.field_70146_Z.nextInt(5) - this.field_70146_Z.nextInt(5);
/* 173 */           if ((a != (int)this.field_70165_t) && (b != (int)this.field_70161_v) && (this.field_70170_p.func_147437_c(a, (int)this.field_70163_u, b))) {
/* 174 */             this.field_70170_p.func_72960_a(this, (byte)16);
/* 175 */             float rr = this.field_70170_p.field_73012_v.nextFloat();
/* 176 */             int md = rr < 0.2F ? 1 : rr < 0.05F ? 2 : 0;
/* 177 */             this.field_70170_p.func_147465_d(a, (int)this.field_70163_u, b, ConfigBlocks.blockLootCrate, md, 3);
/* 178 */             PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockArc(a, (int)this.field_70163_u, b, func_145782_y()), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.field_76574_g, this.field_70165_t, this.field_70163_u, this.field_70161_v, 32.0D));
/*     */             
/*     */ 
/* 181 */             func_85030_a("thaumcraft:wandfail", 1.0F, 1.0F);
/*     */           }
/*     */         }
/*     */       }
/* 185 */       else if (this.field_70170_p.func_72890_a(this, 48.0D) != null)
/*     */       {
/* 187 */         this.field_70170_p.func_72960_a(this, (byte)16);
/*     */         
/* 189 */         switch (this.stage) {
/*     */         case 0: case 1: case 2: case 3: case 4: 
/* 191 */           this.stagecounter = (15 + this.field_70146_Z.nextInt(10 - this.stage) - this.stage);
/* 192 */           spawnMinions();
/* 193 */           break;
/*     */         case 12: 
/* 195 */           this.stagecounter = (50 + getTiming() * 2 + this.field_70146_Z.nextInt(50));
/* 196 */           spawnBoss();
/* 197 */           break;
/*     */         case 5: case 6: case 7: case 8: case 9: case 10: case 11: default: 
/* 199 */           int t = getTiming();
/* 200 */           this.stagecounter = (t + this.field_70146_Z.nextInt(5 + t / 3));
/* 201 */           spawnMinions();
/*     */         }
/*     */         
/*     */         
/* 205 */         this.stage += 1;
/*     */       }
/*     */       else {
/* 208 */         this.stagecounter = (30 + this.field_70146_Z.nextInt(30));
/*     */       }
/*     */       
/* 211 */       if (this.stage < 12) { func_70691_i(1.0F);
/*     */       }
/*     */     }
/*     */     
/* 215 */     if (this.pulse > 0) this.pulse -= 1;
/*     */   }
/*     */   
/*     */   int getTiming() {
/* 219 */     List<Entity> l = EntityUtils.getEntitiesInRange(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this, EntityCultist.class, 32.0D);
/* 220 */     return l.size() * 20;
/*     */   }
/*     */   
/*     */   void spawnMinions() {
/* 224 */     EntityCultist cultist = null;
/* 225 */     if (this.field_70146_Z.nextFloat() > 0.33D) {
/* 226 */       cultist = new EntityCultistKnight(this.field_70170_p);
/*     */     } else {
/* 228 */       cultist = new thaumcraft.common.entities.monster.EntityCultistCleric(this.field_70170_p);
/*     */     }
/* 230 */     cultist.func_70107_b(this.field_70165_t + this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat(), this.field_70163_u + 0.25D, this.field_70161_v + this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 235 */     cultist.func_110161_a((IEntityLivingData)null);
/* 236 */     cultist.func_70656_aK();
/* 237 */     cultist.func_110171_b((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 32);
/* 238 */     this.field_70170_p.func_72838_d(cultist);
/* 239 */     cultist.func_85030_a("thaumcraft:wandfail", 1.0F, 1.0F);
/*     */     
/* 241 */     if (this.stage > 12) {
/* 242 */       func_70097_a(DamageSource.field_76380_i, 5 + this.field_70146_Z.nextInt(5));
/*     */     }
/*     */   }
/*     */   
/*     */   void spawnBoss() {
/* 247 */     EntityCultistLeader cultist = new EntityCultistLeader(this.field_70170_p);
/* 248 */     cultist.func_70107_b(this.field_70165_t + this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat(), this.field_70163_u + 0.25D, this.field_70161_v + this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat());
/*     */     
/*     */ 
/*     */ 
/* 252 */     cultist.func_110161_a((IEntityLivingData)null);
/* 253 */     cultist.func_110171_b((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 32);
/* 254 */     cultist.func_70656_aK();
/* 255 */     this.field_70170_p.func_72838_d(cultist);
/* 256 */     cultist.func_85030_a("thaumcraft:wandfail", 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public void func_70100_b_(EntityPlayer p)
/*     */   {
/* 261 */     if ((func_70068_e(p) < 3.0D) && (p.func_70097_a(DamageSource.func_76354_b(this, this), 8.0F)))
/*     */     {
/* 263 */       func_85030_a("thaumcraft:zap", 1.0F, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F + 1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected float func_70599_aP()
/*     */   {
/* 270 */     return 0.75F;
/*     */   }
/*     */   
/*     */   public int func_70627_aG()
/*     */   {
/* 275 */     return 540;
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 280 */     return "thaumcraft:monolith";
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_70621_aR()
/*     */   {
/* 286 */     return "thaumcraft:zap";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 291 */     return "thaumcraft:shock";
/*     */   }
/*     */   
/*     */ 
/*     */   protected Item func_146068_u()
/*     */   {
/* 297 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70628_a(boolean flag, int fortune)
/*     */   {
/* 303 */     EntityUtils.entityDropSpecialItem(this, new ItemStack(thaumcraft.common.config.ConfigItems.itemEldritchObject, 1, 3), this.field_70131_O / 2.0F);
/*     */   }
/*     */   
/* 306 */   public int pulse = 0;
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte msg)
/*     */   {
/* 312 */     if (msg == 16)
/*     */     {
/* 314 */       this.pulse = 10;
/* 315 */       func_70656_aK();
/*     */     }
/*     */     else
/*     */     {
/* 319 */       super.func_70103_a(msg);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70690_d(PotionEffect p_70690_1_) {}
/*     */   
/*     */ 
/*     */   protected void func_70069_a(float p_70069_1_) {}
/*     */   
/*     */ 
/*     */   public void func_70645_a(DamageSource p_70645_1_)
/*     */   {
/* 332 */     if (!this.field_70170_p.field_72995_K) {
/* 333 */       this.field_70170_p.func_72885_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 2.0F, false, false);
/*     */     }
/*     */     
/* 336 */     super.func_70645_a(p_70645_1_);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/boss/EntityCultistPortal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */