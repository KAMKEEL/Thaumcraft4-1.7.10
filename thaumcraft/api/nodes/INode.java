package thaumcraft.api.nodes;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;

public abstract interface INode
  extends IAspectContainer
{
  public abstract String getId();
  
  public abstract AspectList getAspectsBase();
  
  public abstract NodeType getNodeType();
  
  public abstract void setNodeType(NodeType paramNodeType);
  
  public abstract void setNodeModifier(NodeModifier paramNodeModifier);
  
  public abstract NodeModifier getNodeModifier();
  
  public abstract int getNodeVisBase(Aspect paramAspect);
  
  public abstract void setNodeVisBase(Aspect paramAspect, short paramShort);
}


/* Location:              /Users/shannon/Desktop/Thaumcraft-1.7.10-4.2.3.5.jar!/thaumcraft/api/nodes/INode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */