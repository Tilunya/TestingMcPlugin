package com.zomboplugin.listener.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.zomboplugin.data.PlayerData;

public class HydrationChangeEvent extends Event implements Cancellable {
	
	private static final HandlerList HANDLERS = new HandlerList();
	private boolean _canceled;
	
	private PlayerData _playerData;
	private double _changeHydrationValue;
	private boolean _loss;

	
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

	public double get_changeHydrationValue() {
		return _changeHydrationValue;
	}

	public boolean is_loss() {
		return _loss;
	}

	public HydrationChangeEvent(PlayerData _playerData, double _changeHydrationValue, boolean _loss) {
		super();
		this._playerData = _playerData;
		this._changeHydrationValue = _changeHydrationValue;
		this._loss = _loss;
		this._canceled = false;
	}
	
	
	
	

}
