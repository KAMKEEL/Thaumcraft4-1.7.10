/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.codechicken.lib.raytracer.RayTracer;
/*     */ 
/*     */ public class TileTubeValve extends TileTube
/*     */ {
/*  16 */   public boolean allowFlow = true;
/*  17 */   boolean wasPoweredLastTick = false;
/*     */   
/*  19 */   public float rotation = 0.0F;
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_145845_h()
/*     */   {
/*  25 */     if ((!this.field_145850_b.field_72995_K) && (this.count % 5 == 0)) {
/*  26 */       boolean gettingPower = gettingPower();
/*  27 */       if ((this.wasPoweredLastTick) && (!gettingPower) && 
/*  28 */         (this.allowFlow != true)) {
/*  29 */         this.allowFlow = true;
/*  30 */         this.field_145850_b.func_72908_a(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, "thaumcraft:squeek", 0.7F, 0.9F + this.field_145850_b.field_73012_v.nextFloat() * 0.2F);
/*  31 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  32 */         func_70296_d();
/*     */       }
/*     */       
/*     */ 
/*  36 */       if ((!this.wasPoweredLastTick) && (gettingPower) && 
/*  37 */         (this.allowFlow)) {
/*  38 */         this.allowFlow = false;
/*  39 */         this.field_145850_b.func_72908_a(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, "thaumcraft:squeek", 0.7F, 0.9F + this.field_145850_b.field_73012_v.nextFloat() * 0.2F);
/*  40 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  41 */         func_70296_d();
/*     */       }
/*     */       
/*     */ 
/*  45 */       this.wasPoweredLastTick = gettingPower;
/*     */     }
/*     */     
/*  48 */     if (this.field_145850_b.field_72995_K) {
/*  49 */       if ((!this.allowFlow) && (this.rotation < 360.0F)) {
/*  50 */         this.rotation += 20.0F;
/*     */       }
/*  52 */       else if ((this.allowFlow) && (this.rotation > 0.0F)) {
/*  53 */         this.rotation -= 20.0F;
/*     */       }
/*     */     }
/*     */     
/*  57 */     super.func_145845_h();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md)
/*     */   {
/*  64 */     MovingObjectPosition hit = RayTracer.retraceBlock(world, player, x, y, z);
/*  65 */     if (hit == null) { return 0;
/*     */     }
/*  67 */     if ((hit.subHit >= 0) && (hit.subHit < 6))
/*     */     {
/*  69 */       player.field_70170_p.func_72980_b(x + 0.5D, y + 0.5D, z + 0.5D, "thaumcraft:tool", 0.5F, 0.9F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F, false);
/*  70 */       player.func_71038_i();
/*  71 */       func_70296_d();
/*  72 */       world.func_147471_g(x, y, z);
/*  73 */       this.openSides[hit.subHit] = (this.openSides[hit.subHit] == 0 ? 1 : false);
/*  74 */       ForgeDirection dir = ForgeDirection.getOrientation(hit.subHit);
/*  75 */       TileEntity tile = this.field_145850_b.func_147438_o(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ);
/*  76 */       if ((tile != null) && ((tile instanceof TileTube))) {
/*  77 */         ((TileTube)tile).openSides[dir.getOpposite().ordinal()] = this.openSides[hit.subHit];
/*  78 */         world.func_147471_g(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ);
/*  79 */         tile.func_70296_d();
/*     */       }
/*     */     }
/*     */     
/*  83 */     if (hit.subHit == 6)
/*     */     {
/*  85 */       player.field_70170_p.func_72980_b(x + 0.5D, y + 0.5D, z + 0.5D, "thaumcraft:tool", 0.5F, 0.9F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F, false);
/*  86 */       player.func_71038_i();
/*  87 */       int a = this.facing.ordinal();
/*  88 */       func_70296_d();
/*  89 */       do { a++; if (a >= 20) break;
/*  90 */       } while (canConnectSide(ForgeDirection.getOrientation(a % 6).ordinal()));
/*  91 */       a %= 6;
/*  92 */       this.facing = ForgeDirection.getOrientation(a);
/*  93 */       world.func_147471_g(x, y, z);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 101 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 107 */     super.readCustomNBT(nbttagcompound);
/*     */     
/* 109 */     this.allowFlow = nbttagcompound.func_74767_n("flow");
/* 110 */     this.wasPoweredLastTick = nbttagcompound.func_74767_n("hadpower");
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 116 */     super.writeCustomNBT(nbttagcompound);
/*     */     
/* 118 */     nbttagcompound.func_74757_a("flow", this.allowFlow);
/* 119 */     nbttagcompound.func_74757_a("hadpower", this.wasPoweredLastTick);
/*     */   }
/*     */   
/*     */   public boolean isConnectable(ForgeDirection face)
/*     */   {
/* 124 */     return (face != this.facing) && (super.isConnectable(face));
/*     */   }
/*     */   
/*     */   public void setSuction(Aspect aspect, int amount)
/*     */   {
/* 129 */     if (this.allowFlow) super.setSuction(aspect, amount);
/*     */   }
/*     */   
/*     */   public boolean gettingPower() {
/* 133 */     return this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileTubeValve.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */