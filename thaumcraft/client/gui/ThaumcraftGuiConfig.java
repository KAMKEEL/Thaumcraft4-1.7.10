/*    */ package thaumcraft.client.gui;
/*    */ 
/*    */ import cpw.mods.fml.client.config.GuiConfig;
/*    */ import cpw.mods.fml.client.config.IConfigElement;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraftforge.common.config.ConfigElement;
/*    */ import net.minecraftforge.common.config.Configuration;
/*    */ import thaumcraft.common.config.Config;
/*    */ 
/*    */ public class ThaumcraftGuiConfig extends GuiConfig
/*    */ {
/*    */   public ThaumcraftGuiConfig(GuiScreen parent)
/*    */   {
/* 16 */     super(parent, getConfigElements(), "Thaumcraft", false, false, GuiConfig.getAbridgedConfigPath(Config.config.toString()));
/*    */   }
/*    */   
/*    */   private static List<IConfigElement> getConfigElements()
/*    */   {
/* 21 */     List<IConfigElement> list = new ArrayList();
/*    */     
/* 23 */     list.addAll(new ConfigElement(Config.config.getCategory("general")).getChildElements());
/*    */     
/*    */ 
/* 26 */     list.addAll(new ConfigElement(Config.config.getCategory("Monster_Spawning")).getChildElements());
/*    */     
/*    */ 
/* 29 */     list.addAll(new ConfigElement(Config.config.getCategory("World_Generation")).getChildElements());
/*    */     
/*    */ 
/* 32 */     list.addAll(new ConfigElement(Config.config.getCategory("World_Regeneration")).getChildElements());
/*    */     
/*    */ 
/* 35 */     list.addAll(new ConfigElement(Config.config.getCategory("Research")).getChildElements());
/*    */     
/*    */ 
/* 38 */     return list;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/gui/ThaumcraftGuiConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */