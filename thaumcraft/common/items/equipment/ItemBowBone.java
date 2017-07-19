/*     */ package thaumcraft.common.items.equipment;
/*     */ 
/*     */ import cpw.mods.fml.common.eventhandler.EventBus;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.player.ArrowLooseEvent;
/*     */ import net.minecraftforge.event.entity.player.ArrowNockEvent;
/*     */ 
/*     */ public class ItemBowBone extends net.minecraft.item.ItemBow implements thaumcraft.api.IRepairable
/*     */ {
/*  23 */   public static final String[] bowPullIconNameArray = { "pulling_0", "pulling_1", "pulling_2" };
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon[] iconArray;
/*     */   
/*     */   public ItemBowBone()
/*     */   {
/*  30 */     this.field_77777_bU = 1;
/*  31 */     func_77656_e(512);
/*  32 */     func_77637_a(thaumcraft.common.Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
/*     */   {
/*  37 */     int ticks = func_77626_a(stack) - count;
/*  38 */     if (ticks > 18) { player.func_71034_by();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_77615_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
/*     */   {
/*  47 */     int j = func_77626_a(par1ItemStack) - par4;
/*     */     
/*  49 */     ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, j);
/*  50 */     MinecraftForge.EVENT_BUS.post(event);
/*  51 */     if (event.isCanceled())
/*     */     {
/*  53 */       return;
/*     */     }
/*  55 */     j = event.charge;
/*     */     
/*  57 */     boolean flag = (par3EntityPlayer.field_71075_bZ.field_75098_d) || (EnchantmentHelper.func_77506_a(Enchantment.field_77342_w.field_77352_x, par1ItemStack) > 0);
/*     */     
/*  59 */     if ((flag) || (par3EntityPlayer.field_71071_by.func_146028_b(Items.field_151032_g)))
/*     */     {
/*  61 */       float f = j / 10.0F;
/*  62 */       f = (f * f + f * 2.0F) / 3.0F;
/*     */       
/*  64 */       if (f < 0.1D)
/*     */       {
/*  66 */         return;
/*     */       }
/*     */       
/*  69 */       if (f > 1.0F)
/*     */       {
/*  71 */         f = 1.0F;
/*     */       }
/*     */       
/*  74 */       EntityArrow entityarrow = new EntityArrow(par2World, par3EntityPlayer, f * 2.5F);
/*  75 */       entityarrow.func_70239_b(entityarrow.func_70242_d() + 0.5D);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  82 */       int k = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, par1ItemStack);
/*     */       
/*  84 */       if (k > 0)
/*     */       {
/*  86 */         entityarrow.func_70239_b(entityarrow.func_70242_d() + k * 0.5D + 0.5D);
/*     */       }
/*     */       
/*  89 */       int l = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, par1ItemStack);
/*     */       
/*  91 */       if (l > 0)
/*     */       {
/*  93 */         entityarrow.func_70240_a(l);
/*     */       }
/*     */       
/*  96 */       if (EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, par1ItemStack) > 0)
/*     */       {
/*  98 */         entityarrow.func_70015_d(100);
/*     */       }
/*     */       
/* 101 */       par1ItemStack.func_77972_a(1, par3EntityPlayer);
/* 102 */       par2World.func_72956_a(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (field_77697_d.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
/*     */       
/* 104 */       if (flag)
/*     */       {
/* 106 */         entityarrow.field_70251_a = 2;
/*     */       }
/*     */       else
/*     */       {
/* 110 */         par3EntityPlayer.field_71071_by.func_146026_a(Items.field_151032_g);
/*     */       }
/*     */       
/* 113 */       if (!par2World.field_72995_K)
/*     */       {
/* 115 */         par2World.func_72838_d(entityarrow);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
/*     */   {
/* 127 */     ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer, par1ItemStack);
/* 128 */     MinecraftForge.EVENT_BUS.post(event);
/* 129 */     if (event.isCanceled())
/*     */     {
/* 131 */       return event.result;
/*     */     }
/*     */     
/* 134 */     if ((par3EntityPlayer.field_71075_bZ.field_75098_d) || (par3EntityPlayer.field_71071_by.func_146028_b(Items.field_151032_g)) || (EnchantmentHelper.func_77506_a(Enchantment.field_77342_w.field_77352_x, par1ItemStack) > 0))
/*     */     {
/*     */ 
/* 137 */       par3EntityPlayer.func_71008_a(par1ItemStack, func_77626_a(par1ItemStack));
/*     */     }
/*     */     
/* 140 */     return par1ItemStack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_77619_b()
/*     */   {
/* 149 */     return 3;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister par1IconRegister)
/*     */   {
/* 156 */     this.field_77791_bV = par1IconRegister.func_94245_a("thaumcraft:bonebow");
/* 157 */     this.iconArray = new IIcon[bowPullIconNameArray.length];
/*     */     
/* 159 */     for (int i = 0; i < this.iconArray.length; i++)
/*     */     {
/* 161 */       this.iconArray[i] = par1IconRegister.func_94245_a("thaumcraft:bonebow_" + bowPullIconNameArray[i]);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
/*     */   {
/* 168 */     int j = stack.func_77988_m() - useRemaining;
/* 169 */     if (usingItem == null) { return this.field_77791_bV;
/*     */     }
/* 171 */     if (j >= 13)
/*     */     {
/* 173 */       return func_94599_c(2);
/*     */     }
/*     */     
/* 176 */     if (j > 7)
/*     */     {
/* 178 */       return func_94599_c(1);
/*     */     }
/*     */     
/* 181 */     if (j > 0)
/*     */     {
/* 183 */       return func_94599_c(0);
/*     */     }
/* 185 */     return this.field_77791_bV;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_94599_c(int par1)
/*     */   {
/* 192 */     return this.iconArray[par1];
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/equipment/ItemBowBone.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */