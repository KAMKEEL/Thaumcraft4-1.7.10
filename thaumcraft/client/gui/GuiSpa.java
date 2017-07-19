/*     */ package thaumcraft.client.gui;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.multiplayer.PlayerControllerMP;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.FluidTank;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.container.ContainerSpa;
/*     */ import thaumcraft.common.tiles.TileSpa;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class GuiSpa
/*     */   extends GuiContainer
/*     */ {
/*     */   private TileSpa spa;
/*     */   private float xSize_lo;
/*     */   private float ySize_lo;
/*     */   
/*     */   public GuiSpa(InventoryPlayer par1InventoryPlayer, TileSpa teSpa)
/*     */   {
/*  41 */     super(new ContainerSpa(par1InventoryPlayer, teSpa));
/*  42 */     this.spa = teSpa;
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
/*     */   public void func_73863_a(int par1, int par2, float par3)
/*     */   {
/*  55 */     super.func_73863_a(par1, par2, par3);
/*  56 */     this.xSize_lo = par1;
/*  57 */     this.ySize_lo = par2;
/*     */     
/*  59 */     int baseX = this.field_147003_i;
/*  60 */     int baseY = this.field_147009_r;
/*     */     
/*  62 */     int mposx = par1 - (baseX + 104);
/*  63 */     int mposy = par2 - (baseY + 10);
/*     */     
/*  65 */     if ((mposx >= 0) && (mposy >= 0) && (mposx < 10) && (mposy < 55))
/*     */     {
/*  67 */       List list = new ArrayList();
/*  68 */       FluidStack fluid = this.spa.tank.getFluid();
/*  69 */       if (fluid != null) {
/*  70 */         list.add(fluid.getFluid().getLocalizedName(fluid));
/*  71 */         list.add(fluid.amount + " mb");
/*  72 */         drawHoveringText(list, par1, par2, this.field_146289_q);
/*     */       }
/*     */     }
/*     */     
/*  76 */     mposx = par1 - (baseX + 88);
/*  77 */     mposy = par2 - (baseY + 34);
/*     */     
/*  79 */     if ((mposx >= 0) && (mposy >= 0) && (mposx < 10) && (mposy < 10))
/*     */     {
/*  81 */       List list = new ArrayList();
/*  82 */       if (this.spa.getMix()) {
/*  83 */         list.add(StatCollector.func_74838_a("text.spa.mix.true"));
/*     */       } else {
/*  85 */         list.add(StatCollector.func_74838_a("text.spa.mix.false"));
/*     */       }
/*  87 */       drawHoveringText(list, par1, par2, this.field_146289_q);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_146976_a(float par1, int par2, int par3)
/*     */   {
/*  98 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  99 */     UtilsFX.bindTexture("textures/gui/gui_spa.png");
/* 100 */     int k = (this.field_146294_l - this.field_146999_f) / 2;
/* 101 */     int l = (this.field_146295_m - this.field_147000_g) / 2;
/*     */     
/* 103 */     GL11.glEnable(3042);
/* 104 */     GL11.glBlendFunc(770, 771);
/*     */     
/* 106 */     func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
/*     */     
/* 108 */     if (this.spa.getMix()) {
/* 109 */       func_73729_b(k + 89, l + 35, 208, 16, 8, 8);
/*     */     } else {
/* 111 */       func_73729_b(k + 89, l + 35, 208, 32, 8, 8);
/*     */     }
/*     */     
/*     */ 
/* 115 */     if (this.spa.tank.getFluidAmount() > 0) {
/* 116 */       FluidStack fluid = this.spa.tank.getFluid();
/* 117 */       if (fluid != null) {
/* 118 */         IIcon icon = fluid.getFluid().getIcon();
/* 119 */         if (icon != null) {
/* 120 */           float bar = this.spa.tank.getFluidAmount() / this.spa.tank.getCapacity();
/* 121 */           GL11.glPushMatrix();
/* 122 */           GL11.glTranslatef(this.field_147003_i + 107, this.field_147009_r + 15, 0.0F);
/* 123 */           renderFluid(icon);
/* 124 */           GL11.glPopMatrix();
/* 125 */           UtilsFX.bindTexture("textures/gui/gui_spa.png");
/* 126 */           func_73729_b(k + 107, l + 15, 107, 15, 10, (int)(48.0F - 48.0F * bar));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 131 */     func_73729_b(k + 106, l + 11, 232, 0, 10, 55);
/*     */     
/*     */ 
/* 134 */     GL11.glDisable(3042);
/*     */   }
/*     */   
/*     */   public static void renderFluid(IIcon icon)
/*     */   {
/* 139 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
/*     */     
/* 141 */     Tessellator tessellator = Tessellator.field_78398_a;
/*     */     
/* 143 */     float f1 = icon.func_94212_f();
/* 144 */     float f2 = icon.func_94206_g();
/* 145 */     float f3 = icon.func_94209_e();
/* 146 */     float f4 = icon.func_94210_h();
/*     */     
/* 148 */     GL11.glScalef(8.0F, 8.0F, 8.0F);
/*     */     
/* 150 */     for (int a = 0; a < 6; a++) {
/* 151 */       tessellator.func_78382_b();
/* 152 */       tessellator.func_78369_a(1.0F, 1.0F, 1.0F, 1.0F);
/* 153 */       tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
/* 154 */       tessellator.func_78374_a(0.0D, 1 + a, 0.0D, f1, f4);
/* 155 */       tessellator.func_78374_a(1.0D, 1 + a, 0.0D, f3, f4);
/* 156 */       tessellator.func_78374_a(1.0D, 0 + a, 0.0D, f3, f2);
/* 157 */       tessellator.func_78374_a(0.0D, 0 + a, 0.0D, f1, f2);
/* 158 */       tessellator.func_78381_a();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_73864_a(int mx, int my, int par3)
/*     */   {
/* 165 */     super.func_73864_a(mx, my, par3);
/*     */     
/* 167 */     int gx = (this.field_146294_l - this.field_146999_f) / 2;
/* 168 */     int gy = (this.field_146295_m - this.field_147000_g) / 2;
/*     */     
/*     */ 
/* 171 */     int var7 = mx - (gx + 89);
/* 172 */     int var8 = my - (gy + 35);
/*     */     
/* 174 */     if ((var7 >= 0) && (var8 >= 0) && (var7 < 8) && (var8 < 8))
/*     */     {
/*     */ 
/* 177 */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 1);
/* 178 */       playButtonClick();
/* 179 */       return;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void playButtonClick()
/*     */   {
/* 186 */     this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:cameraclack", 0.4F, 1.0F, false);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/gui/GuiSpa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */