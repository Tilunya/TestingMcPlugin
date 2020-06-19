package com.zomboplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.zomboplugin.commands.ApocalypseDifficulty;
import com.zomboplugin.commands.CommandsKit;
import com.zomboplugin.config.IOConfigFiles;
import com.zomboplugin.data.PersistData;
import com.zomboplugin.data.database.ConfigDatabase;
import com.zomboplugin.initializer.CommandsInitializer;
import com.zomboplugin.initializer.InitializerListener;
import com.zomboplugin.initializer.TimerInitializer;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {

		IOConfigFiles.manageConfigurationFiles();

		CommandsInitializer.InitializeCommands(this);
		
		ConfigDatabase.createNewDatabase("main.db");

		InitializerListener.init(this);
		TimerInitializer.initializeTimers(this);
	}

	@Override
	public void onDisable() {
		TimerInitializer.stopAllTimers(this);
		PersistData.clearAllPlayer();
	}

}
