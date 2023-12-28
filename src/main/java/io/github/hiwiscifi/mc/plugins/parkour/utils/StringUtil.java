package io.github.hiwiscifi.mc.plugins.parkour.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.NamespacedKey;

import io.github.hiwiscifi.mc.plugins.parkour.Main;

public class StringUtil {

	public final static TextColor BLACK        = TextColor.color( 0,   0,   0   );
	public final static TextColor DARK_BLUE    = TextColor.color( 0,   0,   170 );
	public final static TextColor DARK_GREEN   = TextColor.color( 0,   170, 0   );
	public final static TextColor DARK_AQUA    = TextColor.color( 0,   170, 170 );
	public final static TextColor DARK_RED     = TextColor.color( 170, 0,   0   );
	public final static TextColor DARK_PURPLE  = TextColor.color( 170, 0,   170 );
	public final static TextColor GOLD         = TextColor.color( 255, 170, 0   );
	public final static TextColor GRAY         = TextColor.color( 170, 170, 170 );
	public final static TextColor DARK_GRAY    = TextColor.color( 58,  58,  58  );
	public final static TextColor BLUE         = TextColor.color( 58,  58,  255 );
	public final static TextColor GREEN        = TextColor.color( 58,  255, 58  );
	public final static TextColor AQUA         = TextColor.color( 58,  255, 255 );
	public final static TextColor RED          = TextColor.color( 255, 58,  58  );
	public final static TextColor LIGHT_PURPLE = TextColor.color( 255, 58,  255 );
	public final static TextColor YELLOW       = TextColor.color( 255, 255, 58  );
	public final static TextColor WHITE        = TextColor.color( 255, 255, 255 );

	public final static TextComponent EMPTY = Component.text("");
	public final static TextComponent OUT_PREFIX = Component.text().color(WHITE).append(Component.text("[Parkour] ", AQUA)).build();

	public final static String itemVal_reset_checkpoint = "reset checkpoint";
	public final static String itemVal_reset_start = "reset start";
	public final static String itemVal_cancelParkour = "cancel parkour";

	/** the key for the onParkour:Integer value in a players persistentDataContainer */
	public static NamespacedKey onParkourKey;
	/** the key for the currentParkour:String value in a players persistentDataContainer */
	public static NamespacedKey currentParkourKey;
	/** the key for the currentCheckpoint:Integer value in a players persistentDataContainer */
	public static NamespacedKey currentCheckpointKey;

	/** the key for the timerStartParkourTime:Long value in a players persistentDataContainer */
	public static NamespacedKey timerStartParkourTimeKey;
	/** the key for the timerStartCheckpointTime:Long value in a players persistentDataContainer */
	public static NamespacedKey timerStartCheckpointTimeKey;

	/** the key for the itemInteractionType:String value in an items persistentDataContainer */
	public static NamespacedKey itemInteractionTypeKey;

	/** Initializes the NamespacedKeys for later use */
	public static void initializeKeys() {
		onParkourKey = new NamespacedKey(Main.getInstance(), "onParkour");
		currentParkourKey = new NamespacedKey(Main.getInstance(), "currentParkour");
		currentCheckpointKey = new NamespacedKey(Main.getInstance(), "currentCheckpoint");

		timerStartParkourTimeKey = new NamespacedKey(Main.getInstance(), "timerStartParkourTime");
		timerStartCheckpointTimeKey = new NamespacedKey(Main.getInstance(), "timerStartCheckpointTime");

		itemInteractionTypeKey = new NamespacedKey(Main.getInstance(), "itemInteractionType");
	}

	/** put a string into quotes
	 * @param content the string to put quotes around
	 * @return the original string in quotes */
	public static TextComponent inQuotes(TextComponent content) {
		return Component.text().content("\"").append(content).append(Component.text("\"")).build();
	}

	/** Fills up a string to a set length with minuses on both sides
	 * @param content the string to fill up
	 * @param desiredLength the desired final length of the string
	 * @return the string with added minuses */
	public static String fillWithMinus(String content, int desiredLength) {
		if (desiredLength - content.length() > 1) {
			if ((desiredLength - content.length()) % 2 != 0) {
				desiredLength -= 1;
			}
			int toAdd = (desiredLength - content.length()) / 2;
			StringBuilder str = new StringBuilder();
            str.append("-".repeat(Math.max(0, toAdd)));
			return str + content + str;
		}
		return content;
	}
}
