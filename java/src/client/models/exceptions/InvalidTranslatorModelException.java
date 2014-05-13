package client.models.exceptions;

public class InvalidTranslatorModelException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3709693889751026519L;
	
	public InvalidTranslatorModelException(){
		super();
	}
	
	public InvalidTranslatorModelException(String message){
		super(message);
	}
}
