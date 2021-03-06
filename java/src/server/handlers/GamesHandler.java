package server.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import server.commands.CommandResponse;
import server.facades.IGamesFacade;
import server.models.UserAttributes;
import client.models.ReturnedUser;
import client.models.User;
import client.server.ServerJoinGame;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
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
		System.out.println("Games Handler in use");
		InputStream is = exchange.getRequestBody();		
		String requestMethod = exchange.getRequestMethod();
		String[] pathPieces = exchange.getRequestURI().getPath().split("/");
		String finalPiece = pathPieces[pathPieces.length - 1];
		
		Scanner s = new Scanner(is);
		s.useDelimiter("\\A");
		String json = s.hasNext() ? s.next() : "";
		s.close();
		is.close();

		CommandResponse response = null;

		switch(finalPiece){
			case "list":
				if(requestMethod.equals("GET")){
					//ua is blank for getting games list
					UserAttributes ua = new UserAttributes();
					response = this.commandFacade.listGames(json, ua);
				}
				break;
			case "create":
				if(requestMethod.equals("POST")){
					//ua is blank for creating games
					UserAttributes ua = new UserAttributes();
					response = this.commandFacade.createGame(json, ua);
				}
				break;
			case "join":
				if(requestMethod.equals("POST")){
					
					//get cookie header and decode
					Gson gson = new Gson();
					Map<String, List<String>> map = exchange.getRequestHeaders();
					List<String> setCookie = map.get("Cookie");
					String temp = setCookie.get(0);
					temp = temp.substring(11, temp.length());
					ReturnedUser rUser = gson.fromJson(URLDecoder.decode(temp, "UTF-8"), ReturnedUser.class);
					UserAttributes ua = new UserAttributes(rUser); //set user
					
					response = this.commandFacade.joinGame(json, ua);
					Headers headers = exchange.getResponseHeaders();
					headers.add("Set-cookie", "catan.game=" + response.getResponse() + ";Path=/;");
					response.setResponse("Success");
				}
				break;
			case "save":
				if(requestMethod.equals("POST")){
					//get cookie header and decode
					Gson gson = new Gson();
					Map<String, List<String>> map = exchange.getRequestHeaders();
					List<String> setCookie = map.get("Cookie");
					String temp = setCookie.get(0);
					temp = temp.substring(11, temp.length());
					ReturnedUser rUser = gson.fromJson(URLDecoder.decode(temp, "UTF-8"), ReturnedUser.class);
					UserAttributes ua = new UserAttributes(rUser); //set user
					response = this.commandFacade.saveGame(json, ua);
				}
				break;
			case "load":
				if(requestMethod.equals("POST")){
					//get cookie header and decode
					Gson gson = new Gson();
					Map<String, List<String>> map = exchange.getRequestHeaders();
					List<String> setCookie = map.get("Cookie");
					String temp = setCookie.get(0);
					temp = temp.substring(11, temp.length());
					ReturnedUser rUser = gson.fromJson(URLDecoder.decode(temp, "UTF-8"), ReturnedUser.class);
					UserAttributes ua = new UserAttributes(rUser); //set user
					response = this.commandFacade.loadGame(json, ua);
				}
				break;
			default:
				response = new CommandResponse("Invaid endpoing requested", "403");
		}
		
		///prepare responseBody
		if(response.getResponseCode().equals("200")){
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
		}
		else
			exchange.sendResponseHeaders(Integer.parseInt(response.getResponseCode()), 0);

		OutputStream responseBody = exchange.getResponseBody(); 
		responseBody.write(response.getResponse().getBytes(Charset.forName("UTF-8")));
		responseBody.close();
		
	}

	

}
