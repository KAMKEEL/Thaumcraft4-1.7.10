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
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*     */ import thaumcraft.common.tiles.TileJarFillable;
/*     */ 
/*     */ public class ItemJarFilled extends Item implements thaumcraft.api.aspects.IEssentiaContainerItem
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon icon;
/*     */   
/*     */   public ItemJarFilled()
/*     */   {
/*  30 */     func_77656_e(0);
/*  31 */     func_77625_d(1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  39 */     this.icon = ir.func_94245_a("thaumcraft:blank");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1) {
/*  44 */     return this.icon;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_77647_b(int par1)
/*     */   {
/*  50 */     return par1;
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*     */   {
/*  55 */     AspectList aspects = getAspects(stack);
/*  56 */     if ((aspects != null) && (aspects.size() > 0)) {
/*  57 */       for (Aspect tag : aspects.getAspectsSorted()) {
/*  58 */         if (Thaumcraft.proxy.playerKnowledge.hasDiscoveredAspect(player.func_70005_c_(), tag)) {
/*  59 */           list.add(tag.getName() + " x " + aspects.getAmount(tag));
/*     */         } else {
/*  61 */           list.add(net.minecraft.util.StatCollector.func_74838_a("tc.aspect.unknown"));
/*     */         }
/*     */       }
/*     */     }
/*  65 */     if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("AspectFilter"))) {
/*  66 */       String tf = stack.field_77990_d.func_74779_i("AspectFilter");
/*  67 */       Aspect tag = Aspect.getAspect(tf);
/*  68 */       if (Thaumcraft.proxy.playerKnowledge.hasDiscoveredAspect(player.func_70005_c_(), tag)) {
/*  69 */         list.add("§5" + tag.getName());
/*     */       } else {
/*  71 */         list.add("§5" + net.minecraft.util.StatCollector.func_74838_a("tc.aspect.unknown"));
/*     */       }
/*     */     }
/*  74 */     super.func_77624_a(stack, player, list, par4);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String func_77667_c(ItemStack stack)
/*     */   {
/*  81 */     if (stack.func_77960_j() == 3) {
/*  82 */       return super.func_77667_c(stack) + ".void";
/*     */     }
/*  84 */     return super.func_77667_c(stack);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
/*     */   {
/*  90 */     Block block = world.func_147439_a(x, y, z);
/*     */     
/*  92 */     if ((block == Blocks.field_150431_aC) && ((world.func_72805_g(x, y, z) & 0x7) < 1))
/*     */     {
/*  94 */       side = 1;
/*     */     }
/*  96 */     else if ((block != Blocks.field_150395_bd) && (block != Blocks.field_150329_H) && (block != Blocks.field_150330_I) && (!block.isReplaceable(world, x, y, z)))
/*     */     {
/*  98 */       if (side == 0)
/*     */       {
/* 100 */         y--;
/*     */       }
/*     */       
/* 103 */       if (side == 1)
/*     */       {
/* 105 */         y++;
/*     */       }
/*     */       
/* 108 */       if (side == 2)
/*     */       {
/* 110 */         z--;
/*     */       }
/*     */       
/* 113 */       if (side == 3)
/*     */       {
/* 115 */         z++;
/*     */       }
/*     */       
/* 118 */       if (side == 4)
/*     */       {
/* 120 */         x--;
/*     */       }
/*     */       
/* 123 */       if (side == 5)
/*     */       {
/* 125 */         x++;
/*     */       }
/*     */     }
/*     */     
/* 129 */     if (stack.field_77994_a == 0)
/*     */     {
/* 131 */       return false;
/*     */     }
/* 133 */     if (!player.func_82247_a(x, y, z, side, stack))
/*     */     {
/* 135 */       return false;
/*     */     }
/* 137 */     if ((y == 255) && (ConfigBlocks.blockJar.func_149688_o().func_76220_a()))
/*     */     {
/* 139 */       return false;
/*     */     }
/* 141 */     if (world.func_147472_a(ConfigBlocks.blockJar, x, y, z, false, side, player, stack))
/*     */     {
/* 143 */       Block var12 = ConfigBlocks.blockJar;
/* 144 */       int var13 = func_77647_b(stack.func_77960_j());
/* 145 */       int var14 = ConfigBlocks.blockJar.func_149660_a(world, x, y, z, side, par8, par9, par10, var13);
/*     */       
/* 147 */       if (placeBlockAt(stack, player, world, x, y, z, side, par8, par9, par10, var14))
/*     */       {
/* 149 */         net.minecraft.tileentity.TileEntity te = world.func_147438_o(x, y, z);
/* 150 */         if ((te != null) && ((te instanceof TileJarFillable)))
/*     */         {
/* 152 */           if (stack.func_77942_o()) {
/* 153 */             AspectList aspects = getAspects(stack);
/* 154 */             if ((aspects != null) && (aspects.size() == 1)) {
/* 155 */               ((TileJarFillable)te).amount = aspects.getAmount(aspects.getAspects()[0]);
/* 156 */               ((TileJarFillable)te).aspect = aspects.getAspects()[0];
/*     */             }
/* 158 */             String tf = stack.field_77990_d.func_74779_i("AspectFilter");
/* 159 */             if (tf != null) ((TileJarFillable)te).aspectFilter = Aspect.getAspect(tf);
/*     */           }
/*     */         }
/* 162 */         world.func_72908_a(x + 0.5F, y + 0.5F, z + 0.5F, var12.field_149762_H.func_150496_b(), (var12.field_149762_H.func_150497_c() + 1.0F) / 2.0F, var12.field_149762_H.func_150494_d() * 0.8F);
/* 163 */         stack.field_77994_a -= 1;
/*     */       }
/*     */       
/* 166 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 170 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
/*     */   {
/* 176 */     if (!world.func_147465_d(x, y, z, ConfigBlocks.blockJar, metadata, 3))
/*     */     {
/* 178 */       return false;
/*     */     }
/*     */     
/* 181 */     if (world.func_147439_a(x, y, z) == ConfigBlocks.blockJar)
/*     */     {
/* 183 */       ConfigBlocks.blockJar.func_149689_a(world, x, y, z, player, stack);
/* 184 */       ConfigBlocks.blockJar.func_149714_e(world, x, y, z, metadata);
/*     */     }
/*     */     
/* 187 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public AspectList getAspects(ItemStack itemstack)
/*     */   {
/* 193 */     if (itemstack.func_77942_o()) {
/* 194 */       AspectList aspects = new AspectList();
/* 195 */       aspects.readFromNBT(itemstack.func_77978_p());
/* 196 */       return aspects.size() > 0 ? aspects : null;
/*     */     }
/* 198 */     return null;
/*     */   }
/*     */   
/*     */   public Aspect getFilter(ItemStack itemstack) {
/* 202 */     if (itemstack.func_77942_o()) {
/* 203 */       return Aspect.getAspect(itemstack.field_77990_d.func_74779_i("AspectFilter"));
/*     */     }
/* 205 */     return null;
/*     */   }
/*     */   
/*     */   public void setAspects(ItemStack itemstack, AspectList aspects)
/*     */   {
/* 210 */     if (!itemstack.func_77942_o()) itemstack.func_77982_d(new NBTTagCompound());
/* 211 */     aspects.writeToNBT(itemstack.func_77978_p());
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/blocks/ItemJarFilled.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */