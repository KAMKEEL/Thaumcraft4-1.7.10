/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.Block.SoundType;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagInt;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*     */ import thaumcraft.api.nodes.NodeModifier;
/*     */ import thaumcraft.api.nodes.NodeType;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.tiles.TileJarNode;
/*     */ 
/*     */ public class ItemJarNode extends Item implements IEssentiaContainerItem
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon icon;
/*     */   
/*     */   public ItemJarNode()
/*     */   {
/*  34 */     func_77656_e(0);
/*  35 */     func_77625_d(1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  43 */     this.icon = ir.func_94245_a("thaumcraft:blank");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1) {
/*  48 */     return this.icon;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_77647_b(int par1)
/*     */   {
/*  54 */     return par1;
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*     */   {
/*  59 */     String desc = "§9" + StatCollector.func_74838_a(new StringBuilder().append("nodetype.").append(getNodeType(stack)).append(".name").toString());
/*  60 */     if (getNodeModifier(stack) != null) {
/*  61 */       desc = desc + ", " + StatCollector.func_74838_a(new StringBuilder().append("nodemod.").append(getNodeModifier(stack)).append(".name").toString());
/*     */     }
/*  63 */     list.add(desc);
/*  64 */     AspectList aspects = getAspects(stack);
/*  65 */     if ((aspects != null) && (aspects.size() > 0)) {
/*  66 */       for (Aspect tag : aspects.getAspectsSorted()) {
/*  67 */         if (Thaumcraft.proxy.playerKnowledge.hasDiscoveredAspect(player.func_70005_c_(), tag)) {
/*  68 */           list.add(tag.getName() + " x " + aspects.getAmount(tag));
/*     */         } else {
/*  70 */           list.add(StatCollector.func_74838_a("tc.aspect.unknown"));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  75 */     super.func_77624_a(stack, player, list, par4);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
/*     */   {
/*  81 */     Block var11 = world.func_147439_a(x, y, z);
/*     */     
/*  83 */     if (var11 == Blocks.field_150431_aC)
/*     */     {
/*  85 */       side = 1;
/*     */     }
/*  87 */     else if ((var11 != Blocks.field_150395_bd) && (var11 != Blocks.field_150329_H) && (var11 != Blocks.field_150330_I) && ((var11.isAir(world, x, y, z)) || (!var11.isReplaceable(world, x, y, z))))
/*     */     {
/*     */ 
/*  90 */       if (side == 0)
/*     */       {
/*  92 */         y--;
/*     */       }
/*     */       
/*  95 */       if (side == 1)
/*     */       {
/*  97 */         y++;
/*     */       }
/*     */       
/* 100 */       if (side == 2)
/*     */       {
/* 102 */         z--;
/*     */       }
/*     */       
/* 105 */       if (side == 3)
/*     */       {
/* 107 */         z++;
/*     */       }
/*     */       
/* 110 */       if (side == 4)
/*     */       {
/* 112 */         x--;
/*     */       }
/*     */       
/* 115 */       if (side == 5)
/*     */       {
/* 117 */         x++;
/*     */       }
/*     */     }
/*     */     
/* 121 */     if (stack.field_77994_a == 0)
/*     */     {
/* 123 */       return false;
/*     */     }
/* 125 */     if (!player.func_82247_a(x, y, z, side, stack))
/*     */     {
/* 127 */       return false;
/*     */     }
/* 129 */     if ((y == 255) && (ConfigBlocks.blockJar.func_149688_o().func_76220_a()))
/*     */     {
/* 131 */       return false;
/*     */     }
/* 133 */     if (world.func_147472_a(ConfigBlocks.blockJar, x, y, z, false, side, player, stack))
/*     */     {
/* 135 */       Block var12 = ConfigBlocks.blockJar;
/* 136 */       int var13 = 2;
/* 137 */       int var14 = ConfigBlocks.blockJar.func_149660_a(world, x, y, z, side, par8, par9, par10, var13);
/*     */       
/* 139 */       if (placeBlockAt(stack, player, world, x, y, z, side, par8, par9, par10, var14))
/*     */       {
/* 141 */         net.minecraft.tileentity.TileEntity te = world.func_147438_o(x, y, z);
/* 142 */         if ((te != null) && ((te instanceof TileJarNode)))
/*     */         {
/* 144 */           if (stack.func_77942_o()) {
/* 145 */             AspectList aspects = getAspects(stack);
/* 146 */             if (aspects != null) {
/* 147 */               ((TileJarNode)te).setAspects(aspects);
/* 148 */               ((TileJarNode)te).setNodeType(getNodeType(stack));
/* 149 */               ((TileJarNode)te).setNodeModifier(getNodeModifier(stack));
/* 150 */               ((TileJarNode)te).setId(getNodeId(stack));
/*     */             }
/*     */           }
/*     */         }
/* 154 */         world.func_72908_a(x + 0.5F, y + 0.5F, z + 0.5F, var12.field_149762_H.func_150498_e(), (var12.field_149762_H.func_150497_c() + 1.0F) / 2.0F, var12.field_149762_H.func_150494_d() * 0.8F);
/* 155 */         stack.field_77994_a -= 1;
/*     */       }
/*     */       
/* 158 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 162 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
/*     */   {
/* 168 */     if (!world.func_147465_d(x, y, z, ConfigBlocks.blockJar, metadata, 3))
/*     */     {
/* 170 */       return false;
/*     */     }
/*     */     
/* 173 */     if (world.func_147439_a(x, y, z) == ConfigBlocks.blockJar)
/*     */     {
/* 175 */       ConfigBlocks.blockJar.func_149689_a(world, x, y, z, player, stack);
/* 176 */       ConfigBlocks.blockJar.func_149714_e(world, x, y, z, metadata);
/*     */     }
/*     */     
/* 179 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public AspectList getAspects(ItemStack itemstack)
/*     */   {
/* 185 */     if (itemstack.func_77942_o()) {
/* 186 */       AspectList aspects = new AspectList();
/* 187 */       aspects.readFromNBT(itemstack.func_77978_p());
/* 188 */       return aspects.size() > 0 ? aspects : null;
/*     */     }
/* 190 */     return null;
/*     */   }
/*     */   
/*     */   public void setAspects(ItemStack itemstack, AspectList aspects)
/*     */   {
/* 195 */     if (!itemstack.func_77942_o()) itemstack.func_77982_d(new NBTTagCompound());
/* 196 */     aspects.writeToNBT(itemstack.func_77978_p());
/*     */   }
/*     */   
/*     */   public void setNodeAttributes(ItemStack itemstack, NodeType type, NodeModifier mod, String id) {
/* 200 */     if (!itemstack.func_77942_o()) itemstack.func_77982_d(new NBTTagCompound());
/* 201 */     itemstack.func_77983_a("nodetype", new NBTTagInt(type.ordinal()));
/* 202 */     if (mod != null) itemstack.func_77983_a("nodemod", new NBTTagInt(mod.ordinal()));
/* 203 */     itemstack.func_77983_a("nodeid", new net.minecraft.nbt.NBTTagString(id));
/*     */   }
/*     */   
/*     */   public NodeType getNodeType(ItemStack itemstack) {
/* 207 */     if (!itemstack.func_77942_o()) return null;
/* 208 */     return NodeType.values()[itemstack.func_77978_p().func_74762_e("nodetype")];
/*     */   }
/*     */   
/*     */   public NodeModifier getNodeModifier(ItemStack itemstack) {
/* 212 */     if ((!itemstack.func_77942_o()) || (!itemstack.func_77978_p().func_74764_b("nodemod"))) return null;
/* 213 */     return NodeModifier.values()[itemstack.func_77978_p().func_74762_e("nodemod")];
/*     */   }
/*     */   
/*     */   public String getNodeId(ItemStack itemstack) {
/* 217 */     if (!itemstack.func_77942_o()) return "0";
/* 218 */     return itemstack.func_77978_p().func_74779_i("nodeid");
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/ItemJarNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */