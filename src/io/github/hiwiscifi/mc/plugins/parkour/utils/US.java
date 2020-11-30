package io.github.hiwiscifi.mc.plugins.parkour.utils;

import org.bukkit.NamespacedKey;

import io.github.hiwiscifi.mc.plugins.parkour.Main;

public class US {
	
	public final static String EMPTY = "";
	public final static String DOT = ".";
	public final static String QUOTATION_MARK = "\"";
	public final static String SPACE = " ";
	public final static String MINUS = "-";
	public final static String OUT_PREFIX = "[Parkour] ";
	public final static String THREE_DOTS = "...";
	
	public static NamespacedKey onParkourKey;
	public static NamespacedKey currentParkourKey;
	public static NamespacedKey currentCheckpointKey;
	
	public static NamespacedKey timerStartParkourTimeKey;
	public static NamespacedKey timerStartCheckpointTimeKey;
	
	public static void initializeStringCollection() {
		onParkourKey = new NamespacedKey(Main.getInstance(), "onParkour");
		currentParkourKey = new NamespacedKey(Main.getInstance(), "currentParkour");
		currentCheckpointKey = new NamespacedKey(Main.getInstance(), "currentCheckpoint");
		
		timerStartParkourTimeKey = new NamespacedKey(Main.getInstance(), "timerStartParkourTime");
		timerStartCheckpointTimeKey = new NamespacedKey(Main.getInstance(), "timerStartCheckpointTime");
	}
	
	public static String inQuotes(String string) {
		return QUOTATION_MARK + string + QUOTATION_MARK;
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
