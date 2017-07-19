/*    */ package thaumcraft.client.gui;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.inventory.GuiContainer;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.world.World;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.common.entities.ContainerPech;
/*    */ import thaumcraft.common.entities.monster.EntityPech;
/*    */ 
/*    */ @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */ public class GuiPech extends GuiContainer
/*    */ {
/*    */   EntityPech pech;
/*    */   
/*    */   public GuiPech(InventoryPlayer par1InventoryPlayer, World world, EntityPech pech)
/*    */   {
/* 23 */     super(new ContainerPech(par1InventoryPlayer, world, pech));
/* 24 */     this.field_146999_f = 175;
/* 25 */     this.field_147000_g = 232;
/* 26 */     this.pech = pech;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void func_146979_b(int par1, int par2) {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void func_146976_a(float par1, int par2, int par3)
/*    */   {
/* 41 */     UtilsFX.bindTexture("textures/gui/gui_pech.png");
/* 42 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 43 */     int var5 = (this.field_146294_l - this.field_146999_f) / 2;
/* 44 */     int var6 = (this.field_146295_m - this.field_147000_g) / 2;
/* 45 */     GL11.glEnable(3042);
/* 46 */     func_73729_b(var5, var6, 0, 0, this.field_146999_f, this.field_147000_g);
/*    */     
/*    */ 
/* 49 */     if ((this.pech.isValued(this.field_147002_h.func_75139_a(0).func_75211_c())) && (this.field_147002_h.func_75139_a(0).func_75211_c() != null) && (this.field_147002_h.func_75139_a(1).func_75211_c() == null) && (this.field_147002_h.func_75139_a(2).func_75211_c() == null) && (this.field_147002_h.func_75139_a(3).func_75211_c() == null) && (this.field_147002_h.func_75139_a(4).func_75211_c() == null))
/*    */     {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 56 */       func_73729_b(var5 + 67, var6 + 24, 176, 0, 25, 25);
/*    */     }
/*    */     
/* 59 */     GL11.glDisable(3042);
/*    */   }
/*    */   
/*    */   protected void func_73864_a(int mx, int my, int par3)
/*    */   {
/* 64 */     super.func_73864_a(mx, my, par3);
/*    */     
/* 66 */     int gx = (this.field_146294_l - this.field_146999_f) / 2;
/* 67 */     int gy = (this.field_146295_m - this.field_147000_g) / 2;
/*    */     
/*    */ 
/* 70 */     int var7 = mx - (gx + 67);
/* 71 */     int var8 = my - (gy + 24);
/*    */     
/* 73 */     if ((var7 >= 0) && (var8 >= 0) && (var7 < 25) && (var8 < 25) && (this.pech.isValued(this.field_147002_h.func_75139_a(0).func_75211_c())) && (this.field_147002_h.func_75139_a(0).func_75211_c() != null) && (this.field_147002_h.func_75139_a(1).func_75211_c() == null) && (this.field_147002_h.func_75139_a(2).func_75211_c() == null) && (this.field_147002_h.func_75139_a(3).func_75211_c() == null) && (this.field_147002_h.func_75139_a(4).func_75211_c() == null))
/*    */     {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 82 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 0);
/*    */       
/* 84 */       playButton();
/* 85 */       return;
/*    */     }
/*    */   }
/*    */   
/*    */   private void playButton() {
/* 90 */     this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:pech_dice", 0.5F, 0.95F + this.field_146297_k.field_71451_h.field_70170_p.field_73012_v.nextFloat() * 0.1F, false);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/gui/GuiPech.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */