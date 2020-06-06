package com.tilundev.testingplugin.util;

import org.bukkit.entity.EntityType;

public enum ZombieEnum {
	ZOMBIE(EntityType.ZOMBIE),
	HUSK(EntityType.HUSK),
	ZOMBIE_VILLAGER(EntityType.ZOMBIE_VILLAGER),
	DROMWNED(EntityType.DROWNED);
	
	private EntityType _type;
	
	private ZombieEnum(EntityType type) {
		_type = type;
	}
	
	public EntityType getType() {
		return _type;
	}
	
	public static boolean isContain(EntityType type) {
		for(int i = 0; i < ZombieEnum.values().length; i++) {
			if(type.equals(ZombieEnum.values()[i].getType())) {
				return true;
			}
		}
		return false;
	}
}
