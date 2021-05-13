package com.zomboplugin.initializer;

import org.bukkit.plugin.java.JavaPlugin;

import com.zomboplugin.timer.DatabaseBackupTimer;
import com.zomboplugin.timer.PlayerNaturalLossTimer;
import com.zomboplugin.timer.PlayerSleepTimer;
import com.zomboplugin.timer.StatusChangePlayerTimer;
import com.zomboplugin.timer.TimeFallTimer;

public class TimerInitializer {
	
	private static StatusChangePlayerTimer lostStatusPlayerTimer = new StatusChangePlayerTimer();
	private static DatabaseBackupTimer databaseBackupTimer = new DatabaseBackupTimer();
	private static PlayerSleepTimer playerSleepTimer = new PlayerSleepTimer();
	private static PlayerNaturalLossTimer playerNaturalLossTimer = new PlayerNaturalLossTimer();
	private static TimeFallTimer timeFallTimer = null;
	
	public static void initializeTimers(JavaPlugin plugin) {
		
		TimerInitializer.databaseBackupTimer.runTaskTimer(plugin, 600, 600);
		TimerInitializer.playerSleepTimer.runTaskTimer(plugin, 200, 200);
		TimerInitializer.lostStatusPlayerTimer.runTaskTimer(plugin, 400, 400);
		TimerInitializer.playerNaturalLossTimer.runTaskTimer(plugin, 600, 600);
		TimerInitializer.timeFallTimer = new TimeFallTimer(plugin);
		TimerInitializer.timeFallTimer.runTaskLater(plugin, TimerInitializer.timeFallTimer.getTimeBeforeNextNight());
		
	}
	
	public static void stopAllTimers(JavaPlugin plugin) {
		TimerInitializer.lostStatusPlayerTimer.cancel();
		TimerInitializer.databaseBackupTimer.cancel();
		TimerInitializer.playerSleepTimer.cancel();
		TimerInitializer.playerNaturalLossTimer.cancel();
		if(TimerInitializer.timeFallTimer != null) {
			TimerInitializer.timeFallTimer.cancel();
		}
	}
	
	public static void relaunchTimeFallTimer(JavaPlugin plugin) {
		if(TimerInitializer.timeFallTimer != null) {
			TimerInitializer.timeFallTimer = new TimeFallTimer(plugin);
			System.out.println("run after " +  TimerInitializer.timeFallTimer.getTimeBeforeNextNight()+ " tick");
			TimerInitializer.timeFallTimer.runTaskLater(plugin, TimerInitializer.timeFallTimer.getTimeBeforeNextNight());
		}
	}
	

}
