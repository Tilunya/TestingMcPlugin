package com.zomboplugin.data.database.util;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	protected Session session = null;
	protected SessionFactory sessionFactory = null;
	protected Configuration config = null;
	protected Transaction transaction = null;
	protected Class<?> classInUse = null;
	
	protected void init() {
		this.config = new Configuration();
		this.config.addAnnotatedClass(classInUse);
		this.config.setProperty("hibernate.connection.driver_class", "org.sqlite.JDBC");
		this.config.setProperty("hibernate.connection.url", "jdbc:sqlite:plugins/ZombocalypseConfig/db/zomboplugin.db");
		this.config.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLiteDialect");
		this.config.setProperty("hibernate.hbm2ddl.auto", "update");
		this.openSession();
	}
	
	protected void openSession() {
		this.sessionFactory = config.buildSessionFactory();
		this.session = sessionFactory.openSession();
	}
	
	public void closeSession( ) {
		this.session.close();
		this.sessionFactory.close();
		this.session = null;
		this.sessionFactory = null;
	}
	
	public void restartSession() {
		this.session.close();
		this.sessionFactory.close();
		this.session = null;
		this.sessionFactory = null;
		this.init();
	}
}
