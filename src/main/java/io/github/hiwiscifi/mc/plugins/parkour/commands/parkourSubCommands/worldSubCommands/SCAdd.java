package io.github.hiwiscifi.mc.plugins.parkour.commands.parkourSubCommands.worldSubCommands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.hiwiscifi.mc.plugins.parkour.utils.US;
import io.github.hiwiscifi.mc.plugins.parkour.utils.WorldControl;
import io.github.hiwiscifi.mc.plugins.parkour.utils.command.SubCommand;

public class SCAdd implements SubCommand{

	@Override
	public String getName() {
		return "add";
	}

	@Override
	public String getDescription() {
		return "enables parkours in current world";
	}

	@Override
	public String getSyntax() {
		return "/parkour world add";
	}

	@Override
	public boolean perform(CommandSender sender, Command command, String alias, String[] args) {

		if (!(sender instanceof Player player)) {
			return false;
		}

        player.sendMessage(US.OUT_PREFIX + "Adding your current world to set of parkour-enabled worlds" + US.THREE_DOTS);
		WorldControl.addWorld(player.getWorld().getName());
		player.sendMessage(US.OUT_PREFIX + "World added!");

		return true;
	}

	@Override
	public List<String> tabcomplete(CommandSender sender, Command command, String alias, String[] args) {
		return WorldControl.getEnabledWorldNames();
	}

}
