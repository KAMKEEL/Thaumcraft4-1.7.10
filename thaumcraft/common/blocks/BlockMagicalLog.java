/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockRotatedPillar;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.particle.EffectRenderer;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.nodes.INode;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.items.ItemWispEssence;
/*     */ import thaumcraft.common.tiles.TileNode;
/*     */ 
/*     */ public class BlockMagicalLog
/*     */   extends BlockRotatedPillar
/*     */ {
/*  33 */   public static final String[] woodType = { "greatwood", "silverwood", "silverwoodknot" };
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon[] tree_side;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon[] tree_top;
/*     */   
/*     */   public BlockMagicalLog()
/*     */   {
/*  41 */     super(Material.field_151575_d);
/*  42 */     func_149647_a(Thaumcraft.tabTC);
/*  43 */     func_149711_c(2.5F);
/*  44 */     func_149672_a(field_149766_f);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   protected IIcon func_150161_d(int par1)
/*     */   {
/*  52 */     return this.tree_top[(par1 % this.tree_top.length)];
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   protected IIcon func_150163_b(int i)
/*     */   {
/*  58 */     return this.tree_side[(i % this.tree_side.length)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir)
/*     */   {
/*  66 */     this.tree_side = new IIcon[woodType.length];
/*  67 */     this.tree_top = new IIcon[woodType.length];
/*     */     
/*  69 */     for (int i = 0; i < this.tree_side.length; i++)
/*     */     {
/*  71 */       this.tree_side[i] = ir.func_94245_a("thaumcraft:" + woodType[i] + "side");
/*  72 */       this.tree_top[i] = ir.func_94245_a("thaumcraft:" + woodType[i] + "top");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_149745_a(Random par1Random)
/*     */   {
/*  84 */     return 1;
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
/*     */   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
/*     */   {
/* 101 */     byte b0 = 4;
/* 102 */     int j1 = b0 + 1;
/*     */     
/* 104 */     if (par1World.func_72904_c(par2 - j1, par3 - j1, par4 - j1, par2 + j1, par3 + j1, par4 + j1))
/*     */     {
/* 106 */       for (int k1 = -b0; k1 <= b0; k1++)
/*     */       {
/* 108 */         for (int l1 = -b0; l1 <= b0; l1++)
/*     */         {
/* 110 */           for (int i2 = -b0; i2 <= b0; i2++)
/*     */           {
/* 112 */             Block j2 = par1World.func_147439_a(par2 + k1, par3 + l1, par4 + i2);
/*     */             
/* 114 */             if (!j2.isAir(par1World, par2 + k1, par3 + l1, par4 + i2))
/*     */             {
/* 116 */               j2.beginLeavesDecay(par1World, par2 + k1, par3 + l1, par4 + i2);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149681_a(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer)
/*     */   {
/* 127 */     if ((limitToValidMetadata(par5) == 2) && (!par1World.field_72995_K))
/*     */     {
/* 129 */       TileEntity te = par1World.func_147438_o(par2, par3, par4);
/* 130 */       if ((te != null) && ((te instanceof INode)) && (((INode)te).getAspects().size() > 0)) {
/* 131 */         for (Aspect aspect : ((INode)te).getAspects().getAspects()) {
/* 132 */           for (int a = 0; a <= ((INode)te).getAspects().getAmount(aspect) / 10; a++)
/* 133 */             if (((INode)te).getAspects().getAmount(aspect) >= 5) {
/* 134 */               ItemStack ess = new ItemStack(ConfigItems.itemWispEssence);
/* 135 */               AspectList al = new AspectList();
/* 136 */               ((ItemWispEssence)ess.func_77973_b()).setAspects(ess, new AspectList().add(aspect, 2));
/*     */               
/* 138 */               func_149642_a(par1World, par2, par3, par4, ess);
/*     */             }
/*     */         }
/*     */       }
/*     */     }
/* 143 */     super.func_149681_a(par1World, par2, par3, par4, par5, par6EntityPlayer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_149692_a(int par1)
/*     */   {
/* 155 */     if ((par1 & 0x3) == 2) return 1;
/* 156 */     return par1 & 0x3;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int limitToValidMetadata(int par0)
/*     */   {
/* 164 */     return par0 & 0x3;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/* 175 */     par3List.add(new ItemStack(par1, 1, 0));
/* 176 */     par3List.add(new ItemStack(par1, 1, 1));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 184 */     return true;
/*     */   }
/*     */   
/*     */   public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 189 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isWood(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 195 */     return true;
/*     */   }
/*     */   
/*     */   public int getLightValue(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 200 */     if ((world.func_72805_g(x, y, z) & 0x2) == 1) { return 7;
/*     */     }
/* 202 */     if ((world.func_72805_g(x, y, z) & 0x2) == 2) { return 7;
/*     */     }
/* 204 */     return super.getLightValue(world, x, y, z);
/*     */   }
/*     */   
/*     */   public boolean hasTileEntity(int metadata)
/*     */   {
/* 209 */     if (limitToValidMetadata(metadata) == 2) return true;
/* 210 */     return super.hasTileEntity(metadata);
/*     */   }
/*     */   
/*     */ 
/*     */   public TileEntity createTileEntity(World world, int metadata)
/*     */   {
/* 216 */     if (limitToValidMetadata(metadata) == 2) return new TileNode();
/* 217 */     return super.createTileEntity(world, metadata);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
/*     */   {
/* 225 */     if (limitToValidMetadata(meta) == 2) {
/* 226 */       Thaumcraft.proxy.burst(world, x + 0.5D, y + 0.5D, z + 0.5D, 1.0F);
/* 227 */       world.func_72980_b(x + 0.5D, y + 0.5D, z + 0.5D, "thaumcraft:craftfail", 1.0F, 1.0F, false);
/*     */     }
/* 229 */     return super.addDestroyEffects(world, x, y, z, meta, effectRenderer);
/*     */   }
/*     */   
/*     */   public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face)
/*     */   {
/* 234 */     return 5;
/*     */   }
/*     */   
/*     */   public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face)
/*     */   {
/* 239 */     return 5;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockMagicalLog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */