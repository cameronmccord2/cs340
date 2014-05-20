package client.points;

import client.base.*;
import client.models.ICatanModelObserver;
import client.models.IProxy;
import client.models.exceptions.CantFindPlayerException;
import client.server.FinishedTurn;


/**
 * Implementation for the points controller
 */
public class PointsController extends Controller implements IPointsController, ICatanModelObserver {

	private IGameFinishedView finishedView;
    private IProxy proxy;
	
	/**
	 * PointsController constructor
	 * 
	 * @param view Points view
	 * @param finishedView Game finished view, which is displayed when the game is over
	 */
	public PointsController(IPointsView view, IGameFinishedView finishedView, IProxy proxy) {
		
		super(view);
        this.proxy = proxy;
		
		setFinishedView(finishedView);
		
		initFromModel();
        this.proxy.getFacade().registerAsObserver(this);
	}
	
	public IPointsView getPointsView() {
		
		return (IPointsView)super.getView();
	}
	
	public IGameFinishedView getFinishedView() {
		return finishedView;
	}

	public void setFinishedView(IGameFinishedView finishedView) {
		this.finishedView = finishedView;
	}

	private void initFromModel() {
        try {
            Integer points = this.proxy.getFacade().getCurrentUser().getVictoryPoints();
            getPointsView().setPoints( points );
            if( points == 10 ) {
                // Notify server that our turn is over, as we just won!
                //TODO set game state to "complete" or something so everyone knows
                this.proxy.movesFinishTurn(new FinishedTurn("finishTurn", this.proxy.getFacade().getCurrentUserIndex()));
                getFinishedView().setWinner(this.proxy.getFacade().getCurrentUser().getPlayerInfo().getName(), true);
                getFinishedView().showModal();
            }

        }
        catch (CantFindPlayerException e) {
            e.printStackTrace();
        }
	}

    @Override
    public void update() {
       initFromModel();
    }
}

