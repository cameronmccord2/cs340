package server.models;

public class ServerFacadeResponse {
	
	private boolean returnGameModel;
	private String otherResponse;
	
	public ServerFacadeResponse(boolean returnGameModel, String otherResponse) {
		this.returnGameModel = returnGameModel;
		this.otherResponse = otherResponse;
	}
	/**
	 * @return the returnGameModel
	 */
	public boolean isReturnGameModel() {
		return returnGameModel;
	}
	/**
	 * @param returnGameModel the returnGameModel to set
	 */
	public void setReturnGameModel(boolean returnGameModel) {
		this.returnGameModel = returnGameModel;
	}
	/**
	 * @return the otherResponse
	 */
	public String getOtherResponse() {
		return otherResponse;
	}
	/**
	 * @param otherResponse the otherResponse to set
	 */
	public void setOtherResponse(String otherResponse) {
		this.otherResponse = otherResponse;
	}
	
	

}
