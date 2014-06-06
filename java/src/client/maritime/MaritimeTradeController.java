package client.maritime;

import shared.definitions.*;
import client.base.*;
import client.data.PlayerInfo;
import client.models.ResourceCard;
import client.models.exceptions.CantFindGameModelException;
import client.models.exceptions.CantFindPlayerException;
import client.models.interfaces.ICatanModelObserver;
import client.models.interfaces.IFacade;
import client.models.interfaces.IPlayer;
import client.models.interfaces.IProxy;
import client.server.MaritimeTradeOff;


/**
 * Implementation for the maritime trade controller
 */
public class MaritimeTradeController extends Controller implements IMaritimeTradeController, ICatanModelObserver {

	private IMaritimeTradeOverlay tradeOverlay;
	private ResourceType receiveResource;
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
		this.receiveResource = null;
		this.sendResource = null;



		getTradeOverlay().setCancelEnabled(true);
		getTradeOverlay().showModal();
	}

	@Override
	public void makeTrade() {

		if(getTradeOverlay().isModalShowing())
			getTradeOverlay().closeModal();

		try
		{
   		IFacade facade = proxy.getFacade();
   		IPlayer player;
			player = facade.getCurrentUser();
   		PlayerInfo info = player.getPlayerInfo();
   		int ratio = this.neededCountToTrade;
   		String inputResource = this.sendResource.toString().toLowerCase();
   		String outputResource = this.receiveResource.toString().toLowerCase();

   		MaritimeTradeOff mTrade = new MaritimeTradeOff("maritimeTrade",
   																	  info.getPlayerIndex(),
   																	  ratio,
   																	  inputResource,
   																	  outputResource);
   		proxy.movesMaritimeTrade(mTrade);
		}
		catch (CantFindGameModelException | CantFindPlayerException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void cancelTrade() {
		this.getTradeOverlay().reset();
		getTradeOverlay().closeModal();
	}

	@Override
	public void setGetResource(ResourceType resource) {
		this.receiveResource = resource;
		if(resource != null)
			this.getTradeOverlay().selectGetOption(resource, 1);
		this.decideTradeState();
	}

	@Override
	public void setGiveResource(ResourceType resource) {
		try {
			this.sendResource = resource;
			System.out.println("send resource: " + resource);
			if(resource != null){
				this.neededCountToTrade = this.proxy.getFacade().getMaritimeTradeAmountForResource(resource);
				this.getTradeOverlay().selectGiveOption(resource, this.neededCountToTrade);
			}
			this.decideTradeState();
		} catch (CantFindPlayerException e) {
			System.out.println("cant find player");
		} catch (CantFindGameModelException e) {
			System.out.println("cant find game model");
		}
	}

	private void decideTradeState() {
		if(this.sendResource == null){
			this.getTradeOverlay().setStateMessage("Choose what to give up");
			this.getTradeOverlay().setTradeEnabled(false);
			return;
		}else if(this.receiveResource == null){
			this.getTradeOverlay().setStateMessage("Choose what to get");
			this.getTradeOverlay().setTradeEnabled(false);
		}else{
			this.getTradeOverlay().setStateMessage("Trade!");
			this.getTradeOverlay().setTradeEnabled(true);
		}
	}

	@Override
	public void unsetGetValue() {
		this.receiveResource = null;

		this.getTradeOverlay().showGetOptions(this.enabledGetResources);
		this.getTradeOverlay().showGiveOptions(this.enabledGiveResources);
		this.setGiveResource(this.sendResource);
		this.decideTradeState();
	}
	/*
	private String printArray(ResourceType[] enabledGetResources2) {
		StringBuilder sb = new StringBuilder();
		for (ResourceType r : enabledGetResources2) {
			if(r != null){
				sb.append(r.toString());
				sb.append(" ");
			}
		}
		return sb.toString();
	}
	*/
	@Override
	public void unsetGiveValue() {
		this.sendResource = null;
		this.getTradeOverlay().showGetOptions(this.enabledGetResources);
		this.getTradeOverlay().showGiveOptions(this.enabledGiveResources);
		this.setGetResource(this.receiveResource);
		this.decideTradeState();
	}

	@Override
	public void update() {

		try{
			this.enabledGiveResources = new ResourceType[5];
			int counter = 0;
			System.out.println("needed count: " + this.neededCountToTrade);
			if(this.proxy.getFacade().getPlayerResourceCount(ResourceCard.BRICK) >= this.proxy.getFacade().getMaritimeTradeAmountForResource(ResourceType.BRICK))
				this.enabledGiveResources[counter++] = ResourceType.BRICK;
			if(this.proxy.getFacade().getPlayerResourceCount(ResourceCard.ORE) >= this.proxy.getFacade().getMaritimeTradeAmountForResource(ResourceType.ORE))
				this.enabledGiveResources[counter++] = ResourceType.ORE;
			if(this.proxy.getFacade().getPlayerResourceCount(ResourceCard.WHEAT) >= this.proxy.getFacade().getMaritimeTradeAmountForResource(ResourceType.WHEAT))
				this.enabledGiveResources[counter++] = ResourceType.WHEAT;
			if(this.proxy.getFacade().getPlayerResourceCount(ResourceCard.WOOD) >= this.proxy.getFacade().getMaritimeTradeAmountForResource(ResourceType.WOOD))
				this.enabledGiveResources[counter++] = ResourceType.WOOD;
			if(this.proxy.getFacade().getPlayerResourceCount(ResourceCard.SHEEP) >= this.proxy.getFacade().getMaritimeTradeAmountForResource(ResourceType.SHEEP))
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

			if(this.proxy.getFacade().isMyTurn()){

				this.getTradeOverlay().showGetOptions(this.enabledGetResources);
				this.getTradeOverlay().showGiveOptions(this.enabledGiveResources);
				this.decideTradeState();
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
		} catch (CantFindPlayerException e) {
		}
	}

}




























