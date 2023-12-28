package io.github.hiwiscifi.mc.plugins.parkour.utils.effects;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import io.github.hiwiscifi.mc.plugins.parkour.utils.ParkourEffect;

public class TeleportEffect extends ParkourEffect{

	private final double x;
    private final double y;
    private final double z;
	private boolean relative = false;

	public TeleportEffect(String[] args) {

		super(args);

		x = Double.parseDouble(arguments[1]);
		y = Double.parseDouble(arguments[2]);
		z = Double.parseDouble(arguments[3]);

		if (arguments.length >= 5) {
			relative = arguments[4].equals("true");
		}
	}

	@Override
	public void apply(Player player) {

		if (!relative) {
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
