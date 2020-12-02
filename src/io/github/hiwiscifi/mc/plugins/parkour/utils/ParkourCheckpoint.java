package io.github.hiwiscifi.mc.plugins.parkour.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;


public class ParkourCheckpoint extends EffectPoint implements Cloneable, ConfigurationSerializable{

	public List<EffectPoint> effectPoints = new ArrayList<EffectPoint>();

	public ParkourCheckpoint(Location location) {
		super(location);
	}

	@Override
	public Map<String, Object> serialize() {

		Map<String, Object> map = new HashMap<>();
		map.put("location", location);
		map.put("effects", effectPoints);

		return map;
	}

	public static ParkourCheckpoint deserialize(Map<String, Object> map) {
		ParkourCheckpoint target = new ParkourCheckpoint((Location) map.get("location"));
		System.out.println(target);
		return target;
	}

}