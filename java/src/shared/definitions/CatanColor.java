package shared.definitions;

import java.awt.Color;

public enum CatanColor {
	RED, ORANGE, YELLOW, BLUE, GREEN, PURPLE, PUCE, WHITE, BROWN;
	
	private Color color;
	
	static {
		RED.color = new Color(227, 66, 52);
		ORANGE.color = new Color(255, 165, 0);
		YELLOW.color = new Color(253, 224, 105);
		BLUE.color = new Color(111, 183, 246);
		GREEN.color = new Color(109, 192, 102);
		PURPLE.color = new Color(157, 140, 212);
		PUCE.color = new Color(204, 136, 153);
		WHITE.color = new Color(223, 223, 223);
		BROWN.color = new Color(161, 143, 112);
	}
	
	public Color getJavaColor() {
		return color;
	}

	public static CatanColor getColorForName(String color2) {
		switch(color2.toUpperCase()){
			case "RED":
				return CatanColor.RED;
			case "ORANGE":
				return CatanColor.ORANGE;
			case "YELLOW":
				return CatanColor.YELLOW;
			case "BLUE":
				return CatanColor.BLUE;
			case "GREEN":
				return CatanColor.GREEN;
			case "PURPLE":
				return CatanColor.PURPLE;
			case "PUCE":
				return CatanColor.PUCE;
			case "WHITE":
				return CatanColor.WHITE;
			case "BROWN":
				return CatanColor.BROWN;
		}
		return CatanColor.RED;
	}
}

