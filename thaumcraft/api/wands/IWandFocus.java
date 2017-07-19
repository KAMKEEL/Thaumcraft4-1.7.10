/*    */ package thaumcraft.api.wands;
/*    */ 
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public abstract interface IWandFocus
/*    */ {
/*    */   public abstract int getFocusColor();
/*    */   
/*    */   public abstract net.minecraft.util.IIcon getFocusDepthLayerIcon();
/*    */   
/*    */   public abstract net.minecraft.util.IIcon getOrnament();
/*    */   
/*    */   public static enum WandFocusAnimation {
/* 14 */     WAVE,  CHARGE;
/*    */     
/*    */     private WandFocusAnimation() {}
/*    */   }
/*    */   
/*    */   public abstract WandFocusAnimation getAnimation();
/*    */   
/*    */   public abstract thaumcraft.api.aspects.AspectList getVisCost();
/*    */   
/*    */   public abstract boolean isVisCostPerTick();
/*    */   
/*    */   public abstract ItemStack onFocusRightClick(ItemStack paramItemStack, net.minecraft.world.World paramWorld, net.minecraft.entity.player.EntityPlayer paramEntityPlayer, net.minecraft.util.MovingObjectPosition paramMovingObjectPosition);
/*    */   
/*    */   public abstract void onUsingFocusTick(ItemStack paramItemStack, net.minecraft.entity.player.EntityPlayer paramEntityPlayer, int paramInt);
/*    */   
/*    */   public abstract void onPlayerStoppedUsingFocus(ItemStack paramItemStack, net.minecraft.world.World paramWorld, net.minecraft.entity.player.EntityPlayer paramEntityPlayer, int paramInt);
/*    */   
/*    */   public abstract String getSortingHelper(ItemStack paramItemStack);
/*    */   
/*    */   public abstract boolean onFocusBlockStartBreak(ItemStack paramItemStack, int paramInt1, int paramInt2, int paramInt3, net.minecraft.entity.player.EntityPlayer paramEntityPlayer);
/*    */   
/*    */   public abstract boolean acceptsEnchant(int paramInt);
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/wands/IWandFocus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */