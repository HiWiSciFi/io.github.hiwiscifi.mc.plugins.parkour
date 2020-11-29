package io.github.hiwiscifi.mc.plugins.parkour.listeners;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import io.github.hiwiscifi.mc.plugins.parkour.utils.US;

public class Listener_PlayerJoin implements Listener {

	public static Listener_PlayerJoin getInstance() { return instance; }
	private static Listener_PlayerJoin instance;

	public Listener_PlayerJoin() {
		System.out.println(US.OUT_PREFIX + US.getString(38) + US.THREE_DOTS);
		instance = this;
	}

	//TODO add extract functions
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e)
	{
		//event.setJoinMessage("Welcome, " + event.getPlayer().getName() + "!");
		Player p = e.getPlayer();
		PersistentDataContainer pdc = p.getPersistentDataContainer();
		
		if(!pdc.has(US.currentCheckpointKey, PersistentDataType.INTEGER))
		{
			pdc.set(US.currentCheckpointKey, PersistentDataType.INTEGER,0);
			// 0 => startCheckpoint
			// 1 => checkpoints(0)
		}
		
		if(!pdc.has(US.currentParkourKey, PersistentDataType.STRING)) {
			pdc.set(US.currentParkourKey, PersistentDataType.STRING,"");
		}
		
		
		pdc.set(US.onParkourKey, PersistentDataType.INTEGER,0);

	}
}
