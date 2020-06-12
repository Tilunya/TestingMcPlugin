package com.zomboplugin.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.zomboplugin.listeners.event.ZombieEvent;
import com.zomboplugin.util.ZombieEnum;

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
