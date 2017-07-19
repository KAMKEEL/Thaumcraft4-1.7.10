/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.tileentity.TileEntityFurnace;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ import thaumcraft.api.TileThaumcraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileBellows
/*    */   extends TileThaumcraft
/*    */ {
/* 18 */   public float inflation = 1.0F;
/* 19 */   boolean direction = false;
/* 20 */   boolean firstrun = true;
/* 21 */   public byte orientation = 0;
/* 22 */   public boolean onVanillaFurnace = false;
/* 23 */   public int delay = 0;
/*    */   
/*    */   public void func_145845_h()
/*    */   {
/* 27 */     if (this.field_145850_b.field_72995_K) {
/* 28 */       if (!gettingPower()) {
/* 29 */         if (this.firstrun)
/* 30 */           this.inflation = (0.35F + this.field_145850_b.field_73012_v.nextFloat() * 0.55F);
/* 31 */         this.firstrun = false;
/*    */         
/* 33 */         if ((this.inflation > 0.35F) && (!this.direction)) this.inflation -= 0.075F;
/* 34 */         if ((this.inflation <= 0.35F) && (!this.direction))
/*    */         {
/* 36 */           this.direction = true;
/*    */         }
/*    */         
/* 39 */         if ((this.inflation < 1.0F) && (this.direction)) this.inflation += 0.025F;
/* 40 */         if ((this.inflation >= 1.0F) && (this.direction)) {
/* 41 */           this.direction = false;
/* 42 */           this.field_145850_b.func_72980_b(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, "mob.ghast.fireball", 0.01F, 0.5F + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.2F, false);
/*    */         }
/*    */         
/*    */       }
/*    */     }
/* 47 */     else if ((this.onVanillaFurnace) && (!gettingPower())) {
/* 48 */       this.delay += 1;
/* 49 */       if (this.delay >= 2) {
/* 50 */         this.delay = 0;
/* 51 */         ForgeDirection dir = ForgeDirection.getOrientation(this.orientation);
/* 52 */         TileEntity tile = this.field_145850_b.func_147438_o(this.field_145851_c + dir.offsetX, this.field_145848_d, this.field_145849_e + dir.offsetZ);
/* 53 */         if ((tile != null) && ((tile instanceof TileEntityFurnace))) {
/* 54 */           TileEntityFurnace tf = (TileEntityFurnace)tile;
/* 55 */           if ((tf.field_145961_j > 0) && (tf.field_145961_j < 199)) tf.field_145961_j += 1;
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean gettingPower()
/*    */   {
/* 63 */     return this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*    */   }
/*    */   
/*    */   public static int getBellows(World world, int x, int y, int z, ForgeDirection[] directions) {
/* 67 */     int bellows = 0;
/* 68 */     for (ForgeDirection dir : directions) {
/* 69 */       int xx = x + dir.offsetX;
/* 70 */       int yy = y + dir.offsetY;
/* 71 */       int zz = z + dir.offsetZ;
/* 72 */       TileEntity tile = world.func_147438_o(xx, yy, zz);
/* 73 */       if ((tile != null) && ((tile instanceof TileBellows)) && (((TileBellows)tile).orientation == dir.getOpposite().ordinal()) && (!world.func_72864_z(xx, yy, zz)))
/*    */       {
/*    */ 
/* 76 */         bellows++;
/*    */       }
/*    */     }
/* 79 */     return bellows;
/*    */   }
/*    */   
/*    */ 
/*    */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*    */   {
/* 85 */     this.orientation = nbttagcompound.func_74771_c("orientation");
/* 86 */     this.onVanillaFurnace = nbttagcompound.func_74767_n("onVanillaFurnace");
/*    */   }
/*    */   
/*    */ 
/*    */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*    */   {
/* 92 */     nbttagcompound.func_74774_a("orientation", this.orientation);
/* 93 */     nbttagcompound.func_74757_a("onVanillaFurnace", this.onVanillaFurnace);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileBellows.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */