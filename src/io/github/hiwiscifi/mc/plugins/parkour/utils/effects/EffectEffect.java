package io.github.hiwiscifi.mc.plugins.parkour.utils.effects;

import java.util.Map;

import org.bukkit.entity.Player;

import io.github.hiwiscifi.mc.plugins.parkour.utils.ParkourEffect;

public class EffectEffect extends ParkourEffect{

	public EffectEffect(String[] args) {
		super(args);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void apply(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, Object> serialize() {
		// TODO Auto-generated method stub
		return null;
	}

	public static EffectEffect deserialize(Map<String, Object> map) {

		//TODO implement
		String args[] = null;

		return new EffectEffect(args);
	}
}
