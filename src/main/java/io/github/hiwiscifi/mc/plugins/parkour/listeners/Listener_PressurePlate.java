package io.github.hiwiscifi.mc.plugins.parkour.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import io.github.hiwiscifi.mc.plugins.parkour.Main;
import io.github.hiwiscifi.mc.plugins.parkour.utils.Parkour;
import io.github.hiwiscifi.mc.plugins.parkour.utils.ParkourLogic;
import io.github.hiwiscifi.mc.plugins.parkour.utils.StringUtil;
import io.github.hiwiscifi.mc.plugins.parkour.utils.WorldControl;

public class Listener_PressurePlate implements Listener {

	public Listener_PressurePlate getInstance() {
		return instance;
	}

	private static Listener_PressurePlate instance;
	private final List<Material> supportedMaterials;

	public Listener_PressurePlate() {
		Main.getInstance().getLogger().info("Initializing pressure plate activation event listener...");
		instance = this;

		supportedMaterials = new ArrayList<>(){{
			add(Material.OAK_PRESSURE_PLATE);
			add(Material.SPRUCE_PRESSURE_PLATE);
			add(Material.BIRCH_PRESSURE_PLATE);
			add(Material.JUNGLE_PRESSURE_PLATE);
			add(Material.ACACIA_PRESSURE_PLATE);
			add(Material.DARK_OAK_PRESSURE_PLATE);
			add(Material.MANGROVE_PRESSURE_PLATE);
			add(Material.CHERRY_PRESSURE_PLATE);
			add(Material.BAMBOO_PRESSURE_PLATE);
			add(Material.CRIMSON_PRESSURE_PLATE);
			add(Material.WARPED_PRESSURE_PLATE);
			add(Material.STONE_PRESSURE_PLATE);
			add(Material.POLISHED_BLACKSTONE_PRESSURE_PLATE);
			add(Material.HEAVY_WEIGHTED_PRESSURE_PLATE);
			add(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
		}};
	}

	@EventHandler
	public void pressurePlatePress(PlayerInteractEvent e) {
		if (!WorldControl.enabled(e.getPlayer().getWorld())) {
			return;
		}

		if (!e.getAction().equals(Action.PHYSICAL)) {
			return;
		}

		Block ablock = e.getClickedBlock();

		if (ablock == null || !supportedMaterials.contains(ablock.getType())) {
			return;
		}

		Player player = e.getPlayer();
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		boolean onParkour = pdc.get(StringUtil.onParkourKey, PersistentDataType.INTEGER) == 1;
		String currentParkour = pdc.get(StringUtil.currentParkourKey, PersistentDataType.STRING);
		int currentCheckpoint = pdc.get(StringUtil.currentCheckpointKey, PersistentDataType.INTEGER);


		if (onParkour) {
			List<Parkour> parkours = Main.getInstance().parkours;
			Parkour parkour = null;

            for (Parkour value : parkours) {
                if (value.name.equals(currentParkour)) {
                    parkour = value;
                }
            }

			if(parkour == null) {
				pdc.set(StringUtil.onParkourKey, PersistentDataType.INTEGER, 0);
				return;
			}

			if(!(parkour.checkpoints.size() > currentCheckpoint)) {
				//Theoretically never reached
				ParkourLogic.finishParkour(player,parkour);
				return;
			}

			if (parkour.checkpoints.get(currentCheckpoint).location.distance(ablock.getLocation()) < 0.75d) {
				if(parkour.checkpoints.size() == currentCheckpoint+1) {
					ParkourLogic.finishParkour(player,parkour);
					return;
				}
				ParkourLogic.startCheckpoint(player, parkour, currentCheckpoint + 1);
			}else if (currentCheckpoint > 0) {
				if (parkour.checkpoints.get(currentCheckpoint-1).location.distance(ablock.getLocation()) < 0.75d) {
					ParkourLogic.restartCheckpoint(player, parkour, currentCheckpoint);

				} else {
					for (int i = 0; i < parkour.checkpoints.get(currentCheckpoint-1).effectPoints.size(); i++) {
						if (parkour.checkpoints.get(currentCheckpoint-1).effectPoints.get(i).location.distance(ablock.getLocation()) < 0.75d) {
							ParkourLogic.applyEffect(player, parkour.checkpoints.get(currentCheckpoint-1).effectPoints.get(i));
						}
					}
				}
			}
			else if(parkour.startCheckpoint.location.distance(ablock.getLocation()) < 0.75d) {
				ParkourLogic.restartParkour(player, parkour);
			}
			else {
				for (int i = 0; i < parkour.startCheckpoint.effectPoints.size(); i++) {
					if (parkour.startCheckpoint.effectPoints.get(i).location.distance(ablock.getLocation()) < 0.75d) {
						ParkourLogic.applyEffect(player, parkour.startCheckpoint.effectPoints.get(i));
					}
				}
			}

		} else {
			for (Parkour parkour : Main.getInstance().parkours) {
				if (parkour.startCheckpoint != null) {
					if (parkour.startCheckpoint.location.distance(ablock.getLocation()) < 0.75d) {
						ParkourLogic.startParkour(player, parkour);
					}
				}
			}
		}
	}
}
