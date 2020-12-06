package io.github.hiwiscifi.mc.plugins.parkour.commands.parkourSubCommands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import io.github.hiwiscifi.mc.plugins.parkour.utils.command.CommandManager;
import io.github.hiwiscifi.mc.plugins.parkour.utils.command.SubCommand;

public class SCCheckpoint extends CommandManager implements SubCommand{

	public static SCCheckpoint getInstance() {
		return instance;
	}

	private static SCCheckpoint instance;

	public SCCheckpoint() {
		super();
		name = "checkpoint";

		// TODO Auto-generated constructor stub
		instance = this;
	}

	@Override
	public List<String> tabcomplete(CommandSender sender, Command command, String alias, String[] args) {
		return complete(sender, command, alias, args);
	}

	@Override
	public String getDescription() {
		return "With this subcommand you can do everything related to chekpoint";
	}

	@Override
	public String getSyntax() {
		return "/parkour checkpoint <subcommand> <arg1> ...";
	}

}