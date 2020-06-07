package com.tilundev.testingplugin.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class EtatPlayer {

	private Scoreboard _scoreboardPlayer = null;
	private Player _player = null;
	private Objective _state = null;
	private Double _txTiredness;
	private Double _txHydration;
	
	
	
	public double get_txTiredness() {
		return _txTiredness;
	}

	public double get_txHydration() {
		return _txHydration;
	}

	public Scoreboard get_scoreboardPlayer() {
		return _scoreboardPlayer;
	}

	public Player get_player() {
		return _player;
	}

	public Objective get_state() {
		return _state;
	}


	
	
	public void set_scoreboardPlayer(Scoreboard _scoreboardPlayer) {
		this._scoreboardPlayer = _scoreboardPlayer;
	}

	public void set_player(Player _player) {
		this._player = _player;
	}

	public void set_state(Objective _state) {
		this._state = _state;
	}
	
	public void set_txTiredness(double _txTiredness) {
		this._txTiredness = _txTiredness;
	}

	public void set_txHydration(double _txHydration) {
		this._txHydration = _txHydration;
	}
	

	
	public EtatPlayer() {
		
	}
	
	public void initState() {
		if(_scoreboardPlayer == null) {
			_scoreboardPlayer = Bukkit.getScoreboardManager().getNewScoreboard();
		}
		_state = _scoreboardPlayer.registerNewObjective("playerstate_"+_player.getUniqueId().toString(), "dummy", "State");
		_state.getScore("Tiredness").setScore(1);
		_state.getScore(Math.floor(_txTiredness) + " %").setScore(0);
		_state.getScore("Hydration").setScore(4);
		_state.getScore(Math.floor(_txHydration) + " %").setScore(3);
		
	}
}
