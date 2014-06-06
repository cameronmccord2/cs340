package client.models.interfaces;

import client.exceptions.InvalidTradeException;

// TODO: Auto-generated Javadoc
/**
 * The Interface IMaritimeTrade.
 */
public interface IMaritimeTrade extends ITrade {

	/**
	 * Gets the send ratio for the trade, the left integer of 4:1, 3:1, or 2:1
	 *
	 * @return the send ratio for the trade
	 */
	Integer getSendRatio();

	/**
	 * Sets the send ratio for the trade, must be 2, 3, or 4
	 *
	 * @param sendRatio the new send ratio, must be 2, 3, 4
	 * @throws InvalidTradeException 
	 */
	void setSendRatio(Integer sendRatio) throws InvalidTradeException;
	
}
