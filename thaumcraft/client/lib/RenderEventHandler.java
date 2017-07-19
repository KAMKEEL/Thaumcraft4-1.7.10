/*     */ package thaumcraft.client.lib;
/*     */ 
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.shader.ShaderGroup;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityNote;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.client.event.DrawBlockHighlightEvent;
/*     */ import net.minecraftforge.client.event.EntityViewRenderEvent.RenderFogEvent;
/*     */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*     */ import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
/*     */ import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
/*     */ import net.minecraftforge.client.event.RenderPlayerEvent.Specials.Pre;
/*     */ import net.minecraftforge.client.event.RenderWorldLastEvent;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.IGoggles;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
/*     */ import thaumcraft.api.research.ScanResult;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ import thaumcraft.common.entities.golems.ItemGolemBell;
/*     */ import thaumcraft.common.entities.monster.mods.ChampionModifier;
/*     */ import thaumcraft.common.items.armor.ItemFortressArmor;
/*     */ import thaumcraft.common.lib.research.ScanManager;
/*     */ import thaumcraft.common.tiles.TileSensor;
/*     */ import thaumcraft.common.tiles.TileWandPedestal;
/*     */ import truetyper.FontLoader;
/*     */ import truetyper.TrueTypeFont;
/*     */ 
/*     */ public class RenderEventHandler
/*     */ {
/*  70 */   TrueTypeFont font = null;
/*  71 */   public static List blockTags = new ArrayList();
/*  72 */   int q = 0;
/*  73 */   public static float tagscale = 0.0F;
/*  74 */   public long scanCount = 0L;
/*  75 */   public int scanX = 0;
/*  76 */   public int scanY = 0;
/*  77 */   public int scanZ = 0;
/*  78 */   int[][][] scannedBlocks = new int[17][17][17];
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public REHWandHandler wandHandler;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public REHNotifyHandler notifyHandler;
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public void renderOverlay(RenderGameOverlayEvent event)
/*     */   {
/*  91 */     if (this.font == null) this.font = FontLoader.loadSystemFont("Arial", 12.0F, true);
/*  92 */     Minecraft mc = Minecraft.func_71410_x();
/*  93 */     long time = System.nanoTime() / 1000000L;
/*  94 */     if (this.wandHandler == null) this.wandHandler = new REHWandHandler();
/*  95 */     if (this.notifyHandler == null) this.notifyHandler = new REHNotifyHandler();
/*  96 */     if (event.type == RenderGameOverlayEvent.ElementType.TEXT)
/*     */     {
/*  98 */       this.notifyHandler.handleNotifications(mc, time, event);
/*     */       
/* 100 */       this.wandHandler.handleFociRadial(mc, time, event);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 105 */     if (event.type == RenderGameOverlayEvent.ElementType.PORTAL) {
/* 106 */       renderVignette(targetBrightness, event.resolution.func_78327_c(), event.resolution.func_78324_d());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public void renderShaders(RenderGameOverlayEvent.Pre event)
/*     */   {
/* 115 */     if ((Config.shaders) && (event.type == RenderGameOverlayEvent.ElementType.ALL)) {
/* 116 */       Minecraft mc = Minecraft.func_71410_x();
/* 117 */       long time = System.nanoTime() / 1000000L;
/* 118 */       if ((OpenGlHelper.field_148824_g) && (shaderGroups.size() > 0))
/*     */       {
/*     */ 
/* 121 */         updateShaderFrameBuffers(mc);
/* 122 */         GL11.glMatrixMode(5890);
/* 123 */         GL11.glLoadIdentity();
/* 124 */         for (ShaderGroup sg : shaderGroups.values()) {
/* 125 */           GL11.glPushMatrix();
/*     */           try {
/* 127 */             sg.func_148018_a(event.partialTicks);
/*     */           } catch (Exception e) {}
/* 129 */           GL11.glPopMatrix();
/*     */         }
/* 131 */         mc.func_147110_a().func_147610_a(true);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/* 136 */   public static boolean resetShaders = false;
/* 137 */   private static int oldDisplayWidth = 0;
/* 138 */   private static int oldDisplayHeight = 0;
/*     */   
/* 140 */   private void updateShaderFrameBuffers(Minecraft mc) { if ((resetShaders) || (mc.field_71443_c != oldDisplayWidth) || (oldDisplayHeight != mc.field_71440_d)) {
/* 141 */       for (ShaderGroup sg : shaderGroups.values()) {
/* 142 */         sg.func_148026_a(mc.field_71443_c, mc.field_71440_d);
/*     */       }
/* 144 */       oldDisplayWidth = mc.field_71443_c;
/* 145 */       oldDisplayHeight = mc.field_71440_d;
/* 146 */       resetShaders = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public void blockHighlight(DrawBlockHighlightEvent event)
/*     */   {
/* 156 */     int ticks = event.player.field_70173_aa;
/* 157 */     MovingObjectPosition target = event.target;
/*     */     
/* 159 */     if (blockTags.size() > 0) {
/* 160 */       int x = ((Integer)blockTags.get(0)).intValue();
/* 161 */       int y = ((Integer)blockTags.get(1)).intValue();
/* 162 */       int z = ((Integer)blockTags.get(2)).intValue();
/* 163 */       AspectList ot = (AspectList)blockTags.get(3);
/* 164 */       ForgeDirection dir = ForgeDirection.getOrientation(((Integer)blockTags.get(4)).intValue());
/* 165 */       if ((x == target.field_72311_b) && (y == target.field_72312_c) && (z == target.field_72309_d)) {
/* 166 */         if (tagscale < 0.5F) tagscale += 0.031F - tagscale / 10.0F;
/* 167 */         drawTagsOnContainer(target.field_72311_b + dir.offsetX / 2.0F, target.field_72312_c + dir.offsetY / 2.0F, target.field_72309_d + dir.offsetZ / 2.0F, ot, 220, dir, event.partialTicks);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 173 */     if ((event.player.field_71071_by.func_70440_f(3) != null) && ((event.player.field_71071_by.func_70440_f(3).func_77973_b() instanceof IGoggles)) && (((IGoggles)event.player.field_71071_by.func_70440_f(3).func_77973_b()).showIngamePopups(event.player.field_71071_by.func_70440_f(3), event.player)))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 179 */       boolean spaceAbove = event.player.field_70170_p.func_147437_c(target.field_72311_b, target.field_72312_c + 1, target.field_72309_d);
/* 180 */       TileEntity te = event.player.field_70170_p.func_147438_o(target.field_72311_b, target.field_72312_c, target.field_72309_d);
/* 181 */       if (te != null) {
/* 182 */         int note = -1;
/* 183 */         if ((te instanceof TileEntityNote)) {
/* 184 */           note = ((TileEntityNote)te).field_145879_a;
/*     */         }
/* 186 */         else if ((te instanceof TileSensor)) {
/* 187 */           note = ((TileSensor)te).note;
/*     */         }
/* 189 */         else if (((te instanceof IAspectContainer)) && (((IAspectContainer)te).getAspects() != null) && (((IAspectContainer)te).getAspects().size() > 0))
/*     */         {
/* 191 */           float shift = 0.0F;
/* 192 */           if ((te instanceof TileWandPedestal)) shift = 0.6F;
/* 193 */           if (tagscale < 0.3F) tagscale += 0.031F - tagscale / 10.0F;
/* 194 */           drawTagsOnContainer(target.field_72311_b, target.field_72312_c + (spaceAbove ? 0.4F : 0.0F) + shift, target.field_72309_d, ((IAspectContainer)te).getAspects(), 220, spaceAbove ? ForgeDirection.UP : ForgeDirection.getOrientation(event.target.field_72310_e), event.partialTicks);
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 200 */         if (note >= 0) {
/* 201 */           if (ticks % 5 == 0)
/* 202 */             thaumcraft.common.lib.network.PacketHandler.INSTANCE.sendToServer(new thaumcraft.common.lib.network.misc.PacketNote(target.field_72311_b, target.field_72312_c, target.field_72309_d, event.player.field_70170_p.field_73011_w.field_76574_g));
/* 203 */           drawTextInAir(target.field_72311_b, target.field_72312_c + 1, target.field_72309_d, event.partialTicks, "Note: " + note);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 209 */     if (this.wandHandler == null) { this.wandHandler = new REHWandHandler();
/*     */     }
/* 211 */     if ((target.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) && (event.player.func_70694_bm() != null) && ((event.player.func_70694_bm().func_77973_b() instanceof thaumcraft.api.IArchitect)) && (!(event.player.func_70694_bm().func_77973_b() instanceof ItemFocusBasic)))
/*     */     {
/*     */ 
/*     */ 
/* 215 */       if (this.wandHandler.handleArchitectOverlay(event.player.func_70694_bm(), event, ticks, target)) {
/* 216 */         event.setCanceled(true);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public void renderLast(RenderWorldLastEvent event)
/*     */   {
/* 227 */     if (tagscale > 0.0F) tagscale -= 0.005F;
/* 228 */     float partialTicks = event.partialTicks;
/* 229 */     Minecraft mc = Minecraft.func_71410_x();
/*     */     
/* 231 */     if ((Minecraft.func_71410_x().field_71451_h instanceof EntityPlayer)) {
/* 232 */       EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
/* 233 */       long time = System.currentTimeMillis();
/*     */       
/* 235 */       if ((player.field_71071_by.func_70448_g() != null) && (((player.field_71071_by.func_70448_g().func_77973_b() instanceof thaumcraft.common.entities.golems.ItemGolemPlacer)) || ((player.field_71071_by.func_70448_g().func_77973_b() instanceof ItemGolemBell))))
/*     */       {
/*     */ 
/* 238 */         renderMarkedBlocks(event, partialTicks, player, time);
/*     */       }
/*     */       
/* 241 */       if (this.scanCount > time) {
/* 242 */         showScannedBlocks(partialTicks, player, time);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 250 */   public static HashMap<Integer, ShaderGroup> shaderGroups = new HashMap();
/*     */   
/*     */ 
/*     */ 
/* 254 */   public static boolean fogFiddled = false;
/* 255 */   public static float fogTarget = 0.0F;
/* 256 */   public static int fogDuration = 0;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public void fogDensityEvent(EntityViewRenderEvent.RenderFogEvent event) {
/* 261 */     if ((fogFiddled) && (fogTarget > 0.0F)) {
/* 262 */       GL11.glFogi(2917, 2048);
/* 263 */       GL11.glFogf(2914, fogTarget);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public void renderPlayerSpecialsEvent(RenderPlayerEvent.Specials.Pre event)
/*     */   {
/* 272 */     if ((event.entityPlayer != null) && (event.entityPlayer.field_71071_by.field_70460_b[2] != null) && (((event.entityPlayer.field_71071_by.field_70460_b[2].func_77973_b() instanceof ItemFortressArmor)) || ((event.entityPlayer.field_71071_by.field_70460_b[2].func_77973_b() instanceof thaumcraft.common.items.armor.ItemVoidRobeArmor))))
/*     */     {
/*     */ 
/* 275 */       event.renderCape = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void drawTagsOnContainer(double x, double y, double z, AspectList tags, int bright, ForgeDirection dir, float partialTicks)
/*     */   {
/* 283 */     if (((Minecraft.func_71410_x().field_71451_h instanceof EntityPlayer)) && (tags != null) && (tags.size() > 0)) {
/* 284 */       EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
/* 285 */       double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * partialTicks;
/* 286 */       double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * partialTicks;
/* 287 */       double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * partialTicks;
/*     */       
/*     */ 
/* 290 */       int e = 0;
/*     */       
/* 292 */       int rowsize = 5;
/* 293 */       int current = 0;
/* 294 */       float shifty = 0.0F;
/* 295 */       int left = tags.size();
/* 296 */       for (Aspect tag : tags.getAspects()) {
/* 297 */         int div = Math.min(left, rowsize);
/*     */         
/* 299 */         if (current >= rowsize) {
/* 300 */           current = 0;
/* 301 */           shifty -= tagscale * 1.05F;
/* 302 */           left -= rowsize;
/* 303 */           if (left < rowsize) {
/* 304 */             div = left % rowsize;
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 309 */         float shift = (current - div / 2.0F + 0.5F) * tagscale * 4.0F;
/* 310 */         shift *= tagscale;
/*     */         
/* 312 */         Color color = new Color(tag.getColor());
/*     */         
/* 314 */         GL11.glPushMatrix();
/*     */         
/* 316 */         GL11.glDisable(2929);
/*     */         
/* 318 */         GL11.glTranslated(-iPX + x + 0.5D + tagscale * 2.0F * dir.offsetX, -iPY + y - shifty + 0.5D + tagscale * 2.0F * dir.offsetY, -iPZ + z + 0.5D + tagscale * 2.0F * dir.offsetZ);
/*     */         
/* 320 */         float xd = (float)(iPX - (x + 0.5D));
/* 321 */         float zd = (float)(iPZ - (z + 0.5D));
/* 322 */         float rotYaw = (float)(Math.atan2(xd, zd) * 180.0D / 3.141592653589793D);
/*     */         
/* 324 */         GL11.glRotatef(rotYaw + 180.0F, 0.0F, 1.0F, 0.0F);
/*     */         
/* 326 */         GL11.glTranslated(shift, 0.0D, 0.0D);
/* 327 */         GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 328 */         GL11.glScalef(tagscale, tagscale, tagscale);
/*     */         
/* 330 */         if (!Thaumcraft.proxy.playerKnowledge.hasDiscoveredAspect(player.func_70005_c_(), tag)) {
/* 331 */           UtilsFX.renderQuadCenteredFromTexture("textures/aspects/_unknown.png", 1.0F, color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, bright, 771, 0.75F);
/*     */           
/* 333 */           color = new Color(11184810);
/*     */         } else {
/* 335 */           UtilsFX.renderQuadCenteredFromTexture(tag.getImage(), 1.0F, color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, bright, 771, 0.75F);
/*     */         }
/*     */         
/*     */ 
/* 339 */         if (tags.getAmount(tag) >= 0) {
/* 340 */           String am = "" + tags.getAmount(tag);
/* 341 */           GL11.glScalef(0.04F, 0.04F, 0.04F);
/* 342 */           GL11.glTranslated(0.0D, 6.0D, -0.1D);
/* 343 */           int sw = Minecraft.func_71410_x().field_71466_p.func_78256_a(am);
/* 344 */           GL11.glEnable(3042);
/* 345 */           Minecraft.func_71410_x().field_71466_p.func_78276_b(am, 14 - sw, 1, 1118481);
/* 346 */           GL11.glTranslated(0.0D, 0.0D, -0.1D);
/* 347 */           Minecraft.func_71410_x().field_71466_p.func_78276_b(am, 13 - sw, 0, 16777215);
/*     */         }
/* 349 */         GL11.glEnable(2929);
/* 350 */         GL11.glPopMatrix();
/* 351 */         current++;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void drawTextInAir(double x, double y, double z, float partialTicks, String text) {
/* 357 */     if ((Minecraft.func_71410_x().field_71451_h instanceof EntityPlayer)) {
/* 358 */       EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
/* 359 */       double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * partialTicks;
/* 360 */       double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * partialTicks;
/* 361 */       double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * partialTicks;
/*     */       
/* 363 */       GL11.glPushMatrix();
/*     */       
/* 365 */       GL11.glTranslated(-iPX + x + 0.5D, -iPY + y + 0.5D, -iPZ + z + 0.5D);
/*     */       
/* 367 */       float xd = (float)(iPX - (x + 0.5D));
/* 368 */       float zd = (float)(iPZ - (z + 0.5D));
/* 369 */       float rotYaw = (float)(Math.atan2(xd, zd) * 180.0D / 3.141592653589793D);
/*     */       
/* 371 */       GL11.glRotatef(rotYaw + 180.0F, 0.0F, 1.0F, 0.0F);
/*     */       
/*     */ 
/* 374 */       GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 375 */       GL11.glScalef(0.02F, 0.02F, 0.02F);
/* 376 */       int sw = Minecraft.func_71410_x().field_71466_p.func_78256_a(text);
/* 377 */       GL11.glEnable(3042);
/* 378 */       Minecraft.func_71410_x().field_71466_p.func_78276_b(text, 1 - sw / 2, 1, 1118481);
/* 379 */       GL11.glTranslated(0.0D, 0.0D, -0.1D);
/* 380 */       Minecraft.func_71410_x().field_71466_p.func_78276_b(text, -sw / 2, 0, 16777215);
/*     */       
/*     */ 
/* 383 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void startScan(Entity player, int x, int y, int z, long time, int range)
/*     */   {
/* 390 */     this.scannedBlocks = new int[17][17][17];
/* 391 */     this.scanX = x;
/* 392 */     this.scanY = y;
/* 393 */     this.scanZ = z;
/* 394 */     this.scanCount = time;
/* 395 */     for (int xx = -range; xx <= range; xx++)
/* 396 */       for (int yy = -range; yy <= range; yy++)
/* 397 */         for (int zz = -range; zz <= range; zz++) {
/* 398 */           int value = -1;
/* 399 */           Block bi = player.field_70170_p.func_147439_a(x + xx, y + yy, z + zz);
/* 400 */           if ((bi != Blocks.field_150350_a) && (bi != Blocks.field_150357_h)) {
/* 401 */             if (bi.func_149688_o() == Material.field_151587_i) { value = -10;
/*     */             }
/* 403 */             else if (bi.func_149688_o() == Material.field_151586_h) { value = -5;
/*     */             } else {
/* 405 */               int md = bi.func_149643_k(player.field_70170_p, x + xx, y + yy, z + zz);
/* 406 */               int[] od = OreDictionary.getOreIDs(new ItemStack(bi, 1, md));
/* 407 */               boolean ore = false;
/* 408 */               if ((od != null) && (od.length > 0)) {
/* 409 */                 for (int id : od) {
/* 410 */                   if ((OreDictionary.getOreName(id) != null) && (OreDictionary.getOreName(id).toUpperCase().contains("ORE")))
/*     */                   {
/* 412 */                     ore = true;
/* 413 */                     value = 0;
/* 414 */                     break;
/*     */                   }
/*     */                 }
/*     */               }
/* 418 */               if (ore) {
/*     */                 try {
/* 420 */                   ScanResult scan = new ScanResult((byte)1, Block.func_149682_b(bi), md, null, "");
/* 421 */                   value = ScanManager.getScanAspects(scan, player.field_70170_p).visSize();
/*     */                 } catch (Exception e) {
/*     */                   try {
/* 424 */                     ScanResult scan = new ScanResult((byte)1, Item.func_150891_b(bi.func_149694_d(player.field_70170_p, x + xx, y + yy, z + zz)), bi.func_149643_k(player.field_70170_p, x + xx, y + yy, z + zz), null, "");
/*     */                     
/*     */ 
/* 427 */                     value = ScanManager.getScanAspects(scan, player.field_70170_p).visSize();
/*     */                   } catch (Exception e2) {}
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/* 433 */           this.scannedBlocks[(xx + 8)][(yy + 8)][(zz + 8)] = value;
/*     */         }
/*     */   }
/*     */   
/*     */   public void showScannedBlocks(float partialTicks, EntityPlayer player, long time) {
/* 438 */     Minecraft mc = Minecraft.func_71410_x();
/* 439 */     long dif = this.scanCount - time;
/* 440 */     GL11.glPushMatrix();
/* 441 */     GL11.glDepthMask(false);
/* 442 */     GL11.glDisable(2929);
/*     */     
/* 444 */     for (int xx = -8; xx <= 8; xx++) {
/* 445 */       for (int yy = -8; yy <= 8; yy++)
/* 446 */         for (int zz = -8; zz <= 8; zz++) {
/* 447 */           int value = this.scannedBlocks[(xx + 8)][(yy + 8)][(zz + 8)];
/* 448 */           float alpha = 1.0F;
/* 449 */           if (dif > 4750L) alpha = 1.0F - (float)(dif - 4750L) / 5.0F;
/* 450 */           if (dif < 1500L) { alpha = (float)dif / 1500.0F;
/*     */           }
/* 452 */           float dist = 1.0F - (xx * xx + yy * yy + zz * zz) / 64.0F;
/*     */           
/* 454 */           alpha *= dist;
/* 455 */           if (value == -5) {
/* 456 */             drawSpecialBlockoverlay(this.scanX + xx, this.scanY + yy, this.scanZ + zz, partialTicks, 3986684, alpha);
/*     */           }
/* 458 */           else if (value == -10) {
/* 459 */             drawSpecialBlockoverlay(this.scanX + xx, this.scanY + yy, this.scanZ + zz, partialTicks, 16734721, alpha);
/*     */           }
/* 461 */           else if (value >= 0) {
/* 462 */             GL11.glPushMatrix();
/* 463 */             GL11.glEnable(3042);
/* 464 */             GL11.glBlendFunc(770, 1);
/* 465 */             GL11.glAlphaFunc(516, 0.003921569F);
/*     */             
/* 467 */             GL11.glDisable(2884);
/* 468 */             UtilsFX.bindTexture(thaumcraft.client.renderers.tile.TileNodeRenderer.nodetex);
/*     */             
/* 470 */             drawPickScannedObject(this.scanX + xx, this.scanY + yy, this.scanZ + zz, partialTicks, alpha, (int)(time / 50L % 32L), value / 7.0F);
/*     */             
/* 472 */             GL11.glAlphaFunc(516, 0.1F);
/* 473 */             GL11.glDisable(3042);
/* 474 */             GL11.glEnable(2884);
/*     */             
/* 476 */             GL11.glPopMatrix();
/*     */           }
/*     */         }
/*     */     }
/* 480 */     GL11.glEnable(2929);
/* 481 */     GL11.glDepthMask(true);
/* 482 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void drawPickScannedObject(double x, double y, double z, float partialTicks, float alpha, int cframe, float size) {
/* 487 */     GL11.glPushMatrix();
/* 488 */     UtilsFX.renderFacingStrip(x + 0.5D, y + 0.5D, z + 0.5D, 0.0F, 0.2F * size, alpha, 32, 0, cframe, partialTicks, 11184657);
/* 489 */     GL11.glPopMatrix();
/* 490 */     GL11.glPushMatrix();
/* 491 */     UtilsFX.renderFacingStrip(x + 0.5D, y + 0.5D, z + 0.5D, 0.0F, 0.5F * size, alpha, 32, 0, cframe, partialTicks, 11145506);
/* 492 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public void drawSpecialBlockoverlay(double x, double y, double z, float partialTicks, int color, float alpha) {
/* 496 */     float r = 1.0F;
/* 497 */     float g = 1.0F;
/* 498 */     float b = 1.0F;
/* 499 */     EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
/* 500 */     double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * partialTicks;
/* 501 */     double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * partialTicks;
/* 502 */     double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * partialTicks;
/*     */     
/* 504 */     float time = (float)(System.nanoTime() / 30000000L);
/*     */     
/* 506 */     Color cc = new Color(color);
/* 507 */     r = cc.getRed() / 255.0F;
/* 508 */     g = cc.getGreen() / 255.0F;
/* 509 */     b = cc.getBlue() / 255.0F;
/*     */     
/* 511 */     for (int side = 0; side < 6; side++) {
/* 512 */       GL11.glPushMatrix();
/* 513 */       ForgeDirection dir = ForgeDirection.getOrientation(side);
/*     */       
/* 515 */       GL11.glTranslated(-iPX + x + 0.5D, -iPY + y + 0.5D, -iPZ + z + 0.5D);
/* 516 */       GL11.glRotatef(90.0F, -dir.offsetY, dir.offsetX, -dir.offsetZ);
/*     */       
/* 518 */       if (dir.offsetZ < 0) {
/* 519 */         GL11.glTranslated(0.0D, 0.0D, 0.5D);
/*     */       } else {
/* 521 */         GL11.glTranslated(0.0D, 0.0D, -0.5D);
/*     */       }
/* 523 */       GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
/* 524 */       UtilsFX.renderQuadCenteredFromTexture("textures/blocks/wardedglass.png", 1.0F, r, g, b, 200, 1, alpha);
/* 525 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void renderMarkedBlocks(RenderWorldLastEvent event, float partialTicks, EntityPlayer player, long time)
/*     */   {
/* 533 */     Minecraft mc = Minecraft.func_71410_x();
/*     */     
/* 535 */     if ((player.field_71071_by.func_70448_g().func_77942_o()) && (player.field_71071_by.func_70448_g().field_77990_d.func_74764_b("markers")))
/*     */     {
/* 537 */       Entity golem = null;
/* 538 */       ChunkCoordinates cc = null;
/* 539 */       int face = -1;
/* 540 */       if ((player.field_71071_by.func_70448_g().func_77973_b() instanceof ItemGolemBell)) {
/* 541 */         cc = ItemGolemBell.getGolemHomeCoords(player.field_71071_by.func_70448_g());
/* 542 */         face = ItemGolemBell.getGolemHomeFace(player.field_71071_by.func_70448_g());
/*     */         
/* 544 */         int gid = ItemGolemBell.getGolemId(player.field_71071_by.func_70448_g());
/* 545 */         if (gid > -1) {
/* 546 */           golem = player.field_70170_p.func_73045_a(gid);
/*     */         }
/* 548 */         if ((golem == null) || (!(golem instanceof EntityGolemBase))) { return;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 553 */       GL11.glPushMatrix();
/* 554 */       GL11.glAlphaFunc(516, 0.003921569F);
/*     */       
/* 556 */       if ((golem != null) && (cc != null) && (face > -1) && (player.func_70092_e(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c) < 4096.0D)) {
/* 557 */         GL11.glPushMatrix();
/* 558 */         drawGolemHomeOverlay(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c, face, partialTicks);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 563 */         GL11.glPopMatrix();
/*     */       }
/*     */       
/* 566 */       NBTTagList tl = player.field_71071_by.func_70448_g().field_77990_d.func_150295_c("markers", 10);
/* 567 */       for (int q = 0; q < tl.func_74745_c(); q++)
/*     */       {
/* 569 */         NBTTagCompound nbttagcompound1 = tl.func_150305_b(q);
/* 570 */         double x = nbttagcompound1.func_74762_e("x");
/* 571 */         double y = nbttagcompound1.func_74762_e("y");
/* 572 */         double z = nbttagcompound1.func_74762_e("z");
/* 573 */         int ox = nbttagcompound1.func_74762_e("x");
/* 574 */         int oy = nbttagcompound1.func_74762_e("y");
/* 575 */         int oz = nbttagcompound1.func_74762_e("z");
/* 576 */         int dim = nbttagcompound1.func_74762_e("dim");
/* 577 */         byte s = nbttagcompound1.func_74771_c("side");
/* 578 */         byte c = nbttagcompound1.func_74771_c("color");
/*     */         
/* 580 */         x += ForgeDirection.getOrientation(s).offsetX;
/* 581 */         y += ForgeDirection.getOrientation(s).offsetY;
/* 582 */         z += ForgeDirection.getOrientation(s).offsetZ;
/*     */         
/* 584 */         if (dim == player.field_70170_p.field_73011_w.field_76574_g)
/*     */         {
/* 586 */           if (player.func_70092_e(x, y, z) < 4096.0D) {
/* 587 */             GL11.glPushMatrix();
/* 588 */             drawMarkerOverlay(x, y, z, s, partialTicks, c);
/* 589 */             GL11.glPopMatrix();
/*     */             
/* 591 */             if (player.field_70170_p.func_147437_c(ox, oy, oz)) {
/* 592 */               GL11.glPushMatrix();
/* 593 */               for (int a = 0; a < 6; a++) {
/* 594 */                 drawAirBlockoverlay(ox + ForgeDirection.getOrientation(a).offsetX, oy + ForgeDirection.getOrientation(a).offsetY, oz + ForgeDirection.getOrientation(a).offsetZ, a, partialTicks, c);
/*     */               }
/*     */               
/*     */ 
/*     */ 
/*     */ 
/* 600 */               GL11.glPopMatrix();
/*     */             }
/*     */             
/* 603 */             if ((golem != null) && (Config.golemLinkQuality > 3)) {
/* 604 */               x -= ForgeDirection.getOrientation(s).offsetX * 0.5D;
/* 605 */               y -= ForgeDirection.getOrientation(s).offsetY * 0.5D;
/* 606 */               z -= ForgeDirection.getOrientation(s).offsetZ * 0.5D;
/* 607 */               GL11.glPushMatrix();
/* 608 */               drawMarkerLine(x, y, z, s, partialTicks, c, golem);
/* 609 */               GL11.glPopMatrix();
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 614 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 615 */       GL11.glAlphaFunc(516, 0.1F);
/* 616 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void drawAirBlockoverlay(double x, double y, double z, int side, float partialTicks, int color)
/*     */   {
/* 624 */     float r = 1.0F;
/* 625 */     float g = 1.0F;
/* 626 */     float b = 1.0F;
/* 627 */     EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
/* 628 */     double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * partialTicks;
/* 629 */     double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * partialTicks;
/* 630 */     double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * partialTicks;
/*     */     
/* 632 */     float time = (float)(System.nanoTime() / 30000000L);
/* 633 */     if (color == -1) {
/* 634 */       r = MathHelper.func_76126_a(time % 32767.0F / 12.0F + side) * 0.2F + 0.8F;
/* 635 */       g = MathHelper.func_76126_a(time % 32767.0F / 14.0F + side) * 0.2F + 0.8F;
/* 636 */       b = MathHelper.func_76126_a(time % 32767.0F / 16.0F + side) * 0.2F + 0.8F;
/*     */     } else {
/* 638 */       Color cc = new Color(UtilsFX.colors[color]);
/* 639 */       r = cc.getRed() / 255.0F;
/* 640 */       g = cc.getGreen() / 255.0F;
/* 641 */       b = cc.getBlue() / 255.0F;
/*     */     }
/* 643 */     GL11.glPushMatrix();
/* 644 */     GL11.glDepthMask(false);
/*     */     
/* 646 */     GL11.glDisable(2884);
/* 647 */     GL11.glEnable(3042);
/* 648 */     GL11.glBlendFunc(770, 1);
/* 649 */     ForgeDirection dir = ForgeDirection.getOrientation(side);
/*     */     
/* 651 */     GL11.glTranslated(-iPX + x + 0.5D - dir.offsetX * 0.01F, -iPY + y + 0.5D - dir.offsetY * 0.01F, -iPZ + z + 0.5D - dir.offsetZ * 0.01F);
/* 652 */     GL11.glRotatef(90.0F, -dir.offsetY, dir.offsetX, -dir.offsetZ);
/*     */     
/* 654 */     GL11.glPushMatrix();
/* 655 */     if (dir.offsetZ < 0) {
/* 656 */       GL11.glTranslated(0.0D, 0.0D, 0.5D);
/*     */     } else {
/* 658 */       GL11.glTranslated(0.0D, 0.0D, -0.5D);
/*     */     }
/* 660 */     GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
/* 661 */     GL11.glScalef(0.98F, 0.98F, 0.98F);
/* 662 */     UtilsFX.renderQuadCenteredFromTexture("textures/blocks/empty.png", 1.0F, r, g, b, 200, 1, 1.0F);
/* 663 */     GL11.glPopMatrix();
/*     */     
/*     */ 
/* 666 */     GL11.glDisable(3042);
/* 667 */     GL11.glEnable(2884);
/*     */     
/* 669 */     GL11.glDepthMask(true);
/* 670 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public void drawMarkerOverlay(double x, double y, double z, int side, float partialTicks, int color) {
/* 674 */     float r = 1.0F;
/* 675 */     float g = 1.0F;
/* 676 */     float b = 1.0F;
/* 677 */     EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
/* 678 */     double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * partialTicks;
/* 679 */     double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * partialTicks;
/* 680 */     double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * partialTicks;
/*     */     
/* 682 */     float time = (float)(System.nanoTime() / 30000000L);
/* 683 */     if (color == -1) {
/* 684 */       r = MathHelper.func_76126_a(time % 32767.0F / 12.0F + side) * 0.2F + 0.8F;
/* 685 */       g = MathHelper.func_76126_a(time % 32767.0F / 14.0F + side) * 0.2F + 0.8F;
/* 686 */       b = MathHelper.func_76126_a(time % 32767.0F / 16.0F + side) * 0.2F + 0.8F;
/*     */     } else {
/* 688 */       Color cc = new Color(UtilsFX.colors[color]);
/* 689 */       r = cc.getRed() / 255.0F;
/* 690 */       g = cc.getGreen() / 255.0F;
/* 691 */       b = cc.getBlue() / 255.0F;
/*     */     }
/* 693 */     GL11.glPushMatrix();
/* 694 */     GL11.glDepthMask(false);
/*     */     
/* 696 */     GL11.glDisable(2884);
/* 697 */     GL11.glEnable(3042);
/* 698 */     GL11.glBlendFunc(770, 1);
/* 699 */     ForgeDirection dir = ForgeDirection.getOrientation(side);
/*     */     
/* 701 */     GL11.glTranslated(-iPX + x + 0.5D + dir.offsetX * 0.01F, -iPY + y + 0.5D + dir.offsetY * 0.01F, -iPZ + z + 0.5D + dir.offsetZ * 0.01F);
/* 702 */     GL11.glRotatef(90.0F, -dir.offsetY, dir.offsetX, -dir.offsetZ);
/*     */     
/* 704 */     GL11.glPushMatrix();
/* 705 */     if (dir.offsetZ < 0) {
/* 706 */       GL11.glTranslated(0.0D, 0.0D, 0.5D);
/*     */     } else {
/* 708 */       GL11.glTranslated(0.0D, 0.0D, -0.5D);
/*     */     }
/* 710 */     GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
/* 711 */     GL11.glScalef(0.4F, 0.4F, 0.4F);
/* 712 */     UtilsFX.renderQuadCenteredFromTexture("textures/misc/mark.png", 1.0F, r, g, b, 200, 1, 1.0F);
/* 713 */     GL11.glPopMatrix();
/*     */     
/*     */ 
/* 716 */     GL11.glDisable(3042);
/* 717 */     GL11.glEnable(2884);
/*     */     
/* 719 */     GL11.glDepthMask(true);
/* 720 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public void drawGolemHomeOverlay(double x, double y, double z, int side, float partialTicks) {
/* 724 */     float r = 1.0F;
/* 725 */     float g = 1.0F;
/* 726 */     float b = 1.0F;
/* 727 */     EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
/* 728 */     double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * partialTicks;
/* 729 */     double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * partialTicks;
/* 730 */     double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * partialTicks;
/*     */     
/* 732 */     float time = (float)(System.nanoTime() / 30000000L);
/*     */     
/* 734 */     r = MathHelper.func_76126_a(time % 32767.0F / 12.0F + side) * 0.2F + 0.8F;
/* 735 */     g = MathHelper.func_76126_a(time % 32767.0F / 14.0F + side) * 0.2F + 0.8F;
/* 736 */     b = MathHelper.func_76126_a(time % 32767.0F / 16.0F + side) * 0.2F + 0.8F;
/* 737 */     GL11.glPushMatrix();
/* 738 */     GL11.glDepthMask(false);
/*     */     
/* 740 */     GL11.glDisable(2884);
/* 741 */     GL11.glEnable(3042);
/* 742 */     GL11.glBlendFunc(770, 1);
/* 743 */     ForgeDirection dir = ForgeDirection.getOrientation(side);
/*     */     
/* 745 */     GL11.glTranslated(-iPX + x + 0.5D + dir.offsetX * 0.01F, -iPY + y + 0.5D + dir.offsetY * 0.01F, -iPZ + z + 0.5D + dir.offsetZ * 0.01F);
/* 746 */     GL11.glRotatef(90.0F, -dir.offsetY, dir.offsetX, -dir.offsetZ);
/*     */     
/* 748 */     GL11.glPushMatrix();
/* 749 */     if (dir.offsetZ < 0) {
/* 750 */       GL11.glTranslated(0.0D, 0.0D, 0.5D);
/*     */     } else {
/* 752 */       GL11.glTranslated(0.0D, 0.0D, -0.5D);
/*     */     }
/* 754 */     GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
/* 755 */     GL11.glScalef(0.65F, 0.65F, 0.65F);
/* 756 */     UtilsFX.renderQuadCenteredFromTexture("textures/misc/home.png", 1.0F, r, g, b, 200, 1, 1.0F);
/* 757 */     GL11.glPopMatrix();
/*     */     
/*     */ 
/* 760 */     GL11.glDisable(3042);
/* 761 */     GL11.glEnable(2884);
/*     */     
/* 763 */     GL11.glDepthMask(true);
/* 764 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public void drawMarkerLine(double x, double y, double z, int side, float partialTicks, int color, Entity cc)
/*     */   {
/* 769 */     EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
/* 770 */     double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * partialTicks;
/* 771 */     double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * partialTicks;
/* 772 */     double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * partialTicks;
/*     */     
/* 774 */     double ePX = cc.field_70169_q + (cc.field_70165_t - cc.field_70169_q) * partialTicks;
/* 775 */     double ePY = cc.field_70167_r + (cc.field_70163_u - cc.field_70167_r) * partialTicks;
/* 776 */     double ePZ = cc.field_70166_s + (cc.field_70161_v - cc.field_70166_s) * partialTicks;
/*     */     
/* 778 */     GL11.glTranslated(-iPX + ePX, -iPY + ePY + cc.field_70131_O, -iPZ + ePZ);
/*     */     
/* 780 */     float r = 1.0F;
/* 781 */     float g = 1.0F;
/* 782 */     float b = 1.0F;
/* 783 */     float time = (float)(System.nanoTime() / 30000000L);
/* 784 */     if (color > -1) {
/* 785 */       Color co = new Color(UtilsFX.colors[color]);
/* 786 */       r = co.getRed() / 255.0F;
/* 787 */       g = co.getGreen() / 255.0F;
/* 788 */       b = co.getBlue() / 255.0F;
/*     */     }
/*     */     
/* 791 */     GL11.glDepthMask(false);
/* 792 */     GL11.glEnable(3042);
/* 793 */     GL11.glBlendFunc(770, 1);
/*     */     
/* 795 */     Tessellator tessellator = Tessellator.field_78398_a;
/*     */     
/* 797 */     double ds1x = ePX;
/* 798 */     double ds1y = ePY + cc.field_70131_O;
/* 799 */     double ds1z = ePZ;
/* 800 */     double dd1x = x + 0.5D + ForgeDirection.getOrientation(side).offsetX * 0.5D;
/* 801 */     double dd1y = y + 0.5D + ForgeDirection.getOrientation(side).offsetY * 0.5D;
/* 802 */     double dd1z = z + 0.5D + ForgeDirection.getOrientation(side).offsetZ * 0.5D;
/* 803 */     double dc1x = (float)(dd1x - ds1x);
/* 804 */     double dc1y = (float)(dd1y - ds1y);
/* 805 */     double dc1z = (float)(dd1z - ds1z);
/*     */     
/* 807 */     double ds2x = x + 0.5D;
/* 808 */     double ds2y = y + 0.5D;
/* 809 */     double ds2z = z + 0.5D;
/*     */     
/*     */ 
/* 812 */     double dc22x = (float)(ds2x - ds1x);
/* 813 */     double dc22y = (float)(ds2y - ds1y);
/* 814 */     double dc22z = (float)(ds2z - ds1z);
/*     */     
/* 816 */     UtilsFX.bindTexture("textures/misc/script.png");
/* 817 */     GL11.glDisable(2884);
/* 818 */     tessellator.func_78371_b(5);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 823 */     float f4 = 0.0F;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 829 */     double dx2 = 0.0D;
/* 830 */     double dy2 = 0.0D;
/* 831 */     double dz2 = 0.0D;
/* 832 */     double d3 = x - ePX;
/* 833 */     double d4 = y - ePY;
/* 834 */     double d5 = z - ePZ;
/*     */     
/* 836 */     float dist = MathHelper.func_76133_a(d3 * d3 + d4 * d4 + d5 * d5);
/* 837 */     float blocks = Math.round(dist);
/* 838 */     float length = blocks * Config.golemLinkQuality;
/*     */     
/* 840 */     float f9 = 0.0F;
/* 841 */     float f10 = 1.0F;
/*     */     
/* 843 */     int count = 0;
/* 844 */     for (int i = 0; i <= length; i++)
/*     */     {
/* 846 */       float f2 = i / length;
/* 847 */       float f2a = i * 1.5F / length;
/* 848 */       f2a = Math.min(0.75F, f2a);
/* 849 */       float f3 = 1.0F - Math.abs(i - length / 2.0F) / (length / 2.0F);
/* 850 */       f4 = 0.0F;
/* 851 */       if (color == -1) {
/* 852 */         r = MathHelper.func_76126_a(time % 32767.0F / 12.0F + side + i) * 0.2F + 0.8F;
/* 853 */         g = MathHelper.func_76126_a(time % 32767.0F / 14.0F + side + i) * 0.2F + 0.8F;
/* 854 */         b = MathHelper.func_76126_a(time % 32767.0F / 16.0F + side + i) * 0.2F + 0.8F;
/*     */       }
/*     */       
/* 857 */       double dx = dc1x + MathHelper.func_76126_a((float)((side * 20 + z % 16.0D + dist * (1.0F - f2) * Config.golemLinkQuality - time % 32767.0F / 5.0F) / 4.0D)) * 0.5F * f3;
/* 858 */       double dy = dc1y + MathHelper.func_76126_a((float)((side * 20 + x % 16.0D + dist * (1.0F - f2) * Config.golemLinkQuality - time % 32767.0F / 5.0F) / 3.0D)) * 0.5F * f3;
/* 859 */       double dz = dc1z + MathHelper.func_76126_a((float)((side * 20 + y % 16.0D + dist * (1.0F - f2) * Config.golemLinkQuality - time % 32767.0F / 5.0F) / 2.0D)) * 0.5F * f3;
/*     */       
/* 861 */       if (i > length - Config.golemLinkQuality / 2) {
/* 862 */         dx2 = dc22x + MathHelper.func_76126_a((float)((side * 20 + z % 16.0D + dist * (1.0F - f2) * Config.golemLinkQuality - time % 32767.0F / 5.0F) / 4.0D)) * 0.5F * f3;
/* 863 */         dy2 = dc22y + MathHelper.func_76126_a((float)((side * 20 + x % 16.0D + dist * (1.0F - f2) * Config.golemLinkQuality - time % 32767.0F / 5.0F) / 3.0D)) * 0.5F * f3;
/* 864 */         dz2 = dc22z + MathHelper.func_76126_a((float)((side * 20 + y % 16.0D + dist * (1.0F - f2) * Config.golemLinkQuality - time % 32767.0F / 5.0F) / 2.0D)) * 0.5F * f3;
/*     */         
/* 866 */         f3 = (length - i) / (Config.golemLinkQuality / 2.0F);
/* 867 */         f4 = 1.0F - f3;
/* 868 */         dx = dx * f3 + dx2 * f4;
/* 869 */         dy = dy * f3 + dy2 * f4;
/* 870 */         dz = dz * f3 + dz2 * f4;
/*     */       }
/*     */       
/* 873 */       tessellator.func_78369_a(r, g, b, f2a * (1.0F - f4));
/*     */       
/*     */ 
/* 876 */       float f13 = (1.0F - f2) * dist - time * 0.005F;
/*     */       
/* 878 */       tessellator.func_78374_a(dx * f2, dy * f2 - 0.05D, dz * f2, f13, f10);
/* 879 */       tessellator.func_78374_a(dx * f2, dy * f2 + 0.05D, dz * f2, f13, f9);
/*     */     }
/*     */     
/*     */ 
/* 883 */     tessellator.func_78381_a();
/*     */     
/* 885 */     GL11.glEnable(2884);
/* 886 */     GL11.glDisable(3042);
/* 887 */     GL11.glDepthMask(true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 892 */   public static float prevVignetteBrightness = 0.0F;
/* 893 */   public static float targetBrightness = 1.0F;
/* 894 */   protected static final ResourceLocation vignetteTexPath = new ResourceLocation("thaumcraft", "textures/misc/vignette.png");
/*     */   
/*     */   protected void renderVignette(float brightness, double sw, double sh)
/*     */   {
/* 898 */     int k = (int)sw;
/* 899 */     int l = (int)sh;
/*     */     
/* 901 */     brightness = 1.0F - brightness;
/* 902 */     prevVignetteBrightness = (float)(prevVignetteBrightness + (brightness - prevVignetteBrightness) * 0.01D);
/*     */     
/* 904 */     if (prevVignetteBrightness > 0.0F) {
/* 905 */       float b = prevVignetteBrightness * (1.0F + MathHelper.func_76126_a(Minecraft.func_71410_x().field_71439_g.field_70173_aa / 2.0F) * 0.1F);
/*     */       
/* 907 */       GL11.glPushMatrix();
/* 908 */       GL11.glClear(256);
/* 909 */       GL11.glMatrixMode(5889);
/* 910 */       GL11.glLoadIdentity();
/* 911 */       GL11.glOrtho(0.0D, sw, sh, 0.0D, 1000.0D, 3000.0D);
/*     */       
/* 913 */       Minecraft.func_71410_x().func_110434_K().func_110577_a(vignetteTexPath);
/*     */       
/* 915 */       GL11.glMatrixMode(5888);
/* 916 */       GL11.glLoadIdentity();
/* 917 */       GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
/* 918 */       GL11.glDisable(2929);
/* 919 */       GL11.glDepthMask(false);
/* 920 */       OpenGlHelper.func_148821_a(0, 769, 1, 0);
/* 921 */       GL11.glColor4f(b, b, b, 1.0F);
/*     */       
/*     */ 
/* 924 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 925 */       tessellator.func_78382_b();
/* 926 */       tessellator.func_78374_a(0.0D, l, -90.0D, 0.0D, 1.0D);
/* 927 */       tessellator.func_78374_a(k, l, -90.0D, 1.0D, 1.0D);
/* 928 */       tessellator.func_78374_a(k, 0.0D, -90.0D, 1.0D, 0.0D);
/* 929 */       tessellator.func_78374_a(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
/* 930 */       tessellator.func_78381_a();
/* 931 */       GL11.glDepthMask(true);
/* 932 */       GL11.glEnable(2929);
/* 933 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 934 */       OpenGlHelper.func_148821_a(770, 771, 1, 0);
/*     */       
/*     */ 
/*     */ 
/* 938 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   @SubscribeEvent
/*     */   public void livingTick(LivingEvent.LivingUpdateEvent event)
/*     */   {
/* 946 */     if ((event.entity.field_70170_p.field_72995_K) && ((event.entity instanceof EntityMob)) && (!event.entity.field_70128_L))
/*     */     {
/* 948 */       EntityMob mob = (EntityMob)event.entity;
/* 949 */       int t = (int)mob.func_110148_a(thaumcraft.common.lib.utils.EntityUtils.CHAMPION_MOD).func_111126_e();
/* 950 */       if (t >= 0) {
/* 951 */         ChampionModifier.mods[t].effect.showFX(mob);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/lib/RenderEventHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */