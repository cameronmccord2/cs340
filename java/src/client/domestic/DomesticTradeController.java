package client.domestic;

import java.util.ArrayList;
import java.util.List;

import shared.definitions.*;
import client.base.*;
import client.misc.*;
import client.models.ICatanModelObserver;
import client.models.IProxy;
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
	private boolean playersHaveNotBeenSet;

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
		this.playersHaveNotBeenSet = true;
		
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
		this.playerToTradeWith = -1;
//		this.update();
		getTradeOverlay().showModal();
	}

	@Override
	public void decreaseResourceAmount(ResourceType resource) {
		System.out.println("decreaseing: " + resource.toString());
		Integer hasOfResource = this.proxy.getFacade().getPlayerResourceCount(resource);
		Integer currentTradeCount = 0;
		if(this.recieveTypes.indexOf(resource) != -1){
			this.recieveCounts.decreaseResourceAmount(resource);
			currentTradeCount = this.recieveCounts.getResourceCount(resource);
		}
		else if(this.sendTypes.indexOf(resource) != -1){
			this.sendCounts.decreaseResourceAmount(resource);
			currentTradeCount = this.recieveCounts.getResourceCount(resource);
		}
		else
			throw new RuntimeException("decrease error, " + resource.toString() + ", " + this.recieveCounts.toString() + ":" + this.recieveTypes.toString() + ", " + this.sendCounts.toString() + ":" + this.sendTypes.toString());
		
		this.tradeOverlay.setResourceAmountChangeEnabled(resource, (hasOfResource > currentTradeCount), (currentTradeCount > 0));
		this.decideTradeState();
	}

	@Override
	public void increaseResourceAmount(ResourceType resource) {
		System.out.println("increasing: " + resource.toString());
		Integer hasOfResource = this.proxy.getFacade().getPlayerResourceCount(resource);
		Integer currentTradeCount = 0;
		if(this.recieveTypes.indexOf(resource) != -1){
			this.recieveCounts.increaseResourceAmount(resource);
			currentTradeCount = this.recieveCounts.getResourceCount(resource);
		}
		else if(this.sendTypes.indexOf(resource) != -1){
			this.sendCounts.increaseResourceAmount(resource);
			currentTradeCount = this.recieveCounts.getResourceCount(resource);
		}
		else
			throw new RuntimeException("increase error, " + resource.toString() + ", " + this.recieveCounts.toString() + ":" + this.recieveTypes.toString() + ", " + this.sendCounts.toString() + ":" + this.sendTypes.toString());
		
		this.tradeOverlay.setResourceAmountChangeEnabled(resource, (hasOfResource > currentTradeCount), (currentTradeCount > 0));
		this.decideTradeState();
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
//		System.out.println("Player: " + playerIndex);
		this.playerToTradeWith = playerIndex;
		this.decideTradeState();
	}

	@Override
	public void setResourceToReceive(ResourceType resource) {
//		System.out.println("resourceToRecieve: " + resource.toString());
		this.sendTypes.remove(resource);
		this.sendCounts.setResourceToZero(resource);
		if(this.recieveTypes.indexOf(resource) != -1)
			throw new RuntimeException("there is an error with set resource to recieve, already in list: " + this.recieveTypes.toString() + ", reosurce: " + resource.toString());
		this.recieveTypes.add(resource);
		this.recieveCounts.setResourceToZero(resource);
		this.decideTradeState();
	}

	@Override
	public void setResourceToSend(ResourceType resource) {
//		System.out.println("resourceToSend: " + resource.toString());
		this.recieveTypes.remove(resource);
		this.recieveCounts.setResourceToZero(resource);
		if(this.sendTypes.indexOf(resource) != -1)
			throw new RuntimeException("the resource is already in the send list: " + resource.toString() + ", list: " + this.sendTypes.toString());
		this.sendTypes.add(resource);
		this.sendCounts.setResourceToZero(resource);
		this.decideTradeState();
	}

	@Override
	public void unsetResource(ResourceType resource) {
//		System.out.println("unsetResource: " + resource.toString());
		this.recieveCounts.setResourceToZero(resource);
		this.sendCounts.setResourceToZero(resource);
		this.recieveTypes.remove(resource);
		this.sendTypes.remove(resource);
		this.decideTradeState();
	}
	
	private void decideTradeState(){
		System.out.println("deciding: " + this.playerToTradeWith + ", " + this.sendCounts.getTotalCount() + ", " + this.recieveCounts.getTotalCount());
		if(this.playerToTradeWith == -1 && (this.sendCounts.getTotalCount() == 0 && this.recieveCounts.getTotalCount() == 0))
			this.tradeOverlay.setStateMessage("Set the trade you want to make");
		else if(this.playerToTradeWith != -1 && (this.sendCounts.getTotalCount() == 0 && this.recieveCounts.getTotalCount() == 0))
			this.tradeOverlay.setStateMessage("Select resources to trade");
		else if(this.playerToTradeWith == -1 && (this.sendCounts.getTotalCount() != 0 || this.recieveCounts.getTotalCount() != 0))
			this.tradeOverlay.setStateMessage("Select a player");
		else
			this.tradeOverlay.setStateMessage("Trade!");
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
		if(this.playersHaveNotBeenSet){
			this.tradeOverlay.setPlayers(this.proxy.getFacade().getAllPlayerInfos());
			this.playersHaveNotBeenSet = false;
		}
		
		if(this.proxy.getFacade().isMyTurn()){
			this.tradeOverlay.setResourceSelectionEnabled(true);
			this.tradeOverlay.setPlayerSelectionEnabled(true);
			this.tradeOverlay.setStateMessage("Set the trade you want to make");
			this.setResourceChangeForResource(ResourceType.BRICK);
			this.setResourceChangeForResource(ResourceType.ORE);
			this.setResourceChangeForResource(ResourceType.SHEEP);
			this.setResourceChangeForResource(ResourceType.WHEAT);
			this.setResourceChangeForResource(ResourceType.WOOD);
			// have the view reset counts to 0 when unhidden?
		}else{
			this.tradeOverlay.setResourceSelectionEnabled(false);
			this.tradeOverlay.setPlayerSelectionEnabled(false);
			this.tradeOverlay.setStateMessage("It is not your turn");
		}
	}

	private void setResourceChangeForResource(ResourceType resource) {
		this.tradeOverlay.setResourceAmountChangeEnabled(resource, (this.proxy.getFacade().getPlayerResourceCount(resource) > 0), false);
	}

}

