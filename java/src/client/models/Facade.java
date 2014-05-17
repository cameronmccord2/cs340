package client.models;

public class Facade implements IFacade {
	
	private IProxy proxy;
	
	public Facade(IProxy proxy){
		this.proxy = proxy;
	}
}
