package com.zomboplugin.data.database;

import org.hibernate.Session;

import com.zomboplugin.data.PlayerData;

public class PlayerDatabaseManager {
	
	public static void createAndStorePlayerDatabase(PlayerData player) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		PlayerDatabase playerDatabase = new PlayerDatabase();
		playerDatabase.createData(player);
		
		session.saveOrUpdate(playerDatabase);
		
		session.getTransaction().commit();
	}

}
