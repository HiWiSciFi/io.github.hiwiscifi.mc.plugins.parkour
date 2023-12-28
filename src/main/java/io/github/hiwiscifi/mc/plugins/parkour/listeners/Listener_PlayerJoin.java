package io.github.hiwiscifi.mc.plugins.parkour.listeners;


import io.github.hiwiscifi.mc.plugins.parkour.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import io.github.hiwiscifi.mc.plugins.parkour.utils.StringUtil;

public class Listener_PlayerJoin implements Listener {

	public static Listener_PlayerJoin getInstance() { return instance; }
	private static Listener_PlayerJoin instance;

	public Listener_PlayerJoin() {
		Main.getInstance().getLogger().info("Initializing player join event listener...");
		instance = this;
	}

	//TODO add extract functions
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e)
	{
		//event.setJoinMessage("Welcome, " + event.getPlayer().getName() + "!");
		Player p = e.getPlayer();
		PersistentDataContainer pdc = p.getPersistentDataContainer();

		if(!pdc.has(StringUtil.currentCheckpointKey, PersistentDataType.INTEGER))
		{
			pdc.set(StringUtil.currentCheckpointKey, PersistentDataType.INTEGER,0);
			// 0 => startCheckpoint
			// 1 => checkpoints(0)
		}

		if(!pdc.has(StringUtil.currentParkourKey, PersistentDataType.STRING)) {
			pdc.set(StringUtil.currentParkourKey, PersistentDataType.STRING, "");
		}


		pdc.set(StringUtil.onParkourKey, PersistentDataType.INTEGER,0);

	}
}
