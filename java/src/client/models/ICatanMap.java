package client.models;

public interface ICatanMap {
	public void placeRoadSegment(IRoadSegment segment);
	public boolean canPlaceSegment(IRoadSegment segment);
}
