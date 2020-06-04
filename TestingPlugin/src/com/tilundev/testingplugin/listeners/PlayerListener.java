package com.tilundev.testingplugin.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.tilundev.testingplugin.scoreboard.ScoreboardObjectives;
import com.tilundev.testingplugin.timer.TimerPlayer;

public class PlayerListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		event.getPlayer().setScoreboard(ScoreboardObjectives.getScoreboard());
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent block) {
		if (block.getBlock().getType() == Material.ACACIA_LOG || 
				block.getBlock().getType() == Material.BIRCH_LOG || 
				block.getBlock().getType() == Material.DARK_OAK_LOG || 
				block.getBlock().getType() == Material.JUNGLE_LOG || 
				block.getBlock().getType() == Material.OAK_LOG || 
				block.getBlock().getType() == Material.SPRUCE_LOG ) {
			ScoreboardObjectives.addScore(0);
		} 
		else if (block.getBlock().getType() == Material.STONE) {
			ScoreboardObjectives.addScore(1);
		}
		else if (block.getBlock().getType() == Material.COAL_ORE) {
			ScoreboardObjectives.addScore(2);
		}
	}
	
	

}
