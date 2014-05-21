package client.discard;

import java.util.HashMap;
import java.util.Map;

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
		this.initializeMessage();
		this.initializeButtons();
	}
	
	private void initializeButtons()
	{
		for(ResourceType type : ResourceType.values())
		{
			this.getDiscardView().setResourceAmountChangeEnabled(type, false, false);
		}
	}
	
	private void initializeMessage()
	{
		discardEnabled = false;
		buttonMessage = new StringBuilder();
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
	public void increaseAmount(ResourceType resource)
	{
		Map<IResourceCard, Integer> playerMap = player.getResourceCards();
	}

	/**
	 * This method is called when the user decreases the amount of the specified
	 * resource.
	 *
	 * @param resource
	 *            The resource that was decreased
	 */
	@Override
	public void decreaseAmount(ResourceType resource)
	{
		Map<IResourceCard, Integer> playerMap = player.getResourceCards();
	}

	/**
	 * This method is called when the user clicks the discard button.
	 */
	@Override
	public void discard()
	{
		
		getDiscardView().closeModal();
		this.initialize();
	}
	
	private void updateView()
	{
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
		IFacade facade = proxy.getFacade();
		try
		{
			player = facade.getCurrentUser();
		}
		catch(CantFindGameModelException | CantFindPlayerException e)
		{
			e.printStackTrace();
		}
		updateView();
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

