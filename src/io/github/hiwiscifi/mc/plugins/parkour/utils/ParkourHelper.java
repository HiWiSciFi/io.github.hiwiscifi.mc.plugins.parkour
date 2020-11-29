package io.github.hiwiscifi.mc.plugins.parkour.utils;

import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import com.destroystokyo.paper.Title;

import io.github.hiwiscifi.mc.plugins.parkour.Main;
import net.md_5.bungee.api.ChatColor;

public class ParkourHelper {

	public static Location calculateCheckpointLocation(Player player) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		boolean onParkour = pdc.get(US.onParkourKey, PersistentDataType.INTEGER) == 1;
		String currentParkour = pdc.get(US.currentParkourKey, PersistentDataType.STRING);
		int currentCheckpoint = pdc.get(US.currentCheckpointKey, PersistentDataType.INTEGER);

		if(currentCheckpoint == 0) {
			return calculateParkourStartLocation(player);
		}

		if(onParkour) {
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

		boolean onParcour = pdc.get(US.onParkourKey, PersistentDataType.INTEGER) == 1;
		String currentParkour = pdc.get(US.currentParkourKey, PersistentDataType.STRING);

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

		if(player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR) {
			return;
		}else if(player.getGameMode() == GameMode.SURVIVAL) {
			player.setGameMode(GameMode.ADVENTURE);
		}

		pdc.set(US.currentCheckpointKey,PersistentDataType.INTEGER,0);
		pdc.set(US.currentParkourKey, PersistentDataType.STRING, parkour.name);
		pdc.set(US.onParkourKey, PersistentDataType.INTEGER, 1);

		player.sendMessage(US.OUT_PREFIX + US.getString(45) + US.addSpace(parkour.name, true, true) + US.getString(3));
		//TODO set item and check gamemode store those items and remove them after parkour is finished or aborted
		if(parkour.checkpoints.size() == 0) {
			finishParkour(player,parkour);
		}

		ParkourTimer.startParkourTimer(player);

		applyEffect(player, parkour.startCheckpoint);
	}

	public static void restartParkour(Player player, Parkour parkour) {

		ParkourTimer.startParkourTimer(player);
		applyEffect(player, parkour.startCheckpoint);

	}

	public static void startCheckpoint(Player player, Parkour parkour, int checkpoint) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		pdc.set(US.currentCheckpointKey, PersistentDataType.INTEGER, checkpoint);

		ParkourTimer.startCheckpointTimer(player);
		applyEffect(player, parkour.checkpoints.get(checkpoint-1));

	}

	public static void restartCheckpoint(Player player, Parkour parkour, int checkpoint) {
		ParkourTimer.startCheckpointTimer(player);

		applyEffect(player, parkour.checkpoints.get(checkpoint-1));

	}

	public static void finishParkour(Player player, Parkour parkour) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		pdc.set(US.onParkourKey, PersistentDataType.INTEGER, 0);
				
		player.sendMessage(ChatColor.GREEN + "Pop that pussy like that pop that pussy like that");
		//TODO Zeit ausgeben im Subtitle Hax
		player.sendTitle("CONGRATULATIONS, YOU COMPLETED THE PARKOUR!", "Time: ", 10, 30, 20);
		//TODO ausgelagertes timer zeug
	}

	public static void applyEffect(Player player, EffectPoint effectPoint) {
		effectPoint.apply(player);
	}

	public static void totalAbortion(Player player) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		pdc.set(US.currentCheckpointKey,PersistentDataType.INTEGER,0);
		pdc.set(US.currentParkourKey, PersistentDataType.STRING, US.EMPTY);
		pdc.set(US.onParkourKey, PersistentDataType.INTEGER, 0);
	}
}
