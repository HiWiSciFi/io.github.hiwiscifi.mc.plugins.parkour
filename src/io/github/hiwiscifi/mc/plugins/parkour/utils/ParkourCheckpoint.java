package io.github.hiwiscifi.mc.plugins.parkour.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;


public class ParkourCheckpoint extends EffectPoint {

	public List<EffectPoint> effectPoints = new ArrayList<EffectPoint>();

	public ParkourCheckpoint(Location location) {
		this.location = location;
	}
}