/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
/*     */ import thaumcraft.api.nodes.INode;
/*     */ import thaumcraft.api.nodes.NodeModifier;
/*     */ import thaumcraft.api.nodes.NodeType;
/*     */ import thaumcraft.api.wands.IWandable;
/*     */ import thaumcraft.common.CommonProxy;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ 
/*     */ public class TileJarNode extends TileJar implements IAspectContainer, INode, IWandable
/*     */ {
/*  23 */   private AspectList aspects = new AspectList();
/*  24 */   private AspectList aspectsBase = new AspectList();
/*     */   
/*  26 */   private NodeType nodeType = NodeType.NORMAL;
/*  27 */   private NodeModifier nodeModifier = null;
/*     */   
/*  29 */   private String id = "";
/*     */   
/*  31 */   public long animate = 0L;
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/*  35 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void readCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  41 */     this.aspects.readFromNBT(nbttagcompound);
/*  42 */     this.id = nbttagcompound.func_74779_i("nodeId");
/*     */     
/*  44 */     AspectList al = new AspectList();
/*  45 */     NBTTagList tlist = nbttagcompound.func_150295_c("AspectsBase", 10);
/*  46 */     for (int j = 0; j < tlist.func_74745_c(); j++) {
/*  47 */       NBTTagCompound rs = tlist.func_150305_b(j);
/*  48 */       if (rs.func_74764_b("key")) {
/*  49 */         al.add(Aspect.getAspect(rs.func_74779_i("key")), rs.func_74762_e("amount"));
/*     */       }
/*     */     }
/*  52 */     Short oldBase = Short.valueOf(nbttagcompound.func_74765_d("nodeVisBase"));
/*  53 */     this.aspectsBase = new AspectList();
/*  54 */     if ((oldBase.shortValue() > 0) && (al.size() == 0)) {
/*  55 */       for (Aspect a : this.aspects.getAspects()) {
/*  56 */         this.aspectsBase.merge(a, oldBase.shortValue());
/*     */       }
/*     */     } else {
/*  59 */       this.aspectsBase = al.copy();
/*     */     }
/*     */     
/*  62 */     setNodeType(NodeType.values()[nbttagcompound.func_74771_c("type")]);
/*  63 */     byte mod = nbttagcompound.func_74771_c("modifier");
/*  64 */     if (mod >= 0) {
/*  65 */       setNodeModifier(NodeModifier.values()[mod]);
/*     */     } else {
/*  67 */       setNodeModifier(null);
/*     */     }
/*     */   }
/*     */   
/*     */   public void writeCustomNBT(NBTTagCompound nbttagcompound)
/*     */   {
/*  73 */     this.aspects.writeToNBT(nbttagcompound);
/*  74 */     nbttagcompound.func_74778_a("nodeId", this.id);
/*  75 */     NBTTagList tlist = new NBTTagList();
/*  76 */     nbttagcompound.func_74782_a("AspectsBase", tlist);
/*  77 */     for (Aspect aspect : this.aspectsBase.getAspects())
/*  78 */       if (aspect != null) {
/*  79 */         NBTTagCompound f = new NBTTagCompound();
/*  80 */         f.func_74778_a("key", aspect.getTag());
/*  81 */         f.func_74768_a("amount", this.aspectsBase.getAmount(aspect));
/*  82 */         tlist.func_74742_a(f);
/*     */       }
/*  84 */     nbttagcompound.func_74774_a("type", (byte)getNodeType().ordinal());
/*  85 */     nbttagcompound.func_74774_a("modifier", getNodeModifier() == null ? -1 : (byte)getNodeModifier().ordinal());
/*     */   }
/*     */   
/*     */ 
/*     */   public AspectList getAspects()
/*     */   {
/*  91 */     return this.aspects;
/*     */   }
/*     */   
/*     */   public AspectList getAspectsBase()
/*     */   {
/*  96 */     return this.aspectsBase;
/*     */   }
/*     */   
/*     */   public void setAspects(AspectList aspects) {
/* 100 */     this.aspects = aspects.copy();
/* 101 */     this.aspectsBase = aspects.copy();
/*     */   }
/*     */   
/*     */   public int addToContainer(Aspect tt, int am)
/*     */   {
/* 106 */     int out = 0;
/* 107 */     if (this.aspects.getAmount(tt) + am > this.aspectsBase.getAmount(tt)) {
/* 108 */       out = this.aspects.getAmount(tt) + am - this.aspectsBase.getAmount(tt);
/*     */     }
/* 110 */     this.aspects.add(tt, am - out);
/* 111 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 112 */     func_70296_d();
/* 113 */     return out;
/*     */   }
/*     */   
/*     */   public boolean takeFromContainer(Aspect tt, int am)
/*     */   {
/* 118 */     if (this.aspects.getAmount(tt) >= am) {
/* 119 */       this.aspects.remove(tt, am);
/* 120 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 121 */       func_70296_d();
/* 122 */       return true;
/*     */     }
/* 124 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean takeFromContainer(AspectList ot)
/*     */   {
/* 130 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean doesContainerContainAmount(Aspect tag, int amt)
/*     */   {
/* 136 */     if (this.aspects.getAmount(tag) >= amt) return true;
/* 137 */     return false;
/*     */   }
/*     */   
/*     */   public boolean doesContainerContain(AspectList ot)
/*     */   {
/* 142 */     for (Aspect tt : ot.getAspects()) {
/* 143 */       if (this.aspects.getAmount(tt) < ot.getAmount(tt)) return false;
/*     */     }
/* 145 */     return true;
/*     */   }
/*     */   
/*     */   public int containerContains(Aspect tag)
/*     */   {
/* 150 */     return this.aspects.getAmount(tag);
/*     */   }
/*     */   
/*     */   public NodeType getNodeType()
/*     */   {
/* 155 */     return this.nodeType;
/*     */   }
/*     */   
/*     */   public void setNodeType(NodeType nodeType)
/*     */   {
/* 160 */     this.nodeType = nodeType;
/*     */   }
/*     */   
/*     */   public void setNodeModifier(NodeModifier nodeModifier)
/*     */   {
/* 165 */     this.nodeModifier = nodeModifier;
/*     */   }
/*     */   
/*     */   public NodeModifier getNodeModifier()
/*     */   {
/* 170 */     return this.nodeModifier;
/*     */   }
/*     */   
/*     */   public int getNodeVisBase(Aspect aspect)
/*     */   {
/* 175 */     return this.aspectsBase.getAmount(aspect);
/*     */   }
/*     */   
/*     */   public String getId()
/*     */   {
/* 180 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/* 184 */     this.id = id;
/*     */   }
/*     */   
/*     */   public void setNodeVisBase(Aspect aspect, short nodeVisBase)
/*     */   {
/* 189 */     if (this.aspectsBase.getAmount(aspect) < nodeVisBase) {
/* 190 */       this.aspectsBase.merge(aspect, nodeVisBase);
/*     */     } else {
/* 192 */       this.aspectsBase.reduce(aspect, this.aspectsBase.getAmount(aspect) - nodeVisBase);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_145842_c(int i, int j)
/*     */   {
/* 198 */     if (i == 9)
/*     */     {
/* 200 */       if (this.field_145850_b.field_72995_K) {
/* 201 */         for (int yy = -1; yy < 3; yy++) {
/* 202 */           for (int xx = -1; xx < 2; xx++)
/* 203 */             for (int zz = -1; zz < 2; zz++)
/* 204 */               Thaumcraft.proxy.blockSparkle(this.field_145850_b, this.field_145851_c + xx, this.field_145848_d + yy, this.field_145849_e + zz, 55537, 5);
/*     */         }
/* 206 */         this.animate = (System.currentTimeMillis() + 1000L);
/*     */       }
/* 208 */       return true;
/*     */     }
/*     */     
/*     */ 
/* 212 */     return super.func_145842_c(i, j);
/*     */   }
/*     */   
/* 215 */   public boolean drop = true;
/*     */   
/*     */ 
/*     */   public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md)
/*     */   {
/* 220 */     if (!world.field_72995_K) {
/* 221 */       this.drop = false;
/* 222 */       world.func_147465_d(x, y, z, ConfigBlocks.blockAiry, 0, 3);
/* 223 */       TileNode tn = (TileNode)world.func_147438_o(x, y, z);
/* 224 */       if (tn != null) {
/* 225 */         tn.setAspects(getAspects());
/* 226 */         tn.setNodeModifier(getNodeModifier());
/* 227 */         tn.setNodeType(getNodeType());
/* 228 */         tn.id = getId();
/* 229 */         world.func_147471_g(x, y, z);
/* 230 */         tn.func_70296_d();
/*     */       }
/*     */     }
/* 233 */     world.func_72926_e(2001, x, y, z, Block.func_149682_b(ConfigBlocks.blockJar) + 61440);
/* 234 */     player.field_70170_p.func_72980_b(x + 0.5D, y + 0.5D, z + 0.5D, "random.glass", 1.0F, 0.9F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F, false);
/* 235 */     player.func_71038_i();
/* 236 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player)
/*     */   {
/* 243 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean doesContainerAccept(Aspect tag)
/*     */   {
/* 262 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/tiles/TileJarNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */