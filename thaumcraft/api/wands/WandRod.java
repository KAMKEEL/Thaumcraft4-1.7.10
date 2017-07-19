/*     */ package thaumcraft.api.wands;
/*     */ 
/*     */ import java.util.LinkedHashMap;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WandRod
/*     */ {
/*     */   private String tag;
/*     */   private int craftCost;
/*     */   int capacity;
/*     */   protected ResourceLocation texture;
/*     */   ItemStack item;
/*     */   IWandRodOnUpdate onUpdate;
/*     */   boolean glow;
/*  52 */   public static LinkedHashMap<String, WandRod> rods = new LinkedHashMap();
/*     */   
/*     */   public WandRod(String tag, int capacity, ItemStack item, int craftCost, ResourceLocation texture) {
/*  55 */     setTag(tag);
/*  56 */     this.capacity = capacity;
/*  57 */     this.texture = texture;
/*  58 */     this.item = item;
/*  59 */     setCraftCost(craftCost);
/*  60 */     rods.put(tag, this);
/*     */   }
/*     */   
/*     */   public WandRod(String tag, int capacity, ItemStack item, int craftCost, IWandRodOnUpdate onUpdate, ResourceLocation texture) {
/*  64 */     setTag(tag);
/*  65 */     this.capacity = capacity;
/*  66 */     this.texture = texture;
/*  67 */     this.item = item;
/*  68 */     setCraftCost(craftCost);
/*  69 */     rods.put(tag, this);
/*  70 */     this.onUpdate = onUpdate;
/*     */   }
/*     */   
/*     */   public WandRod(String tag, int capacity, ItemStack item, int craftCost) {
/*  74 */     setTag(tag);
/*  75 */     this.capacity = capacity;
/*  76 */     this.texture = new ResourceLocation("thaumcraft", "textures/models/wand_rod_" + getTag() + ".png");
/*  77 */     this.item = item;
/*  78 */     setCraftCost(craftCost);
/*  79 */     rods.put(tag, this);
/*     */   }
/*     */   
/*     */   public WandRod(String tag, int capacity, ItemStack item, int craftCost, IWandRodOnUpdate onUpdate) {
/*  83 */     setTag(tag);
/*  84 */     this.capacity = capacity;
/*  85 */     this.texture = new ResourceLocation("thaumcraft", "textures/models/wand_rod_" + getTag() + ".png");
/*  86 */     this.item = item;
/*  87 */     setCraftCost(craftCost);
/*  88 */     rods.put(tag, this);
/*  89 */     this.onUpdate = onUpdate;
/*     */   }
/*     */   
/*     */   public String getTag() {
/*  93 */     return this.tag;
/*     */   }
/*     */   
/*     */   public void setTag(String tag) {
/*  97 */     this.tag = tag;
/*     */   }
/*     */   
/*     */   public int getCapacity() {
/* 101 */     return this.capacity;
/*     */   }
/*     */   
/*     */   public void setCapacity(int capacity) {
/* 105 */     this.capacity = capacity;
/*     */   }
/*     */   
/*     */   public ResourceLocation getTexture() {
/* 109 */     return this.texture;
/*     */   }
/*     */   
/*     */   public void setTexture(ResourceLocation texture) {
/* 113 */     this.texture = texture;
/*     */   }
/*     */   
/*     */   public ItemStack getItem() {
/* 117 */     return this.item;
/*     */   }
/*     */   
/*     */   public void setItem(ItemStack item) {
/* 121 */     this.item = item;
/*     */   }
/*     */   
/*     */   public int getCraftCost() {
/* 125 */     return this.craftCost;
/*     */   }
/*     */   
/*     */   public void setCraftCost(int craftCost) {
/* 129 */     this.craftCost = craftCost;
/*     */   }
/*     */   
/*     */   public IWandRodOnUpdate getOnUpdate() {
/* 133 */     return this.onUpdate;
/*     */   }
/*     */   
/*     */   public void setOnUpdate(IWandRodOnUpdate onUpdate) {
/* 137 */     this.onUpdate = onUpdate;
/*     */   }
/*     */   
/*     */   public boolean isGlowing() {
/* 141 */     return this.glow;
/*     */   }
/*     */   
/*     */   public void setGlowing(boolean hasGlow) {
/* 145 */     this.glow = hasGlow;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getResearch()
/*     */   {
/* 152 */     return "ROD_" + getTag();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/wands/WandRod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */