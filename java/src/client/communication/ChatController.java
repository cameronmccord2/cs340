package client.communication;

import java.util.List;

import client.base.Controller;
import client.models.IProxy;
import client.server.ServerChat;


/**
 * Chat controller implementation
 */
public class ChatController extends Controller implements IChatController {

	private IProxy proxy;
	
	public ChatController(IChatView view, IProxy proxy2) {
		
		super(view);
		this.proxy = proxy2;
	}

	@Override
	public IChatView getView() {
		return (IChatView)super.getView();
	}

	@Override
	public void sendMessage(String message) {
		ServerChat chat = new ServerChat("sendChat",0,message);
		if(proxy.movesSendChat(chat).getResponseCode() == 200){
			System.out.println("message sent");
		}
	}
	
	public void updateMessages(List<LogEntry> entries){
		getView().setEntries(entries);
	}

}

