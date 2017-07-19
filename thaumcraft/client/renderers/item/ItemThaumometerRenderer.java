/*     */ package thaumcraft.client.renderers.item;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.renderer.EntityRenderer;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.entity.RenderPlayer;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.IItemRenderer;
/*     */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/*     */ import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
/*     */ import net.minecraftforge.client.model.IModelCustom;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.nodes.INode;
/*     */ import thaumcraft.api.research.IScanEventHandler;
/*     */ import thaumcraft.api.research.ScanResult;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.lib.research.ScanManager;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ public class ItemThaumometerRenderer implements IItemRenderer
/*     */ {
/*     */   private IModelCustom model;
/*  45 */   private static final ResourceLocation SCANNER = new ResourceLocation("thaumcraft", "textures/models/scanner.obj");
/*     */   
/*     */   public ItemThaumometerRenderer() {
/*  48 */     this.model = net.minecraftforge.client.model.AdvancedModelLoader.loadModel(SCANNER);
/*     */   }
/*     */   
/*     */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
/*     */   {
/*  53 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
/*     */   {
/*  60 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
/*     */   {
/*  67 */     Minecraft mc = Minecraft.func_71410_x();
/*  68 */     int rve_id = 0;
/*  69 */     int player_id = 0;
/*  70 */     if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
/*  71 */       rve_id = mc.field_71451_h.func_145782_y();
/*  72 */       player_id = ((EntityLivingBase)data[1]).func_145782_y();
/*     */     }
/*  74 */     EntityPlayer playermp = mc.field_71439_g;
/*     */     
/*  76 */     float par1 = UtilsFX.getTimer(mc).field_74281_c;
/*  77 */     float var7 = 0.8F;
/*  78 */     EntityPlayerSP playersp = (EntityPlayerSP)playermp;
/*     */     
/*  80 */     GL11.glPushMatrix();
/*     */     
/*  82 */     if ((type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) && (player_id == rve_id) && (mc.field_71474_y.field_74320_O == 0))
/*     */     {
/*  84 */       GL11.glTranslatef(1.0F, 0.75F, -1.0F);
/*  85 */       GL11.glRotatef(135.0F, 0.0F, -1.0F, 0.0F);
/*     */       
/*  87 */       float f3 = playersp.field_71164_i + (playersp.field_71155_g - playersp.field_71164_i) * par1;
/*  88 */       float f4 = playersp.field_71163_h + (playersp.field_71154_f - playersp.field_71163_h) * par1;
/*  89 */       GL11.glRotatef((playermp.field_70125_A - f3) * 0.1F, 1.0F, 0.0F, 0.0F);
/*  90 */       GL11.glRotatef((playermp.field_70177_z - f4) * 0.1F, 0.0F, 1.0F, 0.0F);
/*     */       
/*  92 */       float var4 = playermp.field_70127_C + (playermp.field_70125_A - playermp.field_70127_C) * par1;
/*     */       
/*     */ 
/*     */ 
/*  96 */       float f1 = UtilsFX.getPrevEquippedProgress(mc.field_71460_t.field_78516_c) + (UtilsFX.getEquippedProgress(mc.field_71460_t.field_78516_c) - UtilsFX.getPrevEquippedProgress(mc.field_71460_t.field_78516_c)) * par1;
/*     */       
/*     */ 
/*     */ 
/* 100 */       GL11.glTranslatef(-0.7F * var7, -(-0.65F * var7) + (1.0F - f1) * 1.5F, 0.9F * var7);
/*     */       
/* 102 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*     */       
/* 104 */       GL11.glTranslatef(0.0F, 0.0F * var7, -0.9F * var7);
/* 105 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 106 */       GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
/* 107 */       GL11.glEnable(32826);
/*     */       
/* 109 */       GL11.glPushMatrix();
/* 110 */       GL11.glScalef(5.0F, 5.0F, 5.0F);
/*     */       
/* 112 */       mc.field_71446_o.func_110577_a(mc.field_71439_g.func_110306_p());
/*     */       
/*     */ 
/* 115 */       for (int var9 = 0; var9 < 2; var9++)
/*     */       {
/* 117 */         int var22 = var9 * 2 - 1;
/* 118 */         GL11.glPushMatrix();
/* 119 */         GL11.glTranslatef(-0.0F, -0.6F, 1.1F * var22);
/* 120 */         GL11.glRotatef(-45 * var22, 1.0F, 0.0F, 0.0F);
/* 121 */         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
/* 122 */         GL11.glRotatef(59.0F, 0.0F, 0.0F, 1.0F);
/* 123 */         GL11.glRotatef(-65 * var22, 0.0F, 1.0F, 0.0F);
/* 124 */         Render var24 = RenderManager.field_78727_a.func_78713_a(mc.field_71439_g);
/* 125 */         RenderPlayer var26 = (RenderPlayer)var24;
/* 126 */         float var13 = 1.0F;
/* 127 */         GL11.glScalef(var13, var13, var13);
/* 128 */         var26.func_82441_a(mc.field_71439_g);
/* 129 */         GL11.glPopMatrix();
/*     */       }
/* 131 */       GL11.glPopMatrix();
/* 132 */       GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/*     */       
/*     */ 
/* 135 */       GL11.glTranslatef(0.4F, -0.4F, 0.0F);
/* 136 */       GL11.glEnable(32826);
/* 137 */       GL11.glScalef(2.0F, 2.0F, 2.0F);
/*     */     } else {
/* 139 */       GL11.glScalef(0.5F, 0.5F, 0.5F);
/* 140 */       if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
/* 141 */         GL11.glTranslatef(1.6F, 0.3F, 2.0F);
/* 142 */         GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
/* 143 */         GL11.glRotatef(30.0F, 0.0F, 0.0F, -1.0F);
/*     */       }
/* 145 */       else if (type == IItemRenderer.ItemRenderType.INVENTORY) {
/* 146 */         GL11.glRotatef(60.0F, 1.0F, 0.0F, 0.0F);
/* 147 */         GL11.glRotatef(30.0F, 0.0F, 0.0F, -1.0F);
/* 148 */         GL11.glRotatef(248.0F, 0.0F, -1.0F, 0.0F);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 153 */     UtilsFX.bindTexture("textures/models/scanner.png");
/* 154 */     this.model.renderAll();
/*     */     
/* 156 */     GL11.glPushMatrix();
/* 157 */     GL11.glTranslatef(0.0F, 0.11F, 0.0F);
/* 158 */     GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 159 */     GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/* 160 */     UtilsFX.renderQuadCenteredFromTexture("textures/models/scanscreen.png", 2.5F, 1.0F, 1.0F, 1.0F, (int)(190.0F + MathHelper.func_76126_a(playermp.field_70173_aa - playermp.field_70170_p.field_73012_v.nextInt(2)) * 10.0F + 10.0F), 771, 1.0F);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 165 */     if (((playermp instanceof EntityPlayer)) && (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) && (player_id == rve_id) && (mc.field_71474_y.field_74320_O == 0))
/*     */     {
/*     */ 
/*     */ 
/* 169 */       RenderHelper.func_74518_a();
/*     */       
/* 171 */       int j = (int)(190.0F + MathHelper.func_76126_a(playermp.field_70173_aa - playermp.field_70170_p.field_73012_v.nextInt(2)) * 10.0F + 10.0F);
/* 172 */       int k = j % 65536;
/* 173 */       int l = j / 65536;
/* 174 */       OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/*     */       
/* 176 */       ScanResult scan = doScan(playermp.field_71071_by.func_70448_g(), playermp);
/*     */       
/* 178 */       if (scan != null) {
/* 179 */         AspectList aspects = null;
/* 180 */         GL11.glTranslatef(0.0F, 0.0F, -0.01F);
/* 181 */         String text = "?";
/* 182 */         ItemStack stack = null;
/*     */         
/* 184 */         if (scan.id > 0) {
/* 185 */           stack = new ItemStack(Item.func_150899_d(scan.id), 1, scan.meta);
/* 186 */           if (ScanManager.hasBeenScanned(playermp, scan)) {
/* 187 */             aspects = ScanManager.getScanAspects(scan, playermp.field_70170_p);
/*     */           }
/*     */         }
/*     */         
/* 191 */         if (scan.type == 2) {
/* 192 */           if ((scan.entity instanceof EntityItem)) {
/* 193 */             stack = ((EntityItem)scan.entity).func_92059_d();
/*     */           } else {
/* 195 */             text = scan.entity.func_70005_c_();
/*     */           }
/* 197 */           if (ScanManager.hasBeenScanned(playermp, scan)) {
/* 198 */             aspects = ScanManager.getScanAspects(scan, playermp.field_70170_p);
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 203 */         if ((scan.type == 3) && 
/* 204 */           (scan.phenomena.startsWith("NODE")) && (ScanManager.hasBeenScanned(playermp, scan))) {
/* 205 */           MovingObjectPosition mop = null;
/* 206 */           if ((stack != null) && (stack.func_77973_b() != null))
/* 207 */             mop = EntityUtils.getMovingObjectPositionFromPlayer(playermp.field_70170_p, playermp, true);
/* 208 */           if ((mop != null) && (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK)) {
/* 209 */             TileEntity tile = playermp.field_70170_p.func_147438_o(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/* 210 */             if ((tile != null) && ((tile instanceof INode))) {
/* 211 */               aspects = ((INode)tile).getAspects();
/*     */               
/* 213 */               GL11.glPushMatrix();
/* 214 */               GL11.glEnable(3042);
/* 215 */               GL11.glBlendFunc(770, 1);
/* 216 */               String t = StatCollector.func_74838_a("nodetype." + ((INode)tile).getNodeType() + ".name");
/* 217 */               if (((INode)tile).getNodeModifier() != null)
/* 218 */                 t = t + ", " + StatCollector.func_74838_a(new StringBuilder().append("nodemod.").append(((INode)tile).getNodeModifier()).append(".name").toString());
/* 219 */               int sw = mc.field_71466_p.func_78256_a(t);
/* 220 */               float scale = 0.004F;
/* 221 */               GL11.glScalef(scale, scale, scale);
/* 222 */               mc.field_71466_p.func_78276_b(t, -sw / 2, -40, 15642134);
/* 223 */               GL11.glDisable(3042);
/* 224 */               GL11.glPopMatrix();
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 230 */         if (stack != null) {
/* 231 */           if (stack.func_77973_b() != null) {
/*     */             try {
/* 233 */               text = stack.func_82833_r();
/*     */             }
/*     */             catch (Exception e) {}
/* 236 */           } else if (stack.func_77973_b() != null) {
/*     */             try {
/* 238 */               text = stack.func_77973_b().func_77653_i(stack);
/*     */             }
/*     */             catch (Exception e) {}
/*     */           }
/*     */         }
/* 243 */         if (aspects != null) {
/* 244 */           int posX = 0;
/* 245 */           int posY = 0;
/* 246 */           int aa = aspects.size();
/* 247 */           int baseX = Math.min(5, aa) * 8;
/* 248 */           for (thaumcraft.api.aspects.Aspect aspect : aspects.getAspectsSorted()) {
/* 249 */             GL11.glPushMatrix();
/* 250 */             GL11.glScalef(0.0075F, 0.0075F, 0.0075F);
/* 251 */             j = (int)(190.0F + MathHelper.func_76126_a(posX + playermp.field_70173_aa - playermp.field_70170_p.field_73012_v.nextInt(2)) * 10.0F + 10.0F);
/* 252 */             k = j % 65536;
/* 253 */             l = j / 65536;
/* 254 */             OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/* 255 */             UtilsFX.drawTag(-baseX + posX * 16, -8 + posY * 16, aspect, aspects.getAmount(aspect), 0, 0.01D, 1, 1.0F, false);
/* 256 */             GL11.glPopMatrix();
/* 257 */             posX++;
/* 258 */             if (posX >= 5 - posY) {
/* 259 */               posX = 0;
/* 260 */               posY++;
/* 261 */               aa -= 5 - posY;
/* 262 */               baseX = Math.min(5 - posY, aa) * 8;
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 267 */         if (text == null) text = "?";
/* 268 */         if (text.length() > 0) {
/* 269 */           RenderHelper.func_74518_a();
/* 270 */           GL11.glPushMatrix();
/*     */           
/* 272 */           GL11.glEnable(3042);
/* 273 */           GL11.glBlendFunc(770, 1);
/* 274 */           GL11.glTranslatef(0.0F, -0.25F, 0.0F);
/* 275 */           int sw = mc.field_71466_p.func_78256_a(text);
/* 276 */           float scale = 0.005F;
/* 277 */           if (sw > 90) scale -= 2.5E-5F * (sw - 90);
/* 278 */           GL11.glScalef(scale, scale, scale);
/* 279 */           mc.field_71466_p.func_78276_b(text, -sw / 2, 0, 16777215);
/* 280 */           GL11.glDisable(3042);
/* 281 */           GL11.glPopMatrix();
/*     */         }
/*     */       }
/* 284 */       RenderHelper.func_74520_c();
/*     */     }
/*     */     
/* 287 */     GL11.glPopMatrix();
/* 288 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */   private ScanResult doScan(ItemStack stack, EntityPlayer p)
/*     */   {
/* 294 */     if ((stack == null) || (p == null)) { return null;
/*     */     }
/*     */     
/* 297 */     Entity pointedEntity = EntityUtils.getPointedEntity(p.field_70170_p, p, 0.5D, 10.0D, 0.0F, true);
/* 298 */     if (pointedEntity != null) {
/* 299 */       ScanResult sr = new ScanResult((byte)2, 0, 0, pointedEntity, "");
/* 300 */       return sr;
/*     */     }
/*     */     
/*     */ 
/* 304 */     MovingObjectPosition mop = EntityUtils.getMovingObjectPositionFromPlayer(p.field_70170_p, p, true);
/* 305 */     if ((mop != null) && (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK)) {
/* 306 */       Block bi = p.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/*     */       
/* 308 */       TileEntity tile = p.field_70170_p.func_147438_o(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/* 309 */       if ((tile != null) && ((tile instanceof INode))) {
/* 310 */         int md = bi.func_149643_k(p.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/* 311 */         ScanResult sr = new ScanResult((byte)3, Block.func_149682_b(bi), md, null, "NODE" + ((INode)tile).getId());
/* 312 */         return sr;
/*     */       }
/* 314 */       if (bi != net.minecraft.init.Blocks.field_150350_a) {
/* 315 */         ItemStack is = bi.getPickBlock(mop, p.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/* 316 */         ScanResult sr = null;
/* 317 */         int md = p.field_70170_p.func_72805_g(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/*     */         try
/*     */         {
/* 320 */           if (is == null) {
/* 321 */             is = thaumcraft.common.lib.utils.BlockUtils.createStackedBlock(bi, md);
/*     */           }
/*     */         }
/*     */         catch (Exception e) {}
/*     */         try {
/* 326 */           if (is == null) {
/* 327 */             sr = new ScanResult((byte)1, Block.func_149682_b(bi), md, null, "");
/*     */           } else {
/* 329 */             sr = new ScanResult((byte)1, Item.func_150891_b(is.func_77973_b()), is.func_77960_j(), null, "");
/*     */           }
/*     */         }
/*     */         catch (Exception e) {}
/* 333 */         return sr;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 340 */     for (IScanEventHandler seh : thaumcraft.api.ThaumcraftApi.scanEventhandlers) {
/* 341 */       ScanResult scan = seh.scanPhenomena(stack, p.field_70170_p, p);
/* 342 */       if (scan != null) {
/* 343 */         return scan;
/*     */       }
/*     */     }
/* 346 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/item/ItemThaumometerRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */