/*    */ package thaumcraft.api;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract interface IArchitect
/*    */ {
/*    */   public abstract ArrayList<BlockCoordinates> getArchitectBlocks(ItemStack paramItemStack, World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, EntityPlayer paramEntityPlayer);
/*    */   
/*    */   public abstract boolean showAxis(ItemStack paramItemStack, World paramWorld, EntityPlayer paramEntityPlayer, int paramInt, EnumAxis paramEnumAxis);
/*    */   
/*    */   public static enum EnumAxis
/*    */   {
/* 23 */     X, 
/* 24 */     Y, 
/* 25 */     Z;
/*    */     
/*    */     private EnumAxis() {}
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/IArchitect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */