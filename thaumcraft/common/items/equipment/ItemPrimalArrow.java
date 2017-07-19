/*    */ package thaumcraft.common.items.equipment;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ 
/*    */ public class ItemPrimalArrow
/*    */   extends Item
/*    */ {
/*    */   public ItemPrimalArrow()
/*    */   {
/* 19 */     func_77625_d(64);
/* 20 */     func_77627_a(true);
/* 21 */     func_77656_e(0);
/* 22 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/* 25 */   public IIcon[] icon = new IIcon[6];
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir) {
/* 29 */     this.icon[0] = ir.func_94245_a("thaumcraft:el_arrow_air");
/* 30 */     this.icon[1] = ir.func_94245_a("thaumcraft:el_arrow_fire");
/* 31 */     this.icon[2] = ir.func_94245_a("thaumcraft:el_arrow_water");
/* 32 */     this.icon[3] = ir.func_94245_a("thaumcraft:el_arrow_earth");
/* 33 */     this.icon[4] = ir.func_94245_a("thaumcraft:el_arrow_order");
/* 34 */     this.icon[5] = ir.func_94245_a("thaumcraft:el_arrow_entropy");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int par1)
/*    */   {
/* 40 */     return this.icon[par1];
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*    */   {
/* 46 */     for (int a = 0; a <= 5; a++) {
/* 47 */       par3List.add(new ItemStack(this, 1, a));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public String func_77667_c(ItemStack par1ItemStack)
/*    */   {
/* 54 */     return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/equipment/ItemPrimalArrow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */