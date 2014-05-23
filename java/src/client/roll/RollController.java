package client.roll;

import client.base.Controller;
import client.models.ICatanModelObserver;
import client.models.IFacade;
import client.models.IProxy;
import client.models.ServerResponse;
import client.models.exceptions.CantFindGameModelException;
import client.server.ServerRoll;



/**
 * Implementation for the roll controller
 */
public class RollController extends Controller implements IRollController, ICatanModelObserver {

	private IProxy proxy;
	private IRollResultView resultView;

	/**
	 * RollController constructor
	 *
	 * @param view Roll view
	 * @param resultView Roll result view
	 */
	public RollController(IRollView view, IRollResultView resultView, IProxy proxy) {

		super(view);
		this.proxy = proxy;
		setResultView(resultView);
		this.proxy.getFacade().registerAsObserver(this);
	}

	public IRollResultView getResultView() {
		return resultView;
	}
	public void setResultView(IRollResultView resultView) {
		this.resultView = resultView;
	}

	public IRollView getRollView() {
		return (IRollView)getView();
	}

	@Override
	public void rollDice()
	{
		if(this.getRollView().isModalShowing())
			getRollView().closeModal();

		try
		{
			IFacade facade = proxy.getFacade();
			Integer playerIndex = facade.getCurrentUserIndex();

			int rolledResult = (int) (2 + (Math.random() * 11));
			ServerRoll serverRoll = new ServerRoll("rollNumber", playerIndex, rolledResult);
	   		ServerResponse response = proxy.movesRollNumber(serverRoll);

	   		if(response.getResponseCode() == 200){
	   			getResultView().setRollValue(rolledResult);
	   			getResultView().showModal();
	   		}
		}
		catch (CantFindGameModelException e)
		{
			e.printStackTrace();
		}

	}

	@Override
	public void update() {
		try {
			IFacade facade = proxy.getFacade();
//			System.out.println(this.proxy.getFacade().getTurnTracker().toString() + ", my index: " + this.proxy.getFacade().getCurrentUserIndex());
			if(facade.isMyTurn() && facade.getCurrentState().equals("Rolling")){
				if(!getRollView().isModalShowing() && !getResultView().isModalShowing())
					getRollView().showModal();
			}

		} catch (CantFindGameModelException e) {
			e.printStackTrace();
		}
	}
}

