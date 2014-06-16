package client.models;

import java.io.Serializable;

public class ServerResponse  implements Serializable  {
	
	private String json;
	private int responseCode;

	public ServerResponse(String json, int responseCode) {
		this.json = json;
		this.responseCode = responseCode;
	}

	/**
	 * @return the json
	 */
	public String getJson() {
		return json;
	}

	/**
	 * @param json the json to set
	 */
	public void setJson(String json) {
		this.json = json;
	}

	/**
	 * @return the responseCode
	 */
	public int getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServerResponse [json=");
		builder.append(json);
		builder.append(", responseCode=");
		builder.append(responseCode);
		builder.append("]");
		return builder.toString();
	}

	
	
}
