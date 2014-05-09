package client.models;

import java.util.List;

import client.data.PlayerInfo;

// TODO: Auto-generated Javadoc
/**
 * The Class Bank.
 */
public class Bank extends Participant implements IBank {

	/**
	 * Instantiates a new bank.
	 */
	public Bank(){
		super();
		
	}
	
	/**
	 * Instantiates a new bank.
	 *
	 * @param playerInfo the player info
	 * @param developmentCards the development cards
	 * @param resourceCards the resource cards
	 */
	public Bank(PlayerInfo playerInfo, List<IDevelopmentCard> developmentCards, List<IResourceCard> resourceCards){
		super(developmentCards, resourceCards);
	}
	
	/* (non-Javadoc)
	 * @see client.models.IBank#setUpNewBank()
	 */
	@Override
	public void setUpNewBank(){
		
	}

	/* (non-Javadoc)
	 * @see client.models.IParticipant#willAcceptTrade(client.models.ITrade)
	 */
	@Override
	public boolean willAcceptTrade(ITrade trade) {
		return true;
	}
}
