package com.zomboplugin.listener.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.zomboplugin.data.PlayerData;
import com.zomboplugin.util.HydExhEnumUtil;

public class HydExhChangeEvent extends Event implements Cancellable {
	
	private static final HandlerList HANDLERS = new HandlerList();
	private boolean _canceled;
	
	private PlayerData _playerData;
	private double _changeHydrationValue;
	private double _changeExhautionValue;
	private boolean _loss;
	private HydExhEnumUtil _type;

	
	@Override
	public boolean isCancelled() {
		return _canceled;
	}

	@Override
	public void setCancelled(boolean arg0) {
		this._canceled = arg0;

	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}
	
	
	public static HandlerList getHandlerList() {
		return HANDLERS;
	}

	public PlayerData get_playerData() {
		return _playerData;
	}

	public HydExhEnumUtil get_type() {
		return _type;
	}
	
	public double get_changeHydrationValue() {
		return _changeHydrationValue;
	}
	
	public double get_changeExhautionValue() {
		return _changeExhautionValue;
	}

	public boolean is_loss() {
		return _loss;
	}

	public HydExhChangeEvent(PlayerData _playerData, double _changeHydrationValue, double _changeExhautionValue, boolean _loss, HydExhEnumUtil type) {
		super();
		this._playerData = _playerData;
		this._changeHydrationValue = _changeHydrationValue;
		this._changeExhautionValue = _changeExhautionValue;
		this._loss = _loss;
		this._canceled = false;
		this._type = type;
	}
	
	
	
	

}
