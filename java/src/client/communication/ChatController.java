package client.communication;

import client.base.Controller;
import client.models.ICatanModelObserver;
import client.models.IProxy;
import client.server.ServerChat;


/**
 * Chat controller implementation
 */
public class ChatController extends Controller implements IChatController, ICatanModelObserver {

	private IProxy proxy;
	
	public ChatController(IChatView view, IProxy proxy) {
		
		super(view);
		this.proxy = proxy;
		this.proxy.getFacade().registerAsObserver(this);
	}

	@Override
	public IChatView getView() {
		return (IChatView)super.getView();
	}

	@Override
	public void sendMessage(String message) {
		ServerChat chat = new ServerChat("sendChat",0,message);
		if(proxy.movesSendChat(chat).getResponseCode() != 200){
			System.out.println("error sending chat");
		}
	}

	@Override
	public void update() {
		getView().setEntries(this.proxy.getFacade().getChats());
	}

}

