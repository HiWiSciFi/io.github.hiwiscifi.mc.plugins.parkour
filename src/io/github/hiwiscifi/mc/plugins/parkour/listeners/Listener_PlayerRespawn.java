package io.github.hiwiscifi.mc.plugins.parkour.listeners;

import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class Listener_PlayerRespawn implements Listener {
	
	public static Listener_PlayerRespawn Instance;
	
	public Listener_PlayerRespawn() {
		System.out.println("[Parkour] Initializing player post respawn event listener...");
		Instance = this;
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player player = e.getPlayer();
		Set<String> tags = player.getScoreboardTags();
		if (tags.contains("[Parkour]DoingParkour")) {
			// set respawn to last visited checkpoint
		}
	}
}
