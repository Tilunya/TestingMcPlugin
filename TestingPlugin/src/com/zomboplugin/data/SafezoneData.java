package com.zomboplugin.data;

import org.bukkit.Location;

public class SafezoneData {

	private static String safezoneName = "";
	private static Location safeZoneLocation = null;
	private static double safeZoneRadius = 0;
	private Boolean isPvpDisabled = true;
	private Boolean isMobSpawnDisabled = true;

	public SafezoneData(Location pLocation, double pRadius) {
		safeZoneLocation = pLocation;
		safeZoneRadius = pRadius;
	}

	public static Boolean isLocationInsideSafezone(Location pLocation) {
		double squareX = (pLocation.getX() - safeZoneLocation.getX()) * (pLocation.getX() - safeZoneLocation.getX());
		double squareY = (pLocation.getZ() - safeZoneLocation.getZ()) * (pLocation.getZ() - safeZoneLocation.getZ());
		double squareRadius = safeZoneRadius * safeZoneRadius;
		
		return ((squareX + squareY) < squareRadius);
	}

	public static String getSafezoneName() {
		return safezoneName;
	}

	public static void setSafezoneName(String pSafezoneName) {
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
