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

	public boolean endTpBack = false;

	public Parkour(String name) {
		this.name = name;
		checkpoints = new ArrayList<>();
		startCheckpoint = null;
		startLocation = null;
	}

	public Parkour(String name, Location startLocation) {
		this.name = name;
		this.startLocation = startLocation;
		checkpoints = new ArrayList<>();
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

	//TODO add functionality
	public void removeCheckpoint() {
		save();
	}

	public void removeCheckpoint(int i) {
		save();
	}

	public static List<String> getParkourNames() {
		if (Main.getInstance().getConfig().contains("parkours")) {
			Set<String> keys = Main.getInstance().getConfig().getConfigurationSection("parkours").getKeys(false);
            return new ArrayList<>(keys);
		} else {
			return new ArrayList<>();
		}
	}

	//TODO no US and parkour checkpoint
	@SuppressWarnings("unchecked")
	public static Parkour load(String name) {
		if (Main.getInstance().getConfig().contains("parkours." + name)) {
			Parkour parkour = new Parkour(name);

			if (Main.getInstance().getConfig().contains("parkours." + name + ".checkpoints")) {
				parkour.checkpoints = (List<ParkourCheckpoint>) Main.getInstance().getConfig().get("parkours." + name + ".checkpoints");
			}

			if (Main.getInstance().getConfig().contains("parkours." + name + ".startCheckpoint")) {
				parkour.startCheckpoint = (ParkourCheckpoint) Main.getInstance().getConfig().get("parkours." + name + ".startCheckpoint");
			}

			if (Main.getInstance().getConfig().contains("parkours." + name + ".startLocation")) {
				parkour.startLocation = Main.getInstance().getConfig().getLocation("parkours." + name + ".startLocation");
			}

			if (Main.getInstance().getConfig().contains("parkours." + name + ".endTpBack")) {
				parkour.endTpBack = Main.getInstance().getConfig().getBoolean("parkours." + name + ".endTpBack");
			}

			return parkour;
		} else {
			return null;
		}

	}

	//TODO Do parkour checkpoint
	public void save() {
		Main.getInstance().getConfig().set("parkours." + name + ".startCheckpoint", startCheckpoint);
		Main.getInstance().getConfig().set("parkours." + name + ".startLocation", startLocation);
		Main.getInstance().getConfig().set("parkours." + name + ".checkpoints", checkpoints);
		Main.getInstance().getConfig().set("parkours." + name + ".endTpBack", endTpBack);
		Main.getInstance().saveConfig();
	}

	public void delete() {
		Main.getInstance().getConfig().set("parkours." + name, null);
		Main.getInstance().saveConfig();
	}
}
