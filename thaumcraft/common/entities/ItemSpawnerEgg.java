/*     */ package thaumcraft.common.entities;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockLiquid;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemSpawnerEgg extends Item
/*     */ {
/*  29 */   static ArrayList<EntityEggStuff> spawnList = new ArrayList();
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon theIcon;
/*     */   
/*     */   static class EntityEggStuff {
/*     */     String name;
/*     */     
/*  36 */     public EntityEggStuff(String name, int color1, int color2) { this.name = name;
/*  37 */       this.color1 = color1;
/*  38 */       this.color2 = color2; }
/*     */     
/*     */     int color1;
/*     */     int color2; }
/*     */   
/*  43 */   public static void addMapping(String name, int c1, int c2) { spawnList.add(new EntityEggStuff("Thaumcraft." + name, c1, c2)); }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemSpawnerEgg()
/*     */   {
/*  53 */     func_77627_a(true);
/*  54 */     func_77637_a(CreativeTabs.field_78026_f);
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_77653_i(ItemStack par1ItemStack)
/*     */   {
/*  60 */     String s = ("" + StatCollector.func_74838_a("item.monsterPlacer.name")).trim();
/*  61 */     String s1 = ((EntityEggStuff)spawnList.get(par1ItemStack.func_77960_j())).name;
/*     */     
/*  63 */     if (s1 != null)
/*     */     {
/*  65 */       s = s + " " + StatCollector.func_74838_a(new StringBuilder().append("entity.").append(s1).append(".name").toString());
/*     */     }
/*     */     
/*  68 */     return s;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_82790_a(ItemStack stack, int layer)
/*     */   {
/*  75 */     EntityEggStuff entityegginfo = (EntityEggStuff)spawnList.get(stack.func_77960_j());
/*  76 */     return entityegginfo != null ? entityegginfo.color2 : layer == 0 ? entityegginfo.color1 : 16777215;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
/*     */   {
/*  86 */     if (world.field_72995_K)
/*     */     {
/*  88 */       return true;
/*     */     }
/*     */     
/*     */ 
/*  92 */     Block block = world.func_147439_a(x, y, z);
/*  93 */     x += net.minecraft.util.Facing.field_71586_b[side];
/*  94 */     y += net.minecraft.util.Facing.field_71587_c[side];
/*  95 */     z += net.minecraft.util.Facing.field_71585_d[side];
/*  96 */     double d0 = 0.0D;
/*     */     
/*  98 */     if ((side == 1) && (block.func_149645_b() == 11))
/*     */     {
/* 100 */       d0 = 0.5D;
/*     */     }
/*     */     
/* 103 */     Entity entity = spawnCreature(world, stack.func_77960_j(), x + 0.5D, y + d0, z + 0.5D);
/*     */     
/*     */ 
/*     */ 
/* 107 */     if (entity != null)
/*     */     {
/* 109 */       if (((entity instanceof EntityLivingBase)) && (stack.func_82837_s()))
/*     */       {
/* 111 */         ((EntityLiving)entity).func_94058_c(stack.func_82833_r());
/*     */       }
/*     */       
/* 114 */       if (!player.field_71075_bZ.field_75098_d)
/*     */       {
/* 116 */         stack.field_77994_a -= 1;
/*     */       }
/*     */     }
/*     */     
/* 120 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
/*     */   {
/* 130 */     if (world.field_72995_K)
/*     */     {
/* 132 */       return stack;
/*     */     }
/*     */     
/*     */ 
/* 136 */     MovingObjectPosition movingobjectposition = func_77621_a(world, player, true);
/*     */     
/* 138 */     if (movingobjectposition == null)
/*     */     {
/* 140 */       return stack;
/*     */     }
/*     */     
/*     */ 
/* 144 */     if (movingobjectposition.field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.BLOCK)
/*     */     {
/* 146 */       int i = movingobjectposition.field_72311_b;
/* 147 */       int j = movingobjectposition.field_72312_c;
/* 148 */       int k = movingobjectposition.field_72309_d;
/*     */       
/* 150 */       if (!world.func_72962_a(player, i, j, k))
/*     */       {
/* 152 */         return stack;
/*     */       }
/*     */       
/* 155 */       if (!player.func_82247_a(i, j, k, movingobjectposition.field_72310_e, stack))
/*     */       {
/* 157 */         return stack;
/*     */       }
/*     */       
/* 160 */       if ((world.func_147439_a(i, j, k) instanceof BlockLiquid))
/*     */       {
/* 162 */         Entity entity = spawnCreature(world, stack.func_77960_j(), i, j, k);
/*     */         
/* 164 */         if (entity != null)
/*     */         {
/* 166 */           if (((entity instanceof EntityLivingBase)) && (stack.func_82837_s()))
/*     */           {
/* 168 */             ((EntityLiving)entity).func_94058_c(stack.func_82833_r());
/*     */           }
/*     */           
/* 171 */           if (!player.field_71075_bZ.field_75098_d)
/*     */           {
/* 173 */             stack.field_77994_a -= 1;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 179 */     return stack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Entity spawnCreature(World par0World, int par1, double par2, double par4, double par6)
/*     */   {
/* 191 */     if (spawnList.get(par1) == null)
/*     */     {
/* 193 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 197 */     Entity entity = null;
/*     */     
/* 199 */     for (int j = 0; j < 1; j++)
/*     */     {
/* 201 */       entity = EntityList.func_75620_a(((EntityEggStuff)spawnList.get(par1)).name, par0World);
/*     */       
/* 203 */       if ((entity != null) && ((entity instanceof EntityLivingBase)))
/*     */       {
/* 205 */         EntityLiving entityliving = (EntityLiving)entity;
/* 206 */         entity.func_70012_b(par2, par4, par6, MathHelper.func_76142_g(par0World.field_73012_v.nextFloat() * 360.0F), 0.0F);
/* 207 */         entityliving.field_70759_as = entityliving.field_70177_z;
/* 208 */         entityliving.field_70761_aq = entityliving.field_70177_z;
/* 209 */         entityliving.func_110161_a((IEntityLivingData)null);
/* 210 */         par0World.func_72838_d(entity);
/* 211 */         entityliving.func_70642_aH();
/*     */       }
/*     */     }
/*     */     
/* 215 */     return entity;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77623_v()
/*     */   {
/* 223 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77618_c(int par1, int par2)
/*     */   {
/* 233 */     return par2 > 0 ? this.theIcon : super.func_77618_c(par1, par2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_150895_a(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_)
/*     */   {
/* 243 */     for (int a = 0; a < spawnList.size(); a++) {
/* 244 */       p_150895_3_.add(new ItemStack(p_150895_1_, 1, a));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected String func_111208_A()
/*     */   {
/* 251 */     return "spawn_egg";
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister par1IconRegister)
/*     */   {
/* 258 */     super.func_94581_a(par1IconRegister);
/* 259 */     this.theIcon = par1IconRegister.func_94245_a(func_111208_A() + "_overlay");
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ItemSpawnerEgg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */