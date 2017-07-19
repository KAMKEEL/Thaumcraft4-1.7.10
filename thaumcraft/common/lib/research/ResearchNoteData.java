/*    */ package thaumcraft.common.lib.research;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import thaumcraft.common.lib.utils.HexUtils.Hex;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ResearchNoteData
/*    */ {
/*    */   public String key;
/*    */   public int color;
/* 12 */   public HashMap<String, ResearchManager.HexEntry> hexEntries = new HashMap();
/* 13 */   public HashMap<String, HexUtils.Hex> hexes = new HashMap();
/*    */   public boolean complete;
/*    */   public int copies;
/*    */   
/* 17 */   public boolean isComplete() { return this.complete; }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/research/ResearchNoteData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */