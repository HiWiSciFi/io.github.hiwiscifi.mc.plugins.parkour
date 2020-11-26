package io.github.hiwiscifi.mc.plugins.parkour.listeners;




import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import io.github.hiwiscifi.mc.plugins.parkour.Main;
import io.github.hiwiscifi.mc.plugins.parkour.utils.PlayerTeleport;

public class Listener_ItemUse implements Listener{

	public static Listener_ItemUse getInstance() { return instance; }
	private static Listener_ItemUse instance;

	public Listener_ItemUse() {
		System.out.println("[Parkour] Initializing item use event listener...");
		instance = this;
	}



	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		
		ItemStack item = player.getInventory().getItemInMainHand();
		
		Material mat = item.getType();
		
		if(mat.isAir()) {
			return;
		}
		
		ItemMeta itemMeta = item.getItemMeta();
		
		PersistentDataContainer itemData = itemMeta.getPersistentDataContainer();
		
		NamespacedKey key = new NamespacedKey(Main.getInstance(),"parkour_func");
		itemData.has(key, PersistentDataType.STRING);
		
		String func = itemData.get(key, PersistentDataType.STRING);
		
		switch(func) {
			case "resetToCheckpoint":
				player.teleport(PlayerTeleport.calculateCheckpointLocation(player));
				break;
			case "resetToStart":
				player.teleport(PlayerTeleport.calculateParkourStartLocation(player));
				break;
			case "resetToSpawn":
				player.teleport(player.getWorld().getSpawnLocation());
				//TODO pluginworld speichern hilfe????
				break;				
		}

	}
}
