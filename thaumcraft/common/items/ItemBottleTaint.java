/*    */ package thaumcraft.common.items;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ItemBottleTaint extends net.minecraft.item.Item implements thaumcraft.api.IScribeTools
/*    */ {
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon icon;
/*    */   
/*    */   public ItemBottleTaint()
/*    */   {
/* 20 */     this.field_77777_bU = 8;
/* 21 */     func_77656_e(0);
/* 22 */     func_77637_a(Thaumcraft.tabTC);
/* 23 */     func_77627_a(false);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir)
/*    */   {
/* 31 */     this.icon = ir.func_94245_a("thaumcraft:bottle_taint");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int par1) {
/* 36 */     return this.icon;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
/*    */   {
/* 43 */     if (!player.field_71075_bZ.field_75098_d)
/*    */     {
/* 45 */       stack.field_77994_a -= 1;
/*    */     }
/*    */     
/* 48 */     world.func_72956_a(player, "random.bow", 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
/*    */     
/* 50 */     if (!world.field_72995_K)
/*    */     {
/* 52 */       world.func_72838_d(new thaumcraft.common.entities.projectile.EntityBottleTaint(world, player));
/*    */     }
/*    */     
/* 55 */     return stack;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/ItemBottleTaint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */