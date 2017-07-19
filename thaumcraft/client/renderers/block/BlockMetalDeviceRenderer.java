/*     */ package thaumcraft.client.renderers.block;
/*     */ 
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.tiles.TileAlembic;
/*     */ import thaumcraft.common.tiles.TileBrainbox;
/*     */ import thaumcraft.common.tiles.TileCrucible;
/*     */ import thaumcraft.common.tiles.TileMagicWorkbenchCharger;
/*     */ import thaumcraft.common.tiles.TileVisRelay;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockMetalDeviceRenderer
/*     */   extends BlockRenderer
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
/*     */   {
/*  27 */     if ((metadata == 0) || (metadata == 6)) {
/*  28 */       block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  29 */       renderer.func_147775_a(block);
/*  30 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[2], ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[4], ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[3], ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[3], ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[3], ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[3], true);
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/*  35 */     else if (metadata == 1) {
/*  36 */       GL11.glTranslatef(-0.5F, 0.0F, -0.5F);
/*  37 */       TileEntityRendererDispatcher.field_147556_a.func_147549_a(new TileAlembic(), 0.0D, 0.0D, 0.0D, 0.0F);
/*  38 */       GL11.glEnable(32826);
/*     */     }
/*  40 */     else if (metadata == 5) {
/*  41 */       GL11.glTranslatef(0.0F, -0.3F, 0.0F);
/*  42 */       block.func_149676_a(0.0F, 0.8125F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  43 */       renderer.func_147775_a(block);
/*  44 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[8], false);
/*  45 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[9], false);
/*     */     }
/*  47 */     else if (metadata == 7) {
/*  48 */       block.func_149676_a(W4, W2, W4, W12, W14, W12);
/*  49 */       renderer.func_147775_a(block);
/*  50 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[11], ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[11], ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[10], ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[10], ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[10], ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[10], true);
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/*  55 */     else if (metadata == 8) {
/*  56 */       block.func_149676_a(W4, W2, W4, W12, W14, W12);
/*  57 */       renderer.func_147775_a(block);
/*  58 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[13], ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[13], ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[12], ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[12], ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[12], ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[12], true);
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/*  63 */     else if (metadata == 9) {
/*  64 */       block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  65 */       renderer.func_147775_a(block);
/*  66 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[16], true);
/*     */     }
/*  68 */     else if (metadata == 3) {
/*  69 */       block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  70 */       renderer.func_147775_a(block);
/*  71 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[22], true);
/*     */     }
/*  73 */     else if (metadata == 12) {
/*  74 */       block.func_149676_a(W3, W3, W3, W13, W13, W13);
/*  75 */       renderer.func_147775_a(block);
/*  76 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[17], true);
/*     */     }
/*  78 */     else if (metadata == 13) {
/*  79 */       block.func_149676_a(W4, W2, W4, W12, W14, W12);
/*  80 */       renderer.func_147775_a(block);
/*  81 */       drawFaces(renderer, block, ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[19], ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[19], ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[18], ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[18], ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[18], ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[18], true);
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/*  86 */     else if (metadata == 14) {
/*  87 */       GL11.glPushMatrix();
/*  88 */       GL11.glScaled(1.5D, 1.5D, 1.5D);
/*  89 */       GL11.glTranslatef(-0.5F, -0.25F, -0.5F);
/*  90 */       TileEntityRendererDispatcher.field_147556_a.func_147549_a(new TileVisRelay(), 0.0D, 0.0D, 0.0D, 0.0F);
/*  91 */       GL11.glEnable(32826);
/*  92 */       GL11.glPopMatrix();
/*     */     }
/*  94 */     else if (metadata == 2) {
/*  95 */       GL11.glPushMatrix();
/*  96 */       GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/*  97 */       TileEntityRendererDispatcher.field_147556_a.func_147549_a(new TileMagicWorkbenchCharger(), 0.0D, 0.0D, 0.0D, 0.0F);
/*  98 */       GL11.glEnable(32826);
/*  99 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
/*     */   {
/* 105 */     int metadata = world.func_72805_g(x, y, z);
/*     */     
/* 107 */     if (metadata == 0) {
/* 108 */       block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 109 */       renderer.func_147775_a(block);
/* 110 */       renderer.func_147784_q(block, x, y, z);
/*     */       
/* 112 */       IIcon innerSide = ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[5];
/* 113 */       IIcon bottom = ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[6];
/* 114 */       float f5 = 0.123F;
/* 115 */       TileEntity te = world.func_147438_o(x, y, z);
/* 116 */       if ((te != null) && ((te instanceof TileCrucible)) && (((TileCrucible)te).aspects.size() > 0))
/* 117 */         setBrightness(world, x, y, z, block);
/* 118 */       renderer.func_147764_f(block, x - 1.0F + f5, y, z, innerSide);
/* 119 */       renderer.func_147798_e(block, x + 1.0F - f5, y, z, innerSide);
/* 120 */       renderer.func_147734_d(block, x, y, z - 1.0F + f5, innerSide);
/* 121 */       renderer.func_147761_c(block, x, y, z + 1.0F - f5, innerSide);
/* 122 */       renderer.func_147806_b(block, x, y - 1.0F + 0.25F, z, bottom);
/* 123 */       renderer.func_147768_a(block, x, y + 1.0F - 0.75F, z, bottom);
/*     */     }
/* 125 */     else if ((metadata == 5) || (metadata == 6)) {
/* 126 */       setBrightness(world, x, y, z, block);
/* 127 */       block.func_149676_a(0.0F, 0.8125F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 128 */       renderer.func_147775_a(block);
/* 129 */       renderer.func_147784_q(block, x, y, z);
/*     */       
/* 131 */       renderer.func_147764_f(block, x - 1.002F + W3, y, z, block.func_149733_h(0));
/* 132 */       renderer.func_147798_e(block, x + 1.002F - W3, y, z, block.func_149733_h(0));
/* 133 */       renderer.func_147734_d(block, x, y, z - 1.002F + W3, block.func_149733_h(0));
/* 134 */       renderer.func_147761_c(block, x, y, z + 1.002F - W3, block.func_149733_h(0));
/*     */       
/* 136 */       renderer.func_147764_f(block, x - 1.002F + W9, y, z, block.func_149733_h(0));
/* 137 */       renderer.func_147798_e(block, x + 1.002F - W9, y, z, block.func_149733_h(0));
/* 138 */       renderer.func_147734_d(block, x, y, z - 1.002F + W9, block.func_149733_h(0));
/* 139 */       renderer.func_147761_c(block, x, y, z + 1.002F - W9, block.func_149733_h(0));
/*     */       
/* 141 */       if (metadata == 6) {
/* 142 */         block.func_149676_a(W1, W14, W1, W15, W15, W15);
/* 143 */         renderer.func_147775_a(block);
/* 144 */         renderer.field_147840_d = ((thaumcraft.common.blocks.BlockMetalDevice)block).icon[9];
/* 145 */         renderer.func_147784_q(block, x, y, z);
/*     */       }
/*     */     }
/* 148 */     else if ((metadata == 7) || (metadata == 8) || (metadata == 13)) {
/* 149 */       block.func_149676_a(W4, W2, W4, W12, W14, W12);
/* 150 */       renderer.func_147775_a(block);
/* 151 */       renderer.func_147784_q(block, x, y, z);
/*     */     }
/* 153 */     else if ((metadata == 3) || (metadata == 9)) {
/* 154 */       block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 155 */       renderer.func_147775_a(block);
/* 156 */       renderer.func_147784_q(block, x, y, z);
/*     */     }
/* 158 */     else if (metadata == 12) {
/* 159 */       block.func_149676_a(W3, W3, W3, W13, W13, W13);
/* 160 */       renderer.func_147775_a(block);
/* 161 */       renderer.func_147784_q(block, x, y, z);
/* 162 */       TileEntity te = world.func_147438_o(x, y, z);
/* 163 */       if ((te != null) && ((te instanceof TileBrainbox))) {
/* 164 */         switch (((TileBrainbox)te).facing) {
/*     */         case UP: 
/* 166 */           block.func_149676_a(W6, W13, W6, W10, 1.0F, W10);
/* 167 */           renderer.func_147775_a(block);
/* 168 */           renderer.func_147784_q(block, x, y, z);
/* 169 */           break;
/*     */         case DOWN: 
/* 171 */           block.func_149676_a(W6, 0.0F, W6, W10, W3, W10);
/* 172 */           renderer.func_147775_a(block);
/* 173 */           renderer.func_147784_q(block, x, y, z);
/* 174 */           break;
/*     */         case EAST: 
/* 176 */           block.func_149676_a(W13, W6, W6, 1.0F, W10, W10);
/* 177 */           renderer.func_147775_a(block);
/* 178 */           renderer.func_147784_q(block, x, y, z);
/* 179 */           break;
/*     */         case WEST: 
/* 181 */           block.func_149676_a(0.0F, W6, W6, W3, W10, W10);
/* 182 */           renderer.func_147775_a(block);
/* 183 */           renderer.func_147784_q(block, x, y, z);
/* 184 */           break;
/*     */         case SOUTH: 
/* 186 */           block.func_149676_a(W6, W6, W13, W10, W10, 1.0F);
/* 187 */           renderer.func_147775_a(block);
/* 188 */           renderer.func_147784_q(block, x, y, z);
/* 189 */           break;
/*     */         case NORTH: 
/* 191 */           block.func_149676_a(W6, W6, 0.0F, W10, W10, W3);
/* 192 */           renderer.func_147775_a(block);
/* 193 */           renderer.func_147784_q(block, x, y, z);
/*     */         }
/*     */         
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 201 */     renderer.func_147771_a();
/* 202 */     block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 203 */     renderer.func_147775_a(block);
/* 204 */     return true;
/*     */   }
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId)
/*     */   {
/* 209 */     return true;
/*     */   }
/*     */   
/*     */   public int getRenderId()
/*     */   {
/* 214 */     return ConfigBlocks.blockMetalDeviceRI;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/block/BlockMetalDeviceRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */