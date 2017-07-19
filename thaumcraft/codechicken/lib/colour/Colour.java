/*     */ package thaumcraft.codechicken.lib.colour;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.codechicken.lib.math.MathHelper;
/*     */ import thaumcraft.codechicken.lib.util.Copyable;
/*     */ 
/*     */ public abstract class Colour
/*     */   implements Copyable<Colour>
/*     */ {
/*     */   public byte r;
/*     */   public byte g;
/*     */   public byte b;
/*     */   public byte a;
/*     */   
/*     */   public Colour(int r, int g, int b, int a)
/*     */   {
/*  19 */     this.r = ((byte)r);
/*  20 */     this.g = ((byte)g);
/*  21 */     this.b = ((byte)b);
/*  22 */     this.a = ((byte)a);
/*     */   }
/*     */   
/*     */   public Colour(Colour colour)
/*     */   {
/*  27 */     this.r = colour.r;
/*  28 */     this.g = colour.g;
/*  29 */     this.b = colour.b;
/*  30 */     this.a = colour.a;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void glColour()
/*     */   {
/*  36 */     GL11.glColor4ub(this.r, this.g, this.b, this.a);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void glColour(int a)
/*     */   {
/*  42 */     GL11.glColor4ub(this.r, this.g, this.b, (byte)a);
/*     */   }
/*     */   
/*     */ 
/*     */   public abstract int pack();
/*     */   
/*     */   public String toString()
/*     */   {
/*  50 */     return getClass().getSimpleName() + "[0x" + Integer.toHexString(pack()).toUpperCase() + "]";
/*     */   }
/*     */   
/*     */   public Colour add(Colour colour2)
/*     */   {
/*  55 */     this.a = ((byte)(this.a + colour2.a));
/*  56 */     this.r = ((byte)(this.r + colour2.r));
/*  57 */     this.g = ((byte)(this.g + colour2.g));
/*  58 */     this.b = ((byte)(this.b + colour2.b));
/*  59 */     return this;
/*     */   }
/*     */   
/*     */   public Colour sub(Colour colour2)
/*     */   {
/*  64 */     int ia = (this.a & 0xFF) - (colour2.a & 0xFF);
/*  65 */     int ir = (this.r & 0xFF) - (colour2.r & 0xFF);
/*  66 */     int ig = (this.g & 0xFF) - (colour2.g & 0xFF);
/*  67 */     int ib = (this.b & 0xFF) - (colour2.b & 0xFF);
/*  68 */     this.a = ((byte)(ia < 0 ? 0 : ia));
/*  69 */     this.r = ((byte)(ir < 0 ? 0 : ir));
/*  70 */     this.g = ((byte)(ig < 0 ? 0 : ig));
/*  71 */     this.b = ((byte)(ib < 0 ? 0 : ib));
/*  72 */     return this;
/*     */   }
/*     */   
/*     */   public Colour invert()
/*     */   {
/*  77 */     this.a = ((byte)(255 - (this.a & 0xFF)));
/*  78 */     this.r = ((byte)(255 - (this.r & 0xFF)));
/*  79 */     this.g = ((byte)(255 - (this.g & 0xFF)));
/*  80 */     this.b = ((byte)(255 - (this.b & 0xFF)));
/*  81 */     return this;
/*     */   }
/*     */   
/*     */   public Colour multiply(Colour colour2)
/*     */   {
/*  86 */     this.a = ((byte)(int)((this.a & 0xFF) * ((colour2.a & 0xFF) / 255.0D)));
/*  87 */     this.r = ((byte)(int)((this.r & 0xFF) * ((colour2.r & 0xFF) / 255.0D)));
/*  88 */     this.g = ((byte)(int)((this.g & 0xFF) * ((colour2.g & 0xFF) / 255.0D)));
/*  89 */     this.b = ((byte)(int)((this.b & 0xFF) * ((colour2.b & 0xFF) / 255.0D)));
/*  90 */     return this;
/*     */   }
/*     */   
/*     */   public Colour scale(double d)
/*     */   {
/*  95 */     this.a = ((byte)(int)((this.a & 0xFF) * d));
/*  96 */     this.r = ((byte)(int)((this.r & 0xFF) * d));
/*  97 */     this.g = ((byte)(int)((this.g & 0xFF) * d));
/*  98 */     this.b = ((byte)(int)((this.b & 0xFF) * d));
/*  99 */     return this;
/*     */   }
/*     */   
/*     */   public Colour interpolate(Colour colour2, double d)
/*     */   {
/* 104 */     return add(colour2.copy().sub(this).scale(d));
/*     */   }
/*     */   
/*     */   public Colour multiplyC(double d)
/*     */   {
/* 109 */     this.r = ((byte)(int)MathHelper.clip((this.r & 0xFF) * d, 0.0D, 255.0D));
/* 110 */     this.g = ((byte)(int)MathHelper.clip((this.g & 0xFF) * d, 0.0D, 255.0D));
/* 111 */     this.b = ((byte)(int)MathHelper.clip((this.b & 0xFF) * d, 0.0D, 255.0D));
/*     */     
/* 113 */     return this;
/*     */   }
/*     */   
/*     */   public abstract Colour copy();
/*     */   
/*     */   public int rgb()
/*     */   {
/* 120 */     return (this.r & 0xFF) << 16 | (this.g & 0xFF) << 8 | this.b & 0xFF;
/*     */   }
/*     */   
/*     */   public int argb()
/*     */   {
/* 125 */     return (this.a & 0xFF) << 24 | (this.r & 0xFF) << 16 | (this.g & 0xFF) << 8 | this.b & 0xFF;
/*     */   }
/*     */   
/*     */   public int rgba()
/*     */   {
/* 130 */     return (this.r & 0xFF) << 24 | (this.g & 0xFF) << 16 | (this.b & 0xFF) << 8 | this.a & 0xFF;
/*     */   }
/*     */   
/*     */   public Colour set(Colour colour)
/*     */   {
/* 135 */     this.r = colour.r;
/* 136 */     this.g = colour.g;
/* 137 */     this.b = colour.b;
/* 138 */     this.a = colour.a;
/* 139 */     return this;
/*     */   }
/*     */   
/*     */   public boolean equals(Colour colour)
/*     */   {
/* 144 */     return (colour != null) && (rgba() == colour.rgba());
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/codechicken/lib/colour/Colour.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */