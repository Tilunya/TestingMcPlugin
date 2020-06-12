package com.zomboplugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.zomboplugin.commands.CommandsKit;
import com.zomboplugin.initializer.InitializerListener;
import com.zomboplugin.scoreboard.ScoreboardObjectives;
import com.zomboplugin.timer.TimerLostStatusPlayer;
import com.zomboplugin.timer.TimerPlayer;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {

		if(getCommand("kit") != null) {
			getCommand("kit").setExecutor(new CommandsKit());
		}
		else {
			System.out.println("Error on setting kitDiamond");
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