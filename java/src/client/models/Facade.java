package client.models;

public class Facade {
	
	private IProxy proxy;
	
	public Facade(IProxy proxy){
		this.proxy = proxy;
	}
}
