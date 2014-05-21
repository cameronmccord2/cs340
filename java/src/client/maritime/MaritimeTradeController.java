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
		this.getTradeOverlay().selectGetOption(resource, 1);
		this.decideTradeState();
	}

	@Override
	public void setGiveResource(ResourceType resource) {
//		System.out.println("resourceToSend: " + resource.toString());
		this.sendResource = resource;
		this.getTradeOverlay().selectGiveOption(resource, this.neededCountToTrade);
		this.decideTradeState();
	}

	private void decideTradeState() {
		try {
			if(this.sendResource != null && this.proxy.getFacade().getPlayerResourceCount(ResourceCard.getCardForType(this.sendResource)) >= 4 && this.recieveResource != null){
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
		this.recieveResource = null;
		
		System.out.println(this.printArray(this.enabledGetResources) + ", " + this.printArray(this.enabledGiveResources));
		this.getTradeOverlay().showGiveOptions(this.enabledGiveResources);
		this.getTradeOverlay().showGetOptions(this.enabledGetResources);
		this.setGiveResource(this.sendResource);
		this.decideTradeState();
	}

	private String printArray(ResourceType[] enabledGetResources2) {
		StringBuilder sb = new StringBuilder();
		for (ResourceType r : enabledGetResources2) {
			sb.append(r.toString());
			sb.append(" ");
		}
		return sb.toString();
	}

	@Override
	public void unsetGiveValue() {
		this.sendResource = null;
		System.out.println(this.printArray(this.enabledGetResources) + ", " + this.printArray(this.enabledGiveResources));
		this.getTradeOverlay().showGetOptions(this.enabledGetResources);
		this.getTradeOverlay().showGiveOptions(this.enabledGiveResources);
		this.setGetResource(this.recieveResource);
		this.decideTradeState();
	}

	@Override
	public void update() {
		
		try{
			this.enabledGiveResources = new ResourceType[5];
			int counter = 0;
			if(this.proxy.getFacade().getPlayerResourceCount(ResourceCard.BRICK) >= this.neededCountToTrade)
				this.enabledGiveResources[counter++] = ResourceType.BRICK;
			if(this.proxy.getFacade().getPlayerResourceCount(ResourceCard.ORE) >= this.neededCountToTrade)
				this.enabledGiveResources[counter++] = ResourceType.ORE;
			if(this.proxy.getFacade().getPlayerResourceCount(ResourceCard.WHEAT) >= this.neededCountToTrade)
				this.enabledGiveResources[counter++] = ResourceType.WHEAT;
			if(this.proxy.getFacade().getPlayerResourceCount(ResourceCard.WOOD) >= this.neededCountToTrade)
				this.enabledGiveResources[counter++] = ResourceType.WOOD;
			if(this.proxy.getFacade().getPlayerResourceCount(ResourceCard.SHEEP) >= this.neededCountToTrade)
				this.enabledGiveResources[counter++] = ResourceType.SHEEP;
			
			this.enabledGetResources = new ResourceType[5];
			counter = 0;
			if(this.proxy.getFacade().getBankResourceCount(ResourceCard.BRICK) >= 1)
				this.enabledGetResources[counter++] = ResourceType.BRICK;
			if(this.proxy.getFacade().getBankResourceCount(ResourceCard.ORE) >= 1)
				this.enabledGetResources[counter++] = ResourceType.ORE;
			if(this.proxy.getFacade().getBankResourceCount(ResourceCard.WHEAT) >= 1)
				this.enabledGetResources[counter++] = ResourceType.WHEAT;
			if(this.proxy.getFacade().getBankResourceCount(ResourceCard.WOOD) >= 1)
				this.enabledGetResources[counter++] = ResourceType.WOOD;
			if(this.proxy.getFacade().getBankResourceCount(ResourceCard.SHEEP) >= 1)
				this.enabledGetResources[counter++] = ResourceType.SHEEP;
			
			this.getTradeOverlay().showGetOptions(this.enabledGetResources);
			this.getTradeOverlay().showGiveOptions(this.enabledGiveResources);
			
			if(this.proxy.getFacade().isMyTurn()){
				this.getTradeOverlay().setStateMessage("Choose what to give up");
				
				this.getTradeOverlay().showGetOptions(this.enabledGetResources);
				this.getTradeOverlay().showGiveOptions(this.enabledGiveResources);
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




























