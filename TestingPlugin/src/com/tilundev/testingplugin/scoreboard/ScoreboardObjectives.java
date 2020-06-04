package com.tilundev.testingplugin.scoreboard;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardObjectives {

	private Scoreboard _boardobjective;
	private List<Objective> _objectives;
	
	public ScoreboardObjectives() {
		_boardobjective = Bukkit.getScoreboardManager().getNewScoreboard();
		_objectives = new ArrayList<Objective>();
	}
	
	public void initQuest() {
		
	}

}
