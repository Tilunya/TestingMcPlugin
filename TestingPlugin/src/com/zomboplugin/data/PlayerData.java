package com.zomboplugin.data;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;

public class PlayerData {
	
	private Player _player;
	private StatePlayerData _state;
	private Objective _stateScoreboard = null;
	
	public static final float DEFAULT_WALKING_SPEED = 0.2f;
	
	// ---- GETTER ----
	public Player get_player() {
		return _player;
	}
	public StatePlayerData get_state() {
		return _state;
	}
	public Objective get_stateScoreboard() {
		return _stateScoreboard;
	}
	
	
	// ---- SETTER ----
	public void set_player(Player _player) {
		this._player = _player;
	}
	public void set_state(StatePlayerData _state) {
		this._state = _state;
	}
	public void set_stateScoreboard(Objective _stateScoreboard) {
		this._stateScoreboard = _stateScoreboard;
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
	
	public void refreshSpeed() {
		_player.setWalkSpeed(DEFAULT_WALKING_SPEED * _state.getSpeedModifier());
	}
}
