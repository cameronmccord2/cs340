package client.turntracker;

import java.awt.*;

import javax.swing.*;

import shared.definitions.*;
import client.base.*;
import client.catan.*;


/**
 * Implementation for the turn tracker view, which displays whose turn it is, and what state the game is in
 */
@SuppressWarnings("serial")
public class TurnTrackerView extends PanelView implements ITurnTrackerView {

	private TitlePanel titlePanel;
	private GameStatePanel gameStatePanel;
	
	public TurnTrackerView(TitlePanel titlePanel, GameStatePanel gameStatePanel) {
		
		// TEMPORARY
		this.add(new JLabel("Turn Tracker View"));
		
		this.titlePanel = titlePanel;
		this.gameStatePanel = gameStatePanel;
		
		this.setPreferredSize(new Dimension(350, 100));
	}

	@Override
	public ITurnTrackerController getController() {
		return (ITurnTrackerController)super.getController();
	}

	@Override
	public void setLocalPlayerColor(CatanColor value) {
		
		titlePanel.setLocalPlayerColor(value);
	}

	@Override
	public void initializePlayer(int playerIndex, String playerName,
			CatanColor playerColor) {
		
	}

	@Override
	public void updatePlayer(int playerIndex, int points, boolean highlight,
			boolean largestArmy, boolean longestRoad) {
		
	}

	@Override
	public void updateGameState(String stateMessage, boolean enable) {

		gameStatePanel.updateGameState(stateMessage, enable);
	}
	
}

