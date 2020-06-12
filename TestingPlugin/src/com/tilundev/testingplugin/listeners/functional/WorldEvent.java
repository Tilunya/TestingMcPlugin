package com.tilundev.testingplugin.listeners.functional;

import org.bukkit.event.entity.CreatureSpawnEvent;

import com.tilundev.testingplugin.util.ZombieEnum;

public class WorldEvent {

	/**
	 * Each time a mob want to spawn, remove it if it's not an allowed one.
	 * @param entity
	 */
	public static void manageMobSpawning(CreatureSpawnEvent entity) {
        if (!ZombieEnum.isContain(entity.getEntity().getType())) {
        	entity.getEntity().remove();
        }
    }
}
