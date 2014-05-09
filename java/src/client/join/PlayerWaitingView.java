package client.join;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import client.base.*;
import client.data.*;


/**
 * Implementation for the player waiting view, which is displayed when the user is
 * waiting for other players to join their game
 */
@SuppressWarnings("serial")
public class PlayerWaitingView extends OverlayView implements IPlayerWaitingView {

	private final int LABEL_TEXT_SIZE = 40;
	private final int BUTTON_TEXT_SIZE = 28;
	private final int BORDER_WIDTH = 10;

	private JLabel label;
	private JButton addAiButton;
	private JPanel buttonPanel;

	public PlayerWaitingView() {
		
		this.setOpaque(true);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black, BORDER_WIDTH));
		
		label = new JLabel("New Game View");
		Font labelFont = label.getFont();
		labelFont = labelFont.deriveFont(labelFont.getStyle(), LABEL_TEXT_SIZE);
		label.setFont(labelFont);
		this.add(label, BorderLayout.CENTER);
		
		addAiButton = new JButton("Add AI");
		addAiButton.addActionListener(actionListener);
		Font buttonFont = addAiButton.getFont();
		buttonFont = buttonFont.deriveFont(buttonFont.getStyle(), BUTTON_TEXT_SIZE);
		addAiButton.setFont(buttonFont);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));		
		buttonPanel.add(addAiButton);		
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	private ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == addAiButton) {
				
				getController().addAI();
			}
		}	
	};

	@Override
	public IPlayerWaitingController getController() {

		return (IPlayerWaitingController)super.getController();
	}

	@Override
	public void setPlayers(PlayerInfo[] value) {

	}

	@Override
	public void setAIChoices(String[] value) {

	}

	@Override
	public String getSelectedAI() {

		return null;
	}

}

