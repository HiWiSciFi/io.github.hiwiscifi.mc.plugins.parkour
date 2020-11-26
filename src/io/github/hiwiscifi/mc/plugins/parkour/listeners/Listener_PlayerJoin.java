package io.github.hiwiscifi.mc.plugins.parkour.listeners;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listener_PlayerJoin implements Listener {

	public static Listener_PlayerJoin getInstance() { return instance; }
	private static Listener_PlayerJoin instance;

	public Listener_PlayerJoin() {
		System.out.println("[Parkour] Initializing player join event listener...");
		instance = this;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		//event.setJoinMessage("Welcome, " + event.getPlayer().getName() + "!");
	}
}
