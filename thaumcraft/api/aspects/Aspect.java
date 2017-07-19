/*     */ package thaumcraft.api.aspects;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedHashMap;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import org.apache.commons.lang3.text.WordUtils;
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
/*     */ public class Aspect
/*     */ {
/*     */   String tag;
/*     */   Aspect[] components;
/*     */   int color;
/*     */   private String chatcolor;
/*     */   ResourceLocation image;
/*     */   int blend;
/*     */   
/*     */   public Aspect(String tag, int color, Aspect[] components, ResourceLocation image, int blend)
/*     */   {
/*  30 */     if (aspects.containsKey(tag)) throw new IllegalArgumentException(tag + " already registered!");
/*  31 */     this.tag = tag;
/*  32 */     this.components = components;
/*  33 */     this.color = color;
/*  34 */     this.image = image;
/*  35 */     this.blend = blend;
/*  36 */     aspects.put(tag, this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Aspect(String tag, int color, Aspect[] components)
/*     */   {
/*  43 */     this(tag, color, components, new ResourceLocation("thaumcraft", "textures/aspects/" + tag.toLowerCase() + ".png"), 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Aspect(String tag, int color, Aspect[] components, int blend)
/*     */   {
/*  50 */     this(tag, color, components, new ResourceLocation("thaumcraft", "textures/aspects/" + tag.toLowerCase() + ".png"), blend);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Aspect(String tag, int color, String chatcolor, int blend)
/*     */   {
/*  58 */     this(tag, color, (Aspect[])null, blend);
/*  59 */     setChatcolor(chatcolor);
/*     */   }
/*     */   
/*     */   public int getColor() {
/*  63 */     return this.color;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  67 */     return WordUtils.capitalizeFully(this.tag);
/*     */   }
/*     */   
/*     */   public String getLocalizedDescription() {
/*  71 */     return StatCollector.func_74838_a("tc.aspect." + this.tag);
/*     */   }
/*     */   
/*     */   public String getTag() {
/*  75 */     return this.tag;
/*     */   }
/*     */   
/*     */   public void setTag(String tag) {
/*  79 */     this.tag = tag;
/*     */   }
/*     */   
/*     */   public Aspect[] getComponents() {
/*  83 */     return this.components;
/*     */   }
/*     */   
/*     */   public void setComponents(Aspect[] components) {
/*  87 */     this.components = components;
/*     */   }
/*     */   
/*     */   public ResourceLocation getImage() {
/*  91 */     return this.image;
/*     */   }
/*     */   
/*     */   public static Aspect getAspect(String tag) {
/*  95 */     return (Aspect)aspects.get(tag);
/*     */   }
/*     */   
/*     */   public int getBlend() {
/*  99 */     return this.blend;
/*     */   }
/*     */   
/*     */   public void setBlend(int blend) {
/* 103 */     this.blend = blend;
/*     */   }
/*     */   
/*     */   public boolean isPrimal() {
/* 107 */     return (getComponents() == null) || (getComponents().length != 2);
/*     */   }
/*     */   
/*     */   public static ArrayList<Aspect> getPrimalAspects()
/*     */   {
/* 112 */     ArrayList<Aspect> primals = new ArrayList();
/* 113 */     Collection<Aspect> pa = aspects.values();
/* 114 */     for (Aspect aspect : pa) {
/* 115 */       if (aspect.isPrimal()) primals.add(aspect);
/*     */     }
/* 117 */     return primals;
/*     */   }
/*     */   
/*     */   public static ArrayList<Aspect> getCompoundAspects() {
/* 121 */     ArrayList<Aspect> compounds = new ArrayList();
/* 122 */     Collection<Aspect> pa = aspects.values();
/* 123 */     for (Aspect aspect : pa) {
/* 124 */       if (!aspect.isPrimal()) compounds.add(aspect);
/*     */     }
/* 126 */     return compounds;
/*     */   }
/*     */   
/*     */   public String getChatcolor() {
/* 130 */     return this.chatcolor;
/*     */   }
/*     */   
/*     */   public void setChatcolor(String chatcolor) {
/* 134 */     this.chatcolor = chatcolor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 139 */   public static LinkedHashMap<String, Aspect> aspects = new LinkedHashMap();
/*     */   
/*     */ 
/* 142 */   public static final Aspect AIR = new Aspect("aer", 16777086, "e", 1);
/* 143 */   public static final Aspect EARTH = new Aspect("terra", 5685248, "2", 1);
/* 144 */   public static final Aspect FIRE = new Aspect("ignis", 16734721, "c", 1);
/* 145 */   public static final Aspect WATER = new Aspect("aqua", 3986684, "3", 1);
/* 146 */   public static final Aspect ORDER = new Aspect("ordo", 14013676, "7", 1);
/* 147 */   public static final Aspect ENTROPY = new Aspect("perditio", 4210752, "8", 771);
/*     */   
/*     */ 
/* 150 */   public static final Aspect VOID = new Aspect("vacuos", 8947848, new Aspect[] { AIR, ENTROPY }, 771);
/* 151 */   public static final Aspect LIGHT = new Aspect("lux", 16774755, new Aspect[] { AIR, FIRE });
/* 152 */   public static final Aspect WEATHER = new Aspect("tempestas", 16777215, new Aspect[] { AIR, WATER });
/* 153 */   public static final Aspect MOTION = new Aspect("motus", 13487348, new Aspect[] { AIR, ORDER });
/* 154 */   public static final Aspect COLD = new Aspect("gelum", 14811135, new Aspect[] { FIRE, ENTROPY });
/* 155 */   public static final Aspect CRYSTAL = new Aspect("vitreus", 8454143, new Aspect[] { EARTH, ORDER });
/* 156 */   public static final Aspect LIFE = new Aspect("victus", 14548997, new Aspect[] { WATER, EARTH });
/* 157 */   public static final Aspect POISON = new Aspect("venenum", 9039872, new Aspect[] { WATER, ENTROPY });
/* 158 */   public static final Aspect ENERGY = new Aspect("potentia", 12648447, new Aspect[] { ORDER, FIRE });
/* 159 */   public static final Aspect EXCHANGE = new Aspect("permutatio", 5735255, new Aspect[] { ENTROPY, ORDER });
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 167 */   public static final Aspect METAL = new Aspect("metallum", 11908557, new Aspect[] { EARTH, CRYSTAL });
/* 168 */   public static final Aspect DEATH = new Aspect("mortuus", 8943496, new Aspect[] { LIFE, ENTROPY });
/* 169 */   public static final Aspect FLIGHT = new Aspect("volatus", 15198167, new Aspect[] { AIR, MOTION });
/* 170 */   public static final Aspect DARKNESS = new Aspect("tenebrae", 2236962, new Aspect[] { VOID, LIGHT });
/* 171 */   public static final Aspect SOUL = new Aspect("spiritus", 15461371, new Aspect[] { LIFE, DEATH });
/* 172 */   public static final Aspect HEAL = new Aspect("sano", 16723764, new Aspect[] { LIFE, ORDER });
/* 173 */   public static final Aspect TRAVEL = new Aspect("iter", 14702683, new Aspect[] { MOTION, EARTH });
/* 174 */   public static final Aspect ELDRITCH = new Aspect("alienis", 8409216, new Aspect[] { VOID, DARKNESS });
/* 175 */   public static final Aspect MAGIC = new Aspect("praecantatio", 9896128, new Aspect[] { VOID, ENERGY });
/* 176 */   public static final Aspect AURA = new Aspect("auram", 16761087, new Aspect[] { MAGIC, AIR });
/* 177 */   public static final Aspect TAINT = new Aspect("vitium", 8388736, new Aspect[] { MAGIC, ENTROPY });
/* 178 */   public static final Aspect SLIME = new Aspect("limus", 129024, new Aspect[] { LIFE, WATER });
/* 179 */   public static final Aspect PLANT = new Aspect("herba", 109568, new Aspect[] { LIFE, EARTH });
/* 180 */   public static final Aspect TREE = new Aspect("arbor", 8873265, new Aspect[] { AIR, PLANT });
/* 181 */   public static final Aspect BEAST = new Aspect("bestia", 10445833, new Aspect[] { MOTION, LIFE });
/* 182 */   public static final Aspect FLESH = new Aspect("corpus", 15615885, new Aspect[] { DEATH, BEAST });
/* 183 */   public static final Aspect UNDEAD = new Aspect("exanimis", 3817472, new Aspect[] { MOTION, DEATH });
/* 184 */   public static final Aspect MIND = new Aspect("cognitio", 16761523, new Aspect[] { FIRE, SOUL });
/* 185 */   public static final Aspect SENSES = new Aspect("sensus", 1038847, new Aspect[] { AIR, SOUL });
/* 186 */   public static final Aspect MAN = new Aspect("humanus", 16766912, new Aspect[] { BEAST, MIND });
/* 187 */   public static final Aspect CROP = new Aspect("messis", 14791537, new Aspect[] { PLANT, MAN });
/* 188 */   public static final Aspect MINE = new Aspect("perfodio", 14471896, new Aspect[] { MAN, EARTH });
/* 189 */   public static final Aspect TOOL = new Aspect("instrumentum", 4210926, new Aspect[] { MAN, ORDER });
/* 190 */   public static final Aspect HARVEST = new Aspect("meto", 15641986, new Aspect[] { CROP, TOOL });
/* 191 */   public static final Aspect WEAPON = new Aspect("telum", 12603472, new Aspect[] { TOOL, FIRE });
/* 192 */   public static final Aspect ARMOR = new Aspect("tutamen", 49344, new Aspect[] { TOOL, EARTH });
/* 193 */   public static final Aspect HUNGER = new Aspect("fames", 10093317, new Aspect[] { LIFE, VOID });
/* 194 */   public static final Aspect GREED = new Aspect("lucrum", 15121988, new Aspect[] { MAN, HUNGER });
/* 195 */   public static final Aspect CRAFT = new Aspect("fabrico", 8428928, new Aspect[] { MAN, TOOL });
/* 196 */   public static final Aspect CLOTH = new Aspect("pannus", 15395522, new Aspect[] { TOOL, BEAST });
/* 197 */   public static final Aspect MECHANISM = new Aspect("machina", 8421536, new Aspect[] { MOTION, TOOL });
/* 198 */   public static final Aspect TRAP = new Aspect("vinculum", 10125440, new Aspect[] { MOTION, ENTROPY });
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/aspects/Aspect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */