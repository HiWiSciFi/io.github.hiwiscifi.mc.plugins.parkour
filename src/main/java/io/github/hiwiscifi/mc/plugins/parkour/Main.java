package io.github.hiwiscifi.mc.plugins.parkour;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		getLogger().info("Parkour plugin initialized");
	}

	@Override
	public void onDisable() {
		getLogger().info("Saving data to files...");

		getLogger().info("Preventing memory leaks...");

		// Unregister Events
		HandlerList.unregisterAll();

		// run garbage collector
		System.gc();

		getLogger().info("Plugin disabled");
	}
}
