package client.join;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import client.base.*;
import client.data.*;


/**
 * Implementation for the join game view, which lets the user select a game to join
 */
@SuppressWarnings("serial")
public class JoinGameView extends OverlayView implements IJoinGameView {

	private final int LABEL_TEXT_SIZE = 40;
	private final int PANEL_TEXT_SIZE = 14;
	private final int BUTTON_TEXT_SIZE = 28;
	private final int BORDER_WIDTH = 10;

	private JLabel label;
	private JLabel subLabel; 
	
	private JLabel hash;
	private JLabel name;
	private JLabel currentPlayer;
	private JLabel join;
	
	private JButton createButton;
	private JButton joinButton;
	
	private JPanel labelPanel;
	private JPanel gamePanel;
	private JPanel buttonPanel;
	
	GameInfo[] games;
	PlayerInfo localPlayer;

	public JoinGameView() {
		
		this.setOpaque(true);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black, BORDER_WIDTH));
		
		label = new JLabel("Welcome to the game hub");
		Font labelFont = label.getFont();
		labelFont = labelFont.deriveFont(labelFont.getStyle(), LABEL_TEXT_SIZE);
		label.setFont(labelFont);
		subLabel = new JLabel("Join an existing game, or create your own");
		labelFont = subLabel.getFont();
		labelFont = labelFont.deriveFont(labelFont.getStyle(), LABEL_TEXT_SIZE*2/3);
		subLabel.setFont(labelFont);
		
		
		labelPanel = new JPanel();
		labelPanel.setLayout(new FlowLayout());
		labelPanel.add(label);
		labelPanel.add(subLabel);
		this.add(labelPanel, BorderLayout.NORTH);
		
		// Padding
		Dimension edge0 = new Dimension(5,10);
		Dimension edge1 = new Dimension(15,20);
		
		JComponent box0 = new Box.Filler(edge0, edge0, edge0);
		JComponent box1 = new Box.Filler(edge1, edge1, edge1);
		
		
		// This is the header layout
		gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(0, 8));
		hash = new JLabel("#");
		labelFont = new Font(labelFont.getFontName(), Font.BOLD, PANEL_TEXT_SIZE);
		hash.setFont(labelFont);
		name = new JLabel("Name");
		name.setFont(labelFont);
		currentPlayer = new JLabel("Current Players");
		currentPlayer.setFont(labelFont);
		join = new JLabel("Join");
		join.setFont(labelFont);
		
		
		gamePanel.add(box0);
		gamePanel.add(hash);
		gamePanel.add(box0);
		gamePanel.add(name);
		gamePanel.add(box1);
		gamePanel.add(currentPlayer);
		gamePanel.add(box1);
		gamePanel.add(join);
		gamePanel.add(box1);
		
		
		// This is the looped layout
		if (games != null && games.length > 0) {
			labelFont = labelFont.deriveFont(labelFont.getStyle(), PANEL_TEXT_SIZE);
			for (int i = 0; i < games.length; i++) {
				gamePanel.add(box0);
				JLabel tmp = new JLabel(String.valueOf(games[i].getId()));
				tmp.setFont(labelFont);
				gamePanel.add(tmp);
				
				gamePanel.add(box0);
				tmp = new JLabel(games[i].getTitle());
				tmp.setFont(labelFont);
				gamePanel.add(tmp);
				
				gamePanel.add(box1);
				String players = String.valueOf(games[i].getPlayers().size()) + "/4 : ";
				for (int j = 0; j < games[i].getPlayers().size(); j++) {
					players = players + games[i].getPlayers().get(j).getName() + " ";
				}
				tmp = new JLabel(players);
				tmp.setFont(labelFont);
				gamePanel.add(tmp);
				
				gamePanel.add(box1);
				JButton joinButton;
				if (games[i].getPlayers().size() < 4) {
					if (games[i].getPlayers().contains(localPlayer)) {
						joinButton = new JButton("Re-Join");
					} else { 
						joinButton = new JButton("Join");
					}
				} else {
					joinButton = new JButton("Full");
					joinButton.setEnabled(false);
				}
				gamePanel.add(joinButton);
				gamePanel.add(box1);
			} 
		} 
		
		//Add all the above
		
		this.add(gamePanel, BorderLayout.CENTER);
		
		joinButton = new JButton("Join");
		joinButton.addActionListener(actionListener);
		Font buttonFont = joinButton.getFont();
		buttonFont = buttonFont.deriveFont(buttonFont.getStyle(), BUTTON_TEXT_SIZE);
		joinButton.setFont(buttonFont);
		
		createButton = new JButton("Create Game");
		createButton.addActionListener(actionListener);
		createButton.setFont(buttonFont);	
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(createButton);
		buttonPanel.add(joinButton);		
		this.add(buttonPanel, BorderLayout.SOUTH);	
	}

	private ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == createButton) {
				
				getController().startCreateNewGame();
			}
			else if (e.getSource() == joinButton) {

				getController().startJoinGame(null);
			}
		}	
	};

	@Override
	public IJoinGameController getController() {

		return (IJoinGameController)super.getController();
	}

	@Override
	public void setGames(GameInfo[] games, PlayerInfo localPlayer) {
		this.games = games;
		this.localPlayer = localPlayer;
	}

	
}

