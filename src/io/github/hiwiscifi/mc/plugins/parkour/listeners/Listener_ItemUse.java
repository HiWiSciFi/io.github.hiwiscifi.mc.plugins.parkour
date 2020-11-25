package io.github.hiwiscifi.mc.plugins.parkour.listeners;




import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class Listener_ItemUse implements Listener{

	public static Listener_ItemUse Instance;

	public Listener_ItemUse() {
		System.out.println("[Parkour] Initializing item use event listener...");
		Instance = this;
	}



	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event)
	{

		Player p = event.getPlayer();

		System.out.println(p.getInventory().getItemInMainHand());

	}
}
