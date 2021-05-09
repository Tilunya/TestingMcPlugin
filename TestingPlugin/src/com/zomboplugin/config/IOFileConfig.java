package com.zomboplugin.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class IOFileConfig extends UtilsConfig {
/* *
 * ATTRIBUTS
 * */
	private static final String pluginFolder = "plugins/";
	private static final String configurationFolderPath = "/ZomboPlugin";
	private static final String configurationFilePath = "/config.properties";
	private static String defaultConfigFile = "INFECTION_CHANCE_PERCENT = 85\n" + 
			"INFECTION_SPEED = 1\n" + 
			"ZOMBIE_AGGRO_RANGE = 80\n" + 
			"SLOW_EFFECT_ZOMBIE_GRAB = 0\n" + 
			"NAUSEA_EFFECT_ZOMBIE_GRAB = 0\n" + 
			"POISON_EFFECT_ZOMBIE_GRAB = 0\n" + 
			"FALLING_LIMIT = 15\n" + 
			"HIT_LIMIT = 15\n" + 
			"SLOW_EFFECT_FALLING_MALUS = 0\n" + 
			"SLOWDIGGING_EFFECT_HIT_MALUS = 0\n" +
			"DEHYDRATION_SPEED = 0\n" + 
			"WITHER_EFFECT_DEHYDRATION = 0\n" + 
			"NAUSEA_DEHYDRATION = 0\n" +
			"LOOTBOX_SPAWN_TIME = 15\n" +
			"LOOTBOX_FALL_TIME = 5\n" +
			"MAP_X_LENGTH = 20\n" +
			"MAP_Z_LENGTH = 20";
	
	private static Map<String, String> easySettings = new HashMap<String, String>(){
		{
			put("INFECTION_CHANCE_PERCENT",		 "85");
			put("INFECTION_SPEED",				 "1");
			put("ZOMBIE_AGGRO_RANGE", 			 "80");
			put("SLOW_EFFECT_ZOMBIE_GRAB",		 "0");
			put("NAUSEA_EFFECT_ZOMBIE_GRAB",	 "0");
			put("POISON_EFFECT_ZOMBIE_GRAB",	 "0");
			put("FALLING_LIMIT",				 "15");
			put("HIT_LIMIT",					 "15");
			put("SLOW_EFFECT_FALLING_MALUS",	 "0");
			put("SLOWDIGGING_EFFECT_HIT_MALUS",	 "0");
			put("DEHYDRATION_SPEED",			 "0");
			put("WITHER_EFFECT_DEHYDRATION",	 "0");
			put("NAUSEA_DEHYDRATION",			 "0");
			put("LOOTBOX_SPAWN_TIME",			 "15");
			put("LOOTBOX_FALL_TIME",			 "5");
			put("MAP_X_LENGTH",					 "20");
			put("MAP_Z_LENGTH",					 "20");
		}
	};
	
	private static Map<String, String> mediumSettings = new HashMap<String, String>(){
		{
			put("INFECTION_CHANCE_PERCENT",		 "90");
			put("INFECTION_SPEED",				 "2");
			put("ZOMBIE_AGGRO_RANGE", 			 "100");
			put("SLOW_EFFECT_ZOMBIE_GRAB",		 "1");
			put("NAUSEA_EFFECT_ZOMBIE_GRAB",	 "1");
			put("POISON_EFFECT_ZOMBIE_GRAB",	 "1");
			put("FALLING_LIMIT",				 "11");
			put("HIT_LIMIT",					 "11");
			put("SLOW_EFFECT_FALLING_MALUS",	 "1");
			put("SLOWDIGGING_EFFECT_HIT_MALUS",	 "1");
			put("DEHYDRATION_SPEED",			 "2");
			put("WITHER_EFFECT_DEHYDRATION",	 "1");
			put("NAUSEA_DEHYDRATION",			 "1");
			put("LOOTBOX_SPAWN_TIME",			 "15");
			put("LOOTBOX_FALL_TIME",			 "5");
			put("MAP_X_LENGTH",					 "20");
			put("MAP_Z_LENGTH",					 "20");
		}
	};
	
	private static Map<String, String> hardSettings = new HashMap<String, String>(){
		{
			put("INFECTION_CHANCE_PERCENT",		 "100");
			put("INFECTION_SPEED",				 "4");
			put("ZOMBIE_AGGRO_RANGE", 			 "120");
			put("SLOW_EFFECT_ZOMBIE_GRAB",		 "2");
			put("NAUSEA_EFFECT_ZOMBIE_GRAB",	 "2");
			put("POISON_EFFECT_ZOMBIE_GRAB",	 "2");
			put("FALLING_LIMIT",				 "6");
			put("HIT_LIMIT",					 "6");
			put("SLOW_EFFECT_FALLING_MALUS",	 "2");
			put("SLOWDIGGING_EFFECT_HIT_MALUS",	 "2");
			put("DEHYDRATION_SPEED",			 "2");
			put("WITHER_EFFECT_DEHYDRATION",	 "2");
			put("NAUSEA_DEHYDRATION",			 "2");
			put("LOOTBOX_SPAWN_TIME",			 "15");
			put("LOOTBOX_FALL_TIME",			 "5");
			put("MAP_X_LENGTH",					 "20");
			put("MAP_Z_LENGTH",					 "20");
		}
	};

/* *
 * PUBLIC METHODS
 * */
	
	/**
	 * Create the configuration file if it's not exists already. Put easy difficulty by default.
	 * @author Fabibulabix
	 */
	public static void manageConfigurationFiles() {
		File directory = new File(pluginFolder + configurationFolderPath);
		File configFile = new File(pluginFolder + configurationFolderPath + configurationFilePath);
		if(!directory.exists()) {
			directory.mkdir();
		}
		writeFile(configFile, defaultConfigFile);
	}
	
	/**
	 * Set the difficulty of the apocalypse in regards to the difficulty choosen from the ingame command.
	 * @param choosenDifficulty : chooser ingame difficulty (writen in the chat).
	 * @author Fabibulabix
	 */
	public static void chooseDifficulty(String choosenDifficulty) {
		switch(choosenDifficulty) {
			case "medium":
				setDifficulty(mediumSettings);
				break;
			case "hard":
				setDifficulty(hardSettings);
				break;
			default:
				setDifficulty(easySettings);
		}
	}
	
	/**
	 * Getter the plugin can use to obtain the current value of a given key.
	 * @param key : name of the choosen property.
	 * @return actuel value within the property file.
	 * @author Fabibulabix
	 */
	public static String getConfigValue(String key) {
		String value = "";
		try(InputStream input = new FileInputStream(pluginFolder + configurationFolderPath + configurationFilePath)){
			Properties prop = new Properties();
			prop.load(input);
			value = prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

/* *
 * PRIVATE METHODS
 * */
	
	/**
	 * Write into the configuration file all the modifications about apocalypse difficulty.
	 * @param difficultySettings : Map containing new values of the properties.
	 * @author Fabibulabix
	 */
	private static void setDifficulty(Map<String, String> difficultySettings) {
		try(OutputStream output = new FileOutputStream(pluginFolder + configurationFolderPath + configurationFilePath)){
			Properties prop = new Properties();
			for (Map.Entry<String, String> entry : difficultySettings.entrySet()) {
				prop.setProperty(entry.getKey(), entry.getValue());
			}
			prop.store(output, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
