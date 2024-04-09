package io.github.hiwiscifi.mc.plugins.parkour.listeners;

import io.github.hiwiscifi.mc.plugins.parkour.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class Listener_PlayerDamage implements Listener {

	public Listener_PlayerDamage getInstance() { return instance; }
	private final Listener_PlayerDamage instance;

	public Listener_PlayerDamage() {
		instance = this;
		Main.getInstance().getLogger().info("Initializing player damage event listener...");
	}

	@EventHandler
	public void onEntityDamaged(EntityDamageEvent e) {
		if (e.getCause() == DamageCause.VOID) {
			if (e.getEntity() instanceof Player player) {
                player.setHealth(0.0d);
			}
		}
	}
}
