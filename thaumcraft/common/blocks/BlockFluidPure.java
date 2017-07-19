/*    */ package thaumcraft.common.blocks;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fluids.BlockFluidClassic;
/*    */ import thaumcraft.client.fx.ParticleEngine;
/*    */ import thaumcraft.client.fx.particles.FXBubble;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.config.Config;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ 
/*    */ public class BlockFluidPure extends BlockFluidClassic
/*    */ {
/*    */   public IIcon iconStill;
/*    */   public IIcon iconFlow;
/*    */   
/*    */   public BlockFluidPure()
/*    */   {
/* 25 */     super(ConfigBlocks.FLUIDPURE, net.minecraft.block.material.Material.field_151586_h);
/* 26 */     func_149647_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149651_a(IIconRegister ir)
/*    */   {
/* 35 */     this.iconStill = ir.func_94245_a("thaumcraft:fluidpure");
/* 36 */     this.iconFlow = ir.func_94245_a("thaumcraft:fluidpure");
/* 37 */     ConfigBlocks.FLUIDPURE.setIcons(this.iconStill, this.iconFlow);
/*    */   }
/*    */   
/*    */   public IIcon func_149691_a(int par1, int par2)
/*    */   {
/* 42 */     return this.iconStill;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void func_149670_a(World world, int x, int y, int z, Entity entity)
/*    */   {
/* 49 */     if ((!world.field_72995_K) && (isSourceBlock(world, x, y, z)) && ((entity instanceof EntityPlayer)) && (!((EntityPlayer)entity).func_82165_m(Config.potionWarpWardID)))
/*    */     {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 55 */       int warp = Thaumcraft.proxy.getPlayerKnowledge().getWarpPerm(((EntityPlayer)entity).func_70005_c_());
/*    */       
/* 57 */       int div = 1;
/* 58 */       if (warp > 0) {
/* 59 */         div = (int)Math.sqrt(warp);
/* 60 */         if (div < 1)
/* 61 */           div = 1;
/*    */       }
/* 63 */       ((EntityPlayer)entity).func_70690_d(new net.minecraft.potion.PotionEffect(Config.potionWarpWardID, Math.min(32000, 200000 / div), 0, true));
/*    */       
/*    */ 
/* 66 */       world.func_147468_f(x, y, z);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149734_b(World world, int x, int y, int z, Random rand)
/*    */   {
/* 74 */     int meta = world.func_72805_g(x, y, z);
/*    */     
/* 76 */     FXBubble fb = new FXBubble(world, x + rand.nextFloat(), y + 0.125F * (8 - meta), z + rand.nextFloat(), 0.0D, 0.0D, 0.0D, 0);
/*    */     
/* 78 */     fb.func_82338_g(0.25F);
/* 79 */     fb.setRGB(1.0F, 1.0F, 1.0F);
/* 80 */     ParticleEngine.instance.addEffect(world, fb);
/*    */     
/* 82 */     if (rand.nextInt(25) == 0) {
/* 83 */       double var21 = x + rand.nextFloat();
/* 84 */       double var22 = y + this.field_149756_F;
/* 85 */       double var23 = z + rand.nextFloat();
/* 86 */       world.func_72980_b(var21, var22, var23, "liquid.lavapop", 0.1F + rand.nextFloat() * 0.1F, 0.9F + rand.nextFloat() * 0.15F, false);
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 91 */     super.func_149734_b(world, x, y, z, rand);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockFluidPure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */