package com.zomboplugin.data;

import java.util.ArrayList;

import com.zomboplugin.config.IOFileConfig;

public class InfectedData {
	
	public static boolean isPlayerCanBeHealed = false;
	public static ArrayList<PlayerData> infectedPlayersList = new ArrayList<PlayerData>();

	@SuppressWarnings("unlikely-arg-type")
	public static void addInfectedPlayerToList(PlayerData p) {
		if(p.get_state().is_infected() && (!infectedPlayersList.contains(p.get_player()))) {
			infectedPlayersList.add(p);
			p.get_state().set_infectionLevel(Integer.parseInt(IOFileConfig.getConfigValue("INFECTION_LEVEL")));
			p.get_player().sendMessage("You've got infected by a zombie");
		}
	}

	public static void removeInfectedPlayerToList(PlayerData p) {
		if(infectedPlayersList.contains(p)) {
			infectedPlayersList.remove(p);
		}
	}
	
	public static void resetInfectionCountdown(PlayerData p) {
		p.get_state().set_infectionCountdown(Integer.parseInt(IOFileConfig.getConfigValue("INFECTION_COUNTDOWN")));
	}
	
	public static void resetInfectionLevel(PlayerData p) {
		p.get_state().set_infectionCountdown(Integer.parseInt(IOFileConfig.getConfigValue("INFECTION_LEVEL")));
	}

	public static void removeInfection(PlayerData p) {
		removeInfectedPlayerToList(p);
		PersistData.getPlayerData(p.get_player()).get_state().set_infected(false);
		resetInfectionCountdown(p);
		resetInfectionLevel(p);
		isPlayerCanBeHealed = false;
		p.get_player().sendMessage("You've healed from the infection");
		System.out.println("OnItemConsume happened");
	}
}
