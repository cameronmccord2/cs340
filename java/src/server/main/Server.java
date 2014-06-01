package server.main;

import java.io.IOException;
import java.net.InetSocketAddress;

import server.facades.DummyGameFacade;
import server.facades.DummyGamesFacade;
import server.facades.DummyMovesFacade;
import server.facades.DummyUserFacade;
import server.facades.GameFacade;
import server.facades.GamesFacade;
import server.facades.IGameFacade;
import server.facades.IGamesFacade;
import server.facades.IMovesFacade;
import server.facades.IUserFacade;
import server.facades.MovesFacade;
import server.facades.UserFacade;
import server.handlers.GameHandler;
import server.handlers.GamesHandler;
import server.handlers.MovesHandler;
import server.handlers.UserHandler;
import server.modelFacade.IServerModelFacade;

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
		server.createContext("/user/login", userHandler);
		server.createContext("/user/register", userHandler);
		
		server.createContext("/games/list", gamesHandler);
		server.createContext("/games/creat", gamesHandler);
		server.createContext("/games/join", gamesHandler);
		server.createContext("/games/save", gamesHandler);
		server.createContext("/games/load", gamesHandler);
		
		server.createContext("/game/model", gameHandler);
		server.createContext("/game/reset", gameHandler);
		server.createContext("/game/commands", gameHandler);
		
		server.createContext("/moves/sendChat", movesHandler);
		server.createContext("/moves/rollNumber", movesHandler);
		server.createContext("/moves/robPlayer", movesHandler);
		server.createContext("/moves/finishTurn", movesHandler);
		server.createContext("/moves/buyDevCard", movesHandler);
		server.createContext("/moves/Year_of_Plenty", movesHandler);
		server.createContext("/moves/Road_Building", movesHandler);
		server.createContext("/moves/Soldier", movesHandler);
		server.createContext("/moves/Monopoly", movesHandler);
		server.createContext("/moves/Monument", movesHandler);
		server.createContext("/moves/buildRoad", movesHandler);
		server.createContext("/moves/buildSettlement", movesHandler);
		server.createContext("/moves/buildCity", movesHandler);
		server.createContext("/moves/offerTrade", movesHandler);
		server.createContext("/moves/acceptTrade", movesHandler);
		server.createContext("/moves/maritimeTrade", movesHandler);
		server.createContext("/moves/discardCards", movesHandler);
		
		System.out.println("server started");
		server.start();
	}
}
