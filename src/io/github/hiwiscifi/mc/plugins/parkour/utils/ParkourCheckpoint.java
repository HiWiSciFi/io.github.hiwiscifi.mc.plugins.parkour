package io.github.hiwiscifi.mc.plugins.parkour.utils;

import java.util.ArrayList;
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
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 	public ParkourCheckpoint deserialise(Map<String, Object> map) {
return

	}
	 */
}