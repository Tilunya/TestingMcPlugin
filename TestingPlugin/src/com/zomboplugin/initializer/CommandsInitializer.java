package com.zomboplugin.initializer;

import org.bukkit.plugin.java.JavaPlugin;

import com.zomboplugin.commands.ApocalypseDifficulty;
import com.zomboplugin.commands.CommandsKit;

public class CommandsInitializer {
	
	public static void InitializeCommands(JavaPlugin plugin) {
		if(plugin.getCommand("ZP_kit") != null) {
			plugin.getCommand("ZP_kit").setExecutor(new CommandsKit());
		}
		else {
			System.err.println("Error on setting kitDiamond");
		}
		
		if(plugin.getCommand("ZP_apocalypse") != null) {
			plugin.getCommand("ZP_apocalypse").setExecutor(new ApocalypseDifficulty());
		}
		else {
			System.err.println("Error on setting apocalypse difficulty");
		}
		
//		if(plugin.getCommand("ZP_DataDeletePlayer") != null) { // Non pertinent
//			plugin.getCommand("ZP_DataDeletePlayer").setExecutor(new ApocalypseDifficulty());
//		}
//		else {
//			System.err.println("Error on setting apocalypse difficulty");
//		}
	}

}
