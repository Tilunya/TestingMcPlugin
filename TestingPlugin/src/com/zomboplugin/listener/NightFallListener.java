package com.zomboplugin.listener;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.zomboplugin.data.PersistData;
import com.zomboplugin.data.PlayerData;
import com.zomboplugin.listener.event.NightFallEvent;
import com.zomboplugin.util.exceptions.NoPlayerException;

public class NightFallListener implements Listener {
	
	private int _numberClosePlayer = 0;

	@EventHandler
	public void onNightFallEvent(NightFallEvent nfe) {
		if(nfe.is_night()) {
			System.out.println("NIGHT IS FALL");
			generateHordes();
			
		}else {
			System.out.println("DAY IS ON RTX");
		}
	}
	
	private void generateHordes() {
		if(PersistData.getNbPlayerActive() == 0 ) {
			return;
		}
		double random = Math.abs(Math.random()*99) + 1.0;
		if(Double.compare(random, 15) <= 0) {
			//TODO Hordes Personel sur 30% des joueurs de la Map avec au minimum 1 joueur touché
		}
		random = Math.abs(Math.random()*99) + 1.0;

		if(Double.compare(random, 5) <= 0) {
			try {
				PlayerData playerCenter = getHighDensityPlayer();
				generateBigHorde(playerCenter);
			}
			catch (NoPlayerException e) {
				System.err.println(e.getMessage());
			}
		}
		
		random = Math.abs(Math.random()*99) + 1.0;

		if(Double.compare(random, 10) <= 0) {
			//TODO Vague successive sur 20 % des joueurs de la Map avec au minimum 1 joueur touché
		}
	}
	
	private PlayerData getHighDensityPlayer() {
		List<PlayerData> playerDataList = PersistData.get_playerConnectedList();
		int maxPlayerAround = -1;
		PlayerData playerCenter = null;
		for (int i = 0; i < playerDataList.size(); i++) {
			PlayerData playerTested = playerDataList.get(i);
			int playerAround = 0;
			for (int j = 0; j < playerDataList.size(); j++) {
				PlayerData otherPlayer = playerDataList.get(i);
				Location locationPlayer = playerTested.get_player().getLocation();
				Location locationOtherPlayer = otherPlayer.get_player().getLocation();
				try {
					if (Double.compare(locationPlayer.distanceSquared(locationOtherPlayer),50.0) < 0) {
						playerAround++;
					}
				} catch (IllegalArgumentException e) {
					System.err.println(e.getMessage());
				}
			}
			if(maxPlayerAround < playerAround) {
				playerCenter = playerTested;
			}
		}
		_numberClosePlayer = maxPlayerAround;
		return playerCenter;
	}
	
	private void generateBigHorde(PlayerData player) throws NoPlayerException {
		// TODO Change the way to set the amount of zombie
		if(player == null) {
			NoPlayerException npe = new NoPlayerException("No player has been found for generate the hordes");
			throw npe;
		}
		int numZombieToSpawn = 0;
		if(_numberClosePlayer < 2) {
			numZombieToSpawn = 50;
		} else if(_numberClosePlayer < 6) {
			numZombieToSpawn = 120;
		} else {
			numZombieToSpawn = 200;
		}

		World world = player.get_player().getWorld();
		for (int i = 0; i < numZombieToSpawn; i++) {
			Location locationBase = player.get_player().getLocation();
			
		}
	}
	
}
