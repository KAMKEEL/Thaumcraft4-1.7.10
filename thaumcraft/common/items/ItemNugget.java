/*    */ package thaumcraft.common.items;
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
/*    */ public class ItemNugget
/*    */   extends Item
/*    */ {
/*    */   public ItemNugget()
/*    */   {
/* 21 */     func_77625_d(64);
/* 22 */     func_77627_a(true);
/* 23 */     func_77656_e(0);
/* 24 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/* 27 */   public IIcon[] icon = new IIcon[64];
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir) {
/* 31 */     this.icon[0] = ir.func_94245_a("thaumcraft:nuggetiron");
/* 32 */     this.icon[16] = ir.func_94245_a("thaumcraft:clusteriron");
/* 33 */     this.icon[5] = ir.func_94245_a("thaumcraft:nuggetquicksilver");
/* 34 */     this.icon[21] = ir.func_94245_a("thaumcraft:clustercinnabar");
/* 35 */     this.icon[6] = ir.func_94245_a("thaumcraft:nuggetthaumium");
/* 36 */     this.icon[7] = ir.func_94245_a("thaumcraft:nuggetvoid");
/* 37 */     this.icon[31] = ir.func_94245_a("thaumcraft:clustergold");
/* 38 */     this.icon[1] = ir.func_94245_a("thaumcraft:nuggetcopper");
/* 39 */     this.icon[17] = ir.func_94245_a("thaumcraft:clustercopper");
/* 40 */     this.icon[2] = ir.func_94245_a("thaumcraft:nuggettin");
/* 41 */     this.icon[18] = ir.func_94245_a("thaumcraft:clustertin");
/* 42 */     this.icon[3] = ir.func_94245_a("thaumcraft:nuggetsilver");
/* 43 */     this.icon[19] = ir.func_94245_a("thaumcraft:clustersilver");
/* 44 */     this.icon[4] = ir.func_94245_a("thaumcraft:nuggetlead");
/* 45 */     this.icon[20] = ir.func_94245_a("thaumcraft:clusterlead");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int meta) {
/* 50 */     return this.icon[meta];
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*    */   {
/* 56 */     par3List.add(new ItemStack(this, 1, 0));
/* 57 */     par3List.add(new ItemStack(this, 1, 5));
/* 58 */     par3List.add(new ItemStack(this, 1, 21));
/* 59 */     par3List.add(new ItemStack(this, 1, 6));
/* 60 */     par3List.add(new ItemStack(this, 1, 7));
/* 61 */     par3List.add(new ItemStack(this, 1, 16));
/* 62 */     par3List.add(new ItemStack(this, 1, 31));
/* 63 */     if (Config.foundCopperIngot) {
/* 64 */       par3List.add(new ItemStack(this, 1, 1));
/* 65 */       par3List.add(new ItemStack(this, 1, 17));
/*    */     }
/* 67 */     if (Config.foundTinIngot) {
/* 68 */       par3List.add(new ItemStack(this, 1, 2));
/* 69 */       par3List.add(new ItemStack(this, 1, 18));
/*    */     }
/* 71 */     if (Config.foundSilverIngot) {
/* 72 */       par3List.add(new ItemStack(this, 1, 3));
/* 73 */       par3List.add(new ItemStack(this, 1, 19));
/*    */     }
/* 75 */     if (Config.foundLeadIngot) {
/* 76 */       par3List.add(new ItemStack(this, 1, 4));
/* 77 */       par3List.add(new ItemStack(this, 1, 20));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String func_77667_c(ItemStack par1ItemStack)
/*    */   {
/* 85 */     return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/ItemNugget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */