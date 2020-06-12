package com.tilundev.testingplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import configurationFile.util.IOConfigFiles;

public class ApocalypseDifficulty implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args != null) {
			sender.sendMessage(args[0].toString());
			IOConfigFiles.chooseDifficulty(args[0].replace("class", ""));
		}else {
			sender.sendMessage("You must write easy, medium or hard after the command 'apocalypse' to choose the difficulty you want.");
		}
		return true;
	}
}
