/*     */ package thaumcraft.codechicken.lib.raytracer;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.PlayerControllerMP;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.codechicken.lib.math.MathHelper;
/*     */ import thaumcraft.codechicken.lib.vec.BlockCoord;
/*     */ import thaumcraft.codechicken.lib.vec.Cuboid6;
/*     */ import thaumcraft.codechicken.lib.vec.Vector3;
/*     */ 
/*     */ public class RayTracer
/*     */ {
/*  23 */   private Vector3 vec = new Vector3();
/*  24 */   private Vector3 vec2 = new Vector3();
/*     */   
/*  26 */   private Vector3 s_vec = new Vector3();
/*     */   
/*     */   private double s_dist;
/*     */   private int s_side;
/*     */   private IndexedCuboid6 c_cuboid;
/*  31 */   private static ThreadLocal<RayTracer> t_inst = new ThreadLocal();
/*     */   
/*     */   public static RayTracer instance()
/*     */   {
/*  35 */     RayTracer inst = (RayTracer)t_inst.get();
/*  36 */     if (inst == null)
/*  37 */       t_inst.set(inst = new RayTracer());
/*  38 */     return inst;
/*     */   }
/*     */   
/*     */   private void traceSide(int side, Vector3 start, Vector3 end, Cuboid6 cuboid)
/*     */   {
/*  43 */     this.vec.set(start);
/*  44 */     Vector3 hit = null;
/*  45 */     switch (side)
/*     */     {
/*     */     case 0: 
/*  48 */       hit = this.vec.XZintercept(end, cuboid.min.y);
/*  49 */       break;
/*     */     case 1: 
/*  51 */       hit = this.vec.XZintercept(end, cuboid.max.y);
/*  52 */       break;
/*     */     case 2: 
/*  54 */       hit = this.vec.XYintercept(end, cuboid.min.z);
/*  55 */       break;
/*     */     case 3: 
/*  57 */       hit = this.vec.XYintercept(end, cuboid.max.z);
/*  58 */       break;
/*     */     case 4: 
/*  60 */       hit = this.vec.YZintercept(end, cuboid.min.x);
/*  61 */       break;
/*     */     case 5: 
/*  63 */       hit = this.vec.YZintercept(end, cuboid.max.x);
/*     */     }
/*     */     
/*  66 */     if (hit == null) {
/*  67 */       return;
/*     */     }
/*  69 */     switch (side)
/*     */     {
/*     */     case 0: 
/*     */     case 1: 
/*  73 */       if ((!MathHelper.between(cuboid.min.x, hit.x, cuboid.max.x)) || (!MathHelper.between(cuboid.min.z, hit.z, cuboid.max.z)))
/*     */         return;
/*     */       break;
/*     */     case 2: case 3: 
/*  77 */       if ((!MathHelper.between(cuboid.min.x, hit.x, cuboid.max.x)) || (!MathHelper.between(cuboid.min.y, hit.y, cuboid.max.y)))
/*     */         return;
/*     */       break;
/*     */     case 4: case 5: 
/*  81 */       if ((!MathHelper.between(cuboid.min.y, hit.y, cuboid.max.y)) || (!MathHelper.between(cuboid.min.z, hit.z, cuboid.max.z)))
/*     */         return;
/*     */       break;
/*     */     }
/*  85 */     double dist = this.vec2.set(hit).subtract(start).magSquared();
/*  86 */     if (dist < this.s_dist)
/*     */     {
/*  88 */       this.s_side = side;
/*  89 */       this.s_dist = dist;
/*  90 */       this.s_vec.set(this.vec);
/*     */     }
/*     */   }
/*     */   
/*     */   public MovingObjectPosition rayTraceCuboid(Vector3 start, Vector3 end, Cuboid6 cuboid)
/*     */   {
/*  96 */     this.s_dist = Double.MAX_VALUE;
/*  97 */     this.s_side = -1;
/*     */     
/*  99 */     for (int i = 0; i < 6; i++) {
/* 100 */       traceSide(i, start, end, cuboid);
/*     */     }
/* 102 */     if (this.s_side < 0) {
/* 103 */       return null;
/*     */     }
/* 105 */     MovingObjectPosition mop = new MovingObjectPosition(0, 0, 0, this.s_side, this.s_vec.toVec3D());
/* 106 */     mop.field_72313_a = null;
/* 107 */     return mop;
/*     */   }
/*     */   
/*     */   public MovingObjectPosition rayTraceCuboid(Vector3 start, Vector3 end, Cuboid6 cuboid, BlockCoord pos)
/*     */   {
/* 112 */     MovingObjectPosition mop = rayTraceCuboid(start, end, cuboid);
/* 113 */     if (mop != null)
/*     */     {
/* 115 */       mop.field_72313_a = MovingObjectPosition.MovingObjectType.BLOCK;
/* 116 */       mop.field_72311_b = pos.x;
/* 117 */       mop.field_72312_c = pos.y;
/* 118 */       mop.field_72309_d = pos.z;
/*     */     }
/* 120 */     return mop;
/*     */   }
/*     */   
/*     */   public MovingObjectPosition rayTraceCuboid(Vector3 start, Vector3 end, Cuboid6 cuboid, Entity e)
/*     */   {
/* 125 */     MovingObjectPosition mop = rayTraceCuboid(start, end, cuboid);
/* 126 */     if (mop != null)
/*     */     {
/* 128 */       mop.field_72313_a = MovingObjectPosition.MovingObjectType.ENTITY;
/* 129 */       mop.field_72308_g = e;
/*     */     }
/* 131 */     return mop;
/*     */   }
/*     */   
/*     */   public MovingObjectPosition rayTraceCuboids(Vector3 start, Vector3 end, List<IndexedCuboid6> cuboids)
/*     */   {
/* 136 */     double c_dist = Double.MAX_VALUE;
/* 137 */     MovingObjectPosition c_hit = null;
/*     */     
/* 139 */     for (IndexedCuboid6 cuboid : cuboids)
/*     */     {
/* 141 */       MovingObjectPosition mop = rayTraceCuboid(start, end, cuboid);
/* 142 */       if ((mop != null) && (this.s_dist < c_dist))
/*     */       {
/* 144 */         mop = new ExtendedMOP(mop, cuboid.data, this.s_dist);
/* 145 */         c_dist = this.s_dist;
/* 146 */         c_hit = mop;
/* 147 */         this.c_cuboid = cuboid;
/*     */       }
/*     */     }
/*     */     
/* 151 */     return c_hit;
/*     */   }
/*     */   
/*     */   public MovingObjectPosition rayTraceCuboids(Vector3 start, Vector3 end, List<IndexedCuboid6> cuboids, BlockCoord pos, Block block)
/*     */   {
/* 156 */     MovingObjectPosition mop = rayTraceCuboids(start, end, cuboids);
/* 157 */     if (mop != null)
/*     */     {
/* 159 */       mop.field_72313_a = MovingObjectPosition.MovingObjectType.BLOCK;
/* 160 */       mop.field_72311_b = pos.x;
/* 161 */       mop.field_72312_c = pos.y;
/* 162 */       mop.field_72309_d = pos.z;
/* 163 */       if (block != null)
/* 164 */         this.c_cuboid.add(new Vector3(-pos.x, -pos.y, -pos.z)).setBlockBounds(block);
/*     */     }
/* 166 */     return mop;
/*     */   }
/*     */   
/*     */   public void rayTraceCuboids(Vector3 start, Vector3 end, List<IndexedCuboid6> cuboids, BlockCoord pos, Block block, List<ExtendedMOP> hitList)
/*     */   {
/* 171 */     for (IndexedCuboid6 cuboid : cuboids)
/*     */     {
/* 173 */       MovingObjectPosition mop = rayTraceCuboid(start, end, cuboid);
/* 174 */       if (mop != null)
/*     */       {
/* 176 */         ExtendedMOP emop = new ExtendedMOP(mop, cuboid.data, this.s_dist);
/* 177 */         emop.field_72313_a = MovingObjectPosition.MovingObjectType.BLOCK;
/* 178 */         emop.field_72311_b = pos.x;
/* 179 */         emop.field_72312_c = pos.y;
/* 180 */         emop.field_72309_d = pos.z;
/* 181 */         hitList.add(emop);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static MovingObjectPosition retraceBlock(World world, EntityPlayer player, int x, int y, int z)
/*     */   {
/* 188 */     Block block = world.func_147439_a(x, y, z);
/*     */     
/* 190 */     Vec3 headVec = getCorrectedHeadVec(player);
/* 191 */     Vec3 lookVec = player.func_70676_i(1.0F);
/* 192 */     double reach = getBlockReachDistance(player);
/* 193 */     Vec3 endVec = headVec.func_72441_c(lookVec.field_72450_a * reach, lookVec.field_72448_b * reach, lookVec.field_72449_c * reach);
/* 194 */     return block.func_149731_a(world, x, y, z, headVec, endVec);
/*     */   }
/*     */   
/*     */   private static double getBlockReachDistance_server(EntityPlayerMP player)
/*     */   {
/* 199 */     return player.field_71134_c.getBlockReachDistance();
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   private static double getBlockReachDistance_client()
/*     */   {
/* 205 */     return Minecraft.func_71410_x().field_71442_b.func_78757_d();
/*     */   }
/*     */   
/*     */   public static MovingObjectPosition reTrace(World world, EntityPlayer player)
/*     */   {
/* 210 */     return reTrace(world, player, getBlockReachDistance(player));
/*     */   }
/*     */   
/*     */   public static MovingObjectPosition reTrace(World world, EntityPlayer player, double reach)
/*     */   {
/* 215 */     Vec3 headVec = getCorrectedHeadVec(player);
/* 216 */     Vec3 lookVec = player.func_70676_i(1.0F);
/* 217 */     Vec3 endVec = headVec.func_72441_c(lookVec.field_72450_a * reach, lookVec.field_72448_b * reach, lookVec.field_72449_c * reach);
/* 218 */     return world.func_147447_a(headVec, endVec, true, false, true);
/*     */   }
/*     */   
/*     */   public static Vec3 getCorrectedHeadVec(EntityPlayer player)
/*     */   {
/* 223 */     Vec3 v = Vec3.func_72443_a(player.field_70165_t, player.field_70163_u, player.field_70161_v);
/* 224 */     if (player.field_70170_p.field_72995_K) {
/* 225 */       v.field_72448_b += player.func_70047_e() - player.getDefaultEyeHeight();
/*     */     } else {
/* 227 */       v.field_72448_b += player.func_70047_e();
/* 228 */       if (((player instanceof EntityPlayerMP)) && (player.func_70093_af()))
/* 229 */         v.field_72448_b -= 0.08D;
/*     */     }
/* 231 */     return v;
/*     */   }
/*     */   
/*     */   public static Vec3 getStartVec(EntityPlayer player)
/*     */   {
/* 236 */     return getCorrectedHeadVec(player);
/*     */   }
/*     */   
/*     */   public static double getBlockReachDistance(EntityPlayer player)
/*     */   {
/* 241 */     return (player instanceof EntityPlayerMP) ? getBlockReachDistance_server((EntityPlayerMP)player) : player.field_70170_p.field_72995_K ? getBlockReachDistance_client() : 5.0D;
/*     */   }
/*     */   
/*     */ 
/*     */   public static Vec3 getEndVec(EntityPlayer player)
/*     */   {
/* 247 */     Vec3 headVec = getCorrectedHeadVec(player);
/* 248 */     Vec3 lookVec = player.func_70676_i(1.0F);
/* 249 */     double reach = getBlockReachDistance(player);
/* 250 */     return headVec.func_72441_c(lookVec.field_72450_a * reach, lookVec.field_72448_b * reach, lookVec.field_72449_c * reach);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/raytracer/RayTracer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */