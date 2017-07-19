/*    */ package thaumcraft.api.research;
/*    */ 
/*    */ import cpw.mods.fml.common.FMLLog;
/*    */ import java.util.Collection;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.StatCollector;
/*    */ import org.apache.logging.log4j.Level;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ResearchCategories
/*    */ {
/* 16 */   public static LinkedHashMap<String, ResearchCategoryList> researchCategories = new LinkedHashMap();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public static ResearchCategoryList getResearchList(String key)
/*    */   {
/* 23 */     return (ResearchCategoryList)researchCategories.get(key);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static String getCategoryName(String key)
/*    */   {
/* 32 */     return StatCollector.func_74838_a("tc.research_category." + key);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public static ResearchItem getResearch(String key)
/*    */   {
/* 40 */     Collection rc = researchCategories.values();
/* 41 */     for (Object cat : rc) {
/* 42 */       Collection rl = ((ResearchCategoryList)cat).research.values();
/* 43 */       for (Object ri : rl) {
/* 44 */         if (((ResearchItem)ri).key.equals(key)) return (ResearchItem)ri;
/*    */       }
/*    */     }
/* 47 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void registerCategory(String key, ResourceLocation icon, ResourceLocation background)
/*    */   {
/* 58 */     if (getResearchList(key) == null) {
/* 59 */       ResearchCategoryList rl = new ResearchCategoryList(icon, background);
/* 60 */       researchCategories.put(key, rl);
/*    */     }
/*    */   }
/*    */   
/*    */   public static void addResearch(ResearchItem ri) {
/* 65 */     ResearchCategoryList rl = getResearchList(ri.category);
/* 66 */     if ((rl != null) && (!rl.research.containsKey(ri.key)))
/*    */     {
/* 68 */       if (!ri.isVirtual()) {
/* 69 */         for (ResearchItem rr : rl.research.values()) {
/* 70 */           if ((rr.displayColumn == ri.displayColumn) && (rr.displayRow == ri.displayRow)) {
/* 71 */             FMLLog.log(Level.FATAL, "[Thaumcraft] Research [" + ri.getName() + "] not added as it overlaps with existing research [" + rr.getName() + "]", new Object[0]);
/* 72 */             return;
/*    */           }
/*    */         }
/*    */       }
/*    */       
/*    */ 
/* 78 */       rl.research.put(ri.key, ri);
/*    */       
/* 80 */       if (ri.displayColumn < rl.minDisplayColumn)
/*    */       {
/* 82 */         rl.minDisplayColumn = ri.displayColumn;
/*    */       }
/*    */       
/* 85 */       if (ri.displayRow < rl.minDisplayRow)
/*    */       {
/* 87 */         rl.minDisplayRow = ri.displayRow;
/*    */       }
/*    */       
/* 90 */       if (ri.displayColumn > rl.maxDisplayColumn)
/*    */       {
/* 92 */         rl.maxDisplayColumn = ri.displayColumn;
/*    */       }
/*    */       
/* 95 */       if (ri.displayRow > rl.maxDisplayRow)
/*    */       {
/* 97 */         rl.maxDisplayRow = ri.displayRow;
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/research/ResearchCategories.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */