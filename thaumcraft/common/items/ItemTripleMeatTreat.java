/*    */ package thaumcraft.common.items;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.item.ItemFood;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.util.IIcon;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemTripleMeatTreat
/*    */   extends ItemFood
/*    */ {
/*    */   public IIcon icon;
/*    */   
/*    */   public ItemTripleMeatTreat()
/*    */   {
/* 20 */     super(6, 0.8F, true);
/* 21 */     func_77848_i();
/* 22 */     func_77844_a(Potion.field_76428_l.field_76415_H, 5, 0, 0.66F);
/* 23 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir)
/*    */   {
/* 31 */     this.icon = ir.func_94245_a("thaumcraft:tripletreat");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int meta) {
/* 36 */     return this.icon;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/ItemTripleMeatTreat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */