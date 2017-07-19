/*    */ package thaumcraft.common.items.wands;
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
/*    */ import thaumcraft.common.config.Config;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemWandCap
/*    */   extends Item
/*    */ {
/*    */   public ItemWandCap()
/*    */   {
/* 21 */     func_77625_d(64);
/* 22 */     func_77627_a(true);
/* 23 */     func_77656_e(0);
/* 24 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/* 29 */   public IIcon[] icon = new IIcon[9];
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir) {
/* 33 */     this.icon[0] = ir.func_94245_a("thaumcraft:wand_cap_iron");
/* 34 */     this.icon[1] = ir.func_94245_a("thaumcraft:wand_cap_gold");
/* 35 */     this.icon[2] = ir.func_94245_a("thaumcraft:wand_cap_thaumium");
/* 36 */     this.icon[3] = ir.func_94245_a("thaumcraft:wand_cap_copper");
/* 37 */     this.icon[4] = ir.func_94245_a("thaumcraft:wand_cap_silver");
/* 38 */     this.icon[5] = ir.func_94245_a("thaumcraft:wand_cap_silver_inert");
/* 39 */     this.icon[6] = ir.func_94245_a("thaumcraft:wand_cap_thaumium_inert");
/* 40 */     this.icon[7] = ir.func_94245_a("thaumcraft:wand_cap_void");
/* 41 */     this.icon[8] = ir.func_94245_a("thaumcraft:wand_cap_void_inert");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int meta) {
/* 46 */     return this.icon[meta];
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*    */   {
/* 52 */     par3List.add(new ItemStack(this, 1, 0));
/* 53 */     par3List.add(new ItemStack(this, 1, 1));
/* 54 */     if (Config.foundCopperIngot) {
/* 55 */       par3List.add(new ItemStack(this, 1, 3));
/*    */     }
/* 57 */     if (Config.foundSilverIngot) {
/* 58 */       par3List.add(new ItemStack(this, 1, 4));
/* 59 */       par3List.add(new ItemStack(this, 1, 5));
/*    */     }
/* 61 */     par3List.add(new ItemStack(this, 1, 2));
/* 62 */     par3List.add(new ItemStack(this, 1, 6));
/* 63 */     par3List.add(new ItemStack(this, 1, 7));
/* 64 */     par3List.add(new ItemStack(this, 1, 8));
/*    */   }
/*    */   
/*    */ 
/*    */   public String func_77667_c(ItemStack par1ItemStack)
/*    */   {
/* 70 */     return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/wands/ItemWandCap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */