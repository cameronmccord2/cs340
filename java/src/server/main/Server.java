package server.main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import server.facades.DummyGameFacade;
import server.facades.DummyGamesFacade;
import server.facades.DummyMovesFacade;
import server.facades.DummyUserFacade;
import server.facades.IGameFacade;
import server.facades.IGamesFacade;
import server.facades.IMovesFacade;
import server.facades.IUserFacade;
import server.handlers.GameHandler;
import server.handlers.GamesHandler;
import server.handlers.MovesHandler;
import server.handlers.UserHandler;
import server.modelFacade.IServerModelFacade;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


public class Server {

	private static int SERVER_PORT_NUMBER;
	private static final int MAX_WAITING_CONNECTIONS = 10;
	
	private HttpServer server;
	private GamesHandler gamesHandler;
	private GameHandler gameHandler;
	private MovesHandler movesHandler;
	private UserHandler userHandler;
	
	private Server() {
		SERVER_PORT_NUMBER = 8080;
	}
	private Server(String port) {
		SERVER_PORT_NUMBER = Integer.parseInt(port);
	}
	
	public static void main(String[] args) {
		//check if port number is specified
		if(args.length == 0){
			new Server().run();
		}
		else if(args.length == 2){
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
		
		//ICommandCreationFacade commandFacade = new CommandCreationFacade(modelFacade);// send modelFacade into this constructor
		
		//comment the following 4 lines if you want to use the dummyfacades
//		IUserFacade userFacade = new UserFacade(modelFacade);
//		IGamesFacade gamesFacade = new GamesFacade(modelFacade);
//		IGameFacade gameFacade = new GameFacade(modelFacade);
//		IMovesFacade movesFacade = new MovesFacade(modelFacade);
		
		//un-comment the following lines if you want to use the dummyfacades
		IUserFacade userFacade = new DummyUserFacade();
		IGamesFacade gamesFacade = new DummyGamesFacade();
		IGameFacade gameFacade = new DummyGameFacade();
		IMovesFacade movesFacade = new DummyMovesFacade();
		
		//handlers
		movesHandler = new MovesHandler(movesFacade);
		gamesHandler = new GamesHandler(gamesFacade);
		gameHandler = new GameHandler(gameFacade);
		userHandler = new UserHandler(userFacade);
		
		
		// contexts
		server.createContext("/user/login", HttpUserHandler);
		server.createContext("/user/register", HttpUserHandler);
		
		server.createContext("/games/list", HttpGamesHandler);
		server.createContext("/games/creat", HttpGamesHandler);
		server.createContext("/games/join", HttpGamesHandler);
		server.createContext("/games/save", HttpGamesHandler);
		server.createContext("/games/load", HttpGamesHandler);
		
		server.createContext("/game/model", HttpGameHandler);
		server.createContext("/game/reset", HttpGameHandler);
		server.createContext("/game/commands", HttpGameHandler);
		
		server.createContext("/moves/sendChat", HttpMovesHandler);
		server.createContext("/moves/rollNumber", HttpMovesHandler);
		server.createContext("/moves/robPlayer", HttpMovesHandler);
		server.createContext("/moves/finishTurn", HttpMovesHandler);
		server.createContext("/moves/buyDevCard", HttpMovesHandler);
		server.createContext("/moves/Year_of_Plenty", HttpMovesHandler);
		server.createContext("/moves/Road_Building", HttpMovesHandler);
		server.createContext("/moves/Soldier", HttpMovesHandler);
		server.createContext("/moves/Monopoly", HttpMovesHandler);
		server.createContext("/moves/Monument", HttpMovesHandler);
		server.createContext("/moves/buildRoad", HttpMovesHandler);
		server.createContext("/moves/buildSettlement", HttpMovesHandler);
		server.createContext("/moves/buildCity", HttpMovesHandler);
		server.createContext("/moves/offerTrade", HttpMovesHandler);
		server.createContext("/moves/acceptTrade", HttpMovesHandler);
		server.createContext("/moves/maritimeTrade", HttpMovesHandler);
		server.createContext("/moves/discardCards", HttpMovesHandler);
		
		System.out.println("server started");
		server.start();
	}
	
	private HttpHandler HttpUserHandler = new HttpHandler() {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			System.out.println("user handler started");
			userHandler.handle(exchange);
		};
	};
	private HttpHandler HttpGamesHandler = new HttpHandler() {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			System.out.println("games handler started");
			gamesHandler.handle(exchange);
		};
	};
	private HttpHandler HttpGameHandler = new HttpHandler() {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			System.out.println("game handler started");
			//read the input stream
			InputStream is = exchange.getRequestBody();
			is.close();			
			
			//prepare responseBody
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			OutputStream responseBody = exchange.getResponseBody(); 
			String test = "Success";
			responseBody.write(test.getBytes(Charset.forName("UTF-8")));
			responseBody.close();
		};
	};
	private HttpHandler HttpMovesHandler = new HttpHandler() {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			System.out.println("moves handler started");
			//read the input stream
			InputStream is = exchange.getRequestBody();
			is.close();			
			
			//prepare responseBody
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			OutputStream responseBody = exchange.getResponseBody(); 
			String test = "Success";
			responseBody.write(test.getBytes(Charset.forName("UTF-8")));
			responseBody.close();
		};
	};
}
