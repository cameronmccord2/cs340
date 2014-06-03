/**
 * 
 */
package server.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.Scanner;

import server.commands.CommandResponse;
import server.facades.IUserFacade;
import server.models.UserAttributes;

import com.sun.net.httpserver.Headers;
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
	
	private IUserFacade commandFacade;
	
	public UserHandler(IUserFacade commandFacade) {
		this.commandFacade = commandFacade;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		//read the input stream
		System.out.println("User Handler started");
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
			case "login":
				if(requestMethod.equals("POST"))
					response = this.commandFacade.login(json, ua);
				break;
			case "register":
				if(requestMethod.equals("POST"))
					response = this.commandFacade.register(json, ua);
				break;
			default:
				response = new CommandResponse("Invaid endpoing requested", "403");
		}

		//prepare responseBody
		if(response.getResponseCode().equals("200")){
			Headers headers = exchange.getResponseHeaders();
			headers.add("Set-cookie", "catan.user=%7B%22name%22%3A%22Sam%22%2C%22password%22%3A%22sam%22%2C%22playerID%22%3A0%7D;Path=/;");
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
		}
		else
			exchange.sendResponseHeaders(Integer.parseInt(response.getResponseCode()), 0);

		OutputStream responseBody = exchange.getResponseBody(); 
		responseBody.write(response.getResponse().getBytes(Charset.forName("UTF-8")));
		responseBody.close();
	}

}
