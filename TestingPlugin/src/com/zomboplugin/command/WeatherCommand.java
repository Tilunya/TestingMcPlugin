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
						if(Boolean.parseBoolean(args[1])) world.setWeatherDuration(Integer.MAX_VALUE);
					}
					break;
				case "THUNDER" :
					for(World world : worlds) {
						world.setStorm(true);
						world.setWeatherDuration(Integer.MAX_VALUE);
						WeatherListener.canChangeWeather = true;
						world.setThundering(true);
						world.setWeatherDuration(Integer.MAX_VALUE);
					}
					break;
				case "SUN" :
					for(World world : worlds) {
						world.setThundering(false);
						world.setWeatherDuration(Integer.MAX_VALUE);
						WeatherListener.canChangeWeather = true;
						world.setStorm(false);
						world.setWeatherDuration(Integer.MAX_VALUE);
					}
					break;
				default :
					sender.sendMessage("The weather you type is not a valid one. You must write /zp_weather <sun|rain|thunder> <true|false>");
					break;
			}
		}
		return false;
	}

}
