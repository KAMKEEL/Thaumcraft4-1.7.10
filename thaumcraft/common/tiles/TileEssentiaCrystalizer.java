/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.awt.Color;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.TileThaumcraft;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.api.visnet.VisNetHandler;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.items.ItemCrystalEssence;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ 
/*     */ public class TileEssentiaCrystalizer
/*     */   extends TileThaumcraft implements IAspectContainer, IEssentiaTransport
/*     */ {
/*  31 */   public Aspect aspect = null;
/*  32 */   public ForgeDirection facing = ForgeDirection.DOWN;
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/*  36 */     return true;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox()
/*     */   {
/*  42 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  50 */     this.aspect = Aspect.getAspect(nbttagcompound.func_74779_i("Aspect"));
/*  51 */     this.facing = ForgeDirection.getOrientation(nbttagcompound.func_74771_c("face"));
/*     */   }
/*     */   
/*     */ 
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  57 */     if (this.aspect != null) nbttagcompound.func_74778_a("Aspect", this.aspect.getTag());
/*  58 */     nbttagcompound.func_74774_a("face", (byte)this.facing.ordinal());
/*     */   }
/*     */   
/*     */ 
/*     */   public AspectList getAspects()
/*     */   {
/*  64 */     AspectList al = new AspectList();
/*  65 */     if (this.aspect != null) al.add(this.aspect, 1);
/*  66 */     return al;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setAspects(AspectList aspects) {}
/*     */   
/*     */ 
/*     */   public int addToContainer(Aspect tt, int am)
/*     */   {
/*  75 */     if (am == 0) return am;
/*  76 */     if (this.aspect == null) {
/*  77 */       am--;
/*  78 */       this.aspect = tt;
/*  79 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  80 */       func_70296_d();
/*     */     }
/*  82 */     return am;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(Aspect tt, int am)
/*     */   {
/*  87 */     if ((this.aspect != null) && (am == 1)) {
/*  88 */       this.aspect = null;
/*  89 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  90 */       func_70296_d();
/*  91 */       return true;
/*     */     }
/*  93 */     return false;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(AspectList ot)
/*     */   {
/*  98 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean doesContainerContainAmount(Aspect tag, int amt)
/*     */   {
/* 104 */     if ((amt == 1) && (this.aspect != null) && (tag == this.aspect)) return true;
/* 105 */     return false;
/*     */   }
/*     */   
/*     */   public boolean doesContainerContain(AspectList ot)
/*     */   {
/* 110 */     for (Aspect tt : ot.getAspects()) {
/* 111 */       if ((this.aspect == null) || (this.aspect != tt) || (ot.getAmount(tt) != 1)) return false;
/*     */     }
/* 113 */     return true;
/*     */   }
/*     */   
/*     */   public int containerContains(Aspect tag)
/*     */   {
/* 118 */     return (this.aspect != null) && (tag == this.aspect) ? 1 : 0;
/*     */   }
/*     */   
/*     */   public boolean doesContainerAccept(Aspect tag)
/*     */   {
/* 123 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isConnectable(ForgeDirection face)
/*     */   {
/* 131 */     return face == this.facing;
/*     */   }
/*     */   
/*     */   public boolean canInputFrom(ForgeDirection face)
/*     */   {
/* 136 */     return face == this.facing;
/*     */   }
/*     */   
/*     */   public boolean canOutputTo(ForgeDirection face)
/*     */   {
/* 141 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */   
/*     */   public boolean renderExtendedTube()
/*     */   {
/* 149 */     return false;
/*     */   }
/*     */   
/*     */   public int getMinimumSuction()
/*     */   {
/* 154 */     return 0;
/*     */   }
/*     */   
/*     */   public Aspect getSuctionType(ForgeDirection loc)
/*     */   {
/* 159 */     return null;
/*     */   }
/*     */   
/*     */   public int getSuctionAmount(ForgeDirection loc)
/*     */   {
/* 164 */     return (loc == this.facing) && (this.aspect == null) ? 128 : gettingPower() ? 0 : 64;
/*     */   }
/*     */   
/*     */   public Aspect getEssentiaType(ForgeDirection loc)
/*     */   {
/* 169 */     return this.aspect;
/*     */   }
/*     */   
/*     */   public int getEssentiaAmount(ForgeDirection loc)
/*     */   {
/* 174 */     return this.aspect == null ? 0 : 1;
/*     */   }
/*     */   
/*     */   public int takeEssentia(Aspect aspect, int amount, ForgeDirection face)
/*     */   {
/* 179 */     return 0;
/*     */   }
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, ForgeDirection face)
/*     */   {
/* 184 */     return canInputFrom(face) ? amount - addToContainer(aspect, amount) : 0;
/*     */   }
/*     */   
/* 187 */   int count = 0;
/* 188 */   int progress = 0;
/* 189 */   final int progMax = 200;
/* 190 */   public float spin = 0.0F;
/* 191 */   public float spinInc = 0.0F;
/*     */   
/* 193 */   float tr = 1.0F;
/* 194 */   float tg = 1.0F;
/* 195 */   float tb = 1.0F;
/* 196 */   public float cr = 1.0F;
/* 197 */   public float cg = 1.0F;
/* 198 */   public float cb = 1.0F;
/*     */   
/*     */   public void func_145845_h()
/*     */   {
/* 202 */     super.func_145845_h();
/* 203 */     if (!this.field_145850_b.field_72995_K)
/*     */     {
/* 205 */       if ((++this.count % 5 == 0) && 
/* 206 */         (!gettingPower())) {
/* 207 */         if (this.aspect == null) {
/* 208 */           fillReservoir();
/* 209 */           this.progress = 0;
/*     */         } else {
/* 211 */           this.progress += 1 + VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, Aspect.EARTH, Math.min(20, Math.max(1, (200 - this.progress) / 2))) * 2;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 216 */       if ((this.aspect != null) && (this.progress >= 200)) {
/* 217 */         eject();
/* 218 */         this.aspect = null;
/* 219 */         this.progress = 0;
/* 220 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 221 */         func_70296_d();
/*     */       }
/*     */     }
/*     */     else {
/* 225 */       if (this.aspect == null) {
/* 226 */         this.tr = (this.tg = this.tb = 1.0F);
/*     */       } else {
/* 228 */         Color c = new Color(this.aspect.getColor());
/* 229 */         this.tr = (c.getRed() / 220.0F);
/* 230 */         this.tg = (c.getGreen() / 220.0F);
/* 231 */         this.tb = (c.getBlue() / 220.0F);
/*     */       }
/* 233 */       if (this.cr < this.tr) this.cr += 0.05F; if (this.cr > this.tr) this.cr -= 0.05F;
/* 234 */       if (this.cg < this.tg) this.cg += 0.05F; if (this.cg > this.tg) this.cg -= 0.05F;
/* 235 */       if (this.cb < this.tb) this.cb += 0.05F; if (this.cb > this.tb) this.cb -= 0.05F;
/* 236 */       this.spin += this.spinInc;
/* 237 */       if (this.spin > 360.0F) { this.spin -= 360.0F;
/*     */       }
/* 239 */       if ((this.aspect != null) && (this.spinInc < 20.0F) && (!gettingPower())) {
/* 240 */         this.spinInc += 0.1F;
/* 241 */         if (this.spinInc > 20.0F) this.spinInc = 20.0F;
/*     */       }
/* 243 */       else if (((this.aspect == null) || (gettingPower())) && (this.spinInc > 0.0F)) {
/* 244 */         this.spinInc -= 0.2F;
/* 245 */         if (this.spinInc < 0.0F) { this.spinInc = 0.0F;
/*     */         }
/*     */       }
/* 248 */       if (this.venting > 0) {
/* 249 */         this.venting -= 1;
/* 250 */         float fx = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 251 */         float fz = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 252 */         float fy = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 253 */         float fx2 = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 254 */         float fz2 = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 255 */         float fy2 = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 256 */         int color = 16777215;
/* 257 */         Thaumcraft.proxy.drawVentParticles(this.field_145850_b, this.field_145851_c + 0.5F + fx + this.facing.getOpposite().offsetX / 2.1F, this.field_145848_d + 0.5F + fy + this.facing.getOpposite().offsetY / 2.1F, this.field_145849_e + 0.5F + fz + this.facing.getOpposite().offsetZ / 2.1F, this.facing.getOpposite().offsetX / 4.0F + fx2, this.facing.getOpposite().offsetY / 4.0F + fy2, this.facing.getOpposite().offsetZ / 4.0F + fz2, color);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 268 */   int venting = 0;
/*     */   
/*     */ 
/*     */   public boolean func_145842_c(int i, int j)
/*     */   {
/* 273 */     if (i >= 0)
/*     */     {
/* 275 */       if (this.field_145850_b.field_72995_K) {
/* 276 */         this.venting = 7;
/*     */       }
/* 278 */       return true;
/*     */     }
/*     */     
/* 281 */     return super.func_145842_c(i, j);
/*     */   }
/*     */   
/*     */   public void eject()
/*     */   {
/* 286 */     ItemStack stack = new ItemStack(ConfigItems.itemCrystalEssence, 1, 0);
/* 287 */     ((ItemCrystalEssence)stack.func_77973_b()).setAspects(stack, new AspectList().add(this.aspect, 1));
/* 288 */     TileEntity inventory = this.field_145850_b.func_147438_o(this.field_145851_c + this.facing.getOpposite().offsetX, this.field_145848_d + this.facing.getOpposite().offsetY, this.field_145849_e + this.facing.getOpposite().offsetZ);
/*     */     
/* 290 */     if ((inventory != null) && ((inventory instanceof IInventory))) {
/* 291 */       stack = InventoryUtils.placeItemStackIntoInventory(stack, (IInventory)inventory, this.facing.ordinal(), true);
/*     */     }
/*     */     
/* 294 */     if (stack != null) {
/* 295 */       spawnItem(stack);
/*     */     }
/* 297 */     this.field_145850_b.func_72908_a(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, "random.fizz", 0.25F, 2.6F + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.8F);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean spawnItem(ItemStack stack)
/*     */   {
/* 303 */     EntityItem ie2 = new EntityItem(this.field_145850_b, this.field_145851_c + 0.5D + this.facing.getOpposite().offsetX * 0.65D, this.field_145848_d + 0.5D + this.facing.getOpposite().offsetY * 0.65D, this.field_145849_e + 0.5D + this.facing.getOpposite().offsetZ * 0.65D, stack);
/*     */     
/*     */ 
/*     */ 
/* 307 */     ie2.field_70159_w = (this.facing.getOpposite().offsetX * 0.04F);
/* 308 */     ie2.field_70181_x = (this.facing.getOpposite().offsetY * 0.04F);
/* 309 */     ie2.field_70179_y = (this.facing.getOpposite().offsetZ * 0.04F);
/* 310 */     this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, func_145838_q(), 0, 0);
/* 311 */     return this.field_145850_b.func_72838_d(ie2);
/*     */   }
/*     */   
/*     */   void fillReservoir() {
/* 315 */     TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.facing);
/* 316 */     if (te != null) {
/* 317 */       IEssentiaTransport ic = (IEssentiaTransport)te;
/* 318 */       if (!ic.canOutputTo(this.facing.getOpposite())) { return;
/*     */       }
/* 320 */       Aspect ta = null;
/*     */       
/* 322 */       if ((ic.getEssentiaAmount(this.facing.getOpposite()) > 0) && 
/* 323 */         (ic.getSuctionAmount(this.facing.getOpposite()) < getSuctionAmount(this.facing)) && (getSuctionAmount(this.facing) >= ic.getMinimumSuction()))
/*     */       {
/* 325 */         ta = ic.getEssentiaType(this.facing.getOpposite());
/*     */       }
/*     */       
/*     */ 
/* 329 */       if ((ta != null) && (ic.getSuctionAmount(this.facing.getOpposite()) < getSuctionAmount(this.facing))) {
/* 330 */         addToContainer(ta, ic.takeEssentia(ta, 1, this.facing.getOpposite()));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean gettingPower()
/*     */   {
/* 337 */     return this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileEssentiaCrystalizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */