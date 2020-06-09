package com.tilundev.testingplugin.initializer;

import org.bukkit.plugin.java.JavaPlugin;

import com.tilundev.testingplugin.listeners.PlayerListener;
import com.tilundev.testingplugin.listeners.WorldListener;
import com.tilundev.testingplugin.listeners.ZombieListener;

public class InitializerListener {
	
	public static void init(JavaPlugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(new PlayerListener(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new WorldListener(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new ZombieListener(), plugin);
	}

}
