/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.nbt.NBTTagList;
/*    */ import thaumcraft.api.TileThaumcraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileOwned
/*    */   extends TileThaumcraft
/*    */ {
/* 13 */   public String owner = "";
/* 14 */   public ArrayList<String> accessList = new ArrayList();
/*    */   
/* 16 */   public boolean safeToRemove = false;
/*    */   
/*    */   public boolean canUpdate()
/*    */   {
/* 20 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_145839_a(NBTTagCompound nbttagcompound)
/*    */   {
/* 26 */     super.func_145839_a(nbttagcompound);
/* 27 */     this.owner = nbttagcompound.func_74779_i("owner");
/*    */     
/* 29 */     NBTTagList var2 = nbttagcompound.func_150295_c("access", 10);
/* 30 */     this.accessList = new ArrayList();
/*    */     
/* 32 */     for (int var3 = 0; var3 < var2.func_74745_c(); var3++)
/*    */     {
/* 34 */       NBTTagCompound var4 = var2.func_150305_b(var3);
/* 35 */       this.accessList.add(var4.func_74779_i("name"));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public void func_145841_b(NBTTagCompound nbttagcompound)
/*    */   {
/* 42 */     super.func_145841_b(nbttagcompound);
/*    */     
/*    */ 
/* 45 */     NBTTagList var2 = new NBTTagList();
/*    */     
/* 47 */     for (int var3 = 0; var3 < this.accessList.size(); var3++)
/*    */     {
/* 49 */       NBTTagCompound var4 = new NBTTagCompound();
/* 50 */       var4.func_74778_a("name", (String)this.accessList.get(var3));
/* 51 */       var2.func_74742_a(var4);
/*    */     }
/* 53 */     nbttagcompound.func_74782_a("access", var2);
/*    */   }
/*    */   
/*    */ 
/*    */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*    */   {
/* 59 */     this.owner = nbttagcompound.func_74779_i("owner");
/*    */   }
/*    */   
/*    */ 
/*    */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*    */   {
/* 65 */     nbttagcompound.func_74778_a("owner", this.owner);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileOwned.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */