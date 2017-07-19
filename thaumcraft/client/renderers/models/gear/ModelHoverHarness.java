/*     */ package thaumcraft.client.renderers.models.gear;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.HashMap;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.model.IModelCustom;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.fx.bolt.FXLightningBolt;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.items.armor.ItemHoverHarness;
/*     */ 
/*     */ @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */ public class ModelHoverHarness extends ModelBiped
/*     */ {
/*  29 */   HashMap<Integer, Long> timingShock = new HashMap();
/*  30 */   private static final ResourceLocation HARNESS = new ResourceLocation("thaumcraft", "textures/models/hoverharness.obj");
/*     */   private IModelCustom modelBack;
/*     */   
/*     */   public ModelHoverHarness()
/*     */   {
/*  35 */     this.field_78115_e = new ModelRenderer(this, 16, 16);
/*  36 */     this.field_78115_e.func_78790_a(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.6F);
/*  37 */     this.modelBack = net.minecraftforge.client.model.AdvancedModelLoader.loadModel(HARNESS);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_78088_a(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7)
/*     */   {
/*  45 */     GL11.glPushMatrix();
/*  46 */     GL11.glPushMatrix();
/*  47 */     if ((entity != null) && (entity.func_70093_af())) {
/*  48 */       GL11.glRotatef(28.64789F, 1.0F, 0.0F, 0.0F);
/*     */     }
/*  50 */     this.field_78115_e.func_78785_a(par7);
/*  51 */     GL11.glPopMatrix();
/*     */     
/*  53 */     GL11.glPushMatrix();
/*  54 */     GL11.glDisable(2896);
/*  55 */     GL11.glScalef(0.1F, 0.1F, 0.1F);
/*  56 */     GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
/*  57 */     if ((entity != null) && (entity.func_70093_af())) {
/*  58 */       GL11.glRotatef(28.64789F, 1.0F, 0.0F, 0.0F);
/*     */     }
/*  60 */     GL11.glTranslatef(0.0F, 0.33F, -3.7F);
/*     */     
/*  62 */     FMLClientHandler.instance().getClient().field_71446_o.func_110577_a(new ResourceLocation("thaumcraft", "textures/models/hoverharness2.png"));
/*  63 */     this.modelBack.renderAll();
/*  64 */     GL11.glEnable(2896);
/*  65 */     GL11.glPopMatrix();
/*     */     
/*  67 */     if ((entity != null) && ((entity instanceof EntityPlayer)) && (!GL11.glIsEnabled(3042)) && (GL11.glGetInteger(2976) == 5888) && (((EntityPlayer)entity).field_71071_by.func_70440_f(2).func_77942_o()) && (((EntityPlayer)entity).field_71071_by.func_70440_f(2).field_77990_d.func_74764_b("hover")) && (((EntityPlayer)entity).field_71071_by.func_70440_f(2).field_77990_d.func_74771_c("hover") == 1))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  73 */       long currenttime = System.currentTimeMillis();
/*  74 */       long timeShock = 0L;
/*  75 */       if (this.timingShock.get(Integer.valueOf(entity.func_145782_y())) != null) { timeShock = ((Long)this.timingShock.get(Integer.valueOf(entity.func_145782_y()))).longValue();
/*     */       }
/*  77 */       GL11.glPushMatrix();
/*  78 */       float mod = 0.0F;
/*  79 */       if (entity.func_70093_af()) {
/*  80 */         GL11.glRotatef(28.64789F, 1.0F, 0.0F, 0.0F);
/*  81 */         GL11.glTranslatef(0.0F, 0.075F, -0.05F);
/*  82 */         mod = 0.075F;
/*     */       }
/*  84 */       GL11.glTranslatef(0.0F, 0.2F, 0.55F);
/*  85 */       GL11.glPushMatrix();
/*  86 */       UtilsFX.renderQuadCenteredFromIcon(false, ((ItemHoverHarness)((EntityPlayer)entity).field_71071_by.func_70440_f(2).func_77973_b()).iconLightningRing, 2.5F, 1.0F, 1.0F, 1.0F, 230, 1, 1.0F);
/*     */       
/*     */ 
/*  89 */       GL11.glPopMatrix();
/*  90 */       GL11.glPushMatrix();
/*  91 */       GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*  92 */       GL11.glTranslatef(0.0F, 0.0F, 0.03F);
/*  93 */       UtilsFX.renderQuadCenteredFromIcon(false, ((ItemHoverHarness)((EntityPlayer)entity).field_71071_by.func_70440_f(2).func_77973_b()).iconLightningRing, 1.5F, 1.0F, 0.5F, 1.0F, 230, 1, 1.0F);
/*     */       
/*     */ 
/*  96 */       GL11.glPopMatrix();
/*  97 */       GL11.glPopMatrix();
/*     */       
/*     */ 
/* 100 */       if (timeShock < currenttime) {
/* 101 */         timeShock = currenttime + 50L + entity.field_70170_p.field_73012_v.nextInt(50);
/* 102 */         this.timingShock.put(Integer.valueOf(entity.func_145782_y()), Long.valueOf(timeShock));
/*     */         
/* 104 */         MovingObjectPosition mop = thaumcraft.common.lib.utils.BlockUtils.getTargetBlock(entity.field_70170_p, entity.field_70165_t, entity.field_70163_u - 0.44999998807907104D - mod, entity.field_70161_v, ((EntityPlayer)entity).field_70761_aq - 90.0F - entity.field_70170_p.field_73012_v.nextInt(180), -80 + entity.field_70170_p.field_73012_v.nextInt(160), false, 6.0D);
/*     */         
/*     */ 
/*     */ 
/* 108 */         if (mop != null)
/*     */         {
/* 110 */           double px = mop.field_72307_f.field_72450_a;
/* 111 */           double py = mop.field_72307_f.field_72448_b;
/* 112 */           double pz = mop.field_72307_f.field_72449_c;
/*     */           
/* 114 */           FXLightningBolt bolt = new FXLightningBolt(entity.field_70170_p, entity.field_70165_t - MathHelper.func_76134_b((((EntityPlayer)entity).field_70761_aq + 90.0F) / 180.0F * 3.141593F) * 0.5F, entity.field_70163_u - 0.44999998807907104D - mod, entity.field_70161_v - MathHelper.func_76126_a((((EntityPlayer)entity).field_70761_aq + 90.0F) / 180.0F * 3.141593F) * 0.5F, px, py, pz, entity.field_70170_p.field_73012_v.nextLong(), 1, 2.0F, 3);
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 120 */           bolt.defaultFractal();
/* 121 */           bolt.setType(6);
/* 122 */           bolt.setWidth(0.015F);
/* 123 */           bolt.finalizeBolt();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 129 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/client/renderers/models/gear/ModelHoverHarness.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */