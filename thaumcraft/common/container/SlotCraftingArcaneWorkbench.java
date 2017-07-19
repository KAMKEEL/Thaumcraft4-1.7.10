/*    */ package thaumcraft.common.container;
/*    */ 
/*    */ import cpw.mods.fml.common.FMLCommonHandler;
/*    */ import cpw.mods.fml.common.eventhandler.EventBus;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.SlotCrafting;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.common.items.wands.ItemWandCasting;
/*    */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SlotCraftingArcaneWorkbench
/*    */   extends SlotCrafting
/*    */ {
/*    */   private final IInventory craftMatrix;
/*    */   private EntityPlayer thePlayer;
/*    */   private int amountCrafted;
/*    */   
/*    */   public SlotCraftingArcaneWorkbench(EntityPlayer par1EntityPlayer, IInventory par2IInventory, IInventory par3IInventory, int par4, int par5, int par6)
/*    */   {
/* 29 */     super(par1EntityPlayer, par2IInventory, par3IInventory, par4, par5, par6);
/* 30 */     this.thePlayer = par1EntityPlayer;
/* 31 */     this.craftMatrix = par2IInventory;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void func_82870_a(EntityPlayer par1EntityPlayer, ItemStack par1ItemStack)
/*    */   {
/* 41 */     FMLCommonHandler.instance().firePlayerCraftingEvent(this.thePlayer, par1ItemStack, this.craftMatrix);
/* 42 */     func_75208_c(par1ItemStack);
/*    */     
/* 44 */     AspectList aspects = ThaumcraftCraftingManager.findMatchingArcaneRecipeAspects(this.craftMatrix, this.thePlayer);
/* 45 */     if ((aspects.size() > 0) && (this.craftMatrix.func_70301_a(10) != null)) {
/* 46 */       ItemWandCasting wand = (ItemWandCasting)this.craftMatrix.func_70301_a(10).func_77973_b();
/* 47 */       wand.consumeAllVisCrafting(this.craftMatrix.func_70301_a(10), par1EntityPlayer, aspects, true);
/*    */     }
/*    */     
/* 50 */     for (int var2 = 0; var2 < 9; var2++)
/*    */     {
/* 52 */       ItemStack var3 = this.craftMatrix.func_70301_a(var2);
/*    */       
/* 54 */       if (var3 != null)
/*    */       {
/* 56 */         this.craftMatrix.func_70298_a(var2, 1);
/*    */         
/* 58 */         if (var3.func_77973_b().hasContainerItem(var3))
/*    */         {
/* 60 */           ItemStack var4 = var3.func_77973_b().getContainerItem(var3);
/*    */           
/* 62 */           if ((var4 != null) && (var4.func_77984_f()) && (var4.func_77960_j() > var4.func_77958_k()))
/*    */           {
/* 64 */             MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(this.thePlayer, var4));
/*    */ 
/*    */ 
/*    */ 
/*    */           }
/* 69 */           else if ((!var3.func_77973_b().func_77630_h(var3)) || (!this.thePlayer.field_71071_by.func_70441_a(var4)))
/*    */           {
/* 71 */             if (this.craftMatrix.func_70301_a(var2) == null)
/*    */             {
/* 73 */               this.craftMatrix.func_70299_a(var2, var4);
/*    */             }
/*    */             else
/*    */             {
/* 77 */               this.thePlayer.func_71019_a(var4, false);
/*    */             }
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/container/SlotCraftingArcaneWorkbench.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */