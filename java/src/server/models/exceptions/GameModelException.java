package server.models.exceptions;

public class GameModelException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2944509313251615770L;

	public GameModelException(String message){
		super(message);
	}
	
	public GameModelException(){
		super();
	}
}
