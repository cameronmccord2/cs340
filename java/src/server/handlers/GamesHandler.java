package server.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.Scanner;

import server.commands.CommandResponse;
import server.facades.IGamesFacade;
import server.models.UserAttributes;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handles all request sent to the endpoint that starts with /games
 * Converts HttpExchange objects to JSON strings which are then passed to the appropriate facade.
 * Extracts necessary cookies needed to pass on to the facade.
 * @author scottdaly
 *
 */
public class GamesHandler implements HttpHandler {
	
	private IGamesFacade commandFacade;
	
	public GamesHandler(IGamesFacade commandFacade) {
		this.commandFacade = commandFacade;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		//read the input stream
		InputStream is = exchange.getRequestBody();		
		String requestMethod = exchange.getRequestMethod();
		String[] pathPieces = exchange.getRequestURI().getPath().split("/");
		String finalPiece = pathPieces[pathPieces.length - 1];
		
		Scanner s = new Scanner(is);
		s.useDelimiter("\\A");
	    String json = s.hasNext() ? s.next() : "";
	    s.close();
	    is.close();
	    
	    UserAttributes ua = new UserAttributes(exchange);
	    CommandResponse response = null;
	    
	    switch(finalPiece){
			case "list":
				if(requestMethod.equals("GET")){
					response = this.commandFacade.listGames(json, ua);
				}
				break;
			case "create":
				if(requestMethod.equals("POST")){
					response = this.commandFacade.createGame(json, ua);
				}
				break;
			case "join":
				if(requestMethod.equals("POST")){
					response = this.commandFacade.joinGame(json, ua);
				}
				break;
			case "save":
				if(requestMethod.equals("POST")){
					response = this.commandFacade.saveGame(json, ua);
				}
				break;
			case "load":
				if(requestMethod.equals("POST")){
					response = this.commandFacade.loadGame(json, ua);
				}
				break;
			default:
				response = new CommandResponse("Invaid endpoing requested", "403");
	    }
		
		///prepare responseBody
	    if(response.getResponseCode().equals("200")){
	    	exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
	    	//add cookie here?
	    	
	    }
	    else
	    	exchange.sendResponseHeaders(Integer.parseInt(response.getResponseCode()), 0);
	    
		OutputStream responseBody = exchange.getResponseBody(); 
		responseBody.write(response.getResponse().getBytes(Charset.forName("UTF-8")));
		responseBody.close();
		
	}

	

}
