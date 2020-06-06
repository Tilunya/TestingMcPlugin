package com.tilundev.testingplugin.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.tilundev.testingplugin.listeners.functional.ZombieEvent;
import com.tilundev.testingplugin.util.ZombieEnum;

public class ZombieListener implements Listener {
	
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent creature) {
//		if (creature.getEntityType().equals(EntityType.ZOMBIE)) {
//			creature.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 900000, 3)); // 20 Tick => 1 sec
////			System.out.println("One Zombie Affected with SPEED 3  900000 ticks");
//		}
//		else {
////			System.out.println("None affected");
//		}
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent ede) {
		if(ZombieEnum.isContain(ede.getDamager().getType()) && ede.getEntity().getType().equals(EntityType.PLAYER)) {
			ZombieEvent.hitZombieEffect(ede);
		}
	}

}
