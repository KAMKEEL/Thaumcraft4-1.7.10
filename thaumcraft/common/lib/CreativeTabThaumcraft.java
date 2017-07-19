/*    */ package thaumcraft.common.lib;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.item.Item;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ 
/*    */ public final class CreativeTabThaumcraft extends CreativeTabs
/*    */ {
/*    */   public CreativeTabThaumcraft(int par1, String par2Str)
/*    */   {
/* 13 */     super(par1, par2Str);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public Item func_78016_d()
/*    */   {
/* 24 */     return ConfigItems.itemWandCasting;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/CreativeTabThaumcraft.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */