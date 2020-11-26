package io.github.hiwiscifi.mc.plugins.parkour.listeners;


import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import io.github.hiwiscifi.mc.plugins.parkour.Main;

public class Listener_PlayerJoin implements Listener {

	public static Listener_PlayerJoin getInstance() { return instance; }
	private static Listener_PlayerJoin instance;

	public Listener_PlayerJoin() {
		System.out.println("[Parkour] Initializing player join event listener...");
		instance = this;
	}

	//TODO add extract functions
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e)
	{
		//event.setJoinMessage("Welcome, " + event.getPlayer().getName() + "!");
		Player p = e.getPlayer();
		PersistentDataContainer pdc = p.getPersistentDataContainer();

		NamespacedKey currentParkourKey = new NamespacedKey(Main.getInstance(), "parkour_currentParkour");
		pdc.set(currentParkourKey, PersistentDataType.STRING,"");


		NamespacedKey onParkourKey = new NamespacedKey(Main.getInstance(), "parkour_onParkour");
		pdc.set(onParkourKey, PersistentDataType.INTEGER,0);

		NamespacedKey currentCheckpointKey = new NamespacedKey(Main.getInstance(), "parkour_currentCheckpoint");
		pdc.set(currentCheckpointKey, PersistentDataType.INTEGER,-1);

	}
}
