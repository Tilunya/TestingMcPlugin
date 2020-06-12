package com.tilundev.testingplugin.data;

import org.bukkit.Bukkit;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.tilundev.testingplugin.listeners.event.HydrationChangeEvent;

public class StatePlayerData {
	
	private Double _hydration;
	private Double _tiredness;
	private String _hydrationStr;
	private String _tirednessStr;
	private boolean _infected;
	private int _hydrationLevel;
	private int _tirednessLevel;
	
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
	public int get_hydrationLevel() {
		return _hydrationLevel;
	}
	public int get_tirednessLevel() {
		return _tirednessLevel;
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
	public void set_hydrationLevel(int _hydrationLevel) {
		this._hydrationLevel = _hydrationLevel;
	}
	public void set_tirednessLevel(int _tirednessLevel) {
		this._tirednessLevel = _tirednessLevel;
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
		_hydrationLevel = 1;
		_infected = false;
	}
	
	public void drinkClearWater(PlayerData playerData) {
		double valAdd = 35.0;
		_hydration += valAdd;
		if(_hydration.compareTo(100.0)>0) {
			_hydration = 100.0;
		}
		HydrationChangeEvent hce = new HydrationChangeEvent(playerData, valAdd, false);
		Bukkit.getPluginManager().callEvent(hce);
	}

	public void drinkDirtWater(PlayerData playerData) {
		double valAdd = 5.2;
		_hydration += valAdd;
		if(_hydration.compareTo(100.0)>0) {
			_hydration = 100.0;
		}
		if(Double.compare((Math.random()*100.0),75.0) <= 0){
			playerData.get_player().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 300, 1));
		}
		if(Double.compare((Math.random()*100.0),40.0) <= 0){
			playerData.get_player().addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 1));
		}
		HydrationChangeEvent hce = new HydrationChangeEvent(playerData, valAdd, false);
		Bukkit.getPluginManager().callEvent(hce);
	}
	
	public void lostHydration(PlayerData playerData) {
		double valAdd = 1.2;
		_hydration -= valAdd;
		if(_hydration.compareTo(0.0)<0) {
			_hydration = 0.0;
		}
		HydrationChangeEvent hce = new HydrationChangeEvent(playerData, 2.0, true);
		Bukkit.getPluginManager().callEvent(hce);
	}
	
	public void lostNaturalHydration(PlayerData playerData) {
		double valAdd = 0.5;
		_hydration -= valAdd;
		if(_hydration.compareTo(0.0)<0) {
			_hydration = 0.0;
		}
		HydrationChangeEvent hce = new HydrationChangeEvent(playerData, valAdd, true);
		Bukkit.getPluginManager().callEvent(hce);
	}
	
	public void lostTiredness() {
		_tiredness -= 1;
		if(_tiredness.compareTo(0.0)<0) {
			_tiredness = 0.0;
		}
	}
	
	
}
