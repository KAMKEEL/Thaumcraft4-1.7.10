/*    */ package thaumcraft.common.items.armor;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.HashMap;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemArmor;
/*    */ import net.minecraft.item.ItemArmor.ArmorMaterial;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.lib.events.EventHandlerEntity;
/*    */ 
/*    */ public class ItemBootsTraveller extends ItemArmor implements thaumcraft.api.IRepairable, thaumcraft.api.IRunicArmor
/*    */ {
/*    */   public IIcon icon;
/*    */   
/*    */   public ItemBootsTraveller(ItemArmor.ArmorMaterial enumarmormaterial, int j, int k)
/*    */   {
/* 21 */     super(enumarmormaterial, j, k);
/* 22 */     func_77656_e(350);
/* 23 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir)
/*    */   {
/* 30 */     this.icon = ir.func_94245_a("thaumcraft:bootstraveler");
/*    */   }
/*    */   
/*    */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */   public IIcon func_77617_a(int par1) {
/* 35 */     return this.icon;
/*    */   }
/*    */   
/*    */   public String getArmorTexture(ItemStack stack, net.minecraft.entity.Entity entity, int slot, String type)
/*    */   {
/* 40 */     return "thaumcraft:textures/models/bootstraveler.png";
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public net.minecraft.item.EnumRarity func_77613_e(ItemStack itemstack)
/*    */   {
/* 47 */     return net.minecraft.item.EnumRarity.rare;
/*    */   }
/*    */   
/*    */   public int getRunicCharge(ItemStack itemstack)
/*    */   {
/* 52 */     return 0;
/*    */   }
/*    */   
/*    */ 
/*    */   public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
/*    */   {
/* 58 */     if ((!player.field_71075_bZ.field_75100_b) && (player.field_70701_bs > 0.0F))
/*    */     {
/* 60 */       if ((player.field_70170_p.field_72995_K) && (!player.func_70093_af())) {
/* 61 */         if (!Thaumcraft.instance.entityEventHandler.prevStep.containsKey(Integer.valueOf(player.func_145782_y()))) {
/* 62 */           Thaumcraft.instance.entityEventHandler.prevStep.put(Integer.valueOf(player.func_145782_y()), Float.valueOf(player.field_70138_W));
/*    */         }
/* 64 */         player.field_70138_W = 1.0F;
/*    */       }
/*    */       
/* 67 */       if (player.field_70122_E) {
/* 68 */         float bonus = 0.055F;
/* 69 */         if (player.func_70090_H()) bonus /= 4.0F;
/* 70 */         player.func_70060_a(0.0F, 1.0F, bonus);
/*    */       }
/* 72 */       else if (Hover.getHover(player.func_145782_y())) {
/* 73 */         player.field_70747_aH = 0.03F;
/*    */       } else {
/* 75 */         player.field_70747_aH = 0.05F;
/*    */       }
/*    */     }
/*    */     
/* 79 */     if (player.field_70143_R > 0.0F) {
/* 80 */       player.field_70143_R -= 0.25F;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/armor/ItemBootsTraveller.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */