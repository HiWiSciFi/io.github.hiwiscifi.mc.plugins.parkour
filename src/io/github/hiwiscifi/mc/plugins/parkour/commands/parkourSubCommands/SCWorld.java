package io.github.hiwiscifi.mc.plugins.parkour.commands.parkourSubCommands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import io.github.hiwiscifi.mc.plugins.parkour.commands.parkourSubCommands.worldSubCommands.SCAdd;
import io.github.hiwiscifi.mc.plugins.parkour.commands.parkourSubCommands.worldSubCommands.SCRemove;
import io.github.hiwiscifi.mc.plugins.parkour.utils.command.CommandManager;
import io.github.hiwiscifi.mc.plugins.parkour.utils.command.SubCommand;

public class SCWorld extends CommandManager implements SubCommand {

	public static SCWorld getInstance() {
		return instance;
	}

	private static SCWorld instance;

	public SCWorld() {
		super();
		name = "world";

		instance = this;

		register(new SCAdd());
		register(new SCRemove());
	}

	@Override
	public List<String> tabcomplete(CommandSender sender, Command command, String alias, String[] args) {
		return complete(sender, command, alias, args);
	}

	@Override
	public String getDescription() {
		return "changes the worlds this plugin is enabled in";
	}

	@Override
	public String getSyntax() {
		return "/parkour world <add|remove>";
	}

}
