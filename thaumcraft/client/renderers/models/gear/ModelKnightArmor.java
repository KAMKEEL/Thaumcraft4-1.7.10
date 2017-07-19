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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelKnightArmor
/*     */   extends ModelBiped
/*     */ {
/*     */   ModelRenderer Frontcloth1;
/*     */   ModelRenderer Helmet;
/*     */   ModelRenderer BeltR;
/*     */   ModelRenderer Mbelt;
/*     */   ModelRenderer MbeltL;
/*     */   ModelRenderer MbeltR;
/*     */   ModelRenderer BeltL;
/*     */   ModelRenderer Chestplate;
/*     */   ModelRenderer CloakAtL;
/*     */   ModelRenderer Backplate;
/*     */   ModelRenderer Cloak3;
/*     */   ModelRenderer CloakAtR;
/*     */   ModelRenderer Tabbard;
/*     */   ModelRenderer Cloak1;
/*     */   ModelRenderer Cloak2;
/*     */   ModelRenderer ShoulderR1;
/*     */   ModelRenderer GauntletR;
/*     */   ModelRenderer GauntletstrapR1;
/*     */   ModelRenderer GauntletstrapR2;
/*     */   ModelRenderer ShoulderR;
/*     */   ModelRenderer ShoulderR0;
/*     */   ModelRenderer ShoulderR2;
/*     */   ModelRenderer ShoulderL1;
/*     */   ModelRenderer GauntletL;
/*     */   ModelRenderer GauntletstrapL1;
/*     */   ModelRenderer GauntletstrapL2;
/*     */   ModelRenderer ShoulderL;
/*     */   ModelRenderer ShoulderL0;
/*     */   ModelRenderer ShoulderL2;
/*     */   ModelRenderer SidepanelR3;
/*     */   ModelRenderer SidepanelR2;
/*     */   ModelRenderer SidepanelL2;
/*     */   ModelRenderer SidepanelR0;
/*     */   ModelRenderer SidepanelL0;
/*     */   ModelRenderer SidepanelR1;
/*     */   ModelRenderer SidepanelL3;
/*     */   ModelRenderer Frontcloth2;
/*     */   ModelRenderer SidepanelL1;
/*     */   
/*     */   public ModelKnightArmor(float f)
/*     */   {
/*  66 */     super(f, 0.0F, 128, 64);
/*  67 */     this.field_78090_t = 128;
/*  68 */     this.field_78089_u = 64;
/*     */     
/*     */ 
/*     */ 
/*  72 */     this.Helmet = new ModelRenderer(this, 41, 8);
/*  73 */     this.Helmet.func_78789_a(-4.5F, -9.0F, -4.5F, 9, 9, 9);
/*  74 */     this.Helmet.func_78787_b(128, 64);
/*  75 */     setRotation(this.Helmet, 0.0F, 0.0F, 0.0F);
/*     */     
/*  77 */     this.BeltR = new ModelRenderer(this, 76, 44);
/*  78 */     this.BeltR.func_78789_a(-5.0F, 4.0F, -3.0F, 1, 3, 6);
/*  79 */     this.BeltR.func_78787_b(128, 64);
/*  80 */     setRotation(this.BeltR, 0.0F, 0.0F, 0.0F);
/*     */     
/*  82 */     this.Mbelt = new ModelRenderer(this, 56, 55);
/*  83 */     this.Mbelt.func_78789_a(-4.0F, 8.0F, -3.0F, 8, 4, 1);
/*  84 */     this.Mbelt.func_78787_b(128, 64);
/*  85 */     setRotation(this.Mbelt, 0.0F, 0.0F, 0.0F);
/*     */     
/*  87 */     this.MbeltL = new ModelRenderer(this, 76, 44);
/*  88 */     this.MbeltL.func_78789_a(4.0F, 8.0F, -3.0F, 1, 3, 6);
/*  89 */     this.MbeltL.func_78787_b(128, 64);
/*  90 */     setRotation(this.MbeltL, 0.0F, 0.0F, 0.0F);
/*     */     
/*  92 */     this.MbeltR = new ModelRenderer(this, 76, 44);
/*  93 */     this.MbeltR.func_78789_a(-5.0F, 8.0F, -3.0F, 1, 3, 6);
/*  94 */     this.MbeltR.func_78787_b(128, 64);
/*  95 */     setRotation(this.MbeltR, 0.0F, 0.0F, 0.0F);
/*     */     
/*  97 */     this.BeltL = new ModelRenderer(this, 76, 44);
/*  98 */     this.BeltL.func_78789_a(4.0F, 4.0F, -3.0F, 1, 3, 6);
/*  99 */     this.BeltL.func_78787_b(128, 64);
/* 100 */     setRotation(this.BeltL, 0.0F, 0.0F, 0.0F);
/*     */     
/* 102 */     this.Tabbard = new ModelRenderer(this, 114, 52);
/* 103 */     this.Tabbard.func_78789_a(-3.0F, 1.2F, -3.5F, 6, 10, 1);
/* 104 */     this.Tabbard.func_78787_b(128, 64);
/* 105 */     setRotation(this.Tabbard, 0.0F, 0.0F, 0.0F);
/*     */     
/* 107 */     this.CloakAtL = new ModelRenderer(this, 0, 43);
/* 108 */     this.CloakAtL.func_78789_a(2.5F, 1.0F, 2.0F, 2, 1, 3);
/* 109 */     this.CloakAtL.func_78787_b(128, 64);
/* 110 */     setRotation(this.CloakAtL, 0.1396263F, 0.0F, 0.0F);
/*     */     
/* 112 */     this.Backplate = new ModelRenderer(this, 36, 45);
/* 113 */     this.Backplate.func_78789_a(-4.0F, 1.0F, 2.0F, 8, 11, 2);
/* 114 */     this.Backplate.func_78787_b(128, 64);
/* 115 */     setRotation(this.Backplate, 0.0F, 0.0F, 0.0F);
/*     */     
/* 117 */     this.Cloak1 = new ModelRenderer(this, 0, 47);
/* 118 */     this.Cloak1.func_78789_a(0.0F, 0.0F, 0.0F, 9, 12, 1);
/* 119 */     this.Cloak1.func_78793_a(-4.5F, 1.3F, 4.2F);
/* 120 */     this.Cloak1.func_78787_b(128, 64);
/* 121 */     setRotation(this.Cloak1, 0.1396263F, 0.0F, 0.0F);
/* 122 */     this.Cloak2 = new ModelRenderer(this, 0, 59);
/* 123 */     this.Cloak2.func_78789_a(0.0F, 11.7F, -2.0F, 9, 4, 1);
/* 124 */     this.Cloak2.func_78793_a(-4.5F, 1.3F, 4.2F);
/* 125 */     this.Cloak2.func_78787_b(128, 64);
/* 126 */     setRotation(this.Cloak2, 0.3069452F, 0.0F, 0.0F);
/* 127 */     this.Cloak3 = new ModelRenderer(this, 0, 59);
/* 128 */     this.Cloak3.func_78789_a(0.0F, 15.2F, -4.2F, 9, 4, 1);
/* 129 */     this.Cloak3.func_78793_a(-4.5F, 1.3F, 4.2F);
/* 130 */     this.Cloak3.func_78787_b(128, 64);
/* 131 */     setRotation(this.Cloak3, 0.4465716F, 0.0F, 0.0F);
/*     */     
/* 133 */     this.CloakAtR = new ModelRenderer(this, 0, 43);
/* 134 */     this.CloakAtR.func_78789_a(-4.5F, 1.0F, 2.0F, 2, 1, 3);
/* 135 */     this.CloakAtR.func_78787_b(128, 64);
/* 136 */     setRotation(this.CloakAtR, 0.1396263F, 0.0F, 0.0F);
/*     */     
/* 138 */     this.Chestplate = new ModelRenderer(this, 56, 45);
/* 139 */     this.Chestplate.func_78789_a(-4.0F, 1.0F, -3.0F, 8, 7, 1);
/* 140 */     this.Chestplate.func_78787_b(128, 64);
/* 141 */     setRotation(this.Chestplate, 0.0F, 0.0F, 0.0F);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 147 */     this.ShoulderR1 = new ModelRenderer(this, 0, 19);
/* 148 */     this.ShoulderR1.func_78789_a(-3.3F, 3.5F, -2.5F, 1, 1, 5);
/*     */     
/* 150 */     this.ShoulderR1.func_78787_b(128, 64);
/* 151 */     setRotation(this.ShoulderR1, 0.0F, 0.0F, 0.7853982F);
/*     */     
/* 153 */     this.GauntletR = new ModelRenderer(this, 100, 26);
/* 154 */     this.GauntletR.func_78789_a(-3.5F, 3.5F, -2.5F, 2, 6, 5);
/*     */     
/* 156 */     this.GauntletR.func_78787_b(128, 64);
/* 157 */     this.GauntletR.field_78809_i = true;
/* 158 */     setRotation(this.GauntletR, 0.0F, 0.0F, 0.0F);
/*     */     
/* 160 */     this.GauntletstrapR1 = new ModelRenderer(this, 84, 31);
/* 161 */     this.GauntletstrapR1.func_78789_a(-1.5F, 3.5F, -2.5F, 3, 1, 5);
/*     */     
/* 163 */     this.GauntletstrapR1.func_78787_b(128, 64);
/* 164 */     this.GauntletstrapR1.field_78809_i = true;
/* 165 */     setRotation(this.GauntletstrapR1, 0.0F, 0.0F, 0.0F);
/*     */     
/* 167 */     this.GauntletstrapR2 = new ModelRenderer(this, 84, 31);
/* 168 */     this.GauntletstrapR2.func_78789_a(-1.5F, 6.5F, -2.5F, 3, 1, 5);
/*     */     
/* 170 */     this.GauntletstrapR2.func_78787_b(128, 64);
/* 171 */     this.GauntletstrapR2.field_78809_i = true;
/* 172 */     setRotation(this.GauntletstrapR2, 0.0F, 0.0F, 0.0F);
/*     */     
/* 174 */     this.ShoulderR = new ModelRenderer(this, 56, 35);
/* 175 */     this.ShoulderR.func_78789_a(-3.5F, -2.5F, -2.5F, 5, 5, 5);
/*     */     
/* 177 */     this.ShoulderR.func_78787_b(128, 64);
/* 178 */     this.ShoulderR.field_78809_i = true;
/* 179 */     setRotation(this.ShoulderR, 0.0F, 0.0F, 0.0F);
/*     */     
/* 181 */     this.ShoulderR0 = new ModelRenderer(this, 0, 0);
/* 182 */     this.ShoulderR0.func_78789_a(-4.3F, -1.5F, -3.0F, 3, 5, 6);
/*     */     
/* 184 */     this.ShoulderR0.func_78787_b(128, 64);
/* 185 */     this.ShoulderR0.field_78809_i = true;
/* 186 */     setRotation(this.ShoulderR0, 0.0F, 0.0F, 0.7853982F);
/*     */     
/* 188 */     this.ShoulderR2 = new ModelRenderer(this, 0, 11);
/* 189 */     this.ShoulderR2.func_78789_a(-2.3F, 3.5F, -3.0F, 1, 2, 6);
/*     */     
/* 191 */     this.ShoulderR2.func_78787_b(128, 64);
/* 192 */     this.ShoulderR2.field_78809_i = true;
/* 193 */     setRotation(this.ShoulderR2, 0.0F, 0.0F, 0.7853982F);
/*     */     
/* 195 */     this.ShoulderL1 = new ModelRenderer(this, 0, 19);
/* 196 */     this.ShoulderL1.field_78809_i = true;
/* 197 */     this.ShoulderL1.func_78789_a(2.3F, 3.5F, -2.5F, 1, 1, 5);
/*     */     
/* 199 */     this.ShoulderL1.func_78787_b(128, 64);
/*     */     
/* 201 */     setRotation(this.ShoulderL1, 0.0F, 0.0F, -0.7853982F);
/*     */     
/* 203 */     this.GauntletL = new ModelRenderer(this, 114, 26);
/* 204 */     this.GauntletL.func_78789_a(1.5F, 3.5F, -2.5F, 2, 6, 5);
/*     */     
/* 206 */     this.GauntletL.func_78787_b(128, 64);
/* 207 */     setRotation(this.GauntletL, 0.0F, 0.0F, 0.0F);
/*     */     
/* 209 */     this.GauntletstrapL1 = new ModelRenderer(this, 84, 31);
/* 210 */     this.GauntletstrapL1.field_78809_i = true;
/* 211 */     this.GauntletstrapL1.func_78789_a(-1.5F, 3.5F, -2.5F, 3, 1, 5);
/*     */     
/* 213 */     this.GauntletstrapL1.func_78787_b(128, 64);
/* 214 */     setRotation(this.GauntletstrapL1, 0.0F, 0.0F, 0.0F);
/*     */     
/* 216 */     this.GauntletstrapL2 = new ModelRenderer(this, 84, 31);
/* 217 */     this.GauntletstrapL2.field_78809_i = true;
/* 218 */     this.GauntletstrapL2.func_78789_a(-1.5F, 6.5F, -2.5F, 3, 1, 5);
/*     */     
/* 220 */     this.GauntletstrapL2.func_78787_b(128, 64);
/* 221 */     setRotation(this.GauntletstrapL2, 0.0F, 0.0F, 0.0F);
/*     */     
/* 223 */     this.ShoulderL = new ModelRenderer(this, 56, 35);
/* 224 */     this.ShoulderL.func_78789_a(-1.5F, -2.5F, -2.5F, 5, 5, 5);
/*     */     
/* 226 */     this.ShoulderL.func_78787_b(128, 64);
/* 227 */     setRotation(this.ShoulderL, 0.0F, 0.0F, 0.0F);
/*     */     
/* 229 */     this.ShoulderL0 = new ModelRenderer(this, 0, 0);
/* 230 */     this.ShoulderL0.func_78789_a(1.3F, -1.5F, -3.0F, 3, 5, 6);
/*     */     
/* 232 */     this.ShoulderL0.func_78787_b(128, 64);
/* 233 */     setRotation(this.ShoulderL0, 0.0F, 0.0F, -0.7853982F);
/*     */     
/* 235 */     this.ShoulderL2 = new ModelRenderer(this, 0, 11);
/* 236 */     this.ShoulderL2.func_78789_a(1.3F, 3.5F, -3.0F, 1, 2, 6);
/*     */     
/* 238 */     this.ShoulderL2.func_78787_b(128, 64);
/* 239 */     setRotation(this.ShoulderL2, 0.0F, 0.0F, -0.7853982F);
/*     */     
/* 241 */     this.SidepanelR3 = new ModelRenderer(this, 116, 13);
/* 242 */     this.SidepanelR3.func_78789_a(-3.0F, 2.5F, -2.5F, 1, 4, 5);
/*     */     
/* 244 */     this.SidepanelR3.func_78787_b(128, 64);
/* 245 */     setRotation(this.SidepanelR3, 0.0F, 0.0F, 0.1396263F);
/*     */     
/*     */ 
/* 248 */     this.SidepanelR2 = new ModelRenderer(this, 114, 5);
/* 249 */     this.SidepanelR2.field_78809_i = true;
/* 250 */     this.SidepanelR2.func_78789_a(-2.0F, 2.5F, -2.5F, 2, 3, 5);
/*     */     
/* 252 */     this.SidepanelR2.func_78787_b(128, 64);
/* 253 */     setRotation(this.SidepanelR2, 0.0F, 0.0F, 0.1396263F);
/*     */     
/* 255 */     this.SidepanelL2 = new ModelRenderer(this, 114, 5);
/* 256 */     this.SidepanelL2.func_78789_a(0.0F, 2.5F, -2.5F, 2, 3, 5);
/*     */     
/* 258 */     this.SidepanelL2.func_78787_b(128, 64);
/* 259 */     setRotation(this.SidepanelL2, 0.0F, 0.0F, -0.1396263F);
/*     */     
/* 261 */     this.SidepanelR0 = new ModelRenderer(this, 96, 14);
/* 262 */     this.SidepanelR0.func_78789_a(-3.0F, -0.5F, -2.5F, 5, 3, 5);
/*     */     
/* 264 */     this.SidepanelR0.func_78787_b(128, 64);
/* 265 */     setRotation(this.SidepanelR0, 0.0F, 0.0F, 0.1396263F);
/*     */     
/* 267 */     this.SidepanelL0 = new ModelRenderer(this, 96, 14);
/* 268 */     this.SidepanelL0.func_78789_a(-2.0F, -0.5F, -2.5F, 5, 3, 5);
/*     */     
/* 270 */     this.SidepanelL0.func_78787_b(128, 64);
/* 271 */     setRotation(this.SidepanelL0, 0.0F, 0.0F, -0.1396263F);
/*     */     
/* 273 */     this.SidepanelR1 = new ModelRenderer(this, 96, 7);
/* 274 */     this.SidepanelR1.field_78809_i = true;
/* 275 */     this.SidepanelR1.func_78789_a(0.0F, 2.5F, -2.5F, 2, 2, 5);
/*     */     
/* 277 */     this.SidepanelR1.func_78787_b(128, 64);
/* 278 */     setRotation(this.SidepanelR1, 0.0F, 0.0F, 0.1396263F);
/*     */     
/* 280 */     this.SidepanelL3 = new ModelRenderer(this, 116, 13);
/* 281 */     this.SidepanelL3.func_78789_a(2.0F, 2.5F, -2.5F, 1, 4, 5);
/*     */     
/* 283 */     this.SidepanelL3.func_78787_b(128, 64);
/* 284 */     setRotation(this.SidepanelL3, 0.0F, 0.0F, -0.1396263F);
/*     */     
/* 286 */     this.SidepanelL1 = new ModelRenderer(this, 96, 7);
/* 287 */     this.SidepanelL1.func_78789_a(-2.0F, 2.5F, -2.5F, 2, 2, 5);
/*     */     
/* 289 */     this.SidepanelL1.func_78787_b(128, 64);
/* 290 */     setRotation(this.SidepanelL1, 0.0F, 0.0F, -0.1396263F);
/*     */     
/* 292 */     this.Frontcloth1 = new ModelRenderer(this, 120, 39);
/* 293 */     this.Frontcloth1.func_78789_a(0.0F, 0.0F, 0.0F, 6, 8, 1);
/* 294 */     this.Frontcloth1.func_78793_a(-3.0F, 11.0F, -3.5F);
/* 295 */     this.Frontcloth1.func_78787_b(128, 64);
/* 296 */     setRotation(this.Frontcloth1, -0.1047198F, 0.0F, 0.0F);
/*     */     
/* 298 */     this.Frontcloth2 = new ModelRenderer(this, 100, 37);
/* 299 */     this.Frontcloth2.func_78789_a(0.0F, 7.5F, 1.8F, 6, 3, 1);
/* 300 */     this.Frontcloth2.func_78793_a(-3.0F, 11.0F, -3.5F);
/* 301 */     this.Frontcloth2.func_78787_b(128, 64);
/* 302 */     setRotation(this.Frontcloth2, -0.3316126F, 0.0F, 0.0F);
/*     */     
/* 304 */     this.field_78114_d.field_78804_l.clear();
/* 305 */     this.field_78116_c.field_78804_l.clear();
/* 306 */     this.field_78116_c.func_78792_a(this.Helmet);
/*     */     
/* 308 */     this.field_78115_e.field_78804_l.clear();
/* 309 */     this.field_78123_h.field_78804_l.clear();
/* 310 */     this.field_78124_i.field_78804_l.clear();
/*     */     
/* 312 */     this.field_78115_e.func_78792_a(this.Mbelt);
/* 313 */     this.field_78115_e.func_78792_a(this.MbeltL);
/* 314 */     this.field_78115_e.func_78792_a(this.MbeltR);
/*     */     
/* 316 */     if (f >= 1.0F)
/*     */     {
/*     */ 
/* 319 */       this.field_78115_e.func_78792_a(this.Chestplate);
/* 320 */       this.field_78115_e.func_78792_a(this.Frontcloth1);
/* 321 */       this.field_78115_e.func_78792_a(this.Frontcloth2);
/* 322 */       this.field_78115_e.func_78792_a(this.Tabbard);
/* 323 */       this.field_78115_e.func_78792_a(this.Backplate);
/* 324 */       this.field_78115_e.func_78792_a(this.Cloak1);
/* 325 */       this.field_78115_e.func_78792_a(this.Cloak2);
/* 326 */       this.field_78115_e.func_78792_a(this.Cloak3);
/* 327 */       this.field_78115_e.func_78792_a(this.CloakAtL);
/* 328 */       this.field_78115_e.func_78792_a(this.CloakAtR);
/*     */     }
/*     */     
/* 331 */     this.field_78112_f.field_78804_l.clear();
/* 332 */     this.field_78112_f.func_78792_a(this.ShoulderR);
/* 333 */     this.field_78112_f.func_78792_a(this.ShoulderR0);
/* 334 */     this.field_78112_f.func_78792_a(this.ShoulderR1);
/* 335 */     this.field_78112_f.func_78792_a(this.ShoulderR2);
/* 336 */     this.field_78112_f.func_78792_a(this.GauntletR);
/* 337 */     this.field_78112_f.func_78792_a(this.GauntletstrapR1);
/* 338 */     this.field_78112_f.func_78792_a(this.GauntletstrapR2);
/*     */     
/* 340 */     this.field_78113_g.field_78804_l.clear();
/* 341 */     this.field_78113_g.func_78792_a(this.ShoulderL);
/* 342 */     this.field_78113_g.func_78792_a(this.ShoulderL0);
/* 343 */     this.field_78113_g.func_78792_a(this.ShoulderL1);
/* 344 */     this.field_78113_g.func_78792_a(this.ShoulderL2);
/* 345 */     this.field_78113_g.func_78792_a(this.GauntletL);
/* 346 */     this.field_78113_g.func_78792_a(this.GauntletstrapL1);
/* 347 */     this.field_78113_g.func_78792_a(this.GauntletstrapL2);
/*     */     
/* 349 */     this.field_78123_h.func_78792_a(this.SidepanelR0);
/* 350 */     this.field_78123_h.func_78792_a(this.SidepanelR1);
/* 351 */     this.field_78123_h.func_78792_a(this.SidepanelR2);
/* 352 */     this.field_78123_h.func_78792_a(this.SidepanelR3);
/*     */     
/* 354 */     this.field_78124_i.func_78792_a(this.SidepanelL0);
/* 355 */     this.field_78124_i.func_78792_a(this.SidepanelL1);
/* 356 */     this.field_78124_i.func_78792_a(this.SidepanelL2);
/* 357 */     this.field_78124_i.func_78792_a(this.SidepanelL3);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 363 */     if (((entity instanceof EntitySkeleton)) || ((entity instanceof EntityZombie))) {
/* 364 */       setRotationAnglesZombie(f, f1, f2, f3, f4, f5, entity);
/*     */     } else {
/* 366 */       func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */     }
/*     */     
/* 369 */     float a = MathHelper.func_76134_b(f * 0.6662F) * 1.4F * f1;
/* 370 */     float b = MathHelper.func_76134_b(f * 0.6662F + 3.1415927F) * 1.4F * f1;
/* 371 */     float c = Math.min(a, b);
/*     */     
/* 373 */     this.Frontcloth1.field_78795_f = (c - 0.1047198F);
/* 374 */     this.Frontcloth2.field_78795_f = (c - 0.3316126F);
/*     */     
/* 376 */     this.Cloak1.field_78795_f = (-c / 2.0F + 0.1396263F);
/* 377 */     this.Cloak2.field_78795_f = (-c / 2.0F + 0.3069452F);
/* 378 */     this.Cloak3.field_78795_f = (-c / 2.0F + 0.4465716F);
/*     */     
/* 380 */     if (this.field_78091_s) {
/* 381 */       float f6 = 2.0F;
/* 382 */       GL11.glPushMatrix();
/* 383 */       GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
/* 384 */       GL11.glTranslatef(0.0F, 16.0F * f5, 0.0F);
/* 385 */       this.field_78116_c.func_78785_a(f5);
/* 386 */       GL11.glPopMatrix();
/* 387 */       GL11.glPushMatrix();
/* 388 */       GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
/* 389 */       GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
/* 390 */       this.field_78115_e.func_78785_a(f5);
/* 391 */       this.field_78112_f.func_78785_a(f5);
/* 392 */       this.field_78113_g.func_78785_a(f5);
/* 393 */       this.field_78123_h.func_78785_a(f5);
/* 394 */       this.field_78124_i.func_78785_a(f5);
/* 395 */       this.field_78114_d.func_78785_a(f5);
/* 396 */       GL11.glPopMatrix();
/*     */     } else {
/* 398 */       GL11.glPushMatrix();
/* 399 */       GL11.glScalef(1.01F, 1.01F, 1.01F);
/* 400 */       this.field_78116_c.func_78785_a(f5);
/* 401 */       GL11.glPopMatrix();
/* 402 */       this.field_78115_e.func_78785_a(f5);
/* 403 */       this.field_78112_f.func_78785_a(f5);
/* 404 */       this.field_78113_g.func_78785_a(f5);
/* 405 */       this.field_78123_h.func_78785_a(f5);
/* 406 */       this.field_78124_i.func_78785_a(f5);
/* 407 */       this.field_78114_d.func_78785_a(f5);
/*     */     }
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 412 */     model.field_78795_f = x;
/* 413 */     model.field_78796_g = y;
/* 414 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setRotationAnglesZombie(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
/*     */   {
/* 420 */     super.func_78087_a(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
/*     */     
/* 422 */     float f6 = MathHelper.func_76126_a(this.field_78095_p * 3.1415927F);
/* 423 */     float f7 = MathHelper.func_76126_a((1.0F - (1.0F - this.field_78095_p) * (1.0F - this.field_78095_p)) * 3.1415927F);
/*     */     
/*     */ 
/* 426 */     this.field_78112_f.field_78808_h = 0.0F;
/* 427 */     this.field_78113_g.field_78808_h = 0.0F;
/* 428 */     this.field_78112_f.field_78796_g = (-(0.1F - f6 * 0.6F));
/* 429 */     this.field_78113_g.field_78796_g = (0.1F - f6 * 0.6F);
/* 430 */     this.field_78112_f.field_78795_f = -1.5707964F;
/* 431 */     this.field_78113_g.field_78795_f = -1.5707964F;
/* 432 */     this.field_78112_f.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
/* 433 */     this.field_78113_g.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
/* 434 */     this.field_78112_f.field_78808_h += MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
/* 435 */     this.field_78113_g.field_78808_h -= MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
/* 436 */     this.field_78112_f.field_78795_f += MathHelper.func_76126_a(p_78087_3_ * 0.067F) * 0.05F;
/* 437 */     this.field_78113_g.field_78795_f -= MathHelper.func_76126_a(p_78087_3_ * 0.067F) * 0.05F;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/gear/ModelKnightArmor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */