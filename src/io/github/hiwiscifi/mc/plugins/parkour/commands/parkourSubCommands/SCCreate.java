package io.github.hiwiscifi.mc.plugins.parkour.commands.parkourSubCommands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.hiwiscifi.mc.plugins.parkour.Main;
import io.github.hiwiscifi.mc.plugins.parkour.utils.Parkour;
import io.github.hiwiscifi.mc.plugins.parkour.utils.US;
import io.github.hiwiscifi.mc.plugins.parkour.utils.WorldControl;
import io.github.hiwiscifi.mc.plugins.parkour.utils.command.SubCommand;

public class SCCreate implements SubCommand {

	public static SCCreate getInstance() {
		return instance;
	}

	private static SCCreate instance;

	public SCCreate() {
		instance = this;
	}

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

		if (!(sender instanceof Player)) {
			return false;
		}

		Player player = (Player) sender;

		if (!WorldControl.enabled(player.getWorld(), player)) {
			return false;
		}

		if(args.length < 1) {
			return false;
		}

		String parkourName = args[0];

		if(Parkour.getParkourNames().contains(parkourName)) {
			player.sendMessage(US.OUT_PREFIX + "Parkour " + US.inQuotes(parkourName) + " already exists" + US.THREE_DOTS);
			return false;
		}

		player.sendMessage(US.OUT_PREFIX + "Creating parkour " + US.inQuotes(parkourName) + US.THREE_DOTS);
		Parkour p = new Parkour(parkourName, player.getLocation());
		Main.getInstance().parkours.add(p);
		p.save();
		player.sendMessage(US.OUT_PREFIX + "Parkour created");

		return true;

	}

	@Override
	public List<String> tabcomplete(CommandSender sender, Command command, String alias, String[] args) {
		return new ArrayList<String>();
	}

}
