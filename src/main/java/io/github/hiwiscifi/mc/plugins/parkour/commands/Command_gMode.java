package io.github.hiwiscifi.mc.plugins.parkour.commands;

import java.util.ArrayList;
import java.util.List;

//TODO do string us

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import io.github.hiwiscifi.mc.plugins.parkour.utils.US;
import io.github.hiwiscifi.mc.plugins.parkour.utils.WorldControl;
import org.jetbrains.annotations.NotNull;

public class Command_gMode implements TabExecutor{

	//Singleton implementation
	public static Command_gMode getInstance() { return instance; }
	private static Command_gMode instance;

	public Command_gMode() {
		instance = this;
	}

	//gets executed when Command is executed
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
		//stop Sender is not a Player or no arguments are given (missing game Mode)
		if (!(sender instanceof Player player) || (args.length == 0)) {
			return false;
		}

        if (!WorldControl.enabled(player.getWorld(), player)) {
			return false;
		}

		PersistentDataContainer pdc = player.getPersistentDataContainer();

		//stop if player is not in the parkours world (cheating)
		if(!(pdc.get(US.onParkourKey, PersistentDataType.INTEGER) == 0)) {
			return false;
		}

		// argument 1 is the wanted gamemode and the player gamemode is set to it
		switch (args[0].toLowerCase()) {
			case "creative", "spectator":
				player.setGameMode(GameMode.CREATIVE);
				break;

			case "survival":
				player.setGameMode(GameMode.SURVIVAL);
				break;

			case "adventure":
				player.setGameMode(GameMode.ADVENTURE);
				break;

            default:
				player.sendMessage(US.OUT_PREFIX + args[0].substring(0, 1).toUpperCase() + args[0].substring(1).toLowerCase() + " is not a valid gamemode!");
				return false;
		}
		player.sendMessage(US.OUT_PREFIX + "Your gamemode has been set to " + args[0].substring(0, 1).toUpperCase() + args[0].substring(1).toLowerCase() + "!");
		return true;
	}

	@Override
	public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
		if(args.length == 1) {
			List<String> strings = new ArrayList<>();
			strings.add("creative");
			strings.add("survival");
			strings.add("adventure");
			strings.add("spectator");
			return strings;
		}
		return null;
	}
}
