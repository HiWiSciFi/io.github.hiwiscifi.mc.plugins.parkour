package io.github.hiwiscifi.mc.plugins.parkour.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PressurePlateListener implements Listener {
	
	public PressurePlateListener() {
		System.out.println("Initializing pressure plate activation event listener...");
	}
	
	@EventHandler
	public void pressurePlatePress(PlayerInteractEvent e) {
        if(e.getAction().equals(Action.PHYSICAL)) {
        	Block ablock = e.getClickedBlock();
            if (ablock.getType() == Material.STONE_PRESSURE_PLATE) {
            	// stone
            	Bukkit.broadcastMessage("Stone plate activated");
            }
            else if (ablock.getType() == Material.HEAVY_WEIGHTED_PRESSURE_PLATE) {
            	// iron
            	Bukkit.broadcastMessage("Iron plate activated");
            }
            else if (ablock.getType() == Material.LIGHT_WEIGHTED_PRESSURE_PLATE) {
            	// golden
            	Bukkit.broadcastMessage("Golden plate activated");
            }
        }
    }
}
