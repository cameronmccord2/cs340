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
	private final int BUTTON_TEXT_SIZE = 28;
	private final int BORDER_WIDTH = 10;

	private JLabel label;
	private JButton createButton;
	private JButton joinButton;
	private JPanel buttonPanel;

	public JoinGameView() {
		
		this.setOpaque(true);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black, BORDER_WIDTH));
		
		label = new JLabel("Join Game View");
		Font labelFont = label.getFont();
		labelFont = labelFont.deriveFont(labelFont.getStyle(), LABEL_TEXT_SIZE);
		label.setFont(labelFont);
		this.add(label, BorderLayout.CENTER);
		
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
		
	}

	
}

