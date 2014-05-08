package client.misc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import client.base.*;


/**
 * Implementation for the wait view, which is used to display wait dialogs to the user
 */
@SuppressWarnings("serial")
public class WaitView extends OverlayView implements IWaitView {

	private final int LABEL_TEXT_SIZE = 40;
	private final int BORDER_WIDTH = 10;

	private JLabel label;

	public WaitView() {

		super();
		
		this.setOpaque(true);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black, BORDER_WIDTH));
		
		label = new JLabel("");
		Font labelFont = label.getFont();
		labelFont = labelFont.deriveFont(labelFont.getStyle(), LABEL_TEXT_SIZE);
		label.setFont(labelFont);
		this.add(label, BorderLayout.CENTER);
	}

	@Override
	public void setMessage(String message) {

		label.setText(message);
	}

}

