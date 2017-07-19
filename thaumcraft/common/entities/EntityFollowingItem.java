/*     */ package thaumcraft.common.entities;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ public class EntityFollowingItem extends EntitySpecialItem implements cpw.mods.fml.common.registry.IEntityAdditionalSpawnData
/*     */ {
/*  14 */   double targetX = 0.0D;
/*  15 */   double targetY = 0.0D;
/*  16 */   double targetZ = 0.0D;
/*  17 */   int type = 3;
/*  18 */   public Entity target = null;
/*  19 */   int field_70292_b = 20;
/*  20 */   public double gravity = 0.03999999910593033D;
/*     */   
/*     */   public EntityFollowingItem(World par1World, double par2, double par4, double par6, ItemStack par8ItemStack)
/*     */   {
/*  24 */     super(par1World);
/*  25 */     func_70105_a(0.25F, 0.25F);
/*  26 */     this.field_70129_M = (this.field_70131_O / 2.0F);
/*  27 */     func_70107_b(par2, par4, par6);
/*  28 */     func_92058_a(par8ItemStack);
/*  29 */     this.field_70177_z = ((float)(Math.random() * 360.0D));
/*     */   }
/*     */   
/*     */ 
/*     */   public EntityFollowingItem(World par1World, double par2, double par4, double par6, ItemStack par8ItemStack, Entity target, int t)
/*     */   {
/*  35 */     this(par1World, par2, par4, par6, par8ItemStack);
/*  36 */     this.target = target;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  41 */     this.field_70145_X = true;
/*     */   }
/*     */   
/*     */   public EntityFollowingItem(World par1World, double par2, double par4, double par6, ItemStack par8ItemStack, double tx, double ty, double tz)
/*     */   {
/*  46 */     this(par1World, par2, par4, par6, par8ItemStack);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public EntityFollowingItem(World par1World)
/*     */   {
/*  54 */     super(par1World);
/*  55 */     func_70105_a(0.25F, 0.25F);
/*  56 */     this.field_70129_M = (this.field_70131_O / 2.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/*  62 */     if (this.target != null) {
/*  63 */       this.targetX = this.target.field_70165_t;
/*  64 */       this.targetY = (this.target.field_70121_D.field_72338_b + this.target.field_70131_O / 2.0F);
/*  65 */       this.targetZ = this.target.field_70161_v;
/*     */     }
/*     */     
/*  68 */     if ((this.targetX != 0.0D) || (this.targetY != 0.0D) || (this.targetZ != 0.0D)) {
/*  69 */       float xd = (float)(this.targetX - this.field_70165_t);
/*  70 */       float yd = (float)(this.targetY - this.field_70163_u);
/*  71 */       float zd = (float)(this.targetZ - this.field_70161_v);
/*  72 */       if (this.field_70292_b > 1) { this.field_70292_b -= 1;
/*     */       }
/*  74 */       double distance = net.minecraft.util.MathHelper.func_76129_c(xd * xd + yd * yd + zd * zd);
/*     */       
/*  76 */       if (distance > 0.5D) {
/*  77 */         distance *= this.field_70292_b;
/*  78 */         this.field_70159_w = (xd / distance);
/*  79 */         this.field_70181_x = (yd / distance);
/*  80 */         this.field_70179_y = (zd / distance);
/*     */       } else {
/*  82 */         this.field_70159_w *= 0.10000000149011612D;
/*  83 */         this.field_70181_x *= 0.10000000149011612D;
/*  84 */         this.field_70179_y *= 0.10000000149011612D;
/*  85 */         this.targetX = 0.0D;
/*  86 */         this.targetY = 0.0D;
/*  87 */         this.targetZ = 0.0D;
/*  88 */         this.target = null;
/*  89 */         this.field_70145_X = false;
/*     */       }
/*  91 */       if (this.field_70170_p.field_72995_K) {
/*  92 */         if (this.type != 10) {
/*  93 */           Thaumcraft.proxy.sparkle((float)this.field_70169_q + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.125F, (float)this.field_70167_r + this.field_70129_M + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.125F, (float)this.field_70166_s + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.125F, this.type);
/*     */ 
/*     */         }
/*     */         else
/*     */         {
/*  98 */           Thaumcraft.proxy.crucibleBubble(this.field_70170_p, (float)this.field_70169_q + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.125F, (float)this.field_70167_r + this.field_70129_M + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.125F, (float)this.field_70166_s + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.125F, 0.33F, 0.33F, 1.0F);
/*     */         }
/*     */         
/*     */       }
/*     */       
/*     */     }
/*     */     else
/*     */     {
/* 106 */       this.field_70181_x -= this.gravity;
/*     */     }
/*     */     
/* 109 */     super.func_70071_h_();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 119 */     super.func_70014_b(par1NBTTagCompound);
/* 120 */     par1NBTTagCompound.func_74777_a("type", (short)this.type);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 128 */     super.func_70037_a(par1NBTTagCompound);
/* 129 */     this.type = par1NBTTagCompound.func_74765_d("type");
/*     */   }
/*     */   
/*     */   public void writeSpawnData(ByteBuf data)
/*     */   {
/* 134 */     if (this.target != null) {
/* 135 */       data.writeInt(this.target == null ? -1 : this.target.func_145782_y());
/* 136 */       data.writeDouble(this.targetX);
/* 137 */       data.writeDouble(this.targetY);
/* 138 */       data.writeDouble(this.targetZ);
/* 139 */       data.writeByte(this.type);
/*     */     }
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data)
/*     */   {
/*     */     try {
/* 146 */       int ent = data.readInt();
/* 147 */       if (ent > -1) {
/* 148 */         this.target = this.field_70170_p.func_73045_a(ent);
/*     */       }
/* 150 */       this.targetX = data.readDouble();
/* 151 */       this.targetY = data.readDouble();
/* 152 */       this.targetZ = data.readDouble();
/* 153 */       this.type = data.readByte();
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/EntityFollowingItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */