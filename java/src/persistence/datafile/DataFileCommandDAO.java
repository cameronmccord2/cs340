package persistence.datafile;

import server.commands.ICommandParams;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class dataFileCommandDAO.
 */
public class DataFileCommandDAO
{
	private static final String COMMANDS_DIR = DataFilePlugin.PARENT_PREFIX + 
											  DataFilePlugin.COMMANDS_DIRECTORY + 
											  File.separator;
	
	private static final String commandFilenameFormat = COMMANDS_DIR + 
														"commandGameId%dcommandIndex%d.catanGameData";
	
	private int commandId;
	
	public DataFileCommandDAO () {
		commandId = 0;
	}

	/**
	 * Gets the commands for game id.
	 *
	 * @param gameId the game id
	 * @return the commands for game id
	 */
	public List<ICommandParams> getCommandsForGameId(Integer gameId){
		
		List<ICommandParams> commandsList = new ArrayList<>();
		byte[] commandData;

		for (File dataFile : new File(COMMANDS_DIR).listFiles()) {

			if (dataFile.isFile() && dataFile.getPath().contains("commandGameId"+gameId)) {
				try {
					commandData = Files.readAllBytes(Paths.get(dataFile.getPath()));
					ByteArrayInputStream bis = new ByteArrayInputStream(commandData);
					ObjectInput in = new ObjectInputStream(bis);
					ICommandParams command = (ICommandParams)in.readObject();

					commandsList.add(command);

				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return commandsList;

	}

	/**
	 * Save command for game id in the following format:
	 * filename: commands/gameId.dat
	 * serializedCommandData1
	 * serializedCommandData2
	 * ...
	 *
	 * @param command the command to save
	 * @param gameId the game id to save the command for
	 */
	public void saveCommandForGameId(ICommandParams command, Integer gameId){

		String filename = String.format(commandFilenameFormat, gameId, commandId++);

		File commandDataFile = new File(filename);

		try {
			OutputStream os = new FileOutputStream(commandDataFile, true);
			OutputStream buffer = new BufferedOutputStream(os);
			ObjectOutput output = new ObjectOutputStream(buffer);
			commandDataFile.createNewFile();

			output.writeObject(command);

			output.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Count commands for game id.
	 *
	 * @param gameId the game id
	 * @return the count of the current commands for a game
	 */
	public Integer countCommandsForGameId(Integer gameId)
	{
		int count = 0;
		
		for (File dataFile : new File(COMMANDS_DIR).listFiles()) 
		{
			if (dataFile.isFile() && dataFile.getPath().contains("commandGameId"+gameId)) 
			{
				count ++;
			}
		}
		
		return count;

	}
}
