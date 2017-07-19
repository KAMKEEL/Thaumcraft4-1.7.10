/*     */ package thaumcraft.common.lib.events;
/*     */ 
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import java.util.HashMap;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ import net.minecraftforge.event.entity.player.ItemTooltipEvent;
/*     */ import thaumcraft.api.IRunicArmor;
/*     */ import thaumcraft.api.IWarpingGear;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.entities.IEldritchMob;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.monster.mods.ChampionModifier;
/*     */ import thaumcraft.common.items.armor.ItemFortressArmor;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXShield;
/*     */ import thaumcraft.common.lib.network.playerdata.PacketRunicCharge;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ public class EventHandlerRunic
/*     */ {
/*  41 */   public HashMap<Integer, Integer> runicCharge = new HashMap();
/*  42 */   private HashMap<Integer, Long> nextCycle = new HashMap();
/*  43 */   private HashMap<Integer, Integer> lastCharge = new HashMap();
/*  44 */   public HashMap<Integer, Integer[]> runicInfo = new HashMap();
/*  45 */   private HashMap<String, Long> upgradeCooldown = new HashMap();
/*     */   
/*  47 */   public boolean isDirty = true;
/*  48 */   private int rechargeDelay = 0;
/*     */   
/*     */ 
/*     */   @SubscribeEvent
/*     */   public void livingTick(LivingEvent.LivingUpdateEvent event)
/*     */   {
/*  54 */     if ((!event.entity.field_70170_p.field_72995_K) && ((event.entity instanceof EntityPlayer))) {
/*  55 */       EntityPlayer player = (EntityPlayer)event.entity;
/*     */       
/*     */ 
/*  58 */       if ((this.isDirty) || (player.field_70173_aa % 40 == 0)) {
/*  59 */         int max = 0;
/*  60 */         int charged = 0;
/*  61 */         int kinetic = 0;
/*  62 */         int healing = 0;
/*  63 */         int emergency = 0;
/*  64 */         this.isDirty = false;
/*     */         
/*  66 */         for (int a = 0; a < 4; a++) {
/*  67 */           if ((player.field_71071_by.func_70440_f(a) != null) && ((player.field_71071_by.func_70440_f(a).func_77973_b() instanceof IRunicArmor)))
/*     */           {
/*  69 */             int amount = getFinalCharge(player.field_71071_by.func_70440_f(a));
/*  70 */             max += amount;
/*     */           }
/*     */         }
/*     */         
/*  74 */         IInventory baubles = baubles.api.BaublesApi.getBaubles(player);
/*  75 */         for (int a = 0; a < 4; a++) {
/*  76 */           if ((baubles.func_70301_a(a) != null) && ((baubles.func_70301_a(a).func_77973_b() instanceof IRunicArmor)))
/*     */           {
/*  78 */             int amount = getFinalCharge(baubles.func_70301_a(a));
/*  79 */             if ((baubles.func_70301_a(a).func_77973_b() instanceof thaumcraft.common.items.baubles.ItemRingRunic)) {
/*  80 */               switch (baubles.func_70301_a(a).func_77960_j()) {
/*  81 */               case 2:  charged++; break;
/*  82 */               case 3:  healing++;
/*     */               }
/*     */               
/*  85 */             } else if (((baubles.func_70301_a(a).func_77973_b() instanceof thaumcraft.common.items.baubles.ItemAmuletRunic)) && (baubles.func_70301_a(a).func_77960_j() == 1))
/*     */             {
/*  87 */               emergency++;
/*     */             }
/*  89 */             else if (((baubles.func_70301_a(a).func_77973_b() instanceof thaumcraft.common.items.baubles.ItemGirdleRunic)) && (baubles.func_70301_a(a).func_77960_j() == 1))
/*     */             {
/*  91 */               kinetic++;
/*     */             }
/*  93 */             max += amount;
/*     */           }
/*     */         }
/*     */         
/*  97 */         if (max > 0) {
/*  98 */           this.runicInfo.put(Integer.valueOf(player.func_145782_y()), new Integer[] { Integer.valueOf(max), Integer.valueOf(charged), Integer.valueOf(kinetic), Integer.valueOf(healing), Integer.valueOf(emergency) });
/*  99 */           if (this.runicCharge.containsKey(Integer.valueOf(player.func_145782_y()))) {
/* 100 */             int charge = ((Integer)this.runicCharge.get(Integer.valueOf(player.func_145782_y()))).intValue();
/* 101 */             if (charge > max) {
/* 102 */               this.runicCharge.put(Integer.valueOf(player.func_145782_y()), Integer.valueOf(max));
/* 103 */               PacketHandler.INSTANCE.sendTo(new PacketRunicCharge(player, Short.valueOf((short)max), max), (EntityPlayerMP)player);
/*     */             }
/*     */           }
/*     */         } else {
/* 107 */           this.runicInfo.remove(Integer.valueOf(player.func_145782_y()));
/* 108 */           this.runicCharge.put(Integer.valueOf(player.func_145782_y()), Integer.valueOf(0));
/* 109 */           PacketHandler.INSTANCE.sendTo(new PacketRunicCharge(player, Short.valueOf((short)0), 0), (EntityPlayerMP)player);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 116 */       if (this.rechargeDelay > 0) {
/* 117 */         this.rechargeDelay -= 1;
/*     */       }
/* 119 */       else if (this.runicInfo.containsKey(Integer.valueOf(player.func_145782_y()))) {
/* 120 */         if (!this.lastCharge.containsKey(Integer.valueOf(player.func_145782_y()))) {
/* 121 */           this.lastCharge.put(Integer.valueOf(player.func_145782_y()), Integer.valueOf(-1));
/*     */         }
/* 123 */         if (!this.runicCharge.containsKey(Integer.valueOf(player.func_145782_y()))) {
/* 124 */           this.runicCharge.put(Integer.valueOf(player.func_145782_y()), Integer.valueOf(0));
/*     */         }
/* 126 */         if (!this.nextCycle.containsKey(Integer.valueOf(player.func_145782_y()))) {
/* 127 */           this.nextCycle.put(Integer.valueOf(player.func_145782_y()), Long.valueOf(0L));
/*     */         }
/*     */         
/* 130 */         long time = System.currentTimeMillis();
/*     */         
/* 132 */         int charge = ((Integer)this.runicCharge.get(Integer.valueOf(player.func_145782_y()))).intValue();
/* 133 */         if (charge > ((Integer[])this.runicInfo.get(Integer.valueOf(player.func_145782_y())))[0].intValue()) {
/* 134 */           charge = ((Integer[])this.runicInfo.get(Integer.valueOf(player.func_145782_y())))[0].intValue();
/*     */         }
/* 136 */         else if ((charge < ((Integer[])this.runicInfo.get(Integer.valueOf(player.func_145782_y())))[0].intValue()) && (((Long)this.nextCycle.get(Integer.valueOf(player.func_145782_y()))).longValue() < time) && (thaumcraft.common.items.wands.WandManager.consumeVisFromInventory(player, new AspectList().add(thaumcraft.api.aspects.Aspect.AIR, Config.shieldCost).add(thaumcraft.api.aspects.Aspect.EARTH, Config.shieldCost))))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/* 141 */           long interval = Config.shieldRecharge - ((Integer[])this.runicInfo.get(Integer.valueOf(player.func_145782_y())))[1].intValue() * 500;
/* 142 */           this.nextCycle.put(Integer.valueOf(player.func_145782_y()), Long.valueOf(time + interval));
/* 143 */           charge++;
/* 144 */           this.runicCharge.put(Integer.valueOf(player.func_145782_y()), Integer.valueOf(charge));
/*     */         }
/* 146 */         if (((Integer)this.lastCharge.get(Integer.valueOf(player.func_145782_y()))).intValue() != charge)
/*     */         {
/* 148 */           PacketHandler.INSTANCE.sendTo(new PacketRunicCharge(player, Short.valueOf((short)charge), ((Integer[])this.runicInfo.get(Integer.valueOf(player.func_145782_y())))[0].intValue()), (EntityPlayerMP)player);
/* 149 */           this.lastCharge.put(Integer.valueOf(player.func_145782_y()), Integer.valueOf(charge));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SubscribeEvent
/*     */   public void entityHurt(LivingHurtEvent event)
/*     */   {
/* 162 */     if ((event.source.func_76364_f() != null) && ((event.source.func_76364_f() instanceof EntityPlayer))) {
/* 163 */       EntityPlayer leecher = (EntityPlayer)event.source.func_76364_f();
/* 164 */       ItemStack helm = leecher.field_71071_by.field_70460_b[3];
/* 165 */       if ((helm != null) && ((helm.func_77973_b() instanceof ItemFortressArmor)) && 
/* 166 */         (helm.func_77942_o()) && (helm.field_77990_d.func_74764_b("mask")) && (helm.field_77990_d.func_74762_e("mask") == 2) && (leecher.field_70170_p.field_73012_v.nextFloat() < event.ammount / 12.0F))
/*     */       {
/*     */ 
/*     */ 
/* 170 */         leecher.func_70691_i(1.0F);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 175 */     if ((event.entity instanceof EntityPlayer)) {
/* 176 */       long time = System.currentTimeMillis();
/* 177 */       EntityPlayer player = (EntityPlayer)event.entity;
/*     */       
/*     */ 
/* 180 */       if ((event.source.func_76364_f() != null) && ((event.source.func_76364_f() instanceof EntityLivingBase))) {
/* 181 */         EntityLivingBase attacker = (EntityLivingBase)event.source.func_76364_f();
/* 182 */         ItemStack helm = player.field_71071_by.field_70460_b[3];
/* 183 */         if ((helm != null) && ((helm.func_77973_b() instanceof ItemFortressArmor)) && 
/* 184 */           (helm.func_77942_o()) && (helm.field_77990_d.func_74764_b("mask")) && (helm.field_77990_d.func_74762_e("mask") == 1) && (player.field_70170_p.field_73012_v.nextFloat() < event.ammount / 10.0F))
/*     */         {
/*     */           try
/*     */           {
/* 188 */             attacker.func_70690_d(new PotionEffect(Potion.field_82731_v.func_76396_c(), 80));
/*     */           }
/*     */           catch (Exception e) {}
/*     */         }
/*     */       }
/*     */       
/* 194 */       if ((event.source == DamageSource.field_76369_e) || (event.source == DamageSource.field_82727_n) || (event.source == DamageSource.field_76380_i) || (event.source == DamageSource.field_76366_f))
/*     */       {
/*     */ 
/* 197 */         return;
/*     */       }
/* 199 */       if ((this.runicInfo.containsKey(Integer.valueOf(player.func_145782_y()))) && (this.runicCharge.containsKey(Integer.valueOf(player.func_145782_y()))) && (((Integer)this.runicCharge.get(Integer.valueOf(player.func_145782_y()))).intValue() > 0))
/*     */       {
/*     */ 
/* 202 */         int target = -1;
/* 203 */         if (event.source.func_76346_g() != null) target = event.source.func_76346_g().func_145782_y();
/* 204 */         if (event.source == DamageSource.field_76379_h) target = -2;
/* 205 */         if (event.source == DamageSource.field_82729_p) { target = -3;
/*     */         }
/* 207 */         PacketHandler.INSTANCE.sendToAllAround(new PacketFXShield(event.entity.func_145782_y(), target), new NetworkRegistry.TargetPoint(event.entity.field_70170_p.field_73011_w.field_76574_g, event.entity.field_70165_t, event.entity.field_70163_u, event.entity.field_70161_v, 64.0D));
/*     */         
/*     */ 
/*     */ 
/* 211 */         int charge = ((Integer)this.runicCharge.get(Integer.valueOf(player.func_145782_y()))).intValue();
/* 212 */         if (charge > event.ammount) {
/* 213 */           charge = (int)(charge - event.ammount);
/* 214 */           event.ammount = 0.0F;
/*     */         } else {
/* 216 */           event.ammount -= charge;
/* 217 */           charge = 0;
/*     */         }
/*     */         
/* 220 */         String key = player.func_145782_y() + ":" + 2;
/* 221 */         if ((charge <= 0) && (((Integer[])this.runicInfo.get(Integer.valueOf(player.func_145782_y())))[2].intValue() > 0) && ((!this.upgradeCooldown.containsKey(key)) || (((Long)this.upgradeCooldown.get(key)).longValue() < time)))
/*     */         {
/* 223 */           this.upgradeCooldown.put(key, Long.valueOf(time + 20000L));
/* 224 */           player.field_70170_p.func_72885_a(player, player.field_70165_t, player.field_70163_u + player.field_70131_O / 2.0F, player.field_70161_v, 1.5F + ((Integer[])this.runicInfo.get(Integer.valueOf(player.func_145782_y())))[2].intValue() * 0.5F, false, false);
/*     */         }
/*     */         
/* 227 */         key = player.func_145782_y() + ":" + 3;
/* 228 */         if ((charge <= 0) && (((Integer[])this.runicInfo.get(Integer.valueOf(player.func_145782_y())))[3].intValue() > 0) && ((!this.upgradeCooldown.containsKey(key)) || (((Long)this.upgradeCooldown.get(key)).longValue() < time)))
/*     */         {
/* 230 */           this.upgradeCooldown.put(key, Long.valueOf(time + 20000L));
/* 231 */           synchronized (player) {
/*     */             try {
/* 233 */               player.func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 240, ((Integer[])this.runicInfo.get(Integer.valueOf(player.func_145782_y())))[3].intValue()));
/*     */             } catch (Exception e) {}
/*     */           }
/* 236 */           player.field_70170_p.func_72956_a(player, "thaumcraft:runicShieldEffect", 1.0F, 1.0F);
/*     */         }
/*     */         
/* 239 */         key = player.func_145782_y() + ":" + 4;
/* 240 */         if ((charge <= 0) && (((Integer[])this.runicInfo.get(Integer.valueOf(player.func_145782_y())))[4].intValue() > 0) && ((!this.upgradeCooldown.containsKey(key)) || (((Long)this.upgradeCooldown.get(key)).longValue() < time)))
/*     */         {
/* 242 */           this.upgradeCooldown.put(key, Long.valueOf(time + 60000L));
/* 243 */           int t = 8 * ((Integer[])this.runicInfo.get(Integer.valueOf(player.func_145782_y())))[4].intValue();
/* 244 */           charge = Math.min(((Integer[])this.runicInfo.get(Integer.valueOf(player.func_145782_y())))[0].intValue(), t);
/* 245 */           this.isDirty = true;
/* 246 */           player.field_70170_p.func_72956_a(player, "thaumcraft:runicShieldCharge", 1.0F, 1.0F);
/*     */         }
/*     */         
/* 249 */         if (charge <= 0) { this.rechargeDelay = Config.shieldWait;
/*     */         }
/* 251 */         this.runicCharge.put(Integer.valueOf(player.func_145782_y()), Integer.valueOf(charge));
/* 252 */         PacketHandler.INSTANCE.sendTo(new PacketRunicCharge(player, Short.valueOf((short)charge), ((Integer[])this.runicInfo.get(Integer.valueOf(player.func_145782_y())))[0].intValue()), (EntityPlayerMP)player);
/*     */       }
/*     */       
/*     */ 
/*     */     }
/* 257 */     else if (((event.entity instanceof EntityMob)) && ((((EntityMob)event.entity).func_110148_a(EntityUtils.CHAMPION_MOD).func_111126_e() >= 0.0D) || ((event.entity instanceof IEldritchMob))))
/*     */     {
/*     */ 
/* 260 */       EntityMob mob = (EntityMob)event.entity;
/* 261 */       int t = (int)((EntityMob)event.entity).func_110148_a(EntityUtils.CHAMPION_MOD).func_111126_e();
/*     */       
/* 263 */       if (((t == 5) || ((event.entity instanceof IEldritchMob))) && (mob.func_110139_bj() > 0.0F)) {
/* 264 */         int target = -1;
/* 265 */         if (event.source.func_76346_g() != null) {
/* 266 */           target = event.source.func_76346_g().func_145782_y();
/*     */         }
/* 268 */         if (event.source == DamageSource.field_76379_h) target = -2;
/* 269 */         if (event.source == DamageSource.field_82729_p) target = -3;
/* 270 */         PacketHandler.INSTANCE.sendToAllAround(new PacketFXShield(mob.func_145782_y(), target), new NetworkRegistry.TargetPoint(event.entity.field_70170_p.field_73011_w.field_76574_g, event.entity.field_70165_t, event.entity.field_70163_u, event.entity.field_70161_v, 32.0D));
/*     */         
/*     */ 
/* 273 */         event.entity.field_70170_p.func_72908_a(event.entity.field_70165_t, event.entity.field_70163_u, event.entity.field_70161_v, "thaumcraft:runicShieldEffect", 0.66F, 1.1F + event.entity.field_70170_p.field_73012_v.nextFloat() * 0.1F);
/*     */ 
/*     */       }
/* 276 */       else if ((t >= 0) && (ChampionModifier.mods[t].type == 2) && (event.source.func_76364_f() != null) && ((event.source.func_76364_f() instanceof EntityLivingBase)))
/*     */       {
/* 278 */         EntityLivingBase attacker = (EntityLivingBase)event.source.func_76364_f();
/* 279 */         event.ammount = ChampionModifier.mods[t].effect.performEffect(mob, attacker, event.source, event.ammount);
/*     */       }
/*     */     }
/*     */     
/* 283 */     if ((event.ammount > 0.0F) && (event.source.func_76364_f() != null) && ((event.entity instanceof EntityLivingBase)) && ((event.source.func_76364_f() instanceof EntityMob)) && (((EntityMob)event.source.func_76364_f()).func_110148_a(EntityUtils.CHAMPION_MOD).func_111126_e() >= 0.0D))
/*     */     {
/*     */ 
/* 286 */       EntityMob mob = (EntityMob)event.source.func_76364_f();
/* 287 */       int t = (int)mob.func_110148_a(EntityUtils.CHAMPION_MOD).func_111126_e();
/* 288 */       if (ChampionModifier.mods[t].type == 1) {
/* 289 */         event.ammount = ChampionModifier.mods[t].effect.performEffect(mob, (EntityLivingBase)event.entity, event.source, event.ammount);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void tooltipEvent(ItemTooltipEvent event) {
/* 296 */     int charge = getFinalCharge(event.itemStack);
/* 297 */     if (charge > 0) {
/* 298 */       event.toolTip.add(EnumChatFormatting.GOLD + net.minecraft.util.StatCollector.func_74838_a("item.runic.charge") + " +" + charge);
/*     */     }
/* 300 */     int warp = getFinalWarp(event.itemStack, event.entityPlayer);
/* 301 */     if (warp > 0)
/* 302 */       event.toolTip.add(EnumChatFormatting.DARK_PURPLE + net.minecraft.util.StatCollector.func_74838_a("item.warping") + " " + warp);
/*     */   }
/*     */   
/*     */   public static int getFinalCharge(ItemStack stack) {
/* 306 */     if (!(stack.func_77973_b() instanceof IRunicArmor)) return 0;
/* 307 */     IRunicArmor armor = (IRunicArmor)stack.func_77973_b();
/* 308 */     int base = armor.getRunicCharge(stack);
/* 309 */     if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("RS.HARDEN"))) {
/* 310 */       base += stack.field_77990_d.func_74771_c("RS.HARDEN");
/*     */     }
/* 312 */     return base;
/*     */   }
/*     */   
/*     */   public static int getFinalWarp(ItemStack stack, EntityPlayer player) {
/* 316 */     if ((stack == null) || (!(stack.func_77973_b() instanceof IWarpingGear))) return 0;
/* 317 */     IWarpingGear armor = (IWarpingGear)stack.func_77973_b();
/* 318 */     return armor.getWarp(stack, player);
/*     */   }
/*     */   
/*     */   public static int getHardening(ItemStack stack) {
/* 322 */     if (!(stack.func_77973_b() instanceof IRunicArmor)) return 0;
/* 323 */     int base = 0;
/* 324 */     if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("RS.HARDEN"))) {
/* 325 */       base += stack.field_77990_d.func_74771_c("RS.HARDEN");
/*     */     }
/* 327 */     return base;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/events/EventHandlerRunic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */