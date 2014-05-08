package client.map;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import client.base.*;
import client.data.*;


/**
 * Implementation for the rob view, which lets the user select a player to rob
 */
@SuppressWarnings("serial")
public class RobView extends OverlayView implements IRobView {

	private final int LABEL_TEXT_SIZE = 40;
	private final int BUTTON_TEXT_SIZE = 28;
	private final int BORDER_WIDTH = 10;

	private JLabel label;
	private JButton robButton;
	private JPanel buttonPanel;

	public RobView() {
		
		this.setOpaque(true);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black, BORDER_WIDTH));
		
		label = new JLabel("Rob View");
		Font labelFont = label.getFont();
		labelFont = labelFont.deriveFont(labelFont.getStyle(), LABEL_TEXT_SIZE);
		label.setFont(labelFont);
		this.add(label, BorderLayout.CENTER);
		
		robButton = new JButton("Rob");
		robButton.addActionListener(actionListener);
		Font buttonFont = robButton.getFont();
		buttonFont = buttonFont.deriveFont(buttonFont.getStyle(), BUTTON_TEXT_SIZE);
		robButton.setFont(buttonFont);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));		
		buttonPanel.add(robButton);		
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	private ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == robButton) {
				
				closeModal();
				
				getController().robPlayer(null);				
			}
		}	
	};

	@Override
	public IMapController getController() {

		return (IMapController)super.getController();
	}

	@Override
	public void setPlayers(RobPlayerInfo[] candidateVictims) {

	}

}

