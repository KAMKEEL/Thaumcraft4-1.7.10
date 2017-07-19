/*     */ package thaumcraft.client.renderers.models.entities;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.common.entities.monster.EntityEldritchGuardian;
/*     */ import thaumcraft.common.entities.monster.boss.EntityEldritchWarden;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelEldritchGuardian
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer BeltR;
/*     */   ModelRenderer Mbelt;
/*     */   ModelRenderer MbeltL;
/*     */   ModelRenderer MbeltR;
/*     */   ModelRenderer BeltL;
/*     */   ModelRenderer Hood4;
/*     */   ModelRenderer Cloak3;
/*     */   ModelRenderer Chestplate;
/*     */   ModelRenderer HoodEye;
/*     */   ModelRenderer Hood1;
/*     */   ModelRenderer Hood2;
/*     */   ModelRenderer Hood3;
/*     */   ModelRenderer Backplate;
/*     */   ModelRenderer Cloak1;
/*     */   ModelRenderer Cloak2;
/*     */   ModelRenderer ShoulderplateTopR;
/*     */   ModelRenderer ShoulderplateR1;
/*     */   ModelRenderer ShoulderplateR2;
/*     */   ModelRenderer ShoulderplateR3;
/*     */   ModelRenderer ShoulderR;
/*     */   ModelRenderer ArmR3;
/*     */   ModelRenderer ArmL1;
/*     */   ModelRenderer ArmL3;
/*     */   ModelRenderer ArmR1;
/*     */   ModelRenderer ArmR2;
/*     */   ModelRenderer ArmL2;
/*     */   ModelRenderer ShoulderL;
/*     */   ModelRenderer ShoulderplateLtop;
/*     */   ModelRenderer ShoulderplateL1;
/*     */   ModelRenderer ShoulderplateL2;
/*     */   ModelRenderer ShoulderplateL3;
/*     */   ModelRenderer LegpanelR4;
/*     */   ModelRenderer LegpanelR5;
/*     */   ModelRenderer LegpanelR6;
/*     */   ModelRenderer SidepanelR1;
/*     */   ModelRenderer BackpanelR1;
/*     */   ModelRenderer BackpanelR2;
/*     */   ModelRenderer BackpanelR3;
/*     */   ModelRenderer BackpanelL3;
/*     */   ModelRenderer LegpanelL4;
/*     */   ModelRenderer LegpanelL5;
/*     */   ModelRenderer LegpanelL6;
/*     */   ModelRenderer SidepanelL1;
/*     */   ModelRenderer SidepanelR4;
/*     */   ModelRenderer BackpanelL1;
/*     */   ModelRenderer BackpanelL2;
/*     */   ModelRenderer LegpanelC1;
/*     */   ModelRenderer LegpanelC2;
/*     */   ModelRenderer LegpanelC3;
/*     */   ModelRenderer SidepanelR3;
/*     */   ModelRenderer SidepanelL4;
/*     */   ModelRenderer SidepanelL3;
/*     */   ModelRenderer SidepanelR2;
/*     */   ModelRenderer SidepanelL2;
/*     */   private float partialTicks;
/*     */   
/*     */   public ModelEldritchGuardian()
/*     */   {
/*  80 */     this.field_78090_t = 128;
/*  81 */     this.field_78089_u = 64;
/*     */     
/*  83 */     this.BeltR = new ModelRenderer(this, 76, 44);
/*  84 */     this.BeltR.func_78789_a(-5.0F, 4.0F, -3.0F, 1, 3, 6);
/*  85 */     this.BeltR.func_78793_a(0.0F, -6.0F, 0.0F);
/*  86 */     this.BeltR.func_78787_b(128, 64);
/*  87 */     this.BeltR.field_78809_i = true;
/*  88 */     setRotation(this.BeltR, 0.0F, 0.0F, 0.0F);
/*  89 */     this.Mbelt = new ModelRenderer(this, 56, 55);
/*  90 */     this.Mbelt.func_78789_a(-4.0F, 8.0F, -3.0F, 8, 4, 1);
/*  91 */     this.Mbelt.func_78793_a(0.0F, -6.0F, 0.0F);
/*  92 */     this.Mbelt.func_78787_b(128, 64);
/*  93 */     this.Mbelt.field_78809_i = true;
/*  94 */     setRotation(this.Mbelt, 0.0F, 0.0F, 0.0F);
/*  95 */     this.MbeltL = new ModelRenderer(this, 76, 44);
/*  96 */     this.MbeltL.func_78789_a(4.0F, 8.0F, -3.0F, 1, 3, 6);
/*  97 */     this.MbeltL.func_78793_a(0.0F, -6.0F, 0.0F);
/*  98 */     this.MbeltL.func_78787_b(128, 64);
/*  99 */     this.MbeltL.field_78809_i = true;
/* 100 */     setRotation(this.MbeltL, 0.0F, 0.0F, 0.0F);
/* 101 */     this.MbeltR = new ModelRenderer(this, 76, 44);
/* 102 */     this.MbeltR.func_78789_a(-5.0F, 8.0F, -3.0F, 1, 3, 6);
/* 103 */     this.MbeltR.func_78793_a(0.0F, -6.0F, 0.0F);
/* 104 */     this.MbeltR.func_78787_b(128, 64);
/* 105 */     this.MbeltR.field_78809_i = true;
/* 106 */     setRotation(this.MbeltR, 0.0F, 0.0F, 0.0F);
/* 107 */     this.BeltL = new ModelRenderer(this, 76, 44);
/* 108 */     this.BeltL.func_78789_a(4.0F, 4.0F, -3.0F, 1, 3, 6);
/* 109 */     this.BeltL.func_78793_a(0.0F, -6.0F, 0.0F);
/* 110 */     this.BeltL.func_78787_b(128, 64);
/* 111 */     this.BeltL.field_78809_i = true;
/* 112 */     setRotation(this.BeltL, 0.0F, 0.0F, 0.0F);
/*     */     
/*     */ 
/* 115 */     this.Chestplate = new ModelRenderer(this, 56, 45);
/* 116 */     this.Chestplate.func_78789_a(-4.0F, 1.0F, -4.0F, 8, 7, 2);
/* 117 */     this.Chestplate.func_78793_a(0.0F, -6.0F, 0.0F);
/* 118 */     this.Chestplate.func_78787_b(128, 64);
/* 119 */     this.Chestplate.field_78809_i = true;
/* 120 */     setRotation(this.Chestplate, 0.0F, 0.0F, 0.0F);
/*     */     
/* 122 */     this.HoodEye = new ModelRenderer(this, 0, 0);
/* 123 */     this.HoodEye.func_78789_a(-4.0F, -8.0F, -4.0F, 8, 8, 8);
/* 124 */     this.HoodEye.func_78793_a(0.0F, -6.0F, 0.0F);
/* 125 */     this.HoodEye.func_78787_b(128, 64);
/* 126 */     this.HoodEye.field_78809_i = true;
/* 127 */     setRotation(this.HoodEye, 0.0F, 0.0F, 0.0F);
/*     */     
/* 129 */     this.Hood1 = new ModelRenderer(this, 40, 12);
/* 130 */     this.Hood1.func_78789_a(-4.0F, -8.0F, -4.0F, 8, 8, 8);
/* 131 */     this.Hood1.func_78793_a(0.0F, -6.0F, 0.0F);
/* 132 */     this.Hood1.func_78787_b(128, 64);
/* 133 */     this.Hood1.field_78809_i = true;
/* 134 */     setRotation(this.Hood1, 0.0F, 0.0F, 0.0F);
/* 135 */     this.Hood2 = new ModelRenderer(this, 36, 28);
/* 136 */     this.Hood2.func_78789_a(-3.5F, -8.7F, 2.0F, 7, 7, 3);
/* 137 */     this.Hood2.func_78787_b(128, 64);
/* 138 */     this.Hood2.field_78809_i = true;
/* 139 */     setRotation(this.Hood2, -0.2268928F, 0.0F, 0.0F);
/* 140 */     this.Hood3 = new ModelRenderer(this, 22, 19);
/* 141 */     this.Hood3.func_78789_a(-3.0F, -9.0F, 2.5F, 6, 6, 3);
/* 142 */     this.Hood3.func_78787_b(128, 64);
/* 143 */     this.Hood3.field_78809_i = true;
/* 144 */     setRotation(this.Hood3, -0.3490659F, 0.0F, 0.0F);
/* 145 */     this.Hood4 = new ModelRenderer(this, 40, 4);
/* 146 */     this.Hood4.func_78789_a(-2.5F, -9.7F, 3.5F, 5, 5, 3);
/* 147 */     this.Hood4.func_78787_b(128, 64);
/* 148 */     this.Hood4.field_78809_i = true;
/* 149 */     setRotation(this.Hood4, -0.5759587F, 0.0F, 0.0F);
/* 150 */     this.Hood1.func_78792_a(this.Hood2);
/* 151 */     this.Hood1.func_78792_a(this.Hood3);
/* 152 */     this.Hood1.func_78792_a(this.Hood4);
/*     */     
/*     */ 
/* 155 */     this.Backplate = new ModelRenderer(this, 36, 45);
/* 156 */     this.Backplate.func_78789_a(-4.0F, 1.0F, 2.0F, 8, 11, 2);
/* 157 */     this.Backplate.func_78793_a(0.0F, -6.0F, 0.0F);
/* 158 */     this.Backplate.func_78787_b(128, 64);
/* 159 */     this.Backplate.field_78809_i = true;
/* 160 */     setRotation(this.Backplate, 0.0F, 0.0F, 0.0F);
/*     */     
/* 162 */     this.ShoulderplateTopR = new ModelRenderer(this, 110, 37);
/* 163 */     this.ShoulderplateTopR.func_78789_a(-5.5F, -2.5F, -3.5F, 2, 1, 7);
/* 164 */     this.ShoulderplateTopR.func_78793_a(-5.0F, -4.0F, 0.0F);
/* 165 */     this.ShoulderplateTopR.func_78787_b(128, 64);
/* 166 */     this.ShoulderplateTopR.field_78809_i = true;
/* 167 */     setRotation(this.ShoulderplateTopR, -0.3665191F, 0.3141593F, 0.4363323F);
/* 168 */     this.ShoulderplateR1 = new ModelRenderer(this, 110, 45);
/* 169 */     this.ShoulderplateR1.func_78789_a(3.5F, -1.5F, -3.5F, 1, 4, 7);
/* 170 */     this.ShoulderplateR1.func_78793_a(5.0F, -4.0F, 0.0F);
/* 171 */     this.ShoulderplateR1.func_78787_b(128, 64);
/* 172 */     this.ShoulderplateR1.field_78809_i = true;
/* 173 */     setRotation(this.ShoulderplateR1, -0.3665191F, -0.3141593F, -0.4363323F);
/* 174 */     this.ShoulderplateR2 = new ModelRenderer(this, 94, 45);
/* 175 */     this.ShoulderplateR2.func_78789_a(-3.5F, 1.5F, -3.5F, 1, 3, 7);
/* 176 */     this.ShoulderplateR2.func_78793_a(-5.0F, -4.0F, 0.0F);
/* 177 */     this.ShoulderplateR2.func_78787_b(128, 64);
/* 178 */     this.ShoulderplateR2.field_78809_i = true;
/* 179 */     setRotation(this.ShoulderplateR2, -0.3665191F, 0.3141593F, 0.4363323F);
/* 180 */     this.ShoulderplateR3 = new ModelRenderer(this, 94, 45);
/* 181 */     this.ShoulderplateR3.func_78789_a(-2.5F, 3.5F, -3.5F, 1, 3, 7);
/* 182 */     this.ShoulderplateR3.func_78793_a(-5.0F, -4.0F, 0.0F);
/* 183 */     this.ShoulderplateR3.func_78787_b(128, 64);
/* 184 */     this.ShoulderplateR3.field_78809_i = true;
/* 185 */     setRotation(this.ShoulderplateR3, -0.3665191F, 0.3141593F, 0.4363323F);
/*     */     
/* 187 */     this.ShoulderR = new ModelRenderer(this, 56, 35);
/* 188 */     this.ShoulderR.func_78789_a(-3.5F, -2.5F, -2.5F, 5, 5, 5);
/* 189 */     this.ShoulderR.func_78793_a(-5.0F, -4.0F, 0.0F);
/* 190 */     this.ShoulderR.func_78787_b(128, 64);
/* 191 */     this.ShoulderR.field_78809_i = true;
/* 192 */     setRotation(this.ShoulderR, -0.3665191F, 0.122173F, 0.0349066F);
/*     */     
/* 194 */     this.ArmL1 = new ModelRenderer(this, 72, 8);
/* 195 */     this.ArmL1.func_78789_a(-1.0F, 2.5F, -1.5F, 4, 10, 5);
/* 196 */     this.ArmL1.func_78793_a(5.0F, -4.0F, 0.0F);
/* 197 */     this.ArmL1.func_78787_b(128, 64);
/* 198 */     this.ArmL1.field_78809_i = true;
/* 199 */     setRotation(this.ArmL1, -0.9599311F, -0.1047198F, -0.1919862F);
/* 200 */     this.ArmL2 = new ModelRenderer(this, 76, 28);
/* 201 */     this.ArmL2.func_78789_a(-1.0F, 9.5F, 3.5F, 4, 3, 3);
/* 202 */     this.ArmL2.func_78787_b(128, 64);
/* 203 */     this.ArmL2.field_78809_i = true;
/* 204 */     this.ArmL3 = new ModelRenderer(this, 76, 23);
/* 205 */     this.ArmL3.func_78789_a(-1.0F, 6.5F, 3.5F, 4, 3, 2);
/* 206 */     this.ArmL3.func_78787_b(128, 64);
/* 207 */     this.ArmL3.field_78809_i = true;
/* 208 */     this.ArmL1.func_78792_a(this.ArmL2);
/* 209 */     this.ArmL1.func_78792_a(this.ArmL3);
/*     */     
/* 211 */     this.ArmR1 = new ModelRenderer(this, 72, 8);
/* 212 */     this.ArmR1.func_78789_a(-3.0F, 2.5F, -1.5F, 4, 10, 5);
/* 213 */     this.ArmR1.func_78793_a(-5.0F, -4.0F, 0.0F);
/* 214 */     this.ArmR1.func_78787_b(128, 64);
/* 215 */     this.ArmR1.field_78809_i = true;
/* 216 */     setRotation(this.ArmR1, -0.9599311F, 0.1047198F, 0.1919862F);
/* 217 */     this.ArmR2 = new ModelRenderer(this, 76, 28);
/* 218 */     this.ArmR2.func_78789_a(-3.0F, 9.5F, 3.5F, 4, 3, 3);
/* 219 */     this.ArmR2.func_78787_b(128, 64);
/* 220 */     this.ArmR2.field_78809_i = true;
/* 221 */     this.ArmR3 = new ModelRenderer(this, 76, 23);
/* 222 */     this.ArmR3.func_78789_a(-3.0F, 6.5F, 3.5F, 4, 3, 2);
/* 223 */     this.ArmR3.func_78787_b(128, 64);
/* 224 */     this.ArmR3.field_78809_i = true;
/* 225 */     this.ArmR1.func_78792_a(this.ArmR2);
/* 226 */     this.ArmR1.func_78792_a(this.ArmR3);
/*     */     
/* 228 */     this.ShoulderL = new ModelRenderer(this, 56, 35);
/* 229 */     this.ShoulderL.field_78809_i = true;
/* 230 */     this.ShoulderL.func_78789_a(-1.5F, -2.5F, -2.5F, 5, 5, 5);
/* 231 */     this.ShoulderL.func_78793_a(5.0F, -4.0F, 0.0F);
/* 232 */     this.ShoulderL.func_78787_b(128, 64);
/*     */     
/* 234 */     setRotation(this.ShoulderL, -0.3665191F, -0.122173F, -0.0349066F);
/*     */     
/* 236 */     this.ShoulderplateLtop = new ModelRenderer(this, 110, 37);
/* 237 */     this.ShoulderplateLtop.func_78789_a(3.5F, -2.5F, -3.5F, 2, 1, 7);
/* 238 */     this.ShoulderplateLtop.func_78793_a(5.0F, -4.0F, 0.0F);
/* 239 */     this.ShoulderplateLtop.func_78787_b(128, 64);
/* 240 */     this.ShoulderplateLtop.field_78809_i = true;
/* 241 */     setRotation(this.ShoulderplateLtop, -0.3665191F, -0.3141593F, -0.4363323F);
/* 242 */     this.ShoulderplateL1 = new ModelRenderer(this, 110, 45);
/* 243 */     this.ShoulderplateL1.func_78789_a(-4.5F, -1.5F, -3.5F, 1, 4, 7);
/* 244 */     this.ShoulderplateL1.func_78793_a(-5.0F, -4.0F, 0.0F);
/* 245 */     this.ShoulderplateL1.func_78787_b(128, 64);
/* 246 */     this.ShoulderplateL1.field_78809_i = true;
/* 247 */     setRotation(this.ShoulderplateL1, -0.3665191F, 0.3141593F, 0.4363323F);
/* 248 */     this.ShoulderplateLtop.field_78809_i = false;
/*     */     
/* 250 */     this.ShoulderplateL2 = new ModelRenderer(this, 94, 45);
/* 251 */     this.ShoulderplateL2.func_78789_a(2.5F, 1.5F, -3.5F, 1, 3, 7);
/* 252 */     this.ShoulderplateL2.func_78793_a(5.0F, -4.0F, 0.0F);
/* 253 */     this.ShoulderplateL2.func_78787_b(128, 64);
/* 254 */     this.ShoulderplateL2.field_78809_i = true;
/* 255 */     setRotation(this.ShoulderplateL2, -0.3665191F, -0.3141593F, -0.4363323F);
/* 256 */     this.ShoulderplateL2.field_78809_i = false;
/*     */     
/* 258 */     this.ShoulderplateL3 = new ModelRenderer(this, 94, 45);
/* 259 */     this.ShoulderplateL3.func_78789_a(1.5F, 3.5F, -3.5F, 1, 3, 7);
/* 260 */     this.ShoulderplateL3.func_78793_a(5.0F, -4.0F, 0.0F);
/* 261 */     this.ShoulderplateL3.func_78787_b(128, 64);
/* 262 */     this.ShoulderplateL3.field_78809_i = true;
/* 263 */     setRotation(this.ShoulderplateL3, -0.3665191F, -0.3141593F, -0.4363323F);
/* 264 */     this.ShoulderplateL3.field_78809_i = false;
/* 265 */     this.LegpanelR4 = new ModelRenderer(this, 0, 43);
/* 266 */     this.LegpanelR4.func_78789_a(-3.0F, 0.5F, -3.5F, 2, 3, 1);
/* 267 */     this.LegpanelR4.func_78793_a(-2.0F, 6.0F, 0.0F);
/* 268 */     this.LegpanelR4.func_78787_b(128, 64);
/* 269 */     this.LegpanelR4.field_78809_i = true;
/* 270 */     setRotation(this.LegpanelR4, -0.4363323F, 0.0F, 0.0F);
/* 271 */     this.LegpanelR5 = new ModelRenderer(this, 0, 47);
/* 272 */     this.LegpanelR5.func_78789_a(-3.0F, 2.5F, -2.5F, 2, 3, 1);
/* 273 */     this.LegpanelR5.func_78793_a(-2.0F, 6.0F, 0.0F);
/* 274 */     this.LegpanelR5.func_78787_b(128, 64);
/* 275 */     this.LegpanelR5.field_78809_i = true;
/* 276 */     setRotation(this.LegpanelR5, -0.4363323F, 0.0F, 0.0F);
/* 277 */     this.LegpanelR6 = new ModelRenderer(this, 6, 43);
/* 278 */     this.LegpanelR6.func_78789_a(-3.0F, 4.5F, -1.5F, 2, 3, 1);
/* 279 */     this.LegpanelR6.func_78793_a(-2.0F, 6.0F, 0.0F);
/* 280 */     this.LegpanelR6.func_78787_b(128, 64);
/* 281 */     this.LegpanelR6.field_78809_i = true;
/* 282 */     setRotation(this.LegpanelR6, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 284 */     this.BackpanelR1 = new ModelRenderer(this, 0, 18);
/* 285 */     this.BackpanelR1.func_78789_a(-3.0F, 0.5F, 2.5F, 5, 3, 1);
/* 286 */     this.BackpanelR1.func_78793_a(-2.0F, 6.0F, 0.0F);
/* 287 */     this.BackpanelR1.func_78787_b(128, 64);
/* 288 */     this.BackpanelR1.field_78809_i = true;
/* 289 */     setRotation(this.BackpanelR1, 0.4363323F, 0.0F, 0.0F);
/* 290 */     this.BackpanelR2 = new ModelRenderer(this, 0, 18);
/* 291 */     this.BackpanelR2.func_78789_a(-3.0F, 2.5F, 1.5F, 5, 3, 1);
/* 292 */     this.BackpanelR2.func_78793_a(-2.0F, 6.0F, 0.0F);
/* 293 */     this.BackpanelR2.func_78787_b(128, 64);
/* 294 */     this.BackpanelR2.field_78809_i = true;
/* 295 */     setRotation(this.BackpanelR2, 0.4363323F, 0.0F, 0.0F);
/* 296 */     this.BackpanelR3 = new ModelRenderer(this, 0, 18);
/* 297 */     this.BackpanelR3.func_78789_a(-3.0F, 4.5F, 0.5F, 5, 3, 1);
/* 298 */     this.BackpanelR3.func_78793_a(-2.0F, 6.0F, 0.0F);
/* 299 */     this.BackpanelR3.func_78787_b(128, 64);
/* 300 */     this.BackpanelR3.field_78809_i = true;
/* 301 */     setRotation(this.BackpanelR3, 0.4363323F, 0.0F, 0.0F);
/* 302 */     this.BackpanelL3 = new ModelRenderer(this, 0, 18);
/* 303 */     this.BackpanelL3.func_78789_a(-2.0F, 4.5F, 0.5F, 5, 3, 1);
/* 304 */     this.BackpanelL3.func_78793_a(2.0F, 6.0F, 0.0F);
/* 305 */     this.BackpanelL3.func_78787_b(128, 64);
/* 306 */     this.BackpanelL3.field_78809_i = true;
/* 307 */     setRotation(this.BackpanelL3, 0.4363323F, 0.0F, 0.0F);
/*     */     
/* 309 */     this.LegpanelL4 = new ModelRenderer(this, 0, 43);
/* 310 */     this.LegpanelL4.func_78789_a(1.0F, 0.5F, -3.5F, 2, 3, 1);
/* 311 */     this.LegpanelL4.func_78793_a(2.0F, 6.0F, 0.0F);
/* 312 */     this.LegpanelL4.func_78787_b(128, 64);
/* 313 */     this.LegpanelL4.field_78809_i = true;
/* 314 */     setRotation(this.LegpanelL4, -0.4363323F, 0.0F, 0.0F);
/* 315 */     this.LegpanelL4.field_78809_i = false;
/*     */     
/* 317 */     this.LegpanelL5 = new ModelRenderer(this, 0, 47);
/* 318 */     this.LegpanelL5.func_78789_a(1.0F, 2.5F, -2.5F, 2, 3, 1);
/* 319 */     this.LegpanelL5.func_78793_a(2.0F, 6.0F, 0.0F);
/* 320 */     this.LegpanelL5.func_78787_b(128, 64);
/* 321 */     this.LegpanelL5.field_78809_i = true;
/* 322 */     setRotation(this.LegpanelL5, -0.4363323F, 0.0F, 0.0F);
/* 323 */     this.LegpanelL5.field_78809_i = false;
/*     */     
/* 325 */     this.LegpanelL6 = new ModelRenderer(this, 6, 43);
/* 326 */     this.LegpanelL6.func_78789_a(1.0F, 4.5F, -1.5F, 2, 3, 1);
/* 327 */     this.LegpanelL6.func_78793_a(2.0F, 6.0F, 0.0F);
/* 328 */     this.LegpanelL6.func_78787_b(128, 64);
/* 329 */     this.LegpanelL6.field_78809_i = true;
/* 330 */     setRotation(this.LegpanelL6, -0.4363323F, 0.0F, 0.0F);
/* 331 */     this.LegpanelL6.field_78809_i = false;
/*     */     
/* 333 */     this.BackpanelL1 = new ModelRenderer(this, 0, 18);
/* 334 */     this.BackpanelL1.func_78789_a(-2.0F, 0.5F, 2.5F, 5, 3, 1);
/* 335 */     this.BackpanelL1.func_78793_a(2.0F, 6.0F, 0.0F);
/* 336 */     this.BackpanelL1.func_78787_b(128, 64);
/* 337 */     this.BackpanelL1.field_78809_i = true;
/* 338 */     setRotation(this.BackpanelL1, 0.4363323F, 0.0F, 0.0F);
/* 339 */     this.BackpanelL2 = new ModelRenderer(this, 0, 18);
/* 340 */     this.BackpanelL2.func_78789_a(-2.0F, 2.5F, 1.5F, 5, 3, 1);
/* 341 */     this.BackpanelL2.func_78793_a(2.0F, 6.0F, 0.0F);
/* 342 */     this.BackpanelL2.func_78787_b(128, 64);
/* 343 */     this.BackpanelL2.field_78809_i = true;
/* 344 */     setRotation(this.BackpanelL2, 0.4363323F, 0.0F, 0.0F);
/*     */     
/* 346 */     this.SidepanelL1 = new ModelRenderer(this, 0, 22);
/* 347 */     this.SidepanelL1.func_78789_a(1.5F, 0.5F, -2.5F, 1, 4, 5);
/* 348 */     this.SidepanelL1.func_78793_a(2.0F, 6.0F, 0.0F);
/* 349 */     this.SidepanelL1.func_78787_b(128, 64);
/* 350 */     this.SidepanelL1.field_78809_i = true;
/* 351 */     setRotation(this.SidepanelL1, 0.0F, 0.0F, -0.4363323F);
/* 352 */     this.SidepanelR1 = new ModelRenderer(this, 0, 22);
/* 353 */     this.SidepanelR1.func_78789_a(-2.5F, 0.5F, -2.5F, 1, 4, 5);
/* 354 */     this.SidepanelR1.func_78793_a(-2.0F, 6.0F, 0.0F);
/* 355 */     this.SidepanelR1.func_78787_b(128, 64);
/* 356 */     this.SidepanelR1.field_78809_i = true;
/* 357 */     setRotation(this.SidepanelR1, 0.0F, 0.0F, 0.4363323F);
/*     */     
/*     */ 
/*     */ 
/* 361 */     this.SidepanelR2 = new ModelRenderer(this, 0, 54);
/* 362 */     this.SidepanelR2.func_78789_a(0.0F, 0.0F, -0.5F, 1, 5, 5);
/* 363 */     this.SidepanelR2.func_78793_a(-4.5F, 9.5F, -2.0F);
/* 364 */     this.SidepanelR2.func_78787_b(128, 64);
/* 365 */     this.SidepanelR2.field_78809_i = true;
/* 366 */     setRotation(this.SidepanelR2, 0.0F, 0.0F, 0.122173F);
/* 367 */     this.SidepanelR3 = new ModelRenderer(this, 0, 35);
/* 368 */     this.SidepanelR3.func_78789_a(0.0F, 0.0F, -0.5F, 1, 3, 5);
/* 369 */     this.SidepanelR3.func_78793_a(0.0F, 5.0F, 0.0F);
/* 370 */     this.SidepanelR3.func_78787_b(128, 64);
/* 371 */     this.SidepanelR3.field_78809_i = true;
/* 372 */     setRotation(this.SidepanelR3, 0.0F, 0.0F, 0.296706F);
/* 373 */     this.SidepanelR4 = new ModelRenderer(this, 24, 35);
/* 374 */     this.SidepanelR4.func_78789_a(0.0F, 0.0F, -0.5F, 1, 3, 5);
/* 375 */     this.SidepanelR4.func_78793_a(0.0F, 3.0F, 0.0F);
/* 376 */     this.SidepanelR4.func_78787_b(128, 64);
/* 377 */     this.SidepanelR4.field_78809_i = true;
/* 378 */     setRotation(this.SidepanelR4, 0.0F, 0.0F, 0.5235988F);
/*     */     
/*     */ 
/* 381 */     this.SidepanelL2 = new ModelRenderer(this, 0, 54);
/* 382 */     this.SidepanelL2.func_78789_a(0.0F, 0.0F, -0.5F, 1, 5, 5);
/* 383 */     this.SidepanelL2.func_78793_a(4.5F, 9.5F, -2.0F);
/* 384 */     this.SidepanelL2.func_78787_b(128, 64);
/* 385 */     this.SidepanelL2.field_78809_i = true;
/* 386 */     setRotation(this.SidepanelL2, 0.0F, 0.0F, -0.122173F);
/* 387 */     this.SidepanelL3 = new ModelRenderer(this, 0, 35);
/* 388 */     this.SidepanelL3.func_78789_a(0.0F, 0.0F, -0.5F, 1, 3, 5);
/* 389 */     this.SidepanelL3.func_78793_a(0.0F, 5.0F, 0.0F);
/* 390 */     this.SidepanelL3.func_78787_b(128, 64);
/* 391 */     this.SidepanelL3.field_78809_i = true;
/* 392 */     setRotation(this.SidepanelL3, 0.0F, 0.0F, -0.296706F);
/* 393 */     this.SidepanelL4 = new ModelRenderer(this, 24, 35);
/* 394 */     this.SidepanelL4.func_78789_a(0.0F, 0.0F, -0.5F, 1, 3, 5);
/* 395 */     this.SidepanelL4.func_78793_a(0.0F, 3.0F, 0.0F);
/* 396 */     this.SidepanelL4.func_78787_b(128, 64);
/* 397 */     this.SidepanelL4.field_78809_i = true;
/* 398 */     setRotation(this.SidepanelL4, 0.0F, 0.0F, -0.5235988F);
/*     */     
/*     */ 
/* 401 */     this.LegpanelC1 = new ModelRenderer(this, 16, 45);
/* 402 */     this.LegpanelC1.func_78789_a(-3.0F, 0.0F, -0.5F, 6, 8, 1);
/* 403 */     this.LegpanelC1.func_78793_a(0.0F, 5.5F, -3.0F);
/* 404 */     this.LegpanelC1.func_78787_b(128, 64);
/* 405 */     this.LegpanelC1.field_78809_i = true;
/* 406 */     setRotation(this.LegpanelC1, 0.0F, 0.0F, 0.0F);
/* 407 */     this.LegpanelC2 = new ModelRenderer(this, 16, 54);
/* 408 */     this.LegpanelC2.func_78789_a(-3.0F, 0.0F, -0.5F, 6, 4, 1);
/* 409 */     this.LegpanelC2.func_78793_a(0.0F, 8.0F, 0.0F);
/* 410 */     this.LegpanelC2.func_78787_b(128, 64);
/* 411 */     this.LegpanelC2.field_78809_i = true;
/* 412 */     setRotation(this.LegpanelC2, 0.0F, 0.0F, 0.0F);
/* 413 */     this.LegpanelC3 = new ModelRenderer(this, 32, 59);
/* 414 */     this.LegpanelC3.func_78789_a(-3.0F, 0.0F, -0.5F, 6, 4, 1);
/* 415 */     this.LegpanelC3.func_78793_a(0.0F, 4.0F, 0.0F);
/* 416 */     this.LegpanelC3.func_78787_b(128, 64);
/* 417 */     this.LegpanelC3.field_78809_i = true;
/* 418 */     setRotation(this.LegpanelC3, 0.0F, 0.0F, 0.0F);
/*     */     
/* 420 */     this.Cloak1 = new ModelRenderer(this, 106, 0);
/* 421 */     this.Cloak1.func_78789_a(0.0F, 0.0F, -0.5F, 10, 18, 1);
/* 422 */     this.Cloak1.func_78793_a(-5.0F, -6.0F, 4.0F);
/* 423 */     this.Cloak1.func_78787_b(128, 64);
/* 424 */     this.Cloak1.field_78809_i = true;
/* 425 */     setRotation(this.Cloak1, 0.0F, 0.0F, 0.0F);
/* 426 */     this.Cloak2 = new ModelRenderer(this, 106, 19);
/* 427 */     this.Cloak2.func_78789_a(0.0F, 0.0F, -0.5F, 10, 4, 1);
/* 428 */     this.Cloak2.func_78793_a(0.0F, 18.0F, 0.0F);
/* 429 */     this.Cloak2.func_78787_b(128, 64);
/* 430 */     this.Cloak2.field_78809_i = true;
/* 431 */     setRotation(this.Cloak2, 0.0F, 0.0F, 0.0F);
/* 432 */     this.Cloak3 = new ModelRenderer(this, 106, 24);
/* 433 */     this.Cloak3.func_78789_a(0.0F, 0.0F, -0.5F, 10, 4, 1);
/* 434 */     this.Cloak3.func_78793_a(0.0F, 4.0F, 0.0F);
/* 435 */     this.Cloak3.func_78787_b(128, 64);
/* 436 */     this.Cloak3.field_78809_i = true;
/* 437 */     setRotation(this.Cloak3, 0.0F, 0.0F, 0.0F);
/*     */     
/* 439 */     this.LegpanelC1.func_78792_a(this.LegpanelC2);
/* 440 */     this.LegpanelC2.func_78792_a(this.LegpanelC3);
/* 441 */     this.SidepanelL2.func_78792_a(this.SidepanelL3);
/* 442 */     this.SidepanelL3.func_78792_a(this.SidepanelL4);
/* 443 */     this.SidepanelR2.func_78792_a(this.SidepanelR3);
/* 444 */     this.SidepanelR3.func_78792_a(this.SidepanelR4);
/* 445 */     this.Cloak1.func_78792_a(this.Cloak2);
/* 446 */     this.Cloak2.func_78792_a(this.Cloak3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_78086_a(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_)
/*     */   {
/* 456 */     this.partialTicks = p_78086_4_;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 462 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 463 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */     
/* 465 */     this.BeltR.func_78785_a(f5);
/* 466 */     this.Mbelt.func_78785_a(f5);
/* 467 */     this.MbeltL.func_78785_a(f5);
/* 468 */     this.MbeltR.func_78785_a(f5);
/* 469 */     this.BeltL.func_78785_a(f5);
/*     */     
/* 471 */     this.Chestplate.func_78785_a(f5);
/* 472 */     this.Hood1.func_78785_a(f5);
/*     */     
/* 474 */     this.Backplate.func_78785_a(f5);
/*     */     
/* 476 */     this.ShoulderplateTopR.func_78785_a(f5);
/* 477 */     this.ShoulderplateR1.func_78785_a(f5);
/* 478 */     this.ShoulderplateR2.func_78785_a(f5);
/* 479 */     this.ShoulderplateR3.func_78785_a(f5);
/*     */     
/* 481 */     this.ShoulderR.func_78785_a(f5);
/*     */     
/* 483 */     this.ArmL1.func_78785_a(f5);
/* 484 */     this.ArmR1.func_78785_a(f5);
/*     */     
/* 486 */     this.ShoulderL.func_78785_a(f5);
/* 487 */     this.ShoulderplateLtop.func_78785_a(f5);
/* 488 */     this.ShoulderplateL1.func_78785_a(f5);
/* 489 */     this.ShoulderplateL2.func_78785_a(f5);
/* 490 */     this.ShoulderplateL3.func_78785_a(f5);
/* 491 */     this.LegpanelR4.func_78785_a(f5);
/* 492 */     this.LegpanelR5.func_78785_a(f5);
/* 493 */     this.LegpanelR6.func_78785_a(f5);
/*     */     
/* 495 */     this.BackpanelR1.func_78785_a(f5);
/* 496 */     this.BackpanelR2.func_78785_a(f5);
/* 497 */     this.BackpanelR3.func_78785_a(f5);
/* 498 */     this.BackpanelL3.func_78785_a(f5);
/* 499 */     this.LegpanelL4.func_78785_a(f5);
/* 500 */     this.LegpanelL5.func_78785_a(f5);
/* 501 */     this.LegpanelL6.func_78785_a(f5);
/*     */     
/* 503 */     this.BackpanelL1.func_78785_a(f5);
/* 504 */     this.BackpanelL2.func_78785_a(f5);
/*     */     
/*     */ 
/* 507 */     this.Cloak1.func_78785_a(f5);
/*     */     
/* 509 */     this.SidepanelR1.func_78785_a(f5);
/* 510 */     this.SidepanelL1.func_78785_a(f5);
/*     */     
/* 512 */     this.SidepanelL2.func_78785_a(f5);
/* 513 */     this.SidepanelR2.func_78785_a(f5);
/*     */     
/* 515 */     this.LegpanelC1.func_78785_a(f5);
/*     */     
/* 517 */     if ((entity instanceof EntityEldritchWarden)) {
/* 518 */       GL11.glPushMatrix();
/* 519 */       GL11.glEnable(3042);
/* 520 */       GL11.glBlendFunc(770, 1);
/* 521 */       GL11.glScaled(1.01D, 1.01D, 1.01D);
/* 522 */       int j = (int)(195.0F + MathHelper.func_76126_a(entity.field_70173_aa / 3.0F) * 15.0F + 15.0F);
/* 523 */       int k = j % 65536;
/* 524 */       int l = j / 65536;
/* 525 */       OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/* 526 */       this.HoodEye.func_78785_a(f5);
/* 527 */       GL11.glDisable(3042);
/* 528 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
/*     */   {
/* 537 */     super.func_78087_a(par1, par2, par3, par4, par5, par6, entity);
/*     */     
/* 539 */     this.Hood1.field_78796_g = (par4 / 57.295776F);
/* 540 */     this.Hood1.field_78795_f = (par5 / 57.295776F);
/* 541 */     this.HoodEye.field_78795_f = this.Hood1.field_78795_f;
/* 542 */     this.HoodEye.field_78796_g = this.Hood1.field_78796_g;
/* 543 */     float alr = 0.0F;
/* 544 */     float all = 0.0F;
/* 545 */     if ((entity instanceof EntityEldritchGuardian)) {
/* 546 */       alr = ((EntityEldritchGuardian)entity).armLiftR;
/* 547 */       all = ((EntityEldritchGuardian)entity).armLiftL;
/*     */     }
/* 549 */     if ((entity instanceof EntityEldritchWarden)) {
/* 550 */       alr = ((EntityEldritchWarden)entity).armLiftR;
/* 551 */       all = ((EntityEldritchWarden)entity).armLiftL;
/*     */     }
/* 553 */     this.ArmL1.field_78795_f = (-1.0F - all + MathHelper.func_76126_a((entity.field_70173_aa + 20 + this.partialTicks) / 10.0F) * 0.08F);
/* 554 */     this.ArmR1.field_78795_f = (-1.0F - alr + MathHelper.func_76126_a((entity.field_70173_aa + this.partialTicks) / 10.0F) * 0.08F);
/*     */     
/* 556 */     this.LegpanelC1.field_78795_f = (-0.15F + MathHelper.func_76126_a((entity.field_70173_aa + this.partialTicks) / 8.0F) * 0.12F);
/* 557 */     this.LegpanelC2.field_78795_f = (MathHelper.func_76126_a((entity.field_70173_aa + this.partialTicks - 5.0F) / 8.0F) * 0.13F);
/* 558 */     this.LegpanelC3.field_78795_f = (MathHelper.func_76126_a((entity.field_70173_aa + this.partialTicks - 10.0F) / 8.0F) * 0.14F);
/*     */     
/* 560 */     this.Cloak1.field_78795_f = (0.2F + MathHelper.func_76126_a((entity.field_70173_aa + this.partialTicks) / 7.0F) * 0.08F);
/* 561 */     this.Cloak2.field_78795_f = (MathHelper.func_76126_a((entity.field_70173_aa + this.partialTicks - 5.0F) / 7.0F) * 0.1F);
/* 562 */     this.Cloak3.field_78795_f = (MathHelper.func_76126_a((entity.field_70173_aa + this.partialTicks - 10.0F) / 7.0F) * 0.12F);
/*     */     
/* 564 */     this.SidepanelL2.field_78808_h = (-0.2F + MathHelper.func_76126_a((entity.field_70173_aa + 10 + this.partialTicks) / 8.0F) * 0.12F);
/* 565 */     this.SidepanelL3.field_78808_h = (MathHelper.func_76126_a((entity.field_70173_aa + this.partialTicks + 5.0F) / 8.0F) * 0.13F);
/* 566 */     this.SidepanelL4.field_78808_h = (MathHelper.func_76126_a((entity.field_70173_aa + this.partialTicks) / 8.0F) * 0.14F);
/*     */     
/* 568 */     this.SidepanelR2.field_78808_h = (0.2F + MathHelper.func_76126_a((entity.field_70173_aa - 5 + this.partialTicks) / 8.0F) * 0.12F);
/* 569 */     this.SidepanelR3.field_78808_h = (MathHelper.func_76126_a((entity.field_70173_aa + this.partialTicks - 10.0F) / 8.0F) * 0.13F);
/* 570 */     this.SidepanelR4.field_78808_h = (MathHelper.func_76126_a((entity.field_70173_aa + this.partialTicks - 15.0F) / 8.0F) * 0.14F);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*     */   {
/* 575 */     model.field_78795_f = x;
/* 576 */     model.field_78796_g = y;
/* 577 */     model.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/entities/ModelEldritchGuardian.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */