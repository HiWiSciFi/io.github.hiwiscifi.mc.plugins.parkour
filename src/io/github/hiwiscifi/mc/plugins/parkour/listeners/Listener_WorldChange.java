package io.github.hiwiscifi.mc.plugins.parkour.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import io.github.hiwiscifi.mc.plugins.parkour.utils.ParkourHelper;
import io.github.hiwiscifi.mc.plugins.parkour.utils.US;

public class Listener_WorldChange implements Listener {

	public static Listener_WorldChange getInstance() { return instance; }
	private static Listener_WorldChange instance;

	public Listener_WorldChange() {
		//TODO Change Text
		System.out.println(US.OUT_PREFIX + US.getString(33) + US.THREE_DOTS);
		instance = this;
	}

	public void onWorldChange(PlayerChangedWorldEvent event) {

		Player player = event.getPlayer();

		String worldName = player.getWorld().getName();

		switch(worldName) {
		case "parcours":
			//TODO: Finish this shit, cause I am to incompetent for this
			break;
		default:
			ParkourHelper.totalAbortion(player);


		}
	}


}