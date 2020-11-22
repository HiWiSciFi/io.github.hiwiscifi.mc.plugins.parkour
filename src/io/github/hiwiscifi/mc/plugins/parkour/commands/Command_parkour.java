package io.github.hiwiscifi.mc.plugins.parkour.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_parkour implements CommandExecutor {

	public Command_parkour() {
		System.out.println("Initializing parkour command...");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		// parkour add course <name>
		// parkour remove course <name>
		// parkour list
		
		// parkour checkpoint add <#> <name>
		// parkour checkpoint remove <#> <name>
		
		// parkour world add <world name>
		// parkour world remove <world name>
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args[0].equals("create")) {
				
			} else if (args[0].equals("")) {
				
			}
			return true;
		}
		return false;
	}
}
