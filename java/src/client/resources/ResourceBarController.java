package client.resources;

import java.util.*;

import client.base.*;
import client.models.*;
import client.models.exceptions.CantFindGameModelException;
import client.models.exceptions.CantFindPlayerException;
import shared.definitions.ResourceType;


/**
 * Implementation for the resource bar controller
 */
public class ResourceBarController extends Controller implements IResourceBarController, ICatanModelObserver {

	private Map<ResourceBarElement, IAction> elementActions;
    private static final int MAX_ROADS = 15;
    private static final int MAX_SETTLEMENTS = 5;
    private static final int MAX_CITIES = 4;
    private IProxy proxy;
	
	public ResourceBarController(IResourceBarView view, IProxy proxy) {

		super(view);
        this.proxy = proxy;
		
		elementActions = new HashMap<>();
        this.proxy.getFacade().registerAsObserver(this);
	}

	@Override
	public IResourceBarView getView() {
		return (IResourceBarView)super.getView();
	}

	/**
	 * Sets the action to be executed when the specified resource bar element is clicked by the user
	 * 
	 * @param element The resource bar element with which the action is associated
	 * @param action The action to be executed
	 */
	public void setElementAction(ResourceBarElement element, IAction action) {

		elementActions.put(element, action);
	}

	@Override
	public void buildRoad() {
		executeElementAction(ResourceBarElement.ROAD);
	}

	@Override
	public void buildSettlement() {
		executeElementAction(ResourceBarElement.SETTLEMENT);
	}

	@Override
	public void buildCity() {
		executeElementAction(ResourceBarElement.CITY);
	}

	@Override
	public void buyCard() {
		executeElementAction(ResourceBarElement.BUY_CARD);
	}

	@Override
	public void playCard() {
		executeElementAction(ResourceBarElement.PLAY_CARD);
	}
	
	private void executeElementAction(ResourceBarElement element) {
		
		if (elementActions.containsKey(element)) {
			
			IAction action = elementActions.get(element);
			action.execute();
		}
	}
	
	private void setCityButtonState() {
		
		try {
			
			Collection<Resource> buildingCost = City.getResourceCost();
			boolean canBuild = true;
			
			for(Resource resource : buildingCost) {
				ResourceType type = resource.getResourceType();
				Integer cost = resource.getAmount();
				
				if(this.proxy.getFacade().getPlayerResourceCount(ResourceCard.getCardForType(type)) < cost)
					canBuild = false;
				
			}

			if(this.proxy.getFacade().getCurrentUser().getSettlements().size() >= MAX_CITIES)
				canBuild = false;
			
			getView().setElementEnabled(ResourceBarElement.CITY, canBuild);
			
		} catch (CantFindGameModelException | CantFindPlayerException e) {
			e.printStackTrace();
		}
	}
	
	private void setSettlementButtonState() {

		try {
			
			Collection<Resource> buildingCost = Settlement.getResourceCost();
			boolean canBuild = true;
			
			for(Resource resource : buildingCost) {
				ResourceType type = resource.getResourceType();
				Integer cost = resource.getAmount();
				
				if(this.proxy.getFacade().getPlayerResourceCount(ResourceCard.getCardForType(type)) < cost)
					canBuild = false;
				
			}
			
			if(this.proxy.getFacade().getCurrentUser().getSettlements().size() >= MAX_SETTLEMENTS)
				canBuild = false;
			
			getView().setElementEnabled(ResourceBarElement.SETTLEMENT, canBuild);
			
		} catch (CantFindGameModelException | CantFindPlayerException e) {
			e.printStackTrace();
		}
	}
	
	private void setRoadButtonState() {
		try {
			
			Collection<Resource> buildingCost = RoadSegment.getResourceCost();
			boolean canBuild = true;
			
			for(Resource resource : buildingCost) {
				ResourceType type = resource.getResourceType();
				Integer cost = resource.getAmount();
				
				if(this.proxy.getFacade().getPlayerResourceCount(ResourceCard.getCardForType(type)) < cost)
					canBuild = false;
				
			}
	
			if(this.proxy.getFacade().getCurrentUser().getRoads().size() >= MAX_ROADS)
				canBuild = false;
	
			getView().setElementEnabled(ResourceBarElement.ROAD, canBuild);
			
		} catch (CantFindGameModelException | CantFindPlayerException e) {
			e.printStackTrace();
		}
		
	}
	
	private void setBuyDevCardButtonState() {
		try {
			
			Collection<Resource> buildingCost = DevelopmentCard.getResourceCost();
			boolean canPlay = true;
			
			for(Resource resource : buildingCost) {
				ResourceType type = resource.getResourceType();
				Integer cost = resource.getAmount();
				
				if(this.proxy.getFacade().getPlayerResourceCount(ResourceCard.getCardForType(type)) < cost)
					canPlay = false;
				
			}

			if( ! this.proxy.getFacade().isMyTurn())
				canPlay = false;
	
			getView().setElementEnabled(ResourceBarElement.BUY_CARD, canPlay);
			
		} catch (CantFindGameModelException e) {
			e.printStackTrace();
		}
	}

    private void setPlayDevCardButtonState() {
        try {
            getView().setElementEnabled(ResourceBarElement.PLAY_CARD, this.proxy.getFacade().isMyTurn());
        } catch (CantFindGameModelException e) {
            e.printStackTrace();
        }
    }

    private void updateQuantities()  {

        try {


            getView().setElementAmount(ResourceBarElement.WOOD, this.proxy.getFacade().getPlayerResourceCount(ResourceCard.WOOD));
            getView().setElementAmount(ResourceBarElement.BRICK, this.proxy.getFacade().getPlayerResourceCount(ResourceCard.BRICK));
            getView().setElementAmount(ResourceBarElement.WHEAT, this.proxy.getFacade().getPlayerResourceCount(ResourceCard.WHEAT));
            getView().setElementAmount(ResourceBarElement.SHEEP, this.proxy.getFacade().getPlayerResourceCount(ResourceCard.SHEEP));
            getView().setElementAmount(ResourceBarElement.ORE, this.proxy.getFacade().getPlayerResourceCount(ResourceCard.ORE));

            getView().setElementAmount(ResourceBarElement.SOLDIERS, this.proxy.getFacade().getCurrentUser().getSoldiers());
            getView().setElementAmount(ResourceBarElement.CITY, MAX_CITIES - this.proxy.getFacade().getCurrentUser().getCities().size());
            getView().setElementAmount(ResourceBarElement.SETTLEMENT, MAX_SETTLEMENTS - this.proxy.getFacade().getCurrentUser().getSettlements().size());
            getView().setElementAmount(ResourceBarElement.ROAD, MAX_ROADS - this.proxy.getFacade().getCurrentUser().getRoads().size());

        }
        catch (CantFindPlayerException | CantFindGameModelException e) {
                e.getStackTrace();
        }
    }

    @Override
    public void update() {

        updateQuantities();

        setCityButtonState();
        setSettlementButtonState();
        setRoadButtonState();
        setBuyDevCardButtonState();

        setPlayDevCardButtonState();

    }
}

