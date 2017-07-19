/*    */ package thaumcraft.common.blocks;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fluids.BlockFluidFinite;
/*    */ import thaumcraft.client.fx.ParticleEngine;
/*    */ import thaumcraft.client.fx.particles.FXSlimyBubble;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ 
/*    */ public class BlockFluidDeath extends BlockFluidFinite
/*    */ {
/*    */   public IIcon iconStill;
/*    */   public IIcon iconFlow;
/*    */   
/*    */   public BlockFluidDeath()
/*    */   {
/* 23 */     super(ConfigBlocks.FLUIDDEATH, net.minecraft.block.material.Material.field_151586_h);
/* 24 */     func_149647_a(Thaumcraft.tabTC);
/* 25 */     setQuantaPerBlock(4);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149651_a(IIconRegister ir)
/*    */   {
/* 34 */     this.iconStill = ir.func_94245_a("thaumcraft:fluiddeath");
/* 35 */     this.iconFlow = ir.func_94245_a("thaumcraft:fluiddeath");
/* 36 */     ConfigBlocks.FLUIDDEATH.setIcons(this.iconStill, this.iconFlow);
/*    */   }
/*    */   
/*    */   public IIcon func_149691_a(int par1, int par2)
/*    */   {
/* 41 */     return this.iconStill;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_149670_a(World world, int x, int y, int z, Entity entity)
/*    */   {
/* 48 */     if ((!world.field_72995_K) && ((entity instanceof net.minecraft.entity.EntityLivingBase))) {
/* 49 */       entity.func_70097_a(thaumcraft.api.damagesource.DamageSourceThaumcraft.dissolve, world.func_72805_g(x, y, z) + 1);
/*    */     }
/*    */   }
/*    */   
/*    */   public int getQuanta() {
/* 54 */     return this.quantaPerBlock;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149734_b(World world, int x, int y, int z, Random rand)
/*    */   {
/* 60 */     int meta = world.func_72805_g(x, y, z);
/*    */     
/* 62 */     float h = rand.nextFloat() * 0.075F;
/* 63 */     FXSlimyBubble ef = new FXSlimyBubble(world, x + rand.nextFloat(), y + 0.1F + 0.225F * meta, z + rand.nextFloat(), 0.075F + h);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 69 */     ef.func_82338_g(0.8F);
/* 70 */     ef.func_70538_b(0.3F - rand.nextFloat() * 0.1F, 0.0F, 0.4F + rand.nextFloat() * 0.1F);
/*    */     
/* 72 */     ParticleEngine.instance.addEffect(world, ef);
/*    */     
/* 74 */     if (rand.nextInt(50) == 0) {
/* 75 */       double var21 = x + rand.nextFloat();
/* 76 */       double var22 = y + this.field_149756_F;
/* 77 */       double var23 = z + rand.nextFloat();
/* 78 */       world.func_72980_b(var21, var22, var23, "liquid.lavapop", 0.1F + rand.nextFloat() * 0.1F, 0.9F + rand.nextFloat() * 0.15F, false);
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 83 */     super.func_149734_b(world, x, y, z, rand);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockFluidDeath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */