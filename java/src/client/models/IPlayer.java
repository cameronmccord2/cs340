package client.models;

import java.util.List;

import client.data.PlayerInfo;

public interface IPlayer extends IParticipant {

	List<ISettlement> getSettlements();

	void setSettlements(List<ISettlement> settlements);

	List<IRoad> getRoads();

	void setRoads(List<IRoad> roads);

	PlayerInfo getPlayerInfo();

	void setPlayerInfo(PlayerInfo playerInfo);
	
	List<IResourceCard> robPlayer();

}
