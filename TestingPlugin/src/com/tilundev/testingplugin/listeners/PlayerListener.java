package com.tilundev.testingplugin.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;

import com.tilundev.testingplugin.scoreboard.ScoreboardObjectives;

public class PlayerListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		event.getPlayer().setScoreboard(ScoreboardObjectives.getScoreboard());
	}
	
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent block) {
		// Search for a better method
		List<Material> logs = new ArrayList<>();
		logs.add(Material.ACACIA_LOG);
		logs.add(Material.BIRCH_LOG);
		logs.add(Material.DARK_OAK_LOG);
		logs.add(Material.JUNGLE_LOG);
		logs.add(Material.OAK_LOG);
		logs.add(Material.SPRUCE_LOG);
		logs.add(Material.STRIPPED_ACACIA_LOG);
		logs.add(Material.STRIPPED_BIRCH_LOG);
		logs.add(Material.STRIPPED_DARK_OAK_LOG);
		logs.add(Material.STRIPPED_JUNGLE_LOG);
		logs.add(Material.STRIPPED_OAK_LOG);
		logs.add(Material.STRIPPED_SPRUCE_LOG);
		
		if (logs.contains(block.getBlock().getType())) {
			ScoreboardObjectives.addScore(0);
		} 
		else if (block.getBlock().getType() == Material.STONE) {
			ScoreboardObjectives.addScore(1);
		}
		else if (block.getBlock().getType() == Material.COAL_ORE) {
			ScoreboardObjectives.addScore(2);
		}
	}
	
	@EventHandler
	public void onPlayerItemConsume(PlayerItemConsumeEvent pic) {
		if(pic.getItem().getType().equals(Material.POTION)) {
			PotionMeta potionMeta = (PotionMeta) pic.getItem().getItemMeta();
			if(potionMeta.getBasePotionData().getType().equals(PotionType.WATER)) {
				Bukkit.broadcastMessage("Oh regarder cet homme qui a bu de l'eau. Quel homme réhydraté!");
			}
		}
	}
	

}
