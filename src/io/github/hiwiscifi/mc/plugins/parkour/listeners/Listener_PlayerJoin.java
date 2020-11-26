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
		
		NamespacedKey key1 = new NamespacedKey(Main.getInstance(), "parkour_currentParkour");
		if(!pdc.has(key1, PersistentDataType.STRING)){
			pdc.set(key1, PersistentDataType.STRING,"");
		}
		
		NamespacedKey key2 = new NamespacedKey(Main.getInstance(), "parkour_onParkour");
		//TODO change data type
		if(!pdc.has(key2, PersistentDataType.INTEGER)){
			pdc.set(key2, PersistentDataType.INTEGER,0);
		}
		
		NamespacedKey key3 = new NamespacedKey(Main.getInstance(), "parkour_currentCheckpoint");
		//TODO change data type
		if(!pdc.has(key3, PersistentDataType.INTEGER)){
			pdc.set(key3, PersistentDataType.INTEGER,-1);
		}
	}
}
