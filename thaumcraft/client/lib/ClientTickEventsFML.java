/*     */ package thaumcraft.client.lib;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
/*     */ import cpw.mods.fml.common.gameevent.TickEvent.Phase;
/*     */ import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
/*     */ import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
/*     */ import cpw.mods.fml.common.registry.FMLControlledNamespacedRegistry;
/*     */ import cpw.mods.fml.common.registry.GameData;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.awt.Color;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.GuiIngame;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.entity.RenderItem;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.client.shader.ShaderGroup;
/*     */ import net.minecraft.client.util.JsonException;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.input.Mouse;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.gui.GuiResearchPopup;
/*     */ import thaumcraft.client.gui.MappingThread;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.blocks.ItemJarFilled;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import thaumcraft.common.items.wands.WandManager;
/*     */ import thaumcraft.common.items.wands.foci.ItemFocusTrade;
/*     */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*     */ import thaumcraft.common.lib.events.EssentiaHandler;
/*     */ import thaumcraft.common.lib.events.EssentiaHandler.EssentiaSourceFX;
/*     */ import thaumcraft.common.lib.events.EventHandlerRunic;
/*     */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*     */ 
/*     */ public class ClientTickEventsFML
/*     */ {
/*  68 */   public static GuiResearchPopup researchPopup = null;
/*  69 */   public int tickCount = 0;
/*     */   int prevWorld;
/*  71 */   boolean checkedDate = false;
/*  72 */   final ResourceLocation HUD = new ResourceLocation("thaumcraft", "textures/gui/hud.png");
/*  73 */   RenderItem ri = new RenderItem();
/*  74 */   DecimalFormat myFormatter = new DecimalFormat("#######.##");
/*  75 */   DecimalFormat myFormatter2 = new DecimalFormat("#######.#");
/*  76 */   HashMap<Integer, AspectList> oldvals = new HashMap();
/*  77 */   long nextsync = 0L;
/*  78 */   boolean startThread = false;
/*  79 */   public static int warpVignette = 0;
/*     */   private static final int SHADER_DESAT = 0;
/*     */   private static final int SHADER_BLUR = 1;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public void playerTick(TickEvent.PlayerTickEvent event) {
/*  86 */     if (event.side == Side.SERVER) return;
/*  87 */     if (event.phase == TickEvent.Phase.START)
/*     */     {
/*  89 */       if ((!this.startThread) && (thaumcraft.client.gui.GuiResearchRecipe.cache.size() <= 0)) {
/*  90 */         Map<String, Integer> idMappings = Maps.newHashMap();
/*  91 */         GameData.getBlockRegistry().serializeInto(idMappings);
/*  92 */         GameData.getItemRegistry().serializeInto(idMappings);
/*  93 */         Thread t = new Thread(new MappingThread(idMappings));
/*  94 */         t.start();
/*  95 */         this.startThread = true;
/*     */       }
/*     */       
/*  98 */       Minecraft mc = Minecraft.func_71410_x();
/*  99 */       if ((event.player.func_71011_bu() != null) && ((event.player.func_71011_bu().func_77973_b() instanceof ItemWandCasting)))
/*     */       {
/* 101 */         event.player.func_71008_a(event.player.field_71071_by.func_70448_g(), event.player.func_71052_bv());
/*     */       }
/*     */       
/*     */       try
/*     */       {
/* 106 */         if (event.player.func_145782_y() == mc.field_71439_g.func_145782_y())
/*     */         {
/* 108 */           checkShaders(event, mc);
/*     */           
/* 110 */           if (warpVignette > 0) {
/* 111 */             warpVignette -= 1;
/* 112 */             RenderEventHandler.targetBrightness = 0.0F;
/*     */           } else {
/* 114 */             RenderEventHandler.targetBrightness = 1.0F;
/*     */           }
/*     */           
/* 117 */           if (RenderEventHandler.fogFiddled) {
/* 118 */             if (RenderEventHandler.fogDuration < 100) {
/* 119 */               RenderEventHandler.fogTarget = 0.1F * (RenderEventHandler.fogDuration / 100.0F);
/* 120 */             } else if (RenderEventHandler.fogTarget < 0.1F) {
/* 121 */               RenderEventHandler.fogTarget += 0.001F;
/*     */             }
/* 123 */             RenderEventHandler.fogDuration -= 1;
/* 124 */             if (RenderEventHandler.fogDuration < 0) { RenderEventHandler.fogFiddled = false;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (Exception e) {}
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private static final int SHADER_HUNGER = 2;
/*     */   private static final int SHADER_SUNSCORNED = 3;
/* 136 */   ResourceLocation[] shader_resources = { new ResourceLocation("shaders/post/desaturatetc.json"), new ResourceLocation("shaders/post/blurtc.json"), new ResourceLocation("shaders/post/hunger.json"), new ResourceLocation("shaders/post/sunscorned.json") };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void checkShaders(TickEvent.PlayerTickEvent event, Minecraft mc)
/*     */   {
/* 143 */     if (event.player.func_82165_m(Config.potionDeathGazeID)) {
/* 144 */       warpVignette = 10;
/* 145 */       if (!RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(0))) {
/*     */         try {
/* 147 */           setShader(new ShaderGroup(mc.func_110434_K(), mc.func_110442_L(), mc.func_147110_a(), this.shader_resources[0]), 0);
/*     */ 
/*     */         }
/*     */         catch (JsonException e) {}
/*     */       }
/*     */     }
/* 153 */     else if (RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(0))) {
/* 154 */       deactivateShader(0);
/*     */     }
/*     */     
/*     */ 
/* 158 */     if (event.player.func_82165_m(Config.potionBlurredID)) {
/* 159 */       if (!RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(1))) {
/*     */         try {
/* 161 */           setShader(new ShaderGroup(mc.func_110434_K(), mc.func_110442_L(), mc.func_147110_a(), this.shader_resources[1]), 1);
/*     */ 
/*     */         }
/*     */         catch (JsonException e) {}
/*     */       }
/*     */     }
/* 167 */     else if (RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(1))) {
/* 168 */       deactivateShader(1);
/*     */     }
/*     */     
/*     */ 
/* 172 */     if (event.player.func_82165_m(Config.potionUnHungerID)) {
/* 173 */       if (!RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(2))) {
/*     */         try {
/* 175 */           setShader(new ShaderGroup(mc.func_110434_K(), mc.func_110442_L(), mc.func_147110_a(), this.shader_resources[2]), 2);
/*     */ 
/*     */         }
/*     */         catch (JsonException e) {}
/*     */       }
/*     */     }
/* 181 */     else if (RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(2))) {
/* 182 */       deactivateShader(2);
/*     */     }
/*     */     
/*     */ 
/* 186 */     if (event.player.func_82165_m(Config.potionSunScornedID)) {
/* 187 */       if (!RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(3))) {
/*     */         try {
/* 189 */           setShader(new ShaderGroup(mc.func_110434_K(), mc.func_110442_L(), mc.func_147110_a(), this.shader_resources[3]), 3);
/*     */ 
/*     */         }
/*     */         catch (JsonException e) {}
/*     */       }
/*     */     }
/* 195 */     else if (RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(3))) {
/* 196 */       deactivateShader(3);
/*     */     }
/*     */   }
/*     */   
/*     */   void setShader(ShaderGroup target, int shaderId)
/*     */   {
/* 202 */     if (OpenGlHelper.field_148824_g)
/*     */     {
/* 204 */       Minecraft mc = Minecraft.func_71410_x();
/* 205 */       if (RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(shaderId)))
/*     */       {
/* 207 */         ((ShaderGroup)RenderEventHandler.shaderGroups.get(Integer.valueOf(shaderId))).func_148021_a();
/* 208 */         RenderEventHandler.shaderGroups.remove(Integer.valueOf(shaderId));
/*     */       }
/*     */       
/*     */       try
/*     */       {
/* 213 */         if (target == null) {
/* 214 */           deactivateShader(shaderId);
/*     */         } else {
/* 216 */           RenderEventHandler.resetShaders = true;
/* 217 */           RenderEventHandler.shaderGroups.put(Integer.valueOf(shaderId), target);
/*     */         }
/*     */       }
/*     */       catch (Exception ioexception)
/*     */       {
/* 222 */         RenderEventHandler.shaderGroups.remove(Integer.valueOf(shaderId));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void deactivateShader(int shaderId)
/*     */   {
/* 229 */     if (RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(shaderId)))
/*     */     {
/* 231 */       ((ShaderGroup)RenderEventHandler.shaderGroups.get(Integer.valueOf(shaderId))).func_148021_a();
/*     */     }
/*     */     
/* 234 */     RenderEventHandler.shaderGroups.remove(Integer.valueOf(shaderId));
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public void clientWorldTick(TickEvent.ClientTickEvent event)
/*     */   {
/* 242 */     if (event.side == Side.SERVER) return;
/* 243 */     Minecraft mc = FMLClientHandler.instance().getClient();
/* 244 */     World world = mc.field_71441_e;
/* 245 */     if (event.phase == TickEvent.Phase.START)
/*     */     {
/* 247 */       this.tickCount += 1;
/* 248 */       for (String fxk : (String[])EssentiaHandler.sourceFX.keySet().toArray(new String[0])) {
/* 249 */         EssentiaHandler.EssentiaSourceFX fx = (EssentiaHandler.EssentiaSourceFX)EssentiaHandler.sourceFX.get(fxk);
/* 250 */         if (fx.ticks <= 0) {
/* 251 */           EssentiaHandler.sourceFX.remove(fxk);
/* 252 */         } else if (world != null) {
/* 253 */           int mod = 0;
/* 254 */           net.minecraft.tileentity.TileEntity tile = world.func_147438_o(fx.start.field_71574_a, fx.start.field_71572_b, fx.start.field_71573_c);
/* 255 */           if ((tile != null) && ((tile instanceof thaumcraft.common.tiles.TileInfusionMatrix))) { mod = -1;
/*     */           }
/* 257 */           if (fx.ticks > 5) {
/* 258 */             Thaumcraft.proxy.essentiaTrailFx(world, fx.end.field_71574_a, fx.end.field_71572_b, fx.end.field_71573_c, fx.start.field_71574_a, fx.start.field_71572_b + mod, fx.start.field_71573_c, this.tickCount, fx.color, 1.0F);
/*     */           }
/*     */           else
/*     */           {
/* 262 */             float scale = fx.ticks * fx.ticks / 25.0F;
/* 263 */             Thaumcraft.proxy.essentiaTrailFx(world, fx.end.field_71574_a, fx.end.field_71572_b, fx.end.field_71573_c, fx.start.field_71574_a, fx.start.field_71572_b + mod, fx.start.field_71573_c, this.tickCount - (5 - fx.ticks), fx.color, scale);
/*     */           }
/*     */           
/*     */ 
/*     */ 
/* 268 */           fx.ticks -= 1;
/* 269 */           EssentiaHandler.sourceFX.put(fxk, fx);
/*     */         }
/*     */         
/*     */       }
/*     */       
/*     */     }
/* 275 */     else if ((mc.field_71441_e != null) && (!this.checkedDate)) {
/* 276 */       this.checkedDate = true;
/* 277 */       Calendar calendar = mc.field_71441_e.func_83015_S();
/* 278 */       if ((calendar.get(2) + 1 == 10) && (calendar.get(5) == 31)) {
/* 279 */         Thaumcraft.isHalloween = true;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public void renderTick(TickEvent.RenderTickEvent event)
/*     */   {
/* 290 */     Minecraft mc = FMLClientHandler.instance().getClient();
/* 291 */     World world = mc.field_71441_e;
/* 292 */     if (event.phase != TickEvent.Phase.START)
/*     */     {
/*     */ 
/* 295 */       if ((Minecraft.func_71410_x().field_71451_h instanceof EntityPlayer)) {
/* 296 */         EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
/* 297 */         long time = System.currentTimeMillis();
/* 298 */         if (researchPopup == null) researchPopup = new GuiResearchPopup(mc);
/* 299 */         researchPopup.updateResearchWindow();
/* 300 */         GuiScreen gui = mc.field_71462_r;
/*     */         
/* 302 */         if (((gui instanceof GuiContainer)) && (((GuiScreen.func_146272_n()) && (!Config.showTags)) || ((!GuiScreen.func_146272_n()) && (Config.showTags) && (!Mouse.isGrabbed()))))
/*     */         {
/*     */ 
/* 305 */           renderAspectsInGui((GuiContainer)gui, player);
/*     */         }
/*     */         
/* 308 */         if ((player != null) && (mc.field_71415_G) && (Minecraft.func_71382_s())) {
/* 309 */           if ((player.field_71071_by.func_70440_f(2) != null) && (player.field_71071_by.func_70440_f(2).func_77973_b() == thaumcraft.common.config.ConfigItems.itemHoverHarness))
/*     */           {
/* 311 */             renderHoverHUD(event.renderTickTime, player, time, player.field_71071_by.func_70440_f(2));
/*     */           }
/*     */           
/* 314 */           if ((!player.field_71075_bZ.field_75098_d) && (Thaumcraft.instance.runicEventHandler.runicCharge.containsKey(Integer.valueOf(player.func_145782_y()))) && (((Integer)Thaumcraft.instance.runicEventHandler.runicCharge.get(Integer.valueOf(player.func_145782_y()))).intValue() > 0) && (Thaumcraft.instance.runicEventHandler.runicInfo.containsKey(Integer.valueOf(player.func_145782_y()))))
/*     */           {
/*     */ 
/*     */ 
/* 318 */             renderRunicArmorBar(event.renderTickTime, player, time);
/*     */           }
/*     */           
/* 321 */           if (player.field_71071_by.func_70448_g() != null) {
/* 322 */             if ((player.field_71071_by.func_70448_g().func_77973_b() instanceof ItemWandCasting)) {
/* 323 */               renderCastingWandHud(Float.valueOf(event.renderTickTime), player, time, player.field_71071_by.func_70448_g());
/*     */             }
/* 325 */             else if ((player.field_71071_by.func_70448_g().func_77973_b() instanceof thaumcraft.common.items.relics.ItemSanityChecker)) {
/* 326 */               renderSanityHud(Float.valueOf(event.renderTickTime), player, time);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   private void renderSanityHud(Float partialTicks, EntityPlayer player, long time)
/*     */   {
/* 339 */     Minecraft mc = Minecraft.func_71410_x();
/*     */     
/* 341 */     GL11.glPushMatrix();
/*     */     
/* 343 */     ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x(), mc.field_71443_c, mc.field_71440_d);
/* 344 */     GL11.glClear(256);
/* 345 */     GL11.glMatrixMode(5889);
/* 346 */     GL11.glLoadIdentity();
/* 347 */     GL11.glOrtho(0.0D, sr.func_78327_c(), sr.func_78324_d(), 0.0D, 1000.0D, 3000.0D);
/* 348 */     GL11.glMatrixMode(5888);
/* 349 */     GL11.glLoadIdentity();
/* 350 */     int k = sr.func_78326_a();
/* 351 */     int l = sr.func_78328_b();
/*     */     
/* 353 */     GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
/* 354 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 356 */     GL11.glEnable(3042);
/* 357 */     GL11.glBlendFunc(770, 771);
/*     */     
/* 359 */     mc.field_71446_o.func_110577_a(this.HUD);
/*     */     
/* 361 */     GL11.glPushMatrix();
/* 362 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 363 */     UtilsFX.drawTexturedQuad(1, 1, 152, 0, 20, 76, -90.0D);
/* 364 */     GL11.glPopMatrix();
/*     */     
/* 366 */     float tw = Thaumcraft.proxy.getPlayerKnowledge().getWarpTotal(player.func_70005_c_());
/* 367 */     int p = Thaumcraft.proxy.getPlayerKnowledge().getWarpPerm(player.func_70005_c_());
/* 368 */     int s = Thaumcraft.proxy.getPlayerKnowledge().getWarpSticky(player.func_70005_c_());
/* 369 */     int t = Thaumcraft.proxy.getPlayerKnowledge().getWarpTemp(player.func_70005_c_());
/* 370 */     float mod = 1.0F;
/* 371 */     if (tw > 100.0F) {
/* 372 */       mod = 100.0F / tw;
/* 373 */       tw = 100.0F;
/*     */     }
/* 375 */     int gap = (int)((100.0F - tw) / 100.0F * 48.0F);
/* 376 */     int wt = (int)(t / 100.0F * 48.0F * mod);
/* 377 */     int ws = (int)(s / 100.0F * 48.0F * mod);
/*     */     
/* 379 */     if (t > 0) {
/* 380 */       GL11.glPushMatrix();
/* 381 */       GL11.glColor4f(1.0F, 0.5F, 1.0F, 1.0F);
/* 382 */       UtilsFX.drawTexturedQuad(7, 21 + gap, 200, gap, 8, wt + gap, -90.0D);
/* 383 */       GL11.glPopMatrix();
/*     */     }
/* 385 */     if (s > 0) {
/* 386 */       GL11.glPushMatrix();
/* 387 */       GL11.glColor4f(0.75F, 0.0F, 0.75F, 1.0F);
/* 388 */       UtilsFX.drawTexturedQuad(7, 21 + wt + gap, 200, wt + gap, 8, wt + ws + gap, -90.0D);
/* 389 */       GL11.glPopMatrix();
/*     */     }
/* 391 */     if (p > 0) {
/* 392 */       GL11.glPushMatrix();
/* 393 */       GL11.glColor4f(0.5F, 0.0F, 0.5F, 1.0F);
/* 394 */       UtilsFX.drawTexturedQuad(7, 21 + wt + ws + gap, 200, wt + ws + gap, 8, 48, -90.0D);
/* 395 */       GL11.glPopMatrix();
/*     */     }
/* 397 */     GL11.glPushMatrix();
/* 398 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 399 */     UtilsFX.drawTexturedQuad(1, 1, 176, 0, 20, 76, -90.0D);
/* 400 */     GL11.glPopMatrix();
/* 401 */     if (tw >= 100.0F) {
/* 402 */       GL11.glPushMatrix();
/* 403 */       UtilsFX.drawTexturedQuad(1, 1, 216, 0, 20, 16, -90.0D);
/* 404 */       GL11.glPopMatrix();
/*     */     }
/* 406 */     GL11.glDisable(3042);
/*     */     
/* 408 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   private void renderCastingWandHud(Float partialTicks, EntityPlayer player, long time, ItemStack wandstack)
/*     */   {
/* 418 */     Minecraft mc = Minecraft.func_71410_x();
/* 419 */     ItemWandCasting wand = (ItemWandCasting)wandstack.func_77973_b();
/*     */     
/* 421 */     if (this.oldvals.get(Integer.valueOf(player.field_71071_by.field_70461_c)) == null) {
/* 422 */       this.oldvals.put(Integer.valueOf(player.field_71071_by.field_70461_c), wand.getAllVis(wandstack));
/*     */     }
/* 424 */     else if (this.nextsync <= time) {
/* 425 */       this.oldvals.put(Integer.valueOf(player.field_71071_by.field_70461_c), wand.getAllVis(wandstack));
/* 426 */       this.nextsync = (time + 1000L);
/*     */     }
/*     */     
/* 429 */     GL11.glPushMatrix();
/*     */     
/* 431 */     ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x(), mc.field_71443_c, mc.field_71440_d);
/* 432 */     GL11.glClear(256);
/* 433 */     GL11.glMatrixMode(5889);
/* 434 */     GL11.glLoadIdentity();
/* 435 */     GL11.glOrtho(0.0D, sr.func_78327_c(), sr.func_78324_d(), 0.0D, 1000.0D, 3000.0D);
/* 436 */     GL11.glMatrixMode(5888);
/* 437 */     GL11.glLoadIdentity();
/* 438 */     int k = sr.func_78326_a();
/* 439 */     int l = sr.func_78328_b();
/*     */     
/* 441 */     int dailLocation = Config.dialBottom ? l - 32 : 0;
/*     */     
/* 443 */     GL11.glTranslatef(0.0F, dailLocation, -2000.0F);
/* 444 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 446 */     GL11.glEnable(3042);
/* 447 */     GL11.glBlendFunc(770, 771);
/*     */     
/* 449 */     mc.field_71446_o.func_110577_a(this.HUD);
/*     */     
/* 451 */     GL11.glPushMatrix();
/* 452 */     GL11.glScaled(0.5D, 0.5D, 0.5D);
/* 453 */     UtilsFX.drawTexturedQuad(0, 0, 0, 0, 64, 64, -90.0D);
/* 454 */     GL11.glPopMatrix();
/*     */     
/* 456 */     GL11.glTranslatef(16.0F, 16.0F, 0.0F);
/*     */     
/* 458 */     int max = wand.getMaxVis(wandstack);
/* 459 */     ItemFocusBasic focus = wand.getFocus(wandstack);
/* 460 */     ItemStack focusStack = wand.getFocusItem(wandstack);
/* 461 */     int count = 0;
/*     */     
/* 463 */     AspectList aspects = wand.getAllVis(wandstack);
/* 464 */     for (Aspect aspect : aspects.getAspects()) {
/* 465 */       int amt = aspects.getAmount(aspect);
/* 466 */       GL11.glPushMatrix();
/* 467 */       if (!Config.dialBottom) GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/* 468 */       GL11.glRotatef(-15 + count * 24, 0.0F, 0.0F, 1.0F);
/* 469 */       GL11.glTranslatef(0.0F, -32.0F, 0.0F);
/* 470 */       GL11.glScaled(0.5D, 0.5D, 0.5D);
/*     */       
/* 472 */       int loc = (int)(30.0F * amt / max);
/*     */       
/* 474 */       GL11.glPushMatrix();
/* 475 */       Color ac = new Color(aspect.getColor());
/* 476 */       GL11.glColor4f(ac.getRed() / 255.0F, ac.getGreen() / 255.0F, ac.getBlue() / 255.0F, 0.8F);
/* 477 */       UtilsFX.drawTexturedQuad(-4, 35 - loc, 104, 0, 8, loc, -90.0D);
/* 478 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 479 */       GL11.glPopMatrix();
/*     */       
/* 481 */       GL11.glPushMatrix();
/* 482 */       UtilsFX.drawTexturedQuad(-8, -3, 72, 0, 16, 42, -90.0D);
/* 483 */       GL11.glPopMatrix();
/* 484 */       int sh = 0;
/* 485 */       if ((focus != null) && (focus.getVisCost(focusStack).getAmount(aspect) > 0)) {
/* 486 */         GL11.glPushMatrix();
/* 487 */         UtilsFX.drawTexturedQuad(-4, -8, 136, 0, 8, 8, -90.0D);
/* 488 */         sh = 8;
/* 489 */         GL11.glPopMatrix();
/*     */       }
/*     */       
/* 492 */       if (((AspectList)this.oldvals.get(Integer.valueOf(player.field_71071_by.field_70461_c))).getAmount(aspect) > amt) {
/* 493 */         GL11.glPushMatrix();
/* 494 */         UtilsFX.drawTexturedQuad(-4, -8 - sh, 128, 0, 8, 8, -90.0D);
/* 495 */         GL11.glPopMatrix();
/*     */       }
/* 497 */       else if (((AspectList)this.oldvals.get(Integer.valueOf(player.field_71071_by.field_70461_c))).getAmount(aspect) < amt) {
/* 498 */         GL11.glPushMatrix();
/* 499 */         UtilsFX.drawTexturedQuad(-4, -8 - sh, 120, 0, 8, 8, -90.0D);
/* 500 */         GL11.glPopMatrix();
/*     */       }
/*     */       
/* 503 */       if (player.func_70093_af()) {
/* 504 */         GL11.glPushMatrix();
/* 505 */         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
/* 506 */         String msg = amt / 100 + "";
/* 507 */         mc.field_71456_v.func_73731_b(mc.field_71466_p, msg, -32, -4, 16777215);
/* 508 */         GL11.glPopMatrix();
/*     */         
/* 510 */         if ((focus != null) && (focus.getVisCost(focusStack).getAmount(aspect) > 0)) {
/* 511 */           float mod = wand.getConsumptionModifier(wandstack, player, aspect, false);
/* 512 */           GL11.glPushMatrix();
/* 513 */           GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
/* 514 */           msg = this.myFormatter.format(focus.getVisCost(focusStack).getAmount(aspect) * mod / 100.0F);
/* 515 */           mc.field_71456_v.func_73731_b(mc.field_71466_p, msg, 8, -4, 16777215);
/* 516 */           GL11.glPopMatrix();
/*     */         }
/*     */         
/* 519 */         mc.field_71446_o.func_110577_a(this.HUD);
/*     */       }
/*     */       
/* 522 */       GL11.glPopMatrix();
/* 523 */       count++;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 528 */     if (focus != null) {
/* 529 */       ItemStack picked = null;
/* 530 */       if ((focus instanceof ItemFocusTrade)) {
/* 531 */         ItemFocusTrade wt = (ItemFocusTrade)focus;
/* 532 */         picked = wt.getPickedBlock(player.field_71071_by.func_70448_g());
/* 533 */         if (picked != null) {
/* 534 */           renderWandTradeHud(partialTicks.floatValue(), player, time, picked);
/*     */         }
/*     */       }
/* 537 */       if (picked == null) {
/* 538 */         GL11.glPushMatrix();
/* 539 */         GL11.glTranslatef(-24.0F, -24.0F, 90.0F);
/* 540 */         GL11.glEnable(2896);
/* 541 */         this.ri.func_82406_b(mc.field_71466_p, mc.field_71446_o, wand.getFocusItem(wandstack), 16, 16);
/* 542 */         GL11.glDisable(2896);
/* 543 */         GL11.glPopMatrix();
/* 544 */         float f = WandManager.getCooldown(player);
/* 545 */         if (f > 0.0F) {
/* 546 */           GL11.glPushMatrix();
/* 547 */           GL11.glTranslatef(0.0F, 0.0F, 150.0F);
/* 548 */           GL11.glScaled(0.5D, 0.5D, 0.5D);
/* 549 */           String secs = this.myFormatter2.format(f) + "s";
/* 550 */           int w = mc.field_71466_p.func_78256_a(secs) / 2;
/* 551 */           mc.field_71456_v.func_73731_b(mc.field_71466_p, secs, -w, -4, 16777215);
/* 552 */           GL11.glPopMatrix();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 558 */     GL11.glDisable(3042);
/*     */     
/* 560 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void renderRunicArmorBar(float partialTicks, EntityPlayer player, long time)
/*     */   {
/* 569 */     Minecraft mc = Minecraft.func_71410_x();
/* 570 */     float total = ((Integer[])Thaumcraft.instance.runicEventHandler.runicInfo.get(Integer.valueOf(player.func_145782_y())))[0].intValue();
/* 571 */     float current = ((Integer)Thaumcraft.instance.runicEventHandler.runicCharge.get(Integer.valueOf(player.func_145782_y()))).intValue();
/*     */     
/* 573 */     GL11.glPushMatrix();
/*     */     
/* 575 */     ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x(), mc.field_71443_c, mc.field_71440_d);
/* 576 */     GL11.glClear(256);
/* 577 */     GL11.glMatrixMode(5889);
/* 578 */     GL11.glLoadIdentity();
/* 579 */     GL11.glOrtho(0.0D, sr.func_78327_c(), sr.func_78324_d(), 0.0D, 1000.0D, 3000.0D);
/* 580 */     GL11.glMatrixMode(5888);
/* 581 */     GL11.glLoadIdentity();
/* 582 */     GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
/*     */     
/* 584 */     GL11.glDisable(2929);
/* 585 */     GL11.glDepthMask(false);
/* 586 */     GL11.glEnable(3042);
/* 587 */     GL11.glBlendFunc(770, 771);
/* 588 */     GL11.glDisable(3008);
/*     */     
/* 590 */     int k = sr.func_78326_a();
/* 591 */     int l = sr.func_78328_b();
/*     */     
/* 593 */     GL11.glTranslatef(k / 2 - 91, l - 39, 0.0F);
/*     */     
/* 595 */     mc.field_71446_o.func_110577_a(ParticleEngine.particleTexture);
/*     */     
/* 597 */     float fill = current / total;
/*     */     
/* 599 */     for (int a = 0; a < fill * 10.0F; a++) {
/* 600 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 601 */       UtilsFX.drawTexturedQuad(a * 8, 0, 160, 16, 9, 9, -90.0D);
/* 602 */       GL11.glPushMatrix();
/* 603 */       GL11.glScaled(0.5D, 0.5D, 0.5D);
/* 604 */       GL11.glColor4f(1.0F, 0.75F, 0.24F, net.minecraft.util.MathHelper.func_76126_a(player.field_70173_aa / 4.0F + a) * 0.4F + 0.6F);
/* 605 */       UtilsFX.drawTexturedQuad(a * 16, 0, a * 16, 96, 16, 16, -90.0D);
/* 606 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 611 */     GL11.glDepthMask(true);
/* 612 */     GL11.glEnable(2929);
/* 613 */     GL11.glEnable(3008);
/* 614 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*     */ 
/* 617 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void renderHoverHUD(float partialTicks, EntityPlayer player, long time, ItemStack armor)
/*     */   {
/* 624 */     Minecraft mc = Minecraft.func_71410_x();
/* 625 */     GL11.glPushMatrix();
/*     */     
/* 627 */     ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x(), mc.field_71443_c, mc.field_71440_d);
/* 628 */     GL11.glClear(256);
/* 629 */     GL11.glMatrixMode(5889);
/* 630 */     GL11.glLoadIdentity();
/* 631 */     GL11.glOrtho(0.0D, sr.func_78327_c(), sr.func_78324_d(), 0.0D, 1000.0D, 3000.0D);
/* 632 */     GL11.glMatrixMode(5888);
/* 633 */     GL11.glLoadIdentity();
/* 634 */     GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
/*     */     
/*     */ 
/* 637 */     GL11.glDisable(2929);
/* 638 */     GL11.glDepthMask(false);
/* 639 */     GL11.glEnable(3042);
/* 640 */     GL11.glBlendFunc(770, 771);
/* 641 */     GL11.glDisable(3008);
/*     */     
/* 643 */     int k = sr.func_78326_a();
/* 644 */     int l = sr.func_78328_b();
/*     */     
/* 646 */     int fuel = 0;
/* 647 */     if ((armor.func_77942_o()) && (armor.field_77990_d.func_74764_b("jar"))) {
/* 648 */       ItemStack jar = ItemStack.func_77949_a(armor.field_77990_d.func_74775_l("jar"));
/* 649 */       if ((jar != null) && ((jar.func_77973_b() instanceof ItemJarFilled)) && (jar.func_77942_o())) {
/* 650 */         AspectList aspects = ((ItemJarFilled)jar.func_77973_b()).getAspects(jar);
/* 651 */         if ((aspects != null) && (aspects.size() > 0)) {
/* 652 */           fuel = (short)aspects.getAmount(Aspect.ENERGY);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 658 */     int level = Math.round(fuel / 64.0F * 48.0F);
/*     */     
/*     */ 
/* 661 */     mc.field_71446_o.func_110577_a(ParticleEngine.particleTexture);
/* 662 */     GL11.glColor4f(0.0F, 1.0F, 0.75F, 1.0F);
/* 663 */     UtilsFX.drawTexturedQuad(6, l / 2 + 24 - level, 224, 48 - level, 8, level, -91.0D);
/* 664 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 665 */     UtilsFX.drawTexturedQuad(5, l / 2 - 28, 240, 0, 10, 56, -90.0D);
/*     */     
/* 667 */     if ((armor.func_77942_o()) && (armor.field_77990_d.func_74764_b("hover")) && (armor.field_77990_d.func_74771_c("hover") == 1))
/*     */     {
/* 669 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.66F);
/* 670 */       UtilsFX.drawTexturedQuad(2, l / 2 - 43, 16 * ((int)(Minecraft.func_71386_F() % 700L) / 50), 32, 16, 16, -90.0D);
/*     */       
/* 672 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*     */     try
/*     */     {
/* 676 */       ResourceLocation resourcelocation = mc.field_71446_o.func_130087_a(armor.func_94608_d());
/* 677 */       mc.field_71446_o.func_110577_a(resourcelocation);
/* 678 */       Object object = armor.func_77954_c();
/* 679 */       if (object == null)
/*     */       {
/* 681 */         object = ((TextureMap)Minecraft.func_71410_x().func_110434_K().func_110581_b(resourcelocation)).func_110572_b("missingno");
/*     */       }
/*     */       
/* 684 */       int i1 = armor.func_77973_b().func_82790_a(armor, 0);
/* 685 */       float f2 = (i1 >> 16 & 0xFF) / 255.0F;
/* 686 */       float f = (i1 >> 8 & 0xFF) / 255.0F;
/* 687 */       float f1 = (i1 & 0xFF) / 255.0F;
/*     */       
/* 689 */       GL11.glColor4f(f2, f, f1, 1.0F);
/*     */       
/* 691 */       this.ri.func_94149_a(2, l / 2 - 43, (net.minecraft.util.IIcon)object, 16, 16);
/*     */     }
/*     */     catch (Exception e) {}
/*     */     
/*     */ 
/* 696 */     GL11.glDepthMask(true);
/* 697 */     GL11.glEnable(2929);
/* 698 */     GL11.glEnable(3008);
/* 699 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*     */ 
/* 702 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/* 706 */   ItemStack lastItem = null;
/* 707 */   int lastCount = 0;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void renderWandTradeHud(float partialTicks, EntityPlayer player, long time, ItemStack picked)
/*     */   {
/* 712 */     Minecraft mc = Minecraft.func_71410_x();
/* 713 */     int amount = this.lastCount;
/* 714 */     if ((player.field_71071_by.field_70459_e) || (!picked.func_77969_a(this.lastItem))) {
/* 715 */       amount = 0;
/* 716 */       for (ItemStack is : player.field_71071_by.field_70462_a) {
/* 717 */         if ((is != null) && (is.func_77969_a(picked))) {
/* 718 */           amount += is.field_77994_a;
/*     */         }
/*     */       }
/* 721 */       this.lastItem = picked;
/* 722 */       player.field_71071_by.field_70459_e = false;
/*     */     }
/* 724 */     this.lastCount = amount;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 737 */     GL11.glPushMatrix();
/* 738 */     net.minecraft.client.renderer.RenderHelper.func_74520_c();
/* 739 */     GL11.glDisable(2896);
/* 740 */     GL11.glEnable(32826);
/* 741 */     GL11.glEnable(2903);
/* 742 */     GL11.glEnable(2896);
/*     */     try {
/* 744 */       this.ri.func_82406_b(mc.field_71466_p, mc.field_71446_o, picked, -8, -8);
/*     */     } catch (Exception e) {}
/* 746 */     GL11.glDisable(2896);
/*     */     
/* 748 */     GL11.glPushMatrix();
/* 749 */     String am = "" + amount;
/* 750 */     int sw = mc.field_71466_p.func_78256_a(am);
/* 751 */     GL11.glTranslatef(0.0F, -mc.field_71466_p.field_78288_b, 500.0F);
/* 752 */     GL11.glScalef(0.5F, 0.5F, 0.5F);
/* 753 */     for (int a = -1; a <= 1; a++) for (int b = -1; b <= 1; b++)
/* 754 */         if (((a == 0) || (b == 0)) && ((a != 0) || (b != 0)))
/* 755 */           mc.field_71466_p.func_78276_b(am, a + 16 - sw, b + 24, 0);
/* 756 */     mc.field_71466_p.func_78276_b(am, 16 - sw, 24, 16777215);
/* 757 */     GL11.glPopMatrix();
/*     */     
/* 759 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public void renderAspectsInGui(GuiContainer gui, EntityPlayer player)
/*     */   {
/* 764 */     Minecraft mc = FMLClientHandler.instance().getClient();
/* 765 */     ScaledResolution var13 = new ScaledResolution(Minecraft.func_71410_x(), mc.field_71443_c, mc.field_71440_d);
/* 766 */     int var14 = var13.func_78326_a();
/* 767 */     int var15 = var13.func_78328_b();
/* 768 */     int var16 = Mouse.getX() * var14 / mc.field_71443_c;
/* 769 */     int var17 = var15 - Mouse.getY() * var15 / mc.field_71440_d - 1;
/*     */     
/*     */ 
/* 772 */     GL11.glPushMatrix();
/*     */     
/*     */ 
/* 775 */     GL11.glPushAttrib(1048575);
/*     */     
/*     */ 
/*     */ 
/* 779 */     GL11.glDisable(2896);
/* 780 */     for (int var20 = 0; var20 < gui.field_147002_h.field_75151_b.size(); var20++)
/*     */     {
/*     */ 
/* 783 */       int xs = UtilsFX.getGuiXSize(gui);
/* 784 */       int ys = UtilsFX.getGuiYSize(gui);
/* 785 */       int shift = 0;
/* 786 */       int shift2 = 0;
/* 787 */       int shiftx = -8;
/* 788 */       int shifty = -8;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 795 */       if (Thaumcraft.instance.aspectShift) {
/* 796 */         shiftx -= 8;
/* 797 */         shifty -= 8;
/*     */       }
/* 799 */       Slot var23 = (Slot)gui.field_147002_h.field_75151_b.get(var20);
/* 800 */       int guiLeft = shift + (gui.field_146294_l - xs - shift2) / 2;
/* 801 */       int guiTop = (gui.field_146295_m - ys) / 2;
/* 802 */       if (isMouseOverSlot(var23, var16, var17, guiLeft, guiTop))
/*     */       {
/* 804 */         if (var23.func_75211_c() != null)
/*     */         {
/* 806 */           int h = thaumcraft.common.lib.research.ScanManager.generateItemHash(var23.func_75211_c().func_77973_b(), var23.func_75211_c().func_77960_j());
/*     */           
/* 808 */           List<String> list = (List)Thaumcraft.proxy.getScannedObjects().get(player.func_70005_c_());
/* 809 */           if ((list != null) && ((list.contains("@" + h)) || (list.contains("#" + h))))
/*     */           {
/* 811 */             AspectList tags = ThaumcraftCraftingManager.getObjectTags(var23.func_75211_c());
/* 812 */             tags = ThaumcraftCraftingManager.getBonusTags(var23.func_75211_c(), tags);
/*     */             
/* 814 */             if (tags != null)
/*     */             {
/* 816 */               int x = var16 + 17;
/* 817 */               int y = var17 + 7 - 33;
/* 818 */               GL11.glDisable(2929);
/*     */               
/* 820 */               int index = 0;
/* 821 */               if (tags.size() > 0)
/* 822 */                 for (Aspect tag : tags.getAspectsSortedAmount())
/* 823 */                   if (tag != null) {
/* 824 */                     x = var16 + 17 + index * 18;
/* 825 */                     y = var17 + 7 - 33;
/*     */                     
/* 827 */                     UtilsFX.bindTexture("textures/aspects/_back.png");
/* 828 */                     GL11.glPushMatrix();
/* 829 */                     GL11.glEnable(3042);
/* 830 */                     GL11.glBlendFunc(770, 771);
/* 831 */                     GL11.glTranslated(x + shiftx - 2, y + shifty - 2, 0.0D);
/* 832 */                     GL11.glScaled(1.25D, 1.25D, 0.0D);
/* 833 */                     UtilsFX.drawTexturedQuadFull(0, 0, UtilsFX.getGuiZLevel(gui));
/* 834 */                     GL11.glDisable(3042);
/* 835 */                     GL11.glPopMatrix();
/*     */                     
/* 837 */                     if (Thaumcraft.proxy.playerKnowledge.hasDiscoveredAspect(player.func_70005_c_(), tag)) {
/* 838 */                       UtilsFX.drawTag(x + shiftx, y + shifty, tag, tags.getAmount(tag), 0, UtilsFX.getGuiZLevel(gui));
/*     */                     } else {
/* 840 */                       UtilsFX.bindTexture("textures/aspects/_unknown.png");
/* 841 */                       GL11.glPushMatrix();
/* 842 */                       GL11.glEnable(3042);
/* 843 */                       GL11.glBlendFunc(770, 771);
/* 844 */                       GL11.glTranslated(x + shiftx, y + shifty, 0.0D);
/* 845 */                       UtilsFX.drawTexturedQuadFull(0, 0, UtilsFX.getGuiZLevel(gui));
/* 846 */                       GL11.glDisable(3042);
/* 847 */                       GL11.glPopMatrix();
/*     */                     }
/*     */                     
/* 850 */                     index++;
/*     */                   }
/* 852 */               GL11.glEnable(2929);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 859 */     GL11.glPopAttrib();
/*     */     
/* 861 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isMouseOverSlot(Slot par1Slot, int par2, int par3, int par4, int par5)
/*     */   {
/* 869 */     int var4 = par4;
/* 870 */     int var5 = par5;
/* 871 */     par2 -= var4;
/* 872 */     par3 -= var5;
/* 873 */     return (par2 >= par1Slot.field_75223_e - 1) && (par2 < par1Slot.field_75223_e + 16 + 1) && (par3 >= par1Slot.field_75221_f - 1) && (par3 < par1Slot.field_75221_f + 16 + 1);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/lib/ClientTickEventsFML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */