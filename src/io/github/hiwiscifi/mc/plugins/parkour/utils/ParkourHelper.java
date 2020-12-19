package io.github.hiwiscifi.mc.plugins.parkour.utils;

import java.util.List;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import io.github.hiwiscifi.mc.plugins.parkour.Main;
import net.md_5.bungee.api.ChatColor;

public class ParkourHelper {

	public static void restartAtCheckpoint(Player player) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		boolean onParkour = pdc.get(US.onParkourKey, PersistentDataType.INTEGER) == 1;
		String currentParkour = pdc.get(US.currentParkourKey, PersistentDataType.STRING);

		if (!onParkour) {
			return;
		}
		List<Parkour> parkours = Main.getInstance().parkours;
		Parkour parkour = null;

		for (int i = 0; i < parkours.size(); i++) {
			if (parkours.get(i).name == currentParkour) {
				parkour = parkours.get(i);
			}
		}
		if (parkour == null) {
			return;
		}

		player.teleport(calculateCheckpointLocation(player));

	}

	public static void restartAtCheckpoint(Player player, int check) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		boolean onParkour = pdc.get(US.onParkourKey, PersistentDataType.INTEGER) == 1;
		String currentParkour = pdc.get(US.currentParkourKey, PersistentDataType.STRING);
		int currentCheckpoint = pdc.get(US.currentCheckpointKey, PersistentDataType.INTEGER);

		if (!onParkour) {
			return;
		}
		List<Parkour> parkours = Main.getInstance().parkours;
		Parkour parkour = null;

		for (int i = 0; i < parkours.size(); i++) {
			if (parkours.get(i).name == currentParkour) {
				parkour = parkours.get(i);
			}
		}
		if (parkour == null) {
			return;
		}

		if (check == 0) {
			if (parkour.startCheckpoint == null) {
				return;
			}
		}

		if (!(parkour.checkpoints.size() >= check)) {
			return;
		}

		if (parkour.checkpoints.get(check) != null) {
			return;
		}


		pdc.set(US.currentCheckpointKey, PersistentDataType.INTEGER, check);
		restartAtCheckpoint(player);

	}

	/**
	 * calculate the location to teleport the player to to reset them to a
	 * checkpoint
	 *
	 * @param player  the player to calculate the location for
	 * @param isReset if it is a reset to a previous checkpoint
	 * @return the location to teleport the player to
	 */
	public static Location calculateCheckpointLocation(Player player) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		boolean onParkour = pdc.get(US.onParkourKey, PersistentDataType.INTEGER) == 1;
		String currentParkour = pdc.get(US.currentParkourKey, PersistentDataType.STRING);
		int currentCheckpoint = pdc.get(US.currentCheckpointKey, PersistentDataType.INTEGER);

		if (currentCheckpoint == 0) {
			return calculateParkourStartLocation(player);
		}

		if (onParkour) {
			List<Parkour> parkours = Main.getInstance().parkours;
			Parkour parkour = null;

			for (int i = 0; i < parkours.size(); i++) {
				if (parkours.get(i).name == currentParkour) {
					parkour = parkours.get(i);
				}
			}
			if (parkour == null) {
				return null;
			}

			try {
				Location loc = parkour.checkpoints.get(currentCheckpoint - 1).location;
				Location l = loc;
				l.setX(loc.getBlockX() + 0.5d);
				l.setY(loc.getBlockY());
				l.setZ(loc.getBlockZ() + 0.5d);
				return loc;
			} catch (IndexOutOfBoundsException e) {
				return null;
			}

		} else {
			return null;
		}

	}

	/**
	 * calculate the location to teleport the player to to reset to the start of the
	 * parkour
	 *
	 * @param player the player to get the location for
	 * @return the location to teleport the player to
	 */
	public static Location calculateParkourStartLocation(Player player) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		boolean onParcour = pdc.get(US.onParkourKey, PersistentDataType.INTEGER) == 1;
		String currentParkour = pdc.get(US.currentParkourKey, PersistentDataType.STRING);

		if (onParcour) {
			List<Parkour> parkours = Main.getInstance().parkours;
			Parkour parkour = null;

			for (int i = 0; i < parkours.size(); i++) {
				if (parkours.get(i).name == currentParkour) {
					parkour = parkours.get(i);
				}
			}
			if (parkour == null) {
				return null;
			}

			return parkour.startLocation;
		} else {
			return null;
		}
	}

	/**
	 * checks if a certain parkour can be started by a given player and if so sets
	 * all the variables to start the parkour
	 *
	 * @param player  the player to start the parkour for
	 * @param parkour the parkour to start
	 */
	public static void startParkour(Player player, Parkour parkour) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR) {
			return;
		} else if (player.getGameMode() == GameMode.SURVIVAL) {
			player.setGameMode(GameMode.ADVENTURE);
		}

		pdc.set(US.currentCheckpointKey, PersistentDataType.INTEGER, 0);
		pdc.set(US.currentParkourKey, PersistentDataType.STRING, parkour.name);
		pdc.set(US.onParkourKey, PersistentDataType.INTEGER, 1);

		player.sendMessage(US.OUT_PREFIX + "You are now on the " + parkour.name + " parkour");
		// TODO set item and check gamemode store those items and remove them after
		// parkour is finished or aborted
		if (parkour.checkpoints.size() == 0) {
			finishParkour(player, parkour);
		}

		ParkourItems.setPlayerInventory(player);

		ParkourTimer.startParkourTimer(player);

		ParkourTimer.startCheckpointTimer(player);
		applyEffect(player, parkour.startCheckpoint);
	}

	/**
	 * restart a parkour for a given player
	 *
	 * @param player  the player to start the parkour for
	 * @param parkour the parkour to start
	 */
	public static void restartParkour(Player player, Parkour parkour) {
		ParkourTimer.startParkourTimer(player);
		applyEffect(player, parkour.startCheckpoint);

	}

	/**
	 * set the current checkpoint for a player on a parkour
	 *
	 * @param player     the player currently on the parkour
	 * @param parkour    the parkour the player is on
	 * @param checkpoint the index of the checkpoint in the parkour
	 */
	public static void startCheckpoint(Player player, Parkour parkour, int checkpoint) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		pdc.set(US.currentCheckpointKey, PersistentDataType.INTEGER, checkpoint);

		long timeSinceLastCheckpoint = ParkourTimer.endParkourTimer(player);
		player.sendMessage(US.OUT_PREFIX + ChatColor.AQUA + "Your time for the last Checkpoint was: "
				+ ParkourTimer.getTimeStringFromMs(timeSinceLastCheckpoint));

		ParkourTimer.startCheckpointTimer(player);
		applyEffect(player, parkour.checkpoints.get(checkpoint - 1));

	}

	/**
	 * restart the parkour from a specific checkpoint
	 *
	 * @param player     the player to restart for
	 * @param parkour    the parkour the player is on
	 * @param checkpoint the checkpoint to restart from
	 */
	public static void restartCheckpoint(Player player, Parkour parkour, int checkpoint) {
		ParkourTimer.startCheckpointTimer(player);

		applyEffect(player, parkour.checkpoints.get(checkpoint - 1));

	}

	/**
	 * finish a parkour for a certain player
	 *
	 * @param player  the player to finish the parkour
	 * @param parkour the parkour to finish
	 */
	public static void finishParkour(Player player, Parkour parkour) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		pdc.set(US.onParkourKey, PersistentDataType.INTEGER, 0);

		// https://en.wikipedia.org/wiki/Box-drawing_character

		long lastCheckpointTime = ParkourTimer.endCheckpointTimer(player);
		long parkourTime = ParkourTimer.endParkourTimer(player);

		spawnFireworks(player);
		// TODO pb tracker with pdc
		// TODO Return Leaderboard rank or personal best
		player.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD.toString() + ChatColor.MAGIC.toString() + "ooo "
				+ ChatColor.GREEN.toString() + ChatColor.BOLD.toString() + "CONGRATULATIONS!"
				+ ChatColor.GREEN.toString() + ChatColor.BOLD.toString() + ChatColor.MAGIC.toString() + " ooo");
		player.sendMessage(ChatColor.GREEN + "Your time to complete the parkour was: " + ChatColor.GOLD
				+ ChatColor.BOLD.toString() + ParkourTimer.getTimeStringFromMs(parkourTime));
		player.sendMessage(ChatColor.AQUA /* got norted */ + "Your time for the last Checkpoint was:" + ChatColor.GOLD
				+ ChatColor.BOLD.toString() + ParkourTimer.getTimeStringFromMs(lastCheckpointTime));
		player.sendTitle(
				ChatColor.GREEN + "ᐅ" + ChatColor.GREEN + ChatColor.BOLD.toString() + "CONGRATULATIONS!"
						+ ChatColor.GREEN + "ᐊ",
				ChatColor.GOLD + " Your Time: " + ChatColor.GOLD + ParkourTimer.getTimeStringFromMs(parkourTime), 10,
				30, 20);
		if (parkour.endTpBack) {
			player.teleport(parkour.startLocation);
		}
	}

	public static void spawnFireworks(Player player) {

		Location loc = player.getLocation();
		int diameter = 5;
		World w = player.getWorld();

		for (int i = 0; i < 6; i++) {
			Location newloc = loc.add(new Vector(Math.random() - 0.5, 2, Math.random() - 0.5).multiply(diameter));
			Firework fw = (Firework) w.spawnEntity(newloc, EntityType.FIREWORK);
			FireworkMeta fwm = fw.getFireworkMeta();
			fwm.setPower(2);

			if (i % 2 == 0) {
				fwm.addEffect(FireworkEffect.builder().withColor(Color.LIME).flicker(true).build());
			} else {
				fwm.addEffect(FireworkEffect.builder().withColor(Color.YELLOW).flicker(true).build());
			}

			fw.setFireworkMeta(fwm);
			fw.detonate();
		}
	}

	/**
	 * apply an effect to a player
	 *
	 * @param player      the player to apply the effect to
	 * @param effectPoint effect point to apply
	 */
	public static void applyEffect(Player player, EffectPoint effectPoint) {
		effectPoint.apply(player);
	}

	/**
	 * cancel the any parkour for a given player
	 *
	 * @param player the player to cancel the parkour running for
	 */
	public static void totalAbortion(Player player) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		pdc.set(US.currentCheckpointKey, PersistentDataType.INTEGER, 0);
		pdc.set(US.currentParkourKey, PersistentDataType.STRING, US.EMPTY);
		pdc.set(US.onParkourKey, PersistentDataType.INTEGER, 0);

		// stop timer without handeling output
		ParkourTimer.endParkourTimer(player);
		ParkourTimer.endCheckpointTimer(player);
	}
}
