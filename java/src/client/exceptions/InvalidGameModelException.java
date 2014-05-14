package client.exceptions;

public class InvalidGameModelException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3902499133919740399L;

	public InvalidGameModelException(){
		super();
	}
	
	public InvalidGameModelException(String message){
		super(message);
	}
}
