package io.github.hiwiscifi.mc.plugins.parkour.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

@SerializableAs("checkpoint")
public class ParkourCheckpoint extends EffectPoint implements Cloneable, ConfigurationSerializable{

	public List<EffectPoint> effectPoints = new ArrayList<EffectPoint>();

	public ParkourCheckpoint(Location location) {
		super(location);
	}

	@Override
	public Map<String, Object> serialize() {

		Map<String, Object> map = new HashMap<>();
		map.put("location", location);
		map.put("effects",effects);
		map.put("effectPoints",effectPoints);

		return map;
	}

	@SuppressWarnings("unchecked")
	public static ParkourCheckpoint deserialize(Map<String, Object> map) {
		if(map == null) {
			return null;
		}

		ParkourCheckpoint target = new ParkourCheckpoint((Location) map.get("location"));

		if(map.containsKey("effects")) {
			target.effects = (List<ParkourEffect>) map.get("effects");
		}
		if(map.containsKey("effectPoints")) {
			target.effectPoints = (List<EffectPoint>) map.get("effectPoints");
		}

		return target;
	}

}