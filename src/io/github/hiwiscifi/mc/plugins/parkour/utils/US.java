package io.github.hiwiscifi.mc.plugins.parkour.utils;

import org.bukkit.NamespacedKey;

import io.github.hiwiscifi.mc.plugins.parkour.Main;

public class US {
	
	/** an empty string */
	public final static String EMPTY = "";
	/** an dot character as a string */
	public final static String DOT = ".";
	/** a quotation mark character as a string */
	public final static String QUOTATION_MARK = "\"";
	/** a space character as a string */
	public final static String SPACE = " ";
	/** a minus character as a string */
	public final static String MINUS = "-";
	/** the general output prefix for the plugin */
	public final static String OUT_PREFIX = "[Parkour] ";
	/** three dots */
	public final static String THREE_DOTS = "...";
	
	/** the key for the onParkour:Integer value in a player's persistentDataContainer */
	public static NamespacedKey onParkourKey;
	/** the key for the currentParkour:String value in a player's persistentDataContainer */
	public static NamespacedKey currentParkourKey;
	/** the key for the currentCheckpoint:Integer value in a player's persistentDataContainer */
	public static NamespacedKey currentCheckpointKey;
	
	/** the key for the timerStartParkourTime:Long value in a player's persistentDataContainer*/
	public static NamespacedKey timerStartParkourTimeKey;
	/** the key for the timerStartCheckpointTime:Long value in a player's persistentDataContainer*/
	public static NamespacedKey timerStartCheckpointTimeKey;
	
	/** Initializes the NamespacedKeys for later use */
	public static void initializeKeys() {
		onParkourKey = new NamespacedKey(Main.getInstance(), "onParkour");
		currentParkourKey = new NamespacedKey(Main.getInstance(), "currentParkour");
		currentCheckpointKey = new NamespacedKey(Main.getInstance(), "currentCheckpoint");
		
		timerStartParkourTimeKey = new NamespacedKey(Main.getInstance(), "timerStartParkourTime");
		timerStartCheckpointTimeKey = new NamespacedKey(Main.getInstance(), "timerStartCheckpointTime");
	}
	
	/** put a string into quotes
	 * @param string the string to put quotes around
	 * @return the original string in quotes */
	public static String inQuotes(String string) {
		return QUOTATION_MARK + string + QUOTATION_MARK;
	}
	
	/** Fills up a string to a set length with minuses on both sides
	 * @param string the string to fill up
	 * @param desiredLength the desired final length of the string
	 * @return the string with added minuses */
	public static String fillWithMinus(String string, int desiredLength) {
		if (desiredLength - string.length() > 1) {
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
