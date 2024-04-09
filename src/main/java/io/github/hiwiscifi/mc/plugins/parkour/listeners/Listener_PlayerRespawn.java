package io.github.hiwiscifi.mc.plugins.parkour.listeners;

import io.github.hiwiscifi.mc.plugins.parkour.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import io.github.hiwiscifi.mc.plugins.parkour.utils.ParkourHelper;
import io.github.hiwiscifi.mc.plugins.parkour.utils.ParkourLogic;

public class Listener_PlayerRespawn implements Listener {

	public Listener_PlayerRespawn getInstance() { return instance; }
	private static Listener_PlayerRespawn instance;

	//TODO change naming
	public Listener_PlayerRespawn() {
		Main.getInstance().getLogger().info("Initializing player respawn event listener...");
		instance = this;
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player player = e.getPlayer();

		Location respawnLocation = ParkourHelper.calculateCheckpointLocation(player);

		if(respawnLocation != null) {
			e.setRespawnLocation(respawnLocation);
			ParkourLogic.restartCheckpoint(player);
			Main.getInstance().getLogger().info("respawning");
		}
	}
}
