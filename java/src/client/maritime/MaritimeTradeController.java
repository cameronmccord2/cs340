package client.maritime;

import shared.definitions.*;
import client.base.*;
import client.models.ICatanModelObserver;
import client.models.IProxy;
import client.models.ResourceCard;
import client.models.exceptions.CantFindGameModelException;


/**
 * Implementation for the maritime trade controller
 */
public class MaritimeTradeController extends Controller implements IMaritimeTradeController, ICatanModelObserver {

	private IMaritimeTradeOverlay tradeOverlay;
	private ResourceType recieveResource;
	private ResourceType sendResource;
	
	private IProxy proxy;
	private ResourceType[] enabledGiveResources;
	private ResourceType[] enabledGetResources;
	private Integer neededCountToTrade;
	
	public MaritimeTradeController(IMaritimeTradeView tradeView, IMaritimeTradeOverlay tradeOverlay, IProxy proxy) {
		
		super(tradeView);
		this.proxy = proxy;
		this.proxy.getFacade().registerAsObserver(this);
		setTradeOverlay(tradeOverlay);
		this.neededCountToTrade = 4;
	}
	
	public IMaritimeTradeView getTradeView() {
		
		return (IMaritimeTradeView)super.getView();
	}
	
	public IMaritimeTradeOverlay getTradeOverlay() {
		return tradeOverlay;
	}

	public void setTradeOverlay(IMaritimeTradeOverlay tradeOverlay) {
		this.tradeOverlay = tradeOverlay;
	}

	@Override
	public void startTrade() {
		this.recieveResource = null;
		this.sendResource = null;
		
		
		
		getTradeOverlay().setCancelEnabled(true);
		getTradeOverlay().showModal();
	}

	@Override
	public void makeTrade() {

		getTradeOverlay().closeModal();
	}

	@Override
	public void cancelTrade() {

		getTradeOverlay().closeModal();
	}

	@Override
	public void setGetResource(ResourceType resource) {
//		System.out.println("resourceToRecieve: " + resource.toString());
		this.recieveResource = resource;
		this.decideTradeState();
	}

	@Override
	public void setGiveResource(ResourceType resource) {
//		System.out.println("resourceToSend: " + resource.toString());
		this.sendResource = resource;
		this.decideTradeState();
	}

	private void decideTradeState() {
		try {
			if(this.proxy.getFacade().getPlayerResourceCount(ResourceCard.getCardForType(this.sendResource)) >= 4 && this.recieveResource != null){
				if(this.recieveResource != null){
					this.getTradeOverlay().setStateMessage("Trade!");
					this.getTradeOverlay().setTradeEnabled(true);
				}else{
					this.getTradeOverlay().setStateMessage("Choose what to get");
					this.getTradeOverlay().setTradeEnabled(false);
				}
				return;
			}
		} catch (CantFindGameModelException e) {
			// do nothing
		}
		this.getTradeOverlay().setStateMessage("You don't have enough resources");
		this.getTradeOverlay().setTradeEnabled(false);
	}

	@Override
	public void unsetGetValue() {
//		System.out.println("unsetResource: " + resource.toString());
		this.recieveResource = null;
		this.decideTradeState();
	}

	@Override
	public void unsetGiveValue() {
		this.sendResource = null;
		this.decideTradeState();
	}

	@Override
	public void update() {
		
		try{
			this.enabledGiveResources = new ResourceType[5];
			int counter = 0;
			if(this.proxy.getFacade().getPlayerResourceCount(ResourceCard.BRICK) >= this.neededCountToTrade)
				enabledGiveResources[counter++] = ResourceType.BRICK;
			if(this.proxy.getFacade().getPlayerResourceCount(ResourceCard.ORE) >= neededCountToTrade)
				enabledGiveResources[counter++] = ResourceType.ORE;
			if(this.proxy.getFacade().getPlayerResourceCount(ResourceCard.WHEAT) >= neededCountToTrade)
				enabledGiveResources[counter++] = ResourceType.WHEAT;
			if(this.proxy.getFacade().getPlayerResourceCount(ResourceCard.WOOD) >= neededCountToTrade)
				enabledGiveResources[counter++] = ResourceType.WOOD;
			if(this.proxy.getFacade().getPlayerResourceCount(ResourceCard.SHEEP) >= neededCountToTrade)
				enabledGiveResources[counter++] = ResourceType.SHEEP;
			
			this.enabledGetResources = new ResourceType[5];
			counter = 0;
			if(this.proxy.getFacade().getBankResourceCount(ResourceCard.BRICK) >= 1)
				enabledGetResources[counter++] = ResourceType.BRICK;
			if(this.proxy.getFacade().getBankResourceCount(ResourceCard.ORE) >= 1)
				enabledGetResources[counter++] = ResourceType.ORE;
			if(this.proxy.getFacade().getBankResourceCount(ResourceCard.WHEAT) >= 1)
				enabledGetResources[counter++] = ResourceType.WHEAT;
			if(this.proxy.getFacade().getBankResourceCount(ResourceCard.WOOD) >= 1)
				enabledGetResources[counter++] = ResourceType.WOOD;
			if(this.proxy.getFacade().getBankResourceCount(ResourceCard.SHEEP) >= 1)
				enabledGetResources[counter++] = ResourceType.SHEEP;
			
			this.getTradeOverlay().showGetOptions(enabledGetResources);
			this.getTradeOverlay().showGiveOptions(enabledGiveResources);
			
			if(this.proxy.getFacade().isMyTurn()){
				this.getTradeOverlay().setStateMessage("Choose what to give up");
				
				
				System.out.println(enabledGiveResources.toString());
				
				this.getTradeOverlay().showGetOptions(enabledGetResources);
				this.getTradeOverlay().showGiveOptions(enabledGiveResources);
			}else{
				this.getTradeOverlay().hideGetOptions();
				this.getTradeOverlay().hideGiveOptions();
				this.getTradeOverlay().setStateMessage("Not your turn");
				this.getTradeOverlay().setTradeEnabled(false);
				this.getTradeOverlay().hideGetOptions();
				this.getTradeOverlay().hideGiveOptions();
			}
		}catch(CantFindGameModelException e){
			// dont do any updating
		}
	}

}




























