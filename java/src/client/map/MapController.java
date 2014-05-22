package client.map;

import java.util.*;

import shared.definitions.*;
import shared.locations.*;
import client.base.*;
import client.data.*;
import client.models.City;
import client.models.ICatanMap;
import client.models.ICatanModelObserver;
import client.models.ICity;
import client.models.IFacade;
import client.models.IGame;
import client.models.IHex;
import client.models.IPlayer;
import client.models.IPort;
import client.models.IProxy;
import client.models.IRoadSegment;
import client.models.IRobber;
import client.models.ISettlement;
import client.models.InvalidLocationException;
import client.models.RoadSegment;
import client.models.Settlement;
import client.models.exceptions.CantFindGameModelException;
import client.models.exceptions.CantFindPlayerException;

/**
 * Implementation for the map controller.
 *
 * Okay.  So here are some important notes about the x-y system that
 * Dr. Rodham is using in his MapView/MapComponent/MapController.
 *
 * The center Hex is located at x=0, y=0 and radiates outward from
 * there. The column in the center is the y-axis.  The diagonal that
 * goes from the top left corner to the bottom right corner is the
 * x-axis.  The positive y-axis is DOWNWARD.  The positive x-axis is
 * RIGHTWARD.
 *
 *
 * @author Craig Call
 *
 */
@SuppressWarnings({"unused"})
public class MapController extends Controller implements IMapController,
														 ICatanModelObserver
{
	private IRobView robView;
	private IProxy proxy;

	public MapController(IMapView view, IRobView robView)
	{
		super(view);

		setRobView(robView);
		
//		this.defaultInit();
	}
	
	public void setProxy(IProxy proxy)
	{
		this.proxy = proxy;
		proxy.getFacade().registerAsObserver(this);
	}

	public IMapView getView()
	{
		return (IMapView)super.getView();
	}

	private IRobView getRobView()
	{
		return robView;
	}
	
	private void setRobView(IRobView robView)
	{
		this.robView = robView;
	}

	// THIS NEEDS TO BE UPDATED WITH THE REAL THING.
	protected void initFromModel()
	{
		try
		{
			IFacade facade = proxy.getFacade();
//			System.out.println(facade.getCurrentState());
			ICatanMap map = facade.getCatanMap();
			for(IHex hex : map.getHexes())
			{
//				System.out.println(hex.toString());
				this.getView().addHex(hex.getLocation(), hex.getHexType());
				if(hex.getHexNumber() != null)
					this.getView().addNumber(hex.getLocation(), hex.getHexNumber());
			}
			
			for(IPort port : map.getPorts())
			{
				this.getView().addPort((EdgeLocation)port.getLocation(),
				                       port.getPortType());
			}
			
			for(ISettlement settlement : map.getSettlements())
			{
//				System.out.println(settlement.toString());
				IPlayer player = settlement.getPlayer();
				PlayerInfo info = player.getPlayerInfo();
				CatanColor color = info.getColor();
				VertexLocation location = (VertexLocation)settlement.getLocation();
				
				this.getView().placeSettlement(location, color);
			}
			
			for(ICity city : map.getCities())
			{
				IPlayer player = city.getPlayer();
				PlayerInfo info = player.getPlayerInfo();
				CatanColor color = info.getColor();
				VertexLocation location = (VertexLocation)city.getLocation();
				
				this.getView().placeCity(location, color);
			}
			
			for(IRoadSegment segment : map.getRoads())
			{
				IPlayer player = segment.getPlayer();
				PlayerInfo info = player.getPlayerInfo();
				CatanColor color = info.getColor();
				EdgeLocation location = (EdgeLocation)segment.getLocation();
				
				this.getView().placeRoad(location, color);
			}
			
			IRobber robber = map.getRobber();
			this.getView().placeRobber(robber.getLocation().getHexLocation());
		}
		catch(CantFindGameModelException e)
		{
			e.printStackTrace();
		}

        try
        {
            //TODO add check to see if the overlay is already shown
            if(this.proxy.getFacade().isMyTurn() &&
               this.proxy.getFacade().getTurnTracker().getStatus().equals("Robbing"))
            {
                this.getView().startDrop(PieceType.ROBBER, null, false);
            }
        }
        catch (CantFindGameModelException e)
        {
            e.printStackTrace();
        }
    }
	
	private void defaultInit()
	{
		Random rand = new Random();

		for (int x = 0; x <= 3; ++x) {

			int maxY = 3 - x;
			for (int y = -3; y <= maxY; ++y) {
				int r = rand.nextInt(HexType.values().length);
				HexType hexType = HexType.values()[r];
				HexLocation hexLoc = new HexLocation(x, y);
				getView().addHex(hexLoc, hexType);
				getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.NorthWest),
						CatanColor.RED);
				getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.SouthWest),
						CatanColor.BLUE);
				getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.South),
						CatanColor.ORANGE);
				getView().placeSettlement(new VertexLocation(hexLoc,  VertexDirection.NorthWest), CatanColor.GREEN);
				getView().placeCity(new VertexLocation(hexLoc,  VertexDirection.NorthEast), CatanColor.PURPLE);
			}

			if (x != 0) {
				int minY = x - 3;
				for (int y = minY; y <= 3; ++y) {
					int r = rand.nextInt(HexType.values().length);
					HexType hexType = HexType.values()[r];
					HexLocation hexLoc = new HexLocation(-x, y);
					getView().addHex(hexLoc, hexType);
					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.NorthWest),
							CatanColor.RED);
					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.SouthWest),
							CatanColor.BLUE);
					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.South),
							CatanColor.ORANGE);
					getView().placeSettlement(new VertexLocation(hexLoc,  VertexDirection.NorthWest), CatanColor.GREEN);
					getView().placeCity(new VertexLocation(hexLoc,  VertexDirection.NorthEast), CatanColor.PURPLE);
				}
			}
		}

		PortType portType = PortType.BRICK;
		getView().addPort(new EdgeLocation(new HexLocation(0, 3), EdgeDirection.North), portType);
		getView().addPort(new EdgeLocation(new HexLocation(0, -3), EdgeDirection.South), portType);
		getView().addPort(new EdgeLocation(new HexLocation(-3, 3), EdgeDirection.NorthEast), portType);
		getView().addPort(new EdgeLocation(new HexLocation(-3, 0), EdgeDirection.SouthEast), portType);
		getView().addPort(new EdgeLocation(new HexLocation(3, -3), EdgeDirection.SouthWest), portType);
		getView().addPort(new EdgeLocation(new HexLocation(3, 0), EdgeDirection.NorthWest), portType);

		getView().placeRobber(new HexLocation(0, 0));

		getView().addNumber(new HexLocation(-2, 0), 2);
		getView().addNumber(new HexLocation(-2, 1), 3);
		getView().addNumber(new HexLocation(-2, 2), 4);
		getView().addNumber(new HexLocation(-1, 0), 5);
		getView().addNumber(new HexLocation(-1, 1), 6);
		getView().addNumber(new HexLocation(1, -1), 8);
		getView().addNumber(new HexLocation(1, 0), 9);
		getView().addNumber(new HexLocation(2, -2), 10);
		getView().addNumber(new HexLocation(2, -1), 11);
		getView().addNumber(new HexLocation(2, 0), 12);
	}

	public boolean canPlaceRoad(EdgeLocation edgeLoc)
	{
		IRoadSegment segment = new RoadSegment();
		IFacade facade = this.proxy.getFacade();
		ICatanMap map = null;
		try
		{
			segment.setLocation(edgeLoc);
			segment.setPlayer(facade.getCurrentUser());
			map = facade.getCatanMap();
		}
		catch(CantFindGameModelException | CantFindPlayerException e)
		{
			e.printStackTrace();
		}
		
		if(map != null)
			return map.canPlaceRoad(segment);
		else
			return false;
	}

	public boolean canPlaceSettlement(VertexLocation vertLoc)
	{
		IPlayer currentPlayer = null;
		try {
			currentPlayer = this.proxy.getFacade().getCurrentUser();
		} catch (CantFindGameModelException | CantFindPlayerException e) {
			e.printStackTrace();
		}
		ISettlement settlement = new Settlement(vertLoc, currentPlayer);
		
		IFacade facade = this.proxy.getFacade();
		ICatanMap map = null;
		try
		{
			map = facade.getCatanMap();
		}
		catch(CantFindGameModelException e)
		{
			e.printStackTrace();
		}
		
		if(map != null)
			return map.canPlaceSettlement(settlement);
		else
			return false;
	}

	public boolean canPlaceCity(VertexLocation vertLoc)
	{
		IPlayer currentPlayer = null;
		try {
			currentPlayer = this.proxy.getFacade().getCurrentUser();
		} catch (CantFindGameModelException | CantFindPlayerException e) {
			e.printStackTrace();
		}
		ICity city = new City(vertLoc, currentPlayer);
		
		IFacade facade = this.proxy.getFacade();
		ICatanMap map = null;
		try
		{
			map = facade.getCatanMap();
		}
		catch(CantFindGameModelException e)
		{
			e.printStackTrace();
		}
		
		if(map != null)
			return map.canPlaceCity(city);
		else
			return false;
	}

	public boolean canPlaceRobber(HexLocation hexLoc)
	{
        HexLocation currentLocation = null;
        boolean isOceanHex = false;
        try {
            currentLocation = this.proxy.getFacade().getRobberLocation();
            isOceanHex = this.proxy.getFacade().getCatanMap().isOceanHex(hexLoc);

        } catch (CantFindGameModelException e) {
            e.printStackTrace();
        }

        return (( ! hexLoc.equals( currentLocation )) && ( ! isOceanHex ));
		
	}

	public void placeRoad(EdgeLocation edgeLoc)
	{
		try
		{
			IFacade facade = this.proxy.getFacade();
			IPlayer player = facade.getCurrentUser();
			ICatanMap map = facade.getCatanMap();
			PlayerInfo info = player.getPlayerInfo();
			
			getView().placeRoad(edgeLoc, info.getColor());
		
    		IRoadSegment segment = new RoadSegment();
    		segment.setLocation(edgeLoc);
    		segment.setPlayer(player);
    		
			map.placeRoadSegment(segment);
		}
		catch (InvalidLocationException | CantFindGameModelException | CantFindPlayerException e)
		{
			e.printStackTrace();
		}
	}

	public void placeSettlement(VertexLocation vertLoc)
	{
		try
		{
			IFacade facade = this.proxy.getFacade();
			IPlayer player = facade.getCurrentUser();
			ICatanMap map = facade.getCatanMap();
			PlayerInfo info = player.getPlayerInfo();
			
			getView().placeSettlement(vertLoc, info.getColor());
			
			ISettlement settlement = new Settlement(vertLoc, player);
			
			map.placeSettlement(settlement);
		}
		catch(InvalidLocationException | CantFindGameModelException | CantFindPlayerException e)
		{
			e.printStackTrace();
		}
	}

	public void placeCity(VertexLocation vertLoc)
	{
		try
		{
			IFacade facade = this.proxy.getFacade();
			IPlayer player = facade.getCurrentUser();
			ICatanMap map = facade.getCatanMap();
			PlayerInfo info = player.getPlayerInfo();
			
			getView().placeCity(vertLoc, info.getColor());
			
			ICity city = new City(vertLoc, player);
			
			map.placeCity(city);
		}
		catch(InvalidLocationException | CantFindGameModelException | CantFindPlayerException e)
		{
			e.printStackTrace();
		}
	}

	public void placeRobber(HexLocation hexLoc)
	{
        getView().placeRobber(hexLoc);
        IFacade facade = this.proxy.getFacade();

        HashSet<RobPlayerInfo> robbable = new HashSet<>();

        try {

            for( IPlayer player : facade.getCatanMap().getPlayersAroundHex(hexLoc) )
            {
                RobPlayerInfo rob = new RobPlayerInfo(player.getPlayerInfo());
                int playerId = player.getPlayerInfo().getId();

                rob.setNumCards( facade.getResourcesForPlayerId( playerId ).size() );
                robbable.add(rob);

            }

        } catch (CantFindGameModelException | CantFindPlayerException e) {
            e.printStackTrace();
        }

        RobPlayerInfo[] victims = robbable.toArray(new RobPlayerInfo[robbable.size()]);

        getRobView().setPlayers(victims);
        getRobView().showModal();

    }

	public void startMove(PieceType pieceType,
	                      boolean isFree,
	                      boolean allowDisconnected)
	{
		IFacade facade = this.proxy.getFacade();
		try
		{
			IPlayer player = facade.getCurrentUser();
			PlayerInfo info = player.getPlayerInfo();
			getView().startDrop(pieceType, info.getColor(), true);
		}
		catch(CantFindGameModelException | CantFindPlayerException e)
		{
			e.printStackTrace();
		}
	}

	public void cancelMove()
	{
		
	}

	public void playSoldierCard()
	{
		
	}

	public void playRoadBuildingCard()
	{

	}

	public void robPlayer(RobPlayerInfo victim)
	{
        getRobView().showModal();
	}

	@Override
	public void update()
	{
		try
        {
            initFromModel();
		}
        catch(Exception e)
        {
            System.out.println("Error initializing Map from Model");
        }
	}

}

