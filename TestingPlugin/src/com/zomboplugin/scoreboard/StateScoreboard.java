package com.zomboplugin.scoreboard;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import com.zomboplugin.data.PlayerData;

public class StateScoreboard {

	private static final String TIREDNESS_STATE_NAME = "Exhaution : ";
	private static final String HYDRATION_STATE_NAME = "Hydration : ";
	
	public static void initState(PlayerData player) {
		Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		UUID uuidScoreboard = UUID.randomUUID();
		String[] uuidSplit = uuidScoreboard.toString().split("-");
		Objective state = scoreboard.registerNewObjective(uuidSplit[uuidSplit.length-1], "dummy", "State");
		state.setDisplaySlot(DisplaySlot.SIDEBAR);
		player.get_player().setScoreboard(scoreboard);
		player.set_stateScoreboard(state);
		player.get_state().set_tirednessStr(TIREDNESS_STATE_NAME + Math.round(player.get_state().get_tiredness()) + " %");
		state.getScore(player.get_state().get_tirednessStr()).setScore(0);
		player.get_state().set_hydrationStr(HYDRATION_STATE_NAME + Math.round(player.get_state().get_hydration()) + " %");
		state.getScore(player.get_state().get_hydrationStr()).setScore(1);
	}
	
	public static void updateScoreboard(PlayerData player){
		Objective state = player.get_stateScoreboard();
		player.get_player().getScoreboard().resetScores(player.get_state().get_hydrationStr());
		player.get_player().getScoreboard().resetScores(player.get_state().get_tirednessStr());
		player.get_state().set_tirednessStr(TIREDNESS_STATE_NAME + Math.round(player.get_state().get_tiredness()) + " %");
		state.getScore(player.get_state().get_tirednessStr()).setScore(0);
		player.get_state().set_hydrationStr(HYDRATION_STATE_NAME + Math.round(player.get_state().get_hydration()) + " %");
		state.getScore(player.get_state().get_hydrationStr()).setScore(1);
	}
}
