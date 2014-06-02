package server.models.exceptions;

public class InvalidUserAttributesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2156389336556029126L;

	public InvalidUserAttributesException(String message){
		super(message);
	}
	
	public InvalidUserAttributesException(){
		super();
	}
}
