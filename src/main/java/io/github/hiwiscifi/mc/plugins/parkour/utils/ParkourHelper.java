package io.github.hiwiscifi.mc.plugins.parkour.utils;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import io.github.hiwiscifi.mc.plugins.parkour.Main;

public class ParkourHelper {

	//TODO add return or errors
	/** resets player to the start of the parkour they are on
	 * @param player to be reset*/
	public static void resetToStart(Player player) {
		resetToCheckpoint(player, 0);
	}

	/** resets player to the checkpoint they are on
	 * @param player to be reset*/
	public static void resetToCheckpoint(Player player) {
		Location loc = calculateCheckpointLocation(player);

		if (loc != null) {
			player.teleport(loc);
			ParkourLogic.restartCheckpoint(player);
		}
	}

	/** resets player to the n-th checkpoint of the parkour they are on
	 * @param player to be reset
	 * @param check of checkpoint to reset to*/
	public static void resetToCheckpoint(Player player, int check) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		boolean onParkour = pdc.get(StringUtil.onParkourKey, PersistentDataType.INTEGER) == 1;
		String currentParkour = pdc.get(StringUtil.currentParkourKey, PersistentDataType.STRING);

		if (!onParkour) {
			return;
		}
		List<Parkour> parkours = Main.getInstance().parkours;
		Parkour parkour = null;

        for (Parkour value : parkours) {
            if (value.name.equals(currentParkour)) {
                parkour = value;
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

		if (parkour.checkpoints.get(check) == null) {
			return;
		}


		pdc.set(StringUtil.currentCheckpointKey, PersistentDataType.INTEGER, check);
		resetToCheckpoint(player);

	}

	/**
	 * calculate the location of the checkpoint a player is on
	 *
	 * @param player  the player to calculate the location for
	 * @return the location of the checkpoint
	 */
	public static Location calculateCheckpointLocation(Player player) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		boolean onParkour = pdc.get(StringUtil.onParkourKey, PersistentDataType.INTEGER) == 1;
		String currentParkour = pdc.get(StringUtil.currentParkourKey, PersistentDataType.STRING);
		int currentCheckpoint = pdc.get(StringUtil.currentCheckpointKey, PersistentDataType.INTEGER);

		if (currentCheckpoint == 0) {
			return calculateParkourStartLocation(player);
		}

		if (onParkour) {
			List<Parkour> parkours = Main.getInstance().parkours;
			Parkour parkour = null;

            for (Parkour value : parkours) {
                if (value.name.equals(currentParkour)) {
                    parkour = value;
                }
            }
			if (parkour == null) {
				return null;
			}

			try {
				Location loc = parkour.checkpoints.get(currentCheckpoint - 1).location;
                loc.setX(loc.getBlockX() + 0.5d);
				loc.setY(loc.getBlockY());
				loc.setZ(loc.getBlockZ() + 0.5d);
				return loc;
			} catch (IndexOutOfBoundsException e) {
				return null;
			}

		} else {
			return null;
		}

	}

	/**
	 * calculate the location of the start of the parkour the player is on
	 *
	 * @param player the player to get the location for
	 * @return the location of the start (can be null)
	 */
	public static Location calculateParkourStartLocation(Player player) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		boolean onParkour = pdc.get(StringUtil.onParkourKey, PersistentDataType.INTEGER) == 1;
		String currentParkour = pdc.get(StringUtil.currentParkourKey, PersistentDataType.STRING);

		if (onParkour) {
			List<Parkour> parkours = Main.getInstance().parkours;
			Parkour parkour = null;

            for (Parkour value : parkours) {
                if (value.name.equals(currentParkour)) {
                    parkour = value;
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
}
