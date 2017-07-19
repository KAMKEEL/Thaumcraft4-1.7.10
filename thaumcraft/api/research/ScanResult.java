/*    */ package thaumcraft.api.research;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ public class ScanResult {
/*  6 */   public byte type = 0;
/*    */   
/*    */   public int id;
/*    */   public int meta;
/*    */   public Entity entity;
/*    */   public String phenomena;
/*    */   
/*    */   public ScanResult(byte type, int blockId, int blockMeta, Entity entity, String phenomena)
/*    */   {
/* 15 */     this.type = type;
/* 16 */     this.id = blockId;
/* 17 */     this.meta = blockMeta;
/* 18 */     this.entity = entity;
/* 19 */     this.phenomena = phenomena;
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj)
/*    */   {
/* 24 */     if ((obj instanceof ScanResult)) {
/* 25 */       ScanResult sr = (ScanResult)obj;
/* 26 */       if (this.type != sr.type)
/* 27 */         return false;
/* 28 */       if ((this.type == 1) && ((this.id != sr.id) || (this.meta != sr.meta)))
/*    */       {
/* 30 */         return false; }
/* 31 */       if ((this.type == 2) && (this.entity.func_145782_y() != sr.entity.func_145782_y()))
/* 32 */         return false;
/* 33 */       if ((this.type == 3) && (!this.phenomena.equals(sr.phenomena)))
/* 34 */         return false;
/*    */     }
/* 36 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/research/ScanResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */