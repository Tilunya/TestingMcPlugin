package com.tilundev.testingplugin.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.tilundev.testingplugin.listeners.functional.PlayerEvent;
import com.tilundev.testingplugin.scoreboard.ScoreboardObjectives;

public class PlayerListener implements Listener {
	
	//TODO : Those two variables can be moved into a configuration file.
	private int maxHightBeforeInjury = 5;
	private int maxDamageBeforeInjury = 5;
	private Material bandage = Material.BREAD;
	
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
	
	/**
	 * Event when player fall from a high place.
	 * @param entityDamaged
	 */
	@EventHandler
	public void onPlayerFall(EntityDamageEvent entityDamaged) {
		if(entityDamaged.getCause().equals(DamageCause.FALL) && entityDamaged.getDamage() > maxHightBeforeInjury) {
			PlayerEvent.brokenHarm((LivingEntity) entityDamaged.getEntity());
		}
	}
	
	/**
	 * Event when player gets a violent hit.
	 * @param entityHit
	 */
	@EventHandler
	public void onPlayerHit(EntityDamageByEntityEvent entityHit) {
		if((entityHit.getCause().equals(DamageCause.ENTITY_ATTACK) || entityHit.getCause().equals(DamageCause.PROJECTILE)) && entityHit.getDamage() > maxDamageBeforeInjury){
			PlayerEvent.brokenLeg((LivingEntity) entityHit.getEntity());
		}
	}
	
	/**
	 * Event when player use a bandage (consumable item) for healing purpose.
	 * @param consumeItem
	 */
	@EventHandler
	public void onPlayerHealing(PlayerItemConsumeEvent consumeItem) {
		if(consumeItem.getItem().getType().equals(bandage)) {
			PlayerEvent.playerHealingInjuries((LivingEntity) consumeItem.getPlayer());
		}
	}
}
