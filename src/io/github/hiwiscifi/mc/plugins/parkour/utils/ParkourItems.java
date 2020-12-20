package io.github.hiwiscifi.mc.plugins.parkour.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import io.github.hiwiscifi.mc.plugins.parkour.Main;
import net.md_5.bungee.api.ChatColor;

public class ParkourItems {

	/** function to handle interaction items in the hotbar
	 * @param player the player who interacted with an item
	 * @param item The Item the player right-clicked with */
	public static void playerInteracted(Player player, ItemStack item) {
		PersistentDataContainer pdc = item.getItemMeta().getPersistentDataContainer();

		if (!pdc.has(US.itemInteractionTypeKey, PersistentDataType.STRING)) { return; }
		if (player.getPersistentDataContainer().get(US.onParkourKey, PersistentDataType.INTEGER) != 1) { return; }
		String itemConfigString = pdc.get(US.itemInteractionTypeKey, PersistentDataType.STRING);

		// get current parkour
		String currentParkour = player.getPersistentDataContainer().get(US.currentParkourKey, PersistentDataType.STRING);
		List<Parkour> parkours = Main.getInstance().parkours;
		Parkour parkour = null;
		for (int i = 0; i < parkours.size(); i++) {
			if(parkours.get(i).name == currentParkour) {
				parkour = parkours.get(i);
			}
		}

		if(parkour == null) {
			return;
		}

		switch(itemConfigString) {
		case US.itemVal_reset_checkpoint:
			ParkourHelper.resetToCheckpoint(player);
			break;
		case US.itemVal_reset_start:
			ParkourHelper.resetToStart(player);
			break;
		case US.itemVal_cancelParkour:
			if (player.isSneaking()) ParkourHelper.resetToStart(player);
			ParkourLogic.totalAbortion(player);
			player.sendMessage(US.OUT_PREFIX + "You are no longer on any parkour");
			break;
		}
	}

	public static void setPlayerInventory(Player player) {
		player.getInventory().clear();

		// reset checkpoint
		ItemStack resetCpItem = new ItemStack(Material.HEAVY_WEIGHTED_PRESSURE_PLATE);
		resetCpItem.setAmount(1);
		ItemMeta resetCpItemMeta = resetCpItem.getItemMeta();
		resetCpItemMeta.setDisplayName("reset to last Checkpoint");
		resetCpItemMeta.getPersistentDataContainer().set(US.itemInteractionTypeKey, PersistentDataType.STRING, US.itemVal_reset_checkpoint);
		List<String> resetCpItemLore = new ArrayList<String>();
		resetCpItemLore.add(ChatColor.AQUA + "right-click to teleport back to the last checkpoint");
		resetCpItemMeta.setLore(resetCpItemLore);
		resetCpItem.setItemMeta(resetCpItemMeta);
		player.getInventory().setItem(3, resetCpItem);

		// reset start
		ItemStack resetStartItem = new ItemStack(Material.OAK_DOOR);
		resetStartItem.setAmount(1);
		ItemMeta resetStartItemMeta = resetStartItem.getItemMeta();
		resetStartItemMeta.setDisplayName("reset to parkour start");
		resetStartItemMeta.getPersistentDataContainer().set(US.itemInteractionTypeKey, PersistentDataType.STRING, US.itemVal_reset_start);
		List<String> resetStartItemLore = new ArrayList<String>();
		resetStartItemLore.add(ChatColor.AQUA + "right-click to teleport back to the parkour start");
		resetStartItemMeta.setLore(resetStartItemLore);
		resetStartItem.setItemMeta(resetStartItemMeta);
		player.getInventory().setItem(4, resetStartItem);

		// cancel parkour
		ItemStack cancelParkourItem = new ItemStack(Material.BLACK_BED);
		cancelParkourItem.setAmount(1);
		ItemMeta cancelParkourItemMeta = cancelParkourItem.getItemMeta();
		cancelParkourItemMeta.setDisplayName("cancel parkour");
		cancelParkourItemMeta.getPersistentDataContainer().set(US.itemInteractionTypeKey, PersistentDataType.STRING, US.itemVal_cancelParkour);
		List<String> cancelParkourItemLore = new ArrayList<String>();
		cancelParkourItemLore.add(ChatColor.AQUA + "right-click to cancel the current parkour to be able to start another one");
		cancelParkourItemLore.add(ChatColor.DARK_PURPLE + "sneak while clicking to telport back to the start");
		cancelParkourItemMeta.setLore(cancelParkourItemLore);
		cancelParkourItem.setItemMeta(cancelParkourItemMeta);
		player.getInventory().setItem(5, cancelParkourItem);

		player.closeInventory();
		player.getInventory().setHeldItemSlot(3);
	}
}
