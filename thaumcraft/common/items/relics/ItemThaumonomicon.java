/*     */ package thaumcraft.common.items.relics;
/*     */ 
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.research.ResearchCategoryList;
/*     */ import thaumcraft.api.research.ResearchItem;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ 
/*     */ public class ItemThaumonomicon extends Item
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon icon;
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon iconCheat;
/*     */   
/*     */   public ItemThaumonomicon()
/*     */   {
/*  35 */     func_77627_a(true);
/*  36 */     func_77656_e(0);
/*  37 */     func_77625_d(1);
/*  38 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  48 */     this.icon = ir.func_94245_a("thaumcraft:thaumonomicon");
/*  49 */     this.iconCheat = ir.func_94245_a("thaumcraft:thaumonomiconcheat");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1) {
/*  54 */     return par1 != 42 ? this.icon : this.iconCheat;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  60 */     par3List.add(new ItemStack(this, 1, 0));
/*  61 */     if (Config.allowCheatSheet) par3List.add(new ItemStack(this, 1, 42));
/*     */   }
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack stack, World par2World, EntityPlayer player)
/*     */   {
/*  66 */     if (!par2World.field_72995_K)
/*     */     {
/*     */ 
/*  69 */       if ((Config.allowCheatSheet) && (stack.func_77960_j() == 42)) {
/*  70 */         Collection<ResearchCategoryList> rc = thaumcraft.api.research.ResearchCategories.researchCategories.values();
/*  71 */         for (ResearchCategoryList cat : rc) {
/*  72 */           Collection<ResearchItem> rl = cat.research.values();
/*  73 */           for (ResearchItem ri : rl) {
/*  74 */             if (!ResearchManager.isResearchComplete(player.func_70005_c_(), ri.key)) {
/*  75 */               Thaumcraft.proxy.getResearchManager().completeResearch(player, ri.key);
/*     */             }
/*     */           }
/*     */         }
/*  79 */         for (thaumcraft.api.aspects.Aspect aspect : thaumcraft.api.aspects.Aspect.aspects.values()) {
/*  80 */           if (!Thaumcraft.proxy.getPlayerKnowledge().hasDiscoveredAspect(player.func_70005_c_(), aspect))
/*  81 */             Thaumcraft.proxy.researchManager.completeAspect(player, aspect, (short)50);
/*     */         }
/*     */       } else {
/*  84 */         Collection<ResearchCategoryList> rc = thaumcraft.api.research.ResearchCategories.researchCategories.values();
/*  85 */         for (ResearchCategoryList cat : rc) {
/*  86 */           Collection<ResearchItem> rl = cat.research.values();
/*  87 */           for (ResearchItem ri : rl) {
/*  88 */             if ((ResearchManager.isResearchComplete(player.func_70005_c_(), ri.key)) && 
/*  89 */               (ri.siblings != null)) {
/*  90 */               for (String sib : ri.siblings) {
/*  91 */                 if (!ResearchManager.isResearchComplete(player.func_70005_c_(), sib)) {
/*  92 */                   Thaumcraft.proxy.getResearchManager().completeResearch(player, sib);
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 102 */       PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketSyncResearch(player), (net.minecraft.entity.player.EntityPlayerMP)player);
/* 103 */       PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketSyncAspects(player), (net.minecraft.entity.player.EntityPlayerMP)player);
/*     */     }
/*     */     else {
/* 106 */       par2World.func_72980_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, "thaumcraft:page", 1.0F, 1.0F, false);
/*     */     }
/*     */     
/* 109 */     player.openGui(Thaumcraft.instance, 12, par2World, 0, 0, 0);
/* 110 */     return stack;
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/* 116 */     return itemstack.func_77960_j() != 42 ? EnumRarity.uncommon : EnumRarity.epic;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
/*     */   {
/* 122 */     if (par1ItemStack.func_77960_j() == 42) {
/* 123 */       par3List.add("Cheat Sheet");
/*     */     }
/* 125 */     super.func_77624_a(par1ItemStack, par2EntityPlayer, par3List, par4);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/relics/ItemThaumonomicon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */