package io.github.hiwiscifi.mc.plugins.parkour.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

import io.github.hiwiscifi.mc.plugins.parkour.utils.effects.TeleportEffect;

public class EffectPoint implements Cloneable, ConfigurationSerializable{

	public List<ParkourEffect> effects = new ArrayList<ParkourEffect>();

	public Location location;

	public EffectPoint(Location location) {
		this.location = location;
	}

	public EffectPoint(Location location, List<ParkourEffect> effects) {
		this.location = location;
		this.effects = effects;
	}

	public void apply(Player player) {

	}

	public void addEffect(String[] args) {
		if(!(args.length > 0)) {
			//TODO error
		}

		//TODO move to helper
		switch(args[0].toLowerCase()) {
		case "teleport":
		case "tp":
			effects.add(new TeleportEffect(args));
			break;
		}
	}


	@Override
	public Map<String, Object> serialize() {

		Map<String, Object> map = new HashMap<>();
		map.put("location", location);
		map.put("effects", effects);

		return null;
	}
	/*
	public EffectPoint deserialise(Map<String, Object> map) {
		System.out.println(map.toString());
		return new EffectPoint((Location) map.get("location"), null);
	}
	 */
}
