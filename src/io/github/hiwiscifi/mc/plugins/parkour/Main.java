package io.github.hiwiscifi.mc.plugins.parkour;

//BIG TODOS
//TODO save current checkpoint on player (already in join event)
//TODO rename key variables

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
import io.github.hiwiscifi.mc.plugins.parkour.utils.UtilityStrings;

public class Main extends JavaPlugin {

	public static Main getInstance() { return instance; }
	private static Main instance;

	public List<Parkour> parkours = new ArrayList<Parkour>();

	@Override
	public void onEnable() {
		UtilityStrings.initializeStringCollection();
		
		System.out.println(UtilityStrings.fillWithMinus(UtilityStrings.getString(0), 40));

		instance = this;

		System.out.println(UtilityStrings.OUT_PREFIX + UtilityStrings.getString(1) + UtilityStrings.THREE_DOTS);
		//TODO Remove
		this.getCommand(UtilityStrings.getString(2)).setExecutor(new Command_test());
		this.getCommand(UtilityStrings.getString(3)).setExecutor(new Command_parkour());

		System.out.println(UtilityStrings.OUT_PREFIX + UtilityStrings.getString(4) + UtilityStrings.THREE_DOTS);
		this.getServer().getPluginManager().registerEvents(new Listener_PlayerJoin(), this);
		this.getServer().getPluginManager().registerEvents(new Listener_PressurePlate(), this);
		this.getServer().getPluginManager().registerEvents(new Listener_ItemUse(), this);
		this.getServer().getPluginManager().registerEvents(new Listener_PlayerRespawn(), this);

		System.out.println(UtilityStrings.OUT_PREFIX + UtilityStrings.getString(5) + UtilityStrings.THREE_DOTS);
		List<String> parkourNames = Parkour.getParkourNames();
		for (String name : parkourNames) {
			parkours.add(Parkour.Load(name));
		}

		System.out.println(UtilityStrings.fillWithMinus(UtilityStrings.getString(6), 40));
	}

	@Override
	public void onDisable() {
		System.out.println(UtilityStrings.OUT_PREFIX + UtilityStrings.getString(7) + UtilityStrings.THREE_DOTS);

		System.out.println(UtilityStrings.OUT_PREFIX + UtilityStrings.getString(8) + UtilityStrings.THREE_DOTS);

		instance = null;

		// Unregister Events
		HandlerList.unregisterAll();

		// run garbage collector
		System.gc();

		System.out.println(UtilityStrings.OUT_PREFIX + UtilityStrings.getString(9));
	}
}
