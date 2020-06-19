package com.zomboplugin.data.database;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;

import com.zomboplugin.data.PlayerData;

public class PlayerDatabaseManager extends HibernateUtil {
	
	public PlayerDatabaseManager() {
		this.classInUse = PlayerDatabase.class;
		this.init();
	}
	
	public void saveOrUpdatePlayerDatabase(PlayerData player) {
		try {
			this.transaction = session.beginTransaction();
			PlayerDatabase playerDatabase = new PlayerDatabase();
			playerDatabase.createData(player);
			
			session.saveOrUpdate(playerDatabase);
			session.flush();
			this.transaction.commit();
		}
		catch (Exception e) {
			System.err.println("Save Player Data failled !");
			if (this.transaction != null) {
				this.transaction.rollback();
				System.err.println("Rollback done");
				this.transaction = null;
			}
		}
	}
	
	public PlayerData findOnePlayerDatabase(PlayerData player) {
		this.transaction = session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<PlayerDatabase> query = builder.createQuery(PlayerDatabase.class);
		Root<PlayerDatabase> root = query.from(PlayerDatabase.class);
		query.select(root).where(builder.equal(root.get(PlayerDatabase.TABLE_UUID_VAR), player.get_player().getUniqueId()));
		Query<PlayerDatabase> q = session.createQuery(query);
		PlayerDatabase playerDatabase = q.getSingleResult();
		player = playerDatabase.updatePlayerData(player);
		this.transaction.commit();
	
		return player;
	}
	
	/**
	 * TODO a tester
	 * @param player
	 * @return
	 */
	public boolean deleteOnePlayer(PlayerData player) {
		this.transaction = session.beginTransaction();
		session.delete(player);
		this.transaction.commit();
		return true;
	}

}
