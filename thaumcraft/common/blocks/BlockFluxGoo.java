/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fluids.BlockFluidFinite;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.fx.particles.FXBubble;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.entities.monster.EntityThaumicSlime;
/*     */ import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
/*     */ 
/*     */ public class BlockFluxGoo extends BlockFluidFinite
/*     */ {
/*     */   public IIcon iconStill;
/*     */   public IIcon iconFlow;
/*     */   
/*     */   public BlockFluxGoo()
/*     */   {
/*  31 */     super(ConfigBlocks.FLUXGOO, Config.fluxGoomaterial);
/*  32 */     func_149672_a(new thaumcraft.common.lib.CustomSoundType("gore", 1.0F, 1.0F));
/*  33 */     func_149647_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */   static {
/*  37 */     defaultDisplacements.put(ConfigBlocks.blockTaintFibres, Boolean.valueOf(true));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister ir)
/*     */   {
/*  46 */     this.iconStill = ir.func_94245_a("thaumcraft:fluxgoo");
/*  47 */     this.iconFlow = ir.func_94245_a("thaumcraft:fluxgoo");
/*  48 */     ConfigBlocks.FLUXGOO.setIcons(this.iconStill, this.iconFlow);
/*     */   }
/*     */   
/*     */   public IIcon func_149691_a(int par1, int par2)
/*     */   {
/*  53 */     return this.iconStill;
/*     */   }
/*     */   
/*     */   public int getQuanta() {
/*  57 */     return this.quantaPerBlock;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149670_a(World world, int x, int y, int z, Entity entity)
/*     */   {
/*  63 */     int md = world.func_72805_g(x, y, z);
/*  64 */     if ((entity instanceof EntityThaumicSlime)) {
/*  65 */       EntityThaumicSlime slime = (EntityThaumicSlime)entity;
/*  66 */       if ((slime.getSlimeSize() < md) && (world.field_73012_v.nextBoolean())) {
/*  67 */         slime.setSlimeSize(slime.getSlimeSize() + 1);
/*  68 */         if (md > 1) {
/*  69 */           world.func_72921_c(x, y, z, md - 1, 3);
/*     */         } else {
/*  71 */           world.func_147468_f(x, y, z);
/*     */         }
/*     */       }
/*     */     } else {
/*  75 */       entity.field_70159_w *= (1.0F - getQuantaPercentage(world, x, y, z));
/*  76 */       entity.field_70179_y *= (1.0F - getQuantaPercentage(world, x, y, z));
/*     */       
/*  78 */       if ((entity instanceof EntityLivingBase)) {
/*  79 */         PotionEffect pe = new PotionEffect(Config.potionVisExhaustID, 600, md / 3, true);
/*  80 */         pe.getCurativeItems().clear();
/*  81 */         ((EntityLivingBase)entity).func_70690_d(pe);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand)
/*     */   {
/*  90 */     int meta = world.func_72805_g(x, y, z);
/*  91 */     if (rand.nextInt(50 - Thaumcraft.proxy.particleCount(10)) <= meta)
/*     */     {
/*  93 */       FXBubble fb = new FXBubble(world, x + rand.nextFloat(), y + 0.125F * meta, z + rand.nextFloat(), 0.0D, 0.0D, 0.0D, 0);
/*     */       
/*  95 */       fb.func_82338_g(0.25F);
/*  96 */       ParticleEngine.instance.addEffect(world, fb);
/*     */     }
/*     */     
/*  99 */     super.func_149734_b(world, x, y, z, rand);
/*     */   }
/*     */   
/*     */   public void func_149674_a(World world, int x, int y, int z, Random rand)
/*     */   {
/* 104 */     super.func_149674_a(world, x, y, z, rand);
/*     */     
/* 106 */     int meta = world.func_72805_g(x, y, z);
/* 107 */     if ((meta >= 2) && (meta < 6) && (world.func_147437_c(x, y + 1, z)) && (rand.nextInt(25) == 0))
/*     */     {
/* 109 */       world.func_147468_f(x, y, z);
/* 110 */       EntityThaumicSlime slime = new EntityThaumicSlime(world);
/* 111 */       slime.func_70012_b(x + 0.5F, y, z + 0.5F, 0.0F, 0.0F);
/* 112 */       slime.setSlimeSize(1);
/* 113 */       world.func_72838_d(slime);
/* 114 */       world.func_72956_a(slime, "thaumcraft:gore", 1.0F, 1.0F);
/* 115 */     } else if ((meta >= 6) && (world.func_147437_c(x, y + 1, z))) {
/* 116 */       if (rand.nextInt(25) == 0) {
/* 117 */         world.func_147468_f(x, y, z);
/* 118 */         EntityThaumicSlime slime = new EntityThaumicSlime(world);
/* 119 */         slime.func_70012_b(x + 0.5F, y, z + 0.5F, 0.0F, 0.0F);
/* 120 */         slime.setSlimeSize(2);
/* 121 */         world.func_72838_d(slime);
/* 122 */         world.func_72956_a(slime, "thaumcraft:gore", 1.0F, 1.0F);
/* 123 */       } else if ((Config.taintFromFlux) && (rand.nextInt(50) == 0)) {
/* 124 */         thaumcraft.common.lib.utils.Utils.setBiomeAt(world, x, z, ThaumcraftWorldGenerator.biomeTaint);
/*     */         
/* 126 */         world.func_147465_d(x, y, z, ConfigBlocks.blockTaintFibres, 0, 3);
/* 127 */         world.func_147452_c(x, y, z, ConfigBlocks.blockTaintFibres, 1, 0);
/*     */       }
/*     */     }
/* 130 */     else if (rand.nextInt(30) == 0) {
/* 131 */       if (meta == 0) {
/* 132 */         world.func_147468_f(x, y, z);
/*     */       } else {
/* 134 */         world.func_72921_c(x, y, z, meta - 1, 3);
/* 135 */         if ((rand.nextBoolean()) && (world.func_147437_c(x, y + 1, z))) {
/* 136 */           world.func_147465_d(x, y + 1, z, ConfigBlocks.blockFluxGas, 0, 3);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
/* 143 */     int meta = world.func_72805_g(x, y, z);
/* 144 */     return meta < 2;
/*     */   }
/*     */   
/*     */   public boolean isInsideOfMaterial(World worldObj, Entity entity) {
/* 148 */     double d0 = entity.field_70163_u + entity.func_70047_e();
/* 149 */     int i = MathHelper.func_76128_c(entity.field_70165_t);
/* 150 */     int j = MathHelper.func_76141_d(MathHelper.func_76128_c(d0));
/* 151 */     int k = MathHelper.func_76128_c(entity.field_70161_v);
/* 152 */     Block l = worldObj.func_147439_a(i, j, k);
/*     */     
/* 154 */     if (l.func_149688_o() == this.field_149764_J) {
/* 155 */       float f = getQuantaPercentage(worldObj, i, j, k) - 0.11111111F;
/* 156 */       float f1 = j + 1 - f;
/* 157 */       return d0 < f1;
/*     */     }
/* 159 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockFluxGoo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */