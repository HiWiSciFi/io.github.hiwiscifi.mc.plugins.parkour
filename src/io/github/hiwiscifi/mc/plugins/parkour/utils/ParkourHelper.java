package io.github.hiwiscifi.mc.plugins.parkour.utils;

import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import io.github.hiwiscifi.mc.plugins.parkour.Main;

public class ParkourHelper {

	public static Location calculateCheckpointLocation(Player player) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		NamespacedKey onParkourKey = new NamespacedKey(Main.getInstance(), "parkour_onParkour");
		boolean onParcour = (pdc.get(onParkourKey, PersistentDataType.INTEGER) % 2) == 1;

		NamespacedKey currentParkourKey = new NamespacedKey(Main.getInstance(), "parkour_currentParkour");
		String currentParkour = pdc.get(currentParkourKey, PersistentDataType.STRING);

		NamespacedKey currentCheckpointKey = new NamespacedKey(Main.getInstance(), "parkour_currentCheckpoint");
		int currentCheckpoint = pdc.get(currentCheckpointKey, PersistentDataType.INTEGER);

		if(currentCheckpoint == 0) {
			return calculateParkourStartLocation(player);
		}

		if(onParcour) {
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

			try {
				return parkour.checkpoints.get(currentCheckpoint-1).location;
			} catch(IndexOutOfBoundsException e){
				return null;
			}

		} else {
			return null;
		}
	}

	public static Location calculateParkourStartLocation(Player player) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		NamespacedKey onParkourKey = new NamespacedKey(Main.getInstance(), "parkour_onParkour");
		boolean onParcour = (pdc.get(onParkourKey, PersistentDataType.INTEGER) % 2) == 1;

		NamespacedKey currentParkourKey = new NamespacedKey(Main.getInstance(), "parkour_currentParkour");
		String currentParkour = pdc.get(currentParkourKey, PersistentDataType.STRING);

		if(onParcour) {
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

	public static void startParkour(Player player, Parkour parkour) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		NamespacedKey onParkourKey = new NamespacedKey(Main.getInstance(), US.getString(40));
		NamespacedKey currentParkourKey = new NamespacedKey(Main.getInstance(), US.getString(39));
		NamespacedKey currentCheckpointKey = new NamespacedKey(Main.getInstance(), US.getString(41));

		if(player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR) {
			return;
		}else if(player.getGameMode() == GameMode.SURVIVAL) {
			player.setGameMode(GameMode.ADVENTURE);
		}

		pdc.set(currentCheckpointKey,PersistentDataType.INTEGER,0);
		pdc.set(currentParkourKey, PersistentDataType.STRING, parkour.name);
		pdc.set(onParkourKey, PersistentDataType.INTEGER, 1);

		player.sendMessage(US.OUT_PREFIX + US.getString(45) + US.addSpace(parkour.name, true, true) + US.getString(3));
		//TODO set item and check gamemode store those items and remove them after parkour is finished or aborted
		if(parkour.checkpoints.size() == 0) {
			finishParkour(player,parkour);
		}

		//TODO start timer
		applyEffect(player, parkour.startCheckpoint);
	}

	public static void restartParkour(Player player, Parkour parkour) {

		//TODO reset timer
		applyEffect(player, parkour.startCheckpoint);

	}

	public static void startCheckpoint(Player player, Parkour parkour, int checkpoint) {
		NamespacedKey currentCheckpointKey = new NamespacedKey(Main.getInstance(), US.getString(41));

		PersistentDataContainer pdc = player.getPersistentDataContainer();

		pdc.set(currentCheckpointKey, PersistentDataType.INTEGER, checkpoint);
		//TODO start checkpoint timer

		applyEffect(player, parkour.checkpoints.get(checkpoint-1));

	}

	public static void restartCheckpoint(Player player, Parkour parkour, int checkpoint) {
		//TODO reset checkpoint timer

		applyEffect(player, parkour.checkpoints.get(checkpoint-1));

	}

	public static void finishParkour(Player player, Parkour parkour) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		NamespacedKey onParkourKey = new NamespacedKey(Main.getInstance(), US.getString(40));

		pdc.set(onParkourKey, PersistentDataType.INTEGER, 0);

		System.out.println("completed");
		//TODO broadcast

		//TODO ausgelagertes timer zeug
	}

	public static void applyEffect(Player player, EffectPoint effectPoint) {

	}

	public static void totalAbortion(Player player) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		NamespacedKey onParkourKey = new NamespacedKey(Main.getInstance(), US.getString(40));
		NamespacedKey currentParkourKey = new NamespacedKey(Main.getInstance(), US.getString(39));
		NamespacedKey currentCheckpointKey = new NamespacedKey(Main.getInstance(), US.getString(41));

		pdc.set(currentCheckpointKey,PersistentDataType.INTEGER,0);
		pdc.set(currentParkourKey, PersistentDataType.STRING, "");
		pdc.set(onParkourKey, PersistentDataType.INTEGER, 0);

		//TODO abort timer
	}
}
