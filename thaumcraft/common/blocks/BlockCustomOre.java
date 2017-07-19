/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.particle.EffectRenderer;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ 
/*     */ public class BlockCustomOre
/*     */   extends Block
/*     */ {
/*     */   public BlockCustomOre()
/*     */   {
/*  34 */     super(Material.field_151576_e);
/*  35 */     func_149752_b(5.0F);
/*  36 */     func_149711_c(1.5F);
/*  37 */     func_149672_a(Block.field_149769_e);
/*  38 */     func_149647_a(Thaumcraft.tabTC);
/*  39 */     func_149675_a(true);
/*     */   }
/*     */   
/*  42 */   public IIcon[] icon = new IIcon[5];
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir) {
/*  46 */     this.icon[0] = ir.func_94245_a("thaumcraft:cinnibar");
/*  47 */     this.icon[1] = ir.func_94245_a("thaumcraft:infusedorestone");
/*  48 */     this.icon[2] = ir.func_94245_a("thaumcraft:infusedore");
/*  49 */     this.icon[3] = ir.func_94245_a("thaumcraft:amberore");
/*  50 */     this.icon[4] = ir.func_94245_a("thaumcraft:frostshard");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int par2) {
/*  55 */     if (par2 == 0) {
/*  56 */       return this.icon[0];
/*     */     }
/*  58 */     if (par2 == 7) {
/*  59 */       return this.icon[3];
/*     */     }
/*  61 */     if (par2 == 15) {
/*  62 */       return this.icon[4];
/*     */     }
/*  64 */     return this.icon[1];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
/*     */   {
/*  72 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149692_a(int par1)
/*     */   {
/*  78 */     return par1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  86 */     par3List.add(new ItemStack(par1, 1, 0));
/*  87 */     par3List.add(new ItemStack(par1, 1, 1));
/*  88 */     par3List.add(new ItemStack(par1, 1, 2));
/*  89 */     par3List.add(new ItemStack(par1, 1, 3));
/*  90 */     par3List.add(new ItemStack(par1, 1, 4));
/*  91 */     par3List.add(new ItemStack(par1, 1, 5));
/*  92 */     par3List.add(new ItemStack(par1, 1, 6));
/*  93 */     par3List.add(new ItemStack(par1, 1, 7));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer)
/*     */   {
/* 101 */     int md = worldObj.func_72805_g(target.field_72311_b, target.field_72312_c, target.field_72309_d);
/* 102 */     if ((md != 0) && (md < 6))
/* 103 */       UtilsFX.infusedStoneSparkle(worldObj, target.field_72311_b, target.field_72312_c, target.field_72309_d, md);
/* 104 */     return super.addHitEffects(worldObj, target, effectRenderer);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
/*     */   {
/* 110 */     return super.addDestroyEffects(world, x, y, z, meta, effectRenderer);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149719_a(IBlockAccess par1iBlockAccess, int par2, int par3, int par4)
/*     */   {
/* 116 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 117 */     super.func_149719_a(par1iBlockAccess, par2, par3, par4);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist, Entity par7Entity)
/*     */   {
/* 125 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 126 */     super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int md, int fortune)
/*     */   {
/* 133 */     ArrayList<ItemStack> ret = new ArrayList();
/* 134 */     if (md == 0) {
/* 135 */       ret.add(new ItemStack(ConfigBlocks.blockCustomOre, 1, 0));
/*     */     }
/* 137 */     else if (md == 7) {
/* 138 */       ret.add(new ItemStack(ConfigItems.itemResource, 1 + world.field_73012_v.nextInt(fortune + 1), 6));
/*     */     } else {
/* 140 */       int q = 1 + world.field_73012_v.nextInt(2 + fortune);
/* 141 */       for (int a = 0; a < q; a++) {
/* 142 */         ret.add(new ItemStack(ConfigItems.itemShard, 1, md - 1));
/*     */       }
/*     */     }
/* 145 */     return ret;
/*     */   }
/*     */   
/*     */ 
/* 149 */   private Random rand = new Random();
/*     */   
/*     */   public int getExpDrop(IBlockAccess world, int md, int fortune) {
/* 152 */     if ((md != 0) && (md != 7))
/* 153 */       return MathHelper.func_76136_a(this.rand, 0, 3);
/* 154 */     if (md == 7) {
/* 155 */       return MathHelper.func_76136_a(this.rand, 1, 4);
/*     */     }
/* 157 */     return super.getExpDrop(world, md, fortune);
/*     */   }
/*     */   
/*     */   public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
/*     */   {
/* 162 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/* 169 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/* 174 */     return ConfigBlocks.blockCustomOreRI;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockCustomOre.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */