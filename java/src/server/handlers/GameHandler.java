
package server.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.Scanner;

import server.commands.CommandResponse;
import server.commands.ICommand;
import server.facades.ICommandCreationFacade;
import server.facades.IGameFacade;
import server.modelFacade.IServerModelFacade;
import server.models.UserAttributes;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handles all request sent to the endpoint that starts with /game
 * Converts HttpExchange objects to JSON strings which are then passed to the appropriate facade.
 * Extracts necessary cookies needed to pass on to the facade.
 * @author scottdaly
 *
 */
public class GameHandler implements HttpHandler {

	private IGameFacade commandFacade;

	public GameHandler(IGameFacade commandFacade) {
		this.commandFacade = commandFacade;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		System.out.println("Game Handler started");
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
			case "model":
				if(requestMethod.equals("GET")){
					response = this.commandFacade.getGameModel(json, ua);
				}
				break;
			case "reset":
				if(requestMethod.equals("POST")){
					response = this.commandFacade.reset(json, ua);
				}
				break;
			case "commands":
				if(requestMethod.equals("GET")){
					response = this.commandFacade.getCommands(json, ua);
				}else if(requestMethod.equals("POST")){
					response = this.commandFacade.runCommands(json, ua);
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
