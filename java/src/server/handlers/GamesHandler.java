package server.handlers;

import java.io.IOException;

import server.facades.IServerModelFacade;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handles all request sent to the endpoint that starts with /games
 * @author scottdaly
 *
 */
public class GamesHandler implements HttpHandler {

	public GamesHandler(IServerModelFacade facade) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(HttpExchange arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

	

}
