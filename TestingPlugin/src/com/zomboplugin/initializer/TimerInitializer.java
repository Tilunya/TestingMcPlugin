package com.zomboplugin.initializer;

import org.bukkit.plugin.java.JavaPlugin;

import com.zomboplugin.config.IOFileConfig;
import com.zomboplugin.timer.DatabaseBackupTimer;
import com.zomboplugin.timer.LootboxLandingTimer;
import com.zomboplugin.timer.PlayerNaturalLossTimer;
import com.zomboplugin.timer.PlayerSleepTimer;
import com.zomboplugin.timer.StatusChangePlayerTimer;

public class TimerInitializer {
	
	private final static long minutesToTicks = 1200;
	
	private static StatusChangePlayerTimer lostStatusPlayerTimer = new StatusChangePlayerTimer();
	private static DatabaseBackupTimer databaseBackupTimer = new DatabaseBackupTimer();
	private static PlayerSleepTimer playerSleepTimer = new PlayerSleepTimer();
	private static PlayerNaturalLossTimer playerNaturalLossTimer = new PlayerNaturalLossTimer();
	private static LootboxLandingTimer lootboxLandingTimer = new LootboxLandingTimer();
	
	public static void initializeTimers(JavaPlugin plugin) {
		TimerInitializer.databaseBackupTimer.runTaskTimer(plugin, 600, 600);
		TimerInitializer.playerSleepTimer.runTaskTimer(plugin, 200, 200);
		TimerInitializer.lostStatusPlayerTimer.runTaskTimer(plugin, 400, 400);
		TimerInitializer.playerNaturalLossTimer.runTaskTimer(plugin, 600, 600);
		TimerInitializer.lootboxLandingTimer.runTaskTimer(plugin, Long.parseLong(IOFileConfig.getConfigValue("LOOTBOX_SPAWN_TIME"))*minutesToTicks, 
																Long.parseLong(IOFileConfig.getConfigValue("LOOTBOX_SPAWN_TIME"))*minutesToTicks);
	}
	
	public static void stopAllTimers(JavaPlugin plugin) {
		TimerInitializer.lostStatusPlayerTimer.cancel();
		TimerInitializer.databaseBackupTimer.cancel();
		TimerInitializer.playerSleepTimer.cancel();
		TimerInitializer.playerNaturalLossTimer.cancel();
		TimerInitializer.lootboxLandingTimer.cancel();
	}

}
