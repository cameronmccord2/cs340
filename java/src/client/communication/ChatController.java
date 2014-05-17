package client.communication;

import java.util.List;

import client.base.Controller;
import client.models.Proxy;
import client.server.ServerChat;


/**
 * Chat controller implementation
 */
public class ChatController extends Controller implements IChatController {

	private Proxy proxy;
	
	public ChatController(IChatView view, Proxy proxy) {
		
		super(view);
		this.proxy = proxy;
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

