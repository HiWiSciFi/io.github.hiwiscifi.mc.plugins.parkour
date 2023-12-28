package io.github.hiwiscifi.mc.plugins.parkour;

//BIG TODOS
//TODO respawn checkpoint not with block pos
//TODO inv bar
//TODO do tab completion

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.hiwiscifi.mc.plugins.parkour.commands.Command_gMode;
import io.github.hiwiscifi.mc.plugins.parkour.commands.Command_parkour;
import io.github.hiwiscifi.mc.plugins.parkour.commands.Command_test;
import io.github.hiwiscifi.mc.plugins.parkour.listeners.Listener_ItemUse;
import io.github.hiwiscifi.mc.plugins.parkour.listeners.Listener_PlayerDamage;
import io.github.hiwiscifi.mc.plugins.parkour.listeners.Listener_PlayerJoin;
import io.github.hiwiscifi.mc.plugins.parkour.listeners.Listener_PlayerRespawn;
import io.github.hiwiscifi.mc.plugins.parkour.listeners.Listener_PressurePlate;
import io.github.hiwiscifi.mc.plugins.parkour.utils.EffectPoint;
import io.github.hiwiscifi.mc.plugins.parkour.utils.Parkour;
import io.github.hiwiscifi.mc.plugins.parkour.utils.ParkourCheckpoint;
import io.github.hiwiscifi.mc.plugins.parkour.utils.ParkourEffect;
import io.github.hiwiscifi.mc.plugins.parkour.utils.StringUtil;
import io.github.hiwiscifi.mc.plugins.parkour.utils.WorldControl;

public class Main extends JavaPlugin {

	/** get the singleton instance of this class
	 * @return the only instance of this class */
	public static Main getInstance() { return instance; }
	/** private reference to the singleton instance of this class */
	private static Main instance;

	/** List of parkours that have been registered */
	public List<Parkour> parkours = new ArrayList<>();

	@Override
	public void onEnable() {
		instance = this;

		StringUtil.initializeKeys();

		getLogger().info(StringUtil.fillWithMinus("Initializing Parkour plugin", 40));

		getLogger().info("Registering commands...");
		//TODO Remove
		this.getCommand("test").setExecutor(new Command_test());
		this.getCommand("parkour").setExecutor(new Command_parkour());
		this.getCommand("gMode").setExecutor(new Command_gMode());
		//TODO add teleport

		getLogger().info("Registering events...");
		this.getServer().getPluginManager().registerEvents(new Listener_PlayerJoin(), this);
		this.getServer().getPluginManager().registerEvents(new Listener_PressurePlate(), this);
		this.getServer().getPluginManager().registerEvents(new Listener_ItemUse(), this);
		this.getServer().getPluginManager().registerEvents(new Listener_PlayerRespawn(), this);
		this.getServer().getPluginManager().registerEvents(new Listener_PlayerDamage(), this);

		getLogger().info("Registering serializables...");
		ConfigurationSerialization.registerClass(ParkourCheckpoint.class, "ParkourCheckpoint");
		ConfigurationSerialization.registerClass(EffectPoint.class, "EffectPoint");
		ConfigurationSerialization.registerClass(ParkourEffect.class, "ParkourEffect");

		getLogger().info("Loading parkours from config...");
		List<String> parkourNames = Parkour.getParkourNames();
		for (String name : parkourNames) {
			parkours.add(Parkour.load(name));
		}

		getLogger().info("Loading list of enabled worlds from config...");
		WorldControl.loadWorlds();

		getLogger().info(StringUtil.fillWithMinus("Parkour plugin initialized", 40));
	}

	@Override
	public void onDisable() {
		getLogger().info("Saving data to files...");

		getLogger().info("Preventing memory leaks...");

		instance = null;

		// Unregister Events
		HandlerList.unregisterAll();

		// run garbage collector
		System.gc();

		getLogger().info("Plugin disabled");
	}
}
