/*     */ package thaumcraft.common.items.armor;
/*     */ 
/*     */ import baubles.api.BaublesApi;
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import java.util.HashMap;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagByte;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagShort;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.common.blocks.ItemJarFilled;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.items.baubles.ItemGirdleHover;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.misc.PacketFlyToServer;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ public class Hover
/*     */ {
/*     */   public static final int EFFICIENCY = 360;
/*  28 */   static HashMap<Integer, Boolean> hovering = new HashMap();
/*     */   
/*  30 */   static HashMap<Integer, Long> timing = new HashMap();
/*     */   
/*     */   public static boolean toggleHover(EntityPlayer player, int playerId, ItemStack armor) {
/*  33 */     boolean hover = hovering.get(Integer.valueOf(playerId)) == null ? false : ((Boolean)hovering.get(Integer.valueOf(playerId))).booleanValue();
/*  34 */     short fuel = 0;
/*  35 */     if ((armor.func_77942_o()) && (armor.field_77990_d.func_74764_b("jar"))) {
/*  36 */       ItemStack jar = ItemStack.func_77949_a(armor.field_77990_d.func_74775_l("jar"));
/*     */       
/*  38 */       if ((jar != null) && ((jar.func_77973_b() instanceof ItemJarFilled)) && (jar.func_77942_o())) {
/*  39 */         AspectList aspects = ((ItemJarFilled)jar.func_77973_b()).getAspects(jar);
/*  40 */         if ((aspects != null) && (aspects.size() > 0) && (aspects.getAmount(Aspect.ENERGY) > 0)) {
/*  41 */           fuel = (short)aspects.getAmount(Aspect.ENERGY);
/*     */         }
/*     */       }
/*     */     }
/*  45 */     if ((!hover) && (fuel <= 0)) { return false;
/*     */     }
/*  47 */     hovering.put(Integer.valueOf(playerId), Boolean.valueOf(!hover));
/*  48 */     if (player.field_70170_p.field_72995_K) {
/*  49 */       PacketHandler.INSTANCE.sendToServer(new PacketFlyToServer(player, !hover));
/*  50 */       player.field_70170_p.func_72980_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, !hover ? "thaumcraft:hhon" : "thaumcraft:hhoff", 0.33F, 1.0F, false);
/*     */     }
/*     */     
/*  53 */     return !hover;
/*     */   }
/*     */   
/*     */   public static void setHover(int playerId, boolean hover) {
/*  57 */     hovering.put(Integer.valueOf(playerId), Boolean.valueOf(hover));
/*     */   }
/*     */   
/*     */   public static boolean getHover(int playerId) {
/*  61 */     return hovering.containsKey(Integer.valueOf(playerId)) ? ((Boolean)hovering.get(Integer.valueOf(playerId))).booleanValue() : false;
/*     */   }
/*     */   
/*     */   public static void handleHoverArmor(EntityPlayer player, ItemStack armor)
/*     */   {
/*  66 */     if ((hovering.get(Integer.valueOf(player.func_145782_y())) == null) && (armor.func_77942_o()) && (armor.field_77990_d.func_74764_b("hover")))
/*     */     {
/*  68 */       hovering.put(Integer.valueOf(player.func_145782_y()), Boolean.valueOf(armor.field_77990_d.func_74771_c("hover") == 1));
/*  69 */       if (player.field_70170_p.field_72995_K) {
/*  70 */         PacketHandler.INSTANCE.sendToServer(new PacketFlyToServer(player, armor.field_77990_d.func_74771_c("hover") == 1));
/*     */       }
/*     */     }
/*  73 */     boolean hover = hovering.get(Integer.valueOf(player.func_145782_y())) == null ? false : ((Boolean)hovering.get(Integer.valueOf(player.func_145782_y()))).booleanValue();
/*  74 */     World world = player.field_70170_p;
/*     */     
/*  76 */     player.field_71075_bZ.field_75100_b = hover;
/*     */     
/*  78 */     if ((world.field_72995_K) && ((player instanceof EntityPlayerSP)))
/*     */     {
/*  80 */       if ((hover) && (expendCharge(player, armor)))
/*     */       {
/*  82 */         long currenttime = System.currentTimeMillis();
/*  83 */         long time = 0L;
/*  84 */         if (timing.get(Integer.valueOf(player.func_145782_y())) != null) { time = ((Long)timing.get(Integer.valueOf(player.func_145782_y()))).longValue();
/*     */         }
/*  86 */         if (time < currenttime) {
/*  87 */           time = currenttime + 1200L;
/*  88 */           timing.put(Integer.valueOf(player.func_145782_y()), Long.valueOf(time));
/*     */           
/*  90 */           player.field_70170_p.func_72980_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, "thaumcraft:jacobs", 0.05F, 1.0F + player.field_70170_p.field_73012_v.nextFloat() * 0.05F, false);
/*     */         }
/*     */         
/*     */ 
/*  94 */         int haste = net.minecraft.enchantment.EnchantmentHelper.func_77506_a(Config.enchHaste.field_77352_x, armor);
/*  95 */         float mod = 0.7F + 0.075F * haste;
/*     */         
/*  97 */         if ((BaublesApi.getBaubles(player).func_70301_a(3) != null) && ((BaublesApi.getBaubles(player).func_70301_a(3).func_77973_b() instanceof ItemGirdleHover)))
/*     */         {
/*  99 */           mod += 0.21F;
/*     */         }
/*     */         
/* 102 */         if (mod > 1.0F) { mod = 1.0F;
/*     */         }
/* 104 */         player.field_70159_w *= mod;
/* 105 */         player.field_70179_y *= mod;
/*     */ 
/*     */       }
/* 108 */       else if (hover) { toggleHover(player, player.func_145782_y(), armor);
/*     */       }
/*     */     } else {
/* 111 */       if ((armor.func_77942_o()) && (!armor.field_77990_d.func_74764_b("hover"))) {
/* 112 */         armor.func_77983_a("hover", new NBTTagByte((byte)(hover ? 1 : 0)));
/*     */       }
/* 114 */       if ((hover) && (expendCharge(player, armor))) {
/* 115 */         if ((player instanceof EntityPlayerMP)) {
/* 116 */           Utils.resetFloatCounter((EntityPlayerMP)player);
/*     */         }
/* 118 */         player.field_70143_R = 0.0F;
/* 119 */         if ((armor.func_77942_o()) && (armor.field_77990_d.func_74764_b("hover")) && (armor.field_77990_d.func_74771_c("hover") != 1))
/*     */         {
/* 121 */           armor.func_77983_a("hover", new NBTTagByte((byte)(hover ? 1 : 0)));
/*     */         }
/*     */       } else {
/* 124 */         if (hover) toggleHover(player, player.func_145782_y(), armor);
/* 125 */         player.field_70143_R *= 0.75F;
/* 126 */         if ((armor.func_77942_o()) && (armor.field_77990_d.func_74764_b("hover")) && (armor.field_77990_d.func_74771_c("hover") == 1))
/*     */         {
/* 128 */           armor.func_77983_a("hover", new NBTTagByte((byte)(hover ? 1 : 0)));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean expendCharge(EntityPlayer player, ItemStack is) {
/* 135 */     if ((is.func_77942_o()) && (is.field_77990_d.func_74764_b("jar"))) {
/* 136 */       ItemStack jar = ItemStack.func_77949_a(is.field_77990_d.func_74775_l("jar"));
/* 137 */       short fuel = 0;
/*     */       
/* 139 */       if ((jar != null) && ((jar.func_77973_b() instanceof ItemJarFilled)) && (jar.func_77942_o())) {
/* 140 */         AspectList aspects = ((ItemJarFilled)jar.func_77973_b()).getAspects(jar);
/* 141 */         if ((aspects != null) && (aspects.size() > 0) && (aspects.getAmount(Aspect.ENERGY) > 0)) {
/* 142 */           fuel = (short)aspects.getAmount(Aspect.ENERGY);
/*     */         }
/*     */       }
/* 145 */       float mod = 1.0F;
/* 146 */       if ((BaublesApi.getBaubles(player).func_70301_a(3) != null) && ((BaublesApi.getBaubles(player).func_70301_a(3).func_77973_b() instanceof ItemGirdleHover)))
/*     */       {
/* 148 */         mod = 0.8F;
/*     */       }
/*     */       
/* 151 */       if (!is.field_77990_d.func_74764_b("charge")) {
/* 152 */         is.func_77983_a("charge", new NBTTagShort((short)0));
/*     */       }
/* 154 */       if ((fuel > 0) && (is.field_77990_d.func_74764_b("charge"))) {
/* 155 */         short charge = is.field_77990_d.func_74765_d("charge");
/* 156 */         if (charge < 360.0F * mod) {
/* 157 */           is.func_77983_a("charge", new NBTTagShort((short)(charge + 1)));
/* 158 */           return true;
/*     */         }
/* 160 */         is.func_77983_a("charge", new NBTTagShort((short)0));
/* 161 */         fuel = (short)(fuel - 1);
/* 162 */         if (fuel > 0) {
/* 163 */           ((ItemJarFilled)jar.func_77973_b()).setAspects(jar, new AspectList().add(Aspect.ENERGY, fuel));
/*     */         }
/*     */         else
/*     */         {
/* 167 */           ((ItemJarFilled)jar.func_77973_b()).setAspects(jar, new AspectList().remove(Aspect.ENERGY));
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 174 */         NBTTagCompound var4 = new NBTTagCompound();
/* 175 */         jar.func_77955_b(var4);
/* 176 */         is.func_77983_a("jar", var4);
/* 177 */         return fuel > 0;
/*     */       }
/*     */     }
/*     */     
/* 181 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/armor/Hover.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */