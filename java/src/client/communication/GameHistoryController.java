package client.communication;

import java.util.ArrayList;
import java.util.List;

import client.base.Controller;
import client.models.ICatanModelObserver;
import client.models.IProxy;


/**
 * Game history controller implementation
 */
public class GameHistoryController extends Controller implements IGameHistoryController, ICatanModelObserver {

	private IProxy proxy;
	
	public GameHistoryController(IGameHistoryView view, IProxy proxy) {
		
		super(view);
		this.proxy = proxy;
		this.proxy.getFacade().registerAsObserver(this);
	}
	
	@Override
	public IGameHistoryView getView() {
		
		return (IGameHistoryView)super.getView();
	}
	
	private void initFromModel() {
		
		//List<LogEntry> entries = new ArrayList<LogEntry>();
//		entries.add(new LogEntry(CatanColor.BROWN, "This is a brown message"));
//		entries.add(new LogEntry(CatanColor.ORANGE, "This is an orange message ss x y z w.  This is an orange message.  This is an orange message.  This is an orange message."));
//		entries.add(new LogEntry(CatanColor.BROWN, "This is a brown message"));
//		entries.add(new LogEntry(CatanColor.ORANGE, "This is an orange message ss x y z w.  This is an orange message.  This is an orange message.  This is an orange message."));
//		entries.add(new LogEntry(CatanColor.BROWN, "This is a brown message"));
//		entries.add(new LogEntry(CatanColor.ORANGE, "This is an orange message ss x y z w.  This is an orange message.  This is an orange message.  This is an orange message."));
//		entries.add(new LogEntry(CatanColor.BROWN, "This is a brown message"));
//		entries.add(new LogEntry(CatanColor.ORANGE, "This is an orange message ss x y z w.  This is an orange message.  This is an orange message.  This is an orange message."));
		
		getView().setEntries(this.proxy.getFacade().getGameHistory());

	}

	@Override
	public void update() {
		System.out.println("hist update");
		initFromModel();	
	}
	
}

