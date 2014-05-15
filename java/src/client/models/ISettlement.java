package client.models;

import java.util.Collection;

public interface ISettlement extends IPiece
{
	public Collection<Resource> getResourceCost();
	public int getPointValue();
}
