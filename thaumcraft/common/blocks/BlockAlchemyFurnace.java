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
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.fx.particles.FXSlimyBubble;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.tiles.TileAlchemyFurnaceAdvanced;
/*     */ import thaumcraft.common.tiles.TileAlchemyFurnaceAdvancedNozzle;
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
/*     */ public class BlockAlchemyFurnace
/*     */   extends BlockContainer
/*     */ {
/*     */   public IIcon icon;
/*     */   
/*     */   public BlockAlchemyFurnace()
/*     */   {
/*  60 */     super(Material.field_151573_f);
/*  61 */     func_149711_c(3.0F);
/*  62 */     func_149752_b(17.0F);
/*  63 */     func_149672_a(Block.field_149777_j);
/*  64 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir)
/*     */   {
/*  71 */     this.icon = ir.func_94245_a("thaumcraft:metalbase");
/*     */   }
/*     */   
/*     */   public IIcon func_149691_a(int i, int md)
/*     */   {
/*  76 */     return this.icon;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  83 */     par3List.add(new ItemStack(par1, 1, 0));
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/*  88 */     return -1;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  93 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  99 */     return false;
/*     */   }
/*     */   
/*     */   public void func_149719_a(IBlockAccess world, int i, int j, int k)
/*     */   {
/* 104 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149633_g(World w, int i, int j, int k)
/*     */   {
/* 109 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 110 */     return super.func_149633_g(w, i, j, k);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist, Entity entity)
/*     */   {
/* 116 */     int md = world.func_72805_g(i, j, k);
/* 117 */     if ((md == 0) && (!(entity instanceof EntityLivingBase))) {
/* 118 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.7F, 1.0F);
/* 119 */       super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, entity);
/*     */     } else {
/* 121 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 122 */       super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, entity);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getLightValue(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 130 */     int metadata = world.func_72805_g(x, y, z);
/* 131 */     if (metadata == 0) {
/* 132 */       TileAlchemyFurnaceAdvanced tile = (TileAlchemyFurnaceAdvanced)world.func_147438_o(x, y, z);
/* 133 */       if ((tile != null) && (tile.heat > 100)) {
/* 134 */         return (int)(tile.heat / tile.maxPower * 12.0F);
/*     */       }
/*     */     }
/* 137 */     return super.getLightValue(world, x, y, z);
/*     */   }
/*     */   
/*     */   public void func_149670_a(World world, int i, int j, int k, Entity entity)
/*     */   {
/* 142 */     if (!world.field_72995_K) {
/* 143 */       int metadata = world.func_72805_g(i, j, k);
/* 144 */       if (metadata == 0) {
/* 145 */         TileAlchemyFurnaceAdvanced tile = (TileAlchemyFurnaceAdvanced)world.func_147438_o(i, j, k);
/* 146 */         if ((tile != null) && ((entity instanceof EntityItem)) && (tile.process(((EntityItem)entity).func_92059_d()))) {
/* 147 */           ItemStack s = ((EntityItem)entity).func_92059_d();
/* 148 */           s.field_77994_a -= 1;
/* 149 */           world.func_72956_a(entity, "thaumcraft:bubble", 0.2F, 1.0F + world.field_73012_v.nextFloat() * 0.4F);
/* 150 */           if (s.field_77994_a <= 0) {
/* 151 */             entity.func_70106_y();
/*     */           } else {
/* 153 */             ((EntityItem)entity).func_92058_a(s);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int md, Random par2Random, int par3)
/*     */   {
/* 162 */     return (md == 1) || (md == 2) || (md == 3) || (md == 4) ? Item.func_150898_a(ConfigBlocks.blockMetalDevice) : md == 0 ? Item.func_150898_a(ConfigBlocks.blockStoneDevice) : Item.func_150899_d(0);
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149692_a(int metadata)
/*     */   {
/* 168 */     if ((metadata == 1) || (metadata == 4)) return 3;
/* 169 */     if (metadata == 3) return 9;
/* 170 */     if (metadata == 2) return 1;
/* 171 */     return 0;
/*     */   }
/*     */   
/*     */   public TileEntity createTileEntity(World world, int metadata)
/*     */   {
/* 176 */     if (metadata == 0) return new TileAlchemyFurnaceAdvanced();
/* 177 */     if (metadata == 1) return new TileAlchemyFurnaceAdvancedNozzle();
/* 178 */     return super.createTileEntity(world, metadata);
/*     */   }
/*     */   
/*     */   public boolean func_149740_M()
/*     */   {
/* 183 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149736_g(World world, int x, int y, int z, int rs)
/*     */   {
/* 189 */     TileEntity te = world.func_147438_o(x, y, z);
/* 190 */     if ((te != null) && ((te instanceof TileAlchemyFurnaceAdvancedNozzle))) {
/* 191 */       if (((TileAlchemyFurnaceAdvancedNozzle)te).furnace != null) {
/* 192 */         float r = ((TileAlchemyFurnaceAdvancedNozzle)te).furnace.vis / ((TileAlchemyFurnaceAdvancedNozzle)te).furnace.maxVis;
/*     */         
/* 194 */         return MathHelper.func_76141_d(r * 14.0F) + ((TileAlchemyFurnaceAdvancedNozzle)te).furnace.vis > 0 ? 1 : 0;
/*     */       }
/* 196 */       return 0;
/*     */     }
/* 198 */     return 0;
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World var1, int md)
/*     */   {
/* 203 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_149749_a(World world, int x, int y, int z, Block bl, int md)
/*     */   {
/* 210 */     if (!world.field_72995_K) {
/* 211 */       if (md != 0)
/*     */       {
/* 213 */         for (int a = -1; a <= 1; a++) {
/* 214 */           for (int b = -1; b <= 1; b++)
/* 215 */             for (int c = -1; c <= 1; c++)
/* 216 */               if ((world.func_147439_a(x + a, y + b, z + c) == this) && (world.func_72805_g(x + a, y + b, z + c) == 0)) {
/* 217 */                 TileAlchemyFurnaceAdvanced tile = (TileAlchemyFurnaceAdvanced)world.func_147438_o(x + a, y + b, z + c);
/* 218 */                 if (tile != null) {
/* 219 */                   tile.destroy = true;
/* 220 */                   return;
/*     */                 }
/*     */               }
/*     */         }
/*     */       } else {
/* 225 */         for (int a = -1; a <= 1; a++) {
/* 226 */           for (int b = 0; b <= 1; b++) {
/* 227 */             for (int c = -1; c <= 1; c++) {
/* 228 */               if (((a != 0) || (b != 0) || (c != 0)) && (world.func_147439_a(x + a, y + b, z + c) == this)) {
/* 229 */                 int m = world.func_72805_g(x + a, y + b, z + c);
/* 230 */                 world.func_147465_d(x + a, y + b, z + c, Block.func_149634_a(func_149650_a(m, world.field_73012_v, 0)), func_149692_a(m), 3);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand)
/*     */   {
/* 242 */     int meta = world.func_72805_g(x, y, z);
/* 243 */     if (meta == 0) {
/* 244 */       TileAlchemyFurnaceAdvanced tile = (TileAlchemyFurnaceAdvanced)world.func_147438_o(x, y, z);
/* 245 */       if ((tile != null) && (tile.vis > 0)) {
/* 246 */         FXSlimyBubble ef = new FXSlimyBubble(world, x + rand.nextFloat(), y + 1, z + rand.nextFloat(), 0.06F + rand.nextFloat() * 0.06F);
/*     */         
/* 248 */         ef.func_82338_g(0.8F);
/* 249 */         ef.func_70538_b(0.6F - rand.nextFloat() * 0.2F, 0.0F, 0.6F + rand.nextFloat() * 0.2F);
/* 250 */         ParticleEngine.instance.addEffect(world, ef);
/*     */         
/* 252 */         if (rand.nextInt(50) == 0) {
/* 253 */           double var21 = x + rand.nextFloat();
/* 254 */           double var22 = y + this.field_149756_F;
/* 255 */           double var23 = z + rand.nextFloat();
/* 256 */           world.func_72980_b(var21, var22, var23, "liquid.lavapop", 0.1F + rand.nextFloat() * 0.1F, 0.9F + rand.nextFloat() * 0.15F, false);
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 261 */         int q = rand.nextInt(2);
/* 262 */         int w = rand.nextInt(2);
/*     */         
/* 264 */         FXSlimyBubble ef2 = new FXSlimyBubble(world, x - 0.6D + rand.nextFloat() * 0.2D + q * 2, y + 2, z - 0.6D + rand.nextFloat() * 0.2D + w * 2, 0.06F + rand.nextFloat() * 0.06F);
/*     */         
/* 266 */         ef2.func_82338_g(0.8F);
/* 267 */         ef2.func_70538_b(0.6F - rand.nextFloat() * 0.2F, 0.0F, 0.6F + rand.nextFloat() * 0.2F);
/* 268 */         ParticleEngine.instance.addEffect(world, ef2);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 273 */     super.func_149734_b(world, x, y, z, rand);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockAlchemyFurnace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */