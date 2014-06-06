package client.communication;

import client.base.Controller;
import client.models.exceptions.CantFindGameModelException;
import client.models.interfaces.ICatanModelObserver;
import client.models.interfaces.IProxy;
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
		ServerChat chat;
		try {
			chat = new ServerChat("sendChat",proxy.getFacade().getCurrentUserIndex(),message);
			if(proxy.movesSendChat(chat).getResponseCode() != 200){
				System.out.println("error sending chat");
			}
		} catch (CantFindGameModelException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update() {
		getView().setEntries(this.proxy.getFacade().getChats());
	}

}

