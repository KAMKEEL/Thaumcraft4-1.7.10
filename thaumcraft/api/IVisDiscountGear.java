package thaumcraft.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;

public abstract interface IVisDiscountGear
{
  public abstract int getVisDiscount(ItemStack paramItemStack, EntityPlayer paramEntityPlayer, Aspect paramAspect);
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/IVisDiscountGear.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */