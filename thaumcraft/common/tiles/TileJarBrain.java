/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityXPOrb;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileJarBrain
/*     */   extends TileJar
/*     */ {
/*     */   public float field_40063_b;
/*     */   public float field_40061_d;
/*     */   public float field_40059_f;
/*     */   public float field_40066_q;
/*     */   public float rota;
/*     */   public float rotb;
/*  23 */   public int xp = 0;
/*  24 */   public int xpMax = 2000;
/*  25 */   public int eatDelay = 0;
/*     */   
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  30 */     this.xp = nbttagcompound.func_74762_e("XP");
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  36 */     nbttagcompound.func_74768_a("XP", this.xp);
/*     */   }
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/*  41 */     return true;
/*     */   }
/*     */   
/*  44 */   long lastsigh = System.currentTimeMillis() + 1500L;
/*     */   
/*     */   public void func_145845_h()
/*     */   {
/*  48 */     super.func_145845_h();
/*  49 */     Entity entity = null;
/*  50 */     if (this.xp > this.xpMax) this.xp = this.xpMax;
/*  51 */     if (this.xp < this.xpMax) {
/*  52 */       entity = getClosestXPOrb();
/*     */       
/*  54 */       if ((entity != null) && (this.eatDelay == 0)) {
/*  55 */         double var3 = (this.field_145851_c + 0.5D - entity.field_70165_t) / 7.0D;
/*  56 */         double var5 = (this.field_145848_d + 0.5D - entity.field_70163_u) / 7.0D;
/*  57 */         double var7 = (this.field_145849_e + 0.5D - entity.field_70161_v) / 7.0D;
/*  58 */         double var9 = Math.sqrt(var3 * var3 + var5 * var5 + var7 * var7);
/*  59 */         double var11 = 1.0D - var9;
/*     */         
/*  61 */         if (var11 > 0.0D)
/*     */         {
/*  63 */           var11 *= var11;
/*  64 */           entity.field_70159_w += var3 / var9 * var11 * 0.15D;
/*  65 */           entity.field_70181_x += var5 / var9 * var11 * 0.33D;
/*  66 */           entity.field_70179_y += var7 / var9 * var11 * 0.15D;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  71 */     if (this.field_145850_b.field_72995_K)
/*     */     {
/*  73 */       this.rotb = this.rota;
/*  74 */       if (entity == null) {
/*  75 */         entity = this.field_145850_b.func_72977_a(this.field_145851_c + 0.5F, this.field_145848_d + 0.5F, this.field_145849_e + 0.5F, 6.0D);
/*  76 */         if ((entity != null) && (this.lastsigh < System.currentTimeMillis())) {
/*  77 */           this.field_145850_b.func_72980_b(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, "thaumcraft:brain", 0.15F, 0.8F + this.field_145850_b.field_73012_v.nextFloat() * 0.4F, false);
/*  78 */           this.lastsigh = (System.currentTimeMillis() + 5000L + this.field_145850_b.field_73012_v.nextInt(25000));
/*     */         }
/*     */       }
/*     */       
/*  82 */       if (entity != null)
/*     */       {
/*  84 */         double d = entity.field_70165_t - (this.field_145851_c + 0.5F);
/*  85 */         double d1 = entity.field_70161_v - (this.field_145849_e + 0.5F);
/*  86 */         this.field_40066_q = ((float)Math.atan2(d1, d));
/*  87 */         this.field_40059_f += 0.1F;
/*  88 */         if ((this.field_40059_f < 0.5F) || (rand.nextInt(40) == 0))
/*     */         {
/*  90 */           float f3 = this.field_40061_d;
/*     */           do
/*     */           {
/*  93 */             this.field_40061_d += rand.nextInt(4) - rand.nextInt(4);
/*     */           }
/*  95 */           while (f3 == this.field_40061_d);
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 100 */         this.field_40066_q += 0.01F;
/*     */       }
/* 102 */       while (this.rota >= 3.141593F) this.rota -= 6.283185F;
/* 103 */       while (this.rota < -3.141593F) this.rota += 6.283185F;
/* 104 */       while (this.field_40066_q >= 3.141593F) this.field_40066_q -= 6.283185F;
/* 105 */       while (this.field_40066_q < -3.141593F) { this.field_40066_q += 6.283185F;
/*     */       }
/* 107 */       for (float f = this.field_40066_q - this.rota; f >= 3.141593F; f -= 6.283185F) {}
/* 108 */       while (f < -3.141593F) f += 6.283185F;
/* 109 */       this.rota += f * 0.04F;
/*     */     }
/*     */     
/*     */ 
/* 113 */     if (this.eatDelay > 0) {
/* 114 */       this.eatDelay -= 1;
/* 115 */     } else if (this.xp < this.xpMax) {
/* 116 */       List ents = this.field_145850_b.func_72872_a(EntityXPOrb.class, AxisAlignedBB.func_72330_a(this.field_145851_c - 0.1D, this.field_145848_d - 0.1D, this.field_145849_e - 0.1D, this.field_145851_c + 1.1D, this.field_145848_d + 1.1D, this.field_145849_e + 1.1D));
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 122 */       if (ents.size() > 0) {
/* 123 */         for (Object ent : ents) {
/* 124 */           EntityXPOrb eo = (EntityXPOrb)ent;
/* 125 */           this.xp += eo.func_70526_d();
/* 126 */           this.field_145850_b.func_72956_a(eo, "random.eat", 0.1F, (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.2F + 1.0F);
/*     */           
/* 128 */           eo.func_70106_y();
/*     */         }
/* 130 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 131 */         func_70296_d();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Entity getClosestXPOrb()
/*     */   {
/* 140 */     double cdist = Double.MAX_VALUE;
/* 141 */     Entity orb = null;
/*     */     
/* 143 */     List ents = this.field_145850_b.func_72872_a(EntityXPOrb.class, AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + 1).func_72314_b(6.0D, 6.0D, 6.0D));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 151 */     if (ents.size() > 0) {
/* 152 */       for (Object ent : ents) {
/* 153 */         EntityXPOrb eo = (EntityXPOrb)ent;
/*     */         
/* 155 */         double d = getDistanceTo(eo.field_70165_t, eo.field_70163_u, eo.field_70161_v);
/* 156 */         if (d < cdist) {
/* 157 */           orb = eo;
/* 158 */           cdist = d;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 163 */     return orb;
/*     */   }
/*     */   
/*     */   public double getDistanceTo(double par1, double par3, double par5)
/*     */   {
/* 168 */     double var7 = this.field_145851_c + 0.5D - par1;
/* 169 */     double var9 = this.field_145848_d + 0.5D - par3;
/* 170 */     double var11 = this.field_145849_e + 0.5D - par5;
/* 171 */     return var7 * var7 + var9 * var9 + var11 * var11;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileJarBrain.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */