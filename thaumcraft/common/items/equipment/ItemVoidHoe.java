/*    */ package thaumcraft.common.items.equipment;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.Item.ToolMaterial;
/*    */ import net.minecraft.item.ItemHoe;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ItemVoidHoe extends ItemHoe implements thaumcraft.api.IRepairable, thaumcraft.api.IWarpingGear
/*    */ {
/*    */   public IIcon icon;
/*    */   
/*    */   public ItemVoidHoe(Item.ToolMaterial enumtoolmaterial)
/*    */   {
/* 25 */     super(enumtoolmaterial);
/* 26 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir)
/*    */   {
/* 33 */     this.icon = ir.func_94245_a("thaumcraft:voidhoe");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int par1) {
/* 38 */     return this.icon;
/*    */   }
/*    */   
/*    */   public int func_77619_b()
/*    */   {
/* 43 */     return 5;
/*    */   }
/*    */   
/*    */ 
/*    */   public EnumRarity func_77613_e(ItemStack itemstack)
/*    */   {
/* 49 */     return EnumRarity.uncommon;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*    */   {
/* 55 */     return par2ItemStack.func_77969_a(new ItemStack(thaumcraft.common.config.ConfigItems.itemResource, 1, 15)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_77663_a(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_)
/*    */   {
/* 61 */     super.func_77663_a(stack, world, entity, p_77663_4_, p_77663_5_);
/*    */     
/* 63 */     if ((stack.func_77951_h()) && (entity != null) && (entity.field_70173_aa % 20 == 0) && ((entity instanceof EntityLivingBase))) {
/* 64 */       stack.func_77972_a(-1, (EntityLivingBase)entity);
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
/*    */   {
/* 70 */     if ((!player.field_70170_p.field_72995_K) && ((entity instanceof EntityLivingBase)) && (
/* 71 */       (!(entity instanceof EntityPlayer)) || (MinecraftServer.func_71276_C().func_71219_W())))
/*    */     {
/*    */ 
/* 74 */       ((EntityLivingBase)entity).func_70690_d(new net.minecraft.potion.PotionEffect(Potion.field_76437_t.func_76396_c(), 80));
/*    */     }
/*    */     
/* 77 */     return super.onLeftClickEntity(stack, player, entity);
/*    */   }
/*    */   
/*    */   public int getWarp(ItemStack itemstack, EntityPlayer player)
/*    */   {
/* 82 */     return 1;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/equipment/ItemVoidHoe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */