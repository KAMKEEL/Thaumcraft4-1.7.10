/*    */ package thaumcraft.common.items;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*    */ 
/*    */ public class ItemWispEssence extends Item implements IEssentiaContainerItem
/*    */ {
/*    */   public IIcon icon;
/*    */   
/*    */   public ItemWispEssence()
/*    */   {
/* 25 */     func_77625_d(64);
/* 26 */     func_77627_a(true);
/* 27 */     func_77656_e(0);
/* 28 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir)
/*    */   {
/* 35 */     this.icon = ir.func_94245_a("thaumcraft:wispessence");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int par1) {
/* 40 */     return this.icon;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*    */   {
/* 46 */     for (Aspect tag : Aspect.aspects.values()) {
/* 47 */       ItemStack i = new ItemStack(this, 1, 0);
/* 48 */       setAspects(i, new AspectList().add(tag, 2));
/* 49 */       par3List.add(i);
/*    */     }
/*    */   }
/*    */   
/*    */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4)
/*    */   {
/* 55 */     AspectList aspects = getAspects(stack);
/* 56 */     if ((aspects != null) && (aspects.size() > 0)) {
/* 57 */       for (Aspect tag : aspects.getAspectsSorted()) {
/* 58 */         if (Thaumcraft.proxy.playerKnowledge.hasDiscoveredAspect(player.func_70005_c_(), tag)) {
/* 59 */           list.add(tag.getName() + " x" + aspects.getAmount(tag));
/*    */         } else {
/* 61 */           list.add(net.minecraft.util.StatCollector.func_74838_a("tc.aspect.unknown"));
/*    */         }
/*    */       }
/*    */     }
/* 65 */     super.func_77624_a(stack, player, list, par4);
/*    */   }
/*    */   
/* 68 */   static Aspect[] displayAspects = (Aspect[])Aspect.aspects.values().toArray(new Aspect[0]);
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_82790_a(ItemStack stack, int par2)
/*    */   {
/* 74 */     if (getAspects(stack) != null) {
/* 75 */       return getAspects(stack).getAspects()[0].getColor();
/*    */     }
/* 77 */     int idx = (int)(System.currentTimeMillis() / 500L % displayAspects.length);
/* 78 */     return displayAspects[idx].getColor();
/*    */   }
/*    */   
/*    */ 
/*    */   public AspectList getAspects(ItemStack itemstack)
/*    */   {
/* 84 */     if (itemstack.func_77942_o()) {
/* 85 */       AspectList aspects = new AspectList();
/* 86 */       aspects.readFromNBT(itemstack.func_77978_p());
/* 87 */       return aspects.size() > 0 ? aspects : null;
/*    */     }
/* 89 */     return null;
/*    */   }
/*    */   
/*    */   public void setAspects(ItemStack itemstack, AspectList aspects)
/*    */   {
/* 94 */     if (!itemstack.func_77942_o())
/* 95 */       itemstack.func_77982_d(new net.minecraft.nbt.NBTTagCompound());
/* 96 */     aspects.writeToNBT(itemstack.func_77978_p());
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/ItemWispEssence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */