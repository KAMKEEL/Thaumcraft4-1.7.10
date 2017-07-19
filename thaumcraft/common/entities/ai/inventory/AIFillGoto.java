/*     */ package thaumcraft.common.entities.ai.inventory;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.RandomPositionGenerator;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ import thaumcraft.common.entities.golems.GolemHelper;
/*     */ 
/*     */ public class AIFillGoto extends EntityAIBase
/*     */ {
/*     */   private EntityGolemBase theGolem;
/*     */   private double movePosX;
/*     */   private double movePosY;
/*     */   private double movePosZ;
/*  25 */   private ChunkCoordinates dest = null;
/*     */   
/*     */   public AIFillGoto(EntityGolemBase par1EntityCreature)
/*     */   {
/*  29 */     this.theGolem = par1EntityCreature;
/*  30 */     func_75248_a(3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  38 */     if ((this.theGolem.getCarried() != null) || (this.theGolem.field_70173_aa % thaumcraft.common.config.Config.golemDelay > 0) || (!this.theGolem.hasSomething()))
/*     */     {
/*  40 */       return false;
/*     */     }
/*     */     
/*  43 */     ArrayList<ItemStack> mi = GolemHelper.getMissingItems(this.theGolem);
/*  44 */     if ((mi == null) || (mi.size() == 0)) {
/*  45 */       return false;
/*     */     }
/*     */     
/*  48 */     ArrayList<ItemStack> missingItems = new ArrayList();
/*     */     
/*     */ 
/*  51 */     if (this.theGolem.getUpgradeAmount(5) > 0) {
/*  52 */       for (ItemStack stack : mi) {
/*  53 */         int od = OreDictionary.getOreID(stack);
/*  54 */         if (od != -1) {
/*  55 */           ItemStack[] ores = (ItemStack[])OreDictionary.getOres(Integer.valueOf(od)).toArray(new ItemStack[0]);
/*  56 */           for (ItemStack ore : ores) {
/*  57 */             missingItems.add(ore.func_77946_l());
/*     */           }
/*     */         } else {
/*  60 */           missingItems.add(stack.func_77946_l());
/*     */         }
/*     */       }
/*     */     } else {
/*  64 */       for (ItemStack stack : mi) {
/*  65 */         missingItems.add(stack.func_77946_l());
/*     */       }
/*     */     }
/*     */     
/*  69 */     ArrayList<IInventory> results = new ArrayList();
/*     */     
/*  71 */     for (ItemStack stack : missingItems) {
/*  72 */       this.theGolem.itemWatched = stack.func_77946_l();
/*  73 */       ArrayList<Byte> matchingColors = this.theGolem.getColorsMatching(this.theGolem.itemWatched);
/*  74 */       for (Iterator i$ = matchingColors.iterator(); i$.hasNext();) { byte color = ((Byte)i$.next()).byteValue();
/*  75 */         results = GolemHelper.getContainersWithGoods(this.theGolem.field_70170_p, this.theGolem, this.theGolem.itemWatched, color);
/*     */       }
/*     */       
/*     */ 
/*  79 */       if (results.size() > 0) {
/*     */         break;
/*     */       }
/*     */     }
/*     */     
/*  84 */     if ((results == null) || (results.size() == 0)) {
/*  85 */       return false;
/*     */     }
/*     */     
/*  88 */     ForgeDirection facing = ForgeDirection.getOrientation(this.theGolem.homeFacing);
/*  89 */     ChunkCoordinates home = this.theGolem.func_110172_bL();
/*  90 */     int cX = home.field_71574_a - facing.offsetX;
/*  91 */     int cY = home.field_71572_b - facing.offsetY;
/*  92 */     int cZ = home.field_71573_c - facing.offsetZ;
/*     */     
/*  94 */     int tX = 0;
/*  95 */     int tY = 0;
/*  96 */     int tZ = 0;
/*  97 */     double range = Double.MAX_VALUE;
/*  98 */     float dmod = this.theGolem.getRange();
/*  99 */     for (IInventory i : results) {
/* 100 */       TileEntity te = (TileEntity)i;
/* 101 */       double distance = this.theGolem.func_70092_e(te.field_145851_c + 0.5D, te.field_145848_d + 0.5D, te.field_145849_e + 0.5D);
/* 102 */       if ((distance < range) && (distance <= dmod * dmod) && ((te.field_145851_c != cX) || (te.field_145848_d != cY) || (te.field_145849_e != cZ))) {
/* 103 */         range = distance;
/* 104 */         tX = te.field_145851_c;
/* 105 */         tY = te.field_145848_d;
/* 106 */         tZ = te.field_145849_e;
/* 107 */         this.dest = new ChunkCoordinates(tX, tY, tZ);
/*     */       }
/*     */     }
/*     */     
/* 111 */     if (this.dest != null) {
/* 112 */       this.movePosX = tX;
/* 113 */       this.movePosY = tY;
/* 114 */       this.movePosZ = tZ;
/* 115 */       return true;
/*     */     }
/* 117 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/* 126 */     return (this.count > 0) && (!this.theGolem.func_70661_as().func_75500_f());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 131 */   int count = 0;
/* 132 */   int prevX = 0;
/* 133 */   int prevY = 0;
/* 134 */   int prevZ = 0;
/*     */   
/*     */   public void func_75246_d()
/*     */   {
/* 138 */     this.count -= 1;
/* 139 */     if ((this.count == 0) && (this.prevX == MathHelper.func_76128_c(this.theGolem.field_70165_t)) && (this.prevY == MathHelper.func_76128_c(this.theGolem.field_70163_u)) && (this.prevZ == MathHelper.func_76128_c(this.theGolem.field_70161_v))) {
/* 140 */       Vec3 var2 = RandomPositionGenerator.func_75463_a(this.theGolem, 2, 1);
/*     */       
/* 142 */       if (var2 != null)
/*     */       {
/* 144 */         this.count = 20;
/* 145 */         this.theGolem.func_70661_as().func_75492_a(var2.field_72450_a, var2.field_72448_b, var2.field_72449_c, this.theGolem.func_70689_ay());
/*     */       }
/*     */     }
/* 148 */     super.func_75246_d();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/* 156 */     this.dest = null;
/* 157 */     this.count = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/* 165 */     this.count = 200;
/* 166 */     this.prevX = MathHelper.func_76128_c(this.theGolem.field_70165_t);
/* 167 */     this.prevY = MathHelper.func_76128_c(this.theGolem.field_70163_u);
/* 168 */     this.prevZ = MathHelper.func_76128_c(this.theGolem.field_70161_v);
/* 169 */     this.theGolem.func_70661_as().func_75492_a(this.movePosX, this.movePosY, this.movePosZ, this.theGolem.func_70689_ay());
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/inventory/AIFillGoto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */