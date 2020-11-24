package io.github.hiwiscifi.mc.plugins.parkour;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;

public class Parkour {
	public String name;
	
	public Location startCheckpoint;
	public Location startLocation;
	
	public List<Location> checkpoints;
	
	public Parkour(String name) {
		this.name = name;
		checkpoints = new ArrayList<Location>();
		startCheckpoint = null;
		startLocation = null;
	}
	
	public Parkour(String name, Location startLocation) {
		this.name = name;
		this.startLocation = startLocation;
		checkpoints = new ArrayList<Location>();
		startCheckpoint = null;
	}
	
	public void AddCheckpoint(Location location) {
		Location loc = location.toBlockLocation();
		if (startCheckpoint == null) {
			loc.getBlock().setType(Material.HEAVY_WEIGHTED_PRESSURE_PLATE);
			startCheckpoint = loc;
		} else {
			loc.getBlock().setType(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
			checkpoints.add(loc);
		}
		Save();
	}
	
	public void RemoveCheckpoint() {
		Save();
	}
	
	public static List<String> getParkourNames() {
		if (Main.Instance.getConfig().contains("parkours")) {
			Set<String> keys = Main.Instance.getConfig().getConfigurationSection("parkours").getKeys(false);
			List<String> names = new ArrayList<String>();
			for (String s : keys) {
				names.add(s);
			}
			return names;
		} else {
			return new ArrayList<String>();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Parkour Load(String name) {
		if (Main.Instance.getConfig().contains("parkours." + name)) {
			Parkour parkour = new Parkour(name);
			if (Main.Instance.getConfig().contains("parkours." + name + ".checkpoints")) {
				parkour.checkpoints = (List<Location>) Main.Instance.getConfig().getList("parkours." + name + ".checkpoints", parkour.checkpoints);
			}
			if (Main.Instance.getConfig().contains("parkours." + name + ".startCheckpoint")) {
				parkour.startCheckpoint = Main.Instance.getConfig().getLocation("parkours." + name + ".startCheckpoint");
			}
			if (Main.Instance.getConfig().contains("parkours." + name + ".startLocation")) {
				parkour.startLocation = Main.Instance.getConfig().getLocation("parkours." + name + ".startLocation");
			}
			return parkour;
		} else {
			return null;
		}
	}
	
	public void Save() {
		Main.Instance.getConfig().set("parkours." + name + ".startCheckpoint", startCheckpoint);
		Main.Instance.getConfig().set("parkours." + name + ".startLocation", startLocation);
		Main.Instance.getConfig().set("parkours." + name + ".checkpoints", checkpoints);
		Main.Instance.saveConfig();
	}
	
	public void Delete() {
		Main.Instance.getConfig().set("parkours." + name, null);
		Main.Instance.saveConfig();
	}
}
