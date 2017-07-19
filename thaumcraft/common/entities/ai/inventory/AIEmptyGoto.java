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
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ import thaumcraft.common.entities.golems.Marker;
/*     */ 
/*     */ public class AIEmptyGoto extends EntityAIBase
/*     */ {
/*     */   private EntityGolemBase theGolem;
/*     */   private double movePosX;
/*     */   private double movePosY;
/*     */   private double movePosZ;
/*  24 */   private ChunkCoordinates dest = null;
/*     */   
/*     */   public AIEmptyGoto(EntityGolemBase par1EntityCreature)
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
/*  37 */     if ((this.theGolem.itemCarried == null) || (this.theGolem.field_70173_aa % thaumcraft.common.config.Config.golemDelay > 0)) {
/*  38 */       return false;
/*     */     }
/*     */     
/*  41 */     ArrayList<Byte> matchingColors = this.theGolem.getColorsMatching(this.theGolem.itemCarried);
/*  42 */     for (Iterator i$ = matchingColors.iterator(); i$.hasNext();) { byte color = ((Byte)i$.next()).byteValue();
/*  43 */       ArrayList<IInventory> results = thaumcraft.common.entities.golems.GolemHelper.getContainersWithRoom(this.theGolem.field_70170_p, this.theGolem, color);
/*     */       
/*  45 */       if (results.size() != 0)
/*     */       {
/*     */ 
/*     */ 
/*  49 */         ForgeDirection facing = ForgeDirection.getOrientation(this.theGolem.homeFacing);
/*  50 */         ChunkCoordinates home = this.theGolem.func_110172_bL();
/*  51 */         int cX = home.field_71574_a - facing.offsetX;
/*  52 */         int cY = home.field_71572_b - facing.offsetY;
/*  53 */         int cZ = home.field_71573_c - facing.offsetZ;
/*     */         
/*  55 */         int tX = 0;
/*  56 */         int tY = 0;
/*  57 */         int tZ = 0;
/*  58 */         double range = Double.MAX_VALUE;
/*  59 */         float dmod = this.theGolem.getRange();
/*  60 */         for (IInventory te : results) {
/*  61 */           double distance = this.theGolem.func_70092_e(((TileEntity)te).field_145851_c + 0.5D, ((TileEntity)te).field_145848_d + 0.5D, ((TileEntity)te).field_145849_e + 0.5D);
/*  62 */           if ((distance < range) && (distance <= dmod * dmod) && ((((TileEntity)te).field_145851_c != cX) || (((TileEntity)te).field_145848_d != cY) || (((TileEntity)te).field_145849_e != cZ))) {
/*  63 */             range = distance;
/*  64 */             tX = ((TileEntity)te).field_145851_c;
/*  65 */             tY = ((TileEntity)te).field_145848_d;
/*  66 */             tZ = ((TileEntity)te).field_145849_e;
/*  67 */             this.dest = new ChunkCoordinates(tX, tY, tZ);
/*     */           }
/*     */         }
/*     */         
/*  71 */         if (this.dest != null) {
/*  72 */           this.movePosX = tX;
/*  73 */           this.movePosY = tY;
/*  74 */           this.movePosZ = tZ;
/*  75 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  80 */     for (Iterator i$ = matchingColors.iterator(); i$.hasNext();) { color = ((Byte)i$.next()).byteValue();
/*  81 */       ArrayList<Marker> markers = this.theGolem.getMarkers();
/*  82 */       for (Marker marker : markers) {
/*  83 */         if (((marker.color == color) || (color == -1)) && ((this.theGolem.field_70170_p.func_147438_o(marker.x, marker.y, marker.z) == null) || (!(this.theGolem.field_70170_p.func_147438_o(marker.x, marker.y, marker.z) instanceof IInventory))))
/*     */         {
/*     */ 
/*     */ 
/*  87 */           this.movePosX = marker.x;
/*  88 */           this.movePosY = marker.y;
/*  89 */           this.movePosZ = marker.z;
/*  90 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     byte color;
/*  96 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/* 105 */     return (this.count > 0) && (!this.theGolem.func_70661_as().func_75500_f());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 110 */   int count = 0;
/* 111 */   int prevX = 0;
/* 112 */   int prevY = 0;
/* 113 */   int prevZ = 0;
/*     */   
/*     */   public void func_75246_d()
/*     */   {
/* 117 */     this.count -= 1;
/* 118 */     if ((this.count == 0) && (this.prevX == MathHelper.func_76128_c(this.theGolem.field_70165_t)) && (this.prevY == MathHelper.func_76128_c(this.theGolem.field_70163_u)) && (this.prevZ == MathHelper.func_76128_c(this.theGolem.field_70161_v))) {
/* 119 */       Vec3 var2 = RandomPositionGenerator.func_75463_a(this.theGolem, 2, 1);
/*     */       
/* 121 */       if (var2 != null)
/*     */       {
/* 123 */         this.count = 20;
/* 124 */         this.theGolem.func_70661_as().func_75492_a(var2.field_72450_a, var2.field_72448_b, var2.field_72449_c, this.theGolem.func_70689_ay());
/*     */       }
/*     */     }
/*     */     
/* 128 */     super.func_75246_d();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/* 136 */     this.count = 0;
/* 137 */     this.dest = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/* 145 */     this.count = 200;
/* 146 */     this.prevX = MathHelper.func_76128_c(this.theGolem.field_70165_t);
/* 147 */     this.prevY = MathHelper.func_76128_c(this.theGolem.field_70163_u);
/* 148 */     this.prevZ = MathHelper.func_76128_c(this.theGolem.field_70161_v);
/* 149 */     this.theGolem.func_70661_as().func_75492_a(this.movePosX, this.movePosY, this.movePosZ, this.theGolem.func_70689_ay());
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/inventory/AIEmptyGoto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */