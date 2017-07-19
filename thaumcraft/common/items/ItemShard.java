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
/*    */ 
/*    */ 
/*    */ public class ItemShard
/*    */   extends Item
/*    */ {
/*    */   public IIcon icon;
/*    */   public IIcon iconBalanced;
/*    */   
/*    */   public ItemShard()
/*    */   {
/* 22 */     func_77625_d(64);
/* 23 */     func_77627_a(true);
/* 24 */     func_77656_e(0);
/* 25 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir)
/*    */   {
/* 33 */     this.icon = ir.func_94245_a("thaumcraft:shard");
/* 34 */     this.iconBalanced = ir.func_94245_a("thaumcraft:shard_balanced");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int par1) {
/* 39 */     return par1 == 6 ? this.iconBalanced : this.icon;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_82790_a(ItemStack stack, int par2)
/*    */   {
/* 45 */     if (stack.func_77960_j() == 6) {
/* 46 */       return super.func_82790_a(stack, par2);
/*    */     }
/* 48 */     return thaumcraft.common.blocks.BlockCustomOreItem.colors[(stack.func_77960_j() + 1)];
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*    */   {
/* 54 */     for (int a = 0; a <= 6; a++) {
/* 55 */       par3List.add(new ItemStack(this, 1, a));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public String func_77667_c(ItemStack par1ItemStack)
/*    */   {
/* 62 */     return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/ItemShard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */