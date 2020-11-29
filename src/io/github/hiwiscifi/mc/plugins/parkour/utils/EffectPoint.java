package io.github.hiwiscifi.mc.plugins.parkour.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import io.github.hiwiscifi.mc.plugins.parkour.utils.effects.TeleportEffect;

public class EffectPoint {

	public List<ParkourEffect> effects = new ArrayList<ParkourEffect>();

	public Location location;

	public void apply(Player player) {

	}

	public void addEffect(String[] args) {
		if(!(args.length > 0)) {
			//TODO error
		}

		switch(args[0].toLowerCase()) {
		case "teleport":
		case "tp":
			effects.add(new TeleportEffect(removeFirstArgument(args)));
			break;
		}
	}

	private String[] removeFirstArgument(String[] source) {
		String[] destination = null;
		System.arraycopy(source, 1, destination, 0, source.length-1);
		return destination;
	}
}
