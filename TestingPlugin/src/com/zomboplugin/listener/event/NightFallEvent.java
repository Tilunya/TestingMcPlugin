package com.zomboplugin.listener.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;	

public class NightFallEvent extends Event implements Cancellable {
	

	private static final HandlerList HANDLERS = new HandlerList();
	
	private double _time;
	private boolean _night;
	private boolean _canceled;

	
	public double get_time() {
		return _time;
	}

	public boolean is_night() {
		return _night;
	}
	
	public boolean is_canceled() {
		return _canceled;
	}

	public void set_canceled(boolean _canceled) {
		this._canceled = _canceled;
	}

	public NightFallEvent(double _time, boolean _night, boolean _canceled) {
		super();
		this._time = _time;
		this._night = _night;
		this._canceled = _canceled;
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}
	
	public static HandlerList getHandlerList() {
		return HANDLERS;
	}

	@Override
	public boolean isCancelled() {
		return _canceled;
	}

	@Override
	public void setCancelled(boolean arg0) {
		this._canceled = arg0;
		
	}

}
