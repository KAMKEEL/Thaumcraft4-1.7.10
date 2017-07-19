/*     */ package thaumcraft.common.items.relics;
/*     */ 
/*     */ import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.nodes.INode;
/*     */ import thaumcraft.api.research.IScanEventHandler;
/*     */ import thaumcraft.api.research.ScanResult;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.lib.network.playerdata.PacketScannedToServer;
/*     */ import thaumcraft.common.lib.research.ScanManager;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ public class ItemThaumometer extends Item
/*     */ {
/*     */   public IIcon icon;
/*     */   
/*     */   public ItemThaumometer()
/*     */   {
/*  36 */     func_77625_d(1);
/*  37 */     setNoRepair();
/*  38 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  44 */     return EnumRarity.uncommon;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister ir)
/*     */   {
/*  51 */     this.icon = ir.func_94245_a("thaumcraft:blank");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1)
/*     */   {
/*  57 */     return this.icon;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_77626_a(ItemStack itemstack)
/*     */   {
/*  63 */     return 25;
/*     */   }
/*     */   
/*     */   public EnumAction func_77661_b(ItemStack itemstack)
/*     */   {
/*  68 */     return EnumAction.none;
/*     */   }
/*     */   
/*     */   private ScanResult doScan(ItemStack stack, World world, EntityPlayer p, int count)
/*     */   {
/*  73 */     Entity pointedEntity = EntityUtils.getPointedEntity(p.field_70170_p, p, 0.5D, 10.0D, 0.0F, true);
/*  74 */     if (pointedEntity != null) {
/*  75 */       ScanResult sr = new ScanResult((byte)2, 0, 0, pointedEntity, "");
/*  76 */       if (ScanManager.isValidScanTarget(p, sr, "@")) {
/*  77 */         Thaumcraft.proxy.blockRunes(world, pointedEntity.field_70165_t - 0.5D, pointedEntity.field_70163_u + pointedEntity.func_70047_e() / 2.0F, pointedEntity.field_70161_v - 0.5D, 0.3F + world.field_73012_v.nextFloat() * 0.7F, 0.0F, 0.3F + world.field_73012_v.nextFloat() * 0.7F, (int)(pointedEntity.field_70131_O * 15.0F), 0.03F);
/*  78 */         return sr; }
/*  79 */       return null;
/*     */     }
/*     */     
/*     */ 
/*  83 */     MovingObjectPosition mop = func_77621_a(p.field_70170_p, p, true);
/*     */     
/*  85 */     if ((mop != null) && (mop.field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.BLOCK))
/*     */     {
/*  87 */       TileEntity tile = world.func_147438_o(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/*     */       
/*  89 */       if ((tile instanceof INode)) {
/*  90 */         ScanResult sr = new ScanResult((byte)3, 0, 0, null, "NODE" + ((INode)tile).getId());
/*  91 */         if (ScanManager.isValidScanTarget(p, sr, "@")) {
/*  92 */           Thaumcraft.proxy.blockRunes(world, mop.field_72311_b, mop.field_72312_c + 0.25D, mop.field_72309_d, 0.3F + world.field_73012_v.nextFloat() * 0.7F, 0.0F, 0.3F + world.field_73012_v.nextFloat() * 0.7F, 15, 0.03F);
/*  93 */           return sr; }
/*  94 */         return null;
/*     */       }
/*  96 */       Block bi = world.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/*     */       
/*  98 */       if (bi != net.minecraft.init.Blocks.field_150350_a) {
/*  99 */         int md = bi.func_149643_k(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/* 100 */         ItemStack is = bi.getPickBlock(mop, p.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/* 101 */         ScanResult sr = null;
/*     */         try {
/* 103 */           if (is == null) {
/* 104 */             is = thaumcraft.common.lib.utils.BlockUtils.createStackedBlock(bi, md);
/*     */           }
/*     */         }
/*     */         catch (Exception e) {}
/*     */         try {
/* 109 */           if (is == null) {
/* 110 */             sr = new ScanResult((byte)1, Block.func_149682_b(bi), md, null, "");
/*     */           }
/*     */           else {
/* 113 */             sr = new ScanResult((byte)1, Item.func_150891_b(is.func_77973_b()), is.func_77960_j(), null, "");
/*     */           }
/*     */         } catch (Exception e) {}
/* 116 */         if (ScanManager.isValidScanTarget(p, sr, "@")) {
/* 117 */           Thaumcraft.proxy.blockRunes(world, mop.field_72311_b, mop.field_72312_c + 0.25D, mop.field_72309_d, 0.3F + world.field_73012_v.nextFloat() * 0.7F, 0.0F, 0.3F + world.field_73012_v.nextFloat() * 0.7F, 15, 0.03F);
/* 118 */           return sr; }
/* 119 */         return null;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 125 */     for (IScanEventHandler seh : thaumcraft.api.ThaumcraftApi.scanEventhandlers) {
/* 126 */       ScanResult scan = seh.scanPhenomena(stack, world, p);
/* 127 */       if (scan != null) {
/* 128 */         return scan;
/*     */       }
/*     */     }
/* 131 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer p)
/*     */   {
/* 138 */     if (world.field_72995_K) {
/* 139 */       ScanResult scan = doScan(stack, world, p, 0);
/* 140 */       if (scan != null) {
/* 141 */         this.startScan = scan;
/*     */       }
/*     */     }
/* 144 */     p.func_71008_a(stack, func_77626_a(stack));
/* 145 */     return stack;
/*     */   }
/*     */   
/*     */ 
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer p, int count)
/*     */   {
/* 151 */     if ((p.field_70170_p.field_72995_K) && (p.func_70005_c_() == Minecraft.func_71410_x().field_71439_g.func_70005_c_())) {
/* 152 */       ScanResult scan = doScan(stack, p.field_70170_p, p, count);
/* 153 */       if ((scan != null) && (scan.equals(this.startScan))) {
/* 154 */         if (count <= 5) {
/* 155 */           this.startScan = null;
/* 156 */           p.func_71034_by();
/* 157 */           if (ScanManager.completeScan(p, scan, "@")) {
/* 158 */             thaumcraft.common.lib.network.PacketHandler.INSTANCE.sendToServer(new PacketScannedToServer(scan, p, "@"));
/*     */           }
/*     */         }
/* 161 */         if (count % 2 == 0) {
/* 162 */           p.field_70170_p.func_72980_b(p.field_70165_t, p.field_70163_u, p.field_70161_v, "thaumcraft:cameraticks", 0.2F, 0.45F + p.field_70170_p.field_73012_v.nextFloat() * 0.1F, false);
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 167 */         this.startScan = null;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_77615_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
/*     */   {
/* 175 */     super.func_77615_a(par1ItemStack, par2World, par3EntityPlayer, par4);
/*     */     
/* 177 */     this.startScan = null;
/*     */   }
/*     */   
/* 180 */   ScanResult startScan = null;
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/relics/ItemThaumometer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */