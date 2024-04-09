package io.github.hiwiscifi.mc.plugins.parkour.listeners;

import io.github.hiwiscifi.mc.plugins.parkour.Main;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import io.github.hiwiscifi.mc.plugins.parkour.utils.ParkourLogic;
import io.github.hiwiscifi.mc.plugins.parkour.utils.WorldControl;

public class Listener_WorldChange implements Listener {

	public static Listener_WorldChange getInstance() { return instance; }
	private static Listener_WorldChange instance;

	public Listener_WorldChange() {
		Main.getInstance().getLogger().info("Initializing world change event listener...");
		instance = this;
	}

	public void onWorldChange(PlayerChangedWorldEvent e) {
		Player player = e.getPlayer();

		World fromWorld = e.getFrom();
		World toWorld = player.getWorld();

		if (WorldControl.enabled(fromWorld) || WorldControl.enabled(toWorld)) {
			ParkourLogic.totalAbortion(player);
		}
	}
}
