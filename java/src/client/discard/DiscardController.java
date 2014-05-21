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
		this.initializeMap();
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
		this.initializeMap();
	}
	
	private void updateView()
	{
		
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

