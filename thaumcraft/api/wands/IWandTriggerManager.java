package thaumcraft.api.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract interface IWandTriggerManager
{
  public abstract boolean performTrigger(World paramWorld, ItemStack paramItemStack, EntityPlayer paramEntityPlayer, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/wands/IWandTriggerManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */