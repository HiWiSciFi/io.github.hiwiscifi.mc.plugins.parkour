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
		pdc.set(StringUtil.timerStartCheckpointTimeKey, PersistentDataType.LONG, System.currentTimeMillis());
	}

	/** stop the stopwatch and read the time it took a given player to reach their last checkpoint
	 * @param player the player to stop the stopwatch for
	 * @return the time that went by since the stopwatch got started. If it didn't get started it'll return Long.MAX_VALUE */
	public static long endCheckpointTimer(Player player) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();
		if (pdc.has(StringUtil.timerStartCheckpointTimeKey, PersistentDataType.LONG)) {
			long startTime = pdc.get(StringUtil.timerStartCheckpointTimeKey, PersistentDataType.LONG);
			return System.currentTimeMillis() - startTime;
		} else {
			return Long.MAX_VALUE;
		}
	}


	/** start the stopwatch to count the time until a given player reaches the next checkpoint
	 * @param player the player to start the stopwatch for */
	public static void startParkourTimer(Player player) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();
		pdc.set(StringUtil.timerStartParkourTimeKey, PersistentDataType.LONG, System.currentTimeMillis());
	}

	/** stop the stopwatch and read the time it took a given player to reach their last checkpoint
	 * @param player the player to stop the stopwatch for
	 * @return the time that went by since the stopwatch got started. If it didn't get started it'll return Long.MAX_VALUE */
	public static long endParkourTimer(Player player) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();
		if (pdc.has(StringUtil.timerStartParkourTimeKey, PersistentDataType.LONG)) {
			long startTime = pdc.get(StringUtil.timerStartParkourTimeKey, PersistentDataType.LONG);
			return System.currentTimeMillis() - startTime;
		} else {
			return Long.MAX_VALUE;
		}
	}


	/** turns a long value in milliseconds into a string and splits it into hours, minutes, seconds and milliseconds
	 * @param time the time to parse in milliseconds
	 * @return the parsed value in the 0h 0m 0s 0ms format without the hours if they are not needed*/
	public static String getTimeStringFromMs(long time) {

		// format: 0h 00m 00s 000ms

		long hours = TimeUnit.MILLISECONDS.toHours(time);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(time) % TimeUnit.HOURS.toMinutes(1);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(time) % TimeUnit.MINUTES.toSeconds(1);
		long milliseconds = time % TimeUnit.SECONDS.toMillis(1);

		String timeStr = "";
		if (hours > 0L) timeStr += hours + "h ";
		timeStr += minutes + "m ";
		timeStr += seconds + "s ";
		timeStr += milliseconds + "ms";

		return timeStr;
	}
}
