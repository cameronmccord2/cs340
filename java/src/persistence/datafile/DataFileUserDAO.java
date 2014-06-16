package persistence.datafile;

import client.models.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class dataFileUserDAO.
 */
public class DataFileUserDAO {

	private String usersDir;
	private String userFilenameFormat;

	public DataFileUserDAO() {
		usersDir = "users";
		userFilenameFormat = usersDir + File.separator + "userId%d.catanGameData";
		File userDir = new File(usersDir);

		// if the directory does not exist, create it
		if (!userDir.exists()) {
			boolean success = userDir.mkdir();
			if(!success)
				System.out.println("Couldn't make users directory");
		}
	}
	/**
	 * Insert User as a new datafile in the following format:
	 * filename: users/userId.dat
	 * serializedUserData
	 *
	 * If a dataFile for the user already exists, data will be overwritten.
	 *
	 * @param user the user to add/update
	 */
	public void upsertUser(User user){

		int userId = user.getPlayerID();

		String filename = String.format(userFilenameFormat, userId);

		File userDataFile = new File(filename);

		// No need to check if user exists, we'll just overwrite it anyway
		try {
			OutputStream os = new FileOutputStream(userDataFile, false);
			OutputStream buffer = new BufferedOutputStream(os);
			ObjectOutput output = new ObjectOutputStream(buffer);
			userDataFile.createNewFile();

			output.writeObject(user);

			output.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the all users in the dataFiles
	 *
	 * @return the all users in the dataFiles
	 */
	public List<User> getAllUsers(){
		List<User> usersList = new ArrayList<>();
		byte[] userData;

		for (File dataFile : new File(usersDir).listFiles()) {

			if (dataFile.isFile()) {
				try {
					userData = Files.readAllBytes(Paths.get(dataFile.getPath()));
					ByteArrayInputStream bis = new ByteArrayInputStream(userData);
					ObjectInput in = new ObjectInputStream(bis);
					User player = (User)in.readObject();

					usersList.add(player);

				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}

		return usersList;
	}
}
