package com.zomboplugin.data;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.Inventory;

import com.zomboplugin.config.IOFileConfig;

public class LootboxData {

	private final int landingHigh = 480; //fall is 8 block/seconds, we * by 60 seconds to have time in minutes

	private String name = "LOOTBOX";
	private World world;
	private Inventory inventory;
	private Location coordinates;

	public LootboxData(World pWorld) {
		world = pWorld;
		coordinates = new Location(pWorld, Math.random()*Integer.parseInt(IOFileConfig.getConfigValue("MAP_X_LENGTH")),
											landingHigh*Integer.parseInt(IOFileConfig.getConfigValue("LOOTBOX_FALL_TIME")),
											Math.random()*Integer.parseInt(IOFileConfig.getConfigValue("MAP_Z_LENGTH")));
	}

	public void removeAllLootbox() {
		for (Entity entity : world.getEntities())
			if (EntityType.MINECART_CHEST.equals(entity.getType()) && name == entity.getName()) entity.remove();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String pName) {
		name = pName;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World pWorld) {
		world = pWorld;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory pInventory) {
		inventory = pInventory;
	}
	
	public Location getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Location pCoordinates) {
		coordinates = pCoordinates;
	}
}
