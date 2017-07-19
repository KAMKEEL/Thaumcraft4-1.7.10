/*     */ package thaumcraft.common.entities.ai.inventory;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.RandomPositionGenerator;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ import thaumcraft.common.entities.golems.GolemHelper;
/*     */ 
/*     */ public class AISortingGoto extends EntityAIBase
/*     */ {
/*     */   private EntityGolemBase theGolem;
/*     */   private double movePosX;
/*     */   private double movePosY;
/*     */   private double movePosZ;
/*  24 */   private ChunkCoordinates dest = null;
/*     */   
/*     */   public AISortingGoto(EntityGolemBase par1EntityCreature)
/*     */   {
/*  28 */     this.theGolem = par1EntityCreature;
/*  29 */     func_75248_a(3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  37 */     if ((this.theGolem.itemCarried == null) || (this.theGolem.field_70173_aa % Config.golemDelay > 0)) {
/*  38 */       return false;
/*     */     }
/*     */     
/*  41 */     ArrayList<IInventory> results = GolemHelper.getContainersWithRoom(this.theGolem.field_70170_p, this.theGolem, (byte)-1);
/*     */     
/*  43 */     if (results.size() == 0) {
/*  44 */       return false;
/*     */     }
/*     */     
/*  47 */     ForgeDirection facing = ForgeDirection.getOrientation(this.theGolem.homeFacing);
/*  48 */     ChunkCoordinates home = this.theGolem.func_110172_bL();
/*  49 */     int cX = home.field_71574_a - facing.offsetX;
/*  50 */     int cY = home.field_71572_b - facing.offsetY;
/*  51 */     int cZ = home.field_71573_c - facing.offsetZ;
/*     */     
/*  53 */     int tX = 0;
/*  54 */     int tY = 0;
/*  55 */     int tZ = 0;
/*  56 */     double range = Double.MAX_VALUE;
/*  57 */     float dmod = this.theGolem.getRange();
/*     */     
/*  59 */     for (Iterator i$ = results.iterator(); i$.hasNext();) { te = (IInventory)i$.next();
/*  60 */       distance = this.theGolem.func_70092_e(((TileEntity)te).field_145851_c + 0.5D, ((TileEntity)te).field_145848_d + 0.5D, ((TileEntity)te).field_145849_e + 0.5D);
/*  61 */       if ((distance < range) && (distance <= dmod * dmod) && ((((TileEntity)te).field_145851_c != cX) || (((TileEntity)te).field_145848_d != cY) || (((TileEntity)te).field_145849_e != cZ)))
/*     */       {
/*  63 */         for (i$ = GolemHelper.getMarkedSides(this.theGolem, (TileEntity)te, (byte)-1).iterator(); i$.hasNext();) { int side = ((Integer)i$.next()).intValue();
/*  64 */           if (thaumcraft.common.lib.utils.InventoryUtils.inventoryContains(te, this.theGolem.itemCarried, side, this.theGolem.checkOreDict(), this.theGolem.ignoreDamage(), this.theGolem.ignoreNBT())) {
/*  65 */             tX = ((TileEntity)te).field_145851_c;
/*  66 */             tY = ((TileEntity)te).field_145848_d;
/*  67 */             tZ = ((TileEntity)te).field_145849_e;
/*  68 */             this.dest = new ChunkCoordinates(tX, tY, tZ);
/*  69 */             range = distance;
/*  70 */             break;
/*     */           }
/*     */         } } }
/*     */     IInventory te;
/*     */     double distance;
/*     */     Iterator i$;
/*  76 */     if (this.dest != null) {
/*  77 */       this.movePosX = tX;
/*  78 */       this.movePosY = tY;
/*  79 */       this.movePosZ = tZ;
/*  80 */       return true;
/*     */     }
/*     */     
/*  83 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  92 */     return (this.count > 0) && (!this.theGolem.func_70661_as().func_75500_f());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*  97 */   int count = 0;
/*  98 */   int prevX = 0;
/*  99 */   int prevY = 0;
/* 100 */   int prevZ = 0;
/*     */   
/*     */   public void func_75246_d()
/*     */   {
/* 104 */     this.count -= 1;
/* 105 */     if ((this.count == 0) && (this.prevX == MathHelper.func_76128_c(this.theGolem.field_70165_t)) && (this.prevY == MathHelper.func_76128_c(this.theGolem.field_70163_u)) && (this.prevZ == MathHelper.func_76128_c(this.theGolem.field_70161_v))) {
/* 106 */       Vec3 var2 = RandomPositionGenerator.func_75463_a(this.theGolem, 2, 1);
/*     */       
/* 108 */       if (var2 != null)
/*     */       {
/* 110 */         this.count = 20;
/* 111 */         this.theGolem.func_70661_as().func_75492_a(var2.field_72450_a, var2.field_72448_b, var2.field_72449_c, this.theGolem.func_70689_ay());
/*     */       }
/*     */     }
/*     */     
/* 115 */     super.func_75246_d();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/* 123 */     this.count = 0;
/* 124 */     this.dest = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/* 132 */     this.count = 200;
/* 133 */     this.prevX = MathHelper.func_76128_c(this.theGolem.field_70165_t);
/* 134 */     this.prevY = MathHelper.func_76128_c(this.theGolem.field_70163_u);
/* 135 */     this.prevZ = MathHelper.func_76128_c(this.theGolem.field_70161_v);
/* 136 */     this.theGolem.func_70661_as().func_75492_a(this.movePosX, this.movePosY, this.movePosZ, this.theGolem.func_70689_ay());
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/inventory/AISortingGoto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */