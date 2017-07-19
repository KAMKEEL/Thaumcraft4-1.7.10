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
/*    */ public class ItemGolemCore
/*    */   extends Item
/*    */ {
/*    */   public ItemGolemCore()
/*    */   {
/* 22 */     func_77625_d(64);
/* 23 */     func_77627_a(true);
/* 24 */     func_77656_e(0);
/* 25 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/* 28 */   public IIcon[] icon = new IIcon[12];
/*    */   public IIcon blankIcon;
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir) {
/* 33 */     this.icon[0] = ir.func_94245_a("thaumcraft:golem_core_fill");
/* 34 */     this.icon[1] = ir.func_94245_a("thaumcraft:golem_core_empty");
/* 35 */     this.icon[2] = ir.func_94245_a("thaumcraft:golem_core_gather");
/* 36 */     this.icon[3] = ir.func_94245_a("thaumcraft:golem_core_harvest");
/* 37 */     this.icon[4] = ir.func_94245_a("thaumcraft:golem_core_guard");
/* 38 */     this.icon[5] = ir.func_94245_a("thaumcraft:golem_core_liquid");
/* 39 */     this.icon[6] = ir.func_94245_a("thaumcraft:golem_core_essentia");
/* 40 */     this.icon[7] = ir.func_94245_a("thaumcraft:golem_core_lumber");
/* 41 */     this.icon[8] = ir.func_94245_a("thaumcraft:golem_core_use");
/* 42 */     this.icon[9] = ir.func_94245_a("thaumcraft:golem_core_butcher");
/* 43 */     this.icon[10] = ir.func_94245_a("thaumcraft:golem_core_sorting");
/* 44 */     this.icon[11] = ir.func_94245_a("thaumcraft:golem_core_fish");
/*    */     
/*    */ 
/*    */ 
/* 48 */     this.blankIcon = ir.func_94245_a("thaumcraft:golem_core_blank");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int d) {
/* 53 */     return d == 100 ? this.blankIcon : this.icon[d];
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*    */   {
/* 59 */     par3List.add(new ItemStack(this, 1, 100));
/* 60 */     for (int a = 0; a <= 11; a++) {
/* 61 */       par3List.add(new ItemStack(this, 1, a));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_77624_a(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4)
/*    */   {
/* 69 */     list.add(StatCollector.func_74838_a("item.ItemGolemCore." + stack.func_77960_j() + ".name"));
/*    */   }
/*    */   
/*    */ 
/*    */   public EnumRarity func_77613_e(ItemStack itemstack)
/*    */   {
/* 75 */     return itemstack.func_77960_j() == 100 ? EnumRarity.common : EnumRarity.uncommon;
/*    */   }
/*    */   
/*    */   public static boolean hasGUI(int core) {
/* 79 */     switch (core) {
/* 80 */     case 0: case 1: case 2: case 4: case 5: case 8: case 10:  return true;
/*    */     }
/* 82 */     return false;
/*    */   }
/*    */   
/*    */   public static boolean canSort(int core) {
/* 86 */     switch (core) {
/* 87 */     case 0: case 1: case 2: case 8: case 10:  return true;
/*    */     }
/* 89 */     return false;
/*    */   }
/*    */   
/*    */   public static boolean hasInventory(int core) {
/* 93 */     switch (core) {
/* 94 */     case 0: case 1: case 2: case 5: case 8:  return true;
/*    */     }
/* 96 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/golems/ItemGolemCore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */