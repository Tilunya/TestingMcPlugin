package com.zomboplugin.listener.event;

import org.bukkit.event.entity.CreatureSpawnEvent;

import com.zomboplugin.util.ZombieEnumUtil;

public class WorldEvent {

	/**
	 * Each time a mob want to spawn, remove it if it's not an allowed one.
	 * @param entity
	 */
	public static void manageMobSpawning(CreatureSpawnEvent entity) {
        if (!ZombieEnumUtil.isContain(entity.getEntity().getType())) {
        	entity.getEntity().remove();
        }
    }
}
