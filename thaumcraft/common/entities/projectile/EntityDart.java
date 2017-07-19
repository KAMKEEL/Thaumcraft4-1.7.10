/*    */ package thaumcraft.common.entities.projectile;
/*    */ 
/*    */ import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.IProjectile;
/*    */ import net.minecraft.entity.projectile.EntityArrow;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityDart extends EntityArrow implements IProjectile, IEntityAdditionalSpawnData
/*    */ {
/* 13 */   private int xTile = -1;
/* 14 */   private int yTile = -1;
/* 15 */   private int zTile = -1;
/* 16 */   private int inTile = 0;
/* 17 */   private int inData = 0;
/* 18 */   private boolean inGround = false;
/*    */   
/*    */   private int ticksInGround;
/* 21 */   private int ticksInAir = 0;
/* 22 */   private double damage = 1.0D;
/*    */   
/*    */   private int knockbackStrength;
/*    */   
/*    */   public void writeSpawnData(ByteBuf data)
/*    */   {
/* 28 */     data.writeDouble(this.field_70159_w);
/* 29 */     data.writeDouble(this.field_70181_x);
/* 30 */     data.writeDouble(this.field_70179_y);
/* 31 */     data.writeFloat(this.field_70177_z);
/* 32 */     data.writeFloat(this.field_70125_A);
/*    */   }
/*    */   
/*    */   public void readSpawnData(ByteBuf data)
/*    */   {
/* 37 */     this.field_70159_w = data.readDouble();
/* 38 */     this.field_70181_x = data.readDouble();
/* 39 */     this.field_70179_y = data.readDouble();
/* 40 */     this.field_70177_z = data.readFloat();
/* 41 */     this.field_70125_A = data.readFloat();
/*    */   }
/*    */   
/*    */   public EntityDart(World par1World)
/*    */   {
/* 46 */     super(par1World);
/* 47 */     this.field_70155_l = 10.0D;
/* 48 */     func_70105_a(0.5F, 0.5F);
/*    */   }
/*    */   
/*    */ 
/*    */   public EntityDart(World par1World, EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving, float par4, float par5)
/*    */   {
/* 54 */     super(par1World);
/* 55 */     this.field_70155_l = 10.0D;
/* 56 */     this.field_70250_c = par2EntityLiving;
/*    */     
/* 58 */     this.field_70163_u = (par2EntityLiving.field_70163_u + par2EntityLiving.func_70047_e() - 0.10000000149011612D);
/* 59 */     double var6 = par3EntityLiving.field_70165_t - par2EntityLiving.field_70165_t;
/* 60 */     double var8 = par3EntityLiving.field_70163_u + par3EntityLiving.func_70047_e() - 0.699999988079071D - this.field_70163_u;
/* 61 */     double var10 = par3EntityLiving.field_70161_v - par2EntityLiving.field_70161_v;
/* 62 */     double var12 = net.minecraft.util.MathHelper.func_76133_a(var6 * var6 + var10 * var10);
/*    */     
/* 64 */     if (var12 >= 1.0E-7D)
/*    */     {
/* 66 */       float var14 = (float)(Math.atan2(var10, var6) * 180.0D / 3.141592653589793D) - 90.0F;
/* 67 */       float var15 = (float)-(Math.atan2(var8, var12) * 180.0D / 3.141592653589793D);
/* 68 */       double var16 = var6 / var12;
/* 69 */       double var18 = var10 / var12;
/* 70 */       func_70012_b(par2EntityLiving.field_70165_t + var16 / 5.0D, this.field_70163_u, par2EntityLiving.field_70161_v + var18 / 5.0D, var14, var15);
/* 71 */       this.field_70129_M = 0.0F;
/* 72 */       float var20 = (float)var12 * 0.2F;
/* 73 */       func_70186_c(var6, var8 + var20, var10, par4, par5);
/*    */     }
/*    */   }
/*    */   
/* 77 */   private boolean first = true;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_70071_h_()
/*    */   {
/* 85 */     if ((this.first) && (this.field_70170_p.field_72995_K)) {
/* 86 */       this.first = false;
/* 87 */       for (int a = 0; a < 5; a++) {
/* 88 */         this.field_70170_p.func_72869_a("smoke", this.field_70165_t - this.field_70159_w / 1.5D, this.field_70163_u - this.field_70181_x / 1.5D, this.field_70161_v - this.field_70179_y / 1.5D, this.field_70159_w / 9.0D + this.field_70146_Z.nextGaussian() * 0.01D, this.field_70181_x / 9.0D + this.field_70146_Z.nextGaussian() * 0.01D, this.field_70179_y / 9.0D + this.field_70146_Z.nextGaussian() * 0.01D);
/*    */       }
/*    */     }
/*    */     
/* 92 */     super.func_70071_h_();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/projectile/EntityDart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */