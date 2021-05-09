package com.zomboplugin.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import com.zomboplugin.data.SafezoneData;

public class SafezoneConfig extends UtilsConfig {

	private static String defaultFilePath = "plugins/ZomboPlugin/safezone.properties";
	public static Map<String, String> safezonesMap = new HashMap<String, String>();

	public static void writeSafezoneFile (String filePath) {
		String textToWrite = "";
		for (Map.Entry<String, String> entry : safezonesMap.entrySet()) {
			textToWrite += entry.getKey() + " : " + entry.getValue() + "\n";
		}
		writeFile(new File(filePath), textToWrite);
	}

	public static Map<String, String> readSafezoneFile(String filePath) {
		Map<String, String> szdm = new HashMap<String, String>();
		String line;
	    BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(filePath!=null?filePath:defaultFilePath));
			while ((line = reader.readLine()) != null) {
				System.out.println("line = " + line.toString());
			    String[] parts = line.split(":");
		        String key = parts[0].trim();
		        System.out.println("parts[1] = " + parts[1].toString());
		        String value = parts[1];
		        szdm.put(key, value);
			}

			//showing result into console
		    for (String key : szdm.keySet()) {
		        System.out.println(key + " = " + szdm.get(key));
		    }

			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return szdm;
	}

	public static void initializeSafezoneList() {
		safezonesMap = readSafezoneFile(defaultFilePath);
	}

	public static void addSafezoneToList(SafezoneData safezone) {
		safezonesMap.put(safezone.getSafezoneName(), safezone.getAllDataFromSafezone());
		writeSafezoneFile(defaultFilePath);
	}

	public static void removeSafezoneToList(SafezoneData safezone) {
		safezonesMap.remove(safezone.getSafezoneName());
		writeSafezoneFile(defaultFilePath);
	}

	public static double getSafeZoneRadiusFromSafezone(String keyName) {
		SafezoneData szd = getDataFromList(keyName);
		return szd.getSafeZoneRadius();
	}

	public static Location getLocationFromSafezone(String keyName) {
		SafezoneData szd = getDataFromList(keyName);
		return szd.getSafezoneLocation();
	}

	private static SafezoneData getDataFromList(String keyName) {
		SafezoneData szd = new SafezoneData();
		String[] datas = safezonesMap.get(keyName).split(";");

		szd.setSafezoneName(keyName);
		szd.setSafezoneLocation(new Location(Bukkit.getWorld(datas[0]), Double.parseDouble(datas[1]), Double.parseDouble(datas[2]), Double.parseDouble(datas[3])));
		szd.setSafeZoneRadius(Double.parseDouble(datas[4]));
		szd.setIsPvpDisabled(Boolean.parseBoolean(datas[5]));
		szd.setIsMobSpawnDisabled(Boolean.parseBoolean(datas[6]));

		return szd;
	}
}
