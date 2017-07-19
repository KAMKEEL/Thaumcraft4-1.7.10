/*     */ package thaumcraft.api.wands;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
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
/*     */ public class WandTriggerRegistry
/*     */ {
/*  24 */   private static HashMap<String, HashMap<List, List>> triggers = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final String DEFAULT = "default";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void registerWandBlockTrigger(IWandTriggerManager manager, int event, Block block, int meta, String modid)
/*     */   {
/*  37 */     if (!triggers.containsKey(modid)) {
/*  38 */       triggers.put(modid, new HashMap());
/*     */     }
/*  40 */     HashMap<List, List> temp = (HashMap)triggers.get(modid);
/*  41 */     temp.put(Arrays.asList(new Object[] { block, Integer.valueOf(meta) }), Arrays.asList(new Object[] { manager, Integer.valueOf(event) }));
/*  42 */     triggers.put(modid, temp);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void registerWandBlockTrigger(IWandTriggerManager manager, int event, Block block, int meta)
/*     */   {
/*  49 */     registerWandBlockTrigger(manager, event, block, meta, "default");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean hasTrigger(Block block, int meta)
/*     */   {
/*  59 */     for (String modid : triggers.keySet()) {
/*  60 */       HashMap<List, List> temp = (HashMap)triggers.get(modid);
/*  61 */       if (!temp.containsKey(Arrays.asList(new Object[] { block, Integer.valueOf(meta) }))) { if (!temp.containsKey(Arrays.asList(new Object[] { block, Integer.valueOf(-1) }))) {}
/*  62 */       } else return true;
/*     */     }
/*  64 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static boolean hasTrigger(Block block, int meta, String modid)
/*     */   {
/*  71 */     if (!triggers.containsKey(modid)) return false;
/*  72 */     HashMap<List, List> temp = (HashMap)triggers.get(modid);
/*  73 */     if (!temp.containsKey(Arrays.asList(new Object[] { block, Integer.valueOf(meta) }))) { if (!temp.containsKey(Arrays.asList(new Object[] { block, Integer.valueOf(-1) }))) {}
/*  74 */     } else return true;
/*  75 */     return false;
/*     */   }
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
/*     */   public static boolean performTrigger(World world, ItemStack wand, EntityPlayer player, int x, int y, int z, int side, Block block, int meta)
/*     */   {
/*  96 */     for (String modid : triggers.keySet()) {
/*  97 */       HashMap<List, List> temp = (HashMap)triggers.get(modid);
/*  98 */       List l = (List)temp.get(Arrays.asList(new Object[] { block, Integer.valueOf(meta) }));
/*  99 */       if (l == null) l = (List)temp.get(Arrays.asList(new Object[] { block, Integer.valueOf(-1) }));
/* 100 */       if (l != null)
/*     */       {
/* 102 */         IWandTriggerManager manager = (IWandTriggerManager)l.get(0);
/* 103 */         int event = ((Integer)l.get(1)).intValue();
/* 104 */         boolean result = manager.performTrigger(world, wand, player, x, y, z, side, event);
/* 105 */         if (result) return true;
/*     */       } }
/* 107 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean performTrigger(World world, ItemStack wand, EntityPlayer player, int x, int y, int z, int side, Block block, int meta, String modid)
/*     */   {
/* 115 */     if (!triggers.containsKey(modid)) return false;
/* 116 */     HashMap<List, List> temp = (HashMap)triggers.get(modid);
/* 117 */     List l = (List)temp.get(Arrays.asList(new Object[] { block, Integer.valueOf(meta) }));
/* 118 */     if (l == null) l = (List)temp.get(Arrays.asList(new Object[] { block, Integer.valueOf(-1) }));
/* 119 */     if (l == null) { return false;
/*     */     }
/* 121 */     IWandTriggerManager manager = (IWandTriggerManager)l.get(0);
/* 122 */     int event = ((Integer)l.get(1)).intValue();
/* 123 */     return manager.performTrigger(world, wand, player, x, y, z, side, event);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/wands/WandTriggerRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */