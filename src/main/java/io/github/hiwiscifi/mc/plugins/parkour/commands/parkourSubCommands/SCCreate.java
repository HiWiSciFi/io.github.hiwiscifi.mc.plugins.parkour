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

public class SCCreate implements SubCommand {

	@Override
	public String getName() {
		return "create";
	}

	@Override
	public String getDescription() {
		return "creates a parkour and sets its start location";
	}

	@Override
	public String getSyntax() {
		return "/parkour create <parkour>";
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

		String parkourName = args[0];

		if(Parkour.getParkourNames().contains(parkourName)) {
			player.sendMessage(StringUtil.OUT_PREFIX
				.append(Component.text("Parkour "))
				.append(StringUtil.inQuotes(Component.text(parkourName)))
				.append(Component.text(" already exists..."))
			);
			return false;
		}

		player.sendMessage(StringUtil.OUT_PREFIX
			.append(Component.text("Creating parkour "))
			.append(StringUtil.inQuotes(Component.text(parkourName)))
			.append(Component.text("..."))
		);
		Parkour p = new Parkour(parkourName, player.getLocation());
		Main.getInstance().parkours.add(p);
		p.save();
		player.sendMessage(StringUtil.OUT_PREFIX
			.append(Component.text("Parkour created"))
		);

		return true;

	}

	@Override
	public List<String> tabcomplete(CommandSender sender, Command command, String alias, String[] args) {
		return new ArrayList<>();
	}

}
