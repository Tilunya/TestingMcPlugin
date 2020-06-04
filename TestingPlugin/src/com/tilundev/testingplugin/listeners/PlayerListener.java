package com.tilundev.testingplugin.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.tilundev.testingplugin.timer.TimerPlayer;

public class PlayerListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		TimerPlayer.addOneConnection();
		event.getPlayer().setScoreboard(TimerPlayer.getScorboard());
		System.out.println("Tick");
	}

}
