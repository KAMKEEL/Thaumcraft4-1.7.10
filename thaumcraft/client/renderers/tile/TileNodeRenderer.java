/*     */ package thaumcraft.client.renderers.tile;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.awt.Color;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.nodes.INode;
/*     */ import thaumcraft.api.nodes.IRevealer;
/*     */ import thaumcraft.api.nodes.NodeModifier;
/*     */ import thaumcraft.api.nodes.NodeType;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.items.relics.ItemThaumometer;
/*     */ import thaumcraft.common.tiles.TileJarNode;
/*     */ import thaumcraft.common.tiles.TileNode;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class TileNodeRenderer extends net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
/*     */ {
/*  32 */   public static final ResourceLocation nodetex = new ResourceLocation("thaumcraft", "textures/misc/nodes.png");
/*     */   
/*     */   public static void renderNode(EntityLivingBase viewer, double viewDistance, boolean visible, boolean depthIgnore, float size, int x, int y, int z, float partialTicks, AspectList aspects, NodeType type, NodeModifier mod)
/*     */   {
/*  36 */     long nt = System.nanoTime();
/*     */     
/*  38 */     UtilsFX.bindTexture(nodetex);
/*  39 */     int frames = 32;
/*     */     
/*  41 */     if ((aspects.size() > 0) && (visible))
/*     */     {
/*  43 */       double distance = viewer.func_70011_f(x + 0.5D, y + 0.5D, z + 0.5D);
/*  44 */       if (distance > viewDistance) { return;
/*     */       }
/*  46 */       float alpha = (float)((viewDistance - distance) / viewDistance);
/*     */       
/*  48 */       if (mod != null) {
/*  49 */         switch (mod) {
/*  50 */         case BRIGHT:  alpha *= 1.5F; break;
/*  51 */         case PALE:  alpha *= 0.66F; break;
/*     */         case FADING: 
/*  53 */           alpha *= (MathHelper.func_76126_a(viewer.field_70173_aa / 3.0F) * 0.25F + 0.33F);
/*     */         }
/*     */         
/*     */       }
/*     */       
/*     */ 
/*  59 */       GL11.glPushMatrix();
/*  60 */       GL11.glAlphaFunc(516, 0.003921569F);
/*  61 */       GL11.glDepthMask(false);
/*  62 */       if (depthIgnore) GL11.glDisable(2929);
/*  63 */       GL11.glDisable(2884);
/*     */       
/*  65 */       long time = nt / 5000000L;
/*  66 */       float bscale = 0.25F;
/*     */       
/*  68 */       GL11.glPushMatrix();
/*  69 */       float rad = 6.2831855F;
/*  70 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);
/*     */       
/*     */ 
/*  73 */       int i = (int)((nt / 40000000L + x) % frames);
/*     */       
/*  75 */       int count = 0;
/*  76 */       float scale = 0.0F;
/*  77 */       float angle = 0.0F;
/*  78 */       float average = 0.0F;
/*     */       
/*     */ 
/*  81 */       for (Aspect aspect : aspects.getAspects()) {
/*  82 */         if (aspect.getBlend() == 771) alpha = (float)(alpha * 1.5D);
/*  83 */         average += aspects.getAmount(aspect);
/*  84 */         GL11.glPushMatrix();
/*  85 */         GL11.glEnable(3042);
/*  86 */         GL11.glBlendFunc(770, aspect.getBlend());
/*  87 */         scale = MathHelper.func_76126_a(viewer.field_70173_aa / (14.0F - count)) * bscale + bscale * 2.0F;
/*  88 */         scale = 0.2F + scale * (aspects.getAmount(aspect) / 50.0F);
/*  89 */         scale *= size;
/*  90 */         angle = (float)(time % (5000 + 500 * count)) / (5000.0F + 500 * count) * rad;
/*  91 */         UtilsFX.renderFacingStrip(x + 0.5D, y + 0.5D, z + 0.5D, angle, scale, alpha / Math.max(1.0F, aspects.size() / 2.0F), frames, 0, i, partialTicks, aspect.getColor());
/*  92 */         GL11.glDisable(3042);
/*  93 */         GL11.glPopMatrix();
/*  94 */         count++;
/*  95 */         if (aspect.getBlend() == 771) { alpha = (float)(alpha / 1.5D);
/*     */         }
/*     */       }
/*     */       
/*  99 */       average /= aspects.size();
/* 100 */       GL11.glPushMatrix();
/* 101 */       GL11.glEnable(3042);
/* 102 */       i = (int)((nt / 40000000L + x) % frames);
/* 103 */       scale = 0.1F + average / 150.0F;
/* 104 */       scale *= size;
/* 105 */       int strip = 1;
/* 106 */       switch (type) {
/*     */       case NORMAL: 
/* 108 */         GL11.glBlendFunc(770, 1);
/* 109 */         break;
/*     */       case UNSTABLE: 
/* 111 */         GL11.glBlendFunc(770, 1);
/* 112 */         strip = 6;
/* 113 */         angle = 0.0F;
/* 114 */         break;
/*     */       case DARK: 
/* 116 */         GL11.glBlendFunc(770, 771);
/* 117 */         strip = 2;
/* 118 */         break;
/*     */       case TAINTED: 
/* 120 */         GL11.glBlendFunc(770, 771);
/* 121 */         strip = 5;
/* 122 */         break;
/*     */       case PURE: 
/* 124 */         GL11.glBlendFunc(770, 1);
/* 125 */         strip = 4;
/* 126 */         break;
/*     */       case HUNGRY: 
/* 128 */         scale *= 0.75F;
/* 129 */         GL11.glBlendFunc(770, 1);
/* 130 */         strip = 3;
/*     */       }
/*     */       
/*     */       
/* 134 */       GL11.glColor4f(1.0F, 0.0F, 1.0F, alpha);
/* 135 */       UtilsFX.renderFacingStrip(x + 0.5D, y + 0.5D, z + 0.5D, angle, scale, alpha, frames, strip, i, partialTicks, 16777215);
/* 136 */       GL11.glDisable(3042);
/* 137 */       GL11.glPopMatrix();
/*     */       
/* 139 */       GL11.glPopMatrix();
/*     */       
/* 141 */       GL11.glEnable(2884);
/* 142 */       if (depthIgnore) GL11.glEnable(2929);
/* 143 */       GL11.glDepthMask(true);
/* 144 */       GL11.glAlphaFunc(516, 0.1F);
/* 145 */       GL11.glPopMatrix();
/*     */     } else {
/* 147 */       GL11.glPushMatrix();
/* 148 */       GL11.glAlphaFunc(516, 0.003921569F);
/* 149 */       GL11.glEnable(3042);
/* 150 */       GL11.glBlendFunc(770, 1);
/* 151 */       GL11.glDepthMask(false);
/*     */       
/* 153 */       int i = (int)((nt / 40000000L + x) % frames);
/* 154 */       GL11.glColor4f(1.0F, 0.0F, 1.0F, 0.1F);
/* 155 */       UtilsFX.renderFacingStrip(x + 0.5D, y + 0.5D, z + 0.5D, 0.0F, 0.5F, 0.1F, frames, 1, i, partialTicks, 16777215);
/*     */       
/* 157 */       GL11.glDepthMask(true);
/* 158 */       GL11.glDisable(3042);
/* 159 */       GL11.glAlphaFunc(516, 0.1F);
/* 160 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_147500_a(TileEntity tile, double x, double y, double z, float partialTicks)
/*     */   {
/* 169 */     if (!(tile instanceof INode)) { return;
/*     */     }
/* 171 */     float size = 1.0F;
/* 172 */     INode node = (INode)tile;
/* 173 */     double viewDistance = 64.0D;
/* 174 */     EntityLivingBase viewer = Minecraft.func_71410_x().field_71451_h;
/* 175 */     boolean condition = false;
/* 176 */     boolean depthIgnore = false;
/* 177 */     if ((viewer instanceof EntityPlayer)) {
/* 178 */       if ((tile != null) && ((tile instanceof TileJarNode))) {
/* 179 */         condition = true;
/* 180 */         size = 0.7F;
/*     */       }
/* 182 */       else if ((((EntityPlayer)viewer).field_71071_by.func_70440_f(3) != null) && ((((EntityPlayer)viewer).field_71071_by.func_70440_f(3).func_77973_b() instanceof IRevealer)) && (((IRevealer)((EntityPlayer)viewer).field_71071_by.func_70440_f(3).func_77973_b()).showNodes(((EntityPlayer)viewer).field_71071_by.func_70440_f(3), viewer)))
/*     */       {
/*     */ 
/* 185 */         condition = true;
/* 186 */         depthIgnore = true;
/*     */       }
/* 188 */       else if ((((EntityPlayer)viewer).field_71071_by.func_70448_g() != null) && ((((EntityPlayer)viewer).field_71071_by.func_70448_g().func_77973_b() instanceof ItemThaumometer)))
/*     */       {
/* 190 */         if (UtilsFX.isVisibleTo(0.44F, viewer, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e)) {
/* 191 */           condition = true;
/* 192 */           depthIgnore = true;
/* 193 */           viewDistance = 48.0D;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 199 */     renderNode(viewer, viewDistance, condition, depthIgnore, size, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, partialTicks, ((INode)tile).getAspects(), ((INode)tile).getNodeType(), ((INode)tile).getNodeModifier());
/*     */     
/*     */ 
/*     */ 
/* 203 */     if (((tile instanceof TileNode)) && (((TileNode)tile).drainEntity != null) && (((TileNode)tile).drainCollision != null))
/*     */     {
/*     */ 
/*     */ 
/* 207 */       Entity drainEntity = ((TileNode)tile).drainEntity;
/* 208 */       if (((drainEntity instanceof EntityPlayer)) && (!((EntityPlayer)drainEntity).func_71039_bw())) {
/* 209 */         ((TileNode)tile).drainEntity = null;
/* 210 */         ((TileNode)tile).drainCollision = null;
/* 211 */         return;
/*     */       }
/*     */       
/* 214 */       MovingObjectPosition drainCollision = ((TileNode)tile).drainCollision;
/* 215 */       GL11.glPushMatrix();
/*     */       
/* 217 */       float f10 = 0.0F;
/* 218 */       int iiud = ((EntityPlayer)drainEntity).func_71057_bx();
/* 219 */       if ((drainEntity instanceof EntityPlayer))
/* 220 */         f10 = MathHelper.func_76126_a(iiud / 10.0F) * 10.0F;
/* 221 */       Vec3 vec3 = Vec3.func_72443_a(-0.1D, -0.1D, 0.5D);
/* 222 */       vec3.func_72440_a(-(drainEntity.field_70127_C + (drainEntity.field_70125_A - drainEntity.field_70127_C) * partialTicks) * 3.1415927F / 180.0F);
/* 223 */       vec3.func_72442_b(-(drainEntity.field_70126_B + (drainEntity.field_70177_z - drainEntity.field_70126_B) * partialTicks) * 3.1415927F / 180.0F);
/* 224 */       vec3.func_72442_b(-f10 * 0.01F);
/* 225 */       vec3.func_72440_a(-f10 * 0.015F);
/* 226 */       double d3 = drainEntity.field_70169_q + (drainEntity.field_70165_t - drainEntity.field_70169_q) * partialTicks + vec3.field_72450_a;
/* 227 */       double d4 = drainEntity.field_70167_r + (drainEntity.field_70163_u - drainEntity.field_70167_r) * partialTicks + vec3.field_72448_b;
/* 228 */       double d5 = drainEntity.field_70166_s + (drainEntity.field_70161_v - drainEntity.field_70166_s) * partialTicks + vec3.field_72449_c;
/* 229 */       double d6 = drainEntity == Minecraft.func_71410_x().field_71439_g ? 0.0D : drainEntity.func_70047_e();
/*     */       
/* 231 */       UtilsFX.drawFloatyLine(d3, d4 + d6, d5, drainCollision.field_72311_b + 0.5D, drainCollision.field_72312_c + 0.5D, drainCollision.field_72309_d + 0.5D, partialTicks, ((TileNode)tile).color.getRGB(), "textures/misc/wispy.png", -0.02F, Math.min(iiud, 10) / 10.0F);
/*     */       
/* 233 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/TileNodeRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */