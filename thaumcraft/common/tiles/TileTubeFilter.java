/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.api.aspects.IAspectContainer;
/*    */ 
/*    */ public class TileTubeFilter
/*    */   extends TileTube implements IAspectContainer
/*    */ {
/* 11 */   public Aspect aspectFilter = null;
/*    */   
/*    */ 
/*    */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*    */   {
/* 16 */     super.readCustomNBT(nbttagcompound);
/* 17 */     this.aspectFilter = Aspect.getAspect(nbttagcompound.func_74779_i("AspectFilter"));
/*    */   }
/*    */   
/*    */ 
/*    */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*    */   {
/* 23 */     super.writeCustomNBT(nbttagcompound);
/* 24 */     if (this.aspectFilter != null) nbttagcompound.func_74778_a("AspectFilter", this.aspectFilter.getTag());
/*    */   }
/*    */   
/*    */   void calculateSuction(Aspect filter, boolean restrict, boolean dir)
/*    */   {
/* 29 */     super.calculateSuction(this.aspectFilter, restrict, dir);
/*    */   }
/*    */   
/*    */   public AspectList getAspects()
/*    */   {
/* 34 */     if (this.aspectFilter != null) return new AspectList().add(this.aspectFilter, -1);
/* 35 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */   public void setAspects(AspectList aspects) {}
/*    */   
/*    */   public boolean doesContainerAccept(Aspect tag)
/*    */   {
/* 43 */     return false;
/*    */   }
/*    */   
/*    */   public int addToContainer(Aspect tag, int amount)
/*    */   {
/* 48 */     return 0;
/*    */   }
/*    */   
/*    */   public boolean takeFromContainer(Aspect tag, int amount)
/*    */   {
/* 53 */     return false;
/*    */   }
/*    */   
/*    */   public boolean takeFromContainer(AspectList ot)
/*    */   {
/* 58 */     return false;
/*    */   }
/*    */   
/*    */   public boolean doesContainerContainAmount(Aspect tag, int amount)
/*    */   {
/* 63 */     return false;
/*    */   }
/*    */   
/*    */   public boolean doesContainerContain(AspectList ot)
/*    */   {
/* 68 */     return false;
/*    */   }
/*    */   
/*    */   public int containerContains(Aspect tag)
/*    */   {
/* 73 */     return 0;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileTubeFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */