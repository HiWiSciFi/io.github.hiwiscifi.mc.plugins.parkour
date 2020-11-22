package io.github.hiwiscifi.mc.plugins.parkour.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Command_holo implements CommandExecutor {

	public Command_holo() {
		System.out.println("Initializing holo command...");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			Bukkit.broadcastMessage("Creating armor stand");
			World w = player.getLocation().getWorld();
			Location loc = player.getLocation().toBlockLocation();
			loc.setX(loc.getX()+0.5d);
			loc.setZ(loc.getZ()+0.5d);
			ArmorStand a = (ArmorStand) w.spawnEntity(loc, EntityType.ARMOR_STAND);
			a.setCustomName(ChatColor.AQUA + "Checkpoint X");
			a.setCustomNameVisible(true);
			a.setInvulnerable(true);
			a.setVisible(false);
			a.setGravity(false);
			a.setCanMove(false);
			a.setCollidable(false);
			a.setMarker(true);
			return true;
		}
		return false;
	}

}
