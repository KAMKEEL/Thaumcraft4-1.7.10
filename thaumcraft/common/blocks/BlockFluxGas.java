/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fluids.BlockFluidFinite;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ 
/*     */ public class BlockFluxGas extends BlockFluidFinite
/*     */ {
/*     */   public IIcon iconStill;
/*     */   public IIcon iconFlow;
/*     */   
/*     */   public BlockFluxGas()
/*     */   {
/*  26 */     super(ConfigBlocks.FLUXGAS, Config.fluxGoomaterial);
/*  27 */     func_149647_a(Thaumcraft.tabTC);
/*  28 */     this.densityDir = 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir)
/*     */   {
/*  37 */     this.iconStill = ir.func_94245_a("thaumcraft:fluxgas");
/*  38 */     this.iconFlow = ir.func_94245_a("thaumcraft:fluxgas");
/*  39 */     ConfigBlocks.FLUXGAS.setIcons(this.iconStill, this.iconFlow);
/*     */   }
/*     */   
/*     */   public int getDensityDir()
/*     */   {
/*  44 */     return this.densityDir;
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/*  49 */     return ConfigBlocks.blockFluxGasRI;
/*     */   }
/*     */   
/*     */   public void setDensityDir(int densityDir) {
/*  53 */     this.densityDir = densityDir;
/*     */   }
/*     */   
/*     */   public IIcon func_149691_a(int par1, int par2)
/*     */   {
/*  58 */     return this.iconStill;
/*     */   }
/*     */   
/*     */   public int getQuanta() {
/*  62 */     return this.quantaPerBlock;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149670_a(World world, int x, int y, int z, Entity entity)
/*     */   {
/*  68 */     int md = world.func_72805_g(x, y, z);
/*  69 */     if ((world.field_73012_v.nextInt(10) == 0) && ((entity instanceof EntityLivingBase)) && (!(entity instanceof thaumcraft.api.entities.ITaintedMob)) && (!((EntityLivingBase)entity).func_70662_br()) && (!((EntityLivingBase)entity).func_82165_m(Config.potionVisExhaustID)) && (!((EntityLivingBase)entity).func_82165_m(Potion.field_76431_k.field_76415_H)))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*  74 */       if (world.field_73012_v.nextBoolean()) {
/*  75 */         PotionEffect pe = new PotionEffect(Config.potionVisExhaustID, 1200, md / 3, true);
/*  76 */         pe.getCurativeItems().clear();
/*  77 */         ((EntityLivingBase)entity).func_70690_d(pe);
/*     */       } else {
/*  79 */         ((EntityLivingBase)entity).func_70690_d(new PotionEffect(Potion.field_76431_k.field_76415_H, 80 + md * 20, 0, false));
/*     */       }
/*     */       
/*  82 */       if (md > 0) {
/*  83 */         world.func_72921_c(x, y, z, md - 1, 3);
/*     */       } else {
/*  85 */         world.func_147468_f(x, y, z);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149674_a(World world, int x, int y, int z, Random rand)
/*     */   {
/*  93 */     super.func_149674_a(world, x, y, z, rand);
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
/*     */ 
/*     */ 
/*     */   public boolean isReplaceable(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 112 */     int meta = world.func_72805_g(x, y, z);
/* 113 */     return meta < 2;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isInsideOfMaterial(World worldObj, Entity entity)
/*     */   {
/* 119 */     double d0 = entity.field_70163_u + entity.func_70047_e();
/* 120 */     int i = MathHelper.func_76128_c(entity.field_70165_t);
/* 121 */     int j = MathHelper.func_76141_d(MathHelper.func_76128_c(d0));
/* 122 */     int k = MathHelper.func_76128_c(entity.field_70161_v);
/* 123 */     Block l = worldObj.func_147439_a(i, j, k);
/*     */     
/* 125 */     if (l.func_149688_o() == this.field_149764_J)
/*     */     {
/* 127 */       float f = getQuantaPercentage(worldObj, i, j, k) - 0.11111111F;
/* 128 */       float f1 = j + 1 - f;
/* 129 */       return d0 < f1;
/*     */     }
/*     */     
/*     */ 
/* 133 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockFluxGas.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */