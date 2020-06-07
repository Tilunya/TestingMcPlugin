package com.tilundev.testingplugin.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
	
	/**
	 * Whenever player lose health after falling from a high place, he gets a broken leg.
	 * After losing at least 5 hearts from a fall, the player will gets a permanent slow effect.
	 * @param entityDamaged
	 */
	@EventHandler
	public void onPlayerFall(EntityDamageEvent entityDamaged) {
		LivingEntity player = (LivingEntity) entityDamaged.getEntity();
		if(entityDamaged.getCause().equals(DamageCause.FALL) && entityDamaged.getDamage() > 5) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 0));
			//add "broken leg" to the status scoreboard of the player.
		}
	}
	
	/**
	 * Whenever player lose a high amount of health from a hit, he gets a broken harm.
	 * After losing at least 5 hearts from a hit, the player will gets a permanent mining fatigue effect.
	 * @param entityHit
	 */
	@EventHandler
	public void onPlayerHit(EntityDamageByEntityEvent entityHit) {
		LivingEntity player = (LivingEntity) entityHit.getEntity();
		if((entityHit.getCause().equals(DamageCause.ENTITY_ATTACK) || entityHit.getCause().equals(DamageCause.PROJECTILE)) && entityHit.getDamage() > 5){
			player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE, 0));
			//add "broken harm" to the status scoreboard of the player.
		}
	}
	

}
