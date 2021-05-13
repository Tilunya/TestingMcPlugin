package com.zomboplugin.listener.event;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.BoundingBox;

import com.zomboplugin.timer.TimeFallTimer;
import com.zomboplugin.util.ZombieEnumUtil;

public class WorldEvent {

	/**
	 * Each time a mob want to spawn, remove it if it's not an allowed one.
	 * @param entity
	 */
	public static void manageMobSpawning(CreatureSpawnEvent entity) {
        if (!ZombieEnumUtil.isContain(entity.getEntity().getType())) {
        	entity.getEntity().remove();
        	return;
        }
        List<World> listworld = Bukkit.getServer().getWorlds();
        for (int i = 0; i < listworld.size(); i++) {
			World world = listworld.get(i);
			Long time = world.getTime();
			if((Long.compare(time, TimeFallTimer._DAY_TIME)<=0 && Long.compare(time, 0) >= 0) || (Long.compare(time, TimeFallTimer._NIGHT_TIME)>=0 && Long.compare(time, 24000) <= 0)) {
				LivingEntity zombie = entity.getEntity();
				AttributeInstance zombieSpeed = zombie.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
				AttributeInstance zombieMaxHealth = zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH);
				AttributeInstance zombieFollowRange = zombie.getAttribute(Attribute.GENERIC_FOLLOW_RANGE);
				AttributeInstance zombieKnockBackResistance = zombie.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
				if(Double.compare(zombieSpeed.getBaseValue(),zombieSpeed.getDefaultValue()) == 0 ) {
					zombieSpeed.setBaseValue(zombieSpeed.getBaseValue()*1.15);
				}
				if(Double.compare(zombieMaxHealth.getBaseValue(),zombieMaxHealth.getDefaultValue()) == 0 ) {
					zombieMaxHealth.setBaseValue(zombieMaxHealth.getBaseValue()*1.5);
				}
				if(Double.compare(zombieFollowRange.getBaseValue(),zombieFollowRange.getDefaultValue()) == 0 ) {
					zombieFollowRange.setBaseValue(zombieFollowRange.getBaseValue()*3.0);
				}
				if(Double.compare(zombieKnockBackResistance.getBaseValue(),zombieKnockBackResistance.getDefaultValue()) == 0 ) {
					zombieKnockBackResistance.setBaseValue(zombieKnockBackResistance.getBaseValue()*1.5);
				}
			}
		}
    }
	
	public static void detectHeadShot(EntityDamageByEntityEvent edbe) {
		System.out.println("Ca bolosse sévère ici ?");
		if(!edbe.getEntity().getClass().isInstance(LivingEntity.class) && edbe.getDamager().getType().equals(EntityType.ARROW)) {
			System.out.println("ah oui oui");
			LivingEntity bolossed = (LivingEntity) edbe.getEntity();
			Arrow arrowBolosseur = (Arrow) edbe.getDamager();
			BoundingBox arrowBB = arrowBolosseur.getBoundingBox();
			BoundingBox bolossedBB = bolossed.getBoundingBox();
			Double bolossedHeadMin = bolossedBB.getMaxY()- ((bolossedBB.getMaxY()-bolossedBB.getMinY())*0.12);
			Double bolossedHeadMax = bolossedBB.getMaxY();
//			bolossed.getWorld().spawnParticle(Particle.FLAME, arrowBB.getCenterX(), arrowBB.getCenterY(), arrowBB.getCenterZ(),1);
			if(Double.compare(arrowBB.getCenterY(), bolossedHeadMin) >= 0 && Double.compare(arrowBB.getMinY(), bolossedHeadMax) <= 0) {
				System.out.println("Arrow Bolosse sévèrement " + bolossed.getName());
			} else {
				System.out.println("Non mais ce système c'est vraiment naze");
			}
		}
	}
}
