/*     */ package thaumcraft.client.gui;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.multiplayer.PlayerControllerMP;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.tiles.TileDeconstructionTable;
/*     */ 
/*     */ @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */ public class GuiDeconstructionTable extends GuiContainer
/*     */ {
/*     */   private TileDeconstructionTable tableInventory;
/*     */   
/*     */   public GuiDeconstructionTable(InventoryPlayer par1InventoryPlayer, TileDeconstructionTable par2TileEntityFurnace)
/*     */   {
/*  25 */     super(new thaumcraft.common.container.ContainerDeconstructionTable(par1InventoryPlayer, par2TileEntityFurnace));
/*  26 */     this.tableInventory = par2TileEntityFurnace;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_146979_b(int par1, int par2) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_146976_a(float par1, int par2, int par3)
/*     */   {
/*  44 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  45 */     UtilsFX.bindTexture("textures/gui/gui_decontable.png");
/*  46 */     int k = (this.field_146294_l - this.field_146999_f) / 2;
/*  47 */     int l = (this.field_146295_m - this.field_147000_g) / 2;
/*     */     
/*  49 */     GL11.glEnable(3042);
/*  50 */     func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
/*     */     
/*     */ 
/*  53 */     if (this.tableInventory.breaktime > 0)
/*     */     {
/*  55 */       int i1 = this.tableInventory.getBreakTimeScaled(46);
/*  56 */       func_73729_b(k + 93, l + 15 + 46 - i1, 176, 46 - i1, 9, i1);
/*     */     }
/*     */     
/*  59 */     if (this.tableInventory.aspect != null) {
/*  60 */       UtilsFX.drawTag(k + 64, l + 48, this.tableInventory.aspect, 0.0F, 0, this.field_73735_i);
/*  61 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  62 */       RenderHelper.func_74518_a();
/*     */       
/*  64 */       int var7 = par2 - (k + 64);
/*  65 */       int var8 = par3 - (l + 48);
/*  66 */       if ((var7 >= 0) && (var8 >= 0) && (var7 < 16) && (var8 < 16))
/*     */       {
/*  68 */         UtilsFX.drawCustomTooltip(this, field_146296_j, this.field_146289_q, java.util.Arrays.asList(new String[] { this.tableInventory.aspect.getName(), this.tableInventory.aspect.getLocalizedDescription() }), par2, par3 - 8, 11);
/*     */         
/*     */ 
/*  71 */         return;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  77 */     GL11.glDisable(3042);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_73864_a(int mx, int my, int par3)
/*     */   {
/*  84 */     super.func_73864_a(mx, my, par3);
/*     */     
/*  86 */     int gx = (this.field_146294_l - this.field_146999_f) / 2;
/*  87 */     int gy = (this.field_146295_m - this.field_147000_g) / 2;
/*     */     
/*     */ 
/*  90 */     int var7 = mx - (gx + 64);
/*  91 */     int var8 = my - (gy + 48);
/*     */     
/*  93 */     if ((var7 >= 0) && (var8 >= 0) && (var7 < 16) && (var8 < 16) && (this.tableInventory.aspect != null))
/*     */     {
/*     */ 
/*     */ 
/*  97 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 1);
/*     */       
/*  99 */       playButtonAspect();
/* 100 */       return;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void playButtonAspect()
/*     */   {
/* 107 */     this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:hhoff", 0.2F, 1.0F + this.field_146297_k.field_71451_h.field_70170_p.field_73012_v.nextFloat() * 0.1F, false);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/gui/GuiDeconstructionTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */