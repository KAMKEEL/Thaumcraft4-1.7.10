/*    */ package thaumcraft.client.renderers.tile;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraftforge.client.IItemRenderer;
/*    */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/*    */ import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.common.blocks.ItemJarFilled;
/*    */ import thaumcraft.common.config.ConfigBlocks;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ import thaumcraft.common.tiles.TileJarFillable;
/*    */ import thaumcraft.common.tiles.TileJarFillableVoid;
/*    */ 
/*    */ public class ItemJarFilledRenderer implements IItemRenderer
/*    */ {
/*    */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
/*    */   {
/* 26 */     return (item != null) && (item.func_77973_b() == ConfigItems.itemJarFilled);
/*    */   }
/*    */   
/*    */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
/*    */   {
/* 31 */     if (helper == IItemRenderer.ItemRendererHelper.EQUIPPED_BLOCK) return false;
/* 32 */     return true;
/*    */   }
/*    */   
/* 35 */   RenderBlocks rb = new RenderBlocks();
/*    */   
/*    */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
/*    */   {
/* 39 */     if (item.func_77973_b() == ConfigItems.itemJarFilled) {
/* 40 */       if (type == IItemRenderer.ItemRenderType.ENTITY) { GL11.glTranslatef(-0.5F, -0.25F, -0.5F);
/*    */       }
/* 42 */       else if ((type == IItemRenderer.ItemRenderType.EQUIPPED) && ((data[1] instanceof EntityPlayer))) { GL11.glTranslatef(0.0F, 0.0F, -0.5F);
/*    */       }
/*    */       
/* 45 */       TileJarFillable tjf = new TileJarFillable();
/* 46 */       if (item.func_77942_o()) {
/* 47 */         if (item.func_77960_j() == 3) tjf = new TileJarFillableVoid();
/* 48 */         AspectList aspects = ((ItemJarFilled)item.func_77973_b()).getAspects(item);
/* 49 */         if ((aspects != null) && (aspects.size() == 1)) {
/* 50 */           tjf.amount = aspects.getAmount(aspects.getAspects()[0]);
/* 51 */           tjf.aspect = aspects.getAspects()[0];
/*    */         }
/* 53 */         String tf = item.field_77990_d.func_74779_i("AspectFilter");
/* 54 */         if (tf != null) tjf.aspectFilter = Aspect.getAspect(tf);
/*    */       }
/* 56 */       tjf.facing = 5;
/* 57 */       tjf.field_145854_h = ConfigBlocks.blockJar;
/* 58 */       tjf.field_145847_g = 0;
/* 59 */       TileEntityRendererDispatcher.field_147556_a.func_147549_a(tjf, 0.0D, 0.0D, 0.0D, 0.0F);
/*    */       
/* 61 */       GL11.glPushMatrix();
/* 62 */       GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/* 63 */       GL11.glEnable(3042);
/* 64 */       GL11.glBlendFunc(770, 771);
/* 65 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 66 */       Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
/* 67 */       this.rb.field_147844_c = true;
/* 68 */       this.rb.func_147800_a(ConfigBlocks.blockJar, item.func_77960_j(), 1.0F);
/* 69 */       GL11.glPopMatrix();
/*    */       
/* 71 */       GL11.glEnable(32826);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/tile/ItemJarFilledRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */