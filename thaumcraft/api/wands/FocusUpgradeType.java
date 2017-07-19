/*     */ package thaumcraft.api.wands;
/*     */ 
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ 
/*     */ 
/*     */ public class FocusUpgradeType
/*     */ {
/*  13 */   public static FocusUpgradeType[] types = new FocusUpgradeType[20];
/*     */   
/*     */ 
/*     */   public short id;
/*     */   
/*     */ 
/*     */   public ResourceLocation icon;
/*     */   
/*     */ 
/*     */   public String name;
/*     */   
/*     */   public String text;
/*     */   
/*     */   public AspectList aspects;
/*     */   
/*     */ 
/*     */   public FocusUpgradeType(int id, ResourceLocation icon, String name, String text, AspectList aspects)
/*     */   {
/*  31 */     this.id = ((short)id);
/*  32 */     this.icon = icon;
/*  33 */     this.name = name;
/*  34 */     this.text = text;
/*  35 */     this.aspects = aspects;
/*     */     
/*  37 */     if ((id < types.length) && (types[id] != null)) {
/*  38 */       LogManager.getLogger("THAUMCRAFT").fatal("Focus Upgrade id " + id + " already occupied. Ignoring.");
/*  39 */       return;
/*     */     }
/*     */     
/*     */ 
/*  43 */     if (id >= types.length) {
/*  44 */       FocusUpgradeType[] temp = new FocusUpgradeType[id + 1];
/*  45 */       System.arraycopy(types, 0, temp, 0, types.length);
/*  46 */       types = temp;
/*     */     }
/*     */     
/*  49 */     types[id] = this;
/*     */   }
/*     */   
/*     */   public String getLocalizedName() {
/*  53 */     return StatCollector.func_74838_a(this.name);
/*     */   }
/*     */   
/*     */   public String getLocalizedText() {
/*  57 */     return StatCollector.func_74838_a(this.text);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj)
/*     */   {
/*  62 */     if ((obj instanceof FocusUpgradeType))
/*  63 */       return this.id == ((FocusUpgradeType)obj).id;
/*  64 */     return false;
/*     */   }
/*     */   
/*     */ 
/*  68 */   public static FocusUpgradeType potency = new FocusUpgradeType(0, new ResourceLocation("thaumcraft", "textures/foci/potency.png"), "focus.upgrade.potency.name", "focus.upgrade.potency.text", new AspectList().add(Aspect.WEAPON, 1));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  73 */   public static FocusUpgradeType frugal = new FocusUpgradeType(1, new ResourceLocation("thaumcraft", "textures/foci/frugal.png"), "focus.upgrade.frugal.name", "focus.upgrade.frugal.text", new AspectList().add(Aspect.HUNGER, 1));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  78 */   public static FocusUpgradeType treasure = new FocusUpgradeType(2, new ResourceLocation("thaumcraft", "textures/foci/treasure.png"), "focus.upgrade.treasure.name", "focus.upgrade.treasure.text", new AspectList().add(Aspect.GREED, 1));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  83 */   public static FocusUpgradeType enlarge = new FocusUpgradeType(3, new ResourceLocation("thaumcraft", "textures/foci/enlarge.png"), "focus.upgrade.enlarge.name", "focus.upgrade.enlarge.text", new AspectList().add(Aspect.TRAVEL, 1));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  88 */   public static FocusUpgradeType alchemistsfire = new FocusUpgradeType(4, new ResourceLocation("thaumcraft", "textures/foci/alchemistsfire.png"), "focus.upgrade.alchemistsfire.name", "focus.upgrade.alchemistsfire.text", new AspectList().add(Aspect.ENERGY, 1).add(Aspect.SLIME, 1));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  93 */   public static FocusUpgradeType alchemistsfrost = new FocusUpgradeType(5, new ResourceLocation("thaumcraft", "textures/foci/alchemistsfrost.png"), "focus.upgrade.alchemistsfrost.name", "focus.upgrade.alchemistsfrost.text", new AspectList().add(Aspect.COLD, 1).add(Aspect.TRAP, 1));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  98 */   public static FocusUpgradeType architect = new FocusUpgradeType(6, new ResourceLocation("thaumcraft", "textures/foci/architect.png"), "focus.upgrade.architect.name", "focus.upgrade.architect.text", new AspectList().add(Aspect.CRAFT, 1));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 103 */   public static FocusUpgradeType extend = new FocusUpgradeType(7, new ResourceLocation("thaumcraft", "textures/foci/extend.png"), "focus.upgrade.extend.name", "focus.upgrade.extend.text", new AspectList().add(Aspect.EXCHANGE, 1));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 108 */   public static FocusUpgradeType silktouch = new FocusUpgradeType(8, new ResourceLocation("thaumcraft", "textures/foci/silktouch.png"), "focus.upgrade.silktouch.name", "focus.upgrade.silktouch.text", new AspectList().add(Aspect.GREED, 1));
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/wands/FocusUpgradeType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */