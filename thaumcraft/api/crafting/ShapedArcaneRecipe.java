/*     */ package thaumcraft.api.crafting;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ShapedArcaneRecipe
/*     */   implements IArcaneRecipe
/*     */ {
/*     */   private static final int MAX_CRAFT_GRID_WIDTH = 3;
/*     */   private static final int MAX_CRAFT_GRID_HEIGHT = 3;
/*  22 */   public ItemStack output = null;
/*  23 */   public Object[] input = null;
/*  24 */   public AspectList aspects = null;
/*     */   public String research;
/*  26 */   public int width = 0;
/*  27 */   public int height = 0;
/*  28 */   private boolean mirrored = true;
/*     */   
/*  30 */   public ShapedArcaneRecipe(String research, Block result, AspectList aspects, Object... recipe) { this(research, new ItemStack(result), aspects, recipe); }
/*  31 */   public ShapedArcaneRecipe(String research, Item result, AspectList aspects, Object... recipe) { this(research, new ItemStack(result), aspects, recipe); }
/*     */   
/*     */   public ShapedArcaneRecipe(String research, ItemStack result, AspectList aspects, Object... recipe) {
/*  34 */     this.output = result.func_77946_l();
/*  35 */     this.research = research;
/*  36 */     this.aspects = aspects;
/*  37 */     String shape = "";
/*     */     
/*  39 */     int idx = 0;
/*     */     
/*  41 */     if ((recipe[idx] instanceof Boolean))
/*     */     {
/*  43 */       this.mirrored = ((Boolean)recipe[idx]).booleanValue();
/*  44 */       if ((recipe[(idx + 1)] instanceof Object[]))
/*     */       {
/*  46 */         recipe = (Object[])recipe[(idx + 1)];
/*     */       }
/*     */       else
/*     */       {
/*  50 */         idx = 1;
/*     */       }
/*     */     }
/*     */     
/*  54 */     if ((recipe[idx] instanceof String[]))
/*     */     {
/*  56 */       String[] parts = (String[])recipe[(idx++)];
/*     */       
/*  58 */       for (String s : parts)
/*     */       {
/*  60 */         this.width = s.length();
/*  61 */         shape = shape + s;
/*     */       }
/*     */       
/*  64 */       this.height = parts.length;
/*     */     }
/*     */     else
/*     */     {
/*  68 */       while ((recipe[idx] instanceof String))
/*     */       {
/*  70 */         String s = (String)recipe[(idx++)];
/*  71 */         shape = shape + s;
/*  72 */         this.width = s.length();
/*  73 */         this.height += 1;
/*     */       }
/*     */     }
/*     */     
/*  77 */     if (this.width * this.height != shape.length())
/*     */     {
/*  79 */       String ret = "Invalid shaped ore recipe: ";
/*  80 */       for (Object tmp : recipe)
/*     */       {
/*  82 */         ret = ret + tmp + ", ";
/*     */       }
/*  84 */       ret = ret + this.output;
/*  85 */       throw new RuntimeException(ret);
/*     */     }
/*     */     
/*  88 */     HashMap<Character, Object> itemMap = new HashMap();
/*  90 */     for (; 
/*  90 */         idx < recipe.length; idx += 2)
/*     */     {
/*  92 */       Character chr = (Character)recipe[idx];
/*  93 */       Object in = recipe[(idx + 1)];
/*     */       
/*  95 */       if ((in instanceof ItemStack))
/*     */       {
/*  97 */         itemMap.put(chr, ((ItemStack)in).func_77946_l());
/*     */       }
/*  99 */       else if ((in instanceof Item))
/*     */       {
/* 101 */         itemMap.put(chr, new ItemStack((Item)in));
/*     */       }
/* 103 */       else if ((in instanceof Block))
/*     */       {
/* 105 */         itemMap.put(chr, new ItemStack((Block)in, 1, 32767));
/*     */       }
/* 107 */       else if ((in instanceof String))
/*     */       {
/* 109 */         itemMap.put(chr, OreDictionary.getOres((String)in));
/*     */       }
/*     */       else
/*     */       {
/* 113 */         String ret = "Invalid shaped ore recipe: ";
/* 114 */         for (Object tmp : recipe)
/*     */         {
/* 116 */           ret = ret + tmp + ", ";
/*     */         }
/* 118 */         ret = ret + this.output;
/* 119 */         throw new RuntimeException(ret);
/*     */       }
/*     */     }
/*     */     
/* 123 */     this.input = new Object[this.width * this.height];
/* 124 */     int x = 0;
/* 125 */     for (char chr : shape.toCharArray())
/*     */     {
/* 127 */       this.input[(x++)] = itemMap.get(Character.valueOf(chr));
/*     */     }
/*     */   }
/*     */   
/*     */   public ItemStack getCraftingResult(IInventory var1) {
/* 132 */     return this.output.func_77946_l();
/*     */   }
/*     */   
/* 135 */   public int getRecipeSize() { return this.input.length; }
/*     */   
/*     */   public ItemStack getRecipeOutput() {
/* 138 */     return this.output;
/*     */   }
/*     */   
/*     */   public boolean matches(IInventory inv, World world, EntityPlayer player)
/*     */   {
/* 143 */     if ((this.research.length() > 0) && (!ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), this.research))) {
/* 144 */       return false;
/*     */     }
/* 146 */     for (int x = 0; x <= 3 - this.width; x++)
/*     */     {
/* 148 */       for (int y = 0; y <= 3 - this.height; y++)
/*     */       {
/* 150 */         if (checkMatch(inv, x, y, false))
/*     */         {
/* 152 */           return true;
/*     */         }
/*     */         
/* 155 */         if ((this.mirrored) && (checkMatch(inv, x, y, true)))
/*     */         {
/* 157 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 162 */     return false;
/*     */   }
/*     */   
/*     */   private boolean checkMatch(IInventory inv, int startX, int startY, boolean mirror)
/*     */   {
/* 167 */     for (int x = 0; x < 3; x++)
/*     */     {
/* 169 */       for (int y = 0; y < 3; y++)
/*     */       {
/* 171 */         int subX = x - startX;
/* 172 */         int subY = y - startY;
/* 173 */         Object target = null;
/*     */         
/* 175 */         if ((subX >= 0) && (subY >= 0) && (subX < this.width) && (subY < this.height))
/*     */         {
/* 177 */           if (mirror)
/*     */           {
/* 179 */             target = this.input[(this.width - subX - 1 + subY * this.width)];
/*     */           }
/*     */           else
/*     */           {
/* 183 */             target = this.input[(subX + subY * this.width)];
/*     */           }
/*     */         }
/*     */         
/* 187 */         ItemStack slot = ThaumcraftApiHelper.getStackInRowAndColumn(inv, x, y);
/*     */         
/* 189 */         if ((target instanceof ItemStack))
/*     */         {
/* 191 */           if (!checkItemEquals((ItemStack)target, slot))
/*     */           {
/* 193 */             return false;
/*     */           }
/*     */         }
/* 196 */         else if ((target instanceof ArrayList))
/*     */         {
/* 198 */           boolean matched = false;
/*     */           
/* 200 */           for (ItemStack item : (ArrayList)target)
/*     */           {
/* 202 */             matched = (matched) || (checkItemEquals(item, slot));
/*     */           }
/*     */           
/* 205 */           if (!matched)
/*     */           {
/* 207 */             return false;
/*     */           }
/*     */         }
/* 210 */         else if ((target == null) && (slot != null))
/*     */         {
/* 212 */           return false;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 217 */     return true;
/*     */   }
/*     */   
/*     */   private boolean checkItemEquals(ItemStack target, ItemStack input)
/*     */   {
/* 222 */     if (((input == null) && (target != null)) || ((input != null) && (target == null)))
/*     */     {
/* 224 */       return false;
/*     */     }
/* 226 */     return (target.func_77973_b() == input.func_77973_b()) && ((!target.func_77942_o()) || (ThaumcraftApiHelper.areItemStackTagsEqualForCrafting(input, target))) && ((target.func_77960_j() == 32767) || (target.func_77960_j() == input.func_77960_j()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ShapedArcaneRecipe setMirrored(boolean mirror)
/*     */   {
/* 233 */     this.mirrored = mirror;
/* 234 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object[] getInput()
/*     */   {
/* 244 */     return this.input;
/*     */   }
/*     */   
/*     */   public AspectList getAspects()
/*     */   {
/* 249 */     return this.aspects;
/*     */   }
/*     */   
/*     */   public AspectList getAspects(IInventory inv)
/*     */   {
/* 254 */     return this.aspects;
/*     */   }
/*     */   
/*     */   public String getResearch()
/*     */   {
/* 259 */     return this.research;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/crafting/ShapedArcaneRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */