/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.visnet.VisNetHandler;
/*     */ import thaumcraft.api.wands.FocusUpgradeType;
/*     */ import thaumcraft.api.wands.ItemFocusBasic;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ public class TileFocalManipulator extends TileThaumcraftInventory
/*     */ {
/*  20 */   public AspectList aspects = new AspectList();
/*  21 */   public int size = 0;
/*  22 */   public int upgrade = -1;
/*  23 */   public int rank = -1;
/*     */   
/*     */   public TileFocalManipulator()
/*     */   {
/*  27 */     this.itemStacks = new ItemStack[1];
/*  28 */     this.syncedSlots = new int[] { 0 };
/*     */   }
/*     */   
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  33 */     this.itemStacks = new ItemStack[1];
/*  34 */     this.syncedSlots = new int[] { 0 };
/*  35 */     super.readCustomNBT(nbttagcompound);
/*  36 */     this.aspects.readFromNBT(nbttagcompound);
/*  37 */     this.size = nbttagcompound.func_74762_e("size");
/*  38 */     this.upgrade = nbttagcompound.func_74762_e("upgrade");
/*  39 */     this.rank = nbttagcompound.func_74762_e("rank");
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  44 */     super.writeCustomNBT(nbttagcompound);
/*  45 */     this.aspects.writeToNBT(nbttagcompound);
/*  46 */     nbttagcompound.func_74768_a("size", this.size);
/*  47 */     nbttagcompound.func_74768_a("upgrade", this.upgrade);
/*  48 */     nbttagcompound.func_74768_a("rank", this.rank);
/*     */   }
/*     */   
/*     */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox()
/*     */   {
/*  54 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e, this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + 1);
/*     */   }
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/*  59 */     return true;
/*     */   }
/*     */   
/*  62 */   int ticks = 0;
/*     */   
/*  64 */   public boolean reset = false;
/*     */   public static final int XP_MULT = 8;
/*     */   public static final int VIS_MULT = 200;
/*     */   
/*  68 */   public void func_70299_a(int par1, ItemStack par2ItemStack) { super.func_70299_a(par1, par2ItemStack);
/*     */     
/*  70 */     if (this.field_145850_b.field_72995_K) {
/*  71 */       this.reset = true;
/*     */     } else {
/*  73 */       this.aspects = new AspectList();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_145845_h()
/*     */   {
/*  80 */     boolean complete = false;
/*  81 */     if (!this.field_145850_b.field_72995_K)
/*     */     {
/*  83 */       if (this.rank < 0) {
/*  84 */         this.rank = 0;
/*     */       }
/*  86 */       this.ticks += 1;
/*  87 */       if (this.ticks % 5 == 0) {
/*  88 */         if ((this.size > 0) && ((this.aspects.visSize() <= 0) || (func_70301_a(0) == null))) {
/*  89 */           complete = true;
/*  90 */           this.field_145850_b.func_72908_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, "thaumcraft:craftfail", 0.33F, 1.0F);
/*     */         }
/*     */         
/*  93 */         if (this.size > 0) {
/*  94 */           for (Aspect aspect : this.aspects.getAspectsSortedAmount()) {
/*  95 */             int drain = VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, aspect, Math.min(100, this.aspects.getAmount(aspect)));
/*     */             
/*  97 */             if (drain > 0) {
/*  98 */               this.aspects.reduce(aspect, drain);
/*  99 */               this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 100 */               func_70296_d();
/*     */             }
/*     */           }
/* 103 */           if ((this.aspects.visSize() <= 0) && (func_70301_a(0) != null)) {
/* 104 */             complete = true;
/* 105 */             ItemFocusBasic focus = (ItemFocusBasic)func_70301_a(0).func_77973_b();
/* 106 */             boolean b = focus.applyUpgrade(func_70301_a(0), FocusUpgradeType.types[this.upgrade], this.rank);
/* 107 */             this.field_145850_b.func_72908_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, "thaumcraft:wand", 1.0F, 1.0F);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 112 */     else if (this.size > 0) {
/* 113 */       Thaumcraft.proxy.drawGenericParticles(func_145831_w(), this.field_145851_c + 0.5D + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.3F, this.field_145848_d + 1.25D + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.3F, this.field_145849_e + 0.5D + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.3F, 0.0D, 0.0D, 0.0D, 0.5F + func_145831_w().field_73012_v.nextFloat() * 0.4F, 1.0F - func_145831_w().field_73012_v.nextFloat() * 0.4F, 1.0F - func_145831_w().field_73012_v.nextFloat() * 0.4F, 0.8F, false, 112, 9, 1, 6 + this.field_145850_b.field_73012_v.nextInt(5), 0, 0.7F + func_145831_w().field_73012_v.nextFloat() * 0.4F);
/*     */     }
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
/* 127 */     if (complete)
/*     */     {
/* 129 */       this.size = 0;
/* 130 */       this.rank = -1;
/* 131 */       this.aspects = new AspectList();
/* 132 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 133 */       func_70296_d();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean startCraft(int id, EntityPlayer p) {
/* 138 */     if ((this.size > 0) || (func_70301_a(0) == null) || (!(func_70301_a(0).func_77973_b() instanceof ItemFocusBasic))) { return false;
/*     */     }
/* 140 */     ItemFocusBasic focus = (ItemFocusBasic)func_70301_a(0).func_77973_b();
/* 141 */     short[] s = focus.getAppliedUpgrades(func_70301_a(0));
/* 142 */     this.rank = 1;
/* 143 */     while ((this.rank <= 5) && 
/* 144 */       (s[(this.rank - 1)] != -1)) {
/* 143 */       this.rank += 1;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 149 */     int xp = this.rank * 8;
/* 150 */     if (p.field_71068_ca < xp) { return false;
/*     */     }
/* 152 */     FocusUpgradeType[] ut = focus.getPossibleUpgradesByRank(func_70301_a(0), this.rank);
/* 153 */     if (ut == null) return false;
/* 154 */     boolean b = false;
/* 155 */     for (int a = 0; a < ut.length; a++) {
/* 156 */       if (ut[a].id == id) {
/* 157 */         b = true;
/* 158 */         break;
/*     */       }
/*     */     }
/* 161 */     if (!b) { return false;
/*     */     }
/* 163 */     if ((id > FocusUpgradeType.types.length - 1) || (FocusUpgradeType.types[id] == null) || (!focus.canApplyUpgrade(func_70301_a(0), p, FocusUpgradeType.types[id], this.rank))) {
/* 164 */       return false;
/*     */     }
/* 166 */     int amt = 200;
/* 167 */     for (int a = 1; a < this.rank; a++) amt *= 2;
/* 168 */     AspectList tal = new AspectList();
/* 169 */     for (Aspect as : FocusUpgradeType.types[id].aspects.getAspects()) {
/* 170 */       tal.add(as, amt);
/*     */     }
/* 172 */     this.aspects = thaumcraft.common.lib.research.ResearchManager.reduceToPrimals(tal);
/* 173 */     this.size = this.aspects.visSize();
/* 174 */     this.upgrade = id;
/* 175 */     if (!p.field_71075_bZ.field_75098_d) p.func_82242_a(-xp);
/* 176 */     func_70296_d();
/* 177 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 178 */     this.field_145850_b.func_72908_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, "thaumcraft:craftstart", 0.25F, 1.0F);
/* 179 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_94041_b(int par1, ItemStack par2ItemStack)
/*     */   {
/* 188 */     if ((par2ItemStack != null) && ((par2ItemStack.func_77973_b() instanceof ItemFocusBasic)))
/* 189 */       return true;
/* 190 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileFocalManipulator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */