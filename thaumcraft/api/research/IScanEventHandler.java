package thaumcraft.api.research;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract interface IScanEventHandler
{
  public abstract ScanResult scanPhenomena(ItemStack paramItemStack, World paramWorld, EntityPlayer paramEntityPlayer);
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/research/IScanEventHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */