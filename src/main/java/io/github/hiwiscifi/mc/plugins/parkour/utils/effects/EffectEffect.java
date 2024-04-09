package io.github.hiwiscifi.mc.plugins.parkour.utils.effects;

import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.hiwiscifi.mc.plugins.parkour.utils.ParkourEffect;

public class EffectEffect extends ParkourEffect{

	PotionEffect effect;
	public EffectEffect(String[] args) {

		super(args);

		String type = arguments[1];
		int amplifier = Integer.parseInt(arguments[2]);
		int length = 999999;

		if(arguments.length >= 4) {
			length = Integer.parseInt(arguments[3]);
		}

		PotionEffectType effectType = Registry.EFFECT.get(NamespacedKey.minecraft(type));
		if (effectType == null) {
			// TODO: prompt user
			return;
		}

		effect = new PotionEffect(
                effectType,
				amplifier,
				length,
				false,
				false);
	}

	@Override
	public void apply(Player player) {
		player.addPotionEffect(effect);
	}


}
