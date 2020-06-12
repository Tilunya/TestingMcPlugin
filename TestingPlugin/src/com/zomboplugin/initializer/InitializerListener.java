package com.zomboplugin.initializer;

import org.bukkit.plugin.java.JavaPlugin;

import com.zomboplugin.listeners.HydrationChangeListener;
import com.zomboplugin.listeners.PlayerListener;
import com.zomboplugin.listeners.WorldListener;
import com.zomboplugin.listeners.ZombieListener;

public class InitializerListener {
	
	public static void init(JavaPlugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(new PlayerListener(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new WorldListener(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new ZombieListener(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new HydrationChangeListener(), plugin);
	}

}
