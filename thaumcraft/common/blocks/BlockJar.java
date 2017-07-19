/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockContainer;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EffectRenderer;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.particle.EntitySpellParticleFX;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityXPOrb;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.tiles.TileJarBrain;
/*     */ import thaumcraft.common.tiles.TileJarFillable;
/*     */ import thaumcraft.common.tiles.TileJarFillableVoid;
/*     */ import thaumcraft.common.tiles.TileJarNode;
/*     */ 
/*     */ public class BlockJar extends BlockContainer
/*     */ {
/*     */   public IIcon iconLiquid;
/*     */   public IIcon iconJarBottom;
/*     */   public IIcon iconJarSide;
/*     */   public IIcon iconJarTop;
/*     */   public IIcon iconJarTopVoid;
/*     */   public IIcon iconJarSideVoid;
/*     */   
/*     */   public BlockJar()
/*     */   {
/*  49 */     super(net.minecraft.block.material.Material.field_151592_s);
/*  50 */     func_149711_c(0.3F);
/*  51 */     func_149672_a(new CustomStepSound("jar", 1.0F, 1.0F));
/*  52 */     func_149647_a(thaumcraft.common.Thaumcraft.tabTC);
/*  53 */     func_149715_a(0.66F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir)
/*     */   {
/*  65 */     this.iconLiquid = ir.func_94245_a("thaumcraft:animatedglow");
/*  66 */     this.iconJarSide = ir.func_94245_a("thaumcraft:jar_side");
/*  67 */     this.iconJarTop = ir.func_94245_a("thaumcraft:jar_top");
/*  68 */     this.iconJarTopVoid = ir.func_94245_a("thaumcraft:jar_top_void");
/*  69 */     this.iconJarSideVoid = ir.func_94245_a("thaumcraft:jar_side_void");
/*  70 */     this.iconJarBottom = ir.func_94245_a("thaumcraft:jar_bottom");
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int side, int meta)
/*     */   {
/*  77 */     if ((meta == 0) || (meta == 1) || (meta == 2)) {
/*  78 */       return side == 1 ? this.iconJarTop : side == 0 ? this.iconJarBottom : this.iconJarSide;
/*     */     }
/*  80 */     if (meta == 3) {
/*  81 */       return side == 1 ? this.iconJarTopVoid : side == 0 ? this.iconJarBottom : this.iconJarSideVoid;
/*     */     }
/*  83 */     return this.iconJarBottom;
/*     */   }
/*     */   
/*     */   public int func_149701_w()
/*     */   {
/*  88 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  95 */     par3List.add(new ItemStack(par1, 1, 0));
/*  96 */     par3List.add(new ItemStack(par1, 1, 1));
/*  97 */     par3List.add(new ItemStack(par1, 1, 3));
/*     */   }
/*     */   
/*     */ 
/*     */   public TileEntity createTileEntity(World world, int metadata)
/*     */   {
/* 103 */     if (metadata == 0) return new TileJarFillable();
/* 104 */     if (metadata == 1) return new TileJarBrain();
/* 105 */     if (metadata == 2) return new TileJarNode();
/* 106 */     if (metadata == 3) return new TileJarFillableVoid();
/* 107 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/* 113 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/* 119 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149645_b()
/*     */   {
/* 125 */     return thaumcraft.common.config.ConfigBlocks.blockJarRI;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149681_a(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer)
/*     */   {
/* 131 */     func_149697_b(par1World, par2, par3, par4, par5, 0);
/* 132 */     super.func_149681_a(par1World, par2, par3, par4, par5, par6EntityPlayer);
/*     */   }
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
/*     */   {
/* 137 */     ArrayList<ItemStack> drops = new ArrayList();
/* 138 */     int md = world.func_72805_g(x, y, z);
/* 139 */     if ((md == 0) || (md == 3)) {
/* 140 */       TileEntity te = world.func_147438_o(x, y, z);
/* 141 */       if ((te != null) && ((te instanceof TileJarFillable))) {
/* 142 */         ItemStack drop = new ItemStack(ConfigItems.itemJarFilled);
/*     */         
/* 144 */         if ((((TileJarFillable)te).amount <= 0) && (((TileJarFillable)te).aspectFilter == null)) {
/* 145 */           drop = new ItemStack(this);
/*     */         }
/*     */         
/* 148 */         if ((te instanceof TileJarFillableVoid)) {
/* 149 */           drop.func_77964_b(3);
/*     */         }
/*     */         
/* 152 */         if (((TileJarFillable)te).amount > 0) {
/* 153 */           ((ItemJarFilled)drop.func_77973_b()).setAspects(drop, new AspectList().add(((TileJarFillable)te).aspect, ((TileJarFillable)te).amount));
/*     */         }
/* 155 */         if (((TileJarFillable)te).aspectFilter != null) {
/* 156 */           if (!drop.func_77942_o()) drop.func_77982_d(new NBTTagCompound());
/* 157 */           drop.field_77990_d.func_74778_a("AspectFilter", ((TileJarFillable)te).aspectFilter.getTag());
/*     */         }
/* 159 */         drops.add(drop);
/*     */       }
/* 161 */       return drops;
/*     */     }
/* 163 */     if (md == 2) {
/* 164 */       TileEntity te = world.func_147438_o(x, y, z);
/* 165 */       if ((te != null) && ((te instanceof TileJarNode)) && (((TileJarNode)te).drop) && 
/* 166 */         (((TileJarNode)te).getAspects() != null)) {
/* 167 */         ItemStack drop = new ItemStack(ConfigItems.itemJarNode);
/* 168 */         ((ItemJarNode)drop.func_77973_b()).setAspects(drop, ((TileJarNode)te).getAspects().copy());
/*     */         
/* 170 */         ((ItemJarNode)drop.func_77973_b()).setNodeAttributes(drop, ((TileJarNode)te).getNodeType(), ((TileJarNode)te).getNodeModifier(), ((TileJarNode)te).getId());
/*     */         
/*     */ 
/*     */ 
/* 174 */         drops.add(drop);
/*     */       }
/*     */       
/* 177 */       return drops;
/*     */     }
/* 179 */     return super.getDrops(world, x, y, z, metadata, fortune);
/*     */   }
/*     */   
/*     */   public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6)
/*     */   {
/* 184 */     int md = par1World.func_72805_g(par2, par3, par4);
/*     */     
/* 186 */     if ((md == 1) && 
/* 187 */       (!par1World.field_72995_K)) {
/* 188 */       TileEntity te = par1World.func_147438_o(par2, par3, par4);
/* 189 */       if ((te != null) && ((te instanceof TileJarBrain))) {
/* 190 */         int xp = ((TileJarBrain)te).xp;
/* 191 */         while (xp > 0)
/*     */         {
/* 193 */           int var2 = EntityXPOrb.func_70527_a(xp);
/* 194 */           xp -= var2;
/* 195 */           par1World.func_72838_d(new EntityXPOrb(par1World, par2, par3, par4, var2));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 201 */     super.func_149749_a(par1World, par2, par3, par4, par5, par6);
/*     */   }
/*     */   
/*     */   public int func_149692_a(int par1)
/*     */   {
/* 206 */     return par1;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
/*     */   {
/* 213 */     if (meta == 15) {
/* 214 */       return false;
/*     */     }
/* 216 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149689_a(World world, int par2, int par3, int par4, EntityLivingBase ent, ItemStack stack)
/*     */   {
/* 222 */     int l = MathHelper.func_76128_c(ent.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
/*     */     
/* 224 */     TileEntity tile = world.func_147438_o(par2, par3, par4);
/* 225 */     if ((tile instanceof TileJarFillable)) {
/* 226 */       if (l == 0)
/*     */       {
/* 228 */         ((TileJarFillable)tile).facing = 2;
/*     */       }
/*     */       
/* 231 */       if (l == 1)
/*     */       {
/* 233 */         ((TileJarFillable)tile).facing = 5;
/*     */       }
/*     */       
/* 236 */       if (l == 2)
/*     */       {
/* 238 */         ((TileJarFillable)tile).facing = 3;
/*     */       }
/*     */       
/* 241 */       if (l == 3)
/*     */       {
/* 243 */         ((TileJarFillable)tile).facing = 4;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float what, float these, float are)
/*     */   {
/* 252 */     TileEntity te = world.func_147438_o(x, y, z);
/* 253 */     if ((te != null) && ((te instanceof TileJarBrain))) {
/* 254 */       ((TileJarBrain)te).eatDelay = 40;
/* 255 */       if (!world.field_72995_K)
/*     */       {
/* 257 */         int var6 = world.field_73012_v.nextInt(Math.min(((TileJarBrain)te).xp + 1, 64));
/* 258 */         if (var6 > 0) {
/* 259 */           ((TileJarBrain)te).xp -= var6;
/* 260 */           int xp = var6;
/* 261 */           while (xp > 0)
/*     */           {
/* 263 */             int var2 = EntityXPOrb.func_70527_a(xp);
/* 264 */             xp -= var2;
/* 265 */             world.func_72838_d(new EntityXPOrb(world, x + 0.5D, y + 0.5D, z + 0.5D, var2));
/*     */           }
/* 267 */           world.func_147471_g(x, y, z);
/* 268 */           te.func_70296_d();
/*     */         }
/*     */       } else {
/* 271 */         world.func_72980_b(x + 0.5F, y + 0.5F, z + 0.5F, "thaumcraft:jar", 0.2F, 1.0F, false);
/*     */       }
/*     */     }
/*     */     
/* 275 */     if ((te != null) && ((te instanceof TileJarFillable)) && (player.func_70093_af()) && (((TileJarFillable)te).aspectFilter != null) && (side == ((TileJarFillable)te).facing))
/*     */     {
/* 277 */       ((TileJarFillable)te).aspectFilter = null;
/* 278 */       if (world.field_72995_K) {
/* 279 */         world.func_72980_b(x + 0.5F, y + 0.5F, z + 0.5F, "thaumcraft:page", 1.0F, 1.0F, false);
/*     */       } else {
/* 281 */         ForgeDirection fd = ForgeDirection.getOrientation(side);
/* 282 */         world.func_72838_d(new net.minecraft.entity.item.EntityItem(world, x + 0.5F + fd.offsetX / 3.0F, y + 0.5F, z + 0.5F + fd.offsetZ / 3.0F, new ItemStack(ConfigItems.itemResource, 1, 13)));
/*     */       }
/*     */       
/*     */ 
/*     */     }
/* 287 */     else if ((te != null) && ((te instanceof TileJarFillable)) && (player.func_70093_af()) && (player.func_70694_bm() == null)) {
/* 288 */       ((TileJarFillable)te).amount = 0;
/* 289 */       if (((TileJarFillable)te).aspectFilter == null)
/* 290 */         ((TileJarFillable)te).aspect = null;
/* 291 */       if (world.field_72995_K) {
/* 292 */         world.func_72980_b(x + 0.5F, y + 0.5F, z + 0.5F, "thaumcraft:jar", 0.4F, 1.0F, false);
/* 293 */         world.func_72980_b(x + 0.5F, y + 0.5F, z + 0.5F, "game.neutral.swim", 0.5F, 1.0F + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.3F, false);
/*     */       }
/*     */       
/*     */     }
/* 297 */     else if ((te != null) && ((te instanceof TileJarFillable)) && (player.func_70694_bm() != null) && (((TileJarFillable)te).aspectFilter == null) && (player.func_70694_bm().func_77973_b() == ConfigItems.itemResource) && (player.func_70694_bm().func_77960_j() == 13))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 302 */       if ((((TileJarFillable)te).amount == 0) && (((IEssentiaContainerItem)player.func_70694_bm().func_77973_b()).getAspects(player.func_70694_bm()) == null)) {
/* 303 */         return true;
/*     */       }
/*     */       
/* 306 */       if ((((TileJarFillable)te).amount == 0) && (((IEssentiaContainerItem)player.func_70694_bm().func_77973_b()).getAspects(player.func_70694_bm()) != null)) {
/* 307 */         ((TileJarFillable)te).aspect = ((IEssentiaContainerItem)(IEssentiaContainerItem)player.func_70694_bm().func_77973_b()).getAspects(player.func_70694_bm()).getAspects()[0];
/*     */       }
/*     */       
/* 310 */       player.func_70694_bm().field_77994_a -= 1;
/* 311 */       func_149689_a(world, x, y, z, player, null);
/* 312 */       ((TileJarFillable)te).aspectFilter = ((TileJarFillable)te).aspect;
/* 313 */       if (world.field_72995_K) {
/* 314 */         world.func_72980_b(x + 0.5F, y + 0.5F, z + 0.5F, "thaumcraft:jar", 0.4F, 1.0F, false);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 319 */     return true;
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World var1, int md)
/*     */   {
/* 324 */     return null;
/*     */   }
/*     */   
/*     */   public void func_149719_a(IBlockAccess world, int i, int j, int k)
/*     */   {
/* 329 */     func_149676_a(0.1875F, 0.0F, 0.1875F, 0.8125F, 0.75F, 0.8125F);
/* 330 */     super.func_149719_a(world, i, j, k);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist, Entity par7Entity)
/*     */   {
/* 336 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 337 */     super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
/*     */   }
/*     */   
/*     */ 
/*     */   public float getEnchantPowerBonus(World world, int x, int y, int z)
/*     */   {
/* 343 */     TileEntity te = world.func_147438_o(x, y, z);
/* 344 */     if ((te != null) && ((te instanceof TileJarBrain))) {
/* 345 */       return 2.0F;
/*     */     }
/* 347 */     return super.getEnchantPowerBonus(world, x, y, z);
/*     */   }
/*     */   
/*     */   public int getLightValue(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 352 */     int md = world.func_72805_g(x, y, z);
/* 353 */     if (md == 2) {
/* 354 */       return 11;
/*     */     }
/* 356 */     return super.getLightValue(world, x, y, z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand)
/*     */   {
/* 364 */     TileEntity tile = world.func_147438_o(x, y, z);
/* 365 */     if ((tile != null) && ((tile instanceof TileJarBrain)) && (((TileJarBrain)tile).xp >= ((TileJarBrain)tile).xpMax)) {
/* 366 */       double xx = x + 0.3D + rand.nextFloat() * 0.4F;
/* 367 */       double yy = y + 0.9D;
/* 368 */       double zz = z + 0.3D + rand.nextFloat() * 0.4F;
/* 369 */       EntitySpellParticleFX var21 = new EntitySpellParticleFX(world, xx, yy, zz, 0.0D, 0.0D, 0.0D);
/* 370 */       var21.func_82338_g(0.5F);
/* 371 */       var21.func_70538_b(0.0F, 0.4F + world.field_73012_v.nextFloat() * 0.1F, 0.3F + world.field_73012_v.nextFloat() * 0.2F);
/* 372 */       Minecraft.func_71410_x().field_71452_i.func_78873_a(var21);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_149740_M()
/*     */   {
/* 378 */     return true;
/*     */   }
/*     */   
/*     */   public int func_149736_g(World world, int x, int y, int z, int rs)
/*     */   {
/* 383 */     TileEntity tile = world.func_147438_o(x, y, z);
/* 384 */     if ((tile != null) && ((tile instanceof TileJarBrain))) {
/* 385 */       float r = ((TileJarBrain)tile).xp / ((TileJarBrain)tile).xpMax;
/* 386 */       return MathHelper.func_76141_d(r * 14.0F) + (((TileJarBrain)tile).xp > 0 ? 1 : 0);
/*     */     }
/* 388 */     if ((tile != null) && ((tile instanceof TileJarFillable))) {
/* 389 */       float r = ((TileJarFillable)tile).amount / ((TileJarFillable)tile).maxAmount;
/* 390 */       return MathHelper.func_76141_d(r * 14.0F) + (((TileJarFillable)tile).amount > 0 ? 1 : 0);
/*     */     }
/* 392 */     return super.func_149736_g(world, x, y, z, rs);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockJar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */