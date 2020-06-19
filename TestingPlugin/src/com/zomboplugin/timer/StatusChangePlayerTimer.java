package com.zomboplugin.timer;

import org.bukkit.scheduler.BukkitRunnable;

import com.zomboplugin.data.PersistData;
import com.zomboplugin.data.PlayerData;


public class StatusChangePlayerTimer extends BukkitRunnable {

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
