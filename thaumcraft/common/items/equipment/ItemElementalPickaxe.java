/*    */ package thaumcraft.common.items.equipment;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.Item.ToolMaterial;
/*    */ import net.minecraft.item.ItemPickaxe;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.IRepairable;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ItemElementalPickaxe extends ItemPickaxe implements IRepairable
/*    */ {
/*    */   public IIcon icon;
/*    */   
/*    */   public ItemElementalPickaxe(Item.ToolMaterial enumtoolmaterial)
/*    */   {
/* 27 */     super(enumtoolmaterial);
/* 28 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */   public java.util.Set<String> getToolClasses(ItemStack stack)
/*    */   {
/* 33 */     return ImmutableSet.of("pickaxe");
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir)
/*    */   {
/* 40 */     this.icon = ir.func_94245_a("thaumcraft:elementalpick");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int par1) {
/* 45 */     return this.icon;
/*    */   }
/*    */   
/*    */ 
/*    */   public EnumRarity func_77613_e(ItemStack itemstack)
/*    */   {
/* 51 */     return EnumRarity.rare;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*    */   {
/* 57 */     return par2ItemStack.func_77969_a(new ItemStack(thaumcraft.common.config.ConfigItems.itemResource, 1, 2)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
/*    */   {
/* 63 */     if ((!player.field_70170_p.field_72995_K) && (
/* 64 */       (!(entity instanceof EntityPlayer)) || (MinecraftServer.func_71276_C().func_71219_W())))
/*    */     {
/*    */ 
/* 67 */       entity.func_70015_d(2);
/*    */     }
/*    */     
/* 70 */     return super.onLeftClickEntity(stack, player, entity);
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_77648_a(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
/*    */   {
/* 76 */     itemstack.func_77972_a(5, player);
/*    */     
/* 78 */     if (!world.field_72995_K) {
/* 79 */       world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "thaumcraft:wandfail", 0.2F, 0.2F + world.field_73012_v.nextFloat() * 0.2F);
/* 80 */       return super.func_77648_a(itemstack, player, world, x, y, z, side, par8, par9, par10);
/*    */     }
/* 82 */     Minecraft mc = Minecraft.func_71410_x();
/* 83 */     Thaumcraft.instance.renderEventHandler.startScan(player, x, y, z, System.currentTimeMillis() + 5000L, 8);
/*    */     
/* 85 */     player.func_71038_i();
/* 86 */     return super.func_77648_a(itemstack, player, world, x, y, z, side, par8, par9, par10);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/equipment/ItemElementalPickaxe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */