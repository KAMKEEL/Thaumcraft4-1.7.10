/*    */ package thaumcraft.client.lib;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class PlayerNotifications {
/*    */   public static class Notification {
/*    */     public String text;
/*    */     public net.minecraft.util.ResourceLocation image;
/*    */     public long expire;
/*    */     public long created;
/*    */     public int color;
/*    */     
/* 13 */     public Notification(String text, net.minecraft.util.ResourceLocation image, long expire, long created, int color) { this.text = text;
/* 14 */       this.image = image;
/* 15 */       this.expire = expire;
/* 16 */       this.created = created;
/* 17 */       this.color = color;
/*    */     }
/*    */   }
/*    */   
/*    */   public static class AspectNotification {
/*    */     public thaumcraft.api.aspects.Aspect aspect;
/*    */     public float startX;
/*    */     public float startY;
/*    */     public long expire;
/*    */     public long created;
/*    */     
/* 28 */     public AspectNotification(thaumcraft.api.aspects.Aspect aspect, float startX, float startY, long created, long expire) { this.aspect = aspect;
/* 29 */       this.startX = startX;
/* 30 */       this.startY = startY;
/* 31 */       this.expire = expire;
/* 32 */       this.created = created;
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 41 */   public static ArrayList<Notification> notificationList = new ArrayList();
/* 42 */   public static ArrayList<AspectNotification> aspectList = new ArrayList();
/*    */   
/*    */   public static void addNotification(String text) {
/* 45 */     addNotification(text, null, 16777215);
/*    */   }
/*    */   
/*    */   public static void addAspectNotification(thaumcraft.api.aspects.Aspect aspect) {
/* 49 */     long time = System.nanoTime() / 1000000L + net.minecraft.client.Minecraft.func_71410_x().field_71441_e.field_73012_v.nextInt(1000);
/* 50 */     float x = 0.4F + net.minecraft.client.Minecraft.func_71410_x().field_71441_e.field_73012_v.nextFloat() * 0.2F;
/* 51 */     float y = 0.4F + net.minecraft.client.Minecraft.func_71410_x().field_71441_e.field_73012_v.nextFloat() * 0.2F;
/* 52 */     aspectList.add(new AspectNotification(aspect, x, y, time, time + 1500L));
/*    */   }
/*    */   
/*    */   public static void addNotification(String text, thaumcraft.api.aspects.Aspect aspect)
/*    */   {
/* 57 */     addNotification(text, aspect.getImage(), aspect.getColor());
/*    */   }
/*    */   
/*    */   public static void addNotification(String text, net.minecraft.util.ResourceLocation image) {
/* 61 */     addNotification(text, image, 16777215);
/*    */   }
/*    */   
/*    */   public static void addNotification(String text, net.minecraft.util.ResourceLocation image, int color) {
/* 65 */     long time = System.nanoTime() / 1000000L;
/* 66 */     long timeBonus = notificationList.size() == 0 ? thaumcraft.common.config.Config.notificationDelay / 2 : 0L;
/* 67 */     notificationList.add(new Notification(text, image, time + thaumcraft.common.config.Config.notificationDelay + timeBonus, time + thaumcraft.common.config.Config.notificationDelay / 4, color));
/*    */   }
/*    */   
/*    */   public static ArrayList<Notification> getListAndUpdate(long time) {
/* 71 */     ArrayList<Notification> temp = new ArrayList();
/* 72 */     boolean first = true;
/* 73 */     for (Notification li : notificationList) {
/* 74 */       if (li.expire >= time) {
/* 75 */         if (!first) {
/* 76 */           temp.add(new Notification(li.text, li.image, time + thaumcraft.common.config.Config.notificationDelay, li.created, li.color));
/*    */         } else
/* 78 */           temp.add(li);
/*    */       }
/* 80 */       first = false;
/*    */     }
/* 82 */     notificationList = temp;
/* 83 */     return temp;
/*    */   }
/*    */   
/*    */   public static ArrayList<AspectNotification> getAspectListAndUpdate(long time) {
/* 87 */     ArrayList<AspectNotification> temp = new ArrayList();
/* 88 */     for (AspectNotification li : aspectList) {
/* 89 */       if (li.expire >= time) {
/* 90 */         temp.add(li);
/*    */       }
/*    */     }
/* 93 */     aspectList = temp;
/* 94 */     return temp;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/lib/PlayerNotifications.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */