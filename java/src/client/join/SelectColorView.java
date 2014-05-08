package client.join;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import shared.definitions.*;
import client.base.*;


/**
 * Implementation for the select color view, which lets the user select the desired
 * color when they join a game
 */
@SuppressWarnings("serial")
public class SelectColorView extends OverlayView implements ISelectColorView {

	private final int LABEL_TEXT_SIZE = 40;
	private final int BUTTON_TEXT_SIZE = 28;
	private final int BORDER_WIDTH = 10;

	private JLabel label;
	private JButton joinButton;
	private JButton cancelButton;
	private JPanel buttonPanel;

	public SelectColorView() {
	
		this.setOpaque(true);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black, BORDER_WIDTH));
		
		label = new JLabel("Select Color View");
		Font labelFont = label.getFont();
		labelFont = labelFont.deriveFont(labelFont.getStyle(), LABEL_TEXT_SIZE);
		label.setFont(labelFont);
		this.add(label, BorderLayout.CENTER);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(actionListener);
		Font buttonFont = cancelButton.getFont();
		buttonFont = buttonFont.deriveFont(buttonFont.getStyle(), BUTTON_TEXT_SIZE);
		cancelButton.setFont(buttonFont);
		
		joinButton = new JButton("Join Game");
		joinButton.addActionListener(actionListener);
		joinButton.setFont(buttonFont);	
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));		
		buttonPanel.add(joinButton);
		buttonPanel.add(cancelButton);		
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	private ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == joinButton) {
				
				getController().joinGame(null);
			}
			else if (e.getSource() == cancelButton) {
				
				getController().cancelJoinGame();
			}
		}	
	};

	@Override
	public IJoinGameController getController() {

		return (IJoinGameController)super.getController();
	}
	
	@Override
	public void setColorEnabled(CatanColor color, boolean enable) {

	}

	@Override
	public CatanColor getSelectedColor() {

		return null;
	}

}

