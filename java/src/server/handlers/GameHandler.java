
package server.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import server.facades.IServerModelFacade;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
//import com.thoughtworks.xstream.XStream;
//import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Handles all request sent to the endpoint that starts with /game
 * Converts HttpExchange objects to JSON strings which are then passed to the appropriate facade.
 * @author scottdaly
 *
 */
public class GameHandler implements HttpHandler {

	public GameHandler(IServerModelFacade facade) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		InputStream is = exchange.getRequestBody();
		
//		read the input stream
//		Gson not XStream.
//		XStream xstream = new XStream(new DomDriver());
		
		//ValidateUserParams params = (ValidateUserParams)xstream.fromXML(exchange.getRequestBody());
		
		String response = "";
		String requestMethod = exchange.getRequestMethod();
		String[] pathPieces = exchange.getRequestURI().getPath().split("/");
		String finalPiece = pathPieces[pathPieces.length - 1];
		switch(finalPiece){
		case "commands":
			if(requestMethod.equals("GET")){
				
			}else if(requestMethod.equals("POST")){
				
			}
			break;
			
		case "reset":
			if(requestMethod.equals("POST")){
				
			}
			break;
			
		case "model":
			if(requestMethod.equals("GET")){
				
			}
			break;
			
		}
		
		is.close();
		
		//prepare responseBody
		exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
		OutputStream responseBody = exchange.getResponseBody(); 
		//xstream.toXML(results,responseBody);
		responseBody.close();
	}
}
