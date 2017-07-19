/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.monster.EntityEldritchCrab;
/*     */ 
/*     */ public class TileEldritchCrabSpawner extends TileThaumcraft
/*     */ {
/*     */   public boolean canUpdate()
/*     */   {
/*  20 */     return true;
/*     */   }
/*     */   
/*  23 */   public int count = 150;
/*  24 */   public int ticks = 0;
/*     */   
/*     */   public void func_145845_h()
/*     */   {
/*  28 */     super.func_145845_h();
/*  29 */     if (this.ticks == 0) this.ticks = this.field_145850_b.field_73012_v.nextInt(500);
/*  30 */     this.ticks += 1;
/*  31 */     if (!this.field_145850_b.field_72995_K) {
/*  32 */       this.count -= 1;
/*  33 */       if (this.count < 0) {
/*  34 */         this.count = (50 + this.field_145850_b.field_73012_v.nextInt(50));
/*     */       } else {
/*  36 */         if ((this.count == 15) && (isActivated()) && (!maxEntitiesReached())) {
/*  37 */           this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, func_145838_q(), 1, 0);
/*  38 */           this.field_145850_b.func_72908_a(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, "random.fizz", 0.5F, 1.0F);
/*     */         }
/*  40 */         if ((this.count <= 0) && (isActivated()) && (!maxEntitiesReached())) {
/*  41 */           this.count = (150 + this.field_145850_b.field_73012_v.nextInt(100));
/*  42 */           spawnCrab();
/*  43 */           this.field_145850_b.func_72908_a(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, "thaumcraft:gore", 0.5F, 1.0F);
/*     */         }
/*     */       }
/*     */     }
/*  47 */     else if (this.venting > 0) {
/*  48 */       this.venting -= 1;
/*  49 */       for (int a = 0; a < 3; a++) {
/*  50 */         drawVent();
/*     */       }
/*     */     }
/*  53 */     else if (this.field_145850_b.field_73012_v.nextInt(20) == 0) {
/*  54 */       drawVent();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   void drawVent()
/*     */   {
/*  61 */     ForgeDirection dir = ForgeDirection.getOrientation(this.facing);
/*  62 */     float fx = 0.15F - this.field_145850_b.field_73012_v.nextFloat() * 0.3F;
/*  63 */     float fz = 0.15F - this.field_145850_b.field_73012_v.nextFloat() * 0.3F;
/*  64 */     float fy = 0.15F - this.field_145850_b.field_73012_v.nextFloat() * 0.3F;
/*  65 */     float fx2 = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/*  66 */     float fz2 = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/*  67 */     float fy2 = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/*  68 */     Thaumcraft.proxy.drawVentParticles(this.field_145850_b, this.field_145851_c + 0.5F + fx + dir.offsetX / 2.1F, this.field_145848_d + 0.5F + fy + dir.offsetY / 2.1F, this.field_145849_e + 0.5F + fz + dir.offsetZ / 2.1F, dir.offsetX / 3.0F + fx2, dir.offsetY / 3.0F + fy2, dir.offsetZ / 3.0F + fz2, 10061994, 2.0F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  77 */   int venting = 0;
/*     */   
/*     */ 
/*     */   public boolean func_145842_c(int i, int j)
/*     */   {
/*  82 */     if (i == 1)
/*     */     {
/*  84 */       this.venting = 20;
/*  85 */       return true;
/*     */     }
/*     */     
/*  88 */     return super.func_145842_c(i, j);
/*     */   }
/*     */   
/*     */   private boolean maxEntitiesReached()
/*     */   {
/*  93 */     List ents = this.field_145850_b.func_72872_a(EntityEldritchCrab.class, AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c + 1.0D, this.field_145848_d + 1.0D, this.field_145849_e + 1.0D).func_72314_b(32.0D, 32.0D, 32.0D));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  98 */     return ents.size() > 5;
/*     */   }
/*     */   
/*     */   public boolean isActivated()
/*     */   {
/* 103 */     return this.field_145850_b.func_72977_a(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, 16.0D) != null;
/*     */   }
/*     */   
/*     */   private void spawnCrab() {
/* 107 */     ForgeDirection dir = ForgeDirection.getOrientation(this.facing);
/* 108 */     EntityEldritchCrab crab = new EntityEldritchCrab(this.field_145850_b);
/* 109 */     double x = this.field_145851_c + dir.offsetX;
/* 110 */     double y = this.field_145848_d + dir.offsetY;
/* 111 */     double z = this.field_145849_e + dir.offsetZ;
/* 112 */     crab.func_70012_b(x + 0.5D, y + 0.5D, z + 0.5D, 0.0F, 0.0F);
/* 113 */     crab.func_110161_a((IEntityLivingData)null);
/* 114 */     crab.setHelm(false);
/* 115 */     crab.field_70159_w = (dir.offsetX * 0.2F);
/* 116 */     crab.field_70181_x = (dir.offsetY * 0.2F);
/* 117 */     crab.field_70179_y = (dir.offsetZ * 0.2F);
/* 118 */     this.field_145850_b.func_72838_d(crab);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox()
/*     */   {
/* 126 */     return AxisAlignedBB.func_72330_a(this.field_145851_c - 1, this.field_145848_d - 1, this.field_145849_e - 1, this.field_145851_c + 2, this.field_145848_d + 2, this.field_145849_e + 2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 131 */   byte facing = 0;
/*     */   
/*     */   public byte getFacing() {
/* 134 */     return this.facing;
/*     */   }
/*     */   
/*     */   public void setFacing(byte face) {
/* 138 */     this.facing = face;
/* 139 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 140 */     func_70296_d();
/*     */   }
/*     */   
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 145 */     this.facing = nbttagcompound.func_74771_c("facing");
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 150 */     nbttagcompound.func_74774_a("facing", this.facing);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileEldritchCrabSpawner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */