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
		
		// + parkour create <parkour name>
		// + parkour delete <parkour name>
		// + parkour list
		
		// - parkour set location <parkour name>
		// - parkour set endTpBack (true | false) <parkour name>
		
		// + parkour checkpoint add <parkour name>
		// - parkour checkpoint remove <parkour name>
		
		// - parkour world add <world name>
		// - parkour world remove <world name>
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args[0].equals("create")) {
				String parkourName = args[1];
				player.sendMessage("[Parkour] Creating parkour \"" + parkourName + "\"...");
				Parkour p = new Parkour(parkourName);
				Main.Instance.parkours.add(p);
				p.Save();
				player.sendMessage("[Parkour] Parkour created");
			}
			else if (args[0].equals("delete")) {
				String parkourName = args[1];
				player.sendMessage("[Parkour] Deleting parkour \"" + parkourName + "\"...");
				for (int i = 0; i < Main.Instance.parkours.size(); i++) {
					if (Main.Instance.parkours.get(i).name.equals(parkourName)) {
						Main.Instance.parkours.get(i).Delete();
						Main.Instance.parkours.remove(i);
						break;
					}
				}
				player.sendMessage("[Parkour] Parkour deleted");
				
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
			return true;
		}
		return false;
	}
}
