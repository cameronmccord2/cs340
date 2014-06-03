package client.models;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Object that generates a timer and will trigger the Proxy after a period of time to pull new data
 * @author Scott Daly
 *
 */
public class Poller{

	private IProxy proxy;
	private Timer t;
	
//	public Poller() {
//		this(new Proxy());
//	}
	
	
	/**
	 * 
	 */
	public Poller(IProxy proxy) {
		this.proxy = proxy;
		this.startTimer();
	}

	
//	static int interval;
	
	/**
	 * Starts the timer of the poller
	 */
	public void startTimer(){
		
		t = new Timer();

		t.schedule(
			new TimerTask(){
				@Override
				public void run(){
					proxy.getGameModel();
				}
			},
			10,	  // run first occurrence immediately
			2000); // run every two seconds
		
	}
	/**
	 * Calls the Proxy and tells it to pull current game state from the server
	 */
//	public void updateModel(){
//		//proxy.getGameModel();
//	}
	
	public void endTImer(){
		t.cancel();
	}


	

}
