package client.devcards;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import shared.definitions.*;
import client.base.*;


/**
 * "Play dev card" view implementation
 */
@SuppressWarnings("serial")
public class PlayDevCardView extends OverlayView implements IPlayDevCardView {
	
	private final int LABEL_TEXT_SIZE = 40;
	private final int BUTTON_TEXT_SIZE = 28;
	private final int BORDER_WIDTH = 10;

	private JLabel label;
	private JButton cancelButton;

	public PlayDevCardView() {

		this.setOpaque(true);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black, BORDER_WIDTH));
		
		label = new JLabel("Play Development Card View");
		Font labelFont = label.getFont();
		labelFont = labelFont.deriveFont(labelFont.getStyle(), LABEL_TEXT_SIZE);
		label.setFont(labelFont);
		this.add(label, BorderLayout.CENTER);

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(actionListener);
		Font buttonFont = cancelButton.getFont();
		buttonFont = buttonFont.deriveFont(buttonFont.getStyle(), BUTTON_TEXT_SIZE);
		cancelButton.setFont(buttonFont);
		this.add(cancelButton, BorderLayout.SOUTH);
	}
	
	@Override
	public IDevCardController getController() {
		return (IDevCardController)super.getController();
	}

	@Override
	public void reset() {
		
	}

	@Override
	public void setCardEnabled(DevCardType cardType, boolean enabled) {
		
	}

	@Override
	public void setCardAmount(DevCardType cardType, int amount) {
		
	}

	private ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == cancelButton) {
				getController().cancelPlayCard();
			}
		}	
	};

}

