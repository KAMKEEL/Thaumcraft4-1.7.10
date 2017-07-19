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
/*     */ public class ModelLeaderArmor
/*     */   extends ModelBiped
/*     */ {
/*     */   ModelRenderer Helmet;
/*     */   ModelRenderer CollarF;
/*     */   ModelRenderer CollarB;
/*     */   ModelRenderer CollarR;
/*     */   ModelRenderer CollarL;
/*     */   ModelRenderer BeltR;
/*     */   ModelRenderer Mbelt;
/*     */   ModelRenderer MbeltL;
/*     */   ModelRenderer MbeltR;
/*     */   ModelRenderer BeltL;
/*     */   ModelRenderer CloakTL;
/*     */   ModelRenderer Cloak3;
/*     */   ModelRenderer CloakTR;
/*     */   ModelRenderer Cloak1;
/*     */   ModelRenderer Cloak2;
/*     */   ModelRenderer Chestplate;
/*     */   ModelRenderer ChestOrnament;
/*     */   ModelRenderer ChestClothL;
/*     */   ModelRenderer ChestClothR;
/*     */   ModelRenderer Backplate;
/*     */   ModelRenderer GauntletstrapR1;
/*     */   ModelRenderer GauntletstrapR2;
/*     */   ModelRenderer ShoulderR;
/*     */   ModelRenderer ShoulderR1;
/*     */   ModelRenderer ShoulderR2;
/*     */   ModelRenderer ShoulderR5;
/*     */   ModelRenderer ShoulderR3;
/*     */   ModelRenderer ShoulderR4;
/*     */   ModelRenderer GauntletR2;
/*     */   ModelRenderer GauntletR;
/*     */   ModelRenderer GauntletL2;
/*     */   ModelRenderer GauntletstrapL1;
/*     */   ModelRenderer GauntletstrapL2;
/*     */   ModelRenderer ShoulderL;
/*     */   ModelRenderer ShoulderL1;
/*     */   ModelRenderer ShoulderL2;
/*     */   ModelRenderer ShoulderL3;
/*     */   ModelRenderer ShoulderL5;
/*     */   ModelRenderer ShoulderL4;
/*     */   ModelRenderer GauntletL;
/*     */   ModelRenderer LegClothR;
/*     */   ModelRenderer BackpanelR2;
/*     */   ModelRenderer BackpanelR3;
/*     */   ModelRenderer BackpanelR4;
/*     */   ModelRenderer LegClothL;
/*     */   ModelRenderer BackpanelL4;
/*     */   ModelRenderer BackpanelL2;
/*     */   ModelRenderer BackpanelL3;
/*     */   ModelRenderer BackpanelL1;
/*     */   ModelRenderer BackpanelR1;
/*     */   
/*     */   public ModelLeaderArmor(float f)
/*     */   {
/*  72 */     super(f, 0.0F, 128, 64);
/*  73 */     this.field_78090_t = 128;
/*  74 */     this.field_78089_u = 64;
/*     */     
/*  76 */     this.Helmet = new ModelRenderer(this, 41, 8);
/*  77 */     this.Helmet.func_78789_a(-4.5F, -9.0F, -4.5F, 9, 9, 9);
/*  78 */     this.Helmet.func_78787_b(128, 64);
/*  79 */     setRotation(this.Helmet, 0.0F, 0.0F, 0.0F);
/*     */     
/*  81 */     this.CollarF = new ModelRenderer(this, 17, 31);
/*  82 */     this.CollarF.func_78789_a(-4.5F, -1.5F, -3.0F, 9, 4, 1);
/*  83 */     this.CollarF.func_78793_a(0.0F, 0.0F, -2.5F);
/*  84 */     setRotation(this.CollarF, 0.2268928F, 0.0F, 0.0F);
/*  85 */     this.CollarB = new ModelRenderer(this, 17, 26);
/*  86 */     this.CollarB.func_78789_a(-4.5F, -1.5F, 7.0F, 9, 4, 1);
/*  87 */     this.CollarB.func_78793_a(0.0F, 0.0F, -2.5F);
/*  88 */     setRotation(this.CollarB, 0.2268928F, 0.0F, 0.0F);
/*  89 */     this.CollarR = new ModelRenderer(this, 17, 11);
/*  90 */     this.CollarR.func_78789_a(-5.5F, -1.5F, -3.0F, 1, 4, 11);
/*  91 */     this.CollarR.func_78793_a(0.0F, 0.0F, -2.5F);
/*  92 */     setRotation(this.CollarR, 0.2268928F, 0.0F, 0.0F);
/*  93 */     this.CollarL = new ModelRenderer(this, 17, 11);
/*  94 */     this.CollarL.func_78789_a(4.5F, -1.5F, -3.0F, 1, 4, 11);
/*  95 */     this.CollarL.func_78793_a(0.0F, 0.0F, -2.5F);
/*  96 */     setRotation(this.CollarL, 0.2268928F, 0.0F, 0.0F);
/*     */     
/*  98 */     this.BeltR = new ModelRenderer(this, 76, 44);
/*  99 */     this.BeltR.func_78789_a(-5.0F, 4.0F, -3.0F, 1, 3, 6);
/* 100 */     this.Mbelt = new ModelRenderer(this, 56, 55);
/* 101 */     this.Mbelt.func_78789_a(-4.0F, 8.0F, -3.0F, 8, 4, 1);
/* 102 */     this.MbeltL = new ModelRenderer(this, 76, 44);
/* 103 */     this.MbeltL.func_78789_a(4.0F, 8.0F, -3.0F, 1, 3, 6);
/* 104 */     this.MbeltR = new ModelRenderer(this, 76, 44);
/* 105 */     this.MbeltR.func_78789_a(-5.0F, 8.0F, -3.0F, 1, 3, 6);
/* 106 */     this.BeltL = new ModelRenderer(this, 76, 44);
/* 107 */     this.BeltL.func_78789_a(4.0F, 4.0F, -3.0F, 1, 3, 6);
/*     */     
/* 109 */     this.CloakTL = new ModelRenderer(this, 0, 43);
/* 110 */     this.CloakTL.func_78789_a(2.5F, 1.0F, -1.0F, 2, 1, 3);
/* 111 */     this.CloakTL.func_78793_a(0.0F, 0.0F, 3.0F);
/* 112 */     setRotation(this.CloakTL, 0.1396263F, 0.0F, 0.0F);
/* 113 */     this.Cloak3 = new ModelRenderer(this, 0, 59);
/* 114 */     this.Cloak3.func_78789_a(-4.5F, 17.0F, -3.7F, 9, 4, 1);
/* 115 */     this.Cloak3.func_78793_a(0.0F, 0.0F, 3.0F);
/* 116 */     setRotation(this.Cloak3, 0.4465716F, 0.0F, 0.0F);
/* 117 */     this.CloakTR = new ModelRenderer(this, 0, 43);
/* 118 */     this.CloakTR.func_78789_a(-4.5F, 1.0F, -1.0F, 2, 1, 3);
/* 119 */     this.CloakTR.func_78793_a(0.0F, 0.0F, 3.0F);
/* 120 */     setRotation(this.CloakTR, 0.1396263F, 0.0F, 0.0F);
/* 121 */     this.Cloak1 = new ModelRenderer(this, 0, 47);
/* 122 */     this.Cloak1.func_78789_a(-4.5F, 2.0F, 1.0F, 9, 12, 1);
/* 123 */     this.Cloak1.func_78793_a(0.0F, 0.0F, 3.0F);
/* 124 */     setRotation(this.Cloak1, 0.1396263F, 0.0F, 0.0F);
/* 125 */     this.Cloak2 = new ModelRenderer(this, 0, 59);
/* 126 */     this.Cloak2.func_78789_a(-4.5F, 14.0F, -1.3F, 9, 4, 1);
/* 127 */     this.Cloak2.func_78793_a(0.0F, 0.0F, 3.0F);
/* 128 */     setRotation(this.Cloak2, 0.3069452F, 0.0F, 0.0F);
/*     */     
/* 130 */     this.Chestplate = new ModelRenderer(this, 56, 45);
/* 131 */     this.Chestplate.func_78789_a(-4.0F, 1.0F, -3.8F, 8, 7, 2);
/* 132 */     this.ChestOrnament = new ModelRenderer(this, 76, 53);
/* 133 */     this.ChestOrnament.func_78789_a(-2.5F, 3.0F, -4.8F, 5, 5, 1);
/* 134 */     this.ChestClothL = new ModelRenderer(this, 20, 47);
/* 135 */     this.ChestClothL.field_78809_i = true;
/* 136 */     this.ChestClothL.func_78789_a(1.5F, 1.2F, -4.5F, 3, 9, 1);
/* 137 */     setRotation(this.ChestClothL, 0.0663225F, 0.0F, 0.0F);
/* 138 */     this.ChestClothR = new ModelRenderer(this, 20, 47);
/* 139 */     this.ChestClothR.func_78789_a(-4.5F, 1.2F, -4.5F, 3, 9, 1);
/* 140 */     setRotation(this.ChestClothR, 0.0663225F, 0.0F, 0.0F);
/* 141 */     this.Backplate = new ModelRenderer(this, 36, 45);
/* 142 */     this.Backplate.func_78789_a(-4.0F, 1.0F, 2.0F, 8, 11, 2);
/*     */     
/* 144 */     this.GauntletR = new ModelRenderer(this, 100, 26);
/* 145 */     this.GauntletR.func_78789_a(-3.5F, 3.5F, -2.5F, 2, 6, 5);
/* 146 */     this.GauntletL = new ModelRenderer(this, 114, 26);
/* 147 */     this.GauntletL.func_78789_a(1.5F, 3.5F, -2.5F, 2, 6, 5);
/* 148 */     this.GauntletstrapL1 = new ModelRenderer(this, 84, 31);
/* 149 */     this.GauntletstrapL1.field_78809_i = true;
/* 150 */     this.GauntletstrapL1.func_78789_a(-1.5F, 3.5F, -2.5F, 3, 1, 5);
/* 151 */     this.GauntletstrapL2 = new ModelRenderer(this, 84, 31);
/* 152 */     this.GauntletstrapL2.field_78809_i = true;
/* 153 */     this.GauntletstrapL2.func_78789_a(-1.5F, 6.5F, -2.5F, 3, 1, 5);
/* 154 */     this.GauntletstrapR1 = new ModelRenderer(this, 84, 31);
/* 155 */     this.GauntletstrapR1.func_78789_a(-1.5F, 3.5F, -2.5F, 3, 1, 5);
/* 156 */     this.GauntletstrapR2 = new ModelRenderer(this, 84, 31);
/* 157 */     this.GauntletstrapR2.func_78789_a(-1.5F, 6.5F, -2.5F, 3, 1, 5);
/* 158 */     this.GauntletR2 = new ModelRenderer(this, 102, 37);
/* 159 */     this.GauntletR2.func_78789_a(-5.0F, 3.5F, -2.0F, 1, 5, 4);
/* 160 */     setRotation(this.GauntletR2, 0.0F, 0.0F, -0.1675516F);
/* 161 */     this.GauntletL2 = new ModelRenderer(this, 102, 37);
/* 162 */     this.GauntletL2.func_78789_a(4.0F, 3.5F, -2.0F, 1, 5, 4);
/* 163 */     setRotation(this.GauntletL2, 0.0F, 0.0F, 0.1675516F);
/*     */     
/* 165 */     this.ShoulderR = new ModelRenderer(this, 56, 35);
/* 166 */     this.ShoulderR.func_78789_a(-3.5F, -2.5F, -2.5F, 5, 5, 5);
/* 167 */     this.ShoulderR1 = new ModelRenderer(this, 0, 0);
/* 168 */     this.ShoulderR1.func_78789_a(-4.3F, -1.5F, -3.0F, 3, 5, 6);
/* 169 */     setRotation(this.ShoulderR1, 0.0F, 0.0F, 0.7853982F);
/* 170 */     this.ShoulderR2 = new ModelRenderer(this, 0, 19);
/* 171 */     this.ShoulderR2.func_78789_a(-3.3F, 3.5F, -2.5F, 1, 1, 5);
/* 172 */     setRotation(this.ShoulderR2, 0.0F, 0.0F, 0.7853982F);
/* 173 */     this.ShoulderR5 = new ModelRenderer(this, 18, 4);
/* 174 */     this.ShoulderR5.func_78789_a(-2.3F, -1.5F, 3.0F, 1, 6, 1);
/* 175 */     setRotation(this.ShoulderR5, 0.0F, 0.0F, 0.7853982F);
/* 176 */     this.ShoulderR3 = new ModelRenderer(this, 0, 11);
/* 177 */     this.ShoulderR3.func_78789_a(-2.3F, 3.5F, -3.0F, 1, 2, 6);
/* 178 */     setRotation(this.ShoulderR3, 0.0F, 0.0F, 0.7853982F);
/* 179 */     this.ShoulderR4 = new ModelRenderer(this, 18, 4);
/* 180 */     this.ShoulderR4.func_78789_a(-2.3F, -1.5F, -4.0F, 1, 6, 1);
/* 181 */     setRotation(this.ShoulderR4, 0.0F, 0.0F, 0.7853982F);
/*     */     
/* 183 */     this.ShoulderL = new ModelRenderer(this, 56, 35);
/* 184 */     this.ShoulderL.func_78789_a(-1.5F, -2.5F, -2.5F, 5, 5, 5);
/* 185 */     this.ShoulderL1 = new ModelRenderer(this, 0, 0);
/* 186 */     this.ShoulderL1.func_78789_a(1.3F, -1.5F, -3.0F, 3, 5, 6);
/* 187 */     setRotation(this.ShoulderL1, 0.0F, 0.0F, -0.7853982F);
/* 188 */     this.ShoulderL2 = new ModelRenderer(this, 0, 19);
/* 189 */     this.ShoulderL2.field_78809_i = true;
/* 190 */     this.ShoulderL2.func_78789_a(2.3F, 3.5F, -2.5F, 1, 1, 5);
/* 191 */     setRotation(this.ShoulderL2, 0.0F, 0.0F, -0.7853982F);
/* 192 */     this.ShoulderL3 = new ModelRenderer(this, 0, 11);
/* 193 */     this.ShoulderL3.func_78789_a(1.3F, 3.5F, -3.0F, 1, 2, 6);
/* 194 */     setRotation(this.ShoulderL3, 0.0F, 0.0F, -0.7853982F);
/* 195 */     this.ShoulderL5 = new ModelRenderer(this, 18, 4);
/* 196 */     this.ShoulderL5.func_78789_a(1.3F, -1.5F, 3.0F, 1, 6, 1);
/* 197 */     this.ShoulderL5.func_78787_b(128, 64);
/* 198 */     setRotation(this.ShoulderL5, 0.0F, 0.0F, -0.7853982F);
/* 199 */     this.ShoulderL4 = new ModelRenderer(this, 18, 4);
/* 200 */     this.ShoulderL4.func_78789_a(1.3F, -1.5F, -4.0F, 1, 6, 1);
/* 201 */     setRotation(this.ShoulderL4, 0.0F, 0.0F, -0.7853982F);
/*     */     
/* 203 */     this.LegClothR = new ModelRenderer(this, 20, 55);
/* 204 */     this.LegClothR.func_78789_a(0.0F, 0.0F, 0.0F, 3, 8, 1);
/* 205 */     this.LegClothR.func_78793_a(-4.5F, 10.4F, -3.9F);
/* 206 */     setRotation(this.LegClothR, -0.0349066F, 0.0F, 0.0F);
/* 207 */     this.LegClothL = new ModelRenderer(this, 20, 55);
/* 208 */     this.LegClothL.field_78809_i = true;
/* 209 */     this.LegClothL.func_78789_a(0.0F, 0.0F, 0.0F, 3, 8, 1);
/* 210 */     this.LegClothL.func_78793_a(1.5F, 10.4F, -3.9F);
/* 211 */     setRotation(this.LegClothL, -0.0349066F, 0.0F, 0.0F);
/*     */     
/* 213 */     this.BackpanelR1 = new ModelRenderer(this, 0, 25);
/* 214 */     this.BackpanelR1.func_78789_a(-3.0F, -0.5F, 2.5F, 5, 7, 1);
/* 215 */     setRotation(this.BackpanelR1, 0.0698132F, 0.0F, 0.0F);
/* 216 */     this.BackpanelR2 = new ModelRenderer(this, 96, 14);
/* 217 */     this.BackpanelR2.func_78789_a(-3.0F, -0.5F, -2.5F, 5, 3, 5);
/* 218 */     setRotation(this.BackpanelR2, 0.0F, 0.0F, 0.1396263F);
/* 219 */     this.BackpanelR3 = new ModelRenderer(this, 116, 13);
/* 220 */     this.BackpanelR3.func_78789_a(-3.0F, 2.5F, -2.5F, 1, 4, 5);
/* 221 */     setRotation(this.BackpanelR3, 0.0F, 0.0F, 0.1396263F);
/* 222 */     this.BackpanelR4 = new ModelRenderer(this, 0, 25);
/* 223 */     this.BackpanelR4.field_78809_i = true;
/* 224 */     this.BackpanelR4.func_78789_a(-3.0F, -0.5F, -3.5F, 5, 7, 1);
/* 225 */     setRotation(this.BackpanelR4, -0.0349066F, 0.0F, 0.0F);
/*     */     
/* 227 */     this.BackpanelL1 = new ModelRenderer(this, 0, 25);
/* 228 */     this.BackpanelL1.func_78789_a(-2.0F, -0.5F, 2.5F, 5, 7, 1);
/* 229 */     setRotation(this.BackpanelL1, 0.0698132F, 0.0F, 0.0F);
/* 230 */     this.BackpanelL4 = new ModelRenderer(this, 0, 25);
/* 231 */     this.BackpanelL4.func_78789_a(-2.0F, -0.5F, -3.5F, 5, 7, 1);
/* 232 */     setRotation(this.BackpanelL4, -0.0349066F, 0.0F, 0.0F);
/* 233 */     this.BackpanelL2 = new ModelRenderer(this, 96, 14);
/* 234 */     this.BackpanelL2.func_78789_a(-2.0F, -0.5F, -2.5F, 5, 3, 5);
/* 235 */     setRotation(this.BackpanelL2, 0.0F, 0.0F, -0.1396263F);
/* 236 */     this.BackpanelL3 = new ModelRenderer(this, 116, 13);
/* 237 */     this.BackpanelL3.func_78789_a(2.0F, 2.5F, -2.5F, 1, 4, 5);
/* 238 */     setRotation(this.BackpanelL3, 0.0F, 0.0F, -0.1396263F);
/*     */     
/* 240 */     this.field_78114_d.field_78804_l.clear();
/* 241 */     this.field_78116_c.field_78804_l.clear();
/* 242 */     this.field_78116_c.func_78792_a(this.Helmet);
/*     */     
/* 244 */     this.field_78115_e.field_78804_l.clear();
/* 245 */     this.field_78123_h.field_78804_l.clear();
/* 246 */     this.field_78124_i.field_78804_l.clear();
/*     */     
/* 248 */     this.field_78115_e.func_78792_a(this.Mbelt);
/* 249 */     this.field_78115_e.func_78792_a(this.MbeltL);
/* 250 */     this.field_78115_e.func_78792_a(this.MbeltR);
/*     */     
/* 252 */     if (f >= 1.0F)
/*     */     {
/*     */ 
/* 255 */       this.field_78115_e.func_78792_a(this.BeltL);
/* 256 */       this.field_78115_e.func_78792_a(this.BeltR);
/* 257 */       this.field_78115_e.func_78792_a(this.Chestplate);
/* 258 */       this.field_78115_e.func_78792_a(this.ChestOrnament);
/* 259 */       this.field_78115_e.func_78792_a(this.ChestClothR);
/* 260 */       this.field_78115_e.func_78792_a(this.ChestClothL);
/* 261 */       this.field_78115_e.func_78792_a(this.LegClothR);
/* 262 */       this.field_78115_e.func_78792_a(this.LegClothL);
/* 263 */       this.field_78115_e.func_78792_a(this.Backplate);
/* 264 */       this.field_78115_e.func_78792_a(this.CollarB);
/* 265 */       this.field_78115_e.func_78792_a(this.CollarR);
/* 266 */       this.field_78115_e.func_78792_a(this.CollarL);
/* 267 */       this.field_78115_e.func_78792_a(this.CollarF);
/* 268 */       this.field_78115_e.func_78792_a(this.Cloak1);
/* 269 */       this.field_78115_e.func_78792_a(this.Cloak2);
/* 270 */       this.field_78115_e.func_78792_a(this.Cloak3);
/* 271 */       this.field_78115_e.func_78792_a(this.CloakTL);
/* 272 */       this.field_78115_e.func_78792_a(this.CloakTR);
/*     */     }
/*     */     
/* 275 */     this.field_78112_f.field_78804_l.clear();
/* 276 */     this.field_78112_f.func_78792_a(this.ShoulderR);
/* 277 */     this.field_78112_f.func_78792_a(this.ShoulderR1);
/* 278 */     this.field_78112_f.func_78792_a(this.ShoulderR2);
/* 279 */     this.field_78112_f.func_78792_a(this.ShoulderR3);
/* 280 */     this.field_78112_f.func_78792_a(this.ShoulderR4);
/* 281 */     this.field_78112_f.func_78792_a(this.ShoulderR5);
/* 282 */     this.field_78112_f.func_78792_a(this.GauntletR);
/* 283 */     this.field_78112_f.func_78792_a(this.GauntletR2);
/* 284 */     this.field_78112_f.func_78792_a(this.GauntletstrapR1);
/* 285 */     this.field_78112_f.func_78792_a(this.GauntletstrapR2);
/*     */     
/* 287 */     this.field_78113_g.field_78804_l.clear();
/* 288 */     this.field_78113_g.func_78792_a(this.ShoulderL);
/* 289 */     this.field_78113_g.func_78792_a(this.ShoulderL1);
/* 290 */     this.field_78113_g.func_78792_a(this.ShoulderL2);
/* 291 */     this.field_78113_g.func_78792_a(this.ShoulderL3);
/* 292 */     this.field_78113_g.func_78792_a(this.ShoulderL4);
/* 293 */     this.field_78113_g.func_78792_a(this.ShoulderL5);
/* 294 */     this.field_78113_g.func_78792_a(this.GauntletL);
/* 295 */     this.field_78113_g.func_78792_a(this.GauntletL2);
/* 296 */     this.field_78113_g.func_78792_a(this.GauntletstrapL1);
/* 297 */     this.field_78113_g.func_78792_a(this.GauntletstrapL2);
/*     */     
/* 299 */     this.field_78123_h.func_78792_a(this.BackpanelR1);
/* 300 */     this.field_78123_h.func_78792_a(this.BackpanelR2);
/* 301 */     this.field_78123_h.func_78792_a(this.BackpanelR3);
/* 302 */     this.field_78123_h.func_78792_a(this.BackpanelR4);
/*     */     
/* 304 */     this.field_78124_i.func_78792_a(this.BackpanelL1);
/* 305 */     this.field_78124_i.func_78792_a(this.BackpanelL2);
/* 306 */     this.field_78124_i.func_78792_a(this.BackpanelL3);
/* 307 */     this.field_78124_i.func_78792_a(this.BackpanelL4);
/*     */   }
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 311 */     if (((entity instanceof EntitySkeleton)) || ((entity instanceof EntityZombie))) {
/* 312 */       setRotationAnglesZombie(f, f1, f2, f3, f4, f5, entity);
/*     */     } else {
/* 314 */       func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */     }
/*     */     
/* 317 */     float a = MathHelper.func_76134_b(f * 0.6662F) * 1.4F * f1;
/* 318 */     float b = MathHelper.func_76134_b(f * 0.6662F + 3.1415927F) * 1.4F * f1;
/* 319 */     float c = Math.min(a, b);
/*     */     
/* 321 */     this.LegClothR.field_78795_f = (a - 0.1047198F);
/* 322 */     this.LegClothL.field_78795_f = (b - 0.1047198F);
/*     */     
/* 324 */     this.Cloak1.field_78795_f = (-c / 2.0F + 0.1396263F);
/* 325 */     this.Cloak2.field_78795_f = (-c / 2.0F + 0.3069452F);
/* 326 */     this.Cloak3.field_78795_f = (-c / 2.0F + 0.4465716F);
/*     */     
/* 328 */     if (this.field_78091_s) {
/* 329 */       float f6 = 2.0F;
/* 330 */       GL11.glPushMatrix();
/* 331 */       GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
/* 332 */       GL11.glTranslatef(0.0F, 16.0F * f5, 0.0F);
/* 333 */       this.field_78116_c.func_78785_a(f5);
/* 334 */       GL11.glPopMatrix();
/* 335 */       GL11.glPushMatrix();
/* 336 */       GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
/* 337 */       GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
/* 338 */       this.field_78115_e.func_78785_a(f5);
/* 339 */       this.field_78112_f.func_78785_a(f5);
/* 340 */       this.field_78113_g.func_78785_a(f5);
/* 341 */       this.field_78123_h.func_78785_a(f5);
/* 342 */       this.field_78124_i.func_78785_a(f5);
/* 343 */       this.field_78114_d.func_78785_a(f5);
/* 344 */       GL11.glPopMatrix();
/*     */     } else {
/* 346 */       GL11.glPushMatrix();
/* 347 */       GL11.glScalef(1.01F, 1.01F, 1.01F);
/* 348 */       this.field_78116_c.func_78785_a(f5);
/* 349 */       GL11.glPopMatrix();
/* 350 */       this.field_78115_e.func_78785_a(f5);
/* 351 */       this.field_78112_f.func_78785_a(f5);
/* 352 */       this.field_78113_g.func_78785_a(f5);
/* 353 */       this.field_78123_h.func_78785_a(f5);
/* 354 */       this.field_78124_i.func_78785_a(f5);
/* 355 */       this.field_78114_d.func_78785_a(f5);
/*     */     }
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 360 */     model.field_78795_f = x;
/* 361 */     model.field_78796_g = y;
/* 362 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setRotationAnglesZombie(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
/*     */   {
/* 368 */     super.func_78087_a(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
/*     */     
/* 370 */     float f6 = MathHelper.func_76126_a(this.field_78095_p * 3.1415927F);
/* 371 */     float f7 = MathHelper.func_76126_a((1.0F - (1.0F - this.field_78095_p) * (1.0F - this.field_78095_p)) * 3.1415927F);
/*     */     
/*     */ 
/* 374 */     this.field_78112_f.field_78808_h = 0.0F;
/* 375 */     this.field_78113_g.field_78808_h = 0.0F;
/* 376 */     this.field_78112_f.field_78796_g = (-(0.1F - f6 * 0.6F));
/* 377 */     this.field_78113_g.field_78796_g = (0.1F - f6 * 0.6F);
/* 378 */     this.field_78112_f.field_78795_f = -1.5707964F;
/* 379 */     this.field_78113_g.field_78795_f = -1.5707964F;
/* 380 */     this.field_78112_f.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
/* 381 */     this.field_78113_g.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
/* 382 */     this.field_78112_f.field_78808_h += MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
/* 383 */     this.field_78113_g.field_78808_h -= MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
/* 384 */     this.field_78112_f.field_78795_f += MathHelper.func_76126_a(p_78087_3_ * 0.067F) * 0.05F;
/* 385 */     this.field_78113_g.field_78795_f -= MathHelper.func_76126_a(p_78087_3_ * 0.067F) * 0.05F;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/gear/ModelLeaderArmor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */