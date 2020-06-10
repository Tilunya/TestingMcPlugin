package com.tilundev.testingplugin.data;

import org.bukkit.scoreboard.Score;

public class StatePlayerData {
	
	private Double _hydration;
	private Double _tiredness;
	private String _hydrationStr;
	private String _tirednessStr;
	private boolean _infected;
	
	// ---- GETTER ----
	public Double get_hydration() {
		return _hydration;
	}
	public Double get_tiredness() {
		return _tiredness;
	}
	public boolean is_infected() {
		return _infected;
	}
	public String get_hydrationStr() {
		return _hydrationStr;
	}
	public String get_tirednessStr() {
		return _tirednessStr;
	}
	
	
	
	// ---- SETTER ----
	public void set_hydration(Double _hydration) {
		this._hydration = _hydration;
	}
	public void set_tiredness(Double _tiredness) {
		this._tiredness = _tiredness;
	}
	public void set_infected(boolean _infected) {
		this._infected = _infected;
	}
	public void set_hydrationStr(String _hydrationStr) {
		this._hydrationStr = _hydrationStr;
	}
	public void set_tirednessStr(String _tirednessStr) {
		this._tirednessStr = _tirednessStr;
	}
	
	
	
	// ---- CONSTRUCTOR ----
	public StatePlayerData() {
		super();
		initStatePlayer();
	}
	
	
	// ---- FUNCTION ----
	
	public void initStatePlayer() {
		_hydration = 50.0;
		_tiredness = 50.0;
		_infected = false;
	}
	
	public void drinkClearWater() {
		_hydration += 25;
		if(_hydration.compareTo(100.0)>0) {
			_hydration = 100.0;
		}
	}

	public void drinkDirtWater() {
		_hydration += 5;
		if(_hydration.compareTo(100.0)>0) {
			_hydration = 100.0;
		}
	}
	
	public void lostHydration() {
		_hydration -= 2;
		if(_hydration.compareTo(0.0)<0) {
			_hydration = 0.0;
		}
	}
	
	public void lostNaturalHydration() {
		_hydration -= 0.5;
		if(_hydration.compareTo(0.0)<0) {
			_hydration = 0.0;
		}
	}
	
	public void lostTiredness() {
		_tiredness -= 1;
		if(_tiredness.compareTo(0.0)<0) {
			_tiredness = 0.0;
		}
	}
	
}
