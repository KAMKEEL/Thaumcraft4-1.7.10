/*     */ package thaumcraft.common.items.wands;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.Thaumcraft;
/*     */ 
/*     */ public class ItemFocusPouch extends Item
/*     */ {
/*     */   protected IIcon icon;
/*     */   
/*     */   public ItemFocusPouch()
/*     */   {
/*  23 */     func_77625_d(1);
/*  24 */     func_77627_a(false);
/*  25 */     func_77656_e(0);
/*  26 */     func_77637_a(Thaumcraft.tabTC);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister par1IconRegister)
/*     */   {
/*  33 */     this.icon = par1IconRegister.func_94245_a("thaumcraft:focuspouch");
/*     */   }
/*     */   
/*     */ 
/*     */   public IIcon func_77617_a(int par1)
/*     */   {
/*  39 */     return this.icon;
/*     */   }
/*     */   
/*     */   public boolean func_77651_p()
/*     */   {
/*  44 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public EnumRarity func_77613_e(ItemStack itemstack)
/*     */   {
/*  50 */     return EnumRarity.rare;
/*     */   }
/*     */   
/*     */   public boolean func_77636_d(ItemStack par1ItemStack)
/*     */   {
/*  55 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
/*     */   {
/*  62 */     if (!par2World.field_72995_K) {
/*  63 */       par3EntityPlayer.openGui(Thaumcraft.instance, 5, par2World, MathHelper.func_76128_c(par3EntityPlayer.field_70165_t), MathHelper.func_76128_c(par3EntityPlayer.field_70163_u), MathHelper.func_76128_c(par3EntityPlayer.field_70161_v));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  69 */     return super.func_77659_a(par1ItemStack, par2World, par3EntityPlayer);
/*     */   }
/*     */   
/*     */   public ItemStack[] getInventory(ItemStack item) {
/*  73 */     ItemStack[] stackList = new ItemStack[18];
/*     */     
/*  75 */     if (item.func_77942_o()) {
/*  76 */       NBTTagList var2 = item.field_77990_d.func_150295_c("Inventory", 10);
/*  77 */       for (int var3 = 0; var3 < var2.func_74745_c(); var3++)
/*     */       {
/*  79 */         NBTTagCompound var4 = var2.func_150305_b(var3);
/*  80 */         int var5 = var4.func_74771_c("Slot") & 0xFF;
/*     */         
/*  82 */         if ((var5 >= 0) && (var5 < stackList.length))
/*     */         {
/*  84 */           stackList[var5] = ItemStack.func_77949_a(var4);
/*     */         }
/*     */       }
/*     */     }
/*  88 */     return stackList;
/*     */   }
/*     */   
/*     */   public void setInventory(ItemStack item, ItemStack[] stackList) {
/*  92 */     NBTTagList var2 = new NBTTagList();
/*     */     
/*  94 */     for (int var3 = 0; var3 < stackList.length; var3++)
/*     */     {
/*  96 */       if (stackList[var3] != null)
/*     */       {
/*  98 */         NBTTagCompound var4 = new NBTTagCompound();
/*  99 */         var4.func_74774_a("Slot", (byte)var3);
/* 100 */         stackList[var3].func_77955_b(var4);
/* 101 */         var2.func_74742_a(var4);
/*     */       }
/*     */     }
/*     */     
/* 105 */     item.func_77983_a("Inventory", var2);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/items/wands/ItemFocusPouch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */