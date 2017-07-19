/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.particle.EffectRenderer;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.nodes.INode;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.items.ItemWispEssence;
/*     */ import thaumcraft.common.tiles.TileNode;
/*     */ import thaumcraft.common.tiles.TileWardingStone;
/*     */ 
/*     */ public class BlockCosmeticSolid extends Block
/*     */ {
/*     */   public BlockCosmeticSolid()
/*     */   {
/*  41 */     super(Material.field_151576_e);
/*  42 */     func_149752_b(10.0F);
/*  43 */     func_149711_c(2.0F);
/*  44 */     func_149672_a(field_149769_e);
/*  45 */     func_149647_a(Thaumcraft.tabTC);
/*  46 */     func_149675_a(true);
/*     */   }
/*     */   
/*  49 */   public IIcon[] icon = new IIcon[27];
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir) {
/*  53 */     this.icon[0] = ir.func_94245_a("thaumcraft:obsidiantile");
/*  54 */     this.icon[1] = ir.func_94245_a("thaumcraft:obsidiantotembase");
/*  55 */     this.icon[2] = ir.func_94245_a("thaumcraft:obsidiantotem1");
/*  56 */     this.icon[3] = ir.func_94245_a("thaumcraft:obsidiantotem2");
/*  57 */     this.icon[4] = ir.func_94245_a("thaumcraft:obsidiantotem3");
/*  58 */     this.icon[5] = ir.func_94245_a("thaumcraft:obsidiantotem4");
/*  59 */     this.icon[6] = ir.func_94245_a("thaumcraft:obsidiantotembaseshaded");
/*  60 */     this.icon[7] = ir.func_94245_a("thaumcraft:paving_stone_travel");
/*  61 */     this.icon[8] = ir.func_94245_a("thaumcraft:paving_stone_warding");
/*  62 */     this.icon[9] = ir.func_94245_a("thaumcraft:thaumiumblock");
/*  63 */     this.icon[10] = ir.func_94245_a("thaumcraft:tallowblock");
/*  64 */     this.icon[11] = ir.func_94245_a("thaumcraft:tallowblock_top");
/*  65 */     this.icon[12] = ir.func_94245_a("thaumcraft:pedestal_top");
/*  66 */     this.icon[13] = ir.func_94245_a("thaumcraft:arcane_stone");
/*  67 */     this.icon[14] = ir.func_94245_a("thaumcraft:golem_stone_top");
/*  68 */     this.icon[15] = ir.func_94245_a("thaumcraft:golem_stone_side");
/*  69 */     this.icon[16] = ir.func_94245_a("thaumcraft:golem_stone_top_active");
/*  70 */     this.icon[17] = ir.func_94245_a("thaumcraft:es_1");
/*  71 */     this.icon[18] = ir.func_94245_a("thaumcraft:es_2");
/*  72 */     this.icon[19] = ir.func_94245_a("thaumcraft:es_3");
/*  73 */     this.icon[20] = ir.func_94245_a("thaumcraft:es_4");
/*  74 */     this.icon[21] = ir.func_94245_a("thaumcraft:er_1");
/*  75 */     this.icon[22] = ir.func_94245_a("thaumcraft:er_2");
/*  76 */     this.icon[23] = ir.func_94245_a("thaumcraft:er_3");
/*  77 */     this.icon[24] = ir.func_94245_a("thaumcraft:er_4");
/*  78 */     this.icon[25] = ir.func_94245_a("thaumcraft:crust");
/*  79 */     this.icon[26] = ir.func_94245_a("thaumcraft:es_p");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int par2)
/*     */   {
/*  85 */     if ((par2 <= 1) || (par2 == 8)) return this.icon[0];
/*  86 */     if (par2 == 2) {
/*  87 */       return this.icon[7];
/*     */     }
/*  89 */     if (par2 == 3) {
/*  90 */       return this.icon[8];
/*     */     }
/*  92 */     if (par2 == 4) {
/*  93 */       return this.icon[9];
/*     */     }
/*  95 */     if (par2 == 5) {
/*  96 */       return par1 > 1 ? this.icon[10] : this.icon[11];
/*     */     }
/*  98 */     if (par2 == 6) {
/*  99 */       return this.icon[12];
/*     */     }
/* 101 */     if (par2 == 7) {
/* 102 */       return this.icon[13];
/*     */     }
/* 104 */     if ((par2 == 9) || (par2 == 10)) {
/* 105 */       return par1 == 1 ? this.icon[16] : par2 == 9 ? this.icon[14] : par1 == 0 ? this.icon[13] : this.icon[15];
/*     */     }
/* 107 */     if ((par2 == 11) || (par2 == 13)) {
/* 108 */       return this.icon[17];
/*     */     }
/* 110 */     if (par2 == 12) {
/* 111 */       return this.icon[21];
/*     */     }
/* 113 */     if (par2 == 14) {
/* 114 */       return this.icon[25];
/*     */     }
/* 116 */     if (par2 == 15) {
/* 117 */       return par1 <= 1 ? this.icon[17] : this.icon[26];
/*     */     }
/* 119 */     return super.func_149691_a(par1, par2);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149673_e(IBlockAccess ba, int x, int y, int z, int side)
/*     */   {
/* 125 */     int md = ba.func_72805_g(x, y, z);
/* 126 */     if (((md == 0) || (md == 8)) && (side > 1) && (side < 100)) {
/* 127 */       if ((ba.func_147439_a(x, y + 1, z) == this) && ((ba.func_72805_g(x, y + 1, z) == 0) || (ba.func_72805_g(x, y + 1, z) == 8))) return this.icon[6];
/* 128 */       if ((ba.func_147439_a(x, y - 1, z) != this) || ((ba.func_72805_g(x, y - 1, z) != 0) && (ba.func_72805_g(x, y - 1, z) != 8))) return this.icon[1];
/* 129 */       return this.icon[(2 + Math.abs((side + x % 4 + z % 4 + y % 4) % 4))];
/*     */     }
/* 131 */     if ((md == 11) || (md == 13) || (side >= 100)) {
/* 132 */       String l = x + "" + y + "" + z;
/* 133 */       Random r1 = new Random(Math.abs(l.hashCode() * 100) + 1);
/* 134 */       int i = r1.nextInt(12345 + side) % 4;
/* 135 */       return this.icon[(17 + i)]; }
/* 136 */     if (md == 12) {
/* 137 */       switch (side) {
/* 138 */       case 0: case 1:  return this.icon[(21 + Math.abs(x % 2) + Math.abs(z % 2) * 2)];
/* 139 */       case 2: case 3:  return this.icon[(21 + Math.abs(x % 2) + Math.abs(y % 2) * 2)];
/* 140 */       case 4: case 5:  return this.icon[(21 + Math.abs(z % 2) + Math.abs(y % 2) * 2)];
/*     */       }
/*     */     }
/* 143 */     return super.func_149673_e(ba, x, y, z, side);
/*     */   }
/*     */   
/*     */   public void func_149719_a(IBlockAccess world, int i, int j, int k)
/*     */   {
/* 148 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public void func_149683_g()
/*     */   {
/* 153 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z)
/*     */   {
/* 159 */     int md = world.func_72805_g(x, y, z);
/* 160 */     return (md == 2) || (md == 3) || (md == 13) ? false : super.canCreatureSpawn(type, world, x, y, z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/* 169 */     par3List.add(new ItemStack(par1, 1, 0));
/* 170 */     par3List.add(new ItemStack(par1, 1, 1));
/* 171 */     par3List.add(new ItemStack(par1, 1, 2));
/* 172 */     par3List.add(new ItemStack(par1, 1, 3));
/* 173 */     par3List.add(new ItemStack(par1, 1, 4));
/* 174 */     par3List.add(new ItemStack(par1, 1, 5));
/* 175 */     par3List.add(new ItemStack(par1, 1, 6));
/* 176 */     par3List.add(new ItemStack(par1, 1, 7));
/* 177 */     par3List.add(new ItemStack(par1, 1, 8));
/* 178 */     par3List.add(new ItemStack(par1, 1, 9));
/* 179 */     par3List.add(new ItemStack(par1, 1, 11));
/* 180 */     par3List.add(new ItemStack(par1, 1, 12));
/* 181 */     par3List.add(new ItemStack(par1, 1, 14));
/* 182 */     par3List.add(new ItemStack(par1, 1, 15));
/*     */   }
/*     */   
/*     */   public float func_149712_f(World world, int x, int y, int z)
/*     */   {
/* 187 */     if (world.func_147439_a(x, y, z) != this) return 4.0F;
/* 188 */     int md = world.func_72805_g(x, y, z);
/* 189 */     if ((md <= 1) || (md == 8)) return 30.0F;
/* 190 */     if ((md == 4) || (md == 6) || (md == 7)) return 4.0F;
/* 191 */     return super.func_149712_f(world, x, y, z);
/*     */   }
/*     */   
/*     */   public int getLightValue(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 196 */     if (world.func_147439_a(x, y, z) != this) return 0;
/* 197 */     int md = world.func_72805_g(x, y, z);
/* 198 */     if (md == 2) return 9;
/* 199 */     if (md == 14) return 4;
/* 200 */     return super.getLightValue(world, x, y, z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
/*     */   {
/* 207 */     if (world.func_147439_a(x, y, z) != this) return 20.0F;
/* 208 */     int md = world.func_72805_g(x, y, z);
/* 209 */     if ((md <= 1) || (md == 8)) return 999.0F;
/* 210 */     if ((md == 4) || (md == 6) || (md == 7)) return 20.0F;
/* 211 */     return super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_149745_a(Random par1Random)
/*     */   {
/* 218 */     return 1;
/*     */   }
/*     */   
/*     */   public int func_149692_a(int par1)
/*     */   {
/* 223 */     return par1 == 10 ? 9 : par1 == 8 ? 1 : par1;
/*     */   }
/*     */   
/*     */   public void func_149724_b(World world, int x, int y, int z, Entity e)
/*     */   {
/* 228 */     if (world.func_147439_a(x, y, z) != this) return;
/* 229 */     int md = world.func_72805_g(x, y, z);
/* 230 */     if ((md == 2) && ((e instanceof EntityLivingBase))) {
/* 231 */       if (world.field_72995_K) {
/* 232 */         Thaumcraft.proxy.blockSparkle(world, x, y, z, 32768, 5);
/*     */       }
/* 234 */       ((EntityLivingBase)e).func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 40, 1));
/* 235 */       ((EntityLivingBase)e).func_70690_d(new PotionEffect(Potion.field_76430_j.field_76415_H, 40, 0));
/*     */     }
/* 237 */     super.func_149724_b(world, x, y, z, e);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean hasTileEntity(int metadata)
/*     */   {
/* 243 */     if (metadata == 3) return true;
/* 244 */     if (metadata == 8) return true;
/* 245 */     return super.hasTileEntity(metadata);
/*     */   }
/*     */   
/*     */ 
/*     */   public TileEntity createTileEntity(World world, int metadata)
/*     */   {
/* 251 */     if (metadata == 3) return new TileWardingStone();
/* 252 */     if (metadata == 8) return new TileNode();
/* 253 */     return super.createTileEntity(world, metadata);
/*     */   }
/*     */   
/*     */   public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
/*     */   {
/* 258 */     if (meta == 8) {
/* 259 */       Thaumcraft.proxy.burst(world, x + 0.5D, y + 0.5D, z + 0.5D, 1.0F);
/* 260 */       world.func_72980_b(x + 0.5D, y + 0.5D, z + 0.5D, "thaumcraft:craftfail", 1.0F, 1.0F, false);
/*     */     }
/* 262 */     return super.addDestroyEffects(world, x, y, z, meta, effectRenderer);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149681_a(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer)
/*     */   {
/* 268 */     if (par1World.func_147439_a(par2, par3, par4) != this) return;
/* 269 */     if ((par5 == 8) && (!par1World.field_72995_K))
/*     */     {
/* 271 */       TileEntity te = par1World.func_147438_o(par2, par3, par4);
/* 272 */       if ((te != null) && ((te instanceof INode)) && (((INode)te).getAspects().size() > 0)) {
/* 273 */         for (Aspect aspect : ((INode)te).getAspects().getAspects()) {
/* 274 */           for (int a = 0; a <= ((INode)te).getAspects().getAmount(aspect) / 10; a++)
/* 275 */             if (((INode)te).getAspects().getAmount(aspect) >= 5) {
/* 276 */               ItemStack ess = new ItemStack(ConfigItems.itemWispEssence);
/* 277 */               AspectList al = new AspectList();
/* 278 */               ((ItemWispEssence)ess.func_77973_b()).setAspects(ess, new AspectList().add(aspect, 2));
/*     */               
/* 280 */               func_149642_a(par1World, par2, par3, par4, ess);
/*     */             }
/*     */         }
/*     */       }
/*     */     }
/* 285 */     super.func_149681_a(par1World, par2, par3, par4, par5, par6EntityPlayer);
/*     */   }
/*     */   
/*     */   public void func_149734_b(World world, int x, int y, int z, Random random)
/*     */   {
/* 290 */     if (world.func_147439_a(x, y, z) != this) return;
/* 291 */     int md = world.func_72805_g(x, y, z);
/* 292 */     if (md == 3) {
/* 293 */       if (world.func_72864_z(x, y, z)) {
/* 294 */         for (int a = 0; a < Thaumcraft.proxy.particleCount(2); a++) {
/* 295 */           Thaumcraft.proxy.blockRunes(world, x, y + 0.7F, z, 0.2F + world.field_73012_v.nextFloat() * 0.4F, world.field_73012_v.nextFloat() * 0.3F, 0.8F + world.field_73012_v.nextFloat() * 0.2F, 20, -0.02F);
/*     */ 
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */       }
/* 302 */       else if (((world.func_147439_a(x, y + 1, z) != ConfigBlocks.blockAiry) && (world.func_147439_a(x, y + 1, z).func_149655_b(world, x, y + 1, z))) || ((world.func_147439_a(x, y + 2, z) != ConfigBlocks.blockAiry) && (world.func_147439_a(x, y + 1, z).func_149655_b(world, x, y + 1, z))))
/*     */       {
/*     */ 
/*     */ 
/* 306 */         for (int a = 0; a < Thaumcraft.proxy.particleCount(3); a++) {
/* 307 */           Thaumcraft.proxy.blockRunes(world, x, y + 0.7F, z, 0.9F + world.field_73012_v.nextFloat() * 0.1F, world.field_73012_v.nextFloat() * 0.3F, world.field_73012_v.nextFloat() * 0.3F, 24, -0.02F);
/*     */         }
/*     */         
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/* 315 */         List list = world.func_72839_b((Entity)null, AxisAlignedBB.func_72330_a(x, y, z, x + 1, y + 1, z + 1).func_72314_b(1.0D, 1.0D, 1.0D));
/*     */         
/*     */ 
/* 318 */         if (!list.isEmpty())
/*     */         {
/* 320 */           Iterator iterator = list.iterator();
/*     */           
/* 322 */           while (iterator.hasNext())
/*     */           {
/* 324 */             Entity entity = (Entity)iterator.next();
/*     */             
/* 326 */             if (((entity instanceof EntityLivingBase)) && (!(entity instanceof EntityPlayer)))
/*     */             {
/* 328 */               Thaumcraft.proxy.blockRunes(world, x, y + 0.6F + world.field_73012_v.nextFloat() * Math.max(0.8F, entity.func_70047_e()), z, 0.6F + world.field_73012_v.nextFloat() * 0.4F, 0.0F, 0.3F + world.field_73012_v.nextFloat() * 0.7F, 20, 0.0F);
/*     */               
/*     */ 
/*     */ 
/* 332 */               break;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
/*     */   {
/* 343 */     if (worldObj.func_147439_a(x, y, z) != this) return false;
/* 344 */     return true;
/*     */   }
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block block)
/*     */   {
/* 349 */     if (world.func_147439_a(x, y, z) == this) {
/* 350 */       int md = world.func_72805_g(x, y, z);
/* 351 */       if ((md == 9) && (world.func_72864_z(x, y, z))) {
/* 352 */         world.func_72921_c(x, y, z, 10, 3);
/*     */       }
/* 354 */       else if ((md == 10) && (!world.func_72864_z(x, y, z))) {
/* 355 */         world.func_72921_c(x, y, z, 9, 3);
/*     */       }
/*     */     }
/* 358 */     super.func_149695_a(world, x, y, z, block);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockCosmeticSolid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */