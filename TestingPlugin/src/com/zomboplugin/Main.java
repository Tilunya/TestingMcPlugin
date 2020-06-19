package com.zomboplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.zomboplugin.command.ApocalypseDifficultyCommand;
import com.zomboplugin.command.KitCommand;
import com.zomboplugin.config.IOFileConfig;
import com.zomboplugin.data.PersistData;
import com.zomboplugin.data.database.ConfigDatabase;
import com.zomboplugin.initializer.CommandsInitializer;
import com.zomboplugin.initializer.ListenersInitializer;
import com.zomboplugin.initializer.TimerInitializer;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {

		IOFileConfig.manageConfigurationFiles();

		CommandsInitializer.InitializeCommands(this);
		
		ConfigDatabase.createNewDatabase("main.db");

		ListenersInitializer.init(this);
		TimerInitializer.initializeTimers(this);
	}

	@Override
	public void onDisable() {
		TimerInitializer.stopAllTimers(this);
		PersistData.clearAllPlayer();
	}

}
