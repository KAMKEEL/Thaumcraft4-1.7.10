/*    */ package thaumcraft.common.entities.golems;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.util.StatCollector;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ 
/*    */ public class ItemGolemUpgrade
/*    */   extends Item
/*    */ {
/*    */   public ItemGolemUpgrade()
/*    */   {
/* 22 */     func_77625_d(64);
/* 23 */     func_77627_a(true);
/* 24 */     func_77656_e(0);
/* 25 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/* 28 */   public IIcon[] icon = new IIcon[6];
/*    */   public IIcon iconEmpty;
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir) {
/* 33 */     this.iconEmpty = ir.func_94245_a("thaumcraft:golem_upgrade_empty");
/* 34 */     this.icon[0] = ir.func_94245_a("thaumcraft:golem_upgrade_air");
/* 35 */     this.icon[1] = ir.func_94245_a("thaumcraft:golem_upgrade_earth");
/* 36 */     this.icon[2] = ir.func_94245_a("thaumcraft:golem_upgrade_fire");
/* 37 */     this.icon[3] = ir.func_94245_a("thaumcraft:golem_upgrade_water");
/* 38 */     this.icon[4] = ir.func_94245_a("thaumcraft:golem_upgrade_order");
/* 39 */     this.icon[5] = ir.func_94245_a("thaumcraft:golem_upgrade_entropy");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int d) {
/* 44 */     return d < 0 ? this.iconEmpty : this.icon[d];
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*    */   {
/* 50 */     for (int a = 0; a <= 5; a++) {
/* 51 */       par3List.add(new ItemStack(this, 1, a));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public String func_77667_c(ItemStack par1ItemStack)
/*    */   {
/* 58 */     return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_77624_a(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4)
/*    */   {
/* 64 */     list.add(StatCollector.func_74838_a("item.ItemGolemUpgrade." + stack.func_77960_j() + ".desc"));
/*    */   }
/*    */   
/*    */ 
/*    */   public EnumRarity func_77613_e(ItemStack itemstack)
/*    */   {
/* 70 */     return EnumRarity.uncommon;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/golems/ItemGolemUpgrade.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */