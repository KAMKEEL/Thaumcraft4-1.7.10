/*     */ package thaumcraft.client.renderers.models.entities;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import thaumcraft.common.entities.monster.boss.EntityEldritchGolem;
/*     */ 
/*     */ public class ModelEldritchGolem
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer Frontcloth1;
/*     */   ModelRenderer CollarL;
/*     */   ModelRenderer Cloak1;
/*     */   ModelRenderer CloakCL;
/*     */   ModelRenderer CloakCR;
/*     */   ModelRenderer Cloak3;
/*     */   ModelRenderer Cloak2;
/*     */   ModelRenderer Head;
/*     */   ModelRenderer Head2;
/*     */   ModelRenderer Frontcloth0;
/*     */   ModelRenderer CollarB;
/*     */   ModelRenderer Torso;
/*     */   ModelRenderer CollarR;
/*     */   ModelRenderer CollarF;
/*     */   ModelRenderer CollarBlack;
/*     */   ModelRenderer ShoulderR1;
/*     */   ModelRenderer ArmL;
/*     */   ModelRenderer ShoulderR;
/*     */   ModelRenderer ShoulderR2;
/*     */   ModelRenderer ShoulderR0;
/*     */   ModelRenderer ArmR;
/*     */   ModelRenderer ShoulderL1;
/*     */   ModelRenderer ShoulderL0;
/*     */   ModelRenderer ShoulderL;
/*     */   ModelRenderer ShoulderL2;
/*     */   ModelRenderer BackpanelR1;
/*     */   ModelRenderer WaistR1;
/*     */   ModelRenderer WaistR2;
/*     */   ModelRenderer WaistR3;
/*     */   ModelRenderer LegR;
/*     */   ModelRenderer WaistL1;
/*     */   ModelRenderer WaistL2;
/*     */   ModelRenderer WaistL3;
/*     */   ModelRenderer Frontcloth2;
/*     */   ModelRenderer BackpanelL1;
/*     */   ModelRenderer LegL;
/*     */   
/*     */   public ModelEldritchGolem()
/*     */   {
/*  52 */     this.field_78090_t = 128;
/*  53 */     this.field_78089_u = 64;
/*     */     
/*  55 */     this.Cloak1 = new ModelRenderer(this, 0, 47);
/*  56 */     this.Cloak1.func_78789_a(-5.0F, 1.5F, 4.0F, 10, 12, 1);
/*  57 */     this.Cloak1.func_78793_a(0.0F, 0.0F, -2.5F);
/*  58 */     this.Cloak1.func_78787_b(128, 64);
/*  59 */     setRotation(this.Cloak1, 0.1396263F, 0.0F, 0.0F);
/*  60 */     this.Cloak3 = new ModelRenderer(this, 0, 37);
/*  61 */     this.Cloak3.func_78789_a(-5.0F, 17.5F, -0.8F, 10, 4, 1);
/*  62 */     this.Cloak3.func_78793_a(0.0F, 0.0F, -2.5F);
/*  63 */     this.Cloak3.func_78787_b(128, 64);
/*  64 */     setRotation(this.Cloak3, 0.4465716F, 0.0F, 0.0F);
/*  65 */     this.Cloak2 = new ModelRenderer(this, 0, 59);
/*  66 */     this.Cloak2.func_78789_a(-5.0F, 13.5F, 1.7F, 10, 4, 1);
/*  67 */     this.Cloak2.func_78793_a(0.0F, 0.0F, -2.5F);
/*  68 */     this.Cloak2.func_78787_b(128, 64);
/*  69 */     setRotation(this.Cloak2, 0.3069452F, 0.0F, 0.0F);
/*  70 */     this.CloakCL = new ModelRenderer(this, 0, 43);
/*  71 */     this.CloakCL.func_78789_a(3.0F, 0.5F, 2.0F, 2, 1, 3);
/*  72 */     this.CloakCL.func_78793_a(0.0F, 0.0F, -2.5F);
/*  73 */     this.CloakCL.func_78787_b(128, 64);
/*  74 */     setRotation(this.CloakCL, 0.1396263F, 0.0F, 0.0F);
/*  75 */     this.CloakCR = new ModelRenderer(this, 0, 43);
/*  76 */     this.CloakCR.func_78789_a(-5.0F, 0.5F, 2.0F, 2, 1, 3);
/*  77 */     this.CloakCR.func_78793_a(0.0F, 0.0F, -2.5F);
/*  78 */     this.CloakCR.func_78787_b(128, 64);
/*  79 */     setRotation(this.CloakCR, 0.1396263F, 0.0F, 0.0F);
/*     */     
/*  81 */     this.Head = new ModelRenderer(this, 47, 12);
/*  82 */     this.Head.func_78789_a(-3.5F, -6.0F, -2.5F, 7, 7, 5);
/*  83 */     this.Head.func_78793_a(0.0F, 4.5F, -3.8F);
/*  84 */     this.Head.func_78787_b(128, 64);
/*  85 */     setRotation(this.Head, -0.1047198F, 0.0F, 0.0F);
/*     */     
/*  87 */     this.Head2 = new ModelRenderer(this, 26, 16);
/*  88 */     this.Head2.func_78789_a(-2.0F, -2.0F, -2.0F, 4, 4, 4);
/*  89 */     this.Head2.func_78793_a(0.0F, 0.0F, -5.0F);
/*  90 */     this.Head2.func_78787_b(128, 64);
/*  91 */     setRotation(this.Head2, -0.1047198F, 0.0F, 0.0F);
/*     */     
/*  93 */     this.CollarL = new ModelRenderer(this, 75, 50);
/*  94 */     this.CollarL.func_78789_a(3.5F, -0.5F, -7.0F, 1, 4, 10);
/*  95 */     this.CollarL.func_78793_a(0.0F, 0.0F, -2.5F);
/*  96 */     this.CollarL.func_78787_b(128, 64);
/*  97 */     setRotation(this.CollarL, 0.837758F, 0.0F, 0.0F);
/*  98 */     this.CollarR = new ModelRenderer(this, 67, 50);
/*  99 */     this.CollarR.func_78789_a(-4.5F, -0.5F, -7.0F, 1, 4, 10);
/* 100 */     this.CollarR.func_78793_a(0.0F, 0.0F, -2.5F);
/* 101 */     this.CollarR.func_78787_b(128, 64);
/* 102 */     setRotation(this.CollarR, 0.837758F, 0.0F, 0.0F);
/* 103 */     this.CollarB = new ModelRenderer(this, 77, 59);
/* 104 */     this.CollarB.func_78789_a(-3.5F, -0.5F, 2.0F, 7, 4, 1);
/* 105 */     this.CollarB.func_78793_a(0.0F, 0.0F, -2.5F);
/* 106 */     this.CollarB.func_78787_b(128, 64);
/* 107 */     setRotation(this.CollarB, 0.837758F, 0.0F, 0.0F);
/* 108 */     this.CollarF = new ModelRenderer(this, 77, 59);
/* 109 */     this.CollarF.func_78789_a(-3.5F, -0.5F, -7.0F, 7, 4, 1);
/* 110 */     this.CollarF.func_78793_a(0.0F, 0.0F, -2.5F);
/* 111 */     this.CollarF.func_78787_b(128, 64);
/* 112 */     setRotation(this.CollarF, 0.837758F, 0.0F, 0.0F);
/* 113 */     this.CollarBlack = new ModelRenderer(this, 22, 0);
/* 114 */     this.CollarBlack.func_78789_a(-3.5F, 0.0F, -6.0F, 7, 1, 8);
/* 115 */     this.CollarBlack.func_78793_a(0.0F, 0.0F, -2.5F);
/* 116 */     this.CollarBlack.func_78787_b(128, 64);
/* 117 */     setRotation(this.CollarBlack, 0.837758F, 0.0F, 0.0F);
/*     */     
/* 119 */     this.Frontcloth0 = new ModelRenderer(this, 114, 52);
/* 120 */     this.Frontcloth0.func_78789_a(-3.0F, 3.2F, -3.5F, 6, 10, 1);
/* 121 */     this.Frontcloth0.func_78793_a(0.0F, 0.0F, -2.5F);
/* 122 */     this.Frontcloth0.func_78787_b(114, 64);
/* 123 */     setRotation(this.Frontcloth0, 0.1745329F, 0.0F, 0.0F);
/* 124 */     this.Frontcloth1 = new ModelRenderer(this, 114, 39);
/* 125 */     this.Frontcloth1.func_78789_a(-1.0F, 1.5F, -3.5F, 6, 6, 1);
/* 126 */     this.Frontcloth1.func_78793_a(-2.0F, 12.0F, 0.0F);
/* 127 */     this.Frontcloth1.func_78787_b(114, 64);
/* 128 */     setRotation(this.Frontcloth1, -0.1047198F, 0.0F, 0.0F);
/* 129 */     this.Frontcloth2 = new ModelRenderer(this, 114, 47);
/* 130 */     this.Frontcloth2.func_78789_a(-1.0F, 8.5F, -1.5F, 6, 3, 1);
/* 131 */     this.Frontcloth2.func_78793_a(-2.0F, 11.0F, 0.0F);
/* 132 */     this.Frontcloth2.func_78787_b(114, 64);
/* 133 */     setRotation(this.Frontcloth2, -0.3316126F, 0.0F, 0.0F);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 142 */     this.Torso = new ModelRenderer(this, 34, 45);
/* 143 */     this.Torso.func_78789_a(-5.0F, 2.5F, -3.0F, 10, 10, 6);
/* 144 */     this.Torso.func_78793_a(0.0F, 0.0F, -2.5F);
/* 145 */     this.Torso.func_78787_b(128, 64);
/* 146 */     this.Torso.field_78809_i = true;
/* 147 */     setRotation(this.Torso, 0.1745329F, 0.0F, 0.0F);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 153 */     this.ArmR = new ModelRenderer(this, 78, 32);
/* 154 */     this.ArmR.func_78789_a(-3.5F, 1.5F, -2.0F, 4, 13, 5);
/* 155 */     this.ArmR.func_78793_a(-5.0F, 3.0F, -2.0F);
/* 156 */     this.ArmR.func_78787_b(128, 64);
/* 157 */     setRotation(this.ArmR, 0.0F, 0.0F, 0.1047198F);
/*     */     
/* 159 */     this.ShoulderR1 = new ModelRenderer(this, 0, 23);
/* 160 */     this.ShoulderR1.func_78789_a(-3.3F, 4.0F, -2.5F, 1, 2, 6);
/* 161 */     this.ShoulderR1.func_78787_b(128, 64);
/* 162 */     setRotation(this.ShoulderR1, 0.0F, 0.0F, 1.186824F);
/* 163 */     this.ShoulderR = new ModelRenderer(this, 0, 0);
/* 164 */     this.ShoulderR.func_78789_a(-4.3F, -1.0F, -3.0F, 4, 5, 7);
/* 165 */     this.ShoulderR.func_78787_b(128, 64);
/* 166 */     setRotation(this.ShoulderR, 0.0F, 0.0F, 1.186824F);
/* 167 */     this.ShoulderR2 = new ModelRenderer(this, 0, 12);
/* 168 */     this.ShoulderR2.func_78789_a(-2.3F, 4.0F, -3.0F, 2, 3, 7);
/* 169 */     this.ShoulderR2.func_78787_b(128, 64);
/* 170 */     setRotation(this.ShoulderR2, 0.0F, 0.0F, 1.186824F);
/* 171 */     this.ShoulderR0 = new ModelRenderer(this, 56, 31);
/* 172 */     this.ShoulderR0.func_78789_a(-4.5F, -1.5F, -2.5F, 5, 6, 6);
/* 173 */     this.ShoulderR0.func_78787_b(128, 64);
/* 174 */     setRotation(this.ShoulderR0, 0.0F, 0.0F, 0.0F);
/*     */     
/*     */ 
/* 177 */     this.ArmL = new ModelRenderer(this, 78, 32);
/* 178 */     this.ArmL.field_78809_i = true;
/* 179 */     this.ArmL.func_78789_a(-0.5F, 1.5F, -2.0F, 4, 13, 5);
/* 180 */     this.ArmL.func_78793_a(5.0F, 3.0F, -2.0F);
/* 181 */     this.ArmL.func_78787_b(128, 64);
/* 182 */     setRotation(this.ArmL, 0.0F, 0.0F, -0.1047198F);
/* 183 */     this.ShoulderL1 = new ModelRenderer(this, 0, 23);
/* 184 */     this.ShoulderL1.field_78809_i = true;
/* 185 */     this.ShoulderL1.func_78789_a(2.3F, 4.0F, -2.5F, 1, 2, 6);
/* 186 */     this.ShoulderL1.func_78787_b(128, 64);
/* 187 */     setRotation(this.ShoulderL1, 0.0F, 0.0F, -1.186824F);
/* 188 */     this.ShoulderL0 = new ModelRenderer(this, 56, 31);
/* 189 */     this.ShoulderL0.field_78809_i = true;
/* 190 */     this.ShoulderL0.func_78789_a(-0.5F, -1.5F, -2.5F, 5, 6, 6);
/* 191 */     this.ShoulderL0.func_78787_b(128, 64);
/* 192 */     setRotation(this.ShoulderL0, 0.0F, 0.0F, 0.0F);
/* 193 */     this.ShoulderL = new ModelRenderer(this, 0, 0);
/* 194 */     this.ShoulderL.field_78809_i = true;
/* 195 */     this.ShoulderL.func_78789_a(0.3F, -1.0F, -3.0F, 4, 5, 7);
/* 196 */     this.ShoulderL.func_78787_b(128, 64);
/* 197 */     setRotation(this.ShoulderL, 0.0F, 0.0F, -1.186824F);
/* 198 */     this.ShoulderL2 = new ModelRenderer(this, 0, 12);
/* 199 */     this.ShoulderL2.field_78809_i = true;
/* 200 */     this.ShoulderL2.func_78789_a(0.3F, 4.0F, -3.0F, 2, 3, 7);
/* 201 */     this.ShoulderL2.func_78787_b(128, 64);
/* 202 */     setRotation(this.ShoulderL2, 0.0F, 0.0F, -1.186824F);
/*     */     
/* 204 */     this.BackpanelR1 = new ModelRenderer(this, 96, 7);
/* 205 */     this.BackpanelR1.func_78789_a(0.0F, 2.5F, -2.5F, 2, 2, 5);
/* 206 */     this.BackpanelR1.func_78793_a(-2.0F, 12.0F, 0.0F);
/* 207 */     this.BackpanelR1.func_78787_b(128, 64);
/* 208 */     setRotation(this.BackpanelR1, 0.0F, 0.0F, 0.1396263F);
/*     */     
/* 210 */     this.WaistR1 = new ModelRenderer(this, 96, 14);
/* 211 */     this.WaistR1.func_78789_a(-3.0F, -0.5F, -2.5F, 5, 3, 5);
/* 212 */     this.WaistR1.func_78793_a(-2.0F, 12.0F, 0.0F);
/* 213 */     this.WaistR1.func_78787_b(128, 64);
/* 214 */     setRotation(this.WaistR1, 0.0F, 0.0F, 0.1396263F);
/*     */     
/* 216 */     this.WaistR2 = new ModelRenderer(this, 116, 13);
/* 217 */     this.WaistR2.func_78789_a(-3.0F, 2.5F, -2.5F, 1, 4, 5);
/* 218 */     this.WaistR2.func_78793_a(-2.0F, 12.0F, 0.0F);
/* 219 */     this.WaistR2.func_78787_b(128, 64);
/* 220 */     setRotation(this.WaistR2, 0.0F, 0.0F, 0.1396263F);
/*     */     
/*     */ 
/* 223 */     this.WaistR3 = new ModelRenderer(this, 114, 5);
/* 224 */     this.WaistR3.field_78809_i = true;
/* 225 */     this.WaistR3.func_78789_a(-2.0F, 2.5F, -2.5F, 2, 3, 5);
/* 226 */     this.WaistR3.func_78793_a(-2.0F, 12.0F, 0.0F);
/* 227 */     this.WaistR3.func_78787_b(128, 64);
/* 228 */     setRotation(this.WaistR3, 0.0F, 0.0F, 0.1396263F);
/*     */     
/* 230 */     this.LegR = new ModelRenderer(this, 79, 19);
/* 231 */     this.LegR.func_78789_a(-2.5F, 2.5F, -2.0F, 4, 9, 4);
/* 232 */     this.LegR.func_78793_a(-2.0F, 12.5F, 0.0F);
/* 233 */     this.LegR.func_78787_b(128, 64);
/* 234 */     setRotation(this.LegR, 0.0F, 0.0F, 0.0F);
/*     */     
/* 236 */     this.WaistL1 = new ModelRenderer(this, 96, 14);
/* 237 */     this.WaistL1.func_78789_a(-2.0F, -0.5F, -2.5F, 5, 3, 5);
/* 238 */     this.WaistL1.func_78793_a(2.0F, 12.0F, 0.0F);
/* 239 */     this.WaistL1.func_78787_b(128, 64);
/* 240 */     this.WaistL1.field_78809_i = true;
/* 241 */     setRotation(this.WaistL1, 0.0F, 0.0F, -0.1396263F);
/* 242 */     this.WaistL2 = new ModelRenderer(this, 116, 13);
/* 243 */     this.WaistL2.func_78789_a(2.0F, 2.5F, -2.5F, 1, 4, 5);
/* 244 */     this.WaistL2.func_78793_a(2.0F, 12.0F, 0.0F);
/* 245 */     this.WaistL2.func_78787_b(128, 64);
/* 246 */     this.WaistL2.field_78809_i = true;
/* 247 */     setRotation(this.WaistL2, 0.0F, 0.0F, -0.1396263F);
/* 248 */     this.WaistL3 = new ModelRenderer(this, 114, 5);
/* 249 */     this.WaistL3.func_78789_a(0.0F, 2.5F, -2.5F, 2, 3, 5);
/* 250 */     this.WaistL3.func_78793_a(2.0F, 12.0F, 0.0F);
/* 251 */     this.WaistL3.func_78787_b(128, 64);
/* 252 */     this.WaistL3.field_78809_i = true;
/* 253 */     setRotation(this.WaistL3, 0.0F, 0.0F, -0.1396263F);
/*     */     
/* 255 */     this.BackpanelL1 = new ModelRenderer(this, 96, 7);
/* 256 */     this.BackpanelL1.func_78789_a(-2.0F, 2.5F, -2.5F, 2, 2, 5);
/* 257 */     this.BackpanelL1.func_78793_a(2.0F, 12.0F, 0.0F);
/* 258 */     this.BackpanelL1.func_78787_b(128, 64);
/* 259 */     this.BackpanelL1.field_78809_i = true;
/* 260 */     setRotation(this.BackpanelL1, 0.0F, 0.0F, -0.1396263F);
/* 261 */     this.LegL = new ModelRenderer(this, 79, 19);
/* 262 */     this.LegL.func_78789_a(-1.5F, 2.5F, -2.0F, 4, 9, 4);
/* 263 */     this.LegL.func_78793_a(2.0F, 12.5F, 0.0F);
/* 264 */     this.LegL.func_78787_b(128, 64);
/* 265 */     this.LegL.field_78809_i = true;
/* 266 */     setRotation(this.LegL, 0.0F, 0.0F, 0.0F);
/*     */     
/*     */ 
/* 269 */     this.ArmL.func_78792_a(this.ShoulderL);
/* 270 */     this.ArmL.func_78792_a(this.ShoulderL0);
/* 271 */     this.ArmL.func_78792_a(this.ShoulderL1);
/* 272 */     this.ArmL.func_78792_a(this.ShoulderL2);
/*     */     
/* 274 */     this.ArmR.func_78792_a(this.ShoulderR);
/* 275 */     this.ArmR.func_78792_a(this.ShoulderR0);
/* 276 */     this.ArmR.func_78792_a(this.ShoulderR1);
/* 277 */     this.ArmR.func_78792_a(this.ShoulderR2);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
/*     */   {
/* 283 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 284 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */     
/* 286 */     float a = MathHelper.func_76134_b(f * 0.44F) * 1.4F * f1;
/* 287 */     float b = MathHelper.func_76134_b(f * 0.44F + 3.1415927F) * 1.4F * f1;
/* 288 */     float c = Math.min(a, b);
/*     */     
/* 290 */     this.Frontcloth1.field_78795_f = (c - 0.1047198F);
/* 291 */     this.Frontcloth2.field_78795_f = (c - 0.3316126F);
/*     */     
/* 293 */     this.Cloak1.field_78795_f = (-c / 3.0F + 0.1396263F);
/* 294 */     this.Cloak2.field_78795_f = (-c / 3.0F + 0.3069452F);
/* 295 */     this.Cloak3.field_78795_f = (-c / 3.0F + 0.4465716F);
/*     */     
/* 297 */     this.Frontcloth1.func_78785_a(f5);
/* 298 */     this.CollarL.func_78785_a(f5);
/* 299 */     this.CollarBlack.func_78785_a(f5);
/* 300 */     this.Cloak1.func_78785_a(f5);
/* 301 */     this.CloakCL.func_78785_a(f5);
/* 302 */     this.CloakCR.func_78785_a(f5);
/* 303 */     this.Cloak3.func_78785_a(f5);
/* 304 */     this.Cloak2.func_78785_a(f5);
/* 305 */     if (((entity instanceof EntityEldritchGolem)) && (!((EntityEldritchGolem)entity).isHeadless())) {
/* 306 */       this.Head.func_78785_a(f5);
/*     */     } else {
/* 308 */       this.Head2.func_78785_a(f5);
/*     */     }
/*     */     
/* 311 */     this.Frontcloth0.func_78785_a(f5);
/* 312 */     this.CollarB.func_78785_a(f5);
/* 313 */     this.Torso.func_78785_a(f5);
/* 314 */     this.CollarR.func_78785_a(f5);
/* 315 */     this.CollarF.func_78785_a(f5);
/* 316 */     this.Frontcloth1.func_78785_a(f5);
/*     */     
/* 318 */     this.ArmL.func_78785_a(f5);
/*     */     
/*     */ 
/*     */ 
/* 322 */     this.ArmR.func_78785_a(f5);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 327 */     this.BackpanelR1.func_78785_a(f5);
/* 328 */     this.WaistR1.func_78785_a(f5);
/* 329 */     this.WaistR2.func_78785_a(f5);
/* 330 */     this.WaistR3.func_78785_a(f5);
/* 331 */     this.LegR.func_78785_a(f5);
/* 332 */     this.WaistL1.func_78785_a(f5);
/* 333 */     this.WaistL2.func_78785_a(f5);
/* 334 */     this.WaistL3.func_78785_a(f5);
/* 335 */     this.Frontcloth2.func_78785_a(f5);
/* 336 */     this.BackpanelL1.func_78785_a(f5);
/* 337 */     this.LegL.func_78785_a(f5);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
/*     */   {
/* 343 */     super.func_78087_a(par1, par2, par3, par4, par5, par6, entity);
/*     */     
/* 345 */     if (((entity instanceof EntityEldritchGolem)) && (((EntityEldritchGolem)entity).getSpawnTimer() > 0)) {
/* 346 */       this.Head.field_78796_g = 0.0F;
/* 347 */       this.Head.field_78795_f = (((EntityEldritchGolem)entity).getSpawnTimer() / 2 / 57.295776F);
/*     */     } else {
/* 349 */       this.Head.field_78796_g = (par4 / 4.0F / 57.295776F);
/* 350 */       this.Head.field_78795_f = (par5 / 2.0F / 57.295776F);
/* 351 */       this.Head2.field_78796_g = (par4 / 57.295776F);
/* 352 */       this.Head2.field_78795_f = (par5 / 57.295776F);
/*     */     }
/*     */     
/* 355 */     this.LegR.field_78795_f = (MathHelper.func_76134_b(par1 * 0.4662F) * 1.4F * par2);
/* 356 */     this.LegL.field_78795_f = (MathHelper.func_76134_b(par1 * 0.4662F + 3.1415927F) * 1.4F * par2);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_78086_a(EntityLivingBase p_78086_1_, float par1, float par2, float par3)
/*     */   {
/* 362 */     EntityEldritchGolem golem = (EntityEldritchGolem)p_78086_1_;
/* 363 */     int i = golem.getAttackTimer();
/*     */     
/* 365 */     if (i > 0)
/*     */     {
/* 367 */       this.ArmR.field_78795_f = (-2.0F + 1.5F * doAbs(i - par3, 10.0F));
/* 368 */       this.ArmL.field_78795_f = (-2.0F + 1.5F * doAbs(i - par3, 10.0F));
/*     */     }
/*     */     else
/*     */     {
/* 372 */       this.ArmR.field_78795_f = (MathHelper.func_76134_b(par1 * 0.4F + 3.1415927F) * 2.0F * par2 * 0.5F);
/* 373 */       this.ArmL.field_78795_f = (MathHelper.func_76134_b(par1 * 0.4F) * 2.0F * par2 * 0.5F);
/*     */     }
/*     */   }
/*     */   
/*     */   private float doAbs(float p_78172_1_, float p_78172_2_)
/*     */   {
/* 379 */     return (Math.abs(p_78172_1_ % p_78172_2_ - p_78172_2_ * 0.5F) - p_78172_2_ * 0.25F) / (p_78172_2_ * 0.25F);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z)
/*     */   {
/* 384 */     model.field_78795_f = x;
/* 385 */     model.field_78796_g = y;
/* 386 */     model.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/entities/ModelEldritchGolem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */