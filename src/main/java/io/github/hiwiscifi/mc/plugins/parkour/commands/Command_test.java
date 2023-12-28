package io.github.hiwiscifi.mc.plugins.parkour.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.hiwiscifi.mc.plugins.parkour.utils.US;

public class Command_test implements CommandExecutor {

	public static Command_test getInstance() { return instance; }
	private static Command_test instance;

	public Command_test() {
		System.out.println(US.OUT_PREFIX + "Initializing test command" + US.THREE_DOTS);
		instance = this;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			player.sendMessage(US.OUT_PREFIX + "Your position is " +
					player.getLocation().getBlockX() + ":" +
					player.getLocation().getBlockY() + ":" +
					player.getLocation().getBlockZ() + " " +
					player.getLocation().getX() + ":" +
					player.getLocation().getY() + ":" +
					player.getLocation().getZ() + " " +
					player.getLocation().distance(player.getLocation().getBlock().getLocation()));
			return true;
		}

		return false;
	}

}
