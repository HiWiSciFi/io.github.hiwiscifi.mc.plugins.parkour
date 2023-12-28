package io.github.hiwiscifi.mc.plugins.parkour.utils;

import java.util.ArrayList;
import java.util.List;

import net.kyori.adventure.text.Component;
import org.bukkit.World;
import org.bukkit.entity.Player;

import io.github.hiwiscifi.mc.plugins.parkour.Main;

public class WorldControl {

	/** the list to save enabled parkours in */
	private static List<String> enabledWorlds = new ArrayList<>();

	/** check if parkours are enabled in a certain world
	 * @param worldName the name of the world to check for
	 * @return true, if parkours are enabled */
	public static boolean enabled(String worldName) {
		return enabled(worldName, null);
	}

	/** check if parkours are enabled in a certain world
	 * @param worldName the name of the world to check for
	 * @param player an optional player to send an error message to if world is not enabled
	 * @return true, if parkours are enabled */
	public static boolean enabled(String worldName, Player player) {
		if (enabledWorlds.contains(worldName)) {
			return true;
		}
		if (player != null) {
			player.sendMessage(StringUtil.OUT_PREFIX
					.append(Component.text("Nah, not looking good pal - seems like you have to add this world first using ", StringUtil.RED))
					.append(Component.text("/parkour world add", StringUtil.AQUA)));
		}
		return false;
	}

	/** check if parkours are enabled in a certain world
	 * @param world the world to check for
	 * @return true, if parkours are enabled */
	public static boolean enabled(World world) {
		return enabled(world, null);
	}

	/** check if parkours are enabled in a certain world
	 * @param world the world to check for
	 * @param player an optional player to send an error message to if world is not enabled
	 * @return true, if parkours are enabled */
	public static boolean enabled(World world, Player player) {
		return enabled(world.getName(), player);
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
		enabledWorlds = Main.getInstance().getConfig().getStringList("enabledWorlds");
	}

	/** get the list of parkour-enabled worlds
	 * @return a list of the names of enabled worlds and an empty arraylist if no worlds are enabled */
	public static List<String> getEnabledWorldNames() {
		if (Main.getInstance().getConfig().contains("enabledWorlds")) {
			return Main.getInstance().getConfig().getStringList("enabledWorlds");
		}
		return new ArrayList<>();
	}

	/** save list of enabled worlds to the config */
	private static void saveWorlds() {
		Main.getInstance().getConfig().set("enabledWorlds", enabledWorlds);
		Main.getInstance().saveConfig();
	}
}
