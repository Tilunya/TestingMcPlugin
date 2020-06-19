package com.zomboplugin.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import com.zomboplugin.listener.event.WorldEvent;

public class WorldListener implements Listener {

	/**
	 * Event when mob is spawning on the map.
	 * @param event
	 */
	@EventHandler
    public void onMobSpawn(CreatureSpawnEvent event) {
		WorldEvent.manageMobSpawning(event);
    }

}
