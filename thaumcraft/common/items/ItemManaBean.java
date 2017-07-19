/*     */ package thaumcraft.common.items;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemFood;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ import net.minecraftforge.common.BiomeDictionary.Type;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.lib.network.playerdata.PacketAspectPool;
/*     */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*     */ import thaumcraft.common.tiles.TileManaPod;
/*     */ 
/*     */ public class ItemManaBean extends ItemFood implements IEssentiaContainerItem
/*     */ {
/*     */   public final int field_77855_a;
/*     */   public IIcon icon;
/*     */   
/*     */   public ItemManaBean()
/*     */   {
/*  42 */     super(1, 0.5F, true);
/*  43 */     this.field_77855_a = 10;
/*  44 */     func_77625_d(64);
/*  45 */     func_77627_a(true);
/*  46 */     func_77656_e(0);
/*  47 */     func_77637_a(Thaumcraft.tabTC);
/*  48 */     func_77848_i();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_77626_a(ItemStack par1ItemStack)
/*     */   {
/*  56 */     return this.field_77855_a;
/*     */   }
/*     */   
/*     */   protected void func_77849_c(ItemStack stack, World world, EntityPlayer player)
/*     */   {
/*  61 */     if (!world.field_72995_K) {
/*  62 */       Potion p = Potion.field_76425_a[world.field_73012_v.nextInt(Potion.field_76425_a.length)];
/*  63 */       if (p != null) {
/*  64 */         if (p.func_76403_b()) {
/*  65 */           p.func_76402_a(player, player, 2, 3.0D);
/*     */         } else {
/*  67 */           player.func_70690_d(new PotionEffect(p.field_76415_H, 160 + world.field_73012_v.nextInt(80), 0));
/*     */         }
/*     */       }
/*  70 */       if (world.field_73012_v.nextFloat() < 0.25F) {
/*  71 */         AspectList al = ((ItemManaBean)stack.func_77973_b()).getAspects(stack);
/*  72 */         if ((al != null) && (al.size() > 0)) {
/*  73 */           Thaumcraft.proxy.playerKnowledge.addAspectPool(player.func_70005_c_(), al.getAspects()[0], (short)1);
/*  74 */           thaumcraft.common.lib.research.ResearchManager.scheduleSave(player);
/*  75 */           thaumcraft.common.lib.network.PacketHandler.INSTANCE.sendTo(new PacketAspectPool(al.getAspects()[0].getTag(), Short.valueOf((short)1), Short.valueOf(Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.func_70005_c_(), al.getAspects()[0]))), (net.minecraft.entity.player.EntityPlayerMP)player);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  88 */     this.icon = ir.func_94245_a("thaumcraft:mana_bean");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1) {
/*  93 */     return this.icon;
/*     */   }
/*     */   
/*  96 */   Random rand = new Random();
/*  97 */   static Aspect[] displayAspects = (Aspect[])Aspect.aspects.values().toArray(new Aspect[0]);
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/* 102 */     par3List.add(new ItemStack(this, 1, 0));
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*     */   {
/* 107 */     AspectList aspects = getAspects(stack);
/* 108 */     if ((aspects != null) && (aspects.size() > 0)) {
/* 109 */       for (Aspect tag : aspects.getAspectsSorted()) {
/* 110 */         if (Thaumcraft.proxy.playerKnowledge.hasDiscoveredAspect(player.func_70005_c_(), tag)) {
/* 111 */           list.add(tag.getName() + " x" + aspects.getAmount(tag));
/*     */         } else {
/* 113 */           list.add(net.minecraft.util.StatCollector.func_74838_a("tc.aspect.unknown"));
/*     */         }
/*     */       }
/*     */     }
/* 117 */     super.func_77624_a(stack, player, list, par4);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_82790_a(ItemStack stack, int par2)
/*     */   {
/* 124 */     if (getAspects(stack) != null) {
/* 125 */       return getAspects(stack).getAspects()[0].getColor();
/*     */     }
/* 127 */     int idx = (int)(System.currentTimeMillis() / 500L % displayAspects.length);
/* 128 */     return displayAspects[idx].getColor();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_77663_a(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
/*     */   {
/* 135 */     if ((!par2World.field_72995_K) && (!par1ItemStack.func_77942_o())) {
/* 136 */       setAspects(par1ItemStack, new AspectList().add(displayAspects[this.rand.nextInt(displayAspects.length)], 1));
/*     */     }
/* 138 */     super.func_77663_a(par1ItemStack, par2World, par3Entity, par4, par5);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_77622_d(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
/*     */   {
/* 144 */     if (!par1ItemStack.func_77942_o()) {
/* 145 */       setAspects(par1ItemStack, new AspectList().add(displayAspects[this.rand.nextInt(displayAspects.length)], 1));
/*     */     }
/*     */   }
/*     */   
/*     */   public AspectList getAspects(ItemStack itemstack)
/*     */   {
/* 151 */     if (itemstack.func_77942_o()) {
/* 152 */       AspectList aspects = new AspectList();
/* 153 */       aspects.readFromNBT(itemstack.func_77978_p());
/* 154 */       return aspects.size() > 0 ? aspects : null;
/*     */     }
/* 156 */     return null;
/*     */   }
/*     */   
/*     */   public void setAspects(ItemStack itemstack, AspectList aspects)
/*     */   {
/* 161 */     if (!itemstack.func_77942_o())
/* 162 */       itemstack.func_77982_d(new net.minecraft.nbt.NBTTagCompound());
/* 163 */     aspects.writeToNBT(itemstack.func_77978_p());
/*     */   }
/*     */   
/*     */   public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
/*     */   {
/* 168 */     if ((!par2EntityPlayer.func_82247_a(par4, par5, par6, par7, par1ItemStack)) || (par7 != 0))
/*     */     {
/* 170 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 175 */     net.minecraft.world.biome.BiomeGenBase biome = par3World.func_72807_a(par4, par6);
/* 176 */     boolean magicBiome = false;
/* 177 */     if (biome != null) {
/* 178 */       magicBiome = BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.MAGICAL);
/*     */     }
/* 180 */     if (!magicBiome) { return false;
/*     */     }
/* 182 */     Block i1 = par3World.func_147439_a(par4, par5, par6);
/*     */     
/* 184 */     if ((i1 == Blocks.field_150364_r) || (i1 == Blocks.field_150363_s) || (i1 == ConfigBlocks.blockMagicalLog))
/*     */     {
/* 186 */       par5--;
/* 187 */       if (par3World.func_147437_c(par4, par5, par6))
/*     */       {
/* 189 */         int k1 = ConfigBlocks.blockManaPod.func_149660_a(par3World, par4, par5, par6, par7, par8, par9, par10, 0);
/* 190 */         par3World.func_147465_d(par4, par5, par6, ConfigBlocks.blockManaPod, k1, 2);
/*     */         
/* 192 */         TileEntity tile = par3World.func_147438_o(par4, par5, par6);
/* 193 */         if ((tile != null) && ((tile instanceof TileManaPod)) && (getAspects(par1ItemStack) != null) && (getAspects(par1ItemStack).size() > 0))
/*     */         {
/*     */ 
/*     */ 
/* 197 */           ((TileManaPod)tile).aspect = getAspects(par1ItemStack).getAspects()[0];
/*     */         }
/*     */         
/* 200 */         if (!par2EntityPlayer.field_71075_bZ.field_75098_d)
/*     */         {
/* 202 */           par1ItemStack.field_77994_a -= 1;
/*     */         }
/*     */       }
/*     */       
/* 206 */       return true;
/*     */     }
/*     */     
/* 209 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/ItemManaBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */