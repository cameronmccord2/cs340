package client.models;

import client.models.translator.TRRoad;
import shared.definitions.CatanColor;
import shared.locations.EdgeLocation;

public class RoadSegment implements IRoadSegment {
	
	private EdgeLocation edgeLocation;
	private CatanColor color;
	private IPlayer player;

	public RoadSegment(TRRoad road, IPlayer player) {
		this.edgeLocation = new EdgeLocation(road.getLocation());
		this.color = player.getPlayerInfo().getColor();
		this.player = player;
	}

	@Override
	public EdgeLocation getEdgeLocation() {
		return edgeLocation;
	}

	@Override
	public void setEdgeLocation(EdgeLocation edgeLocation) {
		this.edgeLocation = edgeLocation;
	}

	@Override
	public CatanColor getColor() {
		return color;
	}

	@Override
	public void setColor(CatanColor color) {
		this.color = color;
	}

	@Override
	public IPlayer getPlayer() {
		return player;
	}

	@Override
	public void setPlayer(IPlayer player) {
		this.player = player;
	}
}
