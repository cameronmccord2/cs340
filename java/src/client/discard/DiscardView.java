package client.discard;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import shared.definitions.*;
import client.base.*;


/**
 * Discard view implementation
 */
@SuppressWarnings("serial")
public class DiscardView extends OverlayView implements IDiscardView {
	
	private final int LABEL_TEXT_SIZE = 40;
	private final int BUTTON_TEXT_SIZE = 28;
	private final int BORDER_WIDTH = 10;

	private JLabel label;
	private JButton discardButton;

	public DiscardView() {

		this.setOpaque(true);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black, BORDER_WIDTH));
		
		label = new JLabel("Discard View");
		Font labelFont = label.getFont();
		labelFont = labelFont.deriveFont(labelFont.getStyle(), LABEL_TEXT_SIZE);
		label.setFont(labelFont);
		this.add(label, BorderLayout.CENTER);

		discardButton = new JButton("Discard");
		discardButton.addActionListener(actionListener);
		Font buttonFont = discardButton.getFont();
		buttonFont = buttonFont.deriveFont(buttonFont.getStyle(), BUTTON_TEXT_SIZE);
		discardButton.setFont(buttonFont);
		this.add(discardButton, BorderLayout.SOUTH);
	}
	
	@Override
	public IDiscardController getController() {
		return (IDiscardController)super.getController();
	}

	@Override
	public void setDiscardButtonEnabled(boolean enabled) {

	}

	@Override
	public void setResourceDiscardAmount(ResourceType resource, int amount) {

	}

	@Override
	public void setResourceMaxAmount(ResourceType resource, int maxAmount) {

	}

	@Override
	public void setResourceAmountChangeEnabled(ResourceType resource,
												boolean increase, boolean decrease) {

	}

	@Override
	public void setStateMessage(String message) {

	}

	private ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == discardButton) {
				getController().discard();
			}
		}	
	};

}

