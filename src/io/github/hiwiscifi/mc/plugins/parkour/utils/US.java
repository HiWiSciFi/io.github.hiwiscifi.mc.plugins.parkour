package io.github.hiwiscifi.mc.plugins.parkour.utils;

import java.util.HashMap;

public class US {
	
	public final static String EMPTY = "";
	public final static String DOT = ".";
	public final static String QUOTATION_MARK = "\"";
	public final static String SPACE = " ";
	public final static String MINUS = "-";
	public final static String OUT_PREFIX = "[Parkour] ";
	public final static String THREE_DOTS = "...";
	
	private static HashMap<Integer, String> stringCollection;
	public static void initializeStringCollection() {
		stringCollection = new HashMap<Integer, String>();
		
		stringCollection.put(0,		"Initializing Parkour plugin");
		stringCollection.put(1,		"Registring commands");
		stringCollection.put(2,		"test");
		stringCollection.put(3,		"parkour");
		stringCollection.put(4,		"Registring events");
		stringCollection.put(5,		"Loading parkours from config");
		stringCollection.put(6,		"Parkour plugin initialized");
		stringCollection.put(7,		"Saving data to files");
		stringCollection.put(8,		"Preventing memory leaks");
		stringCollection.put(9,		"Plugin disabled");
		stringCollection.put(10,	"parkours");
		stringCollection.put(11,	"checkpoints");
		stringCollection.put(12,	"startCheckpoint");
		stringCollection.put(13,	"startLocation");
		stringCollection.put(14,	"Initializing parkour command");
		stringCollection.put(15,	"create");
		stringCollection.put(16,	"Creating parkour");
		
		stringCollection.put(18,	"Parkour created");
		stringCollection.put(19,	"delete");
		stringCollection.put(20,	"Deleting parkour");
		stringCollection.put(21,	"Parkour deleted");
		stringCollection.put(22,	"list");
		stringCollection.put(23,	"List of registered parkours");
		stringCollection.put(24,	"set");
		stringCollection.put(25,	"location");
		stringCollection.put(26,	"Setting start location for parkour");
		stringCollection.put(27,	"New start location set!");
		stringCollection.put(28,	"checkpoint");
		stringCollection.put(29,	"add");
		stringCollection.put(30,	"remove");
		stringCollection.put(31,	"Initializing test command");
		stringCollection.put(32,	"You are in world");
		stringCollection.put(33,	"Initializing item use event listener");
		stringCollection.put(34,	"parkour_func");
		
		
		
		stringCollection.put(38,	"Initializing player join event listener");
		stringCollection.put(39,	"parkour_currentParkour");
		stringCollection.put(40,	"parkour_onParkour");
		stringCollection.put(41,	"parkour_currentCheckpoint");
		stringCollection.put(42,	"Initializing player respawn event listener");
		stringCollection.put(43,	"respawning");
		stringCollection.put(44,	"Initializing pressure plate activation event listener");
		stringCollection.put(45,	"You are now on the");
	}
	
	public static String getString(int index) {
		if (!stringCollection.containsKey(index) || index < 0) {
			return null;
		}
		return stringCollection.get(index);
	}
	
	public static String inQuotes(String string) {
		return QUOTATION_MARK + string + QUOTATION_MARK;
	}
	
	public static String addSpace(String string, boolean addStartSpace, boolean addEndSpace) {
		if (addStartSpace) string = SPACE + string;
		if (addEndSpace) string = string + SPACE;
		return string;
	}
	
	public static String fillWithMinus(String string, int desiredLength) {
		if (desiredLength > 1) {
			if ((desiredLength - string.length()) % 2 != 0) {
				desiredLength -= 1;
			}
			int toAdd = (desiredLength - string.length()) / 2;
			String str = EMPTY;
			for (int i = 0; i < toAdd; i++) {
				str += MINUS;
			}
			return str + string + str;
		}
		return string;
	}
}
