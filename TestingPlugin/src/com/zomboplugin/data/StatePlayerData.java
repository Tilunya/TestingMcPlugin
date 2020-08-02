package com.zomboplugin.data;

import org.bukkit.Bukkit;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.zomboplugin.listener.event.HydExhChangeEvent;
import com.zomboplugin.util.HydExhEnumUtil;

public class StatePlayerData {
	
	private Double _hydration;
	private Double _tiredness;
	private String _hydrationStr;
	private String _tirednessStr;
	private boolean _infected;
	private boolean _sleep;
	private int _hydrationLevel;
	private int _tirednessLevel;
	
	private float _bonusSpeedExhaution = 1.0f;
	private float _bonusSpeedHydration = 1.0f;
	
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
	public boolean is_sleep() {
		return _sleep;
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
	public float get_bonusSpeedExhaution() {
		return _bonusSpeedExhaution;
	}
	public float get_bonusSpeedHydration() {
		return _bonusSpeedHydration;
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
	public void set_sleep(boolean _sleep) {
		this._sleep = _sleep;
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
	public void set_bonusSpeedExhaution(float _bonusSpeedExhaution) {
		this._bonusSpeedExhaution = _bonusSpeedExhaution;
	}
	public void set_bonusSpeedHydration(float _bonusSpeedHydration) {
		this._bonusSpeedHydration = _bonusSpeedHydration;
	}
	
	
	
	
	// ---- CONSTRUCTOR ----
	public StatePlayerData() {
		super();
		initStatePlayer();
	}
	
	
	// ---- FUNCTION ----
	
	public void initStatePlayer() {
		_hydration = 50.0;
		_tiredness = 75.0;
		_hydrationLevel = 1;
		_tirednessLevel = 1;
		_bonusSpeedExhaution = 1.0f;
		_bonusSpeedHydration = 1.0f;
		_infected = false;
		_sleep = false;
	}
	
	public void drinkClearWater(PlayerData playerData) {
		double valAdd = 35.0;
		_hydration += valAdd;
		if(_hydration.compareTo(100.0)>0) {
			_hydration = 100.0;
		}
		HydExhChangeEvent hce = new HydExhChangeEvent(playerData, valAdd, 0.0, false, HydExhEnumUtil.HYDRATION);
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
		HydExhChangeEvent hce = new HydExhChangeEvent(playerData, valAdd, 0.0, false, HydExhEnumUtil.HYDRATION);
		Bukkit.getPluginManager().callEvent(hce);
	}
	
	public void lostHydration(PlayerData playerData) {
		double valAdd = 1.2;
		_hydration -= valAdd;
		if(_hydration.compareTo(0.0)<0) {
			_hydration = 0.0;
		}
		HydExhChangeEvent hce = new HydExhChangeEvent(playerData, 2.0, 0.0, true, HydExhEnumUtil.HYDRATION);
		Bukkit.getPluginManager().callEvent(hce);
	}
	
	public void lostNaturalHydration(PlayerData playerData) {
		double valAdd = 0.5;
		_hydration -= valAdd;
		if(_hydration.compareTo(0.0)<0) {
			_hydration = 0.0;
		}
		HydExhChangeEvent hce = new HydExhChangeEvent(playerData, valAdd, 0.0, true, HydExhEnumUtil.HYDRATION);
		Bukkit.getPluginManager().callEvent(hce);
	}
	
	public void lostTiredness(PlayerData playerData) {
		double val = 0.9;
		_tiredness -= val;
		if(_tiredness.compareTo(0.0) < 0) {
			_tiredness = 0.0;
		}
		HydExhChangeEvent hce = new HydExhChangeEvent(playerData, 0.0, val, true, HydExhEnumUtil.EXHAUTION);
		Bukkit.getPluginManager().callEvent(hce);
	}
	
	public void lostNaturalExhaution(PlayerData playerData) {
		double val = 0.4;
		_hydration -= val;
		if(_hydration.compareTo(0.0)<0) {
			_hydration = 0.0;
		}
		HydExhChangeEvent hce = new HydExhChangeEvent(playerData, 0.0, val, true, HydExhEnumUtil.EXHAUTION);
		Bukkit.getPluginManager().callEvent(hce);
	}
	
	public void sleep(PlayerData playerData) {
		double val = 10.0;
		_tiredness += val;
		if(_tiredness.compareTo(100.0) > 0) {
			_tiredness = 100.0;
		}
		HydExhChangeEvent hce = new HydExhChangeEvent(playerData, 0.0, val, false, HydExhEnumUtil.EXHAUTION);
		Bukkit.getPluginManager().callEvent(hce);
	}
	
	
	public int getLevelByHydration(double hydration) {
		int newLvl = -1;
		if(Double.compare(hydration,90) > 0) {
			newLvl = 0;
		} 
		else if (Double.compare(hydration, 90) <= 0 && Double.compare(hydration, 30) > 0) {
			newLvl = 1;
		}
		else if (Double.compare(hydration, 30) <= 0 && Double.compare(hydration, 15) > 0) {
			newLvl = 2;
		}
		else if (Double.compare(hydration, 15) <= 0 && Double.compare(hydration, 5) > 0) {
			newLvl = 3;
		}
		else if (Double.compare(hydration, 5) <= 0 && Double.compare(hydration, 0) > 0) {
			newLvl = 4;
		}
		else if (Double.compare(hydration, 0) <= 0 ) {
			newLvl = 5;
		}
		return newLvl;
	}
	
	public void setLevelByHydration() {
		if(Double.compare(this._hydration,90) > 0) {
			this._hydrationLevel = 0;
		} 
		else if (Double.compare(this._hydration, 90) <= 0 && Double.compare(this._hydration, 30) > 0) {
			this._hydrationLevel = 1;
		}
		else if (Double.compare(this._hydration, 30) <= 0 && Double.compare(this._hydration, 15) > 0) {
			this._hydrationLevel = 2;
		}
		else if (Double.compare(this._hydration, 15) <= 0 && Double.compare(this._hydration, 5) > 0) {
			this._hydrationLevel = 3;
		}
		else if (Double.compare(this._hydration, 5) <= 0 && Double.compare(this._hydration, 0) > 0) {
			this._hydrationLevel = 4;
		}
		else if (Double.compare(this._hydration, 0) <= 0 ) {
			this._hydrationLevel = 5;
		}
	}
	
	public float getSpeedModifier() {
		return _bonusSpeedExhaution * _bonusSpeedHydration * (_sleep ? 0.0f : 1.0f);
	}
	
}
