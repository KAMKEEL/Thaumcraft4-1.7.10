/*     */ package thaumcraft.api.crafting;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
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
/*     */ public class ShapelessArcaneRecipe
/*     */   implements IArcaneRecipe
/*     */ {
/*  18 */   private ItemStack output = null;
/*  19 */   private ArrayList input = new ArrayList();
/*     */   
/*  21 */   public AspectList aspects = null;
/*     */   public String research;
/*     */   
/*  24 */   public ShapelessArcaneRecipe(String research, Block result, AspectList aspects, Object... recipe) { this(research, new ItemStack(result), aspects, recipe); }
/*  25 */   public ShapelessArcaneRecipe(String research, Item result, AspectList aspects, Object... recipe) { this(research, new ItemStack(result), aspects, recipe); }
/*     */   
/*     */   public ShapelessArcaneRecipe(String research, ItemStack result, AspectList aspects, Object... recipe)
/*     */   {
/*  29 */     this.output = result.func_77946_l();
/*  30 */     this.research = research;
/*  31 */     this.aspects = aspects;
/*  32 */     for (Object in : recipe)
/*     */     {
/*  34 */       if ((in instanceof ItemStack))
/*     */       {
/*  36 */         this.input.add(((ItemStack)in).func_77946_l());
/*     */       }
/*  38 */       else if ((in instanceof Item))
/*     */       {
/*  40 */         this.input.add(new ItemStack((Item)in));
/*     */       }
/*  42 */       else if ((in instanceof Block))
/*     */       {
/*  44 */         this.input.add(new ItemStack((Block)in));
/*     */       }
/*  46 */       else if ((in instanceof String))
/*     */       {
/*  48 */         this.input.add(OreDictionary.getOres((String)in));
/*     */       }
/*     */       else
/*     */       {
/*  52 */         String ret = "Invalid shapeless ore recipe: ";
/*  53 */         for (Object tmp : recipe)
/*     */         {
/*  55 */           ret = ret + tmp + ", ";
/*     */         }
/*  57 */         ret = ret + this.output;
/*  58 */         throw new RuntimeException(ret);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public int getRecipeSize() {
/*  64 */     return this.input.size();
/*     */   }
/*     */   
/*  67 */   public ItemStack getRecipeOutput() { return this.output; }
/*     */   
/*     */   public ItemStack getCraftingResult(IInventory var1) {
/*  70 */     return this.output.func_77946_l();
/*     */   }
/*     */   
/*     */   public boolean matches(IInventory var1, World world, EntityPlayer player)
/*     */   {
/*  75 */     if ((this.research.length() > 0) && (!ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), this.research))) {
/*  76 */       return false;
/*     */     }
/*     */     
/*  79 */     ArrayList required = new ArrayList(this.input);
/*     */     
/*  81 */     for (int x = 0; x < 9; x++)
/*     */     {
/*  83 */       ItemStack slot = var1.func_70301_a(x);
/*     */       
/*  85 */       if (slot != null)
/*     */       {
/*  87 */         boolean inRecipe = false;
/*  88 */         Iterator req = required.iterator();
/*     */         
/*  90 */         while (req.hasNext())
/*     */         {
/*  92 */           boolean match = false;
/*     */           
/*  94 */           Object next = req.next();
/*     */           
/*  96 */           if ((next instanceof ItemStack))
/*     */           {
/*  98 */             match = checkItemEquals((ItemStack)next, slot);
/*     */           }
/* 100 */           else if ((next instanceof ArrayList))
/*     */           {
/* 102 */             for (ItemStack item : (ArrayList)next)
/*     */             {
/* 104 */               match = (match) || (checkItemEquals(item, slot));
/*     */             }
/*     */           }
/*     */           
/* 108 */           if (match)
/*     */           {
/* 110 */             inRecipe = true;
/* 111 */             required.remove(next);
/* 112 */             break;
/*     */           }
/*     */         }
/*     */         
/* 116 */         if (!inRecipe)
/*     */         {
/* 118 */           return false;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 123 */     return required.isEmpty();
/*     */   }
/*     */   
/*     */   private boolean checkItemEquals(ItemStack target, ItemStack input)
/*     */   {
/* 128 */     if (((input == null) && (target != null)) || ((input != null) && (target == null)))
/*     */     {
/* 130 */       return false;
/*     */     }
/* 132 */     return (target.func_77973_b() == input.func_77973_b()) && ((!target.func_77942_o()) || (ThaumcraftApiHelper.areItemStackTagsEqualForCrafting(input, target))) && ((target.func_77960_j() == 32767) || (target.func_77960_j() == input.func_77960_j()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ArrayList getInput()
/*     */   {
/* 144 */     return this.input;
/*     */   }
/*     */   
/*     */   public AspectList getAspects()
/*     */   {
/* 149 */     return this.aspects;
/*     */   }
/*     */   
/*     */   public AspectList getAspects(IInventory inv)
/*     */   {
/* 154 */     return this.aspects;
/*     */   }
/*     */   
/*     */   public String getResearch()
/*     */   {
/* 159 */     return this.research;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/crafting/ShapelessArcaneRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */