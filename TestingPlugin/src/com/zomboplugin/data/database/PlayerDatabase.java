package com.zomboplugin.data.database;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.bukkit.Bukkit;
import org.hibernate.annotations.Type;

import com.zomboplugin.data.PlayerData;
import com.zomboplugin.listener.event.HydExhChangeEvent;
import com.zomboplugin.util.HydExhEnumUtil;


@Entity(name = "PLAYER")
@Table(name = "PLAYER")
public class PlayerDatabase {

	public static final String TABLE = "PLAYER";
	public static final String TABLE_UUID = "UUID";
	public static final String TABLE_UUID_VAR = "_UUID";
	public static final String TABLE_NAME = "NAME";
	public static final String TABLE_NAME_VAR = "_name";
	public static final String TABLE_HYDRATION = "HYDRATION";
	public static final String TABLE_HYDRATION_VAR = "_hydration";
	public static final String TABLE_EXHAUTION = "EXHAUTION";
	public static final String TABLE_EXHAUTION_VAR = "_exhaution";
	public static final String TABLE_INFECTED = "INFECTED";
	public static final String TABLE_INFECTED_VAR = "_infected";
	public static final String TABLE_ARM_INJURED = "ARM_INJURED";
	public static final String TABLE_ARM_INJURED_VAR = "__armInjured";
	public static final String TABLE_LEG_INJURED = "LEG_INJURED";
	public static final String TABLE_LEG_INJURED_VAR = "_legInjured";
	
	@Id
	@Type(type = "uuid-char")
	@Column(name = TABLE_UUID)
	private UUID _UUID;

	@Column(name = TABLE_NAME)
	private String _name;
	
	@Column(name = TABLE_HYDRATION)
	private Double _hydration;
	
	@Column(name = TABLE_EXHAUTION)
	private Double _exhaution;
	
	@Column(name = TABLE_INFECTED)
	private boolean _infected;
	
	@Column(name = TABLE_ARM_INJURED)
	private boolean _legInjured;
	
	@Column(name = TABLE_LEG_INJURED)
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
	
	public PlayerData updatePlayerData(PlayerData player) {
		player.get_state().set_hydration(this._hydration);
		player.get_state().set_tiredness(this._exhaution);
		player.get_state().set_infected(this._infected);
		HydExhChangeEvent hce = new HydExhChangeEvent(player, 0, 0, true, HydExhEnumUtil.BOTH);
		Bukkit.getPluginManager().callEvent(hce);
		
		return player;
	}

}
