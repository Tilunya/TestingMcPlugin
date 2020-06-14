package com.zomboplugin.data.database;

import java.util.UUID;

import com.zomboplugin.data.PlayerData;

public class PlayerDatabase {
	
	private UUID _UUID;
	
	private String _name;
	private Double _hydration;
	private Double _exhaution;
	private boolean _infected;
	private boolean _legInjured;
	private boolean _armInjured;
	
	
	public PlayerDatabase() {
		super();
	}


	public UUID get_UUID() {
		return _UUID;
	}


	public String get_name() {
		return _name;
	}


	public Double get_hydration() {
		return _hydration;
	}


	public Double get_exhaution() {
		return _exhaution;
	}


	public boolean is_infected() {
		return _infected;
	}


	public boolean is_legInjured() {
		return _legInjured;
	}


	public boolean is_armInjured() {
		return _armInjured;
	}


	private void set_UUID(UUID _UUID) {
		this._UUID = _UUID;
	}


	public void set_name(String _Name) {
		this._name = _Name;
	}


	public void set_hydration(Double _Hydration) {
		this._hydration = _Hydration;
	}


	public void set_exhaution(Double _Exhaution) {
		this._exhaution = _Exhaution;
	}


	public void set_infected(boolean _Infected) {
		this._infected = _Infected;
	}


	public void set_legInjured(boolean _legInjured) {
		this._legInjured = _legInjured;
	}


	public void set_armInjured(boolean _armInjured) {
		this._armInjured = _armInjured;
	}
	
	public void createData(PlayerData playerData) {
		this._UUID = playerData.get_player().getUniqueId();
		this._name = playerData.get_player().getDisplayName();
		this._legInjured = false;
		this._infected = playerData.get_state().is_infected();
		this._hydration = playerData.get_state().get_hydration();
		this._exhaution = playerData.get_state().get_tiredness();
		this._armInjured = false;
	}

}
