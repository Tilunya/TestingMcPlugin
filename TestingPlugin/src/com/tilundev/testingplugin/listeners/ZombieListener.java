package com.tilundev.testingplugin.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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

}
