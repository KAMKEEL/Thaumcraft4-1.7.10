/*     */ package thaumcraft.client.renderers.block;
/*     */ 
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.tiles.TileFluxScrubber;
/*     */ import thaumcraft.common.tiles.TileFocalManipulator;
/*     */ import thaumcraft.common.tiles.TileInfusionMatrix;
/*     */ import thaumcraft.common.tiles.TileNodeConverter;
/*     */ import thaumcraft.common.tiles.TileNodeStabilizer;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockStoneDeviceRenderer
/*     */   extends BlockRenderer
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
/*     */   {
/*  24 */     if (metadata == 0) {
/*  25 */       block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  26 */       renderer.func_147775_a(block);
/*  27 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockStoneDevice)block).iconFurnace[1], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconFurnace[1], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconFurnace[2], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconFurnace[2], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconFurnace[2], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconFurnace[2], true);
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/*  32 */     else if (metadata == 1)
/*     */     {
/*  34 */       block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
/*  35 */       renderer.func_147775_a(block);
/*  36 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockStoneDevice)block).iconPedestal[1], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconPedestal[1], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconPedestal[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconPedestal[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconPedestal[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconPedestal[0], true);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  43 */       block.func_149676_a(0.25F, 0.25F, 0.25F, 0.75F, 0.75F, 0.75F);
/*  44 */       renderer.func_147775_a(block);
/*  45 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockStoneDevice)block).iconPedestal[1], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconPedestal[1], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconPedestal[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconPedestal[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconPedestal[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconPedestal[0], true);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  52 */       block.func_149676_a(0.125F, 0.75F, 0.125F, 0.875F, 1.0F, 0.875F);
/*  53 */       renderer.func_147775_a(block);
/*  54 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockStoneDevice)block).iconPedestal[1], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconPedestal[1], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconPedestal[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconPedestal[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconPedestal[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconPedestal[0], true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/*  61 */     else if (metadata == 2) {
/*  62 */       GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/*  63 */       TileEntityRendererDispatcher.field_147556_a.func_147549_a(new TileInfusionMatrix(), 0.0D, 0.0D, 0.0D, 0.0F);
/*     */     }
/*  65 */     else if (metadata == 5) {
/*  66 */       block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
/*  67 */       renderer.func_147775_a(block);
/*  68 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockStoneDevice)block).iconPedestal[1], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestal[1], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestal[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestal[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestal[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestal[0], true);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  75 */       block.func_149676_a(0.125F, 0.25F, 0.125F, 0.875F, 0.5F, 0.875F);
/*  76 */       renderer.func_147775_a(block);
/*  77 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockStoneDevice)block).iconPedestal[1], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestal[1], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestal[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestal[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestal[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestal[0], true);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  84 */       block.func_149676_a(0.25F, 0.5F, 0.25F, 0.75F, 1.0F, 0.75F);
/*  85 */       renderer.func_147775_a(block);
/*  86 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockStoneDevice)block).iconPedestal[1], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestal[1], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestal[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestal[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestal[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestal[0], true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/*  93 */     else if (metadata == 8) {
/*  94 */       block.func_149676_a(W5, 0.0F, W5, W11, W1, W11);
/*  95 */       renderer.func_147775_a(block);
/*  96 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[2], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[1], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], true);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 102 */       block.func_149676_a(W1, 0.0F, W7, W5, W1, W9);
/* 103 */       renderer.func_147775_a(block);
/* 104 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[2], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[1], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], true);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 110 */       block.func_149676_a(W11, 0.0F, W7, W15, W1, W9);
/* 111 */       renderer.func_147775_a(block);
/* 112 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[2], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[1], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], true);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 118 */       block.func_149676_a(W7, 0.0F, W1, W9, W1, W5);
/* 119 */       renderer.func_147775_a(block);
/* 120 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[2], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[1], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], true);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 126 */       block.func_149676_a(W7, 0.0F, W11, W9, W1, W15);
/* 127 */       renderer.func_147775_a(block);
/* 128 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[2], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[1], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], true);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 134 */       block.func_149676_a(W1, W1, W7, W3, W7, W9);
/* 135 */       renderer.func_147775_a(block);
/* 136 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[2], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[1], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], true);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 142 */       block.func_149676_a(W7, W1, W1, W9, W7, W3);
/* 143 */       renderer.func_147775_a(block);
/* 144 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[2], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[1], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], true);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 150 */       block.func_149676_a(W13, W1, W7, W15, W7, W9);
/* 151 */       renderer.func_147775_a(block);
/* 152 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[2], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[1], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], true);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 158 */       block.func_149676_a(W7, W1, W13, W9, W7, W15);
/* 159 */       renderer.func_147775_a(block);
/* 160 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[2], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[1], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconWandPedestalFocus[0], true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/* 167 */     else if ((metadata == 9) || (metadata == 10)) {
/* 168 */       GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 169 */       TileEntityRendererDispatcher.field_147556_a.func_147549_a(new TileNodeStabilizer(metadata), 0.0D, 0.0D, 0.0D, 0.0F);
/*     */     }
/* 171 */     else if ((metadata == 9) || (metadata == 11)) {
/* 172 */       GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 173 */       TileEntityRendererDispatcher.field_147556_a.func_147549_a(new TileNodeConverter(), 0.0D, 0.0D, 0.0D, 0.0F);
/*     */     }
/* 175 */     else if (metadata == 12) {
/* 176 */       block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 177 */       renderer.func_147775_a(block);
/* 178 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockStoneDevice)block).iconPedestal[1], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconSpa[1], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconSpa[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconSpa[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconSpa[0], ((thaumcraft.common.blocks.BlockStoneDevice)block).iconSpa[0], true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/* 185 */     else if (metadata == 13) {
/* 186 */       GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 187 */       TileEntityRendererDispatcher.field_147556_a.func_147549_a(new TileFocalManipulator(), 0.0D, 0.0D, 0.0D, 0.0F);
/*     */     }
/* 189 */     else if (metadata == 14) {
/* 190 */       GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 191 */       TileEntityRendererDispatcher.field_147556_a.func_147549_a(new TileFluxScrubber(), 0.0D, 0.0D, 0.0D, 0.0F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
/*     */   {
/* 198 */     int metadata = world.func_72805_g(x, y, z);
/*     */     
/* 200 */     if ((metadata == 0) || (metadata == 12)) {
/* 201 */       block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 202 */       renderer.func_147775_a(block);
/* 203 */       renderer.func_147784_q(block, x, y, z);
/*     */ 
/*     */     }
/* 206 */     else if (metadata == 1) {
/* 207 */       block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
/* 208 */       renderer.func_147775_a(block);
/* 209 */       renderer.func_147784_q(block, x, y, z);
/*     */       
/* 211 */       block.func_149676_a(0.25F, 0.25F, 0.25F, 0.75F, 0.75F, 0.75F);
/* 212 */       renderer.func_147775_a(block);
/* 213 */       renderer.func_147784_q(block, x, y, z);
/*     */       
/* 215 */       block.func_149676_a(0.125F, 0.75F, 0.125F, 0.875F, 1.0F, 0.875F);
/* 216 */       renderer.func_147775_a(block);
/* 217 */       renderer.func_147784_q(block, x, y, z);
/*     */     }
/* 219 */     else if (metadata == 5) {
/* 220 */       block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
/* 221 */       renderer.func_147775_a(block);
/* 222 */       renderer.func_147784_q(block, x, y, z);
/*     */       
/* 224 */       block.func_149676_a(0.125F, 0.25F, 0.125F, 0.875F, 0.5F, 0.875F);
/* 225 */       renderer.func_147775_a(block);
/* 226 */       renderer.func_147784_q(block, x, y, z);
/*     */       
/* 228 */       block.func_149676_a(0.25F, 0.5F, 0.25F, 0.75F, 1.0F, 0.75F);
/* 229 */       renderer.func_147775_a(block);
/* 230 */       renderer.func_147784_q(block, x, y, z);
/*     */ 
/*     */     }
/* 233 */     else if (metadata == 8) {
/* 234 */       block.func_149676_a(W5, 0.0F, W5, W11, W1, W11);
/* 235 */       renderer.func_147775_a(block);
/* 236 */       renderer.func_147784_q(block, x, y, z);
/* 237 */       block.func_149676_a(W1, 0.0F, W7, W5, W1, W9);
/* 238 */       renderer.func_147775_a(block);
/* 239 */       renderer.func_147784_q(block, x, y, z);
/* 240 */       block.func_149676_a(W11, 0.0F, W7, W15, W1, W9);
/* 241 */       renderer.func_147775_a(block);
/* 242 */       renderer.func_147784_q(block, x, y, z);
/* 243 */       block.func_149676_a(W7, 0.0F, W1, W9, W1, W5);
/* 244 */       renderer.func_147775_a(block);
/* 245 */       renderer.func_147784_q(block, x, y, z);
/* 246 */       block.func_149676_a(W7, 0.0F, W11, W9, W1, W15);
/* 247 */       renderer.func_147775_a(block);
/* 248 */       renderer.func_147784_q(block, x, y, z);
/* 249 */       block.func_149676_a(W1, W1, W7, W3, W7, W9);
/* 250 */       renderer.func_147775_a(block);
/* 251 */       renderer.func_147784_q(block, x, y, z);
/* 252 */       block.func_149676_a(W7, W1, W1, W9, W7, W3);
/* 253 */       renderer.func_147775_a(block);
/* 254 */       renderer.func_147784_q(block, x, y, z);
/* 255 */       block.func_149676_a(W13, W1, W7, W15, W7, W9);
/* 256 */       renderer.func_147775_a(block);
/* 257 */       renderer.func_147784_q(block, x, y, z);
/* 258 */       block.func_149676_a(W7, W1, W13, W9, W7, W15);
/* 259 */       renderer.func_147775_a(block);
/* 260 */       renderer.func_147784_q(block, x, y, z);
/*     */     }
/*     */     
/* 263 */     renderer.func_147771_a();
/* 264 */     block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 265 */     renderer.func_147775_a(block);
/* 266 */     return true;
/*     */   }
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId)
/*     */   {
/* 271 */     return true;
/*     */   }
/*     */   
/*     */   public int getRenderId()
/*     */   {
/* 276 */     return ConfigBlocks.blockStoneDeviceRI;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/block/BlockStoneDeviceRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */