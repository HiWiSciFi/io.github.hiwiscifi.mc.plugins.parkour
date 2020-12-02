package io.github.hiwiscifi.mc.plugins.parkour.utils;

import java.util.ArrayList;

import org.bukkit.World;

import io.github.hiwiscifi.mc.plugins.parkour.Main;

public class WorldControl {
	
	/** the list to save enabled parkours in */
	private static ArrayList<String> enabledWorlds = new ArrayList<String>();
	
	/** check if parkours are enabled in a certain world
	 * @param worldName the name of the world to check for
	 * @return true, if parkours are enabled */
	public static boolean enabled(String worldName) {
		return enabledWorlds.contains(worldName);
	}
	
	/** check if parkours are enabled in a certain world
	 * @param worldName the world to check for
	 * @return true, if parkours are enabled */
	public static boolean enabled(World world) {
		return enabled(world.getName());
	}
	
	/** enable parkours in a world
	 * @param worldName the name of the world to make parkours available in */
	public static void addWorld(String worldName) {
		if (!enabledWorlds.contains(worldName)) {
			enabledWorlds.add(worldName);
		}
		saveWorlds();
	}
	
	/** disable parkours in a world
	 * @param worldName the name of the world to make parkours unavailable in */
	public static void removeWorld(String worldName) {
		for (int i = 0; i < enabledWorlds.size(); i++) {
			if (enabledWorlds.get(i).equals(worldName)) {
				enabledWorlds.remove(i);
				break;
			}
		}
		saveWorlds();
	}
	
	/** load list of enabled worlds from config file */
	public static void loadWorlds() {
		Main.getInstance().getConfig().getStringList("enabledWorlds");
	}
	
	/** save list of enabled worlds to the config */
	private static void saveWorlds() {
		Main.getInstance().getConfig().set("enabledWorlds", enabledWorlds);
	}
}
