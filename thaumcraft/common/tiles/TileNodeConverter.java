/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.nodes.NodeModifier;
/*     */ import thaumcraft.api.nodes.NodeType;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.blocks.BlockAiry;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ 
/*     */ public class TileNodeConverter extends TileThaumcraft
/*     */ {
/*  18 */   public int count = -1;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean canUpdate()
/*     */   {
/*  26 */     return true;
/*     */   }
/*     */   
/*     */   public void func_145845_h()
/*     */   {
/*  31 */     super.func_145845_h();
/*  32 */     if (this.count == -1) { checkStatus();
/*     */     }
/*  34 */     if ((this.status == 1) && (!this.field_145850_b.field_72995_K) && (this.count >= 1000)) {
/*  35 */       TileEntity tile = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
/*  36 */       if ((tile != null) && ((tile instanceof TileNode))) {
/*  37 */         AspectList base = ((TileNode)tile).getAspectsBase();
/*  38 */         NodeType type = ((TileNode)tile).getNodeType();
/*  39 */         NodeModifier mod = ((TileNode)tile).getNodeModifier();
/*  40 */         this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e, ConfigBlocks.blockAiry, 5, 3);
/*  41 */         TileEntity tilenew = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
/*  42 */         if ((tilenew != null) && ((tilenew instanceof TileNodeEnergized))) {
/*  43 */           ((TileNodeEnergized)tilenew).setNodeModifier(mod);
/*  44 */           ((TileNodeEnergized)tilenew).setNodeType(type);
/*  45 */           ((TileNodeEnergized)tilenew).setAspects(base.copy());
/*  46 */           ((TileNodeEnergized)tilenew).setupNode();
/*     */         }
/*  48 */         checkStatus();
/*  49 */         this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, func_145838_q(), 10, 10);
/*  50 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  51 */         func_70296_d();
/*     */       }
/*     */     }
/*     */     
/*  55 */     if ((this.status == 2) && (!this.field_145850_b.field_72995_K) && (this.count <= 50)) {
/*  56 */       TileEntity tile = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
/*  57 */       if ((tile != null) && ((tile instanceof TileNodeEnergized))) {
/*  58 */         AspectList base = ((TileNodeEnergized)tile).getAuraBase();
/*  59 */         NodeType type = ((TileNodeEnergized)tile).getNodeType();
/*  60 */         NodeModifier mod = ((TileNodeEnergized)tile).getNodeModifier();
/*  61 */         this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e, ConfigBlocks.blockAiry, 0, 3);
/*  62 */         TileEntity tilenew = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
/*  63 */         if ((tilenew != null) && ((tilenew instanceof TileNode))) {
/*  64 */           ((TileNode)tilenew).setNodeModifier(mod);
/*  65 */           ((TileNode)tilenew).setNodeType(type);
/*  66 */           ((TileNode)tilenew).setAspects(base.copy());
/*  67 */           for (thaumcraft.api.aspects.Aspect a : ((TileNode)tilenew).getAspects().getAspects()) {
/*  68 */             ((TileNode)tilenew).takeFromContainer(a, ((TileNode)tilenew).getAspects().getAmount(a));
/*     */           }
/*     */         }
/*  71 */         this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, func_145838_q(), 10, 10);
/*  72 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  73 */         func_70296_d();
/*  74 */         this.status = 0;
/*     */       }
/*     */     }
/*     */     
/*  78 */     if ((this.status == 0) || (!this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e))) {
/*  79 */       if (this.count > 0) {
/*  80 */         this.count -= 1;
/*  81 */         if ((this.count > 50) && (this.field_145850_b.field_72995_K)) {
/*  82 */           if (this.field_145850_b.field_73012_v.nextBoolean()) {
/*  83 */             Thaumcraft.proxy.nodeBolt(this.field_145850_b, this.field_145851_c + 0.25F + this.field_145850_b.field_73012_v.nextFloat() * 0.5F, this.field_145848_d + 0.5F, this.field_145849_e + 0.25F + this.field_145850_b.field_73012_v.nextFloat() * 0.5F, this.field_145851_c + 0.5F, this.field_145848_d - 0.5F, this.field_145849_e + 0.5F);
/*     */           }
/*     */           
/*     */ 
/*     */ 
/*  88 */           if ((this.field_145850_b.field_73012_v.nextBoolean()) && (hasStabilizer())) {
/*  89 */             Thaumcraft.proxy.nodeBolt(this.field_145850_b, this.field_145851_c + 0.25F + this.field_145850_b.field_73012_v.nextFloat() * 0.5F, this.field_145848_d - 1.5F, this.field_145849_e + 0.25F + this.field_145850_b.field_73012_v.nextFloat() * 0.5F, this.field_145851_c + 0.5F, this.field_145848_d - 0.5F, this.field_145849_e + 0.5F);
/*     */           }
/*     */           
/*     */         }
/*     */         
/*     */       }
/*     */       
/*     */     }
/*  97 */     else if (this.count < 1000) {
/*  98 */       this.count += 1;
/*  99 */       if (!this.field_145850_b.field_72995_K) {
/* 100 */         TileEntity tilenew = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
/* 101 */         if ((tilenew != null) && ((tilenew instanceof TileNode))) {
/* 102 */           TileNode nd = (TileNode)tilenew;
/* 103 */           AspectList al = nd.getAspects();
/* 104 */           if (al.getAspects().length > 0) {
/* 105 */             nd.takeFromContainer(al.getAspects()[this.field_145850_b.field_73012_v.nextInt(al.getAspects().length)], 1);
/* 106 */             if ((this.count % 5 == 0) || (nd.getAspects().visSize() == 0)) {
/* 107 */               this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 112 */       if ((this.count > 50) && (this.field_145850_b.field_72995_K)) {
/* 113 */         if (this.field_145850_b.field_73012_v.nextBoolean()) {
/* 114 */           Thaumcraft.proxy.nodeBolt(this.field_145850_b, this.field_145851_c + 0.25F + this.field_145850_b.field_73012_v.nextFloat() * 0.5F, this.field_145848_d + 0.5F, this.field_145849_e + 0.25F + this.field_145850_b.field_73012_v.nextFloat() * 0.5F, this.field_145851_c + 0.5F, this.field_145848_d - 0.5F, this.field_145849_e + 0.5F);
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 119 */         if ((this.field_145850_b.field_73012_v.nextBoolean()) && (hasStabilizer())) {
/* 120 */           Thaumcraft.proxy.nodeBolt(this.field_145850_b, this.field_145851_c + 0.25F + this.field_145850_b.field_73012_v.nextFloat() * 0.5F, this.field_145848_d - 1.5F, this.field_145849_e + 0.25F + this.field_145850_b.field_73012_v.nextFloat() * 0.5F, this.field_145851_c + 0.5F, this.field_145848_d - 0.5F, this.field_145849_e + 0.5F);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 128 */     if (this.count > 1000) {
/* 129 */       this.count = 1000;
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean hasStabilizer() {
/* 134 */     TileEntity te = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e);
/* 135 */     if ((this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e)) || (te == null) || (!(te instanceof TileNodeStabilizer)))
/* 136 */       return false;
/* 137 */     return true;
/*     */   }
/*     */   
/* 140 */   public int status = 0;
/*     */   
/* 142 */   public void checkStatus() { if (this.count == -1) this.count = 0;
/* 143 */     if ((this.status == 2) && (this.count > 50) && ((!hasStabilizer()) || (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) != ConfigBlocks.blockAiry) || (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) != 5)))
/*     */     {
/*     */ 
/* 146 */       BlockAiry.explodify(func_145831_w(), this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
/* 147 */       this.status = 0;
/* 148 */       this.count = 50;
/* 149 */       func_70296_d();
/* 150 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/* 152 */     else if ((this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e)) && (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == ConfigBlocks.blockAiry) && (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == 0) && (hasStabilizer()))
/*     */     {
/*     */ 
/*     */ 
/* 156 */       this.status = 1;
/* 157 */       func_70296_d();
/* 158 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/* 160 */     else if ((this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == ConfigBlocks.blockAiry) && (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == 5))
/*     */     {
/* 162 */       this.status = 2;
/* 163 */       this.count = 1000;
/* 164 */       func_70296_d();
/* 165 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 166 */     } else { this.status = 0;
/*     */     }
/*     */   }
/*     */   
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound) {
/* 171 */     super.readCustomNBT(nbttagcompound);
/* 172 */     this.status = nbttagcompound.func_74762_e("status");
/* 173 */     this.count = nbttagcompound.func_74762_e("count");
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 178 */     super.writeCustomNBT(nbttagcompound);
/* 179 */     nbttagcompound.func_74768_a("status", this.status);
/* 180 */     nbttagcompound.func_74768_a("count", this.count);
/*     */   }
/*     */   
/*     */   public boolean func_145842_c(int i, int j)
/*     */   {
/* 185 */     if ((i == 10) && (j == 10)) {
/* 186 */       if (this.field_145850_b.field_72995_K) {
/* 187 */         Thaumcraft.proxy.burst(this.field_145850_b, this.field_145851_c + 0.5D, this.field_145848_d - 0.5D, this.field_145849_e + 0.5D, 1.0F);
/* 188 */         this.field_145850_b.func_72980_b(this.field_145851_c + 0.5D, this.field_145848_d - 0.5D, this.field_145849_e + 0.5D, "thaumcraft:craftfail", 0.5F, 1.0F, false);
/*     */       }
/* 190 */       return true;
/*     */     }
/* 192 */     return super.func_145842_c(i, j);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileNodeConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */