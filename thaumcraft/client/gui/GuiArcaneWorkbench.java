/*     */ package thaumcraft.client.gui;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.renderer.entity.RenderItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*     */ import thaumcraft.common.tiles.TileArcaneWorkbench;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class GuiArcaneWorkbench extends GuiContainer
/*     */ {
/*     */   private TileArcaneWorkbench tileEntity;
/*     */   private InventoryPlayer ip;
/*  27 */   private int[][] aspectLocs = { { 72, 21 }, { 24, 43 }, { 24, 102 }, { 72, 124 }, { 120, 102 }, { 120, 43 } };
/*     */   
/*     */   public GuiArcaneWorkbench(InventoryPlayer par1InventoryPlayer, TileArcaneWorkbench e)
/*     */   {
/*  31 */     super(new thaumcraft.common.container.ContainerArcaneWorkbench(par1InventoryPlayer, e));
/*  32 */     this.tileEntity = e;
/*  33 */     this.ip = par1InventoryPlayer;
/*  34 */     this.field_147000_g = 234;
/*  35 */     this.field_146999_f = 190;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  47 */   ArrayList<Aspect> primals = Aspect.getPrimalAspects();
/*     */   
/*     */ 
/*     */   protected void drawGuiContainerForegroundLayer() {}
/*     */   
/*     */   protected void func_146976_a(float par1, int par2, int par3)
/*     */   {
/*  54 */     UtilsFX.bindTexture("textures/gui/gui_arcaneworkbench.png");
/*  55 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  56 */     GL11.glEnable(3042);
/*  57 */     int var5 = (this.field_146294_l - this.field_146999_f) / 2;
/*  58 */     int var6 = (this.field_146295_m - this.field_147000_g) / 2;
/*  59 */     func_73729_b(var5, var6, 0, 0, this.field_146999_f, this.field_147000_g);
/*  60 */     GL11.glDisable(3042);
/*     */     
/*  62 */     ItemWandCasting wand = null;
/*  63 */     if ((this.tileEntity.func_70301_a(10) != null) && ((this.tileEntity.func_70301_a(10).func_77973_b() instanceof ItemWandCasting))) {
/*  64 */       wand = (ItemWandCasting)this.tileEntity.func_70301_a(10).func_77973_b();
/*     */     }
/*     */     
/*  67 */     AspectList cost = null;
/*     */     int count;
/*  69 */     if (ThaumcraftCraftingManager.findMatchingArcaneRecipe(this.tileEntity, this.ip.field_70458_d) != null) {
/*  70 */       cost = ThaumcraftCraftingManager.findMatchingArcaneRecipeAspects(this.tileEntity, this.ip.field_70458_d);
/*  71 */       count = 0;
/*  72 */       for (Aspect primal : this.primals) {
/*  73 */         float amt = cost.getAmount(primal);
/*  74 */         if (cost.getAmount(primal) > 0) {
/*  75 */           float alpha = 0.5F + (MathHelper.func_76126_a((this.ip.field_70458_d.field_70173_aa + count * 10) / 2.0F) * 0.2F - 0.2F);
/*  76 */           if (wand != null) {
/*  77 */             amt *= wand.getConsumptionModifier(this.tileEntity.func_70301_a(10), this.ip.field_70458_d, primal, true);
/*  78 */             if (amt * 100.0F <= wand.getVis(this.tileEntity.func_70301_a(10), primal)) {
/*  79 */               alpha = 1.0F;
/*     */             }
/*     */           }
/*     */           
/*  83 */           UtilsFX.drawTag(var5 + this.aspectLocs[count][0] - 8, var6 + this.aspectLocs[count][1] - 8, primal, amt, 0, this.field_73735_i, 771, alpha, false);
/*     */         }
/*     */         
/*  86 */         count++;
/*  87 */         if (count > 5)
/*     */           break;
/*     */       }
/*     */     }
/*  91 */     if ((wand != null) && 
/*  92 */       (cost != null) && (!wand.consumeAllVisCrafting(this.tileEntity.func_70301_a(10), this.ip.field_70458_d, cost, false))) {
/*  93 */       GL11.glPushMatrix();
/*  94 */       float var40 = 0.33F;
/*  95 */       GL11.glColor4f(var40, var40, var40, 0.66F);
/*  96 */       field_146296_j.field_77024_a = false;
/*     */       
/*  98 */       GL11.glEnable(2896);
/*  99 */       GL11.glEnable(2884);
/* 100 */       GL11.glEnable(3042);
/*     */       
/* 102 */       field_146296_j.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, ThaumcraftCraftingManager.findMatchingArcaneRecipe(this.tileEntity, this.ip.field_70458_d), var5 + 160, var6 + 64);
/*     */       
/*     */ 
/*     */ 
/* 106 */       field_146296_j.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, ThaumcraftCraftingManager.findMatchingArcaneRecipe(this.tileEntity, this.ip.field_70458_d), var5 + 160, var6 + 64);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 111 */       field_146296_j.field_77024_a = true;
/*     */       
/* 113 */       GL11.glDisable(3042);
/* 114 */       GL11.glDisable(2896);
/* 115 */       GL11.glPopMatrix();
/*     */       
/* 117 */       GL11.glPushMatrix();
/* 118 */       GL11.glTranslatef(var5 + 168, var6 + 46, 0.0F);
/* 119 */       GL11.glScalef(0.5F, 0.5F, 0.0F);
/* 120 */       String text = "Insufficient vis";
/*     */       
/* 122 */       int ll = this.field_146289_q.func_78256_a(text) / 2;
/* 123 */       this.field_146289_q.func_78276_b(text, -ll, 0, 15625838);
/* 124 */       GL11.glScalef(1.0F, 1.0F, 1.0F);
/* 125 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/gui/GuiArcaneWorkbench.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */