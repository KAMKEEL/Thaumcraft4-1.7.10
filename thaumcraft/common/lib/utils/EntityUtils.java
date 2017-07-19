/*     */ package thaumcraft.common.lib.utils;
/*     */ 
/*     */ import cpw.mods.fml.common.ObfuscationReflectionHelper;
/*     */ import cpw.mods.fml.relauncher.ReflectionHelper;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.IAttribute;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.ai.attributes.RangedAttribute;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.monster.EntityCreeper;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.server.management.ItemInWorldManager;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.entities.EntitySpecialItem;
/*     */ import thaumcraft.common.entities.monster.boss.EntityThaumcraftBoss;
/*     */ import thaumcraft.common.entities.monster.mods.ChampionModifier;
/*     */ 
/*     */ public class EntityUtils
/*     */ {
/*     */   public static Entity getPointedEntity(World world, Entity entityplayer, double minrange, double range, float padding)
/*     */   {
/*  37 */     return getPointedEntity(world, entityplayer, minrange, range, padding, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static Entity getPointedEntity(World world, Entity entityplayer, double minrange, double range, float padding, boolean nonCollide)
/*     */   {
/*  44 */     Entity pointedEntity = null;
/*  45 */     double d = range;
/*  46 */     Vec3 vec3d = Vec3.func_72443_a(entityplayer.field_70165_t, entityplayer.field_70163_u + entityplayer.func_70047_e(), entityplayer.field_70161_v);
/*     */     
/*     */ 
/*  49 */     Vec3 vec3d1 = entityplayer.func_70040_Z();
/*  50 */     Vec3 vec3d2 = vec3d.func_72441_c(vec3d1.field_72450_a * d, vec3d1.field_72448_b * d, vec3d1.field_72449_c * d);
/*     */     
/*  52 */     float f1 = padding;
/*  53 */     List list = world.func_72839_b(entityplayer, entityplayer.field_70121_D.func_72321_a(vec3d1.field_72450_a * d, vec3d1.field_72448_b * d, vec3d1.field_72449_c * d).func_72314_b(f1, f1, f1));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  58 */     double d2 = 0.0D;
/*  59 */     for (int i = 0; i < list.size(); i++) {
/*  60 */       Entity entity = (Entity)list.get(i);
/*  61 */       if (entity.func_70032_d(entityplayer) >= minrange)
/*     */       {
/*  63 */         if (((entity.func_70067_L()) || (nonCollide)) && (world.func_147447_a(Vec3.func_72443_a(entityplayer.field_70165_t, entityplayer.field_70163_u + entityplayer.func_70047_e(), entityplayer.field_70161_v), Vec3.func_72443_a(entity.field_70165_t, entity.field_70163_u + entity.func_70047_e(), entity.field_70161_v), false, true, false) == null))
/*     */         {
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
/*  75 */           float f2 = Math.max(0.8F, entity.func_70111_Y());
/*  76 */           AxisAlignedBB axisalignedbb = entity.field_70121_D.func_72314_b(f2, f2, f2);
/*  77 */           MovingObjectPosition movingobjectposition = axisalignedbb.func_72327_a(vec3d, vec3d2);
/*     */           
/*  79 */           if (axisalignedbb.func_72318_a(vec3d)) {
/*  80 */             if ((0.0D < d2) || (d2 == 0.0D)) {
/*  81 */               pointedEntity = entity;
/*  82 */               d2 = 0.0D;
/*     */             }
/*     */             
/*     */           }
/*  86 */           else if (movingobjectposition != null)
/*     */           {
/*     */ 
/*  89 */             double d3 = vec3d.func_72438_d(movingobjectposition.field_72307_f);
/*  90 */             if ((d3 < d2) || (d2 == 0.0D)) {
/*  91 */               pointedEntity = entity;
/*  92 */               d2 = d3;
/*     */             }
/*     */           } } } }
/*  95 */     return pointedEntity;
/*     */   }
/*     */   
/*     */   public static Entity getPointedEntity(World world, EntityPlayer entityplayer, double range, Class<?> clazz)
/*     */   {
/* 100 */     Entity pointedEntity = null;
/* 101 */     double d = range;
/* 102 */     Vec3 vec3d = Vec3.func_72443_a(entityplayer.field_70165_t, entityplayer.field_70163_u + entityplayer.func_70047_e(), entityplayer.field_70161_v);
/*     */     
/*     */ 
/* 105 */     Vec3 vec3d1 = entityplayer.func_70040_Z();
/* 106 */     Vec3 vec3d2 = vec3d.func_72441_c(vec3d1.field_72450_a * d, vec3d1.field_72448_b * d, vec3d1.field_72449_c * d);
/*     */     
/* 108 */     float f1 = 1.1F;
/* 109 */     List list = world.func_72839_b(entityplayer, entityplayer.field_70121_D.func_72321_a(vec3d1.field_72450_a * d, vec3d1.field_72448_b * d, vec3d1.field_72449_c * d).func_72314_b(f1, f1, f1));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 114 */     double d2 = 0.0D;
/* 115 */     for (int i = 0; i < list.size(); i++)
/*     */     {
/* 117 */       Entity entity = (Entity)list.get(i);
/* 118 */       if ((entity.func_70067_L()) && (world.func_147447_a(Vec3.func_72443_a(entityplayer.field_70165_t, entityplayer.field_70163_u + entityplayer.func_70047_e(), entityplayer.field_70161_v), Vec3.func_72443_a(entity.field_70165_t, entity.field_70163_u + entity.func_70047_e(), entity.field_70161_v), false, true, false) == null) && (!clazz.isInstance(entity)))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 129 */         float f2 = Math.max(0.8F, entity.func_70111_Y());
/* 130 */         AxisAlignedBB axisalignedbb = entity.field_70121_D.func_72314_b(f2, f2, f2);
/* 131 */         MovingObjectPosition movingobjectposition = axisalignedbb.func_72327_a(vec3d, vec3d2);
/*     */         
/* 133 */         if (axisalignedbb.func_72318_a(vec3d)) {
/* 134 */           if ((0.0D < d2) || (d2 == 0.0D)) {
/* 135 */             pointedEntity = entity;
/* 136 */             d2 = 0.0D;
/*     */           }
/*     */           
/*     */         }
/* 140 */         else if (movingobjectposition != null)
/*     */         {
/*     */ 
/* 143 */           double d3 = vec3d.func_72438_d(movingobjectposition.field_72307_f);
/* 144 */           if ((d3 < d2) || (d2 == 0.0D)) {
/* 145 */             pointedEntity = entity;
/* 146 */             d2 = d3;
/*     */           }
/*     */         } } }
/* 149 */     return pointedEntity;
/*     */   }
/*     */   
/*     */   public static boolean canEntityBeSeen(Entity entity, TileEntity te) {
/* 153 */     return te.func_145831_w().func_72901_a(Vec3.func_72443_a(te.field_145851_c + 0.5D, te.field_145848_d + 1.25D, te.field_145849_e + 0.5D), Vec3.func_72443_a(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v), false) == null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static boolean canEntityBeSeen(Entity entity, double x, double y, double z)
/*     */   {
/* 160 */     return entity.field_70170_p.func_72901_a(Vec3.func_72443_a(x, y, z), Vec3.func_72443_a(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v), false) == null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static boolean canEntityBeSeen(Entity entity, Entity entity2)
/*     */   {
/* 167 */     return entity.field_70170_p.func_72901_a(Vec3.func_72443_a(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v), Vec3.func_72443_a(entity2.field_70165_t, entity2.field_70163_u, entity2.field_70161_v), false) == null;
/*     */   }
/*     */   
/*     */ 
/*     */   public static void setRecentlyHit(EntityLivingBase ent, int hit)
/*     */   {
/*     */     try
/*     */     {
/* 175 */       ObfuscationReflectionHelper.setPrivateValue(EntityLivingBase.class, ent, Integer.valueOf(hit), new String[] { "recentlyHit", "field_70718_bc" });
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */   public static int getRecentlyHit(EntityLivingBase ent)
/*     */   {
/*     */     try {
/* 183 */       return ((Integer)ReflectionHelper.getPrivateValue(EntityLivingBase.class, ent, new String[] { "recentlyHit", "field_70718_bc" })).intValue();
/*     */     }
/*     */     catch (Exception e) {}
/*     */     
/* 187 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public static MovingObjectPosition getMovingObjectPositionFromPlayer(World par1World, EntityPlayer par2EntityPlayer, boolean par3)
/*     */   {
/* 193 */     float f = 1.0F;
/* 194 */     float f1 = par2EntityPlayer.field_70127_C + (par2EntityPlayer.field_70125_A - par2EntityPlayer.field_70127_C) * f;
/*     */     
/*     */ 
/* 197 */     float f2 = par2EntityPlayer.field_70126_B + (par2EntityPlayer.field_70177_z - par2EntityPlayer.field_70126_B) * f;
/*     */     
/*     */ 
/* 200 */     double d0 = par2EntityPlayer.field_70169_q + (par2EntityPlayer.field_70165_t - par2EntityPlayer.field_70169_q) * f;
/*     */     
/*     */ 
/* 203 */     double d1 = par2EntityPlayer.field_70167_r + (par2EntityPlayer.field_70163_u - par2EntityPlayer.field_70167_r) * f + (par1World.field_72995_K ? par2EntityPlayer.func_70047_e() - par2EntityPlayer.getDefaultEyeHeight() : par2EntityPlayer.func_70047_e());
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
/* 218 */     double d2 = par2EntityPlayer.field_70166_s + (par2EntityPlayer.field_70161_v - par2EntityPlayer.field_70166_s) * f;
/*     */     
/*     */ 
/* 221 */     Vec3 vec3 = Vec3.func_72443_a(d0, d1, d2);
/* 222 */     float f3 = MathHelper.func_76134_b(-f2 * 0.017453292F - 3.1415927F);
/* 223 */     float f4 = MathHelper.func_76126_a(-f2 * 0.017453292F - 3.1415927F);
/* 224 */     float f5 = -MathHelper.func_76134_b(-f1 * 0.017453292F);
/* 225 */     float f6 = MathHelper.func_76126_a(-f1 * 0.017453292F);
/* 226 */     float f7 = f4 * f5;
/* 227 */     float f8 = f3 * f5;
/* 228 */     double d3 = 5.0D;
/* 229 */     if ((par2EntityPlayer instanceof EntityPlayerMP)) {
/* 230 */       d3 = ((EntityPlayerMP)par2EntityPlayer).field_71134_c.getBlockReachDistance();
/*     */     }
/*     */     
/* 233 */     Vec3 vec31 = vec3.func_72441_c(f7 * d3, f6 * d3, f8 * d3);
/*     */     
/* 235 */     return par1World.func_147447_a(vec3, vec31, par3, !par3, false);
/*     */   }
/*     */   
/*     */   public static ArrayList<Entity> getEntitiesInRange(World world, double x, double y, double z, Entity entity, Class clazz, double range)
/*     */   {
/* 240 */     ArrayList<Entity> out = new ArrayList();
/* 241 */     List list = world.func_72872_a(clazz, AxisAlignedBB.func_72330_a(x, y, z, x, y, z).func_72314_b(range, range, range));
/*     */     
/* 243 */     if (list.size() > 0) {
/* 244 */       for (Object e : list) {
/* 245 */         Entity ent = (Entity)e;
/* 246 */         if ((entity == null) || (entity.func_145782_y() != ent.func_145782_y()))
/*     */         {
/*     */ 
/* 249 */           out.add(ent);
/*     */         }
/*     */       }
/*     */     }
/* 253 */     return out;
/*     */   }
/*     */   
/*     */   public static boolean isVisibleTo(float fov, Entity ent, Entity ent2, float range)
/*     */   {
/* 258 */     double[] x = { ent2.field_70165_t, ent2.field_70121_D.field_72338_b + ent2.field_70131_O / 2.0F, ent2.field_70161_v };
/* 259 */     double[] t = { ent.field_70165_t, ent.field_70121_D.field_72338_b + ent.func_70047_e(), ent.field_70161_v };
/* 260 */     Vec3 q = ent.func_70040_Z();
/* 261 */     q.field_72450_a *= range;
/* 262 */     q.field_72448_b *= range;
/* 263 */     q.field_72449_c *= range;
/* 264 */     Vec3 l = q.func_72441_c(ent.field_70165_t, ent.field_70121_D.field_72338_b + ent.func_70047_e(), ent.field_70161_v);
/*     */     
/* 266 */     double[] b = { l.field_72450_a, l.field_72448_b, l.field_72449_c };
/*     */     
/* 268 */     return Utils.isLyingInCone(x, t, b, fov);
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean isVisibleTo(float fov, Entity ent, double xx, double yy, double zz, float range)
/*     */   {
/* 274 */     double[] x = { xx, yy, zz };
/* 275 */     double[] t = { ent.field_70165_t, ent.field_70121_D.field_72338_b + ent.func_70047_e(), ent.field_70161_v };
/* 276 */     Vec3 q = ent.func_70040_Z();
/* 277 */     q.field_72450_a *= range;
/* 278 */     q.field_72448_b *= range;
/* 279 */     q.field_72449_c *= range;
/* 280 */     Vec3 l = q.func_72441_c(ent.field_70165_t, ent.field_70121_D.field_72338_b + ent.func_70047_e(), ent.field_70161_v);
/*     */     
/* 282 */     double[] b = { l.field_72450_a, l.field_72448_b, l.field_72449_c };
/*     */     
/* 284 */     return Utils.isLyingInCone(x, t, b, fov);
/*     */   }
/*     */   
/*     */ 
/*     */   public static EntityItem entityDropSpecialItem(Entity entity, ItemStack stack, float dropheight)
/*     */   {
/* 290 */     if ((stack.field_77994_a != 0) && (stack.func_77973_b() != null))
/*     */     {
/* 292 */       EntitySpecialItem entityitem = new EntitySpecialItem(entity.field_70170_p, entity.field_70165_t, entity.field_70163_u + dropheight, entity.field_70161_v, stack);
/*     */       
/* 294 */       entityitem.field_145804_b = 10;
/* 295 */       entityitem.field_70181_x = 0.10000000149011612D;
/* 296 */       entityitem.field_70159_w = 0.0D;
/* 297 */       entityitem.field_70179_y = 0.0D;
/* 298 */       if (entity.captureDrops)
/*     */       {
/* 300 */         entity.capturedDrops.add(entityitem);
/*     */       }
/*     */       else
/*     */       {
/* 304 */         entity.field_70170_p.func_72838_d(entityitem);
/*     */       }
/* 306 */       return entityitem;
/*     */     }
/*     */     
/*     */ 
/* 310 */     return null;
/*     */   }
/*     */   
/*     */   public static void makeChampion(EntityMob entity, boolean persist)
/*     */   {
/* 315 */     int type = entity.field_70170_p.field_73012_v.nextInt(ChampionModifier.mods.length);
/*     */     
/* 317 */     if ((entity instanceof EntityCreeper)) {
/* 318 */       type = 0;
/*     */     }
/*     */     
/* 321 */     IAttributeInstance modai = entity.func_110148_a(CHAMPION_MOD);
/* 322 */     modai.func_111124_b(ChampionModifier.mods[type].attributeMod);
/* 323 */     modai.func_111121_a(ChampionModifier.mods[type].attributeMod);
/*     */     
/* 325 */     if (!(entity instanceof EntityThaumcraftBoss)) {
/* 326 */       IAttributeInstance iattributeinstance = entity.func_110148_a(SharedMonsterAttributes.field_111267_a);
/* 327 */       iattributeinstance.func_111124_b(CHAMPION_HEALTH);
/* 328 */       iattributeinstance.func_111121_a(CHAMPION_HEALTH);
/* 329 */       IAttributeInstance iattributeinstance2 = entity.func_110148_a(SharedMonsterAttributes.field_111264_e);
/* 330 */       iattributeinstance2.func_111124_b(CHAMPION_DAMAGE);
/* 331 */       iattributeinstance2.func_111121_a(CHAMPION_DAMAGE);
/* 332 */       entity.func_70691_i(25.0F);
/* 333 */       entity.func_94058_c(ChampionModifier.mods[type].getModNameLocalized() + " " + entity.func_70005_c_());
/*     */     } else {
/* 335 */       ((EntityThaumcraftBoss)entity).generateName();
/*     */     }
/*     */     
/* 338 */     if (persist) { entity.func_110163_bv();
/*     */     }
/* 340 */     switch (type) {
/*     */     case 0: 
/* 342 */       IAttributeInstance sai = entity.func_110148_a(SharedMonsterAttributes.field_111263_d);
/* 343 */       sai.func_111124_b(BOLDBUFF);
/* 344 */       sai.func_111121_a(BOLDBUFF);
/* 345 */       break;
/*     */     case 3: 
/* 347 */       IAttributeInstance mai = entity.func_110148_a(SharedMonsterAttributes.field_111264_e);
/* 348 */       mai.func_111124_b(MIGHTYBUFF);
/* 349 */       mai.func_111121_a(MIGHTYBUFF);
/* 350 */       break;
/*     */     case 5: 
/* 352 */       int bh = (int)entity.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() / 2;
/* 353 */       entity.func_110149_m(entity.func_110139_bj() + bh);
/*     */     }
/*     */     
/*     */   }
/*     */   
/* 358 */   public static final IAttribute CHAMPION_MOD = new RangedAttribute("tc.mobmod", -2.0D, -2.0D, 100.0D).func_111117_a("Champion modifier").func_111112_a(true);
/* 359 */   public static final AttributeModifier CHAMPION_HEALTH = new AttributeModifier(UUID.fromString("a62bef38-48cc-42a6-ac5e-ef913841c4fd"), "Champion health buff", 30.0D, 0);
/* 360 */   public static final AttributeModifier CHAMPION_DAMAGE = new AttributeModifier(UUID.fromString("a340d2db-d881-4c25-ac62-f0ad14cd63b0"), "Champion damage buff", 2.0D, 2);
/* 361 */   public static final AttributeModifier BOLDBUFF = new AttributeModifier(UUID.fromString("4b1edd33-caa9-47ae-a702-d86c05701037"), "Bold speed boost", 0.3D, 1);
/* 362 */   public static final AttributeModifier MIGHTYBUFF = new AttributeModifier(UUID.fromString("7163897f-07f5-49b3-9ce4-b74beb83d2d3"), "Mighty damage boost", 3.0D, 2);
/* 363 */   public static final AttributeModifier[] HPBUFF = { new AttributeModifier(UUID.fromString("54d621c1-dd4d-4b43-8bd2-5531c8875797"), "HEALTH BUFF 1", 50.0D, 0), new AttributeModifier(UUID.fromString("f51257dc-b7fa-4f7a-92d7-75d68e8592c4"), "HEALTH BUFF 2", 50.0D, 0), new AttributeModifier(UUID.fromString("3d6b2e42-4141-4364-b76d-0e8664bbd0bb"), "HEALTH BUFF 3", 50.0D, 0), new AttributeModifier(UUID.fromString("02c97a08-801c-4131-afa2-1427a6151934"), "HEALTH BUFF 4", 50.0D, 0), new AttributeModifier(UUID.fromString("0f354f6a-33c5-40be-93be-81b1338567f1"), "HEALTH BUFF 5", 50.0D, 0) };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 369 */   public static final AttributeModifier[] DMGBUFF = { new AttributeModifier(UUID.fromString("534f8c57-929a-48cf-bbd6-0fd851030748"), "DAMAGE BUFF 1", 0.5D, 0), new AttributeModifier(UUID.fromString("d317a76e-0e7c-4c61-acfd-9fa286053b32"), "DAMAGE BUFF 2", 0.5D, 0), new AttributeModifier(UUID.fromString("ff462d63-26a2-4363-830e-143ed97e2a4f"), "DAMAGE BUFF 3", 0.5D, 0), new AttributeModifier(UUID.fromString("cf1eb39e-0c67-495f-887c-0d3080828d2f"), "DAMAGE BUFF 4", 0.5D, 0), new AttributeModifier(UUID.fromString("3cfab9da-2701-43d8-ac07-885f16fa4117"), "DAMAGE BUFF 5", 0.5D, 0) };
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/utils/EntityUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */