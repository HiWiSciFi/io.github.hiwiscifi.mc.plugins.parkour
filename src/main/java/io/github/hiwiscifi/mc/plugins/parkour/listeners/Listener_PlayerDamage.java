package io.github.hiwiscifi.mc.plugins.parkour.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import io.github.hiwiscifi.mc.plugins.parkour.utils.US;

public class Listener_PlayerDamage implements Listener {

	public Listener_PlayerDamage getInstance() { return instance; }
	private Listener_PlayerDamage instance;

	public Listener_PlayerDamage() {
		instance = this;
		System.out.println(US.OUT_PREFIX + "Initializing player damage event listener" + US.THREE_DOTS);
	}

	@EventHandler
	public void onEntityDamaged(EntityDamageEvent e) {
		if (e.getCause() == DamageCause.VOID) {
			if (e.getEntity() instanceof Player) {
				Player player = (Player)e.getEntity();
				player.setHealth(0.0d);
			}
		}
	}
}
