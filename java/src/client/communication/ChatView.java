package client.communication;

import java.util.List;

import javax.swing.*;

import client.base.*;


/**
 * Chat view implementation
 */
@SuppressWarnings("serial")
public class ChatView extends PanelView implements IChatView {

	public ChatView() {
		
		// TEMPORARY
		this.add(new JLabel("Chat View"));
		
	}
	
	@Override
	public IChatController getController() {
		return (IChatController)super.getController();
	}

	@Override
	public void setEntries(final List<LogEntry> entries) {
	
	}
	
}

