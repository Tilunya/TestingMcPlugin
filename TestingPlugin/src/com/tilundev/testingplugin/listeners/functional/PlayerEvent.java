package com.tilundev.testingplugin.listeners.functional;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerEvent {

	/**
	 * Whenever player lose health after falling from a high place, he gets a broken leg.
	 * After losing at least 5 hearts from a fall, the player will gets a permanent slow effect.
	 * @param player
	 */
	public static void brokenHarm(LivingEntity player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 0));
		//TODO : add "broken leg" to the status scoreboard of the player.
	}
	
	/**
	 * Whenever player lose a high amount of health from a hit, he gets a broken harm.
	 * After losing at least 5 hearts from a hit, the player will gets a permanent mining fatigue effect.
	 * @param player
	 */
	public static void brokenLeg(LivingEntity player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE, 0));
		//TODO : add "broken harm" to the status scoreboard of the player.
	}
	
	/**
	 * Remove slow and slow mining effect corresponding to broken harm and leg.
	 * @param player
	 */
	public static void playerHealingInjuries(LivingEntity player) {
		for (PotionEffect effect : player.getActivePotionEffects()) {
			if(effect.getType().equals(PotionEffectType.SLOW) || effect.getType().equals(PotionEffectType.SLOW_DIGGING)) {
				player.removePotionEffect(effect.getType());
				//TODO : remove "broken leg" and "broken harm" to the status scoreboard of the player.
			}
        }
	}
}
