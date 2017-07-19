/*    */ package thaumcraft.common.items;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.util.StatCollector;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.lib.utils.Utils;
/*    */ 
/*    */ public class ItemLootBag
/*    */   extends Item
/*    */ {
/*    */   public ItemLootBag()
/*    */   {
/* 25 */     func_77625_d(16);
/* 26 */     func_77627_a(true);
/* 27 */     func_77656_e(0);
/* 28 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/* 31 */   public IIcon[] icon = new IIcon[3];
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir)
/*    */   {
/* 36 */     this.icon[0] = ir.func_94245_a("thaumcraft:lootbag");
/* 37 */     this.icon[1] = ir.func_94245_a("thaumcraft:lootbagunc");
/* 38 */     this.icon[2] = ir.func_94245_a("thaumcraft:lootbagrare");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int par1)
/*    */   {
/* 44 */     return this.icon[par1];
/*    */   }
/*    */   
/*    */ 
/*    */   public String func_77667_c(ItemStack par1ItemStack)
/*    */   {
/* 50 */     return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*    */   {
/* 56 */     par3List.add(new ItemStack(this, 1, 0));
/* 57 */     par3List.add(new ItemStack(this, 1, 1));
/* 58 */     par3List.add(new ItemStack(this, 1, 2));
/*    */   }
/*    */   
/*    */ 
/*    */   public EnumRarity func_77613_e(ItemStack stack)
/*    */   {
/* 64 */     switch (stack.func_77960_j()) {
/* 65 */     case 1:  return EnumRarity.uncommon;
/* 66 */     case 2:  return EnumRarity.rare;
/*    */     }
/* 68 */     return EnumRarity.common;
/*    */   }
/*    */   
/*    */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*    */   {
/* 73 */     super.func_77624_a(stack, player, list, par4);
/* 74 */     list.add(StatCollector.func_74838_a("tc.lootbag"));
/*    */   }
/*    */   
/*    */   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
/*    */   {
/* 79 */     if (!world.field_72995_K) {
/* 80 */       int q = 8 + world.field_73012_v.nextInt(5);
/* 81 */       for (int a = 0; a < q; a++) {
/* 82 */         ItemStack is = Utils.generateLoot(stack.func_77960_j(), world.field_73012_v);
/* 83 */         if (is != null) {
/* 84 */           world.func_72838_d(new EntityItem(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, is.func_77946_l()));
/*    */         }
/*    */       }
/*    */       
/* 88 */       world.func_72956_a(player, "thaumcraft:coins", 0.75F, 1.0F);
/*    */     }
/* 90 */     stack.field_77994_a -= 1;
/* 91 */     return stack;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/ItemLootBag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */