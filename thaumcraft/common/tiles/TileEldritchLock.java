/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.entities.monster.EntityTaintacle;
/*     */ import thaumcraft.common.entities.monster.boss.EntityCultistPortal;
/*     */ import thaumcraft.common.entities.monster.boss.EntityEldritchGolem;
/*     */ import thaumcraft.common.entities.monster.boss.EntityEldritchWarden;
/*     */ import thaumcraft.common.entities.monster.boss.EntityTaintacleGiant;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXBlockSparkle;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
/*     */ import thaumcraft.common.lib.world.dim.Cell;
/*     */ import thaumcraft.common.lib.world.dim.CellLoc;
/*     */ import thaumcraft.common.lib.world.dim.GenCommon;
/*     */ import thaumcraft.common.lib.world.dim.MapBossData;
/*     */ import thaumcraft.common.lib.world.dim.MazeHandler;
/*     */ 
/*     */ public class TileEldritchLock extends TileThaumcraft
/*     */ {
/*     */   public boolean canUpdate()
/*     */   {
/*  38 */     return true;
/*     */   }
/*     */   
/*  41 */   public int count = -1;
/*     */   
/*     */ 
/*     */   public void func_145845_h()
/*     */   {
/*  46 */     super.func_145845_h();
/*  47 */     if (this.count != -1) {
/*  48 */       this.count += 1;
/*  49 */       if (this.count % 5 == 0) {
/*  50 */         this.field_145850_b.func_72908_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, "thaumcraft:pump", 1.0F, 1.0F);
/*     */       }
/*  52 */       if (this.count > 100)
/*     */       {
/*  54 */         doBossSpawn();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void doBossSpawn()
/*     */   {
/*  62 */     this.field_145850_b.func_72908_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, "thaumcraft:ice", 1.0F, 1.0F);
/*  63 */     if (!this.field_145850_b.field_72995_K)
/*     */     {
/*  65 */       int cx = this.field_145851_c >> 4;
/*  66 */       int cz = this.field_145849_e >> 4;
/*  67 */       int centerx = this.field_145851_c >> 4;
/*  68 */       int centerz = this.field_145849_e >> 4;
/*  69 */       int exit = 0;
/*  70 */       for (int a = -2; a <= 2; a++) {
/*  71 */         for (int b = -2; b <= 2; b++) {
/*  72 */           Cell c = MazeHandler.getFromHashMap(new CellLoc(cx + a, cz + b));
/*  73 */           if ((c != null) && (c.feature == 2)) {
/*  74 */             centerx = cx + a;
/*  75 */             centerz = cz + b;
/*     */           }
/*  77 */           if ((c != null) && (c.feature >= 2) && (c.feature <= 5) && ((c.north) || (c.south) || (c.east) || (c.west))) {
/*  78 */             exit = c.feature;
/*     */           }
/*     */         }
/*     */       }
/*  82 */       MapBossData mbd = (MapBossData)this.field_145850_b.func_72943_a(MapBossData.class, "BossMapData");
/*  83 */       if (mbd == null) {
/*  84 */         mbd = new MapBossData("BossMapData");
/*  85 */         mbd.bossCount = 0;
/*  86 */         mbd.func_76185_a();
/*  87 */         this.field_145850_b.func_72823_a("BossMapData", mbd);
/*     */       }
/*     */       
/*  90 */       mbd.bossCount += 1;
/*  91 */       if (this.field_145850_b.field_73012_v.nextFloat() < 0.25F) mbd.bossCount += 1;
/*  92 */       mbd.func_76185_a();
/*     */       
/*  94 */       switch (mbd.bossCount % 4) {
/*  95 */       case 0:  spawnGolemBossRoom(centerx, centerz, exit); break;
/*  96 */       case 1:  spawnWardenBossRoom(centerx, centerz, exit); break;
/*  97 */       case 2:  spawnCultistBossRoom(centerx, centerz, exit); break;
/*  98 */       case 3:  spawnTaintBossRoom(centerx, centerz, exit);
/*     */       }
/*     */       
/* 101 */       for (int a = -2; a <= 2; a++) {
/* 102 */         for (int b = -2; b <= 2; b++)
/* 103 */           for (int c = -2; c <= 2; c++)
/* 104 */             if (this.field_145850_b.func_147439_a(this.field_145851_c + a, this.field_145848_d + b, this.field_145849_e + c) == ConfigBlocks.blockAiry) {
/* 105 */               thaumcraft.common.lib.network.PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockSparkle(this.field_145851_c + a, this.field_145848_d + b, this.field_145849_e + c, 4194368), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.field_76574_g, this.field_145851_c + a, this.field_145848_d + b, this.field_145849_e + c, 32.0D));
/*     */               
/*     */ 
/* 108 */               this.field_145850_b.func_147468_f(this.field_145851_c + a, this.field_145848_d + b, this.field_145849_e + c);
/*     */             }
/*     */       }
/* 111 */       this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 121 */   int[][] ped = { { 2, 2, 2 }, { 0, -1, 1 }, { 3, 3, 3 } };
/*     */   
/*     */   private void spawnWardenBossRoom(int cx, int cz, int exit) {
/* 124 */     for (int i = 0; i < this.field_145850_b.field_73010_i.size(); i++)
/*     */     {
/* 126 */       EntityPlayer ep = (EntityPlayer)this.field_145850_b.field_73010_i.get(i);
/* 127 */       if (ep.func_70092_e(this.field_145851_c, this.field_145848_d, this.field_145849_e) < 300.0D) {
/* 128 */         ep.func_145747_a(new ChatComponentText(StatCollector.func_74838_a("tc.boss.warden")));
/*     */       }
/*     */     }
/* 131 */     int x = cx * 16 + 16;
/* 132 */     int y = 50;
/* 133 */     int z = cz * 16 + 16;
/* 134 */     int x2 = x;
/* 135 */     int z2 = z;
/* 136 */     switch (exit) {
/* 137 */     case 2:  x2 += 8;z2 += 8; break;
/* 138 */     case 3:  x2 -= 8;z2 += 8; break;
/* 139 */     case 4:  x2 += 8;z2 -= 8; break;
/* 140 */     case 5:  x2 -= 8;z2 -= 8;
/*     */     }
/*     */     
/* 143 */     GenCommon.genObelisk(this.field_145850_b, x2, y + 4, z);
/* 144 */     GenCommon.genObelisk(this.field_145850_b, x, y + 4, z2);
/* 145 */     this.field_145850_b.func_147465_d(x2, y + 2, z, ConfigBlocks.blockEldritch, 3, 3);
/* 146 */     this.field_145850_b.func_147465_d(x, y + 2, z2, ConfigBlocks.blockEldritch, 3, 3);
/*     */     
/* 148 */     for (int a = -1; a <= 1; a++) {
/* 149 */       for (int b = -1; b <= 1; b++) {
/* 150 */         if ((a != 0) && (b != 0) && (this.field_145850_b.field_73012_v.nextFloat() < 0.9F)) {
/* 151 */           float rr = this.field_145850_b.field_73012_v.nextFloat();
/* 152 */           int md = rr < 0.3F ? 1 : rr < 0.1F ? 2 : 0;
/* 153 */           this.field_145850_b.func_147465_d(x2 + a, y + 2, z + b, ConfigBlocks.blockLootUrn, md, 3);
/*     */         }
/* 155 */         if ((a != 0) && (b != 0) && (this.field_145850_b.field_73012_v.nextFloat() < 0.9F)) {
/* 156 */           float rr = this.field_145850_b.field_73012_v.nextFloat();
/* 157 */           int md = rr < 0.3F ? 1 : rr < 0.1F ? 2 : 0;
/* 158 */           this.field_145850_b.func_147465_d(x + a, y + 2, z2 + b, ConfigBlocks.blockLootUrn, md, 3);
/*     */         }
/*     */       }
/*     */     }
/* 162 */     this.field_145850_b.func_147465_d(x - 2, y + 3, z - 2, ConfigBlocks.blockEldritch, 10, 3);
/* 163 */     this.field_145850_b.func_147465_d(x - 2, y + 3, z + 2, ConfigBlocks.blockEldritch, 10, 3);
/* 164 */     this.field_145850_b.func_147465_d(x + 2, y + 3, z + 2, ConfigBlocks.blockEldritch, 10, 3);
/* 165 */     this.field_145850_b.func_147465_d(x + 2, y + 3, z - 2, ConfigBlocks.blockEldritch, 10, 3);
/* 166 */     this.field_145850_b.func_147465_d(x - 2, y + 2, z - 2, ConfigBlocks.blockCosmeticSolid, 15, 3);
/* 167 */     this.field_145850_b.func_147465_d(x - 2, y + 2, z + 2, ConfigBlocks.blockCosmeticSolid, 15, 3);
/* 168 */     this.field_145850_b.func_147465_d(x + 2, y + 2, z + 2, ConfigBlocks.blockCosmeticSolid, 15, 3);
/* 169 */     this.field_145850_b.func_147465_d(x + 2, y + 2, z - 2, ConfigBlocks.blockCosmeticSolid, 15, 3);
/*     */     
/*     */ 
/* 172 */     for (int a = 0; a < 3; a++) {
/* 173 */       for (int b = 0; b < 3; b++) {
/* 174 */         if (this.ped[a][b] < 0) {
/* 175 */           this.field_145850_b.func_147465_d(x2 - 1 + b, y + 2, z2 - 1 + a, ConfigBlocks.blockEldritch, 4, 3);
/*     */         } else {
/* 177 */           this.field_145850_b.func_147465_d(x2 - 1 + b, y + 2, z2 - 1 + a, ConfigBlocks.blockStairsEldritch, this.ped[a][b], 3);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 183 */     EntityEldritchWarden boss = new EntityEldritchWarden(this.field_145850_b);
/* 184 */     double d0 = this.field_145851_c - (x2 + 0.5D);
/* 185 */     double d1 = this.field_145848_d - (y + 3 + boss.func_70047_e());
/* 186 */     double d2 = this.field_145849_e - (z2 + 0.5D);
/* 187 */     double d3 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
/* 188 */     float f = (float)(Math.atan2(d2, d0) * 180.0D / 3.141592653589793D) - 90.0F;
/* 189 */     float f1 = (float)-(Math.atan2(d1, d3) * 180.0D / 3.141592653589793D);
/* 190 */     boss.func_70012_b(x2 + 0.5D, y + 3, z2 + 0.5D, f, f1);
/* 191 */     boss.func_110161_a((IEntityLivingData)null);
/* 192 */     boss.func_110171_b(x, y + 2, z, 32);
/* 193 */     this.field_145850_b.func_72838_d(boss);
/*     */   }
/*     */   
/*     */   private void spawnGolemBossRoom(int cx, int cz, int exit)
/*     */   {
/* 198 */     for (int i = 0; i < this.field_145850_b.field_73010_i.size(); i++)
/*     */     {
/* 200 */       EntityPlayer ep = (EntityPlayer)this.field_145850_b.field_73010_i.get(i);
/* 201 */       if (ep.func_70092_e(this.field_145851_c, this.field_145848_d, this.field_145849_e) < 300.0D) {
/* 202 */         ep.func_145747_a(new ChatComponentText(StatCollector.func_74838_a("tc.boss.golem")));
/*     */       }
/*     */     }
/* 205 */     int x = cx * 16 + 16;
/* 206 */     int y = 50;
/* 207 */     int z = cz * 16 + 16;
/* 208 */     int x2 = 0;
/* 209 */     int z2 = 0;
/* 210 */     switch (exit) {
/* 211 */     case 2:  x2 = 8;z2 = 8; break;
/* 212 */     case 3:  x2 = -8;z2 = 8; break;
/* 213 */     case 4:  x2 = 8;z2 = -8; break;
/* 214 */     case 5:  x2 = -8;z2 = -8;
/*     */     }
/*     */     
/* 217 */     GenCommon.genObelisk(this.field_145850_b, x + x2, y + 4, z + z2);
/* 218 */     GenCommon.genObelisk(this.field_145850_b, x - x2, y + 4, z + z2);
/* 219 */     GenCommon.genObelisk(this.field_145850_b, x + x2, y + 4, z - z2);
/* 220 */     this.field_145850_b.func_147465_d(x + x2, y + 2, z + z2, ConfigBlocks.blockEldritch, 3, 3);
/* 221 */     this.field_145850_b.func_147465_d(x - x2, y + 2, z + z2, ConfigBlocks.blockEldritch, 3, 3);
/* 222 */     this.field_145850_b.func_147465_d(x + x2, y + 2, z - z2, ConfigBlocks.blockEldritch, 3, 3);
/*     */     
/* 224 */     for (int a = 0; a < 3; a++) {
/* 225 */       for (int b = 0; b < 3; b++) {
/* 226 */         if (this.ped[a][b] < 0) {
/* 227 */           this.field_145850_b.func_147465_d(x - 1 + b, y + 2, z - 1 + a, ConfigBlocks.blockEldritch, 4, 3);
/*     */         } else {
/* 229 */           this.field_145850_b.func_147465_d(x - 1 + b, y + 2, z - 1 + a, ConfigBlocks.blockStairsEldritch, this.ped[a][b], 3);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 234 */     for (int a = -10; a <= 10; a++) {
/* 235 */       for (int b = -10; b <= 10; b++) {
/* 236 */         if (((a < -2) && (b < -2)) || ((a > 2) && (b > 2)) || ((a < -2) && (b > 2)) || ((a > 2) && (b < -2) && (this.field_145850_b.field_73012_v.nextFloat() < 0.15F) && (this.field_145850_b.func_147437_c(x + a, y + 2, z + b)))) {
/* 237 */           float rr = this.field_145850_b.field_73012_v.nextFloat();
/* 238 */           int md = rr < 0.2F ? 1 : rr < 0.05F ? 2 : 0;
/* 239 */           this.field_145850_b.func_147465_d(x + a, y + 2, z + b, this.field_145850_b.field_73012_v.nextFloat() < 0.3F ? ConfigBlocks.blockLootCrate : ConfigBlocks.blockLootUrn, md, 3);
/*     */         }
/*     */       }
/*     */     }
/* 243 */     EntityEldritchGolem boss = new EntityEldritchGolem(this.field_145850_b);
/* 244 */     double d0 = this.field_145851_c - (x + 0.5D);
/* 245 */     double d1 = this.field_145848_d - (y + 3 + boss.func_70047_e());
/* 246 */     double d2 = this.field_145849_e - (z + 0.5D);
/* 247 */     double d3 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
/* 248 */     float f = (float)(Math.atan2(d2, d0) * 180.0D / 3.141592653589793D) - 90.0F;
/* 249 */     float f1 = (float)-(Math.atan2(d1, d3) * 180.0D / 3.141592653589793D);
/* 250 */     boss.func_70012_b(x + 0.5D, y + 3, z + 0.5D, f, f1);
/* 251 */     boss.func_110161_a((IEntityLivingData)null);
/* 252 */     this.field_145850_b.func_72838_d(boss);
/*     */   }
/*     */   
/*     */   private void spawnCultistBossRoom(int cx, int cz, int exit)
/*     */   {
/* 257 */     for (int i = 0; i < this.field_145850_b.field_73010_i.size(); i++)
/*     */     {
/* 259 */       EntityPlayer ep = (EntityPlayer)this.field_145850_b.field_73010_i.get(i);
/* 260 */       if (ep.func_70092_e(this.field_145851_c, this.field_145848_d, this.field_145849_e) < 300.0D) {
/* 261 */         ep.func_145747_a(new ChatComponentText(StatCollector.func_74838_a("tc.boss.crimson")));
/*     */       }
/*     */     }
/*     */     
/* 265 */     int x = cx * 16 + 16;
/* 266 */     int y = 50;
/* 267 */     int z = cz * 16 + 16;
/*     */     
/* 269 */     for (int a = -4; a <= 4; a++) {
/* 270 */       for (int b = -4; b <= 4; b++) {
/* 271 */         if (((Math.abs(a) != 2) && (Math.abs(b) != 2)) || ((!this.field_145850_b.field_73012_v.nextBoolean()) && (
/* 272 */           ((Math.abs(a) != 3) && (Math.abs(b) != 3)) || ((this.field_145850_b.field_73012_v.nextFloat() <= 0.33F) && (
/* 273 */           ((Math.abs(a) != 4) && (Math.abs(b) != 4)) || (this.field_145850_b.field_73012_v.nextFloat() <= 0.25F))))))
/* 274 */           this.field_145850_b.func_147465_d(x + b, y + 1, z + a, ConfigBlocks.blockEldritch, 7, 3);
/*     */       }
/*     */     }
/* 277 */     for (int a = 0; a < 5; a++) {
/* 278 */       for (int b = 0; b < 5; b++) {
/* 279 */         if ((a == 0) || (a == 4) || (b == 0) || (b == 4)) {
/* 280 */           this.field_145850_b.func_147465_d(x - 8 + b * 4, y + 2, z - 8 + a * 4, ConfigBlocks.blockCosmeticSolid, 11, 3);
/* 281 */           this.field_145850_b.func_147465_d(x - 8 + b * 4, y + 3, z - 8 + a * 4, ConfigBlocks.blockEldritch, 5, 3);
/* 282 */           this.field_145850_b.func_147465_d(x - 8 + b * 4, y + 4, z - 8 + a * 4, ConfigBlocks.blockSlabStone, 1, 3);
/*     */           
/* 284 */           this.field_145850_b.func_147465_d(x - 8 + b * 4, y + 10, z - 8 + a * 4, ConfigBlocks.blockCosmeticSolid, 11, 3);
/* 285 */           this.field_145850_b.func_147465_d(x - 8 + b * 4, y + 9, z - 8 + a * 4, ConfigBlocks.blockEldritch, 5, 3);
/* 286 */           this.field_145850_b.func_147465_d(x - 8 + b * 4, y + 8, z - 8 + a * 4, ConfigBlocks.blockSlabStone, 9, 3);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 291 */     EntityCultistPortal boss = new EntityCultistPortal(this.field_145850_b);
/* 292 */     boss.func_70012_b(x + 0.5D, y + 2, z + 0.5D, 0.0F, 0.0F);
/* 293 */     this.field_145850_b.func_72838_d(boss);
/*     */   }
/*     */   
/*     */   private void spawnTaintBossRoom(int cx, int cz, int exit)
/*     */   {
/* 298 */     for (int i = 0; i < this.field_145850_b.field_73010_i.size(); i++)
/*     */     {
/* 300 */       EntityPlayer ep = (EntityPlayer)this.field_145850_b.field_73010_i.get(i);
/* 301 */       if (ep.func_70092_e(this.field_145851_c, this.field_145848_d, this.field_145849_e) < 300.0D) {
/* 302 */         ep.func_145747_a(new ChatComponentText(StatCollector.func_74838_a("tc.boss.taint")));
/*     */       }
/*     */     }
/*     */     
/* 306 */     int x = cx * 16 + 16;
/* 307 */     int y = 50;
/* 308 */     int z = cz * 16 + 16;
/*     */     
/* 310 */     for (int a = -12; a <= 12; a++) {
/* 311 */       for (int b = -12; b <= 12; b++) {
/* 312 */         Utils.setBiomeAt(this.field_145850_b, x + b, z + a, ThaumcraftWorldGenerator.biomeTaint);
/*     */         
/* 314 */         for (int c = 0; c < 9; c++) {
/* 315 */           if ((this.field_145850_b.func_147437_c(x + b, y + 2 + c, z + a)) && (thaumcraft.common.lib.utils.BlockUtils.isAdjacentToSolidBlock(this.field_145850_b, x + b, y + 2 + c, z + a)) && 
/* 316 */             (this.field_145850_b.field_73012_v.nextInt(3) != 0)) { this.field_145850_b.func_147465_d(x + b, y + 2 + c, z + a, ConfigBlocks.blockTaintFibres, this.field_145850_b.field_73012_v.nextInt(4) == 0 ? 1 : 0, 3);
/*     */           }
/*     */         }
/* 319 */         if (this.field_145850_b.field_73012_v.nextFloat() < 0.15D) {
/* 320 */           this.field_145850_b.func_147465_d(x + b, y + 2, z + a, ConfigBlocks.blockTaint, 0, 3);
/* 321 */           if (this.field_145850_b.field_73012_v.nextFloat() < 0.2D) {
/* 322 */             this.field_145850_b.func_147465_d(x + b, y + 3, z + a, ConfigBlocks.blockTaint, 0, 3);
/*     */           }
/*     */         }
/* 325 */         if (((Math.abs(a) != 4) && (Math.abs(b) != 4)) || ((!this.field_145850_b.field_73012_v.nextBoolean()) && (
/* 326 */           ((Math.abs(a) < 5) && (Math.abs(b) < 5)) || ((this.field_145850_b.field_73012_v.nextFloat() <= 0.33F) && (
/* 327 */           ((Math.abs(a) < 7) && (Math.abs(b) < 7)) || (this.field_145850_b.field_73012_v.nextFloat() <= 0.25F)))))) {
/* 328 */           this.field_145850_b.func_147465_d(x + b, y + 1, z + a, ConfigBlocks.blockTaint, 1, 3);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 333 */     EntityTaintacle boss1 = this.field_145850_b.field_73013_u != EnumDifficulty.HARD ? new EntityTaintacle(this.field_145850_b) : new EntityTaintacleGiant(this.field_145850_b);
/* 334 */     boss1.func_70012_b(x + 0.5D, y + 3, z + 0.5D, 0.0F, 0.0F);
/* 335 */     EntityUtils.makeChampion(boss1, true);
/* 336 */     this.field_145850_b.func_72838_d(boss1);
/*     */     
/* 338 */     EntityTaintacle boss2 = this.field_145850_b.field_73012_v.nextBoolean() ? new EntityTaintacle(this.field_145850_b) : new EntityTaintacleGiant(this.field_145850_b);
/* 339 */     boss2.func_70012_b(x + 3.5D, y + 3, z + 3.5D, 0.0F, 0.0F);
/* 340 */     EntityUtils.makeChampion(boss2, true);
/* 341 */     this.field_145850_b.func_72838_d(boss2);
/*     */     
/* 343 */     EntityTaintacle boss3 = (boss2 instanceof EntityTaintacleGiant) ? new EntityTaintacle(this.field_145850_b) : new EntityTaintacleGiant(this.field_145850_b);
/* 344 */     boss3.func_70012_b(x - 2.5D, y + 3, z + 3.5D, 0.0F, 0.0F);
/* 345 */     EntityUtils.makeChampion(boss3, true);
/* 346 */     this.field_145850_b.func_72838_d(boss3);
/*     */     
/* 348 */     EntityTaintacle boss4 = this.field_145850_b.field_73012_v.nextBoolean() ? new EntityTaintacle(this.field_145850_b) : new EntityTaintacleGiant(this.field_145850_b);
/* 349 */     boss4.func_70012_b(x + 3.5D, y + 3, z - 2.5D, 0.0F, 0.0F);
/* 350 */     EntityUtils.makeChampion(boss4, true);
/* 351 */     this.field_145850_b.func_72838_d(boss4);
/*     */     
/* 353 */     EntityTaintacle boss5 = (boss4 instanceof EntityTaintacleGiant) ? new EntityTaintacle(this.field_145850_b) : new EntityTaintacleGiant(this.field_145850_b);
/* 354 */     boss5.func_70012_b(x - 2.5D, y + 3, z - 2.5D, 0.0F, 0.0F);
/* 355 */     EntityUtils.makeChampion(boss5, true);
/* 356 */     this.field_145850_b.func_72838_d(boss5);
/*     */   }
/*     */   
/*     */ 
/*     */   public double func_145833_n()
/*     */   {
/* 362 */     return 9216.0D;
/*     */   }
/*     */   
/*     */   @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox()
/*     */   {
/* 368 */     return AxisAlignedBB.func_72330_a(this.field_145851_c - 2.25D, this.field_145848_d - 2.25D, this.field_145849_e - 2.25D, this.field_145851_c + 3.25D, this.field_145848_d + 3.25D, this.field_145849_e + 3.25D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 373 */   byte facing = 0;
/*     */   
/*     */   public byte getFacing() {
/* 376 */     return this.facing;
/*     */   }
/*     */   
/*     */   public void setFacing(byte face) {
/* 380 */     this.facing = face;
/* 381 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 382 */     func_70296_d();
/*     */   }
/*     */   
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 387 */     this.facing = nbttagcompound.func_74771_c("facing");
/* 388 */     this.count = nbttagcompound.func_74765_d("count");
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 393 */     nbttagcompound.func_74774_a("facing", this.facing);
/* 394 */     nbttagcompound.func_74777_a("count", (short)this.count);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileEldritchLock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */