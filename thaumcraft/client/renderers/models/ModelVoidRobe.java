/*     */ package thaumcraft.client.renderers.models;
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
/*     */ public class ModelVoidRobe
/*     */   extends ModelBiped
/*     */ {
/*     */   ModelRenderer Hood1;
/*     */   ModelRenderer Hood2;
/*     */   ModelRenderer Hood3;
/*     */   ModelRenderer Hood4;
/*     */   ModelRenderer Chestthing;
/*     */   ModelRenderer Mbelt;
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
/*     */   public ModelVoidRobe(float f)
/*     */   {
/*  80 */     super(f, 0.0F, 128, 64);
/*  81 */     this.field_78090_t = 128;
/*  82 */     this.field_78089_u = 64;
/*     */     
/*     */ 
/*  85 */     this.Hood1 = new ModelRenderer(this, 16, 7);
/*  86 */     this.Hood1.func_78789_a(-4.5F, -9.0F, -4.6F, 9, 9, 9);
/*  87 */     this.Hood1.func_78787_b(128, 64);
/*  88 */     setRotation(this.Hood1, 0.0F, 0.0F, 0.0F);
/*     */     
/*  90 */     this.Hood2 = new ModelRenderer(this, 52, 13);
/*  91 */     this.Hood2.func_78789_a(-4.0F, -9.7F, 2.0F, 8, 9, 3);
/*  92 */     this.Hood2.func_78787_b(128, 64);
/*  93 */     setRotation(this.Hood2, -0.2268928F, 0.0F, 0.0F);
/*     */     
/*  95 */     this.Hood3 = new ModelRenderer(this, 52, 14);
/*  96 */     this.Hood3.func_78789_a(-3.5F, -10.0F, 3.5F, 7, 8, 3);
/*  97 */     this.Hood3.func_78787_b(128, 64);
/*  98 */     setRotation(this.Hood3, -0.3490659F, 0.0F, 0.0F);
/*     */     
/* 100 */     this.Hood4 = new ModelRenderer(this, 53, 15);
/* 101 */     this.Hood4.func_78789_a(-3.0F, -10.7F, 3.5F, 6, 7, 3);
/* 102 */     this.Hood4.func_78787_b(128, 64);
/* 103 */     setRotation(this.Hood4, -0.5759587F, 0.0F, 0.0F);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 113 */     this.Chestthing = new ModelRenderer(this, 56, 50);
/* 114 */     this.Chestthing.func_78789_a(-2.5F, 1.0F, -4.0F, 5, 7, 1);
/* 115 */     this.Chestthing.func_78787_b(128, 64);
/* 116 */     setRotation(this.Chestthing, 0.0F, 0.0F, 0.0F);
/*     */     
/* 118 */     this.Mbelt = new ModelRenderer(this, 16, 55);
/* 119 */     this.Mbelt.func_78789_a(-4.0F, 7.0F, -3.0F, 8, 5, 1);
/* 120 */     this.Mbelt.func_78787_b(128, 64);
/* 121 */     setRotation(this.Mbelt, 0.0F, 0.0F, 0.0F);
/*     */     
/* 123 */     this.ClothchestL = new ModelRenderer(this, 108, 38);
/* 124 */     this.ClothchestL.field_78809_i = true;
/* 125 */     this.ClothchestL.func_78789_a(2.1F, 0.5F, -3.5F, 2, 8, 1);
/* 126 */     this.ClothchestL.func_78787_b(128, 64);
/* 127 */     setRotation(this.ClothchestL, 0.0F, 0.0F, 0.0F);
/*     */     
/* 129 */     this.ClothchestR = new ModelRenderer(this, 108, 38);
/* 130 */     this.ClothchestR.func_78789_a(-4.1F, 0.5F, -3.5F, 2, 8, 1);
/* 131 */     this.ClothchestR.func_78787_b(128, 64);
/* 132 */     setRotation(this.ClothchestR, 0.0F, 0.0F, 0.0F);
/*     */     
/* 134 */     this.Book = new ModelRenderer(this, 81, 16);
/* 135 */     this.Book.func_78789_a(1.0F, 0.0F, 4.0F, 5, 7, 2);
/* 136 */     this.Book.func_78787_b(128, 64);
/* 137 */     setRotation(this.Book, 0.0F, 0.0F, 0.7679449F);
/*     */     
/* 139 */     this.Scroll = new ModelRenderer(this, 78, 25);
/* 140 */     this.Scroll.func_78789_a(-2.0F, 9.5F, 4.0F, 8, 3, 3);
/* 141 */     this.Scroll.func_78787_b(128, 64);
/* 142 */     setRotation(this.Scroll, 0.0F, 0.0F, 0.1919862F);
/*     */     
/* 144 */     this.BeltR = new ModelRenderer(this, 16, 36);
/* 145 */     this.BeltR.func_78789_a(-5.0F, 4.0F, -3.0F, 1, 3, 6);
/* 146 */     this.BeltR.func_78787_b(128, 64);
/* 147 */     setRotation(this.BeltR, 0.0F, 0.0F, 0.0F);
/*     */     
/* 149 */     this.Backplate = new ModelRenderer(this, 36, 45);
/* 150 */     this.Backplate.func_78789_a(-4.0F, 1.0F, 2.0F, 8, 11, 2);
/* 151 */     this.Backplate.func_78787_b(128, 64);
/* 152 */     setRotation(this.Backplate, 0.0F, 0.0F, 0.0F);
/*     */     
/* 154 */     this.MbeltL = new ModelRenderer(this, 16, 36);
/* 155 */     this.MbeltL.func_78789_a(4.0F, 8.0F, -3.0F, 1, 3, 6);
/* 156 */     this.MbeltL.func_78787_b(128, 64);
/* 157 */     setRotation(this.MbeltL, 0.0F, 0.0F, 0.0F);
/*     */     
/* 159 */     this.MbeltR = new ModelRenderer(this, 16, 36);
/* 160 */     this.MbeltR.func_78789_a(-5.0F, 8.0F, -3.0F, 1, 3, 6);
/* 161 */     this.MbeltR.func_78787_b(128, 64);
/* 162 */     setRotation(this.MbeltR, 0.0F, 0.0F, 0.0F);
/*     */     
/* 164 */     this.BeltL = new ModelRenderer(this, 16, 36);
/* 165 */     this.BeltL.func_78789_a(4.0F, 4.0F, -3.0F, 1, 3, 6);
/* 166 */     this.BeltL.func_78787_b(128, 64);
/* 167 */     setRotation(this.BeltL, 0.0F, 0.0F, 0.0F);
/*     */     
/* 169 */     this.Chestplate = new ModelRenderer(this, 16, 25);
/* 170 */     this.Chestplate.func_78789_a(-4.0F, 1.0F, -3.0F, 8, 6, 1);
/* 171 */     this.Chestplate.func_78787_b(128, 64);
/* 172 */     setRotation(this.Chestplate, 0.0F, 0.0F, 0.0F);
/*     */     
/* 174 */     this.ShoulderplateR1 = new ModelRenderer(this, 56, 33);
/* 175 */     this.ShoulderplateR1.func_78789_a(-4.5F, -1.5F, -3.5F, 1, 4, 7);
/*     */     
/* 177 */     this.ShoulderplateR1.func_78787_b(128, 64);
/* 178 */     setRotation(this.ShoulderplateR1, 0.0F, 0.0F, 0.4363323F);
/*     */     
/* 180 */     this.ShoulderplateR2 = new ModelRenderer(this, 40, 33);
/* 181 */     this.ShoulderplateR2.func_78789_a(-3.5F, 1.5F, -3.5F, 1, 3, 7);
/*     */     
/* 183 */     this.ShoulderplateR2.func_78787_b(128, 64);
/* 184 */     setRotation(this.ShoulderplateR2, 0.0F, 0.0F, 0.4363323F);
/*     */     
/* 186 */     this.ShoulderplateR3 = new ModelRenderer(this, 40, 33);
/* 187 */     this.ShoulderplateR3.func_78789_a(-2.5F, 3.5F, -3.5F, 1, 3, 7);
/*     */     
/* 189 */     this.ShoulderplateR3.func_78787_b(128, 64);
/* 190 */     setRotation(this.ShoulderplateR3, 0.0F, 0.0F, 0.4363323F);
/*     */     
/* 192 */     this.ShoulderplateTopR = new ModelRenderer(this, 56, 25);
/* 193 */     this.ShoulderplateTopR.func_78789_a(-5.5F, -2.5F, -3.5F, 2, 1, 7);
/*     */     
/* 195 */     this.ShoulderplateTopR.func_78787_b(128, 64);
/* 196 */     setRotation(this.ShoulderplateTopR, 0.0F, 0.0F, 0.4363323F);
/*     */     
/* 198 */     this.RArm1 = new ModelRenderer(this, 88, 39);
/* 199 */     this.RArm1.func_78789_a(-3.5F, 2.5F, -2.5F, 5, 7, 5);
/*     */     
/* 201 */     this.RArm1.func_78787_b(128, 64);
/* 202 */     setRotation(this.RArm1, 0.0F, 0.0F, 0.0F);
/*     */     
/* 204 */     this.RArm2 = new ModelRenderer(this, 76, 32);
/* 205 */     this.RArm2.func_78789_a(-3.0F, 5.5F, 2.5F, 4, 4, 2);
/*     */     
/* 207 */     this.RArm2.func_78787_b(128, 64);
/* 208 */     setRotation(this.RArm2, 0.0F, 0.0F, 0.0F);
/*     */     
/* 210 */     this.RArm3 = new ModelRenderer(this, 88, 32);
/* 211 */     this.RArm3.func_78789_a(-2.5F, 3.5F, 2.5F, 3, 2, 1);
/*     */     
/* 213 */     this.RArm3.func_78787_b(128, 64);
/* 214 */     setRotation(this.RArm3, 0.0F, 0.0F, 0.0F);
/*     */     
/* 216 */     this.ShoulderplateL1 = new ModelRenderer(this, 56, 33);
/* 217 */     this.ShoulderplateL1.func_78789_a(3.5F, -1.5F, -3.5F, 1, 4, 7);
/*     */     
/* 219 */     this.ShoulderplateL1.func_78787_b(128, 64);
/* 220 */     setRotation(this.ShoulderplateL1, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 222 */     this.ShoulderplateL2 = new ModelRenderer(this, 40, 33);
/* 223 */     this.ShoulderplateL2.func_78789_a(2.5F, 1.5F, -3.5F, 1, 3, 7);
/*     */     
/* 225 */     this.ShoulderplateL2.func_78787_b(128, 64);
/* 226 */     setRotation(this.ShoulderplateL2, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 228 */     this.ShoulderplateL3 = new ModelRenderer(this, 40, 33);
/* 229 */     this.ShoulderplateL3.func_78789_a(1.5F, 3.5F, -3.5F, 1, 3, 7);
/*     */     
/* 231 */     this.ShoulderplateL3.func_78787_b(128, 64);
/* 232 */     setRotation(this.ShoulderplateL3, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 234 */     this.ShoulderplateTopL = new ModelRenderer(this, 56, 25);
/* 235 */     this.ShoulderplateTopL.func_78789_a(3.5F, -2.5F, -3.5F, 2, 1, 7);
/*     */     
/* 237 */     this.ShoulderplateTopL.func_78787_b(128, 64);
/* 238 */     setRotation(this.ShoulderplateTopL, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 240 */     this.ShoulderR = new ModelRenderer(this, 16, 45);
/* 241 */     this.ShoulderR.field_78809_i = true;
/* 242 */     this.ShoulderR.func_78789_a(-3.5F, -2.5F, -2.5F, 5, 5, 5);
/*     */     
/* 244 */     this.ShoulderR.func_78787_b(128, 64);
/* 245 */     setRotation(this.ShoulderR, 0.0F, 0.0F, 0.0F);
/*     */     
/* 247 */     this.LArm1 = new ModelRenderer(this, 88, 39);
/* 248 */     this.LArm1.field_78809_i = true;
/* 249 */     this.LArm1.func_78789_a(-1.5F, 2.5F, -2.5F, 5, 7, 5);
/*     */     
/* 251 */     this.LArm1.func_78787_b(128, 64);
/* 252 */     setRotation(this.LArm1, 0.0F, 0.0F, 0.0F);
/*     */     
/* 254 */     this.LArm2 = new ModelRenderer(this, 76, 32);
/* 255 */     this.LArm2.func_78789_a(-1.0F, 5.5F, 2.5F, 4, 4, 2);
/*     */     
/* 257 */     this.LArm2.func_78787_b(128, 64);
/* 258 */     setRotation(this.LArm2, 0.0F, 0.0F, 0.0F);
/*     */     
/* 260 */     this.LArm3 = new ModelRenderer(this, 88, 32);
/* 261 */     this.LArm3.func_78789_a(-0.5F, 3.5F, 2.5F, 3, 2, 1);
/*     */     
/* 263 */     this.LArm3.func_78787_b(128, 64);
/* 264 */     setRotation(this.LArm3, 0.0F, 0.0F, 0.0F);
/*     */     
/* 266 */     this.ShoulderL = new ModelRenderer(this, 16, 45);
/* 267 */     this.ShoulderL.func_78789_a(-1.5F, -2.5F, -2.5F, 5, 5, 5);
/*     */     
/* 269 */     this.ShoulderL.func_78787_b(128, 64);
/* 270 */     this.ShoulderL.field_78809_i = true;
/* 271 */     setRotation(this.ShoulderL, 0.0F, 0.0F, 0.0F);
/*     */     
/* 273 */     this.FrontclothR1 = new ModelRenderer(this, 108, 38);
/* 274 */     this.FrontclothR1.func_78789_a(0.0F, 0.0F, 0.0F, 3, 8, 1);
/* 275 */     this.FrontclothR1.func_78793_a(-3.0F, 11.0F, -3.0F);
/* 276 */     this.FrontclothR1.func_78787_b(128, 64);
/* 277 */     setRotation(this.FrontclothR1, -0.1047198F, 0.0F, 0.0F);
/*     */     
/* 279 */     this.FrontclothR2 = new ModelRenderer(this, 108, 47);
/* 280 */     this.FrontclothR2.func_78789_a(0.0F, 7.5F, 1.7F, 3, 3, 1);
/* 281 */     this.FrontclothR2.func_78793_a(-3.0F, 11.0F, -3.0F);
/* 282 */     this.FrontclothR2.func_78787_b(128, 64);
/* 283 */     setRotation(this.FrontclothR2, -0.3316126F, 0.0F, 0.0F);
/*     */     
/* 285 */     this.FrontclothL1 = new ModelRenderer(this, 108, 38);
/* 286 */     this.FrontclothL1.field_78809_i = true;
/* 287 */     this.FrontclothL1.func_78789_a(0.0F, 0.0F, 0.0F, 3, 8, 1);
/* 288 */     this.FrontclothL1.func_78793_a(0.0F, 11.0F, -3.0F);
/* 289 */     this.FrontclothL1.func_78787_b(128, 64);
/* 290 */     setRotation(this.FrontclothL1, -0.1047198F, 0.0F, 0.0F);
/*     */     
/* 292 */     this.FrontclothL2 = new ModelRenderer(this, 108, 47);
/* 293 */     this.FrontclothL2.field_78809_i = true;
/* 294 */     this.FrontclothL2.func_78789_a(0.0F, 7.5F, 1.7F, 3, 3, 1);
/* 295 */     this.FrontclothL2.func_78793_a(0.0F, 11.0F, -3.0F);
/* 296 */     this.FrontclothL2.func_78787_b(128, 64);
/* 297 */     setRotation(this.FrontclothL2, -0.3316126F, 0.0F, 0.0F);
/*     */     
/*     */ 
/*     */ 
/* 301 */     this.ClothBackR1 = new ModelRenderer(this, 118, 14);
/* 302 */     this.ClothBackR1.field_78809_i = true;
/* 303 */     this.ClothBackR1.func_78789_a(0.0F, 0.0F, 0.0F, 4, 8, 1);
/* 304 */     this.ClothBackR1.func_78793_a(-4.0F, 11.5F, 3.0F);
/* 305 */     this.ClothBackR1.func_78787_b(128, 64);
/* 306 */     setRotation(this.ClothBackR1, 0.1047198F, 0.0F, 0.0F);
/*     */     
/* 308 */     this.ClothBackR2 = new ModelRenderer(this, 123, 9);
/* 309 */     this.ClothBackR2.func_78789_a(0.0F, 7.8F, -0.9F, 1, 2, 1);
/* 310 */     this.ClothBackR2.func_78793_a(-4.0F, 11.5F, 3.0F);
/* 311 */     this.ClothBackR2.func_78787_b(128, 64);
/* 312 */     setRotation(this.ClothBackR2, 0.2268928F, 0.0F, 0.0F);
/*     */     
/* 314 */     this.ClothBackR3 = new ModelRenderer(this, 120, 12);
/* 315 */     this.ClothBackR3.field_78809_i = true;
/* 316 */     this.ClothBackR3.func_78789_a(1.0F, 7.8F, -0.9F, 3, 3, 1);
/* 317 */     this.ClothBackR3.func_78793_a(-4.0F, 11.5F, 3.0F);
/* 318 */     this.ClothBackR3.func_78787_b(128, 64);
/* 319 */     setRotation(this.ClothBackR3, 0.2268928F, 0.0F, 0.0F);
/*     */     
/*     */ 
/* 322 */     this.ClothBackL1 = new ModelRenderer(this, 118, 14);
/* 323 */     this.ClothBackL1.func_78789_a(0.0F, 0.0F, 0.0F, 4, 8, 1);
/* 324 */     this.ClothBackL1.func_78793_a(0.0F, 11.5F, 3.0F);
/* 325 */     this.ClothBackL1.func_78787_b(128, 64);
/* 326 */     setRotation(this.ClothBackL1, 0.1047198F, 0.0F, 0.0F);
/*     */     
/* 328 */     this.ClothBackL2 = new ModelRenderer(this, 123, 9);
/* 329 */     this.ClothBackL2.field_78809_i = true;
/* 330 */     this.ClothBackL2.func_78789_a(3.0F, 7.8F, -0.9F, 1, 2, 1);
/* 331 */     this.ClothBackL2.func_78793_a(0.0F, 11.5F, 3.0F);
/* 332 */     this.ClothBackL2.func_78787_b(128, 64);
/* 333 */     setRotation(this.ClothBackL2, 0.2268928F, 0.0F, 0.0F);
/*     */     
/* 335 */     this.ClothBackL3 = new ModelRenderer(this, 120, 12);
/* 336 */     this.ClothBackL3.func_78789_a(0.0F, 7.8F, -0.9F, 3, 3, 1);
/* 337 */     this.ClothBackL3.func_78793_a(0.0F, 11.5F, 3.0F);
/* 338 */     this.ClothBackL3.func_78787_b(128, 64);
/* 339 */     setRotation(this.ClothBackL3, 0.2268928F, 0.0F, 0.0F);
/*     */     
/*     */ 
/*     */ 
/* 343 */     this.SideclothL2 = new ModelRenderer(this, 116, 34);
/* 344 */     this.SideclothL2.func_78789_a(0.5F, 5.5F, -2.5F, 1, 3, 5);
/*     */     
/* 346 */     this.SideclothL2.func_78787_b(128, 64);
/* 347 */     setRotation(this.SideclothL2, 0.0F, 0.0F, -0.296706F);
/*     */     
/* 349 */     this.SideclothR1 = new ModelRenderer(this, 116, 42);
/* 350 */     this.SideclothR1.func_78789_a(-2.5F, 0.5F, -2.5F, 1, 5, 5);
/*     */     
/* 352 */     this.SideclothR1.func_78787_b(128, 64);
/* 353 */     setRotation(this.SideclothR1, 0.0F, 0.0F, 0.122173F);
/*     */     
/* 355 */     this.SideclothR2 = new ModelRenderer(this, 116, 34);
/* 356 */     this.SideclothR2.func_78789_a(-1.5F, 5.5F, -2.5F, 1, 3, 5);
/*     */     
/* 358 */     this.SideclothR2.func_78787_b(128, 64);
/* 359 */     setRotation(this.SideclothR2, 0.0F, 0.0F, 0.296706F);
/*     */     
/* 361 */     this.SideclothR3 = new ModelRenderer(this, 116, 1);
/* 362 */     this.SideclothR3.func_78789_a(0.4F, 8.4F, -2.5F, 1, 3, 5);
/*     */     
/* 364 */     this.SideclothR3.func_78787_b(128, 64);
/* 365 */     setRotation(this.SideclothR3, 0.0F, 0.0F, 0.5235988F);
/*     */     
/*     */ 
/*     */ 
/* 369 */     this.SidepanelR1 = new ModelRenderer(this, 116, 25);
/* 370 */     this.SidepanelR1.func_78789_a(-2.5F, 0.5F, -2.5F, 1, 4, 5);
/*     */     
/* 372 */     this.SidepanelR1.func_78787_b(128, 64);
/* 373 */     this.SidepanelR1.field_78809_i = true;
/* 374 */     setRotation(this.SidepanelR1, 0.0F, 0.0F, 0.4363323F);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 380 */     this.LegpanelR6 = new ModelRenderer(this, 82, 38);
/* 381 */     this.LegpanelR6.func_78789_a(-3.0F, 4.5F, -1.5F, 2, 3, 1);
/*     */     
/* 383 */     this.LegpanelR6.func_78787_b(128, 64);
/* 384 */     setRotation(this.LegpanelR6, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 386 */     this.LegpanelR5 = new ModelRenderer(this, 76, 42);
/* 387 */     this.LegpanelR5.func_78789_a(-3.0F, 2.5F, -2.5F, 2, 3, 1);
/*     */     
/* 389 */     this.LegpanelR5.func_78787_b(128, 64);
/* 390 */     setRotation(this.LegpanelR5, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 392 */     this.LegpanelR4 = new ModelRenderer(this, 76, 38);
/* 393 */     this.LegpanelR4.func_78789_a(-3.0F, 0.5F, -3.5F, 2, 3, 1);
/*     */     
/* 395 */     this.LegpanelR4.func_78787_b(128, 64);
/* 396 */     setRotation(this.LegpanelR4, -0.4363323F, 0.0F, 0.0F);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 406 */     this.SideclothL3 = new ModelRenderer(this, 116, 1);
/* 407 */     this.SideclothL3.func_78789_a(-1.4F, 8.4F, -2.5F, 1, 3, 5);
/*     */     
/* 409 */     this.SideclothL3.func_78787_b(128, 64);
/* 410 */     setRotation(this.SideclothL3, 0.0F, 0.0F, -0.5235988F);
/*     */     
/* 412 */     this.Focipouch = new ModelRenderer(this, 100, 20);
/* 413 */     this.Focipouch.func_78789_a(3.5F, 0.5F, -2.5F, 3, 6, 5);
/*     */     
/* 415 */     this.Focipouch.func_78787_b(128, 64);
/* 416 */     setRotation(this.Focipouch, 0.0F, 0.0F, -0.122173F);
/*     */     
/* 418 */     this.SideclothL1 = new ModelRenderer(this, 116, 42);
/* 419 */     this.SideclothL1.func_78789_a(1.5F, 0.5F, -2.5F, 1, 5, 5);
/*     */     
/* 421 */     this.SideclothL1.func_78787_b(128, 64);
/* 422 */     setRotation(this.SideclothL1, 0.0F, 0.0F, -0.122173F);
/*     */     
/*     */ 
/*     */ 
/* 426 */     this.LegpanelL4 = new ModelRenderer(this, 76, 38);
/* 427 */     this.LegpanelL4.field_78809_i = true;
/* 428 */     this.LegpanelL4.func_78789_a(1.0F, 0.5F, -3.5F, 2, 3, 1);
/*     */     
/* 430 */     this.LegpanelL4.func_78787_b(128, 64);
/* 431 */     setRotation(this.LegpanelL4, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 433 */     this.LegpanelL5 = new ModelRenderer(this, 76, 42);
/* 434 */     this.LegpanelL5.field_78809_i = true;
/* 435 */     this.LegpanelL5.func_78789_a(1.0F, 2.5F, -2.5F, 2, 3, 1);
/*     */     
/* 437 */     this.LegpanelL5.func_78787_b(128, 64);
/* 438 */     setRotation(this.LegpanelL5, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 440 */     this.LegpanelL6 = new ModelRenderer(this, 82, 38);
/* 441 */     this.LegpanelL6.field_78809_i = true;
/* 442 */     this.LegpanelL6.func_78789_a(1.0F, 4.5F, -1.5F, 2, 3, 1);
/*     */     
/* 444 */     this.LegpanelL6.func_78787_b(128, 64);
/* 445 */     setRotation(this.LegpanelL6, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 447 */     this.SidepanelL1 = new ModelRenderer(this, 116, 25);
/* 448 */     this.SidepanelL1.func_78789_a(1.5F, 0.5F, -2.5F, 1, 4, 5);
/*     */     
/* 450 */     this.SidepanelL1.func_78787_b(128, 64);
/* 451 */     setRotation(this.SidepanelL1, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 453 */     this.field_78114_d.field_78804_l.clear();
/* 454 */     this.field_78116_c.field_78804_l.clear();
/* 455 */     this.field_78116_c.func_78792_a(this.Hood1);
/* 456 */     this.field_78116_c.func_78792_a(this.Hood2);
/* 457 */     this.field_78116_c.func_78792_a(this.Hood3);
/* 458 */     this.field_78116_c.func_78792_a(this.Hood4);
/*     */     
/* 460 */     this.field_78115_e.field_78804_l.clear();
/* 461 */     this.field_78123_h.field_78804_l.clear();
/* 462 */     this.field_78124_i.field_78804_l.clear();
/* 463 */     if (f < 1.0F) {
/* 464 */       this.field_78115_e.func_78792_a(this.Mbelt);
/* 465 */       this.field_78115_e.func_78792_a(this.MbeltL);
/* 466 */       this.field_78115_e.func_78792_a(this.MbeltR);
/* 467 */       this.field_78124_i.func_78792_a(this.Focipouch);
/*     */     } else {
/* 469 */       this.field_78115_e.func_78792_a(this.BeltR);
/* 470 */       this.field_78115_e.func_78792_a(this.BeltL);
/* 471 */       this.field_78115_e.func_78792_a(this.Chestplate);
/* 472 */       this.field_78115_e.func_78792_a(this.Chestthing);
/* 473 */       this.field_78115_e.func_78792_a(this.Scroll);
/* 474 */       this.field_78115_e.func_78792_a(this.Backplate);
/* 475 */       this.field_78115_e.func_78792_a(this.Book);
/* 476 */       this.field_78115_e.func_78792_a(this.ClothchestL);
/* 477 */       this.field_78115_e.func_78792_a(this.ClothchestR);
/*     */       
/* 479 */       this.field_78115_e.func_78792_a(this.FrontclothR1);
/* 480 */       this.field_78115_e.func_78792_a(this.FrontclothR2);
/* 481 */       this.field_78115_e.func_78792_a(this.FrontclothL1);
/* 482 */       this.field_78115_e.func_78792_a(this.FrontclothL2);
/*     */       
/* 484 */       this.field_78115_e.func_78792_a(this.ClothBackR1);
/* 485 */       this.field_78115_e.func_78792_a(this.ClothBackR2);
/* 486 */       this.field_78115_e.func_78792_a(this.ClothBackR3);
/*     */       
/* 488 */       this.field_78115_e.func_78792_a(this.ClothBackL1);
/* 489 */       this.field_78115_e.func_78792_a(this.ClothBackL2);
/* 490 */       this.field_78115_e.func_78792_a(this.ClothBackL3);
/*     */     }
/*     */     
/* 493 */     this.field_78112_f.field_78804_l.clear();
/* 494 */     this.field_78112_f.func_78792_a(this.ShoulderR);
/* 495 */     this.field_78112_f.func_78792_a(this.RArm1);
/* 496 */     this.field_78112_f.func_78792_a(this.RArm2);
/* 497 */     this.field_78112_f.func_78792_a(this.RArm3);
/* 498 */     this.field_78112_f.func_78792_a(this.ShoulderplateTopR);
/* 499 */     this.field_78112_f.func_78792_a(this.ShoulderplateR1);
/* 500 */     this.field_78112_f.func_78792_a(this.ShoulderplateR2);
/* 501 */     this.field_78112_f.func_78792_a(this.ShoulderplateR3);
/*     */     
/* 503 */     this.field_78113_g.field_78804_l.clear();
/* 504 */     this.field_78113_g.func_78792_a(this.ShoulderL);
/* 505 */     this.field_78113_g.func_78792_a(this.LArm1);
/* 506 */     this.field_78113_g.func_78792_a(this.LArm2);
/* 507 */     this.field_78113_g.func_78792_a(this.LArm3);
/* 508 */     this.field_78113_g.func_78792_a(this.ShoulderplateTopL);
/* 509 */     this.field_78113_g.func_78792_a(this.ShoulderplateL1);
/* 510 */     this.field_78113_g.func_78792_a(this.ShoulderplateL2);
/* 511 */     this.field_78113_g.func_78792_a(this.ShoulderplateL3);
/*     */     
/*     */ 
/*     */ 
/* 515 */     this.field_78123_h.func_78792_a(this.LegpanelR4);
/* 516 */     this.field_78123_h.func_78792_a(this.LegpanelR5);
/* 517 */     this.field_78123_h.func_78792_a(this.LegpanelR6);
/* 518 */     this.field_78123_h.func_78792_a(this.SidepanelR1);
/*     */     
/* 520 */     this.field_78123_h.func_78792_a(this.SideclothR1);
/* 521 */     this.field_78123_h.func_78792_a(this.SideclothR2);
/* 522 */     this.field_78123_h.func_78792_a(this.SideclothR3);
/*     */     
/*     */ 
/*     */ 
/* 526 */     this.field_78124_i.func_78792_a(this.LegpanelL4);
/* 527 */     this.field_78124_i.func_78792_a(this.LegpanelL5);
/* 528 */     this.field_78124_i.func_78792_a(this.LegpanelL6);
/* 529 */     this.field_78124_i.func_78792_a(this.SidepanelL1);
/*     */     
/* 531 */     this.field_78124_i.func_78792_a(this.SideclothL1);
/* 532 */     this.field_78124_i.func_78792_a(this.SideclothL2);
/* 533 */     this.field_78124_i.func_78792_a(this.SideclothL3);
/*     */   }
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 538 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/*     */     
/* 540 */     if (((entity instanceof EntitySkeleton)) || ((entity instanceof EntityZombie))) {
/* 541 */       setRotationAnglesZombie(f, f1, f2, f3, f4, f5, entity);
/*     */     } else {
/* 543 */       func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */     }
/*     */     
/* 546 */     float a = MathHelper.func_76134_b(f * 0.6662F) * 1.4F * f1;
/* 547 */     float b = MathHelper.func_76134_b(f * 0.6662F + 3.1415927F) * 1.4F * f1;
/* 548 */     float c = Math.min(a, b);
/*     */     
/* 550 */     this.FrontclothR1.field_78795_f = (this.FrontclothL1.field_78795_f = c - 0.1047198F);
/* 551 */     this.FrontclothR2.field_78795_f = (this.FrontclothL2.field_78795_f = c - 0.3316126F);
/*     */     
/* 553 */     this.ClothBackR1.field_78795_f = (this.ClothBackL1.field_78795_f = -c + 0.1047198F);
/* 554 */     this.ClothBackR2.field_78795_f = (this.ClothBackL2.field_78795_f = this.ClothBackR3.field_78795_f = this.ClothBackL3.field_78795_f = -c + 0.2268928F);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 560 */     if (this.field_78091_s) {
/* 561 */       float f6 = 2.0F;
/* 562 */       GL11.glPushMatrix();
/* 563 */       GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
/* 564 */       GL11.glTranslatef(0.0F, 16.0F * f5, 0.0F);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 571 */       this.field_78116_c.func_78785_a(f5);
/*     */       
/* 573 */       GL11.glPopMatrix();
/* 574 */       GL11.glPushMatrix();
/* 575 */       GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
/* 576 */       GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
/* 577 */       this.field_78115_e.func_78785_a(f5);
/* 578 */       this.field_78112_f.func_78785_a(f5);
/* 579 */       this.field_78113_g.func_78785_a(f5);
/* 580 */       this.field_78123_h.func_78785_a(f5);
/* 581 */       this.field_78124_i.func_78785_a(f5);
/*     */       
/* 583 */       this.field_78114_d.func_78785_a(f5);
/*     */       
/* 585 */       GL11.glPopMatrix();
/*     */     } else {
/* 587 */       GL11.glPushMatrix();
/* 588 */       GL11.glScalef(1.01F, 1.01F, 1.01F);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 594 */       this.field_78116_c.func_78785_a(f5);
/* 595 */       GL11.glPopMatrix();
/* 596 */       this.field_78115_e.func_78785_a(f5);
/* 597 */       this.field_78112_f.func_78785_a(f5);
/* 598 */       this.field_78113_g.func_78785_a(f5);
/* 599 */       this.field_78123_h.func_78785_a(f5);
/* 600 */       this.field_78124_i.func_78785_a(f5);
/* 601 */       this.field_78114_d.func_78785_a(f5);
/*     */     }
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 606 */     model.field_78795_f = x;
/* 607 */     model.field_78796_g = y;
/* 608 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setRotationAnglesZombie(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
/*     */   {
/* 614 */     super.func_78087_a(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
/*     */     
/* 616 */     float f6 = MathHelper.func_76126_a(this.field_78095_p * 3.1415927F);
/* 617 */     float f7 = MathHelper.func_76126_a((1.0F - (1.0F - this.field_78095_p) * (1.0F - this.field_78095_p)) * 3.1415927F);
/*     */     
/*     */ 
/* 620 */     this.field_78112_f.field_78808_h = 0.0F;
/* 621 */     this.field_78113_g.field_78808_h = 0.0F;
/* 622 */     this.field_78112_f.field_78796_g = (-(0.1F - f6 * 0.6F));
/* 623 */     this.field_78113_g.field_78796_g = (0.1F - f6 * 0.6F);
/* 624 */     this.field_78112_f.field_78795_f = -1.5707964F;
/* 625 */     this.field_78113_g.field_78795_f = -1.5707964F;
/* 626 */     this.field_78112_f.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
/* 627 */     this.field_78113_g.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
/* 628 */     this.field_78112_f.field_78808_h += MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
/* 629 */     this.field_78113_g.field_78808_h -= MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
/* 630 */     this.field_78112_f.field_78795_f += MathHelper.func_76126_a(p_78087_3_ * 0.067F) * 0.05F;
/* 631 */     this.field_78113_g.field_78795_f -= MathHelper.func_76126_a(p_78087_3_ * 0.067F) * 0.05F;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/ModelVoidRobe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */