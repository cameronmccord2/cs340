package client.models;

import java.util.List;

import client.exceptions.InvalidTradeException;

/**
 * The Class MaritimeTrade.
 */
public class MaritimeTrade extends Trade implements IMaritimeTrade {
	
	/** The send ratio. */
	private Integer sendRatio = 0;

	/**
	 * Instantiates a new maritime trade.
	 *
	 * @param from the from
	 * @param to the to
	 * @param sendResources the send resources
	 * @param recieveResources the recieve resources
	 * @param sendRatio the send ratio, must be 2, 3, or 4
	 * @throws InvalidTradeException 
	 */
	public MaritimeTrade(IParticipant from, IParticipant to, List<IResourceCard> sendResources, List<IResourceCard> recieveResources, Integer sendRatio) throws InvalidTradeException {
		super(from, to, sendResources, recieveResources);
		this.setSendRatio(sendRatio);
	}

	/* (non-Javadoc)
	 * @see client.models.ITrade#canDo()
	 */
	@Override
	public boolean canDo() {
		// TODO assume the correct card types are being traded?
		return (this.recieve.size() * this.sendRatio == this.send.size());
	}

	/* (non-Javadoc)
	 * @see client.models.IMaritimeTrade#getSendRatio()
	 */
	@Override
	public Integer getSendRatio() {
		return sendRatio;
	}

	/* (non-Javadoc)
	 * @see client.models.IMaritimeTrade#setSendRatio(java.lang.Integer)
	 */
	@Override
	public void setSendRatio(Integer sendRatio) throws InvalidTradeException {
		switch(sendRatio){
		case 2:
		case 3:
		case 4:
			break;
			
		default:
			throw new InvalidTradeException("You must specify a ratio of 2, 3, or 4");
		}
		if(this.sendRatio == 0)
			this.sendRatio = sendRatio;
	}

}
