package io.github.hiwiscifi.mc.plugins.parkour.commands.parkourSubCommands.worldSubCommands;

import java.util.ArrayList;
import java.util.List;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.hiwiscifi.mc.plugins.parkour.utils.StringUtil;
import io.github.hiwiscifi.mc.plugins.parkour.utils.WorldControl;
import io.github.hiwiscifi.mc.plugins.parkour.utils.command.SubCommand;

public class SCRemove implements SubCommand {

	@Override
	public String getName() {
		return "remove";
	}

	@Override
	public String getDescription() {
		return "disables parkours in current world";
	}

	@Override
	public String getSyntax() {
		return "/parkour world remove";
	}

	@Override
	public boolean perform(CommandSender sender, Command command, String alias, String[] args) {
		if (!(sender instanceof Player player)) {
			return false;
		}

		player.sendMessage(StringUtil.OUT_PREFIX
			.append(Component.text("Removing your current world from the set of parkour-enabled worlds..."))
		);
		WorldControl.removeWorld(player.getWorld().getName());
		player.sendMessage(StringUtil.OUT_PREFIX
			.append(Component.text("World removed!"))
		);

		return true;
	}

	@Override
	public List<String> tabcomplete(CommandSender sender, Command command, String alias, String[] args) {
		return new ArrayList<>();
	}

}
