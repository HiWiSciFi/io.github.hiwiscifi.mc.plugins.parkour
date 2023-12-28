package io.github.hiwiscifi.mc.plugins.parkour.commands.parkourSubCommands.checkpointSubCommands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.hiwiscifi.mc.plugins.parkour.Main;
import io.github.hiwiscifi.mc.plugins.parkour.utils.Parkour;
import io.github.hiwiscifi.mc.plugins.parkour.utils.WorldControl;
import io.github.hiwiscifi.mc.plugins.parkour.utils.command.SubCommand;

public class SCAdd implements SubCommand {

	@Override
	public String getName() {
		return "add";
	}

	@Override
	public String getDescription() {
		return "Adds a checkpoint at the end of a parkour.";
	}

	@Override
	public String getSyntax() {
		return "/parkour checkpoint add <parkour name>";
	}

	@Override
	public boolean perform(CommandSender sender, Command command, String alias, String[] args) {
		if (!(sender instanceof Player player) || (args.length == 0)) {
			return false;
		}

        if (!WorldControl.enabled(player.getWorld())) {
			return false;
		}

		String parkourName = args[0];
		for (Parkour p : Main.getInstance().parkours) {
			if (p.name.equals(parkourName)) {
				p.addCheckpoint(player.getLocation());
			}
		}
		return true;
	}

	@Override
	public List<String> tabcomplete(CommandSender sender, Command command, String alias, String[] args) {
		List <String> result = new ArrayList<>();

		for (Parkour p : Main.getInstance().parkours) {
			result.add(p.name);
		}
		return result;
	}

}
