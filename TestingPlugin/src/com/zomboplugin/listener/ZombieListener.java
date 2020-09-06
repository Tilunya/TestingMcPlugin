package com.zomboplugin.listener;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.zomboplugin.data.SafezoneData;
import com.zomboplugin.listener.event.WorldEvent;
import com.zomboplugin.listener.event.ZombieEvent;
import com.zomboplugin.util.ZombieEnumUtil;

public class ZombieListener implements Listener {
	
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent creature) {
		if(SafezoneData.isLocationInsideSafezone(creature.getEntity().getLocation())) {
			creature.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent ede) {
		if(ZombieEnumUtil.isContain(ede.getDamager().getType()) && ede.getEntity().getType().equals(EntityType.PLAYER)) {
			ZombieEvent.hitZombieEffect(ede);
		}
		WorldEvent.detectHeadShot(ede);
	}

}
