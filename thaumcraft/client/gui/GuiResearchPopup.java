/*     */ package thaumcraft.client.gui;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.entity.RenderItem;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.research.ResearchItem;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class GuiResearchPopup
/*     */   extends Gui
/*     */ {
/*     */   private Minecraft theGame;
/*     */   private int windowWidth;
/*     */   private int windowHeight;
/*  27 */   private ArrayList<ResearchItem> theResearch = new ArrayList();
/*     */   
/*     */   private long researchTime;
/*     */   private RenderItem itemRender;
/*  31 */   private static final ResourceLocation texture = new ResourceLocation("textures/gui/achievement/achievement_background.png");
/*     */   
/*     */ 
/*     */   public GuiResearchPopup(Minecraft par1Minecraft)
/*     */   {
/*  36 */     this.theGame = par1Minecraft;
/*  37 */     this.itemRender = new RenderItem();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void queueResearchInformation(ResearchItem research)
/*     */   {
/*  45 */     if (this.researchTime == 0L) this.researchTime = Minecraft.func_71386_F();
/*  46 */     this.theResearch.add(research);
/*  47 */     GuiResearchBrowser.lastX = research.displayColumn;
/*  48 */     GuiResearchBrowser.lastY = research.displayRow;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void updateResearchWindowScale()
/*     */   {
/*  56 */     GL11.glViewport(0, 0, this.theGame.field_71443_c, this.theGame.field_71440_d);
/*  57 */     GL11.glMatrixMode(5889);
/*  58 */     GL11.glLoadIdentity();
/*  59 */     GL11.glMatrixMode(5888);
/*  60 */     GL11.glLoadIdentity();
/*  61 */     this.windowWidth = this.theGame.field_71443_c;
/*  62 */     this.windowHeight = this.theGame.field_71440_d;
/*  63 */     ScaledResolution var1 = new ScaledResolution(Minecraft.func_71410_x(), this.theGame.field_71443_c, this.theGame.field_71440_d);
/*  64 */     this.windowWidth = var1.func_78326_a();
/*  65 */     this.windowHeight = var1.func_78328_b();
/*  66 */     GL11.glClear(256);
/*  67 */     GL11.glMatrixMode(5889);
/*  68 */     GL11.glLoadIdentity();
/*  69 */     GL11.glOrtho(0.0D, this.windowWidth, this.windowHeight, 0.0D, 1000.0D, 3000.0D);
/*  70 */     GL11.glMatrixMode(5888);
/*  71 */     GL11.glLoadIdentity();
/*  72 */     GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void updateResearchWindow()
/*     */   {
/*  80 */     if ((this.theResearch.size() > 0) && (this.researchTime != 0L))
/*     */     {
/*  82 */       double var1 = (Minecraft.func_71386_F() - this.researchTime) / 3000.0D;
/*     */       
/*  84 */       if ((var1 < 0.0D) || (var1 > 1.0D))
/*     */       {
/*  86 */         this.theResearch.remove(0);
/*  87 */         if (this.theResearch.size() > 0) {
/*  88 */           this.researchTime = Minecraft.func_71386_F();
/*     */         } else {
/*  90 */           this.researchTime = 0L;
/*     */         }
/*     */       }
/*     */       else {
/*  94 */         updateResearchWindowScale();
/*  95 */         GL11.glDisable(2929);
/*  96 */         GL11.glDepthMask(false);
/*  97 */         double var3 = var1 * 2.0D;
/*     */         
/*  99 */         if (var3 > 1.0D)
/*     */         {
/* 101 */           var3 = 2.0D - var3;
/*     */         }
/*     */         
/* 104 */         var3 *= 4.0D;
/* 105 */         var3 = 1.0D - var3;
/*     */         
/* 107 */         if (var3 < 0.0D)
/*     */         {
/* 109 */           var3 = 0.0D;
/*     */         }
/*     */         
/* 112 */         var3 *= var3;
/* 113 */         var3 *= var3;
/* 114 */         int var5 = 0;
/* 115 */         int var6 = 0 - (int)(var3 * 36.0D);
/* 116 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 117 */         GL11.glEnable(3553);
/* 118 */         this.theGame.func_110434_K().func_110577_a(texture);
/* 119 */         GL11.glDisable(2896);
/* 120 */         func_73729_b(var5, var6, 96, 202, 160, 32);
/*     */         
/* 122 */         this.theGame.field_71466_p.func_78276_b("Research Completed!", var5 + 30, var6 + 7, 65280);
/* 123 */         int offset = this.theGame.field_71466_p.func_78256_a(((ResearchItem)this.theResearch.get(0)).getName());
/* 124 */         if (offset <= 125) {
/* 125 */           this.theGame.field_71466_p.func_78276_b(((ResearchItem)this.theResearch.get(0)).getName(), var5 + 30, var6 + 18, -1);
/*     */         }
/*     */         else {
/* 128 */           float vv = 125.0F / offset;
/* 129 */           GL11.glPushMatrix();
/* 130 */           GL11.glTranslatef(var5 + 30, var6 + 16 + 2.0F / vv, 0.0F);
/* 131 */           GL11.glScalef(vv, vv, vv);
/* 132 */           this.theGame.field_71466_p.func_78276_b(((ResearchItem)this.theResearch.get(0)).getName(), 0, 0, -1);
/*     */           
/* 134 */           GL11.glPopMatrix();
/*     */         }
/*     */         
/* 137 */         GL11.glDepthMask(true);
/* 138 */         GL11.glEnable(2929);
/*     */         
/* 140 */         RenderHelper.func_74520_c();
/* 141 */         GL11.glDisable(2896);
/* 142 */         GL11.glEnable(32826);
/* 143 */         GL11.glEnable(2903);
/* 144 */         GL11.glEnable(2896);
/* 145 */         if (((ResearchItem)this.theResearch.get(0)).icon_item != null) {
/* 146 */           this.itemRender.func_77015_a(Minecraft.func_71410_x().field_71466_p, Minecraft.func_71410_x().field_71446_o, InventoryUtils.cycleItemStack(((ResearchItem)this.theResearch.get(0)).icon_item), var5 + 8, var6 + 8);
/*     */ 
/*     */ 
/*     */         }
/* 150 */         else if (((ResearchItem)this.theResearch.get(0)).icon_resource != null) {
/* 151 */           Minecraft.func_71410_x().field_71446_o.func_110577_a(((ResearchItem)this.theResearch.get(0)).icon_resource);
/* 152 */           UtilsFX.drawTexturedQuadFull(var5 + 8, var6 + 8, this.field_73735_i);
/*     */         }
/* 154 */         GL11.glDisable(2896);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/gui/GuiResearchPopup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */