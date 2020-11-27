package io.github.hiwiscifi.mc.plugins.parkour;

//BIG TODOS
//TODO respawn checkpoint not with block pos
//TODO inv bar
//TODO remove all sysoutsprints
//TODO reset to deafault and abort in dirfrent class
//TODO world change and gamemode change

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
import io.github.hiwiscifi.mc.plugins.parkour.utils.US;

public class Main extends JavaPlugin {

	public static Main getInstance() { return instance; }
	private static Main instance;

	public List<Parkour> parkours = new ArrayList<Parkour>();

	@Override
	public void onEnable() {
		US.initializeStringCollection();

		System.out.println(US.fillWithMinus(US.getString(0), 40));

		instance = this;

		System.out.println(US.OUT_PREFIX + US.getString(1) + US.THREE_DOTS);
		//TODO Remove
		this.getCommand(US.getString(2)).setExecutor(new Command_test());
		this.getCommand(US.getString(3)).setExecutor(new Command_parkour());

		System.out.println(US.OUT_PREFIX + US.getString(4) + US.THREE_DOTS);
		this.getServer().getPluginManager().registerEvents(new Listener_PlayerJoin(), this);
		this.getServer().getPluginManager().registerEvents(new Listener_PressurePlate(), this);
		this.getServer().getPluginManager().registerEvents(new Listener_ItemUse(), this);
		this.getServer().getPluginManager().registerEvents(new Listener_PlayerRespawn(), this);

		System.out.println(US.OUT_PREFIX + US.getString(5) + US.THREE_DOTS);
		List<String> parkourNames = Parkour.getParkourNames();
		for (String name : parkourNames) {
			parkours.add(Parkour.Load(name));
		}

		System.out.println(US.fillWithMinus(US.getString(6), 40));
	}

	@Override
	public void onDisable() {
		System.out.println(US.OUT_PREFIX + US.getString(7) + US.THREE_DOTS);

		System.out.println(US.OUT_PREFIX + US.getString(8) + US.THREE_DOTS);

		instance = null;

		// Unregister Events
		HandlerList.unregisterAll();

		// run garbage collector
		System.gc();

		System.out.println(US.OUT_PREFIX + US.getString(9));
	}
}
