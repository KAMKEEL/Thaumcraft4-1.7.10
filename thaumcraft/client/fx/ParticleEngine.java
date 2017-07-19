/*     */ package thaumcraft.client.fx;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
/*     */ import cpw.mods.fml.common.gameevent.TickEvent.Phase;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.Callable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ReportedException;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraftforge.client.event.RenderWorldLastEvent;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ public class ParticleEngine
/*     */ {
/*  32 */   public static ParticleEngine instance = new ParticleEngine();
/*     */   
/*  34 */   public static final ResourceLocation particleTexture = new ResourceLocation("thaumcraft", "textures/misc/particles.png");
/*     */   
/*  36 */   public static final ResourceLocation particleTexture2 = new ResourceLocation("thaumcraft", "textures/misc/particles2.png");
/*     */   
/*     */ 
/*     */   protected World worldObj;
/*     */   
/*  41 */   private HashMap<Integer, ArrayList<EntityFX>>[] particles = { new HashMap(), new HashMap(), new HashMap(), new HashMap() };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  47 */   private Random rand = new Random();
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public void onRenderWorldLast(RenderWorldLastEvent event) {
/*  52 */     float frame = event.partialTicks;
/*  53 */     Entity entity = Minecraft.func_71410_x().field_71439_g;
/*  54 */     TextureManager renderer = Minecraft.func_71410_x().field_71446_o;
/*  55 */     int dim = Minecraft.func_71410_x().field_71441_e.field_73011_w.field_76574_g;
/*     */     
/*  57 */     renderer.func_110577_a(particleTexture);
/*     */     
/*  59 */     GL11.glPushMatrix();
/*     */     
/*  61 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  62 */     GL11.glDepthMask(false);
/*  63 */     GL11.glEnable(3042);
/*  64 */     GL11.glAlphaFunc(516, 0.003921569F);
/*  65 */     boolean rebound = false;
/*  66 */     for (int layer = 0; layer < 4; layer++)
/*  67 */       if (this.particles[layer].containsKey(Integer.valueOf(dim))) {
/*  68 */         ArrayList<EntityFX> parts = (ArrayList)this.particles[layer].get(Integer.valueOf(dim));
/*  69 */         if (parts.size() != 0)
/*     */         {
/*  71 */           if ((!rebound) && (layer >= 2)) {
/*  72 */             renderer.func_110577_a(particleTexture2);
/*  73 */             rebound = true;
/*     */           }
/*     */           
/*  76 */           GL11.glPushMatrix();
/*     */           
/*  78 */           switch (layer) {
/*     */           case 0: case 2: 
/*  80 */             GL11.glBlendFunc(770, 1);
/*  81 */             break;
/*     */           case 1: case 3: 
/*  83 */             GL11.glBlendFunc(770, 771);
/*     */           }
/*     */           
/*     */           
/*  87 */           float f1 = ActiveRenderInfo.field_74588_d;
/*  88 */           float f2 = ActiveRenderInfo.field_74586_f;
/*  89 */           float f3 = ActiveRenderInfo.field_74587_g;
/*  90 */           float f4 = ActiveRenderInfo.field_74596_h;
/*  91 */           float f5 = ActiveRenderInfo.field_74589_e;
/*  92 */           EntityFX.field_70556_an = entity.field_70142_S + (entity.field_70165_t - entity.field_70142_S) * frame;
/*  93 */           EntityFX.field_70554_ao = entity.field_70137_T + (entity.field_70163_u - entity.field_70137_T) * frame;
/*  94 */           EntityFX.field_70555_ap = entity.field_70136_U + (entity.field_70161_v - entity.field_70136_U) * frame;
/*     */           
/*  96 */           Tessellator tessellator = Tessellator.field_78398_a;
/*  97 */           tessellator.func_78382_b();
/*     */           
/*  99 */           for (int j = 0; j < parts.size(); j++) {
/* 100 */             final EntityFX entityfx = (EntityFX)parts.get(j);
/* 101 */             if (entityfx != null) {
/* 102 */               tessellator.func_78380_c(entityfx.func_70070_b(frame));
/*     */               try
/*     */               {
/* 105 */                 entityfx.func_70539_a(tessellator, frame, f1, f5, f2, f3, f4);
/*     */               } catch (Throwable throwable) {
/* 107 */                 CrashReport crashreport = CrashReport.func_85055_a(throwable, "Rendering Particle");
/* 108 */                 CrashReportCategory crashreportcategory = crashreport.func_85058_a("Particle being rendered");
/* 109 */                 crashreportcategory.func_71500_a("Particle", new Callable()
/*     */                 {
/*     */                   public String call() {
/* 112 */                     return entityfx.toString();
/*     */                   }
/* 114 */                 });
/* 115 */                 crashreportcategory.func_71500_a("Particle Type", new Callable()
/*     */                 {
/*     */                   public String call() {
/* 118 */                     return "ENTITY_PARTICLE_TEXTURE";
/*     */                   }
/* 120 */                 });
/* 121 */                 throw new ReportedException(crashreport);
/*     */               }
/*     */             }
/*     */           }
/* 125 */           tessellator.func_78381_a();
/*     */           
/* 127 */           GL11.glPopMatrix();
/*     */         }
/*     */       }
/* 130 */     GL11.glDisable(3042);
/* 131 */     GL11.glDepthMask(true);
/* 132 */     GL11.glAlphaFunc(516, 0.1F);
/* 133 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public void addEffect(World world, EntityFX fx) {
/* 137 */     if (!this.particles[fx.func_70537_b()].containsKey(Integer.valueOf(world.field_73011_w.field_76574_g))) {
/* 138 */       this.particles[fx.func_70537_b()].put(Integer.valueOf(world.field_73011_w.field_76574_g), new ArrayList());
/*     */     }
/* 140 */     ArrayList<EntityFX> parts = (ArrayList)this.particles[fx.func_70537_b()].get(Integer.valueOf(world.field_73011_w.field_76574_g));
/* 141 */     if (parts.size() >= 2000) {
/* 142 */       parts.remove(0);
/*     */     }
/* 144 */     parts.add(fx);
/* 145 */     this.particles[fx.func_70537_b()].put(Integer.valueOf(world.field_73011_w.field_76574_g), parts);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public void updateParticles(TickEvent.ClientTickEvent event) {
/* 151 */     if (event.side == Side.SERVER) return;
/* 152 */     Minecraft mc = FMLClientHandler.instance().getClient();
/* 153 */     World world = mc.field_71441_e;
/* 154 */     if (mc.field_71441_e == null) return;
/* 155 */     int dim = world.field_73011_w.field_76574_g;
/* 156 */     if (event.phase == TickEvent.Phase.START) {
/* 157 */       for (int layer = 0; layer < 4; layer++) {
/* 158 */         if (this.particles[layer].containsKey(Integer.valueOf(dim))) {
/* 159 */           ArrayList<EntityFX> parts = (ArrayList)this.particles[layer].get(Integer.valueOf(dim));
/*     */           
/* 161 */           for (int j = 0; j < parts.size(); j++) {
/* 162 */             final EntityFX entityfx = (EntityFX)parts.get(j);
/*     */             try
/*     */             {
/* 165 */               if (entityfx != null) {
/* 166 */                 entityfx.func_70071_h_();
/*     */               }
/*     */             } catch (Throwable throwable) {
/* 169 */               CrashReport crashreport = CrashReport.func_85055_a(throwable, "Ticking Particle");
/*     */               
/* 171 */               CrashReportCategory crashreportcategory = crashreport.func_85058_a("Particle being ticked");
/*     */               
/* 173 */               crashreportcategory.func_71500_a("Particle", new Callable()
/*     */               {
/*     */                 public String call() {
/* 176 */                   return entityfx.toString();
/*     */                 }
/* 178 */               });
/* 179 */               crashreportcategory.func_71500_a("Particle Type", new Callable()
/*     */               {
/*     */                 public String call() {
/* 182 */                   return "ENTITY_PARTICLE_TEXTURE";
/*     */                 }
/* 184 */               });
/* 185 */               throw new ReportedException(crashreport);
/*     */             }
/*     */             
/* 188 */             if ((entityfx == null) || (entityfx.field_70128_L)) {
/* 189 */               parts.remove(j--);
/* 190 */               this.particles[layer].put(Integer.valueOf(dim), parts);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/fx/ParticleEngine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */