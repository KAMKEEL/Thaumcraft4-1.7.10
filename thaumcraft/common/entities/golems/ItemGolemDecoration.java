/*    */ package thaumcraft.common.entities.golems;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.util.StatCollector;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ 
/*    */ public class ItemGolemDecoration
/*    */   extends Item
/*    */ {
/*    */   public ItemGolemDecoration()
/*    */   {
/* 20 */     func_77625_d(64);
/* 21 */     func_77627_a(true);
/* 22 */     func_77656_e(0);
/* 23 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/* 26 */   public IIcon[] icon = new IIcon[8];
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir) {
/* 30 */     this.icon[0] = ir.func_94245_a("thaumcraft:golemdecotophat");
/* 31 */     this.icon[1] = ir.func_94245_a("thaumcraft:golemdecoglasses");
/* 32 */     this.icon[2] = ir.func_94245_a("thaumcraft:golemdecobowtie");
/* 33 */     this.icon[3] = ir.func_94245_a("thaumcraft:golemdecofez");
/* 34 */     this.icon[4] = ir.func_94245_a("thaumcraft:golemdecodart");
/* 35 */     this.icon[5] = ir.func_94245_a("thaumcraft:golemdecovisor");
/* 36 */     this.icon[6] = ir.func_94245_a("thaumcraft:golemdecoarmor");
/* 37 */     this.icon[7] = ir.func_94245_a("thaumcraft:golemdecomace");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int d) {
/* 42 */     return this.icon[d];
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*    */   {
/* 48 */     for (int a = 0; a <= 7; a++) {
/* 49 */       par3List.add(new ItemStack(this, 1, a));
/*    */     }
/*    */   }
/*    */   
/*    */   public static String getDecoChar(int md) {
/* 54 */     switch (md) {
/* 55 */     case 0:  return "H";
/* 56 */     case 1:  return "G";
/* 57 */     case 2:  return "B";
/* 58 */     case 3:  return "F";
/* 59 */     case 4:  return "R";
/* 60 */     case 5:  return "V";
/* 61 */     case 6:  return "P";
/* 62 */     case 7:  return "M";
/*    */     }
/* 64 */     return "";
/*    */   }
/*    */   
/*    */   public String func_77653_i(ItemStack stack)
/*    */   {
/* 69 */     return StatCollector.func_74838_a("item.ItemGolemDecoration.name") + ": " + super.func_77653_i(stack);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String func_77667_c(ItemStack par1ItemStack)
/*    */   {
/* 76 */     return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/golems/ItemGolemDecoration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */