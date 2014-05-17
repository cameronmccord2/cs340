package client.domestic;

import java.util.ArrayList;
import java.util.List;

import shared.definitions.*;
import client.base.*;
import client.misc.*;
import client.models.DomesticTrade;
import client.models.ICatanModelObserver;
import client.models.IDomesticTrade;
import client.models.IParticipant;
import client.models.IProxy;
import client.models.IResourceCard;
import client.models.ResourceCollection;
import client.models.exceptions.CantFindPlayerException;
import client.server.OfferTrade;


/**
 * Domestic trade controller implementation
 */
public class DomesticTradeController extends Controller implements IDomesticTradeController, ICatanModelObserver {

	private IDomesticTradeOverlay tradeOverlay;
	private IWaitView waitOverlay;
	private IAcceptTradeOverlay acceptOverlay;
	private IProxy proxy;
	private ResourceCollection recieveCounts;
	private ResourceCollection sendCounts;
	private List<ResourceType> recieveTypes;
	private List<ResourceType> sendTypes;
	private Integer playerToTradeWith;

	/**
	 * DomesticTradeController constructor
	 * 
	 * @param tradeView Domestic trade view (i.e., view that contains the "Domestic Trade" button)
	 * @param tradeOverlay Domestic trade overlay (i.e., view that lets the user propose a domestic trade)
	 * @param waitOverlay Wait overlay used to notify the user they are waiting for another player to accept a trade
	 * @param acceptOverlay Accept trade overlay which lets the user accept or reject a proposed trade
	 */
	public DomesticTradeController(IDomesticTradeView tradeView, IDomesticTradeOverlay tradeOverlay,
									IWaitView waitOverlay, IAcceptTradeOverlay acceptOverlay,
									IProxy proxy) {

		super(tradeView);
		
		this.proxy = proxy;
		this.proxy.getFacade().registerAsObserver(this);
		
		setTradeOverlay(tradeOverlay);
		setWaitOverlay(waitOverlay);
		setAcceptOverlay(acceptOverlay);
		
		// dummy data
//		int playercount = 3;
//		PlayerInfo[] pinfos = new PlayerInfo[playercount];
//		for (int i = 0; i < playercount; i++) {
//			PlayerInfo pi = new PlayerInfo();
//			pi.setName("asdf" + i);
//			pi.setPlayerIndex(i);
//			pi.setColor(CatanColor.RED);
//			pi.setId(i);
//			pinfos[i] = pi;
//		}
//		tradeOverlay.setPlayers(pinfos);
	}
	
	public IDomesticTradeView getTradeView() {
		
		return (IDomesticTradeView)super.getView();
	}

	public IDomesticTradeOverlay getTradeOverlay() {
		return tradeOverlay;
	}

	public void setTradeOverlay(IDomesticTradeOverlay tradeOverlay) {
		this.tradeOverlay = tradeOverlay;
	}

	public IWaitView getWaitOverlay() {
		return waitOverlay;
	}

	public void setWaitOverlay(IWaitView waitView) {
		this.waitOverlay = waitView;
	}

	public IAcceptTradeOverlay getAcceptOverlay() {
		return acceptOverlay;
	}

	public void setAcceptOverlay(IAcceptTradeOverlay acceptOverlay) {
		this.acceptOverlay = acceptOverlay;
	}

	@Override
	public void startTrade() {
		this.recieveTypes = new ArrayList<ResourceType>();
		this.sendTypes = new ArrayList<ResourceType>();
		this.recieveCounts = new ResourceCollection(this.proxy);
		this.sendCounts = new ResourceCollection(this.proxy);
		getTradeOverlay().showModal();
	}

	@Override
	public void decreaseResourceAmount(ResourceType resource) {
		if(this.recieveTypes.indexOf(resource) != -1)
			this.recieveCounts.decreaseResourceAmount(resource);
		else if(this.sendTypes.indexOf(resource) != -1)
			this.sendCounts.decreaseResourceAmount(resource);
		else
			throw new RuntimeException("decrease error, " + resource.toString() + ", " + this.recieveCounts.toString() + ":" + this.recieveTypes.toString() + ", " + this.sendCounts.toString() + ":" + this.sendTypes.toString());
	}

	@Override
	public void increaseResourceAmount(ResourceType resource) {
		if(this.recieveTypes.indexOf(resource) != -1)
			this.recieveCounts.increaseResourceAmount(resource);
		else if(this.sendTypes.indexOf(resource) != -1)
			this.sendCounts.increaseResourceAmount(resource);
		else
			throw new RuntimeException("increase error, " + resource.toString() + ", " + this.recieveCounts.toString() + ":" + this.recieveTypes.toString() + ", " + this.sendCounts.toString() + ":" + this.sendTypes.toString());
	}

	@Override
	public void sendTradeOffer() {
		try {
			this.proxy.movesOfferTrade(new OfferTrade(this.proxy.getFacade().getCurrentUser().getPlayerInfo().getPlayerIndex(), this.playerToTradeWith, ResourceCollection.getOffer(this.sendCounts, this.recieveCounts), "Domestic"));
			
			getTradeOverlay().closeModal();
			getWaitOverlay().showModal();
		
		} catch (CantFindPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setPlayerToTradeWith(int playerIndex) {
		this.playerToTradeWith = playerIndex;
	}

	@Override
	public void setResourceToReceive(ResourceType resource) {
		if(this.recieveTypes.indexOf(resource) != -1)
			throw new RuntimeException("there is an error with set resource to recieve, already in list: " + this.recieveTypes.toString() + ", reosurce: " + resource.toString());
		this.recieveTypes.add(resource);
		this.recieveCounts.setResourceToZero(resource);
	}

	@Override
	public void setResourceToSend(ResourceType resource) {
		if(this.sendTypes.indexOf(resource) != -1)
			throw new RuntimeException("the resource is already in the send list: " + resource.toString() + ", list: " + this.sendTypes.toString());
		this.sendTypes.add(resource);
		this.sendCounts.setResourceToZero(resource);
	}

	@Override
	public void unsetResource(ResourceType resource) {
		this.recieveCounts.setResourceToZero(resource);
		this.sendCounts.setResourceToZero(resource);
		this.recieveTypes.remove(resource);
		this.sendTypes.remove(resource);
	}

	@Override
	public void cancelTrade() {
		// everything gets cleared out when the modal gets shown
		getTradeOverlay().closeModal();
	}

	@Override
	public void acceptTrade(boolean willAccept) {
		
		getAcceptOverlay().closeModal();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}

