/*     */ package thaumcraft.common.lib.crafting;
/*     */ 
/*     */ import java.util.LinkedHashMap;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.crafting.IArcaneRecipe;
/*     */ import thaumcraft.api.wands.WandCap;
/*     */ import thaumcraft.api.wands.WandRod;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ 
/*     */ public class ArcaneWandRecipe implements IArcaneRecipe
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
/*  32 */     ItemStack cap1 = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 0, 2);
/*  33 */     ItemStack cap2 = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 2, 0);
/*  34 */     ItemStack rod = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 1, 1);
/*  35 */     if ((ThaumcraftApiHelper.getStackInRowAndColumn(inv, 0, 0) != null) || (ThaumcraftApiHelper.getStackInRowAndColumn(inv, 0, 1) != null) || (ThaumcraftApiHelper.getStackInRowAndColumn(inv, 1, 0) != null) || (ThaumcraftApiHelper.getStackInRowAndColumn(inv, 1, 2) != null) || (ThaumcraftApiHelper.getStackInRowAndColumn(inv, 2, 1) != null) || (ThaumcraftApiHelper.getStackInRowAndColumn(inv, 2, 2) != null))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*  40 */       return null; }
/*  41 */     if ((cap1 != null) && (cap2 != null) && (rod != null) && (checkItemEquals(cap1, cap2)))
/*     */     {
/*  43 */       for (WandCap wc : WandCap.caps.values()) {
/*  44 */         if (checkItemEquals(cap1, wc.getItem())) {
/*  45 */           bc = wc.getTag();
/*  46 */           cc = wc.getCraftCost();
/*  47 */           break;
/*     */         }
/*     */       }
/*     */       
/*  51 */       for (WandRod wr : WandRod.rods.values()) {
/*  52 */         if (checkItemEquals(rod, wr.getItem())) {
/*  53 */           br = wr.getTag();
/*  54 */           cr = wr.getCraftCost();
/*  55 */           break;
/*     */         }
/*     */       }
/*     */       
/*  59 */       if ((bc != null) && (br != null) && ((!br.equals("wood")) || (!bc.equals("iron")))) {
/*  60 */         int cost = cc * cr;
/*  61 */         out = new ItemStack(ConfigItems.itemWandCasting, 1, cost);
/*  62 */         ((ItemWandCasting)out.func_77973_b()).setCap(out, (WandCap)WandCap.caps.get(bc));
/*  63 */         ((ItemWandCasting)out.func_77973_b()).setRod(out, (WandRod)WandRod.rods.get(br));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  68 */     return out;
/*     */   }
/*     */   
/*     */ 
/*     */   public AspectList getAspects(IInventory inv)
/*     */   {
/*  74 */     AspectList al = new AspectList();
/*     */     
/*  76 */     int cc = -1;
/*  77 */     int cr = -1;
/*  78 */     ItemStack cap1 = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 0, 2);
/*  79 */     ItemStack cap2 = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 2, 0);
/*  80 */     ItemStack rod = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 1, 1);
/*  81 */     if ((ThaumcraftApiHelper.getStackInRowAndColumn(inv, 0, 0) != null) || (ThaumcraftApiHelper.getStackInRowAndColumn(inv, 0, 1) != null) || (ThaumcraftApiHelper.getStackInRowAndColumn(inv, 1, 0) != null) || (ThaumcraftApiHelper.getStackInRowAndColumn(inv, 1, 2) != null) || (ThaumcraftApiHelper.getStackInRowAndColumn(inv, 2, 1) != null) || (ThaumcraftApiHelper.getStackInRowAndColumn(inv, 2, 2) != null))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*  86 */       return al; }
/*  87 */     int cost; if ((cap1 != null) && (cap2 != null) && (rod != null) && (checkItemEquals(cap1, cap2)))
/*     */     {
/*  89 */       for (WandCap wc : WandCap.caps.values()) {
/*  90 */         if (checkItemEquals(cap1, wc.getItem())) {
/*  91 */           cc = wc.getCraftCost();
/*  92 */           break;
/*     */         }
/*     */       }
/*     */       
/*  96 */       for (WandRod wr : WandRod.rods.values()) {
/*  97 */         if (checkItemEquals(rod, wr.getItem())) {
/*  98 */           cr = wr.getCraftCost();
/*  99 */           break;
/*     */         }
/*     */       }
/*     */       
/* 103 */       if ((cc >= 0) && (cr >= 0)) {
/* 104 */         cost = cc * cr;
/* 105 */         for (Aspect as : Aspect.getPrimalAspects()) {
/* 106 */           al.add(as, cost);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 111 */     return al;
/*     */   }
/*     */   
/*     */   public ItemStack getRecipeOutput()
/*     */   {
/* 116 */     return null;
/*     */   }
/*     */   
/*     */   public boolean matches(IInventory inv, World world, EntityPlayer player)
/*     */   {
/* 121 */     ItemStack cap1 = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 0, 2);
/* 122 */     ItemStack cap2 = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 2, 0);
/* 123 */     ItemStack rod = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 1, 1);
/*     */     
/* 125 */     if ((ThaumcraftApiHelper.getStackInRowAndColumn(inv, 0, 0) != null) || (ThaumcraftApiHelper.getStackInRowAndColumn(inv, 0, 1) != null) || (ThaumcraftApiHelper.getStackInRowAndColumn(inv, 1, 0) != null) || (ThaumcraftApiHelper.getStackInRowAndColumn(inv, 1, 2) != null) || (ThaumcraftApiHelper.getStackInRowAndColumn(inv, 2, 1) != null) || (ThaumcraftApiHelper.getStackInRowAndColumn(inv, 2, 2) != null))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 130 */       return false;
/*     */     }
/*     */     
/* 133 */     return checkMatch(cap1, cap2, rod, player);
/*     */   }
/*     */   
/*     */   private boolean checkMatch(ItemStack cap1, ItemStack cap2, ItemStack rod, EntityPlayer player)
/*     */   {
/* 138 */     boolean bc = false;
/* 139 */     boolean br = false;
/*     */     
/* 141 */     if ((cap1 != null) && (cap2 != null) && (rod != null) && (checkItemEquals(cap1, cap2)))
/*     */     {
/* 143 */       for (WandCap wc : WandCap.caps.values()) {
/* 144 */         if ((checkItemEquals(cap1, wc.getItem())) && (ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), wc.getResearch())))
/*     */         {
/* 146 */           bc = true;
/* 147 */           break;
/*     */         }
/*     */       }
/*     */       
/* 151 */       for (WandRod wr : WandRod.rods.values()) {
/* 152 */         if ((checkItemEquals(rod, wr.getItem())) && (ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), wr.getResearch())))
/*     */         {
/* 154 */           br = true;
/* 155 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 161 */     return (br) && (bc);
/*     */   }
/*     */   
/*     */   private boolean checkItemEquals(ItemStack target, ItemStack input)
/*     */   {
/* 166 */     if (((input == null) && (target != null)) || ((input != null) && (target == null)))
/*     */     {
/* 168 */       return false;
/*     */     }
/* 170 */     return (target.func_77973_b() == input.func_77973_b()) && ((!target.func_77942_o()) || (ItemStack.func_77970_a(target, input))) && ((target.func_77960_j() == 32767) || (target.func_77960_j() == input.func_77960_j()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getRecipeSize()
/*     */   {
/* 177 */     return 9;
/*     */   }
/*     */   
/*     */   public AspectList getAspects()
/*     */   {
/* 182 */     return null;
/*     */   }
/*     */   
/*     */   public String getResearch()
/*     */   {
/* 187 */     return "";
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/crafting/ArcaneWandRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */