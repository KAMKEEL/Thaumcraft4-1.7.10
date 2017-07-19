package thaumcraft.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public abstract interface IRepairableExtended
  extends IRepairable
{
  public abstract boolean doRepair(ItemStack paramItemStack, EntityPlayer paramEntityPlayer, int paramInt);
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/IRepairableExtended.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */