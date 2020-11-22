package io.github.hiwiscifi.mc.plugins.parkour.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_parkour implements CommandExecutor {

	public static Command_parkour Instance = null;
	
	public Command_parkour() {
		System.out.println("Initializing parkour command...");
		Instance = this;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		// parkour create <name>
		// parkour delete <name>
		// parkour list
		
		// parkour checkpoint add <#> <name>
		// parkour checkpoint remove <#> <name>
		
		// parkour world add <world name>
		// parkour world remove <world name>
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args[0].equals("create")) {
				String parkourName = args[1];
			}
			else if (args[0].equals("delete")) {
				String parkourName = args[1];
			}
			else if (args[0].equals("list")) {
				
			}
			else if (args[0].equals("checkpoint")) {
				
			}
			else if (args[0].equals("world")) {
				if (args[1].equals("add")) {
					String worldName = args[2];
				}
				else if (args[1].equals("remove")) {
					String worldName = args[2];
				}
			}
			return true;
		}
		return false;
	}
}
