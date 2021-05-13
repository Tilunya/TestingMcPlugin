package com.zomboplugin.timer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.zomboplugin.initializer.TimerInitializer;
import com.zomboplugin.listener.event.NightFallEvent;

public class TimeFallTimer extends BukkitRunnable {
	
	private static JavaPlugin _plugin;
	private static List<World> _activeWorlds = new ArrayList<World>();
	private static Long _timeBeforeNextEvent = Long.MAX_VALUE;
	private static World _worldNextEvent = null;
	private static boolean _isNextNightEvent = false;
	private static boolean _eventThrow = false;

	public final static long _NIGHT_TIME = 13050l;
	public final static long _DAY_TIME = 1050l;

	
	public long getTimeBeforeNextNight() {
		return _timeBeforeNextEvent;
	}
	
	public TimeFallTimer(JavaPlugin plugin) {
		super();
		_plugin = plugin;
		initialize();
		calcNextEvent();
	}

	@Override
	public void run() {
		// Rework this part for Handle late event or Skip night time Or cmd Time Set
		if(_worldNextEvent != null ) {
			if (_isNextNightEvent && (Long.compare(_worldNextEvent.getTime(),_NIGHT_TIME - 75l) > 0 && Long.compare(_worldNextEvent.getTime(),_NIGHT_TIME + 75l) < 0 )) {
				NightFallEvent nfe = new NightFallEvent(_worldNextEvent.getTime(), true, false);
				Bukkit.getPluginManager().callEvent(nfe);
				_eventThrow = true;
			} else if (!_isNextNightEvent && (Long.compare(_worldNextEvent.getTime(),_DAY_TIME - 75l) > 0 && Long.compare(_worldNextEvent.getTime(),_DAY_TIME + 75l) < 0 )) {
				NightFallEvent nfe = new NightFallEvent(_worldNextEvent.getTime(), false, false);
				Bukkit.getPluginManager().callEvent(nfe);
				_eventThrow = true;
			}
		}
		RelaunchTimer relaunch = new RelaunchTimer();
		relaunch.runTaskLater(_plugin, 20); // See if there are a better way to relaunch Timer with custom time
	}
	
	private void initialize() {
		_timeBeforeNextEvent = Long.MAX_VALUE;
		List<World> worldList = _plugin.getServer().getWorlds();
		for (int i = 0; i < worldList.size(); i++) {
			World world = worldList.get(i);
			if(world.getEnvironment().equals(Environment.NORMAL)) {
				if(!_activeWorlds.contains(world)) {
					_activeWorlds.add(world);
				}
			}
		}
	}
	
	private void calcNextEvent() {
		for(int i=0; i< _activeWorlds.size();i++) {
			World world = _activeWorlds.get(i);
			Long fyfrel = world.getTime();
			Long nextNight = 0l;
			if(Long.compare(fyfrel, _NIGHT_TIME) > 0) {
				nextNight = (24000l - fyfrel) + _NIGHT_TIME;
			} else {
				nextNight = _NIGHT_TIME - fyfrel;
			}
			System.out.println("Next night : " +  nextNight);
			if(Long.compare(nextNight, _timeBeforeNextEvent) < 0 ) {
				_timeBeforeNextEvent = nextNight;
				_worldNextEvent = world;
				_isNextNightEvent = true;
			}
			

			Long tenji = world.getTime();
			Long nextDay = 0l;
			if(Long.compare(tenji, _DAY_TIME) > 0) {
				nextDay = (24000l - tenji) + _DAY_TIME;
			} else {
				nextDay = _DAY_TIME - tenji;
			}

			System.out.println("Next Day : " +  nextDay);
			if(Long.compare(nextDay, _timeBeforeNextEvent) < 0 ) {
				_timeBeforeNextEvent = nextDay;
				_worldNextEvent = world;
				_isNextNightEvent = false;
			}
		}
		if(_eventThrow) {
			_timeBeforeNextEvent = 300l;
			_eventThrow = false;
		}else if(_timeBeforeNextEvent > 50l) {
			_timeBeforeNextEvent = 50l;
		}
		
	}
	
	private class RelaunchTimer extends BukkitRunnable {

		@Override
		public void run() {
			TimerInitializer.relaunchTimeFallTimer(_plugin);
		}
		
	}
	

}
