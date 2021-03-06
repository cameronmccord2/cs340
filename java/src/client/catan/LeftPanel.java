package client.catan;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import client.communication.ChatController;
import client.communication.ChatView;
import client.communication.GameHistoryController;
import client.communication.GameHistoryView;
import client.models.interfaces.IProxy;
import client.turntracker.TurnTrackerController;
import client.turntracker.TurnTrackerView;


@SuppressWarnings("serial")
public class LeftPanel extends JPanel {

	private JTabbedPane tabPane;
	private GameHistoryView historyView;
	private GameHistoryController historyController;
	private ChatView chatView;
		private ChatController chatController;
	private TurnTrackerView turnView;
	private TurnTrackerController turnController;
	
	public LeftPanel(TitlePanel titlePanel, GameStatePanel gameStatePanel, IProxy proxy)
	{
		this.setLayout(new BorderLayout());
		
		tabPane = new JTabbedPane();
		Font font = tabPane.getFont();
		Font newFont = font.deriveFont(font.getStyle(), 20);
		tabPane.setFont(newFont);
		
		historyView = new GameHistoryView();
		historyController = new GameHistoryController(historyView, proxy);
		historyView.setController(historyController);
		
		chatView = new ChatView();
				chatController = new ChatController(chatView, proxy);
				chatView.setController(chatController);
		
		turnView = new TurnTrackerView(titlePanel, gameStatePanel);
		turnController = new TurnTrackerController(turnView, proxy);
		turnView.setController(turnController);
		
		tabPane.add("Game History", historyView);
		tabPane.add("Chat Messages", chatView);
		
		this.add(tabPane, BorderLayout.CENTER);
		this.add(turnView, BorderLayout.SOUTH);

		this.setPreferredSize(new Dimension(350, 700));
	}

	public GameHistoryView getHistoryView() {
		return historyView;
	}

	public ChatView getChatView() {
		return chatView;
	}

	public TurnTrackerView getTurnView() {
		return turnView;
	}

}

