/*    */ package thaumcraft.api.research;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ResearchCategoryList
/*    */ {
/*    */   public int minDisplayColumn;
/*    */   public int minDisplayRow;
/*    */   public int maxDisplayColumn;
/*    */   public int maxDisplayRow;
/*    */   public ResourceLocation icon;
/*    */   public ResourceLocation background;
/*    */   
/*    */   public ResearchCategoryList(ResourceLocation icon, ResourceLocation background)
/*    */   {
/* 27 */     this.icon = icon;
/* 28 */     this.background = background;
/*    */   }
/*    */   
/*    */ 
/* 32 */   public Map<String, ResearchItem> research = new HashMap();
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/research/ResearchCategoryList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */