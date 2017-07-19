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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemWandRod
/*    */   extends Item
/*    */ {
/*    */   public ItemWandRod()
/*    */   {
/* 20 */     func_77625_d(64);
/* 21 */     func_77627_a(true);
/* 22 */     func_77656_e(0);
/* 23 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/* 26 */   public IIcon[] iconWand = new IIcon[8];
/* 27 */   public IIcon[] iconStaff = new IIcon[8];
/*    */   public IIcon iconPrimalStaff;
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir) {
/* 32 */     this.iconWand[0] = ir.func_94245_a("thaumcraft:wand_rod_greatwood");
/* 33 */     this.iconWand[1] = ir.func_94245_a("thaumcraft:wand_rod_obsidian");
/* 34 */     this.iconWand[2] = ir.func_94245_a("thaumcraft:wand_rod_silverwood");
/* 35 */     this.iconWand[3] = ir.func_94245_a("thaumcraft:wand_rod_ice");
/* 36 */     this.iconWand[4] = ir.func_94245_a("thaumcraft:wand_rod_quartz");
/* 37 */     this.iconWand[5] = ir.func_94245_a("thaumcraft:wand_rod_reed");
/* 38 */     this.iconWand[6] = ir.func_94245_a("thaumcraft:wand_rod_blaze");
/* 39 */     this.iconWand[7] = ir.func_94245_a("thaumcraft:wand_rod_bone");
/*    */     
/* 41 */     this.iconStaff[0] = ir.func_94245_a("thaumcraft:staff_rod_greatwood");
/* 42 */     this.iconStaff[1] = ir.func_94245_a("thaumcraft:staff_rod_obsidian");
/* 43 */     this.iconStaff[2] = ir.func_94245_a("thaumcraft:staff_rod_silverwood");
/* 44 */     this.iconStaff[3] = ir.func_94245_a("thaumcraft:staff_rod_ice");
/* 45 */     this.iconStaff[4] = ir.func_94245_a("thaumcraft:staff_rod_quartz");
/* 46 */     this.iconStaff[5] = ir.func_94245_a("thaumcraft:staff_rod_reed");
/* 47 */     this.iconStaff[6] = ir.func_94245_a("thaumcraft:staff_rod_blaze");
/* 48 */     this.iconStaff[7] = ir.func_94245_a("thaumcraft:staff_rod_bone");
/*    */     
/* 50 */     this.iconPrimalStaff = ir.func_94245_a("thaumcraft:staff_rod_primal");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int meta) {
/* 55 */     return meta < 100 ? this.iconStaff[(meta - 50)] : meta < 50 ? this.iconWand[meta] : this.iconPrimalStaff;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*    */   {
/* 61 */     par3List.add(new ItemStack(this, 1, 0));
/* 62 */     par3List.add(new ItemStack(this, 1, 1));
/* 63 */     par3List.add(new ItemStack(this, 1, 2));
/* 64 */     par3List.add(new ItemStack(this, 1, 3));
/* 65 */     par3List.add(new ItemStack(this, 1, 4));
/* 66 */     par3List.add(new ItemStack(this, 1, 5));
/* 67 */     par3List.add(new ItemStack(this, 1, 6));
/* 68 */     par3List.add(new ItemStack(this, 1, 7));
/*    */     
/* 70 */     par3List.add(new ItemStack(this, 1, 50));
/* 71 */     par3List.add(new ItemStack(this, 1, 51));
/* 72 */     par3List.add(new ItemStack(this, 1, 52));
/* 73 */     par3List.add(new ItemStack(this, 1, 53));
/* 74 */     par3List.add(new ItemStack(this, 1, 54));
/* 75 */     par3List.add(new ItemStack(this, 1, 55));
/* 76 */     par3List.add(new ItemStack(this, 1, 56));
/* 77 */     par3List.add(new ItemStack(this, 1, 57));
/*    */     
/* 79 */     par3List.add(new ItemStack(this, 1, 100));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String func_77667_c(ItemStack par1ItemStack)
/*    */   {
/* 86 */     return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/wands/ItemWandRod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */