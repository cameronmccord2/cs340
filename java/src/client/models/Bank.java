package client.models;

import java.util.List;

import client.data.PlayerInfo;
import shared.definitions.ResourceType;

// TODO: Auto-generated Javadoc
/**
 * The Class Bank.
 */
public class Bank extends Participant implements IBank {

    private PlayerInfo playerInfo;

	/**
	 * Instantiates a new bank.
	 */
	public Bank(){
		super();
		
	}
	
	/**
	 * Instantiates a new bank.
	 *
	 * @param _playerInfo the player info
	 * @param developmentCards the development cards
	 * @param resourceCards the resource cards
	 */
	public Bank(PlayerInfo _playerInfo, List<IDevelopmentCard> developmentCards, List<IResourceCard> resourceCards){
		super(developmentCards, resourceCards);
        playerInfo = _playerInfo;
	}
	
	public Bank(TRDevCardList deck, TRResourceList bank) {
		// TODO Auto-generated constructor stub
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
