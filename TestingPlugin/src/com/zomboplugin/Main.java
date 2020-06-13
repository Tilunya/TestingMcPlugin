package com.zomboplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.zomboplugin.commands.ApocalypseDifficulty;
import com.zomboplugin.commands.CommandsKit;
import com.zomboplugin.config.IOConfigFiles;
import com.zomboplugin.data.database.ConfigDatabase;
import com.zomboplugin.initializer.InitializerListener;
import com.zomboplugin.scoreboard.ScoreboardObjectives;
import com.zomboplugin.timer.TimerLostStatusPlayer;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {

		IOConfigFiles.manageConfigurationFiles();

		if(getCommand("kit") != null) {
			getCommand("kit").setExecutor(new CommandsKit());
		}
		else {
			System.out.println("Error on setting kitDiamond");
		}
		
		if(getCommand("apocalypse") != null) {
			getCommand("apocalypse").setExecutor(new ApocalypseDifficulty());
		}
		else {
			Bukkit.getConsoleSender().sendMessage("Error on setting apocalypse difficulty");
		}
		
		ConfigDatabase.createNewDatabase("main.db");

		InitializerListener.init(this);
		ScoreboardObjectives.initQuest();
		initTimers();
	}

	@Override
	public void onDisable() {

	}
	
	//TODO Change values to config's values
	public void initTimers() {
		TimerLostStatusPlayer lostStatusPlayerTimer = new TimerLostStatusPlayer();
		lostStatusPlayerTimer.runTaskTimer(this, 400, 400);
		
	}

}
