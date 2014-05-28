/**
 * 
 */
package server.main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Holds an instance of HttpServer and receives HttpExchange objects from endpoints.
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
		
		//handlers
		server.createContext("/user/login", validateUserHandler);
		
		server.start();
	}
	
	private HttpHandler validateUserHandler = new HttpHandler() {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			InputStream is = exchange.getRequestBody();
			
			//read the input stream
			XStream xstream = new XStream(new DomDriver());
			//ValidateUserParams params = (ValidateUserParams)xstream.fromXML(exchange.getRequestBody());
			is.close();
			
			//prepare responseBody
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			OutputStream responseBody = exchange.getResponseBody(); 
			//xstream.toXML(results,responseBody);
			responseBody.close();
		}
	};

}
