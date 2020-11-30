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
import io.github.hiwiscifi.mc.plugins.parkour.utils.ParkourHelper;
import io.github.hiwiscifi.mc.plugins.parkour.utils.US;

public class Listener_PressurePlate implements Listener {

	public Listener_PressurePlate getInstance() {
		return instance;
	}

	private static Listener_PressurePlate instance;
	private List<Material> supportedMaterials;

	public Listener_PressurePlate() {
		System.out.println(US.OUT_PREFIX + "Initializing pressure plate activation event listener" + US.THREE_DOTS);
		instance = this;

		supportedMaterials = new ArrayList<Material>();
		supportedMaterials.add(Material.ACACIA_PRESSURE_PLATE);
		supportedMaterials.add(Material.BIRCH_PRESSURE_PLATE);
		supportedMaterials.add(Material.CRIMSON_PRESSURE_PLATE);
		supportedMaterials.add(Material.DARK_OAK_PRESSURE_PLATE);
		supportedMaterials.add(Material.HEAVY_WEIGHTED_PRESSURE_PLATE);
		supportedMaterials.add(Material.JUNGLE_PRESSURE_PLATE);
		supportedMaterials.add(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
		supportedMaterials.add(Material.OAK_PRESSURE_PLATE);
		supportedMaterials.add(Material.POLISHED_BLACKSTONE_PRESSURE_PLATE);
		supportedMaterials.add(Material.SPRUCE_PRESSURE_PLATE);
		supportedMaterials.add(Material.STONE_PRESSURE_PLATE);
		supportedMaterials.add(Material.WARPED_PRESSURE_PLATE);
	}

	@EventHandler
	public void pressurePlatePress(PlayerInteractEvent e) {
		if (!e.getAction().equals(Action.PHYSICAL)) {
			return;
		}

		Block ablock = e.getClickedBlock();


		if (!supportedMaterials.contains(ablock.getType())) {
			return;
		}

		Player player = e.getPlayer();
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		boolean onParkour = pdc.get(US.onParkourKey, PersistentDataType.INTEGER) == 1;
		String currentParkour = pdc.get(US.currentParkourKey, PersistentDataType.STRING);
		int currentCheckpoint = pdc.get(US.currentCheckpointKey, PersistentDataType.INTEGER);


		if (onParkour) {
			List<Parkour> parkours = Main.getInstance().parkours;
			Parkour parkour = null;

			for (int i = 0; i < parkours.size(); i++) {
				if(parkours.get(i).name == currentParkour) {
					parkour = parkours.get(i);
				}
			}

			if(parkour == null) {
				pdc.set(US.onParkourKey, PersistentDataType.INTEGER, 0);
				return;
			}

			if(!(parkour.checkpoints.size() > currentCheckpoint)) {
				//Theoretically never reached
				ParkourHelper.finishParkour(player,parkour);
				return;
			}

			if (parkour.checkpoints.get(currentCheckpoint).location.distance(ablock.getLocation()) < 0.25d) {
				if(parkour.checkpoints.size() == currentCheckpoint+1) {
					ParkourHelper.finishParkour(player,parkour);
					return;
				}
				ParkourHelper.startCheckpoint(player, parkour, currentCheckpoint + 1);
			}else if (currentCheckpoint > 0 && parkour.checkpoints.get(currentCheckpoint-1).location.distance(ablock.getLocation()) < 0.25d) {
				ParkourHelper.restartCheckpoint(player, parkour, currentCheckpoint);

			} else {
				if(parkour.checkpoints.size() > 0) {
					for (int i = 0; i < parkour.checkpoints.get(currentCheckpoint-1).effectPoints.size(); i++) {
						if (parkour.checkpoints.get(currentCheckpoint-1).effectPoints.get(i).location.distance(ablock.getLocation()) < 0.25d) {
							ParkourHelper.applyEffect(player, parkour.checkpoints.get(currentCheckpoint-1).effectPoints.get(i));
						}
					}
				}
				else {
					for (int i = 0; i < parkour.startCheckpoint.effectPoints.size(); i++) {
						if (parkour.startCheckpoint.effectPoints.get(i).location.distance(ablock.getLocation()) < 0.25d) {
							ParkourHelper.applyEffect(player, parkour.startCheckpoint.effectPoints.get(i));
						}
					}
				}
			}


		} else {
			for (Parkour parkour : Main.getInstance().parkours) {
				if (parkour.startCheckpoint != null) {
					if (parkour.startCheckpoint.location.distance(ablock.getLocation()) < 0.25d) {
						if(!onParkour) {
							ParkourHelper.startParkour(player, parkour);
						}
						else {
							ParkourHelper.restartParkour(player, parkour);
						}
					}
				}
			}
		}
	}
}
