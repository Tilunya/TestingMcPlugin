package com.tilundev.testingplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.tilundev.testingplugin.commands.ApocalypseDifficulty;
import com.tilundev.testingplugin.commands.CommandsKit;
import com.tilundev.testingplugin.initializer.InitializerListener;
import com.tilundev.testingplugin.scoreboard.ScoreboardObjectives;
import com.tilundev.testingplugin.timer.TimerLostStatusPlayer;

import configurationFile.util.IOConfigFiles;

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
