package io.github.hiwiscifi.mc.plugins.parkour.utils;

import java.util.ArrayList;
import java.util.List;

import net.kyori.adventure.text.Component;
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

		if (!pdc.has(StringUtil.itemInteractionTypeKey, PersistentDataType.STRING)) { return; }
		if (player.getPersistentDataContainer().get(StringUtil.onParkourKey, PersistentDataType.INTEGER) != 1) { return; }
		String itemConfigString = pdc.get(StringUtil.itemInteractionTypeKey, PersistentDataType.STRING);

		// get current parkour
		String currentParkour = player.getPersistentDataContainer().get(StringUtil.currentParkourKey, PersistentDataType.STRING);
		List<Parkour> parkours = Main.getInstance().parkours;
		Parkour parkour = null;
        for (Parkour value : parkours) {
            if (value.name.equals(currentParkour)) {
                parkour = value;
            }
        }

		if(parkour == null) {
			return;
		}

		switch(itemConfigString) {
			case StringUtil.itemVal_reset_checkpoint:
				ParkourHelper.resetToCheckpoint(player);
				break;
			case StringUtil.itemVal_reset_start:
				ParkourHelper.resetToStart(player);
				break;
			case StringUtil.itemVal_cancelParkour:
				if (player.isSneaking()) ParkourHelper.resetToStart(player);
				ParkourLogic.totalAbortion(player);
				player.sendMessage(StringUtil.OUT_PREFIX
					.append(Component.text("You are no longer on any parkour"))
				);
				break;
		}
	}

	/** set up a players inventory to have only the interaction menu items
	 * @param player the player whose inventory shall be set up*/
	public static void setPlayerInventory(Player player) {
		player.getInventory().clear();

		// reset checkpoint
		ItemStack resetCpItem = new ItemStack(Material.HEAVY_WEIGHTED_PRESSURE_PLATE);
		resetCpItem.setAmount(1);
		ItemMeta resetCpItemMeta = resetCpItem.getItemMeta();
		resetCpItemMeta.displayName(Component.text("reset to last Checkpoint"));
		resetCpItemMeta.getPersistentDataContainer().set(StringUtil.itemInteractionTypeKey, PersistentDataType.STRING, StringUtil.itemVal_reset_checkpoint);
		List<Component> resetCpItemLore = new ArrayList<>();
		resetCpItemLore.add(Component.text("right-click to teleport back to the last checkpoint", StringUtil.AQUA));
		resetCpItemMeta.lore(resetCpItemLore);
		resetCpItem.setItemMeta(resetCpItemMeta);
		player.getInventory().setItem(3, resetCpItem);

		// reset start
		ItemStack resetStartItem = new ItemStack(Material.OAK_DOOR);
		resetStartItem.setAmount(1);
		ItemMeta resetStartItemMeta = resetStartItem.getItemMeta();
		resetStartItemMeta.displayName(Component.text("reset to parkour start"));
		resetStartItemMeta.getPersistentDataContainer().set(StringUtil.itemInteractionTypeKey, PersistentDataType.STRING, StringUtil.itemVal_reset_start);
		List<Component> resetStartItemLore = new ArrayList<>();
		resetStartItemLore.add(Component.text("right-click to teleport back to the parkour start", StringUtil.AQUA));
		resetStartItemMeta.lore(resetStartItemLore);
		resetStartItem.setItemMeta(resetStartItemMeta);
		player.getInventory().setItem(4, resetStartItem);

		// cancel parkour
		ItemStack cancelParkourItem = new ItemStack(Material.BLACK_BED);
		cancelParkourItem.setAmount(1);
		ItemMeta cancelParkourItemMeta = cancelParkourItem.getItemMeta();
		cancelParkourItemMeta.displayName(Component.text("cancel parkour"));
		cancelParkourItemMeta.getPersistentDataContainer().set(StringUtil.itemInteractionTypeKey, PersistentDataType.STRING, StringUtil.itemVal_cancelParkour);
		List<Component> cancelParkourItemLore = new ArrayList<>();
		cancelParkourItemLore.add(Component.text("right-click to cancel the current parkour to be able to start another one", StringUtil.AQUA));
		cancelParkourItemLore.add(Component.text("sneak while clicking to teleport back to the start", StringUtil.DARK_PURPLE));
		cancelParkourItemMeta.lore(cancelParkourItemLore);
		cancelParkourItem.setItemMeta(cancelParkourItemMeta);
		player.getInventory().setItem(5, cancelParkourItem);

		player.closeInventory();
		player.getInventory().setHeldItemSlot(3);
	}
}
