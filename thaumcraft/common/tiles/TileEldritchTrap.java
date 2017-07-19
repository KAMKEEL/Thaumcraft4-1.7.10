/*    */ package thaumcraft.common.tiles;
/*    */ 
/*    */ import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
/*    */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.WorldProvider;
/*    */ import thaumcraft.common.Thaumcraft;
/*    */ import thaumcraft.common.lib.network.PacketHandler;
/*    */ import thaumcraft.common.lib.network.fx.PacketFXBlockZap;
/*    */ 
/*    */ public class TileEldritchTrap
/*    */   extends TileEntity
/*    */ {
/*    */   public boolean canUpdate()
/*    */   {
/* 21 */     return true;
/*    */   }
/*    */   
/* 24 */   int count = 20;
/*    */   
/*    */   public void func_145845_h()
/*    */   {
/* 28 */     super.func_145845_h();
/*    */     
/* 30 */     if ((!this.field_145850_b.field_72995_K) && (this.count-- <= 0))
/*    */     {
/* 32 */       this.count = (10 + this.field_145850_b.field_73012_v.nextInt(25));
/* 33 */       EntityPlayer p = this.field_145850_b.func_72977_a(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, 3.0D);
/* 34 */       if (p != null) {
/* 35 */         p.func_70097_a(DamageSource.field_76376_m, 2.0F);
/* 36 */         if (this.field_145850_b.field_73012_v.nextBoolean()) {
/* 37 */           Thaumcraft.addWarpToPlayer(p, 1 + this.field_145850_b.field_73012_v.nextInt(2), true);
/*    */         }
/* 39 */         PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockZap(this.field_145851_c + 0.5F, this.field_145848_d + 0.5F, this.field_145849_e + 0.5F, (float)p.field_70165_t, (float)p.field_70121_D.field_72338_b + p.eyeHeight, (float)p.field_70161_v), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.field_76574_g, this.field_145851_c, this.field_145848_d, this.field_145849_e, 32.0D));
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileEldritchTrap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */