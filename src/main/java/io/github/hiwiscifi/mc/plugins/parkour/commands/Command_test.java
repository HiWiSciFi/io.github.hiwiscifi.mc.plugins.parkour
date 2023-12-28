package io.github.hiwiscifi.mc.plugins.parkour.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.hiwiscifi.mc.plugins.parkour.utils.StringUtil;
import org.jetbrains.annotations.NotNull;

public class Command_test implements CommandExecutor {

	public static Command_test getInstance() { return instance; }
	private static Command_test instance;

	public Command_test() {
		System.out.println(StringUtil.OUT_PREFIX + "Initializing test command...");
		instance = this;
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
		if (sender instanceof Player player) {
			player.sendMessage(StringUtil.OUT_PREFIX
				.append(Component.text("Your position is "))
				.append(Component.text(player.getLocation().getBlockX())).append(Component.text(":"))
				.append(Component.text(player.getLocation().getBlockY())).append(Component.text(":"))
				.append(Component.text(player.getLocation().getBlockZ())).append(Component.text(" "))
				.append(Component.text(player.getLocation().getX())).append(Component.text(":"))
				.append(Component.text(player.getLocation().getY())).append(Component.text(":"))
				.append(Component.text(player.getLocation().getZ())).append(Component.text(" "))
				.append(Component.text(player.getLocation().distance(player.getLocation().getBlock().getLocation())))
			);
			return true;
		}

		return false;
	}

}
