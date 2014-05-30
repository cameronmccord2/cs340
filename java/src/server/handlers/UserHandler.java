/**
 * 
 */
package server.handlers;

import java.io.IOException;

import server.facades.IServerModelFacade;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handles a request to any endpoint that starts with /user
 * @author scottdaly
 *
 */
public class UserHandler implements HttpHandler {

	public UserHandler(IServerModelFacade facade) {
		
	}

	@Override
	public void handle(HttpExchange arg0) throws IOException {
		
	}

}
