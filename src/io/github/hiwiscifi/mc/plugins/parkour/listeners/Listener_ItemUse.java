package io.github.hiwiscifi.mc.plugins.parkour.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import io.github.hiwiscifi.mc.plugins.parkour.utils.ParkourItems;
import io.github.hiwiscifi.mc.plugins.parkour.utils.US;

public class Listener_ItemUse implements Listener{

	public static Listener_ItemUse getInstance() { return instance; }
	private static Listener_ItemUse instance;

	public Listener_ItemUse() {
		System.out.println(US.OUT_PREFIX + "Initializing item use event listener" + US.THREE_DOTS);
		instance = this;
	}

	@EventHandler
	public void onPlayerUse(PlayerInteractEvent e)
	{
		Player player = e.getPlayer();

		if (!e.getAction().equals(Action.RIGHT_CLICK_AIR) && !e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			return;
		}
		
		ItemStack item = player.getInventory().getItemInMainHand();

		Material mat = item.getType();

		/*if(mat.isAir()) {
			return;
		}*/

		if (!mat.isAir()) ParkourItems.playerInteracted(player, item);
		
		/*
		ItemMeta itemMeta = item.getItemMeta();

		PersistentDataContainer itemData = itemMeta.getPersistentDataContainer();
		//TODO change name
		NamespacedKey funcKey = new NamespacedKey(Main.getInstance(), "func");
		if(itemData.has(funcKey, PersistentDataType.STRING)) {
			String func = itemData.get(funcKey, PersistentDataType.STRING);

			switch(func) {
			case "resetToCheckpoint":
				player.teleport(ParkourHelper.calculateCheckpointLocation(player, true));
				break;
			case "resetToStart":
				player.teleport(ParkourHelper.calculateParkourStartLocation(player));
				break;
			case "resetToSpawn":
				player.teleport(player.getWorld().getSpawnLocation() );
				//TODO pluginworld speichern hilfe????
				break;
			}
		}*/

	}
}
