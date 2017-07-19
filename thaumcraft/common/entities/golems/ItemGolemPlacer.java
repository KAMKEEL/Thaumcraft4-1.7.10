/*     */ package thaumcraft.common.entities.golems;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ public class ItemGolemPlacer extends Item
/*     */ {
/*     */   public ItemGolemPlacer()
/*     */   {
/*  27 */     func_77627_a(true);
/*  28 */     func_77637_a(Thaumcraft.tabTC);
/*  29 */     func_77625_d(1);
/*     */   }
/*     */   
/*  32 */   public IIcon[] iconGolem = new IIcon[8];
/*     */   public IIcon iconAdvanced;
/*     */   public IIcon iconCore;
/*     */   private IIcon iconBlank;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir) {
/*  39 */     this.iconGolem[0] = ir.func_94245_a("thaumcraft:golem_straw");
/*  40 */     this.iconGolem[1] = ir.func_94245_a("thaumcraft:golem_wood");
/*  41 */     this.iconGolem[2] = ir.func_94245_a("thaumcraft:golem_tallow");
/*  42 */     this.iconGolem[3] = ir.func_94245_a("thaumcraft:golem_clay");
/*  43 */     this.iconGolem[4] = ir.func_94245_a("thaumcraft:golem_flesh");
/*  44 */     this.iconGolem[5] = ir.func_94245_a("thaumcraft:golem_stone");
/*  45 */     this.iconGolem[6] = ir.func_94245_a("thaumcraft:golem_iron");
/*  46 */     this.iconGolem[7] = ir.func_94245_a("thaumcraft:golem_thaumium");
/*  47 */     this.iconAdvanced = ir.func_94245_a("thaumcraft:golem_over_adv");
/*  48 */     this.iconCore = ir.func_94245_a("thaumcraft:golem_over_core");
/*  49 */     this.iconBlank = ir.func_94245_a("thaumcraft:blank");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getRenderPasses(int metadata)
/*     */   {
/*  55 */     return 3;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1)
/*     */   {
/*  61 */     return this.iconGolem[par1];
/*     */   }
/*     */   
/*     */   public IIcon getIcon(ItemStack stack, int pass)
/*     */   {
/*  66 */     if (pass == 0) {
/*  67 */       return super.getIcon(stack, pass);
/*     */     }
/*  69 */     if ((pass == 1) && (stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("advanced"))) {
/*  70 */       return this.iconAdvanced;
/*     */     }
/*  72 */     if ((pass == 2) && (stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("core"))) {
/*  73 */       return this.iconCore;
/*     */     }
/*  75 */     return this.iconBlank;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77623_v()
/*     */   {
/*  82 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_77667_c(ItemStack par1ItemStack)
/*     */   {
/*  88 */     return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4)
/*     */   {
/*  96 */     if (stack.func_77942_o()) {
/*  97 */       if (stack.field_77990_d.func_74764_b("core")) {
/*  98 */         list.add(StatCollector.func_74838_a("item.ItemGolemCore.name") + ": §6" + StatCollector.func_74838_a(new StringBuilder().append("item.ItemGolemCore.").append(stack.field_77990_d.func_74771_c("core")).append(".name").toString()));
/*     */       }
/*     */       
/*     */ 
/* 102 */       if (stack.field_77990_d.func_74764_b("advanced")) {
/* 103 */         list.add(StatCollector.func_74838_a("tc.adv"));
/*     */       }
/*     */       
/* 106 */       if (stack.field_77990_d.func_74764_b("upgrades")) {
/* 107 */         byte[] ba = stack.field_77990_d.func_74770_j("upgrades");
/* 108 */         String text = "§9";
/* 109 */         for (byte b : ba) {
/* 110 */           if (b > -1) text = text + StatCollector.func_74838_a(new StringBuilder().append("item.ItemGolemUpgrade.").append(b).append(".name").toString()) + " ";
/*     */         }
/* 112 */         list.add(text);
/*     */       }
/*     */       
/* 115 */       if (stack.field_77990_d.func_74764_b("markers")) {
/* 116 */         NBTTagList tl = stack.field_77990_d.func_150295_c("markers", 10);
/* 117 */         list.add("§5" + tl.func_74745_c() + " " + StatCollector.func_74838_a("tc.markedloc"));
/*     */       }
/*     */       
/* 120 */       if (stack.field_77990_d.func_74764_b("deco")) {
/* 121 */         String decoDesc = "§2";
/* 122 */         String deco = stack.field_77990_d.func_74779_i("deco");
/* 123 */         if (deco.contains("H")) decoDesc = decoDesc + StatCollector.func_74838_a("item.ItemGolemDecoration.0.name") + " ";
/* 124 */         if (deco.contains("G")) decoDesc = decoDesc + StatCollector.func_74838_a("item.ItemGolemDecoration.1.name") + " ";
/* 125 */         if (deco.contains("B")) decoDesc = decoDesc + StatCollector.func_74838_a("item.ItemGolemDecoration.2.name") + " ";
/* 126 */         if (deco.contains("F")) decoDesc = decoDesc + StatCollector.func_74838_a("item.ItemGolemDecoration.3.name") + " ";
/* 127 */         if (deco.contains("R")) decoDesc = decoDesc + StatCollector.func_74838_a("item.ItemGolemDecoration.4.name") + " ";
/* 128 */         if (deco.contains("V")) decoDesc = decoDesc + StatCollector.func_74838_a("item.ItemGolemDecoration.5.name") + " ";
/* 129 */         if (deco.contains("P")) decoDesc = decoDesc + StatCollector.func_74838_a("item.ItemGolemDecoration.6.name") + " ";
/* 130 */         list.add(decoDesc);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_77651_p()
/*     */   {
/* 137 */     return true;
/*     */   }
/*     */   
/*     */   public boolean doesSneakBypassUse(World world, int x, int y, int z, EntityPlayer player)
/*     */   {
/* 142 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int par4, int par5, int par6, int side, float par8, float par9, float par10)
/*     */   {
/* 150 */     if ((world.field_72995_K) || (player.func_70093_af()))
/*     */     {
/* 152 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 156 */     Block var11 = world.func_147439_a(par4, par5, par6);
/* 157 */     par4 += net.minecraft.util.Facing.field_71586_b[side];
/* 158 */     par5 += net.minecraft.util.Facing.field_71587_c[side];
/* 159 */     par6 += net.minecraft.util.Facing.field_71585_d[side];
/* 160 */     double var12 = 0.0D;
/*     */     
/* 162 */     if (((side == 1) && (var11 == Blocks.field_150422_aJ)) || (var11 == Blocks.field_150386_bk))
/*     */     {
/* 164 */       var12 = 0.5D;
/*     */     }
/*     */     
/* 167 */     if ((spawnCreature(world, par4 + 0.5D, par5 + var12, par6 + 0.5D, side, stack, player)) && (!player.field_71075_bZ.field_75098_d))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 174 */       stack.field_77994_a -= 1;
/*     */     }
/*     */     
/* 177 */     return true;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/* 183 */     for (int a = 0; a <= 7; a++) {
/* 184 */       par3List.add(new ItemStack(this, 1, a));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean spawnCreature(World par0World, double par2, double par4, double par6, int side, ItemStack stack, EntityPlayer player) {
/* 189 */     boolean adv = false;
/* 190 */     if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("advanced"))) {
/* 191 */       adv = true;
/*     */     }
/*     */     
/* 194 */     EntityGolemBase golem = new EntityGolemBase(par0World, EnumGolemType.getType(stack.func_77960_j()), adv);
/*     */     
/* 196 */     if (golem != null)
/*     */     {
/* 198 */       golem.func_70012_b(par2, par4, par6, par0World.field_73012_v.nextFloat() * 360.0F, 0.0F);
/* 199 */       golem.func_70642_aH();
/* 200 */       golem.func_110171_b(MathHelper.func_76128_c(par2), MathHelper.func_76128_c(par4), MathHelper.func_76128_c(par6), 32);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 205 */       if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("core"))) {
/* 206 */         golem.setCore(stack.field_77990_d.func_74771_c("core"));
/*     */       }
/*     */       
/* 209 */       if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("upgrades")))
/*     */       {
/*     */ 
/* 212 */         int ul = golem.upgrades.length;
/* 213 */         golem.upgrades = stack.field_77990_d.func_74770_j("upgrades");
/* 214 */         if (ul != golem.upgrades.length) {
/* 215 */           byte[] tt = new byte[ul];
/* 216 */           for (int a = 0; a < ul; a++) tt[a] = -1;
/* 217 */           for (int a = 0; a < golem.upgrades.length; a++) {
/* 218 */             if (a < ul) tt[a] = golem.upgrades[a];
/*     */           }
/* 220 */           golem.upgrades = tt;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 227 */       String deco = "";
/* 228 */       if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("deco"))) {
/* 229 */         deco = stack.field_77990_d.func_74779_i("deco");
/* 230 */         golem.decoration = deco;
/*     */       }
/*     */       
/* 233 */       golem.setup(side);
/*     */       
/* 235 */       par0World.func_72838_d(golem);
/*     */       
/* 237 */       golem.setGolemDecoration(deco);
/* 238 */       golem.setOwner(player.func_70005_c_());
/* 239 */       golem.setMarkers(ItemGolemBell.getMarkers(stack));
/* 240 */       int a = 0;
/* 241 */       for (byte b : golem.upgrades) {
/* 242 */         golem.setUpgrade(a, b);
/* 243 */         a++;
/*     */       }
/*     */       
/* 246 */       if (stack.func_82837_s())
/*     */       {
/* 248 */         golem.func_94058_c(stack.func_82833_r());
/* 249 */         golem.func_110163_bv();
/*     */       }
/*     */       
/* 252 */       if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("Inventory"))) {
/* 253 */         NBTTagList nbttaglist2 = stack.field_77990_d.func_150295_c("Inventory", 10);
/* 254 */         golem.inventory.readFromNBT(nbttaglist2);
/*     */       }
/*     */     }
/*     */     
/* 258 */     return golem != null;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/golems/ItemGolemPlacer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */