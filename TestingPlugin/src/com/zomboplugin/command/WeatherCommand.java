package com.zomboplugin.command;

import java.util.List;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.zomboplugin.listener.WeatherListener;

public class WeatherCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		WeatherListener.canChangeWeather = true;
		List<World> worlds = sender.getServer().getWorlds();
		if(args != null) {
		System.out.println(args.toString());
			switch(args[0].toString().toUpperCase()) {
				case "RAIN" :
					for(World world : worlds) {
						world.setStorm(true);
					}
					break;
				case "THUNDER" :
					for(World world : worlds) {
						world.setThundering(true);
					}
					break;
				case "SUN" :
					for(World world : worlds) {
						world.setStorm(false);
						WeatherListener.canChangeWeather = true;
						world.setThundering(false);
					}
					break;
				default :
					sender.sendMessage("You must write rain, thunder or sun to change weather with the command.");
					break;
			}
		}
		return false;
	}

}
