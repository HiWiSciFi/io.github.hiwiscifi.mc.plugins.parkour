package io.github.hiwiscifi.mc.plugins.parkour;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.hiwiscifi.mc.plugins.parkour.commands.Command_test;
import io.github.hiwiscifi.mc.plugins.parkour.listeners.MyListener;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		System.out.println("Initializing parkour plugin...");
		this.getCommand("test").setExecutor(new Command_test());
		this.getServer().getPluginManager().registerEvents(new MyListener(), this);
		System.out.println("Parkour plugin initialized!");
	}
	
	@Override
	public void onDisable() {
		System.out.println("Preventing memory leaks...");
		HandlerList.unregisterAll();
		System.out.println("done");
		
		System.out.println("Saving data to files...");
		System.out.println("done");
	}
}
