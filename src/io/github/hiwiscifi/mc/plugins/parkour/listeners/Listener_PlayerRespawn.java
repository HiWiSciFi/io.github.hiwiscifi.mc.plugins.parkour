package io.github.hiwiscifi.mc.plugins.parkour.listeners;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import io.github.hiwiscifi.mc.plugins.parkour.Main;
import io.github.hiwiscifi.mc.plugins.parkour.utils.PlayerTeleport;

public class Listener_PlayerRespawn implements Listener {
	
	public Listener_PlayerRespawn getInstance() { return instance; }
	private static Listener_PlayerRespawn instance;
	
	//TODO change naming
	public Listener_PlayerRespawn() {
		System.out.println("[Parkour] Initializing player post respawn event listener...");
		instance = this;
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		//TODO remove coments
		//von Max für Hax <3
		Player player = e.getPlayer();
		
		Location respawnLocation = PlayerTeleport.calculateCheckpointLocation(player);
		
		if(respawnLocation != null) {
			e.setRespawnLocation(respawnLocation);
		}
		
		//Set<String> tags = player.getScoreboardTags();
		//if (tags.contains("[Parkour]DoingParkour")) {
			
			
			// set respawn to last visited checkpoint
		//}
	}
}
