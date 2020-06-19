package com.zomboplugin.util;

import org.bukkit.entity.EntityType;

public enum ZombieEnumUtil {
	ZOMBIE(EntityType.ZOMBIE),
	HUSK(EntityType.HUSK),
	ZOMBIE_VILLAGER(EntityType.ZOMBIE_VILLAGER),
	DROMWNED(EntityType.DROWNED);
	
	private EntityType _type;
	
	private ZombieEnumUtil(EntityType type) {
		_type = type;
	}
	
	public EntityType getType() {
		return _type;
	}
	
	public static boolean isContain(EntityType type) {
		for(int i = 0; i < ZombieEnumUtil.values().length; i++) {
			if(type.equals(ZombieEnumUtil.values()[i].getType())) {
				return true;
			}
		}
		return false;
	}
}
