/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagInt;
/*     */ import net.minecraft.nbt.NBTTagString;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.common.DimensionManager;
/*     */ import thaumcraft.common.tiles.TileMirror;
/*     */ import thaumcraft.common.tiles.TileMirrorEssentia;
/*     */ 
/*     */ public class BlockMirrorItem extends ItemBlock
/*     */ {
/*     */   public BlockMirrorItem(Block par1)
/*     */   {
/*  31 */     super(par1);
/*  32 */     func_77656_e(0);
/*  33 */     func_77627_a(true);
/*     */   }
/*     */   
/*  36 */   public IIcon[] icon = new IIcon[5];
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister par1IconRegister)
/*     */   {
/*  41 */     this.icon[0] = par1IconRegister.func_94245_a("thaumcraft:mirrorframe");
/*  42 */     this.icon[1] = par1IconRegister.func_94245_a("thaumcraft:mirrorpane");
/*  43 */     this.icon[2] = par1IconRegister.func_94245_a("thaumcraft:mirrorpanetrans");
/*  44 */     this.icon[3] = par1IconRegister.func_94245_a("thaumcraft:mirrorpaneopen");
/*  45 */     this.icon[4] = par1IconRegister.func_94245_a("thaumcraft:mirrorframe2");
/*     */   }
/*     */   
/*     */   public IIcon func_77618_c(int par1, int par2)
/*     */   {
/*  50 */     if (par2 == 0) {
/*  51 */       return this.icon[4];
/*     */     }
/*  53 */     return this.icon[(par2 + par1 % 2 * 2)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_77651_p()
/*     */   {
/*  61 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_77667_c(ItemStack par1ItemStack)
/*     */   {
/*  67 */     int d = par1ItemStack.func_77960_j() < 6 ? 0 : 6;
/*  68 */     return super.func_77658_a() + "." + d;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
/*     */   {
/*  75 */     if (world.func_147439_a(x, y, z) == thaumcraft.common.config.ConfigBlocks.blockMirror) {
/*  76 */       if (world.field_72995_K) {
/*  77 */         player.func_71038_i();
/*  78 */         return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
/*     */       }
/*     */       
/*  81 */       if (stack.func_77960_j() <= 5) {
/*  82 */         TileEntity tm = world.func_147438_o(x, y, z);
/*  83 */         if ((tm != null) && ((tm instanceof TileMirror)) && (!((TileMirror)tm).isLinkValid())) {
/*  84 */           ItemStack st = stack.func_77946_l();
/*  85 */           st.field_77994_a = 1;
/*  86 */           st.func_77964_b(1);
/*  87 */           st.func_77983_a("linkX", new NBTTagInt(tm.field_145851_c));
/*  88 */           st.func_77983_a("linkY", new NBTTagInt(tm.field_145848_d));
/*  89 */           st.func_77983_a("linkZ", new NBTTagInt(tm.field_145849_e));
/*  90 */           st.func_77983_a("linkDim", new NBTTagInt(world.field_73011_w.field_76574_g));
/*  91 */           st.func_77983_a("dimname", new NBTTagString(DimensionManager.getProvider(world.field_73011_w.field_76574_g).func_80007_l()));
/*  92 */           world.func_72908_a(x, y, z, "thaumcraft:jar", 1.0F, 2.0F);
/*  93 */           if ((!player.field_71071_by.func_70441_a(st)) && 
/*  94 */             (!world.field_72995_K)) {
/*  95 */             world.func_72838_d(new EntityItem(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, st));
/*     */           }
/*     */           
/*  98 */           if (!player.field_71075_bZ.field_75098_d) {
/*  99 */             stack.field_77994_a -= 1;
/*     */           }
/* 101 */           player.field_71069_bz.func_75142_b();
/* 102 */         } else if ((tm != null) && ((tm instanceof TileMirror))) {
/* 103 */           player.func_145747_a(new net.minecraft.util.ChatComponentTranslation("§5§oThat mirror is already linked to a valid destination.", new Object[0]));
/*     */         }
/*     */       } else {
/* 106 */         TileEntity tm = world.func_147438_o(x, y, z);
/* 107 */         if ((tm != null) && ((tm instanceof TileMirrorEssentia)) && (!((TileMirrorEssentia)tm).isLinkValid())) {
/* 108 */           ItemStack st = stack.func_77946_l();
/* 109 */           st.field_77994_a = 1;
/* 110 */           st.func_77964_b(7);
/* 111 */           st.func_77983_a("linkX", new NBTTagInt(tm.field_145851_c));
/* 112 */           st.func_77983_a("linkY", new NBTTagInt(tm.field_145848_d));
/* 113 */           st.func_77983_a("linkZ", new NBTTagInt(tm.field_145849_e));
/* 114 */           st.func_77983_a("linkDim", new NBTTagInt(world.field_73011_w.field_76574_g));
/* 115 */           st.func_77983_a("dimname", new NBTTagString(DimensionManager.getProvider(world.field_73011_w.field_76574_g).func_80007_l()));
/* 116 */           world.func_72908_a(x, y, z, "thaumcraft:jar", 1.0F, 2.0F);
/* 117 */           if ((!player.field_71071_by.func_70441_a(st)) && 
/* 118 */             (!world.field_72995_K)) {
/* 119 */             world.func_72838_d(new EntityItem(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, st));
/*     */           }
/*     */           
/* 122 */           if (!player.field_71075_bZ.field_75098_d) {
/* 123 */             stack.field_77994_a -= 1;
/*     */           }
/* 125 */           player.field_71069_bz.func_75142_b();
/* 126 */         } else if ((tm != null) && ((tm instanceof TileMirrorEssentia))) {
/* 127 */           player.func_145747_a(new net.minecraft.util.ChatComponentTranslation("§5§oThat mirror is already linked to a valid destination.", new Object[0]));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 132 */     return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
/*     */   {
/* 140 */     boolean ret = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
/*     */     
/* 142 */     if ((ret) && (!world.field_72995_K))
/*     */     {
/* 144 */       if (metadata <= 5) {
/* 145 */         TileEntity te = world.func_147438_o(x, y, z);
/* 146 */         if ((te != null) && ((te instanceof TileMirror)) && 
/* 147 */           (stack.func_77942_o())) {
/* 148 */           ((TileMirror)te).linkX = stack.field_77990_d.func_74762_e("linkX");
/* 149 */           ((TileMirror)te).linkY = stack.field_77990_d.func_74762_e("linkY");
/* 150 */           ((TileMirror)te).linkZ = stack.field_77990_d.func_74762_e("linkZ");
/* 151 */           ((TileMirror)te).linkDim = stack.field_77990_d.func_74762_e("linkDim");
/* 152 */           ((TileMirror)te).restoreLink();
/*     */         }
/*     */       }
/*     */       else {
/* 156 */         TileEntity te = world.func_147438_o(x, y, z);
/* 157 */         if ((te != null) && ((te instanceof TileMirrorEssentia)) && 
/* 158 */           (stack.func_77942_o())) {
/* 159 */           ((TileMirrorEssentia)te).linkX = stack.field_77990_d.func_74762_e("linkX");
/* 160 */           ((TileMirrorEssentia)te).linkY = stack.field_77990_d.func_74762_e("linkY");
/* 161 */           ((TileMirrorEssentia)te).linkZ = stack.field_77990_d.func_74762_e("linkZ");
/* 162 */           ((TileMirrorEssentia)te).linkDim = stack.field_77990_d.func_74762_e("linkDim");
/* 163 */           ((TileMirrorEssentia)te).restoreLink();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 171 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getRenderPasses(int metadata)
/*     */   {
/* 178 */     return 2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_77623_v()
/*     */   {
/* 185 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/* 191 */     return EnumRarity.uncommon;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_77647_b(int par1)
/*     */   {
/* 198 */     return par1;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_77624_a(ItemStack item, EntityPlayer par2EntityPlayer, List list, boolean par4)
/*     */   {
/* 204 */     if (item.func_77942_o()) {
/* 205 */       int lx = item.field_77990_d.func_74762_e("linkX");
/* 206 */       int ly = item.field_77990_d.func_74762_e("linkY");
/* 207 */       int lz = item.field_77990_d.func_74762_e("linkZ");
/* 208 */       int ldim = item.field_77990_d.func_74762_e("linkDim");
/* 209 */       String dimname = item.field_77990_d.func_74779_i("dimname");
/* 210 */       list.add("Linked to " + lx + "," + ly + "," + lz + " in " + dimname);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/BlockMirrorItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */