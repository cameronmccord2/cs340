package client.models;

import java.util.List;

import client.data.PlayerInfo;

public class Bank extends Participant implements IBank {

	public Bank(){
		super();
		
	}
	
	public Bank(PlayerInfo playerInfo, List<IDevelopmentCard> developmentCards, List<IResourceCard> resourceCards){
		super(developmentCards, resourceCards);
	}
	
	@Override
	public void setUpNewBank(){
		
	}

	@Override
	public boolean willAcceptTrade(ITrade trade) {
		return true;
	}
}
