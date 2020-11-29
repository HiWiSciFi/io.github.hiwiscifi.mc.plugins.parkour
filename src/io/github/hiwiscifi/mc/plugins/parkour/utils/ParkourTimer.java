package io.github.hiwiscifi.mc.plugins.parkour.utils;

import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ParkourTimer {
	
	public static void startCheckpointTimer(Player player) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();
		pdc.set(US.timerStartCheckpointTimeKey, PersistentDataType.LONG, System.currentTimeMillis());
	}
	
	public static long endCheckpointTimer(Player player) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();
		if (pdc.has(US.timerStartCheckpointTimeKey, PersistentDataType.LONG)) {
			long startTime = pdc.get(US.timerStartCheckpointTimeKey, PersistentDataType.LONG);
			return System.currentTimeMillis() - startTime;
		} else {
			return Long.MAX_VALUE;
		}
	}
	
	
	public static void startParkourTimer(Player player) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();
		pdc.set(US.timerStartParkourTimeKey, PersistentDataType.LONG, System.currentTimeMillis());
	}
	
	public static long endParkourTimer(Player player) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();
		if (pdc.has(US.timerStartParkourTimeKey, PersistentDataType.LONG)) {
			long startTime = pdc.get(US.timerStartParkourTimeKey, PersistentDataType.LONG);
			return System.currentTimeMillis() - startTime;
		} else {
			return Long.MAX_VALUE;
		}
	}
}
