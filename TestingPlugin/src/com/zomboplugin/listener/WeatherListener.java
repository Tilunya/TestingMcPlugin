package com.zomboplugin.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherListener implements Listener {

	public static boolean canChangeWeather = false;
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent weatherEvent) {
		if(canChangeWeather) {
			canChangeWeather = false;
		} else {
			weatherEvent.setCancelled(true);
		}
	}
}
