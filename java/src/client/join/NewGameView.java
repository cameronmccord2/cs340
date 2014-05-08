package client.join;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import client.base.*;


/**
 * Implementation for the new game view, which lets the user enter parameters for a new game
 */
@SuppressWarnings("serial")
public class NewGameView extends OverlayView implements INewGameView {

	private final int LABEL_TEXT_SIZE = 40;
	private final int BUTTON_TEXT_SIZE = 28;
	private final int BORDER_WIDTH = 10;

	private JLabel label;
	private JButton createButton;
	private JButton cancelButton;
	private JPanel buttonPanel;

	public NewGameView() {
	
		this.setOpaque(true);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black, BORDER_WIDTH));
		
		label = new JLabel("New Game View");
		Font labelFont = label.getFont();
		labelFont = labelFont.deriveFont(labelFont.getStyle(), LABEL_TEXT_SIZE);
		label.setFont(labelFont);
		this.add(label, BorderLayout.CENTER);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(actionListener);
		Font buttonFont = cancelButton.getFont();
		buttonFont = buttonFont.deriveFont(buttonFont.getStyle(), BUTTON_TEXT_SIZE);
		cancelButton.setFont(buttonFont);
		
		createButton = new JButton("Create Game");
		createButton.addActionListener(actionListener);
		createButton.setFont(buttonFont);	
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));		
		buttonPanel.add(createButton);
		buttonPanel.add(cancelButton);		
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	private ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == createButton) {
				
				getController().createNewGame();
			}
			else if (e.getSource() == cancelButton) {
				
				getController().cancelCreateNewGame();
			}
		}	
	};

	@Override
	public IJoinGameController getController() {

		return (IJoinGameController)super.getController();
	}

	@Override
	public void setTitle(String value) {

	}

	@Override
	public String getTitle() {

		return null;
	}

	@Override
	public void setRandomlyPlaceNumbers(boolean value) {

	}

	@Override
	public boolean getRandomlyPlaceNumbers() {

		return false;
	}

	@Override
	public void setRandomlyPlaceHexes(boolean value) {

	}

	@Override
	public boolean getRandomlyPlaceHexes() {

		return false;
	}

	@Override
	public void setUseRandomPorts(boolean value) {

	}

	@Override
	public boolean getUseRandomPorts() {

		return false;
	}

}

