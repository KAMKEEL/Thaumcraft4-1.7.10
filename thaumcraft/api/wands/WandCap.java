/*     */ package thaumcraft.api.wands;
/*     */ 
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WandCap
/*     */ {
/*     */   private String tag;
/*     */   private int craftCost;
/*     */   float baseCostModifier;
/*     */   List<Aspect> specialCostModifierAspects;
/*     */   float specialCostModifier;
/*     */   ResourceLocation texture;
/*     */   ItemStack item;
/*  50 */   public static LinkedHashMap<String, WandCap> caps = new LinkedHashMap();
/*     */   
/*     */   public WandCap(String tag, float discount, ItemStack item, int craftCost) {
/*  53 */     setTag(tag);
/*  54 */     this.baseCostModifier = discount;
/*  55 */     this.specialCostModifierAspects = null;
/*  56 */     this.texture = new ResourceLocation("thaumcraft", "textures/models/wand_cap_" + getTag() + ".png");
/*  57 */     this.item = item;
/*  58 */     setCraftCost(craftCost);
/*  59 */     caps.put(tag, this);
/*     */   }
/*     */   
/*     */   public WandCap(String tag, float discount, List<Aspect> specialAspects, float discountSpecial, ItemStack item, int craftCost) {
/*  63 */     setTag(tag);
/*  64 */     this.baseCostModifier = discount;
/*  65 */     this.specialCostModifierAspects = specialAspects;
/*  66 */     this.specialCostModifier = discountSpecial;
/*  67 */     this.texture = new ResourceLocation("thaumcraft", "textures/models/wand_cap_" + getTag() + ".png");
/*  68 */     this.item = item;
/*  69 */     setCraftCost(craftCost);
/*  70 */     caps.put(tag, this);
/*     */   }
/*     */   
/*     */   public float getBaseCostModifier() {
/*  74 */     return this.baseCostModifier;
/*     */   }
/*     */   
/*     */   public List<Aspect> getSpecialCostModifierAspects() {
/*  78 */     return this.specialCostModifierAspects;
/*     */   }
/*     */   
/*     */   public float getSpecialCostModifier() {
/*  82 */     return this.specialCostModifier;
/*     */   }
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  86 */     return this.texture;
/*     */   }
/*     */   
/*     */   public void setTexture(ResourceLocation texture) {
/*  90 */     this.texture = texture;
/*     */   }
/*     */   
/*     */   public String getTag() {
/*  94 */     return this.tag;
/*     */   }
/*     */   
/*     */   public void setTag(String tag) {
/*  98 */     this.tag = tag;
/*     */   }
/*     */   
/*     */   public ItemStack getItem()
/*     */   {
/* 103 */     return this.item;
/*     */   }
/*     */   
/*     */   public void setItem(ItemStack item) {
/* 107 */     this.item = item;
/*     */   }
/*     */   
/*     */   public int getCraftCost() {
/* 111 */     return this.craftCost;
/*     */   }
/*     */   
/*     */   public void setCraftCost(int craftCost) {
/* 115 */     this.craftCost = craftCost;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getResearch()
/*     */   {
/* 122 */     return "CAP_" + getTag();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/wands/WandCap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */