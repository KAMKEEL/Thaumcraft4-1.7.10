/*      */ package thaumcraft.client.gui;
/*      */ 
/*      */ import cpw.mods.fml.common.ObfuscationReflectionHelper;
/*      */ import cpw.mods.fml.relauncher.Side;
/*      */ import cpw.mods.fml.relauncher.SideOnly;
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.concurrent.ConcurrentHashMap;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*      */ import net.minecraft.client.gui.FontRenderer;
/*      */ import net.minecraft.client.gui.GuiButton;
/*      */ import net.minecraft.client.gui.GuiScreen;
/*      */ import net.minecraft.client.multiplayer.WorldClient;
/*      */ import net.minecraft.client.renderer.RenderHelper;
/*      */ import net.minecraft.client.renderer.Tessellator;
/*      */ import net.minecraft.client.renderer.entity.RenderItem;
/*      */ import net.minecraft.client.settings.GameSettings;
/*      */ import net.minecraft.client.settings.KeyBinding;
/*      */ import net.minecraft.enchantment.Enchantment;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.item.crafting.FurnaceRecipes;
/*      */ import net.minecraft.item.crafting.IRecipe;
/*      */ import net.minecraft.item.crafting.ShapedRecipes;
/*      */ import net.minecraft.item.crafting.ShapelessRecipes;
/*      */ import net.minecraft.nbt.NBTBase;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.StatCollector;
/*      */ import net.minecraftforge.oredict.ShapedOreRecipe;
/*      */ import net.minecraftforge.oredict.ShapelessOreRecipe;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ import thaumcraft.api.ThaumcraftApi;
/*      */ import thaumcraft.api.ThaumcraftApiHelper;
/*      */ import thaumcraft.api.aspects.Aspect;
/*      */ import thaumcraft.api.aspects.AspectList;
/*      */ import thaumcraft.api.crafting.CrucibleRecipe;
/*      */ import thaumcraft.api.crafting.IArcaneRecipe;
/*      */ import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
/*      */ import thaumcraft.api.crafting.InfusionRecipe;
/*      */ import thaumcraft.api.crafting.ShapedArcaneRecipe;
/*      */ import thaumcraft.api.crafting.ShapelessArcaneRecipe;
/*      */ import thaumcraft.api.research.ResearchCategories;
/*      */ import thaumcraft.api.research.ResearchItem;
/*      */ import thaumcraft.api.research.ResearchPage;
/*      */ import thaumcraft.api.research.ResearchPage.PageType;
/*      */ import thaumcraft.client.lib.TCFontRenderer;
/*      */ import thaumcraft.client.lib.UtilsFX;
/*      */ import thaumcraft.common.CommonProxy;
/*      */ import thaumcraft.common.Thaumcraft;
/*      */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*      */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*      */ import thaumcraft.common.lib.utils.InventoryUtils;
/*      */ 
/*      */ 
/*      */ @SideOnly(Side.CLIENT)
/*      */ public class GuiResearchRecipe
/*      */   extends GuiScreen
/*      */ {
/*   64 */   protected static RenderItem itemRenderer = new RenderItem();
/*      */   
/*   66 */   public static LinkedList<Object[]> history = new LinkedList();
/*      */   
/*   68 */   protected int paneWidth = 256;
/*   69 */   protected int paneHeight = 181;
/*      */   
/*      */ 
/*      */   protected double guiMapX;
/*      */   
/*      */ 
/*      */   protected double guiMapY;
/*      */   
/*      */ 
/*   78 */   protected int mouseX = 0;
/*      */   
/*      */ 
/*   81 */   protected int mouseY = 0;
/*      */   
/*      */   private GuiButton button;
/*      */   
/*      */   private ResearchItem research;
/*   86 */   private ResearchPage[] pages = null;
/*   87 */   private int page = 0;
/*   88 */   private int maxPages = 0;
/*      */   
/*   90 */   TCFontRenderer fr = null;
/*      */   
/*   92 */   HashMap<Aspect, ArrayList<ItemStack>> aspectItems = new HashMap();
/*   93 */   public static ConcurrentHashMap<Integer, ItemStack> cache = new ConcurrentHashMap();
/*      */   
/*      */   public static synchronized void putToCache(int key, ItemStack stack) {
/*   96 */     cache.put(Integer.valueOf(key), stack);
/*      */   }
/*      */   
/*      */   public static synchronized ItemStack getFromCache(int key) {
/*  100 */     return (ItemStack)cache.get(Integer.valueOf(key));
/*      */   }
/*      */   
/*      */   public GuiResearchRecipe(ResearchItem research, int page, double x, double y)
/*      */   {
/*  105 */     this.research = research;
/*  106 */     this.guiMapX = x;
/*  107 */     this.guiMapY = y;
/*      */     
/*  109 */     this.field_146297_k = Minecraft.func_71410_x();
/*      */     
/*  111 */     this.pages = research.getPages();
/*      */     
/*  113 */     List<ResearchPage> p1 = Arrays.asList(this.pages);
/*  114 */     ArrayList<ResearchPage> p2 = new ArrayList();
/*  115 */     for (ResearchPage pp : p1) {
/*  116 */       if ((pp == null) || (pp.type != ResearchPage.PageType.TEXT_CONCEALED) || (ThaumcraftApiHelper.isResearchComplete(this.field_146297_k.field_71439_g.func_70005_c_(), pp.research)))
/*      */       {
/*      */ 
/*      */ 
/*  120 */         p2.add(pp);
/*      */       }
/*      */     }
/*  123 */     this.pages = ((ResearchPage[])p2.toArray(new ResearchPage[0]));
/*      */     
/*  125 */     if (research.key.equals("ASPECTS")) {
/*  126 */       AspectList aspectsKnownSorted = Thaumcraft.proxy.getPlayerKnowledge().getAspectsDiscovered(Minecraft.func_71410_x().field_71439_g.func_70005_c_());
/*  127 */       List<String> list = (List)Thaumcraft.proxy.getScannedObjects().get(Minecraft.func_71410_x().field_71439_g.func_70005_c_());
/*  128 */       if ((list != null) && (list.size() > 0)) {
/*  129 */         for (String s : list) {
/*      */           try {
/*  131 */             String s2 = s.substring(1);
/*  132 */             ItemStack is = getFromCache(Integer.parseInt(s2));
/*  133 */             if (is != null)
/*      */             {
/*  135 */               AspectList tags = ThaumcraftCraftingManager.getObjectTags(is);
/*  136 */               tags = ThaumcraftCraftingManager.getBonusTags(is, tags);
/*      */               
/*  138 */               if ((tags != null) && (tags.size() > 0)) {
/*  139 */                 for (Aspect a : tags.getAspects()) {
/*  140 */                   ArrayList<ItemStack> items = (ArrayList)this.aspectItems.get(a);
/*  141 */                   if (items == null) {
/*  142 */                     items = new ArrayList();
/*      */                   }
/*  144 */                   ItemStack is2 = is.func_77946_l();
/*  145 */                   is2.field_77994_a = tags.getAmount(a);
/*  146 */                   items.add(is2);
/*  147 */                   this.aspectItems.put(a, items);
/*      */                 }
/*      */               }
/*      */             }
/*      */           } catch (NumberFormatException e) {}
/*      */         }
/*      */       }
/*  154 */       ArrayList<ResearchPage> tpl = new ArrayList();
/*  155 */       for (ResearchPage p : research.getPages()) tpl.add(p);
/*  156 */       AspectList tal = new AspectList();
/*  157 */       if (aspectsKnownSorted != null) {
/*  158 */         int count = 0;
/*  159 */         for (Aspect aspect : aspectsKnownSorted.getAspectsSorted()) {
/*  160 */           if (count <= 4) {
/*  161 */             count++;
/*  162 */             tal.add(aspect, aspectsKnownSorted.getAmount(aspect));
/*      */           }
/*  164 */           if (count == 4) {
/*  165 */             count = 0;
/*  166 */             tpl.add(new ResearchPage(tal.copy()));
/*  167 */             tal = new AspectList();
/*      */           }
/*      */         }
/*  170 */         if (count > 0) {
/*  171 */           tpl.add(new ResearchPage(tal));
/*      */         }
/*      */       }
/*  174 */       this.pages = ((ResearchPage[])tpl.toArray(this.pages));
/*      */     }
/*      */     
/*  177 */     this.maxPages = this.pages.length;
/*      */     
/*      */ 
/*  180 */     this.fr = new TCFontRenderer(this.field_146297_k.field_71474_y, TCFontRenderer.FONT_NORMAL, this.field_146297_k.field_71446_o, true);
/*  181 */     if (page % 2 == 1) page--;
/*  182 */     this.page = page;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void func_73866_w_() {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void func_146284_a(GuiButton par1GuiButton)
/*      */   {
/*  200 */     super.func_146284_a(par1GuiButton);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void func_73869_a(char par1, int par2)
/*      */   {
/*  209 */     if ((par2 == this.field_146297_k.field_71474_y.field_151445_Q.func_151463_i()) || (par2 == 1))
/*      */     {
/*      */ 
/*  212 */       history.clear();
/*  213 */       this.field_146297_k.func_147108_a(new GuiResearchBrowser(this.guiMapX, this.guiMapY));
/*      */     }
/*      */     else
/*      */     {
/*  217 */       super.func_73869_a(par1, par2);
/*      */     }
/*      */   }
/*      */   
/*      */   public void func_146281_b()
/*      */   {
/*  223 */     super.func_146281_b();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void func_73863_a(int par1, int par2, float par3)
/*      */   {
/*  235 */     func_146276_q_();
/*  236 */     genResearchBackground(par1, par2, par3);
/*      */     
/*      */ 
/*      */ 
/*  240 */     int sw = (this.field_146294_l - this.paneWidth) / 2;
/*  241 */     int sh = (this.field_146295_m - this.paneHeight) / 2;
/*      */     
/*      */ 
/*  244 */     if (!history.isEmpty()) {
/*  245 */       int mx = par1 - (sw + 118);
/*  246 */       int my = par2 - (sh + 189);
/*  247 */       if ((mx >= 0) && (my >= 0) && (mx < 20) && (my < 12)) {
/*  248 */         this.field_146289_q.func_78261_a(StatCollector.func_74838_a("recipe.return"), par1, par2, 16777215);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*  253 */   String tex1 = "textures/gui/gui_researchbook.png";
/*  254 */   String tex2 = "textures/gui/gui_researchbook_overlay.png";
/*      */   
/*      */ 
/*      */ 
/*      */   protected void genResearchBackground(int par1, int par2, float par3)
/*      */   {
/*  260 */     int sw = (this.field_146294_l - this.paneWidth) / 2;
/*  261 */     int sh = (this.field_146295_m - this.paneHeight) / 2;
/*      */     
/*  263 */     float var10 = (this.field_146294_l - this.paneWidth * 1.3F) / 2.0F;
/*  264 */     float var11 = (this.field_146295_m - this.paneHeight * 1.3F) / 2.0F;
/*      */     
/*  266 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  267 */     UtilsFX.bindTexture(this.tex1);
/*      */     
/*  269 */     GL11.glPushMatrix();
/*  270 */     GL11.glTranslatef(var10, var11, 0.0F);
/*  271 */     GL11.glEnable(3042);
/*  272 */     GL11.glScalef(1.3F, 1.3F, 1.0F);
/*  273 */     func_73729_b(0, 0, 0, 0, this.paneWidth, this.paneHeight);
/*  274 */     GL11.glPopMatrix();
/*      */     
/*  276 */     this.reference.clear();
/*  277 */     this.tooltip = null;
/*  278 */     int current = 0;
/*  279 */     for (int a = 0; a < this.pages.length; a++) {
/*  280 */       if (((current == this.page) || (current == this.page + 1)) && (current < this.maxPages)) {
/*  281 */         drawPage(this.pages[a], current % 2, sw, sh, par1, par2);
/*      */       }
/*  283 */       current++;
/*  284 */       if (current > this.page + 1)
/*      */         break;
/*      */     }
/*  287 */     if (this.tooltip != null) {
/*  288 */       UtilsFX.drawCustomTooltip(this, itemRenderer, this.field_146289_q, (List)this.tooltip[0], ((Integer)this.tooltip[1]).intValue(), ((Integer)this.tooltip[2]).intValue(), ((Integer)this.tooltip[3]).intValue());
/*      */     }
/*      */     
/*  291 */     UtilsFX.bindTexture(this.tex1);
/*  292 */     int mx1 = par1 - (sw + 261);
/*  293 */     int my1 = par2 - (sh + 189);
/*  294 */     int mx2 = par1 - (sw - 17);
/*  295 */     int my2 = par2 - (sh + 189);
/*      */     
/*  297 */     float bob = MathHelper.func_76126_a(this.field_146297_k.field_71439_g.field_70173_aa / 3.0F) * 0.2F + 0.1F;
/*      */     
/*      */ 
/*  300 */     if (!history.isEmpty()) {
/*  301 */       GL11.glEnable(3042);
/*  302 */       drawTexturedModalRectScaled(sw + 118, sh + 189, 38, 202, 20, 12, bob);
/*      */     }
/*      */     
/*      */ 
/*  306 */     if (this.page > 0) {
/*  307 */       GL11.glEnable(3042);
/*  308 */       drawTexturedModalRectScaled(sw - 16, sh + 190, 0, 184, 12, 8, bob);
/*      */     }
/*  310 */     if (this.page < this.maxPages - 2) {
/*  311 */       GL11.glEnable(3042);
/*  312 */       drawTexturedModalRectScaled(sw + 262, sh + 190, 12, 184, 12, 8, bob);
/*      */     }
/*      */   }
/*      */   
/*  316 */   private Object[] tooltip = null;
/*      */   
/*      */   public void drawCustomTooltip(GuiScreen gui, RenderItem itemRenderer, FontRenderer fr, List var4, int par2, int par3, int subTipColor)
/*      */   {
/*  320 */     this.tooltip = new Object[] { var4, Integer.valueOf(par2), Integer.valueOf(par3), Integer.valueOf(subTipColor) };
/*      */   }
/*      */   
/*  323 */   private long lastCycle = 0L;
/*      */   
/*      */   private void drawPage(ResearchPage pageParm, int side, int x, int y, int mx, int my) {
/*  326 */     GL11.glPushAttrib(1048575);
/*  327 */     if (this.lastCycle < System.currentTimeMillis()) {
/*  328 */       this.cycle += 1;
/*  329 */       this.lastCycle = (System.currentTimeMillis() + 1000L);
/*      */     }
/*      */     
/*  332 */     if ((this.page == 0) && (side == 0)) {
/*  333 */       func_73729_b(x + 4, y - 13, 24, 184, 96, 4);
/*  334 */       func_73729_b(x + 4, y + 4, 24, 184, 96, 4);
/*  335 */       int offset = this.field_146289_q.func_78256_a(this.research.getName());
/*  336 */       if (offset <= 130) {
/*  337 */         this.field_146289_q.func_78276_b(this.research.getName(), x + 52 - offset / 2, y - 6, 3158064);
/*      */       } else {
/*  339 */         float vv = 130.0F / offset;
/*  340 */         GL11.glPushMatrix();
/*  341 */         GL11.glTranslatef(x + 52 - offset / 2 * vv, y - 6.0F * vv, 0.0F);
/*  342 */         GL11.glScalef(vv, vv, vv);
/*  343 */         this.field_146289_q.func_78276_b(this.research.getName(), 0, 0, 3158064);
/*  344 */         GL11.glPopMatrix();
/*      */       }
/*  346 */       y += 25;
/*      */     }
/*      */     
/*      */ 
/*  350 */     GL11.glAlphaFunc(516, 0.003921569F);
/*  351 */     if ((pageParm.type == ResearchPage.PageType.TEXT) || (pageParm.type == ResearchPage.PageType.TEXT_CONCEALED)) {
/*  352 */       drawTextPage(side, x, y - 10, pageParm.getTranslatedText());
/*      */     }
/*  354 */     else if (pageParm.type == ResearchPage.PageType.ASPECTS) {
/*  355 */       drawAspectPage(side, x - 8, y - 8, mx, my, pageParm.aspects);
/*      */     }
/*  357 */     else if (pageParm.type == ResearchPage.PageType.CRUCIBLE_CRAFTING) {
/*  358 */       drawCruciblePage(side, x - 4, y - 8, mx, my, pageParm);
/*      */     }
/*  360 */     else if (pageParm.type == ResearchPage.PageType.NORMAL_CRAFTING) {
/*  361 */       drawCraftingPage(side, x - 4, y - 8, mx, my, pageParm);
/*      */     }
/*  363 */     else if (pageParm.type == ResearchPage.PageType.ARCANE_CRAFTING) {
/*  364 */       drawArcaneCraftingPage(side, x - 4, y - 8, mx, my, pageParm);
/*      */     }
/*  366 */     else if (pageParm.type == ResearchPage.PageType.COMPOUND_CRAFTING) {
/*  367 */       drawCompoundCraftingPage(side, x - 4, y - 8, mx, my, pageParm);
/*      */     }
/*  369 */     else if (pageParm.type == ResearchPage.PageType.INFUSION_CRAFTING) {
/*  370 */       drawInfusionPage(side, x - 4, y - 8, mx, my, pageParm);
/*      */     }
/*  372 */     else if (pageParm.type == ResearchPage.PageType.INFUSION_ENCHANTMENT) {
/*  373 */       drawInfusionEnchantingPage(side, x - 4, y - 8, mx, my, pageParm);
/*      */     }
/*  375 */     else if (pageParm.type == ResearchPage.PageType.SMELTING) {
/*  376 */       drawSmeltingPage(side, x - 4, y - 8, mx, my, pageParm);
/*      */     }
/*      */     
/*      */ 
/*  380 */     GL11.glAlphaFunc(516, 0.1F);
/*  381 */     GL11.glPopAttrib();
/*      */   }
/*      */   
/*      */   private void drawCompoundCraftingPage(int side, int x, int y, int mx, int my, ResearchPage page) {
/*  385 */     List r = (List)page.recipe;
/*      */     
/*  387 */     if (r != null) {
/*  388 */       AspectList aspects = (AspectList)r.get(0);
/*  389 */       int dx = ((Integer)r.get(1)).intValue();
/*  390 */       int dy = ((Integer)r.get(2)).intValue();
/*  391 */       int dz = ((Integer)r.get(3)).intValue();
/*  392 */       int xoff = 64 - (dx * 16 + dz * 16) / 2;
/*  393 */       int yoff = -dy * 25;
/*      */       
/*  395 */       List<ItemStack> items = (List)r.get(4);
/*      */       
/*  397 */       GL11.glPushMatrix();
/*  398 */       int start = side * 152;
/*      */       
/*  400 */       String text = StatCollector.func_74838_a("recipe.type.construct");
/*  401 */       int offset = this.field_146289_q.func_78256_a(text);
/*  402 */       this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y, 5263440);
/*      */       
/*  404 */       int mposx = mx;
/*  405 */       int mposy = my;
/*      */       
/*  407 */       if ((aspects != null) && (aspects.size() > 0)) {
/*  408 */         int count = 0;
/*  409 */         for (Aspect tag : aspects.getAspectsSortedAmount()) {
/*  410 */           UtilsFX.drawTag(x + start + 14 + 18 * count + (5 - aspects.size()) * 8, y + 182, tag, aspects.getAmount(tag), 0, 0.0D, 771, 1.0F, false);
/*      */           
/*  412 */           count++;
/*      */         }
/*  414 */         count = 0;
/*  415 */         for (Aspect tag : aspects.getAspectsSortedAmount()) {
/*  416 */           int tx = x + start + 14 + 18 * count + (5 - aspects.size()) * 8;
/*  417 */           int ty = y + 182;
/*  418 */           if ((mposx >= tx) && (mposy >= ty) && (mposx < tx + 16) && (mposy < ty + 16))
/*      */           {
/*  420 */             drawCustomTooltip(this, itemRenderer, this.field_146289_q, Arrays.asList(new String[] { tag.getName(), tag.getLocalizedDescription() }), mx, my - 8, 11);
/*      */           }
/*      */           
/*  423 */           count++;
/*      */         }
/*      */       }
/*      */       
/*  427 */       UtilsFX.bindTexture(this.tex2);
/*      */       
/*  429 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  430 */       RenderHelper.func_74520_c();
/*  431 */       GL11.glDisable(2896);
/*      */       
/*  433 */       if ((aspects != null) && (aspects.size() > 0)) {
/*  434 */         GL11.glPushMatrix();
/*  435 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.4F);
/*  436 */         GL11.glEnable(3042);
/*  437 */         GL11.glTranslatef(x + start, y + 174, 0.0F);
/*  438 */         GL11.glScalef(2.0F, 2.0F, 1.0F);
/*  439 */         func_73729_b(0, 0, 68, 76, 12, 12);
/*  440 */         GL11.glPopMatrix();
/*      */       }
/*      */       
/*  443 */       GL11.glPushMatrix();
/*  444 */       float sz = 0.0F;
/*  445 */       if (dy > 3) {
/*  446 */         sz = (dy - 3) * 0.2F;
/*  447 */         GL11.glTranslatef(x + start + xoff * (1.0F + sz), y + 108 + yoff * (1.0F - sz), 0.0F);
/*  448 */         GL11.glScalef(1.0F - sz, 1.0F - sz, 1.0F - sz);
/*      */       } else {
/*  450 */         GL11.glTranslatef(x + start + xoff, y + 108 + yoff, 0.0F);
/*      */       }
/*      */       
/*      */ 
/*  454 */       GL11.glPushMatrix();
/*  455 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
/*  456 */       GL11.glEnable(3042);
/*  457 */       GL11.glTranslatef(-8 - xoff, -119 + Math.max(3 - dx, 3 - dz) * 8 + dx * 4 + dz * 4 + dy * 50, 0.0F);
/*  458 */       GL11.glScalef(2.0F, 2.0F, 1.0F);
/*  459 */       func_73729_b(0, 0, 0, 72, 64, 44);
/*  460 */       GL11.glPopMatrix();
/*      */       
/*  462 */       int count = 0;
/*  463 */       for (int j = 0; j < dy; j++)
/*  464 */         for (int k = dz - 1; k >= 0; k--)
/*  465 */           for (int i = dx - 1; i >= 0; i--)
/*      */           {
/*  467 */             int px = i * 16 + k * 16;
/*  468 */             int py = -i * 8 + k * 8 + j * 50;
/*      */             
/*  470 */             GL11.glPushMatrix();
/*  471 */             GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  472 */             RenderHelper.func_74520_c();
/*  473 */             GL11.glEnable(2884);
/*      */             
/*  475 */             GL11.glTranslatef(0.0F, 0.0F, 60 - j * 10);
/*  476 */             if (items.get(count) != null) {
/*  477 */               itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(items.get(count)), px, py);
/*      */               
/*      */ 
/*  480 */               itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(items.get(count)).func_77946_l().func_77979_a(1), px, py);
/*      */             }
/*      */             
/*      */ 
/*  484 */             RenderHelper.func_74518_a();
/*      */             
/*  486 */             GL11.glPopMatrix();
/*      */             
/*  488 */             count++;
/*      */           }
/*  490 */       GL11.glEnable(2896);
/*  491 */       GL11.glPopMatrix();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  519 */       count = 0;
/*  520 */       for (int j = 0; j < dy; j++) {
/*  521 */         for (int k = dz - 1; k >= 0; k--) {
/*  522 */           for (int i = dx - 1; i >= 0; i--)
/*      */           {
/*  524 */             int px = (int)(x + start + xoff * (1.0F + sz) + i * 16 * (1.0F - sz) + k * 16 * (1.0F - sz));
/*  525 */             int py = (int)(y + 108 + yoff * (1.0F - sz) - i * 8 * (1.0F - sz) + k * 8 * (1.0F - sz) + j * 50 * (1.0F - sz));
/*      */             
/*  527 */             if ((items.get(count) != null) && 
/*  528 */               (mposx >= px) && (mposy >= py) && (mposx < px + 16.0F * (1.0F - sz)) && (mposy < py + 16.0F * (1.0F - sz)))
/*      */             {
/*  530 */               List addtext = InventoryUtils.cycleItemStack(items.get(count)).func_82840_a(this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);
/*  531 */               Object[] ref = findRecipeReference(InventoryUtils.cycleItemStack(items.get(count)));
/*  532 */               if ((ref != null) && (!((String)ref[0]).equals(this.research.key))) {
/*  533 */                 addtext.add("§8§o" + StatCollector.func_74838_a("recipe.clickthrough"));
/*  534 */                 this.reference.add(Arrays.asList(new Serializable[] { Integer.valueOf(mx), Integer.valueOf(my), (String)ref[0], (Integer)ref[1] }));
/*      */               }
/*      */               
/*  537 */               drawCustomTooltip(this, itemRenderer, this.field_146289_q, addtext, mx, my, 11);
/*      */             }
/*      */             
/*  540 */             count++;
/*      */           }
/*      */         }
/*      */       }
/*  544 */       GL11.glPopMatrix();
/*      */     }
/*      */   }
/*      */   
/*      */   private void drawAspectPage(int side, int x, int y, int mx, int my, AspectList aspects)
/*      */   {
/*  550 */     if ((aspects != null) && (aspects.size() > 0)) {
/*  551 */       GL11.glPushMatrix();
/*  552 */       int start = side * 152;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  558 */       int mposx = mx;
/*  559 */       int mposy = my;
/*  560 */       int count = 0;
/*  561 */       for (Aspect aspect : aspects.getAspectsSorted())
/*      */       {
/*  563 */         if (aspect.getImage() != null)
/*      */         {
/*  565 */           GL11.glPushMatrix();
/*  566 */           int tx = x + start;
/*  567 */           int ty = y + count * 50;
/*  568 */           if ((mposx >= tx) && (mposy >= ty) && (mposx < tx + 40) && (mposy < ty + 40))
/*      */           {
/*  570 */             UtilsFX.bindTexture("textures/aspects/_back.png");
/*  571 */             GL11.glPushMatrix();
/*  572 */             GL11.glEnable(3042);
/*  573 */             GL11.glBlendFunc(770, 771);
/*  574 */             GL11.glTranslated(x + start - 5, y + count * 50 - 5, 0.0D);
/*  575 */             GL11.glScaled(2.5D, 2.5D, 0.0D);
/*  576 */             UtilsFX.drawTexturedQuadFull(0, 0, this.field_73735_i);
/*  577 */             GL11.glDisable(3042);
/*  578 */             GL11.glPopMatrix();
/*      */           }
/*  580 */           GL11.glScalef(2.0F, 2.0F, 2.0F);
/*  581 */           UtilsFX.drawTag((x + start) / 2, (y + count * 50) / 2, aspect, aspects.getAmount(aspect), 0, this.field_73735_i);
/*  582 */           GL11.glPopMatrix();
/*  583 */           String text = aspect.getName();
/*  584 */           int offset = this.fr.getStringWidth(text) / 2;
/*  585 */           this.fr.drawString(text, x + start + 16 - offset, y + 33 + count * 50, 5263440);
/*      */           
/*  587 */           if (aspect.getComponents() != null) {
/*  588 */             GL11.glPushMatrix();
/*  589 */             GL11.glScalef(1.5F, 1.5F, 1.5F);
/*  590 */             UtilsFX.drawTag((int)((x + start + 54) / 1.5F), (int)((y + 4 + count * 50) / 1.5F), aspect.getComponents()[0], 0.0F, 0, this.field_73735_i);
/*  591 */             UtilsFX.drawTag((int)((x + start + 96) / 1.5F), (int)((y + 4 + count * 50) / 1.5F), aspect.getComponents()[1], 0.0F, 0, this.field_73735_i);
/*  592 */             GL11.glPopMatrix();
/*      */             
/*      */ 
/*  595 */             text = aspect.getComponents()[0].getName();
/*  596 */             offset = this.fr.getStringWidth(text) / 2;
/*  597 */             this.fr.drawString("§o" + text, x + start + 16 - offset + 50, y + 30 + count * 50, 5263440);
/*      */             
/*  599 */             text = aspect.getComponents()[1].getName();
/*  600 */             offset = this.fr.getStringWidth(text) / 2;
/*  601 */             this.fr.drawString("§o" + text, x + start + 16 - offset + 92, y + 30 + count * 50, 5263440);
/*      */             
/*  603 */             this.field_146289_q.func_78276_b("=", x + start + 7 + 32, y + 12 + count * 50, 10066329);
/*  604 */             this.field_146289_q.func_78276_b("+", x + start + 4 + 79, y + 12 + count * 50, 10066329);
/*      */           } else {
/*  606 */             this.fr.drawString(StatCollector.func_74838_a("tc.aspect.primal"), x + start + 48, y + 12 + count * 50, 4473924);
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*  611 */         count++;
/*      */       }
/*      */       
/*  614 */       count = 0;
/*  615 */       for (Aspect aspect : aspects.getAspectsSorted())
/*      */       {
/*  617 */         int tx = x + start;
/*  618 */         int ty = y + count * 50;
/*  619 */         if ((mposx >= tx) && (mposy >= ty) && (mposx < tx + 40) && (mposy < ty + 40))
/*      */         {
/*  621 */           ArrayList<ItemStack> items = (ArrayList)this.aspectItems.get(aspect);
/*  622 */           if ((items != null) && (items.size() > 0)) {
/*  623 */             int xcount = 0;
/*  624 */             int ycount = 0;
/*  625 */             for (ItemStack item : items) {
/*  626 */               GL11.glPushMatrix();
/*  627 */               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  628 */               RenderHelper.func_74520_c();
/*  629 */               GL11.glEnable(2884);
/*  630 */               itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(item), mposx + 8 + xcount * 17, 17 * ycount + (mposy - (4 + items.size() / 8 * 8)));
/*      */               
/*      */ 
/*      */ 
/*  634 */               itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(item), mposx + 8 + xcount * 17, 17 * ycount + (mposy - (4 + items.size() / 8 * 8)));
/*      */               
/*      */ 
/*      */ 
/*  638 */               RenderHelper.func_74518_a();
/*      */               
/*  640 */               GL11.glPopMatrix();
/*  641 */               xcount++;
/*  642 */               if (xcount >= 8) {
/*  643 */                 xcount = 0;
/*  644 */                 ycount++;
/*      */               }
/*      */             }
/*  647 */             GL11.glEnable(2896);
/*      */           }
/*      */         }
/*  650 */         count++;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  655 */       GL11.glPopMatrix();
/*      */     }
/*      */   }
/*      */   
/*      */   private void drawArcaneCraftingPage(int side, int x, int y, int mx, int my, ResearchPage pageParm)
/*      */   {
/*  661 */     IArcaneRecipe recipe = null;
/*  662 */     Object tr = null;
/*  663 */     if ((pageParm.recipe instanceof Object[])) {
/*      */       try {
/*  665 */         tr = ((Object[])(Object[])pageParm.recipe)[this.cycle];
/*      */       } catch (Exception e) {
/*  667 */         this.cycle = 0;
/*  668 */         tr = ((Object[])(Object[])pageParm.recipe)[this.cycle];
/*      */       }
/*      */     } else {
/*  671 */       tr = pageParm.recipe;
/*      */     }
/*      */     
/*  674 */     if ((tr instanceof ShapedArcaneRecipe)) {
/*  675 */       recipe = (ShapedArcaneRecipe)tr;
/*      */     }
/*  677 */     else if ((tr instanceof ShapelessArcaneRecipe)) {
/*  678 */       recipe = (ShapelessArcaneRecipe)tr;
/*      */     }
/*  680 */     if (recipe == null) { return;
/*      */     }
/*  682 */     GL11.glPushMatrix();
/*  683 */     int start = side * 152;
/*      */     
/*  685 */     UtilsFX.bindTexture(this.tex2);
/*      */     
/*      */ 
/*  688 */     GL11.glPushMatrix();
/*  689 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  690 */     GL11.glEnable(3042);
/*  691 */     GL11.glTranslatef(x + start, y, 0.0F);
/*  692 */     GL11.glScalef(2.0F, 2.0F, 1.0F);
/*  693 */     func_73729_b(2, 27, 112, 15, 52, 52);
/*  694 */     func_73729_b(20, 7, 20, 3, 16, 16);
/*  695 */     GL11.glPopMatrix();
/*      */     
/*  697 */     GL11.glPushMatrix();
/*  698 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.4F);
/*  699 */     GL11.glEnable(3042);
/*  700 */     GL11.glTranslatef(x + start, y + 164, 0.0F);
/*  701 */     GL11.glScalef(2.0F, 2.0F, 1.0F);
/*  702 */     func_73729_b(0, 0, 68, 76, 12, 12);
/*  703 */     GL11.glPopMatrix();
/*      */     
/*  705 */     int mposx = mx;
/*  706 */     int mposy = my;
/*      */     
/*  708 */     AspectList tags = recipe.getAspects();
/*  709 */     if ((tags != null) && (tags.size() > 0)) {
/*  710 */       int count = 0;
/*  711 */       for (Aspect tag : tags.getAspectsSortedAmount()) {
/*  712 */         UtilsFX.drawTag(x + start + 14 + 18 * count + (5 - tags.size()) * 8, y + 172, tag, tags.getAmount(tag), 0, 0.0D, 771, 1.0F);
/*      */         
/*  714 */         count++;
/*      */       }
/*  716 */       count = 0;
/*  717 */       for (Aspect tag : tags.getAspectsSortedAmount()) {
/*  718 */         int tx = x + start + 14 + 18 * count + (5 - tags.size()) * 8;
/*  719 */         int ty = y + 172;
/*  720 */         if ((mposx >= tx) && (mposy >= ty) && (mposx < tx + 16) && (mposy < ty + 16))
/*      */         {
/*  722 */           drawCustomTooltip(this, itemRenderer, this.field_146289_q, Arrays.asList(new String[] { tag.getName(), tag.getLocalizedDescription() }), mx, my - 8, 11);
/*      */         }
/*      */         
/*  725 */         count++;
/*      */       }
/*      */     }
/*      */     
/*  729 */     GL11.glPushMatrix();
/*  730 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  731 */     GL11.glTranslated(0.0D, 0.0D, 100.0D);
/*      */     
/*  733 */     RenderHelper.func_74520_c();
/*  734 */     GL11.glEnable(2884);
/*  735 */     itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(recipe.getRecipeOutput()), x + 48 + start, y + 22);
/*      */     
/*      */ 
/*      */ 
/*  739 */     itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(recipe.getRecipeOutput()), x + 48 + start, y + 22);
/*      */     
/*      */ 
/*      */ 
/*  743 */     RenderHelper.func_74518_a();
/*  744 */     GL11.glEnable(2896);
/*  745 */     GL11.glPopMatrix();
/*      */     
/*  747 */     if ((mposx >= x + 48 + start) && (mposy >= y + 27) && (mposx < x + 48 + start + 16) && (mposy < y + 27 + 16))
/*      */     {
/*      */ 
/*  750 */       drawCustomTooltip(this, itemRenderer, this.field_146289_q, InventoryUtils.cycleItemStack(recipe.getRecipeOutput()).func_82840_a(this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x), mx, my, 11);
/*      */     }
/*      */     
/*      */ 
/*  754 */     String text = StatCollector.func_74838_a("recipe.type.arcane");
/*  755 */     int offset = this.field_146289_q.func_78256_a(text);
/*  756 */     this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y, 5263440);
/*      */     
/*  758 */     if ((recipe != null) && ((recipe instanceof ShapedArcaneRecipe)))
/*      */     {
/*      */ 
/*  761 */       int rw = ((ShapedArcaneRecipe)recipe).width;
/*  762 */       int rh = ((ShapedArcaneRecipe)recipe).height;
/*  763 */       Object[] items = ((ShapedArcaneRecipe)recipe).getInput();
/*      */       
/*  765 */       for (int i = 0; (i < rw) && (i < 3); i++) {
/*  766 */         for (int j = 0; (j < rh) && (j < 3); j++) {
/*  767 */           if (items[(i + j * rw)] != null)
/*      */           {
/*  769 */             GL11.glPushMatrix();
/*  770 */             GL11.glTranslated(0.0D, 0.0D, 100.0D);
/*  771 */             GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  772 */             RenderHelper.func_74520_c();
/*  773 */             GL11.glEnable(2884);
/*      */             
/*  775 */             itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(items[(i + j * rw)]), x + start + 16 + i * 32, y + 66 + j * 32);
/*      */             
/*      */ 
/*      */ 
/*  779 */             itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(items[(i + j * rw)]).func_77946_l().func_77979_a(1), x + start + 16 + i * 32, y + 66 + j * 32);
/*      */             
/*      */ 
/*      */ 
/*  783 */             RenderHelper.func_74518_a();
/*  784 */             GL11.glEnable(2896);
/*  785 */             GL11.glPopMatrix();
/*      */           }
/*      */         }
/*      */       }
/*  789 */       for (int i = 0; (i < rw) && (i < 3); i++) {
/*  790 */         for (int j = 0; (j < rh) && (j < 3); j++) {
/*  791 */           if (items[(i + j * rw)] != null)
/*      */           {
/*  793 */             if ((mposx >= x + 16 + start + i * 32) && (mposy >= y + 66 + j * 32) && (mposx < x + 16 + start + i * 32 + 16) && (mposy < y + 66 + j * 32 + 16))
/*      */             {
/*  795 */               List addtext = InventoryUtils.cycleItemStack(items[(i + j * rw)]).func_82840_a(this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);
/*      */               
/*  797 */               Object[] ref = findRecipeReference(InventoryUtils.cycleItemStack(items[(i + j * rw)]));
/*  798 */               if ((ref != null) && (!((String)ref[0]).equals(this.research.key))) {
/*  799 */                 addtext.add("§8§o" + StatCollector.func_74838_a("recipe.clickthrough"));
/*  800 */                 this.reference.add(Arrays.asList(new Serializable[] { Integer.valueOf(mx), Integer.valueOf(my), (String)ref[0], (Integer)ref[1] }));
/*      */               }
/*      */               
/*  803 */               drawCustomTooltip(this, itemRenderer, this.field_146289_q, addtext, mx, my, 11);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  809 */     if ((recipe != null) && ((recipe instanceof ShapelessArcaneRecipe)))
/*      */     {
/*  811 */       List<Object> items = ((ShapelessArcaneRecipe)recipe).getInput();
/*      */       
/*  813 */       for (int i = 0; (i < items.size()) && (i < 9); i++) {
/*  814 */         if (items.get(i) != null)
/*      */         {
/*  816 */           GL11.glPushMatrix();
/*  817 */           GL11.glTranslated(0.0D, 0.0D, 100.0D);
/*  818 */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  819 */           RenderHelper.func_74520_c();
/*  820 */           GL11.glEnable(2884);
/*      */           
/*  822 */           itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(items.get(i)), x + start + 16 + i % 3 * 32, y + 66 + i / 3 * 32);
/*      */           
/*      */ 
/*      */ 
/*  826 */           itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(items.get(i)), x + start + 16 + i % 3 * 32, y + 66 + i / 3 * 32);
/*      */           
/*      */ 
/*      */ 
/*  830 */           RenderHelper.func_74518_a();
/*  831 */           GL11.glEnable(2896);
/*  832 */           GL11.glPopMatrix();
/*      */         }
/*      */       }
/*  835 */       for (int i = 0; (i < items.size()) && (i < 9); i++) {
/*  836 */         if (items.get(i) != null)
/*      */         {
/*  838 */           if ((mposx >= x + 16 + start + i % 3 * 32) && (mposy >= y + 66 + i / 3 * 32) && (mposx < x + 16 + start + i % 3 * 32 + 16) && (mposy < y + 66 + i / 3 * 32 + 16))
/*      */           {
/*  840 */             List addtext = InventoryUtils.cycleItemStack(items.get(i)).func_82840_a(this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);
/*  841 */             Object[] ref = findRecipeReference(InventoryUtils.cycleItemStack(items.get(i)));
/*  842 */             if ((ref != null) && (!((String)ref[0]).equals(this.research.key))) {
/*  843 */               addtext.add("§8§o" + StatCollector.func_74838_a("recipe.clickthrough"));
/*  844 */               this.reference.add(Arrays.asList(new Serializable[] { Integer.valueOf(mx), Integer.valueOf(my), (String)ref[0], (Integer)ref[1] }));
/*      */             }
/*  846 */             drawCustomTooltip(this, itemRenderer, this.field_146289_q, addtext, mx, my, 11);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  852 */     GL11.glPopMatrix();
/*      */   }
/*      */   
/*      */   private void drawCraftingPage(int side, int x, int y, int mx, int my, ResearchPage pageParm)
/*      */   {
/*  857 */     IRecipe recipe = null;
/*  858 */     Object tr = null;
/*  859 */     if ((pageParm.recipe instanceof Object[])) {
/*      */       try {
/*  861 */         tr = ((Object[])(Object[])pageParm.recipe)[this.cycle];
/*      */       } catch (Exception e) {
/*  863 */         this.cycle = 0;
/*  864 */         tr = ((Object[])(Object[])pageParm.recipe)[this.cycle];
/*      */       }
/*      */     } else {
/*  867 */       tr = pageParm.recipe;
/*      */     }
/*      */     
/*  870 */     if ((tr instanceof ShapedRecipes)) {
/*  871 */       recipe = (ShapedRecipes)tr;
/*      */     }
/*  873 */     else if ((tr instanceof ShapelessRecipes)) {
/*  874 */       recipe = (ShapelessRecipes)tr;
/*      */     }
/*  876 */     else if ((tr instanceof ShapedOreRecipe)) {
/*  877 */       recipe = (ShapedOreRecipe)tr;
/*      */     }
/*  879 */     else if ((tr instanceof ShapelessOreRecipe)) {
/*  880 */       recipe = (ShapelessOreRecipe)tr;
/*      */     }
/*      */     
/*  883 */     if (recipe == null) { return;
/*      */     }
/*  885 */     GL11.glPushMatrix();
/*  886 */     int start = side * 152;
/*      */     
/*  888 */     UtilsFX.bindTexture(this.tex2);
/*      */     
/*      */ 
/*  891 */     GL11.glPushMatrix();
/*  892 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  893 */     GL11.glEnable(3042);
/*  894 */     GL11.glTranslatef(x + start, y, 0.0F);
/*  895 */     GL11.glScalef(2.0F, 2.0F, 1.0F);
/*  896 */     func_73729_b(2, 32, 60, 15, 52, 52);
/*  897 */     func_73729_b(20, 12, 20, 3, 16, 16);
/*  898 */     GL11.glPopMatrix();
/*      */     
/*  900 */     int mposx = mx;
/*  901 */     int mposy = my;
/*      */     
/*  903 */     GL11.glPushMatrix();
/*  904 */     GL11.glTranslated(0.0D, 0.0D, 100.0D);
/*  905 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  906 */     RenderHelper.func_74520_c();
/*  907 */     GL11.glEnable(2884);
/*  908 */     itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(recipe.func_77571_b()), x + 48 + start, y + 32);
/*      */     
/*      */ 
/*      */ 
/*  912 */     itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(recipe.func_77571_b()), x + 48 + start, y + 32);
/*      */     
/*      */ 
/*      */ 
/*  916 */     RenderHelper.func_74518_a();
/*  917 */     GL11.glEnable(2896);
/*  918 */     GL11.glPopMatrix();
/*      */     
/*  920 */     if ((mposx >= x + 48 + start) && (mposy >= y + 32) && (mposx < x + 48 + start + 16) && (mposy < y + 32 + 16))
/*      */     {
/*      */ 
/*  923 */       drawCustomTooltip(this, itemRenderer, this.field_146289_q, InventoryUtils.cycleItemStack(recipe.func_77571_b()).func_82840_a(this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x), mx, my, 11);
/*      */     }
/*      */     
/*      */ 
/*  927 */     if ((recipe != null) && (((recipe instanceof ShapedRecipes)) || ((recipe instanceof ShapedOreRecipe)))) {
/*  928 */       String text = StatCollector.func_74838_a("recipe.type.workbench");
/*  929 */       int offset = this.field_146289_q.func_78256_a(text);
/*  930 */       this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y, 5263440);
/*      */       
/*  932 */       int rw = 0;
/*  933 */       int rh = 0;
/*  934 */       Object[] items = null;
/*  935 */       if ((recipe instanceof ShapedRecipes)) {
/*  936 */         rw = ((ShapedRecipes)recipe).field_77576_b;
/*  937 */         rh = ((ShapedRecipes)recipe).field_77577_c;
/*  938 */         items = ((ShapedRecipes)recipe).field_77574_d;
/*      */       } else {
/*  940 */         rw = ((Integer)ObfuscationReflectionHelper.getPrivateValue(ShapedOreRecipe.class, (ShapedOreRecipe)recipe, new String[] { "width" })).intValue();
/*  941 */         rh = ((Integer)ObfuscationReflectionHelper.getPrivateValue(ShapedOreRecipe.class, (ShapedOreRecipe)recipe, new String[] { "height" })).intValue();
/*  942 */         items = ((ShapedOreRecipe)recipe).getInput();
/*      */       }
/*      */       
/*  945 */       for (int i = 0; (i < rw) && (i < 3); i++) {
/*  946 */         for (int j = 0; (j < rh) && (j < 3); j++) {
/*  947 */           if (items[(i + j * rw)] != null)
/*      */           {
/*  949 */             GL11.glPushMatrix();
/*  950 */             GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  951 */             RenderHelper.func_74520_c();
/*  952 */             GL11.glEnable(2884);
/*  953 */             GL11.glTranslated(0.0D, 0.0D, 100.0D);
/*  954 */             itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(items[(i + j * rw)]), x + start + 16 + i * 32, y + 76 + j * 32);
/*      */             
/*      */ 
/*      */ 
/*  958 */             itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(items[(i + j * rw)]).func_77946_l().func_77979_a(1), x + start + 16 + i * 32, y + 76 + j * 32);
/*      */             
/*      */ 
/*      */ 
/*  962 */             RenderHelper.func_74518_a();
/*  963 */             GL11.glEnable(2896);
/*  964 */             GL11.glPopMatrix();
/*      */           }
/*      */         }
/*      */       }
/*  968 */       for (int i = 0; (i < rw) && (i < 3); i++) {
/*  969 */         for (int j = 0; (j < rh) && (j < 3); j++) {
/*  970 */           if (items[(i + j * rw)] != null)
/*      */           {
/*  972 */             if ((mposx >= x + 16 + start + i * 32) && (mposy >= y + 76 + j * 32) && (mposx < x + 16 + start + i * 32 + 16) && (mposy < y + 76 + j * 32 + 16))
/*      */             {
/*  974 */               List addtext = InventoryUtils.cycleItemStack(items[(i + j * rw)]).func_82840_a(this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);
/*  975 */               Object[] ref = findRecipeReference(InventoryUtils.cycleItemStack(items[(i + j * rw)]));
/*  976 */               if ((ref != null) && (!((String)ref[0]).equals(this.research.key))) {
/*  977 */                 addtext.add("§8§o" + StatCollector.func_74838_a("recipe.clickthrough"));
/*  978 */                 this.reference.add(Arrays.asList(new Serializable[] { Integer.valueOf(mx), Integer.valueOf(my), (String)ref[0], (Integer)ref[1] }));
/*      */               }
/*  980 */               drawCustomTooltip(this, itemRenderer, this.field_146289_q, addtext, mx, my, 11);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  987 */     if ((recipe != null) && (((recipe instanceof ShapelessRecipes)) || ((recipe instanceof ShapelessOreRecipe))))
/*      */     {
/*  989 */       String text = StatCollector.func_74838_a("recipe.type.workbenchshapeless");
/*  990 */       int offset = this.field_146289_q.func_78256_a(text);
/*  991 */       this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y, 5263440);
/*      */       
/*  993 */       List<Object> items = null;
/*  994 */       if ((recipe instanceof ShapelessRecipes)) {
/*  995 */         items = ((ShapelessRecipes)recipe).field_77579_b;
/*      */       } else {
/*  997 */         items = ((ShapelessOreRecipe)recipe).getInput();
/*      */       }
/*  999 */       for (int i = 0; (i < items.size()) && (i < 9); i++) {
/* 1000 */         if (items.get(i) != null)
/*      */         {
/* 1002 */           GL11.glPushMatrix();
/* 1003 */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1004 */           RenderHelper.func_74520_c();
/* 1005 */           GL11.glEnable(2884);
/* 1006 */           GL11.glTranslated(0.0D, 0.0D, 100.0D);
/* 1007 */           itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(items.get(i)), x + start + 16 + i % 3 * 32, y + 76 + i / 3 * 32);
/*      */           
/*      */ 
/*      */ 
/* 1011 */           itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(items.get(i)).func_77946_l().func_77979_a(1), x + start + 16 + i % 3 * 32, y + 76 + i / 3 * 32);
/*      */           
/*      */ 
/*      */ 
/* 1015 */           RenderHelper.func_74518_a();
/* 1016 */           GL11.glEnable(2896);
/* 1017 */           GL11.glPopMatrix();
/*      */         }
/*      */       }
/* 1020 */       for (int i = 0; (i < items.size()) && (i < 9); i++) {
/* 1021 */         if (items.get(i) != null)
/*      */         {
/* 1023 */           if ((mposx >= x + 16 + start + i % 3 * 32) && (mposy >= y + 76 + i / 3 * 32) && (mposx < x + 16 + start + i % 3 * 32 + 16) && (mposy < y + 76 + i / 3 * 32 + 16))
/*      */           {
/* 1025 */             List addtext = InventoryUtils.cycleItemStack(items.get(i)).func_82840_a(this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);
/* 1026 */             Object[] ref = findRecipeReference(InventoryUtils.cycleItemStack(items.get(i)));
/* 1027 */             if ((ref != null) && (!((String)ref[0]).equals(this.research.key))) {
/* 1028 */               addtext.add("§8§o" + StatCollector.func_74838_a("recipe.clickthrough"));
/* 1029 */               this.reference.add(Arrays.asList(new Serializable[] { Integer.valueOf(mx), Integer.valueOf(my), (String)ref[0], (Integer)ref[1] }));
/*      */             }
/* 1031 */             drawCustomTooltip(this, itemRenderer, this.field_146289_q, addtext, mx, my, 11);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 1036 */     GL11.glPopMatrix();
/*      */   }
/*      */   
/*      */   private void drawCruciblePage(int side, int x, int y, int mx, int my, ResearchPage pageParm)
/*      */   {
/* 1041 */     CrucibleRecipe rc = null;
/* 1042 */     Object tr = null;
/* 1043 */     if ((pageParm.recipe instanceof Object[])) {
/*      */       try {
/* 1045 */         tr = ((Object[])(Object[])pageParm.recipe)[this.cycle];
/*      */       } catch (Exception e) {
/* 1047 */         this.cycle = 0;
/* 1048 */         tr = ((Object[])(Object[])pageParm.recipe)[this.cycle];
/*      */       }
/*      */     } else {
/* 1051 */       tr = pageParm.recipe;
/*      */     }
/*      */     
/* 1054 */     if ((tr instanceof CrucibleRecipe)) {
/* 1055 */       rc = (CrucibleRecipe)tr;
/*      */     }
/*      */     
/* 1058 */     if (rc != null) {
/* 1059 */       GL11.glPushMatrix();
/* 1060 */       int start = side * 152;
/*      */       
/* 1062 */       String text = StatCollector.func_74838_a("recipe.type.crucible");
/* 1063 */       int offset = this.field_146289_q.func_78256_a(text);
/* 1064 */       this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y, 5263440);
/*      */       
/* 1066 */       UtilsFX.bindTexture(this.tex2);
/*      */       
/*      */ 
/* 1069 */       GL11.glPushMatrix();
/* 1070 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1071 */       GL11.glEnable(3042);
/* 1072 */       GL11.glTranslatef(x + start, y + 28, 0.0F);
/* 1073 */       GL11.glScalef(2.0F, 2.0F, 1.0F);
/* 1074 */       func_73729_b(0, 0, 0, 3, 56, 17);
/* 1075 */       GL11.glTranslatef(0.0F, 32.0F, 0.0F);
/* 1076 */       func_73729_b(0, 0, 0, 20, 56, 48);
/* 1077 */       GL11.glTranslatef(21.0F, -8.0F, 0.0F);
/* 1078 */       func_73729_b(0, 0, 100, 84, 11, 13);
/* 1079 */       GL11.glPopMatrix();
/*      */       
/* 1081 */       int mposx = mx;
/* 1082 */       int mposy = my;
/*      */       
/* 1084 */       int total = 0;
/* 1085 */       int rows = (rc.aspects.size() - 1) / 3;
/* 1086 */       int shift = (3 - rc.aspects.size() % 3) * 10;
/* 1087 */       int sx = x + start + 28;
/* 1088 */       int sy = y + 96 + 32 - 10 * rows;
/* 1089 */       for (Aspect tag : rc.aspects.getAspectsSorted()) {
/* 1090 */         int m = 0;
/* 1091 */         if ((total / 3 >= rows) && ((rows > 1) || (rc.aspects.size() < 3))) m = 1;
/* 1092 */         int vx = sx + total % 3 * 20 + shift * m;
/* 1093 */         int vy = sy + total / 3 * 20;
/* 1094 */         UtilsFX.drawTag(vx, vy, tag, rc.aspects.getAmount(tag), 0, this.field_73735_i);
/*      */         
/* 1096 */         total++;
/*      */       }
/*      */       
/* 1099 */       GL11.glPushMatrix();
/* 1100 */       GL11.glTranslated(0.0D, 0.0D, 100.0D);
/* 1101 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1102 */       RenderHelper.func_74520_c();
/* 1103 */       GL11.glEnable(2884);
/* 1104 */       itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, rc.getRecipeOutput(), x + 48 + start, y + 36);
/*      */       
/*      */ 
/*      */ 
/* 1108 */       itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, rc.getRecipeOutput(), x + 48 + start, y + 36);
/*      */       
/*      */ 
/*      */ 
/* 1112 */       RenderHelper.func_74518_a();
/* 1113 */       GL11.glEnable(2896);
/* 1114 */       GL11.glPopMatrix();
/*      */       
/* 1116 */       GL11.glPushMatrix();
/* 1117 */       GL11.glTranslated(0.0D, 0.0D, 100.0D);
/* 1118 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1119 */       RenderHelper.func_74520_c();
/* 1120 */       GL11.glEnable(2884);
/* 1121 */       itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(rc.catalyst), x + 26 + start, y + 72);
/*      */       
/*      */ 
/*      */ 
/* 1125 */       itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(rc.catalyst).func_77946_l().func_77979_a(1), x + 26 + start, y + 72);
/*      */       
/*      */ 
/*      */ 
/* 1129 */       RenderHelper.func_74518_a();
/* 1130 */       GL11.glEnable(2896);
/* 1131 */       GL11.glPopMatrix();
/*      */       
/* 1133 */       if ((mposx >= x + 48 + start) && (mposy >= y + 36) && (mposx < x + 48 + start + 16) && (mposy < y + 36 + 16))
/*      */       {
/* 1135 */         drawCustomTooltip(this, itemRenderer, this.field_146289_q, rc.getRecipeOutput().func_82840_a(this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x), mx, my, 11);
/*      */       }
/*      */       
/*      */ 
/* 1139 */       if ((mposx >= x + 26 + start) && (mposy >= y + 72) && (mposx < x + 26 + start + 16) && (mposy < y + 72 + 16))
/*      */       {
/*      */ 
/* 1142 */         List addtext = InventoryUtils.cycleItemStack(rc.catalyst).func_82840_a(this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);
/* 1143 */         Object[] ref = findRecipeReference(InventoryUtils.cycleItemStack(rc.catalyst));
/* 1144 */         if ((ref != null) && (!((String)ref[0]).equals(this.research.key))) {
/* 1145 */           addtext.add("§8§o" + StatCollector.func_74838_a("recipe.clickthrough"));
/* 1146 */           this.reference.add(Arrays.asList(new Serializable[] { Integer.valueOf(mx), Integer.valueOf(my), (String)ref[0], (Integer)ref[1] }));
/*      */         }
/* 1148 */         drawCustomTooltip(this, itemRenderer, this.field_146289_q, addtext, mx, my, 11);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1154 */       total = 0;
/* 1155 */       for (Aspect tag : rc.aspects.getAspectsSorted()) {
/* 1156 */         int m = 0;
/* 1157 */         if ((total / 3 >= rows) && ((rows > 1) || (rc.aspects.size() < 3))) m = 1;
/* 1158 */         int vx = sx + total % 3 * 20 + shift * m;
/* 1159 */         int vy = sy + total / 3 * 20;
/*      */         
/* 1161 */         if ((mposx >= vx) && (mposy >= vy) && (mposx < vx + 16) && (mposy < vy + 16)) {
/* 1162 */           drawCustomTooltip(this, itemRenderer, this.field_146289_q, Arrays.asList(new String[] { tag.getName(), tag.getLocalizedDescription() }), mx, my, 11);
/*      */         }
/*      */         
/*      */ 
/* 1166 */         total++;
/*      */       }
/* 1168 */       GL11.glPopMatrix();
/*      */     }
/*      */   }
/*      */   
/*      */   class Coord2D {
/*      */     int x;
/*      */     int y;
/*      */     
/* 1176 */     Coord2D(int x, int y) { this.x = x;
/* 1177 */       this.y = y;
/*      */     }
/*      */   }
/*      */   
/*      */   private void drawSmeltingPage(int side, int x, int y, int mx, int my, ResearchPage pageParm)
/*      */   {
/* 1183 */     ItemStack in = (ItemStack)pageParm.recipe;
/* 1184 */     ItemStack out = null;
/* 1185 */     if (in != null) {
/* 1186 */       out = FurnaceRecipes.func_77602_a().func_151395_a(in);
/*      */     }
/* 1188 */     if ((in != null) && (out != null)) {
/* 1189 */       GL11.glPushMatrix();
/* 1190 */       int start = side * 152;
/*      */       
/* 1192 */       String text = StatCollector.func_74838_a("recipe.type.smelting");
/* 1193 */       int offset = this.field_146289_q.func_78256_a(text);
/* 1194 */       this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y, 5263440);
/*      */       
/* 1196 */       UtilsFX.bindTexture(this.tex2);
/*      */       
/*      */ 
/* 1199 */       GL11.glPushMatrix();
/* 1200 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1201 */       GL11.glEnable(3042);
/* 1202 */       GL11.glTranslatef(x + start, y + 28, 0.0F);
/* 1203 */       GL11.glScalef(2.0F, 2.0F, 1.0F);
/* 1204 */       func_73729_b(0, 0, 0, 192, 56, 64);
/* 1205 */       GL11.glPopMatrix();
/*      */       
/* 1207 */       int mposx = mx;
/* 1208 */       int mposy = my;
/*      */       
/* 1210 */       GL11.glPushMatrix();
/* 1211 */       GL11.glTranslated(0.0D, 0.0D, 100.0D);
/* 1212 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1213 */       RenderHelper.func_74520_c();
/* 1214 */       GL11.glEnable(2884);
/* 1215 */       itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, in, x + 48 + start, y + 64);
/*      */       
/*      */ 
/*      */ 
/* 1219 */       itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, in, x + 48 + start, y + 64);
/*      */       
/*      */ 
/*      */ 
/* 1223 */       RenderHelper.func_74518_a();
/* 1224 */       GL11.glEnable(2896);
/* 1225 */       GL11.glPopMatrix();
/*      */       
/* 1227 */       GL11.glPushMatrix();
/* 1228 */       GL11.glTranslated(0.0D, 0.0D, 100.0D);
/* 1229 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1230 */       RenderHelper.func_74520_c();
/* 1231 */       GL11.glEnable(2884);
/* 1232 */       itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, out, x + 48 + start, y + 144);
/*      */       
/*      */ 
/*      */ 
/* 1236 */       itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, out, x + 48 + start, y + 144);
/*      */       
/*      */ 
/*      */ 
/* 1240 */       RenderHelper.func_74518_a();
/* 1241 */       GL11.glEnable(2896);
/* 1242 */       GL11.glPopMatrix();
/*      */       
/* 1244 */       if ((mposx >= x + 48 + start) && (mposy >= y + 64) && (mposx < x + 48 + start + 16) && (mposy < y + 64 + 16))
/*      */       {
/* 1246 */         List addtext = in.func_82840_a(this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);
/* 1247 */         Object[] ref = findRecipeReference(in);
/* 1248 */         if ((ref != null) && (!((String)ref[0]).equals(this.research.key))) {
/* 1249 */           addtext.add("§8§o" + StatCollector.func_74838_a("recipe.clickthrough"));
/* 1250 */           this.reference.add(Arrays.asList(new Serializable[] { Integer.valueOf(mx), Integer.valueOf(my), (String)ref[0], (Integer)ref[1] }));
/*      */         }
/* 1252 */         drawCustomTooltip(this, itemRenderer, this.field_146289_q, addtext, mx, my, 11);
/*      */       }
/*      */       
/* 1255 */       if ((mposx >= x + 48 + start) && (mposy >= y + 144) && (mposx < x + 48 + start + 16) && (mposy < y + 144 + 16))
/*      */       {
/* 1257 */         drawCustomTooltip(this, itemRenderer, this.field_146289_q, out.func_82840_a(this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x), mx, my, 11);
/*      */       }
/*      */       
/*      */ 
/* 1261 */       GL11.glPopMatrix();
/*      */     }
/*      */   }
/*      */   
/*      */   private void drawInfusionPage(int side, int x, int y, int mx, int my, ResearchPage pageParm)
/*      */   {
/* 1267 */     Object tr = null;
/* 1268 */     if ((pageParm.recipe instanceof Object[])) {
/*      */       try {
/* 1270 */         tr = ((Object[])(Object[])pageParm.recipe)[this.cycle];
/*      */       } catch (Exception e) {
/* 1272 */         this.cycle = 0;
/* 1273 */         tr = ((Object[])(Object[])pageParm.recipe)[this.cycle];
/*      */       }
/*      */     } else {
/* 1276 */       tr = pageParm.recipe;
/*      */     }
/*      */     
/* 1279 */     InfusionRecipe ri = (InfusionRecipe)tr;
/*      */     
/* 1281 */     if (ri != null) {
/* 1282 */       GL11.glPushMatrix();
/* 1283 */       int start = side * 152;
/*      */       
/* 1285 */       String text = StatCollector.func_74838_a("recipe.type.infusion");
/* 1286 */       int offset = this.field_146289_q.func_78256_a(text);
/* 1287 */       this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y, 5263440);
/*      */       
/* 1289 */       int inst = Math.min(5, ri.getInstability() / 2);
/* 1290 */       text = StatCollector.func_74838_a("tc.inst") + " " + StatCollector.func_74838_a(new StringBuilder().append("tc.inst.").append(inst).toString());
/*      */       
/* 1292 */       offset = this.field_146289_q.func_78256_a(text);
/* 1293 */       this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y + 194, 5263440);
/*      */       
/* 1295 */       UtilsFX.bindTexture(this.tex2);
/*      */       
/*      */ 
/* 1298 */       GL11.glPushMatrix();
/* 1299 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1300 */       GL11.glEnable(3042);
/* 1301 */       GL11.glTranslatef(x + start, y + 20, 0.0F);
/* 1302 */       GL11.glScalef(2.0F, 2.0F, 1.0F);
/* 1303 */       func_73729_b(0, 0, 0, 3, 56, 17);
/* 1304 */       GL11.glTranslatef(0.0F, 19.0F, 0.0F);
/* 1305 */       func_73729_b(0, 0, 200, 77, 60, 44);
/* 1306 */       GL11.glPopMatrix();
/*      */       
/* 1308 */       int mposx = mx;
/* 1309 */       int mposy = my;
/*      */       
/* 1311 */       int total = 0;
/* 1312 */       int rows = (ri.getAspects().size() - 1) / 5;
/* 1313 */       int shift = (5 - ri.getAspects().size() % 5) * 10;
/* 1314 */       int sx = x + start + 8;
/* 1315 */       int sy = y + 164 - 10 * rows;
/* 1316 */       for (Aspect tag : ri.getAspects().getAspectsSorted()) {
/* 1317 */         int m = 0;
/* 1318 */         if ((total / 5 >= rows) && ((rows > 1) || (ri.getAspects().size() < 5))) m = 1;
/* 1319 */         int vx = sx + total % 5 * 20 + shift * m;
/* 1320 */         int vy = sy + total / 5 * 20;
/* 1321 */         UtilsFX.drawTag(vx, vy, tag, ri.getAspects().getAmount(tag), 0, this.field_73735_i);
/*      */         
/* 1323 */         total++;
/*      */       }
/*      */       
/* 1326 */       ItemStack idisp = null;
/* 1327 */       if ((ri.getRecipeOutput() instanceof ItemStack)) {
/* 1328 */         idisp = InventoryUtils.cycleItemStack((ItemStack)ri.getRecipeOutput());
/*      */       } else {
/* 1330 */         idisp = InventoryUtils.cycleItemStack(ri.getRecipeInput()).func_77946_l();
/* 1331 */         Object[] obj = (Object[])ri.getRecipeOutput();
/* 1332 */         NBTBase tag = (NBTBase)obj[1];
/* 1333 */         idisp.func_77983_a((String)obj[0], tag);
/*      */       }
/*      */       
/* 1336 */       GL11.glPushMatrix();
/* 1337 */       GL11.glTranslated(0.0D, 0.0D, 100.0D);
/* 1338 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1339 */       RenderHelper.func_74520_c();
/* 1340 */       GL11.glEnable(2884);
/* 1341 */       itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, idisp, x + 48 + start, y + 28);
/*      */       
/*      */ 
/*      */ 
/* 1345 */       itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, idisp, x + 48 + start, y + 28);
/*      */       
/*      */ 
/*      */ 
/* 1349 */       RenderHelper.func_74518_a();
/* 1350 */       GL11.glEnable(2896);
/* 1351 */       GL11.glPopMatrix();
/*      */       
/*      */ 
/* 1354 */       GL11.glPushMatrix();
/* 1355 */       GL11.glTranslated(0.0D, 0.0D, 100.0D);
/* 1356 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1357 */       RenderHelper.func_74520_c();
/* 1358 */       GL11.glEnable(2884);
/* 1359 */       itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(ri.getRecipeInput()), x + 48 + start, y + 94);
/*      */       
/*      */ 
/*      */ 
/* 1363 */       itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(ri.getRecipeInput()).func_77946_l().func_77979_a(1), x + 48 + start, y + 94);
/*      */       
/*      */ 
/*      */ 
/* 1367 */       RenderHelper.func_74518_a();
/* 1368 */       GL11.glEnable(2896);
/* 1369 */       GL11.glPopMatrix();
/*      */       
/*      */ 
/* 1372 */       GL11.glPushMatrix();
/* 1373 */       GL11.glTranslated(0.0D, 0.0D, 100.0D);
/* 1374 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1375 */       RenderHelper.func_74520_c();
/* 1376 */       GL11.glDisable(2896);
/* 1377 */       GL11.glEnable(2884);
/*      */       
/* 1379 */       int le = ri.getComponents().length;
/* 1380 */       ArrayList<Coord2D> coords = new ArrayList();
/* 1381 */       float pieSlice = 360 / le;
/* 1382 */       float currentRot = -90.0F;
/* 1383 */       for (int a = 0; a < le; a++) {
/* 1384 */         int xx = (int)(MathHelper.func_76134_b(currentRot / 180.0F * 3.1415927F) * 40.0F) - 8;
/* 1385 */         int yy = (int)(MathHelper.func_76126_a(currentRot / 180.0F * 3.1415927F) * 40.0F) - 8;
/* 1386 */         currentRot += pieSlice;
/* 1387 */         coords.add(new Coord2D(xx, yy));
/*      */       }
/*      */       
/*      */ 
/* 1391 */       total = 0;
/* 1392 */       sx = x + 56 + start;
/* 1393 */       sy = y + 102;
/*      */       
/* 1395 */       for (ItemStack ingredient : ri.getComponents()) {
/* 1396 */         RenderHelper.func_74520_c();
/* 1397 */         int vx = sx + ((Coord2D)coords.get(total)).x;
/* 1398 */         int vy = sy + ((Coord2D)coords.get(total)).y;
/*      */         
/* 1400 */         itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(ingredient), vx, vy);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 1405 */         itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(ingredient).func_77946_l().func_77979_a(1), vx, vy);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 1410 */         RenderHelper.func_74518_a();
/* 1411 */         total++;
/*      */       }
/*      */       
/* 1414 */       GL11.glEnable(2896);
/* 1415 */       GL11.glPopMatrix();
/*      */       
/* 1417 */       if ((mposx >= x + 48 + start) && (mposy >= y + 28) && (mposx < x + 48 + start + 16) && (mposy < y + 28 + 16))
/*      */       {
/* 1419 */         drawCustomTooltip(this, itemRenderer, this.field_146289_q, idisp.func_82840_a(this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x), mx, my, 11);
/*      */       }
/*      */       
/*      */ 
/* 1423 */       if ((mposx >= x + 48 + start) && (mposy >= y + 94) && (mposx < x + 48 + start + 16) && (mposy < y + 94 + 16))
/*      */       {
/*      */ 
/* 1426 */         List addtext = InventoryUtils.cycleItemStack(ri.getRecipeInput()).func_82840_a(this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);
/* 1427 */         Object[] ref = findRecipeReference(InventoryUtils.cycleItemStack(ri.getRecipeInput()));
/* 1428 */         if ((ref != null) && (!((String)ref[0]).equals(this.research.key))) {
/* 1429 */           addtext.add("§8§o" + StatCollector.func_74838_a("recipe.clickthrough"));
/* 1430 */           this.reference.add(Arrays.asList(new Serializable[] { Integer.valueOf(mx), Integer.valueOf(my), (String)ref[0], (Integer)ref[1] }));
/*      */         }
/*      */         
/* 1433 */         drawCustomTooltip(this, itemRenderer, this.field_146289_q, addtext, mx, my, 11);
/*      */       }
/*      */       
/* 1436 */       total = 0;
/* 1437 */       sx = x + 56 + start;
/* 1438 */       sy = y + 102;
/* 1439 */       for (ItemStack ingredient : ri.getComponents()) {
/* 1440 */         int vx = sx + ((Coord2D)coords.get(total)).x;
/* 1441 */         int vy = sy + ((Coord2D)coords.get(total)).y;
/* 1442 */         if ((mposx >= vx) && (mposy >= vy) && (mposx < vx + 16) && (mposy < vy + 16)) {
/* 1443 */           List addtext = InventoryUtils.cycleItemStack(ingredient).func_82840_a(this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);
/*      */           
/* 1445 */           Object[] ref = findRecipeReference(InventoryUtils.cycleItemStack(ingredient));
/* 1446 */           if ((ref != null) && (!((String)ref[0]).equals(this.research.key))) {
/* 1447 */             addtext.add("§8§o" + StatCollector.func_74838_a("recipe.clickthrough"));
/* 1448 */             this.reference.add(Arrays.asList(new Serializable[] { Integer.valueOf(mx), Integer.valueOf(my), (String)ref[0], (Integer)ref[1] }));
/*      */           }
/*      */           
/* 1451 */           drawCustomTooltip(this, itemRenderer, this.field_146289_q, addtext, mx, my, 11);
/*      */         }
/* 1453 */         total++;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1459 */       total = 0;
/* 1460 */       rows = (ri.getAspects().size() - 1) / 5;
/* 1461 */       shift = (5 - ri.getAspects().size() % 5) * 10;
/* 1462 */       sx = x + start + 8;
/* 1463 */       sy = y + 164 - 10 * rows;
/* 1464 */       for (Aspect tag : ri.getAspects().getAspectsSorted()) {
/* 1465 */         int m = 0;
/* 1466 */         if ((total / 5 >= rows) && ((rows > 1) || (ri.getAspects().size() < 5))) m = 1;
/* 1467 */         int vx = sx + total % 5 * 20 + shift * m;
/* 1468 */         int vy = sy + total / 5 * 20;
/*      */         
/* 1470 */         if ((mposx >= vx) && (mposy >= vy) && (mposx < vx + 16) && (mposy < vy + 16)) {
/* 1471 */           drawCustomTooltip(this, itemRenderer, this.field_146289_q, Arrays.asList(new String[] { tag.getName(), tag.getLocalizedDescription() }), mx, my, 11);
/*      */         }
/*      */         
/*      */ 
/* 1475 */         total++;
/*      */       }
/* 1477 */       GL11.glPopMatrix();
/*      */     }
/*      */   }
/*      */   
/*      */   private void drawInfusionEnchantingPage(int side, int x, int y, int mx, int my, ResearchPage pageParm)
/*      */   {
/* 1483 */     Object tr = pageParm.recipe;
/*      */     
/* 1485 */     InfusionEnchantmentRecipe ri = (InfusionEnchantmentRecipe)tr;
/*      */     
/* 1487 */     if (ri != null) {
/* 1488 */       GL11.glPushMatrix();
/* 1489 */       int start = side * 152;
/* 1490 */       int level = (int)(1L + System.currentTimeMillis() / 1000L % ri.enchantment.func_77325_b());
/*      */       
/* 1492 */       String text = StatCollector.func_74838_a("recipe.type.infusionenchantment");
/* 1493 */       int offset = this.field_146289_q.func_78256_a(text);
/* 1494 */       this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y, 5263440);
/*      */       
/* 1496 */       int inst = Math.min(5, ri.instability / 2);
/* 1497 */       text = StatCollector.func_74838_a("tc.inst") + " " + StatCollector.func_74838_a(new StringBuilder().append("tc.inst.").append(inst).toString());
/*      */       
/* 1499 */       offset = this.field_146289_q.func_78256_a(text);
/* 1500 */       this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y + 194, 5263440);
/*      */       
/* 1502 */       text = ri.enchantment.func_77316_c(level);
/* 1503 */       offset = this.field_146289_q.func_78256_a(text);
/* 1504 */       this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y + 24, 7360656);
/*      */       
/* 1506 */       int xp = ri.recipeXP * level;
/* 1507 */       text = xp + " levels";
/* 1508 */       offset = this.field_146289_q.func_78256_a(text);
/* 1509 */       this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y + 40, 5277776);
/*      */       
/* 1511 */       UtilsFX.bindTexture(this.tex2);
/*      */       
/* 1513 */       GL11.glPushMatrix();
/* 1514 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1515 */       GL11.glEnable(3042);
/* 1516 */       GL11.glTranslatef(x + start, y + 20, 0.0F);
/* 1517 */       GL11.glScalef(2.0F, 2.0F, 1.0F);
/*      */       
/* 1519 */       GL11.glTranslatef(0.0F, 19.0F, 0.0F);
/* 1520 */       func_73729_b(0, 0, 200, 77, 60, 44);
/* 1521 */       GL11.glPopMatrix();
/*      */       
/* 1523 */       int mposx = mx;
/* 1524 */       int mposy = my;
/*      */       
/* 1526 */       int total = 0;
/* 1527 */       int rows = (ri.aspects.size() - 1) / 5;
/* 1528 */       int shift = (5 - ri.aspects.size() % 5) * 10;
/* 1529 */       int sx = x + start + 8;
/* 1530 */       int sy = y + 164 - 10 * rows;
/* 1531 */       for (Aspect tag : ri.aspects.getAspectsSorted()) {
/* 1532 */         int m = 0;
/* 1533 */         if ((total / 5 >= rows) && ((rows > 1) || (ri.aspects.size() < 5))) m = 1;
/* 1534 */         int vx = sx + total % 5 * 20 + shift * m;
/* 1535 */         int vy = sy + total / 5 * 20;
/* 1536 */         UtilsFX.drawTag(vx, vy, tag, ri.aspects.getAmount(tag) * level, 0, this.field_73735_i);
/* 1537 */         total++;
/*      */       }
/*      */       
/* 1540 */       GL11.glPushMatrix();
/* 1541 */       GL11.glTranslated(0.0D, 0.0D, 100.0D);
/* 1542 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1543 */       RenderHelper.func_74520_c();
/* 1544 */       GL11.glDisable(2896);
/* 1545 */       GL11.glEnable(2884);
/*      */       
/* 1547 */       int le = ri.components.length;
/* 1548 */       ArrayList<Coord2D> coords = new ArrayList();
/* 1549 */       float pieSlice = 360 / le;
/* 1550 */       float currentRot = -90.0F;
/* 1551 */       for (int a = 0; a < le; a++) {
/* 1552 */         int xx = (int)(MathHelper.func_76134_b(currentRot / 180.0F * 3.1415927F) * 40.0F) - 8;
/* 1553 */         int yy = (int)(MathHelper.func_76126_a(currentRot / 180.0F * 3.1415927F) * 40.0F) - 8;
/* 1554 */         currentRot += pieSlice;
/* 1555 */         coords.add(new Coord2D(xx, yy));
/*      */       }
/*      */       
/*      */ 
/* 1559 */       total = 0;
/* 1560 */       sx = x + 56 + start;
/* 1561 */       sy = y + 102;
/*      */       
/* 1563 */       for (ItemStack ingredient : ri.components) {
/* 1564 */         RenderHelper.func_74520_c();
/* 1565 */         int vx = sx + ((Coord2D)coords.get(total)).x;
/* 1566 */         int vy = sy + ((Coord2D)coords.get(total)).y;
/*      */         
/* 1568 */         itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(ingredient), vx, vy);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 1573 */         itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(ingredient).func_77946_l().func_77979_a(1), vx, vy);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 1578 */         total++;
/* 1579 */         RenderHelper.func_74518_a();
/*      */       }
/*      */       
/* 1582 */       GL11.glEnable(2896);
/* 1583 */       GL11.glPopMatrix();
/*      */       
/*      */ 
/* 1586 */       total = 0;
/* 1587 */       sx = x + 56 + start;
/* 1588 */       sy = y + 102;
/* 1589 */       for (ItemStack ingredient : ri.components) {
/* 1590 */         int vx = sx + ((Coord2D)coords.get(total)).x;
/* 1591 */         int vy = sy + ((Coord2D)coords.get(total)).y;
/* 1592 */         if ((mposx >= vx) && (mposy >= vy) && (mposx < vx + 16) && (mposy < vy + 16)) {
/* 1593 */           List addtext = InventoryUtils.cycleItemStack(ingredient).func_82840_a(this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);
/*      */           
/* 1595 */           Object[] ref = findRecipeReference(InventoryUtils.cycleItemStack(ingredient));
/* 1596 */           if ((ref != null) && (!((String)ref[0]).equals(this.research.key))) {
/* 1597 */             addtext.add("§8§o" + StatCollector.func_74838_a("recipe.clickthrough"));
/* 1598 */             this.reference.add(Arrays.asList(new Serializable[] { Integer.valueOf(mx), Integer.valueOf(my), (String)ref[0], (Integer)ref[1] }));
/*      */           }
/*      */           
/* 1601 */           drawCustomTooltip(this, itemRenderer, this.field_146289_q, addtext, mx, my, 11);
/*      */         }
/* 1603 */         total++;
/*      */       }
/*      */       
/*      */ 
/* 1607 */       total = 0;
/* 1608 */       rows = (ri.aspects.size() - 1) / 5;
/* 1609 */       shift = (5 - ri.aspects.size() % 5) * 10;
/* 1610 */       sx = x + start + 8;
/* 1611 */       sy = y + 164 - 10 * rows;
/* 1612 */       for (Aspect tag : ri.aspects.getAspectsSorted()) {
/* 1613 */         int m = 0;
/* 1614 */         if ((total / 5 >= rows) && ((rows > 1) || (ri.aspects.size() < 5))) m = 1;
/* 1615 */         int vx = sx + total % 5 * 20 + shift * m;
/* 1616 */         int vy = sy + total / 5 * 20;
/*      */         
/* 1618 */         if ((mposx >= vx) && (mposy >= vy) && (mposx < vx + 16) && (mposy < vy + 16)) {
/* 1619 */           drawCustomTooltip(this, itemRenderer, this.field_146289_q, Arrays.asList(new String[] { tag.getName(), tag.getLocalizedDescription() }), mx, my, 11);
/*      */         }
/*      */         
/*      */ 
/* 1623 */         total++;
/*      */       }
/* 1625 */       GL11.glPopMatrix();
/*      */     }
/*      */   }
/*      */   
/*      */   private void drawTextPage(int side, int x, int y, String text) {
/* 1630 */     GL11.glPushMatrix();
/* 1631 */     RenderHelper.func_74520_c();
/*      */     
/* 1633 */     GL11.glEnable(3042);
/* 1634 */     this.fr.drawSplitString(text, x - 15 + side * 152, y, 139, 0, this);
/* 1635 */     GL11.glPopMatrix();
/*      */   }
/*      */   
/*      */ 
/* 1639 */   ArrayList<List> reference = new ArrayList();
/*      */   
/*      */ 
/*      */   protected void func_73864_a(int par1, int par2, int par3)
/*      */   {
/* 1644 */     int var4 = (this.field_146294_l - this.paneWidth) / 2;
/* 1645 */     int var5 = (this.field_146295_m - this.paneHeight) / 2;
/*      */     
/*      */ 
/* 1648 */     int mx = par1 - (var4 + 261);
/* 1649 */     int my = par2 - (var5 + 189);
/*      */     
/* 1651 */     if ((this.page < this.maxPages - 2) && (mx >= 0) && (my >= 0) && (mx < 14) && (my < 10)) {
/* 1652 */       this.page += 2;
/* 1653 */       this.lastCycle = 0L;
/* 1654 */       this.cycle = -1;
/* 1655 */       Minecraft.func_71410_x().field_71441_e.func_72980_b(Minecraft.func_71410_x().field_71439_g.field_70165_t, Minecraft.func_71410_x().field_71439_g.field_70163_u, Minecraft.func_71410_x().field_71439_g.field_70161_v, "thaumcraft:page", 0.66F, 1.0F, false);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1661 */     mx = par1 - (var4 - 17);
/* 1662 */     my = par2 - (var5 + 189);
/*      */     
/* 1664 */     if ((this.page >= 2) && (mx >= 0) && (my >= 0) && (mx < 14) && (my < 10)) {
/* 1665 */       this.page -= 2;
/* 1666 */       this.lastCycle = 0L;
/* 1667 */       this.cycle = -1;
/* 1668 */       Minecraft.func_71410_x().field_71441_e.func_72980_b(Minecraft.func_71410_x().field_71439_g.field_70165_t, Minecraft.func_71410_x().field_71439_g.field_70163_u, Minecraft.func_71410_x().field_71439_g.field_70161_v, "thaumcraft:page", 0.66F, 1.0F, false);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1674 */     if (!history.isEmpty()) {
/* 1675 */       mx = par1 - (var4 + 118);
/* 1676 */       my = par2 - (var5 + 189);
/*      */       
/* 1678 */       if ((mx >= 0) && (my >= 0) && (mx < 20) && (my < 12)) {
/* 1679 */         Minecraft.func_71410_x().field_71441_e.func_72980_b(Minecraft.func_71410_x().field_71439_g.field_70165_t, Minecraft.func_71410_x().field_71439_g.field_70163_u, Minecraft.func_71410_x().field_71439_g.field_70161_v, "thaumcraft:page", 0.66F, 1.0F, false);
/*      */         
/*      */ 
/* 1682 */         Object[] o = (Object[])history.pop();
/* 1683 */         this.field_146297_k.func_147108_a(new GuiResearchRecipe(ResearchCategories.getResearch((String)o[0]), ((Integer)o[1]).intValue(), this.guiMapX, this.guiMapY));
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1692 */     if (this.reference.size() > 0) {
/* 1693 */       for (List coords : this.reference) {
/* 1694 */         if ((par1 >= ((Integer)coords.get(0)).intValue()) && (par2 >= ((Integer)coords.get(1)).intValue()) && (par1 < ((Integer)coords.get(0)).intValue() + 16) && (par2 < ((Integer)coords.get(1)).intValue() + 16))
/*      */         {
/* 1696 */           Minecraft.func_71410_x().field_71441_e.func_72980_b(Minecraft.func_71410_x().field_71439_g.field_70165_t, Minecraft.func_71410_x().field_71439_g.field_70163_u, Minecraft.func_71410_x().field_71439_g.field_70161_v, "thaumcraft:page", 0.66F, 1.0F, false);
/*      */           
/*      */ 
/* 1699 */           history.push(new Object[] { this.research.key, Integer.valueOf(this.page) });
/* 1700 */           this.field_146297_k.func_147108_a(new GuiResearchRecipe(ResearchCategories.getResearch((String)coords.get(2)), ((Integer)coords.get(3)).intValue(), this.guiMapX, this.guiMapY));
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1709 */     super.func_73864_a(par1, par2, par3);
/*      */   }
/*      */   
/* 1712 */   private int cycle = -1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean func_73868_f()
/*      */   {
/* 1720 */     return false;
/*      */   }
/*      */   
/*      */   public Object[] findRecipeReference(ItemStack item) {
/* 1724 */     return ThaumcraftApi.getCraftingRecipeKey(this.field_146297_k.field_71439_g, item);
/*      */   }
/*      */   
/*      */   public void drawTexturedModalRectScaled(int par1, int par2, int par3, int par4, int par5, int par6, float scale)
/*      */   {
/* 1729 */     GL11.glPushMatrix();
/* 1730 */     float var7 = 0.00390625F;
/* 1731 */     float var8 = 0.00390625F;
/* 1732 */     Tessellator var9 = Tessellator.field_78398_a;
/* 1733 */     GL11.glTranslatef(par1 + par5 / 2.0F, par2 + par6 / 2.0F, 0.0F);
/* 1734 */     GL11.glScalef(1.0F + scale, 1.0F + scale, 1.0F);
/* 1735 */     var9.func_78382_b();
/* 1736 */     var9.func_78374_a(-par5 / 2.0F, par6 / 2.0F, this.field_73735_i, (par3 + 0) * var7, (par4 + par6) * var8);
/* 1737 */     var9.func_78374_a(par5 / 2.0F, par6 / 2.0F, this.field_73735_i, (par3 + par5) * var7, (par4 + par6) * var8);
/* 1738 */     var9.func_78374_a(par5 / 2.0F, -par6 / 2.0F, this.field_73735_i, (par3 + par5) * var7, (par4 + 0) * var8);
/* 1739 */     var9.func_78374_a(-par5 / 2.0F, -par6 / 2.0F, this.field_73735_i, (par3 + 0) * var7, (par4 + 0) * var8);
/* 1740 */     var9.func_78381_a();
/* 1741 */     GL11.glPopMatrix();
/*      */   }
/*      */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/gui/GuiResearchRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */