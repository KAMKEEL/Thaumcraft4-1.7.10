/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.tiles.TileEldritchNothing;
/*     */ 
/*     */ public class BlockEldritchNothing extends Block
/*     */ {
/*     */   public IIcon icon;
/*     */   
/*     */   public BlockEldritchNothing()
/*     */   {
/*  27 */     super(net.minecraft.block.material.Material.field_151576_e);
/*  28 */     func_149722_s();
/*  29 */     func_149752_b(6000000.0F);
/*  30 */     func_149672_a(Block.field_149775_l);
/*  31 */     func_149715_a(0.2F);
/*  32 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  33 */     func_149675_a(true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir)
/*     */   {
/*  41 */     this.icon = ir.func_94245_a("thaumcraft:blank");
/*     */   }
/*     */   
/*     */   public IIcon func_149691_a(int i, int m)
/*     */   {
/*  46 */     return this.icon;
/*     */   }
/*     */   
/*     */   public net.minecraft.item.ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
/*     */   {
/*  51 */     return null;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149633_g(World w, int i, int j, int k)
/*     */   {
/*  56 */     return AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  62 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/*  67 */     return -1;
/*     */   }
/*     */   
/*     */   public boolean hasTileEntity(int metadata)
/*     */   {
/*  72 */     return metadata == 1;
/*     */   }
/*     */   
/*     */   public TileEntity createTileEntity(World world, int metadata)
/*     */   {
/*  77 */     return metadata == 1 ? new TileEldritchNothing() : null;
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int par1, Random par2Random, int par3)
/*     */   {
/*  82 */     return Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block block)
/*     */   {
/*  87 */     if (BlockUtils.isBlockExposed(world, x, y, z)) {
/*  88 */       world.func_72921_c(x, y, z, 1, 3);
/*     */     } else {
/*  90 */       world.func_72921_c(x, y, z, 0, 3);
/*     */     }
/*  92 */     super.func_149695_a(world, x, y, z, block);
/*     */   }
/*     */   
/*     */   public void func_149670_a(World world, int x, int y, int z, Entity entity)
/*     */   {
/*  97 */     if ((entity.field_70173_aa > 20) && ((!(entity instanceof EntityPlayer)) || (!((EntityPlayer)entity).field_71075_bZ.field_75098_d)))
/*     */     {
/*  99 */       entity.func_70097_a(DamageSource.field_76380_i, 8.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
/*     */   {
/* 105 */     float f = 0.125F;
/* 106 */     return AxisAlignedBB.func_72330_a(x + f, y + f, z + f, x + 1 - f, y + 1 - f, z + 1 - f);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockEldritchNothing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */