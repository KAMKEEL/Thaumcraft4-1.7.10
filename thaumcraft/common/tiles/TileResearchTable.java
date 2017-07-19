/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.IScribeTools;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.research.ResearchItem;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.items.ItemResearchNotes;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.playerdata.PacketAspectPool;
/*     */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ import thaumcraft.common.lib.research.ResearchManager.HexEntry;
/*     */ import thaumcraft.common.lib.research.ResearchNoteData;
/*     */ import thaumcraft.common.lib.utils.HexUtils.Hex;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ 
/*     */ public class TileResearchTable extends TileThaumcraft implements IInventory
/*     */ {
/*  40 */   public ItemStack[] contents = new ItemStack[2];
/*     */   
/*  42 */   public AspectList bonusAspects = new AspectList();
/*     */   
/*  44 */   int nextRecalc = 0;
/*     */   
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  50 */     NBTTagList var2 = nbttagcompound.func_150295_c("Inventory", 10);
/*  51 */     this.contents = new ItemStack[func_70302_i_()];
/*  52 */     for (int var3 = 0; var3 < Math.min(2, var2.func_74745_c()); var3++)
/*     */     {
/*  54 */       NBTTagCompound var4 = var2.func_150305_b(var3);
/*  55 */       int var5 = var4.func_74771_c("Slot") & 0xFF;
/*     */       
/*  57 */       if ((var5 >= 0) && (var5 < this.contents.length))
/*     */       {
/*  59 */         this.contents[var5] = ItemStack.func_77949_a(var4);
/*     */       }
/*     */     }
/*  62 */     this.nextRecalc = nbttagcompound.func_74762_e("nextRecalc");
/*  63 */     this.bonusAspects = new AspectList();
/*  64 */     var2 = nbttagcompound.func_150295_c("bonusAspects", 10);
/*  65 */     for (int var3 = 0; var3 < var2.func_74745_c(); var3++)
/*     */     {
/*  67 */       NBTTagCompound var4 = var2.func_150305_b(var3);
/*  68 */       String var5 = var4.func_74779_i("tag");
/*  69 */       if (Aspect.getAspect(var5) != null) {
/*  70 */         this.bonusAspects.merge(Aspect.getAspect(var5), 1);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  77 */     NBTTagList var2 = new NBTTagList();
/*  78 */     for (int var3 = 0; var3 < this.contents.length; var3++)
/*     */     {
/*  80 */       if (this.contents[var3] != null)
/*     */       {
/*  82 */         NBTTagCompound var4 = new NBTTagCompound();
/*  83 */         var4.func_74774_a("Slot", (byte)var3);
/*  84 */         this.contents[var3].func_77955_b(var4);
/*  85 */         var2.func_74742_a(var4);
/*     */       }
/*     */     }
/*  88 */     nbttagcompound.func_74782_a("Inventory", var2);
/*     */     
/*  90 */     nbttagcompound.func_74768_a("nextRecalc", this.nextRecalc);
/*  91 */     var2 = new NBTTagList();
/*  92 */     for (Aspect aspect : this.bonusAspects.getAspects())
/*     */     {
/*  94 */       if ((aspect != null) && (this.bonusAspects.getAmount(aspect) > 0))
/*     */       {
/*  96 */         NBTTagCompound var4 = new NBTTagCompound();
/*  97 */         var4.func_74778_a("tag", aspect.getTag());
/*  98 */         var2.func_74742_a(var4);
/*     */       }
/*     */     }
/* 101 */     nbttagcompound.func_74782_a("bonusAspects", var2);
/*     */   }
/*     */   
/*     */   public void func_145845_h()
/*     */   {
/* 106 */     super.func_145845_h();
/* 107 */     if ((!this.field_145850_b.field_72995_K) && 
/* 108 */       (this.nextRecalc++ > 600)) {
/* 109 */       this.nextRecalc = 0;
/* 110 */       recalculateBonus();
/* 111 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 112 */       func_70296_d();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean canUpdate()
/*     */   {
/* 120 */     return true;
/*     */   }
/*     */   
/*     */   public void func_70296_d()
/*     */   {
/* 125 */     super.func_70296_d();
/*     */     
/* 127 */     gatherResults();
/*     */   }
/*     */   
/*     */ 
/* 131 */   EntityPlayer researcher = null;
/*     */   
/* 133 */   public ResearchNoteData data = null;
/*     */   
/* 135 */   public void gatherResults() { this.data = null;
/* 136 */     if ((this.contents[1] != null) && ((this.contents[1].func_77973_b() instanceof ItemResearchNotes))) {
/* 137 */       this.data = ResearchManager.getData(this.contents[1]);
/*     */     }
/*     */   }
/*     */   
/*     */   public void placeAspect(int q, int r, Aspect aspect, EntityPlayer player) {
/* 142 */     if (this.data == null) gatherResults();
/* 143 */     if (!ResearchManager.consumeInkFromTable(this.contents[0], false)) {
/* 144 */       return;
/*     */     }
/* 146 */     if ((this.contents[1] != null) && ((this.contents[1].func_77973_b() instanceof ItemResearchNotes)) && (this.data != null) && (this.contents[1].func_77960_j() < 64))
/*     */     {
/*     */ 
/* 149 */       boolean r1 = ResearchManager.isResearchComplete(player.func_70005_c_(), "RESEARCHER1");
/* 150 */       boolean r2 = ResearchManager.isResearchComplete(player.func_70005_c_(), "RESEARCHER2");
/*     */       
/* 152 */       HexUtils.Hex hex = new HexUtils.Hex(q, r);
/* 153 */       ResearchManager.HexEntry he = null;
/* 154 */       if (aspect != null) {
/* 155 */         he = new ResearchManager.HexEntry(aspect, 2);
/* 156 */         if ((r2) && (this.field_145850_b.field_73012_v.nextFloat() < 0.1F)) {
/* 157 */           this.field_145850_b.func_72956_a(player, "random.orb", 0.2F, 0.9F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F);
/*     */ 
/*     */         }
/* 160 */         else if (Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.func_70005_c_(), aspect) <= 0) {
/* 161 */           this.bonusAspects.remove(aspect, 1);
/* 162 */           player.field_70170_p.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 163 */           func_70296_d();
/*     */         } else {
/* 165 */           Thaumcraft.proxy.playerKnowledge.addAspectPool(player.func_70005_c_(), aspect, (short)-1);
/* 166 */           ResearchManager.scheduleSave(player);
/* 167 */           PacketHandler.INSTANCE.sendTo(new PacketAspectPool(aspect.getTag(), Short.valueOf((short)0), Short.valueOf(Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.func_70005_c_(), aspect))), (EntityPlayerMP)player);
/*     */         }
/*     */         
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 174 */         float f = this.field_145850_b.field_73012_v.nextFloat();
/* 175 */         if ((((ResearchManager.HexEntry)this.data.hexEntries.get(hex.toString())).aspect != null) && (((r1) && (f < 0.25F)) || ((r2) && (f < 0.5F))))
/*     */         {
/* 177 */           this.field_145850_b.func_72956_a(player, "random.orb", 0.2F, 0.9F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F);
/*     */           
/* 179 */           Thaumcraft.proxy.playerKnowledge.addAspectPool(player.func_70005_c_(), ((ResearchManager.HexEntry)this.data.hexEntries.get(hex.toString())).aspect, (short)1);
/* 180 */           ResearchManager.scheduleSave(player);
/* 181 */           PacketHandler.INSTANCE.sendTo(new PacketAspectPool(((ResearchManager.HexEntry)this.data.hexEntries.get(hex.toString())).aspect.getTag(), Short.valueOf((short)0), Short.valueOf(Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.func_70005_c_(), ((ResearchManager.HexEntry)this.data.hexEntries.get(hex.toString())).aspect))), (EntityPlayerMP)player);
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 186 */         he = new ResearchManager.HexEntry(null, 0);
/*     */       }
/* 188 */       this.data.hexEntries.put(hex.toString(), he);
/* 189 */       this.data.hexes.put(hex.toString(), hex);
/*     */       
/* 191 */       ResearchManager.updateData(this.contents[1], this.data);
/* 192 */       ResearchManager.consumeInkFromTable(this.contents[0], true);
/*     */       
/* 194 */       if ((!this.field_145850_b.field_72995_K) && (ResearchManager.checkResearchCompletion(this.contents[1], this.data, player.func_70005_c_()))) {
/* 195 */         this.contents[1].func_77964_b(64);
/* 196 */         this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockTable, 1, 1);
/*     */       }
/*     */     }
/*     */     
/* 200 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 201 */     func_70296_d();
/*     */   }
/*     */   
/*     */   private void recalculateBonus()
/*     */   {
/* 206 */     if ((!this.field_145850_b.func_72935_r()) && (this.field_145850_b.func_72957_l(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) < 4) && (!this.field_145850_b.func_72937_j(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e)))
/*     */     {
/*     */ 
/* 209 */       if (this.field_145850_b.field_73012_v.nextInt(20) == 0) this.bonusAspects.merge(Aspect.ENTROPY, 1);
/*     */     }
/* 211 */     if ((this.field_145848_d > this.field_145850_b.func_72940_L() * 0.5F) && (this.field_145850_b.field_73012_v.nextInt(20) == 0)) this.bonusAspects.merge(Aspect.AIR, 1);
/* 212 */     if ((this.field_145848_d > this.field_145850_b.func_72940_L() * 0.66F) && (this.field_145850_b.field_73012_v.nextInt(20) == 0)) this.bonusAspects.merge(Aspect.AIR, 1);
/* 213 */     if ((this.field_145848_d > this.field_145850_b.func_72940_L() * 0.75F) && (this.field_145850_b.field_73012_v.nextInt(20) == 0)) { this.bonusAspects.merge(Aspect.AIR, 1);
/*     */     }
/* 215 */     for (int x = -8; x <= 8; x++) {
/* 216 */       for (int z = -8; z <= 8; z++) {
/* 217 */         for (int y = -8; y <= 8; y++)
/* 218 */           if ((y + this.field_145848_d > 0) && (y + this.field_145848_d < this.field_145850_b.func_72940_L())) {
/* 219 */             Block bi = this.field_145850_b.func_147439_a(x + this.field_145851_c, y + this.field_145848_d, z + this.field_145849_e);
/* 220 */             int md = this.field_145850_b.func_72805_g(x + this.field_145851_c, y + this.field_145848_d, z + this.field_145849_e);
/* 221 */             Material bm = bi.func_149688_o();
/*     */             
/* 223 */             if ((bi == ConfigBlocks.blockCustomOre) && (md == 1)) {
/* 224 */               if ((this.bonusAspects.getAmount(Aspect.AIR) < 1) && (this.field_145850_b.field_73012_v.nextInt(20) == 0)) {
/* 225 */                 this.bonusAspects.merge(Aspect.AIR, 1);
/*     */               }
/*     */               
/*     */             }
/* 229 */             else if ((bi == ConfigBlocks.blockCrystal) && (md == 0)) {
/* 230 */               if ((this.bonusAspects.getAmount(Aspect.AIR) < 1) && (this.field_145850_b.field_73012_v.nextInt(10) == 0)) {
/* 231 */                 this.bonusAspects.merge(Aspect.AIR, 1);
/*     */               }
/*     */               
/*     */             }
/* 235 */             else if ((bm == Material.field_151581_o) || (bm == Material.field_151587_i) || ((bi == ConfigBlocks.blockCustomOre) && (md == 2))) {
/* 236 */               if ((this.bonusAspects.getAmount(Aspect.FIRE) < 1) && (this.field_145850_b.field_73012_v.nextInt(20) == 0)) {
/* 237 */                 this.bonusAspects.merge(Aspect.FIRE, 1);
/*     */               }
/*     */               
/*     */             }
/* 241 */             else if ((bi == ConfigBlocks.blockCrystal) && (md == 1)) {
/* 242 */               if ((this.bonusAspects.getAmount(Aspect.FIRE) < 1) && (this.field_145850_b.field_73012_v.nextInt(10) == 0)) {
/* 243 */                 this.bonusAspects.merge(Aspect.FIRE, 1);
/*     */               }
/*     */               
/*     */ 
/*     */             }
/* 248 */             else if (bm == Material.field_151578_c) {
/* 249 */               if ((this.bonusAspects.getAmount(Aspect.EARTH) < 1) && (this.field_145850_b.field_73012_v.nextInt(20) == 0)) {
/* 250 */                 this.bonusAspects.merge(Aspect.EARTH, 1);
/*     */               }
/*     */               
/*     */ 
/*     */             }
/* 255 */             else if ((bi == ConfigBlocks.blockCustomOre) && (md == 4)) {
/* 256 */               if ((this.bonusAspects.getAmount(Aspect.EARTH) < 1) && (this.field_145850_b.field_73012_v.nextInt(20) == 0)) {
/* 257 */                 this.bonusAspects.merge(Aspect.EARTH, 1);
/*     */               }
/*     */               
/*     */ 
/*     */             }
/* 262 */             else if ((bi == ConfigBlocks.blockCrystal) && (md == 3)) {
/* 263 */               if ((this.bonusAspects.getAmount(Aspect.EARTH) < 1) && (this.field_145850_b.field_73012_v.nextInt(10) == 0)) {
/* 264 */                 this.bonusAspects.merge(Aspect.EARTH, 1);
/*     */               }
/*     */               
/*     */ 
/*     */             }
/* 269 */             else if (bm == Material.field_151586_h) {
/* 270 */               if ((this.bonusAspects.getAmount(Aspect.WATER) < 1) && (this.field_145850_b.field_73012_v.nextInt(15) == 0)) {
/* 271 */                 this.bonusAspects.merge(Aspect.WATER, 1);
/*     */               }
/*     */               
/*     */ 
/*     */             }
/* 276 */             else if ((bi == ConfigBlocks.blockCustomOre) && (md == 3)) {
/* 277 */               if ((this.bonusAspects.getAmount(Aspect.WATER) < 1) && (this.field_145850_b.field_73012_v.nextInt(20) == 0)) {
/* 278 */                 this.bonusAspects.merge(Aspect.WATER, 1);
/*     */               }
/*     */               
/*     */ 
/*     */             }
/* 283 */             else if ((bi == ConfigBlocks.blockCrystal) && (md == 2)) {
/* 284 */               if ((this.bonusAspects.getAmount(Aspect.WATER) < 1) && (this.field_145850_b.field_73012_v.nextInt(10) == 0)) {
/* 285 */                 this.bonusAspects.merge(Aspect.WATER, 1);
/*     */               }
/*     */               
/*     */ 
/*     */             }
/* 290 */             else if ((bm == Material.field_151594_q) || (bm == Material.field_76233_E)) {
/* 291 */               if ((this.bonusAspects.getAmount(Aspect.ORDER) < 1) && (this.field_145850_b.field_73012_v.nextInt(20) == 0)) {
/* 292 */                 this.bonusAspects.merge(Aspect.ORDER, 1);
/*     */               }
/*     */               
/*     */ 
/*     */             }
/* 297 */             else if ((bi == ConfigBlocks.blockCustomOre) && (md == 5)) {
/* 298 */               if ((this.bonusAspects.getAmount(Aspect.ORDER) < 1) && (this.field_145850_b.field_73012_v.nextInt(20) == 0)) {
/* 299 */                 this.bonusAspects.merge(Aspect.ORDER, 1);
/*     */               }
/*     */               
/*     */ 
/*     */             }
/* 304 */             else if ((bi == ConfigBlocks.blockCrystal) && (md == 4)) {
/* 305 */               if ((this.bonusAspects.getAmount(Aspect.ORDER) < 1) && (this.field_145850_b.field_73012_v.nextInt(10) == 0)) {
/* 306 */                 this.bonusAspects.merge(Aspect.ORDER, 1);
/*     */               }
/*     */               
/*     */ 
/*     */             }
/* 311 */             else if ((bi == ConfigBlocks.blockCustomOre) && (md == 6)) {
/* 312 */               if ((this.bonusAspects.getAmount(Aspect.ENTROPY) < 1) && (this.field_145850_b.field_73012_v.nextInt(20) == 0)) {
/* 313 */                 this.bonusAspects.merge(Aspect.ENTROPY, 1);
/*     */               }
/*     */               
/*     */ 
/*     */             }
/* 318 */             else if ((bi == ConfigBlocks.blockCrystal) && (md == 5) && 
/* 319 */               (this.bonusAspects.getAmount(Aspect.ENTROPY) < 1) && (this.field_145850_b.field_73012_v.nextInt(10) == 0)) {
/* 320 */               this.bonusAspects.merge(Aspect.ENTROPY, 1);
/* 321 */               return;
/*     */             }
/*     */             
/*     */ 
/*     */ 
/* 326 */             if (((bi == net.minecraft.init.Blocks.field_150342_X) && (this.field_145850_b.field_73012_v.nextInt(300) == 0)) || ((bi == ConfigBlocks.blockJar) && (md == 1) && (this.field_145850_b.field_73012_v.nextInt(200) == 0)))
/*     */             {
/* 328 */               Aspect[] aspects = new Aspect[0];
/* 329 */               aspects = (Aspect[])Aspect.aspects.values().toArray(aspects);
/* 330 */               this.bonusAspects.merge(aspects[this.field_145850_b.field_73012_v.nextInt(aspects.length)], 1);
/* 331 */               return;
/*     */             }
/*     */           }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public int func_70302_i_() {
/* 339 */     return 2;
/*     */   }
/*     */   
/*     */   public ItemStack func_70301_a(int var1)
/*     */   {
/* 344 */     return this.contents[var1];
/*     */   }
/*     */   
/*     */   public ItemStack func_70298_a(int var1, int var2)
/*     */   {
/* 349 */     if (this.contents[var1] != null)
/*     */     {
/*     */ 
/*     */ 
/* 353 */       if (this.contents[var1].field_77994_a <= var2)
/*     */       {
/* 355 */         ItemStack var3 = this.contents[var1];
/* 356 */         this.contents[var1] = null;
/* 357 */         func_70296_d();
/* 358 */         return var3;
/*     */       }
/*     */       
/*     */ 
/* 362 */       ItemStack var3 = this.contents[var1].func_77979_a(var2);
/*     */       
/* 364 */       if (this.contents[var1].field_77994_a == 0)
/*     */       {
/* 366 */         this.contents[var1] = null;
/*     */       }
/*     */       
/* 369 */       func_70296_d();
/* 370 */       return var3;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 375 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_70304_b(int var1)
/*     */   {
/* 381 */     if (this.contents[var1] != null)
/*     */     {
/* 383 */       ItemStack var2 = this.contents[var1];
/* 384 */       this.contents[var1] = null;
/* 385 */       return var2;
/*     */     }
/*     */     
/*     */ 
/* 389 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70299_a(int var1, ItemStack var2)
/*     */   {
/* 395 */     this.contents[var1] = var2;
/*     */     
/* 397 */     if ((var2 != null) && (var2.field_77994_a > func_70297_j_()))
/*     */     {
/* 399 */       var2.field_77994_a = func_70297_j_();
/*     */     }
/*     */     
/* 402 */     func_70296_d();
/*     */   }
/*     */   
/*     */   public String func_145825_b()
/*     */   {
/* 407 */     return "Research Table";
/*     */   }
/*     */   
/*     */   public int func_70297_j_()
/*     */   {
/* 412 */     return 64;
/*     */   }
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer var1)
/*     */   {
/* 417 */     return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70295_k_() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70305_f() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_145818_k_()
/*     */   {
/* 432 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack itemstack)
/*     */   {
/* 437 */     if (itemstack == null) return false;
/* 438 */     switch (i) {
/* 439 */     case 0:  if ((itemstack.func_77973_b() instanceof IScribeTools)) return true;
/*     */       break; case 1:  if ((itemstack.func_77973_b() == thaumcraft.common.config.ConfigItems.itemResearchNotes) && (itemstack.func_77960_j() < 64)) return true;
/*     */       break; }
/* 442 */     return false;
/*     */   }
/*     */   
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox()
/*     */   {
/* 448 */     return AxisAlignedBB.func_72330_a(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e - 1, this.field_145851_c + 2, this.field_145848_d + 2, this.field_145849_e + 2);
/*     */   }
/*     */   
/*     */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
/*     */   {
/* 453 */     super.onDataPacket(net, pkt);
/* 454 */     if ((this.field_145850_b != null) && (this.field_145850_b.field_72995_K)) {
/* 455 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_145842_c(int i, int j)
/*     */   {
/* 462 */     if (i == 1)
/*     */     {
/* 464 */       if (this.field_145850_b.field_72995_K) {
/* 465 */         this.field_145850_b.func_72980_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, "thaumcraft:learn", 1.0F, 1.0F, false);
/*     */       }
/* 467 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 471 */     return super.func_145842_c(i, j);
/*     */   }
/*     */   
/*     */   public void duplicate(EntityPlayer player) {
/* 475 */     if (this.data == null) gatherResults();
/* 476 */     if ((this.contents[1] != null) && ((this.contents[1].func_77973_b() instanceof ItemResearchNotes)) && (this.data != null) && (this.contents[1].func_77960_j() == 64) && (InventoryUtils.isPlayerCarrying(player, new ItemStack(Items.field_151121_aF)) >= 0) && (InventoryUtils.isPlayerCarrying(player, new ItemStack(Items.field_151100_aR, 1, 0)) >= 0))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 481 */       ResearchItem rr = thaumcraft.api.research.ResearchCategories.getResearch(this.data.key);
/* 482 */       for (Aspect aspect : rr.tags.getAspects()) {
/* 483 */         if (Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.func_70005_c_(), aspect) < rr.tags.getAmount(aspect) + this.data.copies) {
/* 484 */           return;
/*     */         }
/*     */       }
/*     */       
/* 488 */       for (Aspect aspect : rr.tags.getAspects()) {
/* 489 */         Thaumcraft.proxy.playerKnowledge.addAspectPool(player.func_70005_c_(), aspect, (short)-(rr.tags.getAmount(aspect) + this.data.copies));
/* 490 */         ResearchManager.scheduleSave(player);
/* 491 */         PacketHandler.INSTANCE.sendTo(new PacketAspectPool(aspect.getTag(), Short.valueOf((short)0), Short.valueOf(Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.func_70005_c_(), aspect))), (EntityPlayerMP)player);
/*     */       }
/*     */       
/*     */ 
/* 495 */       InventoryUtils.consumeInventoryItem(player, Items.field_151121_aF, 0);
/* 496 */       InventoryUtils.consumeInventoryItem(player, Items.field_151100_aR, 0);
/* 497 */       this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockTable, 1, 1);
/* 498 */       this.data.copies += 1;
/* 499 */       ResearchManager.updateData(this.contents[1], this.data);
/* 500 */       this.contents[1].field_77994_a += 1;
/* 501 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 502 */       func_70296_d();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileResearchTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */