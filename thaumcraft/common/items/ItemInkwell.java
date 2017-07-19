/*    */ package thaumcraft.common.items;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.tiles.TileResearchTable;
/*    */ import thaumcraft.common.tiles.TileTable;
/*    */ 
/*    */ public class ItemInkwell extends net.minecraft.item.Item implements thaumcraft.api.IScribeTools
/*    */ {
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon icon;
/*    */   
/*    */   public ItemInkwell()
/*    */   {
/* 24 */     this.field_77777_bU = 1;
/* 25 */     this.canRepair = true;
/* 26 */     func_77656_e(100);
/* 27 */     func_77637_a(Thaumcraft.tabTC);
/* 28 */     func_77627_a(false);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir)
/*    */   {
/* 36 */     this.icon = ir.func_94245_a("thaumcraft:inkwell");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int par1) {
/* 41 */     return this.icon;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
/*    */   {
/* 48 */     TileEntity tile = world.func_147438_o(x, y, z);
/* 49 */     int md = world.func_72805_g(x, y, z);
/* 50 */     net.minecraft.block.Block bi = world.func_147439_a(x, y, z);
/* 51 */     if ((tile != null) && ((tile instanceof TileTable)) && (md != 6)) {
/* 52 */       if (world.field_72995_K) { return false;
/*    */       }
/* 54 */       for (int a = 2; a < 6; a++) {
/* 55 */         TileEntity tile2 = world.func_147438_o(x + ForgeDirection.getOrientation(a).offsetX, y + ForgeDirection.getOrientation(a).offsetY, z + ForgeDirection.getOrientation(a).offsetZ);
/*    */         
/*    */ 
/*    */ 
/* 59 */         int md2 = world.func_72805_g(x + ForgeDirection.getOrientation(a).offsetX, y + ForgeDirection.getOrientation(a).offsetY, z + ForgeDirection.getOrientation(a).offsetZ);
/*    */         
/*    */ 
/*    */ 
/* 63 */         if ((tile2 != null) && ((tile2 instanceof TileTable)) && (md2 < 6)) {
/* 64 */           world.func_147465_d(x, y, z, bi, a, 0);
/* 65 */           world.func_147455_a(x, y, z, new TileResearchTable());
/* 66 */           world.func_147465_d(x + ForgeDirection.getOrientation(a).offsetX, y + ForgeDirection.getOrientation(a).offsetY, z + ForgeDirection.getOrientation(a).offsetZ, bi, ForgeDirection.getOrientation(a).getOpposite().ordinal() + 4, 0);
/*    */           
/*    */ 
/*    */ 
/*    */ 
/* 71 */           world.func_147471_g(x, y, z);
/* 72 */           world.func_147471_g(x + ForgeDirection.getOrientation(a).offsetX, y + ForgeDirection.getOrientation(a).offsetY, z + ForgeDirection.getOrientation(a).offsetZ);
/*    */           
/*    */ 
/*    */ 
/*    */ 
/* 77 */           TileEntity tile3 = world.func_147438_o(x, y, z);
/* 78 */           if ((tile3 != null) && ((tile3 instanceof TileResearchTable))) {
/* 79 */             ((TileResearchTable)tile3).func_70299_a(0, stack.func_77946_l());
/* 80 */             if (!player.field_71075_bZ.field_75098_d) {
/* 81 */               player.field_71071_by.func_70298_a(player.field_71071_by.field_70461_c, 1);
/* 82 */               player.field_71071_by.func_70296_d();
/*    */             }
/* 84 */             world.func_147471_g(x, y, z);
/*    */           }
/* 86 */           return true;
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 91 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/ItemInkwell.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */