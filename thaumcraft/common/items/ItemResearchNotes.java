/*     */ package thaumcraft.common.items;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.research.ResearchCategories;
/*     */ import thaumcraft.api.research.ResearchItem;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ import thaumcraft.common.lib.research.ResearchNoteData;
/*     */ 
/*     */ public class ItemResearchNotes extends Item
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon iconNote;
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon iconNoteOver;
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon iconDiscovery;
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon iconDiscoveryOver;
/*     */   
/*     */   public ItemResearchNotes()
/*     */   {
/*  35 */     func_77627_a(true);
/*  36 */     func_77656_e(0);
/*  37 */     func_77625_d(1);
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
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  52 */     this.iconNote = ir.func_94245_a("thaumcraft:researchnotes");
/*  53 */     this.iconNoteOver = ir.func_94245_a("thaumcraft:researchnotesoverlay");
/*  54 */     this.iconDiscovery = ir.func_94245_a("thaumcraft:discovery");
/*  55 */     this.iconDiscoveryOver = ir.func_94245_a("thaumcraft:discoveryoverlay");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1) {
/*  60 */     return par1 / 64 == 0 ? this.iconNote : this.iconDiscovery;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77618_c(int par1, int renderPass) {
/*  65 */     return par1 / 64 == 0 ? this.iconNoteOver : renderPass == 0 ? this.iconDiscovery : par1 / 64 == 0 ? this.iconNote : this.iconDiscoveryOver;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
/*     */   {
/*  72 */     if (!world.field_72995_K) {
/*  73 */       if ((ResearchManager.getData(stack) != null) && (ResearchManager.getData(stack).isComplete()) && (!ResearchManager.isResearchComplete(player.func_70005_c_(), ResearchManager.getData(stack).key)))
/*     */       {
/*  75 */         if (ResearchManager.doesPlayerHaveRequisites(player.func_70005_c_(), ResearchManager.getData(stack).key)) {
/*  76 */           thaumcraft.common.lib.network.PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketResearchComplete(ResearchManager.getData(stack).key), (net.minecraft.entity.player.EntityPlayerMP)player);
/*     */           
/*  78 */           Thaumcraft.proxy.getResearchManager().completeResearch(player, ResearchManager.getData(stack).key);
/*     */           
/*  80 */           if (ResearchCategories.getResearch(ResearchManager.getData(stack).key).siblings != null) {
/*  81 */             for (String sibling : ResearchCategories.getResearch(ResearchManager.getData(stack).key).siblings)
/*     */             {
/*  83 */               if ((!ResearchManager.isResearchComplete(player.func_70005_c_(), sibling)) && (ResearchManager.doesPlayerHaveRequisites(player.func_70005_c_(), sibling)))
/*     */               {
/*  85 */                 thaumcraft.common.lib.network.PacketHandler.INSTANCE.sendTo(new thaumcraft.common.lib.network.playerdata.PacketResearchComplete(sibling), (net.minecraft.entity.player.EntityPlayerMP)player);
/*     */                 
/*  87 */                 Thaumcraft.proxy.getResearchManager().completeResearch(player, sibling);
/*     */               }
/*     */             }
/*     */           }
/*  91 */           stack.field_77994_a -= 1;
/*  92 */           world.func_72956_a(player, "thaumcraft:learn", 0.75F, 1.0F);
/*     */         } else {
/*  94 */           player.func_145747_a(new net.minecraft.util.ChatComponentTranslation(StatCollector.func_74838_a("tc.researcherror"), new Object[0]));
/*     */         }
/*     */       }
/*  97 */       else if ((stack.func_77960_j() == 42) || (stack.func_77960_j() == 24)) {
/*  98 */         String key = ResearchManager.findHiddenResearch(player);
/*  99 */         if (key.equals("FAIL")) {
/* 100 */           stack.field_77994_a -= 1;
/* 101 */           EntityItem entityItem = new EntityItem(world, player.field_70165_t, player.field_70163_u + player.func_70047_e() / 2.0F, player.field_70161_v, new ItemStack(thaumcraft.common.config.ConfigItems.itemResource, 7 + world.field_73012_v.nextInt(3), 9));
/*     */           
/*     */ 
/* 104 */           world.func_72838_d(entityItem);
/* 105 */           world.func_72956_a(player, "thaumcraft:erase", 0.75F, 1.0F);
/*     */         } else {
/* 107 */           stack.func_77964_b(0);
/* 108 */           stack.field_77990_d = ResearchManager.createNote(stack, key, player.field_70170_p).field_77990_d;
/* 109 */           world.func_72956_a(player, "thaumcraft:write", 0.75F, 1.0F);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 114 */     return stack;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_82790_a(ItemStack stack, int par2)
/*     */   {
/* 121 */     if (par2 == 1) {
/* 122 */       int c = 10066329;
/* 123 */       ResearchNoteData rd = ResearchManager.getData(stack);
/* 124 */       if (rd != null) c = rd.color;
/* 125 */       return c;
/*     */     }
/* 127 */     return super.func_82790_a(stack, par2);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77623_v()
/*     */   {
/* 134 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_77651_p()
/*     */   {
/* 139 */     return true;
/*     */   }
/*     */   
/*     */   public String func_77653_i(ItemStack itemstack)
/*     */   {
/* 144 */     String name = itemstack.func_77960_j() < 64 ? StatCollector.func_74838_a("item.researchnotes.name") : StatCollector.func_74838_a("item.discovery.name");
/* 145 */     return name;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4)
/*     */   {
/* 151 */     if ((stack.func_77960_j() == 24) || (stack.func_77960_j() == 42)) {
/* 152 */       list.add(EnumChatFormatting.GOLD + StatCollector.func_74838_a("item.researchnotes.unknown.1"));
/* 153 */       list.add(EnumChatFormatting.BLUE + StatCollector.func_74838_a("item.researchnotes.unknown.2"));
/*     */     }
/* 155 */     ResearchNoteData rd = ResearchManager.getData(stack);
/* 156 */     if ((rd != null) && (rd.key != null) && (ResearchCategories.getResearch(rd.key) != null)) {
/* 157 */       list.add("§6" + ResearchCategories.getResearch(rd.key).getName());
/* 158 */       list.add("§o" + ResearchCategories.getResearch(rd.key).getText());
/* 159 */       int warp = thaumcraft.api.ThaumcraftApi.getWarp(rd.key);
/* 160 */       if (warp > 0) {
/* 161 */         if (warp > 5) warp = 5;
/* 162 */         String ws = StatCollector.func_74838_a("tc.forbidden");
/* 163 */         String wr = StatCollector.func_74838_a("tc.forbidden.level." + warp);
/* 164 */         String wte = ws.replaceAll("%n", wr);
/* 165 */         list.add(EnumChatFormatting.DARK_PURPLE + wte);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/* 173 */     return itemstack.func_77960_j() < 64 ? EnumRarity.rare : EnumRarity.epic;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/ItemResearchNotes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */