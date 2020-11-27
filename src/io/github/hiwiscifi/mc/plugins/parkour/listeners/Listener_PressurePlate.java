package io.github.hiwiscifi.mc.plugins.parkour.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import io.github.hiwiscifi.mc.plugins.parkour.Main;
import io.github.hiwiscifi.mc.plugins.parkour.Parkour;
import io.github.hiwiscifi.mc.plugins.parkour.utils.US;

public class Listener_PressurePlate implements Listener {

	public Listener_PressurePlate getInstance() {
		return instance;
	}

	private static Listener_PressurePlate instance;
	private List<Material> supportedMaterials;

	public Listener_PressurePlate() {
		System.out.println(US.OUT_PREFIX + US.getString(44) + US.THREE_DOTS);
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
		if (e.getAction().equals(Action.PHYSICAL)) {
			Block ablock = e.getClickedBlock();
			if (supportedMaterials.contains(ablock.getType())) {

				Player player = e.getPlayer();
				PersistentDataContainer pdc = player.getPersistentDataContainer();

				NamespacedKey onParkourKey = new NamespacedKey(Main.getInstance(), US.getString(40));
				NamespacedKey currentParkourKey = new NamespacedKey(Main.getInstance(), US.getString(39));
				NamespacedKey currentCheckpointKey = new NamespacedKey(Main.getInstance(), US.getString(41));

				boolean onParkour = pdc.get(onParkourKey, PersistentDataType.INTEGER)%2 == 1;
				String currentParkour = pdc.get(currentParkourKey, PersistentDataType.STRING);
				int currentCheckpoint = pdc.get(currentCheckpointKey, PersistentDataType.INTEGER);

				if (onParkour) {
					List<Parkour> parkours = Main.getInstance().parkours;
					Parkour parkour = null;

					for (int i = 0; i < parkours.size(); i++) {
						if(parkours.get(i).name == currentParkour) {
							parkour = parkours.get(i);
						}
					}

					if(parkour == null) {
						pdc.set(onParkourKey, PersistentDataType.INTEGER, 0);
						return;
					}

					if(!(parkour.checkpoints.size() > currentCheckpoint)) {
						//Theoretically never reached
						finishParkour(player,parkour);
						return;
					}

					if (parkour.checkpoints.get(currentCheckpoint).distance(ablock.getLocation()) < 0.25d) {
						if(parkour.checkpoints.size() == currentCheckpoint+1) {
							finishParkour(player,parkour);
							return;
						}
						startCheckpoint(player, parkour, currentCheckpoint + 1);
					}
					if (currentCheckpoint > 0 && parkour.checkpoints.get(currentCheckpoint-1).distance(ablock.getLocation()) < 0.25d) {
						restartCheckpoint(player, parkour, currentCheckpoint);
					}


				} else {
					for (Parkour parkour : Main.getInstance().parkours) {
						if (parkour.startCheckpoint != null) {
							if (parkour.startCheckpoint.distance(ablock.getLocation()) < 0.25d) {
								if(!onParkour) {
									startParkour(player, parkour);
								}
								else {
									restartParkour(player, parkour);
								}
							}
						}
					}
				}
			}
		}
	}
	
	private void startParkour(Player player, Parkour parkour) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		NamespacedKey onParkourKey = new NamespacedKey(Main.getInstance(), US.getString(40));
		NamespacedKey currentParkourKey = new NamespacedKey(Main.getInstance(), US.getString(39));
		NamespacedKey currentCheckpointKey = new NamespacedKey(Main.getInstance(), US.getString(41));
		
		pdc.set(currentCheckpointKey,PersistentDataType.INTEGER,0);
		pdc.set(currentParkourKey, PersistentDataType.STRING, parkour.name);
		pdc.set(onParkourKey, PersistentDataType.INTEGER, 1);

		player.sendMessage(US.OUT_PREFIX + US.getString(45) + US.addSpace(parkour.name, true, true) + US.getString(3));
		//TODO set item and check gamemode store those items and remove them after parkour is finished or aborted
		if(parkour.checkpoints.size() == 0) {
			finishParkour(player,parkour);
		}
		
		//TODO start timer 
	}
	
	private void restartParkour(Player player, Parkour parkour) {
		
		//TODO reset timer
	}
	
	private void startCheckpoint(Player player, Parkour parkour, int checkpoint) {
		NamespacedKey currentCheckpointKey = new NamespacedKey(Main.getInstance(), US.getString(41));

		PersistentDataContainer pdc = player.getPersistentDataContainer();

		pdc.set(currentCheckpointKey, PersistentDataType.INTEGER, checkpoint);
		//TODO start checkpoint timer
		
	}
	
	private void restartCheckpoint(Player player, Parkour parkour, int checkpoint) {
		//TODO reset checkpoint timer
		
	}

	private void finishParkour(Player player, Parkour parkour) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		NamespacedKey onParkourKey = new NamespacedKey(Main.getInstance(), US.getString(40));

		pdc.set(onParkourKey, PersistentDataType.INTEGER, 0);

		System.out.println("completed");
		//TODO broadcast
		
		//TODO ausgelagertes timer zeug
	}
	
}
