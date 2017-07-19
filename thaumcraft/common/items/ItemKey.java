/*     */ package thaumcraft.common.items;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.blocks.BlockArcaneDoor;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.tiles.TileOwned;
/*     */ 
/*     */ public class ItemKey extends Item
/*     */ {
/*     */   public IIcon iconIron;
/*     */   public IIcon iconGold;
/*     */   
/*     */   public ItemKey()
/*     */   {
/*  33 */     func_77625_d(64);
/*  34 */     func_77627_a(true);
/*  35 */     func_77656_e(0);
/*  36 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  44 */     this.iconIron = ir.func_94245_a("thaumcraft:keyiron");
/*  45 */     this.iconGold = ir.func_94245_a("thaumcraft:keygold");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1) {
/*  50 */     return par1 == 0 ? this.iconIron : this.iconGold;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List)
/*     */   {
/*  57 */     par3List.add(new ItemStack(this, 1, 0));
/*  58 */     par3List.add(new ItemStack(this, 1, 1));
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_77667_c(ItemStack par1ItemStack)
/*     */   {
/*  64 */     return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
/*     */   }
/*     */   
/*     */   public boolean func_77636_d(ItemStack par1ItemStack)
/*     */   {
/*  69 */     return par1ItemStack.func_77942_o();
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  75 */     return EnumRarity.uncommon;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean onItemUseFirst(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
/*     */   {
/*  82 */     net.minecraft.block.Block bi = world.func_147439_a(x, y, z);
/*  83 */     int md = world.func_72805_g(x, y, z);
/*  84 */     if ((bi == ConfigBlocks.blockArcaneDoor) || ((bi == ConfigBlocks.blockWoodenDevice) && ((md == 2) || (md == 3))))
/*     */     {
/*  86 */       int mod = 0;
/*  87 */       int mod2 = 1;
/*  88 */       byte type = 0;
/*  89 */       if (bi == ConfigBlocks.blockArcaneDoor) {
/*  90 */         int var10 = ((BlockArcaneDoor)bi).getFullMetadata(world, x, y, z);
/*  91 */         if ((var10 & 0x8) != 0)
/*     */         {
/*  93 */           mod = -1;
/*  94 */           mod2 = 0;
/*     */         }
/*     */       }
/*     */       else {
/*  98 */         type = 1;
/*     */       }
/*     */       
/* 101 */       String loc = x + "," + (y + mod) + "," + z;
/* 102 */       TileEntity tile = world.func_147438_o(x, y + mod, z);
/* 103 */       if ((tile != null) && ((tile instanceof TileOwned))) {
/* 104 */         if (!itemstack.func_77942_o()) {
/* 105 */           if ((player.func_70005_c_().equals(((TileOwned)tile).owner)) || ((((TileOwned)tile).accessList.contains("1" + player.func_70005_c_())) && (itemstack.func_77960_j() == 0)))
/*     */           {
/* 107 */             ItemStack st = new ItemStack(thaumcraft.common.config.ConfigItems.itemKey, 1, itemstack.func_77960_j());
/* 108 */             st.func_77983_a("location", new net.minecraft.nbt.NBTTagString(loc));
/* 109 */             st.func_77983_a("type", new net.minecraft.nbt.NBTTagByte(type));
/*     */             
/* 111 */             if ((!player.field_71071_by.func_70441_a(st)) && 
/* 112 */               (!world.field_72995_K)) { world.func_72838_d(new EntityItem(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, st));
/*     */             }
/*     */             
/* 115 */             if (!player.field_71075_bZ.field_75098_d) { itemstack.field_77994_a -= 1;
/*     */             }
/* 117 */             if (!world.field_72995_K) {
/* 118 */               switch (type) {
/* 119 */               case 0:  player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("tc.key1"))); break;
/* 120 */               case 1:  player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("tc.key2")));
/*     */               }
/* 122 */               world.func_72908_a(x, y, z, "thaumcraft:key", 1.0F, 0.9F);
/*     */             }
/*     */             
/* 125 */             player.func_71038_i();
/*     */           }
/*     */         }
/* 128 */         else if ((!player.func_70005_c_().equals(((TileOwned)tile).owner)) && (!((TileOwned)tile).accessList.contains(itemstack.func_77960_j() + player.func_70005_c_())) && (!((TileOwned)tile).accessList.contains("1" + player.func_70005_c_())) && (loc.equals(itemstack.field_77990_d.func_74779_i("location"))))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/* 133 */           ((TileOwned)tile).accessList.add(itemstack.func_77960_j() + player.func_70005_c_());
/*     */           
/* 135 */           if (type == 0) {
/* 136 */             TileEntity tile2 = world.func_147438_o(x, y + mod2, z);
/* 137 */             if ((tile2 != null) && ((tile2 instanceof TileOwned))) {
/* 138 */               ((TileOwned)tile2).accessList.add(itemstack.func_77960_j() + player.func_70005_c_());
/*     */             }
/* 140 */             world.func_147471_g(x, y + mod2, z);
/*     */           }
/*     */           
/* 143 */           world.func_147471_g(x, y + mod, z);
/*     */           
/* 145 */           if (!world.field_72995_K) {
/* 146 */             switch (type) {
/* 147 */             case 0:  player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("tc.key3") + (itemstack.func_77960_j() == 0 ? "" : StatCollector.func_74838_a("tc.key4")))); break;
/* 148 */             case 1:  player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("tc.key5") + (itemstack.func_77960_j() == 0 ? "" : StatCollector.func_74838_a("tc.key6"))));
/*     */             }
/*     */             
/* 151 */             world.func_72908_a(x, y, z, "thaumcraft:key", 1.0F, 1.1F);
/*     */           }
/* 153 */           if (!player.field_71075_bZ.field_75098_d) itemstack.field_77994_a -= 1;
/* 154 */           player.func_71038_i();
/*     */         }
/* 156 */         else if (!world.field_72995_K) {
/* 157 */           if (!loc.equals(itemstack.field_77990_d.func_74779_i("location"))) {
/* 158 */             player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("tc.key7")));
/*     */           } else {
/* 160 */             player.func_145747_a(new ChatComponentText("§5§o" + StatCollector.func_74838_a("tc.key8")));
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 166 */       return !world.field_72995_K;
/*     */     }
/*     */     
/*     */ 
/* 170 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4)
/*     */   {
/* 176 */     if ((stack.func_77942_o()) && (stack.field_77990_d.func_74764_b("location"))) {
/* 177 */       String location = stack.field_77990_d.func_74779_i("location");
/*     */       try {
/* 179 */         String[] ss = location.split(",");
/* 180 */         location = "x " + ss[0] + ", z " + ss[2] + ", y " + ss[1];
/*     */       }
/*     */       catch (Exception e) {}
/* 183 */       byte type = stack.field_77990_d.func_74771_c("type");
/* 184 */       list.add("§5§o" + StatCollector.func_74838_a("tc.key9"));
/* 185 */       list.add("§5§o" + (type == 0 ? StatCollector.func_74838_a("tc.key10") : StatCollector.func_74838_a("tc.key11")));
/* 186 */       list.add("§5§o" + location);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/ItemKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */