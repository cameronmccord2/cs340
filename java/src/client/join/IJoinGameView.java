package client.join;

import java.util.List;

import client.base.*;
import client.data.*;

/**
 * Interface for the join game view, which lets the user select a game to join
 */
public interface IJoinGameView extends IOverlayView
{
	
	/**
	 * Sets the list of available games to be displayed
	 * 
	 * @param games
	 *			Array of games to be displayed
	 * @param localPlayer
	 *			Information about the local player
	 */
	void setGames(List<GameInfo> games, PlayerInfo localPlayer);
	
}
