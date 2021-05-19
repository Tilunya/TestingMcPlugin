package com.zomboplugin.listener;

import java.util.Iterator;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import com.zomboplugin.config.IOFileConfig;
import com.zomboplugin.data.InfectedData;
import com.zomboplugin.data.PersistData;
import com.zomboplugin.data.PlayerData;
import com.zomboplugin.data.database.manager.PlayerDatabaseManager;
import com.zomboplugin.listener.event.PlayerEvent;
import com.zomboplugin.scoreboard.StateScoreboard;

public class PlayerListener implements Listener {
	
	//TODO : Those two variables can be moved into a configuration file.
	private Material bandage = Material.BREAD;
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		PlayerDatabaseManager pdm = new PlayerDatabaseManager();
		PlayerData playerData = PersistData.addPersistantPlayer(player);
		playerData = pdm.findOnePlayerDatabase(playerData);
		
		if(playerData == null) {
			PersistData.removePersistantPlayer(player);
			playerData = PersistData.addPersistantPlayer(player);
			pdm.saveOrUpdatePlayerDatabase(playerData);
		} 

		if(playerData.get_state().is_infected()) {
			InfectedData.addInfectedPlayerToList(playerData);
		}
		StateScoreboard.initState(playerData);
		pdm.closeSession();
	}

	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent pqe) {
		Player player = pqe.getPlayer();
		PlayerData playerData = PersistData.getPlayerData(player);
		PlayerDatabaseManager pdm = new PlayerDatabaseManager();
		pdm.saveOrUpdatePlayerDatabase(playerData);
		pdm.closeSession();
		PersistData.removePersistantPlayer(player);
		InfectedData.removeInfectedPlayerToList(playerData);
	}
	
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent block) {
		// Search for a better method
//		List<Material> logs = new ArrayList<>();
//		logs.add(Material.ACACIA_LOG);
//		logs.add(Material.BIRCH_LOG);
//		logs.add(Material.DARK_OAK_LOG);
//		logs.add(Material.JUNGLE_LOG);
//		logs.add(Material.OAK_LOG);
//		logs.add(Material.SPRUCE_LOG);
//		logs.add(Material.STRIPPED_ACACIA_LOG);
//		logs.add(Material.STRIPPED_BIRCH_LOG);
//		logs.add(Material.STRIPPED_DARK_OAK_LOG);
//		logs.add(Material.STRIPPED_JUNGLE_LOG);
//		logs.add(Material.STRIPPED_OAK_LOG);
//		logs.add(Material.STRIPPED_SPRUCE_LOG);
//		
//		if (logs.contains(block.getBlock().getType())) {
//			ScoreboardObjectives.addScore(0);
//		} 
//		else if (block.getBlock().getType() == Material.STONE) {
//			ScoreboardObjectives.addScore(1);
//		}
//		else if (block.getBlock().getType() == Material.COAL_ORE) {
//			ScoreboardObjectives.addScore(2);
//		}
	}
	
	@EventHandler
	public void onPlayerItemConsume(PlayerItemConsumeEvent pic) {
		if(pic.getItem().getType().equals(Material.POTION)) {
			PotionMeta potionMeta = (PotionMeta) pic.getItem().getItemMeta();
			if(potionMeta.getBasePotionData().getType().equals(PotionType.WATER)) {
				PlayerData playerData = PersistData.getPlayerData(pic.getPlayer());
				if(playerData != null) {
					playerData.get_state().drinkClearWater(playerData);
					StateScoreboard.updateScoreboard(playerData);
				}
			}
		}
	}
	
	
	/**
	 * Event when player fall from a high place.
	 * @param entityDamaged
	 */
	@EventHandler
	public void onPlayerFall(EntityDamageEvent entityDamaged) {
		if(entityDamaged.getCause().equals(DamageCause.FALL) && entityDamaged.getDamage() > Integer.parseInt(IOFileConfig.getConfigValue("FALLING_LIMIT"))) {
			PlayerEvent.brokenHarm((LivingEntity) entityDamaged.getEntity());
		}
	}
	
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent flc) {
		if(flc.getEntityType().equals(EntityType.PLAYER)) {
			Player player = (Player) flc.getEntity();
			PlayerData playerData = PersistData.getPlayerData(player);
			playerData.get_state().lostHydration(playerData);
			playerData.get_state().lostTiredness(playerData);
			StateScoreboard.updateScoreboard(playerData);
			
		}
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent pre) { // Do this on resurect
		PlayerData playerData = PersistData.getPlayerData(pre.getPlayer());
		playerData.get_state().initStatePlayer();
		playerData.get_player().setWalkSpeed(PlayerData.DEFAULT_WALKING_SPEED);
		StateScoreboard.updateScoreboard(playerData);
		PlayerDatabaseManager pdm = new PlayerDatabaseManager();
		pdm.saveOrUpdatePlayerDatabase(playerData);
		pdm.closeSession();
		
	}

	/**
	 * Event when player gets a violent hit.
	 * @param entityHit
	 */
	@EventHandler
	public void onPlayerHit(EntityDamageByEntityEvent entityHit) {
		if((entityHit.getCause().equals(DamageCause.ENTITY_ATTACK) || 
				entityHit.getCause().equals(DamageCause.PROJECTILE)) && entityHit.getDamage() > Integer.parseInt(IOFileConfig.getConfigValue("HIT_LIMIT"))){
			PlayerEvent.brokenLeg((LivingEntity) entityHit.getEntity());
		}
	}
	
	/**
	 * Event when player use a bandage (consumable item) for healing purpose.
	 * @param consumeItem
	 */
	@EventHandler
	public void onPlayerHealing(PlayerItemConsumeEvent consumeItem) {
		if(consumeItem.getItem().getType().equals(bandage)) {
			PlayerEvent.playerHealingInjuries((LivingEntity) consumeItem.getPlayer());
		}
	}
	
	
	/**
	 * Event when player enter in a bed
	 * @param pbee
	 */
	@EventHandler
	public void onPlayerBedEnter(PlayerBedEnterEvent pbee) {
		pbee.setUseBed(Result.ALLOW);
		pbee.getPlayer().sendMessage("You sleep. Press \"Sneak\" button for leave this state");
		PlayerData playerData = PersistData.getPlayerData(pbee.getPlayer());
		playerData.get_state().set_sleep(true);
		playerData.refreshSpeed();
		playerData.get_player().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 10));
	}
	
	/**
	 * Event when player enter in a bed
	 * @param pbee
	 */
	@EventHandler
	public void onPlayerToogleSneak(PlayerToggleSneakEvent ptse) {
		PlayerData playerData = PersistData.getPlayerData(ptse.getPlayer());
		if(playerData.get_state().is_sleep()) {
			playerData.get_state().set_sleep(false);
			playerData.refreshSpeed();
			playerData.get_player().removePotionEffect(PotionEffectType.BLINDNESS);
		}
	}

	/**
	 * Event when the player consume an item
	 * Manage healing of the infection by detecting if the player consume a weakness potion and a golden apple.
	 * @param e
	 */
	@EventHandler
	public void onItemConsume (PlayerItemConsumeEvent e) {
	    ItemStack consumed = e.getItem();
	    Player player = e.getPlayer();

	    if(Material.POTION.equals(consumed)) {
	    	PotionMeta pm = ((PotionMeta) consumed.getItemMeta());
	    	if(pm.getBasePotionData().getType().equals(PotionType.WEAKNESS)) {
	    		InfectedData.isPlayerCanBeHealed = true;
	    	}
		    System.out.println("InfectedData.isPlayerCanBeHealed" + InfectedData.isPlayerCanBeHealed );
	    }
	    else if(Material.GOLDEN_APPLE.equals(consumed)) {
	    	if (InfectedData.isPlayerCanBeHealed) {
	    		InfectedData.removeInfection(PersistData.getPlayerData(player));
	    	}
	    }
	    player.sendMessage("OnItemConsume happened");
	    System.out.println("OnItemConsume happened");
	}
}