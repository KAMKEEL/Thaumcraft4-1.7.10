/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.Random;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.api.TileThaumcraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileJar
/*    */   extends TileThaumcraft
/*    */ {
/* 15 */   protected static Random rand = new Random();
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public AxisAlignedBB getRenderBoundingBox()
/*    */   {
/* 20 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + 1);
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean canUpdate()
/*    */   {
/* 26 */     return true;
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_145845_h()
/*    */   {
/* 32 */     super.func_145845_h();
/*    */   }
/*    */   
/* 35 */   ResourceLocation texture = new ResourceLocation("thaumcraft", "textures/models/jar.png");
/*    */   
/* 37 */   public ResourceLocation getTexture() { return this.texture; }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileJar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */