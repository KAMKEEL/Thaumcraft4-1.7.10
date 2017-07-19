/*     */ package thaumcraft.api.aspects;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Set;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ 
/*     */ public class AspectList implements Serializable
/*     */ {
/*  13 */   public LinkedHashMap<Aspect, Integer> aspects = new LinkedHashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public AspectList(ItemStack stack)
/*     */   {
/*     */     try
/*     */     {
/*  22 */       AspectList temp = ThaumcraftApiHelper.getObjectAspects(stack);
/*  23 */       if (temp != null) {
/*  24 */         for (Aspect tag : temp.getAspects()) {
/*  25 */           add(tag, temp.getAmount(tag));
/*     */         }
/*     */       }
/*     */     } catch (Exception e) {}
/*     */   }
/*     */   
/*     */   public AspectList() {}
/*     */   
/*     */   public AspectList copy() {
/*  34 */     AspectList out = new AspectList();
/*  35 */     for (Aspect a : getAspects())
/*  36 */       out.add(a, getAmount(a));
/*  37 */     return out;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/*  44 */     return this.aspects.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int visSize()
/*     */   {
/*  51 */     int q = 0;
/*     */     
/*  53 */     for (Aspect as : this.aspects.keySet()) {
/*  54 */       q += getAmount(as);
/*     */     }
/*     */     
/*  57 */     return q;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Aspect[] getAspects()
/*     */   {
/*  64 */     Aspect[] q = new Aspect[1];
/*  65 */     return (Aspect[])this.aspects.keySet().toArray(q);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Aspect[] getPrimalAspects()
/*     */   {
/*  72 */     AspectList t = new AspectList();
/*  73 */     for (Aspect as : this.aspects.keySet()) {
/*  74 */       if (as.isPrimal()) {
/*  75 */         t.add(as, 1);
/*     */       }
/*     */     }
/*  78 */     Aspect[] q = new Aspect[1];
/*  79 */     return (Aspect[])t.aspects.keySet().toArray(q);
/*     */   }
/*     */   
/*     */ 
/*     */   public Aspect[] getAspectsSorted()
/*     */   {
/*     */     try
/*     */     {
/*  87 */       Aspect[] out = (Aspect[])this.aspects.keySet().toArray(new Aspect[0]);
/*  88 */       boolean change = false;
/*     */       do {
/*  90 */         change = false;
/*  91 */         for (int a = 0; a < out.length - 1; a++) {
/*  92 */           Aspect e1 = out[a];
/*  93 */           Aspect e2 = out[(a + 1)];
/*  94 */           if ((e1 != null) && (e2 != null) && (e1.getTag().compareTo(e2.getTag()) > 0)) {
/*  95 */             out[a] = e2;
/*  96 */             out[(a + 1)] = e1;
/*  97 */             change = true;
/*  98 */             break;
/*     */           }
/*     */         }
/* 101 */       } while (change == true);
/* 102 */       return out;
/*     */     } catch (Exception e) {}
/* 104 */     return getAspects();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Aspect[] getAspectsSortedAmount()
/*     */   {
/*     */     try
/*     */     {
/* 113 */       Aspect[] out = (Aspect[])this.aspects.keySet().toArray(new Aspect[1]);
/* 114 */       boolean change = false;
/*     */       do {
/* 116 */         change = false;
/* 117 */         for (int a = 0; a < out.length - 1; a++) {
/* 118 */           int e1 = getAmount(out[a]);
/* 119 */           int e2 = getAmount(out[(a + 1)]);
/* 120 */           if ((e1 > 0) && (e2 > 0) && (e2 > e1)) {
/* 121 */             Aspect ea = out[a];
/* 122 */             Aspect eb = out[(a + 1)];
/* 123 */             out[a] = eb;
/* 124 */             out[(a + 1)] = ea;
/* 125 */             change = true;
/* 126 */             break;
/*     */           }
/*     */         }
/* 129 */       } while (change == true);
/* 130 */       return out;
/*     */     } catch (Exception e) {}
/* 132 */     return getAspects();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getAmount(Aspect key)
/*     */   {
/* 141 */     return this.aspects.get(key) == null ? 0 : ((Integer)this.aspects.get(key)).intValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean reduce(Aspect key, int amount)
/*     */   {
/* 151 */     if (getAmount(key) >= amount) {
/* 152 */       int am = getAmount(key) - amount;
/* 153 */       this.aspects.put(key, Integer.valueOf(am));
/* 154 */       return true;
/*     */     }
/* 156 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AspectList remove(Aspect key, int amount)
/*     */   {
/* 167 */     int am = getAmount(key) - amount;
/* 168 */     if (am <= 0) this.aspects.remove(key); else
/* 169 */       this.aspects.put(key, Integer.valueOf(am));
/* 170 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AspectList remove(Aspect key)
/*     */   {
/* 180 */     this.aspects.remove(key);
/* 181 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AspectList add(Aspect aspect, int amount)
/*     */   {
/* 192 */     if (this.aspects.containsKey(aspect)) {
/* 193 */       int oldamount = ((Integer)this.aspects.get(aspect)).intValue();
/* 194 */       amount += oldamount;
/*     */     }
/* 196 */     this.aspects.put(aspect, Integer.valueOf(amount));
/* 197 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AspectList merge(Aspect aspect, int amount)
/*     */   {
/* 209 */     if (this.aspects.containsKey(aspect)) {
/* 210 */       int oldamount = ((Integer)this.aspects.get(aspect)).intValue();
/* 211 */       if (amount < oldamount) { amount = oldamount;
/*     */       }
/*     */     }
/* 214 */     this.aspects.put(aspect, Integer.valueOf(amount));
/* 215 */     return this;
/*     */   }
/*     */   
/*     */   public AspectList add(AspectList in) {
/* 219 */     for (Aspect a : in.getAspects())
/* 220 */       add(a, in.getAmount(a));
/* 221 */     return this;
/*     */   }
/*     */   
/*     */   public AspectList merge(AspectList in) {
/* 225 */     for (Aspect a : in.getAspects())
/* 226 */       merge(a, in.getAmount(a));
/* 227 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void readFromNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 237 */     this.aspects.clear();
/* 238 */     NBTTagList tlist = nbttagcompound.func_150295_c("Aspects", 10);
/* 239 */     for (int j = 0; j < tlist.func_74745_c(); j++) {
/* 240 */       NBTTagCompound rs = tlist.func_150305_b(j);
/* 241 */       if (rs.func_74764_b("key")) {
/* 242 */         add(Aspect.getAspect(rs.func_74779_i("key")), rs.func_74762_e("amount"));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void readFromNBT(NBTTagCompound nbttagcompound, String label)
/*     */   {
/* 250 */     this.aspects.clear();
/* 251 */     NBTTagList tlist = nbttagcompound.func_150295_c(label, 10);
/* 252 */     for (int j = 0; j < tlist.func_74745_c(); j++) {
/* 253 */       NBTTagCompound rs = tlist.func_150305_b(j);
/* 254 */       if (rs.func_74764_b("key")) {
/* 255 */         add(Aspect.getAspect(rs.func_74779_i("key")), rs.func_74762_e("amount"));
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
/*     */   public void writeToNBT(NBTTagCompound nbttagcompound)
/*     */   {
/* 268 */     NBTTagList tlist = new NBTTagList();
/* 269 */     nbttagcompound.func_74782_a("Aspects", tlist);
/* 270 */     for (Aspect aspect : getAspects()) {
/* 271 */       if (aspect != null) {
/* 272 */         NBTTagCompound f = new NBTTagCompound();
/* 273 */         f.func_74778_a("key", aspect.getTag());
/* 274 */         f.func_74768_a("amount", getAmount(aspect));
/* 275 */         tlist.func_74742_a(f);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void writeToNBT(NBTTagCompound nbttagcompound, String label) {
/* 281 */     NBTTagList tlist = new NBTTagList();
/* 282 */     nbttagcompound.func_74782_a(label, tlist);
/* 283 */     for (Aspect aspect : getAspects()) {
/* 284 */       if (aspect != null) {
/* 285 */         NBTTagCompound f = new NBTTagCompound();
/* 286 */         f.func_74778_a("key", aspect.getTag());
/* 287 */         f.func_74768_a("amount", getAmount(aspect));
/* 288 */         tlist.func_74742_a(f);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/aspects/AspectList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */