package com.zomboplugin.data;

import java.util.Map.Entry;

import org.bukkit.Location;

import com.zomboplugin.config.SafezoneConfig;

public class SafezoneData {

	private static String safezoneName = "";
	private static Location safeZoneLocation = null;
	private static double safeZoneRadius = 0;
	private static Boolean isPvpDisabled = true;
	private static Boolean isMobSpawnDisabled = true;

	//Use that default constructor only for SafezoneConfig class if possible
	public SafezoneData() {}

	//Use that constructor for in game safezone management
	public SafezoneData(Location pLocation, double pRadius, String pName) {
		safeZoneLocation = pLocation;
		safeZoneRadius = pRadius;
		safezoneName = pName;
	}

	public static Boolean isLocationInsideSafezone(Location pLocation, String safezoneName) {
		if(safezoneName.isEmpty()) {
			boolean isPlayerInsideSafezone = false;
			double anonymousSquareX;
			double anonymousSquareZ;
			double anonymousSquareRadius;

			for (Entry<String, String> entry : SafezoneConfig.safezonesMap.entrySet()) {
				String entryKey = entry.getKey();
				anonymousSquareX = (pLocation.getX() - SafezoneConfig.getLocationFromSafezone(entryKey).getX()) * (pLocation.getX() - SafezoneConfig.getLocationFromSafezone(entryKey).getX());
				anonymousSquareZ = (pLocation.getZ() - SafezoneConfig.getLocationFromSafezone(entryKey).getZ()) * (pLocation.getZ() - SafezoneConfig.getLocationFromSafezone(entryKey).getZ());
				anonymousSquareRadius = SafezoneConfig.getSafeZoneRadiusFromSafezone(entryKey) * SafezoneConfig.getSafeZoneRadiusFromSafezone(entryKey);

				isPlayerInsideSafezone |= ((anonymousSquareX + anonymousSquareZ) < anonymousSquareRadius);
			}

			return isPlayerInsideSafezone;
		}

		double squareX = (pLocation.getX() - SafezoneConfig.getLocationFromSafezone(safezoneName).getX()) * (pLocation.getX() - SafezoneConfig.getLocationFromSafezone(safezoneName).getX());
		double squareZ = (pLocation.getZ() - SafezoneConfig.getLocationFromSafezone(safezoneName).getZ()) * (pLocation.getZ() - SafezoneConfig.getLocationFromSafezone(safezoneName).getZ());
		double squareRadius = SafezoneConfig.getSafeZoneRadiusFromSafezone(safezoneName) * SafezoneConfig.getSafeZoneRadiusFromSafezone(safezoneName);

		return ((squareX + squareZ) < squareRadius);
	}

	public String getAllDataFromSafezone() {
		return String.valueOf(safeZoneLocation.getWorld() + ";" + safeZoneLocation.getX()) + ";" + String.valueOf(safeZoneLocation.getY()) + ";" + String.valueOf(safeZoneLocation.getZ())
										   + ";" + String.valueOf(safeZoneRadius) + ";" + isPvpDisabled.toString() + ";" + isMobSpawnDisabled.toString();
	}

	public String getSafezoneName() {
		return safezoneName;
	}

	public void setSafezoneName(String pSafezoneName) {
		safezoneName = pSafezoneName;
	}

	public Location getSafezoneLocation() {
		return safeZoneLocation;
	}

	public void setSafezoneLocation(Location pLocation) {
		safeZoneLocation = pLocation;
	}

	public double getSafeZoneRadius() {
		return safeZoneRadius;
	}

	public void setSafeZoneRadius(double pSafeZoneRadius) {
		safeZoneRadius = pSafeZoneRadius;
	}

	public Boolean getIsPvpDisabled() {
		return isPvpDisabled;
	}

	public void setIsPvpDisabled(Boolean pSetPvp) {
		isPvpDisabled = pSetPvp;
	}

	public Boolean getIsMobSpawnDisabled() {
		return isMobSpawnDisabled;
	}

	public void setIsMobSpawnDisabled(Boolean pSetMobSpawn) {
		isMobSpawnDisabled = pSetMobSpawn;
	}
}
