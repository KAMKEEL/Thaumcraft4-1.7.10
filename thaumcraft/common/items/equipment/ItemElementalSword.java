/*     */ package thaumcraft.common.items.equipment;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.boss.EntityDragonPart;
/*     */ import net.minecraft.entity.passive.EntityTameable;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item.ToolMaterial;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.ItemSword;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import thaumcraft.api.IRepairable;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ public class ItemElementalSword extends ItemSword implements IRepairable
/*     */ {
/*     */   public IIcon icon;
/*     */   
/*     */   public ItemElementalSword(Item.ToolMaterial enumtoolmaterial)
/*     */   {
/*  39 */     super(enumtoolmaterial);
/*  40 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  47 */     this.icon = ir.func_94245_a("thaumcraft:elementalsword");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1) {
/*  52 */     return this.icon;
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  58 */     return EnumRarity.rare;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*     */   {
/*  64 */     return par2ItemStack.func_77969_a(new ItemStack(thaumcraft.common.config.ConfigItems.itemResource, 1, 2)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*     */   }
/*     */   
/*     */ 
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
/*     */   {
/*  70 */     super.onUsingTick(stack, player, count);
/*  71 */     int ticks = func_77626_a(stack) - count;
/*  72 */     if (player.field_70181_x < 0.0D) {
/*  73 */       player.field_70181_x /= 1.2000000476837158D;
/*  74 */       player.field_70143_R /= 1.2F;
/*     */     }
/*     */     
/*     */ 
/*  78 */     player.field_70181_x += 0.07999999821186066D;
/*  79 */     if (player.field_70181_x > 0.5D) player.field_70181_x = 0.20000000298023224D;
/*  80 */     if ((player instanceof EntityPlayerMP)) {
/*  81 */       Utils.resetFloatCounter((EntityPlayerMP)player);
/*     */     }
/*     */     
/*  84 */     List targets = player.field_70170_p.func_72839_b(player, player.field_70121_D.func_72314_b(2.5D, 2.5D, 2.5D));
/*  85 */     if (targets.size() > 0) {
/*  86 */       for (int var9 = 0; var9 < targets.size(); var9++)
/*     */       {
/*  88 */         Entity entity = (Entity)targets.get(var9);
/*  89 */         if ((!(entity instanceof EntityPlayer)) && 
/*  90 */           (!entity.field_70128_L) && (
/*  91 */           (player.field_70154_o == null) || (player.field_70154_o != entity)))
/*     */         {
/*  93 */           Vec3 p = Vec3.func_72443_a(player.field_70165_t, player.field_70163_u, player.field_70161_v);
/*  94 */           Vec3 t = Vec3.func_72443_a(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
/*  95 */           double distance = p.func_72438_d(t) + 0.1D;
/*     */           
/*  97 */           Vec3 r = Vec3.func_72443_a(t.field_72450_a - p.field_72450_a, t.field_72448_b - p.field_72448_b, t.field_72449_c - p.field_72449_c);
/*     */           
/*  99 */           entity.field_70159_w += r.field_72450_a / 2.5D / distance;
/* 100 */           entity.field_70181_x += r.field_72448_b / 2.5D / distance;
/* 101 */           entity.field_70179_y += r.field_72449_c / 2.5D / distance;
/*     */         }
/*     */       }
/*     */     }
/* 105 */     if (player.field_70170_p.field_72995_K) {
/* 106 */       int miny = (int)(player.field_70121_D.field_72338_b - 2.0D);
/* 107 */       if (player.field_70122_E) miny = MathHelper.func_76128_c(player.field_70121_D.field_72338_b);
/* 108 */       for (int a = 0; a < 5; a++) {
/* 109 */         Thaumcraft.proxy.smokeSpiral(player.field_70170_p, player.field_70165_t, player.field_70121_D.field_72338_b + player.field_70131_O / 2.0F, player.field_70161_v, 1.5F, player.field_70170_p.field_73012_v.nextInt(360), miny, 14540253);
/*     */       }
/* 111 */       if (player.field_70122_E) {
/* 112 */         float r1 = player.field_70170_p.field_73012_v.nextFloat() * 360.0F;
/* 113 */         float mx = -MathHelper.func_76126_a(r1 / 180.0F * 3.1415927F) / 5.0F;
/* 114 */         float mz = MathHelper.func_76134_b(r1 / 180.0F * 3.1415927F) / 5.0F;
/* 115 */         player.field_70170_p.func_72869_a("smoke", player.field_70165_t, player.field_70121_D.field_72338_b + 0.10000000149011612D, player.field_70161_v, mx, 0.0D, mz);
/*     */       }
/*     */       
/*     */     }
/* 119 */     else if ((ticks == 0) || (ticks % 20 == 0)) {
/* 120 */       player.field_70170_p.func_72956_a(player, "thaumcraft:wind", 0.5F, 0.9F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F);
/*     */     }
/*     */     
/*     */ 
/* 124 */     if (ticks % 20 == 0) { stack.func_77972_a(1, player);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
/*     */   {
/* 131 */     if (entity.func_70089_S()) {
/* 132 */       List targets = player.field_70170_p.func_72839_b(player, entity.field_70121_D.func_72314_b(1.2D, 1.1D, 1.2D));
/* 133 */       int count = 0;
/* 134 */       if (targets.size() > 1)
/*     */       {
/* 136 */         for (int var9 = 0; var9 < targets.size(); var9++)
/*     */         {
/* 138 */           Entity var10 = (Entity)targets.get(var9);
/* 139 */           if ((!var10.field_70128_L) && 
/* 140 */             ((!(var10 instanceof EntityGolemBase)) || (!((EntityGolemBase)var10).getOwnerName().equals(player.func_70005_c_()))) && 
/* 141 */             ((!(var10 instanceof EntityTameable)) || (!((EntityTameable)var10).func_152113_b().equals(player.func_70005_c_()))) && 
/* 142 */             ((var10 instanceof net.minecraft.entity.EntityLiving)) && (var10.func_145782_y() != entity.func_145782_y()) && (
/* 143 */             (!(var10 instanceof EntityPlayer)) || (((EntityPlayer)var10).func_70005_c_() != player.func_70005_c_())))
/*     */           {
/*     */ 
/*     */ 
/* 147 */             if (var10.func_70089_S()) {
/* 148 */               attackTargetEntityWithCurrentItem(var10, player);
/* 149 */               count++;
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 156 */         if ((count > 0) && (!player.field_70170_p.field_72995_K)) {
/* 157 */           player.field_70170_p.func_72956_a(entity, "thaumcraft:swing", 1.0F, 0.9F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F);
/*     */         }
/*     */       }
/*     */     }
/* 161 */     return super.onLeftClickEntity(stack, player, entity);
/*     */   }
/*     */   
/*     */ 
/*     */   public void attackTargetEntityWithCurrentItem(Entity par1Entity, EntityPlayer player)
/*     */   {
/* 167 */     if (MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.entity.player.AttackEntityEvent(player, par1Entity)))
/*     */     {
/* 169 */       return;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 176 */     if (par1Entity.func_70075_an())
/*     */     {
/* 178 */       if (!par1Entity.func_85031_j(player))
/*     */       {
/* 180 */         float f = (float)player.func_110148_a(net.minecraft.entity.SharedMonsterAttributes.field_111264_e).func_111126_e();
/* 181 */         int i = 0;
/* 182 */         float f1 = 0.0F;
/*     */         
/* 184 */         if ((par1Entity instanceof EntityLivingBase))
/*     */         {
/* 186 */           f1 = EnchantmentHelper.func_77512_a(player, (EntityLivingBase)par1Entity);
/* 187 */           i += EnchantmentHelper.func_77507_b(player, (EntityLivingBase)par1Entity);
/*     */         }
/*     */         
/* 190 */         if (player.func_70051_ag())
/*     */         {
/* 192 */           i++;
/*     */         }
/*     */         
/* 195 */         if ((f > 0.0F) || (f1 > 0.0F))
/*     */         {
/* 197 */           boolean flag = (player.field_70143_R > 0.0F) && (!player.field_70122_E) && (!player.func_70617_f_()) && (!player.func_70090_H()) && (!player.func_70644_a(net.minecraft.potion.Potion.field_76440_q)) && (player.field_70154_o == null) && ((par1Entity instanceof EntityLivingBase));
/*     */           
/* 199 */           if ((flag) && (f > 0.0F))
/*     */           {
/* 201 */             f *= 1.5F;
/*     */           }
/*     */           
/* 204 */           f += f1;
/* 205 */           boolean flag1 = false;
/* 206 */           int j = EnchantmentHelper.func_90036_a(player);
/*     */           
/* 208 */           if (((par1Entity instanceof EntityLivingBase)) && (j > 0) && (!par1Entity.func_70027_ad()))
/*     */           {
/* 210 */             flag1 = true;
/* 211 */             par1Entity.func_70015_d(1);
/*     */           }
/*     */           
/* 214 */           boolean flag2 = par1Entity.func_70097_a(DamageSource.func_76365_a(player), f);
/*     */           
/* 216 */           if (flag2)
/*     */           {
/* 218 */             if (i > 0)
/*     */             {
/* 220 */               par1Entity.func_70024_g(-MathHelper.func_76126_a(player.field_70177_z * 3.1415927F / 180.0F) * i * 0.5F, 0.1D, MathHelper.func_76134_b(player.field_70177_z * 3.1415927F / 180.0F) * i * 0.5F);
/* 221 */               player.field_70159_w *= 0.6D;
/* 222 */               player.field_70179_y *= 0.6D;
/* 223 */               player.func_70031_b(false);
/*     */             }
/*     */             
/* 226 */             if (flag)
/*     */             {
/* 228 */               player.func_71009_b(par1Entity);
/*     */             }
/*     */             
/* 231 */             if (f1 > 0.0F)
/*     */             {
/* 233 */               player.func_71047_c(par1Entity);
/*     */             }
/*     */             
/* 236 */             if (f >= 18.0F)
/*     */             {
/* 238 */               player.func_71029_a(net.minecraft.stats.AchievementList.field_75999_E);
/*     */             }
/*     */             
/* 241 */             player.func_130011_c(par1Entity);
/*     */             
/* 243 */             if ((par1Entity instanceof EntityLivingBase))
/*     */             {
/* 245 */               EnchantmentHelper.func_151384_a((EntityLivingBase)par1Entity, player);
/*     */             }
/*     */           }
/*     */           
/* 249 */           ItemStack itemstack = player.func_71045_bC();
/* 250 */           Object object = par1Entity;
/*     */           
/* 252 */           if ((par1Entity instanceof EntityDragonPart))
/*     */           {
/* 254 */             net.minecraft.entity.IEntityMultiPart ientitymultipart = ((EntityDragonPart)par1Entity).field_70259_a;
/*     */             
/* 256 */             if ((ientitymultipart != null) && ((ientitymultipart instanceof EntityLivingBase)))
/*     */             {
/* 258 */               object = (EntityLivingBase)ientitymultipart;
/*     */             }
/*     */           }
/*     */           
/* 262 */           if ((itemstack != null) && ((object instanceof EntityLivingBase)))
/*     */           {
/* 264 */             itemstack.func_77961_a((EntityLivingBase)object, player);
/*     */             
/* 266 */             if (itemstack.field_77994_a <= 0)
/*     */             {
/* 268 */               player.func_71028_bD();
/*     */             }
/*     */           }
/*     */           
/* 272 */           if ((par1Entity instanceof EntityLivingBase))
/*     */           {
/*     */ 
/* 275 */             player.func_71064_a(StatList.field_75951_w, Math.round(f * 10.0F));
/*     */             
/* 277 */             if ((j > 0) && (flag2))
/*     */             {
/* 279 */               par1Entity.func_70015_d(j * 4);
/*     */             }
/* 281 */             else if (flag1)
/*     */             {
/* 283 */               par1Entity.func_70066_B();
/*     */             }
/*     */           }
/*     */           
/* 287 */           player.func_71020_j(0.3F);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/equipment/ItemElementalSword.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */