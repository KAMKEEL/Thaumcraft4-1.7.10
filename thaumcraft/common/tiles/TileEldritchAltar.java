/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.common.entities.monster.EntityCultist;
/*     */ import thaumcraft.common.entities.monster.EntityCultistCleric;
/*     */ import thaumcraft.common.entities.monster.EntityCultistKnight;
/*     */ import thaumcraft.common.entities.monster.EntityEldritchGuardian;
/*     */ import thaumcraft.common.lib.world.dim.MazeHandler;
/*     */ import thaumcraft.common.lib.world.dim.MazeThread;
/*     */ 
/*     */ 
/*     */ public class TileEldritchAltar
/*     */   extends TileThaumcraft
/*     */ {
/*  24 */   private boolean spawner = false;
/*  25 */   private boolean open = false;
/*  26 */   private boolean spawnedClerics = false;
/*  27 */   private byte spawnType = 0;
/*     */   
/*  29 */   private byte eyes = 0;
/*     */   
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  33 */     setEyes(nbttagcompound.func_74771_c("eyes"));
/*  34 */     setOpen(nbttagcompound.func_74767_n("open"));
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  39 */     nbttagcompound.func_74774_a("eyes", getEyes());
/*  40 */     nbttagcompound.func_74757_a("open", isOpen());
/*     */   }
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbttagcompound)
/*     */   {
/*  45 */     super.func_145839_a(nbttagcompound);
/*  46 */     this.spawnedClerics = nbttagcompound.func_74767_n("spawnedClerics");
/*  47 */     this.spawner = nbttagcompound.func_74767_n("spawner");
/*  48 */     this.spawnType = nbttagcompound.func_74771_c("spawntype");
/*     */   }
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbttagcompound)
/*     */   {
/*  53 */     super.func_145841_b(nbttagcompound);
/*  54 */     nbttagcompound.func_74757_a("spawnedClerics", this.spawnedClerics);
/*  55 */     nbttagcompound.func_74757_a("spawner", this.spawner);
/*  56 */     nbttagcompound.func_74774_a("spawntype", this.spawnType);
/*     */   }
/*     */   
/*     */   public double func_145833_n()
/*     */   {
/*  61 */     return 9216.0D;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox()
/*     */   {
/*  67 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + 1);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isSpawner()
/*     */   {
/*  73 */     return this.spawner;
/*     */   }
/*     */   
/*     */   public void setSpawner(boolean spawner) {
/*  77 */     this.spawner = spawner;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean canUpdate()
/*     */   {
/*  84 */     return true;
/*     */   }
/*     */   
/*  87 */   private int counter = 0;
/*     */   
/*     */ 
/*     */   public void func_145845_h()
/*     */   {
/*  92 */     if ((!this.field_145850_b.field_72995_K) && (isSpawner()) && (this.counter++ >= 80) && (this.counter % 40 == 0))
/*     */     {
/*  94 */       switch (this.spawnType) {
/*     */       case 0: 
/*  96 */         if (!this.spawnedClerics) {
/*  97 */           spawnClerics();
/*     */         } else {
/*  99 */           spawnGuards();
/*     */         }
/* 101 */         break;
/*     */       case 1: 
/* 103 */         spawnGuardian();
/*     */       }
/*     */       
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void spawnGuards()
/*     */   {
/* 115 */     List ents = this.field_145850_b.func_72872_a(EntityCultistCleric.class, AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + 1).func_72314_b(24.0D, 16.0D, 24.0D));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 121 */     if (ents.size() < 1) {
/* 122 */       setSpawner(false);
/* 123 */       return;
/*     */     }
/*     */     
/* 126 */     ents = this.field_145850_b.func_72872_a(EntityCultist.class, AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + 1).func_72314_b(24.0D, 16.0D, 24.0D));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 131 */     if (ents.size() < 8) {
/* 132 */       EntityCultistKnight eg = new EntityCultistKnight(this.field_145850_b);
/* 133 */       int i1 = this.field_145851_c + MathHelper.func_76136_a(this.field_145850_b.field_73012_v, 4, 10) * MathHelper.func_76136_a(this.field_145850_b.field_73012_v, -1, 1);
/* 134 */       int j1 = this.field_145848_d + MathHelper.func_76136_a(this.field_145850_b.field_73012_v, 0, 3) * MathHelper.func_76136_a(this.field_145850_b.field_73012_v, -1, 1);
/* 135 */       int k1 = this.field_145849_e + MathHelper.func_76136_a(this.field_145850_b.field_73012_v, 4, 10) * MathHelper.func_76136_a(this.field_145850_b.field_73012_v, -1, 1);
/*     */       
/* 137 */       if (World.func_147466_a(this.field_145850_b, i1, j1 - 1, k1))
/*     */       {
/* 139 */         eg.func_70107_b(i1, j1, k1);
/*     */         
/* 141 */         if ((this.field_145850_b.func_72855_b(eg.field_70121_D)) && (this.field_145850_b.func_72945_a(eg, eg.field_70121_D).isEmpty()) && (!this.field_145850_b.func_72953_d(eg.field_70121_D)))
/*     */         {
/*     */ 
/*     */ 
/* 145 */           eg.func_110161_a((IEntityLivingData)null);
/* 146 */           eg.func_70656_aK();
/* 147 */           eg.func_110171_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, 16);
/* 148 */           this.field_145850_b.func_72838_d(eg);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void spawnGuardian()
/*     */   {
/* 156 */     EntityEldritchGuardian eg = new EntityEldritchGuardian(this.field_145850_b);
/* 157 */     int i1 = this.field_145851_c + MathHelper.func_76136_a(this.field_145850_b.field_73012_v, 4, 10) * MathHelper.func_76136_a(this.field_145850_b.field_73012_v, -1, 1);
/* 158 */     int j1 = this.field_145848_d + MathHelper.func_76136_a(this.field_145850_b.field_73012_v, 0, 3) * MathHelper.func_76136_a(this.field_145850_b.field_73012_v, -1, 1);
/* 159 */     int k1 = this.field_145849_e + MathHelper.func_76136_a(this.field_145850_b.field_73012_v, 4, 10) * MathHelper.func_76136_a(this.field_145850_b.field_73012_v, -1, 1);
/*     */     
/* 161 */     if (World.func_147466_a(this.field_145850_b, i1, j1 - 1, k1))
/*     */     {
/* 163 */       eg.func_70107_b(i1, j1, k1);
/*     */       
/* 165 */       if (eg.func_70601_bi())
/*     */       {
/* 167 */         eg.func_110161_a((IEntityLivingData)null);
/* 168 */         eg.func_70656_aK();
/* 169 */         eg.func_110171_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, 16);
/* 170 */         this.field_145850_b.func_72838_d(eg);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void spawnClerics()
/*     */   {
/* 177 */     int success = 0;
/* 178 */     for (int a = 0; a < 4; a++) {
/* 179 */       int xx = 0;
/* 180 */       int zz = 0;
/* 181 */       switch (a) {
/* 182 */       case 0:  xx = -2;zz = -2; break;
/* 183 */       case 1:  xx = -2;zz = 2; break;
/* 184 */       case 2:  xx = 2;zz = -2; break;
/* 185 */       case 3:  xx = 2;zz = 2;
/*     */       }
/* 187 */       EntityCultistCleric cleric = new EntityCultistCleric(this.field_145850_b);
/* 188 */       if (World.func_147466_a(this.field_145850_b, this.field_145851_c + xx, this.field_145848_d - 1, this.field_145849_e + zz))
/*     */       {
/* 190 */         cleric.func_70107_b(this.field_145851_c + 0.5D + xx, this.field_145848_d, this.field_145849_e + 0.5D + zz);
/*     */         
/* 192 */         if ((this.field_145850_b.func_72855_b(cleric.field_70121_D)) && (this.field_145850_b.func_72945_a(cleric, cleric.field_70121_D).isEmpty()) && (!this.field_145850_b.func_72953_d(cleric.field_70121_D)))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/* 197 */           cleric.func_110171_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, 8);
/* 198 */           cleric.func_110161_a((IEntityLivingData)null);
/* 199 */           cleric.func_70656_aK();
/* 200 */           if (this.field_145850_b.func_72838_d(cleric)) {
/* 201 */             success++;
/* 202 */             cleric.setIsRitualist(true);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 207 */     if (success > 2) {
/* 208 */       this.spawnedClerics = true;
/* 209 */       func_70296_d();
/*     */     }
/*     */   }
/*     */   
/*     */   public byte getSpawnType()
/*     */   {
/* 215 */     return this.spawnType;
/*     */   }
/*     */   
/*     */   public void setSpawnType(byte spawnType) {
/* 219 */     this.spawnType = spawnType;
/*     */   }
/*     */   
/*     */   public byte getEyes() {
/* 223 */     return this.eyes;
/*     */   }
/*     */   
/*     */   public void setEyes(byte eyes) {
/* 227 */     this.eyes = eyes;
/*     */   }
/*     */   
/*     */   public boolean isOpen() {
/* 231 */     return this.open;
/*     */   }
/*     */   
/*     */   public void setOpen(boolean open) {
/* 235 */     this.open = open;
/*     */   }
/*     */   
/*     */   public boolean checkForMaze() {
/* 239 */     int w = 15 + this.field_145850_b.field_73012_v.nextInt(8) * 2;
/* 240 */     int h = 15 + this.field_145850_b.field_73012_v.nextInt(8) * 2;
/* 241 */     if (!MazeHandler.mazesInRange(this.field_145851_c >> 4, this.field_145849_e >> 4, w, h)) {
/* 242 */       Thread t = new Thread(new MazeThread(this.field_145851_c >> 4, this.field_145849_e >> 4, w, h, this.field_145850_b.field_73012_v.nextLong()));
/* 243 */       t.start();
/* 244 */       return false;
/*     */     }
/* 246 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileEldritchAltar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */