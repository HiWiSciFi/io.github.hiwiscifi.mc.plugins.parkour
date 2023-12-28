package io.github.hiwiscifi.mc.plugins.parkour.utils.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

//TODO remove first argument
public abstract class CommandManager {

	public String name = "noName";

	private final ArrayList<SubCommand> subCommands = new ArrayList<>();

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
							sender.sendMessage(Component.text(getSubcommands().get(i).getSyntax())
								.append(Component.text(" - "))
								.append(Component.text(getSubcommands().get(i).getDescription()))
							);
						}
					} else {
						result = true;
					}
				}
			}
		} else {
			if (neutral == null) {

				if (sender instanceof Player) {
					doHelp((Player) sender);
					result = true;
				}

			} else {
				if(!neutral.perform(sender, command, label, cutFirst(args))) {
					if(sender instanceof Player) {
						sender.sendMessage(Component.text(neutral.getSyntax())
							.append(Component.text(" - "))
							.append(Component.text(neutral.getDescription()))
						);
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

		if (!(sender instanceof Player player)) {
			return null;
		}

        List<String> strings = new ArrayList<>();

		if (args.length == 1) {
			for (int i = 0; i < getSubcommands().size(); i++) {
				strings.add(getSubcommands().get(i).getName());
			}
		} else {
			for (int i = 0; i < getSubcommands().size(); i++) {
				if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())) {
					strings.addAll(
							getSubcommands().get(i).tabcomplete(player, command, alias, cutFirst(args))
					);
				}
			}
		}
		//TODO add neutral
		return strings;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SubCommand> getSubcommands() {
		return (ArrayList<SubCommand>) subCommands.clone();
	}

	public void doHelp(Player player) {
		player.sendMessage(Component.text("--------------------------------"));
		for (int i = 0; i < getSubcommands().size(); i++) {
			player.sendMessage(Component.text(getSubcommands().get(i).getSyntax())
				.append(Component.text(" - "))
				.append(Component.text(getSubcommands().get(i).getDescription()))
			);
		}
		player.sendMessage(Component.text("--------------------------------"));
	}

	// name of the subcommand ex. /prank <subcommand> <-- that
	public String getName() {
		return name;
	}

	// ex. "This is a subcommand that lets a shark eat someone"
	public abstract String getDescription();

	// How to use command ex. /prank freeze <player>
	public abstract String getSyntax();

}
