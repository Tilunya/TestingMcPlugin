package com.tilundev.testingplugin.initializer;

import org.bukkit.plugin.java.JavaPlugin;

import com.tilundev.testingplugin.listeners.HydrationChangeListener;
import com.tilundev.testingplugin.listeners.PlayerListener;
import com.tilundev.testingplugin.listeners.ZombieListener;

public class InitializerListener {
	
	public static void init(JavaPlugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(new PlayerListener(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new ZombieListener(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new HydrationChangeListener(), plugin);
	}

}
