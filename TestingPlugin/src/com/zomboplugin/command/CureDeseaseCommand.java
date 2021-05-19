package com.zomboplugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.zomboplugin.data.InfectedData;
import com.zomboplugin.data.PersistData;
import com.zomboplugin.data.PlayerData;

public class CureDeseaseCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args != null) {
			sender.sendMessage(args[0].toString());
			PlayerData p = PersistData.getPlayerData((Player) sender);
			InfectedData.removeInfection(p);
			sender.sendMessage("Player got removed from infected list.");
		}else {
			sender.sendMessage("Error happen when the player was removed from the infected list.");
		}
		return true;
	}

}
