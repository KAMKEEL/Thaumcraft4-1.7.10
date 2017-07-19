/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockContainer;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.tiles.TileMagicBox;
/*     */ 
/*     */ 
/*     */ public class BlockMagicBox
/*     */   extends BlockContainer
/*     */ {
/*  25 */   private Random random = new Random();
/*     */   public IIcon icon;
/*     */   
/*     */   public BlockMagicBox() {
/*  29 */     super(Material.field_151575_d);
/*  30 */     func_149711_c(2.5F);
/*  31 */     func_149672_a(field_149766_f);
/*  32 */     func_149647_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir)
/*     */   {
/*  39 */     this.icon = ir.func_94245_a("thaumcraft:woodplain");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int side, int meta) {
/*  44 */     return this.icon;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  50 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  56 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6)
/*     */   {
/*  88 */     InventoryUtils.dropItems(par1World, par2, par3, par4);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 128 */     super.func_149749_a(par1World, par2, par3, par4, par5, par6);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
/*     */   {
/* 135 */     Object var10 = (TileMagicBox)par1World.func_147438_o(par2, par3, par4);
/* 136 */     if (var10 == null)
/*     */     {
/* 138 */       return true;
/*     */     }
/*     */     
/* 141 */     if (par1World.field_72995_K)
/*     */     {
/* 143 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 147 */     par5EntityPlayer.openGui(Thaumcraft.instance, 18, par1World, par2, par3, par4);
/* 148 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public TileEntity func_149915_a(World par1World, int m)
/*     */   {
/* 155 */     return new TileMagicBox();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockMagicBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */