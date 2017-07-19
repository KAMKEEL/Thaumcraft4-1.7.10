/*     */ package thaumcraft.common.lib.crafting;
/*     */ 
/*     */ import java.util.LinkedHashMap;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagByte;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.wands.WandCap;
/*     */ import thaumcraft.api.wands.WandRod;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ 
/*     */ public class ArcaneSceptreRecipe implements thaumcraft.api.crafting.IArcaneRecipe
/*     */ {
/*     */   private static final int MAX_CRAFT_GRID_WIDTH = 3;
/*     */   private static final int MAX_CRAFT_GRID_HEIGHT = 3;
/*  21 */   private boolean mirrored = true;
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack getCraftingResult(IInventory inv)
/*     */   {
/*  27 */     ItemStack out = null;
/*  28 */     String bc = null;
/*  29 */     String br = null;
/*  30 */     int cc = 0;
/*  31 */     int cr = 0;
/*  32 */     ItemStack cap1 = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 1, 0);
/*  33 */     ItemStack cap2 = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 2, 1);
/*  34 */     ItemStack cap3 = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 0, 2);
/*  35 */     ItemStack rod = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 1, 1);
/*  36 */     ItemStack focus = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 2, 0);
/*     */     
/*  38 */     if ((ThaumcraftApiHelper.getStackInRowAndColumn(inv, 0, 0) != null) || (ThaumcraftApiHelper.getStackInRowAndColumn(inv, 0, 1) != null) || (ThaumcraftApiHelper.getStackInRowAndColumn(inv, 1, 2) != null) || (ThaumcraftApiHelper.getStackInRowAndColumn(inv, 2, 2) != null))
/*     */     {
/*     */ 
/*  41 */       return null;
/*     */     }
/*  43 */     if ((cap1 != null) && (cap2 != null) && (cap3 != null) && (rod != null) && (focus != null) && (checkItemEquals(focus, new ItemStack(ConfigItems.itemResource, 1, 15))) && (checkItemEquals(cap1, cap2)) && (checkItemEquals(cap1, cap3)))
/*     */     {
/*     */ 
/*     */ 
/*  47 */       for (WandCap wc : WandCap.caps.values()) {
/*  48 */         if (checkItemEquals(cap1, wc.getItem())) {
/*  49 */           bc = wc.getTag();
/*  50 */           cc = wc.getCraftCost();
/*  51 */           break;
/*     */         }
/*     */       }
/*     */       
/*  55 */       for (WandRod wr : WandRod.rods.values()) {
/*  56 */         if (checkItemEquals(rod, wr.getItem())) {
/*  57 */           br = wr.getTag();
/*  58 */           cr = wr.getCraftCost();
/*  59 */           break;
/*     */         }
/*     */       }
/*     */       
/*  63 */       if ((bc != null) && (br != null)) {
/*  64 */         int cost = (int)(cc * cr * 1.5F);
/*  65 */         out = new ItemStack(ConfigItems.itemWandCasting, 1, cost);
/*  66 */         ((ItemWandCasting)out.func_77973_b()).setCap(out, (WandCap)WandCap.caps.get(bc));
/*  67 */         ((ItemWandCasting)out.func_77973_b()).setRod(out, (WandRod)WandRod.rods.get(br));
/*  68 */         out.func_77983_a("sceptre", new NBTTagByte((byte)1));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  73 */     return out;
/*     */   }
/*     */   
/*     */ 
/*     */   public AspectList getAspects(IInventory inv)
/*     */   {
/*  79 */     AspectList al = new AspectList();
/*     */     
/*  81 */     int cc = -1;
/*  82 */     int cr = -1;
/*  83 */     ItemStack cap1 = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 1, 0);
/*  84 */     ItemStack cap2 = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 2, 1);
/*  85 */     ItemStack cap3 = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 0, 2);
/*  86 */     ItemStack rod = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 1, 1);
/*  87 */     ItemStack focus = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 2, 0);
/*     */     
/*  89 */     if ((ThaumcraftApiHelper.getStackInRowAndColumn(inv, 0, 0) != null) || (ThaumcraftApiHelper.getStackInRowAndColumn(inv, 0, 1) != null) || (ThaumcraftApiHelper.getStackInRowAndColumn(inv, 1, 2) != null) || (ThaumcraftApiHelper.getStackInRowAndColumn(inv, 2, 2) != null))
/*     */     {
/*     */ 
/*  92 */       return al; }
/*     */     int cost;
/*  94 */     if ((cap1 != null) && (cap2 != null) && (cap3 != null) && (rod != null) && (focus != null) && (checkItemEquals(focus, new ItemStack(ConfigItems.itemResource, 1, 15))) && (checkItemEquals(cap1, cap2)) && (checkItemEquals(cap1, cap3)))
/*     */     {
/*     */ 
/*     */ 
/*  98 */       for (WandCap wc : WandCap.caps.values()) {
/*  99 */         if (checkItemEquals(cap1, wc.getItem())) {
/* 100 */           cc = wc.getCraftCost();
/* 101 */           break;
/*     */         }
/*     */       }
/*     */       
/* 105 */       for (WandRod wr : WandRod.rods.values()) {
/* 106 */         if (checkItemEquals(rod, wr.getItem())) {
/* 107 */           cr = wr.getCraftCost();
/* 108 */           break;
/*     */         }
/*     */       }
/*     */       
/* 112 */       if ((cc >= 0) && (cr >= 0)) {
/* 113 */         cost = (int)(cc * cr * 1.5F);
/* 114 */         for (Aspect as : Aspect.getPrimalAspects()) {
/* 115 */           al.add(as, cost);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 120 */     return al;
/*     */   }
/*     */   
/*     */   public ItemStack getRecipeOutput()
/*     */   {
/* 125 */     return null;
/*     */   }
/*     */   
/*     */   public boolean matches(IInventory inv, World world, EntityPlayer player)
/*     */   {
/* 130 */     if (!ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), "SCEPTRE")) { return false;
/*     */     }
/* 132 */     ItemStack cap1 = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 1, 0);
/* 133 */     ItemStack cap2 = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 2, 1);
/* 134 */     ItemStack cap3 = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 0, 2);
/* 135 */     ItemStack rod = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 1, 1);
/* 136 */     ItemStack focus = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 2, 0);
/*     */     
/* 138 */     if ((ThaumcraftApiHelper.getStackInRowAndColumn(inv, 0, 0) != null) || (ThaumcraftApiHelper.getStackInRowAndColumn(inv, 0, 1) != null) || (ThaumcraftApiHelper.getStackInRowAndColumn(inv, 1, 2) != null) || (ThaumcraftApiHelper.getStackInRowAndColumn(inv, 2, 2) != null))
/*     */     {
/*     */ 
/* 141 */       return false;
/*     */     }
/* 143 */     return checkMatch(cap1, cap2, cap3, rod, focus, player);
/*     */   }
/*     */   
/*     */   private boolean checkMatch(ItemStack cap1, ItemStack cap2, ItemStack cap3, ItemStack rod, ItemStack focus, EntityPlayer player)
/*     */   {
/* 148 */     boolean bc = false;
/* 149 */     boolean br = false;
/*     */     
/* 151 */     if ((cap1 != null) && (cap2 != null) && (cap3 != null) && (rod != null) && (focus != null) && (checkItemEquals(focus, new ItemStack(ConfigItems.itemResource, 1, 15))) && (checkItemEquals(cap1, cap2)) && (checkItemEquals(cap1, cap3)))
/*     */     {
/*     */ 
/*     */ 
/* 155 */       for (WandCap wc : WandCap.caps.values()) {
/* 156 */         if ((checkItemEquals(cap1, wc.getItem())) && (ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), wc.getResearch())))
/*     */         {
/* 158 */           bc = true;
/* 159 */           break;
/*     */         }
/*     */       }
/*     */       
/* 163 */       for (WandRod wr : WandRod.rods.values()) {
/* 164 */         if ((checkItemEquals(rod, wr.getItem())) && (ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), wr.getResearch())))
/*     */         {
/* 166 */           br = true;
/* 167 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 173 */     return (br) && (bc);
/*     */   }
/*     */   
/*     */   private boolean checkItemEquals(ItemStack target, ItemStack input)
/*     */   {
/* 178 */     if (((input == null) && (target != null)) || ((input != null) && (target == null)))
/*     */     {
/* 180 */       return false;
/*     */     }
/* 182 */     return (target.func_77973_b() == input.func_77973_b()) && (target.func_77960_j() == input.func_77960_j());
/*     */   }
/*     */   
/*     */ 
/*     */   public int getRecipeSize()
/*     */   {
/* 188 */     return 9;
/*     */   }
/*     */   
/*     */   public AspectList getAspects()
/*     */   {
/* 193 */     return null;
/*     */   }
/*     */   
/*     */   public String getResearch()
/*     */   {
/* 198 */     return "";
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/crafting/ArcaneSceptreRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */