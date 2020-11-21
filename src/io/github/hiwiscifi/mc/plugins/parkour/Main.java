package io.github.hiwiscifi.mc.plugins.parkour;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		System.out.println("Initializing parkour plugin...");
		System.out.println("Parkour plugin initialized!");
	}
	
	@Override
	public void onDisable() {
		System.out.println("Preventing memory leaks...");
		System.out.println("done");
		
		System.out.println("Saving data to files...");
		System.out.println("done");
	}
}
