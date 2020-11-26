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
import io.github.hiwiscifi.mc.plugins.parkour.utils.UtilityStrings;

public class Listener_PressurePlate implements Listener {

	public Listener_PressurePlate getInstance() {
		return instance;
	}

	private static Listener_PressurePlate instance;
	private List<Material> supportedMaterials;

	public Listener_PressurePlate() {
		System.out.println(UtilityStrings.OUT_PREFIX + UtilityStrings.getString(44) + UtilityStrings.THREE_DOTS);
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

				NamespacedKey onParkourKey = new NamespacedKey(Main.getInstance(), UtilityStrings.getString(40));
				NamespacedKey currentParkourKey = new NamespacedKey(Main.getInstance(), UtilityStrings.getString(39));
				NamespacedKey currentCheckpointKey = new NamespacedKey(Main.getInstance(), UtilityStrings.getString(41));

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
					//TODO HIER AEBEITET MAX
				} else {
					for (Parkour p : Main.getInstance().parkours) {
						if (p.startCheckpoint != null) {
							if (p.startCheckpoint.distance(ablock.getLocation()) < 0.25d) {
								if(currentParkour != p.name) {

									pdc.set(currentParkourKey, PersistentDataType.STRING, p.name);
									pdc.set(onParkourKey, PersistentDataType.INTEGER, 1);

									player.sendMessage(UtilityStrings.OUT_PREFIX + UtilityStrings.getString(45) + UtilityStrings.addSpace(p.name, UtilityStrings.BEGIN_SPACE | UtilityStrings.END_SPACE) + UtilityStrings.getString(3));
									//TODO set item and check gamemode
								}
							}
						}
					}
				}
			}
		}
	}
}
