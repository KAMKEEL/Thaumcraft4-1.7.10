/*     */ package thaumcraft.common.items;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.tiles.TileAlembic;
/*     */ import thaumcraft.common.tiles.TileJarFillable;
/*     */ 
/*     */ public class ItemEssence extends Item implements IEssentiaContainerItem
/*     */ {
/*     */   public IIcon icon;
/*     */   public IIcon iconOverlay;
/*     */   
/*     */   public ItemEssence()
/*     */   {
/*  31 */     func_77625_d(64);
/*  32 */     func_77627_a(true);
/*  33 */     func_77656_e(0);
/*  34 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  42 */     this.icon = ir.func_94245_a("thaumcraft:phial");
/*  43 */     this.iconOverlay = ir.func_94245_a("thaumcraft:essence");
/*     */   }
/*     */   
/*     */   public IIcon func_77617_a(int par1)
/*     */   {
/*  48 */     return this.icon;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getRenderPasses(int metadata)
/*     */   {
/*  54 */     return metadata == 0 ? 1 : 2;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77618_c(int par1, int par2)
/*     */   {
/*  60 */     return (par1 == 0) || (par2 == 0) ? this.icon : this.iconOverlay;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_82790_a(ItemStack stack, int par2)
/*     */   {
/*  67 */     if ((stack.func_77960_j() == 0) || (par2 == 0)) return 16777215;
/*  68 */     if ((stack.func_77960_j() == 1) && (getAspects(stack) != null)) {
/*  69 */       return getAspects(stack).getAspects()[0].getColor();
/*     */     }
/*  71 */     return 16777215;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77623_v()
/*     */   {
/*  78 */     return true;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  84 */     par3List.add(new ItemStack(this, 1, 0));
/*  85 */     for (Aspect tag : Aspect.aspects.values()) {
/*  86 */       ItemStack i = new ItemStack(this, 1, 1);
/*  87 */       setAspects(i, new AspectList().add(tag, 8));
/*  88 */       par3List.add(i);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_77667_c(ItemStack par1ItemStack)
/*     */   {
/*  95 */     return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*     */   {
/* 100 */     AspectList aspects = getAspects(stack);
/* 101 */     if ((aspects != null) && (aspects.size() > 0)) {
/* 102 */       for (Aspect tag : aspects.getAspectsSorted()) {
/* 103 */         if (Thaumcraft.proxy.playerKnowledge.hasDiscoveredAspect(player.func_70005_c_(), tag)) {
/* 104 */           list.add(tag.getName() + " x " + aspects.getAmount(tag));
/*     */         } else {
/* 106 */           list.add(net.minecraft.util.StatCollector.func_74838_a("tc.aspect.unknown"));
/*     */         }
/*     */       }
/*     */     }
/* 110 */     super.func_77624_a(stack, player, list, par4);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean onItemUseFirst(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float f1, float f2, float f3)
/*     */   {
/* 116 */     net.minecraft.block.Block bi = world.func_147439_a(x, y, z);
/* 117 */     int md = world.func_72805_g(x, y, z);
/*     */     
/*     */ 
/* 120 */     if ((itemstack.func_77960_j() == 0) && (bi == ConfigBlocks.blockMetalDevice) && (md == 1)) {
/* 121 */       TileAlembic tile = (TileAlembic)world.func_147438_o(x, y, z);
/* 122 */       if (tile.amount >= 8) {
/* 123 */         if (world.field_72995_K) {
/* 124 */           player.func_71038_i();
/* 125 */           return false;
/*     */         }
/* 127 */         ItemStack phial = new ItemStack(this, 1, 1);
/* 128 */         setAspects(phial, new AspectList().add(tile.aspect, 8));
/* 129 */         if (tile.takeFromContainer(tile.aspect, 8)) {
/* 130 */           itemstack.field_77994_a -= 1;
/* 131 */           if (!player.field_71071_by.func_70441_a(phial)) {
/* 132 */             world.func_72838_d(new EntityItem(world, x + 0.5F, y + 0.5F, z + 0.5F, phial));
/*     */           }
/* 134 */           world.func_72956_a(player, "game.neutral.swim", 0.25F, 1.0F);
/* 135 */           player.field_71069_bz.func_75142_b();
/* 136 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 142 */     if ((itemstack.func_77960_j() == 0) && (bi == ConfigBlocks.blockJar) && ((md == 0) || (md == 3))) {
/* 143 */       TileJarFillable tile = (TileJarFillable)world.func_147438_o(x, y, z);
/* 144 */       if (tile.amount >= 8) {
/* 145 */         if (world.field_72995_K) {
/* 146 */           player.func_71038_i();
/* 147 */           return false;
/*     */         }
/* 149 */         Aspect asp = Aspect.getAspect(tile.aspect.getTag());
/* 150 */         if (tile.takeFromContainer(asp, 8)) {
/* 151 */           itemstack.field_77994_a -= 1;
/* 152 */           ItemStack phial = new ItemStack(this, 1, 1);
/* 153 */           setAspects(phial, new AspectList().add(asp, 8));
/* 154 */           if (!player.field_71071_by.func_70441_a(phial)) {
/* 155 */             world.func_72838_d(new EntityItem(world, x + 0.5F, y + 0.5F, z + 0.5F, phial));
/*     */           }
/* 157 */           world.func_72956_a(player, "game.neutral.swim", 0.25F, 1.0F);
/* 158 */           player.field_71069_bz.func_75142_b();
/* 159 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 163 */     AspectList al = getAspects(itemstack);
/* 164 */     if ((al != null) && (al.size() == 1)) {
/* 165 */       Aspect aspect = al.getAspects()[0];
/* 166 */       if ((itemstack.func_77960_j() != 0) && (bi == ConfigBlocks.blockJar) && ((md == 0) || (md == 3))) {
/* 167 */         TileJarFillable tile = (TileJarFillable)world.func_147438_o(x, y, z);
/* 168 */         if ((tile.amount <= tile.maxAmount - 8) && (tile.doesContainerAccept(aspect))) {
/* 169 */           if (world.field_72995_K) {
/* 170 */             player.func_71038_i();
/* 171 */             return false;
/*     */           }
/* 173 */           if (tile.addToContainer(aspect, 8) == 0) {
/* 174 */             world.func_147471_g(x, y, z);
/* 175 */             tile.func_70296_d();
/* 176 */             itemstack.field_77994_a -= 1;
/* 177 */             if (!player.field_71071_by.func_70441_a(new ItemStack(this, 1, 0))) {
/* 178 */               world.func_72838_d(new EntityItem(world, x + 0.5F, y + 0.5F, z + 0.5F, new ItemStack(this, 1, 0)));
/*     */             }
/* 180 */             world.func_72956_a(player, "game.neutral.swim", 0.25F, 1.0F);
/* 181 */             player.field_71069_bz.func_75142_b();
/* 182 */             return true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 189 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public AspectList getAspects(ItemStack itemstack)
/*     */   {
/* 195 */     if (itemstack.func_77942_o()) {
/* 196 */       AspectList aspects = new AspectList();
/* 197 */       aspects.readFromNBT(itemstack.func_77978_p());
/* 198 */       return aspects.size() > 0 ? aspects : null;
/*     */     }
/* 200 */     return null;
/*     */   }
/*     */   
/*     */   public void setAspects(ItemStack itemstack, AspectList aspects)
/*     */   {
/* 205 */     if (!itemstack.func_77942_o())
/* 206 */       itemstack.func_77982_d(new net.minecraft.nbt.NBTTagCompound());
/* 207 */     aspects.writeToNBT(itemstack.func_77978_p());
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/ItemEssence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */