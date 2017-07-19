/*    */ package thaumcraft.common.items.equipment;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.Item.ToolMaterial;
/*    */ import net.minecraft.item.ItemHoe;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.CommonProxy;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.blocks.BlockCustomPlant;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ 
/*    */ public class ItemElementalHoe extends ItemHoe implements thaumcraft.api.IRepairable
/*    */ {
/*    */   public IIcon icon;
/*    */   
/*    */   public ItemElementalHoe(Item.ToolMaterial enumtoolmaterial)
/*    */   {
/* 23 */     super(enumtoolmaterial);
/* 24 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir)
/*    */   {
/* 31 */     this.icon = ir.func_94245_a("thaumcraft:elementalhoe");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int par1) {
/* 36 */     return this.icon;
/*    */   }
/*    */   
/*    */   public int func_77619_b()
/*    */   {
/* 41 */     return 5;
/*    */   }
/*    */   
/*    */ 
/*    */   public net.minecraft.item.EnumRarity func_77613_e(ItemStack itemstack)
/*    */   {
/* 47 */     return net.minecraft.item.EnumRarity.rare;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*    */   {
/* 53 */     return par2ItemStack.func_77969_a(new ItemStack(thaumcraft.common.config.ConfigItems.itemResource, 1, 2)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
/*    */   {
/* 62 */     if (player.func_70093_af()) { return super.func_77648_a(stack, player, world, x, y, z, par7, par8, par9, par10);
/*    */     }
/* 64 */     boolean did = false;
/* 65 */     for (int xx = -1; xx <= 1; xx++) {
/* 66 */       for (int zz = -1; zz <= 1; zz++) {
/* 67 */         if (super.func_77648_a(stack, player, world, x + xx, y, z + zz, par7, par8, par9, par10)) {
/* 68 */           Thaumcraft.proxy.blockSparkle(world, x + xx, y, z + zz, 8401408, 2);
/* 69 */           if (!did) { did = true;
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/* 74 */     if (!did) {
/* 75 */       did = thaumcraft.common.lib.utils.Utils.useBonemealAtLoc(world, player, x, y, z);
/* 76 */       if (!did) {
/* 77 */         net.minecraft.block.Block bi = world.func_147439_a(x, y, z);
/* 78 */         int md = world.func_72805_g(x, y, z);
/* 79 */         if ((bi == ConfigBlocks.blockCustomPlant) && (md == 0) && (stack.func_77960_j() + 20 <= stack.func_77958_k())) {
/* 80 */           ((BlockCustomPlant)bi).growGreatTree(world, x, y, z, world.field_73012_v);
/* 81 */           stack.func_77972_a(5, player);
/* 82 */           Thaumcraft.proxy.blockSparkle(world, x, y, z, 0, 2);
/* 83 */           did = true;
/*    */         }
/* 85 */         else if ((bi == ConfigBlocks.blockCustomPlant) && (md == 1) && (stack.func_77960_j() + 150 <= stack.func_77958_k())) {
/* 86 */           ((BlockCustomPlant)bi).growSilverTree(world, x, y, z, world.field_73012_v);
/* 87 */           stack.func_77972_a(25, player);
/* 88 */           Thaumcraft.proxy.blockSparkle(world, x, y, z, 0, 2);
/* 89 */           did = true;
/*    */         }
/*    */       } else {
/* 92 */         stack.func_77972_a(1, player);
/* 93 */         Thaumcraft.proxy.blockSparkle(world, x, y, z, 0, 3);
/*    */       }
/* 95 */       if (did) { world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "thaumcraft:wand", 0.75F, 0.9F + world.field_73012_v.nextFloat() * 0.2F);
/*    */       }
/*    */     }
/* 98 */     return did;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/equipment/ItemElementalHoe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */