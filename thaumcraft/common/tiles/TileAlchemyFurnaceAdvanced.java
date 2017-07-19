/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.EnumSkyBlock;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.visnet.VisNetHandler;
/*     */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileAlchemyFurnaceAdvanced
/*     */   extends TileThaumcraft
/*     */ {
/*  30 */   public AspectList aspects = new AspectList();
/*     */   public int vis;
/*  32 */   public int maxVis = 500;
/*  33 */   int bellows = -1;
/*  34 */   public int heat = 0;
/*  35 */   public int power1 = 0;
/*  36 */   public int power2 = 0;
/*  37 */   public int maxPower = 500;
/*  38 */   public boolean destroy = false;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox()
/*     */   {
/*  43 */     return AxisAlignedBB.func_72330_a(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e - 1, this.field_145851_c + 2, this.field_145848_d + 2, this.field_145849_e + 2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  50 */     this.vis = nbttagcompound.func_74765_d("vis");
/*  51 */     this.heat = nbttagcompound.func_74765_d("heat");
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  56 */     nbttagcompound.func_74777_a("vis", (short)this.vis);
/*  57 */     nbttagcompound.func_74777_a("heat", (short)this.heat);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_145839_a(NBTTagCompound nbtCompound)
/*     */   {
/*  66 */     super.func_145839_a(nbtCompound);
/*  67 */     this.aspects.readFromNBT(nbtCompound);
/*  68 */     this.power1 = nbtCompound.func_74765_d("power1");
/*  69 */     this.power2 = nbtCompound.func_74765_d("power2");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_145841_b(NBTTagCompound nbtCompound)
/*     */   {
/*  78 */     super.func_145841_b(nbtCompound);
/*  79 */     this.aspects.writeToNBT(nbtCompound);
/*  80 */     nbtCompound.func_74777_a("power1", (short)this.power1);
/*  81 */     nbtCompound.func_74777_a("power2", (short)this.power2);
/*     */   }
/*     */   
/*     */ 
/*     */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
/*     */   {
/*  87 */     super.onDataPacket(net, pkt);
/*  88 */     if (this.field_145850_b != null) {
/*  89 */       this.field_145850_b.func_147463_c(EnumSkyBlock.Block, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/*  95 */     return true;
/*     */   }
/*     */   
/*  98 */   int count = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_145845_h()
/*     */   {
/* 109 */     this.count += 1;
/*     */     
/* 111 */     if (!this.field_145850_b.field_72995_K)
/*     */     {
/* 113 */       if (this.destroy) {
/* 114 */         for (int a = -1; a <= 1; a++) {
/* 115 */           for (int b = 0; b <= 1; b++) {
/* 116 */             for (int c = -1; c <= 1; c++) {
/* 117 */               if (((a != 0) || (b != 0) || (c != 0)) && (this.field_145850_b.func_147439_a(this.field_145851_c + a, this.field_145848_d + b, this.field_145849_e + c) == func_145838_q())) {
/* 118 */                 int m = this.field_145850_b.func_72805_g(this.field_145851_c + a, this.field_145848_d + b, this.field_145849_e + c);
/* 119 */                 this.field_145850_b.func_147465_d(this.field_145851_c + a, this.field_145848_d + b, this.field_145849_e + c, Block.func_149634_a(func_145838_q().func_149650_a(m, this.field_145850_b.field_73012_v, 0)), func_145838_q().func_149692_a(m), 3);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/* 124 */         this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, Block.func_149634_a(func_145838_q().func_149650_a(0, this.field_145850_b.field_73012_v, 0)), func_145838_q().func_149692_a(0), 3);
/*     */         
/* 126 */         return;
/*     */       }
/* 128 */       if (this.processed > 0) this.processed -= 1;
/* 129 */       if (this.count % 5 == 0) {
/* 130 */         int pt = this.heat;
/* 131 */         this.heat -= 1;
/* 132 */         if (this.heat <= this.maxPower) {
/* 133 */           this.heat += VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, Aspect.FIRE, 50);
/*     */         }
/* 135 */         if (this.power1 <= this.maxPower) {
/* 136 */           this.power1 += VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, Aspect.ENTROPY, 50);
/*     */         }
/* 138 */         if (this.power2 <= this.maxPower) {
/* 139 */           this.power2 += VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, Aspect.WATER, 50);
/*     */         }
/* 141 */         if (pt / 50 != this.heat / 50) this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/* 146 */   int processed = 0;
/*     */   
/*     */   public boolean process(ItemStack stack) {
/* 149 */     if ((this.processed == 0) && (canSmelt(stack)))
/*     */     {
/* 151 */       AspectList al = ThaumcraftCraftingManager.getObjectTags(stack);
/* 152 */       al = ThaumcraftCraftingManager.getBonusTags(stack, al);
/* 153 */       int aa = al.visSize();
/* 154 */       if ((aa * 2 > this.heat) || (aa > this.power1) || (aa > this.power2)) return false;
/* 155 */       this.heat -= aa * 2;
/* 156 */       this.power1 -= aa;
/* 157 */       this.power2 -= aa;
/* 158 */       this.processed = ((int)(this.processed + (5.0F + Math.max(0.0F, (1.0F - this.heat / this.maxPower) * 100.0F))));
/* 159 */       this.aspects.add(al);
/* 160 */       this.vis = this.aspects.visSize();
/* 161 */       func_70296_d();
/* 162 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 163 */       return true;
/*     */     }
/* 165 */     return false;
/*     */   }
/*     */   
/*     */   private boolean canSmelt(ItemStack stack)
/*     */   {
/* 170 */     if (stack == null)
/*     */     {
/* 172 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 176 */     AspectList al = ThaumcraftCraftingManager.getObjectTags(stack);
/* 177 */     al = ThaumcraftCraftingManager.getBonusTags(stack, al);
/* 178 */     if ((al == null) || (al.size() == 0)) return false;
/* 179 */     int vs = al.visSize();
/* 180 */     if (vs + this.aspects.visSize() > this.maxVis) return false;
/* 181 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileAlchemyFurnaceAdvanced.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */