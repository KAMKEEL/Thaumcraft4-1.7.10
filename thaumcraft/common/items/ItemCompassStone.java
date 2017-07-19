/*    */ package thaumcraft.common.items;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.WorldCoordinates;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ 
/*    */ public class ItemCompassStone extends Item
/*    */ {
/*    */   public ItemCompassStone()
/*    */   {
/* 24 */     func_77625_d(1);
/* 25 */     func_77627_a(true);
/* 26 */     func_77656_e(0);
/* 27 */     func_77637_a(Thaumcraft.tabTC);
/*    */   }
/*    */   
/* 30 */   public IIcon[] icon = new IIcon[2];
/*    */   
/* 32 */   private IIcon t = null;
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister ir)
/*    */   {
/* 37 */     this.icon[0] = ir.func_94245_a("thaumcraft:sinister_stone");
/* 38 */     this.icon[1] = ir.func_94245_a("thaumcraft:sinister_stone_active");
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int par1)
/*    */   {
/* 44 */     return this.t == null ? this.icon[0] : par1 == 1 ? this.icon[1] : this.t;
/*    */   }
/*    */   
/* 47 */   public static HashMap<WorldCoordinates, Long> sinisterNodes = new HashMap();
/*    */   
/*    */   public void func_77663_a(ItemStack p_77663_1_, World world, Entity entity, int p_77663_4_, boolean p_77663_5_)
/*    */   {
/* 51 */     if (world.field_72995_K) {
/* 52 */       ArrayList<WorldCoordinates> del = new ArrayList();
/* 53 */       this.t = null;
/* 54 */       for (WorldCoordinates wc : sinisterNodes.keySet()) {
/* 55 */         if (((Long)sinisterNodes.get(wc)).longValue() < System.currentTimeMillis() - 10000L) del.add(wc);
/* 56 */         if ((wc.dim == world.field_73011_w.field_76574_g) && 
/* 57 */           (thaumcraft.common.lib.utils.EntityUtils.isVisibleTo(0.66F, entity, wc.x + 0.5D, wc.y + 0.5D, wc.z + 0.5D, 256.0F))) {
/* 58 */           this.t = this.icon[1];
/* 59 */           break;
/*    */         }
/*    */       }
/*    */       WorldCoordinates wc;
/* 63 */       for (Iterator i$ = del.iterator(); i$.hasNext(); sinisterNodes.remove(wc)) wc = (WorldCoordinates)i$.next();
/*    */     }
/*    */   }
/*    */   
/*    */   private double directionToPoint(double x1, double z1, double x2, double z2)
/*    */   {
/* 69 */     double dx = x1 - x2;
/* 70 */     double dz = z1 - z2;
/* 71 */     return Math.atan2(dz, dx) * 180.0D / 3.141592653589793D;
/*    */   }
/*    */   
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*    */   {
/* 78 */     par3List.add(new ItemStack(this, 1, 0));
/*    */   }
/*    */   
/*    */ 
/*    */   public EnumRarity func_77613_e(ItemStack stack)
/*    */   {
/* 84 */     return EnumRarity.rare;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/ItemCompassStone.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */