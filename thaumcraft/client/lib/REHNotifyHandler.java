/*     */ package thaumcraft.client.lib;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.common.config.Config;
/*     */ 
/*     */ public class REHNotifyHandler
/*     */ {
/*     */   public void handleNotifications(Minecraft mc, long time, RenderGameOverlayEvent event)
/*     */   {
/*  21 */     if (PlayerNotifications.getListAndUpdate(time).size() > 0) {
/*  22 */       renderNotifyHUD(event.resolution.func_78327_c(), event.resolution.func_78324_d(), time);
/*     */     }
/*  24 */     if (PlayerNotifications.getAspectListAndUpdate(time).size() > 0) {
/*  25 */       renderAspectHUD(event.resolution.func_78327_c(), event.resolution.func_78324_d(), time);
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void renderNotifyHUD(double sw, double sh, long time)
/*     */   {
/*  32 */     Minecraft mc = Minecraft.func_71410_x();
/*  33 */     GL11.glPushMatrix();
/*     */     
/*  35 */     GL11.glClear(256);
/*  36 */     GL11.glMatrixMode(5889);
/*  37 */     GL11.glLoadIdentity();
/*  38 */     GL11.glOrtho(0.0D, sw, sh, 0.0D, 1000.0D, 3000.0D);
/*  39 */     GL11.glMatrixMode(5888);
/*  40 */     GL11.glLoadIdentity();
/*  41 */     GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
/*     */     
/*  43 */     GL11.glDisable(2929);
/*  44 */     GL11.glDepthMask(false);
/*     */     
/*  46 */     GL11.glDisable(3008);
/*  47 */     GL11.glEnable(2881);
/*  48 */     GL11.glHint(3155, 4354);
/*  49 */     int k = (int)sw;
/*  50 */     int l = (int)sh;
/*     */     
/*  52 */     ArrayList<PlayerNotifications.Notification> notifications = PlayerNotifications.getListAndUpdate(time);
/*  53 */     int entry = 0;
/*  54 */     float shift = -8.0F;
/*  55 */     for (; (entry < notifications.size()) && (entry < Config.notificationMax); entry++) {
/*  56 */       PlayerNotifications.Notification li = (PlayerNotifications.Notification)notifications.get(entry);
/*     */       
/*  58 */       String text = li.text;
/*  59 */       int size = mc.field_71466_p.func_78256_a(text) / 2;
/*  60 */       int alpha = 255;
/*  61 */       if ((entry == notifications.size() - 1) && (li.created > time)) {
/*  62 */         alpha = 255 - (int)((float)(li.created - time) / (Config.notificationDelay / 4) * 240.0F);
/*     */       }
/*  64 */       if (li.expire < time + Config.notificationDelay) {
/*  65 */         alpha = (int)(255.0F - (float)(time + Config.notificationDelay - li.expire) / Config.notificationDelay * 240.0F);
/*  66 */         shift = -8.0F * (alpha / 255.0F);
/*     */       }
/*     */       
/*     */ 
/*  70 */       int color = (alpha / 2 << 24) + 16711680 + 65280 + 255;
/*  71 */       GL11.glPushMatrix();
/*  72 */       GL11.glEnable(3042);
/*  73 */       GL11.glBlendFunc(770, 771);
/*  74 */       GL11.glTranslatef(k - size - 10, l - entry * 8 + shift, 0.0F);
/*  75 */       GL11.glScalef(0.5F, 0.5F, 0.5F);
/*  76 */       mc.field_71456_v.func_73731_b(mc.field_71466_p, text, -4, -8, color);
/*  77 */       GL11.glDisable(3042);
/*  78 */       GL11.glPopMatrix();
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  84 */       GL11.glEnable(3042);
/*  85 */       GL11.glBlendFunc(770, 771);
/*     */       
/*  87 */       if (li.image != null) {
/*  88 */         GL11.glPushMatrix();
/*  89 */         GL11.glTranslatef(k - 9, l - entry * 8 + shift - 6.0F, 0.0F);
/*  90 */         GL11.glScalef(0.03125F, 0.03125F, 0.03125F);
/*  91 */         mc.field_71446_o.func_110577_a(li.image);
/*  92 */         Tessellator tessellator = Tessellator.field_78398_a;
/*  93 */         Color c = new Color(li.color);
/*  94 */         GL11.glColor4f(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, alpha / 511.0F);
/*  95 */         UtilsFX.drawTexturedQuad(0, 0, 0, 0, 256, 256, -90.0D);
/*  96 */         GL11.glPopMatrix();
/*     */       }
/*     */       
/*  99 */       if ((entry == notifications.size() - 1) && (li.created > time)) {
/* 100 */         float scale = (float)(li.created - time) / (Config.notificationDelay / 4);
/* 101 */         alpha = 255 - (int)(scale * 240.0F);
/* 102 */         GL11.glPushMatrix();
/* 103 */         GL11.glTranslatef(k - 5 - 8.0F * scale - (1.0F - scale) * (1.0F - scale) * (1.0F - scale) * size * 3.0F, l - entry * 8 + shift - 2.0F - 8.0F * scale, 0.0F);
/* 104 */         GL11.glScalef(scale, scale, scale);
/* 105 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F - alpha / 511.0F);
/* 106 */         UtilsFX.bindTexture(thaumcraft.client.fx.ParticleEngine.particleTexture);
/* 107 */         int px = 16 * ((mc.field_71439_g.field_70173_aa + entry * 3) % 16);
/* 108 */         UtilsFX.drawTexturedQuad(0, 0, px, 80, 16, 16, -90 - notifications.size());
/* 109 */         GL11.glPopMatrix();
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 114 */     GL11.glDepthMask(true);
/* 115 */     GL11.glEnable(2929);
/* 116 */     GL11.glDisable(3042);
/* 117 */     GL11.glDisable(2881);
/* 118 */     GL11.glEnable(3008);
/* 119 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*     */ 
/* 122 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void renderAspectHUD(double sw, double sh, long time)
/*     */   {
/* 128 */     Minecraft mc = Minecraft.func_71410_x();
/* 129 */     GL11.glPushMatrix();
/*     */     
/* 131 */     GL11.glClear(256);
/* 132 */     GL11.glMatrixMode(5889);
/* 133 */     GL11.glLoadIdentity();
/* 134 */     GL11.glOrtho(0.0D, sw, sh, 0.0D, 1000.0D, 3000.0D);
/* 135 */     GL11.glMatrixMode(5888);
/* 136 */     GL11.glLoadIdentity();
/* 137 */     GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
/*     */     
/* 139 */     GL11.glDisable(2929);
/* 140 */     GL11.glDepthMask(false);
/*     */     
/* 142 */     GL11.glDisable(3008);
/* 143 */     GL11.glEnable(2881);
/* 144 */     GL11.glHint(3155, 4354);
/* 145 */     int k = (int)sw;
/* 146 */     int l = (int)sh;
/*     */     
/* 148 */     float mainAlpha = 0.0F;
/*     */     
/* 150 */     ArrayList<PlayerNotifications.AspectNotification> notifications = PlayerNotifications.getAspectListAndUpdate(time);
/* 151 */     int entry = 0;
/* 152 */     float shift = -8.0F;
/* 153 */     for (; entry < notifications.size(); entry++) {
/* 154 */       PlayerNotifications.AspectNotification li = (PlayerNotifications.AspectNotification)notifications.get(entry);
/*     */       
/* 156 */       if (li.created <= time)
/*     */       {
/* 158 */         GL11.glEnable(3042);
/* 159 */         GL11.glBlendFunc(770, 771);
/*     */         
/* 161 */         if (li.aspect.getImage() != null) {
/* 162 */           GL11.glPushMatrix();
/*     */           
/* 164 */           int startX = (int)(sw * li.startX);
/* 165 */           int startY = (int)(sh * li.startY);
/*     */           
/* 167 */           int endX = k;
/* 168 */           int endY = -8;
/*     */           
/* 170 */           int bezierX = (int)(k * (0.25F + li.startX));
/* 171 */           int bezierY = (int)(l * li.startY);
/* 172 */           double t = (time - li.created) / (li.expire - li.created);
/*     */           
/* 174 */           double x = (1.0D - t) * (1.0D - t) * startX + 2.0D * (1.0D - t) * t * bezierX + t * t * endX;
/* 175 */           double y = (1.0D - t) * (1.0D - t) * startY + 2.0D * (1.0D - t) * t * bezierY + t * t * endY;
/*     */           
/* 177 */           float alpha = 1.0F;
/*     */           
/* 179 */           if (t < 0.30000001192092896D) {
/* 180 */             alpha = (float)(t / 0.30000001192092896D);
/*     */           }
/* 182 */           else if (t > 0.6600000262260437D) {
/* 183 */             alpha = (float)(1.0D - (t - 0.6600000262260437D) / 0.3400000035762787D);
/*     */           }
/* 185 */           if (alpha > mainAlpha) { mainAlpha = alpha;
/*     */           }
/* 187 */           GL11.glTranslated(x, y, 0.0D);
/* 188 */           GL11.glScaled(0.075F * alpha, 0.075D * alpha, 0.075D * alpha);
/* 189 */           mc.field_71446_o.func_110577_a(li.aspect.getImage());
/* 190 */           Color c = new Color(li.aspect.getColor());
/* 191 */           GL11.glColor4f(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, alpha * 0.66F);
/* 192 */           UtilsFX.drawTexturedQuad(0, 0, 0, 0, 256, 256, -90.0D);
/* 193 */           GL11.glPopMatrix();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 198 */     if (mainAlpha > 0.0F) {
/*     */       try {
/* 200 */         GL11.glPushMatrix();
/* 201 */         UtilsFX.bindTexture("textures/items/thaumonomicon.png");
/* 202 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, mainAlpha);
/* 203 */         GL11.glTranslated(k - 16, 0.0D, 0.0D);
/* 204 */         GL11.glScaled(0.0625D, 0.0625D, 0.0625D);
/* 205 */         UtilsFX.drawTexturedQuad(0, 0, 0, 0, 256, 256, -90.0D);
/* 206 */         GL11.glPopMatrix();
/*     */       }
/*     */       catch (Exception e) {}
/*     */     }
/*     */     
/* 211 */     GL11.glDepthMask(true);
/* 212 */     GL11.glEnable(2929);
/* 213 */     GL11.glDisable(3042);
/* 214 */     GL11.glDisable(2881);
/* 215 */     GL11.glEnable(3008);
/* 216 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*     */ 
/* 219 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/lib/REHNotifyHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */