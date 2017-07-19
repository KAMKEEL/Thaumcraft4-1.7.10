/*    */ package thaumcraft.common.blocks;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ 
/*    */ public class BlockAiryItem extends ItemBlock
/*    */ {
/*    */   public IIcon icon;
/*    */   
/*    */   public BlockAiryItem(Block b)
/*    */   {
/* 19 */     super(b);
/* 20 */     func_77656_e(0);
/* 21 */     func_77627_a(true);
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir)
/*    */   {
/* 28 */     this.icon = ir.func_94245_a("thaumcraft:taint_over_2");
/*    */   }
/*    */   
/*    */   public IIcon func_77617_a(int par1)
/*    */   {
/* 33 */     return this.icon;
/*    */   }
/*    */   
/*    */ 
/*    */   public int func_77647_b(int par1)
/*    */   {
/* 39 */     return par1;
/*    */   }
/*    */   
/*    */ 
/*    */   public String func_77667_c(ItemStack par1ItemStack)
/*    */   {
/* 45 */     return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
/*    */   {
/* 51 */     if (par1ItemStack.func_77960_j() == 0) {
/* 52 */       par3List.add("§5Place a randomly generated node");
/* 53 */       par3List.add("§oCreative Mode Only");
/*    */     }
/* 55 */     super.func_77624_a(par1ItemStack, par2EntityPlayer, par3List, par4);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockAiryItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */