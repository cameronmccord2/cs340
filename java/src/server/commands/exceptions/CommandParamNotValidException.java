package server.commands.exceptions;

public class CommandParamNotValidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2392601419511294381L;

	public CommandParamNotValidException(String message){
		super(message);
	}
	
	public CommandParamNotValidException(){
		super();
	}
}
