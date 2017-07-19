/*     */ package thaumcraft.common.entities.golems;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ public class ItemTrunkSpawner extends Item
/*     */ {
/*     */   private IIcon icon;
/*     */   
/*     */   public ItemTrunkSpawner()
/*     */   {
/*  30 */     func_77625_d(1);
/*  31 */     func_77627_a(true);
/*  32 */     func_77656_e(0);
/*  33 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister par1IconRegister)
/*     */   {
/*  41 */     this.icon = par1IconRegister.func_94245_a("thaumcraft:blank");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1)
/*     */   {
/*  47 */     return this.icon;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  53 */     par3List.add(new ItemStack(this, 1, 0));
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*     */   {
/*  58 */     if (stack.func_77942_o()) {
/*  59 */       if (stack.field_77990_d.func_74764_b("upgrade")) {
/*  60 */         byte ba = stack.field_77990_d.func_74771_c("upgrade");
/*  61 */         String text = "§9";
/*  62 */         if (ba > -1) text = text + StatCollector.func_74838_a(new StringBuilder().append("item.ItemGolemUpgrade.").append(ba).append(".name").toString()) + " ";
/*  63 */         list.add(text);
/*     */       }
/*  65 */       if (stack.field_77990_d.func_74764_b("inventory")) {
/*  66 */         list.add(StatCollector.func_74838_a("item.TrunkSpawner.text.1"));
/*     */       }
/*     */     }
/*  69 */     super.func_77624_a(stack, player, list, par4);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_77648_a(ItemStack stack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
/*     */   {
/*  75 */     if (par3World.field_72995_K)
/*     */     {
/*  77 */       return true;
/*     */     }
/*     */     
/*     */ 
/*  81 */     Block i1 = par3World.func_147439_a(par4, par5, par6);
/*  82 */     par4 += net.minecraft.util.Facing.field_71586_b[par7];
/*  83 */     par5 += net.minecraft.util.Facing.field_71587_c[par7];
/*  84 */     par6 += net.minecraft.util.Facing.field_71585_d[par7];
/*  85 */     double d0 = 0.0D;
/*     */     
/*  87 */     if ((par7 == 1) && (!i1.isAir(par3World, par4, par5, par6)) && (i1.func_149645_b() == 11))
/*     */     {
/*  89 */       d0 = 0.5D;
/*     */     }
/*     */     
/*  92 */     Entity entity = new EntityTravelingTrunk(par3World);
/*     */     
/*  94 */     if ((entity != null) && ((entity instanceof net.minecraft.entity.EntityLivingBase)))
/*     */     {
/*  96 */       EntityLiving entityliving = (EntityLiving)entity;
/*  97 */       entity.func_70012_b(par4, par5 + d0, par6, MathHelper.func_76142_g(par3World.field_73012_v.nextFloat() * 360.0F), 0.0F);
/*  98 */       entityliving.field_70759_as = entityliving.field_70177_z;
/*  99 */       entityliving.field_70761_aq = entityliving.field_70177_z;
/*     */       
/* 101 */       ((EntityTravelingTrunk)entity).setOwner(par2EntityPlayer.func_70005_c_());
/* 102 */       if (stack.func_82837_s())
/*     */       {
/* 104 */         ((EntityLiving)entity).func_94058_c(stack.func_82833_r());
/*     */       }
/*     */       
/* 107 */       if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("upgrade"))) {
/* 108 */         ((EntityTravelingTrunk)entity).setUpgrade(stack.field_77990_d.func_74771_c("upgrade"));
/* 109 */         ((EntityTravelingTrunk)entity).setInvSize();
/*     */       }
/*     */       
/* 112 */       if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("inventory"))) {
/* 113 */         net.minecraft.nbt.NBTTagList nbttaglist = stack.field_77990_d.func_150295_c("inventory", 10);
/* 114 */         ((EntityTravelingTrunk)entity).inventory.readFromNBT(nbttaglist);
/*     */       }
/*     */       
/* 117 */       entityliving.func_110161_a((IEntityLivingData)null);
/* 118 */       par3World.func_72838_d(entity);
/* 119 */       entityliving.func_70642_aH();
/*     */       
/*     */ 
/* 122 */       if (!par2EntityPlayer.field_71075_bZ.field_75098_d)
/*     */       {
/* 124 */         stack.field_77994_a -= 1;
/*     */       }
/*     */     }
/*     */     
/* 128 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/golems/ItemTrunkSpawner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */