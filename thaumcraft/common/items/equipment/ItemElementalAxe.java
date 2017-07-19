/*     */ package thaumcraft.common.items.equipment;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item.ToolMaterial;
/*     */ import net.minecraft.item.ItemAxe;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import thaumcraft.api.IRepairable;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.entities.EntityFollowingItem;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXBlockBubble;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ public class ItemElementalAxe extends ItemAxe implements IRepairable
/*     */ {
/*     */   public IIcon icon;
/*     */   
/*     */   public ItemElementalAxe(Item.ToolMaterial enumtoolmaterial)
/*     */   {
/*  39 */     super(enumtoolmaterial);
/*  40 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */   public Set<String> getToolClasses(ItemStack stack)
/*     */   {
/*  45 */     return ImmutableSet.of("axe");
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  52 */     this.icon = ir.func_94245_a("thaumcraft:elementalaxe");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1) {
/*  57 */     return this.icon;
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  63 */     return EnumRarity.rare;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*     */   {
/*  69 */     return par2ItemStack.func_77969_a(new ItemStack(thaumcraft.common.config.ConfigItems.itemResource, 1, 2)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public EnumAction func_77661_b(ItemStack itemstack)
/*     */   {
/*  77 */     return EnumAction.bow;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_77626_a(ItemStack p_77626_1_)
/*     */   {
/*  83 */     return 72000;
/*     */   }
/*     */   
/*     */ 
/*     */   public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
/*     */   {
/*  89 */     p_77659_3_.func_71008_a(p_77659_1_, func_77626_a(p_77659_1_));
/*  90 */     return p_77659_1_;
/*     */   }
/*     */   
/*     */ 
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
/*     */   {
/*  96 */     ArrayList<Entity> stuff = EntityUtils.getEntitiesInRange(player.field_70170_p, player.field_70165_t, player.field_70163_u, player.field_70161_v, player, EntityItem.class, 10.0D);
/*     */     
/*  98 */     if ((stuff != null) && (stuff.size() > 0)) {
/*  99 */       for (Entity e : stuff) {
/* 100 */         if ((!(e instanceof EntityFollowingItem)) || (((EntityFollowingItem)e).target == null)) {
/* 101 */           if ((!e.field_70128_L) && ((e instanceof EntityItem))) {
/* 102 */             double d6 = e.field_70165_t - player.field_70165_t;
/* 103 */             double d8 = e.field_70163_u - player.field_70163_u + player.field_70131_O / 2.0F;
/* 104 */             double d10 = e.field_70161_v - player.field_70161_v;
/* 105 */             double d11 = MathHelper.func_76133_a(d6 * d6 + d8 * d8 + d10 * d10);
/* 106 */             d6 /= d11;
/* 107 */             d8 /= d11;
/* 108 */             d10 /= d11;
/* 109 */             double d13 = 0.3D;
/* 110 */             e.field_70159_w -= d6 * d13;
/* 111 */             e.field_70181_x -= d8 * d13;
/* 112 */             e.field_70179_y -= d10 * d13;
/* 113 */             if (e.field_70159_w > 0.35D) e.field_70159_w = 0.35D;
/* 114 */             if (e.field_70159_w < -0.35D) e.field_70159_w = -0.35D;
/* 115 */             if (e.field_70181_x > 0.35D) e.field_70181_x = 0.35D;
/* 116 */             if (e.field_70181_x < -0.35D) e.field_70181_x = -0.35D;
/* 117 */             if (e.field_70179_y > 0.35D) e.field_70179_y = 0.35D;
/* 118 */             if (e.field_70179_y < -0.35D) e.field_70179_y = -0.35D;
/* 119 */             Thaumcraft.proxy.crucibleBubble(player.field_70170_p, (float)e.field_70165_t + (player.field_70170_p.field_73012_v.nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 0.125F, (float)e.field_70163_u + (player.field_70170_p.field_73012_v.nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 0.125F, (float)e.field_70161_v + (player.field_70170_p.field_73012_v.nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 0.125F, 0.33F, 0.33F, 1.0F);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 130 */   boolean alternateServer = false;
/* 131 */   boolean alternateClient = false;
/*     */   
/* 133 */   public static ArrayList<java.util.List> oreDictLogs = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean onBlockStartBreak(ItemStack itemstack, int x, int y, int z, EntityPlayer player)
/*     */   {
/* 170 */     World world = player.field_70170_p;
/* 171 */     Block bi = world.func_147439_a(x, y, z);
/*     */     
/* 173 */     if ((!player.func_70093_af()) && (Utils.isWoodLog(world, x, y, z))) {
/* 174 */       if (!world.field_72995_K) {
/* 175 */         thaumcraft.common.lib.utils.BlockUtils.breakFurthestBlock(world, x, y, z, bi, player, true, 10);
/* 176 */         thaumcraft.common.lib.network.PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockBubble(x, y, z, new Color(0.33F, 0.33F, 1.0F).getRGB()), new NetworkRegistry.TargetPoint(world.field_73011_w.field_76574_g, x, y, z, 32.0D));
/*     */         
/*     */ 
/* 179 */         world.func_72908_a(x, y, z, "thaumcraft:bubble", 0.15F, 1.0F);
/*     */       }
/* 181 */       itemstack.func_77972_a(1, player);
/* 182 */       return true;
/*     */     }
/*     */     
/* 185 */     return super.onBlockStartBreak(itemstack, x, y, z, player);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/equipment/ItemElementalAxe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */