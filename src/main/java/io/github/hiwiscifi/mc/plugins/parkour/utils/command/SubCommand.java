package io.github.hiwiscifi.mc.plugins.parkour.utils.command;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface SubCommand {

	//name of the subcommand ex. /prank <subcommand> <-- that
	public String getName();

	//ex. "This is a subcommand that let's a shark eat someone"
	public String getDescription();

	//How to use command ex. /prank freeze <player>
	public String getSyntax();

	//code for the subcommand
	public boolean perform(CommandSender sender, Command command, String alias, String[] args);

	public List<String> tabcomplete(CommandSender sender, Command command, String alias, String[] args);
}
