package io.github.hiwiscifi.mc.plugins.parkour.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import io.github.hiwiscifi.mc.plugins.parkour.utils.ParkourHelper;
import io.github.hiwiscifi.mc.plugins.parkour.utils.US;

public class Listener_PlayerRespawn implements Listener {

	public Listener_PlayerRespawn getInstance() { return instance; }
	private static Listener_PlayerRespawn instance;

	//TODO change naming
	public Listener_PlayerRespawn() {
		System.out.println(US.OUT_PREFIX + US.getString(42) + US.THREE_DOTS);
		instance = this;
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		//TODO remove coments
		//von Max für Hax <3
		Player player = e.getPlayer();

		Location respawnLocation = ParkourHelper.calculateCheckpointLocation(player);

		if(respawnLocation != null) {
			e.setRespawnLocation(respawnLocation);
			System.out.println(US.OUT_PREFIX + US.getString(43));
		}

		//Set<String> tags = player.getScoreboardTags();
		//if (tags.contains("[Parkour]DoingParkour")) {


		// set respawn to last visited checkpoint
		//}
	}
}
