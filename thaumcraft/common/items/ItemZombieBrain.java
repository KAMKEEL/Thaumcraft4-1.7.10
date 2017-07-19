/*    */ package thaumcraft.common.items;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemFood;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ItemZombieBrain extends ItemFood
/*    */ {
/*    */   public IIcon icon;
/*    */   
/*    */   public ItemZombieBrain()
/*    */   {
/* 20 */     super(4, 0.2F, true);
/* 21 */     func_77844_a(net.minecraft.potion.Potion.field_76438_s.field_76415_H, 30, 0, 0.8F);
/* 22 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir)
/*    */   {
/* 29 */     this.icon = ir.func_94245_a("thaumcraft:brain");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int meta) {
/* 34 */     return this.icon;
/*    */   }
/*    */   
/*    */   public ItemStack func_77654_b(ItemStack stack, World world, EntityPlayer player)
/*    */   {
/* 39 */     if ((!world.field_72995_K) && ((player instanceof net.minecraft.entity.player.EntityPlayerMP))) {
/* 40 */       if (world.field_73012_v.nextFloat() < 0.1F) {
/* 41 */         Thaumcraft.addStickyWarpToPlayer(player, 1);
/*    */       } else {
/* 43 */         Thaumcraft.addWarpToPlayer(player, 1 + world.field_73012_v.nextInt(3), true);
/*    */       }
/*    */     }
/* 46 */     return super.func_77654_b(stack, world, player);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/ItemZombieBrain.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */