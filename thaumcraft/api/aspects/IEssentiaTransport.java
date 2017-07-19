package thaumcraft.api.aspects;

import net.minecraftforge.common.util.ForgeDirection;

public abstract interface IEssentiaTransport
{
  public abstract boolean isConnectable(ForgeDirection paramForgeDirection);
  
  public abstract boolean canInputFrom(ForgeDirection paramForgeDirection);
  
  public abstract boolean canOutputTo(ForgeDirection paramForgeDirection);
  
  public abstract void setSuction(Aspect paramAspect, int paramInt);
  
  public abstract Aspect getSuctionType(ForgeDirection paramForgeDirection);
  
  public abstract int getSuctionAmount(ForgeDirection paramForgeDirection);
  
  public abstract int takeEssentia(Aspect paramAspect, int paramInt, ForgeDirection paramForgeDirection);
  
  public abstract int addEssentia(Aspect paramAspect, int paramInt, ForgeDirection paramForgeDirection);
  
  public abstract Aspect getEssentiaType(ForgeDirection paramForgeDirection);
  
  public abstract int getEssentiaAmount(ForgeDirection paramForgeDirection);
  
  public abstract int getMinimumSuction();
  
  public abstract boolean renderExtendedTube();
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/aspects/IEssentiaTransport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */