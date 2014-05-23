package client.discard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import shared.definitions.*;
import client.base.*;
import client.data.PlayerInfo;
import client.misc.*;
import client.models.ICatanModelObserver;
import client.models.IFacade;
import client.models.IPlayer;
import client.models.IProxy;
import client.models.IResourceCard;
import client.models.Resource;
import client.models.ResourceCard;
import client.models.exceptions.CantFindGameModelException;
import client.models.exceptions.CantFindPlayerException;
import client.models.translator.TRResourceList;
import client.server.DiscardedCards;


/**
 * Discard controller implementation
 */
@SuppressWarnings({"unused"})
public class DiscardController extends Controller implements IDiscardController,
															 ICatanModelObserver
{
	private IWaitView waitView;
	private IProxy proxy;
	private PlayerInfo info;
	private IPlayer player;
	private Map<ResourceType, Integer> resourceSelection;
	private Integer amountToDiscard;
	private StringBuilder buttonMessage;
	private boolean discardEnabled;
	private Integer selectedAmountTotal;

	private boolean finishedDiscarding;

	/**
	 * DiscardController constructor
	 *
	 * @param view View displayed to let the user select cards to discard
	 * @param waitView View displayed to notify the user that they are waiting for other players to discard
	 */
	public DiscardController(IDiscardView view, IWaitView waitView)
	{
		super(view);

		this.waitView = waitView;
		this.initialize();
	}

	private void initialize()
	{
		this.initializeMap();
		this.initializeValues();
		this.initializeButtons();
	}

	private void initializeButtons()
	{
		for(ResourceType type : ResourceType.values())
		{
			this.getDiscardView().setResourceAmountChangeEnabled(type, false, false);
		}
	}

	private void initializeValues()
	{
		discardEnabled = false;
		buttonMessage = new StringBuilder();
		finishedDiscarding = false;
	}

	private void initializeMap()
	{
		resourceSelection = new HashMap<>();
		resourceSelection.put(ResourceType.BRICK, 0);
		resourceSelection.put(ResourceType.WOOD, 0);
		resourceSelection.put(ResourceType.WHEAT, 0);
		resourceSelection.put(ResourceType.SHEEP, 0);
		resourceSelection.put(ResourceType.ORE, 0);
	}

	public void setProxy(IProxy proxy)
	{
		this.proxy = proxy;
		proxy.getFacade().registerAsObserver(this);
	}

	public IDiscardView getDiscardView()
	{
		return (IDiscardView)super.getView();
	}

	public IWaitView getWaitView()
	{
		return waitView;
	}

	/**
	 * This method is called when the user increases the amount of the specified
	 * resource.
	 *
	 * @param resource
	 *            The resource that was increased
	 */
	@Override
	public void increaseAmount(ResourceType type)
	{
		Set<Resource> resources = this.getResourcesFromPlayer();
		Integer amount = resourceSelection.get(type);
		resourceSelection.put(type, amount+1);
		updateView();
	}

	/**
	 * This method is called when the user decreases the amount of the specified
	 * resource.
	 *
	 * @param resource
	 *            The resource that was decreased
	 */
	@Override
	public void decreaseAmount(ResourceType type)
	{
		Set<Resource> resources = this.getResourcesFromPlayer();
		Integer amount = resourceSelection.get(type);
		resourceSelection.put(type, amount-1);
		updateView();
	}

	/**
	 * This method is called when the user clicks the discard button.
	 */
	@Override
	public void discard()
	{
		try
		{
   		if(getDiscardView().isModalShowing())
   			getDiscardView().closeModal();
   		// I need to calculate and update the amount of each resource
   		// and send it back to the server.
   		finishedDiscarding = true;

   		IFacade facade = this.proxy.getFacade();
   		IPlayer player = facade.getCurrentUser();
   		PlayerInfo info = player.getPlayerInfo();

   		String type = "discardCards";
   		Integer playerIndex = info.getPlayerIndex();

   		TRResourceList list = new TRResourceList();
   		list.setBrick(resourceSelection.get(ResourceType.BRICK));
   		list.setOre(resourceSelection.get(ResourceType.ORE));
   		list.setSheep(resourceSelection.get(ResourceType.SHEEP));
   		list.setWheat(resourceSelection.get(ResourceType.WHEAT));
   		list.setWood(resourceSelection.get(ResourceType.WOOD));

   		DiscardedCards cards = new DiscardedCards(type, playerIndex, list);
   		proxy.movesDiscardCards(cards);


   		this.initialize();
		}
		catch(CantFindGameModelException | CantFindPlayerException e)
		{
			e.printStackTrace();
		}
	}

	private Set<Resource> getResourcesFromPlayer()
	{
		Map<IResourceCard, Integer> playerMap = player.getResourceCards();
		Set<Resource> resources = new HashSet<>();
		for(Map.Entry<IResourceCard, Integer> cardEntry : playerMap.entrySet())
		{
			IResourceCard card = cardEntry.getKey();
			Integer amount = cardEntry.getValue();
			resources.add(new Resource(card.getType(), amount));
		}
		return resources;
	}

	private int getTotalResourceAmount()
	{
		int amount = 0;
		Set<Resource> resources = this.getResourcesFromPlayer();

		for(Resource resource : resources)
			amount += resource.getAmount();

		return amount;
	}

	private int getAmountToDiscard()
	{
		int amount = this.getTotalResourceAmount();

		if(amount <= 7)
			return 0;
		else
			return amount/2;
	}

	private Integer getSelectedAmountTotal()
	{
		Integer total = 0;
		for(Integer amount : resourceSelection.values())
			total += amount;
		return total;
	}

	private void updateDisplayValues()
	{
		this.amountToDiscard = this.getAmountToDiscard();
		this.selectedAmountTotal = this.getSelectedAmountTotal();

		buttonMessage = new StringBuilder();
		buttonMessage.append(this.selectedAmountTotal);
		buttonMessage.append("/");
		buttonMessage.append(this.amountToDiscard);
	}

	private void updateBooleanValues()
	{
		this.discardEnabled = selectedAmountTotal.intValue() ==
							  amountToDiscard.intValue();
		Set<Resource> playerResources = this.getResourcesFromPlayer();
		for(Resource resource : playerResources)
		{
			ResourceType type = resource.getResourceType();
			int discardAmount = resourceSelection.get(type);

//			boolean increase = false;
//			boolean decrease = false;
//			if(discardAmount < resource.getAmount())
//				increase = true;
//			if(discardAmount > 0)
//				decrease = true;

			boolean increase = discardAmount < resource.getAmount() &&
							   	 !discardEnabled;
			boolean decrease = discardAmount > 0;

			getDiscardView().setResourceAmountChangeEnabled(type, increase, decrease);
		}
	}

	private void updateValues()
	{
		this.updateDisplayValues();
		this.updateBooleanValues();
	}

	private void updateView()
	{
		this.updateValues();

		this.getDiscardView().setStateMessage(buttonMessage.toString());
		this.getDiscardView().setDiscardButtonEnabled(discardEnabled);

		for(Map.Entry<IResourceCard, Integer> resource : player.getResourceCards().entrySet())
		{
			ResourceType type = resource.getKey().getType();
			int amount = resource.getValue();
			this.getDiscardView().setResourceMaxAmount(type, amount);
		}

		for(Map.Entry<ResourceType, Integer> resource : resourceSelection.entrySet())
		{
			this.getDiscardView().setResourceDiscardAmount(resource.getKey(),
			                                               resource.getValue());
		}
	}

	@Override
	public void update()
	{
		try
		{
			IFacade facade = proxy.getFacade();
			String status = facade.getCurrentState();
    		player = facade.getCurrentUser();

			if(status.equals("Discarding"))
			{
        		System.out.println(player);
        		
				int discardAmount = this.getAmountToDiscard();

				if(discardAmount == 0)
					finishedDiscarding = true;

				if(!finishedDiscarding)
				{
    				if(!getDiscardView().isModalShowing())
    					getDiscardView().showModal();

        			updateView();
				}
				else
				{
					if(!getWaitView().isModalShowing())
						getWaitView().showModal();
				}
			}
			else
			{
				finishedDiscarding = false;
				
				if(getWaitView().isModalShowing())
					getWaitView().closeModal();
			}
		}
		catch(CantFindGameModelException | CantFindPlayerException e)
		{
			e.printStackTrace();
		}
	}
}

/**
 * Used to enable or disable the discard button.
 *
 * @param enabled
 *            Whether or not the discard button should be enabled
 */
//void setDiscardButtonEnabled(boolean enabled);

/**
 * Sets the discard amount displayed for the specified resource.
 *
 * @param resource
 *            The resource for which the discard amount is being set
 * @param amount
 *            The new discard amount
 */
//void setResourceDiscardAmount(ResourceType resource, int amount);

/**
 * Sets the maximum amount displayed for the specified resource.
 *
 * @param resource
 *            The resource for which the maximum amount is being set
 * @param maxAmount
 *            The new maximum amount
 */
//void setResourceMaxAmount(ResourceType resource, int maxAmount);

/**
 * Used to specify whether or not the discard amount of the specified
 * resource can be increased and decreased. (The buttons for increasing or
 * decreasing the discard amount are only visible when the corresponding
 * operations are allowed.)
 *
 * @param resource
 *            The resource for which amount changes are being enabled or
 *            disabled
 * @param increase
 *            Whether or not the amount for the specified resource can be
 *            increased
 * @param decrease
 *            Whether or not the amount for the specified resource can be
 *            decreased
 */
//void setResourceAmountChangeEnabled(ResourceType resource,
//									boolean increase, boolean decrease);

/**
 * Sets the state message, which indicates how many cards a player has set
 * to discard, and how many remain to set.
 *
 * @param message
 *            The new state message (e.g., "0/6")
 */
//void setStateMessage(String message);

