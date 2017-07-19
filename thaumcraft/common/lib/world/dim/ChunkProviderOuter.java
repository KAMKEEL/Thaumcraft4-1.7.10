/*     */ package thaumcraft.common.lib.world.dim;
/*     */ 
/*     */ import cpw.mods.fml.common.eventhandler.EventBus;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.util.IProgressUpdate;
/*     */ import net.minecraft.world.ChunkPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldType;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.biome.WorldChunkManager;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ import net.minecraft.world.storage.WorldInfo;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.terraingen.PopulateChunkEvent.Post;
/*     */ import net.minecraftforge.event.terraingen.PopulateChunkEvent.Pre;
/*     */ 
/*     */ 
/*     */ public class ChunkProviderOuter
/*     */   implements IChunkProvider
/*     */ {
/*     */   private Random rand;
/*     */   private World worldObj;
/*     */   private WorldType worldType;
/*     */   private BiomeGenBase[] biomesForGeneration;
/*     */   
/*     */   public ChunkProviderOuter(World p_i2006_1_, long p_i2006_2_, boolean p_i2006_4_)
/*     */   {
/*  32 */     this.worldObj = p_i2006_1_;
/*  33 */     this.worldType = p_i2006_1_.func_72912_H().func_76067_t();
/*  34 */     this.rand = new Random(p_i2006_2_);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Chunk func_73158_c(int p_73158_1_, int p_73158_2_)
/*     */   {
/*  45 */     return func_73154_d(p_73158_1_, p_73158_2_);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Chunk func_73154_d(int p_73154_1_, int p_73154_2_)
/*     */   {
/*  55 */     this.rand.setSeed(p_73154_1_ * 341873128712L + p_73154_2_ * 132897987541L);
/*  56 */     Block[] ablock = new Block[32768];
/*  57 */     byte[] meta = new byte[ablock.length];
/*     */     
/*  59 */     for (int k = 0; k < 16; k++)
/*     */     {
/*  61 */       for (int l = 0; l < 16; l++)
/*     */       {
/*  63 */         for (int j1 = 127; j1 >= 0; j1--)
/*     */         {
/*  65 */           int k1 = (l * 16 + k) * 128 + j1;
/*  66 */           ablock[k1] = null;
/*  67 */           meta[k1] = 0;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  72 */     this.biomesForGeneration = this.worldObj.func_72959_q().func_76933_b(this.biomesForGeneration, p_73154_1_ * 16, p_73154_2_ * 16, 16, 16);
/*     */     
/*  74 */     Chunk chunk = new Chunk(this.worldObj, ablock, meta, p_73154_1_, p_73154_2_);
/*  75 */     byte[] abyte = chunk.func_76605_m();
/*     */     
/*  77 */     for (int k = 0; k < abyte.length; k++)
/*     */     {
/*  79 */       abyte[k] = ((byte)this.biomesForGeneration[k].field_76756_M);
/*     */     }
/*     */     
/*  82 */     chunk.func_76603_b();
/*  83 */     return chunk;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_73149_a(int p_73149_1_, int p_73149_2_)
/*     */   {
/*  93 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_73153_a(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_)
/*     */   {
/* 102 */     net.minecraft.block.BlockFalling.field_149832_M = true;
/* 103 */     MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(p_73153_1_, this.worldObj, this.worldObj.field_73012_v, p_73153_2_, p_73153_3_, false));
/*     */     
/* 105 */     int k = p_73153_2_ * 16;
/* 106 */     int l = p_73153_3_ * 16;
/* 107 */     BiomeGenBase biomegenbase = this.worldObj.func_72807_a(k + 16, l + 16);
/* 108 */     biomegenbase.func_76728_a(this.worldObj, this.worldObj.field_73012_v, k, l);
/*     */     
/*     */ 
/*     */ 
/* 112 */     MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(p_73153_1_, this.worldObj, this.worldObj.field_73012_v, p_73153_2_, p_73153_3_, false));
/* 113 */     net.minecraft.block.BlockFalling.field_149832_M = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_73151_a(boolean p_73151_1_, IProgressUpdate p_73151_2_)
/*     */   {
/* 123 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_104112_b() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_73156_b()
/*     */   {
/* 139 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_73157_c()
/*     */   {
/* 148 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String func_73148_d()
/*     */   {
/* 157 */     return "RandomLevelSource";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List func_73155_a(EnumCreatureType p_73155_1_, int p_73155_2_, int p_73155_3_, int p_73155_4_)
/*     */   {
/* 166 */     BiomeGenBase biomegenbase = this.worldObj.func_72807_a(p_73155_2_, p_73155_4_);
/* 167 */     return biomegenbase.func_76747_a(p_73155_1_);
/*     */   }
/*     */   
/*     */ 
/*     */   public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_)
/*     */   {
/* 173 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_73152_e()
/*     */   {
/* 179 */     return 0;
/*     */   }
/*     */   
/*     */   public void func_82695_e(int p_82695_1_, int p_82695_2_) {}
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/lib/world/dim/ChunkProviderOuter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */