/*     */ package thaumcraft.common.lib.world.dim;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.LongHashMap;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.Teleporter;
/*     */ import net.minecraft.world.Teleporter.PortalPosition;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TeleporterThaumcraft
/*     */   extends Teleporter
/*     */ {
/*     */   private final WorldServer worldServerInstance;
/*     */   private final Random random;
/*  25 */   private static final LongHashMap destinationCoordinateCache = new LongHashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  31 */   private static final List destinationCoordinateKeys = new ArrayList();
/*     */   
/*     */   public TeleporterThaumcraft(WorldServer par1WorldServer)
/*     */   {
/*  35 */     super(par1WorldServer);
/*  36 */     this.worldServerInstance = par1WorldServer;
/*  37 */     this.random = new Random(par1WorldServer.func_72905_C());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_77185_a(Entity par1Entity, double par2, double par4, double par6, float par8)
/*     */   {
/*  46 */     if (this.worldServerInstance.field_73011_w.field_76574_g != 1)
/*     */     {
/*  48 */       if (!func_77184_b(par1Entity, par2, par4, par6, par8))
/*     */       {
/*  50 */         func_85188_a(par1Entity);
/*  51 */         func_77184_b(par1Entity, par2, par4, par6, par8);
/*     */       }
/*     */       
/*     */ 
/*     */     }
/*  56 */     else if (!func_77184_b(par1Entity, par2, par4, par6, par8))
/*     */     {
/*  58 */       int i = MathHelper.func_76128_c(par1Entity.field_70165_t);
/*  59 */       int k = MathHelper.func_76128_c(par1Entity.field_70161_v);
/*  60 */       int j = this.worldServerInstance.func_72976_f(i, k);
/*  61 */       byte b0 = 1;
/*  62 */       byte b1 = 0;
/*  63 */       par1Entity.func_70012_b(i, j + 4.0D, k, par1Entity.field_70177_z, 0.0F);
/*  64 */       par1Entity.field_70159_w = (par1Entity.field_70181_x = par1Entity.field_70179_y = 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_77184_b(Entity par1Entity, double par2, double par4, double par6, float par8)
/*     */   {
/*  76 */     short short1 = 128;
/*  77 */     double d3 = -1.0D;
/*  78 */     int i = 0;
/*  79 */     int j = 0;
/*  80 */     int k = 0;
/*  81 */     int l = MathHelper.func_76128_c(par1Entity.field_70165_t);
/*  82 */     int i1 = MathHelper.func_76128_c(par1Entity.field_70161_v);
/*  83 */     int chunkX = l >> 4;
/*  84 */     int chunkZ = i1 >> 4;
/*  85 */     String hs = chunkX + ":" + chunkZ + ":" + this.worldServerInstance.field_73011_w.field_76574_g;
/*  86 */     long j1 = hs.hashCode();
/*  87 */     boolean flag = true;
/*     */     
/*     */ 
/*     */ 
/*  91 */     if (destinationCoordinateCache.func_76161_b(j1))
/*     */     {
/*  93 */       Teleporter.PortalPosition portalposition = (Teleporter.PortalPosition)destinationCoordinateCache.func_76164_a(j1);
/*  94 */       d3 = 0.0D;
/*  95 */       i = portalposition.field_71574_a;
/*  96 */       j = portalposition.field_71572_b;
/*  97 */       k = portalposition.field_71573_c;
/*  98 */       portalposition.field_85087_d = this.worldServerInstance.func_82737_E();
/*  99 */       flag = false;
/*     */     }
/*     */     else
/*     */     {
/* 103 */       for (int k1 = l - short1; k1 <= l + short1; k1++)
/*     */       {
/* 105 */         double d5 = k1 + 0.5D - par1Entity.field_70165_t;
/*     */         
/* 107 */         for (int l1 = i1 - short1; l1 <= i1 + short1; l1++)
/*     */         {
/* 109 */           double d6 = l1 + 0.5D - par1Entity.field_70161_v;
/*     */           
/* 111 */           for (int i2 = this.worldServerInstance.func_72940_L() - 1; i2 >= 0; i2--)
/*     */           {
/* 113 */             if (this.worldServerInstance.func_147439_a(k1, i2, l1) == ConfigBlocks.blockEldritchPortal)
/*     */             {
/*     */ 
/* 116 */               double d4 = i2 + 0.5D - par1Entity.field_70163_u;
/* 117 */               double d7 = d5 * d5 + d4 * d4 + d6 * d6;
/*     */               
/* 119 */               if ((d3 < 0.0D) || (d7 < d3))
/*     */               {
/* 121 */                 d3 = d7;
/* 122 */                 i = k1;
/* 123 */                 j = i2;
/* 124 */                 k = l1;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 132 */     if (d3 >= 0.0D)
/*     */     {
/* 134 */       if (flag)
/*     */       {
/* 136 */         destinationCoordinateCache.func_76163_a(j1, new Teleporter.PortalPosition(this, i, j, k, this.worldServerInstance.func_82737_E()));
/*     */         
/*     */ 
/* 139 */         destinationCoordinateKeys.add(Long.valueOf(j1));
/*     */       }
/*     */       
/* 142 */       double d8 = i + 0.5D + (this.worldServerInstance.field_73012_v.nextBoolean() ? 1 : -1);
/* 143 */       double d9 = j;
/* 144 */       double d4 = k + 0.5D + (this.worldServerInstance.field_73012_v.nextBoolean() ? 1 : -1);
/*     */       
/* 146 */       par1Entity.field_70159_w = (par1Entity.field_70181_x = par1Entity.field_70179_y = 0.0D);
/*     */       
/* 148 */       par1Entity.func_70012_b(d8, d9, d4, par1Entity.field_70177_z, par1Entity.field_70125_A);
/* 149 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 153 */     return false;
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
/*     */   public boolean func_85188_a(Entity par1Entity)
/*     */   {
/* 173 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_85189_a(long par1)
/*     */   {
/* 183 */     if (par1 % 100L == 0L)
/*     */     {
/* 185 */       Iterator iterator = destinationCoordinateKeys.iterator();
/* 186 */       long j = par1 - 600L;
/*     */       
/* 188 */       while (iterator.hasNext())
/*     */       {
/* 190 */         Long olong = (Long)iterator.next();
/* 191 */         Teleporter.PortalPosition portalposition = (Teleporter.PortalPosition)destinationCoordinateCache.func_76164_a(olong.longValue());
/*     */         
/* 193 */         if ((portalposition == null) || (portalposition.field_85087_d < j))
/*     */         {
/* 195 */           iterator.remove();
/* 196 */           destinationCoordinateCache.func_76159_d(olong.longValue());
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/dim/TeleporterThaumcraft.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */