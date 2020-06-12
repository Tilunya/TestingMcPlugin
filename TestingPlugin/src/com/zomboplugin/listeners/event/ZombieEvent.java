package com.zomboplugin.listeners.event;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ZombieEvent {

	public static void hitZombieEffect(EntityDamageByEntityEvent ede) {
		LivingEntity player = (LivingEntity) ede.getEntity();
		if(Math.random()*100 <50) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 2));
		}

		if(Math.random()*100 <80) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 2));
		}

		if(Math.random()*100 <15) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 5));
		}
	}
}
