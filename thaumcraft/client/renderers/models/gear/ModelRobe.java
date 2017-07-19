/*     */ package thaumcraft.client.renderers.models.gear;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.monster.EntitySkeleton;
/*     */ import net.minecraft.entity.monster.EntityZombie;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelRobe
/*     */   extends ModelBiped
/*     */ {
/*     */   ModelRenderer Hood1;
/*     */   ModelRenderer Hood2;
/*     */   ModelRenderer Hood3;
/*     */   ModelRenderer Hood4;
/*     */   ModelRenderer Chestthing;
/*     */   ModelRenderer Mbelt;
/*     */   ModelRenderer MbeltB;
/*     */   ModelRenderer ClothchestL;
/*     */   ModelRenderer ClothchestR;
/*     */   ModelRenderer Book;
/*     */   ModelRenderer Scroll;
/*     */   ModelRenderer BeltR;
/*     */   ModelRenderer Backplate;
/*     */   ModelRenderer MbeltL;
/*     */   ModelRenderer MbeltR;
/*     */   ModelRenderer BeltL;
/*     */   ModelRenderer Chestplate;
/*     */   ModelRenderer ShoulderplateR1;
/*     */   ModelRenderer ShoulderplateR2;
/*     */   ModelRenderer ShoulderplateR3;
/*     */   ModelRenderer ShoulderplateTopR;
/*     */   ModelRenderer RArm1;
/*     */   ModelRenderer RArm2;
/*     */   ModelRenderer RArm3;
/*     */   ModelRenderer LArm1;
/*     */   ModelRenderer LArm2;
/*     */   ModelRenderer LArm3;
/*     */   ModelRenderer ShoulderplateL1;
/*     */   ModelRenderer ShoulderplateL2;
/*     */   ModelRenderer ShoulderplateL3;
/*     */   ModelRenderer ShoulderplateTopL;
/*     */   ModelRenderer ShoulderL;
/*     */   ModelRenderer ShoulderR;
/*     */   ModelRenderer ClothBackR3;
/*     */   ModelRenderer FrontclothR2;
/*     */   ModelRenderer FrontclothR1;
/*     */   ModelRenderer SideclothR2;
/*     */   ModelRenderer SideclothR1;
/*     */   ModelRenderer SideclothR3;
/*     */   ModelRenderer ClothBackR1;
/*     */   ModelRenderer ClothBackR2;
/*     */   ModelRenderer SidepanelR1;
/*     */   ModelRenderer LegpanelR6;
/*     */   ModelRenderer LegpanelR5;
/*     */   ModelRenderer LegpanelR4;
/*     */   ModelRenderer FrontclothL2;
/*     */   ModelRenderer ClothBackL3;
/*     */   ModelRenderer ClothBackL1;
/*     */   ModelRenderer FrontclothL1;
/*     */   ModelRenderer SideclothL2;
/*     */   ModelRenderer SideclothL3;
/*     */   ModelRenderer Focipouch;
/*     */   ModelRenderer SideclothL1;
/*     */   ModelRenderer ClothBackL2;
/*     */   ModelRenderer LegpanelL4;
/*     */   ModelRenderer LegpanelL5;
/*     */   ModelRenderer LegpanelL6;
/*     */   ModelRenderer SidepanelL1;
/*     */   
/*     */   public ModelRobe(float f)
/*     */   {
/*  81 */     super(f, 0.0F, 128, 64);
/*  82 */     this.field_78090_t = 128;
/*  83 */     this.field_78089_u = 64;
/*     */     
/*     */ 
/*  86 */     this.Hood1 = new ModelRenderer(this, 16, 7);
/*  87 */     this.Hood1.func_78789_a(-4.5F, -9.0F, -4.6F, 9, 9, 9);
/*  88 */     this.Hood1.func_78787_b(128, 64);
/*  89 */     setRotation(this.Hood1, 0.0F, 0.0F, 0.0F);
/*     */     
/*  91 */     this.Hood2 = new ModelRenderer(this, 52, 13);
/*  92 */     this.Hood2.func_78789_a(-4.0F, -9.7F, 2.0F, 8, 9, 3);
/*  93 */     this.Hood2.func_78787_b(128, 64);
/*  94 */     setRotation(this.Hood2, -0.2268928F, 0.0F, 0.0F);
/*     */     
/*  96 */     this.Hood3 = new ModelRenderer(this, 52, 14);
/*  97 */     this.Hood3.func_78789_a(-3.5F, -10.0F, 3.5F, 7, 8, 3);
/*  98 */     this.Hood3.func_78787_b(128, 64);
/*  99 */     setRotation(this.Hood3, -0.3490659F, 0.0F, 0.0F);
/*     */     
/* 101 */     this.Hood4 = new ModelRenderer(this, 53, 15);
/* 102 */     this.Hood4.func_78789_a(-3.0F, -10.7F, 3.5F, 6, 7, 3);
/* 103 */     this.Hood4.func_78787_b(128, 64);
/* 104 */     setRotation(this.Hood4, -0.5759587F, 0.0F, 0.0F);
/*     */     
/*     */ 
/* 107 */     this.Chestthing = new ModelRenderer(this, 56, 50);
/* 108 */     this.Chestthing.func_78789_a(-2.5F, 1.0F, -4.0F, 5, 7, 1);
/* 109 */     this.Chestthing.func_78787_b(128, 64);
/* 110 */     setRotation(this.Chestthing, 0.0F, 0.0F, 0.0F);
/*     */     
/* 112 */     this.Mbelt = new ModelRenderer(this, 16, 55);
/* 113 */     this.Mbelt.func_78789_a(-4.0F, 7.0F, -3.0F, 8, 5, 1);
/* 114 */     this.Mbelt.func_78787_b(128, 64);
/* 115 */     setRotation(this.Mbelt, 0.0F, 0.0F, 0.0F);
/*     */     
/* 117 */     this.MbeltB = new ModelRenderer(this, 16, 55);
/* 118 */     this.MbeltB.func_78789_a(-4.0F, 7.0F, -4.0F, 8, 5, 1);
/* 119 */     this.MbeltB.func_78787_b(128, 64);
/* 120 */     setRotation(this.MbeltB, 0.0F, 3.141593F, 0.0F);
/*     */     
/* 122 */     this.ClothchestL = new ModelRenderer(this, 108, 38);
/* 123 */     this.ClothchestL.field_78809_i = true;
/* 124 */     this.ClothchestL.func_78789_a(2.1F, 0.5F, -3.5F, 2, 8, 1);
/* 125 */     this.ClothchestL.func_78787_b(128, 64);
/* 126 */     setRotation(this.ClothchestL, 0.0F, 0.0F, 0.0F);
/*     */     
/* 128 */     this.ClothchestR = new ModelRenderer(this, 108, 38);
/* 129 */     this.ClothchestR.func_78789_a(-4.1F, 0.5F, -3.5F, 2, 8, 1);
/* 130 */     this.ClothchestR.func_78787_b(128, 64);
/* 131 */     setRotation(this.ClothchestR, 0.0F, 0.0F, 0.0F);
/*     */     
/* 133 */     this.Book = new ModelRenderer(this, 81, 16);
/* 134 */     this.Book.func_78789_a(1.0F, 0.0F, 4.0F, 5, 7, 2);
/* 135 */     this.Book.func_78787_b(128, 64);
/* 136 */     setRotation(this.Book, 0.0F, 0.0F, 0.7679449F);
/*     */     
/* 138 */     this.Scroll = new ModelRenderer(this, 78, 25);
/* 139 */     this.Scroll.func_78789_a(-2.0F, 9.5F, 4.0F, 8, 3, 3);
/* 140 */     this.Scroll.func_78787_b(128, 64);
/* 141 */     setRotation(this.Scroll, 0.0F, 0.0F, 0.1919862F);
/*     */     
/* 143 */     this.BeltR = new ModelRenderer(this, 16, 36);
/* 144 */     this.BeltR.func_78789_a(-5.0F, 4.0F, -3.0F, 1, 3, 6);
/* 145 */     this.BeltR.func_78787_b(128, 64);
/* 146 */     setRotation(this.BeltR, 0.0F, 0.0F, 0.0F);
/*     */     
/* 148 */     this.Backplate = new ModelRenderer(this, 36, 45);
/* 149 */     this.Backplate.func_78789_a(-4.0F, 1.0F, 1.9F, 8, 11, 2);
/* 150 */     this.Backplate.func_78787_b(128, 64);
/* 151 */     setRotation(this.Backplate, 0.0F, 0.0F, 0.0F);
/*     */     
/* 153 */     this.MbeltL = new ModelRenderer(this, 16, 36);
/* 154 */     this.MbeltL.func_78789_a(4.0F, 8.0F, -3.0F, 1, 3, 6);
/* 155 */     this.MbeltL.func_78787_b(128, 64);
/* 156 */     setRotation(this.MbeltL, 0.0F, 0.0F, 0.0F);
/*     */     
/* 158 */     this.MbeltR = new ModelRenderer(this, 16, 36);
/* 159 */     this.MbeltR.func_78789_a(-5.0F, 8.0F, -3.0F, 1, 3, 6);
/* 160 */     this.MbeltR.func_78787_b(128, 64);
/* 161 */     setRotation(this.MbeltR, 0.0F, 0.0F, 0.0F);
/*     */     
/* 163 */     this.BeltL = new ModelRenderer(this, 16, 36);
/* 164 */     this.BeltL.func_78789_a(4.0F, 4.0F, -3.0F, 1, 3, 6);
/* 165 */     this.BeltL.func_78787_b(128, 64);
/* 166 */     setRotation(this.BeltL, 0.0F, 0.0F, 0.0F);
/*     */     
/* 168 */     this.Chestplate = new ModelRenderer(this, 16, 25);
/* 169 */     this.Chestplate.func_78789_a(-4.0F, 1.0F, -3.0F, 8, 6, 1);
/* 170 */     this.Chestplate.func_78787_b(128, 64);
/* 171 */     setRotation(this.Chestplate, 0.0F, 0.0F, 0.0F);
/*     */     
/* 173 */     this.ShoulderplateR1 = new ModelRenderer(this, 56, 33);
/* 174 */     this.ShoulderplateR1.func_78789_a(-4.5F, -1.5F, -3.5F, 1, 4, 7);
/*     */     
/* 176 */     this.ShoulderplateR1.func_78787_b(128, 64);
/* 177 */     setRotation(this.ShoulderplateR1, 0.0F, 0.0F, 0.4363323F);
/*     */     
/* 179 */     this.ShoulderplateR2 = new ModelRenderer(this, 40, 33);
/* 180 */     this.ShoulderplateR2.func_78789_a(-3.5F, 1.5F, -3.5F, 1, 3, 7);
/*     */     
/* 182 */     this.ShoulderplateR2.func_78787_b(128, 64);
/* 183 */     setRotation(this.ShoulderplateR2, 0.0F, 0.0F, 0.4363323F);
/*     */     
/* 185 */     this.ShoulderplateR3 = new ModelRenderer(this, 40, 33);
/* 186 */     this.ShoulderplateR3.func_78789_a(-2.5F, 3.5F, -3.5F, 1, 3, 7);
/*     */     
/* 188 */     this.ShoulderplateR3.func_78787_b(128, 64);
/* 189 */     setRotation(this.ShoulderplateR3, 0.0F, 0.0F, 0.4363323F);
/*     */     
/* 191 */     this.ShoulderplateTopR = new ModelRenderer(this, 56, 25);
/* 192 */     this.ShoulderplateTopR.func_78789_a(-5.5F, -2.5F, -3.5F, 2, 1, 7);
/*     */     
/* 194 */     this.ShoulderplateTopR.func_78787_b(128, 64);
/* 195 */     setRotation(this.ShoulderplateTopR, 0.0F, 0.0F, 0.4363323F);
/*     */     
/* 197 */     this.RArm1 = new ModelRenderer(this, 88, 39);
/* 198 */     this.RArm1.func_78789_a(-3.5F, 2.5F, -2.5F, 5, 7, 5);
/*     */     
/* 200 */     this.RArm1.func_78787_b(128, 64);
/* 201 */     setRotation(this.RArm1, 0.0F, 0.0F, 0.0F);
/*     */     
/* 203 */     this.RArm2 = new ModelRenderer(this, 76, 32);
/* 204 */     this.RArm2.func_78789_a(-3.0F, 5.5F, 2.5F, 4, 4, 2);
/*     */     
/* 206 */     this.RArm2.func_78787_b(128, 64);
/* 207 */     setRotation(this.RArm2, 0.0F, 0.0F, 0.0F);
/*     */     
/* 209 */     this.RArm3 = new ModelRenderer(this, 88, 32);
/* 210 */     this.RArm3.func_78789_a(-2.5F, 3.5F, 2.5F, 3, 2, 1);
/*     */     
/* 212 */     this.RArm3.func_78787_b(128, 64);
/* 213 */     setRotation(this.RArm3, 0.0F, 0.0F, 0.0F);
/*     */     
/* 215 */     this.ShoulderplateL1 = new ModelRenderer(this, 56, 33);
/* 216 */     this.ShoulderplateL1.func_78789_a(3.5F, -1.5F, -3.5F, 1, 4, 7);
/*     */     
/* 218 */     this.ShoulderplateL1.func_78787_b(128, 64);
/* 219 */     setRotation(this.ShoulderplateL1, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 221 */     this.ShoulderplateL2 = new ModelRenderer(this, 40, 33);
/* 222 */     this.ShoulderplateL2.func_78789_a(2.5F, 1.5F, -3.5F, 1, 3, 7);
/*     */     
/* 224 */     this.ShoulderplateL2.func_78787_b(128, 64);
/* 225 */     setRotation(this.ShoulderplateL2, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 227 */     this.ShoulderplateL3 = new ModelRenderer(this, 40, 33);
/* 228 */     this.ShoulderplateL3.func_78789_a(1.5F, 3.5F, -3.5F, 1, 3, 7);
/*     */     
/* 230 */     this.ShoulderplateL3.func_78787_b(128, 64);
/* 231 */     setRotation(this.ShoulderplateL3, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 233 */     this.ShoulderplateTopL = new ModelRenderer(this, 56, 25);
/* 234 */     this.ShoulderplateTopL.func_78789_a(3.5F, -2.5F, -3.5F, 2, 1, 7);
/*     */     
/* 236 */     this.ShoulderplateTopL.func_78787_b(128, 64);
/* 237 */     setRotation(this.ShoulderplateTopL, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 239 */     this.ShoulderR = new ModelRenderer(this, 16, 45);
/* 240 */     this.ShoulderR.field_78809_i = true;
/* 241 */     this.ShoulderR.func_78789_a(-3.5F, -2.5F, -2.5F, 5, 5, 5);
/*     */     
/* 243 */     this.ShoulderR.func_78787_b(128, 64);
/* 244 */     setRotation(this.ShoulderR, 0.0F, 0.0F, 0.0F);
/*     */     
/* 246 */     this.LArm1 = new ModelRenderer(this, 88, 39);
/* 247 */     this.LArm1.field_78809_i = true;
/* 248 */     this.LArm1.func_78789_a(-1.5F, 2.5F, -2.5F, 5, 7, 5);
/*     */     
/* 250 */     this.LArm1.func_78787_b(128, 64);
/* 251 */     setRotation(this.LArm1, 0.0F, 0.0F, 0.0F);
/*     */     
/* 253 */     this.LArm2 = new ModelRenderer(this, 76, 32);
/* 254 */     this.LArm2.func_78789_a(-1.0F, 5.5F, 2.5F, 4, 4, 2);
/*     */     
/* 256 */     this.LArm2.func_78787_b(128, 64);
/* 257 */     setRotation(this.LArm2, 0.0F, 0.0F, 0.0F);
/*     */     
/* 259 */     this.LArm3 = new ModelRenderer(this, 88, 32);
/* 260 */     this.LArm3.func_78789_a(-0.5F, 3.5F, 2.5F, 3, 2, 1);
/*     */     
/* 262 */     this.LArm3.func_78787_b(128, 64);
/* 263 */     setRotation(this.LArm3, 0.0F, 0.0F, 0.0F);
/*     */     
/* 265 */     this.ShoulderL = new ModelRenderer(this, 16, 45);
/* 266 */     this.ShoulderL.func_78789_a(-1.5F, -2.5F, -2.5F, 5, 5, 5);
/*     */     
/* 268 */     this.ShoulderL.func_78787_b(128, 64);
/* 269 */     this.ShoulderL.field_78809_i = true;
/* 270 */     setRotation(this.ShoulderL, 0.0F, 0.0F, 0.0F);
/*     */     
/* 272 */     this.FrontclothR1 = new ModelRenderer(this, 108, 38);
/* 273 */     this.FrontclothR1.func_78789_a(0.0F, 0.0F, 0.0F, 3, 8, 1);
/* 274 */     this.FrontclothR1.func_78793_a(-3.0F, 11.0F, -2.9F);
/* 275 */     this.FrontclothR1.func_78787_b(128, 64);
/* 276 */     setRotation(this.FrontclothR1, -0.1047198F, 0.0F, 0.0F);
/*     */     
/* 278 */     this.FrontclothR2 = new ModelRenderer(this, 108, 47);
/* 279 */     this.FrontclothR2.func_78789_a(0.0F, 7.5F, 1.7F, 3, 3, 1);
/* 280 */     this.FrontclothR2.func_78793_a(-3.0F, 11.0F, -2.9F);
/* 281 */     this.FrontclothR2.func_78787_b(128, 64);
/* 282 */     setRotation(this.FrontclothR2, -0.3316126F, 0.0F, 0.0F);
/*     */     
/* 284 */     this.FrontclothL1 = new ModelRenderer(this, 108, 38);
/* 285 */     this.FrontclothL1.field_78809_i = true;
/* 286 */     this.FrontclothL1.func_78789_a(0.0F, 0.0F, 0.0F, 3, 8, 1);
/* 287 */     this.FrontclothL1.func_78793_a(0.0F, 11.0F, -2.9F);
/* 288 */     this.FrontclothL1.func_78787_b(128, 64);
/* 289 */     setRotation(this.FrontclothL1, -0.1047198F, 0.0F, 0.0F);
/*     */     
/* 291 */     this.FrontclothL2 = new ModelRenderer(this, 108, 47);
/* 292 */     this.FrontclothL2.field_78809_i = true;
/* 293 */     this.FrontclothL2.func_78789_a(0.0F, 7.5F, 1.7F, 3, 3, 1);
/* 294 */     this.FrontclothL2.func_78793_a(0.0F, 11.0F, -2.9F);
/* 295 */     this.FrontclothL2.func_78787_b(128, 64);
/* 296 */     setRotation(this.FrontclothL2, -0.3316126F, 0.0F, 0.0F);
/*     */     
/*     */ 
/*     */ 
/* 300 */     this.ClothBackR1 = new ModelRenderer(this, 118, 16);
/* 301 */     this.ClothBackR1.field_78809_i = true;
/* 302 */     this.ClothBackR1.func_78789_a(0.0F, 0.0F, 0.0F, 4, 8, 1);
/* 303 */     this.ClothBackR1.func_78793_a(-4.0F, 11.5F, 2.9F);
/* 304 */     this.ClothBackR1.func_78787_b(128, 64);
/* 305 */     setRotation(this.ClothBackR1, 0.1047198F, 0.0F, 0.0F);
/*     */     
/* 307 */     this.ClothBackR2 = new ModelRenderer(this, 123, 9);
/* 308 */     this.ClothBackR2.func_78789_a(0.0F, 7.8F, -0.9F, 1, 2, 1);
/* 309 */     this.ClothBackR2.func_78793_a(-4.0F, 11.5F, 2.9F);
/* 310 */     this.ClothBackR2.func_78787_b(128, 64);
/* 311 */     setRotation(this.ClothBackR2, 0.2268928F, 0.0F, 0.0F);
/*     */     
/* 313 */     this.ClothBackR3 = new ModelRenderer(this, 120, 12);
/* 314 */     this.ClothBackR3.field_78809_i = true;
/* 315 */     this.ClothBackR3.func_78789_a(1.0F, 7.8F, -0.9F, 3, 3, 1);
/* 316 */     this.ClothBackR3.func_78793_a(-4.0F, 11.5F, 2.9F);
/* 317 */     this.ClothBackR3.func_78787_b(128, 64);
/* 318 */     setRotation(this.ClothBackR3, 0.2268928F, 0.0F, 0.0F);
/*     */     
/*     */ 
/* 321 */     this.ClothBackL1 = new ModelRenderer(this, 118, 16);
/* 322 */     this.ClothBackL1.func_78789_a(0.0F, 0.0F, 0.0F, 4, 8, 1);
/* 323 */     this.ClothBackL1.func_78793_a(0.0F, 11.5F, 2.9F);
/* 324 */     this.ClothBackL1.func_78787_b(128, 64);
/* 325 */     setRotation(this.ClothBackL1, 0.1047198F, 0.0F, 0.0F);
/*     */     
/* 327 */     this.ClothBackL2 = new ModelRenderer(this, 123, 9);
/* 328 */     this.ClothBackL2.field_78809_i = true;
/* 329 */     this.ClothBackL2.func_78789_a(3.0F, 7.8F, -0.9F, 1, 2, 1);
/* 330 */     this.ClothBackL2.func_78793_a(0.0F, 11.5F, 2.9F);
/* 331 */     this.ClothBackL2.func_78787_b(128, 64);
/* 332 */     setRotation(this.ClothBackL2, 0.2268928F, 0.0F, 0.0F);
/*     */     
/* 334 */     this.ClothBackL3 = new ModelRenderer(this, 120, 12);
/* 335 */     this.ClothBackL3.func_78789_a(0.0F, 7.8F, -0.9F, 3, 3, 1);
/* 336 */     this.ClothBackL3.func_78793_a(0.0F, 11.5F, 2.9F);
/* 337 */     this.ClothBackL3.func_78787_b(128, 64);
/* 338 */     setRotation(this.ClothBackL3, 0.2268928F, 0.0F, 0.0F);
/*     */     
/*     */ 
/*     */ 
/* 342 */     this.SideclothL2 = new ModelRenderer(this, 116, 34);
/* 343 */     this.SideclothL2.func_78789_a(0.5F, 5.5F, -2.5F, 1, 3, 5);
/*     */     
/* 345 */     this.SideclothL2.func_78787_b(128, 64);
/* 346 */     setRotation(this.SideclothL2, 0.0F, 0.0F, -0.296706F);
/*     */     
/* 348 */     this.SideclothR1 = new ModelRenderer(this, 116, 42);
/* 349 */     this.SideclothR1.func_78789_a(-2.5F, 0.5F, -2.5F, 1, 5, 5);
/*     */     
/* 351 */     this.SideclothR1.func_78787_b(128, 64);
/* 352 */     setRotation(this.SideclothR1, 0.0F, 0.0F, 0.122173F);
/*     */     
/* 354 */     this.SideclothR2 = new ModelRenderer(this, 116, 34);
/* 355 */     this.SideclothR2.func_78789_a(-1.5F, 5.5F, -2.5F, 1, 3, 5);
/*     */     
/* 357 */     this.SideclothR2.func_78787_b(128, 64);
/* 358 */     setRotation(this.SideclothR2, 0.0F, 0.0F, 0.296706F);
/*     */     
/* 360 */     this.SideclothR3 = new ModelRenderer(this, 116, 1);
/* 361 */     this.SideclothR3.func_78789_a(0.4F, 8.4F, -2.5F, 1, 3, 5);
/*     */     
/* 363 */     this.SideclothR3.func_78787_b(128, 64);
/* 364 */     setRotation(this.SideclothR3, 0.0F, 0.0F, 0.5235988F);
/*     */     
/*     */ 
/*     */ 
/* 368 */     this.SidepanelR1 = new ModelRenderer(this, 116, 25);
/* 369 */     this.SidepanelR1.func_78789_a(-2.5F, 0.5F, -2.5F, 1, 4, 5);
/*     */     
/* 371 */     this.SidepanelR1.func_78787_b(128, 64);
/* 372 */     this.SidepanelR1.field_78809_i = true;
/* 373 */     setRotation(this.SidepanelR1, 0.0F, 0.0F, 0.4363323F);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 379 */     this.LegpanelR6 = new ModelRenderer(this, 82, 38);
/* 380 */     this.LegpanelR6.func_78789_a(-3.0F, 4.5F, -1.5F, 2, 3, 1);
/*     */     
/* 382 */     this.LegpanelR6.func_78787_b(128, 64);
/* 383 */     setRotation(this.LegpanelR6, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 385 */     this.LegpanelR5 = new ModelRenderer(this, 76, 42);
/* 386 */     this.LegpanelR5.func_78789_a(-3.0F, 2.5F, -2.5F, 2, 3, 1);
/*     */     
/* 388 */     this.LegpanelR5.func_78787_b(128, 64);
/* 389 */     setRotation(this.LegpanelR5, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 391 */     this.LegpanelR4 = new ModelRenderer(this, 76, 38);
/* 392 */     this.LegpanelR4.func_78789_a(-3.0F, 0.5F, -3.5F, 2, 3, 1);
/*     */     
/* 394 */     this.LegpanelR4.func_78787_b(128, 64);
/* 395 */     setRotation(this.LegpanelR4, -0.4363323F, 0.0F, 0.0F);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 405 */     this.SideclothL3 = new ModelRenderer(this, 116, 1);
/* 406 */     this.SideclothL3.func_78789_a(-1.4F, 8.4F, -2.5F, 1, 3, 5);
/*     */     
/* 408 */     this.SideclothL3.func_78787_b(128, 64);
/* 409 */     setRotation(this.SideclothL3, 0.0F, 0.0F, -0.5235988F);
/*     */     
/* 411 */     this.Focipouch = new ModelRenderer(this, 100, 20);
/* 412 */     this.Focipouch.func_78789_a(3.5F, 0.5F, -2.5F, 3, 6, 5);
/*     */     
/* 414 */     this.Focipouch.func_78787_b(128, 64);
/* 415 */     setRotation(this.Focipouch, 0.0F, 0.0F, -0.122173F);
/*     */     
/* 417 */     this.SideclothL1 = new ModelRenderer(this, 116, 42);
/* 418 */     this.SideclothL1.func_78789_a(1.5F, 0.5F, -2.5F, 1, 5, 5);
/*     */     
/* 420 */     this.SideclothL1.func_78787_b(128, 64);
/* 421 */     setRotation(this.SideclothL1, 0.0F, 0.0F, -0.122173F);
/*     */     
/*     */ 
/*     */ 
/* 425 */     this.LegpanelL4 = new ModelRenderer(this, 76, 38);
/* 426 */     this.LegpanelL4.field_78809_i = true;
/* 427 */     this.LegpanelL4.func_78789_a(1.0F, 0.5F, -3.5F, 2, 3, 1);
/*     */     
/* 429 */     this.LegpanelL4.func_78787_b(128, 64);
/* 430 */     setRotation(this.LegpanelL4, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 432 */     this.LegpanelL5 = new ModelRenderer(this, 76, 42);
/* 433 */     this.LegpanelL5.field_78809_i = true;
/* 434 */     this.LegpanelL5.func_78789_a(1.0F, 2.5F, -2.5F, 2, 3, 1);
/*     */     
/* 436 */     this.LegpanelL5.func_78787_b(128, 64);
/* 437 */     setRotation(this.LegpanelL5, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 439 */     this.LegpanelL6 = new ModelRenderer(this, 82, 38);
/* 440 */     this.LegpanelL6.field_78809_i = true;
/* 441 */     this.LegpanelL6.func_78789_a(1.0F, 4.5F, -1.5F, 2, 3, 1);
/*     */     
/* 443 */     this.LegpanelL6.func_78787_b(128, 64);
/* 444 */     setRotation(this.LegpanelL6, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 446 */     this.SidepanelL1 = new ModelRenderer(this, 116, 25);
/* 447 */     this.SidepanelL1.func_78789_a(1.5F, 0.5F, -2.5F, 1, 4, 5);
/*     */     
/* 449 */     this.SidepanelL1.func_78787_b(128, 64);
/* 450 */     setRotation(this.SidepanelL1, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 452 */     this.field_78114_d.field_78804_l.clear();
/* 453 */     this.field_78116_c.field_78804_l.clear();
/* 454 */     this.field_78116_c.func_78792_a(this.Hood1);
/* 455 */     this.field_78116_c.func_78792_a(this.Hood2);
/* 456 */     this.field_78116_c.func_78792_a(this.Hood3);
/* 457 */     this.field_78116_c.func_78792_a(this.Hood4);
/*     */     
/* 459 */     this.field_78115_e.field_78804_l.clear();
/* 460 */     this.field_78123_h.field_78804_l.clear();
/* 461 */     this.field_78124_i.field_78804_l.clear();
/* 462 */     this.field_78115_e.func_78792_a(this.Mbelt);
/* 463 */     this.field_78115_e.func_78792_a(this.MbeltB);
/* 464 */     this.field_78115_e.func_78792_a(this.MbeltL);
/* 465 */     this.field_78115_e.func_78792_a(this.MbeltR);
/* 466 */     if (f < 1.0F) {
/* 467 */       this.field_78124_i.func_78792_a(this.Focipouch);
/* 468 */       this.field_78115_e.func_78792_a(this.FrontclothR1);
/* 469 */       this.field_78115_e.func_78792_a(this.FrontclothR2);
/* 470 */       this.field_78115_e.func_78792_a(this.FrontclothL1);
/* 471 */       this.field_78115_e.func_78792_a(this.FrontclothL2);
/*     */       
/* 473 */       this.field_78115_e.func_78792_a(this.ClothBackR1);
/* 474 */       this.field_78115_e.func_78792_a(this.ClothBackR2);
/* 475 */       this.field_78115_e.func_78792_a(this.ClothBackR3);
/*     */       
/* 477 */       this.field_78115_e.func_78792_a(this.ClothBackL1);
/* 478 */       this.field_78115_e.func_78792_a(this.ClothBackL2);
/* 479 */       this.field_78115_e.func_78792_a(this.ClothBackL3);
/*     */     } else {
/* 481 */       this.field_78115_e.func_78792_a(this.Chestplate);
/* 482 */       this.field_78115_e.func_78792_a(this.Chestthing);
/* 483 */       this.field_78115_e.func_78792_a(this.Scroll);
/* 484 */       this.field_78115_e.func_78792_a(this.Backplate);
/* 485 */       this.field_78115_e.func_78792_a(this.Book);
/* 486 */       this.field_78115_e.func_78792_a(this.ClothchestL);
/* 487 */       this.field_78115_e.func_78792_a(this.ClothchestR);
/*     */     }
/*     */     
/* 490 */     this.field_78112_f.field_78804_l.clear();
/* 491 */     this.field_78112_f.func_78792_a(this.ShoulderR);
/* 492 */     this.field_78112_f.func_78792_a(this.RArm1);
/* 493 */     this.field_78112_f.func_78792_a(this.RArm2);
/* 494 */     this.field_78112_f.func_78792_a(this.RArm3);
/* 495 */     this.field_78112_f.func_78792_a(this.ShoulderplateTopR);
/* 496 */     this.field_78112_f.func_78792_a(this.ShoulderplateR1);
/* 497 */     this.field_78112_f.func_78792_a(this.ShoulderplateR2);
/* 498 */     this.field_78112_f.func_78792_a(this.ShoulderplateR3);
/*     */     
/* 500 */     this.field_78113_g.field_78804_l.clear();
/* 501 */     this.field_78113_g.func_78792_a(this.ShoulderL);
/* 502 */     this.field_78113_g.func_78792_a(this.LArm1);
/* 503 */     this.field_78113_g.func_78792_a(this.LArm2);
/* 504 */     this.field_78113_g.func_78792_a(this.LArm3);
/* 505 */     this.field_78113_g.func_78792_a(this.ShoulderplateTopL);
/* 506 */     this.field_78113_g.func_78792_a(this.ShoulderplateL1);
/* 507 */     this.field_78113_g.func_78792_a(this.ShoulderplateL2);
/* 508 */     this.field_78113_g.func_78792_a(this.ShoulderplateL3);
/*     */     
/*     */ 
/*     */ 
/* 512 */     this.field_78123_h.func_78792_a(this.LegpanelR4);
/* 513 */     this.field_78123_h.func_78792_a(this.LegpanelR5);
/* 514 */     this.field_78123_h.func_78792_a(this.LegpanelR6);
/* 515 */     this.field_78123_h.func_78792_a(this.SidepanelR1);
/*     */     
/* 517 */     this.field_78123_h.func_78792_a(this.SideclothR1);
/* 518 */     this.field_78123_h.func_78792_a(this.SideclothR2);
/* 519 */     this.field_78123_h.func_78792_a(this.SideclothR3);
/*     */     
/*     */ 
/*     */ 
/* 523 */     this.field_78124_i.func_78792_a(this.LegpanelL4);
/* 524 */     this.field_78124_i.func_78792_a(this.LegpanelL5);
/* 525 */     this.field_78124_i.func_78792_a(this.LegpanelL6);
/* 526 */     this.field_78124_i.func_78792_a(this.SidepanelL1);
/*     */     
/* 528 */     this.field_78124_i.func_78792_a(this.SideclothL1);
/* 529 */     this.field_78124_i.func_78792_a(this.SideclothL2);
/* 530 */     this.field_78124_i.func_78792_a(this.SideclothL3);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 536 */     if (((entity instanceof EntitySkeleton)) || ((entity instanceof EntityZombie))) {
/* 537 */       setRotationAnglesZombie(f, f1, f2, f3, f4, f5, entity);
/*     */     } else {
/* 539 */       func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */     }
/*     */     
/* 542 */     float a = MathHelper.func_76134_b(f * 0.6662F) * 1.4F * f1;
/* 543 */     float b = MathHelper.func_76134_b(f * 0.6662F + 3.1415927F) * 1.4F * f1;
/* 544 */     float c = Math.min(a, b);
/*     */     
/* 546 */     this.FrontclothR1.field_78795_f = (this.FrontclothL1.field_78795_f = c - 0.1047198F);
/* 547 */     this.FrontclothR2.field_78795_f = (this.FrontclothL2.field_78795_f = c - 0.3316126F);
/*     */     
/* 549 */     this.ClothBackR1.field_78795_f = (this.ClothBackL1.field_78795_f = -c + 0.1047198F);
/* 550 */     this.ClothBackR2.field_78795_f = (this.ClothBackL2.field_78795_f = this.ClothBackR3.field_78795_f = this.ClothBackL3.field_78795_f = -c + 0.2268928F);
/*     */     
/*     */ 
/* 553 */     if (this.field_78091_s) {
/* 554 */       float f6 = 2.0F;
/* 555 */       GL11.glPushMatrix();
/* 556 */       GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
/* 557 */       GL11.glTranslatef(0.0F, 16.0F * f5, 0.0F);
/*     */       
/* 559 */       this.field_78116_c.func_78785_a(f5);
/*     */       
/* 561 */       GL11.glPopMatrix();
/* 562 */       GL11.glPushMatrix();
/* 563 */       GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
/* 564 */       GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
/* 565 */       this.field_78115_e.func_78785_a(f5);
/* 566 */       this.field_78112_f.func_78785_a(f5);
/* 567 */       this.field_78113_g.func_78785_a(f5);
/* 568 */       this.field_78123_h.func_78785_a(f5);
/* 569 */       this.field_78124_i.func_78785_a(f5);
/*     */       
/* 571 */       this.field_78114_d.func_78785_a(f5);
/*     */       
/* 573 */       GL11.glPopMatrix();
/*     */     } else {
/* 575 */       GL11.glPushMatrix();
/* 576 */       GL11.glScalef(1.01F, 1.01F, 1.01F);
/*     */       
/* 578 */       this.field_78116_c.func_78785_a(f5);
/* 579 */       GL11.glPopMatrix();
/* 580 */       this.field_78115_e.func_78785_a(f5);
/* 581 */       this.field_78112_f.func_78785_a(f5);
/* 582 */       this.field_78113_g.func_78785_a(f5);
/* 583 */       this.field_78123_h.func_78785_a(f5);
/* 584 */       this.field_78124_i.func_78785_a(f5);
/* 585 */       this.field_78114_d.func_78785_a(f5);
/*     */     }
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 590 */     model.field_78795_f = x;
/* 591 */     model.field_78796_g = y;
/* 592 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setRotationAnglesZombie(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
/*     */   {
/* 598 */     super.func_78087_a(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
/*     */     
/* 600 */     float f6 = MathHelper.func_76126_a(this.field_78095_p * 3.1415927F);
/* 601 */     float f7 = MathHelper.func_76126_a((1.0F - (1.0F - this.field_78095_p) * (1.0F - this.field_78095_p)) * 3.1415927F);
/*     */     
/*     */ 
/* 604 */     this.field_78112_f.field_78808_h = 0.0F;
/* 605 */     this.field_78113_g.field_78808_h = 0.0F;
/* 606 */     this.field_78112_f.field_78796_g = (-(0.1F - f6 * 0.6F));
/* 607 */     this.field_78113_g.field_78796_g = (0.1F - f6 * 0.6F);
/* 608 */     this.field_78112_f.field_78795_f = -1.5707964F;
/* 609 */     this.field_78113_g.field_78795_f = -1.5707964F;
/* 610 */     this.field_78112_f.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
/* 611 */     this.field_78113_g.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
/* 612 */     this.field_78112_f.field_78808_h += MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
/* 613 */     this.field_78113_g.field_78808_h -= MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
/* 614 */     this.field_78112_f.field_78795_f += MathHelper.func_76126_a(p_78087_3_ * 0.067F) * 0.05F;
/* 615 */     this.field_78113_g.field_78795_f -= MathHelper.func_76126_a(p_78087_3_ * 0.067F) * 0.05F;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/gear/ModelRobe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */