package io.github.hiwiscifi.mc.plugins.parkour.utils.command;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface SubCommand {

	//name of the subcommand ex. /prank <subcommand> <-- that
    String getName();

	//ex. "This is a subcommand that lets a shark eat someone"
    String getDescription();

	//How to use command ex. /prank freeze <player>
    String getSyntax();

	//code for the subcommand
    boolean perform(CommandSender sender, Command command, String alias, String[] args);

	List<String> tabcomplete(CommandSender sender, Command command, String alias, String[] args);
}
