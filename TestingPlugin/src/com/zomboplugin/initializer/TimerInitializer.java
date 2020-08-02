package com.zomboplugin.initializer;

import org.bukkit.plugin.java.JavaPlugin;

import com.zomboplugin.timer.DatabaseBackupTimer;
import com.zomboplugin.timer.PlayerNaturalLossTimer;
import com.zomboplugin.timer.PlayerSleepTimer;
import com.zomboplugin.timer.StatusChangePlayerTimer;

public class TimerInitializer {
	
	private static StatusChangePlayerTimer lostStatusPlayerTimer = new StatusChangePlayerTimer();
	private static DatabaseBackupTimer databaseBackupTimer = new DatabaseBackupTimer();
	private static PlayerSleepTimer playerSleepTimer = new PlayerSleepTimer();
	private static PlayerNaturalLossTimer playerNaturalLossTimer = new PlayerNaturalLossTimer();
	
	public static void initializeTimers(JavaPlugin plugin) {
		
		TimerInitializer.databaseBackupTimer.runTaskTimer(plugin, 600, 600);
		TimerInitializer.playerSleepTimer.runTaskTimer(plugin, 200, 200);
		TimerInitializer.lostStatusPlayerTimer.runTaskTimer(plugin, 400, 400);
		TimerInitializer.playerNaturalLossTimer.runTaskTimer(plugin, 600, 600);
		
	}
	
	public static void stopAllTimers(JavaPlugin plugin) {
		TimerInitializer.lostStatusPlayerTimer.cancel();
		TimerInitializer.databaseBackupTimer.cancel();
		TimerInitializer.playerSleepTimer.cancel();
		TimerInitializer.playerNaturalLossTimer.cancel();
	}

}
