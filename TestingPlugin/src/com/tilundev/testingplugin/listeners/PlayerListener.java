package com.tilundev.testingplugin.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import com.tilundev.testingplugin.data.PersistData;
import com.tilundev.testingplugin.data.PlayerData;
import com.tilundev.testingplugin.scoreboard.ScoreboardObjectives;
import com.tilundev.testingplugin.scoreboard.StateScoreboard;

public class PlayerListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		PlayerData playerData = PersistData.addPersistantPlayer(player);
		StateScoreboard.initState(playerData);
	}

	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent pqe) {
		Player player = pqe.getPlayer();
		PersistData.removePersistantPlayer(player);
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
				PlayerData playerData = PersistData.getPlayerData(pic.getPlayer());
				if(playerData != null) {
					playerData.get_state().drinkClearWater();
					StateScoreboard.updateScoreboard(playerData);
				}
			}
		}
	}
	
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent flc) {
		if(flc.getEntityType().equals(EntityType.PLAYER)) {
			Player player = (Player) flc.getEntity();
			PlayerData playerData = PersistData.getPlayerData(player);
			playerData.get_state().lostHydration();
			StateScoreboard.updateScoreboard(playerData);
			if(Double.compare(playerData.get_state().get_hydration(),25.0) < 0) {
				
			}
		}
	}
	

}
