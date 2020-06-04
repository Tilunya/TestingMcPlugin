package com.tilundev.testingplugin.timer;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class TimerPlayer {
	
	private static int _nbConnect = 0;
	private static Scoreboard _board;
	private static Score _score;
	
	public static void setScoreBoard() {
		_board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = _board.registerNewObjective("Testing Server", "dummy", "Testing Plugin");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		_score = obj.getScore("Compteur : ");
		Score score2 = obj.getScore("Lul"); // GetScore pour set un score GG WP
		score2.setScore(0);
		_score.setScore(_nbConnect);
	}
	
	public static Scoreboard getScorboard(){
		return _board;
	}
	
	public static void addOneConnection() {
		_nbConnect++;
		_score.setScore(_nbConnect);
	}
}
