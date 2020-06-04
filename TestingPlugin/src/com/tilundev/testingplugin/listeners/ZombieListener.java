package com.tilundev.testingplugin.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ZombieListener implements Listener {
	
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent creature) {
		if (creature.getEntityType().equals(EntityType.ZOMBIE)) {
			creature.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 900000, 3)); // 20 Tick => 1 sec
//			System.out.println("One Zombie Affected with SPEED 3  900000 ticks");
		}
		else {
//			System.out.println("None affected");
		}
	}

}
