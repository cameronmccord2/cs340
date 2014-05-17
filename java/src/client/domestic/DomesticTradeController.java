package client.domestic;

import shared.definitions.*;
import client.base.*;
import client.data.PlayerInfo;
import client.misc.*;
import client.models.ICatanModelObserver;
import client.models.IProxy;


/**
 * Domestic trade controller implementation
 */
public class DomesticTradeController extends Controller implements IDomesticTradeController, ICatanModelObserver {

	private IDomesticTradeOverlay tradeOverlay;
	private IWaitView waitOverlay;
	private IAcceptTradeOverlay acceptOverlay;
	private IProxy proxy;

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

		getTradeOverlay().showModal();
	}

	@Override
	public void decreaseResourceAmount(ResourceType resource) {

	}

	@Override
	public void increaseResourceAmount(ResourceType resource) {

	}

	@Override
	public void sendTradeOffer() {

		getTradeOverlay().closeModal();
//		getWaitOverlay().showModal();
	}

	@Override
	public void setPlayerToTradeWith(int playerIndex) {

	}

	@Override
	public void setResourceToReceive(ResourceType resource) {

	}

	@Override
	public void setResourceToSend(ResourceType resource) {

	}

	@Override
	public void unsetResource(ResourceType resource) {

	}

	@Override
	public void cancelTrade() {

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

