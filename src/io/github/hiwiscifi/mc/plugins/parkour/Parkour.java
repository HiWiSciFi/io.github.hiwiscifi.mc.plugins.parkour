package io.github.hiwiscifi.mc.plugins.parkour;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;

public class Parkour {
	public String name;
	public List<Location> checkpoints;
	
	public Parkour(String name) {
		this.name = name;
		checkpoints = new ArrayList<Location>();
	}
	
	public void AddCheckpoint(Location location) {
		Location loc = location.toBlockLocation();
		loc.getBlock().setType(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
		checkpoints.add(loc);
		Save();
	}
	
	public void RemoveCheckpoint() {
		Save();
	}
	
	public void Save() {
		Main.Instance.getConfig().set("parkours."+name+".checkpoints", checkpoints);
		Main.Instance.saveConfig();
	}
}
