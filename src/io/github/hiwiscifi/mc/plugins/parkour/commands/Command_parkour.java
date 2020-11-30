package io.github.hiwiscifi.mc.plugins.parkour.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.hiwiscifi.mc.plugins.parkour.Main;
import io.github.hiwiscifi.mc.plugins.parkour.utils.Parkour;
import io.github.hiwiscifi.mc.plugins.parkour.utils.US;

public class Command_parkour implements CommandExecutor {

	public static Command_parkour getInstance() { return instance; }
	private static Command_parkour instance;

	public Command_parkour() {
		System.out.println(US.OUT_PREFIX + "Initializing parkour command" + US.THREE_DOTS);
		instance = this;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		// + parkour create <parkour name>
		// + parkour delete <parkour name>
		// + parkour list

		// + parkour set location <parkour name>
		// - parkour set endTpBack (true | false) <parkour name>

		// + parkour checkpoint add <parkour name>
		// - parkour checkpoint remove <parkour name>

		// - parkour world add <world name>
		// - parkour world remove <world name>

		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args[0].equals("create")) {
				String parkourName = args[1];
				player.sendMessage(US.OUT_PREFIX + "Creating parkour " + US.inQuotes(parkourName) + US.THREE_DOTS);
				Parkour p = new Parkour(parkourName, player.getLocation());
				Main.getInstance().parkours.add(p);
				p.save();
				player.sendMessage(US.OUT_PREFIX + "Parkour created");
			}
			else if (args[0].equals("delete")) {
				//TODO don't delete just hide
				String parkourName = args[1];
				player.sendMessage(US.OUT_PREFIX + "Deleting parkour " + US.inQuotes(parkourName) + US.THREE_DOTS);
				for (int i = 0; i < Main.getInstance().parkours.size(); i++) {
					if (Main.getInstance().parkours.get(i).name.equals(parkourName)) {
						Main.getInstance().parkours.get(i).delete();
						Main.getInstance().parkours.remove(i);
						break;
					}
				}
				player.sendMessage(US.OUT_PREFIX + "Parkour deleted");

			}
			else if (args[0].equals("list")) {
				player.sendMessage(US.OUT_PREFIX + "List of registered parkours");
				for (Parkour p : Main.getInstance().parkours) {
					player.sendMessage(p.name);
				}
			}
			else if (args[0].equals("set")) {
				if (args[1].equals("location")) {
					String parkourName = args[2];
					player.sendMessage(US.OUT_PREFIX + "Setting start location for parkour " + US.inQuotes(parkourName) + US.THREE_DOTS);
					for (Parkour parkour : Main.getInstance().parkours) {
						if (parkour.name.equals(parkourName)) {
							parkour.startLocation = player.getLocation();
							parkour.save();
							player.sendMessage(US.OUT_PREFIX + "New start location set!");
							break;
						}
					}
				}
				//TODO edit name
			}
			else if (args[0].equals("checkpoint")) {
				if (args[1].equals("add")) {
					String parkourName = args[2];
					for (Parkour p : Main.getInstance().parkours) {
						if (p.name.equals(parkourName)) {
							p.addCheckpoint(player.getLocation());
						}
					}
					//TODO add in between
				}
				else if (args[1].equals("remove")) {
					String parkourName = args[2];
					for (Parkour p : Main.getInstance().parkours) {
						if (p.name.equals(parkourName)) {

						}
					}
				}
				//TODO list
				//TODO edit
			}
			return true;
		}
		return false;
	}
}
