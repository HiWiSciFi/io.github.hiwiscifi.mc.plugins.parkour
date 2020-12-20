package io.github.hiwiscifi.mc.plugins.parkour.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import io.github.hiwiscifi.mc.plugins.parkour.commands.parkourSubCommands.SCCheckpoint;
import io.github.hiwiscifi.mc.plugins.parkour.commands.parkourSubCommands.SCCreate;
import io.github.hiwiscifi.mc.plugins.parkour.commands.parkourSubCommands.SCDelete;
import io.github.hiwiscifi.mc.plugins.parkour.commands.parkourSubCommands.SCList;
import io.github.hiwiscifi.mc.plugins.parkour.commands.parkourSubCommands.SCSet;
import io.github.hiwiscifi.mc.plugins.parkour.commands.parkourSubCommands.SCWorld;
import io.github.hiwiscifi.mc.plugins.parkour.utils.US;
import io.github.hiwiscifi.mc.plugins.parkour.utils.command.CommandManager;

public class Command_parkour extends CommandManager implements TabExecutor {

	public static Command_parkour getInstance() {
		return instance;
	}

	private static Command_parkour instance;

	public Command_parkour() {
		super();
		name = "parkour";
		System.out.println(US.OUT_PREFIX + "Initializing parkour command" + US.THREE_DOTS);
		instance = this;

		//register subcomands
		register(new SCList());
		register(new SCCreate());
		register(new SCDelete());
		register(new SCWorld());
		register(new SCCheckpoint());

		register(new SCSet());

	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		return complete(sender, command, alias, args);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return perform(sender, command, label, args);
	}

	@Override
	public String getDescription() {
		return "a genaral command to interact with the parkour plugin";
	}

	@Override
	public String getSyntax() {
		return "/parkour <subcommand> [arg1] ...";
	}

	/*
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		// + parkour create <parkour name>
		// + parkour delete <parkour name>
		// + parkour list

		// + parkour set location <parkour name>
		// - parkour set endTpBack (true | false) <parkour name>

		// + parkour checkpoint add <parkour name>
		// - parkour checkpoint remove <parkour name>

		// + parkour world add
		// + parkour world remove

		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args[0].equals("create")) {
				if (WorldControl.enabled(player.getWorld(), player)) {
					String parkourName = args[1];
					player.sendMessage(US.OUT_PREFIX + "Creating parkour " + US.inQuotes(parkourName) + US.THREE_DOTS);
					Parkour p = new Parkour(parkourName, player.getLocation());
					Main.getInstance().parkours.add(p);
					p.save();
					player.sendMessage(US.OUT_PREFIX + "Parkour created");
				}
			} else if (args[0].equals("delete")) {
				if (WorldControl.enabled(player.getWorld(), player)) {
					// TODO don't delete just hide
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
			} else if (args[0].equals("list")) {
				player.sendMessage(US.OUT_PREFIX + "List of registered parkours");
				for (Parkour p : Main.getInstance().parkours) {
					player.sendMessage(p.name);
				}
			} else if (args[0].equals("set")) {
				if (WorldControl.enabled(player.getWorld(), player)) {
					if (args[1].equals("location")) {
						String parkourName = args[2];
						player.sendMessage(US.OUT_PREFIX + "Setting start location for parkour "
								+ US.inQuotes(parkourName) + US.THREE_DOTS);
						for (Parkour parkour : Main.getInstance().parkours) {
							if (parkour.name.equals(parkourName)) {
								parkour.startLocation = player.getLocation();
								parkour.save();
								player.sendMessage(US.OUT_PREFIX + "New start location set!");
								break;
							}
						}
					}
					// TODO edit name
				}

			} else if (args[0].equals("checkpoint")) {
				if (WorldControl.enabled(player.getWorld(), player)) {
					if (args[1].equals("add")) {
						String parkourName = args[2];
						for (Parkour p : Main.getInstance().parkours) {
							if (p.name.equals(parkourName)) {
								p.addCheckpoint(player.getLocation());
							}
						}
						// TODO add in between
					} else if (args[1].equals("remove")) {
						String parkourName = args[2];
						for (Parkour p : Main.getInstance().parkours) {
							if (p.name.equals(parkourName)) {

							}
						}
					}
					// TODO list
					// TODO edit
				}
			} else if (args[0].equals("world")) {
				if (args[1].equals("add")) {
					player.sendMessage(US.OUT_PREFIX + "Adding your current world to set of parkour-enabled worlds" + US.THREE_DOTS);
					WorldControl.addWorld(player.getWorld().getName());
					player.sendMessage(US.OUT_PREFIX + "World added!");
				}
				else if (args[1].equals("remove")) {
					player.sendMessage(US.OUT_PREFIX + "Removing your current world from the set of parkour-enabled worlds" + US.THREE_DOTS);
					WorldControl.removeWorld(player.getWorld().getName());
					player.sendMessage(US.OUT_PREFIX + "World removed!");
				}
			}
			return true;
		}
		return false;
	}
	*/


}
