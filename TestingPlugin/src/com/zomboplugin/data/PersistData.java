package com.zomboplugin.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.entity.Player;

public class PersistData {

	private static List<PlayerData> _playerConnectedList = Collections.synchronizedList(new ArrayList<>());

	public static List<PlayerData> get_playerConnectedList() {
		return _playerConnectedList;
	}

	public static void set_playerConnectedList(List<PlayerData> _playerConnectedList) {
		PersistData._playerConnectedList = _playerConnectedList;
	}
	
	
	public static PlayerData addPersistantPlayer(Player player) {
		PlayerData playerData = new PlayerData(player, new StatePlayerData());
		_playerConnectedList.add(playerData);
		return playerData;
	}
	
	public static void removePersistantPlayer(Player player) {
		for (int i = 0; i < _playerConnectedList.size(); i++) {
			if(_playerConnectedList.get(i).get_player().equals(player)) {
				_playerConnectedList.remove(i);
				return;
			}
		}
		System.err.println("Player not found, no remove performed.");
	}
	
	public static int getNbPlayerActive() {
		return _playerConnectedList.size();
	}
	
	public static PlayerData getPlayerData(Player player) {
		for (int i = 0; i < _playerConnectedList.size(); i++) {
			PlayerData playerData = _playerConnectedList.get(i);
			if(playerData.get_player().equals(player)) {
				return playerData;
			}
		}
		System.err.println("Player not store in persistance list");
		return null;
	}
	
	public static void clearAllPlayer() {
		_playerConnectedList.clear();
	}
	
	
}
