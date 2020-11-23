package io.github.hiwiscifi.mc.plugins.parkour;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.hiwiscifi.mc.plugins.parkour.commands.Command_holo;
import io.github.hiwiscifi.mc.plugins.parkour.commands.Command_parkour;
import io.github.hiwiscifi.mc.plugins.parkour.commands.Command_test;
import io.github.hiwiscifi.mc.plugins.parkour.listeners.Listener_PlayerJoin;
import io.github.hiwiscifi.mc.plugins.parkour.listeners.Listener_PressurePlate;

public class Main extends JavaPlugin {
	
	public static Main Instance;
	
	public List<Parkour> parkours = new ArrayList<Parkour>();
	
	@SuppressWarnings("unchecked")
	@Override
	public void onEnable() {
		System.out.println("-----Initializing Parkour plugin-----");
		
		Instance = this;
		
		System.out.println("[Parkour] Registring commands...");
		this.getCommand("test").setExecutor(new Command_test());
		this.getCommand("holo").setExecutor(new Command_holo());
		this.getCommand("parkour").setExecutor(new Command_parkour());
		
		System.out.println("[Parkour] Registring events...");
		this.getServer().getPluginManager().registerEvents(new Listener_PlayerJoin(), this);
		this.getServer().getPluginManager().registerEvents(new Listener_PressurePlate(), this);
		
		System.out.println("[Parkour] Loading parkours from config...");
		if (getConfig().contains("parkours")) {
			Set<String> keys = getConfig().getConfigurationSection("parkours").getKeys(false);
			for (String key : keys) {
				Parkour parkour = new Parkour(key);
				parkour.checkpoints = (List<Location>) getConfig().getList("parkours." + key + ".checkpoints", parkour.checkpoints);
				parkours.add(parkour);
			}
		}
		System.out.println("-----Parkour plugin initialized-----");
	}
	
	@Override
	public void onDisable() {
		System.out.println("[Parkour] Saving data to files...");
		
		System.out.println("[Parkour] Preventing memory leaks...");
		
		// Unregister Events
		HandlerList.unregisterAll();
		
		// run garbage collector
		System.gc();
		
		System.out.println("[Parkour] Plugin disabled");
	}
}
