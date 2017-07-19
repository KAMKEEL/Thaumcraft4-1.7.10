/*     */ package thaumcraft.common.items;
/*     */ 
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.nodes.NodeModifier;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ import thaumcraft.common.tiles.TileNode;
/*     */ 
/*     */ public class ItemEldritchObject extends Item
/*     */ {
/*     */   public ItemEldritchObject()
/*     */   {
/*  33 */     func_77625_d(1);
/*  34 */     func_77627_a(true);
/*  35 */     func_77656_e(0);
/*  36 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*  39 */   public IIcon[] icon = new IIcon[5];
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  44 */     this.icon[0] = ir.func_94245_a("thaumcraft:eldritch_object");
/*  45 */     this.icon[1] = ir.func_94245_a("thaumcraft:crimson_rites");
/*  46 */     this.icon[2] = ir.func_94245_a("thaumcraft:eldritch_object_2");
/*  47 */     this.icon[3] = ir.func_94245_a("thaumcraft:eldritch_object_3");
/*  48 */     this.icon[4] = ir.func_94245_a("thaumcraft:ob_placer");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1)
/*     */   {
/*  54 */     if (par1 < this.icon.length) return this.icon[par1]; return this.icon[0];
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_77667_c(ItemStack par1ItemStack)
/*     */   {
/*  60 */     return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  66 */     par3List.add(new ItemStack(this, 1, 0));
/*  67 */     par3List.add(new ItemStack(this, 1, 1));
/*  68 */     par3List.add(new ItemStack(this, 1, 2));
/*  69 */     par3List.add(new ItemStack(this, 1, 3));
/*  70 */     par3List.add(new ItemStack(this, 1, 4));
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack stack)
/*     */   {
/*  76 */     switch (stack.func_77960_j()) {
/*  77 */     case 2:  return EnumRarity.rare;
/*  78 */     case 3:  return EnumRarity.epic;
/*     */     }
/*  80 */     return EnumRarity.uncommon;
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*     */   {
/*  85 */     super.func_77624_a(stack, player, list, par4);
/*  86 */     if (stack != null) {
/*  87 */       switch (stack.func_77960_j()) {
/*     */       case 0: 
/*  89 */         list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a("item.ItemEldritchObject.text.1"));
/*  90 */         break;
/*     */       case 1: 
/*  92 */         list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a("item.ItemEldritchObject.text.2"));
/*  93 */         list.add(EnumChatFormatting.DARK_BLUE + StatCollector.func_74838_a("item.ItemEldritchObject.text.3"));
/*  94 */         break;
/*     */       case 2: 
/*  96 */         list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a("item.ItemEldritchObject.text.4"));
/*  97 */         break;
/*     */       case 3: 
/*  99 */         list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a("item.ItemEldritchObject.text.5"));
/* 100 */         list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a("item.ItemEldritchObject.text.6"));
/* 101 */         break;
/*     */       case 4: 
/* 103 */         list.add("§oCreative Mode Only");
/*     */       }
/*     */       
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_77659_a(ItemStack stack, World par2World, EntityPlayer player)
/*     */   {
/* 112 */     if ((!par2World.field_72995_K) && (stack.func_77960_j() == 1) && (!ResearchManager.isResearchComplete(player.func_70005_c_(), "CRIMSON")))
/*     */     {
/* 114 */       PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketResearchComplete("CRIMSON"), (EntityPlayerMP)player);
/* 115 */       Thaumcraft.proxy.getResearchManager().completeResearch(player, "CRIMSON");
/* 116 */       par2World.func_72956_a(player, "thaumcraft:learn", 0.75F, 1.0F);
/*     */     }
/* 118 */     return stack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean onItemUseFirst(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
/*     */   {
/* 125 */     if (itemstack.func_77960_j() == 3) {
/* 126 */       TileEntity te = world.func_147438_o(x, y, z);
/* 127 */       if ((te != null) && ((te instanceof TileNode))) {
/* 128 */         player.func_71038_i();
/* 129 */         if (!world.field_72995_K) {
/* 130 */           itemstack.field_77994_a -= 1;
/* 131 */           TileNode node = (TileNode)te;
/* 132 */           boolean research = ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), "PRIMNODE");
/* 133 */           for (Aspect a : node.getAspects().getAspects()) {
/* 134 */             int m = node.getNodeVisBase(a);
/* 135 */             if (!a.isPrimal()) {
/* 136 */               if (world.field_73012_v.nextBoolean())
/* 137 */                 node.setNodeVisBase(a, (short)(m - 1));
/*     */             } else {
/* 139 */               m = m - 2 + world.field_73012_v.nextInt(research ? 9 : 6);
/* 140 */               node.setNodeVisBase(a, (short)m);
/*     */             }
/*     */           }
/*     */           
/* 144 */           for (Aspect a : Aspect.getPrimalAspects()) {
/* 145 */             int m = node.getNodeVisBase(a);
/* 146 */             int r = world.field_73012_v.nextInt(research ? 4 : 3);
/* 147 */             if ((r > 0) && (r > m)) {
/* 148 */               node.setNodeVisBase(a, (short)r);
/* 149 */               node.addToContainer(a, 1);
/*     */             }
/*     */           }
/*     */           
/* 153 */           if ((node.getNodeModifier() == NodeModifier.FADING) && (world.field_73012_v.nextBoolean())) {
/* 154 */             node.setNodeModifier(NodeModifier.PALE);
/*     */           }
/* 156 */           else if ((node.getNodeModifier() == NodeModifier.PALE) && (world.field_73012_v.nextBoolean())) {
/* 157 */             node.setNodeModifier(null);
/*     */           }
/* 159 */           else if ((node.getNodeModifier() == null) && (world.field_73012_v.nextInt(5) == 0)) {
/* 160 */             node.setNodeModifier(NodeModifier.BRIGHT);
/*     */           }
/* 162 */           world.func_147471_g(x, y, z);
/* 163 */           node.func_70296_d();
/* 164 */           world.func_72876_a(null, x + 0.5D, y + 1.5D, z + 0.5D, 3.0F + world.field_73012_v.nextFloat() * (research ? 3 : 5), true);
/* 165 */           for (int a = 0; a < 33; a++) {
/* 166 */             int xx = x + world.field_73012_v.nextInt(6) - world.field_73012_v.nextInt(6);
/* 167 */             int yy = y + world.field_73012_v.nextInt(6) - world.field_73012_v.nextInt(6);
/* 168 */             int zz = z + world.field_73012_v.nextInt(6) - world.field_73012_v.nextInt(6);
/* 169 */             if (world.func_147437_c(xx, yy, zz)) {
/* 170 */               if (yy < y) {
/* 171 */                 world.func_147465_d(xx, yy, zz, ConfigBlocks.blockFluxGoo, 8, 3);
/*     */               } else {
/* 173 */                 world.func_147465_d(xx, yy, zz, ConfigBlocks.blockFluxGas, 8, 3);
/*     */               }
/*     */             }
/*     */           }
/* 177 */           return true;
/*     */         }
/*     */       }
/* 180 */       return false;
/*     */     }
/* 182 */     if ((side == 1) && (itemstack.func_77960_j() == 4)) {
/* 183 */       player.func_71038_i();
/* 184 */       for (int a = 1; a <= 6; a++) if (!world.func_147437_c(x, y + a, z)) return false;
/* 185 */       world.func_147465_d(x, y + 1, z, ConfigBlocks.blockEldritch, 0, 3);
/* 186 */       world.func_147465_d(x, y + 3, z, ConfigBlocks.blockEldritch, 1, 3);
/* 187 */       world.func_147465_d(x, y + 4, z, ConfigBlocks.blockEldritch, 2, 3);
/* 188 */       world.func_147465_d(x, y + 5, z, ConfigBlocks.blockEldritch, 2, 3);
/* 189 */       world.func_147465_d(x, y + 6, z, ConfigBlocks.blockEldritch, 2, 3);
/* 190 */       world.func_147465_d(x, y + 7, z, ConfigBlocks.blockEldritch, 2, 3);
/* 191 */       return !world.field_72995_K;
/*     */     }
/*     */     
/* 194 */     return super.onItemUseFirst(itemstack, player, world, x, y, z, side, par8, par9, par10);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/ItemEldritchObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */