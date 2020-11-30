package io.github.hiwiscifi.mc.plugins.parkour.utils;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

@ SerializableAs("Effect")
public abstract class ParkourEffect implements Cloneable, ConfigurationSerializable{

	public ParkourEffect(String[] args) {
		arguments = args;
	}

	public String[] arguments;

	public abstract void apply();
}
