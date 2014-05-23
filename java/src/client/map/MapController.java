package client.map;

import java.util.*;

import client.server.ServerRobPlayer;
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
import client.models.IPiece;
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
import client.server.ServerBuildCity;
import client.server.ServerBuildRoad;
import client.server.ServerBuildSettlement;

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
    private int playedRoadCard = 0;
    private HexLocation robberLocCache;

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
			ICatanMap map = facade.getCatanMap();
			for(IHex hex : map.getHexes())
			{
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
            System.out.println(robber.getLocation().getHexLocation());
		}
		catch(CantFindGameModelException e)
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

	/**
	 * This method is called whenever the user is trying to place a road on the
	 * map. It is called by the view for each "mouse move" event. The returned
	 * value tells the view whether or not to allow the road to be placed at the
	 * specified location.
	 *
	 * @param edgeLoc
	 *            The proposed road location
	 * @return true if the road can be placed at edgeLoc, false otherwise
	 */
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

	/**
	 * This method is called whenever the user is trying to place a settlement
	 * on the map. It is called by the view for each "mouse move" event. The
	 * returned value tells the view whether or not to allow the settlement to
	 * be placed at the specified location.
	 *
	 * @param vertLoc
	 *            The proposed settlement location
	 * @return true if the settlement can be placed at vertLoc, false otherwise
	 */
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

	/**
	 * This method is called whenever the user is trying to place a city on the
	 * map. It is called by the view for each "mouse move" event. The returned
	 * value tells the view whether or not to allow the city to be placed at the
	 * specified location.
	 *
	 * @param vertLoc
	 *            The proposed city location
	 * @return true if the city can be placed at vertLoc, false otherwise
	 */
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

	/**
	 * This method is called whenever the user is trying to place the robber on
	 * the map. It is called by the view for each "mouse move" event. The
	 * returned value tells the view whether or not to allow the robber to be
	 * placed at the specified location.
	 *
	 * @param hexLoc
	 *            The proposed robber location
	 * @return true if the robber can be placed at hexLoc, false otherwise
	 */
	public boolean canPlaceRobber(HexLocation hexLoc)
	{
        HexLocation currentLocation = null;
        boolean isOceanHex = false;
        try {
      	  IFacade facade = this.proxy.getFacade();
           currentLocation = facade.getRobberLocation();
           isOceanHex = facade.getCatanMap().isOceanHex(hexLoc);

        } catch (CantFindGameModelException e) {
            e.printStackTrace();
        }

        return (( ! hexLoc.equals( currentLocation )) && ( ! isOceanHex ));
	}

	/**
	 * This method is called when the user clicks the mouse to place a road.
	 *
	 * @param edgeLoc
	 *            The road location
	 */
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

			if(!facade.getCurrentState().equals("FirstRound") && !facade.getCurrentState().equals("SecondRound"))
				proxy.movesBuildRoad(new ServerBuildRoad("buildRoad", info.getPlayerIndex(), edgeLoc, true));
		}
		catch (InvalidLocationException | CantFindGameModelException | CantFindPlayerException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * This method is called when the user clicks the mouse to place a
	 * settlement.
	 *
	 * @param vertLoc
	 *            The settlement location
	 */
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

			if(!facade.getCurrentState().equals("FirstRound") && !facade.getCurrentState().equals("SecondRound"))
				proxy.movesBuildSettlement(new ServerBuildSettlement("buildSettlement", info.getPlayerIndex(), vertLoc, true));
		}
		catch(InvalidLocationException | CantFindGameModelException | CantFindPlayerException e)
		{
			e.printStackTrace();
		}
	}

	/**" + i
	 * This method is called when the user clicks the mouse to place a city.
	 *
	 * @param vertLoc
	 *            The city location
	 */
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

			if(!facade.getCurrentState().equals("FirstRound") && !facade.getCurrentState().equals("SecondRound"))
				proxy.movesBuildCity(new ServerBuildCity("buildCity", info.getPlayerIndex(), vertLoc));
		}
		catch(InvalidLocationException | CantFindGameModelException | CantFindPlayerException e)
		{
			e.printStackTrace();
		}
	}

	/**https://github.com/cameronmccord2/cs340
	 * This method is called when the user clicks the mouse to place the robber.
	 *
	 * @param hexLoc
	 *            The robber location
	 */
	public void placeRobber(HexLocation hexLoc)
	{
        // Save the new robber location for later (after the user decides who to rob from)
        robberLocCache = hexLoc;

        getView().placeRobber(hexLoc);
        IFacade facade = this.proxy.getFacade();

        HashSet<RobPlayerInfo> robbable = new HashSet<>();

        try {

            for( IPiece piece : facade.getCatanMap().getSettlementsAroundHex(hexLoc) )
            {
                if(piece.getPlayer() == facade.getCurrentUser())
                        continue;
                RobPlayerInfo rob = new RobPlayerInfo(piece.getPlayer().getPlayerInfo());
                rob.setNumCards( piece.getPlayer().getNumResourceCards() );
                robbable.add(rob);
            }

        } catch (CantFindGameModelException | CantFindPlayerException e) {
            e.printStackTrace();
        }

        RobPlayerInfo[] victims = robbable.toArray(new RobPlayerInfo[robbable.size()]);

        getRobView().setPlayers(victims);
        getRobView().showModal();

    }

	/**
	 * This method is called when the user requests to place a piece on the map
	 * (road, city, or settlement)
	 *
	 * @param pieceType
	 *            The type of piece to be placed
	 * @param isFree
	 *            true" + i if the piece should not cost the player resources, false
	 *            otherwise. Set to true during initial setup and when a road
	 *            building card is played.
	 * @param allowDisconnected
	 *            true if the piece can be disconnected, false otherwise. Set to
	 *            true only during initial setup.
	 */
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

	/**
	 * This method is called from the modal map overlay when the cancel button
	 * is pressed.
	 */
	public void cancelMove()
	{

	}

	/**
	 * This method is called when the user plays a "soldier" development card.
	 * It should initiate robber placement.
	 */
	public void playSoldierCard()
	{

	}

	/**
	 * This method is called when the user plays a "road building" progress
	 * development card. It should initiate the process of allowing the player
	 * to place two roads.
	 */
	public void playRoadBuildingCard()
	{
        this.playedRoadCard = 2;
        CatanColor color = null;
        try {
            IFacade facade = this.proxy.getFacade();
            IPlayer player = facade.getCurrentUser();
            color = player.getPlayerInfo().getColor();
        } catch (CantFindGameModelException | CantFindPlayerException e) {
            e.printStackTrace();
        }

        while (playedRoadCard > 0) {

            this.getView().startDrop(PieceType.ROAD, color, false);

            playedRoadCard--;

        }
	}

	/**
	 * This method is called by the Rob View when a player to rob is selected
	 * via a button click.
	 *
	 * @param victim
	 *            The player to be robbed
	 */
	public void robPlayer(RobPlayerInfo victim)
	{
        try {

            IFacade facade = this.proxy.getFacade();
            IPlayer player = facade.getCurrentUser();
            int playerIndex = player.getPlayerInfo().getPlayerIndex();

            ServerRobPlayer robData = new ServerRobPlayer("robPlayer",playerIndex, victim.getPlayerIndex(), robberLocCache);
            proxy.moveRobPlayer(robData);

        } catch (CantFindGameModelException | CantFindPlayerException e) {
            e.printStackTrace();
        }


    }

    private void initRobber() {

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

	@Override
	public void update()
	{
		try
        {
            initFromModel();
            initRobber();
		}
        catch(Exception e)
        {
            System.out.println("Error initializing Map Data from Model");
        }
	}

}

