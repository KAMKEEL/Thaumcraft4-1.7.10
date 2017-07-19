/*     */ package thaumcraft.common.items;
/*     */ 
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.Block.SoundType;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.entities.EntityAspectOrb;
/*     */ import thaumcraft.common.entities.projectile.EntityAlumentum;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.playerdata.PacketAspectPool;
/*     */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ 
/*     */ public class ItemResource extends Item implements IEssentiaContainerItem
/*     */ {
/*     */   public ItemResource()
/*     */   {
/*  43 */     func_77625_d(64);
/*  44 */     func_77627_a(true);
/*  45 */     func_77656_e(0);
/*  46 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*  49 */   public IIcon[] icon = new IIcon[19];
/*     */   public IIcon iconOverlay;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir) {
/*  54 */     this.icon[0] = ir.func_94245_a("thaumcraft:alumentum");
/*  55 */     this.icon[1] = ir.func_94245_a("thaumcraft:nitor");
/*  56 */     this.icon[2] = ir.func_94245_a("thaumcraft:thaumiumingot");
/*  57 */     this.icon[3] = ir.func_94245_a("thaumcraft:quicksilver");
/*  58 */     this.icon[4] = ir.func_94245_a("thaumcraft:tallow");
/*  59 */     this.icon[5] = ir.func_94245_a("thaumcraft:brain");
/*  60 */     this.icon[6] = ir.func_94245_a("thaumcraft:amber");
/*  61 */     this.icon[7] = ir.func_94245_a("thaumcraft:cloth");
/*  62 */     this.icon[8] = ir.func_94245_a("thaumcraft:filter");
/*  63 */     this.icon[9] = ir.func_94245_a("thaumcraft:knowledgefragment");
/*  64 */     this.icon[10] = ir.func_94245_a("thaumcraft:mirrorglass");
/*  65 */     this.icon[11] = ir.func_94245_a("thaumcraft:taint_slime");
/*  66 */     this.icon[12] = ir.func_94245_a("thaumcraft:taint_tendril");
/*  67 */     this.icon[13] = ir.func_94245_a("thaumcraft:label");
/*  68 */     this.iconOverlay = ir.func_94245_a("thaumcraft:label_over");
/*  69 */     this.icon[14] = ir.func_94245_a("thaumcraft:dust");
/*  70 */     this.icon[15] = ir.func_94245_a("thaumcraft:charm");
/*  71 */     this.icon[16] = ir.func_94245_a("thaumcraft:voidingot");
/*  72 */     this.icon[17] = ir.func_94245_a("thaumcraft:voidseed");
/*  73 */     this.icon[18] = ir.func_94245_a("thaumcraft:coin");
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1)
/*     */   {
/*  80 */     return this.icon[par1];
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getRenderPasses(int metadata)
/*     */   {
/*  86 */     return metadata == 13 ? 2 : 1;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon getIcon(ItemStack stack, int pass)
/*     */   {
/*  92 */     return (pass == 0) || (getAspects(stack) == null) ? func_77617_a(stack.func_77960_j()) : this.iconOverlay;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_82790_a(ItemStack stack, int par2)
/*     */   {
/* 100 */     if ((par2 == 1) && (stack.func_77960_j() == 13) && (getAspects(stack) != null)) {
/* 101 */       return getAspects(stack).getAspects()[0].getColor();
/*     */     }
/* 103 */     return 16777215;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77623_v()
/*     */   {
/* 110 */     return true;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/* 116 */     for (int a = 0; a <= 18; a++) {
/* 117 */       if (a != 5) {
/* 118 */         par3List.add(new ItemStack(this, 1, a));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public String func_77667_c(ItemStack par1ItemStack)
/*     */   {
/* 125 */     return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_77663_a(ItemStack stack, World world, Entity entity, int par4, boolean par5)
/*     */   {
/* 134 */     super.func_77663_a(stack, world, entity, par4, par5);
/*     */     
/* 136 */     if ((!entity.field_70170_p.field_72995_K) && ((stack.func_77960_j() == 11) || (stack.func_77960_j() == 12)) && ((entity instanceof EntityLivingBase)) && (!((EntityLivingBase)entity).func_70662_br()) && (!((EntityLivingBase)entity).func_82165_m(Config.potionTaintPoisonID)) && (world.field_73012_v.nextInt(4321) <= stack.field_77994_a))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 141 */       ((EntityLivingBase)entity).func_70690_d(new net.minecraft.potion.PotionEffect(Config.potionTaintPoisonID, 120, 0, false));
/*     */       
/* 143 */       if ((entity instanceof EntityPlayer)) {
/* 144 */         String s = StatCollector.func_74838_a("tc.taint_item_poison").replace("%s", "§5§o" + stack.func_82833_r() + "§r");
/* 145 */         ((EntityPlayer)entity).func_145747_a(new ChatComponentTranslation(s, new Object[0]));
/* 146 */         thaumcraft.common.lib.utils.InventoryUtils.consumeInventoryItem((EntityPlayer)entity, stack.func_77973_b(), stack.func_77960_j());
/*     */       }
/*     */     }
/* 149 */     else if ((!entity.field_70170_p.field_72995_K) && (stack.func_77960_j() == 15)) {
/* 150 */       int r = world.field_73012_v.nextInt(20000);
/* 151 */       if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("blurb"))) {
/* 152 */         stack.field_77990_d.func_82580_o("blurb");
/*     */       }
/* 154 */       if (r < 20) {
/* 155 */         Aspect aspect = null;
/* 156 */         switch (world.field_73012_v.nextInt(6)) {
/* 157 */         case 0:  aspect = Aspect.AIR; break;
/* 158 */         case 1:  aspect = Aspect.EARTH; break;
/* 159 */         case 2:  aspect = Aspect.FIRE; break;
/* 160 */         case 3:  aspect = Aspect.WATER; break;
/* 161 */         case 4:  aspect = Aspect.ORDER; break;
/* 162 */         case 5:  aspect = Aspect.ENTROPY;
/*     */         }
/* 164 */         if (aspect != null) {
/* 165 */           EntityAspectOrb orb = new EntityAspectOrb(world, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, aspect, 1);
/* 166 */           world.func_72838_d(orb);
/*     */         }
/*     */       }
/* 169 */       else if ((r == 42) && ((entity instanceof EntityPlayer)) && (!ResearchManager.isResearchComplete(((EntityPlayer)entity).func_70005_c_(), "FOCUSPRIMAL")) && (!ResearchManager.isResearchComplete(((EntityPlayer)entity).func_70005_c_(), "@FOCUSPRIMAL")))
/*     */       {
/*     */ 
/* 172 */         ((EntityPlayer)entity).func_145747_a(new ChatComponentTranslation("§5§o" + StatCollector.func_74838_a("tc.primalcharm.trigger"), new Object[0]));
/* 173 */         PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketResearchComplete("@FOCUSPRIMAL"), (EntityPlayerMP)entity);
/* 174 */         Thaumcraft.proxy.getResearchManager().completeResearch((EntityPlayer)entity, "@FOCUSPRIMAL");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_77648_a(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
/*     */   {
/* 183 */     if (itemstack.func_77960_j() == 1) {
/* 184 */       Block var11 = world.func_147439_a(x, y, z);
/*     */       
/* 186 */       if ((var11 == Blocks.field_150431_aC) && ((world.func_72805_g(x, y, z) & 0x7) < 1))
/*     */       {
/* 188 */         par7 = 1;
/*     */       }
/* 190 */       else if ((var11 != Blocks.field_150395_bd) && (var11 != Blocks.field_150329_H) && (var11 != Blocks.field_150330_I) && (!var11.isReplaceable(world, x, y, z)))
/*     */       {
/*     */ 
/* 193 */         if (par7 == 0)
/*     */         {
/* 195 */           y--;
/*     */         }
/*     */         
/* 198 */         if (par7 == 1)
/*     */         {
/* 200 */           y++;
/*     */         }
/*     */         
/* 203 */         if (par7 == 2)
/*     */         {
/* 205 */           z--;
/*     */         }
/*     */         
/* 208 */         if (par7 == 3)
/*     */         {
/* 210 */           z++;
/*     */         }
/*     */         
/* 213 */         if (par7 == 4)
/*     */         {
/* 215 */           x--;
/*     */         }
/*     */         
/* 218 */         if (par7 == 5)
/*     */         {
/* 220 */           x++;
/*     */         }
/*     */       }
/*     */       
/* 224 */       if (itemstack.field_77994_a == 0)
/*     */       {
/* 226 */         return false;
/*     */       }
/* 228 */       if (!player.func_82247_a(x, y, z, par7, itemstack))
/*     */       {
/* 230 */         return false;
/*     */       }
/* 232 */       if (world.func_147472_a(ConfigBlocks.blockAiry, x, y, z, false, par7, player, itemstack))
/*     */       {
/* 234 */         if (placeBlockAt(itemstack, player, world, x, y, z, par7, par8, par9, par10, ConfigBlocks.blockAiry, itemstack.func_77960_j()))
/*     */         {
/* 236 */           world.func_72908_a(x + 0.5F, y + 0.5F, z + 0.5F, ConfigBlocks.blockAiry.field_149762_H.func_150498_e(), (ConfigBlocks.blockAiry.field_149762_H.func_150497_c() + 1.0F) / 2.0F, ConfigBlocks.blockAiry.field_149762_H.func_150494_d() * 0.8F);
/* 237 */           itemstack.field_77994_a -= 1;
/* 238 */           return true;
/*     */         }
/* 240 */         return false;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 245 */       return false;
/*     */     }
/*     */     
/* 248 */     return super.func_77648_a(itemstack, player, world, x, y, z, par7, par8, par9, par10);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, Block bid, int metadata)
/*     */   {
/* 254 */     if (!world.func_147465_d(x, y, z, bid, metadata, 3))
/*     */     {
/* 256 */       return false;
/*     */     }
/*     */     
/* 259 */     if (world.func_147439_a(x, y, z) == bid)
/*     */     {
/* 261 */       bid.func_149689_a(world, x, y, z, player, stack);
/* 262 */       bid.func_149714_e(world, x, y, z, metadata);
/*     */     }
/*     */     
/* 265 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer player)
/*     */   {
/* 271 */     if (itemstack.func_77960_j() == 0) {
/* 272 */       if (!player.field_71075_bZ.field_75098_d)
/*     */       {
/* 274 */         itemstack.field_77994_a -= 1;
/*     */       }
/* 276 */       world.func_72956_a(player, "random.bow", 0.3F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
/* 277 */       if (!world.field_72995_K)
/*     */       {
/* 279 */         world.func_72838_d(new EntityAlumentum(world, player));
/*     */       }
/*     */     }
/* 282 */     else if (itemstack.func_77960_j() == 9) {
/* 283 */       if (!player.field_71075_bZ.field_75098_d)
/*     */       {
/* 285 */         itemstack.field_77994_a -= 1;
/*     */       }
/* 287 */       if (!world.field_72995_K) {
/* 288 */         for (Aspect a : Aspect.getPrimalAspects()) {
/* 289 */           short q = (short)(world.field_73012_v.nextInt(2) + 1);
/* 290 */           Thaumcraft.proxy.playerKnowledge.addAspectPool(player.func_70005_c_(), a, q);
/* 291 */           ResearchManager.scheduleSave(player);
/* 292 */           PacketHandler.INSTANCE.sendTo(new PacketAspectPool(a.getTag(), Short.valueOf(q), Short.valueOf(Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.func_70005_c_(), a))), (EntityPlayerMP)player);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 299 */     return itemstack;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*     */   {
/* 305 */     AspectList aspects = getAspects(stack);
/* 306 */     if ((aspects != null) && (aspects.size() > 0)) {
/* 307 */       for (Aspect tag : aspects.getAspectsSorted()) {
/* 308 */         if (Thaumcraft.proxy.playerKnowledge.hasDiscoveredAspect(player.func_70005_c_(), tag)) {
/* 309 */           list.add(tag.getName());
/*     */         } else {
/* 311 */           list.add(StatCollector.func_74838_a("tc.aspect.unknown"));
/*     */         }
/*     */       }
/*     */     }
/* 315 */     if (stack.func_77960_j() == 15) {
/* 316 */       Random rand = new Random(stack.hashCode() + player.field_70173_aa / 120);
/* 317 */       int r = rand.nextInt(200);
/* 318 */       if (r < 25) {
/* 319 */         list.add("§6" + StatCollector.func_74838_a(new StringBuilder().append("tc.primalcharm.").append(rand.nextInt(5)).toString()));
/*     */       }
/*     */     }
/* 322 */     super.func_77624_a(stack, player, list, par4);
/*     */   }
/*     */   
/*     */   public AspectList getAspects(ItemStack itemstack)
/*     */   {
/* 327 */     if (itemstack.func_77942_o()) {
/* 328 */       AspectList aspects = new AspectList();
/* 329 */       aspects.readFromNBT(itemstack.func_77978_p());
/* 330 */       return aspects.size() > 0 ? aspects : null;
/*     */     }
/* 332 */     return null;
/*     */   }
/*     */   
/*     */   public void setAspects(ItemStack itemstack, AspectList aspects)
/*     */   {
/* 337 */     if (!itemstack.func_77942_o())
/* 338 */       itemstack.func_77982_d(new NBTTagCompound());
/* 339 */     aspects.writeToNBT(itemstack.func_77978_p());
/*     */   }
/*     */   
/*     */ 
/*     */   public int getItemStackLimit(ItemStack stack)
/*     */   {
/* 345 */     return stack.func_77960_j() == 15 ? 1 : super.getItemStackLimit(stack);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/ItemResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */