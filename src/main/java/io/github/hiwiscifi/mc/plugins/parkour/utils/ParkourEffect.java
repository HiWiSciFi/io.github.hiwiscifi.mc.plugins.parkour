package io.github.hiwiscifi.mc.plugins.parkour.utils;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;

import io.github.hiwiscifi.mc.plugins.parkour.utils.effects.EffectEffect;
import io.github.hiwiscifi.mc.plugins.parkour.utils.effects.TeleportEffect;
import org.jetbrains.annotations.NotNull;

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
        return switch (args[0].toLowerCase()) {
            case "tp", "teleport" -> {
                args[0] = "teleport";
                yield new TeleportEffect(args);
            }
            case "effect", "effekt" -> {
                args[0] = "effekt";
                yield new EffectEffect(args);
            }
            default -> null;
        };
	}

	@Override
	@NotNull
	public Map<String, Object> serialize() {
		Map<String, Object> map = new HashMap<>();
		map.put("arguments", arguments);
		return map;
	}

	public static ParkourEffect deserialize(Map<String, Object> map) {
		if(map == null) {
			return null;
		}


        return createEffect((String[]) map.get("arguments"));
	}

    @Override
    public ParkourEffect clone() {
        try {
            return (ParkourEffect) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
