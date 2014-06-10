package server.commands;

/**
 * Holds the response that should be returned to the Client. 
 * This will be returned to the Facade which will then return it to the Handlers which will convert it to an HttpExchange object.
 * @author scottdaly
 *
 */
public class CommandResponse {

	protected String response;
	protected String responseCode;
	
	public CommandResponse(String response, String responseCode) {
		this.response = response;
		this.responseCode = responseCode;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CommandResponse [response=" + response + ", responseCode="
				+ responseCode + "]";
	}
	
}