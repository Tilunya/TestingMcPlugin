package com.tilundev.testingplugin.timer;

import org.bukkit.scheduler.BukkitRunnable;

import com.tilundev.testingplugin.data.PersistData;
import com.tilundev.testingplugin.data.PlayerData;
import com.tilundev.testingplugin.scoreboard.StateScoreboard;

public class TimerLostStatusPlayer extends BukkitRunnable {

	@Override
	public void run() {
		synchronized (PersistData.get_playerConnectedList()) {
			for (int i = 0; i < PersistData.get_playerConnectedList().size(); i++) {
				PlayerData playerData = PersistData.get_playerConnectedList().get(i);
				playerData.naturaExhaustion();
			}
		}
	}

}
