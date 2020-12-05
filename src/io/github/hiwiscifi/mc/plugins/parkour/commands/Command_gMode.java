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

public class Command_gMode implements TabExecutor{

	//Singleton implementation
	public static Command_gMode getInstance() { return instance; }
	private static Command_gMode instance;

	public Command_gMode() {
		instance = this;
	}

	//gets executed when Command is executed
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		//stop Sender is not a Player or no arguments are given (missing game Mode)
		if (!(sender instanceof Player) || (args.length == 0)) {
			return false;
		}
		Player player = (Player)sender;

		if (!WorldControl.enabled(player.getWorld())) {
			return false;
		}

		PersistentDataContainer pdc = player.getPersistentDataContainer();

		//stop if player is not in the parkours world (cheating)
		if(!(pdc.get(US.onParkourKey, PersistentDataType.INTEGER) == 0)) {
			return false;
		}

		// argument 1 is the wanted gamemode and the player gamemode is set to it
		switch (args[0].toLowerCase()) {
		case "creative":
			player.setGameMode(GameMode.CREATIVE);
			return true;

		case "survival":
			player.setGameMode(GameMode.SURVIVAL);
			return true;

		case "adventure":
			player.setGameMode(GameMode.ADVENTURE);
			return true;

		case "spectator":
			player.setGameMode(GameMode.CREATIVE);
			return true;

		default:
			return false;
		}
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
			if(args.length == 1) {
				List<String> strings = new ArrayList<String>();
					strings.add("creative");
					strings.add("survival");
					strings.add("adventure");
					strings.add("spectator");
					return strings;
			}
			return null;
	}
}
