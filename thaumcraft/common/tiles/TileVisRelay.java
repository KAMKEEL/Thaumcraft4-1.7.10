/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.awt.Color;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.EnumSkyBlock;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.visnet.TileVisNode;
/*     */ import thaumcraft.api.visnet.VisNetHandler;
/*     */ import thaumcraft.api.wands.IWandable;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ public class TileVisRelay extends TileVisNode implements IWandable
/*     */ {
/*     */   public short orientation;
/*     */   
/*     */   public TileVisRelay()
/*     */   {
/*  29 */     this.orientation = 1;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox()
/*     */   {
/*  35 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  41 */   public byte color = -1;
/*     */   
/*     */   public byte getAttunement()
/*     */   {
/*  45 */     return this.color;
/*     */   }
/*     */   
/*     */   public int getRange()
/*     */   {
/*  50 */     return 8;
/*     */   }
/*     */   
/*     */   public boolean isSource()
/*     */   {
/*  55 */     return false;
/*     */   }
/*     */   
/*     */   public void parentChanged()
/*     */   {
/*  60 */     if ((this.field_145850_b != null) && 
/*  61 */       (this.field_145850_b.field_72995_K)) {
/*  62 */       this.field_145850_b.func_147463_c(EnumSkyBlock.Block, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_145843_s()
/*     */   {
/*  69 */     this.beam1 = null;
/*  70 */     super.func_145843_s();
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_145845_h()
/*     */   {
/*  76 */     drawEffect();
/*     */     
/*  78 */     super.func_145845_h();
/*     */     
/*  80 */     if ((!this.field_145850_b.field_72995_K) && 
/*  81 */       (this.nodeCounter % 20 == 0)) {
/*  82 */       List<EntityPlayer> var5 = this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + 1).func_72314_b(5.0D, 5.0D, 5.0D));
/*     */       
/*     */ 
/*     */ 
/*  86 */       if ((var5 != null) && (var5.size() > 0)) {
/*  87 */         for (EntityPlayer player : var5) {
/*  88 */           if ((!nearbyPlayers.containsKey(Integer.valueOf(player.func_145782_y()))) || (((WeakReference)nearbyPlayers.get(Integer.valueOf(player.func_145782_y()))).get() == null) || (((TileVisRelay)((WeakReference)nearbyPlayers.get(Integer.valueOf(player.func_145782_y()))).get()).func_145835_a(player.field_70165_t, player.field_70163_u, player.field_70161_v) >= func_145835_a(player.field_70165_t, player.field_70163_u, player.field_70161_v)))
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  94 */             nearbyPlayers.put(Integer.valueOf(player.func_145782_y()), new WeakReference(this));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/* 101 */   public static HashMap<Integer, WeakReference<TileVisRelay>> nearbyPlayers = new HashMap();
/*     */   
/* 103 */   protected Object beam1 = null;
/*     */   protected int pulse;
/*     */   
/* 106 */   protected void drawEffect() { if (this.field_145850_b.field_72995_K) {
/* 107 */       if (this.parentLoaded) {
/* 108 */         if ((this.px != 0) || (this.py != 0) || (this.pz != 0)) {
/* 109 */           TileEntity tile = func_145831_w().func_147438_o(this.field_145851_c - this.px, this.field_145848_d - this.py, this.field_145849_e - this.pz);
/* 110 */           if ((tile != null) && ((tile instanceof TileVisNode))) {
/* 111 */             setParent(new WeakReference(tile));
/*     */           }
/*     */         } else {
/* 114 */           setParent(null);
/*     */         }
/* 116 */         this.parentLoaded = false;
/* 117 */         parentChanged();
/*     */       }
/* 119 */       if (VisNetHandler.isNodeValid(getParent())) {
/* 120 */         double xx = ((TileVisNode)getParent().get()).field_145851_c + 0.5D;
/* 121 */         double yy = ((TileVisNode)getParent().get()).field_145848_d + 0.5D;
/* 122 */         double zz = ((TileVisNode)getParent().get()).field_145849_e + 0.5D;
/* 123 */         ForgeDirection d1 = ForgeDirection.UNKNOWN;
/* 124 */         if ((getParent().get() instanceof TileVisRelay)) {
/* 125 */           d1 = ForgeDirection.getOrientation(((TileVisRelay)getParent().get()).orientation);
/*     */         }
/* 127 */         ForgeDirection d2 = ForgeDirection.getOrientation(this.orientation);
/* 128 */         this.beam1 = Thaumcraft.proxy.beamPower(this.field_145850_b, xx - d1.offsetX * 0.05D, yy - d1.offsetY * 0.05D, zz - d1.offsetZ * 0.05D, this.field_145851_c + 0.5D - d2.offsetX * 0.05D, this.field_145848_d + 0.5D - d2.offsetY * 0.05D, this.field_145849_e + 0.5D - d2.offsetZ * 0.05D, this.pRed, this.pGreen, this.pBlue, this.pulse > 0, this.beam1);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 134 */       if (this.pRed < 1.0F) this.pRed += 0.025F;
/* 135 */       if (this.pRed > 1.0F) this.pRed = 1.0F;
/* 136 */       if (this.pGreen < 1.0F) this.pGreen += 0.025F;
/* 137 */       if (this.pGreen > 1.0F) this.pGreen = 1.0F;
/* 138 */       if (this.pBlue < 1.0F) this.pBlue += 0.025F;
/* 139 */       if (this.pBlue > 1.0F) this.pBlue = 1.0F;
/*     */     }
/* 141 */     if (this.pulse > 0) this.pulse -= 1;
/*     */   }
/*     */   
/*     */   public void triggerConsumeEffect(Aspect aspect)
/*     */   {
/* 146 */     addPulse(aspect);
/*     */   }
/*     */   
/*     */ 
/* 150 */   public float pRed = 0.5F;
/* 151 */   public float pGreen = 0.5F;
/* 152 */   public float pBlue = 0.5F;
/*     */   
/*     */   protected void addPulse(Aspect aspect) {
/* 155 */     int c = -1;
/* 156 */     if (aspect == Aspect.AIR) { c = 0;
/* 157 */     } else if (aspect == Aspect.FIRE) { c = 1;
/* 158 */     } else if (aspect == Aspect.WATER) { c = 2;
/* 159 */     } else if (aspect == Aspect.EARTH) { c = 3;
/* 160 */     } else if (aspect == Aspect.ORDER) { c = 4;
/* 161 */     } else if (aspect == Aspect.ENTROPY) c = 5;
/* 162 */     if ((c >= 0) && (this.pulse == 0)) {
/* 163 */       this.pulse = 5;
/* 164 */       this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, func_145838_q(), 0, c);
/*     */     }
/*     */   }
/*     */   
/* 168 */   public static final int[] colors = { 16777086, 16727041, 37119, 40960, 15650047, 5592439 };
/*     */   
/*     */ 
/*     */   protected int px;
/*     */   
/*     */ 
/*     */   protected int py;
/*     */   
/*     */   protected int pz;
/*     */   
/*     */ 
/*     */   public boolean func_145842_c(int i, int j)
/*     */   {
/* 181 */     if (i == 0)
/*     */     {
/* 183 */       if (this.field_145850_b.field_72995_K) {
/* 184 */         Color c = new Color(colors[j]);
/* 185 */         this.pulse = 5;
/* 186 */         this.pRed = (c.getRed() / 255.0F);
/* 187 */         this.pGreen = (c.getGreen() / 255.0F);
/* 188 */         this.pBlue = (c.getBlue() / 255.0F);
/* 189 */         WeakReference<TileVisNode> vr = getParent();
/*     */         
/* 191 */         while ((VisNetHandler.isNodeValid(vr)) && ((vr.get() instanceof TileVisRelay)) && (((TileVisRelay)vr.get()).pulse == 0))
/*     */         {
/* 193 */           ((TileVisRelay)vr.get()).pRed = this.pRed;
/* 194 */           ((TileVisRelay)vr.get()).pGreen = this.pGreen;
/* 195 */           ((TileVisRelay)vr.get()).pBlue = this.pBlue;
/* 196 */           ((TileVisRelay)vr.get()).pulse = 5;
/* 197 */           vr = ((TileVisNode)vr.get()).getParent();
/*     */         }
/*     */       }
/* 200 */       return true;
/*     */     }
/* 202 */     return super.func_145842_c(i, j);
/*     */   }
/*     */   
/*     */ 
/* 206 */   protected boolean parentLoaded = false;
/*     */   
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 210 */     super.readCustomNBT(nbttagcompound);
/* 211 */     this.orientation = nbttagcompound.func_74765_d("orientation");
/* 212 */     this.color = nbttagcompound.func_74771_c("color");
/* 213 */     this.px = nbttagcompound.func_74771_c("px");
/* 214 */     this.py = nbttagcompound.func_74771_c("py");
/* 215 */     this.pz = nbttagcompound.func_74771_c("pz");
/* 216 */     this.parentLoaded = true;
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 221 */     super.writeCustomNBT(nbttagcompound);
/* 222 */     nbttagcompound.func_74777_a("orientation", this.orientation);
/* 223 */     nbttagcompound.func_74774_a("color", this.color);
/*     */     
/* 225 */     if (VisNetHandler.isNodeValid(getParent())) {
/* 226 */       nbttagcompound.func_74774_a("px", (byte)(this.field_145851_c - ((TileVisNode)getParent().get()).field_145851_c));
/* 227 */       nbttagcompound.func_74774_a("py", (byte)(this.field_145848_d - ((TileVisNode)getParent().get()).field_145848_d));
/* 228 */       nbttagcompound.func_74774_a("pz", (byte)(this.field_145849_e - ((TileVisNode)getParent().get()).field_145849_e));
/*     */     } else {
/* 230 */       nbttagcompound.func_74774_a("px", (byte)0);
/* 231 */       nbttagcompound.func_74774_a("py", (byte)0);
/* 232 */       nbttagcompound.func_74774_a("pz", (byte)0);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md)
/*     */   {
/* 240 */     if (!this.field_145850_b.field_72995_K) {
/* 241 */       this.color = ((byte)(this.color + 1));
/* 242 */       if (this.color > 5) this.color = -1;
/* 243 */       removeThisNode();
/* 244 */       this.nodeRefresh = true;
/* 245 */       func_70296_d();
/* 246 */       world.func_147471_g(x, y, z);
/* 247 */       world.func_72908_a(x, y, z, "thaumcraft:crystal", 0.2F, 1.0F);
/*     */     }
/*     */     
/* 250 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player)
/*     */   {
/* 257 */     return null;
/*     */   }
/*     */   
/*     */   public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {}
/*     */   
/*     */   public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileVisRelay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */