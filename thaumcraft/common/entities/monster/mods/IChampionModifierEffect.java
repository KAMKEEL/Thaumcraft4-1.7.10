package thaumcraft.common.entities.monster.mods;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public abstract interface IChampionModifierEffect
{
  public abstract float performEffect(EntityLivingBase paramEntityLivingBase1, EntityLivingBase paramEntityLivingBase2, DamageSource paramDamageSource, float paramFloat);
  
  @SideOnly(Side.CLIENT)
  public abstract void showFX(EntityLivingBase paramEntityLivingBase);
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/common/entities/monster/mods/IChampionModifierEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */