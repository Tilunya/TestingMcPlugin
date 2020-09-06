package com.zomboplugin.initializer;

import org.bukkit.plugin.java.JavaPlugin;

import com.zomboplugin.command.ApocalypseDifficultyCommand;
import com.zomboplugin.command.KitCommand;
import com.zomboplugin.command.SafezoneCommand;

public class CommandsInitializer {
	
	public static void InitializeCommands(JavaPlugin plugin) {
		if(plugin.getCommand("ZP_kit") != null) {
			plugin.getCommand("ZP_kit").setExecutor(new KitCommand());
		}
		else {
			System.err.println("Error on setting kitDiamond");
		}
		
		if(plugin.getCommand("ZP_apocalypse") != null) {
			plugin.getCommand("ZP_apocalypse").setExecutor(new ApocalypseDifficultyCommand());
		}
		else {
			System.err.println("Error on setting apocalypse difficulty");
		}
		
		if(plugin.getCommand("ZP_safezone_add") != null) {
			plugin.getCommand("ZP_safezone_add").setExecutor(new SafezoneCommand());
		}
		else {
			System.err.println("Error on setting safezone");
		}
		
//		if(plugin.getCommand("ZP_DataDeletePlayer") != null) { // Non pertinent
//			plugin.getCommand("ZP_DataDeletePlayer").setExecutor(new ApocalypseDifficultyCommand());
//		}
//		else {
//			System.err.println("Error on setting apocalypse difficulty");
//		}
	}

}
