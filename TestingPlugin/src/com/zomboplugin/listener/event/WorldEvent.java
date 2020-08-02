package com.zomboplugin.listener.event;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.BoundingBox;

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
	
	public static void detectHeadShot(EntityDamageByEntityEvent edbe) {
		System.out.println("Ca bolosse s�v�re ici ?");
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
				System.out.println("Arrow Bolosse s�v�rement " + bolossed.getName());
			} else {
				System.out.println("Non mais ce syst�me c'est vraiment naze");
			}
		}
	}
}
