/*     */ package thaumcraft.common.items;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.research.PlayerKnowledge;
/*     */ 
/*     */ public class ItemSanitySoap extends net.minecraft.item.Item
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon icon;
/*     */   
/*     */   public ItemSanitySoap()
/*     */   {
/*  23 */     func_77637_a(Thaumcraft.tabTC);
/*  24 */     func_77627_a(false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  32 */     this.icon = ir.func_94245_a("thaumcraft:soap");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1) {
/*  37 */     return this.icon;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int func_77626_a(ItemStack p_77626_1_)
/*     */   {
/*  47 */     return 200;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public net.minecraft.item.EnumAction func_77661_b(ItemStack p_77661_1_)
/*     */   {
/*  56 */     return net.minecraft.item.EnumAction.block;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
/*     */   {
/*  62 */     p_77659_3_.func_71008_a(p_77659_1_, func_77626_a(p_77659_1_));
/*  63 */     return p_77659_1_;
/*     */   }
/*     */   
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
/*     */   {
/*  68 */     int ticks = func_77626_a(stack) - count;
/*  69 */     if (ticks > 195) player.func_71034_by();
/*  70 */     if (player.field_70170_p.field_72995_K) {
/*  71 */       if (player.field_70170_p.field_73012_v.nextFloat() < 0.2F) {
/*  72 */         player.field_70170_p.func_72980_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, "thaumcraft:roots", 0.1F, 1.5F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F, false);
/*     */       }
/*  74 */       for (int a = 0; a < Thaumcraft.proxy.particleCount(5); a++) {
/*  75 */         Thaumcraft.proxy.crucibleBubble(Thaumcraft.proxy.getClientWorld(), (float)player.field_70165_t - 0.5F + player.field_70170_p.field_73012_v.nextFloat(), (float)player.field_70121_D.field_72338_b + player.field_70170_p.field_73012_v.nextFloat() * player.field_70131_O, (float)player.field_70161_v - 0.5F + player.field_70170_p.field_73012_v.nextFloat(), 1.0F, 0.8F, 0.9F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_77615_a(ItemStack stack, World world, EntityPlayer player, int par4)
/*     */   {
/*  86 */     int qq = func_77626_a(stack) - par4;
/*  87 */     if (qq > 195) {
/*  88 */       stack.field_77994_a -= 1;
/*  89 */       if (!world.field_72995_K) {
/*  90 */         float chance = 0.33F;
/*  91 */         if (player.func_82165_m(thaumcraft.common.config.Config.potionWarpWardID)) chance += 0.25F;
/*  92 */         int i = MathHelper.func_76128_c(player.field_70165_t);
/*  93 */         int j = MathHelper.func_76128_c(player.field_70163_u);
/*  94 */         int k = MathHelper.func_76128_c(player.field_70161_v);
/*  95 */         if (world.func_147439_a(i, j, k) == thaumcraft.common.config.ConfigBlocks.blockFluidPure) chance += 0.25F;
/*  96 */         if ((world.field_73012_v.nextFloat() < chance) && (Thaumcraft.proxy.getPlayerKnowledge().getWarpSticky(player.func_70005_c_()) > 0)) {
/*  97 */           Thaumcraft.addStickyWarpToPlayer(player, -1);
/*     */         }
/*  99 */         if (Thaumcraft.proxy.getPlayerKnowledge().getWarpTemp(player.func_70005_c_()) > 0) {
/* 100 */           Thaumcraft.addWarpToPlayer(player, -Thaumcraft.proxy.getPlayerKnowledge().getWarpTemp(player.func_70005_c_()), true);
/*     */         }
/*     */         
/*     */       }
/*     */       else
/*     */       {
/* 106 */         player.field_70170_p.func_72980_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, "thaumcraft:craftstart", 0.25F, 1.0F, false);
/* 107 */         for (int a = 0; a < Thaumcraft.proxy.particleCount(20); a++) {
/* 108 */           Thaumcraft.proxy.crucibleBubble(Thaumcraft.proxy.getClientWorld(), (float)player.field_70165_t - 0.5F + player.field_70170_p.field_73012_v.nextFloat() * 1.5F, (float)player.field_70121_D.field_72338_b + player.field_70170_p.field_73012_v.nextFloat() * player.field_70131_O, (float)player.field_70161_v - 0.5F + player.field_70170_p.field_73012_v.nextFloat() * 1.5F, 1.0F, 0.7F, 0.9F);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/ItemSanitySoap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */