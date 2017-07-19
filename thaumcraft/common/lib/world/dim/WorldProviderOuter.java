/*     */ package thaumcraft.common.lib.world.dim;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.biome.WorldChunkManagerHell;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
/*     */ 
/*     */ public class WorldProviderOuter extends WorldProvider
/*     */ {
/*     */   public String func_80007_l()
/*     */   {
/*  21 */     return "The Outer Lands";
/*     */   }
/*     */   
/*     */ 
/*     */   public String getWelcomeMessage()
/*     */   {
/*  27 */     return "Entering The Outer Lands";
/*     */   }
/*     */   
/*     */ 
/*     */   public String getDepartMessage()
/*     */   {
/*  33 */     return "Leaving The Outer Lands";
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean shouldMapSpin(String entity, double x, double y, double z)
/*     */   {
/*  39 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canBlockFreeze(int x, int y, int z, boolean byWater)
/*     */   {
/*  45 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canSnowAt(int x, int y, int z, boolean checkLight)
/*     */   {
/*  51 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canDoLightning(Chunk chunk)
/*     */   {
/*  57 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean canDoRainSnowIce(Chunk chunk)
/*     */   {
/*  63 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_76572_b()
/*     */   {
/*  70 */     this.field_76578_c = new WorldChunkManagerHell(ThaumcraftWorldGenerator.biomeEldritchLands, 0.0F);
/*  71 */     this.field_76574_g = Config.dimensionOuterId;
/*  72 */     this.field_76576_e = true;
/*     */   }
/*     */   
/*     */ 
/*     */   public IChunkProvider func_76555_c()
/*     */   {
/*  78 */     return new ChunkProviderOuter(this.field_76579_a, this.field_76579_a.func_72905_C(), true);
/*     */   }
/*     */   
/*     */ 
/*     */   public float func_76563_a(long p_76563_1_, float p_76563_3_)
/*     */   {
/*  84 */     return 0.0F;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float[] func_76560_a(float p_76560_1_, float p_76560_2_)
/*     */   {
/*  91 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public Vec3 func_76562_b(float p_76562_1_, float p_76562_2_)
/*     */   {
/*  98 */     int i = 10518688;
/*  99 */     float f2 = MathHelper.func_76134_b(p_76562_1_ * 3.1415927F * 2.0F) * 2.0F + 0.5F;
/*     */     
/* 101 */     if (f2 < 0.0F)
/*     */     {
/* 103 */       f2 = 0.0F;
/*     */     }
/*     */     
/* 106 */     if (f2 > 1.0F)
/*     */     {
/* 108 */       f2 = 1.0F;
/*     */     }
/*     */     
/* 111 */     float f3 = (i >> 16 & 0xFF) / 255.0F;
/* 112 */     float f4 = (i >> 8 & 0xFF) / 255.0F;
/* 113 */     float f5 = (i & 0xFF) / 255.0F;
/* 114 */     f3 *= (f2 * 0.0F + 0.15F);
/* 115 */     f4 *= (f2 * 0.0F + 0.15F);
/* 116 */     f5 *= (f2 * 0.0F + 0.15F);
/* 117 */     return Vec3.func_72443_a(f3, f4, f5);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_76561_g()
/*     */   {
/* 124 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_76567_e()
/*     */   {
/* 130 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_76569_d()
/*     */   {
/* 136 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float func_76571_f()
/*     */   {
/* 143 */     return 1.0F;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_76566_a(int p_76566_1_, int p_76566_2_)
/*     */   {
/* 149 */     return this.field_76579_a.func_147474_b(p_76566_1_, p_76566_2_).func_149688_o().func_76230_c();
/*     */   }
/*     */   
/*     */ 
/*     */   public ChunkCoordinates func_76554_h()
/*     */   {
/* 155 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_76557_i()
/*     */   {
/* 161 */     return 50;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_76568_b(int p_76568_1_, int p_76568_2_)
/*     */   {
/* 168 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/dim/WorldProviderOuter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */