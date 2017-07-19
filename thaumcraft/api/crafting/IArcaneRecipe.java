package thaumcraft.api.crafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.aspects.AspectList;

public abstract interface IArcaneRecipe
{
  public abstract boolean matches(IInventory paramIInventory, World paramWorld, EntityPlayer paramEntityPlayer);
  
  public abstract ItemStack getCraftingResult(IInventory paramIInventory);
  
  public abstract int getRecipeSize();
  
  public abstract ItemStack getRecipeOutput();
  
  public abstract AspectList getAspects();
  
  public abstract AspectList getAspects(IInventory paramIInventory);
  
  public abstract String getResearch();
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/crafting/IArcaneRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */