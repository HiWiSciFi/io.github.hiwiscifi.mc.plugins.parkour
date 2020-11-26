package io.github.hiwiscifi.mc.plugins.parkour;
//TODO same name
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;

import io.github.hiwiscifi.mc.plugins.parkour.utils.US;

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

	//TODO change naming
	public void AddCheckpoint(Location location) {
		Location loc = location.toBlockLocation();
		if (startCheckpoint == null) {
			loc.getBlock().setType(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
			startCheckpoint = loc;
		} else {
			loc.getBlock().setType(Material.HEAVY_WEIGHTED_PRESSURE_PLATE);
			checkpoints.add(loc);
		}
		Save();
	}

	//TODO change naming
	public void RemoveCheckpoint() {
		Save();
	}

	public static List<String> getParkourNames() {
		if (Main.getInstance().getConfig().contains(US.getString(10))) {
			Set<String> keys = Main.getInstance().getConfig().getConfigurationSection(US.getString(10)).getKeys(false);
			List<String> names = new ArrayList<String>();
			for (String s : keys)
				names.add(s);
			return names;
		} else
			return new ArrayList<String>();
	}

	//TODO change naming
	@SuppressWarnings("unchecked")
	public static Parkour Load(String name) {
		if (Main.getInstance().getConfig().contains(US.getString(10) + US.DOT + name)) {
			Parkour parkour = new Parkour(name);
			if (Main.getInstance().getConfig().contains(US.getString(10) + US.DOT + name + US.DOT + US.getString(11)))
				parkour.checkpoints = (List<Location>) Main.getInstance().getConfig().getList(US.getString(10) + US.DOT + name + US.DOT + US.getString(11), parkour.checkpoints);
			if (Main.getInstance().getConfig().contains(US.getString(10) + US.DOT + name + US.DOT + US.getString(12)))
				parkour.startCheckpoint = Main.getInstance().getConfig().getLocation(US.getString(10) + US.DOT + name + US.DOT + US.getString(12));
			if (Main.getInstance().getConfig().contains(US.getString(10) + US.DOT + name + US.DOT + US.getString(13)))
				parkour.startLocation = Main.getInstance().getConfig().getLocation(US.getString(10) + US.DOT + name + US.DOT + "startLocation");
			return parkour;
		} else
			return null;
	}

	//TODO change naming
	public void Save() {
		Main.getInstance().getConfig().set(US.getString(10) + US.DOT + name + US.DOT + US.getString(12), startCheckpoint);
		Main.getInstance().getConfig().set(US.getString(10) + US.DOT + name + US.DOT + US.getString(13), startLocation);
		Main.getInstance().getConfig().set(US.getString(10) + US.DOT + name + US.DOT + US.getString(11), checkpoints);
		Main.getInstance().saveConfig();
	}

	//TODO change naming
	public void Delete() {
		Main.getInstance().getConfig().set(US.getString(10) + US.DOT + name, null);
		Main.getInstance().saveConfig();
	}
}
