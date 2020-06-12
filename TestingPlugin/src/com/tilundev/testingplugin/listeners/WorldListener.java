package com.tilundev.testingplugin.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import com.tilundev.testingplugin.listeners.functional.WorldEvent;

public class WorldListener implements Listener {

	/**
	 * Event when mob is spawning on the map.
	 * @param event
	 */
	@EventHandler
    public void onMobSpawn(EntitySpawnEvent event) {
		WorldEvent.manageMobSpawning(event);
    }

}
