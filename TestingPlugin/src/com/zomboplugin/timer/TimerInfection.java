package com.zomboplugin.timer;


import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.zomboplugin.config.IOFileConfig;
import com.zomboplugin.data.InfectionData;
import com.zomboplugin.data.PersistData;
import com.zomboplugin.data.PlayerData;

public class TimerInfection extends BukkitRunnable {

	private final int infectionCountdownLimit = 0;
	private final int infectionLevelLimit = 0;
	private final int randomDecrease = 50;
	
    @Override
    public void run() {
    	for(PlayerData p : InfectionData.infectedPlayersList){
    		p.get_state().set_infectionCountdown(p.get_state().get_infectionCountdown() - (Integer.parseInt(IOFileConfig.getConfigValue("INFECTION_SPEED"))) + ((int) Math.random()*randomDecrease));
			if(infectionLevelLimit < p.get_state().get_infectionLevel()) {
				if(infectionCountdownLimit > p.get_state().get_infectionCountdown()) {
					p.get_player().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 50, 3));
					p.get_player().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 50, 3));
					p.get_player().addPotionEffect(new PotionEffect(PotionEffectType.POISON, 5, 3));
					p.get_state().set_infectionLevel(p.get_state().get_infectionLevel() - 1);
					InfectionData.resetInfectionCountdown(p);
				}
			}else {
				Location playerCoordinates = p.get_player().getLocation();
				p.get_player().setHealth(0.0);
				p.get_player().getWorld().spawnEntity(playerCoordinates, EntityType.ZOMBIE);
				InfectionData.removeInfectedPlayerToList(p);
				PersistData.getPlayerData(p.get_player()).get_state().set_infected(false);
				InfectionData.resetInfectionCountdown(p);
				InfectionData.resetInfectionLevel(p);
			}
		}
    }

}
