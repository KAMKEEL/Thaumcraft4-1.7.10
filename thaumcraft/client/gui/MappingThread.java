/*    */ package thaumcraft.client.gui;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.common.lib.research.ScanManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MappingThread
/*    */   implements Runnable
/*    */ {
/* 18 */   Map<String, Integer> idMappings = null;
/*    */   
/*    */   public MappingThread(Map<String, Integer> idMappings) {
/* 21 */     this.idMappings = idMappings;
/*    */   }
/*    */   
/*    */   public void run()
/*    */   {
/* 26 */     for (Integer id : this.idMappings.values()) {
/*    */       try {
/* 28 */         Item i = Item.func_150899_d(id.intValue());
/* 29 */         if (i != null) {
/* 30 */           List<ItemStack> q = new ArrayList();
/* 31 */           i.func_150895_a(i, i.func_77640_w(), q);
/* 32 */           if ((q != null) && (q.size() > 0)) {
/* 33 */             for (ItemStack stack : q) {
/* 34 */               GuiResearchRecipe.putToCache(ScanManager.generateItemHash(i, stack.func_77960_j()), stack.func_77946_l());
/*    */             }
/*    */           }
/*    */         } else {
/* 38 */           Block b = Block.func_149729_e(id.intValue());
/* 39 */           for (int a = 0; a < 16; a++) {
/* 40 */             GuiResearchRecipe.putToCache(ScanManager.generateItemHash(Item.func_150898_a(b), a), new ItemStack(b, 1, a));
/*    */           }
/*    */         }
/*    */       }
/*    */       catch (Exception e) {}
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/gui/MappingThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */