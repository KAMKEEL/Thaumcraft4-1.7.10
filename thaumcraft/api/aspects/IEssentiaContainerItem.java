package thaumcraft.api.aspects;

import net.minecraft.item.ItemStack;

public abstract interface IEssentiaContainerItem
{
  public abstract AspectList getAspects(ItemStack paramItemStack);
  
  public abstract void setAspects(ItemStack paramItemStack, AspectList paramAspectList);
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/aspects/IEssentiaContainerItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */