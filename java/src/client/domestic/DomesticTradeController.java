package client.domestic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import shared.definitions.*;
import client.base.*;
import client.data.PlayerInfo;
import client.misc.*;
import client.models.ICatanModelObserver;
import client.models.IProxy;
import client.models.IResourceCard;
import client.models.ResourceCard;
import client.models.ResourceCollection;
import client.models.exceptions.CantDoTradeException;
import client.models.exceptions.CantFindGameModelException;
import client.models.exceptions.CantFindPlayerException;
import client.models.translator.TRTradeOffer;
import client.server.AcceptTrade;
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
	private String state;

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
		this.state = "Inited";
		this.proxy = proxy;
		this.proxy.getFacade().registerAsObserver(this);
		
		setTradeOverlay(tradeOverlay);
		setWaitOverlay(waitOverlay);
		setAcceptOverlay(acceptOverlay);
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
		this.state = "starting";
		getTradeOverlay().showModal();
	}

	@Override
	public void decreaseResourceAmount(ResourceType resource) {
		try{
			Integer hasOfResource = this.proxy.getFacade().getPlayerResourceCount(ResourceCard.getCardForType(resource));
			Integer currentTradeCount = 0;
			if(this.recieveTypes.indexOf(resource) != -1){
				this.recieveCounts.decreaseResourceAmount(resource);
				currentTradeCount = this.recieveCounts.getResourceCount(resource);
				this.tradeOverlay.setResourceAmountChangeEnabled(resource, (hasOfResource > currentTradeCount), (currentTradeCount > 0));
			}
			else if(this.sendTypes.indexOf(resource) != -1){
				this.sendCounts.decreaseResourceAmount(resource);
				currentTradeCount = this.sendCounts.getResourceCount(resource);
				this.tradeOverlay.setResourceAmountChangeEnabled(resource, (hasOfResource > currentTradeCount), (currentTradeCount > 0));
			}
			else
				throw new RuntimeException("decrease error, " + resource.toString() + ", " + this.recieveCounts.toString() + ":" + this.recieveTypes.toString() + ", " + this.sendCounts.toString() + ":" + this.sendTypes.toString());
			
			
			this.decideTradeState();
		}catch (CantFindGameModelException e) {
		}
	}

	@Override
	public void increaseResourceAmount(ResourceType resource) {
		try{
			Integer hasOfResource = 0;
			if(this.recieveTypes.indexOf(resource) != -1){
				this.recieveCounts.increaseResourceAmount(resource);
				int currentTradeCount = this.recieveCounts.getResourceCount(resource);
				if(this.playerToTradeWith != -1){
					hasOfResource = this.proxy.getFacade().getPlayerWithIndex(this.playerToTradeWith).getResourceCards().get(ResourceCard.getCardForType(resource));
					this.tradeOverlay.setResourceAmountChangeEnabled(resource, (hasOfResource > currentTradeCount), (currentTradeCount > 0));
				}
			}
			else if(this.sendTypes.indexOf(resource) != -1){
				hasOfResource = this.proxy.getFacade().getPlayerResourceCount(ResourceCard.getCardForType(resource));
				this.sendCounts.increaseResourceAmount(resource);
				int currentTradeCount = this.sendCounts.getResourceCount(resource);
				this.tradeOverlay.setResourceAmountChangeEnabled(resource, (hasOfResource > currentTradeCount), (currentTradeCount > 0));
			}
			else
				throw new RuntimeException("increase error, " + resource.toString() + ", " + this.recieveCounts.toString() + ":" + this.recieveTypes.toString() + ", " + this.sendCounts.toString() + ":" + this.sendTypes.toString());
			
			
			this.decideTradeState();
		}catch (CantFindGameModelException e) {
		} catch (CantFindPlayerException e) {
		}
	}

	@Override
	public void sendTradeOffer() {
		try {
			OfferTrade tradeOffer = new OfferTrade(this.proxy.getFacade().getCurrentUser().getPlayerInfo().getPlayerIndex(), this.playerToTradeWith, ResourceCollection.getOffer(this.sendCounts, this.recieveCounts), "offerTrade");
			this.proxy.movesOfferTrade(tradeOffer);
			getTradeOverlay().closeModal();
			getTradeOverlay().reset();
			getWaitOverlay().showModal();
		
		} catch (CantFindPlayerException e) {
		} catch (CantFindGameModelException e) {
		}
	}

	@Override
	public void setPlayerToTradeWith(int playerIndex) {
		this.playerToTradeWith = playerIndex;
		
		// restrict all counters to the max possible values if higher, add restrictions
		this.restrictTotalCountForResourceType(ResourceType.BRICK);
		this.restrictTotalCountForResourceType(ResourceType.ORE);
		this.restrictTotalCountForResourceType(ResourceType.SHEEP);
		this.restrictTotalCountForResourceType(ResourceType.WOOD);
		this.restrictTotalCountForResourceType(ResourceType.WHEAT);
		
		
		this.decideTradeState();
	}

	private void restrictTotalCountForResourceType(ResourceType resource) {
		
		try{
			if(this.recieveTypes.indexOf(resource) != -1){
				if(this.playerToTradeWith == -1){
					this.tradeOverlay.setResourceAmount(resource, "0");
					this.tradeOverlay.setResourceAmountChangeEnabled(resource, false, false);
					this.recieveCounts.setResourceToZero(resource);
				}else{
					Map<IResourceCard, Integer> map = this.proxy.getFacade().getPlayerWithIndex(this.playerToTradeWith).getResourceCards();
					if(this.recieveCounts.getResourceCount(resource) > map.get(ResourceCard.getCardForType(resource))){
						this.tradeOverlay.setResourceAmount(resource, map.get(ResourceCard.getCardForType(resource)).toString());
						this.recieveCounts.setResourceCount(resource, map.get(ResourceCard.getCardForType(resource)));
					}
					
					int currentTradeCount = this.recieveCounts.getResourceCount(resource);
					int hasOfResource = this.proxy.getFacade().getPlayerWithIndex(this.playerToTradeWith).getResourceCards().get(ResourceCard.getCardForType(resource));
					this.tradeOverlay.setResourceAmountChangeEnabled(resource, (hasOfResource > currentTradeCount), (currentTradeCount > 0));
				}
			}
		} catch (CantFindPlayerException e) {
		} catch (CantFindGameModelException e) {
		}
	}

	@Override
	public void setResourceToReceive(ResourceType resource) {
		this.sendTypes.remove(resource);
		this.sendCounts.setResourceToZero(resource);
		if(this.recieveTypes.indexOf(resource) != -1)
			throw new RuntimeException("there is an error with set resource to recieve, already in list: " + this.recieveTypes.toString() + ", reosurce: " + resource.toString());
		this.recieveTypes.add(resource);
		this.recieveCounts.setResourceToZero(resource);
		
		try {
			if(this.playerToTradeWith != -1){
				this.tradeOverlay.setResourceAmountChangeEnabled(resource, (this.proxy.getFacade().getPlayerWithIndex(this.playerToTradeWith).getResourceCards().get(ResourceCard.getCardForType(resource)) > 0), false);
			}
		} catch (CantFindPlayerException | CantFindGameModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.decideTradeState();
	}

	@Override
	public void setResourceToSend(ResourceType resource) {
		this.recieveTypes.remove(resource);
		this.recieveCounts.setResourceToZero(resource);
		if(this.sendTypes.indexOf(resource) != -1)
			throw new RuntimeException("the resource is already in the send list: " + resource.toString() + ", list: " + this.sendTypes.toString());
		this.sendTypes.add(resource);
		this.sendCounts.setResourceToZero(resource);
		
		try {
			this.tradeOverlay.setResourceAmountChangeEnabled(resource, (this.proxy.getFacade().getPlayerResourceCount(ResourceCard.getCardForType(resource)) > 0), false);
		} catch (CantFindGameModelException e) {
		
		}
		
		this.decideTradeState();
	}

	@Override
	public void unsetResource(ResourceType resource) {
		this.recieveCounts.setResourceToZero(resource);
		this.sendCounts.setResourceToZero(resource);
		this.recieveTypes.remove(resource);
		this.sendTypes.remove(resource);
		this.decideTradeState();
	}
	
	private void decideTradeState(){
		this.state = "notrade";
		this.tradeOverlay.setTradeEnabled(false);
		if(this.playerToTradeWith == -1 && (this.sendCounts.getTotalCount() == 0 && this.recieveCounts.getTotalCount() == 0))
			this.tradeOverlay.setStateMessage("Set the trade you want to make");
		else if(this.playerToTradeWith != -1 && (this.sendCounts.getTotalCount() == 0 && this.recieveCounts.getTotalCount() == 0))
			this.tradeOverlay.setStateMessage("Select resources to trade");
		else if(this.playerToTradeWith == -1 && (this.sendCounts.getTotalCount() != 0 || this.recieveCounts.getTotalCount() != 0))
			this.tradeOverlay.setStateMessage("Select a player");
		else{
			this.tradeOverlay.setStateMessage("Trade!");
			this.tradeOverlay.setTradeEnabled(true);
			this.state = "cantrade";
		}
	}

	@Override
	public void cancelTrade() {
		// everything gets cleared out when the modal gets shown
		if(getTradeOverlay().isModalShowing()){
			getTradeOverlay().closeModal();
			getTradeOverlay().reset();
		}
		this.state = "closed";
	}

	@Override
	public void acceptTrade(boolean willAccept) {
		try{
			this.state = "accepted";
			this.proxy.movesAcceptTrade(new AcceptTrade(this.proxy.getFacade().getCurrentTrade(), willAccept));
			if(getAcceptOverlay().isModalShowing())
				getAcceptOverlay().closeModal();
		}catch(CantFindGameModelException e){
			// dont do anything, wait for the model to update again
		}
	}

	@Override
	public void update() {
		try{
			
			if(this.playersHaveNotBeenSet){
				PlayerInfo[] players = this.proxy.getFacade().getAllPlayerInfos();
				PlayerInfo[] finalPlayers = new PlayerInfo[players.length - 1];
				int counter = 0;
				for (PlayerInfo pi : players) {
					if(pi.getPlayerIndex() != this.proxy.getFacade().getCurrentUserIndex())
						finalPlayers[counter++] = pi;
				}
				if(finalPlayers == null || finalPlayers.length == 0)
					return;
				
				this.tradeOverlay.setPlayers(finalPlayers);
				this.playersHaveNotBeenSet = false;
			}
			
			TRTradeOffer currentTrade = this.proxy.getFacade().getCurrentTrade();
			if(currentTrade == null){
				if(this.waitOverlay.isModalShowing())
					this.waitOverlay.closeModal();
				
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
				
			}else if(currentTrade.getReceiver() == this.proxy.getFacade().getCurrentUserIndex()){
				this.setupAcceptTradeWithTrade(currentTrade);
			}else if(currentTrade.getSender() == this.proxy.getFacade().getCurrentUserIndex()){
				if(!this.waitOverlay.isModalShowing())
					this.waitOverlay.showModal();
			}
		}catch(CantFindGameModelException e){
			// dont do anything, wait for the model to update again
		}
	}

	private void setupAcceptTradeWithTrade(TRTradeOffer currentTrade) {
		try {
			this.acceptOverlay.setPlayerName(this.proxy.getFacade().getPlayerWithIndex(currentTrade.getSender()).getPlayerInfo().getName());
			
			if(currentTrade.getOffer().getBrick() > 0)
				this.acceptOverlay.addGetResource(ResourceType.BRICK, currentTrade.getOffer().getBrick());
			else if(currentTrade.getOffer().getBrick() < 0)
				this.acceptOverlay.addGiveResource(ResourceType.BRICK, currentTrade.getOffer().getBrick() * -1);
			if(currentTrade.getOffer().getOre() > 0)
				this.acceptOverlay.addGetResource(ResourceType.ORE, currentTrade.getOffer().getOre());
			else if(currentTrade.getOffer().getOre() < 0)
				this.acceptOverlay.addGiveResource(ResourceType.ORE, currentTrade.getOffer().getOre() * -1);
			if(currentTrade.getOffer().getSheep() > 0)
				this.acceptOverlay.addGetResource(ResourceType.SHEEP, currentTrade.getOffer().getSheep());
			else if(currentTrade.getOffer().getSheep() < 0)
				this.acceptOverlay.addGiveResource(ResourceType.SHEEP, currentTrade.getOffer().getSheep() * -1);
			if(currentTrade.getOffer().getWheat() > 0)
				this.acceptOverlay.addGetResource(ResourceType.WHEAT, currentTrade.getOffer().getWheat());
			else if(currentTrade.getOffer().getWheat() < 0)
				this.acceptOverlay.addGiveResource(ResourceType.WHEAT, currentTrade.getOffer().getWheat() * -1);
			if(currentTrade.getOffer().getWood() > 0)
				this.acceptOverlay.addGetResource(ResourceType.WOOD, currentTrade.getOffer().getWood());
			else if(currentTrade.getOffer().getWood() < 0)
				this.acceptOverlay.addGiveResource(ResourceType.WOOD, currentTrade.getOffer().getWood() * -1);
		
			Map<IResourceCard, Integer> resourceMap = this.proxy.getFacade().getResourcesForPlayerId(currentTrade.getReceiver());
			
			if(resourceMap.get(ResourceCard.BRICK) < currentTrade.getOffer().getBrick())
				throw new CantDoTradeException();
			if(resourceMap.get(ResourceCard.ORE) < currentTrade.getOffer().getOre())
				throw new CantDoTradeException();
			if(resourceMap.get(ResourceCard.SHEEP) < currentTrade.getOffer().getSheep())
				throw new CantDoTradeException();
			if(resourceMap.get(ResourceCard.WHEAT) < currentTrade.getOffer().getWheat())
				throw new CantDoTradeException();
			if(resourceMap.get(ResourceCard.WOOD) < currentTrade.getOffer().getWood())
				throw new CantDoTradeException();
			
			this.acceptOverlay.setAcceptEnabled(true);
			this.acceptOverlay.showModal();
		} catch (CantFindPlayerException e) {
			throw new RuntimeException("couldnt find player for trade: " + currentTrade.toString());
		} catch (CantDoTradeException e) {
			this.acceptOverlay.setAcceptEnabled(false);
		} catch (CantFindGameModelException e) {
			// wait for the game model to update
		}
	}

	private void setResourceChangeForResource(ResourceType resource) {
		this.tradeOverlay.setResourceAmountChangeEnabled(resource, false, false);
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}

