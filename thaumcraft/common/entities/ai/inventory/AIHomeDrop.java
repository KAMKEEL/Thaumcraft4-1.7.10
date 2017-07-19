/*     */ package thaumcraft.common.entities.ai.inventory;
/*     */ 
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.common.config.Config;
/*     */ import thaumcraft.common.entities.golems.EntityGolemBase;
/*     */ 
/*     */ public class AIHomeDrop extends EntityAIBase
/*     */ {
/*     */   private EntityGolemBase theGolem;
/*  15 */   private int countChest = 0;
/*     */   private IInventory inv;
/*     */   
/*     */   public AIHomeDrop(EntityGolemBase par1EntityCreature)
/*     */   {
/*  20 */     this.theGolem = par1EntityCreature;
/*  21 */     func_75248_a(3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  29 */     ChunkCoordinates home = this.theGolem.func_110172_bL();
/*     */     
/*  31 */     if ((this.theGolem.getCarried() == null) || (!this.theGolem.func_70661_as().func_75500_f()) || (this.theGolem.func_70092_e(home.field_71574_a + 0.5F, home.field_71572_b + 0.5F, home.field_71573_c + 0.5F) > 5.0D))
/*     */     {
/*  33 */       return false;
/*     */     }
/*     */     
/*  36 */     ForgeDirection facing = ForgeDirection.getOrientation(this.theGolem.homeFacing);
/*  37 */     int cX = home.field_71574_a - facing.offsetX;
/*  38 */     int cY = home.field_71572_b - facing.offsetY;
/*  39 */     int cZ = home.field_71573_c - facing.offsetZ;
/*     */     
/*  41 */     net.minecraft.tileentity.TileEntity tile = this.theGolem.field_70170_p.func_147438_o(cX, cY, cZ);
/*     */     
/*  43 */     if ((tile == null) || (!(tile instanceof IInventory))) {
/*  44 */       return true;
/*     */     }
/*     */     
/*  47 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  56 */     return (this.count > 0) && ((func_75250_a()) || (this.countChest > 0));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/*     */     try
/*     */     {
/*  65 */       if ((this.inv != null) && (Config.golemChestInteract)) this.inv.func_70305_f();
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*  70 */   int count = 0;
/*     */   
/*     */   public void func_75246_d()
/*     */   {
/*  74 */     this.countChest -= 1;
/*  75 */     this.count -= 1;
/*  76 */     super.func_75246_d();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/*  84 */     this.count = 200;
/*  85 */     ChunkCoordinates home = this.theGolem.func_110172_bL();
/*  86 */     ForgeDirection facing = ForgeDirection.getOrientation(this.theGolem.homeFacing);
/*  87 */     int cX = home.field_71574_a - facing.offsetX;
/*  88 */     int cY = home.field_71572_b - facing.offsetY;
/*  89 */     int cZ = home.field_71573_c - facing.offsetZ;
/*     */     
/*  91 */     EntityItem item = new EntityItem(this.theGolem.field_70170_p, this.theGolem.field_70165_t, this.theGolem.field_70163_u + this.theGolem.field_70131_O / 2.0F, this.theGolem.field_70161_v, this.theGolem.itemCarried.func_77946_l());
/*     */     
/*  93 */     if (item != null) {
/*  94 */       double distance = this.theGolem.func_70011_f(cX + 0.5D, cY + 0.5D, cZ + 0.5D);
/*  95 */       item.field_70159_w = ((cX + 0.5D - this.theGolem.field_70165_t) * (distance / 3.0D));
/*  96 */       item.field_70181_x = (0.1D + (cY + 0.5D - (this.theGolem.field_70163_u + this.theGolem.field_70131_O / 2.0F)) * (distance / 3.0D));
/*  97 */       item.field_70179_y = ((cZ + 0.5D - this.theGolem.field_70161_v) * (distance / 3.0D));
/*  98 */       item.field_145804_b = 10;
/*  99 */       this.theGolem.field_70170_p.func_72838_d(item);
/* 100 */       this.theGolem.itemCarried = null;
/* 101 */       this.theGolem.startActionTimer();
/* 102 */       this.theGolem.updateCarried();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/ai/inventory/AIHomeDrop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */