package com.zomboplugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.zomboplugin.config.IOFileConfig;
import com.zomboplugin.config.LootboxConfig;
import com.zomboplugin.data.PersistData;
import com.zomboplugin.data.database.ConfigDatabase;
import com.zomboplugin.initializer.CommandsInitializer;
import com.zomboplugin.initializer.ListenersInitializer;
import com.zomboplugin.initializer.TimerInitializer;
import com.zomboplugin.timer.LootboxLandingTimer;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {

		IOFileConfig.manageConfigurationFiles();
		LootboxConfig.manageLootFiles();

		CommandsInitializer.InitializeCommands(this);
		
		ConfigDatabase.createNewDatabase("main.db");

		ListenersInitializer.init(this);
		TimerInitializer.initializeTimers(this);
		
		LootboxLandingTimer.setActiveWorld(this.getServer());
	}

	@Override
	public void onDisable() {
		TimerInitializer.stopAllTimers(this);
		PersistData.clearAllPlayer();
	}

}