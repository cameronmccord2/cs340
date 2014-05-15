package client.models;

import java.util.Collection;

import shared.definitions.CatanColor;
import shared.locations.EdgeLocation;

public interface IRoadSegment extends IPiece
{
	public Collection<Resource> getResourceCost();
}
