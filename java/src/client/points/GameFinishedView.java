package client.points;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import client.base.*;


/**
 * Implementation for the game finished view, which is displayed when the game is over
 */
@SuppressWarnings("serial")
public class GameFinishedView extends OverlayView implements IGameFinishedView {

	private final int LABEL_TEXT_SIZE = 40;
	private final int BUTTON_TEXT_SIZE = 28;
	private final int BORDER_WIDTH = 10;

	private JLabel label;
	private JButton okButton;
	private JPanel buttonPanel;

	public GameFinishedView() {
		
		this.setOpaque(true);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black, BORDER_WIDTH));
		
		label = new JLabel("Game Finished View");
		Font labelFont = label.getFont();
		labelFont = labelFont.deriveFont(labelFont.getStyle(), LABEL_TEXT_SIZE);
		label.setFont(labelFont);
		this.add(label, BorderLayout.CENTER);
		
		okButton = new JButton("OK");
		okButton.addActionListener(actionListener);
		Font buttonFont = okButton.getFont();
		buttonFont = buttonFont.deriveFont(buttonFont.getStyle(), BUTTON_TEXT_SIZE);
		okButton.setFont(buttonFont);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));		
		buttonPanel.add(okButton);		
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	private ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == okButton) {
				
				closeModal();
			}
		}	
	};
	
	@Override
	public IPointsController getController() {
		
		return (IPointsController)super.getController();
	}

	@Override
	public void setWinner(String name, boolean isLocalPlayer) {

	}

}

