package com.zomboplugin.timer;

import org.bukkit.scheduler.BukkitRunnable;

import com.zomboplugin.data.PersistData;
import com.zomboplugin.data.PlayerData;
import com.zomboplugin.scoreboard.StateScoreboard;

public class PlayerSleepTimer extends BukkitRunnable {

	@Override
	public void run() {
		synchronized (PersistData.get_playerConnectedList()) {
			for (int i = 0; i < PersistData.get_playerConnectedList().size(); i++) {
				PlayerData playerData = PersistData.get_playerConnectedList().get(i);
				if(playerData.get_state().is_sleep()) {
					playerData.get_state().sleep(playerData);
					StateScoreboard.updateScoreboard(playerData);
				}
			}
		}
	}

}
