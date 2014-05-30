
package server.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Scanner;

import server.commands.CommandResponse;
import server.commands.ICommand;
import server.facades.IGameFacade;
import server.facades.IServerModelFacade;
import server.models.UserAttributes;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
//import com.thoughtworks.xstream.XStream;
//import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Handles all request sent to the endpoint that starts with /game
 * Converts HttpExchange objects to JSON strings which are then passed to the appropriate facade.
 * Extracts necessary cookies needed to pass on to the facade.
 * @author scottdaly
 *
 */
public class GameHandler implements HttpHandler {

	private IGameFacade facade;

	public GameHandler(IServerModelFacade facade) {
		this.facade = (IGameFacade)facade;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		InputStream is = exchange.getRequestBody();
		
//		read the input stream
//		Gson not XStream.
//		XStream xstream = new XStream(new DomDriver());
		
		//ValidateUserParams params = (ValidateUserParams)xstream.fromXML(exchange.getRequestBody());
		
		CommandResponse response = null;
		String requestMethod = exchange.getRequestMethod();
		String[] pathPieces = exchange.getRequestURI().getPath().split("/");
		String finalPiece = pathPieces[pathPieces.length - 1];
		
		Scanner s = new Scanner(is).useDelimiter("\\A");
	    String json = s.hasNext() ? s.next() : "";
	    s.close();
	    is.close();
	    
	    UserAttributes ua = new UserAttributes(exchange);
	    
		switch(finalPiece){
		case "commands":
			if(requestMethod.equals("GET")){
				response = this.facade.getCommands(json, ua);
			}else if(requestMethod.equals("POST")){
				response = this.facade.runCommands(json, ua);
			}
			break;
			
		case "reset":
			if(requestMethod.equals("POST")){
				response = this.facade.reset(json, ua);
			}
			break;
			
		case "model":
			if(requestMethod.equals("GET")){
				response = this.facade.getGameModel(json, ua);
			}
			break;
		}
		
		
		//prepare responseBody
		exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
		OutputStream responseBody = exchange.getResponseBody(); 
		//xstream.toXML(results,responseBody);
		responseBody.close();
	}
}
