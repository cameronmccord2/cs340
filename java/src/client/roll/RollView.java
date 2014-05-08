package client.roll;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import client.base.*;


/**
 * Implementation for the roll view, which allows the user to roll the dice
 */
@SuppressWarnings("serial")
public class RollView extends OverlayView implements IRollView {

	private final int LABEL_TEXT_SIZE = 40;
	private final int BUTTON_TEXT_SIZE = 28;
	private final int BORDER_WIDTH = 10;

	private JLabel label;
	private JButton rollButton;
	private JPanel buttonPanel;

	public RollView() {
		
		this.setOpaque(true);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black, BORDER_WIDTH));
		
		label = new JLabel("Roll View");
		Font labelFont = label.getFont();
		labelFont = labelFont.deriveFont(labelFont.getStyle(), LABEL_TEXT_SIZE);
		label.setFont(labelFont);
		this.add(label, BorderLayout.CENTER);
		
		rollButton = new JButton("Roll!");
		rollButton.addActionListener(actionListener);
		Font buttonFont = rollButton.getFont();
		buttonFont = buttonFont.deriveFont(buttonFont.getStyle(), BUTTON_TEXT_SIZE);
		rollButton.setFont(buttonFont);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));		
		buttonPanel.add(rollButton);		
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	private ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == rollButton) {
				
				closeModal();
				
				getController().rollDice();
			}
		}	
	};
	
	@Override
	public IRollController getController() {
		
		return (IRollController)super.getController();
	}

	@Override
	public void setMessage(String message) {

	}

}

