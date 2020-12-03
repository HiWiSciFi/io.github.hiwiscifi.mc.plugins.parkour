package io.github.hiwiscifi.mc.plugins.parkour.utils;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;

import io.github.hiwiscifi.mc.plugins.parkour.utils.effects.EffectEffect;
import io.github.hiwiscifi.mc.plugins.parkour.utils.effects.TeleportEffect;

@ SerializableAs("effect")
public abstract class ParkourEffect implements Cloneable, ConfigurationSerializable{

	public ParkourEffect(String[] args) {
		arguments = args;

		type = args[0];
	}

	public String type;

	public String[] arguments;

	public abstract void apply(Player player);

	public static ParkourEffect createEffect(String[] args) {
		if (args.length == 0) {
			//TODO show error
			return null;
		}
		switch (args[0].toLowerCase()) {
		case "tp":
		case "teleport":
			args[0] = "teleport";
			return new TeleportEffect(args);
		case "effect":
		case "effekt":
			args[0] = "effekt";
			return new EffectEffect(args);
		default:
			return null;
		}
	}

	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> map = new HashMap<>();
		map.put("arguments", arguments);
		return null;
	}

	public static ParkourEffect deserialize(Map<String, Object> map) {
		if(map == null) {
			return null;
		}


		ParkourEffect effect = createEffect((String[]) map.get("arguments"));
		return effect;
	}
}
