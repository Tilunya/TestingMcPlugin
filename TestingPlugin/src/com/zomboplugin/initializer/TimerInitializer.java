package com.zomboplugin.initializer;

import org.bukkit.plugin.java.JavaPlugin;

import com.zomboplugin.timer.DatabaseBackupTimer;
import com.zomboplugin.timer.StatusChangePlayerTimer;

public class TimerInitializer {
	
	private static StatusChangePlayerTimer lostStatusPlayerTimer = new StatusChangePlayerTimer();
	private static DatabaseBackupTimer databaseBackupTimer = new DatabaseBackupTimer();
	
	public static void initializeTimers(JavaPlugin plugin) {
		
		TimerInitializer.databaseBackupTimer.runTaskTimer(plugin, 600, 600);
		TimerInitializer.lostStatusPlayerTimer.runTaskTimer(plugin, 400, 400);
		
	}
	
	public static void stopAllTimers(JavaPlugin plugin) {
		TimerInitializer.lostStatusPlayerTimer.cancel();
		TimerInitializer.databaseBackupTimer.cancel();
	}

}