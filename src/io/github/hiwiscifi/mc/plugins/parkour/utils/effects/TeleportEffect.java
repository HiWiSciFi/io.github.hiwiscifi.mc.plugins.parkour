package io.github.hiwiscifi.mc.plugins.parkour.utils.effects;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import io.github.hiwiscifi.mc.plugins.parkour.utils.ParkourEffect;

public class TeleportEffect extends ParkourEffect{

	private double x,y,z;
	private boolean relativ = false;

	public TeleportEffect(String[] args) {

		super(args);

		x = Double.parseDouble(arguments[1]);
		y = Double.parseDouble(arguments[2]);
		z = Double.parseDouble(arguments[3]);

		if (arguments.length >= 5) {
			relativ = "true" == arguments[4];
		}
	}

	@Override
	public void apply(Player player) {

		if (!relativ) {
			Location place = new Location(player.getWorld(), x, y, z);
			player.teleport(place);
		}
		else {
			Location playerPlace = player.getLocation();
			playerPlace.add(x, y, z);
			player.teleport(playerPlace);
		}
	}



}
