package io.github.hiwiscifi.mc.plugins.parkour.utils.effects;

import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import io.github.hiwiscifi.mc.plugins.parkour.utils.ParkourEffect;

public class TeleportEffect extends ParkourEffect{

	public TeleportEffect(String[] args) {
		super(args);
	}

	@Override
	public void apply(Player player) {
		double x = Double.parseDouble(arguments[1]);
		double y = Double.parseDouble(arguments[2]);
		double z = Double.parseDouble(arguments[3]);
		Location place = new Location(player.getWorld(), x, y, z);
		player.teleport(place);
	}

	@Override
	public Map<String, Object> serialize() {
		// TODO Auto-generated method stub
		return null;
	}

	public static TeleportEffect deserialize(Map<String, Object> map) {

		//TODO implement
		String args[] = null;

		return new TeleportEffect(args);
	}

}
