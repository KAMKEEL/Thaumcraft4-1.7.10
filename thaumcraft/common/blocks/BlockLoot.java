/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.client.renderers.block.BlockRenderer;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ public class BlockLoot extends Block
/*     */ {
/*     */   public BlockLoot(Material mat, String ip, int rt)
/*     */   {
/*  26 */     super(mat);
/*  27 */     func_149711_c(0.15F);
/*  28 */     func_149752_b(0.0F);
/*  29 */     this.iconPre = ip;
/*  30 */     this.renderType = rt;
/*  31 */     func_149647_a(Thaumcraft.tabTC);
/*  32 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*  35 */   String iconPre = "urn";
/*  36 */   int renderType = 0;
/*     */   
/*  38 */   public IIcon[] icon = new IIcon[4];
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir)
/*     */   {
/*  43 */     this.icon[0] = ir.func_94245_a("thaumcraft:" + this.iconPre + "_top");
/*  44 */     this.icon[1] = ir.func_94245_a("thaumcraft:" + this.iconPre + "_side_0");
/*  45 */     this.icon[2] = ir.func_94245_a("thaumcraft:" + this.iconPre + "_side_1");
/*  46 */     this.icon[3] = ir.func_94245_a("thaumcraft:" + this.iconPre + "_side_2");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int side, int meta)
/*     */   {
/*  52 */     if (side <= 1) return this.icon[0];
/*  53 */     return this.icon[(meta + 1)];
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/*  58 */     return this.renderType == 1 ? ConfigBlocks.blockLootUrnRI : ConfigBlocks.blockLootCrateRI;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  63 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  68 */     return false;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149633_g(World w, int i, int j, int k)
/*     */   {
/*  73 */     if (this.renderType == 1) {
/*  74 */       func_149676_a(BlockRenderer.W2, BlockRenderer.W1, BlockRenderer.W2, BlockRenderer.W14, BlockRenderer.W13, BlockRenderer.W14);
/*     */     } else {
/*  76 */       func_149676_a(BlockRenderer.W1, 0.0F, BlockRenderer.W1, BlockRenderer.W15, BlockRenderer.W14, BlockRenderer.W15);
/*     */     }
/*  78 */     return super.func_149633_g(w, i, j, k);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  84 */     par3List.add(new ItemStack(par1, 1, 0));
/*  85 */     par3List.add(new ItemStack(par1, 1, 1));
/*  86 */     par3List.add(new ItemStack(par1, 1, 2));
/*     */   }
/*     */   
/*     */   public int func_149692_a(int par1)
/*     */   {
/*  91 */     return par1;
/*     */   }
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int md, int fortune)
/*     */   {
/*  96 */     ArrayList<ItemStack> ret = new ArrayList();
/*     */     
/*  98 */     int q = 1 + md + world.field_73012_v.nextInt(3);
/*  99 */     for (int a = 0; a < q; a++) {
/* 100 */       ItemStack is = Utils.generateLoot(md, world.field_73012_v);
/* 101 */       if (is != null) {
/* 102 */         ret.add(is.func_77946_l());
/*     */       }
/*     */     }
/*     */     
/* 106 */     return ret;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockLoot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */