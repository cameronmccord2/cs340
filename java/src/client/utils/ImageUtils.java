package client.utils;

import java.awt.image.*;
import java.io.*;
import javax.imageio.*;


public class ImageUtils {

	public static BufferedImage DEFAULT_IMAGE = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);

	public static BufferedImage loadImage(String file) {
		try {
			return ImageIO.read(new File(file));
		}
		catch (IOException e) {
			assert false;
		}
		
		return DEFAULT_IMAGE;
	}
	
}

