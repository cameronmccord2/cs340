package server.handlers;

import java.io.IOException;

import server.facades.IServerModelFacade;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handles all requests sent to the endpoint that starts with /moves
 * @author scottdaly
 *
 */
public class MovesHandler implements HttpHandler {

	public MovesHandler(IServerModelFacade facade) {
		
	}

	@Override
	public void handle(HttpExchange arg0) throws IOException {
		
	}

}
