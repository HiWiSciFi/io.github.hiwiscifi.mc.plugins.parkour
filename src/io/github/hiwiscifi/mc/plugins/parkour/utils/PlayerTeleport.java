package io.github.hiwiscifi.mc.plugins.parkour.utils;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import io.github.hiwiscifi.mc.plugins.parkour.Main;
import io.github.hiwiscifi.mc.plugins.parkour.Parkour;

public class PlayerTeleport {

	public static Location calculateCheckpointLocation(Player player) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		NamespacedKey onParkourKey = new NamespacedKey(Main.getInstance(), "parkour_onParkour");
		boolean onParcour = (pdc.get(onParkourKey, PersistentDataType.INTEGER) % 2) == 1;

		if(onParcour) {
			NamespacedKey currentParkourKey = new NamespacedKey(Main.getInstance(), "parkour_currentParkour");
			String currentParkour = pdc.get(currentParkourKey, PersistentDataType.STRING);

			List<Parkour> parkours = Main.getInstance().parkours;
			Parkour parkour = null;

			for (int i = 0; i < parkours.size(); i++) {
				if(parkours.get(i).name == currentParkour) {
					parkour = parkours.get(i);
				}
			}
			if(parkour == null) {
				return null;
			}

			NamespacedKey currentCheckpointKey = new NamespacedKey(Main.getInstance(), "parkour_currentCheckpoint");
			int currentCheckpoint = pdc.get(currentCheckpointKey, PersistentDataType.INTEGER);

			try {
				return parkour.checkpoints.get(currentCheckpoint);
			} catch(IndexOutOfBoundsException e){
				return null;
			}

		} else {
			return null;
		}
	}

	//TODO try
	public static Location calculateParkourStartLocation(Player player) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		NamespacedKey onParkourKey = new NamespacedKey(Main.getInstance(), "parkour_onParkour");
		boolean onParcour = (pdc.get(onParkourKey, PersistentDataType.INTEGER) % 2) == 1;

		if(onParcour) {
			NamespacedKey currentParkourKey = new NamespacedKey(Main.getInstance(), "parkour_currentParkour");
			String currentParkour = pdc.get(currentParkourKey, PersistentDataType.STRING);

			List<Parkour> parkours = Main.getInstance().parkours;
			Parkour parkour = null;

			for (int i = 0; i < parkours.size(); i++) {
				if(parkours.get(i).name == currentParkour) {
					parkour = parkours.get(i);
				}
			}
			if(parkour == null) {
				return null;
			}

			return parkour.startLocation;
		} else {
			return null;
		}
	}
}
