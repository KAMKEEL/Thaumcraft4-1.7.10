/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileManaPod
/*     */   extends TileThaumcraft
/*     */   implements IAspectContainer
/*     */ {
/*  22 */   public Aspect aspect = null;
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/*  26 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  32 */     this.aspect = Aspect.getAspect(nbttagcompound.func_74779_i("aspect"));
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  38 */     if (this.aspect != null) nbttagcompound.func_74778_a("aspect", this.aspect.getTag());
/*     */   }
/*     */   
/*     */   public void checkGrowth()
/*     */   {
/*  43 */     int l = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     
/*  45 */     if (l < 7) {
/*  46 */       l++;
/*  47 */       this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, l, 3);
/*     */     }
/*     */     
/*  50 */     if (l > 2) {
/*  51 */       if (l == 3) {
/*  52 */         AspectList al = new AspectList();
/*  53 */         if (this.aspect != null) al.add(this.aspect, 1);
/*  54 */         for (int d = 2; d < 6; d++) {
/*  55 */           ForgeDirection dir = ForgeDirection.getOrientation(d);
/*  56 */           int x = this.field_145851_c + dir.offsetX;
/*  57 */           int y = this.field_145848_d + dir.offsetY;
/*  58 */           int z = this.field_145849_e + dir.offsetZ;
/*  59 */           TileEntity tile = this.field_145850_b.func_147438_o(x, y, z);
/*  60 */           if ((tile != null) && ((tile instanceof TileManaPod)) && 
/*  61 */             (((TileManaPod)tile).aspect != null)) {
/*  62 */             al.add(((TileManaPod)tile).aspect, 1);
/*     */           }
/*     */         }
/*     */         
/*  66 */         if (al.size() > 1) {
/*  67 */           Aspect[] aa = al.getAspects();
/*  68 */           ArrayList<Aspect> outlist = new ArrayList();
/*  69 */           for (int i = 0; i < aa.length; i++) {
/*  70 */             outlist.add(aa[i]);
/*  71 */             for (int j = 0; j < aa.length; j++)
/*  72 */               if (i != j) {
/*  73 */                 Aspect combo = ResearchManager.getCombinationResult(aa[i], aa[j]);
/*  74 */                 if (combo != null) {
/*  75 */                   outlist.add(combo);
/*  76 */                   outlist.add(combo);
/*     */                 }
/*     */               }
/*     */           }
/*  80 */           if (outlist.size() > 0) {
/*  81 */             this.aspect = ((Aspect)outlist.get(this.field_145850_b.field_73012_v.nextInt(outlist.size())));
/*  82 */             func_70296_d();
/*     */           }
/*     */         }
/*     */         
/*  86 */         if ((al.size() >= 1) && (this.aspect == null)) {
/*  87 */           this.aspect = al.getAspectsSortedAmount()[0];
/*  88 */           func_70296_d();
/*     */         }
/*     */       }
/*     */       
/*  92 */       if (this.aspect == null) {
/*  93 */         if (this.field_145850_b.field_73012_v.nextInt(8) == 0) {
/*  94 */           this.aspect = Aspect.PLANT;
/*     */         } else {
/*  96 */           ArrayList<Aspect> outlist = Aspect.getPrimalAspects();
/*  97 */           this.aspect = ((Aspect)outlist.get(this.field_145850_b.field_73012_v.nextInt(outlist.size())));
/*     */         }
/*  99 */         func_70296_d();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public AspectList getAspects()
/*     */   {
/* 107 */     return (this.aspect != null) && (func_145832_p() == 7) ? new AspectList().add(this.aspect, 1) : null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAspects(AspectList aspects) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean doesContainerAccept(Aspect tag)
/*     */   {
/* 119 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int addToContainer(Aspect tag, int amount)
/*     */   {
/* 125 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean takeFromContainer(Aspect tag, int amount)
/*     */   {
/* 131 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean takeFromContainer(AspectList ot)
/*     */   {
/* 137 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean doesContainerContainAmount(Aspect tag, int amount)
/*     */   {
/* 143 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean doesContainerContain(AspectList ot)
/*     */   {
/* 149 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int containerContains(Aspect tag)
/*     */   {
/* 155 */     return 0;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileManaPod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */