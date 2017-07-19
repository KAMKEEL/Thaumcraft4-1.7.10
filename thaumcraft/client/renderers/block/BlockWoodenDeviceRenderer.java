/*     */ package thaumcraft.client.renderers.block;
/*     */ 
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.common.blocks.BlockWoodenDevice;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.tiles.TileArcaneBore;
/*     */ import thaumcraft.common.tiles.TileArcaneBoreBase;
/*     */ import thaumcraft.common.tiles.TileBanner;
/*     */ import thaumcraft.common.tiles.TileBellows;
/*     */ import thaumcraft.common.tiles.TileSensor;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockWoodenDeviceRenderer
/*     */   extends BlockRenderer
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderblocks)
/*     */   {
/*  26 */     if (metadata == 0) {
/*  27 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*  28 */       GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/*  29 */       TileEntityRendererDispatcher.field_147556_a.func_147549_a(new TileBellows(), 0.0D, 0.0D, 0.0D, 0.0F);
/*     */     }
/*  31 */     else if (metadata == 4) {
/*  32 */       GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*  33 */       GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/*  34 */       TileEntityRendererDispatcher.field_147556_a.func_147549_a(new TileArcaneBoreBase(), 0.0D, 0.0D, 0.0D, 0.0F);
/*     */ 
/*     */     }
/*  37 */     else if (metadata == 5) {
/*  38 */       GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*  39 */       GL11.glTranslatef(-0.5F, -0.75F, -0.5F);
/*  40 */       TileEntityRendererDispatcher.field_147556_a.func_147549_a(new TileArcaneBore(), 0.0D, 0.0D, 0.0D, 0.0F);
/*     */ 
/*     */     }
/*  43 */     else if (metadata == 1) {
/*  44 */       block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, W3, 1.0F);
/*  45 */       renderblocks.func_147775_a(block);
/*  46 */       drawFaces(renderblocks, block, ((BlockWoodenDevice)block).iconAEar[2], ((BlockWoodenDevice)block).iconAEar[3], ((BlockWoodenDevice)block).iconAEar[0], ((BlockWoodenDevice)block).iconAEar[0], ((BlockWoodenDevice)block).iconAEar[0], ((BlockWoodenDevice)block).iconAEar[0], true);
/*     */       
/*  48 */       block.func_149676_a(W4, W3, W4, W12, 1.0F, W12);
/*  49 */       renderblocks.func_147775_a(block);
/*  50 */       drawFaces(renderblocks, block, ((BlockWoodenDevice)block).iconAEar[2], ((BlockWoodenDevice)block).iconAEar[3], ((BlockWoodenDevice)block).iconAEar[0], ((BlockWoodenDevice)block).iconAEar[0], ((BlockWoodenDevice)block).iconAEar[0], ((BlockWoodenDevice)block).iconAEar[0], true);
/*     */       
/*  52 */       block.func_149676_a(W4, 0.5F, W1, W12, 1.0F, W3);
/*  53 */       renderblocks.func_147775_a(block);
/*  54 */       drawFaces(renderblocks, block, ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], true);
/*  55 */       block.func_149676_a(W5, 0.5F, W3, W11, W15, W4);
/*  56 */       renderblocks.func_147775_a(block);
/*  57 */       drawFaces(renderblocks, block, ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], true);
/*     */       
/*  59 */       block.func_149676_a(W1, 0.5F, W4, W3, 1.0F, W12);
/*  60 */       renderblocks.func_147775_a(block);
/*  61 */       drawFaces(renderblocks, block, ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], true);
/*  62 */       block.func_149676_a(W3, 0.5F, W5, W4, W15, W11);
/*  63 */       renderblocks.func_147775_a(block);
/*  64 */       drawFaces(renderblocks, block, ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], true);
/*     */       
/*  66 */       block.func_149676_a(W4, 0.5F, W13, W12, 1.0F, W15);
/*  67 */       renderblocks.func_147775_a(block);
/*  68 */       drawFaces(renderblocks, block, ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], true);
/*  69 */       block.func_149676_a(W5, 0.5F, W12, W11, W15, W13);
/*  70 */       renderblocks.func_147775_a(block);
/*  71 */       drawFaces(renderblocks, block, ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], true);
/*     */       
/*  73 */       block.func_149676_a(W13, 0.5F, W4, W15, 1.0F, W12);
/*  74 */       renderblocks.func_147775_a(block);
/*  75 */       drawFaces(renderblocks, block, ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], true);
/*  76 */       block.func_149676_a(W12, 0.5F, W5, W13, W15, W11);
/*  77 */       renderblocks.func_147775_a(block);
/*  78 */       drawFaces(renderblocks, block, ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], true);
/*     */ 
/*     */     }
/*  81 */     else if (metadata == 2) {
/*  82 */       GL11.glTranslatef(0.0F, 0.6F, 0.0F);
/*  83 */       GL11.glScalef(1.3F, 1.3F, 1.3F);
/*  84 */       float var6 = 0.0625F;
/*  85 */       block.func_149676_a(var6, 0.0F, var6, 1.0F - var6, 0.125F, 1.0F - var6);
/*  86 */       renderblocks.func_147775_a(block);
/*  87 */       drawFaces(renderblocks, block, ((BlockWoodenDevice)block).iconAPPlate[0], true);
/*     */     }
/*  89 */     else if (metadata == 6) {
/*  90 */       block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  91 */       renderblocks.func_147775_a(block);
/*  92 */       drawFaces(renderblocks, block, ((BlockWoodenDevice)block).iconGreatwood, true);
/*     */     }
/*  94 */     else if (metadata == 7) {
/*  95 */       block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  96 */       renderblocks.func_147775_a(block);
/*  97 */       drawFaces(renderblocks, block, ((BlockWoodenDevice)block).iconSilverwood, true);
/*     */     }
/*  99 */     else if (metadata == 8) {
/* 100 */       GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/* 101 */       GL11.glTranslatef(-0.5F, -1.0F, -0.5F);
/* 102 */       TileEntityRendererDispatcher.field_147556_a.func_147549_a(new TileBanner(), 0.0D, 0.0D, 0.0D, 0.0F);
/*     */     }
/* 104 */     GL11.glEnable(32826);
/*     */   }
/*     */   
/*     */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderblocks)
/*     */   {
/* 109 */     int md = world.func_72805_g(x, y, z);
/* 110 */     if (md == 1)
/*     */     {
/* 112 */       ((BlockWoodenDevice)block).renderState = 0;
/* 113 */       TileEntity tile = world.func_147438_o(x, y, z);
/* 114 */       if ((tile != null) && ((tile instanceof TileSensor)) && (((TileSensor)tile).redstoneSignal > 0)) {
/* 115 */         ((BlockWoodenDevice)block).renderState = 1;
/*     */       }
/*     */       
/* 118 */       block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, W3, 1.0F);
/* 119 */       renderblocks.func_147775_a(block);
/* 120 */       renderblocks.func_147784_q(block, x, y, z);
/*     */       
/* 122 */       block.func_149676_a(W4, W3, W4, W12, 1.0F, W12);
/* 123 */       renderblocks.func_147775_a(block);
/* 124 */       renderblocks.func_147784_q(block, x, y, z);
/*     */       
/* 126 */       ((BlockWoodenDevice)block).renderState = 2;
/*     */       
/* 128 */       block.func_149676_a(W4, 0.5F, W1, W12, 1.0F, W3);
/* 129 */       renderblocks.func_147775_a(block);
/* 130 */       renderblocks.func_147784_q(block, x, y, z);
/* 131 */       block.func_149676_a(W5, 0.5F, W3, W11, W15, W4);
/* 132 */       renderblocks.func_147775_a(block);
/* 133 */       renderblocks.func_147784_q(block, x, y, z);
/*     */       
/* 135 */       block.func_149676_a(W1, 0.5F, W4, W3, 1.0F, W12);
/* 136 */       renderblocks.func_147775_a(block);
/* 137 */       renderblocks.func_147784_q(block, x, y, z);
/* 138 */       block.func_149676_a(W3, 0.5F, W5, W4, W15, W11);
/* 139 */       renderblocks.func_147775_a(block);
/* 140 */       renderblocks.func_147784_q(block, x, y, z);
/*     */       
/* 142 */       block.func_149676_a(W4, 0.5F, W13, W12, 1.0F, W15);
/* 143 */       renderblocks.func_147775_a(block);
/* 144 */       renderblocks.func_147784_q(block, x, y, z);
/* 145 */       block.func_149676_a(W5, 0.5F, W12, W11, W15, W13);
/* 146 */       renderblocks.func_147775_a(block);
/* 147 */       renderblocks.func_147784_q(block, x, y, z);
/*     */       
/* 149 */       block.func_149676_a(W13, 0.5F, W4, W15, 1.0F, W12);
/* 150 */       renderblocks.func_147775_a(block);
/* 151 */       renderblocks.func_147784_q(block, x, y, z);
/* 152 */       block.func_149676_a(W12, 0.5F, W5, W13, W15, W11);
/* 153 */       renderblocks.func_147775_a(block);
/* 154 */       renderblocks.func_147784_q(block, x, y, z);
/*     */       
/* 156 */       ((BlockWoodenDevice)block).renderState = 0;
/*     */       
/* 158 */       block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 159 */       renderblocks.func_147775_a(block);
/* 160 */       return true;
/*     */     }
/* 162 */     if ((md == 2) || (md == 3) || (md == 6) || (md == 7)) {
/* 163 */       block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 164 */       renderblocks.func_147784_q(block, x, y, z);
/* 165 */       return true;
/*     */     }
/* 167 */     return false;
/*     */   }
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId)
/*     */   {
/* 172 */     return true;
/*     */   }
/*     */   
/*     */   public int getRenderId()
/*     */   {
/* 177 */     return ConfigBlocks.blockWoodenDeviceRI;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/block/BlockWoodenDeviceRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */