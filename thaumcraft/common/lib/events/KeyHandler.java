/*     */ package thaumcraft.common.lib.events;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import cpw.mods.fml.client.registry.ClientRegistry;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.common.gameevent.TickEvent.Phase;
/*     */ import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import thaumcraft.common.entities.golems.ItemGolemBell;
/*     */ import thaumcraft.common.items.armor.Hover;
/*     */ import thaumcraft.common.items.armor.ItemHoverHarness;
/*     */ import thaumcraft.common.items.wands.ItemWandCasting;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.misc.PacketFocusChangeToServer;
/*     */ import thaumcraft.common.lib.network.misc.PacketItemKeyToServer;
/*     */ 
/*     */ public class KeyHandler
/*     */ {
/*  26 */   public KeyBinding keyF = new KeyBinding("Change Wand Focus", 33, "key.categories.misc");
/*     */   
/*  28 */   public KeyBinding keyH = new KeyBinding("Activate Hover Harness", 35, "key.categories.misc");
/*     */   
/*  30 */   public KeyBinding keyG = new KeyBinding("Misc Wand Toggle", 34, "key.categories.misc");
/*     */   
/*  32 */   private boolean keyPressedF = false;
/*  33 */   private boolean keyPressedH = false;
/*  34 */   private boolean keyPressedG = false;
/*  35 */   public static boolean radialActive = false;
/*  36 */   public static boolean radialLock = false;
/*  37 */   public static long lastPressF = 0L;
/*  38 */   public static long lastPressH = 0L;
/*  39 */   public static long lastPressG = 0L;
/*     */   
/*     */   public KeyHandler() {
/*  42 */     ClientRegistry.registerKeyBinding(this.keyF);
/*  43 */     ClientRegistry.registerKeyBinding(this.keyH);
/*  44 */     ClientRegistry.registerKeyBinding(this.keyG);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public void playerTick(TickEvent.PlayerTickEvent event) {
/*  50 */     if (event.side == Side.SERVER)
/*  51 */       return;
/*  52 */     if (event.phase == TickEvent.Phase.START)
/*     */     {
/*  54 */       if (this.keyF.func_151470_d()) {
/*  55 */         if (FMLClientHandler.instance().getClient().field_71415_G) {
/*  56 */           EntityPlayer player = event.player;
/*  57 */           if (player != null) {
/*  58 */             if (!this.keyPressedF) {
/*  59 */               lastPressF = System.currentTimeMillis();
/*  60 */               radialLock = false;
/*     */             }
/*  62 */             if ((!radialLock) && (player.func_70694_bm() != null) && ((player.func_70694_bm().func_77973_b() instanceof ItemWandCasting)) && (!((ItemWandCasting)player.func_70694_bm().func_77973_b()).isSceptre(player.func_70694_bm())))
/*     */             {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  68 */               if (player.func_70093_af()) {
/*  69 */                 PacketHandler.INSTANCE.sendToServer(new PacketFocusChangeToServer(player, "REMOVE"));
/*     */               }
/*     */               else
/*     */               {
/*  73 */                 radialActive = true; }
/*  74 */             } else if ((player.func_70694_bm() != null) && ((player.func_70694_bm().func_77973_b() instanceof ItemGolemBell)) && (!this.keyPressedF))
/*     */             {
/*     */ 
/*  77 */               PacketHandler.INSTANCE.sendToServer(new PacketItemKeyToServer(player, 0));
/*     */             }
/*     */           }
/*     */           
/*  81 */           this.keyPressedF = true;
/*     */         }
/*     */       } else {
/*  84 */         radialActive = false;
/*  85 */         if (this.keyPressedF) {
/*  86 */           lastPressF = System.currentTimeMillis();
/*     */         }
/*  88 */         this.keyPressedF = false;
/*     */       }
/*     */       
/*  91 */       if (this.keyH.func_151470_d())
/*     */       {
/*  93 */         if (FMLClientHandler.instance().getClient().field_71415_G) {
/*  94 */           EntityPlayer player = event.player;
/*  95 */           if (player != null) {
/*  96 */             if (!this.keyPressedH) {
/*  97 */               lastPressH = System.currentTimeMillis();
/*     */             }
/*  99 */             if ((player.field_71071_by.func_70440_f(2) != null) && ((player.field_71071_by.func_70440_f(2).func_77973_b() instanceof ItemHoverHarness)) && (!this.keyPressedH))
/*     */             {
/*     */ 
/*     */ 
/* 103 */               Hover.toggleHover(player, player.func_145782_y(), player.field_71071_by.func_70440_f(2));
/*     */             }
/*     */           }
/*     */           
/*     */ 
/*     */ 
/* 109 */           this.keyPressedH = true;
/*     */         }
/*     */       } else {
/* 112 */         if (this.keyPressedH) {
/* 113 */           lastPressH = System.currentTimeMillis();
/*     */         }
/* 115 */         this.keyPressedH = false;
/*     */       }
/*     */       
/*     */ 
/* 119 */       if (this.keyG.func_151470_d())
/*     */       {
/* 121 */         if (FMLClientHandler.instance().getClient().field_71415_G) {
/* 122 */           EntityPlayer player = event.player;
/* 123 */           if ((player != null) && 
/* 124 */             (!this.keyPressedG)) {
/* 125 */             lastPressG = System.currentTimeMillis();
/* 126 */             PacketHandler.INSTANCE.sendToServer(new PacketItemKeyToServer(player, 1));
/*     */           }
/*     */           
/*     */ 
/* 130 */           this.keyPressedG = true;
/*     */         }
/*     */       } else {
/* 133 */         if (this.keyPressedG) {
/* 134 */           lastPressG = System.currentTimeMillis();
/*     */         }
/* 136 */         this.keyPressedG = false;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/events/KeyHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */