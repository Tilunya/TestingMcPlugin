package com.zomboplugin.command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KitCommand implements CommandExecutor {

	/**
	 * @param sender :  Whatever is sending the command
	 * @param command : Command who been called
	 * @param label : Represent the exact first word 
	 * @param args : arguments of command
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			ItemStack item = new ItemStack(Material.DIAMOND, 64);
			player.getInventory().addItem(item);
			player.sendMessage("YOU BRIGHT LIKE A DIAMOND");
		}
		return true;
	}

}
