/*     */ package thaumcraft.client.lib;
/*     */ 
/*     */ import baubles.api.BaublesApi;
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.TreeMap;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.RenderItem;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MouseHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.event.DrawBlockHighlightEvent;
/*     */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*     */ import org.lwjgl.input.Mouse;
/*     */ import org.lwjgl.opengl.Display;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.BlockCoordinates;
/*     */ import thaumcraft.api.IArchitect;
/*     */ import thaumcraft.api.IArchitect.EnumAxis;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.common.blocks.BlockCosmeticOpaque;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.items.wands.ItemFocusPouch;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import thaumcraft.common.lib.events.KeyHandler;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.misc.PacketFocusChangeToServer;
/*     */ 
/*     */ public class REHWandHandler
/*     */ {
/*  46 */   static float radialHudScale = 0.0F;
/*  47 */   TreeMap<String, Integer> foci = new TreeMap();
/*  48 */   HashMap<String, ItemStack> fociItem = new HashMap();
/*  49 */   HashMap<String, Boolean> fociHover = new HashMap();
/*  50 */   HashMap<String, Float> fociScale = new HashMap();
/*  51 */   long lastTime = 0L;
/*  52 */   boolean lastState = false;
/*  53 */   RenderBlocks renderBlocks = new RenderBlocks();
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void handleFociRadial(Minecraft mc, long time, RenderGameOverlayEvent event) {
/*  57 */     if ((KeyHandler.radialActive) || (radialHudScale > 0.0F)) {
/*  58 */       long timeDiff = System.currentTimeMillis() - KeyHandler.lastPressF;
/*  59 */       if (KeyHandler.radialActive)
/*     */       {
/*  61 */         if (mc.field_71462_r != null) {
/*  62 */           KeyHandler.radialActive = false;
/*  63 */           KeyHandler.radialLock = true;
/*  64 */           mc.func_71381_h();
/*  65 */           mc.func_71364_i();
/*  66 */           return;
/*     */         }
/*     */         
/*  69 */         if (radialHudScale == 0.0F) {
/*  70 */           this.foci.clear();
/*  71 */           this.fociItem.clear();
/*  72 */           this.fociHover.clear();
/*  73 */           this.fociScale.clear();
/*     */           
/*  75 */           int pouchcount = 0;
/*  76 */           ItemStack item = null;
/*     */           
/*  78 */           IInventory baubles = BaublesApi.getBaubles(mc.field_71439_g);
/*  79 */           for (int a = 0; a < 4; a++) {
/*  80 */             if ((baubles.func_70301_a(a) != null) && ((baubles.func_70301_a(a).func_77973_b() instanceof ItemFocusPouch))) {
/*  81 */               pouchcount++;
/*  82 */               item = baubles.func_70301_a(a);
/*  83 */               ItemStack[] inv = ((ItemFocusPouch)item.func_77973_b()).getInventory(item);
/*  84 */               for (int q = 0; q < inv.length; q++) {
/*  85 */                 item = inv[q];
/*  86 */                 if ((item != null) && ((item.func_77973_b() instanceof ItemFocusBasic))) {
/*  87 */                   this.foci.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), Integer.valueOf(q + pouchcount * 1000));
/*  88 */                   this.fociItem.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), item.func_77946_l());
/*  89 */                   this.fociScale.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), Float.valueOf(1.0F));
/*  90 */                   this.fociHover.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), Boolean.valueOf(false));
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */           
/*  96 */           for (int a = 0; a < 36; a++) {
/*  97 */             item = mc.field_71439_g.field_71071_by.field_70462_a[a];
/*  98 */             if ((item != null) && ((item.func_77973_b() instanceof ItemFocusBasic))) {
/*  99 */               this.foci.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), Integer.valueOf(a));
/* 100 */               this.fociItem.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), item.func_77946_l());
/* 101 */               this.fociScale.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), Float.valueOf(1.0F));
/* 102 */               this.fociHover.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), Boolean.valueOf(false));
/*     */             }
/* 104 */             if ((item != null) && ((item.func_77973_b() instanceof ItemFocusPouch))) {
/* 105 */               pouchcount++;
/* 106 */               ItemStack[] inv = ((ItemFocusPouch)item.func_77973_b()).getInventory(item);
/* 107 */               for (int q = 0; q < inv.length; q++) {
/* 108 */                 item = inv[q];
/* 109 */                 if ((item != null) && ((item.func_77973_b() instanceof ItemFocusBasic))) {
/* 110 */                   this.foci.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), Integer.valueOf(q + pouchcount * 1000));
/* 111 */                   this.fociItem.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), item.func_77946_l());
/* 112 */                   this.fociScale.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), Float.valueOf(1.0F));
/* 113 */                   this.fociHover.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), Boolean.valueOf(false));
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/* 118 */           if ((this.foci.size() > 0) && 
/* 119 */             (mc.field_71415_G))
/*     */           {
/* 121 */             mc.field_71415_G = false;
/* 122 */             mc.field_71417_B.func_74373_b();
/*     */           }
/*     */         }
/*     */       }
/* 126 */       else if (mc.field_71462_r == null)
/*     */       {
/* 128 */         if (this.lastState) {
/* 129 */           if (Display.isActive())
/*     */           {
/* 131 */             if (!mc.field_71415_G)
/*     */             {
/* 133 */               mc.field_71415_G = true;
/* 134 */               mc.field_71417_B.func_74372_a();
/*     */             }
/*     */           }
/* 137 */           this.lastState = false;
/*     */         }
/*     */       }
/* 140 */       renderFocusRadialHUD(event.resolution.func_78327_c(), event.resolution.func_78324_d(), time, event.partialTicks);
/* 141 */       if (time > this.lastTime) {
/* 142 */         for (String key : this.fociHover.keySet()) {
/* 143 */           if (((Boolean)this.fociHover.get(key)).booleanValue()) {
/* 144 */             if ((!KeyHandler.radialActive) && (!KeyHandler.radialLock)) {
/* 145 */               PacketHandler.INSTANCE.sendToServer(new PacketFocusChangeToServer(mc.field_71439_g, key));
/* 146 */               KeyHandler.radialLock = true;
/*     */             }
/* 148 */             if (((Float)this.fociScale.get(key)).floatValue() < 1.3F) {
/* 149 */               this.fociScale.put(key, Float.valueOf(((Float)this.fociScale.get(key)).floatValue() + 0.025F));
/*     */             }
/*     */           }
/* 152 */           else if (((Float)this.fociScale.get(key)).floatValue() > 1.0F) {
/* 153 */             this.fociScale.put(key, Float.valueOf(((Float)this.fociScale.get(key)).floatValue() - 0.025F));
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 158 */         if (!KeyHandler.radialActive) {
/* 159 */           radialHudScale -= 0.05F;
/*     */         }
/* 161 */         else if ((KeyHandler.radialActive) && (radialHudScale < 1.0F)) { radialHudScale += 0.05F;
/*     */         }
/* 163 */         if (radialHudScale > 1.0F) radialHudScale = 1.0F;
/* 164 */         if (radialHudScale < 0.0F) {
/* 165 */           radialHudScale = 0.0F;
/* 166 */           KeyHandler.radialLock = false;
/*     */         }
/* 168 */         this.lastTime = (time + 5L);
/* 169 */         this.lastState = KeyHandler.radialActive;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   private void renderFocusRadialHUD(double sw, double sh, long time, float partialTicks) {
/* 176 */     RenderItem ri = new RenderItem();
/* 177 */     Minecraft mc = Minecraft.func_71410_x();
/*     */     
/* 179 */     if ((mc.field_71439_g.func_71045_bC() == null) || (!(mc.field_71439_g.func_71045_bC().func_77973_b() instanceof ItemWandCasting)))
/*     */     {
/* 181 */       return;
/*     */     }
/* 183 */     ItemWandCasting wand = (ItemWandCasting)mc.field_71439_g.func_71045_bC().func_77973_b();
/* 184 */     ItemFocusBasic focus = wand.getFocus(mc.field_71439_g.func_71045_bC());
/*     */     
/* 186 */     int i = (int)(Mouse.getEventX() * sw / mc.field_71443_c);
/* 187 */     int j = (int)(sh - Mouse.getEventY() * sh / mc.field_71440_d - 1.0D);
/* 188 */     int k = Mouse.getEventButton();
/*     */     
/* 190 */     if (this.fociItem.size() == 0) { return;
/*     */     }
/* 192 */     GL11.glPushMatrix();
/* 193 */     GL11.glClear(256);
/* 194 */     GL11.glMatrixMode(5889);
/* 195 */     GL11.glLoadIdentity();
/* 196 */     GL11.glOrtho(0.0D, sw, sh, 0.0D, 1000.0D, 3000.0D);
/* 197 */     GL11.glMatrixMode(5888);
/* 198 */     GL11.glLoadIdentity();
/* 199 */     GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
/* 200 */     GL11.glDisable(2929);
/* 201 */     GL11.glDepthMask(false);
/*     */     
/* 203 */     GL11.glPushMatrix();
/*     */     
/* 205 */     GL11.glTranslated(sw / 2.0D, sh / 2.0D, 0.0D);
/*     */     
/* 207 */     ItemStack tt = null;
/*     */     
/* 209 */     float width = 16.0F + this.fociItem.size() * 2.5F;
/*     */     
/* 211 */     UtilsFX.bindTexture("textures/misc/radial.png");
/* 212 */     GL11.glPushMatrix();
/* 213 */     GL11.glRotatef(partialTicks + mc.field_71439_g.field_70173_aa % 720 / 2.0F, 0.0F, 0.0F, 1.0F);
/* 214 */     GL11.glAlphaFunc(516, 0.003921569F);
/* 215 */     GL11.glEnable(3042);
/* 216 */     GL11.glBlendFunc(770, 771);
/* 217 */     UtilsFX.renderQuadCenteredFromTexture(width * 2.75F * radialHudScale, 0.5F, 0.5F, 0.5F, 200, 771, 0.5F);
/* 218 */     GL11.glDisable(3042);
/* 219 */     GL11.glAlphaFunc(516, 0.1F);
/* 220 */     GL11.glPopMatrix();
/*     */     
/* 222 */     UtilsFX.bindTexture("textures/misc/radial2.png");
/* 223 */     GL11.glPushMatrix();
/* 224 */     GL11.glRotatef(-(partialTicks + mc.field_71439_g.field_70173_aa % 720 / 2.0F), 0.0F, 0.0F, 1.0F);
/* 225 */     GL11.glAlphaFunc(516, 0.003921569F);
/* 226 */     GL11.glEnable(3042);
/* 227 */     GL11.glBlendFunc(770, 771);
/* 228 */     UtilsFX.renderQuadCenteredFromTexture(width * 2.55F * radialHudScale, 0.5F, 0.5F, 0.5F, 200, 771, 0.5F);
/* 229 */     GL11.glDisable(3042);
/* 230 */     GL11.glAlphaFunc(516, 0.1F);
/* 231 */     GL11.glPopMatrix();
/*     */     
/* 233 */     if (focus != null) {
/* 234 */       GL11.glPushMatrix();
/* 235 */       GL11.glEnable(32826);
/* 236 */       RenderHelper.func_74520_c();
/* 237 */       ItemStack item = wand.getFocusItem(mc.field_71439_g.func_71045_bC()).func_77946_l();
/* 238 */       item.field_77990_d = null;
/* 239 */       ri.func_77015_a(mc.field_71466_p, mc.field_71446_o, item, -8, -8);
/* 240 */       RenderHelper.func_74518_a();
/* 241 */       GL11.glDisable(32826);
/* 242 */       GL11.glPopMatrix();
/*     */       
/* 244 */       int mx = (int)(i - sw / 2.0D);
/* 245 */       int my = (int)(j - sh / 2.0D);
/*     */       
/* 247 */       if ((mx >= -10) && (mx <= 10) && (my >= -10) && (my <= 10)) {
/* 248 */         tt = wand.getFocusItem(mc.field_71439_g.func_71045_bC());
/*     */       }
/*     */     }
/*     */     
/* 252 */     GL11.glScaled(radialHudScale, radialHudScale, radialHudScale);
/*     */     
/* 254 */     float currentRot = -90.0F * radialHudScale;
/* 255 */     float pieSlice = 360.0F / this.fociItem.size();
/* 256 */     String key = (String)this.foci.firstKey();
/*     */     
/* 258 */     for (int a = 0; a < this.fociItem.size(); a++)
/*     */     {
/* 260 */       double xx = MathHelper.func_76134_b(currentRot / 180.0F * 3.1415927F) * width;
/* 261 */       double yy = MathHelper.func_76126_a(currentRot / 180.0F * 3.1415927F) * width;
/* 262 */       currentRot += pieSlice;
/*     */       
/* 264 */       GL11.glPushMatrix();
/* 265 */       GL11.glTranslated(xx, yy, 100.0D);
/*     */       
/* 267 */       GL11.glScalef(((Float)this.fociScale.get(key)).floatValue(), ((Float)this.fociScale.get(key)).floatValue(), ((Float)this.fociScale.get(key)).floatValue());
/* 268 */       GL11.glEnable(32826);
/* 269 */       RenderHelper.func_74520_c();
/* 270 */       ItemStack item = ((ItemStack)this.fociItem.get(key)).func_77946_l();
/* 271 */       item.field_77990_d = null;
/* 272 */       ri.func_77015_a(mc.field_71466_p, mc.field_71446_o, item, -8, -8);
/* 273 */       RenderHelper.func_74518_a();
/* 274 */       GL11.glDisable(32826);
/* 275 */       GL11.glPopMatrix();
/*     */       
/* 277 */       if ((!KeyHandler.radialLock) && (KeyHandler.radialActive)) {
/* 278 */         int mx = (int)(i - sw / 2.0D - xx);
/* 279 */         int my = (int)(j - sh / 2.0D - yy);
/*     */         
/* 281 */         if ((mx >= -10) && (mx <= 10) && (my >= -10) && (my <= 10)) {
/* 282 */           this.fociHover.put(key, Boolean.valueOf(true));
/*     */           
/* 284 */           tt = (ItemStack)this.fociItem.get(key);
/*     */           
/* 286 */           if (k == 0) {
/* 287 */             KeyHandler.radialActive = false;
/* 288 */             KeyHandler.radialLock = true;
/* 289 */             PacketHandler.INSTANCE.sendToServer(new PacketFocusChangeToServer(mc.field_71439_g, key));
/* 290 */             break;
/*     */           }
/*     */         } else {
/* 293 */           this.fociHover.put(key, Boolean.valueOf(false));
/*     */         }
/*     */       }
/*     */       
/* 297 */       key = (String)this.foci.higherKey(key);
/*     */     }
/*     */     
/* 300 */     GL11.glPopMatrix();
/*     */     
/* 302 */     if (tt != null) {
/* 303 */       UtilsFX.drawCustomTooltip(mc.field_71462_r, ri, mc.field_71466_p, tt.func_82840_a(mc.field_71439_g, mc.field_71474_y.field_82882_x), -4, 20, 11);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 314 */     GL11.glDepthMask(true);
/* 315 */     GL11.glEnable(2929);
/* 316 */     GL11.glDisable(3042);
/* 317 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*     */ 
/* 320 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/* 324 */   int lastArcHash = 0;
/* 325 */   ArrayList<BlockCoordinates> architectBlocks = new ArrayList();
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean handleArchitectOverlay(ItemStack stack, DrawBlockHighlightEvent event, int playerticks, MovingObjectPosition target) {
/* 329 */     Minecraft mc = Minecraft.func_71410_x();
/* 330 */     IArchitect af = (IArchitect)stack.func_77973_b();
/* 331 */     String h = target.field_72311_b + "" + target.field_72312_c + "" + target.field_72309_d + "" + target.field_72310_e + "" + playerticks / 5;
/* 332 */     int hc = h.hashCode();
/* 333 */     if (hc != this.lastArcHash)
/*     */     {
/* 335 */       this.lastArcHash = hc;
/* 336 */       this.architectBlocks = af.getArchitectBlocks(stack, mc.field_71441_e, target.field_72311_b, target.field_72312_c, target.field_72309_d, target.field_72310_e, event.player);
/*     */     }
/*     */     
/*     */ 
/* 340 */     if ((this.architectBlocks == null) || (this.architectBlocks.size() == 0)) { return false;
/*     */     }
/* 342 */     drawArchitectAxis(target.field_72311_b, target.field_72312_c, target.field_72309_d, event.partialTicks, af.showAxis(stack, mc.field_71441_e, event.player, target.field_72310_e, IArchitect.EnumAxis.X), af.showAxis(stack, mc.field_71441_e, event.player, target.field_72310_e, IArchitect.EnumAxis.Y), af.showAxis(stack, mc.field_71441_e, event.player, target.field_72310_e, IArchitect.EnumAxis.Z));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 347 */     for (BlockCoordinates cc : this.architectBlocks) {
/* 348 */       drawOverlayBlock(cc.x, cc.y, cc.z, playerticks, mc, event.partialTicks);
/*     */     }
/*     */     
/* 351 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 352 */     return true;
/*     */   }
/*     */   
/*     */   private boolean isConnectedBlock(World world, int x, int y, int z) {
/* 356 */     if (this.architectBlocks.contains(new BlockCoordinates(x, y, z))) {
/* 357 */       return true;
/*     */     }
/* 359 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon getIconOnSide(World world, int x, int y, int z, int side, int ticks) {
/* 364 */     boolean[] bitMatrix = new boolean[8];
/* 365 */     if ((side == 0) || (side == 1)) {
/* 366 */       bitMatrix[0] = isConnectedBlock(world, x - 1, y, z - 1);
/* 367 */       bitMatrix[1] = isConnectedBlock(world, x, y, z - 1);
/* 368 */       bitMatrix[2] = isConnectedBlock(world, x + 1, y, z - 1);
/* 369 */       bitMatrix[3] = isConnectedBlock(world, x - 1, y, z);
/* 370 */       bitMatrix[4] = isConnectedBlock(world, x + 1, y, z);
/* 371 */       bitMatrix[5] = isConnectedBlock(world, x - 1, y, z + 1);
/* 372 */       bitMatrix[6] = isConnectedBlock(world, x, y, z + 1);
/* 373 */       bitMatrix[7] = isConnectedBlock(world, x + 1, y, z + 1);
/*     */     }
/* 375 */     if ((side == 2) || (side == 3)) {
/* 376 */       bitMatrix[0] = isConnectedBlock(world, x + (side == 2 ? 1 : -1), y + 1, z);
/* 377 */       bitMatrix[1] = isConnectedBlock(world, x, y + 1, z);
/* 378 */       bitMatrix[2] = isConnectedBlock(world, x + (side == 3 ? 1 : -1), y + 1, z);
/* 379 */       bitMatrix[3] = isConnectedBlock(world, x + (side == 2 ? 1 : -1), y, z);
/* 380 */       bitMatrix[4] = isConnectedBlock(world, x + (side == 3 ? 1 : -1), y, z);
/* 381 */       bitMatrix[5] = isConnectedBlock(world, x + (side == 2 ? 1 : -1), y - 1, z);
/* 382 */       bitMatrix[6] = isConnectedBlock(world, x, y - 1, z);
/* 383 */       bitMatrix[7] = isConnectedBlock(world, x + (side == 3 ? 1 : -1), y - 1, z);
/*     */     }
/* 385 */     if ((side == 4) || (side == 5)) {
/* 386 */       bitMatrix[0] = isConnectedBlock(world, x, y + 1, z + (side == 5 ? 1 : -1));
/* 387 */       bitMatrix[1] = isConnectedBlock(world, x, y + 1, z);
/* 388 */       bitMatrix[2] = isConnectedBlock(world, x, y + 1, z + (side == 4 ? 1 : -1));
/* 389 */       bitMatrix[3] = isConnectedBlock(world, x, y, z + (side == 5 ? 1 : -1));
/* 390 */       bitMatrix[4] = isConnectedBlock(world, x, y, z + (side == 4 ? 1 : -1));
/* 391 */       bitMatrix[5] = isConnectedBlock(world, x, y - 1, z + (side == 5 ? 1 : -1));
/* 392 */       bitMatrix[6] = isConnectedBlock(world, x, y - 1, z);
/* 393 */       bitMatrix[7] = isConnectedBlock(world, x, y - 1, z + (side == 4 ? 1 : -1));
/*     */     }
/* 395 */     int idBuilder = 0;
/* 396 */     for (int i = 0; i <= 7; i++) {
/* 397 */       idBuilder += (bitMatrix[i] != 0 ? 128 : i == 6 ? 64 : i == 5 ? 32 : i == 4 ? 16 : i == 3 ? 8 : i == 2 ? 4 : i == 1 ? 2 : i == 0 ? 1 : 0);
/*     */     }
/*     */     
/* 400 */     ((BlockCosmeticOpaque)ConfigBlocks.blockCosmeticOpaque);((BlockCosmeticOpaque)ConfigBlocks.blockCosmeticOpaque);return (idBuilder > 255) || (idBuilder < 0) ? BlockCosmeticOpaque.wardedGlassIcon[0] : BlockCosmeticOpaque.wardedGlassIcon[UtilsFX.connectedTextureRefByID[idBuilder]];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private boolean shouldSideBeRendered(int x, int y, int z, int side)
/*     */   {
/* 407 */     if (!this.architectBlocks.contains(new BlockCoordinates(x - net.minecraft.util.Facing.field_71586_b[side], y - net.minecraft.util.Facing.field_71587_c[side], z - net.minecraft.util.Facing.field_71585_d[side])))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 412 */       return true;
/*     */     }
/*     */     
/* 415 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void drawOverlayBlock(int x, int y, int z, int ticks, Minecraft mc, float partialTicks) {
/* 420 */     GL11.glPushMatrix();
/* 421 */     GL11.glDepthMask(false);
/* 422 */     GL11.glDisable(2929);
/* 423 */     GL11.glDisable(2884);
/*     */     
/* 425 */     GL11.glEnable(3042);
/* 426 */     GL11.glBlendFunc(770, 1);
/* 427 */     GL11.glAlphaFunc(516, 0.003921569F);
/*     */     
/* 429 */     EntityPlayer player = (EntityPlayer)mc.field_71451_h;
/* 430 */     double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * partialTicks;
/* 431 */     double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * partialTicks;
/* 432 */     double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * partialTicks;
/*     */     
/* 434 */     GL11.glTranslated(-iPX + x + 0.5D, -iPY + y, -iPZ + z + 0.5D);
/*     */     
/* 436 */     GL11.glDisable(2896);
/* 437 */     Tessellator t = Tessellator.field_78398_a;
/*     */     
/* 439 */     this.renderBlocks.func_147782_a(-0.0010000000474974513D, -0.0010000000474974513D, -0.0010000000474974513D, 1.0010000467300415D, 1.0010000467300415D, 1.0010000467300415D);
/*     */     
/* 441 */     float r = MathHelper.func_76126_a(ticks / 2.0F + x) * 0.2F + 0.3F;
/* 442 */     float g = MathHelper.func_76126_a(ticks / 3.0F + y) * 0.2F + 0.3F;
/* 443 */     float b = MathHelper.func_76126_a(ticks / 4.0F + z) * 0.2F + 0.8F;
/* 444 */     GL11.glColor4f(r, g, b, 0.2F);
/*     */     
/* 446 */     t.func_78382_b();
/*     */     
/* 448 */     t.func_78380_c(200);
/*     */     
/* 450 */     mc.field_71446_o.func_110577_a(TextureMap.field_110575_b);
/* 451 */     GL11.glTexEnvi(8960, 8704, 260);
/*     */     
/* 453 */     if (shouldSideBeRendered(x, y, z, 1))
/* 454 */       this.renderBlocks.func_147768_a(ConfigBlocks.blockJar, -0.5D, 0.0D, -0.5D, getIconOnSide(mc.field_71441_e, x, y, z, 0, ticks));
/* 455 */     if (shouldSideBeRendered(x, y, z, 0))
/* 456 */       this.renderBlocks.func_147806_b(ConfigBlocks.blockJar, -0.5D, 0.0D, -0.5D, getIconOnSide(mc.field_71441_e, x, y, z, 1, ticks));
/* 457 */     if (shouldSideBeRendered(x, y, z, 3))
/* 458 */       this.renderBlocks.func_147761_c(ConfigBlocks.blockJar, -0.5D, 0.0D, -0.5D, getIconOnSide(mc.field_71441_e, x, y, z, 2, ticks));
/* 459 */     if (shouldSideBeRendered(x, y, z, 2))
/* 460 */       this.renderBlocks.func_147734_d(ConfigBlocks.blockJar, -0.5D, 0.0D, -0.5D, getIconOnSide(mc.field_71441_e, x, y, z, 3, ticks));
/* 461 */     if (shouldSideBeRendered(x, y, z, 5))
/* 462 */       this.renderBlocks.func_147798_e(ConfigBlocks.blockJar, -0.5D, 0.0D, -0.5D, getIconOnSide(mc.field_71441_e, x, y, z, 4, ticks));
/* 463 */     if (shouldSideBeRendered(x, y, z, 4)) {
/* 464 */       this.renderBlocks.func_147764_f(ConfigBlocks.blockJar, -0.5D, 0.0D, -0.5D, getIconOnSide(mc.field_71441_e, x, y, z, 5, ticks));
/*     */     }
/*     */     
/* 467 */     t.func_78381_a();
/* 468 */     GL11.glTexEnvi(8960, 8704, 8448);
/* 469 */     GL11.glEnable(2896);
/* 470 */     GL11.glAlphaFunc(516, 0.1F);
/* 471 */     GL11.glDisable(3042);
/* 472 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 474 */     GL11.glEnable(2884);
/* 475 */     GL11.glEnable(2929);
/* 476 */     GL11.glDepthMask(true);
/* 477 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/* 480 */   String tex = "textures/misc/architect_arrows.png";
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/* 483 */   public void drawArchitectAxis(double x, double y, double z, float partialTicks, boolean dx, boolean dy, boolean dz) { if ((!dx) && (!dy) && (!dz)) return;
/* 484 */     EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
/* 485 */     double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * partialTicks;
/* 486 */     double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * partialTicks;
/* 487 */     double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * partialTicks;
/*     */     
/* 489 */     float r = MathHelper.func_76126_a((float)(player.field_70173_aa / 4.0F + x)) * 0.2F + 0.3F;
/* 490 */     float g = MathHelper.func_76126_a((float)(player.field_70173_aa / 3.0F + y)) * 0.2F + 0.3F;
/* 491 */     float b = MathHelper.func_76126_a((float)(player.field_70173_aa / 2.0F + z)) * 0.2F + 0.8F;
/* 492 */     GL11.glPushMatrix();
/* 493 */     GL11.glDepthMask(false);
/* 494 */     GL11.glDisable(2929);
/* 495 */     GL11.glDisable(2884);
/* 496 */     GL11.glEnable(3042);
/* 497 */     GL11.glBlendFunc(770, 1);
/*     */     
/* 499 */     GL11.glTranslated(-iPX + x + 0.5D, -iPY + y + 0.5D, -iPZ + z + 0.5D);
/*     */     
/* 501 */     GL11.glPushMatrix();
/* 502 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.33F);
/* 503 */     GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 504 */     if (dx) {
/* 505 */       GL11.glPushMatrix();
/* 506 */       UtilsFX.renderQuadCenteredFromTexture(this.tex, 1.0F, r, g, b, 200, 1, 1.0F);
/* 507 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 508 */       UtilsFX.renderQuadCenteredFromTexture(this.tex, 1.0F, r, g, b, 200, 1, 1.0F);
/* 509 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 512 */     if (dz) {
/* 513 */       GL11.glPushMatrix();
/* 514 */       GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/* 515 */       UtilsFX.renderQuadCenteredFromTexture(this.tex, 1.0F, r, g, b, 200, 1, 1.0F);
/* 516 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 517 */       UtilsFX.renderQuadCenteredFromTexture(this.tex, 1.0F, r, g, b, 200, 1, 1.0F);
/* 518 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 521 */     if (dy) {
/* 522 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 523 */       UtilsFX.renderQuadCenteredFromTexture(this.tex, 1.0F, r, g, b, 200, 1, 1.0F);
/* 524 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 525 */       UtilsFX.renderQuadCenteredFromTexture(this.tex, 1.0F, r, g, b, 200, 1, 1.0F);
/*     */     }
/*     */     
/* 528 */     GL11.glPopMatrix();
/* 529 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 531 */     GL11.glDisable(3042);
/* 532 */     GL11.glEnable(2884);
/* 533 */     GL11.glEnable(2929);
/* 534 */     GL11.glDepthMask(true);
/* 535 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/lib/REHWandHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */