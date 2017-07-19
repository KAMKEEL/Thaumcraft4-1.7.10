/*     */ package thaumcraft.client.renderers.block;
/*     */ 
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import java.awt.Color;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.tiles.TileBellows;
/*     */ import thaumcraft.common.tiles.TileCentrifuge;
/*     */ import thaumcraft.common.tiles.TileEssentiaCrystalizer;
/*     */ import thaumcraft.common.tiles.TileTube;
/*     */ import thaumcraft.common.tiles.TileTubeFilter;
/*     */ import thaumcraft.common.tiles.TileTubeValve;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockTubeRenderer
/*     */   extends BlockRenderer
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
/*     */   {
/*     */     try
/*     */     {
/*  33 */       if ((metadata == 0) || (metadata == 1) || (metadata == 3) || (metadata == 5) || (metadata == 6)) {
/*  34 */         block.func_149676_a(W7, 0.0F, W7, W9, 1.0F, W9);
/*  35 */         renderer.func_147775_a(block);
/*  36 */         drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockTube)block).icon[0], true);
/*     */       }
/*  38 */       if (metadata == 6) {
/*  39 */         block.func_149676_a(W7 - 0.001F, 0.1F, W7 - 0.001F, W9 + 0.001F, 0.9F, W9 + 0.001F);
/*  40 */         renderer.func_147775_a(block);
/*  41 */         drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockTube)block).icon[7], true);
/*     */       }
/*  43 */       if ((metadata == 0) || (metadata == 5) || (metadata == 6)) {
/*  44 */         block.func_149676_a(W7 - 0.03125F, W7 - 0.03125F, W7 - 0.03125F, W9 + 0.03125F, W9 + 0.03125F, W9 + 0.03125F);
/*  45 */         renderer.func_147775_a(block);
/*  46 */         drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockTube)block).icon[2], true);
/*     */       }
/*  48 */       if (metadata == 1) {
/*  49 */         block.func_149676_a(W6, W6, W6, W10, W10, W10);
/*  50 */         renderer.func_147775_a(block);
/*  51 */         drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockTube)block).icon[1], true);
/*  52 */         GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/*  53 */         TileTubeValve tc = new TileTubeValve();
/*  54 */         tc.facing = ForgeDirection.EAST;
/*  55 */         TileEntityRendererDispatcher.field_147556_a.func_147549_a(tc, 0.0D, 0.0D, 0.0D, 0.0F);
/*  56 */         GL11.glEnable(32826);
/*     */       }
/*  58 */       if (metadata == 2) {
/*  59 */         GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/*  60 */         TileEntityRendererDispatcher.field_147556_a.func_147549_a(new TileCentrifuge(), 0.0D, 0.0D, 0.0D, 0.0F);
/*  61 */         GL11.glEnable(32826);
/*     */       }
/*  63 */       if (metadata == 3) {
/*  64 */         block.func_149676_a(W6 - 0.03125F, W6 - 0.03125F, W6 - 0.03125F, W10 + 0.03125F, W10 + 0.03125F, W10 + 0.03125F);
/*  65 */         renderer.func_147775_a(block);
/*  66 */         drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockTube)block).icon[3], false);
/*  67 */         block.func_149676_a(W6 - 0.03125F, W6 - 0.03125F, W6 - 0.03125F, W10 + 0.03125F, W10 + 0.03125F, W10 + 0.03125F);
/*  68 */         renderer.func_147775_a(block);
/*  69 */         drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockTube)block).icon[4], false);
/*     */       }
/*  71 */       if (metadata == 4) {
/*  72 */         block.func_149676_a(W4, W4, W4, W12, W12, W12);
/*  73 */         renderer.func_147775_a(block);
/*  74 */         drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockTube)block).icon[5], false);
/*     */         
/*  76 */         block.func_149676_a(W7, 0.0F, W7, W9, 1.0F, W9);
/*  77 */         renderer.func_147775_a(block);
/*  78 */         drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockTube)block).icon[5], false);
/*     */       }
/*  80 */       if (metadata == 7) {
/*  81 */         GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/*  82 */         TileEntityRendererDispatcher.field_147556_a.func_147549_a(new TileEssentiaCrystalizer(), 0.0D, 0.0D, 0.0D, 0.0F);
/*  83 */         GL11.glEnable(32826);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
/*  90 */     int metadata = world.func_72805_g(x, y, z);
/*     */     
/*  92 */     if ((metadata == 0) || (metadata == 1) || (metadata == 3) || (metadata == 4) || (metadata == 5) || (metadata == 6)) {
/*  93 */       renderer.field_152631_f = true;
/*  94 */       float AX_minx = W7;float AX_maxx = W9;boolean drawX = false;
/*  95 */       float AX_miny = W7;float AX_maxy = W9;
/*  96 */       float AX_minz = W7;float AX_maxz = W9;
/*  97 */       float AY_minx = W7;float AY_maxx = W9;boolean drawY = false;
/*  98 */       float AY_miny = W7;float AY_maxy = W9;
/*  99 */       float AY_minz = W7;float AY_maxz = W9;
/* 100 */       float AZ_minx = W7;float AZ_maxx = W9;boolean drawZ = false;
/* 101 */       float AZ_miny = W7;float AZ_maxy = W9;
/* 102 */       float AZ_minz = W7;float AZ_maxz = W9;
/* 103 */       boolean notConduit = false;
/* 104 */       ForgeDirection fd = null;
/* 105 */       IEssentiaTransport tube = null;
/* 106 */       TileEntity tt = world.func_147438_o(x, y, z);
/* 107 */       if ((tt instanceof IEssentiaTransport)) {
/* 108 */         tube = (IEssentiaTransport)tt;
/*     */       }
/* 110 */       for (int side = 0; side < 6; side++) {
/* 111 */         fd = ForgeDirection.getOrientation(side);
/* 112 */         if ((tube == null) || (tube.isConnectable(fd)))
/*     */         {
/* 114 */           TileEntity te = getConnectableTile(world, x, y, z, fd);
/* 115 */           if ((te != null) && ((metadata == 4) || (!(te instanceof TileBellows)))) {
/* 116 */             if (!(te instanceof TileTube)) notConduit = true;
/* 117 */             switch (side) {
/*     */             case 0: 
/* 119 */               AY_miny = 0.0F;drawY = true;
/* 120 */               if (((te instanceof IEssentiaTransport)) && (((IEssentiaTransport)te).renderExtendedTube())) AY_miny = -W6;
/*     */               break;
/*     */             case 1: 
/* 123 */               AY_maxy = 1.0F;drawY = true;
/* 124 */               if (((te instanceof IEssentiaTransport)) && (((IEssentiaTransport)te).renderExtendedTube())) AY_maxy = 1.0F + W6;
/*     */               break;
/*     */             case 2: 
/* 127 */               AZ_minz = 0.0F;drawZ = true;
/* 128 */               if (((te instanceof IEssentiaTransport)) && (((IEssentiaTransport)te).renderExtendedTube())) AZ_minz = -W6;
/*     */               break;
/*     */             case 3: 
/* 131 */               AZ_maxz = 1.0F;drawZ = true;
/* 132 */               if (((te instanceof IEssentiaTransport)) && (((IEssentiaTransport)te).renderExtendedTube())) AZ_maxz = 1.0F + W6;
/*     */               break;
/*     */             case 4: 
/* 135 */               AX_minx = 0.0F;drawX = true;
/* 136 */               if (((te instanceof IEssentiaTransport)) && (((IEssentiaTransport)te).renderExtendedTube())) AX_minx = -W6;
/*     */               break;
/*     */             case 5: 
/* 139 */               AX_maxx = 1.0F;drawX = true;
/* 140 */               if (((te instanceof IEssentiaTransport)) && (((IEssentiaTransport)te).renderExtendedTube())) AX_maxx = 1.0F + W6;
/*     */               break;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 146 */       int drawn = 0;
/*     */       
/* 148 */       if (drawX) {
/* 149 */         drawn++;
/* 150 */         block.func_149676_a(AX_minx, AX_miny, AX_minz, AX_maxx, AX_maxy, AX_maxz);
/* 151 */         renderer.func_147775_a(block);
/* 152 */         renderer.func_147784_q(block, x, y, z);
/*     */       }
/*     */       
/* 155 */       if (drawY) {
/* 156 */         drawn++;
/* 157 */         block.func_149676_a(AY_minx, AY_miny, AY_minz, AY_maxx, AY_maxy, AY_maxz);
/* 158 */         renderer.func_147775_a(block);
/* 159 */         renderer.func_147784_q(block, x, y, z);
/*     */       }
/*     */       
/* 162 */       if (drawZ) {
/* 163 */         drawn++;
/* 164 */         block.func_149676_a(AZ_minx, AZ_miny, AZ_minz, AZ_maxx, AZ_maxy, AZ_maxz);
/* 165 */         renderer.func_147775_a(block);
/* 166 */         renderer.func_147784_q(block, x, y, z);
/*     */       }
/*     */       
/* 169 */       if (metadata == 3) {
/* 170 */         renderer.field_147840_d = ((thaumcraft.common.blocks.BlockTube)block).icon[3];
/* 171 */         block.func_149676_a(W6 - 0.03125F, W6 - 0.03125F, W6 - 0.03125F, W10 + 0.03125F, W10 + 0.03125F, W10 + 0.03125F);
/* 172 */         renderer.func_147775_a(block);
/* 173 */         renderer.func_147784_q(block, x, y, z);
/*     */         
/* 175 */         TileEntity te = world.func_147438_o(x, y, z);
/* 176 */         float r = 1.0F;
/* 177 */         float g = 1.0F;
/* 178 */         float b = 1.0F;
/* 179 */         if ((te != null) && ((te instanceof TileTubeFilter)) && (((TileTubeFilter)te).aspectFilter != null)) {
/* 180 */           Color c = new Color(((TileTubeFilter)te).aspectFilter.getColor());
/* 181 */           r = c.getRed() / 255.0F;
/* 182 */           g = c.getGreen() / 255.0F;
/* 183 */           b = c.getBlue() / 255.0F;
/*     */         }
/* 185 */         renderer.field_147840_d = ((thaumcraft.common.blocks.BlockTube)block).icon[4];
/* 186 */         block.func_149676_a(W6 - 0.03125F, W6 - 0.03125F, W6 - 0.03125F, W10 + 0.03125F, W10 + 0.03125F, W10 + 0.03125F);
/* 187 */         renderer.func_147775_a(block);
/* 188 */         renderer.func_147736_d(block, x, y, z, r, g, b);
/*     */ 
/*     */ 
/*     */       }
/* 192 */       else if (metadata == 4) {
/* 193 */         block.func_149676_a(W4, W4, W4, W12, W12, W12);
/* 194 */         renderer.func_147775_a(block);
/* 195 */         renderer.func_147784_q(block, x, y, z);
/*     */       }
/* 197 */       else if ((drawn == 0) || (notConduit) || (metadata == 1)) {
/* 198 */         renderer.field_147840_d = ((thaumcraft.common.blocks.BlockTube)block).icon[1];
/* 199 */         block.func_149676_a(W6, W6, W6, W10, W10, W10);
/* 200 */         renderer.func_147775_a(block);
/* 201 */         renderer.func_147784_q(block, x, y, z);
/*     */       } else {
/* 203 */         if (metadata != 5) renderer.field_147840_d = ((thaumcraft.common.blocks.BlockTube)block).icon[2];
/* 204 */         block.func_149676_a(W7 - 0.03125F, W7 - 0.03125F, W7 - 0.03125F, W9 + 0.03125F, W9 + 0.03125F, W9 + 0.03125F);
/* 205 */         renderer.func_147775_a(block);
/* 206 */         renderer.func_147784_q(block, x, y, z);
/*     */       }
/*     */       
/*     */ 
/* 210 */       renderer.field_152631_f = false;
/*     */     }
/*     */     
/* 213 */     renderer.func_147771_a();
/* 214 */     block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 215 */     renderer.func_147775_a(block);
/* 216 */     return true;
/*     */   }
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId)
/*     */   {
/* 221 */     return true;
/*     */   }
/*     */   
/*     */   public int getRenderId()
/*     */   {
/* 226 */     return ConfigBlocks.blockTubeRI;
/*     */   }
/*     */   
/*     */   public TileEntity getConnectableTile(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
/* 230 */     TileEntity te = world.func_147438_o(x + face.offsetX, y + face.offsetY, z + face.offsetZ);
/* 231 */     if (((te instanceof IEssentiaTransport)) && (((IEssentiaTransport)te).isConnectable(face.getOpposite()))) {
/* 232 */       return te;
/*     */     }
/* 234 */     if (((te instanceof TileBellows)) && (((TileBellows)te).orientation == face.getOpposite().ordinal())) {
/* 235 */       return te;
/*     */     }
/* 237 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/block/BlockTubeRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */