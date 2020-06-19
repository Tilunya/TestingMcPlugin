package com.zomboplugin.listeners;

import java.util.Iterator;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.zomboplugin.data.PlayerData;
import com.zomboplugin.data.database.PlayerDatabaseManager;
import com.zomboplugin.listeners.event.HydrationChangeEvent;

public class HydrationChangeListener implements Listener {

	@EventHandler
	public void onHydrationChangeEvent(HydrationChangeEvent hce) {
		PlayerData playerData = hce.get_playerData();
		double hydration = playerData.get_state().get_hydration();
		int hydrationActuelLvl = playerData.get_state().get_hydrationLevel();
		int hydrationNewLvl = -1;
		
		if(hydrationActuelLvl == 5 && Double.compare(hydration, 10) > 0) {
			playerData.get_player().removePotionEffect(PotionEffectType.WITHER);
		}
		
		
		if(Double.compare(hydration, 90.0)>0) { // lvl 0 
			hydrationNewLvl = 0;
			if(hydrationNewLvl != hydrationActuelLvl) {
				playerData.get_player().setWalkSpeed(PlayerData.DEFAULT_WALKING_SPEED*1.1f);
			}
		}
		// TODO Handle Loss of Exhaustion for all if
		else if(Double.compare(hydration, 30.0) <= 0 && Double.compare(hydration, 15.0) > 0) { // lvl 2
			hydrationNewLvl = 2;
			changeWalkSpeed(hydrationNewLvl, playerData.get_state().get_hydrationLevel(), playerData, 0.8f, 1.0f, 50.0);
		}
		else if(Double.compare(hydration, 15.0) <= 0 && Double.compare(hydration, 5.0) > 0) { // lvl 3
			hydrationNewLvl = 3;
			changeWalkSpeed(hydrationNewLvl, playerData.get_state().get_hydrationLevel(), playerData, 0.5f, 0.8f, 75.0);
			changePotionEffect(playerData, new PotionEffect(PotionEffectType.CONFUSION, 200, 1), 50.0);
			
		}
		else if(Double.compare(hydration, 5.0) <= 0 && Double.compare(hydration, 0.0) > 0) { // lvl 4
			hydrationNewLvl = 4;
			changeWalkSpeed(hydrationNewLvl, playerData.get_state().get_hydrationLevel(), playerData, 0.25f, 0.5f, 90.0);
			changePotionEffect(playerData, new PotionEffect(PotionEffectType.CONFUSION, 400, 2), 75.0);
			changePotionEffect(playerData, new PotionEffect(PotionEffectType.WITHER, 200, 1), 50.0);
			
		}
		else if(Double.compare(hydration, 0.0) <= 0) { // lvl 5
			hydrationNewLvl = 5;
			playerData.get_player().setWalkSpeed(PlayerData.DEFAULT_WALKING_SPEED*0.2f);
			changePotionEffect(playerData, new PotionEffect(PotionEffectType.CONFUSION, 400, 2), 80.0);
			changePotionEffect(playerData, new PotionEffect(PotionEffectType.WITHER, Integer.MAX_VALUE, 1), 100.0);
			
		} else { // lvl 1
			hydrationNewLvl = 1;
			resetHydrationMalus(playerData);
		}

		playerData.get_state().set_hydrationLevel(hydrationNewLvl);

		PlayerDatabaseManager pdm = new PlayerDatabaseManager();
		pdm.saveOrUpdatePlayerDatabase(playerData);
		pdm.closeSession();
	}
	
	
	private void changeWalkSpeed(int hydrationNewLvl, int hydrationActuallvl, PlayerData playerData, float newModifier, float oldModifier, double chance) {
		if(hydrationNewLvl < hydrationActuallvl && Float.compare(playerData.get_player().getWalkSpeed(),PlayerData.DEFAULT_WALKING_SPEED) < 0 ) {
			playerData.get_player().setWalkSpeed(PlayerData.DEFAULT_WALKING_SPEED*newModifier);
		} else if (Float.compare(playerData.get_player().getWalkSpeed(),PlayerData.DEFAULT_WALKING_SPEED*oldModifier) >= 0){
			if(Double.compare(Math.random()*100.0,chance) <= 0 ) {
				playerData.get_player().setWalkSpeed(PlayerData.DEFAULT_WALKING_SPEED*newModifier);
			}
		}
	}
	
	private void changePotionEffect(PlayerData playerData, PotionEffect newPotionEffect, double chance) {
		boolean isAffected = false;
		boolean stop = false;
		Iterator<PotionEffect> it = playerData.get_player().getActivePotionEffects().iterator();
		while (it.hasNext() && !stop) {
			PotionEffect potionEffect = it.next();
			if(potionEffect.getType().equals(newPotionEffect.getType()) && potionEffect.getAmplifier() <= newPotionEffect.getAmplifier()) {
				isAffected = true;
				stop = true;
				playerData.get_player().addPotionEffect(new PotionEffect(potionEffect.getType(), potionEffect.getDuration()+newPotionEffect.getDuration(), newPotionEffect.getAmplifier()));
			}
		}
		if(!isAffected) {
			if(Double.compare(Math.random()*100.0,chance) <= 0 ) {
				System.out.println("Success");
				playerData.get_player().addPotionEffect(newPotionEffect);
			}
		}
	}
	
	private void resetHydrationMalus(PlayerData playerData) {
		playerData.get_player().setWalkSpeed(PlayerData.DEFAULT_WALKING_SPEED);
	}
}
