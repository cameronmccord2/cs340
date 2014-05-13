package client.models.translator;

public class TRHex implements IModelValid {
	protected TRHexLocation location;
	protected String resource;
	protected Integer number;
	public TRHexLocation getLocation() {
		return location;
	}
	public void setLocation(TRHexLocation location) {
		this.location = location;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}
}
/*
location (HexLocation), 
resource (string, optional) = ['Wood' or 'Brick' or 'Sheep' or 'Wheat' or 'Ore']: What resource this 
tile gives - it's only here if the tile is not desert., 
number (integer, optional): What number is on this tile. It's omitted if this is a desert hex. 

*/