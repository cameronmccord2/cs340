package client.catan;

import java.awt.*;
import javax.swing.*;


@SuppressWarnings("serial")
public class GameStatePanel extends JPanel {
	
	private JButton button;

	public GameStatePanel() {
		
		this.setLayout(new FlowLayout());
		this.setBackground(Color.white);
		this.setOpaque(true);
		
		button = new JButton();
		
		Font font = button.getFont();
		Font newFont = font.deriveFont(font.getStyle(), 20);
		button.setFont(newFont);
		
		button.setPreferredSize(new Dimension(400, 50));
		
		this.add(button);
		
		updateGameState("Waiting for other Players", false);
	}
	
	public void updateGameState(String stateMessage, boolean enable) {
		
		button.setText(stateMessage);
		button.setEnabled(enable);
	}

}

