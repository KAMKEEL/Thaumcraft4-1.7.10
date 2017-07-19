/*     */ package thaumcraft.common.items.equipment;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Sets;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item.ToolMaterial;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.ItemTool;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.ForgeHooks;
/*     */ import net.minecraftforge.common.util.EnumHelper;
/*     */ import thaumcraft.api.IRepairable;
/*     */ import thaumcraft.api.IWarpingGear;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemPrimalCrusher
/*     */   extends ItemTool
/*     */   implements IRepairable, IWarpingGear
/*     */ {
/*  38 */   public static Item.ToolMaterial material = EnumHelper.addToolMaterial("PRIMALVOID", 5, 500, 8.0F, 4.0F, 20);
/*     */   
/*     */ 
/*  41 */   private static final Set isEffective = Sets.newHashSet(new Block[] { Blocks.field_150347_e, Blocks.field_150334_T, Blocks.field_150333_U, Blocks.field_150348_b, Blocks.field_150322_A, Blocks.field_150341_Y, Blocks.field_150366_p, Blocks.field_150339_S, Blocks.field_150365_q, Blocks.field_150340_R, Blocks.field_150352_o, Blocks.field_150482_ag, Blocks.field_150484_ah, Blocks.field_150432_aD, Blocks.field_150424_aL, Blocks.field_150369_x, Blocks.field_150368_y, Blocks.field_150450_ax, Blocks.field_150439_ay, Blocks.field_150448_aq, Blocks.field_150319_E, Blocks.field_150318_D, Blocks.field_150408_cc, Blocks.field_150349_c, Blocks.field_150346_d, Blocks.field_150354_m, Blocks.field_150351_n, Blocks.field_150431_aC, Blocks.field_150433_aE, Blocks.field_150435_aG, Blocks.field_150458_ak, Blocks.field_150425_aM, Blocks.field_150391_bh, ConfigBlocks.blockTaint, ConfigBlocks.blockTaintFibres, Blocks.field_150343_Z });
/*     */   
/*     */ 
/*     */ 
/*     */   public IIcon icon;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemPrimalCrusher(Item.ToolMaterial enumtoolmaterial)
/*     */   {
/*  52 */     super(3.5F, enumtoolmaterial, isEffective);
/*  53 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_150897_b(Block p_150897_1_)
/*     */   {
/*  59 */     return (p_150897_1_.func_149688_o() != Material.field_151575_d) && (p_150897_1_.func_149688_o() != Material.field_151584_j) && (p_150897_1_.func_149688_o() != Material.field_151585_k);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_)
/*     */   {
/*  67 */     return (p_150893_2_.func_149688_o() != Material.field_151573_f) && (p_150893_2_.func_149688_o() != Material.field_151574_g) && (p_150893_2_.func_149688_o() != Material.field_151576_e) ? super.func_150893_a(p_150893_1_, p_150893_2_) : this.field_77864_a;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Set<String> getToolClasses(ItemStack stack)
/*     */   {
/*  74 */     return ImmutableSet.of("shovel", "pickaxe");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  82 */     this.icon = ir.func_94245_a("thaumcraft:primal_crusher");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1)
/*     */   {
/*  88 */     return this.icon;
/*     */   }
/*     */   
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  93 */     return EnumRarity.epic;
/*     */   }
/*     */   
/*     */   public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
/*     */   {
/*  98 */     return par2ItemStack.func_77969_a(new ItemStack(ConfigItems.itemResource, 1, 15)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private boolean isEffectiveAgainst(Block block)
/*     */   {
/* 105 */     for (Object b : isEffective) {
/* 106 */       if (b == block) {
/* 107 */         return true;
/*     */       }
/*     */     }
/* 110 */     return false;
/*     */   }
/*     */   
/* 113 */   int side = 0;
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player)
/*     */   {
/* 119 */     MovingObjectPosition movingobjectposition = BlockUtils.getTargetBlock(player.field_70170_p, player, true);
/*     */     
/* 121 */     if ((movingobjectposition != null) && 
/* 122 */       (movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK)) {
/* 123 */       this.side = movingobjectposition.field_72310_e;
/*     */     }
/*     */     
/* 126 */     return super.onBlockStartBreak(itemstack, X, Y, Z, player);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_150894_a(ItemStack stack, World world, Block bi, int x, int y, int z, EntityLivingBase ent)
/*     */   {
/* 132 */     if (ent.func_70093_af()) {
/* 133 */       return super.func_150894_a(stack, world, bi, x, y, z, ent);
/*     */     }
/* 135 */     if (!ent.field_70170_p.field_72995_K)
/*     */     {
/* 137 */       int md = world.func_72805_g(x, y, z);
/* 138 */       if ((ForgeHooks.isToolEffective(stack, bi, md)) || (isEffectiveAgainst(bi)))
/*     */       {
/* 140 */         for (int aa = -1; aa <= 1; aa++)
/* 141 */           for (int bb = -1; bb <= 1; bb++)
/*     */           {
/* 143 */             int xx = 0;
/* 144 */             int yy = 0;
/* 145 */             int zz = 0;
/*     */             
/* 147 */             if (this.side <= 1) {
/* 148 */               xx = aa;
/* 149 */               zz = bb;
/* 150 */             } else if (this.side <= 3) {
/* 151 */               xx = aa;
/* 152 */               yy = bb;
/*     */             } else {
/* 154 */               zz = aa;
/* 155 */               yy = bb;
/*     */             }
/*     */             
/* 158 */             if ((!(ent instanceof EntityPlayer)) || (world.func_72962_a((EntityPlayer)ent, x + xx, y + yy, z + zz)))
/*     */             {
/*     */ 
/* 161 */               Block bl = world.func_147439_a(x + xx, y + yy, z + zz);
/* 162 */               md = world.func_72805_g(x + xx, y + yy, z + zz);
/*     */               
/* 164 */               if ((bl.func_149712_f(world, x + xx, y + yy, z + zz) >= 0.0F) && ((ForgeHooks.isToolEffective(stack, bl, md)) || (isEffectiveAgainst(bl))))
/*     */               {
/*     */ 
/* 167 */                 stack.func_77972_a(1, ent);
/* 168 */                 BlockUtils.harvestBlock(world, (EntityPlayer)ent, x + xx, y + yy, z + zz, true, 2);
/*     */               }
/*     */             }
/*     */           } }
/*     */     }
/* 173 */     return true;
/*     */   }
/*     */   
/*     */   public int func_77619_b()
/*     */   {
/* 178 */     return 20;
/*     */   }
/*     */   
/*     */   public int getWarp(ItemStack itemstack, EntityPlayer player)
/*     */   {
/* 183 */     return 2;
/*     */   }
/*     */   
/*     */   public void func_77663_a(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_)
/*     */   {
/* 188 */     super.func_77663_a(stack, world, entity, p_77663_4_, p_77663_5_);
/*     */     
/* 190 */     if ((stack.func_77951_h()) && (entity != null) && (entity.field_70173_aa % 20 == 0) && ((entity instanceof EntityLivingBase))) {
/* 191 */       stack.func_77972_a(-1, (EntityLivingBase)entity);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/equipment/ItemPrimalCrusher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */