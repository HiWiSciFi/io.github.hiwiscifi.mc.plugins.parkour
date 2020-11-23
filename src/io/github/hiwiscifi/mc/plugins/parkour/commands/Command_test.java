package io.github.hiwiscifi.mc.plugins.parkour.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_test implements CommandExecutor {

	public static Command_test Instance;
	
	public Command_test() {
		System.out.println("[Parkour] Initializing test command...");
		Instance = this;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			player.sendMessage("You are in world \"" + player.getLocation().getWorld().getName() + "\"");
			return true;
		}
		
		return false;
	}
	
}
