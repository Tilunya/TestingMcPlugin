package com.tilundev.testingplugin.data;

import org.bukkit.entity.Player;

public class PlayerData {
	
	private Player _player;
	private StatePlayerData _state;
	
	// ---- GETTER ----
	public Player get_player() {
		return _player;
	}
	public StatePlayerData get_state() {
		return _state;
	}
	
	
	// ---- SETTER ----
	public void set_player(Player _player) {
		this._player = _player;
	}
	public void set_state(StatePlayerData _state) {
		this._state = _state;
	}
	
	
	// ---- CONSTRUCTOR ----
	public PlayerData(Player _player, StatePlayerData _state) {
		super();
		this._player = _player;
		this._state = _state;
		initPlayerData();
	}
	
	
	// ---- FUNCTIONS ----

	public void initPlayerData() {
		_state = new StatePlayerData();
		_state.initStatePlayer();
	}
	
	public void naturaExhaustion() {
		_player.setExhaustion(_player.getExhaustion()+0.5f);
	}
}
