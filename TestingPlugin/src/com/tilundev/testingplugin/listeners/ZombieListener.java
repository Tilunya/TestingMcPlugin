package com.tilundev.testingplugin.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.tilundev.testingplugin.listeners.event.ZombieEvent;
import com.tilundev.testingplugin.util.ZombieEnum;

public class ZombieListener implements Listener {
	
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent creature) {
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent ede) {
		if(ZombieEnum.isContain(ede.getDamager().getType()) && ede.getEntity().getType().equals(EntityType.PLAYER)) {
			ZombieEvent.hitZombieEffect(ede);
		}
	}

}
