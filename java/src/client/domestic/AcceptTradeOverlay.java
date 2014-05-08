package client.domestic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;

import shared.definitions.*;
import client.base.*;


/**
 * Implementation of the "accept trade" overlay, which is used to let the user
 * accept or reject a trade
 */
@SuppressWarnings("serial")
public class AcceptTradeOverlay extends OverlayView implements IAcceptTradeOverlay {
	
	private final int LABEL_TEXT_SIZE = 40;
	private final int BUTTON_TEXT_SIZE = 28;
	private final int BORDER_WIDTH = 10;

	private JLabel label;
	private JButton acceptButton;
	private JButton rejectButton;
	private JPanel buttonPanel;
	
	public AcceptTradeOverlay() {

		this.setOpaque(true);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black, BORDER_WIDTH));
		
		label = new JLabel("Accept Trade Overlay");
		Font labelFont = label.getFont();
		labelFont = labelFont.deriveFont(labelFont.getStyle(), LABEL_TEXT_SIZE);
		label.setFont(labelFont);
		this.add(label, BorderLayout.CENTER);

		acceptButton = new JButton("Accept");
		acceptButton.addActionListener(actionListener);
		Font buttonFont = acceptButton.getFont();
		buttonFont = buttonFont.deriveFont(buttonFont.getStyle(), BUTTON_TEXT_SIZE);
		acceptButton.setFont(buttonFont);
		
		rejectButton = new JButton("No Thanks!");
		rejectButton.addActionListener(actionListener);
		rejectButton.setFont(buttonFont);	
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));		
		buttonPanel.add(acceptButton);
		buttonPanel.add(rejectButton);		
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	@Override
	public IDomesticTradeController getController() {
		return (IDomesticTradeController)super.getController();
	}

	@Override
	public void addGetResource(ResourceType resource, int amount) {

	}

	@Override
	public void addGiveResource(ResourceType resource, int amount) {

	}

	@Override
	public void setAcceptEnabled(boolean enable) {

	}

	@Override
	public void setPlayerName(String name) {

	}

	private ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == acceptButton) {
				getController().acceptTrade(true);
			}
			else if (e.getSource() == rejectButton) {
				getController().acceptTrade(false);
			}			
		}	
	};
	
}

