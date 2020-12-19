package io.github.hiwiscifi.mc.plugins.parkour.utils.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

//TODO remove first argument
public abstract class CommandManager {

	public String name = "noName";

	private ArrayList<SubCommand> subCommands = new ArrayList<SubCommand>();

	private SubCommand neutral = null;

	public CommandManager() {

	}

	public void register(SubCommand subCommand) {
		subCommands.add(subCommand);
	}

	public void registerNeutral(SubCommand subCommand) {
		neutral = subCommand;
		register(subCommand);
	}

	public boolean perform(CommandSender sender, Command command, String label, String[] args) {
		boolean result = false;

		if (args.length > 0) {

			for (int i = 0; i < getSubcommands().size(); i++) {
				if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())) {
					if(!getSubcommands().get(i).perform(sender, command, label, cutFirst(args))) {
						if(sender instanceof Player) {
							sender.sendMessage(getSubcommands().get(i).getSyntax() + " - " + getSubcommands().get(i).getDescription());
						}
					} else {
						result = true;
					}
				}
			}
		} else if (args.length == 0) {
			if (neutral == null) {

				if (sender instanceof Player) {
					doHelp((Player) sender);
					result = true;
				}

			} else {
				if(!neutral.perform(sender, command, label, cutFirst(args))) {
					if(sender instanceof Player) {
						sender.sendMessage(neutral.getSyntax() + " - " + neutral.getDescription());
					}
				} else {
					result = true;
				}
			}
		}

		return result;
	}

	private String[] cutFirst(String[] args) {
		if(args.length == 0) {
			return args;
		}
		return Arrays.copyOfRange(args, 1, args.length);
	}

	public List<String> complete(CommandSender sender, Command command, String alias, String[] args) {

		if (args.length == 0) {
			return null;
		}

		if (!(sender instanceof Player)) {
			return null;
		}

		Player player = (Player) sender;

		List<String> strings = new ArrayList<String>();

		if (args.length == 1) {
			for (int i = 0; i < getSubcommands().size(); i++) {
				strings.add(getSubcommands().get(i).getName());
			}
		} else if (args.length > 1) {
			for (int i = 0; i < getSubcommands().size(); i++) {
				if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())) {
					strings.addAll(
							getSubcommands().get(i).tabcomplete(player, command, alias, cutFirst(args))
							);
				}
			}
		}
		//TODO addneutral
		return strings;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SubCommand> getSubcommands() {
		return (ArrayList<SubCommand>) subCommands.clone();
	}

	public void doHelp(Player player) {
		player.sendMessage("--------------------------------");
		for (int i = 0; i < getSubcommands().size(); i++) {
			player.sendMessage(getSubcommands().get(i).getSyntax() + " - " + getSubcommands().get(i).getDescription());
		}
		player.sendMessage("--------------------------------");
	}

	// name of the subcommand ex. /prank <subcommand> <-- that
	public String getName() {
		return name;
	}

	// ex. "This is a s-ubcommand that let's a shark eat someone"
	public abstract String getDescription();

	// How to use command ex. /prank freeze <player>
	public abstract String getSyntax();

}