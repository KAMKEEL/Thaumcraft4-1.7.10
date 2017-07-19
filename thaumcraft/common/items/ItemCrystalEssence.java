/*     */ package thaumcraft.common.items;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*     */ 
/*     */ public class ItemCrystalEssence extends Item implements IEssentiaContainerItem
/*     */ {
/*     */   public IIcon icon;
/*     */   
/*     */   public ItemCrystalEssence()
/*     */   {
/*  28 */     func_77625_d(64);
/*  29 */     func_77627_a(true);
/*  30 */     func_77656_e(0);
/*  31 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  38 */     this.icon = ir.func_94245_a("thaumcraft:crystalessence");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1) {
/*  43 */     return this.icon;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  49 */     par3List.add(new ItemStack(this, 1, 0));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*     */   {
/*  59 */     AspectList aspects = getAspects(stack);
/*  60 */     if ((aspects != null) && (aspects.size() > 0)) {
/*  61 */       for (Aspect tag : aspects.getAspectsSorted()) {
/*  62 */         if (Thaumcraft.proxy.playerKnowledge.hasDiscoveredAspect(player.func_70005_c_(), tag)) {
/*  63 */           list.add(tag.getName() + " x" + aspects.getAmount(tag));
/*     */         } else {
/*  65 */           list.add(net.minecraft.util.StatCollector.func_74838_a("tc.aspect.unknown"));
/*     */         }
/*     */       }
/*     */     }
/*  69 */     super.func_77624_a(stack, player, list, par4);
/*     */   }
/*     */   
/*  72 */   static Aspect[] displayAspects = (Aspect[])Aspect.aspects.values().toArray(new Aspect[0]);
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_82790_a(ItemStack stack, int par2)
/*     */   {
/*  78 */     if (getAspects(stack) != null) {
/*  79 */       return getAspects(stack).getAspects()[0].getColor();
/*     */     }
/*  81 */     int idx = (int)(System.currentTimeMillis() / 500L % displayAspects.length);
/*  82 */     return displayAspects[idx].getColor();
/*     */   }
/*     */   
/*     */ 
/*     */   public AspectList getAspects(ItemStack itemstack)
/*     */   {
/*  88 */     if (itemstack.func_77942_o()) {
/*  89 */       AspectList aspects = new AspectList();
/*  90 */       aspects.readFromNBT(itemstack.func_77978_p());
/*  91 */       return aspects.size() > 0 ? aspects : null;
/*     */     }
/*  93 */     return null;
/*     */   }
/*     */   
/*     */   public void setAspects(ItemStack itemstack, AspectList aspects)
/*     */   {
/*  98 */     if (!itemstack.func_77942_o())
/*  99 */       itemstack.func_77982_d(new net.minecraft.nbt.NBTTagCompound());
/* 100 */     aspects.writeToNBT(itemstack.func_77978_p());
/*     */   }
/*     */   
/* 103 */   Random rand = new Random();
/*     */   
/*     */   public void func_77663_a(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
/*     */   {
/* 107 */     if ((!par2World.field_72995_K) && (!par1ItemStack.func_77942_o())) {
/* 108 */       setAspects(par1ItemStack, new AspectList().add(displayAspects[this.rand.nextInt(displayAspects.length)], 1));
/*     */     }
/* 110 */     super.func_77663_a(par1ItemStack, par2World, par3Entity, par4, par5);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_77622_d(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
/*     */   {
/* 116 */     if (!par1ItemStack.func_77942_o()) {
/* 117 */       setAspects(par1ItemStack, new AspectList().add(displayAspects[this.rand.nextInt(displayAspects.length)], 1));
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/ItemCrystalEssence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */