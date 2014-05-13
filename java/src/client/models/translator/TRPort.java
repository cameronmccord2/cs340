package client.models.translator;

public class TRPort implements IModelValid {
	protected String resource;
	protected TRHexLocation location;
	protected String direction;
	protected Integer ratio;
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public TRHexLocation getLocation() {
		return location;
	}
	public void setLocation(TRHexLocation location) {
		this.location = location;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public Integer getRatio() {
		return ratio;
	}
	public void setRatio(Integer ratio) {
		this.ratio = ratio;
	}
	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}
}
/*
resource (string, optional) = ['Wood' or 'Brick' or 'Sheep' or 'Wheat' or 'Ore']: What type 
resource this port trades for. If it's omitted, then it's for any resource., 
location (HexLocation): Which hex this port is on. This shows the (ocean/non-existent) hex to 
draw the port on., 
direction (string) = ['NW' or 'N' or 'NE' or 'E' or 'SE' or 'SW']: Which edge this port is on., 
ratio (integer): The ratio for trade in (ie, if this is 2, then it's a 2:1 port. 

*/