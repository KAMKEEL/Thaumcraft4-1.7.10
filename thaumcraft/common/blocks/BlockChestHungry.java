/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockContainer;
/*     */ import net.minecraft.block.BlockPistonBase;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.tiles.TileChestHungry;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockChestHungry
/*     */   extends BlockContainer
/*     */ {
/*  37 */   private Random random = new Random();
/*     */   public IIcon icon;
/*     */   
/*     */   public BlockChestHungry() {
/*  41 */     super(Material.field_151575_d);
/*  42 */     func_149711_c(2.5F);
/*  43 */     func_149672_a(field_149766_f);
/*  44 */     func_149647_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir)
/*     */   {
/*  51 */     this.icon = ir.func_94245_a("thaumcraft:woodplain");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int side, int meta) {
/*  56 */     return this.icon;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149662_c()
/*     */   {
/*  62 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149686_d()
/*     */   {
/*  68 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149645_b()
/*     */   {
/*  74 */     return ConfigBlocks.blockChestHungryRI;
/*     */   }
/*     */   
/*     */   public boolean func_149740_M()
/*     */   {
/*  79 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149736_g(World world, int x, int y, int z, int rs)
/*     */   {
/*  85 */     TileEntity te = world.func_147438_o(x, y, z);
/*  86 */     if ((te != null) && ((te instanceof IInventory)))
/*  87 */       return Container.func_94526_b((IInventory)te);
/*  88 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack is)
/*     */   {
/*  94 */     int var6 = par1World.func_72805_g(par2, par3, par4) & 0x3;
/*  95 */     int var7 = BlockPistonBase.func_150071_a(par1World, par2, par3, par4, (EntityPlayer)par5EntityLiving);
/*     */     
/*  97 */     par1World.func_147465_d(par2, par3, par4, this, var7, 3);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6)
/*     */   {
/* 103 */     TileChestHungry var7 = (TileChestHungry)par1World.func_147438_o(par2, par3, par4);
/*     */     
/* 105 */     if (var7 != null)
/*     */     {
/* 107 */       for (int var8 = 0; var8 < var7.func_70302_i_(); var8++)
/*     */       {
/* 109 */         ItemStack var9 = var7.func_70301_a(var8);
/*     */         
/* 111 */         if (var9 != null)
/*     */         {
/* 113 */           float var10 = this.random.nextFloat() * 0.8F + 0.1F;
/* 114 */           float var11 = this.random.nextFloat() * 0.8F + 0.1F;
/*     */           
/*     */           EntityItem var14;
/* 117 */           for (float var12 = this.random.nextFloat() * 0.8F + 0.1F; var9.field_77994_a > 0; par1World.func_72838_d(var14))
/*     */           {
/* 119 */             int var13 = this.random.nextInt(21) + 10;
/*     */             
/* 121 */             if (var13 > var9.field_77994_a)
/*     */             {
/* 123 */               var13 = var9.field_77994_a;
/*     */             }
/*     */             
/* 126 */             var9.field_77994_a -= var13;
/* 127 */             var14 = new EntityItem(par1World, par2 + var10, par3 + var11, par4 + var12, new ItemStack(var9.func_77973_b(), var13, var9.func_77960_j()));
/* 128 */             float var15 = 0.05F;
/* 129 */             var14.field_70159_w = ((float)this.random.nextGaussian() * var15);
/* 130 */             var14.field_70181_x = ((float)this.random.nextGaussian() * var15 + 0.2F);
/* 131 */             var14.field_70179_y = ((float)this.random.nextGaussian() * var15);
/*     */             
/* 133 */             if (var9.func_77942_o())
/*     */             {
/* 135 */               var14.func_92059_d().func_77982_d((NBTTagCompound)var9.func_77978_p().func_74737_b());
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 142 */     super.func_149749_a(par1World, par2, par3, par4, par5, par6);
/*     */   }
/*     */   
/*     */ 
/*     */   public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4)
/*     */   {
/* 148 */     float var5 = 0.0625F;
/* 149 */     return AxisAlignedBB.func_72330_a(par2 + var5, par3, par4 + var5, par2 + 1 - var5, par3 + 1 - var5, par4 + 1 - var5);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
/*     */   {
/* 155 */     func_149676_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
/*     */   }
/*     */   
/*     */   public void func_149670_a(World world, int x, int y, int z, Entity entity)
/*     */   {
/* 160 */     Object var10 = (TileChestHungry)world.func_147438_o(x, y, z);
/* 161 */     if (var10 == null)
/*     */     {
/* 163 */       return;
/*     */     }
/* 165 */     if (world.field_72995_K) {
/* 166 */       return;
/*     */     }
/*     */     
/* 169 */     if (((entity instanceof EntityItem)) && (!entity.field_70128_L)) {
/* 170 */       ItemStack leftovers = InventoryUtils.placeItemStackIntoInventory(((EntityItem)entity).func_92059_d(), (IInventory)var10, 1, true);
/* 171 */       if ((leftovers == null) || (leftovers.field_77994_a != ((EntityItem)entity).func_92059_d().field_77994_a)) {
/* 172 */         world.func_72956_a(entity, "random.eat", 0.25F, (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.2F + 1.0F);
/* 173 */         world.func_147452_c(x, y, z, ConfigBlocks.blockChestHungry, 2, 2);
/*     */       }
/*     */       
/* 176 */       if (leftovers != null) {
/* 177 */         ((EntityItem)entity).func_92058_a(leftovers);
/*     */       } else {
/* 179 */         entity.func_70106_y();
/*     */       }
/* 181 */       ((TileChestHungry)var10).func_70296_d();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
/*     */   {
/* 188 */     Object var10 = (TileChestHungry)par1World.func_147438_o(par2, par3, par4);
/* 189 */     if (var10 == null)
/*     */     {
/* 191 */       return true;
/*     */     }
/*     */     
/* 194 */     if (par1World.field_72995_K)
/*     */     {
/* 196 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 200 */     par5EntityPlayer.func_71007_a((IInventory)var10);
/* 201 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public TileEntity func_149915_a(World par1World, int m)
/*     */   {
/* 208 */     return new TileChestHungry();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockChestHungry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */