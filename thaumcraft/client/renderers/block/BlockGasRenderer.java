/*     */ package thaumcraft.client.renderers.block;
/*     */ 
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import net.minecraftforge.fluids.BlockFluidBase;
/*     */ import thaumcraft.common.blocks.BlockFluxGas;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ 
/*     */ public class BlockGasRenderer implements ISimpleBlockRenderingHandler
/*     */ {
/*  18 */   public static BlockGasRenderer instance = new BlockGasRenderer();
/*     */   
/*     */   static final float LIGHT_Y_NEG = 0.5F;
/*     */   static final float LIGHT_Y_POS = 1.0F;
/*     */   static final float LIGHT_XZ_NEG = 0.8F;
/*     */   static final float LIGHT_XZ_POS = 0.6F;
/*     */   static final double RENDER_OFFSET = 0.0010000000474974513D;
/*     */   
/*     */   public float getFluidHeightAverage(float[] flow)
/*     */   {
/*  28 */     float total = 0.0F;
/*  29 */     int count = 0;
/*     */     
/*  31 */     float end = 0.0F;
/*     */     
/*  33 */     for (int i = 0; i < flow.length; i++)
/*     */     {
/*  35 */       if ((flow[i] >= 0.875F) && (end != 1.0F))
/*     */       {
/*  37 */         end = flow[i];
/*     */       }
/*     */       
/*  40 */       if (flow[i] >= 0.0F)
/*     */       {
/*  42 */         total += flow[i];
/*  43 */         count++;
/*     */       }
/*     */     }
/*     */     
/*  47 */     if (end == 0.0F) {
/*  48 */       end = total / count;
/*     */     }
/*  50 */     return end;
/*     */   }
/*     */   
/*     */   public float getFluidHeightForRender(IBlockAccess world, int x, int y, int z, BlockFluxGas block)
/*     */   {
/*  55 */     if (world.func_147439_a(x, y, z) == block)
/*     */     {
/*  57 */       if (world.func_147439_a(x, y - block.getDensityDir(), z).func_149688_o().func_76224_d())
/*     */       {
/*  59 */         return 1.0F;
/*     */       }
/*     */       
/*  62 */       if (world.func_72805_g(x, y, z) == block.getMaxRenderHeightMeta())
/*     */       {
/*  64 */         return 0.875F;
/*     */       }
/*     */     }
/*  67 */     return (!world.func_147439_a(x, y, z).func_149688_o().func_76220_a()) && (world.func_147439_a(x, y - block.getDensityDir(), z) == block) ? 1.0F : block.getQuantaPercentage(world, x, y, z) * 0.875F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}
/*     */   
/*     */ 
/*     */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
/*     */   {
/*  77 */     if (!(block instanceof BlockFluxGas))
/*     */     {
/*  79 */       return false;
/*     */     }
/*     */     
/*  82 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  83 */     int color = block.func_149720_d(world, x, y, z);
/*  84 */     float red = (color >> 16 & 0xFF) / 255.0F;
/*  85 */     float green = (color >> 8 & 0xFF) / 255.0F;
/*  86 */     float blue = (color & 0xFF) / 255.0F;
/*     */     
/*  88 */     BlockFluxGas theFluid = (BlockFluxGas)block;
/*  89 */     int bMeta = world.func_72805_g(x, y, z);
/*     */     
/*  91 */     if (!world.isSideSolid(x, y + theFluid.getDensityDir(), z, ForgeDirection.DOWN, false))
/*     */     {
/*  93 */       tessellator.func_78380_c(block.func_149677_c(world, x, y, z));
/*  94 */       tessellator.func_78386_a(1.0F * red, 1.0F * green, 1.0F * blue);
/*     */       
/*  96 */       block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  97 */       renderer.func_147775_a(block);
/*  98 */       renderer.func_147784_q(block, x, y, z);
/*  99 */       renderer.func_147771_a();
/* 100 */       renderer.func_147775_a(block);
/*     */       
/* 102 */       return true;
/*     */     }
/*     */     
/* 105 */     boolean renderTop = world.func_147439_a(x, y - theFluid.getDensityDir(), z) != theFluid;
/*     */     
/* 107 */     boolean renderBottom = (block.func_149646_a(world, x, y + theFluid.getDensityDir(), z, 0)) && (world.func_147439_a(x, y + theFluid.getDensityDir(), z) != theFluid);
/*     */     
/* 109 */     boolean[] renderSides = { block.func_149646_a(world, x, y, z - 1, 2), block.func_149646_a(world, x, y, z + 1, 3), block.func_149646_a(world, x - 1, y, z, 4), block.func_149646_a(world, x + 1, y, z, 5) };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 117 */     if ((!renderTop) && (!renderBottom) && (renderSides[0] == 0) && (renderSides[1] == 0) && (renderSides[2] == 0) && (renderSides[3] == 0))
/*     */     {
/* 119 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 123 */     boolean rendered = false;
/*     */     
/* 125 */     float flow11 = getFluidHeightForRender(world, x, y, z, theFluid);
/*     */     double heightNE;
/* 127 */     double heightNW; double heightSW; double heightSE; double heightNE; if (flow11 != 1.0F)
/*     */     {
/* 129 */       float flow00 = getFluidHeightForRender(world, x - 1, y, z - 1, theFluid);
/* 130 */       float flow01 = getFluidHeightForRender(world, x - 1, y, z, theFluid);
/* 131 */       float flow02 = getFluidHeightForRender(world, x - 1, y, z + 1, theFluid);
/* 132 */       float flow10 = getFluidHeightForRender(world, x, y, z - 1, theFluid);
/* 133 */       float flow12 = getFluidHeightForRender(world, x, y, z + 1, theFluid);
/* 134 */       float flow20 = getFluidHeightForRender(world, x + 1, y, z - 1, theFluid);
/* 135 */       float flow21 = getFluidHeightForRender(world, x + 1, y, z, theFluid);
/* 136 */       float flow22 = getFluidHeightForRender(world, x + 1, y, z + 1, theFluid);
/*     */       
/* 138 */       double heightNW = getFluidHeightAverage(new float[] { flow00, flow01, flow10, flow11 });
/* 139 */       double heightSW = getFluidHeightAverage(new float[] { flow01, flow02, flow12, flow11 });
/* 140 */       double heightSE = getFluidHeightAverage(new float[] { flow12, flow21, flow22, flow11 });
/* 141 */       heightNE = getFluidHeightAverage(new float[] { flow10, flow20, flow21, flow11 });
/*     */     }
/*     */     else
/*     */     {
/* 145 */       heightNW = flow11;
/* 146 */       heightSW = flow11;
/* 147 */       heightSE = flow11;
/* 148 */       heightNE = flow11;
/*     */     }
/*     */     
/* 151 */     boolean rises = theFluid.getDensityDir() == 1;
/* 152 */     if ((renderer.field_147837_f) || (renderTop))
/*     */     {
/* 154 */       rendered = true;
/* 155 */       IIcon iconStill = block.func_149691_a(1, bMeta);
/* 156 */       float flowDir = (float)BlockFluidBase.getFlowDirection(world, x, y, z);
/*     */       
/* 158 */       if (flowDir > -999.0F)
/*     */       {
/* 160 */         iconStill = block.func_149691_a(2, bMeta);
/*     */       }
/*     */       
/* 163 */       heightNW -= 0.0010000000474974513D;
/* 164 */       heightSW -= 0.0010000000474974513D;
/* 165 */       heightSE -= 0.0010000000474974513D;
/* 166 */       heightNE -= 0.0010000000474974513D;
/*     */       double v3;
/*     */       double u2;
/*     */       double v2;
/* 170 */       double u1; double v1; double u4; double v4; double u3; double v3; if (flowDir < -999.0F)
/*     */       {
/* 172 */         double u2 = iconStill.func_94214_a(0.0D);
/* 173 */         double v2 = iconStill.func_94207_b(0.0D);
/* 174 */         double u1 = u2;
/* 175 */         double v1 = iconStill.func_94207_b(16.0D);
/* 176 */         double u4 = iconStill.func_94214_a(16.0D);
/* 177 */         double v4 = v1;
/* 178 */         double u3 = u4;
/* 179 */         v3 = v2;
/*     */       }
/*     */       else
/*     */       {
/* 183 */         float xFlow = MathHelper.func_76126_a(flowDir) * 0.25F;
/* 184 */         float zFlow = MathHelper.func_76134_b(flowDir) * 0.25F;
/* 185 */         u2 = iconStill.func_94214_a(8.0F + (-zFlow - xFlow) * 16.0F);
/* 186 */         v2 = iconStill.func_94207_b(8.0F + (-zFlow + xFlow) * 16.0F);
/* 187 */         u1 = iconStill.func_94214_a(8.0F + (-zFlow + xFlow) * 16.0F);
/* 188 */         v1 = iconStill.func_94207_b(8.0F + (zFlow + xFlow) * 16.0F);
/* 189 */         u4 = iconStill.func_94214_a(8.0F + (zFlow + xFlow) * 16.0F);
/* 190 */         v4 = iconStill.func_94207_b(8.0F + (zFlow - xFlow) * 16.0F);
/* 191 */         u3 = iconStill.func_94214_a(8.0F + (zFlow - xFlow) * 16.0F);
/* 192 */         v3 = iconStill.func_94207_b(8.0F + (-zFlow - xFlow) * 16.0F);
/*     */       }
/*     */       
/* 195 */       tessellator.func_78380_c(block.func_149677_c(world, x, y, z));
/* 196 */       tessellator.func_78386_a(1.0F * red, 1.0F * green, 1.0F * blue);
/*     */       
/* 198 */       if (!rises)
/*     */       {
/* 200 */         tessellator.func_78374_a(x + 0, y + heightNW, z + 0, u2, v2);
/* 201 */         tessellator.func_78374_a(x + 0, y + heightSW, z + 1, u1, v1);
/* 202 */         tessellator.func_78374_a(x + 1, y + heightSE, z + 1, u4, v4);
/* 203 */         tessellator.func_78374_a(x + 1, y + heightNE, z + 0, u3, v3);
/*     */       }
/*     */       else
/*     */       {
/* 207 */         tessellator.func_78374_a(x + 1, y + 1 - heightNE, z + 0, u3, v3);
/* 208 */         tessellator.func_78374_a(x + 1, y + 1 - heightSE, z + 1, u4, v4);
/* 209 */         tessellator.func_78374_a(x + 0, y + 1 - heightSW, z + 1, u1, v1);
/* 210 */         tessellator.func_78374_a(x + 0, y + 1 - heightNW, z + 0, u2, v2);
/*     */       }
/*     */     }
/*     */     
/* 214 */     if ((renderer.field_147837_f) || (renderBottom))
/*     */     {
/* 216 */       rendered = true;
/* 217 */       tessellator.func_78380_c(block.func_149677_c(world, x, y - 1, z));
/* 218 */       if (!rises)
/*     */       {
/* 220 */         tessellator.func_78386_a(0.5F * red, 0.5F * green, 0.5F * blue);
/* 221 */         renderer.func_147768_a(block, x, y + 0.0010000000474974513D, z, block.func_149691_a(0, bMeta));
/*     */       }
/*     */       else
/*     */       {
/* 225 */         tessellator.func_78386_a(1.0F * red, 1.0F * green, 1.0F * blue);
/* 226 */         renderer.func_147806_b(block, x, y + 0.0010000000474974513D, z, block.func_149691_a(1, bMeta));
/*     */       }
/*     */     }
/*     */     
/* 230 */     for (int side = 0; side < 4; side++)
/*     */     {
/* 232 */       int x2 = x;
/* 233 */       int z2 = z;
/*     */       
/* 235 */       switch (side) {
/*     */       case 0: 
/* 237 */         z2--; break;
/* 238 */       case 1:  z2++; break;
/* 239 */       case 2:  x2--; break;
/* 240 */       case 3:  x2++;
/*     */       }
/*     */       
/* 243 */       IIcon iconFlow = block.func_149691_a(side + 2, bMeta);
/* 244 */       if ((renderer.field_147837_f) || (renderSides[side] != 0))
/*     */       {
/* 246 */         rendered = true;
/*     */         
/*     */         double tz2;
/*     */         double ty1;
/*     */         double ty2;
/*     */         double tx1;
/*     */         double tx2;
/*     */         double tz1;
/*     */         double tz2;
/* 255 */         if (side == 0)
/*     */         {
/* 257 */           double ty1 = heightNW;
/* 258 */           double ty2 = heightNE;
/* 259 */           double tx1 = x;
/* 260 */           double tx2 = x + 1;
/* 261 */           double tz1 = z + 0.0010000000474974513D;
/* 262 */           tz2 = z + 0.0010000000474974513D;
/*     */         } else { double tz2;
/* 264 */           if (side == 1)
/*     */           {
/* 266 */             double ty1 = heightSE;
/* 267 */             double ty2 = heightSW;
/* 268 */             double tx1 = x + 1;
/* 269 */             double tx2 = x;
/* 270 */             double tz1 = z + 1 - 0.0010000000474974513D;
/* 271 */             tz2 = z + 1 - 0.0010000000474974513D;
/*     */           } else { double tz2;
/* 273 */             if (side == 2)
/*     */             {
/* 275 */               double ty1 = heightSW;
/* 276 */               double ty2 = heightNW;
/* 277 */               double tx1 = x + 0.0010000000474974513D;
/* 278 */               double tx2 = x + 0.0010000000474974513D;
/* 279 */               double tz1 = z + 1;
/* 280 */               tz2 = z;
/*     */             }
/*     */             else
/*     */             {
/* 284 */               ty1 = heightNE;
/* 285 */               ty2 = heightSE;
/* 286 */               tx1 = x + 1 - 0.0010000000474974513D;
/* 287 */               tx2 = x + 1 - 0.0010000000474974513D;
/* 288 */               tz1 = z;
/* 289 */               tz2 = z + 1;
/*     */             }
/*     */           } }
/* 292 */         float u1Flow = iconFlow.func_94214_a(0.0D);
/* 293 */         float u2Flow = iconFlow.func_94214_a(8.0D);
/* 294 */         float v1Flow = iconFlow.func_94207_b((1.0D - ty1) * 16.0D * 0.5D);
/* 295 */         float v2Flow = iconFlow.func_94207_b((1.0D - ty2) * 16.0D * 0.5D);
/* 296 */         float v3Flow = iconFlow.func_94207_b(8.0D);
/* 297 */         tessellator.func_78380_c(block.func_149677_c(world, x2, y, z2));
/* 298 */         float sideLighting = 1.0F;
/*     */         
/* 300 */         if (side < 2)
/*     */         {
/* 302 */           sideLighting = 0.8F;
/*     */         }
/*     */         else
/*     */         {
/* 306 */           sideLighting = 0.6F;
/*     */         }
/*     */         
/* 309 */         tessellator.func_78386_a(1.0F * sideLighting * red, 1.0F * sideLighting * green, 1.0F * sideLighting * blue);
/*     */         
/* 311 */         if (!rises)
/*     */         {
/* 313 */           tessellator.func_78374_a(tx1, y + ty1, tz1, u1Flow, v1Flow);
/* 314 */           tessellator.func_78374_a(tx2, y + ty2, tz2, u2Flow, v2Flow);
/* 315 */           tessellator.func_78374_a(tx2, y + 0, tz2, u2Flow, v3Flow);
/* 316 */           tessellator.func_78374_a(tx1, y + 0, tz1, u1Flow, v3Flow);
/*     */         }
/*     */         else
/*     */         {
/* 320 */           tessellator.func_78374_a(tx1, y + 1 - 0, tz1, u1Flow, v3Flow);
/* 321 */           tessellator.func_78374_a(tx2, y + 1 - 0, tz2, u2Flow, v3Flow);
/* 322 */           tessellator.func_78374_a(tx2, y + 1 - ty2, tz2, u2Flow, v2Flow);
/* 323 */           tessellator.func_78374_a(tx1, y + 1 - ty1, tz1, u1Flow, v1Flow);
/*     */         }
/*     */       }
/*     */     }
/* 327 */     renderer.field_147855_j = 0.0D;
/* 328 */     renderer.field_147857_k = 1.0D;
/* 329 */     return rendered;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean shouldRender3DInInventory(int modelId)
/*     */   {
/* 335 */     return false;
/*     */   }
/*     */   
/*     */   public int getRenderId() {
/* 339 */     return ConfigBlocks.blockFluxGasRI;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/block/BlockGasRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */