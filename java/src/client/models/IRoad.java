package client.models;

public interface IRoad {
	public CatanColor getColor();
	public void setColor(CatanColor color);
	
	public IPlayer getPlayer();
	public void setPlayer(IPlayer player);
	
	public boolean isLongestRoad();
}
