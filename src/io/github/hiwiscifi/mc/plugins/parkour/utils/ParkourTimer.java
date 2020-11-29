package io.github.hiwiscifi.mc.plugins.parkour.utils;

import java.util.concurrent.TimeUnit;

import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ParkourTimer {
	
	/** start the stopwatch to count the time until a given player reaches the next checkpoint
	 * @param player the player to start the stopwatch for */
	public static void startCheckpointTimer(Player player) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();
		pdc.set(US.timerStartCheckpointTimeKey, PersistentDataType.LONG, System.currentTimeMillis());
	}
	
	/** stop the stopwatch and read the time it took a given player to reach their last checkpoint
	 * @param player the player to stop the stopwatch for
	 * @return the time that went by since the stopwatch got started. If it didn't get started it'll return Long.MAX_VALUE */
	public static long endCheckpointTimer(Player player) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();
		if (pdc.has(US.timerStartCheckpointTimeKey, PersistentDataType.LONG)) {
			long startTime = pdc.get(US.timerStartCheckpointTimeKey, PersistentDataType.LONG);
			return System.currentTimeMillis() - startTime;
		} else {
			return Long.MAX_VALUE;
		}
	}
	
	
	/** start the stopwatch to count the time until a given player reaches the next checkpoint
	 * @param player the player to start the stopwatch for */
	public static void startParkourTimer(Player player) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();
		pdc.set(US.timerStartParkourTimeKey, PersistentDataType.LONG, System.currentTimeMillis());
	}
	
	/** stop the stopwatch and read the time it took a given player to reach their last checkpoint
	 * @param player the player to stop the stopwatch for
	 * @return the time that went by since the stopwatch got started. If it didn't get started it'll return Long.MAX_VALUE */
	public static long endParkourTimer(Player player) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();
		if (pdc.has(US.timerStartParkourTimeKey, PersistentDataType.LONG)) {
			long startTime = pdc.get(US.timerStartParkourTimeKey, PersistentDataType.LONG);
			return System.currentTimeMillis() - startTime;
		} else {
			return Long.MAX_VALUE;
		}
	}
	
	
	/** turns a long value in milliseconds into a string and splits it into hours, minutes, seconds and milliseconds
	 * @param time
	 * @return */
	public static String getTimeStringFromMs(long time) {
		// 0h 00m 00s 000ms
		long t = time;
		
		long hours = TimeUnit.MILLISECONDS.toHours(t);
		t -= TimeUnit.HOURS.toMillis(t);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(t);
		t -= TimeUnit.MINUTES.toMillis(t);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(t);
		t -= TimeUnit.SECONDS.toMillis(t);
		long milliseconds = t;
		
		String timeStr = US.EMPTY;
		if (hours > 0) timeStr += hours + "h" + US.SPACE;
		timeStr += minutes + "m" + US.SPACE;
		timeStr += seconds + "s" + US.SPACE;
		timeStr += milliseconds + "ms";
		
		return timeStr;
	}
}
