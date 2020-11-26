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

public class Listener_PressurePlate implements Listener {

	public Listener_PressurePlate getInstance() { return instance; }
	private static Listener_PressurePlate instance;
	private List<Material> supportedMaterials;

	public Listener_PressurePlate() {
		System.out.println("[Parkour] Initializing pressure plate activation event listener...");
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

				if (false/*if already on parkour*/) {

				} else
					for (Parkour p : Main.getInstance().parkours)
						if (p.startCheckpoint != null)
							if (p.startCheckpoint.distance(ablock.getLocation()) < 0.25d) {
								NamespacedKey key1 = new NamespacedKey(Main.getInstance(), "parkour_currentParkour");
								pdc.set(key1, PersistentDataType.STRING, p.name);
								NamespacedKey key2 = new NamespacedKey(Main.getInstance(), "parkour_onParkour");
								pdc.set(key2, PersistentDataType.INTEGER, 1);
								player.sendMessage("You are now on the " + p.name + " parkour");
							}

				/*for (Parkour p : Main.Instance.parkours) {
					if (p.startCheckpoint != null) {
						if (p.startCheckpoint.distance(ablock.getLocation()) < 0.25d) {
							e.getPlayer().sendMessage("Start for parkour " + p.name + " reached");
						} else {
							for (Location l : p.checkpoints) {
								if (l.distance(ablock.getLocation()) < 0.25d) {
									e.getPlayer().sendMessage("Checkpoint from parkour " + p.name + " reached");
								}
							}
						}
					}
				}*/
			}
		}
	}
}
