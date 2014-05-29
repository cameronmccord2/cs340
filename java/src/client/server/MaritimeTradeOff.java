/**
 * 
 */
package client.server;

/**
 * Holds info to perform a maritime trade
 * @author scottdaly
 *
 */
public class MaritimeTradeOff  extends CommandParams{
	private String type;
	private int playerIndex;
	private int ratio;
	private String inputResource;
	private String outputResource;
	public MaritimeTradeOff(String type, int playerIndex, int ratio,
			String inputResource, String outputResource) {
		this.type = type;
		this.playerIndex = playerIndex;
		this.ratio = ratio;
		this.inputResource = inputResource;
		this.outputResource = outputResource;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the playerIndex
	 */
	public int getPlayerIndex() {
		return playerIndex;
	}
	/**
	 * @param playerIndex the playerIndex to set
	 */
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	/**
	 * @return the ratio
	 */
	public int getRatio() {
		return ratio;
	}
	/**
	 * @param ratio the ratio to set
	 */
	public void setRatio(int ratio) {
		this.ratio = ratio;
	}
	/**
	 * @return the inputResource
	 */
	public String getInputResource() {
		return inputResource;
	}
	/**
	 * @param inputResource the inputResource to set
	 */
	public void setInputResource(String inputResource) {
		this.inputResource = inputResource;
	}
	/**
	 * @return the outputResource
	 */
	public String getOutputResource() {
		return outputResource;
	}
	/**
	 * @param outputResource the outputResource to set
	 */
	public void setOutputResource(String outputResource) {
		this.outputResource = outputResource;
	}
	
	
}
