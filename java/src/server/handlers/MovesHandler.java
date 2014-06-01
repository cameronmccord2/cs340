package server.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.Scanner;

import server.commands.CommandResponse;
import server.facades.ICommandCreationFacade;
import server.facades.IMovesFacade;
import server.models.UserAttributes;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handles all requests sent to the endpoint that starts with /moves. 
 * Converts HttpExchange objects to JSON strings which are then passed to the appropriate facade.
 * Extracts necessary cookies needed to pass on to the facade.
 * @author scottdaly
 *
 */
public class MovesHandler implements HttpHandler {
	
	private IMovesFacade commandFacade;
	
	

	public MovesHandler(IMovesFacade commandFacade) {
		this.commandFacade = commandFacade;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
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
			case "sendChat":
				if(requestMethod.equals("POST")){
					response = this.commandFacade.sendChat(json, ua);
				}
				break;
			case "rollNumber":
				if(requestMethod.equals("POST")){
					response = this.commandFacade.rollNumber(json, ua);
				}
				break;
			case "robPlayer":
				if(requestMethod.equals("POST")){
					response = this.commandFacade.robPlayer(json, ua);
				}
				break;
			case "finishTurn":
				if(requestMethod.equals("POST")){
					response = this.commandFacade.finishTurn(json, ua);
				}
				break;
			case "buyDevCard":
				if(requestMethod.equals("POST")){
					response = this.commandFacade.buyDevCard(json, ua);
				}
				break;
			case "Year_of_Plenty":
				if(requestMethod.equals("POST")){
					response = this.commandFacade.yearOfPlenty(json, ua);
				}
				break;
			case "Road_Building":
				if(requestMethod.equals("POST")){
					response = this.commandFacade.roadBuilding(json, ua);
				}
				break;
			case "Soldier":
				if(requestMethod.equals("POST")){
					response = this.commandFacade.soldier(json, ua);
				}
				break;
			case "Monopoly":
				if(requestMethod.equals("POST")){
					response = this.commandFacade.monopoly(json, ua);
				}
				break;
			case "Monument":
				if(requestMethod.equals("POST")){
					response = this.commandFacade.monument(json, ua);
				}
				break;
			case "buildRoad":
				if(requestMethod.equals("POST")){
					response = this.commandFacade.buildRoad(json, ua);
				}
				break;
			case "buildSettlement":
				if(requestMethod.equals("POST")){
					response = this.commandFacade.buildSettlement(json, ua);
				}
				break;
			case "buildCity":
				if(requestMethod.equals("POST")){
					response = this.commandFacade.buildCity(json, ua);
				}
				break;
			case "offerTrade":
				if(requestMethod.equals("POST")){
					response = this.commandFacade.offerTrade(json, ua);
				}
				break;
			case "acceptTrade":
				if(requestMethod.equals("POST")){
					response = this.commandFacade.acceptTrade(json, ua);
				}
				break;
			case "maritimeTrade":
				if(requestMethod.equals("POST")){
					response = this.commandFacade.maritimeTrade(json, ua);
				}
				break;
			case "discardCards":
				if(requestMethod.equals("POST")){
					response = this.commandFacade.discardCards(json, ua);
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
