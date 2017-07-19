/*     */ package thaumcraft.common.lib.crafting;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagByte;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.IRunicArmor;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.crafting.InfusionRecipe;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.lib.events.EventHandlerRunic;
/*     */ 
/*     */ 
/*     */ public class InfusionRunicAugmentRecipe
/*     */   extends InfusionRecipe
/*     */ {
/*     */   private ItemStack[] components;
/*     */   
/*     */   public InfusionRunicAugmentRecipe()
/*     */   {
/*  25 */     super("RUNICAUGMENTATION", null, 0, null, null, new ItemStack[] { new ItemStack(Items.field_151045_i), new ItemStack(ConfigItems.itemResource, 1, 14) });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public InfusionRunicAugmentRecipe(ItemStack in)
/*     */   {
/*  35 */     super("RUNICAUGMENTATION", null, 0, null, in, new ItemStack[] { new ItemStack(Items.field_151045_i), new ItemStack(ConfigItems.itemResource, 1, 14) });
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  40 */     this.components = new ItemStack[] { new ItemStack(Items.field_151045_i), new ItemStack(ConfigItems.itemResource, 1, 14) };
/*     */     
/*     */ 
/*     */ 
/*  44 */     int fc = EventHandlerRunic.getFinalCharge(in);
/*  45 */     if (fc > 0) {
/*  46 */       ArrayList<ItemStack> com = new ArrayList();
/*  47 */       com.add(new ItemStack(Items.field_151045_i));
/*  48 */       com.add(new ItemStack(ConfigItems.itemResource, 1, 14));
/*  49 */       int c = 0;
/*  50 */       while (c < fc) {
/*  51 */         c++;
/*  52 */         com.add(new ItemStack(ConfigItems.itemResource, 1, 14));
/*     */       }
/*  54 */       this.components = ((ItemStack[])com.toArray(this.components));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean matches(ArrayList<ItemStack> input, ItemStack central, World world, EntityPlayer player)
/*     */   {
/*  64 */     if ((this.research.length() > 0) && (!ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), this.research))) {
/*  65 */       return false;
/*     */     }
/*     */     
/*  68 */     if (!(central.func_77973_b() instanceof IRunicArmor)) {
/*  69 */       return false;
/*     */     }
/*     */     
/*  72 */     ItemStack i2 = null;
/*     */     
/*  74 */     ArrayList<ItemStack> ii = new ArrayList();
/*  75 */     for (ItemStack is : input) {
/*  76 */       ii.add(is.func_77946_l());
/*     */     }
/*     */     
/*  79 */     for (ItemStack comp : getComponents(central)) {
/*  80 */       boolean b = false;
/*  81 */       for (int a = 0; a < ii.size(); a++) {
/*  82 */         i2 = ((ItemStack)ii.get(a)).func_77946_l();
/*  83 */         if (comp.func_77960_j() == 32767) {
/*  84 */           i2.func_77964_b(32767);
/*     */         }
/*  86 */         if (areItemStacksEqual(i2, comp, true)) {
/*  87 */           ii.remove(a);
/*  88 */           b = true;
/*  89 */           break;
/*     */         }
/*     */       }
/*  92 */       if (!b) return false;
/*     */     }
/*  94 */     return ii.size() == 0;
/*     */   }
/*     */   
/*     */   public Object getRecipeOutput(ItemStack input)
/*     */   {
/*  99 */     if (input == null) return null;
/* 100 */     ItemStack out = input.func_77946_l();
/* 101 */     int base = EventHandlerRunic.getHardening(input) + 1;
/* 102 */     out.func_77983_a("RS.HARDEN", new NBTTagByte((byte)base));
/* 103 */     return out;
/*     */   }
/*     */   
/*     */   public AspectList getAspects(ItemStack input)
/*     */   {
/* 108 */     AspectList out = new AspectList();
/* 109 */     int vis = (int)(32.0D * Math.pow(2.0D, EventHandlerRunic.getFinalCharge(input)));
/* 110 */     if (vis > 0) {
/* 111 */       out.add(Aspect.ARMOR, vis / 2);
/* 112 */       out.add(Aspect.MAGIC, vis / 2);
/* 113 */       out.add(Aspect.ENERGY, vis);
/*     */     }
/* 115 */     return out;
/*     */   }
/*     */   
/*     */   public int getInstability(ItemStack input)
/*     */   {
/* 120 */     int i = 5 + EventHandlerRunic.getFinalCharge(input) / 2;
/* 121 */     return i;
/*     */   }
/*     */   
/*     */   public ItemStack[] getComponents(ItemStack input) {
/* 125 */     ArrayList<ItemStack> com = new ArrayList();
/* 126 */     com.add(new ItemStack(Items.field_151045_i));
/* 127 */     com.add(new ItemStack(ConfigItems.itemResource, 1, 14));
/* 128 */     int fc = EventHandlerRunic.getFinalCharge(input);
/* 129 */     if (fc > 0) {
/* 130 */       for (int c = 0; c < fc; c++) {
/* 131 */         com.add(new ItemStack(ConfigItems.itemResource, 1, 14));
/*     */       }
/*     */     }
/* 134 */     return (ItemStack[])com.toArray(new ItemStack[0]);
/*     */   }
/*     */   
/*     */   public ItemStack[] getComponents()
/*     */   {
/* 139 */     return this.components;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/crafting/InfusionRunicAugmentRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */