/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.common.items.wands.ItemWandCasting;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileMagicWorkbenchCharger
/*    */   extends TileVisRelay
/*    */ {
/*    */   @SideOnly(Side.CLIENT)
/*    */   public AxisAlignedBB getRenderBoundingBox()
/*    */   {
/* 28 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e, this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + 1);
/*    */   }
/*    */   
/* 31 */   public short orientation = 0;
/*    */   
/*    */   public boolean isSource()
/*    */   {
/* 35 */     return false;
/*    */   }
/*    */   
/*    */   public void func_145845_h()
/*    */   {
/* 40 */     super.func_145845_h();
/*    */     
/* 42 */     if (!this.field_145850_b.field_72995_K) {
/* 43 */       TileEntity te = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
/* 44 */       if ((te != null) && ((te instanceof TileMagicWorkbench))) {
/* 45 */         TileMagicWorkbench tm = (TileMagicWorkbench)te;
/* 46 */         ItemStack wand = tm.func_70301_a(10);
/* 47 */         if ((wand != null) && ((wand.func_77973_b() instanceof ItemWandCasting))) {
/* 48 */           AspectList al = ((ItemWandCasting)wand.func_77973_b()).getAspectsWithRoom(wand);
/* 49 */           if (al.size() > 0) {
/* 50 */             for (Aspect aspect : al.getAspects()) {
/* 51 */               int drain = Math.min(5, ((ItemWandCasting)wand.func_77973_b()).getMaxVis(tm.func_70301_a(10)) - ((ItemWandCasting)wand.func_77973_b()).getVis(tm.func_70301_a(10), aspect));
/*    */               
/*    */ 
/* 54 */               if (drain > 0) {
/* 55 */                 ((ItemWandCasting)wand.func_77973_b()).addRealVis(tm.func_70301_a(10), aspect, consumeVis(aspect, drain), true);
/*    */               }
/*    */             }
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileMagicWorkbenchCharger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */