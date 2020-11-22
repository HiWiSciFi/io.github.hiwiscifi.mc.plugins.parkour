package io.github.hiwiscifi.mc.plugins.parkour;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.hiwiscifi.mc.plugins.parkour.commands.Command_test;
import io.github.hiwiscifi.mc.plugins.parkour.listeners.PlayerJoinListener;
import io.github.hiwiscifi.mc.plugins.parkour.listeners.PressurePlateListener;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		System.out.println("Initializing parkour plugin...");
		
		// Register Commands
		this.getCommand("test").setExecutor(new Command_test());
		
		// Register Events
		this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
		this.getServer().getPluginManager().registerEvents(new PressurePlateListener(), this);
		
		System.out.println("Parkour plugin initialized!");
	}
	
	@Override
	public void onDisable() {
		System.out.println("Saving data to files...");
		System.out.println("done");
		
		
		System.out.println("Preventing memory leaks...");
		
		// Unregister Events
		HandlerList.unregisterAll();
		
		// run garbage collector
		System.gc();
		
		System.out.println("done");
	}
}
