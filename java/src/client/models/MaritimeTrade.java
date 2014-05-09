package client.models;

import java.util.List;

public class MaritimeTrade extends Trade implements IMaritimeTrade {
	
	private Integer sendRatio = 0;

	public MaritimeTrade(IParticipant from, IParticipant to, List<IResourceCard> sendResources, List<IResourceCard> recieveResources, Integer sendRatio) {
		super(from, to, sendResources, recieveResources);
		this.sendRatio = sendRatio;
	}

	@Override
	public boolean canDo() {
		// TODO assume the correct card types are being traded?
		return (this.recieve.size() * this.sendRatio == this.send.size());
	}

	@Override
	public Integer getSendRatio() {
		return sendRatio;
	}

	@Override
	public void setSendRatio(Integer sendRatio) {
		if(this.sendRatio == 0)
			this.sendRatio = sendRatio;
	}

}
