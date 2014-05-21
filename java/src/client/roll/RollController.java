package client.roll;

import client.base.Controller;
import client.models.ICatanModelObserver;
import client.models.IProxy;
import client.models.Proxy;
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
	public void rollDice() {
		getRollView().closeModal();
		int rolledResult = (int) (1 + (Math.random() * 12));
		ServerRoll serverRoll = new ServerRoll("rollNumber",0,rolledResult);
		if(proxy.movesRollNumber(serverRoll).getResponseCode() == 200){
			getResultView().setRollValue(rolledResult);
			getResultView().showModal();
			
		}
	}

	@Override
	public void update() {
		try {
			if(this.proxy.getFacade().isMyTurn())
				getRollView().showModal();
		} catch (CantFindGameModelException e) {
			e.printStackTrace();
		}
	}
}

