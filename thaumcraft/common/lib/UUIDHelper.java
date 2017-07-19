/*    */ package thaumcraft.common.lib;
/*    */ 
/*    */ import com.google.common.base.Charsets;
/*    */ import com.google.common.base.Strings;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParser;
/*    */ import java.io.InputStreamReader;
/*    */ import java.net.HttpURLConnection;
/*    */ import java.net.URL;
/*    */ import java.util.UUID;
/*    */ 
/*    */ public class UUIDHelper
/*    */ {
/*    */   public static String uuidToName(String uuid)
/*    */   {
/* 17 */     if (!uuid.isEmpty()) {
/*    */       try {
/* 19 */         URL url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + cleanUUID(uuid));
/*    */         
/* 21 */         HttpURLConnection connection = (HttpURLConnection)url.openConnection();
/*    */         
/* 23 */         connection.setRequestMethod("GET");
/* 24 */         connection.setRequestProperty("Content-Type", "application/json");
/* 25 */         connection.setUseCaches(false);
/* 26 */         connection.setDoInput(true);
/* 27 */         connection.setDoOutput(true);
/* 28 */         InputStreamReader isr = new InputStreamReader(connection.getInputStream());
/* 29 */         JsonObject profile = (JsonObject)new JsonParser().parse(isr);
/* 30 */         return profile.get("name").toString().replace('"', '\000').trim();
/*    */       }
/*    */       catch (Exception e) {}
/*    */     }
/* 34 */     return "";
/*    */   }
/*    */   
/*    */   public static UUID getOnlineUUID(String name)
/*    */   {
/* 39 */     if (!Strings.isNullOrEmpty(name)) {
/*    */       try {
/* 41 */         URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
/* 42 */         HttpURLConnection connection = (HttpURLConnection)url.openConnection();
/* 43 */         connection.setRequestMethod("GET");
/* 44 */         connection.setRequestProperty("Content-Type", "application/json");
/* 45 */         connection.setUseCaches(false);
/* 46 */         connection.setDoInput(true);
/* 47 */         connection.setDoOutput(true);
/* 48 */         JsonObject profile = (JsonObject)new JsonParser().parse(new InputStreamReader(connection.getInputStream()));
/*    */         
/* 50 */         return UUID.fromString(fullUUID(profile.get("id").toString()));
/*    */       }
/*    */       catch (Exception e) {}
/*    */     }
/*    */     
/* 55 */     return null;
/*    */   }
/*    */   
/*    */   public static UUID getOfflineUUID(String name)
/*    */   {
/* 60 */     return UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(Charsets.UTF_8));
/*    */   }
/*    */   
/*    */   public static UUID stringToUUID(String uuid)
/*    */   {
/*    */     try {
/* 66 */       return UUID.fromString(uuid);
/*    */     }
/*    */     catch (Exception e) {}
/* 69 */     return null;
/*    */   }
/*    */   
/*    */   public static String fullUUID(String uuid)
/*    */   {
/* 74 */     uuid = cleanUUID(uuid);
/* 75 */     uuid = uuid.substring(0, 8) + "-" + uuid.substring(8, 12) + "-" + uuid.substring(12, 16) + "-" + uuid.substring(16, 20) + "-" + uuid.substring(20, 32);
/*    */     
/*    */ 
/* 78 */     return uuid;
/*    */   }
/*    */   
/*    */   public static String cleanUUID(String uuid)
/*    */   {
/* 83 */     return uuid.replaceAll("[^a-zA-Z0-9]", "");
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/UUIDHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */