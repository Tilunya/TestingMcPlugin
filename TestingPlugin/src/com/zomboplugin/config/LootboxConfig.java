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

public class LootboxConfig {
/* *
 * ATTRIBUTS
 * */

	private static final String pluginFolder = "plugins/";
	private static final String configurationFolderPath = "/ZomboPlugin";
	private static final String configurationFilePath = "/lootbox.properties";
	private static String defaultLootFile = "COOKED_BEEF = 3\n" + 
			"ROTTEN_FLESH = 		4\n" +
			"SPECTRAL_ARROW = 		2\n" +
			"BEETROOT_SEEDS = 		1\n" +
			"STICK = 				1\n" +
			"GOLDEN_CARROT  = 		3\n" +
			"CHAINMAIL_BOOTS = 		1\n" +
			"COOKIE = 				3\n" +
			"BOW = 					1\n" +
			"DRIED_KELP = 			3\n" +
			"MUSHROOM_STEW = 		1\n" +
			"POTION = 				1\n" +
			"COOKED_MUTTON  = 		3\n" +
			"DIAMOND =		 		2\n" +
			"CHAINMAIL_CHESTPLATE = 1\n" +
			"SHIELD = 				1\n" +
			"GOLDEN_APPLE = 		1\n" +
			"COCOA_BEANS = 			1\n" +
			"ARROW = 				12\n" +
			"PUMPKIN_SEEDS = 		1\n" +
			"CHAINMAIL_HELMET = 	1\n" +
			"BOWL = 				2\n" +
			"COOKED_SALMON = 		3\n" +
			"MELON_SEEDS = 			1\n" +
			"GLASS_BOTTLE = 		3\n" +
			"COOKED_CHICKEN = 		3\n" +
			"CHAINMAIL_LEGGINGS = 	1\n";

	public static Map<String, String> defaultLootSettings = new HashMap<String, String>(){
		{
			put("COOKED_BEEF", 			"3"); 
			put("ROTTEN_FLESH", 		"4");
			put("SPECTRAL_ARROW", 		"2");
			put("BEETROOT_SEEDS", 		"1");
			put("STICK", 				"1");
			put("GOLDEN_CARROT", 		"3");
			put("CHAINMAIL_BOOTS", 		"1");
			put("COOKIE", 				"3");
			put("BOW", 					"1");
			put("DRIED_KELP", 			"3");
			put("MUSHROOM_STEW", 		"1");
			put("POTION", 				"1");
			put("COOKED_MUTTON", 		"3");
			put("DIAMOND", 				"2");
			put("CHAINMAIL_CHESTPLATE", "1");
			put("SHIELD", 				"1");
			put("GOLDEN_APPLE", 		"1");
			put("COCOA_BEANS", 			"1");
			put("ARROW", 				"12");
			put("PUMPKIN_SEEDS", 		"1");
			put("CHAINMAIL_HELMET", 	"1");
			put("BOWL", 				"2");
			put("COOKED_SALMON", 		"3");
			put("MELON_SEEDS", 			"1");
			put("GLASS_BOTTLE", 		"3");
			put("COOKED_CHICKEN", 		"3");
			put("CHAINMAIL_LEGGINGS", 	"1");
		}
	};
/* *
 * PUBLIC METHODS
 * */

	public static void manageLootFiles() {
		File directory = new File(pluginFolder + configurationFolderPath);
		File configFile = new File(pluginFolder + configurationFolderPath + configurationFilePath);
		if(!directory.exists()) {
			directory.mkdir();
		}
		writeFile(configFile, defaultLootFile);
	}

	public static String getLootValue(String key) {
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

	public static void restoreDefaultLootSettings() {
		setLootSettings(defaultLootSettings);
	}

/* *
 * PRIVATE METHODS
 * */

	private static void setLootSettings(Map<String, String> LootSettings) {
		try(OutputStream output = new FileOutputStream(pluginFolder + configurationFolderPath + configurationFilePath)){
			Properties prop = new Properties();
			for (Map.Entry<String, String> entry : LootSettings.entrySet()) {
				prop.setProperty(entry.getKey(), entry.getValue());
			}
			prop.store(output, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeFile(File configFile, String text) {
		try {
			if(configFile.createNewFile()) {
				FileWriter myWriter = new FileWriter(configFile);
				myWriter.write(text);
				myWriter.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
