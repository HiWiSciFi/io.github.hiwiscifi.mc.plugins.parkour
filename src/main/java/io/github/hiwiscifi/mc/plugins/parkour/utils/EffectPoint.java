package io.github.hiwiscifi.mc.plugins.parkour.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@SerializableAs("effectPoint")
public class EffectPoint implements Cloneable, ConfigurationSerializable{

	public List<ParkourEffect> effects = new ArrayList<ParkourEffect>();

	public Location location;

	public EffectPoint(Location location) {
		this.location = location;
	}

	public EffectPoint(Location location, List<ParkourEffect> effects) {
		this.location = location;
		this.effects = effects;
	}

	public void apply(Player player) {
		for (ParkourEffect effect : effects) {
			effect.apply(player);
		}
	}

	public void addEffect(String[] args) {
		effects.add(ParkourEffect.createEffect(args));
	}

	public void addEffect(ParkourEffect effect) {
		effects.add(effect);
	}

	public void removeEffect(int i) {
		effects.remove(i);
	}

	public String[] getTypes() {
		List<String> types = new ArrayList<>();
		for (ParkourEffect effect : effects) {
			types.add(effect.type);
		}
		return (String[]) types.toArray();
	}

	@Override
	@NotNull
	public Map<String, Object> serialize() {

		Map<String, Object> map = new HashMap<>();
		map.put("location", location);
		map.put("effects",effects);

		return map;
	}

	@SuppressWarnings("unchecked")
	public static EffectPoint deserialize(Map<String, Object> map) {
		if(map == null) {
			return null;
		}

		EffectPoint target = new ParkourCheckpoint((Location) map.get("location"));
		if(map.containsKey("effects")) {
			target.effects = (List<ParkourEffect>) map.get("effects");
		}

		return target;
	}

    @Override
    public EffectPoint clone() {
        try {
            return (EffectPoint) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
