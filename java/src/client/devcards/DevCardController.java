package client.devcards;

import java.util.Map;

import shared.definitions.ResourceType;
import client.base.Controller;
import client.base.IAction;
import client.models.exceptions.CantFindGameModelException;
import client.models.exceptions.CantFindPlayerException;
import client.models.interfaces.ICatanModelObserver;
import client.models.interfaces.IDevelopmentCard;
import client.models.interfaces.IProxy;
import client.server.BuyDevCard;
import client.server.ServerMonopoly;
import client.server.ServerMonument;
import client.server.ServerYearofPlenty;


/**
 * "Dev card" controller implementation
 */
public class DevCardController extends Controller implements IDevCardController, ICatanModelObserver {

	private IBuyDevCardView buyCardView;
	private IAction soldierAction;
	private IAction roadAction;
	private IProxy proxy;
	
	/**
	 * DevCardController constructor
	 * 
	 * @param view "Play dev card" view
	 * @param buyCardView "Buy dev card" view
	 * @param soldierAction Action to be executed when the user plays a soldier card.  It calls "mapController.playSoldierCard()".
	 * @param roadAction Action to be executed when the user plays a road building card.  It calls "mapController.playRoadBuildingCard()".
	 */
	public DevCardController(IPlayDevCardView view, IBuyDevCardView buyCardView, 
								IAction soldierAction, IAction roadAction, IProxy proxy) {

		super(view);
		this.proxy = proxy;
		this.buyCardView = buyCardView;
		this.soldierAction = soldierAction;
		this.roadAction = roadAction;
		this.proxy.getFacade().registerAsObserver(this);
	}

	public IPlayDevCardView getPlayCardView() {
		return (IPlayDevCardView)super.getView();
	}

	public IBuyDevCardView getBuyCardView() {
		return buyCardView;
	}

	@Override
	public void startBuyCard() {
		
		getBuyCardView().showModal();
	}

	@Override
	public void cancelBuyCard() {
		
		getBuyCardView().closeModal();
	}

	@Override
	public void buyCard() {
		try {
			BuyDevCard devCard = new BuyDevCard("buyDevCard",this.proxy.getFacade().getCurrentUserIndex().intValue());
			this.proxy.movesBuyDevCard(devCard);
		} catch (CantFindGameModelException e) {
			e.printStackTrace();
			System.out.println("problem buying dev card");
		}
		getBuyCardView().closeModal();
	}

	@Override
	public void startPlayCard() {
		try {
			Map<IDevelopmentCard, Integer> map = this.proxy.getFacade().getDevCardsForPlayerIndex(this.proxy.getFacade().getCurrentUserIndex());
			System.out.println(map.toString());
			for(Map.Entry<IDevelopmentCard, Integer> entry : map.entrySet()){
				System.out.println(entry.toString());
				getPlayCardView().setCardEnabled(entry.getKey().getType(), (entry.getValue().intValue() > 0));
				getPlayCardView().setCardAmount(entry.getKey().getType(), entry.getValue());
			}
			getPlayCardView().showModal();
		} catch (CantFindPlayerException e) {
			e.printStackTrace();
		} catch (CantFindGameModelException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void cancelPlayCard() {

		getPlayCardView().closeModal();
	}

	@Override
	public void playMonopolyCard(ResourceType resource) {
		try {
			ServerMonopoly sm = new ServerMonopoly("Monopoly",resource.toString().toLowerCase(),this.proxy.getFacade().getCurrentUserIndex().intValue());
			if(this.proxy.movesMonopoly(sm).getResponseCode() != 200)
				System.out.println("error playing monopoly card");
		} catch (CantFindGameModelException e) {
			System.out.println("error playing monopoly card");
			e.printStackTrace();
		}
	}

	@Override
	public void playMonumentCard() {
		try {
			ServerMonument sm = new ServerMonument("Monument",this.proxy.getFacade().getCurrentUserIndex().intValue());
			if(this.proxy.movesMonument(sm).getResponseCode() != 200)
				System.out.println("error playing monument card");
		} catch (CantFindGameModelException e) {
			System.out.println("error playing monument card");
			e.printStackTrace();
		}
	}

	@Override
	public void playRoadBuildCard() {
//		getPlayCardView().closeModal();
		roadAction.execute();
	}

	@Override
	public void playSoldierCard() {
//		getPlayCardView().closeModal();
		soldierAction.execute();
	}

	@Override
	public void playYearOfPlentyCard(ResourceType resource1, ResourceType resource2) {
		try {
			ServerYearofPlenty yop = new ServerYearofPlenty("Year_of_Plenty",this.proxy.getFacade().getCurrentUserIndex().intValue(), resource1.toString().toLowerCase(),resource2.toString().toLowerCase());
			if(this.proxy.movesYear_of_Plenty(yop).getResponseCode() != 200)
				System.out.println("error playing year of plenty card");
		} catch (CantFindGameModelException e) {
			System.out.println("error playing year of plenty card");
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		try {
			Map<IDevelopmentCard, Integer> map = this.proxy.getFacade().getDevCardsForPlayerIndex(this.proxy.getFacade().getCurrentUser().getPlayerInfo().getPlayerIndex());
			for(Map.Entry<IDevelopmentCard, Integer> entry : map.entrySet()){
				getPlayCardView().setCardEnabled(entry.getKey().getType(), (entry.getValue().intValue() > 0));
				getPlayCardView().setCardAmount(entry.getKey().getType(), entry.getValue());
			}
		} catch (CantFindPlayerException e) {
			e.printStackTrace();
		} catch (CantFindGameModelException e) {
			e.printStackTrace();
		}
		
	}

}

