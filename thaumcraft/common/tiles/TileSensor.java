/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.WeakHashMap;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ 
/*     */ 
/*     */ public class TileSensor
/*     */   extends TileEntity
/*     */ {
/*  17 */   public byte note = 0;
/*  18 */   public byte tone = 0;
/*  19 */   public int redstoneSignal = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_145841_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/*  28 */     super.func_145841_b(par1NBTTagCompound);
/*  29 */     par1NBTTagCompound.func_74774_a("note", this.note);
/*  30 */     par1NBTTagCompound.func_74774_a("tone", this.tone);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_145839_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/*  38 */     super.func_145839_a(par1NBTTagCompound);
/*  39 */     this.note = par1NBTTagCompound.func_74771_c("note");
/*  40 */     this.tone = par1NBTTagCompound.func_74771_c("tone");
/*     */     
/*  42 */     if (this.note < 0)
/*     */     {
/*  44 */       this.note = 0;
/*     */     }
/*     */     
/*  47 */     if (this.note > 24)
/*     */     {
/*  49 */       this.note = 24;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_145845_h()
/*     */   {
/*  56 */     super.func_145845_h();
/*  57 */     if (!this.field_145850_b.field_72995_K) {
/*  58 */       if (this.redstoneSignal > 0) {
/*  59 */         this.redstoneSignal -= 1;
/*  60 */         if (this.redstoneSignal == 0)
/*     */         {
/*  62 */           this.field_145850_b.func_147459_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockWoodenDevice);
/*  63 */           this.field_145850_b.func_147459_d(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e, ConfigBlocks.blockWoodenDevice);
/*  64 */           this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */         }
/*     */       }
/*  67 */       ArrayList<Integer[]> nbe = (ArrayList)noteBlockEvents.get(this.field_145850_b);
/*  68 */       if (nbe != null) {
/*  69 */         for (Integer[] dat : nbe) {
/*  70 */           if ((dat[3].intValue() == this.tone) && (dat[4].intValue() == this.note) && 
/*  71 */             (func_145835_a(dat[0].intValue() + 0.5D, dat[1].intValue() + 0.5D, dat[2].intValue() + 0.5D) <= 4096.0D)) {
/*  72 */             triggerNote(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, false);
/*  73 */             this.redstoneSignal = 10;
/*     */             
/*  75 */             this.field_145850_b.func_147459_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockWoodenDevice);
/*  76 */             this.field_145850_b.func_147459_d(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e, ConfigBlocks.blockWoodenDevice);
/*  77 */             this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  78 */             break;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*  86 */   public static WeakHashMap<WorldServer, ArrayList<Integer[]>> noteBlockEvents = new WeakHashMap();
/*     */   
/*     */   public double func_145835_a(double par1, double par3, double par5)
/*     */   {
/*  90 */     double var7 = this.field_145851_c + 0.5D - par1;
/*  91 */     double var9 = this.field_145848_d + 0.5D - par3;
/*  92 */     double var11 = this.field_145849_e + 0.5D - par5;
/*  93 */     return var7 * var7 + var9 * var9 + var11 * var11;
/*     */   }
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/*  98 */     return true;
/*     */   }
/*     */   
/*     */   public void updateTone() {
/* 102 */     Material var5 = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e).func_149688_o();
/* 103 */     this.tone = 0;
/* 104 */     if (var5 == Material.field_151576_e)
/*     */     {
/* 106 */       this.tone = 1;
/*     */     }
/*     */     
/* 109 */     if (var5 == Material.field_151595_p)
/*     */     {
/* 111 */       this.tone = 2;
/*     */     }
/*     */     
/* 114 */     if (var5 == Material.field_151592_s)
/*     */     {
/* 116 */       this.tone = 3;
/*     */     }
/*     */     
/* 119 */     if (var5 == Material.field_151575_d)
/*     */     {
/* 121 */       this.tone = 4;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void changePitch()
/*     */   {
/* 130 */     this.note = ((byte)((this.note + 1) % 25));
/* 131 */     func_70296_d();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void triggerNote(World par1World, int par2, int par3, int par4, boolean sound)
/*     */   {
/* 139 */     if (par1World.func_147439_a(par2, par3 + 1, par4).func_149688_o() == Material.field_151579_a)
/*     */     {
/* 141 */       byte var6 = -1;
/*     */       
/* 143 */       if (sound) {
/* 144 */         Material var5 = par1World.func_147439_a(par2, par3 - 1, par4).func_149688_o();
/* 145 */         var6 = 0;
/* 146 */         if (var5 == Material.field_151576_e)
/*     */         {
/* 148 */           var6 = 1;
/*     */         }
/*     */         
/* 151 */         if (var5 == Material.field_151595_p)
/*     */         {
/* 153 */           var6 = 2;
/*     */         }
/*     */         
/* 156 */         if (var5 == Material.field_151592_s)
/*     */         {
/* 158 */           var6 = 3;
/*     */         }
/*     */         
/* 161 */         if (var5 == Material.field_151575_d)
/*     */         {
/* 163 */           var6 = 4;
/*     */         }
/*     */       }
/*     */       
/* 167 */       par1World.func_147452_c(par2, par3, par4, ConfigBlocks.blockWoodenDevice, var6, this.note);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileSensor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */