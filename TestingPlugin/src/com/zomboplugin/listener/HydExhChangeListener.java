package com.zomboplugin.listener;

import java.util.Iterator;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.zomboplugin.data.PlayerData;
import com.zomboplugin.data.database.manager.PlayerDatabaseManager;
import com.zomboplugin.listener.event.HydExhChangeEvent;
import com.zomboplugin.util.HydExhEnumUtil;

public class HydExhChangeListener implements Listener {

	@EventHandler
	public void onHydrationChangeEvent(HydExhChangeEvent hce) {
		PlayerData playerData = hce.get_playerData();
		double hydration = playerData.get_state().get_hydration();
		int hydrationActualLvl = playerData.get_state().get_hydrationLevel();
		int hydrationNewLvl = -1;

		double exhaution = playerData.get_state().get_tiredness();
		int exhautionActualLvl = playerData.get_state().get_tirednessLevel();
		int exhautionNewLvl = -1;
		
		
		
		if(hce.get_type().equals(HydExhEnumUtil.EXHAUTION) || hce.get_type().equals(HydExhEnumUtil.BOTH)) {
			if(hydrationActualLvl == 5 && Double.compare(hydration, 10) > 0) {
				playerData.get_player().removePotionEffect(PotionEffectType.WITHER);
			}
			
			
			if(Double.compare(hydration, 90.0)>0) { // lvl 0 
				hydrationNewLvl = 0;
				if(hydrationNewLvl != hydrationActualLvl) {
					playerData.get_state().set_bonusSpeedHydration(1.1f);
					playerData.get_player().setWalkSpeed(PlayerData.DEFAULT_WALKING_SPEED*(playerData.get_state().getSpeedModifier()));
				}
			}
			else if(Double.compare(hydration, 30.0) <= 0 && Double.compare(hydration, 15.0) > 0) { // lvl 2
				hydrationNewLvl = 2;
				playerData.get_state().set_bonusSpeedHydration(0.8f);
				changeWalkSpeed(hydrationNewLvl, playerData.get_state().get_hydrationLevel(), playerData, playerData.get_state().getSpeedModifier(), playerData.get_player().getWalkSpeed(), 50.0);
			}
			else if(Double.compare(hydration, 15.0) <= 0 && Double.compare(hydration, 5.0) > 0) { // lvl 3
				hydrationNewLvl = 3;
				playerData.get_state().set_bonusSpeedHydration(0.5f);
				changeWalkSpeed(hydrationNewLvl, playerData.get_state().get_hydrationLevel(), playerData, playerData.get_state().getSpeedModifier(), playerData.get_player().getWalkSpeed(), 75.0);
				changePotionEffect(playerData, new PotionEffect(PotionEffectType.CONFUSION, 200, 1), 50.0);
				
			}
			else if(Double.compare(hydration, 5.0) <= 0 && Double.compare(hydration, 0.0) > 0) { // lvl 4
				hydrationNewLvl = 4;
				playerData.get_state().set_bonusSpeedHydration(0.25f);
				changeWalkSpeed(hydrationNewLvl, playerData.get_state().get_hydrationLevel(), playerData, playerData.get_state().getSpeedModifier(), playerData.get_player().getWalkSpeed(), 90.0);
				changePotionEffect(playerData, new PotionEffect(PotionEffectType.CONFUSION, 400, 2), 75.0);
				changePotionEffect(playerData, new PotionEffect(PotionEffectType.WITHER, 200, 1), 50.0);
				
			}
			else if(Double.compare(hydration, 0.0) <= 0) { // lvl 5
				hydrationNewLvl = 5;
				playerData.get_state().set_bonusSpeedHydration(0.2f);
				playerData.get_player().setWalkSpeed(PlayerData.DEFAULT_WALKING_SPEED*playerData.get_state().getSpeedModifier());
				changePotionEffect(playerData, new PotionEffect(PotionEffectType.CONFUSION, 400, 2), 80.0);
				changePotionEffect(playerData, new PotionEffect(PotionEffectType.WITHER, Integer.MAX_VALUE, 1), 100.0);
				
			} else { // lvl 1
				hydrationNewLvl = 1;
				resetHydrationMalus(playerData);
			}
	
			playerData.get_state().set_hydrationLevel(hydrationNewLvl);
		}
		else if (hce.get_type().equals(HydExhEnumUtil.EXHAUTION) || hce.get_type().equals(HydExhEnumUtil.BOTH)) {
			if(Double.compare(exhaution, 90.0)>0) { // lvl 0 
				exhautionNewLvl = 0;
				if(exhautionNewLvl != exhautionActualLvl) {
					playerData.get_state().set_bonusSpeedExhaution(1.15f);
					playerData.get_player().setWalkSpeed(PlayerData.DEFAULT_WALKING_SPEED * playerData.get_state().getSpeedModifier());
				}
			}
			else if(Double.compare(exhaution, 30.0) <= 0 && Double.compare(exhaution, 10.0) > 0) { // lvl 2
				exhautionNewLvl = 2;
				playerData.get_state().set_bonusSpeedExhaution(0.95f);
				changeWalkSpeed(exhautionNewLvl, playerData.get_state().get_tirednessLevel(), playerData, playerData.get_state().getSpeedModifier(), playerData.get_player().getWalkSpeed(), 100.0);
				changePotionEffect(playerData, new PotionEffect(PotionEffectType.WEAKNESS, 400, 1), 50.0);
			}
			else if(Double.compare(exhaution, 10.0) <= 0 && Double.compare(exhaution, 5.0) > 0) { // lvl 3
				exhautionNewLvl = 3;
				playerData.get_state().set_bonusSpeedExhaution(0.7f);
				changeWalkSpeed(exhautionNewLvl, playerData.get_state().get_tirednessLevel(), playerData, playerData.get_state().getSpeedModifier(), playerData.get_player().getWalkSpeed(), 100.0);
				changePotionEffect(playerData, new PotionEffect(PotionEffectType.SLOW_DIGGING, 200, 1), 50.0);
				changePotionEffect(playerData, new PotionEffect(PotionEffectType.WEAKNESS, 800, 1), 50.0);
				
			}
			else if(Double.compare(exhaution, 5.0) <= 0 && Double.compare(exhaution, 0.0) > 0) { // lvl 4
				exhautionNewLvl = 4;
				playerData.get_state().set_bonusSpeedExhaution(0.5f);
				changeWalkSpeed(exhautionNewLvl, playerData.get_state().get_tirednessLevel(), playerData, playerData.get_state().getSpeedModifier(), playerData.get_player().getWalkSpeed(), 100.0);
				changePotionEffect(playerData, new PotionEffect(PotionEffectType.SLOW_DIGGING, 400, 2), 60.0);
				changePotionEffect(playerData, new PotionEffect(PotionEffectType.WEAKNESS, 1000, 2), 70.0);
				changePotionEffect(playerData, new PotionEffect(PotionEffectType.BLINDNESS, 200, 1), 100.0);
				
			}
			else if(Double.compare(exhaution, 0.0) <= 0) { // lvl 5
				exhautionNewLvl = 5;
				playerData.get_state().set_bonusSpeedExhaution(0.3f);
				playerData.get_player().setWalkSpeed(PlayerData.DEFAULT_WALKING_SPEED*playerData.get_state().getSpeedModifier());
				changePotionEffect(playerData, new PotionEffect(PotionEffectType.SLOW_DIGGING, 200, 3), 100.0);
				changePotionEffect(playerData, new PotionEffect(PotionEffectType.WEAKNESS, 800, 3), 100.0);
				changePotionEffect(playerData, new PotionEffect(PotionEffectType.BLINDNESS, 500, 2), 100.0);
				
			} else { // lvl 1
				exhautionNewLvl = 1;
				resetExhautionMalus(playerData);
			}
	
			playerData.get_state().set_tirednessLevel(exhautionNewLvl);
		}

		PlayerDatabaseManager pdm = new PlayerDatabaseManager();
		pdm.saveOrUpdatePlayerDatabase(playerData);
		pdm.closeSession();
	}
	
	/** PLEASE COMMENT THIS !!!!!!!!!!
	 * 
	 * 
	 * @param newLvl
	 * @param actuallvl
	 * @param playerData
	 * @param newModifier
	 * @param oldModifier
	 * @param chance
	 */
	private void changeWalkSpeed(int newLvl, int actuallvl, PlayerData playerData, float newModifier, float oldModifier, double chance) {
		if(newLvl < actuallvl && Float.compare(playerData.get_player().getWalkSpeed(),PlayerData.DEFAULT_WALKING_SPEED) < 0 ) {
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
		playerData.get_state().set_bonusSpeedHydration(1.0f);
		playerData.get_player().setWalkSpeed(PlayerData.DEFAULT_WALKING_SPEED*playerData.get_state().getSpeedModifier());
	}
	
	private void resetExhautionMalus(PlayerData playerData) {
		playerData.get_state().set_bonusSpeedExhaution(1.0f);
		playerData.get_player().setWalkSpeed(PlayerData.DEFAULT_WALKING_SPEED*playerData.get_state().getSpeedModifier());
	}
}
