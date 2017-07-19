/*     */ package thaumcraft.client.renderers.models.gear;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.monster.EntitySkeleton;
/*     */ import net.minecraft.entity.monster.EntityZombie;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.common.items.armor.ItemFortressArmor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelFortressArmor
/*     */   extends ModelBiped
/*     */ {
/*     */   ModelRenderer OrnamentL;
/*     */   ModelRenderer OrnamentL2;
/*     */   ModelRenderer OrnamentR;
/*     */   ModelRenderer OrnamentR2;
/*     */   ModelRenderer Helmet;
/*     */   ModelRenderer HelmetR;
/*     */   ModelRenderer HelmetL;
/*     */   ModelRenderer HelmetB;
/*     */   ModelRenderer capsthingy;
/*     */   ModelRenderer flapR;
/*     */   ModelRenderer flapL;
/*     */   ModelRenderer Gemornament;
/*     */   ModelRenderer Gem;
/*     */   ModelRenderer[] Mask;
/*     */   ModelRenderer Goggles;
/*     */   ModelRenderer BeltR;
/*     */   ModelRenderer Mbelt;
/*     */   ModelRenderer MbeltL;
/*     */   ModelRenderer MbeltR;
/*     */   ModelRenderer BeltL;
/*     */   ModelRenderer Chestplate;
/*     */   ModelRenderer Scroll;
/*     */   ModelRenderer Backplate;
/*     */   ModelRenderer Book;
/*     */   ModelRenderer ShoulderR;
/*     */   ModelRenderer GauntletR;
/*     */   ModelRenderer GauntletstrapR1;
/*     */   ModelRenderer GauntletstrapR2;
/*     */   ModelRenderer ShoulderplateRtop;
/*     */   ModelRenderer ShoulderplateR1;
/*     */   ModelRenderer ShoulderplateR2;
/*     */   ModelRenderer ShoulderplateR3;
/*     */   ModelRenderer ShoulderL;
/*     */   ModelRenderer GauntletL;
/*     */   ModelRenderer Gauntletstrapl1;
/*     */   ModelRenderer GauntletstrapL2;
/*     */   ModelRenderer ShoulderplateLtop;
/*     */   ModelRenderer ShoulderplateL1;
/*     */   ModelRenderer ShoulderplateL2;
/*     */   ModelRenderer ShoulderplateL3;
/*     */   ModelRenderer LegpanelR1;
/*     */   ModelRenderer LegpanelR2;
/*     */   ModelRenderer LegpanelR3;
/*     */   ModelRenderer LegpanelR4;
/*     */   ModelRenderer LegpanelR5;
/*     */   ModelRenderer LegpanelR6;
/*     */   ModelRenderer SidepanelR1;
/*     */   ModelRenderer SidepanelR2;
/*     */   ModelRenderer SidepanelR3;
/*     */   ModelRenderer BackpanelR1;
/*     */   ModelRenderer BackpanelR2;
/*     */   ModelRenderer BackpanelR3;
/*     */   ModelRenderer BackpanelL3;
/*     */   ModelRenderer LegpanelL1;
/*     */   ModelRenderer LegpanelL2;
/*     */   ModelRenderer LegpanelL3;
/*     */   ModelRenderer LegpanelL4;
/*     */   ModelRenderer LegpanelL5;
/*     */   ModelRenderer LegpanelL6;
/*     */   ModelRenderer SidepanelL1;
/*     */   ModelRenderer SidepanelL2;
/*     */   ModelRenderer SidepanelL3;
/*     */   ModelRenderer BackpanelL1;
/*     */   ModelRenderer BackpanelL2;
/*     */   
/*     */   public ModelFortressArmor(float f)
/*     */   {
/*  91 */     super(f, 0.0F, 128, 64);
/*  92 */     this.field_78090_t = 128;
/*  93 */     this.field_78089_u = 64;
/*     */     
/*  95 */     this.Mask = new ModelRenderer[3];
/*  96 */     for (int a = 0; a < 3; a++) {
/*  97 */       this.Mask[a] = new ModelRenderer(this, 52 + a * 24, 2);
/*  98 */       this.Mask[a].func_78789_a(-4.5F, -5.0F, -4.6F, 9, 5, 1);
/*  99 */       this.Mask[a].func_78793_a(0.0F, 0.0F, 0.0F);
/* 100 */       this.Mask[a].func_78787_b(128, 64);
/* 101 */       setRotation(this.Mask[a], 0.0F, 0.0F, 0.0F);
/*     */     }
/*     */     
/* 104 */     this.Goggles = new ModelRenderer(this, 100, 18);
/* 105 */     this.Goggles.func_78789_a(-4.5F, -5.0F, -4.25F, 9, 5, 1);
/* 106 */     this.Goggles.func_78793_a(0.0F, 0.0F, 0.0F);
/* 107 */     this.Goggles.func_78787_b(128, 64);
/* 108 */     setRotation(this.Goggles, 0.0F, 0.0F, 0.0F);
/*     */     
/* 110 */     this.OrnamentL = new ModelRenderer(this, 78, 8);
/* 111 */     this.OrnamentL.field_78809_i = true;
/* 112 */     this.OrnamentL.func_78789_a(1.5F, -9.0F, -6.5F, 2, 2, 1);
/* 113 */     this.OrnamentL.func_78793_a(0.0F, 0.0F, 0.0F);
/* 114 */     this.OrnamentL.func_78787_b(128, 64);
/*     */     
/* 116 */     setRotation(this.OrnamentL, -0.1396263F, 0.0F, 0.0F);
/* 117 */     this.OrnamentL2 = new ModelRenderer(this, 78, 8);
/* 118 */     this.OrnamentL2.field_78809_i = true;
/* 119 */     this.OrnamentL2.func_78789_a(3.5F, -10.0F, -6.5F, 1, 2, 1);
/* 120 */     this.OrnamentL2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 121 */     this.OrnamentL2.func_78787_b(128, 64);
/*     */     
/* 123 */     setRotation(this.OrnamentL2, -0.1396263F, 0.0F, 0.0F);
/* 124 */     this.OrnamentR = new ModelRenderer(this, 78, 8);
/* 125 */     this.OrnamentR.func_78789_a(-3.5F, -9.0F, -6.5F, 2, 2, 1);
/* 126 */     this.OrnamentR.func_78793_a(0.0F, 0.0F, 0.0F);
/* 127 */     this.OrnamentR.func_78787_b(128, 64);
/* 128 */     setRotation(this.OrnamentR, -0.1396263F, 0.0F, 0.0F);
/* 129 */     this.OrnamentR2 = new ModelRenderer(this, 78, 8);
/* 130 */     this.OrnamentR2.func_78789_a(-4.5F, -10.0F, -6.5F, 1, 2, 1);
/* 131 */     this.OrnamentR2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 132 */     this.OrnamentR2.func_78787_b(128, 64);
/* 133 */     setRotation(this.OrnamentR2, -0.1396263F, 0.0F, 0.0F);
/* 134 */     this.Helmet = new ModelRenderer(this, 41, 8);
/* 135 */     this.Helmet.func_78789_a(-4.5F, -9.0F, -4.5F, 9, 4, 9);
/* 136 */     this.Helmet.func_78793_a(0.0F, 0.0F, 0.0F);
/* 137 */     this.Helmet.func_78787_b(128, 64);
/* 138 */     setRotation(this.Helmet, 0.0F, 0.0F, 0.0F);
/* 139 */     this.HelmetR = new ModelRenderer(this, 21, 13);
/* 140 */     this.HelmetR.func_78789_a(-6.5F, -3.0F, -4.5F, 1, 5, 9);
/* 141 */     this.HelmetR.func_78793_a(0.0F, 0.0F, 0.0F);
/* 142 */     this.HelmetR.func_78787_b(128, 64);
/* 143 */     setRotation(this.HelmetR, 0.0F, 0.0F, 0.5235988F);
/* 144 */     this.HelmetL = new ModelRenderer(this, 21, 13);
/* 145 */     this.HelmetL.field_78809_i = true;
/* 146 */     this.HelmetL.func_78789_a(5.5F, -3.0F, -4.5F, 1, 5, 9);
/* 147 */     this.HelmetL.func_78793_a(0.0F, 0.0F, 0.0F);
/* 148 */     this.HelmetL.func_78787_b(128, 64);
/*     */     
/* 150 */     setRotation(this.HelmetL, 0.0F, 0.0F, -0.5235988F);
/* 151 */     this.HelmetB = new ModelRenderer(this, 41, 21);
/* 152 */     this.HelmetB.func_78789_a(-4.5F, -3.0F, 5.5F, 9, 5, 1);
/* 153 */     this.HelmetB.func_78793_a(0.0F, 0.0F, 0.0F);
/* 154 */     this.HelmetB.func_78787_b(128, 64);
/* 155 */     setRotation(this.HelmetB, 0.5235988F, 0.0F, 0.0F);
/* 156 */     this.capsthingy = new ModelRenderer(this, 21, 0);
/* 157 */     this.capsthingy.func_78789_a(-4.5F, -6.0F, -6.5F, 9, 1, 2);
/* 158 */     this.capsthingy.func_78793_a(0.0F, 0.0F, 0.0F);
/* 159 */     this.capsthingy.func_78787_b(128, 64);
/* 160 */     setRotation(this.capsthingy, 0.0F, 0.0F, 0.0F);
/*     */     
/* 162 */     this.flapR = new ModelRenderer(this, 59, 10);
/* 163 */     this.flapR.func_78789_a(-10.0F, -2.0F, -1.0F, 3, 3, 1);
/* 164 */     this.flapR.func_78793_a(0.0F, 0.0F, 0.0F);
/* 165 */     this.flapR.func_78787_b(128, 64);
/* 166 */     setRotation(this.flapR, 0.0F, -0.5235988F, 0.5235988F);
/*     */     
/* 168 */     this.flapL = new ModelRenderer(this, 59, 10);
/* 169 */     this.flapL.field_78809_i = true;
/* 170 */     this.flapL.func_78789_a(7.0F, -2.0F, -1.0F, 3, 3, 1);
/* 171 */     this.flapL.func_78793_a(0.0F, 0.0F, 0.0F);
/* 172 */     this.flapL.func_78787_b(128, 64);
/* 173 */     setRotation(this.flapL, 0.0F, 0.5235988F, -0.5235988F);
/*     */     
/* 175 */     this.Gemornament = new ModelRenderer(this, 68, 11);
/* 176 */     this.Gemornament.func_78789_a(-1.5F, -9.0F, -7.0F, 3, 3, 2);
/* 177 */     this.Gemornament.func_78793_a(0.0F, 0.0F, 0.0F);
/* 178 */     this.Gemornament.func_78787_b(128, 64);
/* 179 */     setRotation(this.Gemornament, -0.1396263F, 0.0F, 0.0F);
/* 180 */     this.Gem = new ModelRenderer(this, 72, 8);
/* 181 */     this.Gem.func_78789_a(-1.0F, -8.5F, -7.5F, 2, 2, 1);
/* 182 */     this.Gem.func_78793_a(0.0F, 0.0F, 0.0F);
/* 183 */     this.Gem.func_78787_b(128, 64);
/* 184 */     setRotation(this.Gem, -0.1396263F, 0.0F, 0.0F);
/* 185 */     this.BeltR = new ModelRenderer(this, 76, 44);
/* 186 */     this.BeltR.func_78789_a(-5.0F, 4.0F, -3.0F, 1, 3, 6);
/* 187 */     this.BeltR.func_78793_a(0.0F, 0.0F, 0.0F);
/* 188 */     this.BeltR.func_78787_b(128, 64);
/* 189 */     setRotation(this.BeltR, 0.0F, 0.0F, 0.0F);
/* 190 */     this.Mbelt = new ModelRenderer(this, 56, 55);
/* 191 */     this.Mbelt.func_78789_a(-4.0F, 8.0F, -3.0F, 8, 4, 1);
/* 192 */     this.Mbelt.func_78793_a(0.0F, 0.0F, 0.0F);
/* 193 */     this.Mbelt.func_78787_b(128, 64);
/* 194 */     setRotation(this.Mbelt, 0.0F, 0.0F, 0.0F);
/* 195 */     this.MbeltL = new ModelRenderer(this, 76, 44);
/* 196 */     this.MbeltL.func_78789_a(4.0F, 8.0F, -3.0F, 1, 3, 6);
/* 197 */     this.MbeltL.func_78793_a(0.0F, 0.0F, 0.0F);
/* 198 */     this.MbeltL.func_78787_b(128, 64);
/* 199 */     setRotation(this.MbeltL, 0.0F, 0.0F, 0.0F);
/* 200 */     this.MbeltR = new ModelRenderer(this, 76, 44);
/* 201 */     this.MbeltR.func_78789_a(-5.0F, 8.0F, -3.0F, 1, 3, 6);
/* 202 */     this.MbeltR.func_78793_a(0.0F, 0.0F, 0.0F);
/* 203 */     this.MbeltR.func_78787_b(128, 64);
/* 204 */     setRotation(this.MbeltR, 0.0F, 0.0F, 0.0F);
/* 205 */     this.BeltL = new ModelRenderer(this, 76, 44);
/* 206 */     this.BeltL.func_78789_a(4.0F, 4.0F, -3.0F, 1, 3, 6);
/* 207 */     this.BeltL.func_78793_a(0.0F, 0.0F, 0.0F);
/* 208 */     this.BeltL.func_78787_b(128, 64);
/* 209 */     setRotation(this.BeltL, 0.0F, 0.0F, 0.0F);
/* 210 */     this.Chestplate = new ModelRenderer(this, 56, 45);
/* 211 */     this.Chestplate.func_78789_a(-4.0F, 1.0F, -4.0F, 8, 7, 2);
/* 212 */     this.Chestplate.func_78793_a(0.0F, 0.0F, 0.0F);
/* 213 */     this.Chestplate.func_78787_b(128, 64);
/* 214 */     setRotation(this.Chestplate, 0.0F, 0.0F, 0.0F);
/* 215 */     this.Scroll = new ModelRenderer(this, 34, 27);
/* 216 */     this.Scroll.func_78789_a(-2.0F, 9.5F, 4.0F, 8, 3, 3);
/* 217 */     this.Scroll.func_78793_a(0.0F, 0.0F, 0.0F);
/* 218 */     this.Scroll.func_78787_b(128, 64);
/* 219 */     setRotation(this.Scroll, 0.0F, 0.0F, 0.1919862F);
/* 220 */     this.Backplate = new ModelRenderer(this, 36, 45);
/* 221 */     this.Backplate.func_78789_a(-4.0F, 1.0F, 2.0F, 8, 11, 2);
/* 222 */     this.Backplate.func_78793_a(0.0F, 0.0F, 0.0F);
/* 223 */     this.Backplate.func_78787_b(128, 64);
/* 224 */     setRotation(this.Backplate, 0.0F, 0.0F, 0.0F);
/* 225 */     this.Book = new ModelRenderer(this, 100, 8);
/* 226 */     this.Book.func_78789_a(1.0F, -0.3F, 4.0F, 5, 7, 2);
/* 227 */     this.Book.func_78793_a(0.0F, 0.0F, 0.0F);
/* 228 */     this.Book.func_78787_b(128, 64);
/* 229 */     setRotation(this.Book, 0.0F, 0.0F, 0.7679449F);
/* 230 */     this.ShoulderR = new ModelRenderer(this, 56, 35);
/* 231 */     this.ShoulderR.func_78789_a(-3.5F, -2.5F, -2.5F, 5, 5, 5);
/* 232 */     this.ShoulderR.func_78793_a(0.0F, 0.0F, 0.0F);
/* 233 */     this.ShoulderR.func_78787_b(128, 64);
/* 234 */     setRotation(this.ShoulderR, 0.0F, 0.0F, 0.0F);
/* 235 */     this.GauntletR = new ModelRenderer(this, 100, 26);
/* 236 */     this.GauntletR.func_78789_a(-3.5F, 3.5F, -2.5F, 2, 6, 5);
/* 237 */     this.GauntletR.func_78793_a(0.0F, 0.0F, 0.0F);
/* 238 */     this.GauntletR.func_78787_b(128, 64);
/* 239 */     setRotation(this.GauntletR, 0.0F, 0.0F, 0.0F);
/* 240 */     this.GauntletstrapR1 = new ModelRenderer(this, 84, 31);
/* 241 */     this.GauntletstrapR1.func_78789_a(-1.5F, 3.5F, -2.5F, 3, 1, 5);
/* 242 */     this.GauntletstrapR1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 243 */     this.GauntletstrapR1.func_78787_b(128, 64);
/* 244 */     setRotation(this.GauntletstrapR1, 0.0F, 0.0F, 0.0F);
/* 245 */     this.GauntletstrapR2 = new ModelRenderer(this, 84, 31);
/* 246 */     this.GauntletstrapR2.func_78789_a(-1.5F, 6.5F, -2.5F, 3, 1, 5);
/* 247 */     this.GauntletstrapR2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 248 */     this.GauntletstrapR2.func_78787_b(128, 64);
/* 249 */     setRotation(this.GauntletstrapR2, 0.0F, 0.0F, 0.0F);
/* 250 */     this.ShoulderplateRtop = new ModelRenderer(this, 110, 37);
/* 251 */     this.ShoulderplateRtop.func_78789_a(-5.5F, -2.5F, -3.5F, 2, 1, 7);
/* 252 */     this.ShoulderplateRtop.func_78793_a(0.0F, 0.0F, 0.0F);
/* 253 */     this.ShoulderplateRtop.func_78787_b(128, 64);
/* 254 */     setRotation(this.ShoulderplateRtop, 0.0F, 0.0F, 0.4363323F);
/* 255 */     this.ShoulderplateR1 = new ModelRenderer(this, 110, 45);
/* 256 */     this.ShoulderplateR1.func_78789_a(-4.5F, -1.5F, -3.5F, 1, 4, 7);
/* 257 */     this.ShoulderplateR1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 258 */     this.ShoulderplateR1.func_78787_b(128, 64);
/* 259 */     setRotation(this.ShoulderplateR1, 0.0F, 0.0F, 0.4363323F);
/* 260 */     this.ShoulderplateR2 = new ModelRenderer(this, 94, 45);
/* 261 */     this.ShoulderplateR2.func_78789_a(-3.5F, 1.5F, -3.5F, 1, 3, 7);
/* 262 */     this.ShoulderplateR2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 263 */     this.ShoulderplateR2.func_78787_b(128, 64);
/* 264 */     setRotation(this.ShoulderplateR2, 0.0F, 0.0F, 0.4363323F);
/* 265 */     this.ShoulderplateR3 = new ModelRenderer(this, 94, 45);
/* 266 */     this.ShoulderplateR3.func_78789_a(-2.5F, 3.5F, -3.5F, 1, 3, 7);
/* 267 */     this.ShoulderplateR3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 268 */     this.ShoulderplateR3.func_78787_b(128, 64);
/* 269 */     setRotation(this.ShoulderplateR3, 0.0F, 0.0F, 0.4363323F);
/*     */     
/* 271 */     this.ShoulderL = new ModelRenderer(this, 56, 35);
/* 272 */     this.ShoulderL.field_78809_i = true;
/* 273 */     this.ShoulderL.func_78789_a(-1.5F, -2.5F, -2.5F, 5, 5, 5);
/* 274 */     this.ShoulderL.func_78793_a(0.0F, 0.0F, 0.0F);
/* 275 */     this.ShoulderL.func_78787_b(128, 64);
/* 276 */     setRotation(this.ShoulderL, 0.0F, 0.0F, 0.0F);
/*     */     
/* 278 */     this.GauntletL = new ModelRenderer(this, 114, 26);
/* 279 */     this.GauntletL.func_78789_a(1.5F, 3.5F, -2.5F, 2, 6, 5);
/* 280 */     this.GauntletL.func_78793_a(0.0F, 0.0F, 0.0F);
/* 281 */     this.GauntletL.func_78787_b(128, 64);
/* 282 */     setRotation(this.GauntletL, 0.0F, 0.0F, 0.0F);
/*     */     
/* 284 */     this.Gauntletstrapl1 = new ModelRenderer(this, 84, 31);
/* 285 */     this.Gauntletstrapl1.field_78809_i = true;
/* 286 */     this.Gauntletstrapl1.func_78789_a(-1.5F, 3.5F, -2.5F, 3, 1, 5);
/* 287 */     this.Gauntletstrapl1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 288 */     this.Gauntletstrapl1.func_78787_b(128, 64);
/* 289 */     setRotation(this.Gauntletstrapl1, 0.0F, 0.0F, 0.0F);
/*     */     
/* 291 */     this.GauntletstrapL2 = new ModelRenderer(this, 84, 31);
/* 292 */     this.GauntletstrapL2.field_78809_i = true;
/* 293 */     this.GauntletstrapL2.func_78789_a(-1.5F, 6.5F, -2.5F, 3, 1, 5);
/* 294 */     this.GauntletstrapL2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 295 */     this.GauntletstrapL2.func_78787_b(128, 64);
/* 296 */     setRotation(this.GauntletstrapL2, 0.0F, 0.0F, 0.0F);
/*     */     
/* 298 */     this.ShoulderplateLtop = new ModelRenderer(this, 110, 37);
/* 299 */     this.ShoulderplateLtop.field_78809_i = true;
/* 300 */     this.ShoulderplateLtop.func_78789_a(3.5F, -2.5F, -3.5F, 2, 1, 7);
/* 301 */     this.ShoulderplateLtop.func_78793_a(0.0F, 0.0F, 0.0F);
/* 302 */     this.ShoulderplateLtop.func_78787_b(128, 64);
/* 303 */     setRotation(this.ShoulderplateLtop, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 305 */     this.ShoulderplateL1 = new ModelRenderer(this, 110, 45);
/* 306 */     this.ShoulderplateL1.field_78809_i = true;
/* 307 */     this.ShoulderplateL1.func_78789_a(3.5F, -1.5F, -3.5F, 1, 4, 7);
/* 308 */     this.ShoulderplateL1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 309 */     this.ShoulderplateL1.func_78787_b(128, 64);
/* 310 */     setRotation(this.ShoulderplateL1, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 312 */     this.ShoulderplateL2 = new ModelRenderer(this, 94, 45);
/* 313 */     this.ShoulderplateL2.field_78809_i = true;
/* 314 */     this.ShoulderplateL2.func_78789_a(2.5F, 1.5F, -3.5F, 1, 3, 7);
/* 315 */     this.ShoulderplateL2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 316 */     this.ShoulderplateL2.func_78787_b(128, 64);
/* 317 */     setRotation(this.ShoulderplateL2, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 319 */     this.ShoulderplateL3 = new ModelRenderer(this, 94, 45);
/* 320 */     this.ShoulderplateL3.field_78809_i = true;
/* 321 */     this.ShoulderplateL3.func_78789_a(1.5F, 3.5F, -3.5F, 1, 3, 7);
/* 322 */     this.ShoulderplateL3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 323 */     this.ShoulderplateL3.func_78787_b(128, 64);
/* 324 */     setRotation(this.ShoulderplateL3, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 326 */     this.LegpanelR1 = new ModelRenderer(this, 0, 51);
/* 327 */     this.LegpanelR1.func_78789_a(-1.0F, 0.5F, -3.5F, 3, 4, 1);
/* 328 */     this.LegpanelR1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 329 */     this.LegpanelR1.func_78787_b(128, 64);
/* 330 */     setRotation(this.LegpanelR1, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 332 */     this.LegpanelR2 = new ModelRenderer(this, 8, 51);
/* 333 */     this.LegpanelR2.func_78789_a(-1.0F, 3.5F, -2.5F, 3, 4, 1);
/* 334 */     this.LegpanelR2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 335 */     this.LegpanelR2.func_78787_b(128, 64);
/* 336 */     setRotation(this.LegpanelR2, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 338 */     this.LegpanelR3 = new ModelRenderer(this, 0, 56);
/* 339 */     this.LegpanelR3.func_78789_a(-1.0F, 6.5F, -1.5F, 3, 3, 1);
/* 340 */     this.LegpanelR3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 341 */     this.LegpanelR3.func_78787_b(128, 64);
/* 342 */     setRotation(this.LegpanelR3, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 344 */     this.LegpanelR4 = new ModelRenderer(this, 0, 43);
/* 345 */     this.LegpanelR4.func_78789_a(-3.0F, 0.5F, -3.5F, 2, 3, 1);
/* 346 */     this.LegpanelR4.func_78793_a(0.0F, 0.0F, 0.0F);
/* 347 */     this.LegpanelR4.func_78787_b(128, 64);
/* 348 */     setRotation(this.LegpanelR4, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 350 */     this.LegpanelR5 = new ModelRenderer(this, 0, 47);
/* 351 */     this.LegpanelR5.func_78789_a(-3.0F, 2.5F, -2.5F, 2, 3, 1);
/* 352 */     this.LegpanelR5.func_78793_a(0.0F, 0.0F, 0.0F);
/* 353 */     this.LegpanelR5.func_78787_b(128, 64);
/* 354 */     setRotation(this.LegpanelR5, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 356 */     this.LegpanelR6 = new ModelRenderer(this, 6, 43);
/* 357 */     this.LegpanelR6.func_78789_a(-3.0F, 4.5F, -1.5F, 2, 3, 1);
/* 358 */     this.LegpanelR6.func_78793_a(0.0F, 0.0F, 0.0F);
/* 359 */     this.LegpanelR6.func_78787_b(128, 64);
/* 360 */     setRotation(this.LegpanelR6, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 362 */     this.SidepanelR1 = new ModelRenderer(this, 0, 22);
/* 363 */     this.SidepanelR1.func_78789_a(-2.5F, 0.5F, -2.5F, 1, 4, 5);
/* 364 */     this.SidepanelR1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 365 */     this.SidepanelR1.func_78787_b(128, 64);
/* 366 */     setRotation(this.SidepanelR1, 0.0F, 0.0F, 0.4363323F);
/*     */     
/* 368 */     this.SidepanelR2 = new ModelRenderer(this, 0, 31);
/* 369 */     this.SidepanelR2.func_78789_a(-1.5F, 3.5F, -2.5F, 1, 3, 5);
/* 370 */     this.SidepanelR2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 371 */     this.SidepanelR2.func_78787_b(128, 64);
/* 372 */     setRotation(this.SidepanelR2, 0.0F, 0.0F, 0.4363323F);
/*     */     
/* 374 */     this.SidepanelR3 = new ModelRenderer(this, 12, 31);
/* 375 */     this.SidepanelR3.func_78789_a(-0.5F, 5.5F, -2.5F, 1, 3, 5);
/* 376 */     this.SidepanelR3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 377 */     this.SidepanelR3.func_78787_b(128, 64);
/* 378 */     setRotation(this.SidepanelR3, 0.0F, 0.0F, 0.4363323F);
/*     */     
/* 380 */     this.BackpanelR1 = new ModelRenderer(this, 0, 18);
/* 381 */     this.BackpanelR1.func_78789_a(-3.0F, 0.5F, 2.5F, 5, 3, 1);
/* 382 */     this.BackpanelR1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 383 */     this.BackpanelR1.func_78787_b(128, 64);
/* 384 */     setRotation(this.BackpanelR1, 0.4363323F, 0.0F, 0.0F);
/*     */     
/* 386 */     this.BackpanelR2 = new ModelRenderer(this, 0, 18);
/* 387 */     this.BackpanelR2.func_78789_a(-3.0F, 2.5F, 1.5F, 5, 3, 1);
/* 388 */     this.BackpanelR2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 389 */     this.BackpanelR2.func_78787_b(128, 64);
/* 390 */     setRotation(this.BackpanelR2, 0.4363323F, 0.0F, 0.0F);
/*     */     
/* 392 */     this.BackpanelR3 = new ModelRenderer(this, 0, 18);
/* 393 */     this.BackpanelR3.func_78789_a(-3.0F, 4.5F, 0.5F, 5, 3, 1);
/* 394 */     this.BackpanelR3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 395 */     this.BackpanelR3.func_78787_b(128, 64);
/* 396 */     setRotation(this.BackpanelR3, 0.4363323F, 0.0F, 0.0F);
/*     */     
/* 398 */     this.BackpanelL3 = new ModelRenderer(this, 0, 18);
/* 399 */     this.BackpanelL3.field_78809_i = true;
/* 400 */     this.BackpanelL3.func_78789_a(-2.0F, 4.5F, 0.5F, 5, 3, 1);
/* 401 */     this.BackpanelL3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 402 */     this.BackpanelL3.func_78787_b(128, 64);
/* 403 */     setRotation(this.BackpanelL3, 0.4363323F, 0.0F, 0.0F);
/*     */     
/* 405 */     this.LegpanelL1 = new ModelRenderer(this, 0, 51);
/* 406 */     this.LegpanelL1.field_78809_i = true;
/* 407 */     this.LegpanelL1.func_78789_a(-2.0F, 0.5F, -3.5F, 3, 4, 1);
/* 408 */     this.LegpanelL1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 409 */     this.LegpanelL1.func_78787_b(128, 64);
/* 410 */     setRotation(this.LegpanelL1, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 412 */     this.LegpanelL2 = new ModelRenderer(this, 8, 51);
/* 413 */     this.LegpanelL2.field_78809_i = true;
/* 414 */     this.LegpanelL2.func_78789_a(-2.0F, 3.5F, -2.5F, 3, 4, 1);
/* 415 */     this.LegpanelL2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 416 */     this.LegpanelL2.func_78787_b(128, 64);
/* 417 */     setRotation(this.LegpanelL2, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 419 */     this.LegpanelL3 = new ModelRenderer(this, 0, 56);
/* 420 */     this.LegpanelL3.field_78809_i = true;
/* 421 */     this.LegpanelL3.func_78789_a(-2.0F, 6.5F, -1.5F, 3, 3, 1);
/* 422 */     this.LegpanelL3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 423 */     this.LegpanelL3.func_78787_b(128, 64);
/* 424 */     setRotation(this.LegpanelL3, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 426 */     this.LegpanelL4 = new ModelRenderer(this, 0, 43);
/* 427 */     this.LegpanelL4.field_78809_i = true;
/* 428 */     this.LegpanelL4.func_78789_a(1.0F, 0.5F, -3.5F, 2, 3, 1);
/* 429 */     this.LegpanelL4.func_78793_a(0.0F, 0.0F, 0.0F);
/* 430 */     this.LegpanelL4.func_78787_b(128, 64);
/* 431 */     setRotation(this.LegpanelL4, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 433 */     this.LegpanelL5 = new ModelRenderer(this, 0, 47);
/* 434 */     this.LegpanelL5.field_78809_i = true;
/* 435 */     this.LegpanelL5.func_78789_a(1.0F, 2.5F, -2.5F, 2, 3, 1);
/* 436 */     this.LegpanelL5.func_78793_a(0.0F, 0.0F, 0.0F);
/* 437 */     this.LegpanelL5.func_78787_b(128, 64);
/* 438 */     setRotation(this.LegpanelL5, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 440 */     this.LegpanelL6 = new ModelRenderer(this, 6, 43);
/* 441 */     this.LegpanelL6.field_78809_i = true;
/* 442 */     this.LegpanelL6.func_78789_a(1.0F, 4.5F, -1.5F, 2, 3, 1);
/* 443 */     this.LegpanelL6.func_78793_a(0.0F, 0.0F, 0.0F);
/* 444 */     this.LegpanelL6.func_78787_b(128, 64);
/* 445 */     setRotation(this.LegpanelL6, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 447 */     this.SidepanelL1 = new ModelRenderer(this, 0, 22);
/* 448 */     this.SidepanelL1.field_78809_i = true;
/* 449 */     this.SidepanelL1.func_78789_a(1.5F, 0.5F, -2.5F, 1, 4, 5);
/* 450 */     this.SidepanelL1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 451 */     this.SidepanelL1.func_78787_b(128, 64);
/* 452 */     setRotation(this.SidepanelL1, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 454 */     this.SidepanelL2 = new ModelRenderer(this, 0, 31);
/* 455 */     this.SidepanelL2.field_78809_i = true;
/* 456 */     this.SidepanelL2.func_78789_a(0.5F, 3.5F, -2.5F, 1, 3, 5);
/* 457 */     this.SidepanelL2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 458 */     this.SidepanelL2.func_78787_b(128, 64);
/* 459 */     setRotation(this.SidepanelL2, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 461 */     this.SidepanelL3 = new ModelRenderer(this, 12, 31);
/* 462 */     this.SidepanelL3.field_78809_i = true;
/* 463 */     this.SidepanelL3.func_78789_a(-0.5F, 5.5F, -2.5F, 1, 3, 5);
/* 464 */     this.SidepanelL3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 465 */     this.SidepanelL3.func_78787_b(128, 64);
/* 466 */     setRotation(this.SidepanelL3, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 468 */     this.BackpanelL1 = new ModelRenderer(this, 0, 18);
/* 469 */     this.BackpanelL1.field_78809_i = true;
/* 470 */     this.BackpanelL1.func_78789_a(-2.0F, 0.5F, 2.5F, 5, 3, 1);
/* 471 */     this.BackpanelL1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 472 */     this.BackpanelL1.func_78787_b(128, 64);
/* 473 */     setRotation(this.BackpanelL1, 0.4363323F, 0.0F, 0.0F);
/*     */     
/* 475 */     this.BackpanelL2 = new ModelRenderer(this, 0, 18);
/* 476 */     this.BackpanelL2.field_78809_i = true;
/* 477 */     this.BackpanelL2.func_78789_a(-2.0F, 2.5F, 1.5F, 5, 3, 1);
/* 478 */     this.BackpanelL2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 479 */     this.BackpanelL2.func_78787_b(128, 64);
/* 480 */     setRotation(this.BackpanelL2, 0.4363323F, 0.0F, 0.0F);
/*     */     
/* 482 */     this.field_78114_d.field_78804_l.clear();
/* 483 */     this.field_78116_c.field_78804_l.clear();
/* 484 */     this.field_78116_c.func_78792_a(this.OrnamentL);
/* 485 */     this.field_78116_c.func_78792_a(this.OrnamentL2);
/* 486 */     this.field_78116_c.func_78792_a(this.OrnamentR);
/* 487 */     this.field_78116_c.func_78792_a(this.OrnamentR2);
/* 488 */     this.field_78116_c.func_78792_a(this.Helmet);
/* 489 */     this.field_78116_c.func_78792_a(this.HelmetR);
/* 490 */     this.field_78116_c.func_78792_a(this.HelmetL);
/* 491 */     this.field_78116_c.func_78792_a(this.HelmetB);
/* 492 */     this.field_78116_c.func_78792_a(this.capsthingy);
/* 493 */     this.field_78116_c.func_78792_a(this.flapR);
/* 494 */     this.field_78116_c.func_78792_a(this.flapL);
/* 495 */     this.field_78116_c.func_78792_a(this.Gemornament);
/* 496 */     this.field_78116_c.func_78792_a(this.Gem);
/* 497 */     this.field_78116_c.func_78792_a(this.Goggles);
/* 498 */     this.field_78116_c.func_78792_a(this.Mask[0]);
/* 499 */     this.field_78116_c.func_78792_a(this.Mask[1]);
/* 500 */     this.field_78116_c.func_78792_a(this.Mask[2]);
/*     */     
/* 502 */     this.field_78115_e.field_78804_l.clear();
/* 503 */     if (f < 1.0F) {
/* 504 */       this.field_78115_e.func_78792_a(this.Mbelt);
/* 505 */       this.field_78115_e.func_78792_a(this.MbeltL);
/* 506 */       this.field_78115_e.func_78792_a(this.MbeltR);
/*     */     } else {
/* 508 */       this.field_78115_e.func_78792_a(this.BeltR);
/* 509 */       this.field_78115_e.func_78792_a(this.BeltL);
/* 510 */       this.field_78115_e.func_78792_a(this.Chestplate);
/* 511 */       this.field_78115_e.func_78792_a(this.Scroll);
/* 512 */       this.field_78115_e.func_78792_a(this.Backplate);
/* 513 */       this.field_78115_e.func_78792_a(this.Book);
/*     */     }
/*     */     
/* 516 */     this.field_78112_f.field_78804_l.clear();
/* 517 */     this.field_78112_f.func_78792_a(this.ShoulderR);
/* 518 */     this.field_78112_f.func_78792_a(this.GauntletR);
/* 519 */     this.field_78112_f.func_78792_a(this.GauntletstrapR1);
/* 520 */     this.field_78112_f.func_78792_a(this.GauntletstrapR2);
/* 521 */     this.field_78112_f.func_78792_a(this.ShoulderplateRtop);
/* 522 */     this.field_78112_f.func_78792_a(this.ShoulderplateR1);
/* 523 */     this.field_78112_f.func_78792_a(this.ShoulderplateR2);
/* 524 */     this.field_78112_f.func_78792_a(this.ShoulderplateR3);
/*     */     
/* 526 */     this.field_78113_g.field_78804_l.clear();
/* 527 */     this.field_78113_g.func_78792_a(this.ShoulderL);
/* 528 */     this.field_78113_g.func_78792_a(this.GauntletL);
/* 529 */     this.field_78113_g.func_78792_a(this.Gauntletstrapl1);
/* 530 */     this.field_78113_g.func_78792_a(this.GauntletstrapL2);
/* 531 */     this.field_78113_g.func_78792_a(this.ShoulderplateLtop);
/* 532 */     this.field_78113_g.func_78792_a(this.ShoulderplateL1);
/* 533 */     this.field_78113_g.func_78792_a(this.ShoulderplateL2);
/* 534 */     this.field_78113_g.func_78792_a(this.ShoulderplateL3);
/*     */     
/* 536 */     this.field_78123_h.field_78804_l.clear();
/* 537 */     this.field_78123_h.func_78792_a(this.LegpanelR1);
/* 538 */     this.field_78123_h.func_78792_a(this.LegpanelR2);
/* 539 */     this.field_78123_h.func_78792_a(this.LegpanelR3);
/* 540 */     this.field_78123_h.func_78792_a(this.LegpanelR4);
/* 541 */     this.field_78123_h.func_78792_a(this.LegpanelR5);
/* 542 */     this.field_78123_h.func_78792_a(this.LegpanelR6);
/* 543 */     this.field_78123_h.func_78792_a(this.SidepanelR1);
/* 544 */     this.field_78123_h.func_78792_a(this.SidepanelR2);
/* 545 */     this.field_78123_h.func_78792_a(this.SidepanelR3);
/* 546 */     this.field_78123_h.func_78792_a(this.BackpanelR1);
/* 547 */     this.field_78123_h.func_78792_a(this.BackpanelR2);
/* 548 */     this.field_78123_h.func_78792_a(this.BackpanelR3);
/*     */     
/* 550 */     this.field_78124_i.field_78804_l.clear();
/* 551 */     this.field_78124_i.func_78792_a(this.BackpanelL3);
/* 552 */     this.field_78124_i.func_78792_a(this.LegpanelL1);
/* 553 */     this.field_78124_i.func_78792_a(this.LegpanelL2);
/* 554 */     this.field_78124_i.func_78792_a(this.LegpanelL3);
/* 555 */     this.field_78124_i.func_78792_a(this.LegpanelL4);
/* 556 */     this.field_78124_i.func_78792_a(this.LegpanelL5);
/* 557 */     this.field_78124_i.func_78792_a(this.LegpanelL6);
/* 558 */     this.field_78124_i.func_78792_a(this.SidepanelL1);
/* 559 */     this.field_78124_i.func_78792_a(this.SidepanelL2);
/* 560 */     this.field_78124_i.func_78792_a(this.SidepanelL3);
/* 561 */     this.field_78124_i.func_78792_a(this.BackpanelL1);
/* 562 */     this.field_78124_i.func_78792_a(this.BackpanelL2);
/*     */   }
/*     */   
/*     */ 
/* 566 */   private static HashMap<Integer, Integer> hasSet = new HashMap();
/* 567 */   private static HashMap<Integer, Integer> hasMask = new HashMap();
/* 568 */   private static HashMap<Integer, Boolean> hasGoggles = new HashMap();
/*     */   
/*     */   private void checkSet(Entity entity) {
/* 571 */     if (((entity instanceof EntityLivingBase)) && (entity.field_70173_aa % 20 == 0)) {
/* 572 */       int set = 0;
/* 573 */       for (int a = 1; a < 4; a++) {
/* 574 */         ItemStack piece = ((EntityLivingBase)entity).func_71124_b(a + 1);
/* 575 */         if ((piece != null) && ((piece.func_77973_b() instanceof ItemFortressArmor))) {
/* 576 */           set++;
/* 577 */           if (a == 3) {
/* 578 */             if ((piece.func_77942_o()) && (piece.field_77990_d.func_74764_b("mask"))) {
/* 579 */               hasMask.put(Integer.valueOf(entity.func_145782_y()), Integer.valueOf(piece.field_77990_d.func_74762_e("mask")));
/*     */             } else {
/* 581 */               hasMask.remove(Integer.valueOf(entity.func_145782_y()));
/*     */             }
/* 583 */             if ((piece.func_77942_o()) && (piece.field_77990_d.func_74764_b("goggles"))) {
/* 584 */               hasGoggles.put(Integer.valueOf(entity.func_145782_y()), Boolean.valueOf(true));
/*     */             } else {
/* 586 */               hasGoggles.remove(Integer.valueOf(entity.func_145782_y()));
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 593 */       if (set > 0) {
/* 594 */         hasSet.put(Integer.valueOf(entity.func_145782_y()), Integer.valueOf(set));
/*     */       } else {
/* 596 */         hasSet.remove(Integer.valueOf(entity.func_145782_y()));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 603 */     checkSet(entity);
/* 604 */     int set = hasSet.containsKey(Integer.valueOf(entity.func_145782_y())) ? ((Integer)hasSet.get(Integer.valueOf(entity.func_145782_y()))).intValue() : -1;
/* 605 */     int mask = hasMask.containsKey(Integer.valueOf(entity.func_145782_y())) ? ((Integer)hasMask.get(Integer.valueOf(entity.func_145782_y()))).intValue() : -1;
/*     */     
/* 607 */     this.Goggles.field_78807_k = (!hasGoggles.containsKey(Integer.valueOf(entity.func_145782_y())));
/*     */     
/*     */ 
/* 610 */     for (int a = 0; a < 3; a++) {
/* 611 */       if (mask == a) {
/* 612 */         this.Mask[a].field_78807_k = false;
/*     */       } else {
/* 614 */         this.Mask[a].field_78807_k = true;
/*     */       }
/*     */     }
/*     */     
/* 618 */     this.Scroll.field_78807_k = (set < 3);
/* 619 */     this.Book.field_78807_k = (set < 2);
/* 620 */     this.OrnamentL.field_78807_k = (set < 3);
/* 621 */     this.OrnamentL2.field_78807_k = (set < 3);
/* 622 */     this.OrnamentR.field_78807_k = (set < 3);
/* 623 */     this.OrnamentR2.field_78807_k = (set < 3);
/* 624 */     this.Gemornament.field_78807_k = (set < 3);
/* 625 */     this.Gem.field_78807_k = (set < 3);
/* 626 */     this.flapL.field_78807_k = (set < 2);
/* 627 */     this.flapR.field_78807_k = (set < 2);
/* 628 */     this.ShoulderplateLtop.field_78807_k = (set < 2);
/* 629 */     this.ShoulderplateL1.field_78807_k = (set < 2);
/* 630 */     this.ShoulderplateL2.field_78807_k = (set < 3);
/* 631 */     this.ShoulderplateL3.field_78807_k = (set < 3);
/* 632 */     this.ShoulderplateRtop.field_78807_k = (set < 2);
/* 633 */     this.ShoulderplateR1.field_78807_k = (set < 2);
/* 634 */     this.ShoulderplateR2.field_78807_k = (set < 3);
/* 635 */     this.ShoulderplateR3.field_78807_k = (set < 3);
/* 636 */     this.SidepanelR2.field_78807_k = (set < 2);
/* 637 */     this.SidepanelL2.field_78807_k = (set < 2);
/* 638 */     this.SidepanelR3.field_78807_k = (set < 3);
/* 639 */     this.SidepanelL3.field_78807_k = (set < 3);
/*     */     
/* 641 */     if (((entity instanceof EntitySkeleton)) || ((entity instanceof EntityZombie))) {
/* 642 */       setRotationAnglesZombie(f, f1, f2, f3, f4, f5, entity);
/*     */     } else {
/* 644 */       func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */     }
/*     */     
/* 647 */     if (this.field_78091_s)
/*     */     {
/* 649 */       float f6 = 2.0F;
/* 650 */       GL11.glPushMatrix();
/* 651 */       GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
/* 652 */       GL11.glTranslatef(0.0F, 16.0F * f5, 0.0F);
/* 653 */       this.field_78116_c.func_78785_a(f5);
/* 654 */       GL11.glPopMatrix();
/* 655 */       GL11.glPushMatrix();
/* 656 */       GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
/* 657 */       GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
/* 658 */       this.field_78115_e.func_78785_a(f5);
/* 659 */       this.field_78112_f.func_78785_a(f5);
/* 660 */       this.field_78113_g.func_78785_a(f5);
/* 661 */       this.field_78123_h.func_78785_a(f5);
/* 662 */       this.field_78124_i.func_78785_a(f5);
/*     */       
/* 664 */       this.field_78114_d.func_78785_a(f5);
/*     */       
/* 666 */       GL11.glPopMatrix();
/*     */     }
/*     */     else
/*     */     {
/* 670 */       GL11.glPushMatrix();
/* 671 */       GL11.glScalef(1.01F, 1.01F, 1.01F);
/* 672 */       this.field_78116_c.func_78785_a(f5);
/* 673 */       GL11.glPopMatrix();
/* 674 */       this.field_78115_e.func_78785_a(f5);
/* 675 */       this.field_78112_f.func_78785_a(f5);
/* 676 */       this.field_78113_g.func_78785_a(f5);
/* 677 */       this.field_78123_h.func_78785_a(f5);
/* 678 */       this.field_78124_i.func_78785_a(f5);
/* 679 */       this.field_78114_d.func_78785_a(f5);
/*     */     }
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*     */   {
/* 685 */     model.field_78795_f = x;
/* 686 */     model.field_78796_g = y;
/* 687 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setRotationAnglesZombie(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
/*     */   {
/* 693 */     super.func_78087_a(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
/* 694 */     float f6 = MathHelper.func_76126_a(this.field_78095_p * 3.1415927F);
/* 695 */     float f7 = MathHelper.func_76126_a((1.0F - (1.0F - this.field_78095_p) * (1.0F - this.field_78095_p)) * 3.1415927F);
/* 696 */     this.field_78112_f.field_78808_h = 0.0F;
/* 697 */     this.field_78113_g.field_78808_h = 0.0F;
/* 698 */     this.field_78112_f.field_78796_g = (-(0.1F - f6 * 0.6F));
/* 699 */     this.field_78113_g.field_78796_g = (0.1F - f6 * 0.6F);
/* 700 */     this.field_78112_f.field_78795_f = -1.5707964F;
/* 701 */     this.field_78113_g.field_78795_f = -1.5707964F;
/* 702 */     this.field_78112_f.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
/* 703 */     this.field_78113_g.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
/* 704 */     this.field_78112_f.field_78808_h += MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
/* 705 */     this.field_78113_g.field_78808_h -= MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
/* 706 */     this.field_78112_f.field_78795_f += MathHelper.func_76126_a(p_78087_3_ * 0.067F) * 0.05F;
/* 707 */     this.field_78113_g.field_78795_f -= MathHelper.func_76126_a(p_78087_3_ * 0.067F) * 0.05F;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/gear/ModelFortressArmor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */