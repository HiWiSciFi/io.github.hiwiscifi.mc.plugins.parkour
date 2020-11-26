package io.github.hiwiscifi.mc.plugins.parkour;

//BIG TODOS
//TODO change all player references form p to player
//TODO save current checkpoint on player

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.hiwiscifi.mc.plugins.parkour.commands.Command_parkour;
import io.github.hiwiscifi.mc.plugins.parkour.commands.Command_test;
import io.github.hiwiscifi.mc.plugins.parkour.listeners.Listener_ItemUse;
import io.github.hiwiscifi.mc.plugins.parkour.listeners.Listener_PlayerJoin;
import io.github.hiwiscifi.mc.plugins.parkour.listeners.Listener_PlayerRespawn;
import io.github.hiwiscifi.mc.plugins.parkour.listeners.Listener_PressurePlate;

public class Main extends JavaPlugin {

	public static Main getInstance() { return instance; }
	private static Main instance;

	public List<Parkour> parkours = new ArrayList<Parkour>();
	
	@Override
	public void onEnable() {
		System.out.println("-----Initializing Parkour plugin-----");
		
		instance = this;

		System.out.println("[Parkour] Registring commands...");
		//TODO Remove
		this.getCommand("test").setExecutor(new Command_test());
		this.getCommand("parkour").setExecutor(new Command_parkour());

		System.out.println("[Parkour] Registring events...");
		this.getServer().getPluginManager().registerEvents(new Listener_PlayerJoin(), this);
		this.getServer().getPluginManager().registerEvents(new Listener_PressurePlate(), this);
		this.getServer().getPluginManager().registerEvents(new Listener_ItemUse(), this);
		this.getServer().getPluginManager().registerEvents(new Listener_PlayerRespawn(), this);

		System.out.println("[Parkour] Loading parkours from config...");
		List<String> parkourNames = Parkour.getParkourNames();
		for (String name : parkourNames) {
			parkours.add(Parkour.Load(name));
		}
		
		System.out.println("-----Parkour plugin initialized-----");
	}

	@Override
	public void onDisable() {
		System.out.println("[Parkour] Saving data to files...");

		System.out.println("[Parkour] Preventing memory leaks...");

		instance = null;
		
		// Unregister Events
		HandlerList.unregisterAll();

		// run garbage collector
		System.gc();

		System.out.println("[Parkour] Plugin disabled");
	}
}
