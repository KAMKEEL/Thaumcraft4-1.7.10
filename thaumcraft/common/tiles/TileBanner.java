/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import thaumcraft.api.TileThaumcraft;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ 
/*    */ public class TileBanner
/*    */   extends TileThaumcraft
/*    */ {
/*    */   public boolean canUpdate()
/*    */   {
/* 15 */     return false;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public AxisAlignedBB getRenderBoundingBox()
/*    */   {
/* 21 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c + 1, this.field_145848_d + 2, this.field_145849_e + 1);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/* 26 */   private byte facing = 0;
/* 27 */   private byte color = -1;
/* 28 */   private Aspect aspect = null;
/* 29 */   private boolean onWall = false;
/*    */   
/*    */   public byte getFacing() {
/* 32 */     return this.facing;
/*    */   }
/*    */   
/*    */   public void setFacing(byte face) {
/* 36 */     this.facing = face;
/* 37 */     func_70296_d();
/*    */   }
/*    */   
/*    */   public boolean getWall() {
/* 41 */     return this.onWall;
/*    */   }
/*    */   
/*    */   public void setWall(boolean b) {
/* 45 */     this.onWall = b;
/* 46 */     func_70296_d();
/*    */   }
/*    */   
/*    */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*    */   {
/* 51 */     this.facing = nbttagcompound.func_74771_c("facing");
/* 52 */     setColor(nbttagcompound.func_74771_c("color"));
/* 53 */     String as = nbttagcompound.func_74779_i("aspect");
/* 54 */     if ((as != null) && (as.length() > 0)) setAspect(Aspect.getAspect(as)); else
/* 55 */       this.aspect = null;
/* 56 */     this.onWall = nbttagcompound.func_74767_n("wall");
/*    */   }
/*    */   
/*    */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*    */   {
/* 61 */     nbttagcompound.func_74774_a("facing", this.facing);
/* 62 */     nbttagcompound.func_74774_a("color", getColor());
/* 63 */     nbttagcompound.func_74778_a("aspect", getAspect() == null ? "" : getAspect().getTag());
/* 64 */     nbttagcompound.func_74757_a("wall", this.onWall);
/*    */   }
/*    */   
/*    */   public Aspect getAspect() {
/* 68 */     return this.aspect;
/*    */   }
/*    */   
/*    */   public void setAspect(Aspect aspect) {
/* 72 */     this.aspect = aspect;
/*    */   }
/*    */   
/*    */   public byte getColor() {
/* 76 */     return this.color;
/*    */   }
/*    */   
/*    */   public void setColor(byte color) {
/* 80 */     this.color = color;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileBanner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */