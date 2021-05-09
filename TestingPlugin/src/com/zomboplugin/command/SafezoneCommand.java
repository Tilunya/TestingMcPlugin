package com.zomboplugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.zomboplugin.config.SafezoneConfig;
import com.zomboplugin.data.SafezoneData;

public class SafezoneCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args != null) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				System.out.println("Setting safezone...");
				//args[0] is the radius of the safezone, args[1] is the name of it
				SafezoneData szd = new SafezoneData(player.getLocation(), Double.parseDouble(args[0]), args[1].toString());
				SafezoneConfig.addSafezoneToList(szd);
				System.out.println("Safezone has been placed");
			}
		}else {
			sender.sendMessage("You must write /ZP_safezone_add <radius> <name> to create a new safezone.");
		}
		return true;
	}
}
