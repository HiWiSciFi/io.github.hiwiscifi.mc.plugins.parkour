package io.github.hiwiscifi.mc.plugins.parkour.utils;

import java.time.Duration;
import java.util.List;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import io.github.hiwiscifi.mc.plugins.parkour.Main;

public class ParkourLogic {



	/**
	 * checks if a certain parkour can be started by a given player and if so sets
	 * all the variables to start the parkour
	 *
	 * @param player  the player to start the parkour for
	 * @param parkour the parkour to start
	 */
	public static void startParkour(Player player, Parkour parkour) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR) {
			return;
		} else if (player.getGameMode() == GameMode.SURVIVAL) {
			player.setGameMode(GameMode.ADVENTURE);
		}

		pdc.set(StringUtil.currentCheckpointKey, PersistentDataType.INTEGER, 0);
		pdc.set(StringUtil.currentParkourKey, PersistentDataType.STRING, parkour.name);
		pdc.set(StringUtil.onParkourKey, PersistentDataType.INTEGER, 1);

		player.sendMessage(StringUtil.OUT_PREFIX
			.append(Component.text("You are now on the "))
			.append(Component.text(parkour.name))
			.append(Component.text(" parkour"))
		);
		// TODO set item and check gamemode store those items and remove them after
		// parkour is finished or aborted
		if (parkour.checkpoints.isEmpty()) {
			finishParkour(player, parkour);
		}

		ParkourItems.setPlayerInventory(player);

		ParkourTimer.startParkourTimer(player);

		ParkourTimer.startCheckpointTimer(player);
		applyEffect(player, parkour.startCheckpoint);
	}

	/**
	 * restart a parkour for a given player
	 *
	 * @param player  the player to start the parkour for
	 * @param parkour the parkour to start
	 */
	public static void restartParkour(Player player, Parkour parkour) {
		ParkourTimer.startParkourTimer(player);
		applyEffect(player, parkour.startCheckpoint);

	}

	/**
	 * set the current checkpoint for a player on a parkour
	 *
	 * @param player     the player currently on the parkour
	 * @param parkour    the parkour the player is on
	 * @param checkpoint the index of the checkpoint in the parkour
	 */
	public static void startCheckpoint(Player player, Parkour parkour, int checkpoint) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		pdc.set(StringUtil.currentCheckpointKey, PersistentDataType.INTEGER, checkpoint);

		long timeSinceLastCheckpoint = ParkourTimer.endParkourTimer(player);
		player.sendMessage(StringUtil.OUT_PREFIX
				.append(Component.text("Your time for the last Checkpoint was: "))
				.append(Component.text(ParkourTimer.getTimeStringFromMs(timeSinceLastCheckpoint))));

		ParkourTimer.startCheckpointTimer(player);
		applyEffect(player, parkour.checkpoints.get(checkpoint - 1));
	}

	/**
	 * restart the parkour from a specific checkpoint
	 *
	 * @param player     the player to restart for
	 * @param parkour    the parkour the player is on
	 * @param checkpoint the checkpoint to restart from
	 */
	public static void restartCheckpoint(Player player, Parkour parkour, int checkpoint) {
		ParkourTimer.startCheckpointTimer(player);

		applyEffect(player, parkour.checkpoints.get(checkpoint - 1));

	}

	/**
	 * finish a parkour for a certain player
	 *
	 * @param player  the player to finish the parkour
	 * @param parkour the parkour to finish
	 */
	public static void finishParkour(Player player, Parkour parkour) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		pdc.set(StringUtil.onParkourKey, PersistentDataType.INTEGER, 0);

		// https://en.wikipedia.org/wiki/Box-drawing_character

		long lastCheckpointTime = ParkourTimer.endCheckpointTimer(player);
		long parkourTime = ParkourTimer.endParkourTimer(player);

		spawnFireworks(player);
		// TODO pb tracker with pdc
		// TODO Return Leaderboard rank or personal best
		player.sendMessage(Component.text()
			.append(Component.text("ooo ", StringUtil.GREEN).decoration(TextDecoration.BOLD, true).decoration(TextDecoration.OBFUSCATED, true))
			.append(Component.text("CONGRATULATIONS!", StringUtil.GREEN).decoration(TextDecoration.BOLD, true))
			.append(Component.text(" ooo", StringUtil.GREEN).decoration(TextDecoration.BOLD, true).decoration(TextDecoration.OBFUSCATED, true))
		);

		player.sendMessage(Component.text("Your time to complete the parkour was: ", StringUtil.GREEN)
			.append(Component.text(ParkourTimer.getTimeStringFromMs(parkourTime), StringUtil.GOLD).decoration(TextDecoration.BOLD, true))
		);

		player.sendMessage(Component.text("Your time for the last Checkpoint was: ", StringUtil.AQUA)
			.append(Component.text(ParkourTimer.getTimeStringFromMs(lastCheckpointTime), StringUtil.GOLD).decoration(TextDecoration.BOLD, true))
		);

		player.showTitle(Title.title(Component.text("Hello"), Component.text("World"),
			Title.Times.times(Duration.ofSeconds(10), Duration.ofSeconds(30), Duration.ofSeconds(20))));

		player.showTitle(Title.title(
			Component.text()
				.append(Component.text("ᐅ", StringUtil.GREEN))
				.append(Component.text("CONGRATULATIONS!", StringUtil.GREEN).decoration(TextDecoration.BOLD, true))
				.append(Component.text("ᐊ", StringUtil.GREEN))
				.build(),
			Component.text()
				.append(Component.text("Your Time: ", StringUtil.GOLD))
				.append(Component.text(ParkourTimer.getTimeStringFromMs(parkourTime), StringUtil.GOLD))
				.build(),
			Title.Times.times(
				Duration.ofMillis(500),
				Duration.ofMillis(1500),
				Duration.ofMillis(1000))
		));

		if (parkour.endTpBack) {
			player.teleport(parkour.startLocation);
		}
	}

	private static void spawnFireworks(Player player) {

		Location loc = player.getLocation();
		int diameter = 5;
		World w = player.getWorld();

		for (int i = 0; i < 6; i++) {
			Location newLocation = loc.add(new Vector(Math.random() - 0.5, 2, Math.random() - 0.5).multiply(diameter));
			Firework fw = (Firework) w.spawnEntity(newLocation, EntityType.FIREWORK);
			FireworkMeta fwm = fw.getFireworkMeta();
			fwm.setPower(2);

			if (i % 2 == 0) {
				fwm.addEffect(FireworkEffect.builder().withColor(Color.LIME).flicker(true).build());
			} else {
				fwm.addEffect(FireworkEffect.builder().withColor(Color.YELLOW).flicker(true).build());
			}

			fw.setFireworkMeta(fwm);
			fw.detonate();
		}
	}

	/**
	 * apply an effect to a player
	 *
	 * @param player      the player to apply the effect to
	 * @param effectPoint effect point to apply
	 */
	public static void applyEffect(Player player, EffectPoint effectPoint) {
		effectPoint.apply(player);
	}

	/**
	 * cancel any parkour for a given player
	 *
	 * @param player the player to cancel the parkour running for
	 */
	public static void totalAbortion(Player player) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		pdc.set(StringUtil.currentCheckpointKey, PersistentDataType.INTEGER, 0);
		pdc.set(StringUtil.currentParkourKey, PersistentDataType.STRING, "");
		pdc.set(StringUtil.onParkourKey, PersistentDataType.INTEGER, 0);

		// stop timer without handling output
		ParkourTimer.endParkourTimer(player);
		ParkourTimer.endCheckpointTimer(player);
	}

	public static void restartCheckpoint(Player player) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();

		boolean onParkour = pdc.get(StringUtil.onParkourKey, PersistentDataType.INTEGER) == 1;
		String currentParkour = pdc.get(StringUtil.currentParkourKey, PersistentDataType.STRING);
		int currentCheckpoint = pdc.get(StringUtil.currentCheckpointKey, PersistentDataType.INTEGER);

		if(!onParkour) return;
		List<Parkour> parkours = Main.getInstance().parkours;
		Parkour parkour = null;

        for (Parkour value : parkours) {
            if (value.name.equals(currentParkour)) {
                parkour = value;
            }
        }

		if(parkour == null) {
			pdc.set(StringUtil.onParkourKey, PersistentDataType.INTEGER, 0);
			return;
		}

		if(currentCheckpoint == 0) {
			restartParkour(player, parkour);
		}
		else {
			restartCheckpoint(player, parkour, currentCheckpoint);
		}
	}
}
