/*     */ package thaumcraft.common.entities.ai.interact;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.ai.EntityLookHelper;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.server.management.ItemInWorldManager;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.common.util.FakePlayer;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ import thaumcraft.common.entities.golems.GolemHelper;
/*     */ import thaumcraft.common.entities.golems.Marker;
/*     */ 
/*     */ public class AIUseItem extends net.minecraft.entity.ai.EntityAIBase
/*     */ {
/*     */   private EntityGolemBase theGolem;
/*     */   private int xx;
/*     */   private int yy;
/*     */   private int zz;
/*     */   private float movementSpeed;
/*     */   private float distance;
/*     */   private World theWorld;
/*  34 */   private Block block = net.minecraft.init.Blocks.field_150350_a;
/*  35 */   private int blockMd = 0;
/*     */   FakePlayer player;
/*  37 */   private int count = 0;
/*  38 */   private int color = -1;
/*     */   ItemInWorldManager im;
/*     */   
/*     */   public AIUseItem(EntityGolemBase par1EntityCreature)
/*     */   {
/*  43 */     this.theGolem = par1EntityCreature;
/*  44 */     this.theWorld = par1EntityCreature.field_70170_p;
/*  45 */     func_75248_a(3);
/*  46 */     this.distance = MathHelper.func_76123_f(this.theGolem.getRange() / 3.0F);
/*  47 */     if ((this.theWorld instanceof WorldServer))
/*  48 */       this.player = net.minecraftforge.common.util.FakePlayerFactory.get((WorldServer)this.theWorld, new com.mojang.authlib.GameProfile((java.util.UUID)null, "FakeThaumcraftGolem"));
/*  49 */     try { this.nextTick = (this.theGolem.field_70173_aa + this.theWorld.field_73012_v.nextInt(6));
/*     */     } catch (Exception e) {} }
/*     */   
/*  52 */   int nextTick = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  59 */     boolean ignoreItem = false;
/*  60 */     ChunkCoordinates home = this.theGolem.func_110172_bL();
/*  61 */     ForgeDirection facing = ForgeDirection.getOrientation(this.theGolem.homeFacing);
/*  62 */     int cX = home.field_71574_a - facing.offsetX;
/*  63 */     int cY = home.field_71572_b - facing.offsetY;
/*  64 */     int cZ = home.field_71573_c - facing.offsetZ;
/*  65 */     TileEntity tile = this.theGolem.field_70170_p.func_147438_o(cX, cY, cZ);
/*  66 */     if ((tile == null) || (!(tile instanceof IInventory))) {
/*  67 */       ignoreItem = true;
/*     */     }
/*     */     
/*  70 */     int d = 5 - this.theGolem.field_70173_aa;
/*  71 */     if (d < 1) { d = 1;
/*     */     }
/*  73 */     if (((this.theGolem.itemCarried == null) && (!ignoreItem)) || (this.theGolem.field_70173_aa < this.nextTick) || (!this.theGolem.func_70661_as().func_75500_f()))
/*     */     {
/*     */ 
/*  76 */       return false;
/*     */     }
/*     */     
/*  79 */     this.nextTick = (this.theGolem.field_70173_aa + d * 3);
/*  80 */     return findSomething();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  88 */     return (this.theWorld.func_147439_a(this.xx, this.yy, this.zz) == this.block) && (this.theWorld.func_72805_g(this.xx, this.yy, this.zz) == this.blockMd) && (this.count-- > 0) && (!this.theGolem.func_70661_as().func_75500_f());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_75246_d()
/*     */   {
/*  95 */     this.theGolem.func_70671_ap().func_75650_a(this.xx + 0.5D, this.yy + 0.5D, this.zz + 0.5D, 30.0F, 30.0F);
/*  96 */     double dist = this.theGolem.func_70092_e(this.xx + 0.5D, this.yy + 0.5D, this.zz + 0.5D);
/*  97 */     if (dist <= 4.0D)
/*     */     {
/*  99 */       click();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/* 107 */     this.count = 0;
/* 108 */     this.theGolem.func_70661_as().func_75499_g();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/* 116 */     this.count = 200;
/* 117 */     this.theGolem.func_70661_as().func_75492_a(this.xx + 0.5D, this.yy + 0.5D, this.zz + 0.5D, this.theGolem.func_70689_ay());
/*     */   }
/*     */   
/*     */   void click() {
/* 121 */     ChunkCoordinates home = this.theGolem.func_110172_bL();
/* 122 */     boolean ignoreItem = false;
/* 123 */     ForgeDirection facing = ForgeDirection.getOrientation(this.theGolem.homeFacing);
/* 124 */     int cX = home.field_71574_a - facing.offsetX;
/* 125 */     int cY = home.field_71572_b - facing.offsetY;
/* 126 */     int cZ = home.field_71573_c - facing.offsetZ;
/* 127 */     TileEntity tile = this.theGolem.field_70170_p.func_147438_o(cX, cY, cZ);
/* 128 */     if ((tile == null) || (!(tile instanceof IInventory))) {
/* 129 */       ignoreItem = true;
/*     */     }
/*     */     
/* 132 */     this.player.func_70080_a(this.theGolem.field_70165_t, this.theGolem.field_70163_u, this.theGolem.field_70161_v, this.theGolem.field_70177_z, this.theGolem.field_70125_A);
/* 133 */     this.player.func_70062_b(0, this.theGolem.itemCarried);
/* 134 */     this.player.func_70095_a(this.theGolem.getToggles()[2]);
/*     */     
/* 136 */     Iterator i$ = GolemHelper.getMarkedSides(this.theGolem, this.xx, this.yy, this.zz, this.theGolem.field_70170_p.field_73011_w.field_76574_g, (byte)this.color).iterator(); if (i$.hasNext()) { Integer side = (Integer)i$.next();
/*     */       
/* 138 */       int x = 0;
/* 139 */       int y = 0;
/* 140 */       int z = 0;
/* 141 */       if (this.theGolem.field_70170_p.func_147437_c(this.xx, this.yy, this.zz)) {
/* 142 */         x = ForgeDirection.getOrientation(side.intValue()).getOpposite().offsetX;
/* 143 */         y = ForgeDirection.getOrientation(side.intValue()).getOpposite().offsetY;
/* 144 */         z = ForgeDirection.getOrientation(side.intValue()).getOpposite().offsetZ;
/*     */       }
/* 146 */       if (this.im == null) {
/* 147 */         this.im = new ItemInWorldManager(this.theGolem.field_70170_p);
/*     */       }
/* 149 */       if ((this.theGolem.itemCarried == null) && (!ignoreItem)) {
/* 150 */         func_75251_c();
/* 151 */         return;
/*     */       }
/*     */       try {
/* 154 */         if (this.theGolem.getToggles()[1] != 0) {
/* 155 */           this.theGolem.startLeftArmTimer();
/* 156 */           this.im.func_73074_a(this.xx + x, this.yy + y, this.zz + z, side.intValue());
/*     */         }
/* 158 */         else if (this.im.func_73078_a(this.player, this.theGolem.field_70170_p, this.theGolem.itemCarried, this.xx + x, this.yy + y, this.zz + z, side.intValue(), 0.5F, 0.5F, 0.5F))
/*     */         {
/* 160 */           this.theGolem.startRightArmTimer();
/*     */         }
/*     */         
/* 163 */         this.theGolem.itemCarried = this.player.func_71045_bC();
/* 164 */         if (this.theGolem.itemCarried.field_77994_a <= 0) this.theGolem.itemCarried = null;
/* 165 */         for (int a = 1; a < this.player.field_71071_by.field_70462_a.length; a++) {
/* 166 */           if (this.player.field_71071_by.func_70301_a(a) != null) {
/* 167 */             if (this.theGolem.itemCarried == null) {
/* 168 */               this.theGolem.itemCarried = this.player.field_71071_by.func_70301_a(a).func_77946_l();
/*     */             } else {
/* 170 */               this.player.func_71019_a(this.player.field_71071_by.func_70301_a(a), false);
/*     */             }
/* 172 */             this.player.field_71071_by.func_70299_a(a, null);
/*     */           }
/*     */         }
/* 175 */         this.theGolem.updateCarried();
/*     */         
/* 177 */         func_75251_c();
/* 178 */         return;
/*     */       }
/*     */       catch (Exception e) {
/* 181 */         func_75251_c();
/* 182 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   boolean findSomething()
/*     */   {
/* 190 */     ArrayList<Byte> matchingColors = this.theGolem.getColorsMatching(this.theGolem.itemCarried);
/*     */     
/* 192 */     for (Iterator i$ = matchingColors.iterator(); i$.hasNext();) { col = ((Byte)i$.next()).byteValue();
/* 193 */       ArrayList<Marker> markers = this.theGolem.getMarkers();
/*     */       
/* 195 */       for (Marker marker : markers) {
/* 196 */         if ((marker.color == col) || (col == -1))
/*     */         {
/* 198 */           if (((this.theGolem.getToggles()[0] == 0) || (this.theGolem.field_70170_p.func_147437_c(marker.x, marker.y, marker.z))) && (
/*     */           
/*     */ 
/* 201 */             (this.theGolem.getToggles()[0] != 0) || (!this.theGolem.field_70170_p.func_147437_c(marker.x, marker.y, marker.z))))
/*     */           {
/*     */ 
/* 204 */             ForgeDirection opp = ForgeDirection.getOrientation(marker.side);
/*     */             
/* 206 */             if (this.theGolem.field_70170_p.func_147437_c(marker.x + opp.offsetX, marker.y + opp.offsetY, marker.z + opp.offsetZ))
/*     */             {
/*     */ 
/* 209 */               this.color = col;
/* 210 */               this.xx = marker.x;
/* 211 */               this.yy = marker.y;
/* 212 */               this.zz = marker.z;
/* 213 */               this.block = this.theWorld.func_147439_a(this.xx, this.yy, this.zz);
/* 214 */               this.blockMd = this.theWorld.func_72805_g(this.xx, this.yy, this.zz);
/* 215 */               return true;
/*     */             }
/*     */           } }
/*     */       }
/*     */     }
/*     */     byte col;
/* 221 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/interact/AIUseItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */