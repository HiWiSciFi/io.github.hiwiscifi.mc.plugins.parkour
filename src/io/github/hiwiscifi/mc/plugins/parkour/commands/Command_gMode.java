package io.github.hiwiscifi.mc.plugins.parkour.commands;

//TODO do string us

import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import io.github.hiwiscifi.mc.plugins.parkour.Main;

public class Command_gMode implements CommandExecutor{

	public static Command_gMode getInstance() { return instance; }
	private static Command_gMode instance;
	
	public Command_gMode() {
		instance = this;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player) || !(args.length == 0)) {
			return false;
		}
		Player player = (Player)sender;
		PersistentDataContainer pdc = player.getPersistentDataContainer();
		
		NamespacedKey onParkourKey = new NamespacedKey(Main.getInstance(), "parkour_onParkour");

		if(!(player.getWorld().getName() == "world") || !((pdc.get(onParkourKey, PersistentDataType.INTEGER) % 2) == 1)) {
			return false;
		}
		
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
}
