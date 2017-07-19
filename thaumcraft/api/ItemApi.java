/*    */ package thaumcraft.api;
/*    */ 
/*    */ import cpw.mods.fml.common.FMLLog;
/*    */ import java.lang.reflect.Field;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemApi
/*    */ {
/*    */   public static ItemStack getItem(String itemString, int meta)
/*    */   {
/* 19 */     ItemStack item = null;
/*    */     try
/*    */     {
/* 22 */       String itemClass = "thaumcraft.common.config.ConfigItems";
/* 23 */       Object obj = Class.forName(itemClass).getField(itemString).get(null);
/* 24 */       if ((obj instanceof Item)) {
/* 25 */         item = new ItemStack((Item)obj, 1, meta);
/* 26 */       } else if ((obj instanceof ItemStack)) {
/* 27 */         item = (ItemStack)obj;
/*    */       }
/*    */     } catch (Exception ex) {
/* 30 */       FMLLog.warning("[Thaumcraft] Could not retrieve item identified by: " + itemString, new Object[0]);
/*    */     }
/*    */     
/* 33 */     return item;
/*    */   }
/*    */   
/*    */   public static ItemStack getBlock(String itemString, int meta) {
/* 37 */     ItemStack item = null;
/*    */     try
/*    */     {
/* 40 */       String itemClass = "thaumcraft.common.config.ConfigBlocks";
/* 41 */       Object obj = Class.forName(itemClass).getField(itemString).get(null);
/* 42 */       if ((obj instanceof Block)) {
/* 43 */         item = new ItemStack((Block)obj, 1, meta);
/* 44 */       } else if ((obj instanceof ItemStack)) {
/* 45 */         item = (ItemStack)obj;
/*    */       }
/*    */     } catch (Exception ex) {
/* 48 */       FMLLog.warning("[Thaumcraft] Could not retrieve block identified by: " + itemString, new Object[0]);
/*    */     }
/*    */     
/* 51 */     return item;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/ItemApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */