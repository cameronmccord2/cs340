package server.main;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import persistence.IPlugin;
import persistence.PluginManager;
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
import server.modelFacade.GameServerModelFacade;
import server.modelFacade.GamesServerModelFacade;
import server.modelFacade.IServerModelFacade;
import server.modelFacade.MovesServerModelFacade;
import server.modelFacade.UserServerModelFacade;
import server.models.GameList;
import client.models.interfaces.IGame;

import com.sun.net.httpserver.HttpServer;

public class Server {

	private static int SERVER_PORT_NUMBER;
	private static final int MAX_WAITING_CONNECTIONS = 10;
	private static final String USAGE_STATEMENT =
    	  "USAGE:\n"
    	+ "\tserver.Server [port]\n"
    	+ "\t\tor\n"
    	+ "\tserver.Server [persistence-type] [commands-between-checkpoints] [port]";

	private HttpServer server;
	private GamesHandler gamesHandler;
	private GameHandler gameHandler;
	private MovesHandler movesHandler;
	private UserHandler userHandler;

	private static Logger logger;
	static
	{
		try
		{
			Level logLevel = Level.OFF;

			logger = Logger.getLogger("Server");
			logger.setLevel(logLevel);
			logger.setUseParentHandlers(false);

			Handler consoleHandler = new ConsoleHandler();
			consoleHandler.setLevel(logLevel);
			consoleHandler.setFormatter(new SimpleFormatter());
			logger.addHandler(consoleHandler);

			FileHandler fileHandler = new FileHandler("server_log.txt", false);
			fileHandler.setLevel(logLevel);
			fileHandler.setFormatter(new SimpleFormatter());
			logger.addHandler(fileHandler);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	private Server() {
		SERVER_PORT_NUMBER = 8081;
	}
	private Server(String port) {
		SERVER_PORT_NUMBER = Integer.parseInt(port);
	}

	public static void main(String[] args) {

		//CRAIG:
		//we need to decide what the exact string will be typed into command line
		//for right now for testing purposes I'm just saying 'sql' and 'file'

		PluginManager pm = new PluginManager();
		pm.parseConfig();
		System.out.println("here");
		// TODO: Add PluginLoader functionality to this
		IPlugin plugin = pm.initPersistence("sql");
		try {
    		Server server;
    		switch(args.length) {
    			case 0:
    				server = new Server();
    				System.out.println("Remove this because it is just for testing");
    				break;
    			case 1:
    				server = new Server(args[0]);
    				break;
    			case 3:
    				server = new Server(args[2]);
    				plugin = pm.initPersistence(args[0]);
    				break;
    			default:
    				throw new IllegalArgumentException();
    		}
    		server.run(plugin);
		} catch(IllegalArgumentException e) {
			System.out.println(USAGE_STATEMENT);
		}


//		check if port number is specified
//		if(args.length == 0)
//		{
//			new Server().run();
//		}
//		else if(args.length == 1)
//		{
//			new Server(args[0]).run();
//		}
//		else if(args.length == 3)
//		{
//			new Server(args[2]).run();
//		}
//		else
//		{
//			System.out.println(USAGE_STATEMENT);
//		}
	}

	private void run(IPlugin plugin) {

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
		//pass plugin into first layer facades and pass instance of server model facade into plugin
		GameList gameList = new GameList();
		List<IGame> gamess = plugin.getGames();
		if(gamess.size() > 0)
			gameList.setGames(gamess);
		
		

		// Facades
		IServerModelFacade movesModelFacade = new MovesServerModelFacade(gameList);
		IServerModelFacade userModelFacade = new UserServerModelFacade(gameList);
		IServerModelFacade gameModelFacade = new GameServerModelFacade(gameList);
		IServerModelFacade gamesModelFacade = new GamesServerModelFacade(gameList);

		//ICommandCreationFacade commandFacade = new CommandCreationFacade(modelFacade);// send modelFacade into this constructor

		//comment the following 4 lines if you want to use the dummyfacades
		IUserFacade userFacade = new UserFacade(userModelFacade, plugin);
		IGamesFacade gamesFacade = new GamesFacade(gamesModelFacade);
		IGameFacade gameFacade = new GameFacade(gameModelFacade);
		IMovesFacade movesFacade = new MovesFacade(movesModelFacade, plugin);


//		// Un-comment to test the persistence plugins
//		plugin.addUser(new User("Cameron", "cameron", 1));
//		plugin.addUser(new User("Cameron", "cameron", 2));
//		plugin.addUser(new User("Cameron", "cameron", 4));
//		plugin.addUser(new User("Cameron", "cameron", 3));
//		System.out.println(plugin.getRegisteredUsers().toString());
//		plugin.createGame(gameList.getGames().get(0));
//		System.out.println(plugin.getGames().toString());
//		System.out.println(plugin.getNewGameByGameId(gameList.getGames().get(0).getGameInfo().getId()));

		//un-comment the following lines if you want to use the dummyfacades
//		IUserFacade userFacade = new DummyUserFacade();
//		IGamesFacade gamesFacade = new DummyGamesFacade();
//		IGameFacade gameFacade = new DummyGameFacade();
//		IMovesFacade movesFacade = new DummyMovesFacade();

		//handlers
		movesHandler = new MovesHandler(movesFacade);
		gamesHandler = new GamesHandler(gamesFacade);
		gameHandler = new GameHandler(gameFacade);
		userHandler = new UserHandler(userFacade);


		// contexts
		server.createContext("/user/login", userHandler);
		server.createContext("/user/register", userHandler);

		server.createContext("/games/list", gamesHandler);
		server.createContext("/games/create", gamesHandler);
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

//		System.out.println("server started");
		logger.info("Server started");
		System.out.println("Server started");
		server.start();
	}
}
