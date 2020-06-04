package com.tilundev.testingplugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.tilundev.testingplugin.commands.CommandsKit;
import com.tilundev.testingplugin.initializer.InitializerListener;
import com.tilundev.testingplugin.listeners.PlayerListener;
import com.tilundev.testingplugin.listeners.ZombieListener;
import com.tilundev.testingplugin.scoreboard.ScoreboardObjectives;
import com.tilundev.testingplugin.timer.TimerPlayer;

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
		TimerPlayer.setScoreBoard();
	}

	@Override
	public void onDisable() {

	}

}
