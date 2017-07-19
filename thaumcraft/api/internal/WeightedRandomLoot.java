/*    */ package thaumcraft.api.internal;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.WeightedRandom.Item;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WeightedRandomLoot
/*    */   extends WeightedRandom.Item
/*    */ {
/*    */   public ItemStack item;
/*    */   
/*    */   public WeightedRandomLoot(ItemStack stack, int weight)
/*    */   {
/* 16 */     super(weight);
/* 17 */     this.item = stack;
/*    */   }
/*    */   
/* 20 */   public static ArrayList<WeightedRandomLoot> lootBagCommon = new ArrayList();
/* 21 */   public static ArrayList<WeightedRandomLoot> lootBagUncommon = new ArrayList();
/* 22 */   public static ArrayList<WeightedRandomLoot> lootBagRare = new ArrayList();
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/internal/WeightedRandomLoot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */