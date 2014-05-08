package client.resources;


import java.awt.event.*;
import javax.swing.*;

import client.base.*;


/**
 * Implementation for the resource bar view
 */
@SuppressWarnings("serial")
public class ResourceBarView extends PanelView implements IResourceBarView {

	public ResourceBarView() {
		
		// TEMPORARY
		this.add(new JLabel("Resource Bar View"));
		
		this.addMouseListener(mouseAdapter);
		this.addKeyListener(keyAdapter);
	}

	@Override
	public IResourceBarController getController() {
		return (IResourceBarController)super.getController();
	}

	@Override
	public void setElementEnabled(ResourceBarElement element, boolean enabled) {
		
	}

	@Override
	public void setElementAmount(ResourceBarElement element, int amount) {
		
	}
	
	private MouseAdapter mouseAdapter = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			ResourceBarView.this.requestFocus();
		}
	};
	
	private KeyAdapter keyAdapter = new KeyAdapter() {
		@Override
		public void keyTyped(KeyEvent e) {
			switch (e.getKeyChar()) {
			case '1':
				getController().buildRoad();
				break;
			case '2':
				getController().buildSettlement();
				break;
			case '3':
				getController().buildCity();
				break;
			case '4':
				getController().buyCard();
				break;
			case '5':
				getController().playCard();
				break;
			}
		}	
	};
	
}

