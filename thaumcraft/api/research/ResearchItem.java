/*     */ package thaumcraft.api.research;
/*     */ 
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
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
/*     */ public class ResearchItem
/*     */ {
/*     */   public final String key;
/*     */   public final String category;
/*     */   public final AspectList tags;
/*  29 */   public String[] parents = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  34 */   public String[] parentsHidden = null;
/*     */   
/*     */ 
/*     */ 
/*  38 */   public String[] siblings = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final int displayColumn;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final int displayRow;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final ItemStack icon_item;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final ResourceLocation icon_resource;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private int complexity;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isSpecial;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isSecondary;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isRound;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isStub;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isVirtual;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isConcealed;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isHidden;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isLost;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isAutoUnlock;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private ItemStack[] itemTriggers;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String[] entityTriggers;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private Aspect[] aspectTriggers;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 131 */   private ResearchPage[] pages = null;
/*     */   
/*     */   public ResearchItem(String key, String category)
/*     */   {
/* 135 */     this.key = key;
/* 136 */     this.category = category;
/* 137 */     this.tags = new AspectList();
/* 138 */     this.icon_resource = null;
/* 139 */     this.icon_item = null;
/* 140 */     this.displayColumn = 0;
/* 141 */     this.displayRow = 0;
/* 142 */     setVirtual();
/*     */   }
/*     */   
/*     */ 
/*     */   public ResearchItem(String key, String category, AspectList tags, int col, int row, int complex, ResourceLocation icon)
/*     */   {
/* 148 */     this.key = key;
/* 149 */     this.category = category;
/* 150 */     this.tags = tags;
/* 151 */     this.icon_resource = icon;
/* 152 */     this.icon_item = null;
/* 153 */     this.displayColumn = col;
/* 154 */     this.displayRow = row;
/* 155 */     this.complexity = complex;
/* 156 */     if (this.complexity < 1) this.complexity = 1;
/* 157 */     if (this.complexity > 3) this.complexity = 3;
/*     */   }
/*     */   
/*     */   public ResearchItem(String key, String category, AspectList tags, int col, int row, int complex, ItemStack icon)
/*     */   {
/* 162 */     this.key = key;
/* 163 */     this.category = category;
/* 164 */     this.tags = tags;
/* 165 */     this.icon_item = icon;
/* 166 */     this.icon_resource = null;
/* 167 */     this.displayColumn = col;
/* 168 */     this.displayRow = row;
/* 169 */     this.complexity = complex;
/* 170 */     if (this.complexity < 1) this.complexity = 1;
/* 171 */     if (this.complexity > 3) this.complexity = 3;
/*     */   }
/*     */   
/*     */   public ResearchItem setSpecial()
/*     */   {
/* 176 */     this.isSpecial = true;
/* 177 */     return this;
/*     */   }
/*     */   
/*     */   public ResearchItem setStub()
/*     */   {
/* 182 */     this.isStub = true;
/* 183 */     return this;
/*     */   }
/*     */   
/*     */   public ResearchItem setLost()
/*     */   {
/* 188 */     this.isLost = true;
/* 189 */     return this;
/*     */   }
/*     */   
/*     */   public ResearchItem setConcealed()
/*     */   {
/* 194 */     this.isConcealed = true;
/* 195 */     return this;
/*     */   }
/*     */   
/*     */   public ResearchItem setHidden()
/*     */   {
/* 200 */     this.isHidden = true;
/* 201 */     return this;
/*     */   }
/*     */   
/*     */   public ResearchItem setVirtual()
/*     */   {
/* 206 */     this.isVirtual = true;
/* 207 */     return this;
/*     */   }
/*     */   
/*     */   public ResearchItem setParents(String... par)
/*     */   {
/* 212 */     this.parents = par;
/* 213 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ResearchItem setParentsHidden(String... par)
/*     */   {
/* 220 */     this.parentsHidden = par;
/* 221 */     return this;
/*     */   }
/*     */   
/*     */   public ResearchItem setSiblings(String... sib)
/*     */   {
/* 226 */     this.siblings = sib;
/* 227 */     return this;
/*     */   }
/*     */   
/*     */   public ResearchItem setPages(ResearchPage... par)
/*     */   {
/* 232 */     this.pages = par;
/* 233 */     return this;
/*     */   }
/*     */   
/*     */   public ResearchPage[] getPages() {
/* 237 */     return this.pages;
/*     */   }
/*     */   
/*     */   public ResearchItem setItemTriggers(ItemStack... par)
/*     */   {
/* 242 */     this.itemTriggers = par;
/* 243 */     return this;
/*     */   }
/*     */   
/*     */   public ResearchItem setEntityTriggers(String... par)
/*     */   {
/* 248 */     this.entityTriggers = par;
/* 249 */     return this;
/*     */   }
/*     */   
/*     */   public ResearchItem setAspectTriggers(Aspect... par)
/*     */   {
/* 254 */     this.aspectTriggers = par;
/* 255 */     return this;
/*     */   }
/*     */   
/*     */   public ItemStack[] getItemTriggers() {
/* 259 */     return this.itemTriggers;
/*     */   }
/*     */   
/*     */   public String[] getEntityTriggers() {
/* 263 */     return this.entityTriggers;
/*     */   }
/*     */   
/*     */   public Aspect[] getAspectTriggers() {
/* 267 */     return this.aspectTriggers;
/*     */   }
/*     */   
/*     */   public ResearchItem registerResearchItem()
/*     */   {
/* 272 */     ResearchCategories.addResearch(this);
/* 273 */     return this;
/*     */   }
/*     */   
/*     */   public String getName()
/*     */   {
/* 278 */     return StatCollector.func_74838_a("tc.research_name." + this.key);
/*     */   }
/*     */   
/*     */   public String getText()
/*     */   {
/* 283 */     return StatCollector.func_74838_a("tc.research_text." + this.key);
/*     */   }
/*     */   
/*     */   public boolean isSpecial()
/*     */   {
/* 288 */     return this.isSpecial;
/*     */   }
/*     */   
/*     */   public boolean isStub()
/*     */   {
/* 293 */     return this.isStub;
/*     */   }
/*     */   
/*     */   public boolean isLost()
/*     */   {
/* 298 */     return this.isLost;
/*     */   }
/*     */   
/*     */   public boolean isConcealed()
/*     */   {
/* 303 */     return this.isConcealed;
/*     */   }
/*     */   
/*     */   public boolean isHidden()
/*     */   {
/* 308 */     return this.isHidden;
/*     */   }
/*     */   
/*     */   public boolean isVirtual()
/*     */   {
/* 313 */     return this.isVirtual;
/*     */   }
/*     */   
/*     */   public boolean isAutoUnlock() {
/* 317 */     return this.isAutoUnlock;
/*     */   }
/*     */   
/*     */   public ResearchItem setAutoUnlock()
/*     */   {
/* 322 */     this.isAutoUnlock = true;
/* 323 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isRound() {
/* 327 */     return this.isRound;
/*     */   }
/*     */   
/*     */   public ResearchItem setRound() {
/* 331 */     this.isRound = true;
/* 332 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isSecondary() {
/* 336 */     return this.isSecondary;
/*     */   }
/*     */   
/*     */   public ResearchItem setSecondary() {
/* 340 */     this.isSecondary = true;
/* 341 */     return this;
/*     */   }
/*     */   
/*     */   public int getComplexity() {
/* 345 */     return this.complexity;
/*     */   }
/*     */   
/*     */   public ResearchItem setComplexity(int complexity) {
/* 349 */     this.complexity = complexity;
/* 350 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Aspect getResearchPrimaryTag()
/*     */   {
/* 357 */     Aspect aspect = null;
/* 358 */     int highest = 0;
/* 359 */     if (this.tags != null) {
/* 360 */       for (Aspect tag : this.tags.getAspects())
/* 361 */         if (this.tags.getAmount(tag) > highest) {
/* 362 */           aspect = tag;
/* 363 */           highest = this.tags.getAmount(tag);
/*     */         }
/*     */     }
/* 366 */     return aspect;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/research/ResearchItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */