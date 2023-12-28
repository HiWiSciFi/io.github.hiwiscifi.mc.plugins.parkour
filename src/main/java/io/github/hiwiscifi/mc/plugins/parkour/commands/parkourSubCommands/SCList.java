package io.github.hiwiscifi.mc.plugins.parkour.commands.parkourSubCommands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.hiwiscifi.mc.plugins.parkour.Main;
import io.github.hiwiscifi.mc.plugins.parkour.utils.Parkour;
import io.github.hiwiscifi.mc.plugins.parkour.utils.US;
import io.github.hiwiscifi.mc.plugins.parkour.utils.command.SubCommand;

public class SCList implements SubCommand{

	@Override
	public String getName() {
		return "list";
	}

	@Override
	public String getDescription() {
		return "lists the names of all existing parkours";
	}

	@Override
	public String getSyntax() {
		return "/parkour list";
	}

	@Override
	public boolean perform(CommandSender sender, Command command, String alias, String[] args) {
		if (!(sender instanceof Player player)) {
			return false;
		}

        player.sendMessage(US.OUT_PREFIX + "List of registered parkours");
		for (Parkour p : Main.getInstance().parkours) {
			player.sendMessage(p.name);
		}
		return true;
	}

	@Override
	public List<String> tabcomplete(CommandSender sender, Command command, String alias, String[] args) {
		return new ArrayList<>();
	}

}
