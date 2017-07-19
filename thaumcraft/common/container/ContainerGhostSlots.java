/*     */ package thaumcraft.common.container;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ public class ContainerGhostSlots extends Container
/*     */ {
/*     */   public boolean func_75145_c(EntityPlayer entityplayer)
/*     */   {
/*  14 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_75144_a(int slotClicked, int button, int mod, EntityPlayer player)
/*     */   {
/*  20 */     ItemStack itemstack = null;
/*  21 */     InventoryPlayer inventoryplayer = player.field_71071_by;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  29 */     if (((mod == 0) || (mod == 1)) && ((button == 0) || (button == 1)))
/*     */     {
/*  31 */       if (slotClicked == 64537)
/*     */       {
/*  33 */         if ((inventoryplayer.func_70445_o() != null) && (slotClicked == 64537))
/*     */         {
/*  35 */           if (button == 0)
/*     */           {
/*  37 */             player.func_71019_a(inventoryplayer.func_70445_o(), false);
/*  38 */             inventoryplayer.func_70437_b((ItemStack)null);
/*     */           }
/*     */           
/*  41 */           if (button == 1)
/*     */           {
/*  43 */             player.func_71019_a(inventoryplayer.func_70445_o().func_77979_a(1), false);
/*     */             
/*  45 */             if (inventoryplayer.func_70445_o().field_77994_a == 0)
/*     */             {
/*  47 */               inventoryplayer.func_70437_b((ItemStack)null);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*  52 */       else if (mod == 1)
/*     */       {
/*  54 */         if (slotClicked < 0)
/*     */         {
/*  56 */           return null;
/*     */         }
/*  58 */         Slot slot2 = (Slot)this.field_75151_b.get(slotClicked);
/*  59 */         if ((slot2 != null) && (slot2.func_75211_c() != null) && ((slot2 instanceof SlotGhost))) {
/*  60 */           if (button == 0) {
/*  61 */             slot2.func_75215_d((ItemStack)null);
/*     */           }
/*  63 */           else if (button == 1) {
/*  64 */             ItemStack slotStack = slot2.func_75211_c();
/*  65 */             slotStack.field_77994_a += 16;
/*  66 */             if (slotStack.field_77994_a > slot2.func_75219_a()) {
/*  67 */               slotStack.field_77994_a = slot2.func_75219_a();
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       else {
/*  73 */         if (slotClicked < 0)
/*     */         {
/*  75 */           return null;
/*     */         }
/*     */         
/*  78 */         Slot slot2 = (Slot)this.field_75151_b.get(slotClicked);
/*     */         
/*  80 */         if (slot2 != null)
/*     */         {
/*  82 */           ItemStack slotStack = slot2.func_75211_c();
/*  83 */           ItemStack playerStack = inventoryplayer.func_70445_o();
/*     */           
/*  85 */           if (slotStack != null)
/*     */           {
/*  87 */             itemstack = slotStack.func_77946_l();
/*     */           }
/*     */           
/*  90 */           if (slotStack == null)
/*     */           {
/*  92 */             if ((playerStack != null) && (slot2.func_75214_a(playerStack)))
/*     */             {
/*  94 */               int k1 = button == 0 ? playerStack.field_77994_a : 1;
/*     */               
/*  96 */               if (k1 > slot2.func_75219_a())
/*     */               {
/*  98 */                 k1 = slot2.func_75219_a();
/*     */               }
/*     */               
/* 101 */               if (playerStack.field_77994_a >= k1)
/*     */               {
/* 103 */                 if ((slot2 instanceof SlotGhost)) {
/* 104 */                   ItemStack ic = playerStack.func_77946_l();
/* 105 */                   ic.field_77994_a = k1;
/* 106 */                   slot2.func_75215_d(ic);
/*     */                 } else {
/* 108 */                   slot2.func_75215_d(playerStack.func_77979_a(k1));
/*     */                 }
/*     */               }
/*     */               
/* 112 */               if ((!(slot2 instanceof SlotGhost)) && (playerStack.field_77994_a == 0))
/*     */               {
/* 114 */                 inventoryplayer.func_70437_b((ItemStack)null);
/*     */               }
/*     */             }
/*     */           }
/* 118 */           else if ((slot2.func_82869_a(player)) || ((slot2 instanceof SlotGhost)))
/*     */           {
/* 120 */             if (playerStack == null)
/*     */             {
/* 122 */               if ((slot2 instanceof SlotGhost)) {
/* 123 */                 int k1 = button == 0 ? 1 : -1;
/* 124 */                 if (slotStack.field_77994_a - k1 <= slot2.func_75219_a())
/* 125 */                   slot2.func_75209_a(k1);
/* 126 */                 if (slotStack.field_77994_a == 0)
/*     */                 {
/* 128 */                   slot2.func_75215_d((ItemStack)null);
/*     */                 }
/*     */               } else {
/* 131 */                 int k1 = button == 0 ? slotStack.field_77994_a : (slotStack.field_77994_a + 1) / 2;
/* 132 */                 ItemStack itemstack3 = slot2.func_75209_a(k1);
/* 133 */                 inventoryplayer.func_70437_b(itemstack3);
/*     */                 
/* 135 */                 if (slotStack.field_77994_a == 0)
/*     */                 {
/* 137 */                   slot2.func_75215_d((ItemStack)null);
/*     */                 }
/*     */                 
/* 140 */                 slot2.func_82870_a(player, inventoryplayer.func_70445_o());
/*     */               }
/*     */             }
/* 143 */             else if (slot2.func_75214_a(playerStack))
/*     */             {
/* 145 */               if ((slotStack.func_77973_b() == playerStack.func_77973_b()) && (slotStack.func_77960_j() == playerStack.func_77960_j()) && (ItemStack.func_77970_a(slotStack, playerStack)))
/*     */               {
/* 147 */                 int k1 = button == 0 ? playerStack.field_77994_a : 1;
/*     */                 
/* 149 */                 if (k1 > slot2.func_75219_a() - slotStack.field_77994_a)
/*     */                 {
/* 151 */                   k1 = slot2.func_75219_a() - slotStack.field_77994_a;
/*     */                 }
/*     */                 
/* 154 */                 if (k1 > playerStack.func_77976_d() - slotStack.field_77994_a)
/*     */                 {
/* 156 */                   k1 = playerStack.func_77976_d() - slotStack.field_77994_a;
/*     */                 }
/* 158 */                 if (!(slot2 instanceof SlotGhost)) {
/* 159 */                   playerStack.func_77979_a(k1);
/*     */                   
/* 161 */                   if (playerStack.field_77994_a == 0)
/*     */                   {
/* 163 */                     inventoryplayer.func_70437_b((ItemStack)null);
/*     */                   }
/*     */                 }
/* 166 */                 slotStack.field_77994_a += k1;
/*     */               }
/* 168 */               else if (playerStack.field_77994_a <= slot2.func_75219_a())
/*     */               {
/* 170 */                 slot2.func_75215_d(playerStack.func_77946_l());
/* 171 */                 if (!(slot2 instanceof SlotGhost)) inventoryplayer.func_70437_b(slotStack);
/*     */               }
/*     */             }
/* 174 */             else if ((slotStack.func_77973_b() == playerStack.func_77973_b()) && (playerStack.func_77976_d() > 1) && ((!slotStack.func_77981_g()) || (slotStack.func_77960_j() == playerStack.func_77960_j())) && (ItemStack.func_77970_a(slotStack, playerStack)))
/*     */             {
/* 176 */               int k1 = slotStack.field_77994_a;
/*     */               
/* 178 */               if ((k1 > 0) && (k1 + playerStack.field_77994_a <= playerStack.func_77976_d()))
/*     */               {
/* 180 */                 if (!(slot2 instanceof SlotGhost)) playerStack.field_77994_a += k1;
/* 181 */                 slotStack = slot2.func_75209_a(k1);
/*     */                 
/* 183 */                 if (slotStack.field_77994_a == 0)
/*     */                 {
/* 185 */                   slot2.func_75215_d((ItemStack)null);
/*     */                 }
/*     */                 
/* 188 */                 if (!(slot2 instanceof SlotGhost)) { slot2.func_82870_a(player, inventoryplayer.func_70445_o());
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/* 193 */           slot2.func_75218_e();
/*     */         }
/*     */       }
/*     */     }
/* 197 */     else if ((mod == 2) && (button >= 0) && (button < 9))
/*     */     {
/* 199 */       Slot slot2 = (Slot)this.field_75151_b.get(slotClicked);
/*     */       
/* 201 */       if (slot2.func_82869_a(player))
/*     */       {
/* 203 */         ItemStack slotStack = inventoryplayer.func_70301_a(button);
/* 204 */         boolean flag = (slotStack == null) || ((slot2.field_75224_c == inventoryplayer) && (slot2.func_75214_a(slotStack)));
/* 205 */         int k1 = -1;
/*     */         
/* 207 */         if (!flag)
/*     */         {
/* 209 */           k1 = inventoryplayer.func_70447_i();
/* 210 */           flag |= k1 > -1;
/*     */         }
/*     */         
/* 213 */         if ((slot2.func_75216_d()) && (flag))
/*     */         {
/* 215 */           ItemStack itemstack3 = slot2.func_75211_c();
/* 216 */           if (!(slot2 instanceof SlotGhost)) { inventoryplayer.func_70299_a(button, itemstack3.func_77946_l());
/*     */           }
/* 218 */           if (((slot2.field_75224_c != inventoryplayer) || (!slot2.func_75214_a(slotStack))) && (slotStack != null))
/*     */           {
/* 220 */             if (k1 > -1)
/*     */             {
/* 222 */               if (!(slot2 instanceof SlotGhost)) inventoryplayer.func_70441_a(slotStack);
/* 223 */               slot2.func_75209_a(itemstack3.field_77994_a);
/* 224 */               slot2.func_75215_d((ItemStack)null);
/* 225 */               if (!(slot2 instanceof SlotGhost)) slot2.func_82870_a(player, itemstack3);
/*     */             }
/*     */           }
/*     */           else
/*     */           {
/* 230 */             slot2.func_75209_a(itemstack3.field_77994_a);
/* 231 */             slot2.func_75215_d(slotStack);
/* 232 */             if (!(slot2 instanceof SlotGhost)) slot2.func_82870_a(player, itemstack3);
/*     */           }
/*     */         }
/* 235 */         else if ((!slot2.func_75216_d()) && (slotStack != null) && (slot2.func_75214_a(slotStack)))
/*     */         {
/* 237 */           if (!(slot2 instanceof SlotGhost)) inventoryplayer.func_70299_a(button, (ItemStack)null);
/* 238 */           slot2.func_75215_d(slotStack);
/*     */         }
/*     */       }
/*     */     }
/* 242 */     else if ((mod == 3) && (player.field_71075_bZ.field_75098_d) && (inventoryplayer.func_70445_o() == null) && (slotClicked >= 0))
/*     */     {
/* 244 */       Slot slot2 = (Slot)this.field_75151_b.get(slotClicked);
/*     */       
/* 246 */       if ((slot2 != null) && (slot2.func_75216_d()))
/*     */       {
/* 248 */         ItemStack slotStack = slot2.func_75211_c().func_77946_l();
/* 249 */         slotStack.field_77994_a = slotStack.func_77976_d();
/* 250 */         if (!(slot2 instanceof SlotGhost)) inventoryplayer.func_70437_b(slotStack);
/*     */       }
/*     */     }
/* 253 */     else if (mod == 3) {
/* 254 */       Slot slot2 = (Slot)this.field_75151_b.get(slotClicked);
/* 255 */       if ((slot2 != null) && ((slot2 instanceof SlotGhost)) && (button == 3)) {
/* 256 */         slot2.func_75215_d((ItemStack)null);
/*     */       }
/*     */     }
/* 259 */     else if ((mod == 4) && (inventoryplayer.func_70445_o() == null) && (slotClicked >= 0))
/*     */     {
/* 261 */       Slot slot2 = (Slot)this.field_75151_b.get(slotClicked);
/*     */       
/* 263 */       if ((slot2 != null) && (slot2.func_75216_d()) && ((slot2.func_82869_a(player)) || ((slot2 instanceof SlotGhost))))
/*     */       {
/*     */ 
/* 266 */         ItemStack slotStack = slot2.func_75209_a(button == 0 ? 1 : slot2.func_75211_c().field_77994_a);
/* 267 */         if (!(slot2 instanceof SlotGhost)) {
/* 268 */           slot2.func_82870_a(player, slotStack);
/* 269 */           player.func_71019_a(slotStack, false);
/*     */         }
/*     */         
/*     */       }
/*     */     }
/* 274 */     else if ((mod == 6) && (slotClicked >= 0))
/*     */     {
/* 276 */       Slot slot2 = (Slot)this.field_75151_b.get(slotClicked);
/* 277 */       ItemStack slotStack = inventoryplayer.func_70445_o();
/*     */       
/* 279 */       if ((slotStack != null) && ((slot2 == null) || (!slot2.func_75216_d()) || (!slot2.func_82869_a(player))))
/*     */       {
/* 281 */         int l = button == 0 ? 0 : this.field_75151_b.size() - 1;
/* 282 */         int k1 = button == 0 ? 1 : -1;
/*     */         
/* 284 */         for (int l1 = 0; l1 < 2; l1++)
/*     */         {
/* 286 */           for (int i2 = l; (i2 >= 0) && (i2 < this.field_75151_b.size()) && (slotStack.field_77994_a < slotStack.func_77976_d()); i2 += k1)
/*     */           {
/* 288 */             Slot slot3 = (Slot)this.field_75151_b.get(i2);
/*     */             
/* 290 */             if ((!(slot3 instanceof SlotGhost)) && (!(slot3 instanceof SlotGhostFluid)) && (slot3.func_75216_d()) && (func_94527_a(slot3, slotStack, true)) && (slot3.func_82869_a(player)) && (func_94530_a(slotStack, slot3)) && ((l1 != 0) || (slot3.func_75211_c().field_77994_a != slot3.func_75211_c().func_77976_d())))
/*     */             {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 296 */               int j2 = Math.min(slotStack.func_77976_d() - slotStack.field_77994_a, slot3.func_75211_c().field_77994_a);
/* 297 */               ItemStack itemstack5 = slot3.func_75209_a(j2);
/* 298 */               if (!(slot2 instanceof SlotGhost)) { slotStack.field_77994_a += j2;
/*     */               }
/* 300 */               if (itemstack5.field_77994_a <= 0)
/*     */               {
/* 302 */                 slot3.func_75215_d((ItemStack)null);
/*     */               }
/*     */               
/* 305 */               if (!(slot2 instanceof SlotGhost)) { slot3.func_82870_a(player, itemstack5);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 311 */       func_75142_b();
/*     */     }
/*     */     
/*     */ 
/* 315 */     return itemstack;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/container/ContainerGhostSlots.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */