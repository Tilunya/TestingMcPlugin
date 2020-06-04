package com.tilundev.testingplugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.tilundev.testingplugin.commands.CommandsKit;
import com.tilundev.testingplugin.initializer.InitializerListener;
import com.tilundev.testingplugin.scoreboard.ScoreboardObjectives;

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
	}

	@Override
	public void onDisable() {

	}

}
