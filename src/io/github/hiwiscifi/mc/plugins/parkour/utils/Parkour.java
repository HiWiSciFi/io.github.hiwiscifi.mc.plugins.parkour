package io.github.hiwiscifi.mc.plugins.parkour.utils;
//TODO same name
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;

import io.github.hiwiscifi.mc.plugins.parkour.Main;

public class Parkour {
	public String name;

	public ParkourCheckpoint startCheckpoint;
	public Location startLocation;

	public List<ParkourCheckpoint> checkpoints;

	public Parkour(String name) {
		this.name = name;
		checkpoints = new ArrayList<ParkourCheckpoint>();
		startCheckpoint = null;
		startLocation = null;
	}

	public Parkour(String name, Location startLocation) {
		this.name = name;
		this.startLocation = startLocation;
		checkpoints = new ArrayList<ParkourCheckpoint>();
		startCheckpoint = null;
	}

	//TODO change naming
	public void addCheckpoint(Location location) {
		Location loc = location.toBlockLocation();
		if (startCheckpoint == null) {
			loc.getBlock().setType(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
			startCheckpoint = new ParkourCheckpoint(loc);
		} else {
			loc.getBlock().setType(Material.HEAVY_WEIGHTED_PRESSURE_PLATE);
			checkpoints.add(new ParkourCheckpoint(loc));
		}
		save();
	}

	//TODO ad funktionality
	public void removeCheckpoint() {
		save();
	}

	public static List<String> getParkourNames() {
		if (Main.getInstance().getConfig().contains("parkours")) {
			Set<String> keys = Main.getInstance().getConfig().getConfigurationSection("parkours").getKeys(false);
			List<String> names = new ArrayList<String>();
			for (String s : keys) {
				names.add(s);
			}
			return names;
		} else {
			return new ArrayList<String>();
		}
	}

	//TODO no US and parkour checkpoint
	public static Parkour load(String name) {
		//	if (Main.getInstance().getConfig().contains("parkours." + name)) {
		//		Parkour parkour = new Parkour(name);
		//
		//		//System.out.println(Main.getInstance().getConfig().contains("parkours.hi.checkpoints"));
		//		System.out.println(Main.getInstance().getConfig().contains("parkours.hi.startLocation"));
		//		System.out.println(Main.getInstance().getConfig().contains("parkours.hi.startCheckpoint"));
		//		List<?> cdsfheckpoints = Main.getInstance().getConfig().getList("parkours." + name + ".checkpoints", parkour.checkpoints);
		//		System.out.println(cdsfheckpoints.get(0).getClass());
		//		//if (Main.getInstance().getConfig().contains("parkours." + name + ".checkpoints")) {
		//
		//
		//
		//
		//
		//		parkour.startCheckpoint = ParkourCheckpoint.deserialise((Map<String, Object>) Main.getInstance().getConfig().get("parkours." + name + ".startCheckpoint"));
		//
		//		System.out.println(parkour.startCheckpoint);
		//
		//		if (Main.getInstance().getConfig().contains("parkours." + name + ".startLocation")) {
		//			parkour.startLocation = Main.getInstance().getConfig().getLocation("parkours." + name + ".startLocation");
		//		}
		//
		//		return parkour;
		//	} else {
		//		return null;
		//	}
		return null;

	}

	//TODO Do parkour checkpoint
	public void save() {
		Main.getInstance().getConfig().set("parkours." + name + ".startCheckpoint", startCheckpoint);
		Main.getInstance().getConfig().set("parkours." + name + ".startLocation", startLocation);
		Main.getInstance().getConfig().set("parkours." + name + ".checkpoints", checkpoints);
		Main.getInstance().saveConfig();
	}

	public void delete() {
		Main.getInstance().getConfig().set("parkours." + name, null);
		Main.getInstance().saveConfig();
	}
}
