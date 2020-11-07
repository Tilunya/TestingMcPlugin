package com.zomboplugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.zomboplugin.data.SafezoneData;

public class SafezoneCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args != null) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				player.sendMessage("Setting safezone...");
				SafezoneData szd = new SafezoneData(player.getLocation(), Double.parseDouble(args[0]), args[1]);
				player.sendMessage("Safezone has been placed");
			}
		}else {
			sender.sendMessage("You must write easy, medium or hard after the command 'apocalypse' to choose the difficulty you want.");
		}
		return true;
	}
}
