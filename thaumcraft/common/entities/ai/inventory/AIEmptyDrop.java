/*    */ package thaumcraft.common.entities.ai.inventory;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import net.minecraft.entity.ai.EntityAIBase;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.util.ChunkCoordinates;
/*    */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*    */ import thaumcraft.common.entities.golems.GolemHelper;
/*    */ 
/*    */ public class AIEmptyDrop extends EntityAIBase
/*    */ {
/*    */   private EntityGolemBase theGolem;
/*    */   
/*    */   public AIEmptyDrop(EntityGolemBase par1EntityCreature)
/*    */   {
/* 17 */     this.theGolem = par1EntityCreature;
/* 18 */     func_75248_a(3);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75250_a()
/*    */   {
/* 26 */     if ((this.theGolem.itemCarried == null) || (!this.theGolem.func_70661_as().func_75500_f())) {
/* 27 */       return false;
/*    */     }
/*    */     
/* 30 */     ChunkCoordinates home = this.theGolem.func_110172_bL();
/*    */     
/* 32 */     ArrayList<Byte> matchingColors = this.theGolem.getColorsMatching(this.theGolem.itemCarried);
/* 33 */     for (Iterator i$ = matchingColors.iterator(); i$.hasNext();) { byte color = ((Byte)i$.next()).byteValue();
/* 34 */       ArrayList<ChunkCoordinates> mc = GolemHelper.getMarkedBlocksAdjacentToGolem(this.theGolem.field_70170_p, this.theGolem, color);
/* 35 */       for (ChunkCoordinates cc : mc) {
/* 36 */         if (cc != home) { return true;
/*    */         }
/*    */       }
/*    */     }
/* 40 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean func_75253_b()
/*    */   {
/* 49 */     return (this.count > 0) && (func_75250_a());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 60 */   int count = 0;
/*    */   
/*    */   public void func_75251_c() {}
/*    */   
/* 64 */   public void func_75246_d() { this.count -= 1;
/* 65 */     super.func_75246_d();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_75249_e()
/*    */   {
/* 73 */     this.count = 200;
/* 74 */     ChunkCoordinates home = this.theGolem.func_110172_bL();
/*    */     
/* 76 */     ArrayList<Byte> matchingColors = this.theGolem.getColorsMatching(this.theGolem.itemCarried);
/* 77 */     for (Iterator i$ = matchingColors.iterator(); i$.hasNext();) { byte color = ((Byte)i$.next()).byteValue();
/* 78 */       ArrayList<ChunkCoordinates> mc = GolemHelper.getMarkedBlocksAdjacentToGolem(this.theGolem.field_70170_p, this.theGolem, color);
/* 79 */       for (ChunkCoordinates cc : mc) {
/* 80 */         if (cc != home)
/*    */         {
/* 82 */           EntityItem item = new EntityItem(this.theGolem.field_70170_p, this.theGolem.field_70165_t, this.theGolem.field_70163_u + this.theGolem.field_70131_O / 2.0F, this.theGolem.field_70161_v, this.theGolem.itemCarried.func_77946_l());
/*    */           
/* 84 */           if (item != null) {
/* 85 */             double distance = this.theGolem.func_70011_f(cc.field_71574_a + 0.5D, cc.field_71572_b + 0.5D, cc.field_71573_c + 0.5D);
/* 86 */             item.field_70159_w = ((cc.field_71574_a + 0.5D - this.theGolem.field_70165_t) * (distance / 3.0D));
/* 87 */             item.field_70181_x = (0.1D + (cc.field_71572_b + 0.5D - (this.theGolem.field_70163_u + this.theGolem.field_70131_O / 2.0F)) * (distance / 3.0D));
/* 88 */             item.field_70179_y = ((cc.field_71573_c + 0.5D - this.theGolem.field_70161_v) * (distance / 3.0D));
/* 89 */             item.field_145804_b = 10;
/* 90 */             this.theGolem.field_70170_p.func_72838_d(item);
/* 91 */             this.theGolem.itemCarried = null;
/* 92 */             this.theGolem.startActionTimer();
/*    */             break label365;
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */     label365:
/* 99 */     this.theGolem.updateCarried();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/inventory/AIEmptyDrop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */