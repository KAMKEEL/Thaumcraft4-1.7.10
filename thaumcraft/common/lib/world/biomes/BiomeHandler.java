/*    */ package thaumcraft.common.lib.world.biomes;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.world.biome.BiomeGenBase;
/*    */ import net.minecraftforge.common.BiomeDictionary;
/*    */ import net.minecraftforge.common.BiomeDictionary.Type;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ 
/*    */ public class BiomeHandler
/*    */ {
/* 13 */   public static HashMap<BiomeDictionary.Type, List> biomeInfo = new HashMap();
/*    */   
/*    */   public static void registerBiomeInfo(BiomeDictionary.Type type, int auraLevel, Aspect tag, boolean greatwood, float greatwoodchance)
/*    */   {
/* 17 */     biomeInfo.put(type, java.util.Arrays.asList(new Object[] { Integer.valueOf(auraLevel), tag, Boolean.valueOf(greatwood), Float.valueOf(greatwoodchance) }));
/*    */   }
/*    */   
/*    */   public static int getBiomeAura(BiomeGenBase biome) {
/*    */     try {
/* 22 */       BiomeDictionary.Type[] types = BiomeDictionary.getTypesForBiome(biome);
/* 23 */       int average = 0;
/* 24 */       int count = 0;
/* 25 */       for (BiomeDictionary.Type type : types) {
/* 26 */         average += ((Integer)((List)biomeInfo.get(type)).get(0)).intValue();
/* 27 */         count++;
/*    */       }
/* 29 */       return average / count;
/*    */     } catch (Exception e) {}
/* 31 */     return 100;
/*    */   }
/*    */   
/*    */   public static Aspect getRandomBiomeTag(int biomeId, Random random) {
/*    */     try {
/* 36 */       BiomeDictionary.Type[] types = BiomeDictionary.getTypesForBiome(BiomeGenBase.func_150568_d(biomeId));
/* 37 */       BiomeDictionary.Type type = types[random.nextInt(types.length)];
/* 38 */       return (Aspect)((List)biomeInfo.get(type)).get(1);
/*    */     } catch (Exception e) {}
/* 40 */     return null;
/*    */   }
/*    */   
/*    */   public static float getBiomeSupportsGreatwood(int biomeId) {
/*    */     try {
/* 45 */       BiomeDictionary.Type[] types = BiomeDictionary.getTypesForBiome(BiomeGenBase.func_150568_d(biomeId));
/* 46 */       for (BiomeDictionary.Type type : types) {
/* 47 */         if (((Boolean)((List)biomeInfo.get(type)).get(2)).booleanValue()) return ((Float)((List)biomeInfo.get(type)).get(3)).floatValue();
/*    */       }
/*    */     } catch (Exception e) {}
/* 50 */     return 0.0F;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/biomes/BiomeHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */