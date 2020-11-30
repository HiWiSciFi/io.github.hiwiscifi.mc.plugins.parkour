package io.github.hiwiscifi.mc.plugins.parkour.utils.command;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class CommandManager {

	private ArrayList<SubCommand> subcommands = new ArrayList<SubCommand>();

	public CommandManager() {
		
	}

	public boolean perform(CommandSender sender, Command command, String label, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (args.length > 0) {
				for (int i = 0; i < getSubcommands().size(); i++){
					if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())){
						getSubcommands().get(i).perform(player, args);
					}
				}
			}else if(args.length == 0) {
				//TODO add neutral
				doHelp(player);
			}

		}


		return true;
	}

	public ArrayList<SubCommand> getSubcommands() {
		return subcommands;
	}

	public void doHelp(Player player) {
		player.sendMessage("--------------------------------");
		for (int i = 0; i < getSubcommands().size(); i++){
			player.sendMessage(getSubcommands().get(i).getSyntax() + " - " + getSubcommands().get(i).getDescription());
		}
		player.sendMessage("--------------------------------");

	}

}