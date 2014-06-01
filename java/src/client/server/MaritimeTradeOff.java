/**
 * 
 */
package client.server;

import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;

/**
 * Holds info to perform a maritime trade
 * @author scottdaly
 *
 */
public class MaritimeTradeOff implements ICommandParams{
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
	@Override
	public void isValid() throws CommandParamNotValidException {
		if(this.type == null || this.type.length() == 0 || !this.type.equals("maritimeTrade") || this.playerIndex < 0)
			throw new CommandParamNotValidException("Type musnt be null, length zero, or not equal to 'maritimeTrade', player index must be greater than zero: " + this.toString());
		if(this.ratio < 2 || this.ratio > 4 || this.inputResource == null || this.inputResource.length() == 0 || this.outputResource == null || this.outputResource.length() == 0)
			throw new CommandParamNotValidException("The ratio should be: 2 <= ratio <=4, input and output resources must not be null or of length zero: " + this.toString());
		if(!(this.outputResource.equals("wood") || this.outputResource.equals("ore") || this.outputResource.equals("wheat") || 
				this.outputResource.equals("sheep") || this.outputResource.equals("brick")))
			throw new CommandParamNotValidException("The output resource must be a valid resource, lowercase: " + this.toString());
		if(!(this.inputResource.equals("wood") || this.inputResource.equals("ore") || this.inputResource.equals("wheat") || 
				this.inputResource.equals("sheep") || this.inputResource.equals("brick")))
			throw new CommandParamNotValidException("The input resource must be a valid resource, lowercase: " + this.toString());
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MaritimeTradeOff [type=");
		builder.append(type);
		builder.append(", playerIndex=");
		builder.append(playerIndex);
		builder.append(", ratio=");
		builder.append(ratio);
		builder.append(", inputResource=");
		builder.append(inputResource);
		builder.append(", outputResource=");
		builder.append(outputResource);
		builder.append("]");
		return builder.toString();
	}
	
	
}
