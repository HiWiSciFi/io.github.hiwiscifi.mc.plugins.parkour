package io.github.hiwiscifi.mc.plugins.parkour.commands.parkourSubCommands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import io.github.hiwiscifi.mc.plugins.parkour.utils.command.CommandManager;
import io.github.hiwiscifi.mc.plugins.parkour.utils.command.SubCommand;

public class SCSet extends CommandManager implements SubCommand{

	public static SCSet getInstance() {
		return instance;
	}

	private static SCSet instance;

	public SCSet() {
		super();
		name = "set";



		instance = this;
	}

	@Override
	public List<String> tabcomplete(CommandSender sender, Command command, String alias, String[] args) {
		return complete(sender, command, alias, args);
	}

	@Override
	public String getDescription() {
		return "resets attributes of a parkour";
	}

	@Override
	public String getSyntax() {
		return "/parkour set [arg1] ... <parkour>";
	}

}
