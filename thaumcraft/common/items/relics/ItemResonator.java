/*     */ package thaumcraft.common.items.relics;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.codechicken.lib.raytracer.RayTracer;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.tiles.TileTubeBuffer;
/*     */ 
/*     */ public class ItemResonator extends Item
/*     */ {
/*     */   private IIcon icon;
/*     */   
/*     */   public ItemResonator()
/*     */   {
/*  32 */     func_77625_d(1);
/*  33 */     func_77627_a(false);
/*  34 */     func_77656_e(0);
/*  35 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister par1IconRegister)
/*     */   {
/*  42 */     this.icon = par1IconRegister.func_94245_a("thaumcraft:resonator");
/*     */   }
/*     */   
/*     */ 
/*     */   public IIcon func_77617_a(int par1)
/*     */   {
/*  48 */     return this.icon;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  54 */     par3List.add(new ItemStack(this));
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  60 */     return EnumRarity.uncommon;
/*     */   }
/*     */   
/*     */   public boolean func_77636_d(ItemStack par1ItemStack)
/*     */   {
/*  65 */     return par1ItemStack.func_77942_o();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean onItemUseFirst(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
/*     */   {
/*  72 */     net.minecraft.tileentity.TileEntity tile = world.func_147438_o(x, y, z);
/*  73 */     if ((tile != null) && ((tile instanceof IEssentiaTransport))) {
/*  74 */       if (world.field_72995_K) {
/*  75 */         player.func_71038_i();
/*  76 */         return super.onItemUseFirst(itemstack, player, world, x, y, z, side, par8, par9, par10);
/*     */       }
/*  78 */       IEssentiaTransport et = (IEssentiaTransport)tile;
/*  79 */       ForgeDirection face = ForgeDirection.getOrientation(side);
/*     */       
/*  81 */       MovingObjectPosition hit = RayTracer.retraceBlock(world, player, x, y, z);
/*  82 */       if ((hit != null) && (hit.subHit >= 0) && (hit.subHit < 6))
/*     */       {
/*  84 */         face = ForgeDirection.getOrientation(hit.subHit);
/*     */       }
/*     */       
/*  87 */       if ((!(tile instanceof TileTubeBuffer)) && (et.getEssentiaType(face) != null)) {
/*  88 */         player.func_145747_a(new ChatComponentTranslation("tc.resonator1", new Object[] { "" + et.getEssentiaAmount(face), et.getEssentiaType(face).getName() }));
/*     */       }
/*  90 */       else if (((tile instanceof TileTubeBuffer)) && (((IAspectContainer)tile).getAspects().size() > 0)) {
/*  91 */         for (Aspect aspect : ((IAspectContainer)tile).getAspects().getAspectsSorted()) {
/*  92 */           player.func_145747_a(new ChatComponentTranslation("tc.resonator1", new Object[] { "" + ((IAspectContainer)tile).getAspects().getAmount(aspect), aspect.getName() }));
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*  97 */       String s = net.minecraft.util.StatCollector.func_74838_a("tc.resonator3");
/*  98 */       if (et.getSuctionType(face) != null)
/*  99 */         s = et.getSuctionType(face).getName();
/* 100 */       player.func_145747_a(new ChatComponentTranslation("tc.resonator2", new Object[] { "" + et.getSuctionAmount(face), s }));
/*     */       
/*     */ 
/* 103 */       world.func_72908_a(x, y, z, "thaumcraft:alembicknock", 0.5F, 1.9F + world.field_73012_v.nextFloat() * 0.1F);
/*     */       
/*     */ 
/* 106 */       return true;
/*     */     }
/*     */     
/* 109 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/relics/ItemResonator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */