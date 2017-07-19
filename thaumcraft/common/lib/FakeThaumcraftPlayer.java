/*    */ package thaumcraft.common.lib;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.ChunkCoordinates;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FakeThaumcraftPlayer
/*    */   extends EntityPlayer
/*    */ {
/* 15 */   public FakeThaumcraftPlayer(World world, GameProfile name) { super(world, name); }
/*    */   
/*    */   public void addChatMessage(String s) {}
/*    */   
/* 19 */   public boolean func_70003_b(int i, String s) { return false; }
/*    */   
/*    */   public ChunkCoordinates func_82114_b() {
/* 22 */     return new ChunkCoordinates(0, 0, 0);
/*    */   }
/*    */   
/*    */   public void openGui(Object mod, int modGuiId, World world, int x, int y, int z) {}
/*    */   
/*    */   public void func_145747_a(IChatComponent var1) {}
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/FakeThaumcraftPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */