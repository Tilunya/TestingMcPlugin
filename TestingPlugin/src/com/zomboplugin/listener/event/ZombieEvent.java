package com.zomboplugin.listener.event;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.zomboplugin.config.IOFileConfig;
import com.zomboplugin.data.InfectedData;
import com.zomboplugin.data.PersistData;

public class ZombieEvent {

	public static void hitZombieEffect(EntityDamageByEntityEvent ede) {
		LivingEntity player = (LivingEntity) ede.getEntity();
		player.sendMessage("Hit by zombie !");
		if(Math.random()*100 <50) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 2));
		}

		if(Math.random()*100 <80) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 2));
		}

		if(Math.random()*100 <15) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 5));
		}

		if(player.getType().equals(EntityType.PLAYER)) {
			if(!PersistData.getPlayerData((Player) player).get_state().is_infected()) {
				PersistData.getPlayerData((Player) player).get_state().set_infected(true);
				player.sendMessage(IOFileConfig.getConfigValue("INFECTION_COUNTDOWN"));
				PersistData.getPlayerData((Player) player).get_state().set_infectionCountdown(Integer.parseInt(IOFileConfig.getConfigValue("INFECTION_COUNTDOWN")));
				InfectedData.addInfectedPlayerToList(PersistData.getPlayerData((Player) player));
			}
		}
	}
}