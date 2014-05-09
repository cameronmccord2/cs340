package client.models;

import java.util.List;

public class DomesticTrade extends Trade implements IDomesticTrade {

	public DomesticTrade(IParticipant from, IParticipant to, List<IResourceCard> sendResources, List<IResourceCard> recieveResources) {
		super(from, to, sendResources, recieveResources);
	}

	@Override
	public boolean canDo() {
		return true;
	}

}
