/**
 * 
 */
package server.main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;

import server.commands.ICommandParams;
import server.facades.ICommandCreationFacade;
import server.handlers.GameHandler;
import server.handlers.GamesHandler;
import server.handlers.MovesHandler;
import server.handlers.UserHandler;
import server.modelFacade.IServerModelFacade;
import server.models.UserAttributes;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * Holds an instance of HttpServer and sends/receives HttpExchange objects to & from endpoints.
 * @author scottdaly
 *
 */
public class Server {
	
	private static int SERVER_PORT_NUMBER;
	private static final int MAX_WAITING_CONNECTIONS = 10;
	
	private HttpServer server;
	
	
	private Server() {
		SERVER_PORT_NUMBER = 8081;
	}
	private Server(String port) {
		SERVER_PORT_NUMBER = Integer.parseInt(port);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//check if port number is specified
		if(args.length == 0){
			new Server().run();
		}
		else{
			new Server(args[0]).run();
		}
	}
	
	private void run() {
		
		try {
			server = HttpServer.create(new InetSocketAddress(SERVER_PORT_NUMBER),
											MAX_WAITING_CONNECTIONS);
		} catch (IOException e) {
			System.out.println("Could not create HTTP server: " + e.getMessage());
			e.printStackTrace();
			return;
		}

		// use the default executor
		server.setExecutor(null); 
		
		// Facade
		IServerModelFacade modelFacade = null;
		ICommandCreationFacade commandFacade = null;// send modelFacade into this constructor
		
		//handlers
		GamesHandler gamesHandler = new GamesHandler(commandFacade);
		GameHandler gameHandler = new GameHandler(commandFacade);
		MovesHandler movesHandler = new MovesHandler(commandFacade);
		UserHandler userHandler = new UserHandler(commandFacade);
		
		
		// contexts
		server.createContext("/user/login", userHandler);
		
		server.createContext("/game/commands", gameHandler);
		server.createContext("/game/reset", gameHandler);
		server.createContext("/game/model", gameHandler);
		
		server.createContext("/games/list", gamesHandler);
		
		server.createContext("/moves/buildRoad", movesHandler);
		
		server.start();
	}
	
	private HttpHandler validateUserHandler = new HttpHandler() {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			InputStream is = exchange.getRequestBody();
			
<<<<<<< HEAD
//			read the input stream
//			We're not going to use XStream here.  We will use Gson instead.
//			XStream xstream = new XStream(new DomDriver());
//			ValidateUserParams params = (ValidateUserParams)xstream.fromXML(exchange.getRequestBody());
			
			
			
=======
>>>>>>> e807ecdc2b170f7a515b117d866d3c9ed96fb7ff
			String[] pathPieces = exchange.getRequestURI().getPath().split("/");
			String finalPiece = pathPieces[pathPieces.length - 1];
			switch(finalPiece){
			
				
			}
			
			is.close();
			
			//prepare responseBody
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			OutputStream responseBody = exchange.getResponseBody(); 
			//xstream.toXML(results,responseBody);
			responseBody.close();
		}
	};

}
