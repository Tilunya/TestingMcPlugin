package com.zomboplugin.scoreboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardObjectives {

	private static Scoreboard _boardobjective = Bukkit.getScoreboardManager().getNewScoreboard();
	private static Map<Objective, List<Score>> _objectives = new HashMap<Objective, List<Score>>(); // Maybe Useless

	
	public static Scoreboard getScoreboard() {
		return _boardobjective;
	}
	

	public static void initQuest() {
		Objective obj = _boardobjective.registerNewObjective("Quest1", "dummy", "Le Reveil");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		List<Score> scoreList = new ArrayList<Score>();
		Score score = obj.getScore("Bois : ");
		score.setScore(0);
		scoreList.add(score);

		score = obj.getScore("Pierre : ");
		score.setScore(0);
		scoreList.add(score);

		score = obj.getScore("Charbon : ");
		score.setScore(0);
		scoreList.add(score);
		_objectives.put(obj, scoreList);
	}

	public static void addScore(int event) {
		Objective obj = _boardobjective.getObjective("Quest1");
		if(obj == null) {
			System.out.println("No Quest Register here");
		}
		else {
			List<Score> scoreList = _objectives.get(obj);
			for(int i=0; i < scoreList.size(); i++) {
				Score sco = scoreList.get(i);
				if(sco.getEntry().contains("Bois") && event == 0) {
					sco.setScore(sco.getScore() + 1);
				} 
				else if(sco.getEntry().contains("Pierre") && event == 1) {
					sco.setScore(sco.getScore() + 1);
				}  
				else if(sco.getEntry().contains("Charbon") && event == 2) {
					sco.setScore(sco.getScore() + 1);
				}
			}
			
		}
	}

}
