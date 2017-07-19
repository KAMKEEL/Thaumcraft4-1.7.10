/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.HashMap;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.WorldCoordinates;
/*     */ import thaumcraft.common.blocks.BlockCosmeticOpaque;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import thaumcraft.common.items.wands.foci.ItemFocusWarding;
/*     */ import thaumcraft.common.tiles.TileWarded;
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class TileWardedRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*     */   public void renderTileEntityAt(TileWarded tile, double x, double y, double z, float f)
/*     */   {
/*  36 */     EntityLivingBase viewer = Minecraft.func_71410_x().field_71451_h;
/*  37 */     if ((viewer instanceof EntityPlayer)) {
/*  38 */       EntityPlayer player = (EntityPlayer)viewer;
/*  39 */       if ((player.func_71045_bC() != null) && ((player.func_71045_bC().func_77973_b() instanceof ItemWandCasting))) {
/*  40 */         ItemWandCasting wand = (ItemWandCasting)player.func_71045_bC().func_77973_b();
/*  41 */         if ((wand.getFocus(player.func_71045_bC()) != null) && ((wand.getFocus(player.func_71045_bC()) instanceof ItemFocusWarding)))
/*     */         {
/*     */ 
/*  44 */           GL11.glPushMatrix();
/*  45 */           GL11.glEnable(3042);
/*  46 */           GL11.glBlendFunc(770, 1);
/*  47 */           GL11.glAlphaFunc(516, 0.003921569F);
/*  48 */           GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
/*     */           
/*  50 */           World world = tile.func_145831_w();
/*  51 */           RenderBlocks renderBlocks = new RenderBlocks();
/*     */           
/*  53 */           GL11.glDisable(2896);
/*     */           
/*  55 */           Tessellator t = Tessellator.field_78398_a;
/*     */           
/*  57 */           renderBlocks.func_147782_a(-0.0010000000474974513D, -0.0010000000474974513D, -0.0010000000474974513D, 1.0010000467300415D, 1.0010000467300415D, 1.0010000467300415D);
/*  58 */           if (tile.owner == ((EntityPlayer)viewer).func_70005_c_().hashCode()) {
/*  59 */             float r = MathHelper.func_76126_a(player.field_70173_aa / 2.0F + tile.field_145851_c) * 0.2F + 0.8F;
/*  60 */             float g = MathHelper.func_76126_a(player.field_70173_aa / 3.0F + tile.field_145848_d) * 0.2F + 0.7F;
/*  61 */             float b = MathHelper.func_76126_a(player.field_70173_aa / 4.0F + tile.field_145849_e) * 0.2F + 0.28F;
/*  62 */             GL11.glColor4f(r, g, b, 0.5F);
/*     */           } else {
/*  64 */             float r = MathHelper.func_76126_a(player.field_70173_aa / 2.0F + tile.field_145851_c) * 0.2F + 0.8F;
/*  65 */             float g = MathHelper.func_76126_a(player.field_70173_aa / 3.0F + tile.field_145848_d) * 0.2F + 0.28F;
/*  66 */             float b = MathHelper.func_76126_a(player.field_70173_aa / 4.0F + tile.field_145849_e) * 0.2F + 0.28F;
/*  67 */             GL11.glColor4f(r, g, b, 0.25F);
/*     */           }
/*  69 */           t.func_78382_b();
/*     */           
/*  71 */           t.func_78380_c(200);
/*     */           
/*  73 */           this.field_147501_a.field_147553_e.func_110577_a(TextureMap.field_110575_b);
/*  74 */           GL11.glTexEnvi(8960, 8704, 260);
/*     */           
/*  76 */           if (shouldSideBeRendered(world, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 1))
/*  77 */             renderBlocks.func_147768_a(ConfigBlocks.blockJar, -0.5001D, 0.0D, -0.5001D, getIconOnSide(world, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 0, tile.owner, player.field_70173_aa));
/*  78 */           if (shouldSideBeRendered(world, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 0))
/*  79 */             renderBlocks.func_147806_b(ConfigBlocks.blockJar, -0.5001D, 0.0D, -0.5001D, getIconOnSide(world, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 1, tile.owner, player.field_70173_aa));
/*  80 */           if (shouldSideBeRendered(world, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 3))
/*  81 */             renderBlocks.func_147761_c(ConfigBlocks.blockJar, -0.5001D, 0.0D, -0.5001D, getIconOnSide(world, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 2, tile.owner, player.field_70173_aa));
/*  82 */           if (shouldSideBeRendered(world, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 2))
/*  83 */             renderBlocks.func_147734_d(ConfigBlocks.blockJar, -0.5001D, 0.0D, -0.5001D, getIconOnSide(world, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 3, tile.owner, player.field_70173_aa));
/*  84 */           if (shouldSideBeRendered(world, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 5))
/*  85 */             renderBlocks.func_147798_e(ConfigBlocks.blockJar, -0.5001D, 0.0D, -0.5001D, getIconOnSide(world, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 4, tile.owner, player.field_70173_aa));
/*  86 */           if (shouldSideBeRendered(world, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 4)) {
/*  87 */             renderBlocks.func_147764_f(ConfigBlocks.blockJar, -0.5001D, 0.0D, -0.5001D, getIconOnSide(world, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 5, tile.owner, player.field_70173_aa));
/*     */           }
/*  89 */           t.func_78381_a();
/*  90 */           GL11.glTexEnvi(8960, 8704, 8448);
/*  91 */           GL11.glEnable(2896);
/*  92 */           GL11.glAlphaFunc(516, 0.1F);
/*  93 */           GL11.glDisable(3042);
/*  94 */           GL11.glColor3f(1.0F, 1.0F, 1.0F);
/*  95 */           GL11.glPopMatrix();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean shouldSideBeRendered(World world, int x, int y, int z, int side)
/*     */   {
/* 107 */     if (world.func_72805_g(x, y, z) != world.func_72805_g(x - net.minecraft.util.Facing.field_71586_b[side], y - net.minecraft.util.Facing.field_71587_c[side], z - net.minecraft.util.Facing.field_71585_d[side]))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 112 */       return true;
/*     */     }
/*     */     
/* 115 */     if (world.func_147439_a(x - net.minecraft.util.Facing.field_71586_b[side], y - net.minecraft.util.Facing.field_71587_c[side], z - net.minecraft.util.Facing.field_71585_d[side]) != ConfigBlocks.blockWarded)
/*     */     {
/*     */ 
/*     */ 
/* 119 */       return true;
/*     */     }
/*     */     
/* 122 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isConnectedBlock(World world, int x, int y, int z, int owner) {
/* 126 */     if (world.func_147439_a(x, y, z) == ConfigBlocks.blockWarded) {
/* 127 */       TileEntity tile = world.func_147438_o(x, y, z);
/* 128 */       if ((tile != null) && ((tile instanceof TileWarded))) {
/* 129 */         return ((TileWarded)tile).owner == owner;
/*     */       }
/*     */     }
/* 132 */     return false;
/*     */   }
/*     */   
/* 135 */   static HashMap<WorldCoordinates, IIcon> iconCache = new HashMap();
/*     */   
/*     */   private IIcon getIconOnSide(World world, int x, int y, int z, int side, int owner, int ticks) {
/* 138 */     WorldCoordinates wc = new WorldCoordinates(x, y, z, side);
/* 139 */     IIcon out = (IIcon)iconCache.get(wc);
/* 140 */     if (((ticks + side) % 10 == 0) || (out == null)) {
/* 141 */       boolean[] bitMatrix = new boolean[8];
/* 142 */       if ((side == 0) || (side == 1)) {
/* 143 */         bitMatrix[0] = isConnectedBlock(world, x - 1, y, z - 1, owner);
/* 144 */         bitMatrix[1] = isConnectedBlock(world, x, y, z - 1, owner);
/* 145 */         bitMatrix[2] = isConnectedBlock(world, x + 1, y, z - 1, owner);
/* 146 */         bitMatrix[3] = isConnectedBlock(world, x - 1, y, z, owner);
/* 147 */         bitMatrix[4] = isConnectedBlock(world, x + 1, y, z, owner);
/* 148 */         bitMatrix[5] = isConnectedBlock(world, x - 1, y, z + 1, owner);
/* 149 */         bitMatrix[6] = isConnectedBlock(world, x, y, z + 1, owner);
/* 150 */         bitMatrix[7] = isConnectedBlock(world, x + 1, y, z + 1, owner);
/*     */       }
/* 152 */       if ((side == 2) || (side == 3)) {
/* 153 */         bitMatrix[0] = isConnectedBlock(world, x + (side == 2 ? 1 : -1), y + 1, z, owner);
/* 154 */         bitMatrix[1] = isConnectedBlock(world, x, y + 1, z, owner);
/* 155 */         bitMatrix[2] = isConnectedBlock(world, x + (side == 3 ? 1 : -1), y + 1, z, owner);
/* 156 */         bitMatrix[3] = isConnectedBlock(world, x + (side == 2 ? 1 : -1), y, z, owner);
/* 157 */         bitMatrix[4] = isConnectedBlock(world, x + (side == 3 ? 1 : -1), y, z, owner);
/* 158 */         bitMatrix[5] = isConnectedBlock(world, x + (side == 2 ? 1 : -1), y - 1, z, owner);
/* 159 */         bitMatrix[6] = isConnectedBlock(world, x, y - 1, z, owner);
/* 160 */         bitMatrix[7] = isConnectedBlock(world, x + (side == 3 ? 1 : -1), y - 1, z, owner);
/*     */       }
/* 162 */       if ((side == 4) || (side == 5)) {
/* 163 */         bitMatrix[0] = isConnectedBlock(world, x, y + 1, z + (side == 5 ? 1 : -1), owner);
/* 164 */         bitMatrix[1] = isConnectedBlock(world, x, y + 1, z, owner);
/* 165 */         bitMatrix[2] = isConnectedBlock(world, x, y + 1, z + (side == 4 ? 1 : -1), owner);
/* 166 */         bitMatrix[3] = isConnectedBlock(world, x, y, z + (side == 5 ? 1 : -1), owner);
/* 167 */         bitMatrix[4] = isConnectedBlock(world, x, y, z + (side == 4 ? 1 : -1), owner);
/* 168 */         bitMatrix[5] = isConnectedBlock(world, x, y - 1, z + (side == 5 ? 1 : -1), owner);
/* 169 */         bitMatrix[6] = isConnectedBlock(world, x, y - 1, z, owner);
/* 170 */         bitMatrix[7] = isConnectedBlock(world, x, y - 1, z + (side == 4 ? 1 : -1), owner);
/*     */       }
/* 172 */       int idBuilder = 0;
/* 173 */       for (int i = 0; i <= 7; i++) {
/* 174 */         idBuilder += (bitMatrix[i] != 0 ? 128 : i == 6 ? 64 : i == 5 ? 32 : i == 4 ? 16 : i == 3 ? 8 : i == 2 ? 4 : i == 1 ? 2 : i == 0 ? 1 : 0);
/*     */       }
/*     */       
/* 177 */       ((BlockCosmeticOpaque)ConfigBlocks.blockCosmeticOpaque);((BlockCosmeticOpaque)ConfigBlocks.blockCosmeticOpaque);out = (idBuilder > 255) || (idBuilder < 0) ? BlockCosmeticOpaque.wardedGlassIcon[0] : BlockCosmeticOpaque.wardedGlassIcon[thaumcraft.client.lib.UtilsFX.connectedTextureRefByID[idBuilder]];
/*     */       
/*     */ 
/* 180 */       iconCache.put(wc, out);
/*     */     }
/* 182 */     return out;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
/*     */   {
/* 189 */     renderTileEntityAt((TileWarded)par1TileEntity, par2, par4, par6, par8);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileWardedRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */