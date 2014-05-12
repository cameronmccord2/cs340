package client.models;

import java.util.List;

/**
 * The Class DomesticTrade.
 */
public class DomesticTrade extends Trade implements IDomesticTrade {

	/**
	 * Instantiates a new domestic trade.
	 *
	 * @param from the from
	 * @param to the to
	 * @param sendResources the send resources
	 * @param recieveResources the recieve resources
	 */
	public DomesticTrade(IParticipant from, IParticipant to, List<IResourceCard> sendResources, List<IResourceCard> recieveResources) {
		super(from, to, sendResources, recieveResources);
	}

	/* (non-Javadoc)
	 * @see client.models.ITrade#canDo()
	 */
	@Override
	public boolean canDo() {
		if(this.from != null && this.to != null && this.send != null && this.recieve != null && 
				(this.send.size() != 0 || this.recieve.size() != 0))
			return true;
		return false;
	}

}
