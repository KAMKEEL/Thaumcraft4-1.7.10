/*     */ package thaumcraft.common.items.relics;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagInt;
/*     */ import net.minecraft.nbt.NBTTagString;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.tiles.TileMirror;
/*     */ 
/*     */ public class ItemHandMirror extends Item
/*     */ {
/*     */   private IIcon icon;
/*     */   
/*     */   public ItemHandMirror()
/*     */   {
/*  36 */     func_77625_d(1);
/*  37 */     func_77627_a(false);
/*  38 */     func_77656_e(0);
/*  39 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister par1IconRegister)
/*     */   {
/*  46 */     this.icon = par1IconRegister.func_94245_a("thaumcraft:mirrorhand");
/*     */   }
/*     */   
/*     */ 
/*     */   public IIcon func_77617_a(int par1)
/*     */   {
/*  52 */     return this.icon;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  58 */     par3List.add(new ItemStack(this));
/*     */   }
/*     */   
/*     */   public boolean func_77651_p()
/*     */   {
/*  63 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  69 */     return EnumRarity.uncommon;
/*     */   }
/*     */   
/*     */   public boolean func_77636_d(ItemStack par1ItemStack)
/*     */   {
/*  74 */     return par1ItemStack.func_77942_o();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean onItemUseFirst(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
/*     */   {
/*  81 */     net.minecraft.block.Block bi = world.func_147439_a(x, y, z);
/*  82 */     if (bi == ConfigBlocks.blockMirror) {
/*  83 */       if (world.field_72995_K) {
/*  84 */         player.func_71038_i();
/*  85 */         return super.onItemUseFirst(itemstack, player, world, x, y, z, par7, par8, par9, par10);
/*     */       }
/*  87 */       TileEntity tm = world.func_147438_o(x, y, z);
/*  88 */       if ((tm != null) && ((tm instanceof TileMirror))) {
/*  89 */         itemstack.func_77983_a("linkX", new NBTTagInt(tm.field_145851_c));
/*  90 */         itemstack.func_77983_a("linkY", new NBTTagInt(tm.field_145848_d));
/*  91 */         itemstack.func_77983_a("linkZ", new NBTTagInt(tm.field_145849_e));
/*  92 */         itemstack.func_77983_a("linkDim", new NBTTagInt(world.field_73011_w.field_76574_g));
/*  93 */         itemstack.func_77983_a("dimname", new NBTTagString(net.minecraftforge.common.DimensionManager.getProvider(world.field_73011_w.field_76574_g).func_80007_l()));
/*  94 */         world.func_72908_a(x, y, z, "thaumcraft:jar", 1.0F, 2.0F);
/*  95 */         player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("tc.handmirrorlinked")));
/*  96 */         player.field_71069_bz.func_75142_b();
/*     */       }
/*     */       
/*  99 */       return true;
/*     */     }
/*     */     
/* 102 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
/*     */   {
/* 110 */     if ((!par2World.field_72995_K) && (par1ItemStack.func_77942_o()))
/*     */     {
/* 112 */       int lx = par1ItemStack.field_77990_d.func_74762_e("linkX");
/* 113 */       int ly = par1ItemStack.field_77990_d.func_74762_e("linkY");
/* 114 */       int lz = par1ItemStack.field_77990_d.func_74762_e("linkZ");
/* 115 */       int ldim = par1ItemStack.field_77990_d.func_74762_e("linkDim");
/*     */       
/* 117 */       World targetWorld = MinecraftServer.func_71276_C().func_71218_a(ldim);
/* 118 */       if (targetWorld == null) return super.func_77659_a(par1ItemStack, par2World, par3EntityPlayer);
/* 119 */       TileEntity te = targetWorld.func_147438_o(lx, ly, lz);
/* 120 */       if ((te == null) || (!(te instanceof TileMirror))) {
/* 121 */         par1ItemStack.func_77982_d(null);
/* 122 */         par2World.func_72956_a(par3EntityPlayer, "thaumcraft:zap", 1.0F, 0.8F);
/* 123 */         par3EntityPlayer.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("tc.handmirrorerror")));
/* 124 */         return super.func_77659_a(par1ItemStack, par2World, par3EntityPlayer);
/*     */       }
/*     */       
/* 127 */       par3EntityPlayer.openGui(Thaumcraft.instance, 16, par2World, MathHelper.func_76128_c(par3EntityPlayer.field_70165_t), MathHelper.func_76128_c(par3EntityPlayer.field_70163_u), MathHelper.func_76128_c(par3EntityPlayer.field_70161_v));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 133 */     return super.func_77659_a(par1ItemStack, par2World, par3EntityPlayer);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_77624_a(ItemStack item, EntityPlayer par2EntityPlayer, List list, boolean par4)
/*     */   {
/* 139 */     if (item.func_77942_o()) {
/* 140 */       int lx = item.field_77990_d.func_74762_e("linkX");
/* 141 */       int ly = item.field_77990_d.func_74762_e("linkY");
/* 142 */       int lz = item.field_77990_d.func_74762_e("linkZ");
/* 143 */       int ldim = item.field_77990_d.func_74762_e("linkDim");
/* 144 */       String dimname = item.field_77990_d.func_74779_i("dimname");
/* 145 */       list.add(StatCollector.func_74838_a("tc.handmirrorlinkedto") + " " + lx + "," + ly + "," + lz + " in " + dimname);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean transport(ItemStack mirror, ItemStack items, EntityPlayer player, World worldObj)
/*     */   {
/* 152 */     if (mirror.func_77942_o()) {
/* 153 */       int lx = mirror.field_77990_d.func_74762_e("linkX");
/* 154 */       int ly = mirror.field_77990_d.func_74762_e("linkY");
/* 155 */       int lz = mirror.field_77990_d.func_74762_e("linkZ");
/* 156 */       int ldim = mirror.field_77990_d.func_74762_e("linkDim");
/*     */       
/* 158 */       World targetWorld = MinecraftServer.func_71276_C().func_71218_a(ldim);
/* 159 */       if (targetWorld == null) return false;
/* 160 */       TileEntity te = targetWorld.func_147438_o(lx, ly, lz);
/* 161 */       if ((te == null) || (!(te instanceof TileMirror))) {
/* 162 */         mirror.func_77982_d(null);
/* 163 */         worldObj.func_72956_a(player, "thaumcraft:zap", 1.0F, 0.8F);
/* 164 */         player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("tc.handmirrorerror")));
/* 165 */         return false;
/*     */       }
/* 167 */       TileMirror tm = (TileMirror)te;
/*     */       
/* 169 */       ForgeDirection linkedFacing = ForgeDirection.getOrientation(targetWorld.func_72805_g(lx, ly, lz));
/*     */       
/* 171 */       EntityItem ie2 = new EntityItem(targetWorld, lx + 0.5D - linkedFacing.offsetX * 0.3D, ly + 0.5D - linkedFacing.offsetY * 0.3D, lz + 0.5D - linkedFacing.offsetZ * 0.3D, items.func_77946_l());
/*     */       
/*     */ 
/*     */ 
/* 175 */       ie2.field_70159_w = (linkedFacing.offsetX * 0.15F);
/* 176 */       ie2.field_70181_x = (linkedFacing.offsetY * 0.15F);
/* 177 */       ie2.field_70179_y = (linkedFacing.offsetZ * 0.15F);
/* 178 */       ie2.field_71088_bW = 20;
/* 179 */       targetWorld.func_72838_d(ie2);
/* 180 */       items = null;
/* 181 */       worldObj.func_72956_a(player, "mob.endermen.portal", 0.1F, 1.0F);
/* 182 */       targetWorld.func_147452_c(lx, ly, lz, ConfigBlocks.blockMirror, 1, 0);
/* 183 */       return true;
/*     */     }
/* 185 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/relics/ItemHandMirror.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */