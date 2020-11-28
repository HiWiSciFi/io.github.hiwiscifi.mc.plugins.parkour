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
		System.out.println(US.OUT_PREFIX + US.getString(14) + US.THREE_DOTS);
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
			if (args[0].equals(US.getString(15))) {
				String parkourName = args[1];
				player.sendMessage(US.OUT_PREFIX + US.addSpace(US.getString(16), false, true) + US.inQuotes(parkourName) + US.THREE_DOTS);
				Parkour p = new Parkour(parkourName, player.getLocation());
				Main.getInstance().parkours.add(p);
				p.save();
				player.sendMessage(US.OUT_PREFIX + US.getString(18));
			}
			else if (args[0].equals(US.getString(19))) {
				//TODO don't delete just hide
				String parkourName = args[1];
				player.sendMessage(US.OUT_PREFIX + US.addSpace(US.getString(20), false, true) + US.inQuotes(parkourName) + US.THREE_DOTS);
				for (int i = 0; i < Main.getInstance().parkours.size(); i++) {
					if (Main.getInstance().parkours.get(i).name.equals(parkourName)) {
						Main.getInstance().parkours.get(i).delete();
						Main.getInstance().parkours.remove(i);
						break;
					}
				}
				player.sendMessage(US.OUT_PREFIX + US.getString(21));

			}
			else if (args[0].equals(US.getString(22))) {
				player.sendMessage(US.OUT_PREFIX + US.getString(23));
				for (Parkour p : Main.getInstance().parkours) {
					player.sendMessage(p.name);
				}
			}
			else if (args[0].equals(US.getString(24))) {
				if (args[1].equals(US.getString(25))) {
					String parkourName = args[2];
					player.sendMessage(US.OUT_PREFIX + US.addSpace(US.getString(26), false, true) + US.inQuotes(parkourName) + US.THREE_DOTS);
					for (Parkour parkour : Main.getInstance().parkours) {
						if (parkour.name.equals(parkourName)) {
							parkour.startLocation = player.getLocation();
							parkour.save();
							player.sendMessage(US.OUT_PREFIX + US.getString(27));
							break;
						}
					}
				}
				//TODO edit name
			}
			else if (args[0].equals(US.getString(28))) {
				if (args[1].equals(US.getString(29))) {
					String parkourName = args[2];
					for (Parkour p : Main.getInstance().parkours) {
						if (p.name.equals(parkourName)) {
							p.addCheckpoint(player.getLocation());
						}
					}
					//TODO add in between
				}
				else if (args[1].equals(US.getString(30))) {
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
