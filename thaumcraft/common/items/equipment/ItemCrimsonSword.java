/*    */ package thaumcraft.common.items.equipment;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.Item.ToolMaterial;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.ItemSword;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.util.EnumChatFormatting;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.util.StatCollector;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.util.EnumHelper;
/*    */ import thaumcraft.api.IRepairable;
/*    */ import thaumcraft.api.IWarpingGear;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ 
/*    */ 
/*    */ public class ItemCrimsonSword
/*    */   extends ItemSword
/*    */   implements IRepairable, IWarpingGear
/*    */ {
/* 32 */   public static Item.ToolMaterial toolMatCrimsonVoid = EnumHelper.addToolMaterial("CVOID", 4, 200, 8.0F, 3.5F, 20);
/*    */   public IIcon icon;
/*    */   
/* 35 */   public ItemCrimsonSword() { super(toolMatCrimsonVoid);
/* 36 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir)
/*    */   {
/* 43 */     this.icon = ir.func_94245_a("thaumcraft:crimson_blade");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int par1) {
/* 48 */     return this.icon;
/*    */   }
/*    */   
/*    */ 
/*    */   public EnumRarity func_77613_e(ItemStack itemstack)
/*    */   {
/* 54 */     return EnumRarity.rare;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*    */   {
/* 60 */     return par2ItemStack.func_77969_a(new ItemStack(ConfigItems.itemResource, 1, 15)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_77663_a(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_)
/*    */   {
/* 66 */     super.func_77663_a(stack, world, entity, p_77663_4_, p_77663_5_);
/*    */     
/* 68 */     if ((stack.func_77951_h()) && (entity != null) && (entity.field_70173_aa % 20 == 0) && ((entity instanceof EntityLivingBase))) {
/* 69 */       stack.func_77972_a(-1, (EntityLivingBase)entity);
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean func_77644_a(ItemStack is, EntityLivingBase target, EntityLivingBase hitter)
/*    */   {
/* 75 */     if ((!target.field_70170_p.field_72995_K) && (
/* 76 */       (!(target instanceof EntityPlayer)) || (!(hitter instanceof EntityPlayer)) || (MinecraftServer.func_71276_C().func_71219_W())))
/*    */     {
/*    */       try
/*    */       {
/* 80 */         target.func_70690_d(new PotionEffect(Potion.field_76437_t.func_76396_c(), 60));
/* 81 */         target.func_70690_d(new PotionEffect(Potion.field_76438_s.func_76396_c(), 120));
/*    */       }
/*    */       catch (Exception e) {}
/*    */     }
/* 85 */     return super.func_77644_a(is, target, hitter);
/*    */   }
/*    */   
/*    */   public int getWarp(ItemStack itemstack, EntityPlayer player)
/*    */   {
/* 90 */     return 2;
/*    */   }
/*    */   
/*    */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*    */   {
/* 95 */     list.add(EnumChatFormatting.GOLD + StatCollector.func_74838_a("enchantment.special.sapgreat"));
/* 96 */     super.func_77624_a(stack, player, list, par4);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/equipment/ItemCrimsonSword.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */