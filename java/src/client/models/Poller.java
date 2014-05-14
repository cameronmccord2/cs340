package client.models;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Object that generates a timer and will trigger the Proxy after a period of time to pull new data
 * @author Scott Daly
 *
 */
public class Poller {

	private Proxy proxy;
	private Timer t;
	
	public Poller(){
		startTimer();
	}
	
	
	/**
	 * 
	 */
	public Poller(Proxy proxy) {
		this.proxy = proxy;
	}
	
	static int interval;
	
	/**
	 * Starts the timer of the poller
	 */
	public void startTimer(){
		
		t = new Timer();

		t.scheduleAtFixedRate(
		    new TimerTask(){
		        public void run(){
		            updateModel();
		        }
		    },
		    0,      // run first occurrence immediately
		    2000); // run every two seconds
	}
	/**
	 * Calls the Proxy and tells it to pull current game state from the server
	 */
	public void updateModel(){
		System.out.println("hi jess");
	}
	
	public void endTImer(){
		t.cancel();
	}

}
