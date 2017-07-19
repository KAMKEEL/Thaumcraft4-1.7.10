package thaumcraft.api.internal;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public abstract interface IInternalMethodHandler
{
  public abstract void generateVisEffect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8);
  
  public abstract boolean isResearchComplete(String paramString1, String paramString2);
  
  public abstract ItemStack getStackInRowAndColumn(Object paramObject, int paramInt1, int paramInt2);
  
  public abstract AspectList getObjectAspects(ItemStack paramItemStack);
  
  public abstract AspectList getBonusObjectTags(ItemStack paramItemStack, AspectList paramAspectList);
  
  public abstract AspectList generateTags(Item paramItem, int paramInt);
  
  public abstract boolean consumeVisFromWand(ItemStack paramItemStack, EntityPlayer paramEntityPlayer, AspectList paramAspectList, boolean paramBoolean1, boolean paramBoolean2);
  
  public abstract boolean consumeVisFromWandCrafting(ItemStack paramItemStack, EntityPlayer paramEntityPlayer, AspectList paramAspectList, boolean paramBoolean);
  
  public abstract boolean consumeVisFromInventory(EntityPlayer paramEntityPlayer, AspectList paramAspectList);
  
  public abstract void addWarpToPlayer(EntityPlayer paramEntityPlayer, int paramInt, boolean paramBoolean);
  
  public abstract void addStickyWarpToPlayer(EntityPlayer paramEntityPlayer, int paramInt);
  
  public abstract boolean hasDiscoveredAspect(String paramString, Aspect paramAspect);
  
  public abstract AspectList getDiscoveredAspects(String paramString);
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/internal/IInternalMethodHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */