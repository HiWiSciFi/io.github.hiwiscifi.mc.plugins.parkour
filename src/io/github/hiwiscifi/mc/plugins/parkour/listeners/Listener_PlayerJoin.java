package io.github.hiwiscifi.mc.plugins.parkour.listeners;


import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import io.github.hiwiscifi.mc.plugins.parkour.Main;
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

		NamespacedKey currentCheckpointKey = new NamespacedKey(Main.getInstance(), US.getString(41));
		if(!pdc.has(currentCheckpointKey, PersistentDataType.INTEGER))
		{
			pdc.set(currentCheckpointKey, PersistentDataType.INTEGER,0);
			// 0 => startCheckpoint
			// 1 => checkpoints(0)
		}

		NamespacedKey currentParkourKey = new NamespacedKey(Main.getInstance(), US.getString(39));
		if(!pdc.has(currentParkourKey, PersistentDataType.STRING)) {
			pdc.set(currentParkourKey, PersistentDataType.STRING,"");
		}


		NamespacedKey onParkourKey = new NamespacedKey(Main.getInstance(), US.getString(40));
		pdc.set(onParkourKey, PersistentDataType.INTEGER,0);

	}
}
