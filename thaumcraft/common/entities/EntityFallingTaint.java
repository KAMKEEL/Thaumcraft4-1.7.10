/*     */ package thaumcraft.common.entities;
/*     */ 
/*     */ import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.blocks.BlockTaint;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityFallingTaint
/*     */   extends Entity
/*     */   implements IEntityAdditionalSpawnData
/*     */ {
/*     */   public Block block;
/*     */   public int metadata;
/*     */   public int oldX;
/*     */   public int oldY;
/*     */   public int oldZ;
/*     */   public int fallTime;
/*     */   private int fallHurtMax;
/*     */   private float fallHurtAmount;
/*     */   
/*     */   public EntityFallingTaint(World par1World)
/*     */   {
/*  38 */     super(par1World);
/*  39 */     this.fallTime = 0;
/*  40 */     this.fallHurtMax = 40;
/*  41 */     this.fallHurtAmount = 2.0F;
/*     */   }
/*     */   
/*     */   public EntityFallingTaint(World par1World, double par2, double par4, double par6, Block par8, int par9, int ox, int oy, int oz)
/*     */   {
/*  46 */     super(par1World);
/*  47 */     this.fallTime = 0;
/*  48 */     this.fallHurtMax = 40;
/*  49 */     this.fallHurtAmount = 2.0F;
/*  50 */     this.block = par8;
/*  51 */     this.metadata = par9;
/*  52 */     this.field_70156_m = true;
/*  53 */     func_70105_a(0.98F, 0.98F);
/*  54 */     this.field_70129_M = (this.field_70131_O / 2.0F);
/*  55 */     func_70107_b(par2, par4, par6);
/*  56 */     this.field_70159_w = 0.0D;
/*  57 */     this.field_70181_x = 0.0D;
/*  58 */     this.field_70179_y = 0.0D;
/*  59 */     this.field_70169_q = par2;
/*  60 */     this.field_70167_r = par4;
/*  61 */     this.field_70166_s = par6;
/*  62 */     this.oldX = ox;
/*  63 */     this.oldY = oy;
/*  64 */     this.oldZ = oz;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean func_70041_e_()
/*     */   {
/*  74 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_70088_a() {}
/*     */   
/*     */ 
/*     */   public void writeSpawnData(ByteBuf data)
/*     */   {
/*  84 */     data.writeInt(Block.func_149682_b(this.block));
/*  85 */     data.writeByte(this.metadata);
/*     */   }
/*     */   
/*     */   public void readSpawnData(ByteBuf data)
/*     */   {
/*     */     try {
/*  91 */       this.block = Block.func_149729_e(data.readInt());
/*  92 */       this.metadata = data.readByte();
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_70067_L()
/*     */   {
/* 103 */     return !this.field_70128_L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 112 */     if ((this.block == null) || (this.block == Blocks.field_150350_a))
/*     */     {
/* 114 */       func_70106_y();
/*     */     }
/*     */     else
/*     */     {
/* 118 */       this.field_70169_q = this.field_70165_t;
/* 119 */       this.field_70167_r = this.field_70163_u;
/* 120 */       this.field_70166_s = this.field_70161_v;
/* 121 */       this.fallTime += 1;
/* 122 */       this.field_70181_x -= 0.03999999910593033D;
/* 123 */       func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/* 124 */       this.field_70159_w *= 0.9800000190734863D;
/* 125 */       this.field_70181_x *= 0.9800000190734863D;
/* 126 */       this.field_70179_y *= 0.9800000190734863D;
/*     */       
/* 128 */       if (!this.field_70170_p.field_72995_K)
/*     */       {
/* 130 */         int i = MathHelper.func_76128_c(this.field_70165_t);
/* 131 */         int j = MathHelper.func_76128_c(this.field_70163_u);
/* 132 */         int k = MathHelper.func_76128_c(this.field_70161_v);
/*     */         
/* 134 */         if (this.fallTime == 1)
/*     */         {
/*     */ 
/* 137 */           if (this.field_70170_p.func_147439_a(this.oldX, this.oldY, this.oldZ) != this.block)
/*     */           {
/* 139 */             func_70106_y();
/* 140 */             return;
/*     */           }
/*     */           
/* 143 */           this.field_70170_p.func_147468_f(this.oldX, this.oldY, this.oldZ);
/*     */         }
/*     */         
/* 146 */         if ((this.field_70122_E) || ((this.field_70170_p.func_147439_a(i, j - 1, k) == ConfigBlocks.blockFluxGoo) && (this.field_70170_p.func_72805_g(i, j - 1, k) >= 4)))
/*     */         {
/* 148 */           this.field_70159_w *= 0.699999988079071D;
/* 149 */           this.field_70179_y *= 0.699999988079071D;
/* 150 */           this.field_70181_x *= -0.5D;
/*     */           
/* 152 */           if ((this.field_70170_p.func_147439_a(i, j, k) != Blocks.field_150331_J) && (this.field_70170_p.func_147439_a(i, j, k) != Blocks.field_150326_M) && (this.field_70170_p.func_147439_a(i, j, k) != Blocks.field_150332_K))
/*     */           {
/*     */ 
/*     */ 
/* 156 */             this.field_70170_p.func_72956_a(this, "thaumcraft:gore", 0.5F, ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F) * 0.8F);
/* 157 */             func_70106_y();
/*     */             
/* 159 */             if ((canPlace(i, j, k)) && (!BlockTaint.canFallBelow(this.field_70170_p, i, j - 1, k)) && (this.field_70170_p.func_147465_d(i, j, k, this.block, this.metadata, 3)))
/*     */             {
/*     */ 
/*     */ 
/* 163 */               if ((this.block instanceof BlockTaint))
/*     */               {
/* 165 */                 ((BlockTaint)this.block).onFinishFalling(this.field_70170_p, i, j, k, this.metadata);
/*     */               }
/*     */               
/*     */             }
/*     */             
/*     */           }
/*     */           
/*     */         }
/* 173 */         else if (((this.fallTime > 100) && (!this.field_70170_p.field_72995_K) && ((j < 1) || (j > 256))) || (this.fallTime > 600))
/*     */         {
/*     */ 
/* 176 */           func_70106_y();
/*     */         }
/*     */       }
/* 179 */       else if ((this.field_70122_E) || (this.fallTime == 1))
/*     */       {
/* 181 */         for (int j = 0; j < 10; j++)
/*     */         {
/* 183 */           Thaumcraft.proxy.taintLandFX(this);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private boolean canPlace(int i, int j, int k)
/*     */   {
/* 192 */     return (this.field_70170_p.func_147439_a(i, j, k) == ConfigBlocks.blockTaintFibres) || (this.field_70170_p.func_147439_a(i, j, k) == ConfigBlocks.blockFluxGoo) || (this.field_70170_p.func_147472_a(this.block, i, j, k, true, 1, (Entity)null, (ItemStack)null));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70069_a(float par1) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 212 */     par1NBTTagCompound.func_74768_a("TileID", Block.func_149682_b(this.block));
/* 213 */     par1NBTTagCompound.func_74774_a("Data", (byte)this.metadata);
/* 214 */     par1NBTTagCompound.func_74774_a("Time", (byte)this.fallTime);
/* 215 */     par1NBTTagCompound.func_74776_a("FallHurtAmount", this.fallHurtAmount);
/* 216 */     par1NBTTagCompound.func_74768_a("FallHurtMax", this.fallHurtMax);
/* 217 */     par1NBTTagCompound.func_74768_a("OldX", this.oldX);
/* 218 */     par1NBTTagCompound.func_74768_a("OldY", this.oldY);
/* 219 */     par1NBTTagCompound.func_74768_a("OldZ", this.oldZ);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 228 */     if (par1NBTTagCompound.func_74764_b("TileID"))
/*     */     {
/* 230 */       this.block = Block.func_149729_e(par1NBTTagCompound.func_74762_e("TileID"));
/*     */     }
/*     */     
/* 233 */     this.metadata = (par1NBTTagCompound.func_74771_c("Data") & 0xFF);
/* 234 */     this.fallTime = (par1NBTTagCompound.func_74771_c("Time") & 0xFF);
/* 235 */     this.oldX = par1NBTTagCompound.func_74762_e("OldX");
/* 236 */     this.oldY = par1NBTTagCompound.func_74762_e("OldY");
/* 237 */     this.oldZ = par1NBTTagCompound.func_74762_e("OldZ");
/*     */     
/* 239 */     if (par1NBTTagCompound.func_74764_b("HurtEntities"))
/*     */     {
/* 241 */       this.fallHurtAmount = par1NBTTagCompound.func_74760_g("FallHurtAmount");
/* 242 */       this.fallHurtMax = par1NBTTagCompound.func_74762_e("FallHurtMax");
/*     */     }
/*     */     
/*     */ 
/* 246 */     if (this.block == null)
/*     */     {
/* 248 */       this.block = Blocks.field_150354_m;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_85029_a(CrashReportCategory par1CrashReportCategory)
/*     */   {
/* 255 */     super.func_85029_a(par1CrashReportCategory);
/* 256 */     par1CrashReportCategory.func_71507_a("Immitating block ID", Integer.valueOf(Block.func_149682_b(this.block)));
/* 257 */     par1CrashReportCategory.func_71507_a("Immitating block data", Integer.valueOf(this.metadata));
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float func_70053_R()
/*     */   {
/* 264 */     return 0.0F;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public World getWorld()
/*     */   {
/* 270 */     return this.field_70170_p;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_90999_ad()
/*     */   {
/* 281 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/EntityFallingTaint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */