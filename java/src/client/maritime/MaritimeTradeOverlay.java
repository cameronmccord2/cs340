package client.maritime;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import shared.definitions.*;
import client.base.*;


/**
 * Implementation for the maritime trade overlay, which lets the user make a maritime trade
 */
@SuppressWarnings("serial")
public class MaritimeTradeOverlay extends OverlayView implements IMaritimeTradeOverlay {

	private final int LABEL_TEXT_SIZE = 40;
	private final int BUTTON_TEXT_SIZE = 28;
	private final int BORDER_WIDTH = 10;

	private JLabel label;
	private JButton tradeButton;
	private JButton cancelButton;
	private JPanel buttonPanel;

	public MaritimeTradeOverlay() {
	
		this.setOpaque(true);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black, BORDER_WIDTH));
		
		label = new JLabel("Maritime Trade Overlay");
		Font labelFont = label.getFont();
		labelFont = labelFont.deriveFont(labelFont.getStyle(), LABEL_TEXT_SIZE);
		label.setFont(labelFont);
		this.add(label, BorderLayout.CENTER);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(actionListener);
		Font buttonFont = cancelButton.getFont();
		buttonFont = buttonFont.deriveFont(buttonFont.getStyle(), BUTTON_TEXT_SIZE);
		cancelButton.setFont(buttonFont);
		
		tradeButton = new JButton("Trade!");
		tradeButton.addActionListener(actionListener);
		tradeButton.setFont(buttonFont);	
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));		
		buttonPanel.add(tradeButton);
		buttonPanel.add(cancelButton);		
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	@Override
	public IMaritimeTradeController getController() {
		return (IMaritimeTradeController)super.getController();
	}

	@Override
	public void reset() {
		
	}

	@Override
	public void hideGetOptions() {
		
	}

	@Override
	public void hideGiveOptions() {
		
	}

	@Override
	public void selectGetOption(ResourceType selectedResource, int amount) {
		
	}

	@Override
	public void selectGiveOption(ResourceType selectedResource, int amount) {
		
	}

	@Override
	public void setStateMessage(String message) {
		
	}

	@Override
	public void setTradeEnabled(boolean enable) {
		
	}

	@Override
	public void setCancelEnabled(boolean enabled) {
		
	}

	@Override
	public void showGetOptions(ResourceType[] enabledResources) {
		
	}

	@Override
	public void showGiveOptions(ResourceType[] enabledResources) {
		
	}

	private ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == tradeButton) {
				getController().makeTrade();
			}
			else if (e.getSource() == cancelButton) {
				getController().cancelTrade();
			}
		}	
	};

}

