package com.zomboplugin.timer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.StorageMinecart;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.zomboplugin.config.LootboxConfig;
import com.zomboplugin.data.LootboxData;

public class LootboxLandingTimer extends BukkitRunnable {

	private static World activeWorld;
	private static String radioMessage = "";

	@Override
	public void run() {
		for (StorageMinecart cart : activeWorld.getEntitiesByClass(StorageMinecart.class)) {
			if(cart.getName().equals(cart.getCustomName())) {
				cart.getInventory().clear();
				cart.remove();
			}
		}

		List<StorageMinecart> le = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			LootboxData lbd = new LootboxData(activeWorld);
			radioMessage += lbd.getCoordinates().toString() + " ";
			le.add((StorageMinecart) activeWorld.spawnEntity(lbd.getCoordinates(), EntityType.MINECART_CHEST));
		}
		
		for(Player p : activeWorld.getPlayers()) {
			p.sendMessage(ChatColor.DARK_GREEN + "RADIO : ALL THE AIR SUPPORTS WILL LAND AT " + radioMessage + ". TRY TO GET THE VALUABLE CONTAINT FOR YOUR OWN GOOD.");
		}

		for (StorageMinecart cart : le) {
			cart.setCustomName(cart.getName());
			Inventory cartInv = cart.getInventory();
			for(Entry<String, String> entry : LootboxConfig.defaultLootSettings.entrySet()) {
				cartInv.addItem(new ItemStack(Material.getMaterial(entry.getKey()), Integer.parseInt(entry.getValue())));
		    }
		}
	}

	public static void setActiveWorld(Server pServer) {
		List<World> worldList = pServer.getWorlds();
		for(World world : worldList) {
			if(Environment.NORMAL.equals(world.getEnvironment())) {
				activeWorld = world;
			}
		}
	}
}
