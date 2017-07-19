/*    */ package thaumcraft.common.items;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ItemBathSalts extends net.minecraft.item.Item
/*    */ {
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon icon;
/*    */   
/*    */   public ItemBathSalts()
/*    */   {
/* 18 */     func_77637_a(Thaumcraft.tabTC);
/* 19 */     func_77627_a(false);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir)
/*    */   {
/* 27 */     this.icon = ir.func_94245_a("thaumcraft:bath_salts");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int par1) {
/* 32 */     return this.icon;
/*    */   }
/*    */   
/*    */   public int getEntityLifespan(ItemStack itemStack, World world)
/*    */   {
/* 37 */     return 200;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/ItemBathSalts.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */