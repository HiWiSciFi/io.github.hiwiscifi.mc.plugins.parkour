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
import io.github.hiwiscifi.mc.plugins.parkour.utils.ParkourHelper;
import io.github.hiwiscifi.mc.plugins.parkour.utils.US;

public class Listener_ItemUse implements Listener{

	public static Listener_ItemUse getInstance() { return instance; }
	private static Listener_ItemUse instance;

	public Listener_ItemUse() {
		System.out.println(US.OUT_PREFIX + US.getString(33) + US.THREE_DOTS);
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
		//TODO change name
		NamespacedKey funcKey = new NamespacedKey(Main.getInstance(), US.getString(34));
		if(itemData.has(funcKey, PersistentDataType.STRING)) {
			String func = itemData.get(funcKey, PersistentDataType.STRING);

			switch(func) {
			case "resetToCheckpoint":
				player.teleport(ParkourHelper.calculateCheckpointLocation(player));
				break;
			case "resetToStart":
				player.teleport(ParkourHelper.calculateParkourStartLocation(player));
				break;
			case "resetToSpawn":
				player.teleport(player.getWorld().getSpawnLocation() );
				//TODO pluginworld speichern hilfe????
				break;
			}
		}

	}
}
