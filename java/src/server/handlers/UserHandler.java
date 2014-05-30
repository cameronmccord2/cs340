/**
 * 
 */
package server.handlers;

import java.io.IOException;

import server.facades.ICommandCreationFacade;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handles a request to any endpoint that starts with /user.
 * Converts HttpExchange objects to JSON strings which are then passed to the appropriate facade.
 * 
 * @author scottdaly
 *
 */
public class UserHandler implements HttpHandler {

	public UserHandler(ICommandCreationFacade facade) {
		
	}

	@Override
	public void handle(HttpExchange arg0) throws IOException {
		
	}

}
