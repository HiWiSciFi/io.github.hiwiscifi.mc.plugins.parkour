package io.github.hiwiscifi.mc.plugins.parkour.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.hiwiscifi.mc.plugins.parkour.Main;
import io.github.hiwiscifi.mc.plugins.parkour.Parkour;

public class Command_parkour implements CommandExecutor {

	public static Command_parkour Instance = null;
	
	public Command_parkour() {
		System.out.println("[Parkour] Initializing parkour command...");
		Instance = this;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		// parkour create <name>
		// parkour delete <name>
		// parkour list
		
		// parkour checkpoint add <parkour name>
		// parkour checkpoint remove <parkour name>
		
		// parkour world add <world name>
		// parkour world remove <world name>
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args[0].equals("create")) {
				String parkourName = args[1];
				Parkour p = new Parkour(parkourName);
				Main.Instance.parkours.add(p);
				p.Save();
			}
			else if (args[0].equals("delete")) {
				String parkourName = args[1];
			}
			else if (args[0].equals("list")) {
				Bukkit.broadcastMessage("[Parkour] List of registered parkours");
				for (Parkour p : Main.Instance.parkours) {
					Bukkit.broadcastMessage(p.name);
				}
			}
			else if (args[0].equals("checkpoint")) {
				if (args[1].equals("add")) {
					String parkourName = args[2];
					for (Parkour p : Main.Instance.parkours) {
						if (p.name.equals(parkourName)) {
							p.AddCheckpoint(player.getLocation());
						}
					}
				}
				else if (args[1].equals("remove")) {
					String parkourName = args[2];
					for (Parkour p : Main.Instance.parkours) {
						if (p.name.equals(parkourName)) {
							
						}
					}
				}
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
