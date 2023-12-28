package io.github.hiwiscifi.mc.plugins.parkour.commands.parkourSubCommands;

import java.util.ArrayList;
import java.util.List;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.hiwiscifi.mc.plugins.parkour.Main;
import io.github.hiwiscifi.mc.plugins.parkour.utils.Parkour;
import io.github.hiwiscifi.mc.plugins.parkour.utils.StringUtil;
import io.github.hiwiscifi.mc.plugins.parkour.utils.WorldControl;
import io.github.hiwiscifi.mc.plugins.parkour.utils.command.SubCommand;

public class SCDelete implements SubCommand {

	@Override
	public String getName() {
		return "delete";
	}

	@Override
	public String getDescription() {
		return "deletes the parkour with the given name";
	}

	@Override
	public String getSyntax() {
		return "/parkour delete <parkour>";
	}

	@Override
	public boolean perform(CommandSender sender, Command command, String alias, String[] args) {

		if (!(sender instanceof Player player)) {
			return false;
		}

        if (!WorldControl.enabled(player.getWorld(), player)) {
			return false;
		}

		if(args.length < 1) {
			return false;
		}

		// TODO don't delete just hide
		String parkourName = args[0];
		player.sendMessage(StringUtil.OUT_PREFIX
			.append(Component.text("Deleting parkour "))
			.append(StringUtil.inQuotes(Component.text(parkourName)))
			.append(Component.text("..."))
		);
		for (int i = 0; i < Main.getInstance().parkours.size(); i++) {
			if (Main.getInstance().parkours.get(i).name.equals(parkourName)) {
				Main.getInstance().parkours.get(i).delete();
				Main.getInstance().parkours.remove(i);
				break;
			}
		}
		player.sendMessage(StringUtil.OUT_PREFIX
			.append(Component.text("Parkour deleted"))
		);

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
