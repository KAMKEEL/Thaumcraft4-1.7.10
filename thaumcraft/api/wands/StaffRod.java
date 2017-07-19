/*    */ package thaumcraft.api.wands;
/*    */ 
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StaffRod
/*    */   extends WandRod
/*    */ {
/* 16 */   boolean runes = false;
/*    */   
/*    */   public StaffRod(String tag, int capacity, ItemStack item, int craftCost) {
/* 19 */     super(tag + "_staff", capacity, item, craftCost);
/* 20 */     this.texture = new ResourceLocation("thaumcraft", "textures/models/wand_rod_" + tag + ".png");
/*    */   }
/*    */   
/*    */   public StaffRod(String tag, int capacity, ItemStack item, int craftCost, IWandRodOnUpdate onUpdate, ResourceLocation texture)
/*    */   {
/* 25 */     super(tag + "_staff", capacity, item, craftCost, onUpdate, texture);
/*    */   }
/*    */   
/*    */   public StaffRod(String tag, int capacity, ItemStack item, int craftCost, IWandRodOnUpdate onUpdate)
/*    */   {
/* 30 */     super(tag + "_staff", capacity, item, craftCost, onUpdate);
/* 31 */     this.texture = new ResourceLocation("thaumcraft", "textures/models/wand_rod_" + tag + ".png");
/*    */   }
/*    */   
/*    */   public StaffRod(String tag, int capacity, ItemStack item, int craftCost, ResourceLocation texture)
/*    */   {
/* 36 */     super(tag + "_staff", capacity, item, craftCost, texture);
/*    */   }
/*    */   
/*    */   public boolean hasRunes() {
/* 40 */     return this.runes;
/*    */   }
/*    */   
/*    */   public void setRunes(boolean hasRunes) {
/* 44 */     this.runes = hasRunes;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/wands/StaffRod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */