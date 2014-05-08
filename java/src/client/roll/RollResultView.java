package client.roll;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import client.base.*;


/**
 * Implementation for the roll result view, which displays the result of a roll
 */
@SuppressWarnings("serial")
public class RollResultView extends OverlayView implements IRollResultView {

	private final int LABEL_TEXT_SIZE = 40;
	private final int BUTTON_TEXT_SIZE = 28;
	private final int BORDER_WIDTH = 10;

	private JLabel label;
	private JButton okayButton;
	private JPanel buttonPanel;

	public RollResultView() {
		
		this.setOpaque(true);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black, BORDER_WIDTH));
		
		label = new JLabel("Roll Result View");
		Font labelFont = label.getFont();
		labelFont = labelFont.deriveFont(labelFont.getStyle(), LABEL_TEXT_SIZE);
		label.setFont(labelFont);
		this.add(label, BorderLayout.CENTER);
		
		okayButton = new JButton("Okay");
		okayButton.addActionListener(actionListener);
		Font buttonFont = okayButton.getFont();
		buttonFont = buttonFont.deriveFont(buttonFont.getStyle(), BUTTON_TEXT_SIZE);
		okayButton.setFont(buttonFont);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));		
		buttonPanel.add(okayButton);		
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	private ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == okayButton) {
				
				closeModal();
			}
		}	
	};
	
	@Override
	public IRollController getController() {
		
		return (IRollController)super.getController();
	}

	@Override
	public void setRollValue(int value) {

	}

}

