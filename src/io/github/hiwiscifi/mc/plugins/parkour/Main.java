package io.github.hiwiscifi.mc.plugins.parkour;

import java.util.HashMap;
import java.util.List;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.hiwiscifi.mc.plugins.parkour.commands.Command_holo;
import io.github.hiwiscifi.mc.plugins.parkour.commands.Command_parkour;
import io.github.hiwiscifi.mc.plugins.parkour.commands.Command_test;
import io.github.hiwiscifi.mc.plugins.parkour.listeners.Listener_PlayerJoin;
import io.github.hiwiscifi.mc.plugins.parkour.listeners.Listener_PressurePlate;

public class Main extends JavaPlugin {
	
	public static HashMap<String, List<ParkourCheckpoint>> parkours = new HashMap<String, List<ParkourCheckpoint>>();
	
	@Override
	public void onEnable() {
		System.out.println("Initializing parkour plugin...");
		
		// Register Commands
		this.getCommand("test").setExecutor(new Command_test());
		this.getCommand("holo").setExecutor(new Command_holo());
		this.getCommand("parkour").setExecutor(new Command_parkour());
		
		// Register Events
		this.getServer().getPluginManager().registerEvents(new Listener_PlayerJoin(), this);
		this.getServer().getPluginManager().registerEvents(new Listener_PressurePlate(), this);
		
		System.out.println("Loading parkours from file");
		
		System.out.println("Parkours loaded!");
		
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
