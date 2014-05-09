package client.models;

import java.util.List;

// TODO: Auto-generated Javadoc
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
		return true;
	}

}
