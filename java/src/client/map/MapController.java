package client.map;

import java.util.*;

import shared.definitions.*;
import shared.locations.*;
import client.base.*;
import client.data.*;
import client.models.City;
import client.models.ICatanModelObserver;
import client.models.ICity;
import client.models.IPlayer;
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

		initFromModel();
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
		//<temp>

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

		//</temp>
	}

	public boolean canPlaceRoad(EdgeLocation edgeLoc)
	{
		IRoadSegment segment = new RoadSegment();
		segment.setLocation(edgeLoc);
		try {
			segment.setPlayer(this.proxy.getFacade().getCurrentUser());
		} catch (CantFindGameModelException | CantFindPlayerException e) {
			e.printStackTrace();
		}
		return this.proxy.getGameModel().getMap().canPlaceRoad(segment);
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
		return this.proxy.getGameModel().getMap().canPlaceSettlement(settlement);
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
		return this.proxy.getGameModel().getMap().canPlaceCity(city);
	}

	public boolean canPlaceRobber(HexLocation hexLoc)
	{
		IRobber robber = this.proxy.getGameModel().getMap().getRobber();
		HexLocation currentLocation = (HexLocation) robber.getLocation();
		
		return (hexLoc != currentLocation);
		
	}

	public void placeRoad(EdgeLocation edgeLoc)
	{
		getView().placeRoad(edgeLoc, CatanColor.ORANGE);
		
		IRoadSegment segment = new RoadSegment();
		segment.setLocation(edgeLoc);
		try {
			this.proxy.getGameModel().getMap().placeRoadSegment(segment);
		} catch (InvalidLocationException e) {
			e.printStackTrace();
		}
	}

	public void placeSettlement(VertexLocation vertLoc)
	{
		getView().placeSettlement(vertLoc, CatanColor.ORANGE);

		IPlayer currentPlayer = null;
		
		try {
			currentPlayer = this.proxy.getFacade().getCurrentUser();
		} catch (CantFindGameModelException | CantFindPlayerException e) {
			e.printStackTrace();
		}
		
		ISettlement settlement = new Settlement(vertLoc, currentPlayer);
		
		try {
			this.proxy.getGameModel().getMap().placeSettlement(settlement);
		} catch (InvalidLocationException e) {
			e.printStackTrace();
		}
	}

	public void placeCity(VertexLocation vertLoc)
	{
		getView().placeCity(vertLoc, CatanColor.ORANGE);

		IPlayer currentPlayer = null;
		
		try {
			currentPlayer = this.proxy.getFacade().getCurrentUser();
		} catch (CantFindGameModelException | CantFindPlayerException e) {
			e.printStackTrace();
		}
		
		ICity city = new City(vertLoc, currentPlayer);
		
		try {
			this.proxy.getGameModel().getMap().placeCity(city);
		} catch (InvalidLocationException e) {
			e.printStackTrace();
		};
	}

	public void placeRobber(HexLocation hexLoc)
	{
		getView().placeRobber(hexLoc);

		getRobView().showModal();
	}

	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected)
	{
		getView().startDrop(pieceType, CatanColor.ORANGE, true);
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

	}

	@Override
	public void update()
	{
		
	}

}

