/*    */ package thaumcraft.client.gui;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.inventory.GuiContainer;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.util.ChatComponentTranslation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.common.entities.golems.ContainerTravelingTrunk;
/*    */ import thaumcraft.common.entities.golems.EntityTravelingTrunk;
/*    */ 
/*    */ public class GuiTravelingTrunk extends GuiContainer
/*    */ {
/*    */   private EntityPlayer theplayer;
/*    */   private EntityTravelingTrunk themob;
/*    */   private int inventoryRows;
/*    */   
/*    */   public GuiTravelingTrunk(EntityPlayer p, EntityTravelingTrunk m)
/*    */   {
/* 22 */     super(new ContainerTravelingTrunk(p.field_71071_by, p.field_70170_p, m));
/* 23 */     this.theplayer = p;
/* 24 */     this.themob = m;
/* 25 */     this.inventoryRows = (m.inventory.slotCount / 9);
/* 26 */     this.field_147000_g = 200;
/*    */   }
/*    */   
/*    */   protected void func_146979_b(int par1, int par2)
/*    */   {
/* 31 */     GL11.glPushMatrix();
/* 32 */     GL11.glScaled(0.5D, 0.5D, 0.5D);
/* 33 */     this.field_146289_q.func_78276_b(this.themob.func_152113_b() + net.minecraft.util.StatCollector.func_74838_a("entity.trunk.guiname"), 8, 4, 12624112);
/* 34 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */ 
/*    */   protected void func_146976_a(float f, int ii, int jj)
/*    */   {
/* 40 */     if (this.themob.field_70128_L) { this.field_146297_k.field_71439_g.func_71053_j();
/*    */     }
/* 42 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 43 */     UtilsFX.bindTexture("textures/gui/guitrunkbase.png");
/* 44 */     GL11.glEnable(3042);
/* 45 */     int j = (this.field_146294_l - this.field_146999_f) / 2;
/* 46 */     int k = (this.field_146295_m - this.field_147000_g) / 2;
/* 47 */     func_73729_b(j, k, 0, 0, this.field_146999_f, this.field_147000_g);
/*    */     
/* 49 */     int hp = Math.round(this.themob.func_110143_aJ() / this.themob.func_110138_aP() * 39.0F);
/* 50 */     func_73729_b(j + 134, k + 2, 176, 16, hp, 6);
/*    */     
/* 52 */     if (this.themob.getUpgrade() == 1) {
/* 53 */       func_73729_b(j, k + 80, 0, 206, this.field_146999_f, 27);
/*    */     }
/*    */     
/* 56 */     if (this.themob.getStay()) {
/* 57 */       func_73729_b(j + 112, k, 176, 0, 10, 10);
/*    */     }
/*    */     
/*    */ 
/* 61 */     GL11.glDisable(3042);
/*    */   }
/*    */   
/*    */   protected void func_73864_a(int i, int j, int k)
/*    */   {
/* 66 */     super.func_73864_a(i, j, k);
/* 67 */     int sx = (this.field_146294_l - this.field_146999_f) / 2;
/* 68 */     int sy = (this.field_146295_m - this.field_147000_g) / 2;
/* 69 */     int k1 = i - (sx + 112);
/* 70 */     int l1 = j - (sy + 0);
/* 71 */     if ((k1 >= 0) && (l1 >= 0) && (k1 < 10) && (l1 <= 10))
/*    */     {
/* 73 */       this.themob.field_70170_p.func_72980_b(this.themob.field_70165_t, this.themob.field_70163_u, this.themob.field_70161_v, "random.click", 0.3F, 0.6F + (this.themob.getStay() ? 0.0F : 0.2F), false);
/*    */       
/*    */ 
/*    */ 
/*    */ 
/* 78 */       if (this.themob.getStay()) {
/* 79 */         this.theplayer.func_145747_a(new ChatComponentTranslation("entity.trunk.move", new Object[0]));
/*    */       } else {
/* 81 */         this.theplayer.func_145747_a(new ChatComponentTranslation("entity.trunk.stay", new Object[0]));
/*    */       }
/*    */       
/* 84 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 1);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean func_73868_f()
/*    */   {
/* 91 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_146281_b()
/*    */   {
/* 97 */     this.themob.setOpen(false);
/*    */     
/* 99 */     super.func_146281_b();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/gui/GuiTravelingTrunk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */