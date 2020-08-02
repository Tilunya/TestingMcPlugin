package com.zomboplugin.initializer;

import org.bukkit.plugin.java.JavaPlugin;

import com.zomboplugin.listener.HydExhChangeListener;
import com.zomboplugin.listener.PlayerListener;
import com.zomboplugin.listener.WorldListener;
import com.zomboplugin.listener.ZombieListener;

public class ListenersInitializer {
	
	public static void init(JavaPlugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(new PlayerListener(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new WorldListener(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new ZombieListener(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new HydExhChangeListener(), plugin);
	}

}
