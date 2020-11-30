package io.github.hiwiscifi.mc.plugins.parkour.utils.effects;

import java.util.Map;

import io.github.hiwiscifi.mc.plugins.parkour.utils.ParkourEffect;

public class TeleportEffect extends ParkourEffect{

	public TeleportEffect(String[] args) {
		super(args);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void apply() {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, Object> serialize() {
		// TODO Auto-generated method stub
		return null;
	}

	public static TeleportEffect deserialize(Map<String, Object> map) {

		//TODO implement
		String args[] = null;

		return new TeleportEffect(args);
	}

}
