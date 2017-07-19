/*    */ package thaumcraft.common.entities.golems;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public enum EnumGolemType
/*    */ {
/*  8 */   STRAW(10, 0, 0.38F, false, 1, 1, 75, 0), 
/*    */   
/* 10 */   WOOD(20, 6, 0.35F, false, 1, 4, 75, 1), 
/* 11 */   TALLOW(20, 9, 0.33F, false, 2, 8, 75, 2), 
/*    */   
/* 13 */   CLAY(25, 9, 0.33F, true, 1, 8, 100, 2), 
/* 14 */   FLESH(15, 6, 0.35F, false, 2, 4, 40, 1), 
/*    */   
/* 16 */   STONE(30, 12, 0.32F, true, 1, 16, 100, 3), 
/*    */   
/* 18 */   IRON(35, 15, 0.31F, true, 1, 32, 125, 4), 
/* 19 */   THAUMIUM(40, 15, 0.32F, true, 2, 32, 100, 4);
/*    */   
/*    */ 
/*    */   public final int health;
/*    */   
/*    */   public final int armor;
/*    */   
/*    */   public final float speed;
/*    */   public final boolean fireResist;
/*    */   public final int upgrades;
/*    */   public final int carry;
/*    */   public final int regenDelay;
/*    */   public final int strength;
/*    */   private static Map<Integer, EnumGolemType> codeToTypeMapping;
/*    */   
/*    */   public static EnumGolemType getType(int i)
/*    */   {
/* 36 */     if (codeToTypeMapping == null) {
/* 37 */       initMapping();
/*    */     }
/* 39 */     return (EnumGolemType)codeToTypeMapping.get(Integer.valueOf(i));
/*    */   }
/*    */   
/*    */   private static void initMapping() {
/* 43 */     codeToTypeMapping = new HashMap();
/* 44 */     for (EnumGolemType s : values()) {
/* 45 */       codeToTypeMapping.put(Integer.valueOf(s.ordinal()), s);
/*    */     }
/*    */   }
/*    */   
/*    */   private EnumGolemType(int health, int armor, float speed, boolean fireResist, int upgrades, int carry, int regenDelay, int strength) {
/* 50 */     this.health = health;
/* 51 */     this.armor = armor;
/* 52 */     this.speed = speed;
/* 53 */     this.fireResist = fireResist;
/* 54 */     this.upgrades = upgrades;
/* 55 */     this.carry = carry;
/* 56 */     this.regenDelay = regenDelay;
/* 57 */     this.strength = strength;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/golems/EnumGolemType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */